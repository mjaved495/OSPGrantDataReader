package edu.cornell.osptographdatabuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import edu.cornell.ospmodel.Award;

public class AwardsDataReader {

	private Document doc;
	private int count = 0;
	
	
	public static void main(String args[]){
		//Set<String> set = obj.viewData();
		//obj.writeViewData(set);
		//obj.display("AWARD_STATUS");
	}

	public Map<String, Award> loadAwardData(File xmlFile){
		Map<String, Award> entries = new HashMap<String, Award>();
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(xmlFile);
			NodeList entryList = doc.getElementsByTagName("Award");	
			Award entry;	

			for(int index=0; index< entryList.getLength(); index++){
				entry = new Award();
				Node node = entryList.item(index);
				//System.out.println(node.getNodeName());
				Element eElement = (Element) node;

				String projectId = eElement.getElementsByTagName("AWARD_PROP_PROJECT_ID").item(0).getTextContent();
				//System.out.println(unitCode);
				entry.setAWARD_PROP_PROJECT_ID(projectId);
				String propId = eElement.getElementsByTagName("AWARD_PROPOSAL_ID").item(0).getTextContent();
				//System.out.println(deptCode);
				entry.setAWARD_PROPOSAL_ID(propId);
				String propTitle = eElement.getElementsByTagName("AWARD_PROP_FULL_TITLE").item(0).getTextContent();
				//System.out.println(mapCode);
				entry.setAWARD_PROP_FULL_TITLE(propTitle);
				String deptId = eElement.getElementsByTagName("AWARD_PROP_DEPARTMENT_ID").item(0).getTextContent();
				//System.out.println(mapCode);
				entry.setAWARD_PROP_DEPARTMENT_ID(deptId);
				String deptName = eElement.getElementsByTagName("AWARD_PROP_DEPARTMENT").item(0).getTextContent();
				//System.out.println(mapCode);
				entry.setAWARD_PROP_DEPARTMENT(deptName);
				String sponsorName = eElement.getElementsByTagName("AWARD_PROP_SPONSOR_NAME").item(0).getTextContent();
				//System.out.println(mapCode);
				entry.setAWARD_PROP_SPONSOR_NAME(sponsorName);
				String sponsorId = eElement.getElementsByTagName("AWARD_PROP_SPONSOR_ID").item(0).getTextContent();
				//System.out.println(mapCode);
				entry.setAWARD_PROP_SPONSOR_ID(sponsorId);
				String startDate = eElement.getElementsByTagName("AWARD_PROP_START_DATE").item(0).getTextContent();
				//System.out.println(mapCode);
				startDate = startDate.substring(0,  startDate.indexOf("-"));
				entry.setAWARD_PROP_START_DATE(startDate);
				String endDate = eElement.getElementsByTagName("AWARD_PROP_END_DATE").item(0).getTextContent();
				endDate = endDate.substring(0,  endDate.indexOf("-"));
				entry.setAWARD_PROP_END_DATE(endDate);
				String total = eElement.getElementsByTagName("AWARD_PROP_TOTAL").item(0).getTextContent();
				//System.out.println(mapCode);
				entry.setAWARD_PROP_TOTAL(total);
				String description = eElement.getElementsByTagName("AWARD_DESCRIPTION").item(0).getTextContent();
				//System.out.println(mapCode);
				entry.setAWARD_DESCRIPTION(description);
				String flowSponsorId = eElement.getElementsByTagName("FLOW_THROUGH_SPONSOR_ID").item(0).getTextContent();
				//System.out.println(mapCode);
				entry.setFLOW_THROUGH_SPONSOR_ID(flowSponsorId);
				String flowSponsorName = eElement.getElementsByTagName("FLOW_THROUGH_SPONSOR_NAME").item(0).getTextContent();
				//System.out.println(mapCode);
				entry.setFLOW_THROUGH_SPONSOR_NAME(flowSponsorName);
				String spLevel1 = eElement.getElementsByTagName("SP_LEV_1").item(0).getTextContent();
				//System.out.println(mapCode);
				entry.setSP_LEV_1(spLevel1);
				String spLevel2 = eElement.getElementsByTagName("SP_LEV_2").item(0).getTextContent();
				//System.out.println(mapCode);
				entry.setSP_LEV_2(spLevel2);
				String spLevel3 = eElement.getElementsByTagName("SP_LEV_3").item(0).getTextContent();
				//System.out.println(mapCode);
				entry.setSP_LEV_3(spLevel3);
				String rollupDeptName = eElement.getElementsByTagName("ROLLUP_DEPT_NAME").item(0).getTextContent();
				//System.out.println(mapCode);
				entry.setROLLUP_DEPT_NAME(rollupDeptName);
				String status = eElement.getElementsByTagName("AWARD_STATUS").item(0).getTextContent();
				//System.out.println(mapCode);
				entry.setAWARD_STATUS(status);

				entries.put(projectId, entry);
				count++;
			}// end of reading entries.
		} catch (SAXException | IOException | ParserConfigurationException e) {
			e.printStackTrace();
		} 
		System.out.println("total award entries:"+ count);
		return entries;
	}

	private void writeViewData(Set<String> set) {
		PrintWriter printWriter;
		try {
			printWriter = new PrintWriter ("/Users/mj495/Documents/VIVO-Scholar/InitialLoad/OSPData/osp/rollupdept-xls.csv");	
			for(Iterator<String> i = set.iterator(); i.hasNext();){
				String entry = i.next();
				printWriter.println (entry.toString());
			}
			printWriter.close ();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	

}
