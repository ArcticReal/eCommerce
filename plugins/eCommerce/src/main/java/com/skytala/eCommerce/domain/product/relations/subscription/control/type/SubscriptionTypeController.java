package com.skytala.eCommerce.domain.product.relations.subscription.control.type;

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
import com.skytala.eCommerce.domain.product.relations.subscription.command.type.AddSubscriptionType;
import com.skytala.eCommerce.domain.product.relations.subscription.command.type.DeleteSubscriptionType;
import com.skytala.eCommerce.domain.product.relations.subscription.command.type.UpdateSubscriptionType;
import com.skytala.eCommerce.domain.product.relations.subscription.event.type.SubscriptionTypeAdded;
import com.skytala.eCommerce.domain.product.relations.subscription.event.type.SubscriptionTypeDeleted;
import com.skytala.eCommerce.domain.product.relations.subscription.event.type.SubscriptionTypeFound;
import com.skytala.eCommerce.domain.product.relations.subscription.event.type.SubscriptionTypeUpdated;
import com.skytala.eCommerce.domain.product.relations.subscription.mapper.type.SubscriptionTypeMapper;
import com.skytala.eCommerce.domain.product.relations.subscription.model.type.SubscriptionType;
import com.skytala.eCommerce.domain.product.relations.subscription.query.type.FindSubscriptionTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@CrossOrigin
@RequestMapping("/subscriptionTypes")
public class SubscriptionTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public SubscriptionTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a SubscriptionType
	 * @return a List with the SubscriptionTypes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findSubscriptionTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindSubscriptionTypesBy query = new FindSubscriptionTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<SubscriptionType> subscriptionTypes =((SubscriptionTypeFound) Scheduler.execute(query).data()).getSubscriptionTypes();

		if (subscriptionTypes.size() == 1) {
			return ResponseEntity.ok().body(subscriptionTypes.get(0));
		}

		return ResponseEntity.ok().body(subscriptionTypes);

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
	public ResponseEntity<Object> createSubscriptionType(HttpServletRequest request) throws Exception {

		SubscriptionType subscriptionTypeToBeAdded = new SubscriptionType();
		try {
			subscriptionTypeToBeAdded = SubscriptionTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createSubscriptionType(subscriptionTypeToBeAdded);

	}

	/**
	 * creates a new SubscriptionType entry in the ofbiz database
	 * 
	 * @param subscriptionTypeToBeAdded
	 *            the SubscriptionType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createSubscriptionType(@RequestBody SubscriptionType subscriptionTypeToBeAdded) throws Exception {

		AddSubscriptionType command = new AddSubscriptionType(subscriptionTypeToBeAdded);
		SubscriptionType subscriptionType = ((SubscriptionTypeAdded) Scheduler.execute(command).data()).getAddedSubscriptionType();
		
		if (subscriptionType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(subscriptionType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("SubscriptionType could not be created.");
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
	public boolean updateSubscriptionType(HttpServletRequest request) throws Exception {

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

		SubscriptionType subscriptionTypeToBeUpdated = new SubscriptionType();

		try {
			subscriptionTypeToBeUpdated = SubscriptionTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateSubscriptionType(subscriptionTypeToBeUpdated, subscriptionTypeToBeUpdated.getSubscriptionTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the SubscriptionType with the specific Id
	 * 
	 * @param subscriptionTypeToBeUpdated
	 *            the SubscriptionType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{subscriptionTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateSubscriptionType(@RequestBody SubscriptionType subscriptionTypeToBeUpdated,
			@PathVariable String subscriptionTypeId) throws Exception {

		subscriptionTypeToBeUpdated.setSubscriptionTypeId(subscriptionTypeId);

		UpdateSubscriptionType command = new UpdateSubscriptionType(subscriptionTypeToBeUpdated);

		try {
			if(((SubscriptionTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{subscriptionTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String subscriptionTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("subscriptionTypeId", subscriptionTypeId);
		try {

			Object foundSubscriptionType = findSubscriptionTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundSubscriptionType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{subscriptionTypeId}")
	public ResponseEntity<Object> deleteSubscriptionTypeByIdUpdated(@PathVariable String subscriptionTypeId) throws Exception {
		DeleteSubscriptionType command = new DeleteSubscriptionType(subscriptionTypeId);

		try {
			if (((SubscriptionTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("SubscriptionType could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/subscriptionType/\" plus one of the following: "
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
