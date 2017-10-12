package com.skytala.eCommerce.domain.accounting.relations.budgetReviewResultType;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Splitter;
import com.skytala.eCommerce.domain.accounting.relations.budgetReviewResultType.command.AddBudgetReviewResultType;
import com.skytala.eCommerce.domain.accounting.relations.budgetReviewResultType.command.DeleteBudgetReviewResultType;
import com.skytala.eCommerce.domain.accounting.relations.budgetReviewResultType.command.UpdateBudgetReviewResultType;
import com.skytala.eCommerce.domain.accounting.relations.budgetReviewResultType.event.BudgetReviewResultTypeAdded;
import com.skytala.eCommerce.domain.accounting.relations.budgetReviewResultType.event.BudgetReviewResultTypeDeleted;
import com.skytala.eCommerce.domain.accounting.relations.budgetReviewResultType.event.BudgetReviewResultTypeFound;
import com.skytala.eCommerce.domain.accounting.relations.budgetReviewResultType.event.BudgetReviewResultTypeUpdated;
import com.skytala.eCommerce.domain.accounting.relations.budgetReviewResultType.mapper.BudgetReviewResultTypeMapper;
import com.skytala.eCommerce.domain.accounting.relations.budgetReviewResultType.model.BudgetReviewResultType;
import com.skytala.eCommerce.domain.accounting.relations.budgetReviewResultType.query.FindBudgetReviewResultTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/budgetReviewResultTypes")
public class BudgetReviewResultTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public BudgetReviewResultTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a BudgetReviewResultType
	 * @return a List with the BudgetReviewResultTypes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findBudgetReviewResultTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindBudgetReviewResultTypesBy query = new FindBudgetReviewResultTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<BudgetReviewResultType> budgetReviewResultTypes =((BudgetReviewResultTypeFound) Scheduler.execute(query).data()).getBudgetReviewResultTypes();

		if (budgetReviewResultTypes.size() == 1) {
			return ResponseEntity.ok().body(budgetReviewResultTypes.get(0));
		}

		return ResponseEntity.ok().body(budgetReviewResultTypes);

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
	public ResponseEntity<Object> createBudgetReviewResultType(HttpServletRequest request) throws Exception {

		BudgetReviewResultType budgetReviewResultTypeToBeAdded = new BudgetReviewResultType();
		try {
			budgetReviewResultTypeToBeAdded = BudgetReviewResultTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createBudgetReviewResultType(budgetReviewResultTypeToBeAdded);

	}

	/**
	 * creates a new BudgetReviewResultType entry in the ofbiz database
	 * 
	 * @param budgetReviewResultTypeToBeAdded
	 *            the BudgetReviewResultType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createBudgetReviewResultType(@RequestBody BudgetReviewResultType budgetReviewResultTypeToBeAdded) throws Exception {

		AddBudgetReviewResultType command = new AddBudgetReviewResultType(budgetReviewResultTypeToBeAdded);
		BudgetReviewResultType budgetReviewResultType = ((BudgetReviewResultTypeAdded) Scheduler.execute(command).data()).getAddedBudgetReviewResultType();
		
		if (budgetReviewResultType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(budgetReviewResultType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("BudgetReviewResultType could not be created.");
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
	public boolean updateBudgetReviewResultType(HttpServletRequest request) throws Exception {

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

		BudgetReviewResultType budgetReviewResultTypeToBeUpdated = new BudgetReviewResultType();

		try {
			budgetReviewResultTypeToBeUpdated = BudgetReviewResultTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateBudgetReviewResultType(budgetReviewResultTypeToBeUpdated, budgetReviewResultTypeToBeUpdated.getBudgetReviewResultTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the BudgetReviewResultType with the specific Id
	 * 
	 * @param budgetReviewResultTypeToBeUpdated
	 *            the BudgetReviewResultType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{budgetReviewResultTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateBudgetReviewResultType(@RequestBody BudgetReviewResultType budgetReviewResultTypeToBeUpdated,
			@PathVariable String budgetReviewResultTypeId) throws Exception {

		budgetReviewResultTypeToBeUpdated.setBudgetReviewResultTypeId(budgetReviewResultTypeId);

		UpdateBudgetReviewResultType command = new UpdateBudgetReviewResultType(budgetReviewResultTypeToBeUpdated);

		try {
			if(((BudgetReviewResultTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{budgetReviewResultTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String budgetReviewResultTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("budgetReviewResultTypeId", budgetReviewResultTypeId);
		try {

			Object foundBudgetReviewResultType = findBudgetReviewResultTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundBudgetReviewResultType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{budgetReviewResultTypeId}")
	public ResponseEntity<Object> deleteBudgetReviewResultTypeByIdUpdated(@PathVariable String budgetReviewResultTypeId) throws Exception {
		DeleteBudgetReviewResultType command = new DeleteBudgetReviewResultType(budgetReviewResultTypeId);

		try {
			if (((BudgetReviewResultTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("BudgetReviewResultType could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/budgetReviewResultType/\" plus one of the following: "
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
