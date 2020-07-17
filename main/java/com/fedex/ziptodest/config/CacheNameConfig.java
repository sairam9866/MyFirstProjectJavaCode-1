//package com.fedex.ziptodest.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cache.CacheManager;
//import org.springframework.cache.annotation.CachingConfigurerSupport;
//import org.springframework.cache.interceptor.CacheResolver;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//
///**
// * @author 3790999
// *
// */
//@Configuration
//public class CacheNameConfig extends CachingConfigurerSupport {
//
//	public static final String PROPERTY_RESOLVER = "propertyResolvingCacheResolver";
//	
//	@Autowired
//	private CacheManager cacheManager;
//	
//	@Autowired
//	private Environment environment;
//	
//	@Bean(PROPERTY_RESOLVER)
//	@Override
//	public CacheResolver cacheResolver(){
//		return new PropertyCacheResolver(cacheManager, environment);
//	}
//}
