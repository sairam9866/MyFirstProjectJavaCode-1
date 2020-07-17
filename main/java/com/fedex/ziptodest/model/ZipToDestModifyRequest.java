package com.fedex.ziptodest.model;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ZipToDestModifyRequest {

	@NotEmpty
	private String network;

	@NotEmpty
	private String destinationTerminal;

	@NotEmpty
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd.HH:mm:ss")
	private String effectiveDate;

	@NotEmpty
	private String zipFrom;

	@NotEmpty
	private String zipTo;

	@NotEmpty
	private String timeZone;

	private String modifiedUser;

	public String getNetwork() {
		return network;
	}

	public void setNetwork(String network) {
		this.network = network;
	}

	public String getDestinationTerminal() {
		return destinationTerminal;
	}

	public void setDestinationTerminal(String destinationTerminal) {
		this.destinationTerminal = destinationTerminal;
	}

	public String getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getZipFrom() {
		return zipFrom;
	}

	public void setZipFrom(String zipFrom) {
		this.zipFrom = zipFrom;
	}

	public String getZipTo() {
		return zipTo;
	}

	public void setZipTo(String zipTo) {
		this.zipTo = zipTo;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public String getModifiedUser() {
		return modifiedUser;
	}

	public void setModifiedUser(String modifiedUser) {
		this.modifiedUser = modifiedUser;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	

}
