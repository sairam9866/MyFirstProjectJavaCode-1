package com.fedex.ziptodest.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Network implements Serializable {

	private static final long serialVersionUID = 674643992433627192L;

	private Long termNum;

	private Integer rowId;

	private String networkId;

	private String createdBy;

	private LocalDate createdDate;

	private String updatedBy;

	private LocalDate updateDate;

	private String modelType;

	private Integer colocNum;

	public Long getTermNum() {
		return termNum;
	}

	public void setTermNum(Long termNum) {
		this.termNum = termNum;
	}

	public Integer getRowId() {
		return rowId;
	}

	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}

	public String getNetworkId() {
		return networkId;
	}

	public void setNetworkId(String networkId) {
		this.networkId = networkId;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public LocalDate getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(LocalDate updateDate) {
		this.updateDate = updateDate;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((termNum == null) ? 0 : termNum.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Network other = (Network) obj;
		if (termNum == null) {
			if (other.termNum != null)
				return false;
		} else if (!termNum.equals(other.termNum)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Network [termNum=" + termNum + ", rowId=" + rowId + ", networkId=" + networkId + ", createdBy="
				+ createdBy + ", createdDate=" + createdDate + ", updatedBy=" + updatedBy + ", updateDate=" + updateDate
				+ ", modelType=" + modelType + ", colocNum=" + colocNum + "]";
	}

}
