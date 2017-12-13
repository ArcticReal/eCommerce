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
	public ResponseEntity<Object> findAcctgTransAttributesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindAcctgTransAttributesBy query = new FindAcctgTransAttributesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<AcctgTransAttribute> acctgTransAttributes =((AcctgTransAttributeFound) Scheduler.execute(query).data()).getAcctgTransAttributes();

		if (acctgTransAttributes.size() == 1) {
			return ResponseEntity.ok().body(acctgTransAttributes.get(0));
		}

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
	public ResponseEntity<Object> createAcctgTransAttribute(HttpServletRequest request) throws Exception {

		AcctgTransAttribute acctgTransAttributeToBeAdded = new AcctgTransAttribute();
		try {
			acctgTransAttributeToBeAdded = AcctgTransAttributeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
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
	public ResponseEntity<Object> createAcctgTransAttribute(@RequestBody AcctgTransAttribute acctgTransAttributeToBeAdded) throws Exception {

		AddAcctgTransAttribute command = new AddAcctgTransAttribute(acctgTransAttributeToBeAdded);
		AcctgTransAttribute acctgTransAttribute = ((AcctgTransAttributeAdded) Scheduler.execute(command).data()).getAddedAcctgTransAttribute();
		
		if (acctgTransAttribute != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(acctgTransAttribute);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("AcctgTransAttribute could not be created.");
	}

	/**
	 * this method will only be called by Springs DispatcherServlet
	 * 
	 * @deprecated
	 * @param request
	 *            HttpServletRequest object
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@PutMapping(value = "/update", consumes = "application/x-www-form-urlencoded")
	public boolean updateAcctgTransAttribute(HttpServletRequest request) throws Exception {

		BufferedReader br;
		String data = null;
		Map<String, String> dataMap = null;

		try {
			br = new BufferedReader(new InputStreamReader(request.getInputStream()));
			if (br != null) {
				data = java.net.URLDecoder.decode(br.readLine(), "UTF-8");
			}
		} catch (IOException e1) {
			e1.printStackTrace();
			return false;
		}

		dataMap = Splitter.on('&').trimResults().withKeyValueSeparator(Splitter.on('=').limit(2).trimResults())
				.split(data);

		AcctgTransAttribute acctgTransAttributeToBeUpdated = new AcctgTransAttribute();

		try {
			acctgTransAttributeToBeUpdated = AcctgTransAttributeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateAcctgTransAttribute(acctgTransAttributeToBeUpdated, acctgTransAttributeToBeUpdated.getAttrName()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateAcctgTransAttribute(@RequestBody AcctgTransAttribute acctgTransAttributeToBeUpdated,
			@PathVariable String attrName) throws Exception {

		acctgTransAttributeToBeUpdated.setAttrName(attrName);

		UpdateAcctgTransAttribute command = new UpdateAcctgTransAttribute(acctgTransAttributeToBeUpdated);

		try {
			if(((AcctgTransAttributeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{acctgTransAttributeId}")
	public ResponseEntity<Object> findById(@PathVariable String acctgTransAttributeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("acctgTransAttributeId", acctgTransAttributeId);
		try {

			Object foundAcctgTransAttribute = findAcctgTransAttributesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundAcctgTransAttribute);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{acctgTransAttributeId}")
	public ResponseEntity<Object> deleteAcctgTransAttributeByIdUpdated(@PathVariable String acctgTransAttributeId) throws Exception {
		DeleteAcctgTransAttribute command = new DeleteAcctgTransAttribute(acctgTransAttributeId);

		try {
			if (((AcctgTransAttributeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("AcctgTransAttribute could not be deleted");

	}

}
