package com.fedex.ziptodest.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties
public class Department {

	@Value("#{'${sortation_role}'.split(',')}")
	private List<String> sortationRole;

	@Value("#{'${vision_management_role}'.split(',')}")
	private List<String> visionManagementRole;

	public List<String> getSortationRole() {
		return sortationRole;
	}

	public List<String> getVisionManagementRole() {
		return visionManagementRole;
	}

}
