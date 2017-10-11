package com.skytala.eCommerce.domain.product.relations.subscriptionResource;

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
import com.skytala.eCommerce.domain.product.relations.subscriptionResource.command.AddSubscriptionResource;
import com.skytala.eCommerce.domain.product.relations.subscriptionResource.command.DeleteSubscriptionResource;
import com.skytala.eCommerce.domain.product.relations.subscriptionResource.command.UpdateSubscriptionResource;
import com.skytala.eCommerce.domain.product.relations.subscriptionResource.event.SubscriptionResourceAdded;
import com.skytala.eCommerce.domain.product.relations.subscriptionResource.event.SubscriptionResourceDeleted;
import com.skytala.eCommerce.domain.product.relations.subscriptionResource.event.SubscriptionResourceFound;
import com.skytala.eCommerce.domain.product.relations.subscriptionResource.event.SubscriptionResourceUpdated;
import com.skytala.eCommerce.domain.product.relations.subscriptionResource.mapper.SubscriptionResourceMapper;
import com.skytala.eCommerce.domain.product.relations.subscriptionResource.model.SubscriptionResource;
import com.skytala.eCommerce.domain.product.relations.subscriptionResource.query.FindSubscriptionResourcesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/subscriptionResources")
public class SubscriptionResourceController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public SubscriptionResourceController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a SubscriptionResource
	 * @return a List with the SubscriptionResources
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findSubscriptionResourcesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindSubscriptionResourcesBy query = new FindSubscriptionResourcesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<SubscriptionResource> subscriptionResources =((SubscriptionResourceFound) Scheduler.execute(query).data()).getSubscriptionResources();

		if (subscriptionResources.size() == 1) {
			return ResponseEntity.ok().body(subscriptionResources.get(0));
		}

		return ResponseEntity.ok().body(subscriptionResources);

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
	public ResponseEntity<Object> createSubscriptionResource(HttpServletRequest request) throws Exception {

		SubscriptionResource subscriptionResourceToBeAdded = new SubscriptionResource();
		try {
			subscriptionResourceToBeAdded = SubscriptionResourceMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createSubscriptionResource(subscriptionResourceToBeAdded);

	}

	/**
	 * creates a new SubscriptionResource entry in the ofbiz database
	 * 
	 * @param subscriptionResourceToBeAdded
	 *            the SubscriptionResource thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createSubscriptionResource(@RequestBody SubscriptionResource subscriptionResourceToBeAdded) throws Exception {

		AddSubscriptionResource command = new AddSubscriptionResource(subscriptionResourceToBeAdded);
		SubscriptionResource subscriptionResource = ((SubscriptionResourceAdded) Scheduler.execute(command).data()).getAddedSubscriptionResource();
		
		if (subscriptionResource != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(subscriptionResource);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("SubscriptionResource could not be created.");
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
	public boolean updateSubscriptionResource(HttpServletRequest request) throws Exception {

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

		SubscriptionResource subscriptionResourceToBeUpdated = new SubscriptionResource();

		try {
			subscriptionResourceToBeUpdated = SubscriptionResourceMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateSubscriptionResource(subscriptionResourceToBeUpdated, subscriptionResourceToBeUpdated.getSubscriptionResourceId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the SubscriptionResource with the specific Id
	 * 
	 * @param subscriptionResourceToBeUpdated
	 *            the SubscriptionResource thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{subscriptionResourceId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateSubscriptionResource(@RequestBody SubscriptionResource subscriptionResourceToBeUpdated,
			@PathVariable String subscriptionResourceId) throws Exception {

		subscriptionResourceToBeUpdated.setSubscriptionResourceId(subscriptionResourceId);

		UpdateSubscriptionResource command = new UpdateSubscriptionResource(subscriptionResourceToBeUpdated);

		try {
			if(((SubscriptionResourceUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{subscriptionResourceId}")
	public ResponseEntity<Object> findById(@PathVariable String subscriptionResourceId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("subscriptionResourceId", subscriptionResourceId);
		try {

			Object foundSubscriptionResource = findSubscriptionResourcesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundSubscriptionResource);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{subscriptionResourceId}")
	public ResponseEntity<Object> deleteSubscriptionResourceByIdUpdated(@PathVariable String subscriptionResourceId) throws Exception {
		DeleteSubscriptionResource command = new DeleteSubscriptionResource(subscriptionResourceId);

		try {
			if (((SubscriptionResourceDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("SubscriptionResource could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/subscriptionResource/\" plus one of the following: "
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
