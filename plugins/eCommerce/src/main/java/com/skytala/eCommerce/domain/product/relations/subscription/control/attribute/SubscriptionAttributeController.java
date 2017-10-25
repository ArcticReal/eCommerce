package com.skytala.eCommerce.domain.product.relations.subscription.control.attribute;

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
import com.skytala.eCommerce.domain.product.relations.subscription.command.attribute.AddSubscriptionAttribute;
import com.skytala.eCommerce.domain.product.relations.subscription.command.attribute.DeleteSubscriptionAttribute;
import com.skytala.eCommerce.domain.product.relations.subscription.command.attribute.UpdateSubscriptionAttribute;
import com.skytala.eCommerce.domain.product.relations.subscription.event.attribute.SubscriptionAttributeAdded;
import com.skytala.eCommerce.domain.product.relations.subscription.event.attribute.SubscriptionAttributeDeleted;
import com.skytala.eCommerce.domain.product.relations.subscription.event.attribute.SubscriptionAttributeFound;
import com.skytala.eCommerce.domain.product.relations.subscription.event.attribute.SubscriptionAttributeUpdated;
import com.skytala.eCommerce.domain.product.relations.subscription.mapper.attribute.SubscriptionAttributeMapper;
import com.skytala.eCommerce.domain.product.relations.subscription.model.attribute.SubscriptionAttribute;
import com.skytala.eCommerce.domain.product.relations.subscription.query.attribute.FindSubscriptionAttributesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/subscriptionAttributes")
public class SubscriptionAttributeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public SubscriptionAttributeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a SubscriptionAttribute
	 * @return a List with the SubscriptionAttributes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findSubscriptionAttributesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindSubscriptionAttributesBy query = new FindSubscriptionAttributesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<SubscriptionAttribute> subscriptionAttributes =((SubscriptionAttributeFound) Scheduler.execute(query).data()).getSubscriptionAttributes();

		if (subscriptionAttributes.size() == 1) {
			return ResponseEntity.ok().body(subscriptionAttributes.get(0));
		}

		return ResponseEntity.ok().body(subscriptionAttributes);

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
	public ResponseEntity<Object> createSubscriptionAttribute(HttpServletRequest request) throws Exception {

		SubscriptionAttribute subscriptionAttributeToBeAdded = new SubscriptionAttribute();
		try {
			subscriptionAttributeToBeAdded = SubscriptionAttributeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createSubscriptionAttribute(subscriptionAttributeToBeAdded);

	}

	/**
	 * creates a new SubscriptionAttribute entry in the ofbiz database
	 * 
	 * @param subscriptionAttributeToBeAdded
	 *            the SubscriptionAttribute thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createSubscriptionAttribute(@RequestBody SubscriptionAttribute subscriptionAttributeToBeAdded) throws Exception {

		AddSubscriptionAttribute command = new AddSubscriptionAttribute(subscriptionAttributeToBeAdded);
		SubscriptionAttribute subscriptionAttribute = ((SubscriptionAttributeAdded) Scheduler.execute(command).data()).getAddedSubscriptionAttribute();
		
		if (subscriptionAttribute != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(subscriptionAttribute);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("SubscriptionAttribute could not be created.");
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
	public boolean updateSubscriptionAttribute(HttpServletRequest request) throws Exception {

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

		SubscriptionAttribute subscriptionAttributeToBeUpdated = new SubscriptionAttribute();

		try {
			subscriptionAttributeToBeUpdated = SubscriptionAttributeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateSubscriptionAttribute(subscriptionAttributeToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the SubscriptionAttribute with the specific Id
	 * 
	 * @param subscriptionAttributeToBeUpdated
	 *            the SubscriptionAttribute thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateSubscriptionAttribute(@RequestBody SubscriptionAttribute subscriptionAttributeToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		subscriptionAttributeToBeUpdated.setnull(null);

		UpdateSubscriptionAttribute command = new UpdateSubscriptionAttribute(subscriptionAttributeToBeUpdated);

		try {
			if(((SubscriptionAttributeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{subscriptionAttributeId}")
	public ResponseEntity<Object> findById(@PathVariable String subscriptionAttributeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("subscriptionAttributeId", subscriptionAttributeId);
		try {

			Object foundSubscriptionAttribute = findSubscriptionAttributesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundSubscriptionAttribute);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{subscriptionAttributeId}")
	public ResponseEntity<Object> deleteSubscriptionAttributeByIdUpdated(@PathVariable String subscriptionAttributeId) throws Exception {
		DeleteSubscriptionAttribute command = new DeleteSubscriptionAttribute(subscriptionAttributeId);

		try {
			if (((SubscriptionAttributeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("SubscriptionAttribute could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/subscriptionAttribute/\" plus one of the following: "
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
