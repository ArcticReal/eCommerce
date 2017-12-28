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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	public ResponseEntity<List<Subscription>> findSubscriptionsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindSubscriptionsBy query = new FindSubscriptionsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<Subscription> subscriptions =((SubscriptionFound) Scheduler.execute(query).data()).getSubscriptions();

		return ResponseEntity.ok().body(subscriptions);

	}

	/**
	 * creates a new Subscription entry in the ofbiz database
	 * 
	 * @param subscriptionToBeAdded
	 *            the Subscription thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Subscription> createSubscription(@RequestBody Subscription subscriptionToBeAdded) throws Exception {

		AddSubscription command = new AddSubscription(subscriptionToBeAdded);
		Subscription subscription = ((SubscriptionAdded) Scheduler.execute(command).data()).getAddedSubscription();
		
		if (subscription != null) 
			return successful(subscription);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateSubscription(@RequestBody Subscription subscriptionToBeUpdated,
			@PathVariable String subscriptionId) throws Exception {

		subscriptionToBeUpdated.setSubscriptionId(subscriptionId);

		UpdateSubscription command = new UpdateSubscription(subscriptionToBeUpdated);

		try {
			if(((SubscriptionUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{subscriptionId}")
	public ResponseEntity<Subscription> findById(@PathVariable String subscriptionId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("subscriptionId", subscriptionId);
		try {

			List<Subscription> foundSubscription = findSubscriptionsBy(requestParams).getBody();
			if(foundSubscription.size()==1){				return successful(foundSubscription.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{subscriptionId}")
	public ResponseEntity<String> deleteSubscriptionByIdUpdated(@PathVariable String subscriptionId) throws Exception {
		DeleteSubscription command = new DeleteSubscription(subscriptionId);

		try {
			if (((SubscriptionDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
