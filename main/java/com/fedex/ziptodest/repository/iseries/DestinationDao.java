package com.fedex.ziptodest.repository.iseries;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Component;

import com.fedex.ziptodest.model.Destination;
import com.fedex.ziptodest.utils.iseries.AppConstants;
import com.fedex.ziptodest.utils.iseries.DestinationMapper;
import com.fedex.ziptodest.utils.iseries.Fields;
import com.fedex.ziptodest.utils.iseries.ValidationUtil;

@Component
public class DestinationDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(DestinationDao.class);
	
	@Resource(name = "strRedisTemplate")
	private HashOperations<String, String, String> strHashOperations;

	@Resource(name = "strRedisTemplate")
	private SetOperations<String, String> strSetOperations;

	@Autowired
	@Qualifier("strRedisTemplate")
	RedisTemplate<String, String> strRedisTemplate;

	@Autowired
	private DestinationMapper destinationMapper;

	@Value(value = "${keyspace}")
	private String keySpace;

	/**
	 * 
	 * @param destination
	 * @return
	 */
	public Destination save(Destination destination) {

		LOGGER.info("Destination  : {}", destination.toString());
		
		String hashKey = ValidationUtil.getIseriesDestinationKey(keySpace,
				String.valueOf(destination.getTerminalNumber()));

		LOGGER.info("Destination Hash Key : {}", hashKey);
		
		strHashOperations.putAll(hashKey, destinationMapper.toMap(destination));

		strSetOperations.add(ValidationUtil.getIseriesKey(keySpace, AppConstants.ISERIES_DESTINATION_SET_KEY), hashKey);

		return destination;
	}

	/**
	 * 
	 * @param destinations
	 */
	@SuppressWarnings("unchecked")
	public void saveAll(List<Destination> destinations) {
		try {
			strRedisTemplate.executePipelined(new SessionCallback<List<Object>>() {

				@Override
				public List<Object> execute(RedisOperations redisOperations) throws DataAccessException {

					String hashKey = "";

					for (Destination destination : destinations) {

						hashKey = ValidationUtil.getIseriesDestinationKey(keySpace,
								String.valueOf(destination.getTerminalNumber()));

						redisOperations.opsForHash().putAll(hashKey, destinationMapper.toMap(destination));

						redisOperations.opsForSet().add(
								ValidationUtil.getIseriesKey(keySpace, AppConstants.ISERIES_DESTINATION_SET_KEY),
								hashKey);
					}
					return null;
				}

			});
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 
	 * @param terminalNumber
	 * @return
	 */
	public boolean findByTerminalNumberAndTerminalStatus(String terminalNumber) {
		boolean distinationExist = false;
		String hashKey = ValidationUtil.getIseriesDestinationKey(keySpace,
				String.valueOf(Integer.parseInt(terminalNumber)));
		Map<String, String> destinations = strHashOperations.entries(hashKey);
		if (!destinations.isEmpty()) {
			distinationExist = AppConstants.TERMINAL_STATUS_ADDED
					.equals(destinations.get(Fields.TERMINAL_STATUS).trim());
		}
		return distinationExist;
	}
}
