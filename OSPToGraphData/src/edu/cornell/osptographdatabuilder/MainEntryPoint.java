package edu.cornell.osptographdatabuilder;

import java.io.File;
import java.util.List;

import edu.cornell.ospmodel.Person_NetIdDeptMap;

public class MainEntryPoint {

	public static void main(String args[]){
		DataReader reader = new DataReader();
		AwardsDataReader obj1 = new AwardsDataReader();
		reader.awd_entries = obj1.loadAwardData(new File(DataReader.INPUT_AWRAD_FILE));
		DataReader.saveDistinctSponsors(reader.awd_entries, "resources/output/AwdDistinctSponsorNames.csv");		
		DataReader.saveSponsorsFlow(reader.awd_entries, "resources/output/AwdSponsors.csv");
		InvestigatorDataReader obj2 = new InvestigatorDataReader();
		reader.inv_entries = obj2.loadInvestigatorData(new File(DataReader.INPUT_INVESTIGATOR_FILE));	
		// Is it possible to create this file on the fly ?
		List<Person_NetIdDeptMap> list = reader.readPersonNetIdDeptMapperFile(DataReader.PERSON_NETID_DEPT_MAPPER_FILE);
		reader.mergeData(list, reader.awd_entries, reader.inv_entries, new File(DataReader.OUTPUT_FILE));
		
		RDFBuilder builder = new RDFBuilder();
		String INPUT_FILE =  "resources/output/AwdInv-all.txt";
		String OUTPUT_FILE = "resources/output/AwdInv-all.nt";
		builder.generateRDF(INPUT_FILE, OUTPUT_FILE);
	}
}
