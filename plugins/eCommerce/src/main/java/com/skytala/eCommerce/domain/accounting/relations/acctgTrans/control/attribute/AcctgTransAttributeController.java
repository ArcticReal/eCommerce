package com.skytala.eCommerce.domain.accounting.relations.acctgTrans.control.attribute;

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
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.command.attribute.AddAcctgTransAttribute;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.command.attribute.DeleteAcctgTransAttribute;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.command.attribute.UpdateAcctgTransAttribute;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.event.attribute.AcctgTransAttributeAdded;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.event.attribute.AcctgTransAttributeDeleted;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.event.attribute.AcctgTransAttributeFound;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.event.attribute.AcctgTransAttributeUpdated;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.mapper.attribute.AcctgTransAttributeMapper;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.model.attribute.AcctgTransAttribute;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.query.attribute.FindAcctgTransAttributesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/acctgTrans/acctgTransAttributes")
public class AcctgTransAttributeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public AcctgTransAttributeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a AcctgTransAttribute
	 * @return a List with the AcctgTransAttributes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<AcctgTransAttribute>> findAcctgTransAttributesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindAcctgTransAttributesBy query = new FindAcctgTransAttributesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<AcctgTransAttribute> acctgTransAttributes =((AcctgTransAttributeFound) Scheduler.execute(query).data()).getAcctgTransAttributes();

		return ResponseEntity.ok().body(acctgTransAttributes);

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
	public ResponseEntity<AcctgTransAttribute> createAcctgTransAttribute(HttpServletRequest request) throws Exception {

		AcctgTransAttribute acctgTransAttributeToBeAdded = new AcctgTransAttribute();
		try {
			acctgTransAttributeToBeAdded = AcctgTransAttributeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createAcctgTransAttribute(acctgTransAttributeToBeAdded);

	}

	/**
	 * creates a new AcctgTransAttribute entry in the ofbiz database
	 * 
	 * @param acctgTransAttributeToBeAdded
	 *            the AcctgTransAttribute thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<AcctgTransAttribute> createAcctgTransAttribute(@RequestBody AcctgTransAttribute acctgTransAttributeToBeAdded) throws Exception {

		AddAcctgTransAttribute command = new AddAcctgTransAttribute(acctgTransAttributeToBeAdded);
		AcctgTransAttribute acctgTransAttribute = ((AcctgTransAttributeAdded) Scheduler.execute(command).data()).getAddedAcctgTransAttribute();
		
		if (acctgTransAttribute != null) 
			return successful(acctgTransAttribute);
		else 
			return conflict(null);
	}

	/**
	 * Updates the AcctgTransAttribute with the specific Id
	 * 
	 * @param acctgTransAttributeToBeUpdated
	 *            the AcctgTransAttribute thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{attrName}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateAcctgTransAttribute(@RequestBody AcctgTransAttribute acctgTransAttributeToBeUpdated,
			@PathVariable String attrName) throws Exception {

		acctgTransAttributeToBeUpdated.setAttrName(attrName);

		UpdateAcctgTransAttribute command = new UpdateAcctgTransAttribute(acctgTransAttributeToBeUpdated);

		try {
			if(((AcctgTransAttributeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{acctgTransAttributeId}")
	public ResponseEntity<AcctgTransAttribute> findById(@PathVariable String acctgTransAttributeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("acctgTransAttributeId", acctgTransAttributeId);
		try {

			List<AcctgTransAttribute> foundAcctgTransAttribute = findAcctgTransAttributesBy(requestParams).getBody();
			if(foundAcctgTransAttribute.size()==1){				return successful(foundAcctgTransAttribute.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{acctgTransAttributeId}")
	public ResponseEntity<String> deleteAcctgTransAttributeByIdUpdated(@PathVariable String acctgTransAttributeId) throws Exception {
		DeleteAcctgTransAttribute command = new DeleteAcctgTransAttribute(acctgTransAttributeId);

		try {
			if (((AcctgTransAttributeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
