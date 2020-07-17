package com.fedex.ziptodest.repository.server;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisZSetCommands.Range;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import com.fedex.ziptodest.model.ZipToDest;
import com.fedex.ziptodest.utils.server.ZDConstants;
import com.fedex.ziptodest.utils.server.ZDMapper;
import com.fedex.ziptodest.utils.server.ZDUtil;

@Component("zdTransactionalRepository")
public class ZDTransactionRepository {

	public static final Logger LOGGER = LoggerFactory.getLogger(ZDTransactionRepository.class);

	@Value(value = "${keyspace}")
	private String keyspace;

	@Autowired
	ZDUtil zipToDestUtil;
	
	@Autowired
	ZDMapper mapper;

	@Autowired
	@Qualifier("strRedisTemplate")
	public RedisTemplate<String, String> strRedisTemplate;

	@Resource(name = "strRedisTemplate")
	SetOperations<String, String> zdSetOperations;

	@Resource(name = "strRedisTemplate")
	private ZSetOperations<String, String> zipSetOperations;

	public ZipToDest saveToRedis(ZipToDest zipToDest) {
		String hashKey = zipToDestUtil.zipToDestHashKey(zipToDest);

		try {

			strRedisTemplate.executePipelined(new SessionCallback<List<String>>() {

				@SuppressWarnings({ "unchecked", "rawtypes" })
				@Override
				public List<String> execute(RedisOperations operations) throws DataAccessException {
					// operations.multi();

					operations.opsForHash().putAll(hashKey, mapper.toMap(zipToDest));

					operations.opsForSet().add(getUIKey(zipToDest), hashKey);
					operations.opsForSet().add(getNetworkZipcodeKey(zipToDest), hashKey);
					operations.opsForSet().add(getNetworkKey(zipToDest), hashKey);
					
					operations.opsForZSet().add(
							zipToDestUtil.getRedisKey(keyspace, ZDConstants.APP_TRANSACTION_ZIP_KEY),
							zipToDest.getZipCode().toUpperCase(), ZDConstants.ZIP_CODE_DEFAULT_SCORE);

					if (zipToDest.getCancelledTimestamp() != null) {
						cancelOperations(zipToDest, hashKey, operations);
						
					}

					operations.opsForZSet()
							.add(zipToDestUtil.getRedisKey(keyspace,
									ZDConstants.APP_TRANSACTION_SAVE_CREATION_TMSTAMP_KEY), hashKey,
									zipToDest.getCreatedDateAt());
					
					

					return null;
				}

				private void cancelOperations(ZipToDest zipToDest, String hashKey, RedisOperations operations) {
					operations.opsForZSet().add(
							zipToDestUtil.getRedisKey(keyspace, ZDConstants.APP_TRANSACTION_SAVE_CANCELLED_TMSTAMP_KEY),
							hashKey, zipToDest.getCancelledTimestamp());

					Long removed = operations.opsForSet().remove(getFutureSet(zipToDest), hashKey);
					Long removedNetwork = operations.opsForSet().remove(getFutureNetWorkSet(zipToDest),hashKey);
					Long removedNetworkZipCode = operations.opsForSet().remove(getFutureNetWorkZipcodeSet(zipToDest),hashKey);
					
					LOGGER.info("successfully removed Ids #" + removed.toString());
					LOGGER.info("successfully removed Ids #" + removedNetwork.toString());
					LOGGER.info("successfully removed Ids #" + removedNetworkZipCode.toString());
				}

			});

		} catch (Exception e) {
			LOGGER.error("Error while saving records" + e);
			// return false;
		}

		return null;
	}
	

	@SuppressWarnings("unchecked")
	public List<ZipToDest> getLatestTransactions(long date) {

		Set<ZipToDest> output = new HashSet<ZipToDest>();

		Set<ZipToDest> processedRecords = new HashSet<ZipToDest>();
		Set<ZipToDest> cancelledRecords = new HashSet<ZipToDest>();
		Set<ZipToDest> createdRecords = new HashSet<ZipToDest>();

		try {
			Set<String> processedDateIds = zipSetOperations.rangeByScore(
					zipToDestUtil.getRedisKey(keyspace, ZDConstants.APP_TRANSACTION_SAVE_PROCESS_TMSTAMP_KEY),
					date, Long.MAX_VALUE);
			List<Object> processedHashes = getHashesFromRedis(processedDateIds);

			// call mapper to convert result to List<ZipToDest>
			processedHashes.forEach(m -> {
				ZipToDest ziptodest = mapper.toZipToDest((Map<String, String>) m);
				processedRecords.add(ziptodest);

			});
		} catch (Exception ex) {
			LOGGER.error("Error while retrieving processed records" + ex);
			ex.printStackTrace();
		}

		try {
			Set<String> cancelledDateIds = zipSetOperations.rangeByScore(
					zipToDestUtil.getRedisKey(keyspace, ZDConstants.APP_TRANSACTION_SAVE_CANCELLED_TMSTAMP_KEY),
					date, Long.MAX_VALUE);
			List<Object> cancelledHashes = getHashesFromRedis(cancelledDateIds);

			// call mapper to convert result to List<ZipToDest>
			cancelledHashes.forEach(m -> {
				ZipToDest ziptodest = mapper.toZipToDest((Map<String, String>) m);
				cancelledRecords.add(ziptodest);

			});
		} catch (Exception ex) {
			LOGGER.error("Error while retrieving cancelled records" + ex);
			ex.printStackTrace();
		}

		try {
			Set<String> creationDateIds = zipSetOperations.rangeByScore(
					zipToDestUtil.getRedisKey(keyspace, ZDConstants.APP_TRANSACTION_SAVE_CREATION_TMSTAMP_KEY),
					date, Long.MAX_VALUE);
			List<Object> creationHashes = getHashesFromRedis(creationDateIds);

			// call mapper to convert result to List<ZipToDest>
			creationHashes.forEach(m -> {
				ZipToDest ziptodest = mapper.toZipToDest((Map<String, String>) m);
				createdRecords.add(ziptodest);

			});
		} catch (Exception ex) {
			LOGGER.error("Error while retrieving all created records" + ex);
			ex.printStackTrace();
		}

		LOGGER.info("Size of the cancelledRecords : {}", cancelledRecords.size());
		LOGGER.info("Size of the createdRecords : {}", createdRecords.size());
		LOGGER.info("Size of the processedRecords : {}", processedRecords.size());
		
		output.addAll(cancelledRecords);
		output.addAll(processedRecords);
		output.addAll(createdRecords);
		
		LOGGER.info("Size of the list : {}", output.size());
		
		return new ArrayList<ZipToDest>(output);
	}

	private String getUIKey(ZipToDest zipToDest) {
		if (zipToDest.getCurrent().equalsIgnoreCase(ZDConstants.FLAG_YES) && zipToDest.getProcessed().equalsIgnoreCase(ZDConstants.FLAG_YES)
				&& zipToDest.getCancelledFlag().equalsIgnoreCase(ZDConstants.FLAG_NO)) {

			return (keyspace.toUpperCase() + ":" + ZDConstants.CURRENT_REC);
		}

		if (zipToDest.getCurrent().equalsIgnoreCase(ZDConstants.FLAG_NO) && zipToDest.getProcessed().equalsIgnoreCase(ZDConstants.FLAG_NO)
				&& zipToDest.getCancelledFlag().equalsIgnoreCase(ZDConstants.FLAG_NO)) {

			return (keyspace.toUpperCase() + ":" + ZDConstants.FUTURE_REC);
		}

		if (zipToDest.getCurrent().equalsIgnoreCase(ZDConstants.FLAG_NO) && zipToDest.getProcessed().equalsIgnoreCase(ZDConstants.FLAG_YES)
				&& zipToDest.getCancelledFlag().equalsIgnoreCase(ZDConstants.FLAG_NO)) {

			return (keyspace.toUpperCase() + ":" + ZDConstants.HISTORICAL_REC);
		}

		if (zipToDest.getCurrent().equalsIgnoreCase(ZDConstants.FLAG_NO) && zipToDest.getProcessed().equalsIgnoreCase(ZDConstants.FLAG_NO)
				&& zipToDest.getCancelledFlag().equalsIgnoreCase(ZDConstants.FLAG_YES)) {

			return (keyspace.toUpperCase() + ":" + ZDConstants.HISTORICAL_REC);
		}

		return (keyspace.toUpperCase() + ":" +  ZDConstants.NA_RECORDS);
	}
	
	
	private String getFutureSet(ZipToDest zipToDest){
		return (keyspace.toUpperCase() + ":" + ZDConstants.FUTURE_REC);
	}
	
	private String getFutureNetWorkSet(ZipToDest zipToDest){
		return (keyspace.toUpperCase() + ":" + ZDConstants.FUTURE_REC+":" + zipToDest.getNetwork().toUpperCase());
	}
	
	private String getFutureNetWorkZipcodeSet(ZipToDest zipToDest){
		return (keyspace.toUpperCase() + ":" + ZDConstants.FUTURE_REC+":" + zipToDest.getNetwork().toUpperCase()  + ":" + zipToDest.getZipCode().toUpperCase());
	}
	
	private String getNetworkZipcodeKey(ZipToDest zipToDest) {
		return (getUIKey(zipToDest) + ":" + zipToDest.getNetwork().toUpperCase() + ":" + zipToDest.getZipCode().toUpperCase());
	}

	private String getNetworkKey(ZipToDest zipToDest) {
		return (getUIKey(zipToDest) + ":" + zipToDest.getNetwork().toUpperCase());
	}
	
	public String getJSONCurrentRecordsKey() {
		return (keyspace + ":" + "CURRENT_RECORDS_JSON" ).toUpperCase();
	}

	@SuppressWarnings("unchecked")
	public List<ZipToDest> getZipToDestfromRedis(String network, String zipCode, String recordType) {

		Set<String> ids = selectSubKeyByNetwork(networkzipCodeKey(network, zipCode, recordType));
		List<ZipToDest> output = new ArrayList<>();
		List<Object> hashes = getHashesFromRedis(ids);

		// call mapper to convert result to List<ZipToDest>
		hashes.forEach(m -> {
			ZipToDest ziptodest = mapper.toZipToDest((Map<String, String>) m);
			output.add(ziptodest);
		});

		return output;
	}

	public List<ZipToDest> getCurrentRecords(String recordType) {
		Set<String> ids = selectSubKeyByNetwork(currentKey(recordType));
		List<ZipToDest> output = new ArrayList<>();
		List<Map<String, String>> hashes = getHashesFromRedisZipToDest(ids);

		// call mapper to convert result to List<ZipToDest>
		hashes.forEach(m -> {
			ZipToDest ziptodest = mapper.toZipToDest(m);
			output.add(ziptodest);
		});

		return output;
	}

	@SuppressWarnings("unchecked")
	public List<ZipToDest> getFutureRecords(String recordType) {
		Set<String> ids = selectSubKeyByNetwork(currentKey(recordType));
		List<ZipToDest> output = new ArrayList<>();
		List<Object> hashes = getHashesFromRedis(ids);

		// call mapper to convert result to List<ZipToDest>
		hashes.forEach(m -> {
			ZipToDest ziptodest = mapper.toZipToDest((Map<String, String>) m);
			output.add(ziptodest);
		});

		return output;
	}

	@SuppressWarnings("unchecked")
	public List<String> getCurrentRecords_String(String recordType) {
		List<String> output = new ArrayList<>();
		String hashKey = getJSONCurrentRecordsKey();
		output = getJSONFromRedis(hashKey);
		return output;
	}

	@SuppressWarnings("unchecked")
	public List<String> getFutureRecords_String(String recordType) {
		Set<String> ids = selectSubKeyByNetwork(currentKey(recordType));
		List<String> output = new ArrayList<>();
		List<Object> hashes = getHashesFromRedis(ids);

		// call mapper to convert result to List<ZipToDest>
		hashes.forEach(m -> {
			String ziptodest = mapper.toJson((Map<String, String>) m);
			output.add(ziptodest);

		});

		return output;
	}

	@SuppressWarnings("unchecked")
	public List<String> getHistoricalRecords_String(String recordType) {
		Set<String> ids = selectSubKeyByNetwork(currentKey(recordType));
		List<String> output = new ArrayList<>();
		List<Object> hashes = getHashesFromRedis(ids);

		// call mapper to convert result to List<ZipToDest>
		hashes.forEach(m -> {
			String ziptodest = mapper.toJson((Map<String, String>) m);
			output.add(ziptodest);

		});

		return output;
	}

	private String currentKey(String recordType) {
		return (keyspace.toUpperCase() + ":" + recordType);
	}

	private String networkzipCodeKey(String network, String zipCode, String recordType) {
		return (keyspace.toUpperCase() + ":" + recordType + ":" + network.toUpperCase() + ":" + zipCode.toUpperCase());
	}

	private String network(String network, String recordType) {
		return (keyspace.toUpperCase() + ":" + recordType + ":" + network.toUpperCase());
	}

	private Set<String> selectSubKeyByNetwork(String hashKey) {
		Set<String> ids = zdSetOperations.members(hashKey.toUpperCase());
		return ids;
	}

	public List<Object> getHashesFromRedis(Set<String> ids) {
		List<Object> result = strRedisTemplate.executePipelined(new SessionCallback<List<Map<String, String>>>() {

			@SuppressWarnings({ "unused", "rawtypes", "unchecked" })
			@Override
			public List<Map<String, String>> execute(RedisOperations operations) throws DataAccessException {
				// operations.multi();

				for (String id : ids) {
					Map<String, String> map = operations.opsForHash().entries(id);

				}

				return null;
			}

		});
		return result;
	}

	private List<Map<String, String>> getHashesFromRedisZipToDest(Set<String> ids) {
		List<Map<String, String>> result = strRedisTemplate.execute(new SessionCallback<List<Map<String, String>>>() {

			@SuppressWarnings({ "unchecked", "rawtypes" })
			@Override
			public List<Map<String, String>> execute(RedisOperations operations) throws DataAccessException {
				// operations.multi();

				for (String id : ids) {
					@SuppressWarnings("unused")
					Map<String, String> map = operations.opsForHash().entries(id);

				}

				// return operations.exec();
				return null;
			}

		});
		return result;
	}
	
	
	private List<String> getJSONFromRedis(String hashKey) {
		List<String> result = strRedisTemplate.execute(new SessionCallback<List<String>>() {

			@SuppressWarnings({ "unchecked", "rawtypes" })
			@Override
			public List<String> execute(RedisOperations operations) throws DataAccessException {
				
				List<String> jsons = operations.opsForHash().values(hashKey);

				return jsons;
			}

		});
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<ZipToDest> getZipCodesRanges(String network, String zipFrom, String zipTo, String recordType) {
		List<ZipToDest> output = new ArrayList<>();

		Set<String> zipCodes = zipSetOperations.rangeByLex(
				zipToDestUtil.getRedisKey(keyspace, ZDConstants.APP_TRANSACTION_ZIP_KEY),
				Range.range().gte(zipFrom).lte(zipTo));

		Set<String> ids = new HashSet<>();

		for (String zipCode : zipCodes) {
			ids.addAll(selectSubKeyByNetwork(networkzipCodeKey(network, zipCode, recordType)));
		}

		List<Object> hashes = getHashesFromRedis(ids);

		// call mapper to convert result to List<ZipToDest>
		hashes.forEach(m -> {
			ZipToDest ziptodest = mapper.toZipToDest((Map<String, String>) m);
			output.add(ziptodest);

		});

		return output;
	}

	@SuppressWarnings("unchecked")
	public List<ZipToDest> getZipToDestNetwork(String network, String recordType) {

		Set<String> ids = selectSubKeyByNetwork(network(network, recordType));
		List<ZipToDest> output = new ArrayList<>();
		List<Object> hashes = getHashesFromRedis(ids);

		// call mapper to convert result to List<ZipToDest>
		hashes.forEach(m -> {
			ZipToDest ziptodest = mapper.toZipToDest((Map<String, String>) m);
			output.add(ziptodest);
		});

		return output;
	}

}
