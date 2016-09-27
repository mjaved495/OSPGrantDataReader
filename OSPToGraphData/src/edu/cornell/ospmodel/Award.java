package edu.cornell.ospmodel;

public class Award {

	private String AWARD_PROP_PROJECT_ID;
	private String AWARD_PROPOSAL_ID;
	private String AWARD_PROP_FULL_TITLE;
	private String AWARD_PROP_DEPARTMENT_ID;
	private String AWARD_PROP_DEPARTMENT;
	private String AWARD_PROP_SPONSOR_NAME;
	private String AWARD_PROP_SPONSOR_ID;
	private String AWARD_PROP_START_DATE;
	private String AWARD_PROP_END_DATE;
	private String AWARD_PROP_TOTAL;
	private String AWARD_DESCRIPTION;
	private String FLOW_THROUGH_SPONSOR_ID;
	private String FLOW_THROUGH_SPONSOR_NAME;
	private String SP_LEV_1;
	private String SP_LEV_2;
	private String SP_LEV_3;
	private String ROLLUP_DEPT_NAME;
	private String AWARD_STATUS;
	
	public Award(){
		
	}

	public String getAWARD_PROP_PROJECT_ID() {
		return AWARD_PROP_PROJECT_ID;
	}

	public void setAWARD_PROP_PROJECT_ID(String aWARD_PROP_PROJECT_ID) {
		AWARD_PROP_PROJECT_ID = aWARD_PROP_PROJECT_ID;
	}

	public String getAWARD_PROPOSAL_ID() {
		return AWARD_PROPOSAL_ID;
	}

	public void setAWARD_PROPOSAL_ID(String aWARD_PROPOSAL_ID) {
		AWARD_PROPOSAL_ID = aWARD_PROPOSAL_ID;
	}

	public String getAWARD_PROP_FULL_TITLE() {
		return AWARD_PROP_FULL_TITLE;
	}

	public void setAWARD_PROP_FULL_TITLE(String aWARD_PROP_FULL_TITLE) {
		AWARD_PROP_FULL_TITLE = aWARD_PROP_FULL_TITLE;
	}

	public String getAWARD_PROP_DEPARTMENT_ID() {
		return AWARD_PROP_DEPARTMENT_ID;
	}

	public void setAWARD_PROP_DEPARTMENT_ID(String aWARD_PROP_DEPARTMENT_ID) {
		AWARD_PROP_DEPARTMENT_ID = aWARD_PROP_DEPARTMENT_ID;
	}

	public String getAWARD_PROP_DEPARTMENT() {
		return AWARD_PROP_DEPARTMENT;
	}

	public void setAWARD_PROP_DEPARTMENT(String aWARD_PROP_DEPARTMENT) {
		AWARD_PROP_DEPARTMENT = aWARD_PROP_DEPARTMENT;
	}

	public String getAWARD_PROP_SPONSOR_NAME() {
		return AWARD_PROP_SPONSOR_NAME;
	}

	public void setAWARD_PROP_SPONSOR_NAME(String aWARD_PROP_SPONSOR_NAME) {
		AWARD_PROP_SPONSOR_NAME = aWARD_PROP_SPONSOR_NAME;
	}

	public String getAWARD_PROP_SPONSOR_ID() {
		return AWARD_PROP_SPONSOR_ID;
	}

	public void setAWARD_PROP_SPONSOR_ID(String aWARD_PROP_SPONSOR_ID) {
		AWARD_PROP_SPONSOR_ID = aWARD_PROP_SPONSOR_ID;
	}

	public String getAWARD_PROP_START_DATE() {
		return AWARD_PROP_START_DATE;
	}

	public void setAWARD_PROP_START_DATE(String aWARD_PROP_START_DATE) {
		AWARD_PROP_START_DATE = aWARD_PROP_START_DATE;
	}

	public String getAWARD_PROP_END_DATE() {
		return AWARD_PROP_END_DATE;
	}

	public void setAWARD_PROP_END_DATE(String aWARD_PROP_END_DATE) {
		AWARD_PROP_END_DATE = aWARD_PROP_END_DATE;
	}

	public String getAWARD_PROP_TOTAL() {
		return AWARD_PROP_TOTAL;
	}

	public void setAWARD_PROP_TOTAL(String aWARD_PROP_TOTAL) {
		AWARD_PROP_TOTAL = aWARD_PROP_TOTAL;
	}

	public String getAWARD_DESCRIPTION() {
		return AWARD_DESCRIPTION;
	}

	public void setAWARD_DESCRIPTION(String aWARD_DESCRIPTION) {
		AWARD_DESCRIPTION = aWARD_DESCRIPTION;
	}

	public String getFLOW_THROUGH_SPONSOR_ID() {
		return FLOW_THROUGH_SPONSOR_ID;
	}

	public void setFLOW_THROUGH_SPONSOR_ID(String fLOW_THROUGH_SPONSOR_ID) {
		FLOW_THROUGH_SPONSOR_ID = fLOW_THROUGH_SPONSOR_ID;
	}

	public String getFLOW_THROUGH_SPONSOR_NAME() {
		return FLOW_THROUGH_SPONSOR_NAME;
	}

	public void setFLOW_THROUGH_SPONSOR_NAME(String fLOW_THROUGH_SPONSOR_NAME) {
		FLOW_THROUGH_SPONSOR_NAME = fLOW_THROUGH_SPONSOR_NAME;
	}

	public String getSP_LEV_1() {
		return SP_LEV_1;
	}

	public void setSP_LEV_1(String sP_LEV_1) {
		SP_LEV_1 = sP_LEV_1;
	}

	public String getSP_LEV_2() {
		return SP_LEV_2;
	}

	public void setSP_LEV_2(String sP_LEV_2) {
		SP_LEV_2 = sP_LEV_2;
	}

	public String getSP_LEV_3() {
		return SP_LEV_3;
	}

	public void setSP_LEV_3(String sP_LEV_3) {
		SP_LEV_3 = sP_LEV_3;
	}

	public String getROLLUP_DEPT_NAME() {
		return ROLLUP_DEPT_NAME;
	}

	public void setROLLUP_DEPT_NAME(String rOLLUP_DEPT_NAME) {
		ROLLUP_DEPT_NAME = rOLLUP_DEPT_NAME;
	}

	public String getAWARD_STATUS() {
		return AWARD_STATUS;
	}

	public void setAWARD_STATUS(String aWARD_STATUS) {
		AWARD_STATUS = aWARD_STATUS;
	}
	
	@Override
	public String toString() {
		return AWARD_PROP_PROJECT_ID + "\t" + AWARD_PROPOSAL_ID
				+ "\t" + AWARD_PROP_FULL_TITLE + "\t"
				+ AWARD_PROP_DEPARTMENT_ID + "\t" + AWARD_PROP_DEPARTMENT
				+ "\t" + AWARD_PROP_SPONSOR_NAME + "\t"
				+ AWARD_PROP_SPONSOR_ID + "\t" + AWARD_PROP_START_DATE + "\t"
				+ AWARD_PROP_END_DATE + "\t" + AWARD_PROP_TOTAL + "\t"
				+ AWARD_DESCRIPTION + "\t" + FLOW_THROUGH_SPONSOR_ID
				+ "\t" + FLOW_THROUGH_SPONSOR_NAME + "\t" + SP_LEV_1 + "\t"
				+ SP_LEV_2 + "\t" + SP_LEV_3 + "\t" + ROLLUP_DEPT_NAME + "\t"
				+ AWARD_STATUS;
	}

}
