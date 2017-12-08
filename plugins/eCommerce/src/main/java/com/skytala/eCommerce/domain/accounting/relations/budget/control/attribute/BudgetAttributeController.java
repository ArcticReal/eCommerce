package com.skytala.eCommerce.domain.accounting.relations.budget.control.attribute;

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
import com.skytala.eCommerce.domain.accounting.relations.budget.command.attribute.AddBudgetAttribute;
import com.skytala.eCommerce.domain.accounting.relations.budget.command.attribute.DeleteBudgetAttribute;
import com.skytala.eCommerce.domain.accounting.relations.budget.command.attribute.UpdateBudgetAttribute;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.attribute.BudgetAttributeAdded;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.attribute.BudgetAttributeDeleted;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.attribute.BudgetAttributeFound;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.attribute.BudgetAttributeUpdated;
import com.skytala.eCommerce.domain.accounting.relations.budget.mapper.attribute.BudgetAttributeMapper;
import com.skytala.eCommerce.domain.accounting.relations.budget.model.attribute.BudgetAttribute;
import com.skytala.eCommerce.domain.accounting.relations.budget.query.attribute.FindBudgetAttributesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/budgetAttributes")
public class BudgetAttributeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public BudgetAttributeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a BudgetAttribute
	 * @return a List with the BudgetAttributes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findBudgetAttributesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindBudgetAttributesBy query = new FindBudgetAttributesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<BudgetAttribute> budgetAttributes =((BudgetAttributeFound) Scheduler.execute(query).data()).getBudgetAttributes();

		if (budgetAttributes.size() == 1) {
			return ResponseEntity.ok().body(budgetAttributes.get(0));
		}

		return ResponseEntity.ok().body(budgetAttributes);

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
	public ResponseEntity<Object> createBudgetAttribute(HttpServletRequest request) throws Exception {

		BudgetAttribute budgetAttributeToBeAdded = new BudgetAttribute();
		try {
			budgetAttributeToBeAdded = BudgetAttributeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createBudgetAttribute(budgetAttributeToBeAdded);

	}

	/**
	 * creates a new BudgetAttribute entry in the ofbiz database
	 * 
	 * @param budgetAttributeToBeAdded
	 *            the BudgetAttribute thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createBudgetAttribute(@RequestBody BudgetAttribute budgetAttributeToBeAdded) throws Exception {

		AddBudgetAttribute command = new AddBudgetAttribute(budgetAttributeToBeAdded);
		BudgetAttribute budgetAttribute = ((BudgetAttributeAdded) Scheduler.execute(command).data()).getAddedBudgetAttribute();
		
		if (budgetAttribute != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(budgetAttribute);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("BudgetAttribute could not be created.");
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
	public boolean updateBudgetAttribute(HttpServletRequest request) throws Exception {

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

		BudgetAttribute budgetAttributeToBeUpdated = new BudgetAttribute();

		try {
			budgetAttributeToBeUpdated = BudgetAttributeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateBudgetAttribute(budgetAttributeToBeUpdated, budgetAttributeToBeUpdated.getAttrName()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the BudgetAttribute with the specific Id
	 * 
	 * @param budgetAttributeToBeUpdated
	 *            the BudgetAttribute thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{attrName}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateBudgetAttribute(@RequestBody BudgetAttribute budgetAttributeToBeUpdated,
			@PathVariable String attrName) throws Exception {

		budgetAttributeToBeUpdated.setAttrName(attrName);

		UpdateBudgetAttribute command = new UpdateBudgetAttribute(budgetAttributeToBeUpdated);

		try {
			if(((BudgetAttributeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{budgetAttributeId}")
	public ResponseEntity<Object> findById(@PathVariable String budgetAttributeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("budgetAttributeId", budgetAttributeId);
		try {

			Object foundBudgetAttribute = findBudgetAttributesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundBudgetAttribute);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{budgetAttributeId}")
	public ResponseEntity<Object> deleteBudgetAttributeByIdUpdated(@PathVariable String budgetAttributeId) throws Exception {
		DeleteBudgetAttribute command = new DeleteBudgetAttribute(budgetAttributeId);

		try {
			if (((BudgetAttributeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("BudgetAttribute could not be deleted");

	}

}
