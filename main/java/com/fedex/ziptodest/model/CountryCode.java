package com.fedex.ziptodest.model;

import java.io.Serializable;

public class CountryCode implements Serializable{	

	/**
	 * 
	 */
	private static final long serialVersionUID = 7705518679133629898L;

	private String cycode;

	private Integer cyclcu;

	public String getCycode() {
		return cycode;
	}

	public void setCycode(String cycode) {
		this.cycode = cycode;
	}

	public Integer getCyclcu() {
		return cyclcu;
	}

	public void setCyclcu(Integer cyclcu) {
		this.cyclcu = cyclcu;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cycode == null) ? 0 : cycode.hashCode());
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
		CountryCode other = (CountryCode) obj;
		if (cycode == null) {
			if (other.cycode != null)
				return false;
		} else if (!cycode.equals(other.cycode)){
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "CountryCode [cycode=" + cycode + ", cyclcu=" + cyclcu + "]";
	}

}
