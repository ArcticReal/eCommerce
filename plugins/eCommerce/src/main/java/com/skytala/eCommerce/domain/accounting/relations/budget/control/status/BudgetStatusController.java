package com.skytala.eCommerce.domain.accounting.relations.budget.control.status;

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
import com.skytala.eCommerce.domain.accounting.relations.budget.command.status.AddBudgetStatus;
import com.skytala.eCommerce.domain.accounting.relations.budget.command.status.DeleteBudgetStatus;
import com.skytala.eCommerce.domain.accounting.relations.budget.command.status.UpdateBudgetStatus;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.status.BudgetStatusAdded;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.status.BudgetStatusDeleted;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.status.BudgetStatusFound;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.status.BudgetStatusUpdated;
import com.skytala.eCommerce.domain.accounting.relations.budget.mapper.status.BudgetStatusMapper;
import com.skytala.eCommerce.domain.accounting.relations.budget.model.status.BudgetStatus;
import com.skytala.eCommerce.domain.accounting.relations.budget.query.status.FindBudgetStatussBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/budgetStatuss")
public class BudgetStatusController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public BudgetStatusController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a BudgetStatus
	 * @return a List with the BudgetStatuss
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findBudgetStatussBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindBudgetStatussBy query = new FindBudgetStatussBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<BudgetStatus> budgetStatuss =((BudgetStatusFound) Scheduler.execute(query).data()).getBudgetStatuss();

		if (budgetStatuss.size() == 1) {
			return ResponseEntity.ok().body(budgetStatuss.get(0));
		}

		return ResponseEntity.ok().body(budgetStatuss);

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
	public ResponseEntity<Object> createBudgetStatus(HttpServletRequest request) throws Exception {

		BudgetStatus budgetStatusToBeAdded = new BudgetStatus();
		try {
			budgetStatusToBeAdded = BudgetStatusMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createBudgetStatus(budgetStatusToBeAdded);

	}

	/**
	 * creates a new BudgetStatus entry in the ofbiz database
	 * 
	 * @param budgetStatusToBeAdded
	 *            the BudgetStatus thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createBudgetStatus(@RequestBody BudgetStatus budgetStatusToBeAdded) throws Exception {

		AddBudgetStatus command = new AddBudgetStatus(budgetStatusToBeAdded);
		BudgetStatus budgetStatus = ((BudgetStatusAdded) Scheduler.execute(command).data()).getAddedBudgetStatus();
		
		if (budgetStatus != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(budgetStatus);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("BudgetStatus could not be created.");
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
	public boolean updateBudgetStatus(HttpServletRequest request) throws Exception {

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

		BudgetStatus budgetStatusToBeUpdated = new BudgetStatus();

		try {
			budgetStatusToBeUpdated = BudgetStatusMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateBudgetStatus(budgetStatusToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the BudgetStatus with the specific Id
	 * 
	 * @param budgetStatusToBeUpdated
	 *            the BudgetStatus thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateBudgetStatus(@RequestBody BudgetStatus budgetStatusToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		budgetStatusToBeUpdated.setnull(null);

		UpdateBudgetStatus command = new UpdateBudgetStatus(budgetStatusToBeUpdated);

		try {
			if(((BudgetStatusUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{budgetStatusId}")
	public ResponseEntity<Object> findById(@PathVariable String budgetStatusId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("budgetStatusId", budgetStatusId);
		try {

			Object foundBudgetStatus = findBudgetStatussBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundBudgetStatus);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{budgetStatusId}")
	public ResponseEntity<Object> deleteBudgetStatusByIdUpdated(@PathVariable String budgetStatusId) throws Exception {
		DeleteBudgetStatus command = new DeleteBudgetStatus(budgetStatusId);

		try {
			if (((BudgetStatusDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("BudgetStatus could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/budgetStatus/\" plus one of the following: "
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
