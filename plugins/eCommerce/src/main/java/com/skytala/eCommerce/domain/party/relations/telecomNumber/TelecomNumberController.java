package com.skytala.eCommerce.domain.party.relations.telecomNumber;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.google.common.base.Splitter;
import com.skytala.eCommerce.domain.party.relations.telecomNumber.command.AddTelecomNumber;
import com.skytala.eCommerce.domain.party.relations.telecomNumber.command.DeleteTelecomNumber;
import com.skytala.eCommerce.domain.party.relations.telecomNumber.command.UpdateTelecomNumber;
import com.skytala.eCommerce.domain.party.relations.telecomNumber.event.TelecomNumberAdded;
import com.skytala.eCommerce.domain.party.relations.telecomNumber.event.TelecomNumberDeleted;
import com.skytala.eCommerce.domain.party.relations.telecomNumber.event.TelecomNumberFound;
import com.skytala.eCommerce.domain.party.relations.telecomNumber.event.TelecomNumberUpdated;
import com.skytala.eCommerce.domain.party.relations.telecomNumber.mapper.TelecomNumberMapper;
import com.skytala.eCommerce.domain.party.relations.telecomNumber.model.TelecomNumber;
import com.skytala.eCommerce.domain.party.relations.telecomNumber.query.FindTelecomNumbersBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/party/telecomNumbers")
public class TelecomNumberController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public TelecomNumberController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a TelecomNumber
	 * @return a List with the TelecomNumbers
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findTelecomNumbersBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindTelecomNumbersBy query = new FindTelecomNumbersBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<TelecomNumber> telecomNumbers =((TelecomNumberFound) Scheduler.execute(query).data()).getTelecomNumbers();

		if (telecomNumbers.size() == 1) {
			return ResponseEntity.ok().body(telecomNumbers.get(0));
		}

		return ResponseEntity.ok().body(telecomNumbers);

	}

	/**
	 * 
	 * this method will only be called by Springs DispatcherServlet
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return true on success; false on fail
	 */
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createTelecomNumber(HttpServletRequest request) throws Exception {

		TelecomNumber telecomNumberToBeAdded = new TelecomNumber();
		try {
			telecomNumberToBeAdded = TelecomNumberMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createTelecomNumber(telecomNumberToBeAdded);

	}

	/**
	 * creates a new TelecomNumber entry in the ofbiz database
	 * 
	 * @param telecomNumberToBeAdded
	 *            the TelecomNumber thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createTelecomNumber(@RequestBody TelecomNumber telecomNumberToBeAdded) throws Exception {

		AddTelecomNumber command = new AddTelecomNumber(telecomNumberToBeAdded);
		TelecomNumber telecomNumber = ((TelecomNumberAdded) Scheduler.execute(command).data()).getAddedTelecomNumber();
		
		if (telecomNumber != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(telecomNumber);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("TelecomNumber could not be created.");
	}

	/**
	 * this method will only be called by Springs DispatcherServlet
	 * 
	 * @deprecated
	 * @param request
	 *            HttpServletRequest object
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@PutMapping(value = "/update", consumes = "application/x-www-form-urlencoded")
	public boolean updateTelecomNumber(HttpServletRequest request) throws Exception {

		BufferedReader br;
		String data = null;
		Map<String, String> dataMap = null;

		try {
			br = new BufferedReader(new InputStreamReader(request.getInputStream()));
			if (br != null) {
				data = java.net.URLDecoder.decode(br.readLine(), "UTF-8");
			}
		} catch (IOException e1) {
			e1.printStackTrace();
			return false;
		}

		dataMap = Splitter.on('&').trimResults().withKeyValueSeparator(Splitter.on('=').limit(2).trimResults())
				.split(data);

		TelecomNumber telecomNumberToBeUpdated = new TelecomNumber();

		try {
			telecomNumberToBeUpdated = TelecomNumberMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateTelecomNumber(telecomNumberToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the TelecomNumber with the specific Id
	 * 
	 * @param telecomNumberToBeUpdated
	 *            the TelecomNumber thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateTelecomNumber(@RequestBody TelecomNumber telecomNumberToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		telecomNumberToBeUpdated.setnull(null);

		UpdateTelecomNumber command = new UpdateTelecomNumber(telecomNumberToBeUpdated);

		try {
			if(((TelecomNumberUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{telecomNumberId}")
	public ResponseEntity<Object> findById(@PathVariable String telecomNumberId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("telecomNumberId", telecomNumberId);
		try {

			Object foundTelecomNumber = findTelecomNumbersBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundTelecomNumber);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{telecomNumberId}")
	public ResponseEntity<Object> deleteTelecomNumberByIdUpdated(@PathVariable String telecomNumberId) throws Exception {
		DeleteTelecomNumber command = new DeleteTelecomNumber(telecomNumberId);

		try {
			if (((TelecomNumberDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("TelecomNumber could not be deleted");

	}

}
