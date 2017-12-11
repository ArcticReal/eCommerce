package com.skytala.eCommerce.domain.product.relations.subscription.control.commEvent;

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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Splitter;
import com.skytala.eCommerce.domain.product.relations.subscription.command.commEvent.AddSubscriptionCommEvent;
import com.skytala.eCommerce.domain.product.relations.subscription.command.commEvent.DeleteSubscriptionCommEvent;
import com.skytala.eCommerce.domain.product.relations.subscription.command.commEvent.UpdateSubscriptionCommEvent;
import com.skytala.eCommerce.domain.product.relations.subscription.event.commEvent.SubscriptionCommEventAdded;
import com.skytala.eCommerce.domain.product.relations.subscription.event.commEvent.SubscriptionCommEventDeleted;
import com.skytala.eCommerce.domain.product.relations.subscription.event.commEvent.SubscriptionCommEventFound;
import com.skytala.eCommerce.domain.product.relations.subscription.event.commEvent.SubscriptionCommEventUpdated;
import com.skytala.eCommerce.domain.product.relations.subscription.mapper.commEvent.SubscriptionCommEventMapper;
import com.skytala.eCommerce.domain.product.relations.subscription.model.commEvent.SubscriptionCommEvent;
import com.skytala.eCommerce.domain.product.relations.subscription.query.commEvent.FindSubscriptionCommEventsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/product/subscription/subscriptionCommEvents")
public class SubscriptionCommEventController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public SubscriptionCommEventController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a SubscriptionCommEvent
	 * @return a List with the SubscriptionCommEvents
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findSubscriptionCommEventsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindSubscriptionCommEventsBy query = new FindSubscriptionCommEventsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<SubscriptionCommEvent> subscriptionCommEvents =((SubscriptionCommEventFound) Scheduler.execute(query).data()).getSubscriptionCommEvents();

		if (subscriptionCommEvents.size() == 1) {
			return ResponseEntity.ok().body(subscriptionCommEvents.get(0));
		}

		return ResponseEntity.ok().body(subscriptionCommEvents);

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
	public ResponseEntity<Object> createSubscriptionCommEvent(HttpServletRequest request) throws Exception {

		SubscriptionCommEvent subscriptionCommEventToBeAdded = new SubscriptionCommEvent();
		try {
			subscriptionCommEventToBeAdded = SubscriptionCommEventMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createSubscriptionCommEvent(subscriptionCommEventToBeAdded);

	}

	/**
	 * creates a new SubscriptionCommEvent entry in the ofbiz database
	 * 
	 * @param subscriptionCommEventToBeAdded
	 *            the SubscriptionCommEvent thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createSubscriptionCommEvent(@RequestBody SubscriptionCommEvent subscriptionCommEventToBeAdded) throws Exception {

		AddSubscriptionCommEvent command = new AddSubscriptionCommEvent(subscriptionCommEventToBeAdded);
		SubscriptionCommEvent subscriptionCommEvent = ((SubscriptionCommEventAdded) Scheduler.execute(command).data()).getAddedSubscriptionCommEvent();
		
		if (subscriptionCommEvent != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(subscriptionCommEvent);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("SubscriptionCommEvent could not be created.");
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
	public boolean updateSubscriptionCommEvent(HttpServletRequest request) throws Exception {

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

		SubscriptionCommEvent subscriptionCommEventToBeUpdated = new SubscriptionCommEvent();

		try {
			subscriptionCommEventToBeUpdated = SubscriptionCommEventMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateSubscriptionCommEvent(subscriptionCommEventToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the SubscriptionCommEvent with the specific Id
	 * 
	 * @param subscriptionCommEventToBeUpdated
	 *            the SubscriptionCommEvent thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateSubscriptionCommEvent(@RequestBody SubscriptionCommEvent subscriptionCommEventToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		subscriptionCommEventToBeUpdated.setnull(null);

		UpdateSubscriptionCommEvent command = new UpdateSubscriptionCommEvent(subscriptionCommEventToBeUpdated);

		try {
			if(((SubscriptionCommEventUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{subscriptionCommEventId}")
	public ResponseEntity<Object> findById(@PathVariable String subscriptionCommEventId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("subscriptionCommEventId", subscriptionCommEventId);
		try {

			Object foundSubscriptionCommEvent = findSubscriptionCommEventsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundSubscriptionCommEvent);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{subscriptionCommEventId}")
	public ResponseEntity<Object> deleteSubscriptionCommEventByIdUpdated(@PathVariable String subscriptionCommEventId) throws Exception {
		DeleteSubscriptionCommEvent command = new DeleteSubscriptionCommEvent(subscriptionCommEventId);

		try {
			if (((SubscriptionCommEventDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("SubscriptionCommEvent could not be deleted");

	}

}
