package com.skytala.eCommerce.domain.accounting.relations.finAccount.control.attribute;

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
import com.skytala.eCommerce.domain.accounting.relations.finAccount.command.attribute.AddFinAccountAttribute;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.command.attribute.DeleteFinAccountAttribute;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.command.attribute.UpdateFinAccountAttribute;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.event.attribute.FinAccountAttributeAdded;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.event.attribute.FinAccountAttributeDeleted;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.event.attribute.FinAccountAttributeFound;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.event.attribute.FinAccountAttributeUpdated;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.mapper.attribute.FinAccountAttributeMapper;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.model.attribute.FinAccountAttribute;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.query.attribute.FindFinAccountAttributesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/finAccount/finAccountAttributes")
public class FinAccountAttributeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public FinAccountAttributeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a FinAccountAttribute
	 * @return a List with the FinAccountAttributes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<FinAccountAttribute>> findFinAccountAttributesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindFinAccountAttributesBy query = new FindFinAccountAttributesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<FinAccountAttribute> finAccountAttributes =((FinAccountAttributeFound) Scheduler.execute(query).data()).getFinAccountAttributes();

		return ResponseEntity.ok().body(finAccountAttributes);

	}

	/**
	 * creates a new FinAccountAttribute entry in the ofbiz database
	 * 
	 * @param finAccountAttributeToBeAdded
	 *            the FinAccountAttribute thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<FinAccountAttribute> createFinAccountAttribute(@RequestBody FinAccountAttribute finAccountAttributeToBeAdded) throws Exception {

		AddFinAccountAttribute command = new AddFinAccountAttribute(finAccountAttributeToBeAdded);
		FinAccountAttribute finAccountAttribute = ((FinAccountAttributeAdded) Scheduler.execute(command).data()).getAddedFinAccountAttribute();
		
		if (finAccountAttribute != null) 
			return successful(finAccountAttribute);
		else 
			return conflict(null);
	}

	/**
	 * Updates the FinAccountAttribute with the specific Id
	 * 
	 * @param finAccountAttributeToBeUpdated
	 *            the FinAccountAttribute thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{attrName}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateFinAccountAttribute(@RequestBody FinAccountAttribute finAccountAttributeToBeUpdated,
			@PathVariable String attrName) throws Exception {

		finAccountAttributeToBeUpdated.setAttrName(attrName);

		UpdateFinAccountAttribute command = new UpdateFinAccountAttribute(finAccountAttributeToBeUpdated);

		try {
			if(((FinAccountAttributeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{finAccountAttributeId}")
	public ResponseEntity<FinAccountAttribute> findById(@PathVariable String finAccountAttributeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("finAccountAttributeId", finAccountAttributeId);
		try {

			List<FinAccountAttribute> foundFinAccountAttribute = findFinAccountAttributesBy(requestParams).getBody();
			if(foundFinAccountAttribute.size()==1){				return successful(foundFinAccountAttribute.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{finAccountAttributeId}")
	public ResponseEntity<String> deleteFinAccountAttributeByIdUpdated(@PathVariable String finAccountAttributeId) throws Exception {
		DeleteFinAccountAttribute command = new DeleteFinAccountAttribute(finAccountAttributeId);

		try {
			if (((FinAccountAttributeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
