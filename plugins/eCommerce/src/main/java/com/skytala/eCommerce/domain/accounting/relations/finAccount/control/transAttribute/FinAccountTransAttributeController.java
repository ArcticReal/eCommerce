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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findFinAccountTransAttributesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindFinAccountTransAttributesBy query = new FindFinAccountTransAttributesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<FinAccountTransAttribute> finAccountTransAttributes =((FinAccountTransAttributeFound) Scheduler.execute(query).data()).getFinAccountTransAttributes();

		if (finAccountTransAttributes.size() == 1) {
			return ResponseEntity.ok().body(finAccountTransAttributes.get(0));
		}

		return ResponseEntity.ok().body(finAccountTransAttributes);

	}

	/**
	 * 
	 * this method will only be called by Springs DispatcherServlet
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createFinAccountTransAttribute(HttpServletRequest request) throws Exception {

		FinAccountTransAttribute finAccountTransAttributeToBeAdded = new FinAccountTransAttribute();
		try {
			finAccountTransAttributeToBeAdded = FinAccountTransAttributeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createFinAccountTransAttribute(finAccountTransAttributeToBeAdded);

	}

	/**
	 * creates a new FinAccountTransAttribute entry in the ofbiz database
	 * 
	 * @param finAccountTransAttributeToBeAdded
	 *            the FinAccountTransAttribute thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createFinAccountTransAttribute(@RequestBody FinAccountTransAttribute finAccountTransAttributeToBeAdded) throws Exception {

		AddFinAccountTransAttribute command = new AddFinAccountTransAttribute(finAccountTransAttributeToBeAdded);
		FinAccountTransAttribute finAccountTransAttribute = ((FinAccountTransAttributeAdded) Scheduler.execute(command).data()).getAddedFinAccountTransAttribute();
		
		if (finAccountTransAttribute != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(finAccountTransAttribute);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("FinAccountTransAttribute could not be created.");
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
	@RequestMapping(method = RequestMethod.PUT, value = "/update", consumes = "application/x-www-form-urlencoded")
	public boolean updateFinAccountTransAttribute(HttpServletRequest request) throws Exception {

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

		FinAccountTransAttribute finAccountTransAttributeToBeUpdated = new FinAccountTransAttribute();

		try {
			finAccountTransAttributeToBeUpdated = FinAccountTransAttributeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateFinAccountTransAttribute(finAccountTransAttributeToBeUpdated, finAccountTransAttributeToBeUpdated.getAttrName()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateFinAccountTransAttribute(@RequestBody FinAccountTransAttribute finAccountTransAttributeToBeUpdated,
			@PathVariable String attrName) throws Exception {

		finAccountTransAttributeToBeUpdated.setAttrName(attrName);

		UpdateFinAccountTransAttribute command = new UpdateFinAccountTransAttribute(finAccountTransAttributeToBeUpdated);

		try {
			if(((FinAccountTransAttributeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{finAccountTransAttributeId}")
	public ResponseEntity<Object> findById(@PathVariable String finAccountTransAttributeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("finAccountTransAttributeId", finAccountTransAttributeId);
		try {

			Object foundFinAccountTransAttribute = findFinAccountTransAttributesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundFinAccountTransAttribute);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{finAccountTransAttributeId}")
	public ResponseEntity<Object> deleteFinAccountTransAttributeByIdUpdated(@PathVariable String finAccountTransAttributeId) throws Exception {
		DeleteFinAccountTransAttribute command = new DeleteFinAccountTransAttribute(finAccountTransAttributeId);

		try {
			if (((FinAccountTransAttributeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("FinAccountTransAttribute could not be deleted");

	}

}
