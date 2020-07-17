package com.fedex.ziptodest.repository.iseries;

import java.util.ArrayList;
import java.util.Collections;
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

import com.fedex.ziptodest.model.CountryCode;
import com.fedex.ziptodest.utils.iseries.AppConstants;
import com.fedex.ziptodest.utils.iseries.CountryCodeMapper;
import com.fedex.ziptodest.utils.iseries.ValidationUtil;

@Component
public class CountryCodeDao {
	public static final Logger LOGGER = LoggerFactory.getLogger(CountryCodeDao.class);

	@Autowired
	@Qualifier("strRedisTemplate")
	RedisTemplate<String, String> strRedisTemplate;

	@Resource(name = "strRedisTemplate")
	HashOperations<String, String, String> strHashOperations;

	@Resource(name = "strRedisTemplate")
	SetOperations<String, String> strSetOperations;

	@Autowired
	CountryCodeMapper countryCodeMapper;

	@Autowired
	private HashSetOperations hashSetOperations;
	
	@Value(value = "${keyspace}")
	private String keyspace;

	/**
	 * 
	 * @param countryCode
	 * @return
	 */
	public CountryCode save(CountryCode countryCode) {

		try {
			String hashKey = ValidationUtil.getIseriesCountryCodeKey(keyspace, countryCode.getCycode());
			strHashOperations.putAll(hashKey, countryCodeMapper.toMap(countryCode));

			strSetOperations.add(
					ValidationUtil.getIseriesKey(keyspace, AppConstants.ISERIES_COUNTRY_CODE_SET_KEY),
					hashKey);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return countryCode;
	}

	/**
	 * 
	 * @param countryCodes
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public void saveAll(List<CountryCode> countryCodes) {

		try {
			strRedisTemplate.executePipelined(new SessionCallback<List<Object>>() {

				@Override
				public List<Object> execute(RedisOperations redisOperations) throws DataAccessException {

					String hashKey = "";
					for (CountryCode countryCode : countryCodes) {
						hashKey = ValidationUtil.getIseriesCountryCodeKey(keyspace, countryCode.getCycode());
						redisOperations.opsForHash().putAll(hashKey,
								countryCodeMapper.toMap(countryCode));

						redisOperations.opsForSet().add(ValidationUtil.getIseriesKey(keyspace,
								AppConstants.ISERIES_COUNTRY_CODE_SET_KEY), hashKey);
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
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CountryCode> findAll() {

		List<CountryCode> result = Collections.emptyList();
		try {
			Set<String> primaryKeys = hashSetOperations.findPrimaryKeys(ValidationUtil.getIseriesKey(keyspace,
					AppConstants.ISERIES_COUNTRY_CODE_SET_KEY));

			if (primaryKeys != null && !primaryKeys.isEmpty()) {
				
				List<Object> resultSet = hashSetOperations.getHashesFromRedis(primaryKeys);

				if (resultSet != null && !resultSet.isEmpty()) {
					result = new ArrayList<>();
					for (Object obj : resultSet) {
						result.add(countryCodeMapper.toCountryCode((Map<String, String>) obj));
					}
				}

			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return result;
	}
}
