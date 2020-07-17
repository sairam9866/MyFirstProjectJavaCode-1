package com.fedex.ziptodest.controller.iseries;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fedex.ziptodest.model.StateProvinceResponse;
import com.fedex.ziptodest.service.iseries.StateProvinceService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class StateProvinceController {
	
	private static Logger log = LoggerFactory.getLogger(StateProvinceController.class);

	@Autowired
	private StateProvinceService stateProvinceService;

	
	/***
	 * Shows List of State-Province along with respective country codes
	 * 
	 * @return List of State-Province with respective Country codes
	 */
	@ApiOperation(notes = "State-province with country codes", value = "Shows State-Province along with respective country code")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success.") })
	@GetMapping(value = "/state-province")
	public ResponseEntity<StateProvinceResponse> getStateCountry() {
		log.info("request :getStateCountry");

		return new ResponseEntity<>(stateProvinceService.getStateCountryCode(), HttpStatus.OK);
	}

}
