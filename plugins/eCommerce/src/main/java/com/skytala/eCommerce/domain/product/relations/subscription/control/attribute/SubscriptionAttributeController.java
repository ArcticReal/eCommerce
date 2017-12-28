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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/subscription/subscriptionAttributes")
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
	@GetMapping("/find")
	public ResponseEntity<List<SubscriptionAttribute>> findSubscriptionAttributesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindSubscriptionAttributesBy query = new FindSubscriptionAttributesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<SubscriptionAttribute> subscriptionAttributes =((SubscriptionAttributeFound) Scheduler.execute(query).data()).getSubscriptionAttributes();

		return ResponseEntity.ok().body(subscriptionAttributes);

	}

	/**
	 * creates a new SubscriptionAttribute entry in the ofbiz database
	 * 
	 * @param subscriptionAttributeToBeAdded
	 *            the SubscriptionAttribute thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<SubscriptionAttribute> createSubscriptionAttribute(@RequestBody SubscriptionAttribute subscriptionAttributeToBeAdded) throws Exception {

		AddSubscriptionAttribute command = new AddSubscriptionAttribute(subscriptionAttributeToBeAdded);
		SubscriptionAttribute subscriptionAttribute = ((SubscriptionAttributeAdded) Scheduler.execute(command).data()).getAddedSubscriptionAttribute();
		
		if (subscriptionAttribute != null) 
			return successful(subscriptionAttribute);
		else 
			return conflict(null);
	}

	/**
	 * Updates the SubscriptionAttribute with the specific Id
	 * 
	 * @param subscriptionAttributeToBeUpdated
	 *            the SubscriptionAttribute thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{attrName}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateSubscriptionAttribute(@RequestBody SubscriptionAttribute subscriptionAttributeToBeUpdated,
			@PathVariable String attrName) throws Exception {

		subscriptionAttributeToBeUpdated.setAttrName(attrName);

		UpdateSubscriptionAttribute command = new UpdateSubscriptionAttribute(subscriptionAttributeToBeUpdated);

		try {
			if(((SubscriptionAttributeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{subscriptionAttributeId}")
	public ResponseEntity<SubscriptionAttribute> findById(@PathVariable String subscriptionAttributeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("subscriptionAttributeId", subscriptionAttributeId);
		try {

			List<SubscriptionAttribute> foundSubscriptionAttribute = findSubscriptionAttributesBy(requestParams).getBody();
			if(foundSubscriptionAttribute.size()==1){				return successful(foundSubscriptionAttribute.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{subscriptionAttributeId}")
	public ResponseEntity<String> deleteSubscriptionAttributeByIdUpdated(@PathVariable String subscriptionAttributeId) throws Exception {
		DeleteSubscriptionAttribute command = new DeleteSubscriptionAttribute(subscriptionAttributeId);

		try {
			if (((SubscriptionAttributeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
