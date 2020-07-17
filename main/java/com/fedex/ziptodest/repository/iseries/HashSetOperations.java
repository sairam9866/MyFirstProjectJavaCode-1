package com.fedex.ziptodest.repository.iseries;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Component;

@Component
public class HashSetOperations {

	public static final Logger LOGGER = LoggerFactory.getLogger(HashSetOperations.class);
	
	@Autowired
	@Qualifier("strRedisTemplate")
	RedisTemplate<String, String> strRedisTemplate;
	
	@Resource(name = "strRedisTemplate")
	private SetOperations<String, String> strSetOperations;

	/**
	 * 
	 * @param setKey
	 * @return
	 */
	public Set<String> findPrimaryKeys(String setKey) {
		return strSetOperations.members(setKey);
	}
	
	public List<Object> getHashesFromRedis(Set<String> ids) {
		List<Object> result = strRedisTemplate.executePipelined(new SessionCallback<List<Map<String, String>>>() {

			@Override
			public List<Map<String, String>> execute(RedisOperations operations) throws DataAccessException {
				//operations.multi();

				for (String id : ids) {
					Map<String, String> map = operations.opsForHash().entries(id);

				}

				return null;
			}

		});
		return result;
	}
	
}
