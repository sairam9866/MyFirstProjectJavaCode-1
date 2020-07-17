package com.fedex.ziptodest.config;

import java.io.Closeable;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.convert.MappingConfiguration;
import org.springframework.data.redis.core.index.IndexConfiguration;
import org.springframework.data.redis.core.mapping.RedisMappingContext;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import redis.clients.jedis.JedisPoolConfig;
import redis.embedded.RedisServer;

@Configuration
@ServletComponentScan
@Profile({"development", "release","stage","production"})
public class ZipToDestRedisPoolConfig implements Closeable {
	
	public static final Logger LOGGER = LoggerFactory.getLogger(ZipToDestRedisPoolConfig.class);
	
	private RedisServer redisServer;
	
	@Value(value = "${keyspace}")
	private String keyspace;

	@Value("${spring.profiles.active:Unknown}")
	private String activeProfile;
	
	@Value("${spring.redis.port}")
	private Integer redisPort;

	@Value("${spring.redis.host}")
	private String redisHostName;
	
	@Value("${spring.redis.password}")
	private String redisPassword;
	
	
	@Value("${spring.redis.pool.maxidle}")
	private Integer maxIdle;
	
	@Value("${spring.redis.pool.minidle}")
	private Integer minIdle;

	@Value("${spring.redis.pool.timeout}")
	private Integer timeOut;
	
	
    @Bean
    JedisConnectionFactory zipToDestJedisConnectionFactory(){
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(redisHostName, redisPort);
        if (StringUtils.isNotBlank(redisPassword)) {
			redisStandaloneConfiguration.setPassword(redisPassword);
		}
        JedisClientConfiguration.JedisClientConfigurationBuilder jedisClientConfigurationBuilder=JedisClientConfiguration.builder();
        jedisClientConfigurationBuilder.usePooling();
        return new JedisConnectionFactory(redisStandaloneConfiguration,jedisClientConfigurationBuilder.build());
    }
	
	
	@Bean(name = "zipToDestRedisTemplate")
	RedisTemplate<?, ?> zipToDestRedisTemplate() {
		RedisTemplate<String, Object> zipToDestRedisTemplate = new RedisTemplate<>();
		zipToDestRedisTemplate.setConnectionFactory(zipToDestJedisConnectionFactory());
		zipToDestRedisTemplate.setKeySerializer(new StringRedisSerializer());

		zipToDestRedisTemplate.setHashKeySerializer(new StringRedisSerializer());
		zipToDestRedisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
		zipToDestRedisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
		zipToDestRedisTemplate.afterPropertiesSet();
		zipToDestRedisTemplate.setEnableTransactionSupport(true);
		return zipToDestRedisTemplate;
	}
	
	
	
	@Bean
    JedisPoolConfig pool(){
        JedisPoolConfig jedisPoolConfig= new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
		jedisPoolConfig.setMinIdle(minIdle);
		jedisPoolConfig.setMaxWaitMillis(timeOut);
        return jedisPoolConfig;
    }
	@Bean
	ZipToDestKeyspaceConfiguration zipToDestKeyspaceConfiguration(){
		LOGGER.info("Setting keyspace to zipToDestKeyspaceConfiguration : {}", keyspace);
		return new ZipToDestKeyspaceConfiguration(keyspace);
	}
	
	@Bean(name = "strRedisTemplate")
	RedisTemplate<?, ?> strRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
		redisTemplate.setConnectionFactory(redisConnectionFactory);
		redisTemplate.setKeySerializer(new StringRedisSerializer());

		//redisTemplate.setHashKeySerializer(new GenericToStringSerializer<Object>(Object.class));
		//redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());
		//redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
		
		redisTemplate.setHashKeySerializer(new StringRedisSerializer());		
		redisTemplate.setHashValueSerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(new StringRedisSerializer());
		//redisTemplate.setEnableTransactionSupport(true);
		redisTemplate.afterPropertiesSet();
		return redisTemplate;
	}

	@Bean
	public RedisMappingContext keyValueMappingContext(ZipToDestKeyspaceConfiguration keyspaceConfiguration){
		
		return new RedisMappingContext(
				new MappingConfiguration(new IndexConfiguration(), keyspaceConfiguration));
	}

	@Override
	public void close() throws IOException {
		LOGGER.info("Redis server Shutdown initiated close: {}", "...");
		redisServer.stop();
		
	}
}