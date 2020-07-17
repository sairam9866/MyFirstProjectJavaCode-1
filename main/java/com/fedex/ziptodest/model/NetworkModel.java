package com.fedex.ziptodest.model;

import java.util.Set;

public class NetworkModel {

	private String status;
	private String message;
	private Set<String> data;

	public NetworkModel() {
		/**
		 * Default Constructor
		 */
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Set<String> getData() {
		return data;
	}

	public void setData(Set<String> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "NetworkModel [status=" + status + ", message=" + message + ", data=" + data + "]";
	}

	
}
