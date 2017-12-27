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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/subscription/subscriptionTypeAttrs")
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
	@GetMapping("/find")
	public ResponseEntity<List<SubscriptionTypeAttr>> findSubscriptionTypeAttrsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindSubscriptionTypeAttrsBy query = new FindSubscriptionTypeAttrsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<SubscriptionTypeAttr> subscriptionTypeAttrs =((SubscriptionTypeAttrFound) Scheduler.execute(query).data()).getSubscriptionTypeAttrs();

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
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<SubscriptionTypeAttr> createSubscriptionTypeAttr(HttpServletRequest request) throws Exception {

		SubscriptionTypeAttr subscriptionTypeAttrToBeAdded = new SubscriptionTypeAttr();
		try {
			subscriptionTypeAttrToBeAdded = SubscriptionTypeAttrMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
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
	public ResponseEntity<SubscriptionTypeAttr> createSubscriptionTypeAttr(@RequestBody SubscriptionTypeAttr subscriptionTypeAttrToBeAdded) throws Exception {

		AddSubscriptionTypeAttr command = new AddSubscriptionTypeAttr(subscriptionTypeAttrToBeAdded);
		SubscriptionTypeAttr subscriptionTypeAttr = ((SubscriptionTypeAttrAdded) Scheduler.execute(command).data()).getAddedSubscriptionTypeAttr();
		
		if (subscriptionTypeAttr != null) 
			return successful(subscriptionTypeAttr);
		else 
			return conflict(null);
	}

	/**
	 * Updates the SubscriptionTypeAttr with the specific Id
	 * 
	 * @param subscriptionTypeAttrToBeUpdated
	 *            the SubscriptionTypeAttr thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{attrName}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateSubscriptionTypeAttr(@RequestBody SubscriptionTypeAttr subscriptionTypeAttrToBeUpdated,
			@PathVariable String attrName) throws Exception {

		subscriptionTypeAttrToBeUpdated.setAttrName(attrName);

		UpdateSubscriptionTypeAttr command = new UpdateSubscriptionTypeAttr(subscriptionTypeAttrToBeUpdated);

		try {
			if(((SubscriptionTypeAttrUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{subscriptionTypeAttrId}")
	public ResponseEntity<SubscriptionTypeAttr> findById(@PathVariable String subscriptionTypeAttrId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("subscriptionTypeAttrId", subscriptionTypeAttrId);
		try {

			List<SubscriptionTypeAttr> foundSubscriptionTypeAttr = findSubscriptionTypeAttrsBy(requestParams).getBody();
			if(foundSubscriptionTypeAttr.size()==1){				return successful(foundSubscriptionTypeAttr.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{subscriptionTypeAttrId}")
	public ResponseEntity<String> deleteSubscriptionTypeAttrByIdUpdated(@PathVariable String subscriptionTypeAttrId) throws Exception {
		DeleteSubscriptionTypeAttr command = new DeleteSubscriptionTypeAttr(subscriptionTypeAttrId);

		try {
			if (((SubscriptionTypeAttrDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
