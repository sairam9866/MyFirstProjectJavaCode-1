package com.fedex.ziptodest.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Test;

import com.fedex.ziptodest.model.Network;

public class NetworkTest {	
	
	@Test
	public void networkBeansTest_Positive() {
		LocalDate localDate = LocalDate.of(2019, 06, 06);
		Network networkBean = new Network();
		networkBean.setColocNum(1);
		networkBean.setCreatedBy("krishna");
		networkBean.setCreatedDate(localDate);
		networkBean.setModelType("M");
		networkBean.setNetworkId("ABC");
		networkBean.setRowId(10);
		networkBean.setTermNum(12L);
		networkBean.setUpdateDate(localDate);
		networkBean.setUpdatedBy("krishna");
		assertEquals(Integer.valueOf(1), networkBean.getColocNum());
		assertEquals("krishna", networkBean.getCreatedBy());
		assertEquals(localDate, networkBean.getCreatedDate());
		assertEquals("M", networkBean.getModelType());
		assertEquals("ABC", networkBean.getNetworkId());
		assertEquals(Integer.valueOf(10), networkBean.getRowId());
		assertEquals(Long.valueOf(12), networkBean.getTermNum());
		assertEquals(localDate, networkBean.getUpdateDate());
		assertEquals("krishna", networkBean.getUpdatedBy());
	}

	@Test
	public void networkBeansTest_Negitive() {
		LocalDate localDate = LocalDate.of(2019, 07, 06);
		Network networkBean = new Network();
		networkBean.setColocNum(1);
		networkBean.setCreatedBy("krishna");
		networkBean.setCreatedDate(localDate);
		networkBean.setModelType("M");
		networkBean.setNetworkId("ABC");
		networkBean.setRowId(10);
		networkBean.setTermNum(12L);
		networkBean.setUpdateDate(localDate);
		networkBean.setUpdatedBy("krishna");
		assertNotEquals(Integer.valueOf(11), networkBean.getColocNum());
		assertNotEquals("krishnaa", networkBean.getCreatedBy());
		assertNotEquals("A", networkBean.getModelType());
		assertNotEquals("ABCC", networkBean.getNetworkId());
		assertNotEquals(Integer.valueOf(100), networkBean.getRowId());
		assertNotEquals(Long.valueOf(121), networkBean.getTermNum());
		assertNotEquals("krishnaa", networkBean.getUpdatedBy());
	}

	@Test
	public void testToString() {
		Network networkBean = new Network();
		assertNotEquals(null, networkBean.toString());
	}
	
	@Test
	public void testHashCode() {
		Network network = new Network();
		assertTrue(network.hashCode() > 0);
		network.setTermNum(1452L);
		assertTrue(network.hashCode() > 0);
	}
	
	@Test
	public void testEquals() {			
		Network network = new Network();
		assertTrue(network.equals(network));
		
		Network network2 = null;
		assertFalse(network.equals(network2));
		assertFalse(network.equals(new Object()));
		
		network2 = new Network();
		network.setTermNum(null);
		network2.setTermNum(1245L);
		assertFalse(network.equals(network2));
		
		network2 = new Network();
		network.setTermNum(null);
		network2.setTermNum(null);
		assertTrue(network.equals(network2));	
		
		network.setTermNum(1234L);
		network2.setTermNum(1234L);
		assertTrue(network.equals(network2));
		
		network.setTermNum(123L);
		assertFalse(network.equals(network2));
	}
}
