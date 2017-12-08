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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@RequestMapping("/subscriptionActivitys")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findSubscriptionActivitysBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindSubscriptionActivitysBy query = new FindSubscriptionActivitysBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<SubscriptionActivity> subscriptionActivitys =((SubscriptionActivityFound) Scheduler.execute(query).data()).getSubscriptionActivitys();

		if (subscriptionActivitys.size() == 1) {
			return ResponseEntity.ok().body(subscriptionActivitys.get(0));
		}

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
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createSubscriptionActivity(HttpServletRequest request) throws Exception {

		SubscriptionActivity subscriptionActivityToBeAdded = new SubscriptionActivity();
		try {
			subscriptionActivityToBeAdded = SubscriptionActivityMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
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
	public ResponseEntity<Object> createSubscriptionActivity(@RequestBody SubscriptionActivity subscriptionActivityToBeAdded) throws Exception {

		AddSubscriptionActivity command = new AddSubscriptionActivity(subscriptionActivityToBeAdded);
		SubscriptionActivity subscriptionActivity = ((SubscriptionActivityAdded) Scheduler.execute(command).data()).getAddedSubscriptionActivity();
		
		if (subscriptionActivity != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(subscriptionActivity);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("SubscriptionActivity could not be created.");
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
	@RequestMapping(method = RequestMethod.PUT, value = "/update", consumes = "application/x-www-form-urlencoded")
	public boolean updateSubscriptionActivity(HttpServletRequest request) throws Exception {

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

		SubscriptionActivity subscriptionActivityToBeUpdated = new SubscriptionActivity();

		try {
			subscriptionActivityToBeUpdated = SubscriptionActivityMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateSubscriptionActivity(subscriptionActivityToBeUpdated, subscriptionActivityToBeUpdated.getSubscriptionActivityId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateSubscriptionActivity(@RequestBody SubscriptionActivity subscriptionActivityToBeUpdated,
			@PathVariable String subscriptionActivityId) throws Exception {

		subscriptionActivityToBeUpdated.setSubscriptionActivityId(subscriptionActivityId);

		UpdateSubscriptionActivity command = new UpdateSubscriptionActivity(subscriptionActivityToBeUpdated);

		try {
			if(((SubscriptionActivityUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{subscriptionActivityId}")
	public ResponseEntity<Object> findById(@PathVariable String subscriptionActivityId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("subscriptionActivityId", subscriptionActivityId);
		try {

			Object foundSubscriptionActivity = findSubscriptionActivitysBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundSubscriptionActivity);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{subscriptionActivityId}")
	public ResponseEntity<Object> deleteSubscriptionActivityByIdUpdated(@PathVariable String subscriptionActivityId) throws Exception {
		DeleteSubscriptionActivity command = new DeleteSubscriptionActivity(subscriptionActivityId);

		try {
			if (((SubscriptionActivityDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("SubscriptionActivity could not be deleted");

	}

}
