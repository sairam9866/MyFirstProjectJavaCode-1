package com.fedex.ziptodest.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import com.fedex.ziptodest.utils.server.ZDConstants;

public class ZipToDest implements Serializable {

	private static final long serialVersionUID = 2214873653028399L;

	private String id;

	private String network;

	private int countryCode;

	private String zipCode;

	private String state;

	private String destinationTerminal;

	private String creationUser;

	private Long createdDateAt;

	private Long effectiveDateAt;

	private String transactionType;

	private String processed;

	private String current;

	private String uuid;

	private String cancelledUser;

	private Long cancelledTimestamp;

	private String cancelledFlag;

	private Long processedDateTime;

	private String zipFrom;

	private String zipTo;

	private String timeZone;

	private transient String effectiveDate;

	private transient String creationDate;

	private transient String processedAt;

	private transient String cancelledAt;

	public ZipToDest() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
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

	public Long getCreatedDateAt() {
		return createdDateAt;
	}

	public void setCreatedDateAt(Long createdDateAt) {
		this.createdDateAt = createdDateAt;
	}

	public Long getCancelledTimestamp() {
		return cancelledTimestamp;
	}

	public Long getEffectiveDateAt() {
		return effectiveDateAt;
	}

	public void setEffectiveDateAt(Long effectiveDateAt) {
		this.effectiveDateAt = effectiveDateAt;
	}

	public void setCancelledTimestamp(Long cancelledTimestamp) {
		this.cancelledTimestamp = cancelledTimestamp;
	}

	public Long getProcessedDateTime() {
		return processedDateTime;
	}

	public void setProcessedDateTime(Long processedDateTime) {
		this.processedDateTime = processedDateTime;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getProcessed() {
		return processed;
	}

	public void setProcessed(String processed) {
		this.processed = processed;
	}

	public String getCurrent() {
		return current;
	}

	public void setCurrent(String current) {
		this.current = current;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getCancelledUser() {
		return cancelledUser;
	}

	public void setCancelledUser(String cancelledUser) {
		this.cancelledUser = cancelledUser;
	}

	public String getCancelledFlag() {
		return cancelledFlag;
	}

	public void setCancelledFlag(String cancelledFlag) {
		this.cancelledFlag = cancelledFlag;
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

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public String getcreationDate() {
		if (createdDateAt != null) {
			creationDate = LocalDateTime.ofEpochSecond(createdDateAt, 0, ZoneOffset.UTC)
					.format(ZDConstants.ZIP_TO_DEST_DATE_TIME_FORMATTER);
		}
		return creationDate;
	}

	public String getEffectiveDate() {
		if (effectiveDateAt != null) {
			effectiveDate = LocalDateTime.ofEpochSecond(effectiveDateAt, 0, ZoneOffset.UTC)
					.format(ZDConstants.ZIP_TO_DEST_DATE_TIME_FORMATTER);
		}
		return effectiveDate;
	}

	public String getProcessedAt() {
		if (processedDateTime != null) {
			processedAt = LocalDateTime.ofEpochSecond(processedDateTime, 0, ZoneOffset.UTC)
					.format(ZDConstants.ZIP_TO_DEST_DATE_TIME_FORMATTER);
		}
		return processedAt;
	}

	public String getCancelledAt() {
		if (cancelledTimestamp != null) {
			cancelledAt = LocalDateTime.ofEpochSecond(cancelledTimestamp, 0, ZoneOffset.UTC)
					.format(ZDConstants.ZIP_TO_DEST_DATE_TIME_FORMATTER);
		}
		return cancelledAt;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		ZipToDest other = (ZipToDest) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ZipToDest [id=" + id + "network=" + network + ", countryCode=" + countryCode + ", zipCode=" + zipCode
				+ ", state=" + state + ", destinationTerminal=" + destinationTerminal + ", creationUser=" + creationUser
				+ ", createdDateAt=" + createdDateAt + ", effectiveDateAt=" + effectiveDateAt + ", transactionType="
				+ transactionType + ", processed=" + processed + ", current=" + current + ", uuid=" + uuid
				+ ", cancelledUser=" + cancelledUser + ", cancelledTimestamp=" + cancelledTimestamp + ", cancelledFlag="
				+ cancelledFlag + ", processedDateTime=" + processedDateTime + ", zipFrom=" + zipFrom + ", zipTo="
				+ zipTo + ", timeZone=" + timeZone + "]";
	}

}
