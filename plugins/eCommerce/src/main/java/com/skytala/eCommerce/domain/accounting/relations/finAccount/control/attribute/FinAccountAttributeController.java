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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<Object> findFinAccountAttributesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindFinAccountAttributesBy query = new FindFinAccountAttributesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<FinAccountAttribute> finAccountAttributes =((FinAccountAttributeFound) Scheduler.execute(query).data()).getFinAccountAttributes();

		if (finAccountAttributes.size() == 1) {
			return ResponseEntity.ok().body(finAccountAttributes.get(0));
		}

		return ResponseEntity.ok().body(finAccountAttributes);

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
	public ResponseEntity<Object> createFinAccountAttribute(HttpServletRequest request) throws Exception {

		FinAccountAttribute finAccountAttributeToBeAdded = new FinAccountAttribute();
		try {
			finAccountAttributeToBeAdded = FinAccountAttributeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createFinAccountAttribute(finAccountAttributeToBeAdded);

	}

	/**
	 * creates a new FinAccountAttribute entry in the ofbiz database
	 * 
	 * @param finAccountAttributeToBeAdded
	 *            the FinAccountAttribute thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createFinAccountAttribute(@RequestBody FinAccountAttribute finAccountAttributeToBeAdded) throws Exception {

		AddFinAccountAttribute command = new AddFinAccountAttribute(finAccountAttributeToBeAdded);
		FinAccountAttribute finAccountAttribute = ((FinAccountAttributeAdded) Scheduler.execute(command).data()).getAddedFinAccountAttribute();
		
		if (finAccountAttribute != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(finAccountAttribute);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("FinAccountAttribute could not be created.");
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
	public boolean updateFinAccountAttribute(HttpServletRequest request) throws Exception {

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

		FinAccountAttribute finAccountAttributeToBeUpdated = new FinAccountAttribute();

		try {
			finAccountAttributeToBeUpdated = FinAccountAttributeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateFinAccountAttribute(finAccountAttributeToBeUpdated, finAccountAttributeToBeUpdated.getAttrName()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateFinAccountAttribute(@RequestBody FinAccountAttribute finAccountAttributeToBeUpdated,
			@PathVariable String attrName) throws Exception {

		finAccountAttributeToBeUpdated.setAttrName(attrName);

		UpdateFinAccountAttribute command = new UpdateFinAccountAttribute(finAccountAttributeToBeUpdated);

		try {
			if(((FinAccountAttributeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{finAccountAttributeId}")
	public ResponseEntity<Object> findById(@PathVariable String finAccountAttributeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("finAccountAttributeId", finAccountAttributeId);
		try {

			Object foundFinAccountAttribute = findFinAccountAttributesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundFinAccountAttribute);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{finAccountAttributeId}")
	public ResponseEntity<Object> deleteFinAccountAttributeByIdUpdated(@PathVariable String finAccountAttributeId) throws Exception {
		DeleteFinAccountAttribute command = new DeleteFinAccountAttribute(finAccountAttributeId);

		try {
			if (((FinAccountAttributeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("FinAccountAttribute could not be deleted");

	}

}
