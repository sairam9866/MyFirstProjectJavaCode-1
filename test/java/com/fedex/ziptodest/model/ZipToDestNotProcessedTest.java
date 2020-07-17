//package com.fedex.ziptodest.model;
//
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertNotEquals;
//import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertTrue;
//
//import java.time.ZoneOffset;
//import java.time.ZonedDateTime;
//
//import org.junit.Before;
//import org.junit.Test;
//
//import com.fedex.ziptodest.model.ZipToDest;
//import com.fedex.ziptodest.model.ZipToDestNotProcessed;
//import com.fedex.ziptodest.utils.server.ZipToDestConstants;
//
//public class ZipToDestNotProcessedTest {
//
//	ZipToDestNotProcessed zipToDestNotProcessed = new ZipToDestNotProcessed();
//	ZipToDestNotProcessed zipToDestNotProcessed1;
//	ZipToDestNotProcessed zipToDestNotProcessed2 = new ZipToDestNotProcessed();
//	ZipToDestNotProcessed zipToDestNotProcessed3 = new ZipToDestNotProcessed();
//
//	ZipToDestNotProcessed zipToDestNotProcessed4;
//
//	ZipToDest zipToDest = new ZipToDest();
//
//	Long timeStamp = ZonedDateTime.now(ZoneOffset.UTC).toEpochSecond();
//
//	@Before
//	public void addRecords() {
//		zipToDestNotProcessed.setDestinationTerminal("123");
//		zipToDestNotProcessed.setNetwork("FXG");
//		zipToDestNotProcessed.setCountryCode(123);
//		zipToDestNotProcessed.setCreationUser("Admin");
//		zipToDestNotProcessed.setProcessed(ZipToDestConstants.FLAG_NO);
//		zipToDestNotProcessed.setUuid("1234");
//		zipToDestNotProcessed.setZipFrom("234");
//		zipToDestNotProcessed.setZipTo("234");
//		zipToDestNotProcessed.setCreationDate(timeStamp);
//		zipToDestNotProcessed.setEffectiveDateAt(timeStamp);
//		zipToDestNotProcessed3.setUuid("1235");
//
//		zipToDest.setDestinationTerminal("123");
//		zipToDest.setNetwork("FXG");
//		zipToDest.setCountryCode(123);
//		zipToDest.setCreationUser("Admin");
//		zipToDest.setProcessed(ZipToDestConstants.FLAG_NO);
//		zipToDest.setUuid("1234");
//		zipToDest.setZipCode("234");
//		zipToDest.setCreatedDateAt(timeStamp);
//		zipToDest.setEffectiveDateAt(timeStamp);
//		zipToDest.setUuid("1234");
//
//		zipToDestNotProcessed1 = new ZipToDestNotProcessed(zipToDest);
//	}
//
//	@Test
//	public void testZipToDestNotProcessed() {
//		assertEquals("N", zipToDestNotProcessed.getProcessed());
//		assertEquals(123, zipToDestNotProcessed.getCountryCode());
//		assertEquals("123", zipToDestNotProcessed.getDestinationTerminal());
//		assertEquals("Admin", zipToDestNotProcessed.getCreationUser());
//		assertEquals(timeStamp, zipToDestNotProcessed.getEffectiveDateAt());
//		assertEquals(timeStamp, zipToDestNotProcessed.getCreationDate());
//		assertNotNull(zipToDestNotProcessed.getUuid());
//		assertNotNull(zipToDestNotProcessed.getZipFrom());
//		assertNotNull(zipToDestNotProcessed.getZipTo());
//		assertNotNull(zipToDestNotProcessed.getNetwork());
//		assertTrue(zipToDestNotProcessed.equals(zipToDestNotProcessed1));
//		assertTrue(zipToDestNotProcessed.equals(zipToDestNotProcessed));
//		assertFalse(zipToDestNotProcessed.equals(null));
//		assertFalse(zipToDestNotProcessed.equals(""));
//		assertFalse(zipToDestNotProcessed2.equals(zipToDestNotProcessed));
//		assertFalse(zipToDestNotProcessed.equals(zipToDestNotProcessed3));
//
//		assertEquals(zipToDestNotProcessed.hashCode(), zipToDestNotProcessed1.hashCode());
//		assertNotEquals(zipToDestNotProcessed2.hashCode(), zipToDestNotProcessed1.hashCode());
//		//assertFalse(zipToDestNotProcessed2.hashCode() == zipToDestNotProcessed1.hashCode());
//		//assertTrue(zipToDestNotProcessed.hashCode() == zipToDestNotProcessed1.hashCode());
//		
//	}
//
//}
