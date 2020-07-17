package com.fedex.ziptodest.repository.iseries;

import java.util.List;
import java.util.Map;
import java.util.Optional;
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

import com.fedex.ziptodest.model.Network;
import com.fedex.ziptodest.utils.iseries.AppConstants;
import com.fedex.ziptodest.utils.iseries.NetworkMapper;
import com.fedex.ziptodest.utils.iseries.ValidationUtil;

@Component
public class NetworkDao {

	public static final Logger LOGGER = LoggerFactory.getLogger(NetworkDao.class);

	@Resource(name = "strRedisTemplate")
	private HashOperations<String, String, String> strHashOperations;

	@Resource(name = "strRedisTemplate")
	private SetOperations<String, String> strSetOperations;

	@Autowired
	@Qualifier("strRedisTemplate")
	RedisTemplate<String, String> strRedisTemplate;

	@Autowired
	NetworkMapper networkMapper;

	@Autowired
	HashSetOperations hashSetOperations;

	@Value(value = "${keyspace}")
	private String keyspace;

	/**
	 * 
	 * @return
	 */
	public String getKeySpace() {
		return this.keyspace;
	}

	/**
	 * 
	 * @param network
	 * @return
	 */
	public Network save(Network network) {
		
		LOGGER.info("Network : {}", network);
		String hashKey = ValidationUtil.getIseriesNetworkKey(keyspace, network.getNetworkId());

		strHashOperations.putAll(hashKey, networkMapper.toMap(network));

		strSetOperations.add(ValidationUtil.getIseriesKey(keyspace, AppConstants.ISERIES_NETWORK_SET_KEY), hashKey);

		strSetOperations.add(ValidationUtil.getIseriesKey(getKeySpace(), AppConstants.HASH_KEY_NETWORK),
				network.getNetworkId());

		return network;
	}

	/**
	 * 
	 * @param networks
	 */
	@SuppressWarnings("unchecked")
	public void saveAll(List<Network> networks) {

		strRedisTemplate.executePipelined(new SessionCallback<List<Object>>() {

			@Override
			public List<Object> execute(RedisOperations redisOperations) throws DataAccessException {

				for (Network network : networks) {
					redisOperations.opsForHash().putAll(network.getNetworkId(), networkMapper.toMap(network));

					redisOperations.opsForSet().add(AppConstants.ISERIES_NETWORK_SET_KEY, network.getNetworkId());

					redisOperations.opsForSet().add(AppConstants.HASH_KEY_NETWORK, network.getNetworkId());
				}
				return null;
			}

		});

	}

	/**
	 * 
	 * @param networkId
	 * @return
	 */
	public Optional<Network> findById(String networkId) {
		Optional<Network> network = Optional.empty();
		Map<String, String> map = strHashOperations.entries(networkId);
		if (map != null) {
			network = Optional.of(networkMapper.toNetwork(map));
		}
		return network;
	}

	/**
	 * 
	 * @return
	 */
	public Set<String> selectAllNetworks() {
		return strSetOperations.members(ValidationUtil.getIseriesKey(getKeySpace(), AppConstants.HASH_KEY_NETWORK));
	}
}
