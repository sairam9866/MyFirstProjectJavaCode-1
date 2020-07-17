package com.fedex.ziptodest.model;


import static org.junit.Assert.assertEquals;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import org.junit.Before;
import org.junit.Test;

import com.fedex.ziptodest.model.ZipToDest;
import com.fedex.ziptodest.utils.server.ZDConstants;
/**
 * 
 * @author 3786636
 *
 */

public class ZipToDestTest {

	ZipToDest zipToDest = new ZipToDest();
	Long timeStamp = ZonedDateTime.now(ZoneOffset.UTC).toEpochSecond();
	

	@Before
	public void addRecord() {
		zipToDest.setCountryCode(1);
		zipToDest.setNetwork("FXG");
		zipToDest.setCreatedDateAt(timeStamp);
		zipToDest.setCreationUser("Fedex");
		zipToDest.setDestinationTerminal("123");
		zipToDest.setEffectiveDateAt(timeStamp);
		zipToDest.setZipCode("12345");
		zipToDest.setState("PA");
		zipToDest.setProcessed(ZDConstants.FLAG_NO);
		zipToDest.setZipFrom("1000");
		zipToDest.setZipTo("2000");
		zipToDest.setTimeZone("(GMT-8:00) Canada/Pacific");
	}

	@Test
	public void testZipToDestRecords() {
		assertEquals(1, zipToDest.getCountryCode());
		assertEquals("FXG", zipToDest.getNetwork());
		assertEquals(timeStamp, zipToDest.getCreatedDateAt());
		assertEquals("Fedex", zipToDest.getCreationUser());
		assertEquals("123", zipToDest.getDestinationTerminal());
		assertEquals(timeStamp, zipToDest.getEffectiveDateAt());
		assertEquals("12345", zipToDest.getZipCode());
		assertEquals("PA", zipToDest.getState());
		assertEquals("N", zipToDest.getProcessed());
		assertEquals("1000", zipToDest.getZipFrom());
		assertEquals("2000", zipToDest.getZipTo());
		assertEquals("(GMT-8:00) Canada/Pacific", zipToDest.getTimeZone());

		
	}

}
