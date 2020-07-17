package com.fedex.ziptodest.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.fedex.ziptodest.model.Destination;

public class DestinationTest {
	
	Destination destination;	
	Destination destinationInvalid;	
	
	@Before
	public void init(){
		destination = new Destination();
		destination.setTerminalAbbreviation("TVMD");
		destination.setTerminalNumber(1234);
		destination.setTerminalStatus("A");
		
		destinationInvalid = new Destination();
		destinationInvalid.setTerminalAbbreviation("TVMDT");
		destinationInvalid.setTerminalNumber(12345);
		destinationInvalid.setTerminalStatus("A");
	}
	
	@Test
	public void testDestination(){
		assertNotNull("Terminal Abbrevaition Must not be null", destination.getTerminalAbbreviation());
		assertNotNull("Terminal Number Must not be null", destination.getTerminalNumber());
		assertNotNull("Terminal Status Must not be null", destination.getTerminalStatus());
		
		assertTrue((destination.getTerminalNumber() >=1 && destination.getTerminalNumber() <= 9999));
		assertTrue((destination.getTerminalAbbreviation().length() >=1 && destination.getTerminalAbbreviation().length() <= 4));
		assertTrue(String.valueOf(destination.getTerminalStatus()).length() == 1);
	}
	
	@Test
	public void testDestination_Negative(){
		assertFalse((destinationInvalid.getTerminalNumber() >=1 && destinationInvalid.getTerminalNumber() <= 9999));
		assertFalse((destinationInvalid.getTerminalAbbreviation().length() >=1 && destinationInvalid.getTerminalAbbreviation().length() <= 4));
	}
	
	@Test
	public void testToString() {
		Destination destination = new Destination();
		assertNotEquals(null, destination.toString());
	}
	
	@Test
	public void testHashCode() {
		Destination destination = new Destination();
		assertTrue(destination.hashCode() > 0);
		destination.setTerminalNumber(1234);
		assertTrue(destination.hashCode() > 0);
	}
	
	@Test
	public void testEquals() {			
		Destination destination = new Destination();
		assertTrue(destination.equals(destination));
		
		Destination destination2 = null;
		assertFalse(destination.equals(destination2));
		assertFalse(destination.equals(new Object()));
		
		destination2 = new Destination();
		destination.setTerminalNumber(null);
		destination2.setTerminalNumber(1234);
		assertFalse(destination.equals(destination2));
		
		destination2 = new Destination();
		destination.setTerminalNumber(null);
		destination2.setTerminalNumber(null);
		assertTrue(destination.equals(destination2));		
		
		destination.setTerminalNumber(1234);
		destination2.setTerminalNumber(1234);
		assertTrue(destination.equals(destination2));
		
		destination.setTerminalNumber(123);
		assertFalse(destination.equals(destination2));
	}
}