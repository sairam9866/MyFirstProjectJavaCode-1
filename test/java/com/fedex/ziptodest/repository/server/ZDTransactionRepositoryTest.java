//package com.fedex.ziptodest.repository.server;
//
//import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertNull;
//import static org.mockito.Mockito.when;
//
//import java.time.ZoneOffset;
//import java.time.ZonedDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.annotation.Resource;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.ZSetOperations;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.util.ReflectionTestUtils;
//
//import com.fedex.ziptodest.model.ZipToDest;
//import com.fedex.ziptodest.utils.server.ZipToDestConstants;
//import com.fedex.ziptodest.utils.server.ZipToDestUtil;
//
////@RunWith(SpringRunner.class)
//public class ZDTransactionRepositoryTest {
//
//	@InjectMocks
//	ZDTransactionRepository zDTransactionRepository;
//
//	@Mock
//	@Qualifier("baseTransactionalRepository")
//	ZDTransactionBaseRepository zdTransactionBaseRepository;
//
//	@Mock
//	ZipToDestUtil zipToDestUtil;
//
//	@InjectMocks
//	private RedisTemplate<String, String> redisStringTemplate;
//
//	@Mock
//	private RedisTemplate<String, Long> redisLongTemplate;
//
//	@Mock
//	@Resource(name = "zipToDestRedisTemplate")
//	private ZSetOperations<String, ZipToDest> zSetOperations;
//
//	@Mock
//	@Resource(name = "zipToDestRedisTemplate")
//	private ZSetOperations<String, ZipToDest> zSetProcessedOperations;
//
//	@Mock
//	@Resource(name = "zipToDestRedisTemplate")
//	private ZSetOperations<String, ZipToDest> zSetCancelledOperations;
//
//	@Mock
//	@Resource(name = "zipToDestRedisTemplate")
//	private ZSetOperations<String, ZipToDest> zSetCreationOperations;
//
//	@Mock
//	@Resource(name = "zipToDestRedisTemplate")
//	private ZSetOperations<String, String> zipSetOperations;
//
//	@Mock
//	private ZSetOperations<String, Long> zipSetProcessedDateOperations;
//	@Mock
//	private ZSetOperations<String, Long> zipSetCancelledDateOperations;
//	@Mock
//	private ZSetOperations<String, Long> zipSetCreationDateOperations;
//
//	@Before
//	public void init() {
//
//		MockitoAnnotations.initMocks(this);
//
//		zipSetOperations = redisStringTemplate.opsForZSet();
//		zipSetProcessedDateOperations = redisLongTemplate.opsForZSet();
//		zipSetCancelledDateOperations = redisLongTemplate.opsForZSet();
//		zipSetCreationDateOperations = redisLongTemplate.opsForZSet();
//		ReflectionTestUtils.setField(zDTransactionRepository, "keyspace", "local");
//	}
//
//	private List<ZipToDest> getZipToDestList() {
//		ZipToDest zipToDest = new ZipToDest();
//		zipToDest.setCancelledFlag(ZipToDestConstants.FLAG_NO);
//		zipToDest.setEffectiveDateAt(ZonedDateTime.now(ZoneOffset.UTC).toEpochSecond());
//		List<ZipToDest> list = new ArrayList<>();
//		list.add(zipToDest);
//		return list;
//	}
//
//	private ZipToDest zipToDestData() {
//		ZipToDest zipToDest = new ZipToDest();
//		zipToDest.setCancelledFlag(ZipToDestConstants.FLAG_NO);
//		zipToDest.setEffectiveDateAt(ZonedDateTime.now(ZoneOffset.UTC).toEpochSecond());
//		zipToDest.setCancelledFlag(ZipToDestConstants.FLAG_NO);
//		zipToDest.setCancelledTimestamp(ZonedDateTime.now(ZoneOffset.UTC).toEpochSecond());
//		zipToDest.setCancelledUser("TestUser");
//		zipToDest.setCountryCode(124);
//		zipToDest.setNetwork("FXG");
//		zipToDest.setProcessed(ZipToDestConstants.FLAG_NO);
//		zipToDest.setState("PA");
//		zipToDest.setTimeZone("UTC");
//		zipToDest.setProcessedDateTime(ZonedDateTime.now(ZoneOffset.UTC).toEpochSecond());
//		zipToDest.setZipCode("12345");
//		zipToDest.setCreatedDateAt(ZonedDateTime.now(ZoneOffset.UTC).toEpochSecond());
//		zipToDest.buildKey();
//		return zipToDest;
//	}
//
//	@Test
//	public void testSave() {
//		assertNull(zDTransactionRepository.save(zipToDestData()));
//	}
//
//	@Test
//	public void testFindByNetworkAndZipCode() {
//		when(zdTransactionBaseRepository.findByNetworkAndZipCode(Mockito.anyString(), Mockito.anyString()))
//				.thenReturn(getZipToDestList());
//		assertNotNull(zDTransactionRepository.findByNetworkAndZipCode(Mockito.anyString(), Mockito.anyString()));
//	}
//
//	@Test
//	public void testFindByZipCodeAndCurrentAndProcessedAndCancelledFlag() {
//		when(zdTransactionBaseRepository.findByZipCodeAndCurrentAndProcessedAndCancelledFlag(Mockito.anyString(),
//				Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).thenReturn(getZipToDestList());
//		assertNotNull(zDTransactionRepository.findByZipCodeAndCurrentAndProcessedAndCancelledFlag(Mockito.anyString(),
//				Mockito.anyString(), Mockito.anyString(), Mockito.anyString()));
//	}
//
//	@Test
//	public void testFindByNetworkAndZipCodeAndCurrentAndProcessedAndCancelledFlag() {
//		when(zdTransactionBaseRepository.findByNetworkAndZipCodeAndCurrentAndProcessedAndCancelledFlag(
//				Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(),
//				Mockito.anyString())).thenReturn(getZipToDestList());
//		assertNotNull(zDTransactionRepository.findByNetworkAndZipCodeAndCurrentAndProcessedAndCancelledFlag(Mockito.anyString(),
//				Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString()));
//	}
//
//	// @Test
//	// public void testScanByNetworkAndZipCodeRange() {
//	// Set<String> zipCode = new HashSet<>();
//	// zipCode.add("12345");
//	// zipCode.add("23234");
//	// ZipToDest zipToDest = new ZipToDest();
//	// zipToDest.setCancelledFlag(ZipToDestConstants.FLAG_NO);
//	// zipToDest.setEffectiveDateAt(ZonedDateTime.now(ZoneOffset.UTC).toEpochSecond());
//	// zipToDest.setZipCode("12345");
//	//
//	// Range r = new Range();
//	// zSetOperations.add("LOCAL" + " - " +
//	// ZipToDestConstants.APP_TRANSACTION_TMSTMP_KEY, zipToDest,
//	// zipToDest.getEffectiveDateAt());
//	//// zSetOperations.add(ZipToDestConstants.APP_TRANSACTION_TMSTMP_KEY,
//	// zipToDest, zipToDest.getEffectiveDateAt());
//	// zipSetOperations.add("LOCAL" + " - " +
//	// ZipToDestConstants.APP_TRANSACTION_ZIP_KEY, zipToDest.getZipCode(),
//	// ZipToDestConstants.ZIP_CODE_DEFAULT_SCORE);
//	//
//	// when(zipSetOperations.rangeByLex("LOCAL" + " - "
//	// +ZipToDestConstants.APP_TRANSACTION_ZIP_KEY,
//	// Range.range().gte(Mockito.anyInt()).lte(Mockito.anyInt())))
//	// .thenReturn(zipCode);
//	//
//	// zDTransactionRepository.scanByNetworkAndZipCodeRange(Mockito.anyString(),
//	// Mockito.anyString(),
//	// Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
//	// }
//
//	@Test
//	public void testfindByNetworkAndZipCodeAndCurrentAndProcessed() {
//		when(zdTransactionBaseRepository.findByNetworkAndZipCodeAndCurrentAndProcessed(Mockito.anyString(),
//				Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).thenReturn(getZipToDestList());
//		assertNotNull(zDTransactionRepository.findByNetworkAndZipCodeAndCurrentAndProcessed(Mockito.anyString(), Mockito.anyString(),
//				Mockito.anyString(), Mockito.anyString()));
//	}
//
//	@Test
//	public void testFindByNetworkAndZipCodeAndCurrentAndProcessedAndCancelledFlagAndTransactionType() {
//		when(zdTransactionBaseRepository
//				.findByNetworkAndZipCodeAndCurrentAndProcessedAndCancelledFlagAndTransactionType(Mockito.anyString(),
//						Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(),
//						Mockito.anyString())).thenReturn(getZipToDestList());
//		assertNotNull(zDTransactionRepository.findByNetworkAndZipCodeAndCurrentAndProcessedAndCancelledFlagAndTransactionType(
//				Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(),
//				Mockito.anyString()));
//
//	}
//
//	@Test
//	public void testFindByNetworkAndDestinationTerminalAndZipCodeAndCurrentAndProcessedAndCancelledFlagAndTransactionType() {
//		when(zdTransactionBaseRepository
//				.findByNetworkAndDestinationTerminalAndZipCodeAndCurrentAndProcessedAndCancelledFlagAndTransactionType(
//						Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(),
//						Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).thenReturn(getZipToDestList());
//		assertNotNull(zDTransactionRepository
//				.findByNetworkAndDestinationTerminalAndZipCodeAndCurrentAndProcessedAndCancelledFlagAndTransactionType(
//						Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(),
//						Mockito.anyString(), Mockito.anyString(), Mockito.anyString()));
//	}
//
//	@Test
//	public void testFindByProcessedAndCancelledFlagAndUuid() {
//		when(zdTransactionBaseRepository.findByProcessedAndCancelledFlagAndUuid(Mockito.anyString(),
//				Mockito.anyString(), Mockito.anyString())).thenReturn(getZipToDestList());
//		assertNotNull(zDTransactionRepository.findByProcessedAndCancelledFlagAndUuid(Mockito.anyString(), Mockito.anyString(),
//				Mockito.anyString()));
//	}
//
//	@Test
//	public void testFindByProcessedAndCancelledFlag() {
//		when(zdTransactionBaseRepository.findByProcessedAndCancelledFlag(Mockito.anyString(), Mockito.anyString()))
//				.thenReturn(getZipToDestList());
//		assertNotNull(zDTransactionRepository.findByProcessedAndCancelledFlag(Mockito.anyString(), Mockito.anyString()));
//	}
//
//	@Test
//	public void testFindByCurrentAndProcessedAndCancelledFlag() {
//		when(zdTransactionBaseRepository.findByCurrentAndProcessedAndCancelledFlag(Mockito.anyString(),
//				Mockito.anyString(), Mockito.anyString())).thenReturn(getZipToDestList());
//		assertNotNull(zDTransactionRepository.findByCurrentAndProcessedAndCancelledFlag(Mockito.anyString(), Mockito.anyString(),
//				Mockito.anyString()));
//	}
//}
