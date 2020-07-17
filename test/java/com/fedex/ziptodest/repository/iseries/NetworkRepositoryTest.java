/*package com.fedex.ziptodest.repository.iseries;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.util.ReflectionTestUtils;

import com.fedex.ziptodest.model.Network;
import com.fedex.ziptodest.repository.iseries.redis.NetworkRedisRepository;

public class NetworkRepositoryTest {

	@Mock
	@Qualifier("networkRedisRepository")
	NetworkRedisRepository networkRedisRepository;

	@InjectMocks
	NetworkRepository networkRepository;

	@Mock
	private ZSetOperations<String, String> networkSetOperations;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		ReflectionTestUtils.setField(networkRepository, "keyspace", "local");
	}

	private Network getNetwork() {
		Network network = new Network();
		network.setColocNum(10);
		network.setCreatedBy("TestUser");
		network.setNetworkId("10");
		network.setTermNum(1L);

		return network;
	}

	@Test
	public void testSave() {
		assertNull(networkRepository.save(getNetwork()));
	}

	@Test
	public void testSelectAllNetworks() {
		assertNotNull(networkRepository.selectAllNetworks());
	}

	@Test
	public void testFindById() {
		assertNotNull(networkRepository.findById(1L));
	}

	@Test
	public void testExistsById() {
		assertNotNull(networkRepository.existsById(1L));
	}

	@Test
	public void testFindAll() {
		assertNotNull(networkRepository.findAll());
	}

	@Test
	public void testCount() {
		assertNotNull(networkRepository.count());
	}

	// @Test
	// public void testDeleteById() {
	// assertNotNull(networkRepository.deleteById(1L));
	// }
	//
	// @Test
	// public void testDelete() {
	// networkRepository.delete(getNetwork());
	// }
	//
	//
	// @Test
	// public void testDeleteAll() {
	// networkRepository.deleteAll();
	// }

}
*/