package com.fedex.ziptodest.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.fedex.ziptodest.model.ZipToDestAddRequest;
/**
 * 
 * @author 3786636
 *
 */

public class ZipToDestAddRequestTest {

	ZipToDestAddRequest zipToDestAddRequest = new ZipToDestAddRequest();

	String timeStamp = "2019-08-22.10:00:00";

	@Before
	public void addRecords() {
		zipToDestAddRequest.setCountryCode(1);
		zipToDestAddRequest.setNetwork("FXG");
		zipToDestAddRequest.setCreationUser("Fedex");
		zipToDestAddRequest.setDestinationTerminal("123");
		zipToDestAddRequest.setEffectiveDate(timeStamp);
		zipToDestAddRequest.setZipCode("12345");
		zipToDestAddRequest.setState("PA");
		zipToDestAddRequest.setTimeZone("(GMT-8:00) Canada/Pacific");
	}

	@Test
	public void testZipToDestAddRequest() {
		assertEquals(1, zipToDestAddRequest.getCountryCode());
		assertEquals("FXG", zipToDestAddRequest.getNetwork());
		assertEquals("Fedex", zipToDestAddRequest.getCreationUser());
		assertEquals("123", zipToDestAddRequest.getDestinationTerminal());
		assertEquals(timeStamp, zipToDestAddRequest.getEffectiveDate());
		assertEquals("12345", zipToDestAddRequest.getZipCode());
		assertEquals("PA", zipToDestAddRequest.getState());

	}

}
