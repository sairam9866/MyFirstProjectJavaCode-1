//package com.fedex.ziptodest.controller.server;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestHeader;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.fedex.ziptodest.exception.server.UnauthorizedException;
//import com.fedex.ziptodest.service.server.CachingService;
//import com.fedex.ziptodest.utils.server.ZipToDestConstants;
//import com.fedex.ziptodest.utils.server.ZipToDestUtil;
//
//
//
//@RestController
//public class CacheController {
//	
//	public static final Logger LOGGER = LoggerFactory.getLogger(CacheController.class);
//	
//	@Autowired
//	CachingService cachingService;
//	
//	@Autowired
//	ZipToDestUtil zipToDestUtil;	
//
//	@GetMapping("clearAllCaches")
//	public void clearAllCaches(@RequestHeader("apiKey") String apiKey) {
//		LOGGER.debug("::clearAllCaches - Cache Key : {}", apiKey);
//		if(!zipToDestUtil.isValidApiKey(ZipToDestConstants.CACHE_SORTX, apiKey)){
//			LOGGER.debug("::clearAllCaches - The key {} is invalid.", apiKey);
//			throw new UnauthorizedException(ZipToDestConstants.MSG_API_KEY_UNAUTHORIZED);
//		}		
//		cachingService.evictAllCaches();
//	}
//}