package com.fedex.ziptodest.model;

import java.io.Serializable;

public class Destination implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1859896497919106160L;

	private Integer terminalNumber;

	private String terminalAbbreviation;

	private String terminalStatus;

	public Integer getTerminalNumber() {
		return terminalNumber;
	}

	public void setTerminalNumber(Integer terminalNumber) {
		this.terminalNumber = terminalNumber;
	}

	public String getTerminalAbbreviation() {
		return terminalAbbreviation;
	}

	public void setTerminalAbbreviation(String terminalAbbreviation) {
		this.terminalAbbreviation = terminalAbbreviation;
	}

	public String getTerminalStatus() {
		return terminalStatus;
	}

	public void setTerminalStatus(String terminalStatus) {
		this.terminalStatus = terminalStatus;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((terminalNumber == null) ? 0 : terminalNumber.hashCode());
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
		Destination other = (Destination) obj;
		if (terminalNumber == null) {
			if (other.terminalNumber != null)
				return false;
		} else if (!terminalNumber.equals(other.terminalNumber)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {		
		return "Destination [terminalNumber=" + terminalNumber + ", terminalAbbreviation=" + terminalAbbreviation
				+ ", terminalStatus=" + terminalStatus + "]";
	}

}
