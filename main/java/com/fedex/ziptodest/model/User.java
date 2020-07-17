package com.fedex.ziptodest.model;

public class User {

	public User(String id, String name, String dept, String mail, String role, Boolean sortFlag,
			Boolean visionManagementFlag, Boolean regFlag) {
		this.userId = id;
		this.givenName = name;
		this.deptNum = dept;
		this.mail = mail;
		this.role = role;
		this.sortation = sortFlag;
		this.visionManagement = visionManagementFlag;
		this.regUser = regFlag;

	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userID) {
		this.userId = userID;
	}

	public String getGivenName() {
		return this.givenName;
	}

	public void setGivenName(String gname) {
		this.givenName = gname;
	}

	public String getDeptNum() {
		return this.deptNum;
	}

	public void setDeptNum(String oname) {
		this.deptNum = oname;
	}

	public String getMail() {
		return this.mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Boolean getSortation() {
		return this.sortation;
	}

	public void setSortation(Boolean flag) {
		this.sortation = flag;
	}

	public Boolean getVisionManagement() {
		return visionManagement;
	}

	public void setVisionManagement(Boolean visionManagement) {
		this.visionManagement = visionManagement;
	}

	public Boolean getRegUser() {
		return this.regUser;
	}

	public void setRegUser(Boolean flag) {
		this.regUser = flag;
	}

	@Override
	public String toString() {
		return "User ID: " + this.userId + "\nUser Name: " + this.givenName + "\nMail: " + this.mail + "\nDepartment: "
				+ this.deptNum + "\nRole: " + this.role + "\nSortation?: " + this.sortation + "\nServ. Measurement?: "
				+ this.visionManagement + "\nRegular User?: " + this.regUser;
	}

	private String userId;
	private String givenName;
	private String mail;
	private String deptNum;
	private String role;
	private Boolean sortation;
	private Boolean visionManagement;
	private Boolean regUser;

}
