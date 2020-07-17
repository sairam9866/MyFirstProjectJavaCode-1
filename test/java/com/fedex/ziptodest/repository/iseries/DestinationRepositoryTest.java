/*package com.fedex.ziptodest.repository.iseries;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Qualifier;

import com.fedex.ziptodest.model.Destination;
import com.fedex.ziptodest.repository.iseries.redis.DestinationRedisRepository;

public class DestinationRepositoryTest {

	@Mock
	@Qualifier("destinationRedisRepository")
	DestinationRedisRepository destinationRedisRepository;

	@InjectMocks
	DestinationRepository destinationRepository;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	private List<Destination> getDestinationList() {
		Destination destination = new Destination();
		List<Destination> destinationList = new ArrayList<Destination>();
		destination.setTerminalAbbreviation("01");
		destinationList.add(destination);
		return destinationList;
	}

	@Test
	public void testFindByTerminalNumberAndTerminalStatus() {
		when(destinationRedisRepository.findByTerminalNumberAndTerminalStatus(1, 'c')).thenReturn(getDestinationList());
		assertNotNull(destinationRepository.findByTerminalNumberAndTerminalStatus(1, 'c'));
	}

	@Test
	public void testSave() {
		Destination destination = new Destination();
		destination.setTerminalAbbreviation("PA");
		when(destinationRedisRepository.save(destination)).thenReturn(destination);
		assertEquals("PA", destinationRepository.save(destination).getTerminalAbbreviation());
	}
	
	@Test
	public void testFindById(){
		Destination destination = new Destination();
		destination.setTerminalAbbreviation("PA");
		List<Destination> destinationList = new ArrayList<>();
		destinationList.add(destination);
		
Optional<Destination> 	optional = Collections.synchronizedList(destinationList);	

		when(destinationRedisRepository.findById(1)).thenReturn(destination);
		
	}
	
	
	@Test
	public void testExistsById(){
		when(destinationRedisRepository.existsById(10)).thenReturn(true);
		assertNotNull(destinationRepository.existsById(10));
	}
	
	
	@Test
	public void testFindAll(){
		Destination destination = new Destination();
		destination.setTerminalAbbreviation("PA");
		
		Iterable<Destination> itr1 = Collections.singletonList(destination);
		when(destinationRedisRepository.findAll()).thenReturn(itr1);
		assertNotNull(destinationRepository.findAll());
	}
	
	@Test
	public void testCount(){
		when(destinationRedisRepository.count()).thenReturn(1L);
		assertNotNull(destinationRepository.count());
	}
	

//	@Test
//	public void testDeleteById(){
//		assertNotNull(destinationRepository.deleteById(10));
//	}
	
//	@Test
//	public void testDelete(){
//		Destination destination = new Destination();
//		destination.setTerminalAbbreviation("PA");
//		destinationRepository.delete(destination);
//		verify(destinationRepository ,times(1)).delete(destination);
//	}
	
//	destinationRedisRepository.deleteAll();
//	@Test
//	public void testDeleteAll(){
//		destinationRepository.deleteAll();
//	}
	
}
*/