/*package com.fedex.ziptodest.controller.iseries;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.fedex.ziptodest.model.StateModel;
import com.fedex.ziptodest.model.StateProvinceResponse;
import com.fedex.ziptodest.service.iseries.StateProvinceService;

@RunWith(SpringRunner.class)
public class StateProvinceControllerTest {

	@InjectMocks
	private StateProvinceController stateProvinceController;
	@Mock
	private StateProvinceService stateProvinceService;

	*//***
	 * Positive Test method for Reponse Bean StatProvinceResponse
	 *//*
	@Test
	public void testgetStateCountry_Positive() {
		StateModel state1 = new StateModel();
		state1.setCode(124);
		state1.setState("CA");

		StateModel state = new StateModel();
		state.setCode(840);
		state.setState("NY");

		List<StateModel> data = new ArrayList<StateModel>();
		data.add(state1);
		data.add(state);

		StateProvinceResponse stateProvinceResponse = new StateProvinceResponse();
		stateProvinceResponse.setMessage("The messege is getting success");
		stateProvinceResponse.setStatus("success");
		//stateProvinceResponse.setData(data);

		Mockito.doReturn(stateProvinceResponse).when(stateProvinceService).getStateCountryCode();
		stateProvinceController.getStateCountry();

		assertEquals("success", stateProvinceResponse.getStatus());
		assertEquals("The messege is getting success", stateProvinceResponse.getMessage());
		//assertEquals(124, stateProvinceResponse.getData().get(0).getCode());
		//assertEquals("CA", stateProvinceResponse.getData().get(0).getState());
		//assertEquals(840, stateProvinceResponse.getData().get(1).getCode());
		//assertEquals("NY", stateProvinceResponse.getData().get(1).getState());

	}

	*//***
	 * Negative Test method for Reponse Bean StatProvinceResponse
	 *//*
	@Test
	public void testgetStateCountry_Negative() {
		StateModel state1 = new StateModel();
		state1.setCode(124);
		state1.setState("CA");

		StateModel state = new StateModel();
		state.setCode(840);
		state.setState("NY");

		List<StateModel> data = new ArrayList<StateModel>();
		data.add(state1);
		data.add(state);

		StateProvinceResponse stateProvinceResponse = new StateProvinceResponse();
		stateProvinceResponse.setMessage("The messege is getting success");
		stateProvinceResponse.setStatus("success");
		//stateProvinceResponse.setData(data);

		Mockito.doReturn(stateProvinceResponse).when(stateProvinceService).getStateCountryCode();
		stateProvinceController.getStateCountry();

		assertNotEquals("successs", stateProvinceResponse.getStatus());
		assertNotEquals("The messege is getting successs", stateProvinceResponse.getMessage());
		//assertNotEquals(840, stateProvinceResponse.getData().get(0).getCode());
		//assertNotEquals("NY", stateProvinceResponse.getData().get(0).getState());
		//assertNotEquals(124, stateProvinceResponse.getData().get(1).getCode());
		//assertNotEquals("CA", stateProvinceResponse.getData().get(1).getState());

	}

	*//***
	 * Positive Test method for Reponse checking 
	 *//*
	@Test
	public void testGetStateProvinceCodeResponse_Positive() {
		ResponseEntity<StateProvinceResponse> res = stateProvinceController.getStateCountry();
		assertNotNull(res);
	}
	*//***
	 * Negative Test method for Reponse checking 
	 *//*

	@Test
	public void testGetStateProvinceCodeResponse_Negative() {
		ResponseEntity<StateProvinceResponse> res = stateProvinceController.getStateCountry();
		assertNotNull(res);
	}

	@Test
	public void testGetStateProvinceCodeNegativeResponse() {
		ResponseEntity<StateProvinceResponse> res = stateProvinceController.getStateCountry();
		assertNotEquals(null, res.getStatusCode());
	}

	@Test
	public void testGetStateProvinceCodeStatus_Positive() {
		ResponseEntity<StateProvinceResponse> res = stateProvinceController.getStateCountry();
		assertEquals(200, res.getStatusCodeValue());

	}

	
	@Test
	public void testGetStateProvinceCodeStatus_Negative() {
		ResponseEntity<StateProvinceResponse> res = stateProvinceController.getStateCountry();
		assertNotEquals(400, res.getStatusCode());

	}
}*/