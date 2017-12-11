package com.skytala.eCommerce.domain.product.relations.subscription;

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
import com.skytala.eCommerce.domain.product.relations.subscription.command.AddSubscription;
import com.skytala.eCommerce.domain.product.relations.subscription.command.DeleteSubscription;
import com.skytala.eCommerce.domain.product.relations.subscription.command.UpdateSubscription;
import com.skytala.eCommerce.domain.product.relations.subscription.event.SubscriptionAdded;
import com.skytala.eCommerce.domain.product.relations.subscription.event.SubscriptionDeleted;
import com.skytala.eCommerce.domain.product.relations.subscription.event.SubscriptionFound;
import com.skytala.eCommerce.domain.product.relations.subscription.event.SubscriptionUpdated;
import com.skytala.eCommerce.domain.product.relations.subscription.mapper.SubscriptionMapper;
import com.skytala.eCommerce.domain.product.relations.subscription.model.Subscription;
import com.skytala.eCommerce.domain.product.relations.subscription.query.FindSubscriptionsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/product/subscriptions")
public class SubscriptionController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public SubscriptionController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a Subscription
	 * @return a List with the Subscriptions
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findSubscriptionsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindSubscriptionsBy query = new FindSubscriptionsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<Subscription> subscriptions =((SubscriptionFound) Scheduler.execute(query).data()).getSubscriptions();

		if (subscriptions.size() == 1) {
			return ResponseEntity.ok().body(subscriptions.get(0));
		}

		return ResponseEntity.ok().body(subscriptions);

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
	public ResponseEntity<Object> createSubscription(HttpServletRequest request) throws Exception {

		Subscription subscriptionToBeAdded = new Subscription();
		try {
			subscriptionToBeAdded = SubscriptionMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createSubscription(subscriptionToBeAdded);

	}

	/**
	 * creates a new Subscription entry in the ofbiz database
	 * 
	 * @param subscriptionToBeAdded
	 *            the Subscription thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createSubscription(@RequestBody Subscription subscriptionToBeAdded) throws Exception {

		AddSubscription command = new AddSubscription(subscriptionToBeAdded);
		Subscription subscription = ((SubscriptionAdded) Scheduler.execute(command).data()).getAddedSubscription();
		
		if (subscription != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(subscription);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("Subscription could not be created.");
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
	public boolean updateSubscription(HttpServletRequest request) throws Exception {

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

		Subscription subscriptionToBeUpdated = new Subscription();

		try {
			subscriptionToBeUpdated = SubscriptionMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateSubscription(subscriptionToBeUpdated, subscriptionToBeUpdated.getSubscriptionId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the Subscription with the specific Id
	 * 
	 * @param subscriptionToBeUpdated
	 *            the Subscription thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{subscriptionId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateSubscription(@RequestBody Subscription subscriptionToBeUpdated,
			@PathVariable String subscriptionId) throws Exception {

		subscriptionToBeUpdated.setSubscriptionId(subscriptionId);

		UpdateSubscription command = new UpdateSubscription(subscriptionToBeUpdated);

		try {
			if(((SubscriptionUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{subscriptionId}")
	public ResponseEntity<Object> findById(@PathVariable String subscriptionId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("subscriptionId", subscriptionId);
		try {

			Object foundSubscription = findSubscriptionsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundSubscription);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{subscriptionId}")
	public ResponseEntity<Object> deleteSubscriptionByIdUpdated(@PathVariable String subscriptionId) throws Exception {
		DeleteSubscription command = new DeleteSubscription(subscriptionId);

		try {
			if (((SubscriptionDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("Subscription could not be deleted");

	}

}
