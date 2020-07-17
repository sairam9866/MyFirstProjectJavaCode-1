package com.fedex.ziptodest.controller.iseries;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fedex.ziptodest.model.Network;
import com.fedex.ziptodest.model.NetworkModel;
import com.fedex.ziptodest.model.NetworkRequest;
import com.fedex.ziptodest.service.iseries.EmbeddedServerPayloadService;
import com.fedex.ziptodest.service.iseries.NetworkService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class NetworkController {
	
	
	
	@Autowired
    EmbeddedServerPayloadService embeddedServerPayloadService;

	@Autowired
	private NetworkService networkService;
	private static Logger log = LoggerFactory.getLogger(NetworkController.class);
	
	@GetMapping("/loadIseries")
    public String embbedePayloadService(){
        embeddedServerPayloadService.init();
        return "Loaded";
    }

	/***
	 * Shows List of all Networks
	 * 
	 * @return List of Networks
	 */
	@ApiOperation(notes = "All Networks", value = "Shows output of all networks")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success.") })
	@GetMapping(value = "/network")
	public ResponseEntity<NetworkModel> getAllNetworks() {
		log.info("Request: allNetworks");
		return new ResponseEntity<>(networkService.getAllNetworks(), HttpStatus.OK);
	}

	@PostMapping("/network/save")
	public ResponseEntity<?> saveNetwork(@RequestBody NetworkRequest networkRequest) {
		ResponseEntity<?> output = null;
		Network netWork = networkService.save(networkRequest);
		if (netWork != null) {
			output = new ResponseEntity<>(netWork, HttpStatus.CREATED);
		} else {
			output = new ResponseEntity<>("Invalid Input",HttpStatus.BAD_REQUEST);
		}
		return output;
	}

}
