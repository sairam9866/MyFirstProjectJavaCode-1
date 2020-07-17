package com.fedex.ziptodest.repository.iseries.redis;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fedex.ziptodest.model.Network;


@Repository("networkRedisRepository")
public interface NetworkRedisRepository extends CrudRepository<Network, Long> {
		public Set<String> selectAllNetworks();
}

