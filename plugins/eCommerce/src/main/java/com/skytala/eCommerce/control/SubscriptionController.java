package com.skytala.eCommerce.control;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Splitter;
import com.skytala.eCommerce.command.AddSubscription;
import com.skytala.eCommerce.command.DeleteSubscription;
import com.skytala.eCommerce.command.UpdateSubscription;
import com.skytala.eCommerce.entity.Subscription;
import com.skytala.eCommerce.entity.SubscriptionMapper;
import com.skytala.eCommerce.event.SubscriptionAdded;
import com.skytala.eCommerce.event.SubscriptionDeleted;
import com.skytala.eCommerce.event.SubscriptionFound;
import com.skytala.eCommerce.event.SubscriptionUpdated;
import com.skytala.eCommerce.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.query.FindSubscriptionsBy;

@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {

	private static int requestTicketId = 0;
	private static Map<Integer, Event> commandReturnVal = new HashMap<>();
	private static Map<Integer, List<Subscription>> queryReturnVal = new HashMap<>();
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
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findSubscriptionsBy(
			@RequestParam(required = false) Map<String, String> allRequestParams) {

		FindSubscriptionsBy query = new FindSubscriptionsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		int usedTicketId;

		synchronized (SubscriptionController.class) {
			usedTicketId = requestTicketId;
			requestTicketId++;
		}
		Broker.instance().subscribe(SubscriptionFound.class,
				event -> sendSubscriptionsFoundMessage(((SubscriptionFound) event).getSubscriptions(), usedTicketId));

		query.execute();

		while (!queryReturnVal.containsKey(usedTicketId)) {

		}

		List<Subscription> subscriptions = queryReturnVal.remove(usedTicketId);

		if (subscriptions.size() == 1) {
			return ResponseEntity.ok().body(subscriptions.get(0));
		}
		return ResponseEntity.ok().body(subscriptions);

	}

	public void sendSubscriptionsFoundMessage(List<Subscription> subscriptions, int usedTicketId) {
		queryReturnVal.put(usedTicketId, subscriptions);
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
	public ResponseEntity<Object> createSubscription(HttpServletRequest request) {

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
	public ResponseEntity<Object> createSubscription(@RequestBody Subscription subscriptionToBeAdded) {

		AddSubscription com = new AddSubscription(subscriptionToBeAdded);
		int usedTicketId;

		synchronized (SubscriptionController.class) {

			usedTicketId = requestTicketId;
			requestTicketId++;
		}
		Broker.instance().subscribe(SubscriptionAdded.class,
				event -> sendSubscriptionChangedMessage(((SubscriptionAdded) event), usedTicketId));

		try {
			Scheduler.instance().schedule(com).executeNext();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An Error ocuured while processing Command!");
		}
		while (!commandReturnVal.containsKey(usedTicketId)) {
		}

		SubscriptionAdded event = (SubscriptionAdded) commandReturnVal.remove(usedTicketId);
		if (event.isSuccess()) {

			return ResponseEntity.status(HttpStatus.CREATED).body(event.getAddedSubscription());

		}
		return ResponseEntity.status(HttpStatus.CONFLICT).body("Subscription could not be created.");

	}

	/**
	 * this method will only be called by Springs DispatcherServlet
	 * 
	 * @deprecated
	 * @param request
	 *            HttpServletRequest object
	 * @return true on success, false on fail
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/update", consumes = "application/x-www-form-urlencoded")
	public boolean updateSubscription(HttpServletRequest request) {

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
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{subscriptionId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateSubscription(@RequestBody Subscription subscriptionToBeUpdated,
			@PathVariable String subscriptionId) {

		subscriptionToBeUpdated.setSubscriptionId(subscriptionId);

		UpdateSubscription com = new UpdateSubscription(subscriptionToBeUpdated);

		int usedTicketId;

		synchronized (SubscriptionController.class) {

			usedTicketId = requestTicketId;
			requestTicketId++;
		}
		Broker.instance().subscribe(SubscriptionUpdated.class,
				event -> sendSubscriptionChangedMessage(((SubscriptionUpdated) event), usedTicketId));

		try {
			Scheduler.instance().schedule(com).executeNext();
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An internal error occured");
		}
		while (!commandReturnVal.containsKey(usedTicketId)) {
		}

		if (((SubscriptionUpdated) commandReturnVal.remove(usedTicketId)).isSuccess()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);

		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	/**
	 * removes a Subscription from the database
	 * 
	 * @deprecated
	 * @param subscriptionId:
	 *            the id of the Subscription thats to be removed
	 * 
	 * @return true on success; false on fail
	 * 
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "/removeById")
	public boolean deletesubscriptionById(@RequestParam(value = "subscriptionId") String subscriptionId) {

		DeleteSubscription com = new DeleteSubscription(subscriptionId);

		int usedTicketId;

		synchronized (SubscriptionController.class) {

			usedTicketId = requestTicketId;
			requestTicketId++;
		}
		Broker.instance().subscribe(SubscriptionDeleted.class,
				event -> sendSubscriptionChangedMessage(((SubscriptionDeleted) event), usedTicketId));

		try {
			Scheduler.instance().schedule(com).executeNext();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return false;
		}
		while (!commandReturnVal.containsKey(usedTicketId)) {
		}

		return ((SubscriptionDeleted) commandReturnVal.remove(usedTicketId)).isSuccess();
	}

	public void sendSubscriptionChangedMessage(Event event, int usedTicketId) {
		commandReturnVal.put(usedTicketId, event);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{subscriptionId}")
	public ResponseEntity<Object> findById(@PathVariable String subscriptionId) {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("subscriptionId", subscriptionId);
		try {

			Object foundSubscription = findSubscriptionsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundSubscription);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{subscriptionId}")
	public ResponseEntity<Object> deleteSubscriptionByIdUpdated(@PathVariable String subscriptionId) {
		DeleteSubscription com = new DeleteSubscription(subscriptionId);

		int usedTicketId;

		synchronized (SubscriptionController.class) {

			usedTicketId = requestTicketId;
			requestTicketId++;
		}
		Broker.instance().subscribe(SubscriptionDeleted.class,
				event -> sendSubscriptionChangedMessage(((SubscriptionDeleted) event), usedTicketId));

		try {
			Scheduler.instance().schedule(com).executeNext();
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An Error ocuured while processing Command!");
		}
		while (!commandReturnVal.containsKey(usedTicketId)) {
		}

		if (((SubscriptionDeleted) commandReturnVal.remove(usedTicketId)).isSuccess()) {

			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);

		}
		return ResponseEntity.status(HttpStatus.CONFLICT).body("Subscription could not be deleted");

	}

	@RequestMapping(value = (" ** "))
	public ResponseEntity<Object> returnErrorPage(HttpServletRequest request) {

		String usedUri = request.getRequestURI();
		String[] splittedString = usedUri.split("/");

		String usedRequest = splittedString[splittedString.length - 1];

		if (validRequests.containsKey(usedRequest)) {
			String returnVal = "Error: request method " + request.getMethod() + " not allowed for \"" + usedUri
					+ "\"!\n" + "Please use " + validRequests.get(usedRequest) + "!";

			return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(returnVal);
		}

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/subscription/\" plus one of the following: "
				+ "";

		Set<String> keySet = validRequests.keySet();
		Iterator<String> it = keySet.iterator();

		while (it.hasNext()) {
			returnVal += "\"" + it.next() + "\"";
			if (it.hasNext())
				returnVal += ", ";
		}

		returnVal += "!";

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnVal);

	}
}
