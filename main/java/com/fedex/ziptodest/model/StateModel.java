package com.fedex.ziptodest.model;

public class StateModel implements Comparable<StateModel> {

	private String state;
	private int code;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public StateModel() {
		/**
		 * Default Constructor
		 */
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + code;
		result = prime * result + ((state == null) ? 0 : state.hashCode());
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
		StateModel other = (StateModel) obj;
		if (code != other.code)
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "State [stateName=" + state + ", code=" + code + "]";
	}

	@Override
	public int compareTo(StateModel stateCompare) {
		return this.state.compareTo(stateCompare.getState());
	}

}
