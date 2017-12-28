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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	public ResponseEntity<List<SubscriptionCommEvent>> findSubscriptionCommEventsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindSubscriptionCommEventsBy query = new FindSubscriptionCommEventsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<SubscriptionCommEvent> subscriptionCommEvents =((SubscriptionCommEventFound) Scheduler.execute(query).data()).getSubscriptionCommEvents();

		return ResponseEntity.ok().body(subscriptionCommEvents);

	}

	/**
	 * creates a new SubscriptionCommEvent entry in the ofbiz database
	 * 
	 * @param subscriptionCommEventToBeAdded
	 *            the SubscriptionCommEvent thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<SubscriptionCommEvent> createSubscriptionCommEvent(@RequestBody SubscriptionCommEvent subscriptionCommEventToBeAdded) throws Exception {

		AddSubscriptionCommEvent command = new AddSubscriptionCommEvent(subscriptionCommEventToBeAdded);
		SubscriptionCommEvent subscriptionCommEvent = ((SubscriptionCommEventAdded) Scheduler.execute(command).data()).getAddedSubscriptionCommEvent();
		
		if (subscriptionCommEvent != null) 
			return successful(subscriptionCommEvent);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateSubscriptionCommEvent(@RequestBody SubscriptionCommEvent subscriptionCommEventToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		subscriptionCommEventToBeUpdated.setnull(null);

		UpdateSubscriptionCommEvent command = new UpdateSubscriptionCommEvent(subscriptionCommEventToBeUpdated);

		try {
			if(((SubscriptionCommEventUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{subscriptionCommEventId}")
	public ResponseEntity<SubscriptionCommEvent> findById(@PathVariable String subscriptionCommEventId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("subscriptionCommEventId", subscriptionCommEventId);
		try {

			List<SubscriptionCommEvent> foundSubscriptionCommEvent = findSubscriptionCommEventsBy(requestParams).getBody();
			if(foundSubscriptionCommEvent.size()==1){				return successful(foundSubscriptionCommEvent.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{subscriptionCommEventId}")
	public ResponseEntity<String> deleteSubscriptionCommEventByIdUpdated(@PathVariable String subscriptionCommEventId) throws Exception {
		DeleteSubscriptionCommEvent command = new DeleteSubscriptionCommEvent(subscriptionCommEventId);

		try {
			if (((SubscriptionCommEventDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
