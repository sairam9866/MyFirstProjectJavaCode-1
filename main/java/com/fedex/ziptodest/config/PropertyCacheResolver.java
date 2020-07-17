//package com.fedex.ziptodest.config;
//
//import java.util.Collection;
//import java.util.stream.Collectors;
//
//import org.springframework.cache.CacheManager;
//import org.springframework.cache.interceptor.CacheOperationInvocationContext;
//import org.springframework.cache.interceptor.SimpleCacheResolver;
//import org.springframework.core.env.PropertyResolver;
//
///**
// * @author 3790999
// *
// */
//public class PropertyCacheResolver extends SimpleCacheResolver {
//
//	private final PropertyResolver propertyResolver;
//
//	protected PropertyCacheResolver(CacheManager cacheManager, PropertyResolver propertyResolver) {
//		super(cacheManager);
//		this.propertyResolver = propertyResolver;
//	}
//
//	@Override
//	protected Collection<String> getCacheNames(CacheOperationInvocationContext<?> context){
//		Collection<String> unresolvedCacheNames = super.getCacheNames(context);
//		return unresolvedCacheNames.stream()
//				.map(propertyResolver::resolveRequiredPlaceholders)
//				.map(String::toUpperCase)
//				.collect(Collectors.toList());
//	}
//
//}
