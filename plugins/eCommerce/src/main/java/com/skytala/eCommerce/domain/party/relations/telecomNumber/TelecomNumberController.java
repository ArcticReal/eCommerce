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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	public ResponseEntity<List<TelecomNumber>> findTelecomNumbersBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindTelecomNumbersBy query = new FindTelecomNumbersBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<TelecomNumber> telecomNumbers =((TelecomNumberFound) Scheduler.execute(query).data()).getTelecomNumbers();

		return ResponseEntity.ok().body(telecomNumbers);

	}

	/**
	 * creates a new TelecomNumber entry in the ofbiz database
	 * 
	 * @param telecomNumberToBeAdded
	 *            the TelecomNumber thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<TelecomNumber> createTelecomNumber(@RequestBody TelecomNumber telecomNumberToBeAdded) throws Exception {

		AddTelecomNumber command = new AddTelecomNumber(telecomNumberToBeAdded);
		TelecomNumber telecomNumber = ((TelecomNumberAdded) Scheduler.execute(command).data()).getAddedTelecomNumber();
		
		if (telecomNumber != null) 
			return successful(telecomNumber);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateTelecomNumber(@RequestBody TelecomNumber telecomNumberToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		telecomNumberToBeUpdated.setnull(null);

		UpdateTelecomNumber command = new UpdateTelecomNumber(telecomNumberToBeUpdated);

		try {
			if(((TelecomNumberUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{telecomNumberId}")
	public ResponseEntity<TelecomNumber> findById(@PathVariable String telecomNumberId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("telecomNumberId", telecomNumberId);
		try {

			List<TelecomNumber> foundTelecomNumber = findTelecomNumbersBy(requestParams).getBody();
			if(foundTelecomNumber.size()==1){				return successful(foundTelecomNumber.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{telecomNumberId}")
	public ResponseEntity<String> deleteTelecomNumberByIdUpdated(@PathVariable String telecomNumberId) throws Exception {
		DeleteTelecomNumber command = new DeleteTelecomNumber(telecomNumberId);

		try {
			if (((TelecomNumberDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
