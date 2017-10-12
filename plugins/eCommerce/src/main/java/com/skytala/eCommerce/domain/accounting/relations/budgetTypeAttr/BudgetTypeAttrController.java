package com.skytala.eCommerce.domain.accounting.relations.budgetTypeAttr;

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
import com.skytala.eCommerce.domain.accounting.relations.budgetTypeAttr.command.AddBudgetTypeAttr;
import com.skytala.eCommerce.domain.accounting.relations.budgetTypeAttr.command.DeleteBudgetTypeAttr;
import com.skytala.eCommerce.domain.accounting.relations.budgetTypeAttr.command.UpdateBudgetTypeAttr;
import com.skytala.eCommerce.domain.accounting.relations.budgetTypeAttr.event.BudgetTypeAttrAdded;
import com.skytala.eCommerce.domain.accounting.relations.budgetTypeAttr.event.BudgetTypeAttrDeleted;
import com.skytala.eCommerce.domain.accounting.relations.budgetTypeAttr.event.BudgetTypeAttrFound;
import com.skytala.eCommerce.domain.accounting.relations.budgetTypeAttr.event.BudgetTypeAttrUpdated;
import com.skytala.eCommerce.domain.accounting.relations.budgetTypeAttr.mapper.BudgetTypeAttrMapper;
import com.skytala.eCommerce.domain.accounting.relations.budgetTypeAttr.model.BudgetTypeAttr;
import com.skytala.eCommerce.domain.accounting.relations.budgetTypeAttr.query.FindBudgetTypeAttrsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/budgetTypeAttrs")
public class BudgetTypeAttrController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public BudgetTypeAttrController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a BudgetTypeAttr
	 * @return a List with the BudgetTypeAttrs
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findBudgetTypeAttrsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindBudgetTypeAttrsBy query = new FindBudgetTypeAttrsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<BudgetTypeAttr> budgetTypeAttrs =((BudgetTypeAttrFound) Scheduler.execute(query).data()).getBudgetTypeAttrs();

		if (budgetTypeAttrs.size() == 1) {
			return ResponseEntity.ok().body(budgetTypeAttrs.get(0));
		}

		return ResponseEntity.ok().body(budgetTypeAttrs);

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
	public ResponseEntity<Object> createBudgetTypeAttr(HttpServletRequest request) throws Exception {

		BudgetTypeAttr budgetTypeAttrToBeAdded = new BudgetTypeAttr();
		try {
			budgetTypeAttrToBeAdded = BudgetTypeAttrMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createBudgetTypeAttr(budgetTypeAttrToBeAdded);

	}

	/**
	 * creates a new BudgetTypeAttr entry in the ofbiz database
	 * 
	 * @param budgetTypeAttrToBeAdded
	 *            the BudgetTypeAttr thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createBudgetTypeAttr(@RequestBody BudgetTypeAttr budgetTypeAttrToBeAdded) throws Exception {

		AddBudgetTypeAttr command = new AddBudgetTypeAttr(budgetTypeAttrToBeAdded);
		BudgetTypeAttr budgetTypeAttr = ((BudgetTypeAttrAdded) Scheduler.execute(command).data()).getAddedBudgetTypeAttr();
		
		if (budgetTypeAttr != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(budgetTypeAttr);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("BudgetTypeAttr could not be created.");
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
	public boolean updateBudgetTypeAttr(HttpServletRequest request) throws Exception {

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

		BudgetTypeAttr budgetTypeAttrToBeUpdated = new BudgetTypeAttr();

		try {
			budgetTypeAttrToBeUpdated = BudgetTypeAttrMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateBudgetTypeAttr(budgetTypeAttrToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the BudgetTypeAttr with the specific Id
	 * 
	 * @param budgetTypeAttrToBeUpdated
	 *            the BudgetTypeAttr thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateBudgetTypeAttr(@RequestBody BudgetTypeAttr budgetTypeAttrToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		budgetTypeAttrToBeUpdated.setnull(null);

		UpdateBudgetTypeAttr command = new UpdateBudgetTypeAttr(budgetTypeAttrToBeUpdated);

		try {
			if(((BudgetTypeAttrUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{budgetTypeAttrId}")
	public ResponseEntity<Object> findById(@PathVariable String budgetTypeAttrId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("budgetTypeAttrId", budgetTypeAttrId);
		try {

			Object foundBudgetTypeAttr = findBudgetTypeAttrsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundBudgetTypeAttr);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{budgetTypeAttrId}")
	public ResponseEntity<Object> deleteBudgetTypeAttrByIdUpdated(@PathVariable String budgetTypeAttrId) throws Exception {
		DeleteBudgetTypeAttr command = new DeleteBudgetTypeAttr(budgetTypeAttrId);

		try {
			if (((BudgetTypeAttrDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("BudgetTypeAttr could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/budgetTypeAttr/\" plus one of the following: "
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
