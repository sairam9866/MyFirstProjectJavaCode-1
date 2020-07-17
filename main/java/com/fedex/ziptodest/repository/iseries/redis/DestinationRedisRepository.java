package com.fedex.ziptodest.repository.iseries.redis;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fedex.ziptodest.model.Destination;


@Repository("destinationRedisRepository")
public interface DestinationRedisRepository extends CrudRepository<Destination, Integer>{
	
	public List<Destination> findByTerminalNumberAndTerminalStatus(Integer terminalNumber, Character terminalStatus);
}
