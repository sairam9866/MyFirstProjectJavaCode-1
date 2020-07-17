package com.fedex.ziptodest.model;

import java.util.List;

public class ZipToDestCancelRequest {

	private String user;
	private List<String> uuid;	
	

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public List<String> getUuid() {
		return uuid;
	}

	public void setUuid(List<String> uuid) {
		this.uuid = uuid;
	}

	@Override
	public String toString() {
		return "ZipToDestCancelRequest [user=" + user + ", "
				+ "uuid=" + (uuid != null ? uuid.size() : null) + "]";
	}
}
