package com.fedex.ziptodest.config;

import java.io.Closeable;
import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.convert.MappingConfiguration;
import org.springframework.data.redis.core.index.IndexConfiguration;
import org.springframework.data.redis.core.mapping.RedisMappingContext;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fedex.ziptodest.service.iseries.EmbeddedServerPayloadService;

import net.logstash.logback.encoder.org.apache.commons.lang.StringUtils;
import redis.embedded.RedisServer;
import redis.embedded.RedisServerBuilder;

@Configuration
@Profile("local")
public class ZipToDestRedisConfig implements Closeable {

	private static final Logger LOGGER = LoggerFactory.getLogger(ZipToDestRedisConfig.class);

	private RedisServer redisServer;

	@Value(value = "${keyspace}")
	private String keyspace;

	@Value("${spring.redis.port:6376}")
	private Integer redisPort;

	@Value("${spring.redis.host:localhost}")
	private String redisHostName;

//	@Autowired
//	EmbeddedServerPayloadService embeddedServerPayloadService;

//	@Autowired
//	ZDServerPayloadService zdServerPayloadService;

	@PostConstruct
	public void init() {
		LOGGER.info("iSeries Redis Host : {}", redisHostName);
		LOGGER.info("iSeries Redis Port : {}", redisPort);
		redisServer = new RedisServerBuilder().port(redisPort).setting("maxmemory 256M").build();
		redisServer.start();
		LOGGER.info("iSeries  Embedded Redis Server Started....");
//		embeddedServerPayloadService.init();
//		zdServerPayloadService.init();
	}

	@PreDestroy
	public void destroy() {
		redisServer.stop();
		LOGGER.info("iSeries  Embedded Redis Server Stopped.");
	}

	@Override
	public void close() throws IOException {
		LOGGER.info("Redis server Shutdown initiated close: {}", "...");
		redisServer.stop();

	}

	@Bean
	RedisConnectionFactory connectionFactory() {
		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(redisHostName,
				redisPort);
		return new JedisConnectionFactory(redisStandaloneConfiguration);
	}

	@Bean(name = "zipToDestRedisTemplate")
	RedisTemplate<?, ?> zipToDestRedisTemplate() {
		RedisTemplate<String, Object> zipToDestRedisTemplate = new RedisTemplate<>();
		zipToDestRedisTemplate.setConnectionFactory(connectionFactory());
		zipToDestRedisTemplate.setKeySerializer(new StringRedisSerializer());

		zipToDestRedisTemplate.setHashKeySerializer(new StringRedisSerializer());
		zipToDestRedisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
		zipToDestRedisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
		zipToDestRedisTemplate.afterPropertiesSet();
		zipToDestRedisTemplate.setEnableTransactionSupport(true);
		return zipToDestRedisTemplate;
	}

	@Bean
	ZipToDestKeyspaceConfiguration zipToDestKeyspaceConfiguration() {
		LOGGER.info("Setting keyspace to zipToDestKeyspaceConfiguration : {}", keyspace);
		return new ZipToDestKeyspaceConfiguration(keyspace);
	}

	@Bean
	public RedisMappingContext keyValueMappingContext(ZipToDestKeyspaceConfiguration keyspaceConfiguration) {
		return new RedisMappingContext(new MappingConfiguration(new IndexConfiguration(), keyspaceConfiguration));
	}
	
	@Bean(name = "strRedisTemplate")
	RedisTemplate<?, ?> strRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
		redisTemplate.setConnectionFactory(redisConnectionFactory);
		redisTemplate.setKeySerializer(new StringRedisSerializer());

		redisTemplate.setHashKeySerializer(new StringRedisSerializer());		
		redisTemplate.setHashValueSerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(new StringRedisSerializer());
		//redisTemplate.setEnableTransactionSupport(true);
		redisTemplate.afterPropertiesSet();
		return redisTemplate;
	}
}