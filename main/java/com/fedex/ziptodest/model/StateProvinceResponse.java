package com.fedex.ziptodest.model;

import java.util.Set;

public class StateProvinceResponse {
	private String status;
	private String message;
	private Set<StateModel> data;

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

	public Set<StateModel> getData() {
		return data;
	}

	public void setData(Set<StateModel> data) {
		this.data = data;
	}

	public StateProvinceResponse() {
		/**
		 * Defalt Constuctor
		 */
	}

	@Override
	public String toString() {
		return "StateProvinceResponse [status=" + status + ", message=" + message + ", data=" + data + "]";
	}
	
}
