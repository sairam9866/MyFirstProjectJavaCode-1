package com.fedex.ziptodest.model;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fedex.ziptodest.utils.server.ZDConstants;

/**
 * 
 * @author 3818669
 *
 */
public class ZipToDestNotProcessed {

	@NotEmpty
	private String network;

	@NotEmpty
	private int countryCode;

	private String zipFrom;
	private String zipTo;

	@NotEmpty
	private String destinationTerminal;

	@NotEmpty
	private String creationUser;

	private transient String  creationDate;

	private transient String effectiveDate;
	
	@JsonIgnore
	private Long creationDateAt;

	@JsonIgnore
	private Long effectiveDateAt;

	@NotEmpty
	private String processed;

	@NotEmpty
	private String uuid;

	public ZipToDestNotProcessed() {
		super();
	}

	public ZipToDestNotProcessed(ZipToDest zipToDest) {
		this.network = zipToDest.getNetwork();
		this.countryCode = zipToDest.getCountryCode();
		this.destinationTerminal = zipToDest.getDestinationTerminal();
		this.zipFrom = zipToDest.getZipCode();
		this.creationUser = zipToDest.getCreationUser();
		this.creationDateAt = zipToDest.getCreatedDateAt();
		this.effectiveDateAt = zipToDest.getEffectiveDateAt();
		this.processed = zipToDest.getProcessed();
		this.uuid = zipToDest.getUuid();
	}

	public String getNetwork() {
		return network;
	}

	public void setNetwork(String network) {
		this.network = network;
	}

	public int getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(int countryCode) {
		this.countryCode = countryCode;
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

	

	public Long getCreationDateAt() {
		return creationDateAt;
	}

	public void setCreationDateAt(Long creationDateAt) {
		this.creationDateAt = creationDateAt;
	}

	public Long getEffectiveDateAt() {
		return effectiveDateAt;
	}

	public void setEffectiveDateAt(Long effectiveDateAt) {
		this.effectiveDateAt = effectiveDateAt;
	}

	public String getProcessed() {
		return processed;
	}

	public void setProcessed(String processed) {
		this.processed = processed;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getEffectiveDate() {
		if (effectiveDateAt != null) {
			effectiveDate = LocalDateTime.ofEpochSecond(effectiveDateAt, 0, ZoneOffset.UTC)
					.format(ZDConstants.ZIP_TO_DEST_DATE_TIME_FORMATTER);
		}
		return effectiveDate;
	}
	
	public String getcreationDate() {
		if (creationDateAt != null) {
			creationDate = LocalDateTime.ofEpochSecond(creationDateAt, 0, ZoneOffset.UTC).format(ZDConstants.ZIP_TO_DEST_DATE_TIME_FORMATTER);
		}
		return creationDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((uuid == null) ? 0 : uuid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		ZipToDestNotProcessed other = (ZipToDestNotProcessed) obj;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid)) {
			return false;
		}
		return true;
	}

}
