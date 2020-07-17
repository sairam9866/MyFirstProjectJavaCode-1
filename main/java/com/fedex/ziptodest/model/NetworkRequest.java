package com.fedex.ziptodest.model;

public class NetworkRequest {

	private Long terminalNumber;

	private Integer rowId;

	private String network;

	private String createdBy;

	private String modelType;

	private Integer colocNum;

	public Long getTerminalNumber() {
		return terminalNumber;
	}

	public void setTerminalNumber(Long terminalNumber) {
		this.terminalNumber = terminalNumber;
	}

	public Integer getRowId() {
		return rowId;
	}

	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}

	public String getNetwork() {
		return network;
	}

	public void setNetwork(String network) {
		this.network = network;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModelType() {
		return modelType;
	}

	public void setModelType(String modelType) {
		this.modelType = modelType;
	}

	public Integer getColocNum() {
		return colocNum;
	}

	public void setColocNum(Integer colocNum) {
		this.colocNum = colocNum;
	}

	@Override
	public String toString() {
		return "NetworkRequest [terminalNumber=" + terminalNumber + ", rowId=" + rowId + ", network=" + network
				+ ", createdBy=" + createdBy + ", modelType=" + modelType + ", colocNum=" + colocNum + "]";
	}

}
