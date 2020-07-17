package com.fedex.ziptodest.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.fedex.ziptodest.model.NetworkRequest;

public class NetworkRequestTest {

	@Test
	public void testNetworkRequest(){
		NetworkRequest networkRequest = new NetworkRequest();
		networkRequest.setColocNum(1234);
		networkRequest.setCreatedBy("TEST");
		networkRequest.setModelType("A");
		networkRequest.setNetwork("FXG");
		networkRequest.setRowId(0);
		networkRequest.setTerminalNumber(1111L);
		
		assertEquals(Integer.valueOf(1234), networkRequest.getColocNum());
		assertEquals("TEST", networkRequest.getCreatedBy());
		assertEquals("A",networkRequest.getModelType());
		assertEquals("FXG", networkRequest.getNetwork());
		assertEquals(Integer.valueOf(0), networkRequest.getRowId());
		assertEquals(Long.valueOf(1111), networkRequest.getTerminalNumber());
		assertNotNull(networkRequest.toString());
	}
}
