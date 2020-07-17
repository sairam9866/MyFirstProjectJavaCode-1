package com.fedex.ziptodest.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.fedex.ziptodest.model.ZipToDestStatusResponse;

public class ZipToDestStatusResponseTest {
	ZipToDestStatusResponse zipToDestStatusResponse = new ZipToDestStatusResponse();

	@Before
	public void addRecord() {
		zipToDestStatusResponse.setMessage("Message Response");
		zipToDestStatusResponse.setStatus("success");
	}

	@Test
	public void testZipToDestStatusResponseTest() {
		assertEquals("Message Response", zipToDestStatusResponse.getMessage());
		assertEquals("success", zipToDestStatusResponse.getStatus());
	}

}
