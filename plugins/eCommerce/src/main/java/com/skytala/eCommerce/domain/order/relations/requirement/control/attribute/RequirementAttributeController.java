package com.skytala.eCommerce.domain.order.relations.requirement.control.attribute;

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
import com.skytala.eCommerce.domain.order.relations.requirement.command.attribute.AddRequirementAttribute;
import com.skytala.eCommerce.domain.order.relations.requirement.command.attribute.DeleteRequirementAttribute;
import com.skytala.eCommerce.domain.order.relations.requirement.command.attribute.UpdateRequirementAttribute;
import com.skytala.eCommerce.domain.order.relations.requirement.event.attribute.RequirementAttributeAdded;
import com.skytala.eCommerce.domain.order.relations.requirement.event.attribute.RequirementAttributeDeleted;
import com.skytala.eCommerce.domain.order.relations.requirement.event.attribute.RequirementAttributeFound;
import com.skytala.eCommerce.domain.order.relations.requirement.event.attribute.RequirementAttributeUpdated;
import com.skytala.eCommerce.domain.order.relations.requirement.mapper.attribute.RequirementAttributeMapper;
import com.skytala.eCommerce.domain.order.relations.requirement.model.attribute.RequirementAttribute;
import com.skytala.eCommerce.domain.order.relations.requirement.query.attribute.FindRequirementAttributesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/order/requirement/requirementAttributes")
public class RequirementAttributeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public RequirementAttributeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a RequirementAttribute
	 * @return a List with the RequirementAttributes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<RequirementAttribute>> findRequirementAttributesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindRequirementAttributesBy query = new FindRequirementAttributesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<RequirementAttribute> requirementAttributes =((RequirementAttributeFound) Scheduler.execute(query).data()).getRequirementAttributes();

		return ResponseEntity.ok().body(requirementAttributes);

	}

	/**
	 * creates a new RequirementAttribute entry in the ofbiz database
	 * 
	 * @param requirementAttributeToBeAdded
	 *            the RequirementAttribute thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<RequirementAttribute> createRequirementAttribute(@RequestBody RequirementAttribute requirementAttributeToBeAdded) throws Exception {

		AddRequirementAttribute command = new AddRequirementAttribute(requirementAttributeToBeAdded);
		RequirementAttribute requirementAttribute = ((RequirementAttributeAdded) Scheduler.execute(command).data()).getAddedRequirementAttribute();
		
		if (requirementAttribute != null) 
			return successful(requirementAttribute);
		else 
			return conflict(null);
	}

	/**
	 * Updates the RequirementAttribute with the specific Id
	 * 
	 * @param requirementAttributeToBeUpdated
	 *            the RequirementAttribute thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{attrName}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateRequirementAttribute(@RequestBody RequirementAttribute requirementAttributeToBeUpdated,
			@PathVariable String attrName) throws Exception {

		requirementAttributeToBeUpdated.setAttrName(attrName);

		UpdateRequirementAttribute command = new UpdateRequirementAttribute(requirementAttributeToBeUpdated);

		try {
			if(((RequirementAttributeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{requirementAttributeId}")
	public ResponseEntity<RequirementAttribute> findById(@PathVariable String requirementAttributeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("requirementAttributeId", requirementAttributeId);
		try {

			List<RequirementAttribute> foundRequirementAttribute = findRequirementAttributesBy(requestParams).getBody();
			if(foundRequirementAttribute.size()==1){				return successful(foundRequirementAttribute.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{requirementAttributeId}")
	public ResponseEntity<String> deleteRequirementAttributeByIdUpdated(@PathVariable String requirementAttributeId) throws Exception {
		DeleteRequirementAttribute command = new DeleteRequirementAttribute(requirementAttributeId);

		try {
			if (((RequirementAttributeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
