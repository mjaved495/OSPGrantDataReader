package edu.cornell.osptographdatabuilder;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import edu.cornell.ospmodel.Investigator;

public class InvestigatorDataReader {

	private Document doc;
	private int count = 0;
	
	public Map<String, Investigator> loadInvestigatorData(File xmlFile) {
		Map<String, Investigator> entries = new HashMap<String, Investigator>();
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(xmlFile);
			NodeList entryList = doc.getElementsByTagName("Investigator");	
			Investigator entry;	

			for(int index=0; index< entryList.getLength(); index++){
				entry = new Investigator();
				Node node = entryList.item(index);
				//System.out.println(node.getNodeName());
				Element eElement = (Element) node;
				String prj_inv_Id = eElement.getElementsByTagName("PROJ_INV").item(0).getTextContent();
				//System.out.println(prj_inv_Id);
				entry.setPROJ_INV(prj_inv_Id);
				String  prj_id = eElement.getElementsByTagName("INVPROJ_PROJECT_ID").item(0).getTextContent();
				entry.setINVPROJ_PROJECT_ID(prj_id);
				String  fullName = eElement.getElementsByTagName("INVPROJ_FULL_NAME").item(0).getTextContent();
				entry.setINVPROJ_FULL_NAME(fullName);
				String  firstName = eElement.getElementsByTagName("INVPROJ_FIRST_NAME").item(0).getTextContent();
				entry.setINVPROJ_FIRST_NAME(firstName);
				String  middleName = eElement.getElementsByTagName("INVPROJ_MIDDLE_NAME").item(0).getTextContent();
				entry.setINVPROJ_MIDDLE_NAME(middleName);
				String  lastName = eElement.getElementsByTagName("INVPROJ_LAST_NAME").item(0).getTextContent();
				entry.setINVPROJ_LAST_NAME(lastName);
				String  netId = eElement.getElementsByTagName("INVPROJ_INVESTIGATOR_NETID").item(0).getTextContent();
				entry.setINVPROJ_INVESTIGATOR_NETID(netId);
				String  invRole= eElement.getElementsByTagName("INVPROJ_INVESTIGATOR_ROLE").item(0).getTextContent();
				entry.setINVPROJ_INVESTIGATOR_ROLE(invRole);
				String  deptName = eElement.getElementsByTagName("INVPROJ_DEPT_NAME").item(0).getTextContent();
				entry.setINVPROJ_DEPT_NAME(deptName);
				String  deptId = eElement.getElementsByTagName("INVPROJ_DEPT_ID").item(0).getTextContent();
				entry.setINVPROJ_DEPT_ID(deptId);
				String  invId = eElement.getElementsByTagName("INVPROJ_INVESTIGATOR_ID").item(0).getTextContent();
				entry.setINVPROJ_INVESTIGATOR_ID(invId);
				entries.put(prj_inv_Id, entry);
				count++;
			}// end of reading entries.
		} catch (SAXException | IOException | ParserConfigurationException e) {
			e.printStackTrace();
		} 
		System.out.println("total investigator entries:"+ count);
		return entries;
	}

	
}
