//package com.fedex.ziptodest.controller.server;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotEquals;
//
//import java.text.ParseException;
//import java.time.ZoneOffset;
//import java.time.ZonedDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.fedex.ziptodest.exception.server.ApplicationException;
//import com.fedex.ziptodest.model.ZipToDest;
//import com.fedex.ziptodest.model.ZipToDestAddRequest;
//import com.fedex.ziptodest.model.ZipToDestDeleteRequest;
//import com.fedex.ziptodest.model.ZipToDestModifyRequest;
//import com.fedex.ziptodest.service.server.ZDService;
//import com.fedex.ziptodest.utils.server.ValidationUtil;
//import com.fedex.ziptodest.utils.server.ZipToDestConstants;
//
//
//
//@RunWith(SpringRunner.class)
//public class ZipToDestControllerTest {
//
//	@InjectMocks
//	private ZipToDestController zipToDestController;
//
//	@Mock
//	private ZDService zDService;
//
//	@Mock
//	private ValidationUtil validationUtil;
//
////	@Mock
////	private ZipToDestRepository zipToDestRepository;
//
//	@Test
//	public void testGetAllZDRecord_Positive() {
//
//		List<ZipToDest> allZDRecord = new ArrayList<>();
//
//		ZipToDest zipToDest = new ZipToDest();
//		zipToDest.setCountryCode(123);
//		zipToDest.setCreationUser("Admin");
//		zipToDest.setCancelledFlag(ZipToDestConstants.FLAG_YES);
//		zipToDest.setCancelledUser("Smith");
//
//		Mockito.doReturn(allZDRecord).when(zDService).getAllZDRecord();
//
//		zipToDestController.getAllZDRecord();
//
//		assertEquals(123, zipToDest.getCountryCode());
//		assertEquals("Admin", zipToDest.getCreationUser());
//		assertEquals(ZipToDestConstants.FLAG_YES, zipToDest.getCancelledFlag());
//		assertEquals("Smith", zipToDest.getCancelledUser());
//	}
//
//	@Test
//	public void testGetAllZDRecord_Negative() {
//
//		List<ZipToDest> allZDRecord = new ArrayList<>();
//
//		ZipToDest zipToDest = new ZipToDest();
//		zipToDest.setCountryCode(123);
//		zipToDest.setCreationUser("Admin");
//		zipToDest.setCancelledFlag(ZipToDestConstants.FLAG_YES);
//		zipToDest.setCancelledUser("Smith");
//		allZDRecord.add(zipToDest);
//
//		Mockito.doReturn(allZDRecord).when(zDService).getAllZDRecord();
//
//		zipToDestController.getAllZDRecord();
//
//		assertNotEquals(1234, zipToDest.getCountryCode());
//		assertNotEquals("Admins", zipToDest.getCreationUser());
//		assertNotEquals('N', zipToDest.getCancelledFlag());
//		assertNotEquals("Smit", zipToDest.getCancelledUser());
//	}
//
//	@Test
//	public void testAddZDRecord_Positive() throws ParseException, ApplicationException {
//
//		ZipToDestAddRequest zipToDestAddRequest = new ZipToDestAddRequest();
//		zipToDestAddRequest.setCountryCode(123);
//		zipToDestAddRequest.setEffectiveDate("2019-08-22.10:00:00");
//		zipToDestAddRequest.setTimeZone("(GMT-5:00) Canada/Central");
//
//		ZipToDest addZDRecord = new ZipToDest();
//
//		Mockito.doReturn(addZDRecord).when(zDService).addZDRecord(zipToDestAddRequest);
//		Mockito.doReturn(ZipToDestConstants.VALID).when(validationUtil).validateAddRequest(zipToDestAddRequest);
//
//		zipToDestController.addZDRecord(zipToDestAddRequest);
//
//		assertEquals(123, zipToDestAddRequest.getCountryCode());
//		assertEquals("2019-08-22.10:00:00", zipToDestAddRequest.getEffectiveDate());
//		assertEquals("(GMT-5:00) Canada/Central", zipToDestAddRequest.getTimeZone());
//	}
//
//	@Test
//	public void testAddZDRecord_Negative() throws ParseException, ApplicationException {
//
//		ZipToDestAddRequest zipToDestAddRequest = new ZipToDestAddRequest();
//		zipToDestAddRequest.setCountryCode(123);
//		zipToDestAddRequest.setEffectiveDate("2019-08-22.10:00:00");
//		zipToDestAddRequest.setTimeZone("(GMT-5:00) Canada/Central");
//
//		ZipToDest addZDRecord = new ZipToDest();
//
//		Mockito.doReturn(ZipToDestConstants.VALID).when(validationUtil).validateAddRequest(zipToDestAddRequest);
//		Mockito.doReturn(addZDRecord).when(zDService).addZDRecord(zipToDestAddRequest);
//
//		zipToDestController.addZDRecord(zipToDestAddRequest);
//
//		assertNotEquals(121, zipToDestAddRequest.getCountryCode());
//		assertNotEquals("2019-08-21.10:00:00", zipToDestAddRequest.getEffectiveDate());
//		assertNotEquals("(GMT-6:00) Canada/Central", zipToDestAddRequest.getTimeZone());
//	}
//
//	@Test
//	public void testAddZDRecord_Negative2() throws ParseException, ApplicationException {
//
//		ZipToDestAddRequest zipToDestAddRequest = new ZipToDestAddRequest();
//		zipToDestAddRequest.setCountryCode(123);
//		zipToDestAddRequest.setEffectiveDate("2019-08-22.10:00:00");
//		zipToDestAddRequest.setTimeZone("(GMT-5:00) Canada/Central");
//
//		ZipToDest addZDRecord = new ZipToDest();
//
//		Mockito.doReturn(ZipToDestConstants.VALID).when(validationUtil).validateAddRequest(zipToDestAddRequest);
//		Mockito.doReturn(addZDRecord).when(zDService).addZDRecord(zipToDestAddRequest);
//
//		zipToDestController.addZDRecord(zipToDestAddRequest);
//
//		assertNotEquals(121, zipToDestAddRequest.getCountryCode());
//		assertNotEquals("2019-08-21.10:00:00", zipToDestAddRequest.getEffectiveDate());
//		assertNotEquals("(GMT-6:00) Canada/Central", zipToDestAddRequest.getTimeZone());
//	}
//
//	@Test
//	public void testModifyZDRecord_Positive() throws ApplicationException, ParseException {
//		String response = "success";
//
//		ZipToDestModifyRequest zipToDestModifyRequest = new ZipToDestModifyRequest();
//		zipToDestModifyRequest.setDestinationTerminal("321");
//		zipToDestModifyRequest.setEffectiveDate("2019-08-22.10:00:00");
//		zipToDestModifyRequest.setTimeZone("(GMT-5:00) Canada/Central");
//		zipToDestModifyRequest.setZipFrom("10000");
//		zipToDestModifyRequest.setZipTo("10000");
//
//		ZipToDest zipToDest = new ZipToDest();
//		zipToDest.setCountryCode(123);
//
//		Mockito.doReturn(ZipToDestConstants.VALID).when(validationUtil).validateModifyRequest(zipToDestModifyRequest);
//		assertEquals("321", zipToDestModifyRequest.getDestinationTerminal());
//		assertEquals("2019-08-22.10:00:00", zipToDestModifyRequest.getEffectiveDate());
//		assertEquals("(GMT-5:00) Canada/Central", zipToDestModifyRequest.getTimeZone());
//		assertEquals("10000", zipToDestModifyRequest.getZipFrom());
//		assertEquals("10000", zipToDestModifyRequest.getZipTo());
//
//	}
//
//	@Test
//	public void testModifyZDRecord_IfCondition() throws ParseException, ApplicationException {
//
//		ZipToDestModifyRequest zipToDestModifyRequest = new ZipToDestModifyRequest();
//		zipToDestModifyRequest.setDestinationTerminal("321");
//		zipToDestModifyRequest.setEffectiveDate("2019-08-22.10:00:00");
//		zipToDestModifyRequest.setTimeZone("(GMT-5:00) Canada/Central");
//		zipToDestModifyRequest.setZipFrom("10000");
//		zipToDestModifyRequest.setZipTo("10000");
//
//		ZipToDest zipToDest = new ZipToDest();
//		zipToDest.setCountryCode(123);
//
//		Mockito.doReturn(ZipToDestConstants.INVALID).when(validationUtil).validateModifyRequest(zipToDestModifyRequest);
//
//		zipToDestController.modifyZDRecord(zipToDestModifyRequest);
//
//	}
//
//	@Test
//	public void testModifyZDRecord_IfCondition1() throws ParseException, ApplicationException {
//
//		ZipToDestModifyRequest zipToDestModifyRequest = new ZipToDestModifyRequest();
//		zipToDestModifyRequest.setDestinationTerminal(null);
//		zipToDestModifyRequest.setEffectiveDate("2019-08-22");
//		zipToDestModifyRequest.setTimeZone("(GMT-5:00) ");
//		zipToDestModifyRequest.setZipFrom(null);
//		zipToDestModifyRequest.setZipTo("10000");
//
//		Mockito.doReturn(ZipToDestConstants.VALID).when(validationUtil).validateModifyRequest(zipToDestModifyRequest);
//
//		Mockito.doReturn(null).when(zDService).modifyRecords(zipToDestModifyRequest);
//
//		zipToDestController.modifyZDRecord(zipToDestModifyRequest);
//
//	}
//
//	@Test
//	public void testModifyZDRecord_IfCondition2() throws ParseException, ApplicationException {
//
//		ZipToDestModifyRequest zipToDestModifyRequest = new ZipToDestModifyRequest();
//		zipToDestModifyRequest.setDestinationTerminal(null);
//		zipToDestModifyRequest.setEffectiveDate("2019-08-22");
//		zipToDestModifyRequest.setTimeZone("(GMT-5:00) ");
//		zipToDestModifyRequest.setZipFrom(null);
//		zipToDestModifyRequest.setZipTo("10000");
//
//		Mockito.doReturn(ZipToDestConstants.VALID).when(validationUtil).validateModifyRequest(zipToDestModifyRequest);
//
//		Mockito.doReturn("success").when(zDService).modifyRecords(zipToDestModifyRequest);
//
//		zipToDestController.modifyZDRecord(zipToDestModifyRequest);
//
//	}
//
//	@Test
//	public void testModifyZDRecord_Negative() throws ApplicationException, ParseException {
//		String response = " ";
//
//		ZipToDestModifyRequest zipToDestModifyRequest = new ZipToDestModifyRequest();
//		zipToDestModifyRequest.setDestinationTerminal("321");
//		zipToDestModifyRequest.setEffectiveDate("2019-08-22.10:00:00");
//		zipToDestModifyRequest.setTimeZone("(GMT-5:00) Canada/Central");
//		zipToDestModifyRequest.setZipFrom("10000");
//		zipToDestModifyRequest.setZipTo("10000");
//
//		ZipToDest zipToDest = new ZipToDest();
//		zipToDest.setCountryCode(123);
//
//		Mockito.doReturn(ZipToDestConstants.VALID).when(validationUtil).validateModifyRequest(zipToDestModifyRequest);
//		Mockito.doReturn(response).when(zDService).modifyRecords(zipToDestModifyRequest);
//		zipToDestController.modifyZDRecord(zipToDestModifyRequest);
//		assertNotEquals("322", zipToDestModifyRequest.getDestinationTerminal());
//		assertNotEquals("2019-03-22.10:00:00", zipToDestModifyRequest.getEffectiveDate());
//		assertNotEquals("(GMT-6:00) Canada/Central", zipToDestModifyRequest.getTimeZone());
//		assertNotEquals("20000", zipToDestModifyRequest.getZipFrom());
//		assertNotEquals("30000", zipToDestModifyRequest.getZipTo());
//	}
//
//	@Test
//	public void testDeleteZDRecord_Positive() throws ApplicationException, ParseException {
//		String response = "success";
//		ZipToDestDeleteRequest zipToDestDeleteRequest = new ZipToDestDeleteRequest();
//		zipToDestDeleteRequest.setEffectiveDate("2019-08-22.10:00:00");
//		zipToDestDeleteRequest.setTimeZone("(GMT-5:00) Canada/Central");
//		zipToDestDeleteRequest.setNetwork("FXG");
//		zipToDestDeleteRequest.setZipFrom("10000");
//		zipToDestDeleteRequest.setZipTo("20000");
//
//		Mockito.doReturn(response).when(zDService).deleteRecords(zipToDestDeleteRequest);
//		zipToDestController.deleteZDRecord(zipToDestDeleteRequest);
//		assertEquals("2019-08-22.10:00:00", zipToDestDeleteRequest.getEffectiveDate());
//		assertEquals("(GMT-5:00) Canada/Central", zipToDestDeleteRequest.getTimeZone());
//		assertEquals("FXG", zipToDestDeleteRequest.getNetwork());
//		assertEquals("10000", zipToDestDeleteRequest.getZipFrom());
//		assertEquals("20000", zipToDestDeleteRequest.getZipTo());
//	}
//
//	@Test
//	public void testDeleteZDRecord_Negative() throws ApplicationException, ParseException {
//		ZipToDestDeleteRequest zipToDestDeleteRequest = new ZipToDestDeleteRequest();
//		String response = " ";
//		zipToDestDeleteRequest.setEffectiveDate("2019-08-22.10:00:00");
//		zipToDestDeleteRequest.setTimeZone("(GMT-5:00) Canada/Central");
//		zipToDestDeleteRequest.setNetwork("FXG");
//		zipToDestDeleteRequest.setZipFrom("10000");
//		zipToDestDeleteRequest.setZipTo("20000");
//
//		Mockito.doReturn(response).when(zDService).deleteRecords(zipToDestDeleteRequest);
//		zipToDestController.deleteZDRecord(zipToDestDeleteRequest);
//		assertNotEquals("2019-09-22.10:00:00", zipToDestDeleteRequest.getEffectiveDate());
//		assertNotEquals("(GMT-6:00) Canada/Central", zipToDestDeleteRequest.getTimeZone());
//		assertNotEquals("FXGL", zipToDestDeleteRequest.getNetwork());
//		assertNotEquals("1000", zipToDestDeleteRequest.getZipFrom());
//		assertNotEquals("2000", zipToDestDeleteRequest.getZipTo());
//	}
//
//	@Test
//	public void testDeleteZDRecord_Condition() throws ApplicationException, ParseException {
//		ZipToDestDeleteRequest zipToDestDeleteRequest = new ZipToDestDeleteRequest();		
//		zipToDestDeleteRequest.setEffectiveDate("2019-08-22.10:00:00");
//		zipToDestDeleteRequest.setTimeZone("(GMT-5:00) Canada/Central");
//		zipToDestDeleteRequest.setNetwork("FXG");
//		zipToDestDeleteRequest.setZipFrom("10000");
//		zipToDestDeleteRequest.setZipTo("20000");
//
//		Mockito.doReturn(ZipToDestConstants.VALID).when(validationUtil).validateDeleteRequest(zipToDestDeleteRequest);
//
//		zipToDestController.deleteZDRecord(zipToDestDeleteRequest);
//
//	}
//
//	@Test
//	public void testDeleteZDRecord_Condition1() throws ApplicationException, ParseException {
//		ZipToDestDeleteRequest zipToDestDeleteRequest = new ZipToDestDeleteRequest();		
//		zipToDestDeleteRequest.setEffectiveDate("2019-08-22.10:00:00");
//		zipToDestDeleteRequest.setTimeZone("(GMT-5:00) Canada/Central");
//		zipToDestDeleteRequest.setNetwork("FXG");
//		zipToDestDeleteRequest.setZipFrom("10000");
//		zipToDestDeleteRequest.setZipTo("20000");
//
//		Mockito.doReturn(ZipToDestConstants.VALID).when(validationUtil).validateDeleteRequest(zipToDestDeleteRequest);
//
//		Mockito.doReturn("success").when(zDService).deleteRecords(zipToDestDeleteRequest);
//
//		zipToDestController.deleteZDRecord(zipToDestDeleteRequest);
//
//	}	
//	
//	@Test
//	public void testGetLatestProcessedTransactions(){
//		List<ZipToDest> zipToDestList = new ArrayList<>();
//		ZipToDest zipToDest = new ZipToDest();
//		zipToDest.setProcessed(ZipToDestConstants.FLAG_YES);		
//		zipToDest.setZipCode("12301");	
//		zipToDest.setCurrent(ZipToDestConstants.FLAG_YES);
//		zipToDest.setProcessedDateTime(ZonedDateTime.now(ZoneOffset.UTC).toEpochSecond());
//		zipToDest.setCancelledTimestamp(ZonedDateTime.now(ZoneOffset.UTC).toEpochSecond());
//		zipToDestList.add(zipToDest);
//		
//		Mockito.doReturn(zipToDestList).when(zDService).findLatestProcesedTransactions("2019-12-02.10:00:00");		
//		ResponseEntity<List<ZipToDest>> response = zipToDestController.getLatestProcessedTransactions("2019-12-02.10:00:00");
//		assertEquals(HttpStatus.OK, response.getStatusCode());		
//	}
//	
//	@Test
//	public void testGetAllPendingTransanctions(){
//		List<ZipToDest> allZDData = new ArrayList<>();
//		ZipToDest zipToDest = new ZipToDest();
//		zipToDest.setCountryCode(123);
//		zipToDest.setCancelledFlag(ZipToDestConstants.FLAG_NO);
//		zipToDest.setCurrent(ZipToDestConstants.FLAG_NO);
//		zipToDest.setProcessed(ZipToDestConstants.FLAG_NO);
//		zipToDest.setCreationUser("Smith");
//		zipToDest.setDestinationTerminal("11");
//		allZDData.add(zipToDest);
//		
//		Mockito.doReturn(allZDData).when(zDService).getAllPendingTransanctions();		
//		ResponseEntity<List<ZipToDest>> response = zipToDestController.getAllPendingTransanctions();
//		assertEquals(HttpStatus.OK, response.getStatusCode());		
//	}
//	
//	@Test
//	public void testGetHistoricalTransanctions(){
//		List<ZipToDest> allZDData = new ArrayList<>();
//		ZipToDest zipToDest = new ZipToDest();
//		zipToDest.setCountryCode(123);
//		zipToDest.setCancelledFlag(ZipToDestConstants.FLAG_NO);
//		zipToDest.setCurrent(ZipToDestConstants.FLAG_NO);
//		zipToDest.setProcessed(ZipToDestConstants.FLAG_NO);
//		zipToDest.setCreationUser("Smith");
//		zipToDest.setDestinationTerminal("11");
//		allZDData.add(zipToDest);
//		
//		Mockito.doReturn(allZDData).when(zDService).getHistoryTransactions();		
//		ResponseEntity<List<ZipToDest>> response = zipToDestController.getHistoricalTransactions();
//		assertEquals(HttpStatus.OK, response.getStatusCode());		
//	}
//
//}