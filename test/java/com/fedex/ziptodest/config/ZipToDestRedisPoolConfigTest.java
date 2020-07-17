package com.fedex.ziptodest.config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

@RunWith(SpringRunner.class)
@Profile("local")
@ContextConfiguration(classes = ZipToDestRedisPoolConfig.class)
public class ZipToDestRedisPoolConfigTest {

	@InjectMocks
	ZipToDestRedisPoolConfig zipToDestRedisPoolConfig;

	@Before
	public void setup() {
		ReflectionTestUtils.setField(zipToDestRedisPoolConfig, "redisHostName",
				"redis-12006.test8-edc-bo.redis.fedex.com");
		ReflectionTestUtils.setField(zipToDestRedisPoolConfig, "redisPort", 12006);
		ReflectionTestUtils.setField(zipToDestRedisPoolConfig, "redisPassword", "changeme");
		ReflectionTestUtils.setField(zipToDestRedisPoolConfig, "keyspace", "local");
		ReflectionTestUtils.setField(zipToDestRedisPoolConfig, "maxIdle", 300);
		ReflectionTestUtils.setField(zipToDestRedisPoolConfig, "minIdle", 300);
		ReflectionTestUtils.setField(zipToDestRedisPoolConfig, "timeOut", 300);
	}

	@Test
	public void testZipToDestJedisConnectionFactory() {
		assertNotNull(zipToDestRedisPoolConfig.zipToDestJedisConnectionFactory());
	}

	@Test
	public void testZipToDestRedisTemplate() {
		assertNotNull(zipToDestRedisPoolConfig.zipToDestRedisTemplate());

	}

	@Test
	public void testPool() {
		assertNotNull(zipToDestRedisPoolConfig.pool());
	}

	@Test
	public void testZipToDestKeyspaceConfiguration() {
		assertNotNull(zipToDestRedisPoolConfig.zipToDestKeyspaceConfiguration());
	}

}
