package com.fedex.ziptodest.exception.server;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;


public class ExceptionResponseTest {

	ExceptionResponse exceptionResponse=new ExceptionResponse("Ok", "Success");
			
	List<String> list=new ArrayList<>();
	
	ExceptionResponse exceptionResponse1=new ExceptionResponse("Ok", "Success",list);
	
	@Before
	public void testExceptionResponseAdd(){
		
		exceptionResponse.setMessage("Success");
		exceptionResponse.setStatusCd("Y");
		exceptionResponse.setValidationErrors(list);
		exceptionResponse.addValidationError(null);
	}
	
	@Test
	public void testExceptionResponse_Positive(){
		
		assertEquals("Success", exceptionResponse.getMessage());
		assertEquals("Y",exceptionResponse.getStatusCd());
		assertNotNull(list);
		assertNotEquals(null, exceptionResponse.getValidationErrors());
	}
	

	@Test
	public void testExceptionResponse_Negative(){
		
		assertNotEquals("Succes", exceptionResponse.getMessage());
		assertNotEquals("N",exceptionResponse.getStatusCd());
		assertNotNull(list);
		assertNotEquals(null, exceptionResponse.getValidationErrors());
	}
	
	
	
	
	
}
