package com.fedex.ziptodest.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.fedex.ziptodest.model.NetworkModel;


public class NetworkModelTest {

	@Test
	public void testNetworkBeanModels_Positive() {
		NetworkModel networkBean = new NetworkModel();
		Set<String> networksids = new HashSet<>();
		networksids.add("FXG");
		networksids.add("FXO");
		networkBean.setData(networksids);
		assertTrue(networkBean.getData().contains("FXG"));
		assertTrue(networkBean.getData().contains("FXO"));		
		assertTrue(networkBean.equals(networkBean));
	}

	@Test
	public void testNetworkBeanModels_Negitive() {
		NetworkModel networkBean = new NetworkModel();
		Set<String> networksids = new HashSet<>();
		networksids.add("FXG");
		networksids.add("FXO");
		networkBean.setData(networksids);
		assertFalse(networkBean.getData().contains("FXGG"));
		assertFalse(networkBean.getData().contains("FXOO"));
	}

	@Test
	public void testToString() {
		NetworkModel networkBean = new NetworkModel();
		assertNotEquals(null, networkBean.toString());
	}
}
