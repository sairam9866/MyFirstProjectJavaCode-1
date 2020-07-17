package com.fedex.ziptodest.exception.server;
/*
 *  A specific custom POJO for Exception response
 */
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

public class ExceptionResponse {

	private String statusCd;
	private String message;

	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private List<String> validationErrors = new ArrayList<>();

	public ExceptionResponse(String statusCd, String message) {
		super();
		this.statusCd = statusCd;
		this.message = message;
	}

	public ExceptionResponse(String statusCd, String message, List<String> validationErrors) {
		super();
		this.statusCd = statusCd;
		this.message = message;
		this.validationErrors = validationErrors;
	}

	public void addValidationError(String error) {
		validationErrors.add(error);
	}

	/**
	 * @return the statusCd
	 */

	public String getStatusCd() {
		return statusCd;
	}

	/**
	 * @param statusCd the statusCd to set
	 */

	public void setStatusCd(String statusCd) {
		this.statusCd = statusCd;
	}

	/**
	 * @return the message
	 */

	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the validationErrors
	 */
	public List<String> getValidationErrors() {
		return validationErrors;
	}

	/**
	 * @param validationErrors the validationErrors to set
	 */
	public void setValidationErrors(List<String> validationErrors) {
		this.validationErrors = validationErrors;
	}

}
