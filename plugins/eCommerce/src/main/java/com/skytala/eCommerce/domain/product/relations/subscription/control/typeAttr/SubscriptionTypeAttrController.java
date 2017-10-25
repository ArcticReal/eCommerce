package com.skytala.eCommerce.domain.product.relations.subscription.control.typeAttr;

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
import com.skytala.eCommerce.domain.product.relations.subscription.command.typeAttr.AddSubscriptionTypeAttr;
import com.skytala.eCommerce.domain.product.relations.subscription.command.typeAttr.DeleteSubscriptionTypeAttr;
import com.skytala.eCommerce.domain.product.relations.subscription.command.typeAttr.UpdateSubscriptionTypeAttr;
import com.skytala.eCommerce.domain.product.relations.subscription.event.typeAttr.SubscriptionTypeAttrAdded;
import com.skytala.eCommerce.domain.product.relations.subscription.event.typeAttr.SubscriptionTypeAttrDeleted;
import com.skytala.eCommerce.domain.product.relations.subscription.event.typeAttr.SubscriptionTypeAttrFound;
import com.skytala.eCommerce.domain.product.relations.subscription.event.typeAttr.SubscriptionTypeAttrUpdated;
import com.skytala.eCommerce.domain.product.relations.subscription.mapper.typeAttr.SubscriptionTypeAttrMapper;
import com.skytala.eCommerce.domain.product.relations.subscription.model.typeAttr.SubscriptionTypeAttr;
import com.skytala.eCommerce.domain.product.relations.subscription.query.typeAttr.FindSubscriptionTypeAttrsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/subscriptionTypeAttrs")
public class SubscriptionTypeAttrController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public SubscriptionTypeAttrController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a SubscriptionTypeAttr
	 * @return a List with the SubscriptionTypeAttrs
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findSubscriptionTypeAttrsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindSubscriptionTypeAttrsBy query = new FindSubscriptionTypeAttrsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<SubscriptionTypeAttr> subscriptionTypeAttrs =((SubscriptionTypeAttrFound) Scheduler.execute(query).data()).getSubscriptionTypeAttrs();

		if (subscriptionTypeAttrs.size() == 1) {
			return ResponseEntity.ok().body(subscriptionTypeAttrs.get(0));
		}

		return ResponseEntity.ok().body(subscriptionTypeAttrs);

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
	public ResponseEntity<Object> createSubscriptionTypeAttr(HttpServletRequest request) throws Exception {

		SubscriptionTypeAttr subscriptionTypeAttrToBeAdded = new SubscriptionTypeAttr();
		try {
			subscriptionTypeAttrToBeAdded = SubscriptionTypeAttrMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createSubscriptionTypeAttr(subscriptionTypeAttrToBeAdded);

	}

	/**
	 * creates a new SubscriptionTypeAttr entry in the ofbiz database
	 * 
	 * @param subscriptionTypeAttrToBeAdded
	 *            the SubscriptionTypeAttr thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createSubscriptionTypeAttr(@RequestBody SubscriptionTypeAttr subscriptionTypeAttrToBeAdded) throws Exception {

		AddSubscriptionTypeAttr command = new AddSubscriptionTypeAttr(subscriptionTypeAttrToBeAdded);
		SubscriptionTypeAttr subscriptionTypeAttr = ((SubscriptionTypeAttrAdded) Scheduler.execute(command).data()).getAddedSubscriptionTypeAttr();
		
		if (subscriptionTypeAttr != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(subscriptionTypeAttr);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("SubscriptionTypeAttr could not be created.");
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
	public boolean updateSubscriptionTypeAttr(HttpServletRequest request) throws Exception {

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

		SubscriptionTypeAttr subscriptionTypeAttrToBeUpdated = new SubscriptionTypeAttr();

		try {
			subscriptionTypeAttrToBeUpdated = SubscriptionTypeAttrMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateSubscriptionTypeAttr(subscriptionTypeAttrToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the SubscriptionTypeAttr with the specific Id
	 * 
	 * @param subscriptionTypeAttrToBeUpdated
	 *            the SubscriptionTypeAttr thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateSubscriptionTypeAttr(@RequestBody SubscriptionTypeAttr subscriptionTypeAttrToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		subscriptionTypeAttrToBeUpdated.setnull(null);

		UpdateSubscriptionTypeAttr command = new UpdateSubscriptionTypeAttr(subscriptionTypeAttrToBeUpdated);

		try {
			if(((SubscriptionTypeAttrUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{subscriptionTypeAttrId}")
	public ResponseEntity<Object> findById(@PathVariable String subscriptionTypeAttrId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("subscriptionTypeAttrId", subscriptionTypeAttrId);
		try {

			Object foundSubscriptionTypeAttr = findSubscriptionTypeAttrsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundSubscriptionTypeAttr);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{subscriptionTypeAttrId}")
	public ResponseEntity<Object> deleteSubscriptionTypeAttrByIdUpdated(@PathVariable String subscriptionTypeAttrId) throws Exception {
		DeleteSubscriptionTypeAttr command = new DeleteSubscriptionTypeAttr(subscriptionTypeAttrId);

		try {
			if (((SubscriptionTypeAttrDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("SubscriptionTypeAttr could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/subscriptionTypeAttr/\" plus one of the following: "
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
