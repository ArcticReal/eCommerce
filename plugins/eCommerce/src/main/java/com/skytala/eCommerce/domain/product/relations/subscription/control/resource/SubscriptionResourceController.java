package com.skytala.eCommerce.domain.product.relations.subscription.control.resource;

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
import com.skytala.eCommerce.domain.product.relations.subscription.command.resource.AddSubscriptionResource;
import com.skytala.eCommerce.domain.product.relations.subscription.command.resource.DeleteSubscriptionResource;
import com.skytala.eCommerce.domain.product.relations.subscription.command.resource.UpdateSubscriptionResource;
import com.skytala.eCommerce.domain.product.relations.subscription.event.resource.SubscriptionResourceAdded;
import com.skytala.eCommerce.domain.product.relations.subscription.event.resource.SubscriptionResourceDeleted;
import com.skytala.eCommerce.domain.product.relations.subscription.event.resource.SubscriptionResourceFound;
import com.skytala.eCommerce.domain.product.relations.subscription.event.resource.SubscriptionResourceUpdated;
import com.skytala.eCommerce.domain.product.relations.subscription.mapper.resource.SubscriptionResourceMapper;
import com.skytala.eCommerce.domain.product.relations.subscription.model.resource.SubscriptionResource;
import com.skytala.eCommerce.domain.product.relations.subscription.query.resource.FindSubscriptionResourcesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/subscription/subscriptionResources")
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
	@GetMapping("/find")
	public ResponseEntity<List<SubscriptionResource>> findSubscriptionResourcesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindSubscriptionResourcesBy query = new FindSubscriptionResourcesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<SubscriptionResource> subscriptionResources =((SubscriptionResourceFound) Scheduler.execute(query).data()).getSubscriptionResources();

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
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<SubscriptionResource> createSubscriptionResource(HttpServletRequest request) throws Exception {

		SubscriptionResource subscriptionResourceToBeAdded = new SubscriptionResource();
		try {
			subscriptionResourceToBeAdded = SubscriptionResourceMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
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
	public ResponseEntity<SubscriptionResource> createSubscriptionResource(@RequestBody SubscriptionResource subscriptionResourceToBeAdded) throws Exception {

		AddSubscriptionResource command = new AddSubscriptionResource(subscriptionResourceToBeAdded);
		SubscriptionResource subscriptionResource = ((SubscriptionResourceAdded) Scheduler.execute(command).data()).getAddedSubscriptionResource();
		
		if (subscriptionResource != null) 
			return successful(subscriptionResource);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateSubscriptionResource(@RequestBody SubscriptionResource subscriptionResourceToBeUpdated,
			@PathVariable String subscriptionResourceId) throws Exception {

		subscriptionResourceToBeUpdated.setSubscriptionResourceId(subscriptionResourceId);

		UpdateSubscriptionResource command = new UpdateSubscriptionResource(subscriptionResourceToBeUpdated);

		try {
			if(((SubscriptionResourceUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{subscriptionResourceId}")
	public ResponseEntity<SubscriptionResource> findById(@PathVariable String subscriptionResourceId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("subscriptionResourceId", subscriptionResourceId);
		try {

			List<SubscriptionResource> foundSubscriptionResource = findSubscriptionResourcesBy(requestParams).getBody();
			if(foundSubscriptionResource.size()==1){				return successful(foundSubscriptionResource.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{subscriptionResourceId}")
	public ResponseEntity<String> deleteSubscriptionResourceByIdUpdated(@PathVariable String subscriptionResourceId) throws Exception {
		DeleteSubscriptionResource command = new DeleteSubscriptionResource(subscriptionResourceId);

		try {
			if (((SubscriptionResourceDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
