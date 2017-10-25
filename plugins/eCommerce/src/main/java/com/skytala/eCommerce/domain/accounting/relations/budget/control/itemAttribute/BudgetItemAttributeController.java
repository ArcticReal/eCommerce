package com.skytala.eCommerce.domain.accounting.relations.budget.control.itemAttribute;

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
import com.skytala.eCommerce.domain.accounting.relations.budget.command.itemAttribute.AddBudgetItemAttribute;
import com.skytala.eCommerce.domain.accounting.relations.budget.command.itemAttribute.DeleteBudgetItemAttribute;
import com.skytala.eCommerce.domain.accounting.relations.budget.command.itemAttribute.UpdateBudgetItemAttribute;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.itemAttribute.BudgetItemAttributeAdded;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.itemAttribute.BudgetItemAttributeDeleted;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.itemAttribute.BudgetItemAttributeFound;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.itemAttribute.BudgetItemAttributeUpdated;
import com.skytala.eCommerce.domain.accounting.relations.budget.mapper.itemAttribute.BudgetItemAttributeMapper;
import com.skytala.eCommerce.domain.accounting.relations.budget.model.itemAttribute.BudgetItemAttribute;
import com.skytala.eCommerce.domain.accounting.relations.budget.query.itemAttribute.FindBudgetItemAttributesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/budgetItemAttributes")
public class BudgetItemAttributeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public BudgetItemAttributeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a BudgetItemAttribute
	 * @return a List with the BudgetItemAttributes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findBudgetItemAttributesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindBudgetItemAttributesBy query = new FindBudgetItemAttributesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<BudgetItemAttribute> budgetItemAttributes =((BudgetItemAttributeFound) Scheduler.execute(query).data()).getBudgetItemAttributes();

		if (budgetItemAttributes.size() == 1) {
			return ResponseEntity.ok().body(budgetItemAttributes.get(0));
		}

		return ResponseEntity.ok().body(budgetItemAttributes);

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
	public ResponseEntity<Object> createBudgetItemAttribute(HttpServletRequest request) throws Exception {

		BudgetItemAttribute budgetItemAttributeToBeAdded = new BudgetItemAttribute();
		try {
			budgetItemAttributeToBeAdded = BudgetItemAttributeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createBudgetItemAttribute(budgetItemAttributeToBeAdded);

	}

	/**
	 * creates a new BudgetItemAttribute entry in the ofbiz database
	 * 
	 * @param budgetItemAttributeToBeAdded
	 *            the BudgetItemAttribute thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createBudgetItemAttribute(@RequestBody BudgetItemAttribute budgetItemAttributeToBeAdded) throws Exception {

		AddBudgetItemAttribute command = new AddBudgetItemAttribute(budgetItemAttributeToBeAdded);
		BudgetItemAttribute budgetItemAttribute = ((BudgetItemAttributeAdded) Scheduler.execute(command).data()).getAddedBudgetItemAttribute();
		
		if (budgetItemAttribute != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(budgetItemAttribute);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("BudgetItemAttribute could not be created.");
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
	public boolean updateBudgetItemAttribute(HttpServletRequest request) throws Exception {

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

		BudgetItemAttribute budgetItemAttributeToBeUpdated = new BudgetItemAttribute();

		try {
			budgetItemAttributeToBeUpdated = BudgetItemAttributeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateBudgetItemAttribute(budgetItemAttributeToBeUpdated, budgetItemAttributeToBeUpdated.getBudgetItemSeqId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the BudgetItemAttribute with the specific Id
	 * 
	 * @param budgetItemAttributeToBeUpdated
	 *            the BudgetItemAttribute thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{budgetItemSeqId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateBudgetItemAttribute(@RequestBody BudgetItemAttribute budgetItemAttributeToBeUpdated,
			@PathVariable String budgetItemSeqId) throws Exception {

		budgetItemAttributeToBeUpdated.setBudgetItemSeqId(budgetItemSeqId);

		UpdateBudgetItemAttribute command = new UpdateBudgetItemAttribute(budgetItemAttributeToBeUpdated);

		try {
			if(((BudgetItemAttributeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{budgetItemAttributeId}")
	public ResponseEntity<Object> findById(@PathVariable String budgetItemAttributeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("budgetItemAttributeId", budgetItemAttributeId);
		try {

			Object foundBudgetItemAttribute = findBudgetItemAttributesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundBudgetItemAttribute);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{budgetItemAttributeId}")
	public ResponseEntity<Object> deleteBudgetItemAttributeByIdUpdated(@PathVariable String budgetItemAttributeId) throws Exception {
		DeleteBudgetItemAttribute command = new DeleteBudgetItemAttribute(budgetItemAttributeId);

		try {
			if (((BudgetItemAttributeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("BudgetItemAttribute could not be deleted");

	}

	@RequestMapping(value = (" ** "))
	public ResponseEntity<Object> returnErrorPage(HttpServletRequest request) {

		String usedUri = request.getRequestURI();
		String[] splittedString = usedUri.split("/");

		String usedRequest = splittedString[splittedString.length - 1];

		if (validRequests.containsKey(usedRequest)) {
			String returnVal = "Error: request method " + request.getMethod() + " not allowed for \"" + usedUri
					+ "\"!\n" + "Please use " + validRequests.get(usedRequest) + "!";

			return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(returnVal);
		}

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/budgetItemAttribute/\" plus one of the following: "
				+ "";

		Set<String> keySet = validRequests.keySet();
		Iterator<String> it = keySet.iterator();

		while (it.hasNext()) {
			returnVal += "\"" + it.next() + "\"";
			if (it.hasNext())
				returnVal += ", ";
		}

		returnVal += "!";

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnVal);

	}
}
