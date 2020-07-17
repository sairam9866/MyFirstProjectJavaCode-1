package com.fedex.ziptodest.model;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ZipToDestAddRequest {

	@NotEmpty
	private String network;

	@NotEmpty
	private int countryCode;

	@NotEmpty
	private String zipCode;

	@NotEmpty
	private String state;

	@NotEmpty
	private String destinationTerminal;

	@NotEmpty
	private String creationUser;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd.HH:mm:ss")
	private String effectiveDate;

	@NotEmpty
	private String timeZone;

	public String getNetwork() {
		return network;
	}

	public void setNetwork(String network) {
		this.network = network;
	}

	public int getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(int country) {
		this.countryCode = country;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipcode) {
		this.zipCode = zipcode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDestinationTerminal() {
		return destinationTerminal;
	}

	public void setDestinationTerminal(String destinationTerminal) {
		this.destinationTerminal = destinationTerminal;
	}

	public String getCreationUser() {
		return creationUser;
	}

	public void setCreationUser(String creationUser) {
		this.creationUser = creationUser;
	}

	public String getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

}
