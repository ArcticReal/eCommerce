package com.skytala.eCommerce.domain.order.relations.requirement.control.typeAttr;

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
import com.skytala.eCommerce.domain.order.relations.requirement.command.typeAttr.AddRequirementTypeAttr;
import com.skytala.eCommerce.domain.order.relations.requirement.command.typeAttr.DeleteRequirementTypeAttr;
import com.skytala.eCommerce.domain.order.relations.requirement.command.typeAttr.UpdateRequirementTypeAttr;
import com.skytala.eCommerce.domain.order.relations.requirement.event.typeAttr.RequirementTypeAttrAdded;
import com.skytala.eCommerce.domain.order.relations.requirement.event.typeAttr.RequirementTypeAttrDeleted;
import com.skytala.eCommerce.domain.order.relations.requirement.event.typeAttr.RequirementTypeAttrFound;
import com.skytala.eCommerce.domain.order.relations.requirement.event.typeAttr.RequirementTypeAttrUpdated;
import com.skytala.eCommerce.domain.order.relations.requirement.mapper.typeAttr.RequirementTypeAttrMapper;
import com.skytala.eCommerce.domain.order.relations.requirement.model.typeAttr.RequirementTypeAttr;
import com.skytala.eCommerce.domain.order.relations.requirement.query.typeAttr.FindRequirementTypeAttrsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/order/requirement/requirementTypeAttrs")
public class RequirementTypeAttrController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public RequirementTypeAttrController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a RequirementTypeAttr
	 * @return a List with the RequirementTypeAttrs
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<RequirementTypeAttr>> findRequirementTypeAttrsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindRequirementTypeAttrsBy query = new FindRequirementTypeAttrsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<RequirementTypeAttr> requirementTypeAttrs =((RequirementTypeAttrFound) Scheduler.execute(query).data()).getRequirementTypeAttrs();

		return ResponseEntity.ok().body(requirementTypeAttrs);

	}

	/**
	 * creates a new RequirementTypeAttr entry in the ofbiz database
	 * 
	 * @param requirementTypeAttrToBeAdded
	 *            the RequirementTypeAttr thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<RequirementTypeAttr> createRequirementTypeAttr(@RequestBody RequirementTypeAttr requirementTypeAttrToBeAdded) throws Exception {

		AddRequirementTypeAttr command = new AddRequirementTypeAttr(requirementTypeAttrToBeAdded);
		RequirementTypeAttr requirementTypeAttr = ((RequirementTypeAttrAdded) Scheduler.execute(command).data()).getAddedRequirementTypeAttr();
		
		if (requirementTypeAttr != null) 
			return successful(requirementTypeAttr);
		else 
			return conflict(null);
	}

	/**
	 * Updates the RequirementTypeAttr with the specific Id
	 * 
	 * @param requirementTypeAttrToBeUpdated
	 *            the RequirementTypeAttr thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{attrName}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateRequirementTypeAttr(@RequestBody RequirementTypeAttr requirementTypeAttrToBeUpdated,
			@PathVariable String attrName) throws Exception {

		requirementTypeAttrToBeUpdated.setAttrName(attrName);

		UpdateRequirementTypeAttr command = new UpdateRequirementTypeAttr(requirementTypeAttrToBeUpdated);

		try {
			if(((RequirementTypeAttrUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{requirementTypeAttrId}")
	public ResponseEntity<RequirementTypeAttr> findById(@PathVariable String requirementTypeAttrId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("requirementTypeAttrId", requirementTypeAttrId);
		try {

			List<RequirementTypeAttr> foundRequirementTypeAttr = findRequirementTypeAttrsBy(requestParams).getBody();
			if(foundRequirementTypeAttr.size()==1){				return successful(foundRequirementTypeAttr.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{requirementTypeAttrId}")
	public ResponseEntity<String> deleteRequirementTypeAttrByIdUpdated(@PathVariable String requirementTypeAttrId) throws Exception {
		DeleteRequirementTypeAttr command = new DeleteRequirementTypeAttr(requirementTypeAttrId);

		try {
			if (((RequirementTypeAttrDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
