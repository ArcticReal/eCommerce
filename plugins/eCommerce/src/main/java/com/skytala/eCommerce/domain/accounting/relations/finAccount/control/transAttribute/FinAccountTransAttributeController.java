package com.skytala.eCommerce.domain.accounting.relations.finAccount.control.transAttribute;

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
import com.skytala.eCommerce.domain.accounting.relations.finAccount.command.transAttribute.AddFinAccountTransAttribute;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.command.transAttribute.DeleteFinAccountTransAttribute;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.command.transAttribute.UpdateFinAccountTransAttribute;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.event.transAttribute.FinAccountTransAttributeAdded;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.event.transAttribute.FinAccountTransAttributeDeleted;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.event.transAttribute.FinAccountTransAttributeFound;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.event.transAttribute.FinAccountTransAttributeUpdated;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.mapper.transAttribute.FinAccountTransAttributeMapper;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.model.transAttribute.FinAccountTransAttribute;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.query.transAttribute.FindFinAccountTransAttributesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/finAccount/finAccountTransAttributes")
public class FinAccountTransAttributeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public FinAccountTransAttributeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a FinAccountTransAttribute
	 * @return a List with the FinAccountTransAttributes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<FinAccountTransAttribute>> findFinAccountTransAttributesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindFinAccountTransAttributesBy query = new FindFinAccountTransAttributesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<FinAccountTransAttribute> finAccountTransAttributes =((FinAccountTransAttributeFound) Scheduler.execute(query).data()).getFinAccountTransAttributes();

		return ResponseEntity.ok().body(finAccountTransAttributes);

	}

	/**
	 * creates a new FinAccountTransAttribute entry in the ofbiz database
	 * 
	 * @param finAccountTransAttributeToBeAdded
	 *            the FinAccountTransAttribute thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<FinAccountTransAttribute> createFinAccountTransAttribute(@RequestBody FinAccountTransAttribute finAccountTransAttributeToBeAdded) throws Exception {

		AddFinAccountTransAttribute command = new AddFinAccountTransAttribute(finAccountTransAttributeToBeAdded);
		FinAccountTransAttribute finAccountTransAttribute = ((FinAccountTransAttributeAdded) Scheduler.execute(command).data()).getAddedFinAccountTransAttribute();
		
		if (finAccountTransAttribute != null) 
			return successful(finAccountTransAttribute);
		else 
			return conflict(null);
	}

	/**
	 * Updates the FinAccountTransAttribute with the specific Id
	 * 
	 * @param finAccountTransAttributeToBeUpdated
	 *            the FinAccountTransAttribute thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{attrName}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateFinAccountTransAttribute(@RequestBody FinAccountTransAttribute finAccountTransAttributeToBeUpdated,
			@PathVariable String attrName) throws Exception {

		finAccountTransAttributeToBeUpdated.setAttrName(attrName);

		UpdateFinAccountTransAttribute command = new UpdateFinAccountTransAttribute(finAccountTransAttributeToBeUpdated);

		try {
			if(((FinAccountTransAttributeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{finAccountTransAttributeId}")
	public ResponseEntity<FinAccountTransAttribute> findById(@PathVariable String finAccountTransAttributeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("finAccountTransAttributeId", finAccountTransAttributeId);
		try {

			List<FinAccountTransAttribute> foundFinAccountTransAttribute = findFinAccountTransAttributesBy(requestParams).getBody();
			if(foundFinAccountTransAttribute.size()==1){				return successful(foundFinAccountTransAttribute.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{finAccountTransAttributeId}")
	public ResponseEntity<String> deleteFinAccountTransAttributeByIdUpdated(@PathVariable String finAccountTransAttributeId) throws Exception {
		DeleteFinAccountTransAttribute command = new DeleteFinAccountTransAttribute(finAccountTransAttributeId);

		try {
			if (((FinAccountTransAttributeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
