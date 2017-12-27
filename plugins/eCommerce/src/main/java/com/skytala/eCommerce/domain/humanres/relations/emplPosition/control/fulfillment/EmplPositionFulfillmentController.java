package com.skytala.eCommerce.domain.humanres.relations.emplPosition.control.fulfillment;

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
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.command.fulfillment.AddEmplPositionFulfillment;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.command.fulfillment.DeleteEmplPositionFulfillment;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.command.fulfillment.UpdateEmplPositionFulfillment;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.event.fulfillment.EmplPositionFulfillmentAdded;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.event.fulfillment.EmplPositionFulfillmentDeleted;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.event.fulfillment.EmplPositionFulfillmentFound;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.event.fulfillment.EmplPositionFulfillmentUpdated;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.mapper.fulfillment.EmplPositionFulfillmentMapper;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.model.fulfillment.EmplPositionFulfillment;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.query.fulfillment.FindEmplPositionFulfillmentsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/humanres/emplPosition/emplPositionFulfillments")
public class EmplPositionFulfillmentController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public EmplPositionFulfillmentController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a EmplPositionFulfillment
	 * @return a List with the EmplPositionFulfillments
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<EmplPositionFulfillment>> findEmplPositionFulfillmentsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindEmplPositionFulfillmentsBy query = new FindEmplPositionFulfillmentsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<EmplPositionFulfillment> emplPositionFulfillments =((EmplPositionFulfillmentFound) Scheduler.execute(query).data()).getEmplPositionFulfillments();

		return ResponseEntity.ok().body(emplPositionFulfillments);

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
	public ResponseEntity<EmplPositionFulfillment> createEmplPositionFulfillment(HttpServletRequest request) throws Exception {

		EmplPositionFulfillment emplPositionFulfillmentToBeAdded = new EmplPositionFulfillment();
		try {
			emplPositionFulfillmentToBeAdded = EmplPositionFulfillmentMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createEmplPositionFulfillment(emplPositionFulfillmentToBeAdded);

	}

	/**
	 * creates a new EmplPositionFulfillment entry in the ofbiz database
	 * 
	 * @param emplPositionFulfillmentToBeAdded
	 *            the EmplPositionFulfillment thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<EmplPositionFulfillment> createEmplPositionFulfillment(@RequestBody EmplPositionFulfillment emplPositionFulfillmentToBeAdded) throws Exception {

		AddEmplPositionFulfillment command = new AddEmplPositionFulfillment(emplPositionFulfillmentToBeAdded);
		EmplPositionFulfillment emplPositionFulfillment = ((EmplPositionFulfillmentAdded) Scheduler.execute(command).data()).getAddedEmplPositionFulfillment();
		
		if (emplPositionFulfillment != null) 
			return successful(emplPositionFulfillment);
		else 
			return conflict(null);
	}

	/**
	 * Updates the EmplPositionFulfillment with the specific Id
	 * 
	 * @param emplPositionFulfillmentToBeUpdated
	 *            the EmplPositionFulfillment thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateEmplPositionFulfillment(@RequestBody EmplPositionFulfillment emplPositionFulfillmentToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		emplPositionFulfillmentToBeUpdated.setnull(null);

		UpdateEmplPositionFulfillment command = new UpdateEmplPositionFulfillment(emplPositionFulfillmentToBeUpdated);

		try {
			if(((EmplPositionFulfillmentUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{emplPositionFulfillmentId}")
	public ResponseEntity<EmplPositionFulfillment> findById(@PathVariable String emplPositionFulfillmentId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("emplPositionFulfillmentId", emplPositionFulfillmentId);
		try {

			List<EmplPositionFulfillment> foundEmplPositionFulfillment = findEmplPositionFulfillmentsBy(requestParams).getBody();
			if(foundEmplPositionFulfillment.size()==1){				return successful(foundEmplPositionFulfillment.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{emplPositionFulfillmentId}")
	public ResponseEntity<String> deleteEmplPositionFulfillmentByIdUpdated(@PathVariable String emplPositionFulfillmentId) throws Exception {
		DeleteEmplPositionFulfillment command = new DeleteEmplPositionFulfillment(emplPositionFulfillmentId);

		try {
			if (((EmplPositionFulfillmentDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
