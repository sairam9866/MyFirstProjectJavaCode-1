package com.fedex.ziptodest.model;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import com.fedex.ziptodest.model.ZipToDestDeleteRequest;

/**
 * 
 * @author 3786636
 *
 */

public class ZipToDestDeleteRequestTest {

	ZipToDestDeleteRequest zipToDestDeleteRequest = new ZipToDestDeleteRequest();
	String timeStamp = "2019-08-22.10:00:00";

	@Before
	public void addRecord() {
		zipToDestDeleteRequest.setNetwork("FXG");
		zipToDestDeleteRequest.setEffectiveDate(timeStamp);
		zipToDestDeleteRequest.setZipFrom("1000");
		zipToDestDeleteRequest.setZipTo("2000");
		zipToDestDeleteRequest.setTimeZone("(GMT-5:00) US/America/New_York");
		zipToDestDeleteRequest.setDeletedUser("TEST");
	}

	@Test
	public void testZipToDestRecords() {
		assertEquals("FXG", zipToDestDeleteRequest.getNetwork());
		assertEquals(timeStamp, zipToDestDeleteRequest.getEffectiveDate());
		assertEquals("1000", zipToDestDeleteRequest.getZipFrom());
		assertEquals("2000", zipToDestDeleteRequest.getZipTo());
		assertEquals("(GMT-5:00) US/America/New_York", zipToDestDeleteRequest.getTimeZone());
		assertEquals("TEST", zipToDestDeleteRequest.getDeletedUser());
	}
}
