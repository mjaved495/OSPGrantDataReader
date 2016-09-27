package edu.cornell.ospmodel;

public class Department {

	String deptId;
	String deptName;
	String rollupName;
	String vivoURI;
	
	public Department(String deptId, String deptName, String rollupName, String deptVIVOURI) {
		this.deptId = deptId;
		this.deptName = deptName;
		this.rollupName = rollupName;
		this.vivoURI = deptVIVOURI;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getRollupName() {
		return rollupName;
	}

	public void setRollupName(String rollupName) {
		this.rollupName = rollupName;
	}

	public String getVivoURI() {
		return vivoURI;
	}

	public void setVivoURI(String vivoURI) {
		this.vivoURI = vivoURI;
	}

	
	
}
