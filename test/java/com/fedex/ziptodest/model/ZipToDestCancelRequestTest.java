package com.fedex.ziptodest.model;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.fedex.ziptodest.model.ZipToDestCancelRequest;

public class ZipToDestCancelRequestTest {

	ZipToDestCancelRequest zipToDestCancelRequest;
	@Before
	public void init(){
		zipToDestCancelRequest = new ZipToDestCancelRequest();
		zipToDestCancelRequest.setUser("FedEx");		
	}
	
	
	@Test
	public void testToString(){
		assertNotNull(zipToDestCancelRequest.toString());		
	}
}
