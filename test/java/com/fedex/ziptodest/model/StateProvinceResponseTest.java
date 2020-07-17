package com.fedex.ziptodest.model;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

import com.fedex.ziptodest.model.StateModel;
import com.fedex.ziptodest.model.StateProvinceResponse;

public class StateProvinceResponseTest {

	@Test
	public void testStateProvinceModels_Positive() {
		StateProvinceResponse stateProvinceResponse = new StateProvinceResponse();
		stateProvinceResponse.setMessage("Hi");
		stateProvinceResponse.setStatus("200");
		StateModel state = new StateModel();
		state.setState("CA");
		state.setCode(124);
		Set<StateModel> states = new TreeSet<>();
		states.add(state);
		stateProvinceResponse.setData(states);		
		assertNotNull(stateProvinceResponse.getData());
		assertNotNull(stateProvinceResponse);
	}
	@Test
	public void testToString() {
		StateProvinceResponse stateProvinceResponse = new StateProvinceResponse();
		assertNotEquals(null, stateProvinceResponse.toString());
	}	
}
