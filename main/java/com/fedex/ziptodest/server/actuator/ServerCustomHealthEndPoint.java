package com.fedex.ziptodest.server.actuator;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id="custom-health")
public class ServerCustomHealthEndPoint  {
	
	@ReadOperation
	public CustomHealth health(){
		Map<String, Object> details=new LinkedHashMap<>();
		details.put("CustomHealthStatus", "Everything looks good");
		
		CustomHealth health=new CustomHealth();
		health.setHealthDetails(details);
		
		return health;
		
	}
	
	@ReadOperation
	public String serverCutomEndPointByName(@Selector String name){
		return "custom-end-point";
	}
	
	@WriteOperation
	public void serverWriteOperation(@Selector String name){
		//Do nothing 
	}
	
	@DeleteOperation
	public void serverDeleteOperation(@Selector String name){
		//Do nothing 
	}

}
