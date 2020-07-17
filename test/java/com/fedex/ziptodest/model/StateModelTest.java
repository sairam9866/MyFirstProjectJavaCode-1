package com.fedex.ziptodest.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.fedex.ziptodest.model.StateModel;

public class StateModelTest {

	
	@Test
	public void testState_Positive() {
		StateModel state = new StateModel();
		state.setCode(124);
		state.setState("CA");

		assertEquals(124, state.getCode());
		assertEquals("CA", state.getState());
	}
	@Test
	public void testState_Negitive() {
		StateModel state = new StateModel();
		state.setCode(840);
		state.setState("NY");

		assertNotEquals(124, state.getCode());
		assertNotEquals("CA", state.getState());

	}

	@Test
	public void testToString() {
		StateModel state = new StateModel();
		assertNotEquals(null, state.toString());
	}
	
	@Test
	public void testHashCode() {
		StateModel state = new StateModel();
		assertTrue(state.hashCode() > 0);
		state.setCode(1);
		state.setState("WA");
		assertTrue(state.hashCode() > 0);
	}
	
	@Test
	public void testEquals() {			
		StateModel state = new StateModel();
		assertTrue(state.equals(state));
		
		StateModel state2 = null;
		assertFalse(state.equals(state2));
		assertFalse(state.equals(new Object()));
		
		state2 = new StateModel();
		state.setCode(0);
		state2.setCode(1);
		assertFalse(state.equals(state2));
		
		state.setState(null);
		state2.setState("QA");
		state.setCode(1);
		state2.setCode(1);
		assertFalse(state.equals(state2));	
		
		state.setState(null);
		state2.setState(null);
		state.setCode(1);
		state2.setCode(1);
		assertTrue(state.equals(state2));
		
		state.setState("AQ");
		state2.setState("AQ");
		state.setCode(1);
		state2.setCode(1);
		assertTrue(state.equals(state2));
		
		state.setState("AQ");
		state2.setState("QA");
		state.setCode(1);
		state2.setCode(1);
		assertFalse(state.equals(state2));
	}
}
