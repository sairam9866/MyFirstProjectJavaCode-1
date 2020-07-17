package com.fedex.ziptodest.controller.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fedex.ziptodest.utils.server.ZDConstants;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/actuator")
public class RefreshController {

	@Autowired
	Environment environment;

	@ApiOperation(notes = "All locations for a network", value = "Shows output of all destination zip associations for a particular network")
	@ApiResponses(value = { @ApiResponse(code = 404, message = ZDConstants.STATUS_CODE_INVALID),
			@ApiResponse(code = 401, message = ZDConstants.STATUS_CODE_UNAUTHORIZED),
			@ApiResponse(code = 200, message = "Success.") })
	@PostMapping(value = "/refresh", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> refresh(@RequestHeader("apiKey") String apiKey) {		
		return new ResponseEntity<>("Refreshed", HttpStatus.OK);
	}
}
