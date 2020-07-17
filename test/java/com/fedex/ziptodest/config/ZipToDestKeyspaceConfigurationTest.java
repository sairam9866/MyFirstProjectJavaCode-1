package com.fedex.ziptodest.config;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.data.redis.core.convert.KeyspaceConfiguration.KeyspaceSettings;
import org.springframework.test.context.junit4.SpringRunner;

import com.fedex.ziptodest.model.ZipToDest;

@RunWith(SpringRunner.class)
public class ZipToDestKeyspaceConfigurationTest {
	@InjectMocks
	ZipToDestKeyspaceConfiguration zipToDestKeyspaceConfiguration;

	@Test
	public void testGetKeyspaceSettings() {
		ZipToDestKeyspaceConfiguration zipToDestKeyspaceConfiguration = new ZipToDestKeyspaceConfiguration("local");
		KeyspaceSettings keySpace = zipToDestKeyspaceConfiguration.getKeyspaceSettings(ZipToDest.class);
		assertEquals("{LOCAL-ZIPTODEST}", keySpace.getKeyspace());
	}

}
