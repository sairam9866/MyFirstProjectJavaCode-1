package com.fedex.ziptodest.controller.server;

import java.text.ParseException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fedex.ziptodest.exception.server.ApplicationException;
import com.fedex.ziptodest.model.ZipToDest;
import com.fedex.ziptodest.model.ZipToDestAddRequest;
import com.fedex.ziptodest.model.ZipToDestCancelRequest;
import com.fedex.ziptodest.model.ZipToDestDeleteRequest;
import com.fedex.ziptodest.model.ZipToDestModifyRequest;
import com.fedex.ziptodest.model.ZipToDestNotProcessed;
import com.fedex.ziptodest.model.ZipToDestStatusResponse;
import com.fedex.ziptodest.service.server.ZDService;
import com.fedex.ziptodest.utils.server.ValidationUtil;
import com.fedex.ziptodest.utils.server.ZDTimer;
import com.fedex.ziptodest.utils.server.ZDConstants;

@RefreshScope
@RestController
@RequestMapping(value = "/records")
public class ZipToDestController {

	@Autowired
	private ZDService zDService;

	@Autowired
	private ValidationUtil validationUtil;

	public static final Logger log = LoggerFactory.getLogger(ZipToDestController.class);

	/**
	 * Get all records from Tans_zip_to_dest table.
	 * 
	 * @return List of records.
	 */
	@GetMapping(value = "/current", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getAllZDRecord() {
		log.info("Request: getCurrentRecords");
		ZDTimer timer = ZDTimer.start();
	String currentRecords = zDService.getAllZDRecord().toString();
		log.info("Time Taken for currentRecords : {}ms", timer.stop());
		return ResponseEntity.ok(currentRecords);
	}

	/**
	 * Add the records as given as input
	 * 
	 * @param addZipToDestRequest
	 * @return Status success if the records get added.
	 * @throws ParseException
	 * @throws ApplicationException 
	 */
	@PostMapping(value = "/add", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ZipToDestStatusResponse> addZDRecord(@RequestBody ZipToDestAddRequest addZipToDestRequest)
			throws ParseException, ApplicationException {
		log.info("Request: add records");
		ZDTimer timer = ZDTimer.start();
		ResponseEntity<ZipToDestStatusResponse> response;
		String validationResponse = validationUtil.validateAddRequest(addZipToDestRequest);

		if (!ZDConstants.VALID.equals(validationResponse)) {
			log.info("Records are not added due to some invalid inputs, exit controller: {}",validationResponse);
			response = getZipToDestStatusResponse(ZDConstants.INVALID, validationResponse,
					HttpStatus.BAD_REQUEST);
		} else {
			zDService.addZDRecord(addZipToDestRequest);
			log.info("Records added successfully to Transaction table, exit controller");
			log.info("Time Taken for add records : {}ms", timer.stop());
			response = getZipToDestStatusResponse(ZDConstants.RESPONSE_SUCCESS_MSG,
					ZDConstants.RESPONSE_ADD_MSG, HttpStatus.OK);
		}

		return response;

	}

	/**
	 * Insert new records with the updated destination and effective date which
	 * falls between the given zipcode ranges.
	 * 
	 * @param zipToDestModifyRequest
	 *            Accept the input request
	 * @return if records gets modified then success message else return
	 *         exception message.
	 * @throws ParseException
	 * @throws ApplicationException 
	 * 
	 */
	@PutMapping(value = "/modify", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ZipToDestStatusResponse> modifyZDRecord(
			@RequestBody ZipToDestModifyRequest zipToDestModifyRequest) throws ParseException, ApplicationException {
		ResponseEntity<ZipToDestStatusResponse> zipToDestStatusResponse = null;
		log.info("ZipToDestController : Entering modifyZDRecord");
		ZDTimer timer = ZDTimer.start();
		String validationResponse = validationUtil.validateModifyRequest(zipToDestModifyRequest);

		if (!ZDConstants.VALID.equals(validationResponse)) {
			zipToDestStatusResponse = getZipToDestStatusResponse(ZDConstants.INVALID, validationResponse,
					HttpStatus.BAD_REQUEST);
		} else {
			String response = zDService.modifyRecords(zipToDestModifyRequest);

			if (null != response && response.equals(ZDConstants.RESPONSE_SUCCESS_MSG)) {
				log.info("ZipToDestController : Exiting modifyZDRecord");
				zipToDestStatusResponse = getZipToDestStatusResponse(ZDConstants.RESPONSE_SUCCESS_MSG,
						ZDConstants.RESPONSE_MODIFY_MSG, HttpStatus.OK);
			} else {
				log.debug(" ZipToDestController modify {} ", response);
				log.info("Time Taken for Modify records : {}ms", timer.stop());
				zipToDestStatusResponse = getZipToDestStatusResponse(ZDConstants.RESPONSE_ERROR_MSG, response,
						HttpStatus.BAD_REQUEST);
			}
		}
		return zipToDestStatusResponse;
	}

	/**
	 * Insert the new records to the transaction table without destination and
	 * with new effective date.
	 * 
	 * @param zipToDestDeleteRequest
	 *            accept the input request
	 * @return if records gets deleted then success message else return
	 *         exception message.
	 * @throws ParseException
	 * @throws ApplicationException 
	 */
	@DeleteMapping(value = "/delete", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ZipToDestStatusResponse> deleteZDRecord(
			@RequestBody ZipToDestDeleteRequest zipToDestDeleteRequest) throws ParseException, ApplicationException {
		ResponseEntity<ZipToDestStatusResponse> zipToDestStatusResponse = null;
		log.info("Request: delete records");
		ZDTimer timer = ZDTimer.start();
		String validationResponse = validationUtil.validateDeleteRequest(zipToDestDeleteRequest);

		if (!ZDConstants.VALID.equals(validationResponse)) {
			zipToDestStatusResponse = getZipToDestStatusResponse(ZDConstants.INVALID, validationResponse,
					HttpStatus.BAD_REQUEST);
		} else {
			String response = zDService.deleteRecords(zipToDestDeleteRequest);
			if (null != response && response.equals(ZDConstants.RESPONSE_SUCCESS_MSG)) {
				log.info("Records deleteZDRecord updated, exit controller");
				zipToDestStatusResponse = getZipToDestStatusResponse(ZDConstants.RESPONSE_SUCCESS_MSG,
						ZDConstants.RESPONSE_DELETE_MSG, HttpStatus.OK);
			} else {
				log.debug(" ZipToDestController delete {} ", response);
				log.info("Time Taken for delete records : {}ms", timer.stop());
				zipToDestStatusResponse = getZipToDestStatusResponse(ZDConstants.RESPONSE_ERROR_MSG, response,
						HttpStatus.BAD_REQUEST);
			}
		}
		return zipToDestStatusResponse;
	}

	/**
	 * Returns a list of ZipToDestNotProcessed object which are not processed.
	 * 
	 * @return List of ZipToDestNotProcessed records.
	 */
	@GetMapping(value = "/future/cancel", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ZipToDestNotProcessed>> getNotProcessedZDRecord() {
		log.info("Request: futureTransactions/get");
		ZDTimer timer = ZDTimer.start();
		List<ZipToDestNotProcessed> notprocessedRecords = zDService.getNotProcessedZDRecords();
		log.info("Time Taken for future cancel records : {}ms", timer.stop());
		return new ResponseEntity<>(notprocessedRecords, HttpStatus.OK);
	}
	
	/**
	 * Method will process list of ZipToDestNotProcessed records and updates its
	 * corresponding ZipToDest record as cancelled in the transaction table.
	 * 
	 * @param zipToDestCancelRequest
	 * @return
	 */
	@PutMapping(value = "/future/cancel", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ZipToDestStatusResponse> cancelNotProcessedZDRecord(
			@RequestBody ZipToDestCancelRequest zipToDestCancelRequest) {
		log.info("::cancelNotProcessedZDRecord - Cancelling future transactions.");
		log.debug("::cancelNotProcessedZDRecord - User Input - {}.", zipToDestCancelRequest);
		ZDTimer timer = ZDTimer.start();
		ResponseEntity<ZipToDestStatusResponse> response = null;

		String validationResponse = validationUtil.validateCancelRequest(zipToDestCancelRequest);

		if (ZDConstants.VALID.equals(validationResponse)) {
			log.debug("::cancelNotProcessedZDRecord - User Name : {}", zipToDestCancelRequest.getUser());
			log.debug("::cancelNotProcessedZDRecord - Count of UUID's : {}", zipToDestCancelRequest.getUuid().size());

			try {
				int updateCount = zDService.cancelZipToDestNotProcessed(zipToDestCancelRequest);
				if (updateCount == 0) {
					response = getZipToDestStatusResponse(ZDConstants.RESPONSE_SUCCESS_MSG,
							ZDConstants.RESPONSE_CANCEL_SUCCESS_MSG, HttpStatus.NO_CONTENT);
				} else {
					response = getZipToDestStatusResponse(ZDConstants.RESPONSE_SUCCESS_MSG,
							ZDConstants.RESPONSE_CANCEL_SUCCESS_MSG, HttpStatus.OK);
				}
				log.debug("::cancelNotProcessedZDRecord - Selected not processed records are successfully cancelled.");
			} catch (Exception exception) {
				log.error("::cancelNotProcessedZDRecord - Exception occured : {}", exception.getMessage());
				response = getZipToDestStatusResponse(ZDConstants.RESPONSE_ERROR_MSG,
						ZDConstants.RESPONSE_CANCEL_ERROR_MSG, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			log.debug("::cancelNotProcessedZDRecord - The payload missing user name or list of uuid.");
			response = getZipToDestStatusResponse(ZDConstants.INVALID, validationResponse,
					HttpStatus.BAD_REQUEST);
		}
		log.info("Time Taken for cancel records : {}ms", timer.stop());
		return response;
	}
	
	@GetMapping(value = "/delta/{lastProcessedAt}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ZipToDest>> getLatestProcessedTransactions(
			@PathVariable("lastProcessedAt") String lastProcessedAt) {
		ZDTimer timer = ZDTimer.start();
		List<ZipToDest> latestProcessedTransaction = zDService.findLatestProcesedTransactions(lastProcessedAt);
		log.info("Time Taken for delta records : {}ms", timer.stop());
		return ResponseEntity.ok(latestProcessedTransaction);
	}

	private ResponseEntity<ZipToDestStatusResponse> getZipToDestStatusResponse(String statusCode, String message,
			HttpStatus httpStatus) {
		return new ResponseEntity<>(new ZipToDestStatusResponse(statusCode, message), httpStatus);
	}

	/**
	 * Get all pending records from Tans_zip_to_dest table.
	 * 
	 * @return List of records.
	 */
	@GetMapping(value = "/future", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getAllPendingTransanctions() {
		ZDTimer timer = ZDTimer.start();
		log.info("Request: pendingTransanctions");
		String futureRecords = zDService.getAllPendingTransanctions().toString();
		log.info("Time Taken for future records : {}ms", timer.stop());
		return ResponseEntity.ok(futureRecords);
	}
	
	@GetMapping("/historical")
	public ResponseEntity<String> getHistoricalTransactions(){
		ZDTimer timer = ZDTimer.start();
		String historicalRecords = zDService.getHistoryTransactions().toString();
		log.info("Time Taken for future records : {}ms", timer.stop());
		return new ResponseEntity<>(historicalRecords,HttpStatus.OK);
	}
}
