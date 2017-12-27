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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/subscription/subscriptionTypes")
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
	@GetMapping("/find")
	public ResponseEntity<List<SubscriptionType>> findSubscriptionTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindSubscriptionTypesBy query = new FindSubscriptionTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<SubscriptionType> subscriptionTypes =((SubscriptionTypeFound) Scheduler.execute(query).data()).getSubscriptionTypes();

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
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<SubscriptionType> createSubscriptionType(HttpServletRequest request) throws Exception {

		SubscriptionType subscriptionTypeToBeAdded = new SubscriptionType();
		try {
			subscriptionTypeToBeAdded = SubscriptionTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
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
	public ResponseEntity<SubscriptionType> createSubscriptionType(@RequestBody SubscriptionType subscriptionTypeToBeAdded) throws Exception {

		AddSubscriptionType command = new AddSubscriptionType(subscriptionTypeToBeAdded);
		SubscriptionType subscriptionType = ((SubscriptionTypeAdded) Scheduler.execute(command).data()).getAddedSubscriptionType();
		
		if (subscriptionType != null) 
			return successful(subscriptionType);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateSubscriptionType(@RequestBody SubscriptionType subscriptionTypeToBeUpdated,
			@PathVariable String subscriptionTypeId) throws Exception {

		subscriptionTypeToBeUpdated.setSubscriptionTypeId(subscriptionTypeId);

		UpdateSubscriptionType command = new UpdateSubscriptionType(subscriptionTypeToBeUpdated);

		try {
			if(((SubscriptionTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{subscriptionTypeId}")
	public ResponseEntity<SubscriptionType> findById(@PathVariable String subscriptionTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("subscriptionTypeId", subscriptionTypeId);
		try {

			List<SubscriptionType> foundSubscriptionType = findSubscriptionTypesBy(requestParams).getBody();
			if(foundSubscriptionType.size()==1){				return successful(foundSubscriptionType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{subscriptionTypeId}")
	public ResponseEntity<String> deleteSubscriptionTypeByIdUpdated(@PathVariable String subscriptionTypeId) throws Exception {
		DeleteSubscriptionType command = new DeleteSubscriptionType(subscriptionTypeId);

		try {
			if (((SubscriptionTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
