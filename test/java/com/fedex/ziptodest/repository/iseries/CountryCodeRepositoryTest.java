/*package com.fedex.ziptodest.repository.iseries;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Qualifier;

import com.fedex.ziptodest.model.CountryCode;
import com.fedex.ziptodest.repository.iseries.redis.CountryCodeRedisRepository;

public class CountryCodeRepositoryTest {

	@Mock
	@Qualifier("countryCodeRedisRepository")
	CountryCodeRedisRepository countryCodeRedisRepository;

	@InjectMocks
	CountryCodeRepository countryCodeRepository;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	private List<CountryCode> getCountryCodeList() {
		CountryCode countryCode = new CountryCode();
		countryCode.setCyclcu(10);
		countryCode.setCycode("1234");
		List<CountryCode> listCountryCode = new ArrayList<CountryCode>();
		listCountryCode.add(countryCode);
		return listCountryCode;
	}

	@Test
	public void testFindAll() {
		when(countryCodeRedisRepository.findAll()).thenReturn(getCountryCodeList());
		assertNotNull(countryCodeRepository.findAll());
	}

	// findById

	
	 * @Test public void testFindById(){ CountryCode countryCode = new
	 * CountryCode(); countryCode.setCyclcu(10); countryCode.setCycode("1234");
	 * List<CountryCode> listCountryCode = new ArrayList();
	 * listCountryCode.add(countryCode);
	 * 
	 * // countryCodeRedisRepository.findById(id)
	 * when(countryCodeRedisRepository.findById("10")).thenReturn(
	 * listCountryCode); }
	 

	@Test
	public void testExistsById() {
		when(countryCodeRedisRepository.existsById("10")).thenReturn(true);
		assertNotNull(countryCodeRepository.existsById(Mockito.anyString()));
	}

	@Test
	public void testFindAllById() {
		CountryCode countryCode = new CountryCode();
		countryCode.setCyclcu(10);
		countryCode.setCycode("1234");
		
		Iterable<String> ite = Collections.singletonList("10");
		Iterable<CountryCode> iterableCountryCode = Collections.singletonList(countryCode);
		when(countryCodeRedisRepository.findAllById(ite)).thenReturn(iterableCountryCode);
		assertNotNull(countryCodeRepository.findAllById(ite));
	}
	
	@Test
	public void testCount(){
		when(countryCodeRedisRepository.count()).thenReturn(2L);
		assertNotNull(countryCodeRepository.findAll());
	}

}
*/