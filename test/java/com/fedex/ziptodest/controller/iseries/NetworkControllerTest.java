/*package com.fedex.ziptodest.controller.iseries;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.fedex.ziptodest.model.Network;
import com.fedex.ziptodest.model.NetworkModel;
import com.fedex.ziptodest.model.NetworkRequest;
import com.fedex.ziptodest.service.iseries.NetworkService;

@RunWith(SpringRunner.class)
public class NetworkControllerTest {

	@InjectMocks
	private NetworkController networkController;
	
	@Mock
	private NetworkService networkService;

	*//***
	 * Positive Test method for Response Bean NetworkModel
	 *//*
	@Test
	public void testgetAllNetworks_Positive() {
		Set<String> data = new HashSet<String>();
		data.add("FXGL");
		data.add("FHDL");
		data.add("FXG");
		
		NetworkModel networkModel = new NetworkModel();
		networkModel.setData(data);
		networkModel.setMessage("The messege is getting success");
		networkModel.setStatus("success");

		Mockito.doReturn(networkModel).when(networkService).getAllNetworks();
		networkController.getAllNetworks();

		assertEquals("success", networkModel.getStatus());
		assertEquals("The messege is getting success", networkModel.getMessage());
		assertTrue(networkModel.getData().contains("FXGL"));
		assertTrue(networkModel.getData().contains("FHDL"));
		assertTrue(networkModel.getData().contains("FXG"));
	}

	*//***
	 * Negative Test method for Response Bean NetworkModel
	 *//*
	@Test
	public void testgetAllNetworks_Negative() {
		Set<String> data = new HashSet<String>();
		data.add("FXGL");
		data.add("FHDL");
		data.add("FXG");

		NetworkModel networkModel = new NetworkModel();
		networkModel.setData(data);
		networkModel.setMessage("The messege is getting success");
		networkModel.setStatus("success");

		Mockito.doReturn(networkModel).when(networkService).getAllNetworks();
		networkController.getAllNetworks();

		assertNotEquals("successs", networkModel.getMessage());
		assertNotEquals("The messege is getting successs", networkModel.getMessage());
		assertTrue(networkModel.getData().contains("FXGL"));
		assertTrue(networkModel.getData().contains("FHDL"));
		assertTrue(networkModel.getData().contains("FXG"));
	}

	*//***
	 * Positive Test method for ResponseEntity
	 *//*
	@Test
	public void testGetAllNetworksResponse_Positive() {
		ResponseEntity<NetworkModel> res = networkController.getAllNetworks();
		assertNotNull(res);
	}

	*//***
	 * Negative Test method for ResponseEntity
	 *//*
	@Test
	public void testGetAllNetworksResponse_Negative() {
		ResponseEntity<NetworkModel> res = networkController.getAllNetworks();
		assertNotEquals(null, res.getStatusCode());

	}
	
	@Test
	public void testSaveNetwork(){		
		NetworkRequest networkRequest = new NetworkRequest();
		networkRequest.setColocNum(1234);
		networkRequest.setCreatedBy("TEST");
		networkRequest.setModelType("A");
		networkRequest.setNetwork("FXG");
		networkRequest.setRowId(0);
		networkRequest.setTerminalNumber(1111L);		
		
		Network network = new Network();
		network.setColocNum(networkRequest.getColocNum());
		network.setCreatedBy(networkRequest.getCreatedBy());
		network.setModelType(networkRequest.getModelType());
		network.setNetworkId(networkRequest.getNetwork());
		network.setRowId(networkRequest.getRowId());
		network.setTermNum(networkRequest.getTerminalNumber());
		
		Mockito.doReturn(network).when(networkService).save(networkRequest);		
//		ResponseEntity<Network> output = networkController.saveNetwork(networkRequest);		
		assertEquals(HttpStatus.CREATED, output.getStatusCode());
		
		Mockito.doReturn(null).when(networkService).save(networkRequest);		
		output = networkController.saveNetwork(networkRequest);
		assertEquals(HttpStatus.BAD_REQUEST, output.getStatusCode());		
	}
}
*/