package com.fedex.ziptodest.controller.server;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doThrow;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fedex.ziptodest.model.ZipToDestCancelRequest;
import com.fedex.ziptodest.model.ZipToDestNotProcessed;
import com.fedex.ziptodest.model.ZipToDestStatusResponse;
import com.fedex.ziptodest.service.server.ZDService;
import com.fedex.ziptodest.utils.server.ValidationUtil;
import com.fedex.ziptodest.utils.server.ZDConstants;



public class ZipToDestControllerCancelTest {

	@InjectMocks
	private ZipToDestController zipToDestController;

	@Mock
	private ZDService zDService;

	@Mock
	private ValidationUtil validationUtil;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetNotProcessedZDRecord_Positive() {
		List<ZipToDestNotProcessed> listNotProcessedZDRecord = new ArrayList<>();
		ZipToDestNotProcessed zipToDest = new ZipToDestNotProcessed();
		zipToDest.setCountryCode(123);
		zipToDest.setCreationUser("Admin");
		zipToDest.setProcessed(ZDConstants.FLAG_NO);
		zipToDest.setUuid("1234");
		zipToDest.setZipFrom("234");
		zipToDest.setZipTo("234");

		listNotProcessedZDRecord.add(zipToDest);

		Mockito.doReturn(listNotProcessedZDRecord).when(zDService).getNotProcessedZDRecords();

		ResponseEntity<List<ZipToDestNotProcessed>> response = zipToDestController.getNotProcessedZDRecord();

		assertNotNull(response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	public void testCancelZDRecord() {

		ZipToDestCancelRequest zipToDestCancelRequest = new ZipToDestCancelRequest();

		zipToDestCancelRequest.setUser("FedExUsr001");

		List<String> uuids = new ArrayList<>();
		uuids.add("AXD-RDRTT-FFG");
		zipToDestCancelRequest.setUuid(uuids);

		Mockito.doReturn(1).when(zDService).cancelZipToDestNotProcessed(zipToDestCancelRequest);

		Mockito.doReturn("Valid").when(validationUtil).validateCancelRequest(zipToDestCancelRequest);

		ResponseEntity<ZipToDestStatusResponse> response1 = zipToDestController
				.cancelNotProcessedZDRecord(zipToDestCancelRequest);

		assertEquals(ZDConstants.RESPONSE_CANCEL_SUCCESS_MSG, response1.getBody().getMessage());
		assertEquals(HttpStatus.OK, response1.getStatusCode());

		Mockito.doReturn(0).when(zDService).cancelZipToDestNotProcessed(zipToDestCancelRequest);

		ResponseEntity<ZipToDestStatusResponse> response2 = zipToDestController
				.cancelNotProcessedZDRecord(zipToDestCancelRequest);

		assertEquals(ZDConstants.RESPONSE_CANCEL_SUCCESS_MSG, response2.getBody().getMessage());
		assertEquals(HttpStatus.NO_CONTENT, response2.getStatusCode());
		
		doThrow(new RuntimeException()).when(zDService).cancelZipToDestNotProcessed(zipToDestCancelRequest);
		
		ResponseEntity<ZipToDestStatusResponse> response3 = zipToDestController
				.cancelNotProcessedZDRecord(zipToDestCancelRequest);

		assertEquals(ZDConstants.RESPONSE_CANCEL_ERROR_MSG, response3.getBody().getMessage());
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response3.getStatusCode());
		
		Mockito.doReturn(ZDConstants.INVALID).when(validationUtil).validateCancelRequest(zipToDestCancelRequest);
		
		ResponseEntity<ZipToDestStatusResponse> response4 = zipToDestController
				.cancelNotProcessedZDRecord(zipToDestCancelRequest);
		
		assertEquals(ZDConstants.INVALID, response4.getBody().getMessage());
		assertEquals(HttpStatus.BAD_REQUEST, response4.getStatusCode());
		
		
	}

}