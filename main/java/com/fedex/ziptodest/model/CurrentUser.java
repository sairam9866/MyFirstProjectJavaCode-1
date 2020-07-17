/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fedex.ziptodest.model;

import java.io.Serializable;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * 
 * @author 3786636
 *
 */

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)

public class CurrentUser implements Serializable {

	private static final long serialVersionUID = 1L;

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

	private String userId;
	private String givenName;
	private String mail;
	private String deptNum;
	private String role;

}
