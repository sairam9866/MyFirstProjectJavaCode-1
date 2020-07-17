
package com.fedex.ziptodest.exception.server;

/*
 *   This is the configuration class for the Exception Handling in Spring Boot
 */
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger exceptionLogger = LoggerFactory
			.getLogger(CustomizedResponseEntityExceptionHandler.class);

	@ExceptionHandler(ApplicationException.class)
	public final ResponseEntity<ExceptionResponse> handleApplicationExceptions(ApplicationException aex,
			WebRequest request) {
		exceptionLogger.info("Caught ApplicationException Type");
		ExceptionResponse exceptionResponse = new ExceptionResponse(aex.getCode(), aex.getMessage());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
