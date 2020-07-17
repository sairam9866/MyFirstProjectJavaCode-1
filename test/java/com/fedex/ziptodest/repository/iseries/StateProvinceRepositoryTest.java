/*package com.fedex.ziptodest.repository.iseries;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Qualifier;

import com.fedex.ziptodest.model.StateProvince;
import com.fedex.ziptodest.repository.iseries.redis.StateProvinceRedisRepository;

public class StateProvinceRepositoryTest {

	@Mock
	@Qualifier("stateProvinceRedisRepository")
	StateProvinceRedisRepository stateProvinceRedisRepository;

	@InjectMocks
	private StateProvinceRepository stateProvinceRepository;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testFindByCntryc() {
		assertNotNull(stateProvinceRepository.findByCntryc("Pa"));
	}

	@Test
	public void testFindById() {
		assertNotNull(stateProvinceRepository.findById("10"));
	}

	@Test
	public void testExistsById() {
		assertNotNull(stateProvinceRepository.existsById("10"));
	}

	@Test
	public void testFindAll() {
		assertNotNull(stateProvinceRepository.findAll());
	}

	@Test
	public void testCount() {
		assertNotNull(stateProvinceRepository.count());
	}

	@Test
	public void testDeleteById() {
		stateProvinceRepository.deleteById("10");
	}

	@Test
	public void testDelete() {
		StateProvince stateProvince = new StateProvince();
		stateProvince.setCntryc("10");
		stateProvinceRepository.delete(stateProvince);
	}

	@Test
	public void testDeleteAll() {
		stateProvinceRepository.deleteAll();
	}

}
*/