package com.fedex.ziptodest.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.fedex.ziptodest.model.StateProvince;

public class StateProvinceTest {
	
	@Test
	public void testStateProvince_Positive() {
		StateProvince stateProvince = new StateProvince();
		stateProvince.setCntryc("A");
		stateProvince.setSpName("FXO");
		stateProvince.setStaPro("FXG");
		assertEquals("A", stateProvince.getCntryc());
		assertEquals("FXO", stateProvince.getSpName());
		assertEquals("FXG", stateProvince.getStaPro());
	}
	
	@Test
	public void testStateProvince_Negitive() {
		StateProvince stateProvince = new StateProvince();
		stateProvince.setCntryc("A");
		stateProvince.setSpName("FXO");
		stateProvince.setStaPro("FXG");
		assertNotEquals("AA", stateProvince.getCntryc());
		assertNotEquals("FXOO", stateProvince.getSpName());
		assertNotEquals("FXGG", stateProvince.getStaPro());
	}
	
	@Test
	public void testToString() {
		StateProvince stateProvince = new StateProvince();
		assertNotEquals(null, stateProvince.toString());
	}
	
	@Test
	public void testHashCode() {
		StateProvince stateProvince = new StateProvince();
		assertTrue(stateProvince.hashCode() > 0);
		stateProvince.setStaPro("FXG");
		assertTrue(stateProvince.hashCode() > 0);
	}
	
	@Test
	public void testEquals() {			
		StateProvince stateProvince = new StateProvince();
		assertTrue(stateProvince.equals(stateProvince));
		
		StateProvince stateProvince2 = null;
		assertFalse(stateProvince.equals(stateProvince2));
		assertFalse(stateProvince.equals(new Object()));
		
		stateProvince2 = new StateProvince();
		stateProvince.setStaPro(null);
		stateProvince2.setStaPro("FXG");
		assertFalse(stateProvince.equals(stateProvince2));
		
		stateProvince2 = new StateProvince();
		stateProvince.setStaPro(null);
		stateProvince2.setStaPro(null);
		assertTrue(stateProvince.equals(stateProvince2));
		
		
		stateProvince.setStaPro("FXG");
		stateProvince2.setStaPro("FXG");
		assertTrue(stateProvince.equals(stateProvince2));
		
		stateProvince.setStaPro("FXGL");
		assertFalse(stateProvince.equals(stateProvince2));
	}
	
}
