package com.fedex.ziptodest.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.convert.KeyspaceConfiguration;

public class ZipToDestKeyspaceConfiguration extends KeyspaceConfiguration {

	public static final Logger LOGGER = LoggerFactory.getLogger(ZipToDestKeyspaceConfiguration.class);

	private String keyspace;

	public ZipToDestKeyspaceConfiguration(String keyspace) {
		LOGGER.info("ZipToDestKeyspaceConfiguration Construnctor - keyspace : {}", keyspace);
		this.keyspace = keyspace;
	}

	public String getKeyspace() {
		return keyspace;
	}

	public void setKeyspace(String keyspace) {
		this.keyspace = keyspace;
	}

	@Override
	public boolean hasSettingsFor(Class<?> type) {
		return true;
	}

	@Override
	public KeyspaceSettings getKeyspaceSettings(Class<?> type) {
		LOGGER.info("getKeyspaceSettings : Keyspace : {}", getKeyspace());
		String typeName = type.getSimpleName().toUpperCase();
		return new KeyspaceSettings(type, "{" + getKeyspace().toUpperCase() + "-" + typeName + "}");
	}
}