package com.fedex.ziptodest.server.actuator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.redis.RedisHealthIndicator;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisCustomHealthCheck implements HealthIndicator {
	private static final Logger log = LoggerFactory.getLogger(RedisCustomHealthCheck.class);

	@Lazy
	@Autowired
	private RedisTemplate<?, ?> redisTemplate;

	@Override
	public Health health() {
		RedisConnectionFactory connectionFactory = redisTemplate.getConnectionFactory();
		RedisHealthIndicator redisHealth = new RedisHealthIndicator(connectionFactory);
		log.info("RedisCustomHealthCheck - {}", redisHealth.health());
		return redisHealth.health();
	}

}
