package com.skytala.eCommerce.domain.product.relations.subscription.control.activity;

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
import com.skytala.eCommerce.domain.product.relations.subscription.command.activity.AddSubscriptionActivity;
import com.skytala.eCommerce.domain.product.relations.subscription.command.activity.DeleteSubscriptionActivity;
import com.skytala.eCommerce.domain.product.relations.subscription.command.activity.UpdateSubscriptionActivity;
import com.skytala.eCommerce.domain.product.relations.subscription.event.activity.SubscriptionActivityAdded;
import com.skytala.eCommerce.domain.product.relations.subscription.event.activity.SubscriptionActivityDeleted;
import com.skytala.eCommerce.domain.product.relations.subscription.event.activity.SubscriptionActivityFound;
import com.skytala.eCommerce.domain.product.relations.subscription.event.activity.SubscriptionActivityUpdated;
import com.skytala.eCommerce.domain.product.relations.subscription.mapper.activity.SubscriptionActivityMapper;
import com.skytala.eCommerce.domain.product.relations.subscription.model.activity.SubscriptionActivity;
import com.skytala.eCommerce.domain.product.relations.subscription.query.activity.FindSubscriptionActivitysBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/subscription/subscriptionActivitys")
public class SubscriptionActivityController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public SubscriptionActivityController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a SubscriptionActivity
	 * @return a List with the SubscriptionActivitys
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<SubscriptionActivity>> findSubscriptionActivitysBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindSubscriptionActivitysBy query = new FindSubscriptionActivitysBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<SubscriptionActivity> subscriptionActivitys =((SubscriptionActivityFound) Scheduler.execute(query).data()).getSubscriptionActivitys();

		return ResponseEntity.ok().body(subscriptionActivitys);

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
	public ResponseEntity<SubscriptionActivity> createSubscriptionActivity(HttpServletRequest request) throws Exception {

		SubscriptionActivity subscriptionActivityToBeAdded = new SubscriptionActivity();
		try {
			subscriptionActivityToBeAdded = SubscriptionActivityMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createSubscriptionActivity(subscriptionActivityToBeAdded);

	}

	/**
	 * creates a new SubscriptionActivity entry in the ofbiz database
	 * 
	 * @param subscriptionActivityToBeAdded
	 *            the SubscriptionActivity thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<SubscriptionActivity> createSubscriptionActivity(@RequestBody SubscriptionActivity subscriptionActivityToBeAdded) throws Exception {

		AddSubscriptionActivity command = new AddSubscriptionActivity(subscriptionActivityToBeAdded);
		SubscriptionActivity subscriptionActivity = ((SubscriptionActivityAdded) Scheduler.execute(command).data()).getAddedSubscriptionActivity();
		
		if (subscriptionActivity != null) 
			return successful(subscriptionActivity);
		else 
			return conflict(null);
	}

	/**
	 * Updates the SubscriptionActivity with the specific Id
	 * 
	 * @param subscriptionActivityToBeUpdated
	 *            the SubscriptionActivity thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{subscriptionActivityId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateSubscriptionActivity(@RequestBody SubscriptionActivity subscriptionActivityToBeUpdated,
			@PathVariable String subscriptionActivityId) throws Exception {

		subscriptionActivityToBeUpdated.setSubscriptionActivityId(subscriptionActivityId);

		UpdateSubscriptionActivity command = new UpdateSubscriptionActivity(subscriptionActivityToBeUpdated);

		try {
			if(((SubscriptionActivityUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{subscriptionActivityId}")
	public ResponseEntity<SubscriptionActivity> findById(@PathVariable String subscriptionActivityId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("subscriptionActivityId", subscriptionActivityId);
		try {

			List<SubscriptionActivity> foundSubscriptionActivity = findSubscriptionActivitysBy(requestParams).getBody();
			if(foundSubscriptionActivity.size()==1){				return successful(foundSubscriptionActivity.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{subscriptionActivityId}")
	public ResponseEntity<String> deleteSubscriptionActivityByIdUpdated(@PathVariable String subscriptionActivityId) throws Exception {
		DeleteSubscriptionActivity command = new DeleteSubscriptionActivity(subscriptionActivityId);

		try {
			if (((SubscriptionActivityDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
