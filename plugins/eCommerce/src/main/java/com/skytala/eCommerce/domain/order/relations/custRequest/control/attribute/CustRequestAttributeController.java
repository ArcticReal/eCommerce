package com.skytala.eCommerce.domain.order.relations.custRequest.control.attribute;

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
import com.skytala.eCommerce.domain.order.relations.custRequest.command.attribute.AddCustRequestAttribute;
import com.skytala.eCommerce.domain.order.relations.custRequest.command.attribute.DeleteCustRequestAttribute;
import com.skytala.eCommerce.domain.order.relations.custRequest.command.attribute.UpdateCustRequestAttribute;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.attribute.CustRequestAttributeAdded;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.attribute.CustRequestAttributeDeleted;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.attribute.CustRequestAttributeFound;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.attribute.CustRequestAttributeUpdated;
import com.skytala.eCommerce.domain.order.relations.custRequest.mapper.attribute.CustRequestAttributeMapper;
import com.skytala.eCommerce.domain.order.relations.custRequest.model.attribute.CustRequestAttribute;
import com.skytala.eCommerce.domain.order.relations.custRequest.query.attribute.FindCustRequestAttributesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/order/custRequest/custRequestAttributes")
public class CustRequestAttributeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public CustRequestAttributeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a CustRequestAttribute
	 * @return a List with the CustRequestAttributes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<CustRequestAttribute>> findCustRequestAttributesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindCustRequestAttributesBy query = new FindCustRequestAttributesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<CustRequestAttribute> custRequestAttributes =((CustRequestAttributeFound) Scheduler.execute(query).data()).getCustRequestAttributes();

		return ResponseEntity.ok().body(custRequestAttributes);

	}

	/**
	 * creates a new CustRequestAttribute entry in the ofbiz database
	 * 
	 * @param custRequestAttributeToBeAdded
	 *            the CustRequestAttribute thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<CustRequestAttribute> createCustRequestAttribute(@RequestBody CustRequestAttribute custRequestAttributeToBeAdded) throws Exception {

		AddCustRequestAttribute command = new AddCustRequestAttribute(custRequestAttributeToBeAdded);
		CustRequestAttribute custRequestAttribute = ((CustRequestAttributeAdded) Scheduler.execute(command).data()).getAddedCustRequestAttribute();
		
		if (custRequestAttribute != null) 
			return successful(custRequestAttribute);
		else 
			return conflict(null);
	}

	/**
	 * Updates the CustRequestAttribute with the specific Id
	 * 
	 * @param custRequestAttributeToBeUpdated
	 *            the CustRequestAttribute thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{attrName}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateCustRequestAttribute(@RequestBody CustRequestAttribute custRequestAttributeToBeUpdated,
			@PathVariable String attrName) throws Exception {

		custRequestAttributeToBeUpdated.setAttrName(attrName);

		UpdateCustRequestAttribute command = new UpdateCustRequestAttribute(custRequestAttributeToBeUpdated);

		try {
			if(((CustRequestAttributeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{custRequestAttributeId}")
	public ResponseEntity<CustRequestAttribute> findById(@PathVariable String custRequestAttributeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("custRequestAttributeId", custRequestAttributeId);
		try {

			List<CustRequestAttribute> foundCustRequestAttribute = findCustRequestAttributesBy(requestParams).getBody();
			if(foundCustRequestAttribute.size()==1){				return successful(foundCustRequestAttribute.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{custRequestAttributeId}")
	public ResponseEntity<String> deleteCustRequestAttributeByIdUpdated(@PathVariable String custRequestAttributeId) throws Exception {
		DeleteCustRequestAttribute command = new DeleteCustRequestAttribute(custRequestAttributeId);

		try {
			if (((CustRequestAttributeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
