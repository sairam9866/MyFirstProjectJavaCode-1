package com.fedex.ziptodest.controller.server;
import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.fedex.ziptodest.exception.server.ApplicationException;
import com.fedex.ziptodest.model.ZipToDest;
import com.fedex.ziptodest.model.ZipToDestAddRequest;
import com.fedex.ziptodest.model.ZipToDestStatusResponse;
import com.fedex.ziptodest.service.server.ZDService;
import com.fedex.ziptodest.utils.server.ValidationUtil;
import com.fedex.ziptodest.utils.server.ZDConstants;



@RunWith(SpringRunner.class)
public class ZipToDestControllerAddTest {
	
	private ZipToDest addZDRecord;
	private ZipToDestAddRequest zipToDestAddRequest;
	
	@InjectMocks
	private ZipToDestController zipToDestController;

	@Mock
	private ZDService zDService;

	@Mock
	private ValidationUtil validationUtil;
	
	@Before
	public void init(){
		addZDRecord = new ZipToDest();
		addZDRecord.setCancelledFlag(ZDConstants.FLAG_NO);
		addZDRecord.setCountryCode(840);
		addZDRecord.setCreatedDateAt(ZonedDateTime.now(ZoneOffset.UTC).toEpochSecond());
		addZDRecord.setCreationUser("Fedex01");
		addZDRecord.setCurrent(ZDConstants.FLAG_NO);
		addZDRecord.setDestinationTerminal("0011");
		addZDRecord.setEffectiveDateAt(null);
		addZDRecord.setNetwork("FXGL");
		addZDRecord.setProcessed(ZDConstants.FLAG_NO);
		addZDRecord.setState("CA");
		addZDRecord.setTimeZone("(GMT-5:00) Canada/Central");
		addZDRecord.setTransactionType(ZDConstants.TRANS_TYPE_ADD);
		addZDRecord.setUuid("XWER-RVFDT-GHFF");
		addZDRecord.setZipCode("45825");
		
		zipToDestAddRequest = new ZipToDestAddRequest();
		zipToDestAddRequest.setNetwork("FXGL");
		zipToDestAddRequest.setCountryCode(840);
		zipToDestAddRequest.setCreationUser("Fedex01");
		zipToDestAddRequest.setDestinationTerminal("0011");
		zipToDestAddRequest.setEffectiveDate(null);
		zipToDestAddRequest.setNetwork("FXGL");
		zipToDestAddRequest.setState("CA");
		zipToDestAddRequest.setTimeZone("(GMT-5:00) Canada/Central");
		zipToDestAddRequest.setZipCode("45825");
		zipToDestAddRequest.setCountryCode(123);
		zipToDestAddRequest.setEffectiveDate("2019-08-22.10:00:00");
		zipToDestAddRequest.setTimeZone("(GMT-5:00) Canada/Central");
	}

	@Test
	public void testAddZDRecord_Positive() throws ParseException, ApplicationException {				

		Mockito.doReturn(addZDRecord).when(zDService).addZDRecord(zipToDestAddRequest);
		Mockito.doReturn(ZDConstants.VALID).when(validationUtil).validateAddRequest(zipToDestAddRequest);

		ResponseEntity<ZipToDestStatusResponse> response = zipToDestController.addZDRecord(zipToDestAddRequest);

		assertEquals(ZDConstants.RESPONSE_SUCCESS_MSG, response.getBody().getStatus());
	}

	@Test
	public void testAddZDRecord_InvalidNetwork() throws ParseException, ApplicationException {
		
		zipToDestAddRequest.setNetwork(StringUtils.EMPTY);		

		Mockito.doReturn(ZDConstants.INVALID_NETWORK).when(validationUtil)
				.validateAddRequest(zipToDestAddRequest);

		ResponseEntity<ZipToDestStatusResponse> response = zipToDestController.addZDRecord(zipToDestAddRequest);

		assertEquals(ZDConstants.INVALID_NETWORK, response.getBody().getMessage());
	}

	@Test
	public void testAddZDRecord_InvalidZipcode() throws ParseException, ApplicationException {
		
		zipToDestAddRequest.setNetwork("FXGL");
		zipToDestAddRequest.setZipCode(StringUtils.EMPTY);	

		Mockito.doReturn(ZDConstants.INVALID_ZIPCODE).when(validationUtil)
				.validateAddRequest(zipToDestAddRequest);

		ResponseEntity<ZipToDestStatusResponse> response = zipToDestController.addZDRecord(zipToDestAddRequest);

		assertEquals(ZDConstants.INVALID_ZIPCODE, response.getBody().getMessage());
	}

}