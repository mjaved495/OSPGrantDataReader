package edu.cornell.osptographdatabuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.opencsv.CSVReader;

import edu.cornell.ospmodel.Award;
import edu.cornell.ospmodel.Department;
import edu.cornell.ospmodel.Investigator;
import edu.cornell.ospmodel.Person_NetIdDeptMap;

public class DataReader {

	public Map<String, Award> awd_entries;
	public Map<String, Investigator> inv_entries;
	
	public static String INPUT_AWRAD_FILE = "resources/input/Awards.xml";
	public static String INPUT_INVESTIGATOR_FILE = "resources/input/Investigators.xml";
	public static File   PERSON_NETID_DEPT_MAPPER_FILE = new File("resources/input/Person-05032016.csv");
	public static File   DEPARTMENT_MAPPER_FILE = new File("resources/input/deptMapper.csv");
	public static String OUTPUT_FILE = "resources/output/AwdInv-all.txt";
	
	public static void main(String[] args) {
		DataReader reader = new DataReader();
		AwardsDataReader obj1 = new AwardsDataReader();
		reader.awd_entries = obj1.loadAwardData(new File(INPUT_AWRAD_FILE));
		
		saveDistinctSponsors(reader.awd_entries, "resources/output/AwdDistinctSponsorNames.csv");		
		saveSponsorsFlow(reader.awd_entries, "resources/output/AwdSponsors.csv");
		InvestigatorDataReader obj2 = new InvestigatorDataReader();
		reader.inv_entries = obj2.loadInvestigatorData(new File(INPUT_INVESTIGATOR_FILE));
		
		List<Person_NetIdDeptMap> list = reader.readPersonNetIdDeptMapperFile(PERSON_NETID_DEPT_MAPPER_FILE);
		reader.mergeData(list, reader.awd_entries, reader.inv_entries, new File(OUTPUT_FILE));
	}

	public static void saveSponsorsFlow(Map<String, Award> awd_entries, String filePath) {
		PrintWriter printWriter;
		try {
			printWriter = new PrintWriter (filePath);	
			for(Award awd: awd_entries.values()){
				printWriter.println("\""+
						awd.getAWARD_PROP_SPONSOR_ID()+"\",\""+
						awd.getAWARD_PROP_SPONSOR_NAME()+"\",\""+
						awd.getFLOW_THROUGH_SPONSOR_ID()+"\",\""+
						awd.getFLOW_THROUGH_SPONSOR_NAME() + "\",\""+
						awd.getSP_LEV_1()+ "\",\""+
						awd.getSP_LEV_2()+"\",\""+
						awd.getSP_LEV_3()+"\""
						);
			}
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
	}
	
	public static void saveDistinctSponsors(Map<String, Award> awd_entries, String filePath) {
		Set<String> distinctSponsors = new HashSet<String>();
		for(Award awd: awd_entries.values()){
			distinctSponsors.add(awd.getAWARD_PROP_SPONSOR_NAME());
		}
		PrintWriter printWriter;
		try {
			printWriter = new PrintWriter (filePath);	
			for(String sponsor: distinctSponsors){
				printWriter.println("\""+sponsor+"\"");
			}
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
	}

	public void mergeData(List<Person_NetIdDeptMap> list, Map<String, Award> awd_entries2, Map<String, Investigator> inv_entries2, File file) {
		PrintWriter printWriter;
		int counter = 0;
		try {
			Set<String> investigators = inv_entries2.keySet();
			printWriter = new PrintWriter (file.getAbsolutePath());	
			for(String inv: investigators){
				String prjId = inv.substring(0, inv.indexOf("-"));
				Award award = awd_entries2.get(prjId);
				Investigator investigator = inv_entries2.get(inv);
				Person_NetIdDeptMap entity = findMappedPerson(list, investigator.getINVPROJ_INVESTIGATOR_NETID());
				//commented out because we do not want to capture a grant which is linked to a person who does not exist in VIVO. 
				if(entity == null){
					//System.err.println("Department map not found for "+ investigator.getINVPROJ_INVESTIGATOR_NETID());
					//printWriter.println("\"\",\"\",\"\",\"\","+investigator.toString()+","+award.toString()); 
				}
//				else{
//					String deptId = award.getAWARD_PROP_DEPARTMENT_ID();
//					Department deptURI = getDeptURIFromControlFile(deptId, DEPARTMENT_MAPPER_FILE);
//					if(deptURI == null){
//						printWriter.println("\""+entity.getCollege()+"\",\""+entity.getPersonURI()+"\",\""+entity.getDept()+"\",\""+entity.getDeptURI()+"\",\"\","
//								+investigator.toString()+","+award.toString()); 
//						counter++;
//					}else{
//						printWriter.println("\""+entity.getCollege()+"\",\""+entity.getPersonURI()+"\",\""+entity.getDept()+"\",\""+entity.getDeptURI()+"\","
//								+deptURI.getVivoURI()+","+investigator.toString()+","+award.toString()); 
//						counter++;
//					}
//					
//				}
				else{
					// Mapping the Department coming from the OSP Feed.
					String deptId = award.getAWARD_PROP_DEPARTMENT_ID();
					Department deptURI = getDeptURIFromControlFile(deptId, DEPARTMENT_MAPPER_FILE);
					if(deptURI == null){
						printWriter.println(entity.getCollege()+"\t"+entity.getPersonURI()+"\t"+entity.getDept()+"\t"+entity.getDeptURI()+"\t\t"
								+investigator.toString()+"\t"+award.toString()); 
						counter++;
					}else{
						printWriter.println(entity.getCollege()+"\t"+entity.getPersonURI()+"\t"+entity.getDept()+"\t"+entity.getDeptURI()+"\t"+deptURI.getVivoURI()+"\t"
								+investigator.toString()+"\t"+award.toString()); 
						counter++;
					}
					
				}
				
			}
			printWriter.close();
			System.out.println(counter+ " number of rows added.");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
	}

	private Department getDeptURIFromControlFile(String deptId, File file) {
		Map<String, Department> map = generateDeptMap(file);
		return map.get(deptId);
	}

	private Map<String, Department> generateDeptMap(File file) {
		Map<String, Department> map = new HashMap<String, Department>(); 
		CSVReader reader;
		try {
			reader = new CSVReader(new FileReader(file),',','\"');
			String [] nextLine;	
			while ((nextLine = reader.readNext()) != null) {
				String deptId = nextLine[0].trim();
				String deptName = nextLine[1].trim();
				String rollupName = nextLine[2].trim();
				String deptVIVOURI = nextLine[3].trim();
				Department dept = new Department(deptId, deptName, rollupName, deptVIVOURI);
				map.put(deptId, dept);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}

	private Person_NetIdDeptMap findMappedPerson(List<Person_NetIdDeptMap> list, String invproj_INVESTIGATOR_NETID) {
		for(Person_NetIdDeptMap obj: list){
			if(obj.getNetId().toUpperCase().equals(invproj_INVESTIGATOR_NETID.toUpperCase())){
				return obj;
			}
		}
		return null;
	}

	public List<Person_NetIdDeptMap> readPersonNetIdDeptMapperFile(File file) {
		List<Person_NetIdDeptMap> list = new ArrayList<Person_NetIdDeptMap>();
		CSVReader reader;
		try {
			reader = new CSVReader(new FileReader(file),',','\"');
			String [] nextLine;	
			Person_NetIdDeptMap obj  = null;
			while ((nextLine = reader.readNext()) != null) {
				obj = new Person_NetIdDeptMap();
				obj.setNetId(nextLine[0].trim());
				obj.setPersonURI(nextLine[1].trim());
				obj.setDept(nextLine[2].trim());
				obj.setDeptURI(nextLine[3].trim());
				obj.setCollege(nextLine[4].trim());
				list.add(obj);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	
//	private static void saveData(Map<String, Award> deptId_2_dept_map, File dEPARTMENT_MAPPER_FILE2) {
//		PrintWriter printWriter;
//		try {
//			printWriter = new PrintWriter (dEPARTMENT_MAPPER_FILE2.getAbsolutePath());	
//			Set<String> set = deptId_2_dept_map.keySet();
//			for(Iterator<String> i = set.iterator(); i.hasNext();){
//				String key = i.next();
//				Award award = deptId_2_dept_map.get(key);
//				printWriter.println("\""+key+"\",\""+award.getAWARD_PROP_DEPARTMENT()+"\",\""+award.getROLLUP_DEPT_NAME()+"\"");
//			}
//			printWriter.close ();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//	}

//	private static Map<String, Award> createDeptMap(Collection<Award> awards) {
//		Map<String,Award> deptId_2_dept_map = new HashMap<String,Award>();
//		for(Award award: awards){
//			deptId_2_dept_map.put(award.getAWARD_PROP_DEPARTMENT_ID(), award);
//		}
//		return deptId_2_dept_map;
//	}
	
//	private Set<String> viewData() {
//		Collection<Award> data = awd_entries.values();
//		Set<String> set = new HashSet<String>();
//		for(Iterator<Award> it = data.iterator(); it.hasNext();){
//			Award award = it.next();
//			set.add(award.getAWARD_PROP_DEPARTMENT_ID()+","+award.getAWARD_PROP_DEPARTMENT()+","+award.getROLLUP_DEPT_NAME());
//		}
//		System.out.println(set.size());
//		return set;
//	}

//	private void display(String entry) {
//		Collection<Award> data = awd_entries.values();
//		Set<String> set = new HashSet<String>();
//		for(Iterator<Award> it = data.iterator(); it.hasNext();){
//			Award award = it.next();
//			if(entry.equals("AWARD_STATUS")){
//				String d = award.getAWARD_STATUS();
//				if(!set.contains(d)){
//					System.out.println(d);
//					set.add(d);
//				}
//			}
//		}
//	}
}
