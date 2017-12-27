package com.skytala.eCommerce.domain.humanres.relations.emplPosition.control.typeRate;

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
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.command.typeRate.AddEmplPositionTypeRate;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.command.typeRate.DeleteEmplPositionTypeRate;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.command.typeRate.UpdateEmplPositionTypeRate;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.event.typeRate.EmplPositionTypeRateAdded;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.event.typeRate.EmplPositionTypeRateDeleted;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.event.typeRate.EmplPositionTypeRateFound;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.event.typeRate.EmplPositionTypeRateUpdated;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.mapper.typeRate.EmplPositionTypeRateMapper;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.model.typeRate.EmplPositionTypeRate;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.query.typeRate.FindEmplPositionTypeRatesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/humanres/emplPosition/emplPositionTypeRates")
public class EmplPositionTypeRateController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public EmplPositionTypeRateController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a EmplPositionTypeRate
	 * @return a List with the EmplPositionTypeRates
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<EmplPositionTypeRate>> findEmplPositionTypeRatesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindEmplPositionTypeRatesBy query = new FindEmplPositionTypeRatesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<EmplPositionTypeRate> emplPositionTypeRates =((EmplPositionTypeRateFound) Scheduler.execute(query).data()).getEmplPositionTypeRates();

		return ResponseEntity.ok().body(emplPositionTypeRates);

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
	public ResponseEntity<EmplPositionTypeRate> createEmplPositionTypeRate(HttpServletRequest request) throws Exception {

		EmplPositionTypeRate emplPositionTypeRateToBeAdded = new EmplPositionTypeRate();
		try {
			emplPositionTypeRateToBeAdded = EmplPositionTypeRateMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createEmplPositionTypeRate(emplPositionTypeRateToBeAdded);

	}

	/**
	 * creates a new EmplPositionTypeRate entry in the ofbiz database
	 * 
	 * @param emplPositionTypeRateToBeAdded
	 *            the EmplPositionTypeRate thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<EmplPositionTypeRate> createEmplPositionTypeRate(@RequestBody EmplPositionTypeRate emplPositionTypeRateToBeAdded) throws Exception {

		AddEmplPositionTypeRate command = new AddEmplPositionTypeRate(emplPositionTypeRateToBeAdded);
		EmplPositionTypeRate emplPositionTypeRate = ((EmplPositionTypeRateAdded) Scheduler.execute(command).data()).getAddedEmplPositionTypeRate();
		
		if (emplPositionTypeRate != null) 
			return successful(emplPositionTypeRate);
		else 
			return conflict(null);
	}

	/**
	 * Updates the EmplPositionTypeRate with the specific Id
	 * 
	 * @param emplPositionTypeRateToBeUpdated
	 *            the EmplPositionTypeRate thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateEmplPositionTypeRate(@RequestBody EmplPositionTypeRate emplPositionTypeRateToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		emplPositionTypeRateToBeUpdated.setnull(null);

		UpdateEmplPositionTypeRate command = new UpdateEmplPositionTypeRate(emplPositionTypeRateToBeUpdated);

		try {
			if(((EmplPositionTypeRateUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{emplPositionTypeRateId}")
	public ResponseEntity<EmplPositionTypeRate> findById(@PathVariable String emplPositionTypeRateId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("emplPositionTypeRateId", emplPositionTypeRateId);
		try {

			List<EmplPositionTypeRate> foundEmplPositionTypeRate = findEmplPositionTypeRatesBy(requestParams).getBody();
			if(foundEmplPositionTypeRate.size()==1){				return successful(foundEmplPositionTypeRate.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{emplPositionTypeRateId}")
	public ResponseEntity<String> deleteEmplPositionTypeRateByIdUpdated(@PathVariable String emplPositionTypeRateId) throws Exception {
		DeleteEmplPositionTypeRate command = new DeleteEmplPositionTypeRate(emplPositionTypeRateId);

		try {
			if (((EmplPositionTypeRateDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
