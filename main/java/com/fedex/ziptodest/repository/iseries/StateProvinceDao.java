package com.fedex.ziptodest.repository.iseries;

import java.util.ArrayList;
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
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Component;

import com.fedex.ziptodest.model.StateProvince;
import com.fedex.ziptodest.utils.iseries.AppConstants;
import com.fedex.ziptodest.utils.iseries.StateProvinceMapper;
import com.fedex.ziptodest.utils.iseries.ValidationUtil;

@Component
public class StateProvinceDao {

	public static final Logger LOGGER = LoggerFactory.getLogger(StateProvinceDao.class);

	@Autowired
	@Qualifier("strRedisTemplate")
	RedisTemplate<String, String> strRedisTemplate;

	@Resource(name = "strRedisTemplate")
	HashOperations<String, String, String> strHashOperations;

	@Resource(name = "strRedisTemplate")
	SetOperations<String, String> strSetOperations;

	@Autowired
	StateProvinceMapper stateProvinceMapper;

	@Autowired
	HashSetOperations hashSetOperations;
	
	@Value(value = "${keyspace}")
	private String keyspace;
	
	/**
	 * 
	 * @param stateProvince
	 * @return
	 */
	public StateProvince save(StateProvince stateProvince) {
		StateProvince result = null;
		try {
			String hashKey = ValidationUtil.getIseriesStateProvinceKey(keyspace, stateProvince.getStaPro());

			strHashOperations.putAll(hashKey, stateProvinceMapper.toMap(stateProvince));

			strSetOperations.add(
					ValidationUtil.getIseriesKey(keyspace, AppConstants.ISERIES_STATE_PROVINCE_SET_KEY), hashKey);

			if (stateProvince.getCntryc() != null) {
				 strSetOperations.add(ValidationUtil.getIseriesStateProCountryKey(keyspace, stateProvince.getCntryc()),
						 hashKey);
			}

			result = stateProvince;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	/**
	 * 
	 * @param stateProvinces
	 */
	@SuppressWarnings("unchecked")
	public void saveAll(List<StateProvince> stateProvinces) {

		try {
			strRedisTemplate.executePipelined(new SessionCallback<List<Object>>() {

				@SuppressWarnings("rawtypes")
				@Override
				public List<Object> execute(RedisOperations redisOperations) throws DataAccessException {
					String hashKey = "";
					
					for (StateProvince stateProvince : stateProvinces) {
						hashKey = ValidationUtil.getIseriesStateProvinceKey(keyspace, stateProvince.getStaPro());

						redisOperations.opsForHash().putAll(hashKey, stateProvinceMapper.toMap(stateProvince));

						redisOperations.opsForSet().add(
								ValidationUtil.getIseriesKey(keyspace, AppConstants.ISERIES_STATE_PROVINCE_SET_KEY),
								hashKey);

						if (stateProvince.getCntryc() != null) {
							 strSetOperations.add(ValidationUtil.getIseriesStateProCountryKey(keyspace, stateProvince.getCntryc()),
									 hashKey);
						}
					}
					return null;
				}

			});
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<StateProvince> selectByCountryCode(String... countryCodes) {
		
		List<StateProvince> output = new ArrayList<>();
		
		LOGGER.info("No of Country Codes : {}", countryCodes.length);
		
		for (String countryCode : countryCodes) {

			LOGGER.info("countryCode : {}", countryCode);

			Set<String> keys = hashSetOperations.findPrimaryKeys(ValidationUtil.getIseriesStateProCountryKey(keyspace, countryCode));
			
			List<Object> states = hashSetOperations.getHashesFromRedis(keys);
			
			if (states != null && !states.isEmpty()) {
				for (Object obj : states) {
					output.add(stateProvinceMapper.toStateProvince((Map<String, String>) obj));
				}
			}
		}
		return output;
	}
}
