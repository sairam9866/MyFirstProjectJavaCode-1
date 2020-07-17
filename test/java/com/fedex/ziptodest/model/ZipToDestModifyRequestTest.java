package com.fedex.ziptodest.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.fedex.ziptodest.model.ZipToDestModifyRequest;

/**
 * 
 * @author 3786636
 *
 */

public class ZipToDestModifyRequestTest {

	ZipToDestModifyRequest zipToDestModifyRequest = new ZipToDestModifyRequest();
	String timeStamp = "2019-08-22.10:00:00";

	@Before
	public void addRecord() {
		zipToDestModifyRequest.setNetwork("FXG");
		zipToDestModifyRequest.setEffectiveDate(timeStamp);
		zipToDestModifyRequest.setZipFrom("1000");
		zipToDestModifyRequest.setZipTo("2000");
		zipToDestModifyRequest.setDestinationTerminal("1235");
		zipToDestModifyRequest.setTimeZone("(GMT-5:00) US/America/New_York");
		zipToDestModifyRequest.setModifiedUser("TEST");
	}

	@Test
	public void testZipToDestRecords() {
		assertEquals("FXG", zipToDestModifyRequest.getNetwork());
		assertEquals(timeStamp, zipToDestModifyRequest.getEffectiveDate());
		assertEquals("1000", zipToDestModifyRequest.getZipFrom());
		assertEquals("2000", zipToDestModifyRequest.getZipTo());
		assertEquals("1235", zipToDestModifyRequest.getDestinationTerminal());
		assertEquals("(GMT-5:00) US/America/New_York", zipToDestModifyRequest.getTimeZone());
		assertEquals("TEST",zipToDestModifyRequest.getModifiedUser());
	}
}
