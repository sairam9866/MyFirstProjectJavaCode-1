package com.fedex.ziptodest.server.actuator;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;


@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CustomHealth {
	
	private Map<String, Object> healthDetails;
	
	
	public void setHealthDetails(Map<String, Object> healthDetails) {
		this.healthDetails = healthDetails;
	}


	
	public Map<String,Object> getHealthDetails(){
		
		return this.healthDetails;
	}

}
