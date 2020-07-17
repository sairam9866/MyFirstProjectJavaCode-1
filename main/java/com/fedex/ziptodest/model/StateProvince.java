package com.fedex.ziptodest.model;

import java.io.Serializable;

public class StateProvince implements Serializable {

	private static final long serialVersionUID = 7069436120025089923L;

	private String spName;
	
	private String staPro;
	
	private String cntryc;

	public String getSpName() {
		return spName;
	}

	public void setSpName(String spName) {
		this.spName = spName;
	}

	public String getStaPro() {
		return staPro;
	}

	public void setStaPro(String staPro) {
		this.staPro = staPro;
	}

	public String getCntryc() {
		return cntryc;
	}

	public void setCntryc(String cntryc) {
		this.cntryc = cntryc;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((staPro == null) ? 0 : staPro.hashCode());
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
		StateProvince other = (StateProvince) obj;
		if (staPro == null) {
			if (other.staPro != null)
				return false;
		} else if (!staPro.equals(other.staPro)){
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "StateProvince [spName=" + spName + ", staPro=" + staPro + ", cntryc=" + cntryc + "]";
	}

}
