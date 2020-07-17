package com.fedex.ziptodest.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.fedex.ziptodest.model.CountryCode;

public class CountryCodeTest {

	@Test
	public void testcountryCode_Positive() {
		CountryCode countryCodeBean = new CountryCode();
		countryCodeBean.setCyclcu(1);
		countryCodeBean.setCycode("ABC");
		assertEquals(Integer.valueOf(1), countryCodeBean.getCyclcu());
		assertEquals("ABC", countryCodeBean.getCycode());
	}

	@Test
	public void testcountryCode_Negitive() {
		CountryCode countryCodeBean = new CountryCode();
		countryCodeBean.setCyclcu(1);
		countryCodeBean.setCycode("ABC");
		assertNotEquals(Integer.valueOf(11), countryCodeBean.getCyclcu());
		assertNotEquals("ABCC", countryCodeBean.getCycode());
	}

	@Test
	public void testToString() {
		CountryCode countryCode = new CountryCode();
		assertNotEquals(null, countryCode.toString());
	}
	
	@Test
	public void testHashCode() {
		CountryCode countryCode = new CountryCode();
		assertTrue(countryCode.hashCode() > 0);
		countryCode.setCycode("US");
		assertTrue(countryCode.hashCode() > 0);
	}
	
	@Test
	public void testEquals() {			
		CountryCode countryCode = new CountryCode();
		assertTrue(countryCode.equals(countryCode));
		
		CountryCode countryCode2 = null;
		assertFalse(countryCode.equals(countryCode2));
		assertFalse(countryCode.equals(new Object()));
		
		countryCode2 = new CountryCode();
		countryCode.setCycode(null);
		countryCode2.setCycode("US");
		assertFalse(countryCode.equals(countryCode2));
		
		countryCode2 = new CountryCode();
		countryCode.setCycode(null);
		countryCode2.setCycode(null);
		assertTrue(countryCode.equals(countryCode2));		
		
		countryCode.setCycode("US");
		countryCode2.setCycode("US");
		assertTrue(countryCode.equals(countryCode2));
		
		countryCode.setCycode("CA");
		assertFalse(countryCode.equals(countryCode2));
	}
}
