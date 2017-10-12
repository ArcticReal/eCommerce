package com.skytala.eCommerce.domain.accounting.relations.budgetItemTypeAttr;

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
import com.skytala.eCommerce.domain.accounting.relations.budgetItemTypeAttr.command.AddBudgetItemTypeAttr;
import com.skytala.eCommerce.domain.accounting.relations.budgetItemTypeAttr.command.DeleteBudgetItemTypeAttr;
import com.skytala.eCommerce.domain.accounting.relations.budgetItemTypeAttr.command.UpdateBudgetItemTypeAttr;
import com.skytala.eCommerce.domain.accounting.relations.budgetItemTypeAttr.event.BudgetItemTypeAttrAdded;
import com.skytala.eCommerce.domain.accounting.relations.budgetItemTypeAttr.event.BudgetItemTypeAttrDeleted;
import com.skytala.eCommerce.domain.accounting.relations.budgetItemTypeAttr.event.BudgetItemTypeAttrFound;
import com.skytala.eCommerce.domain.accounting.relations.budgetItemTypeAttr.event.BudgetItemTypeAttrUpdated;
import com.skytala.eCommerce.domain.accounting.relations.budgetItemTypeAttr.mapper.BudgetItemTypeAttrMapper;
import com.skytala.eCommerce.domain.accounting.relations.budgetItemTypeAttr.model.BudgetItemTypeAttr;
import com.skytala.eCommerce.domain.accounting.relations.budgetItemTypeAttr.query.FindBudgetItemTypeAttrsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/budgetItemTypeAttrs")
public class BudgetItemTypeAttrController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public BudgetItemTypeAttrController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a BudgetItemTypeAttr
	 * @return a List with the BudgetItemTypeAttrs
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findBudgetItemTypeAttrsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindBudgetItemTypeAttrsBy query = new FindBudgetItemTypeAttrsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<BudgetItemTypeAttr> budgetItemTypeAttrs =((BudgetItemTypeAttrFound) Scheduler.execute(query).data()).getBudgetItemTypeAttrs();

		if (budgetItemTypeAttrs.size() == 1) {
			return ResponseEntity.ok().body(budgetItemTypeAttrs.get(0));
		}

		return ResponseEntity.ok().body(budgetItemTypeAttrs);

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
	public ResponseEntity<Object> createBudgetItemTypeAttr(HttpServletRequest request) throws Exception {

		BudgetItemTypeAttr budgetItemTypeAttrToBeAdded = new BudgetItemTypeAttr();
		try {
			budgetItemTypeAttrToBeAdded = BudgetItemTypeAttrMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createBudgetItemTypeAttr(budgetItemTypeAttrToBeAdded);

	}

	/**
	 * creates a new BudgetItemTypeAttr entry in the ofbiz database
	 * 
	 * @param budgetItemTypeAttrToBeAdded
	 *            the BudgetItemTypeAttr thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createBudgetItemTypeAttr(@RequestBody BudgetItemTypeAttr budgetItemTypeAttrToBeAdded) throws Exception {

		AddBudgetItemTypeAttr command = new AddBudgetItemTypeAttr(budgetItemTypeAttrToBeAdded);
		BudgetItemTypeAttr budgetItemTypeAttr = ((BudgetItemTypeAttrAdded) Scheduler.execute(command).data()).getAddedBudgetItemTypeAttr();
		
		if (budgetItemTypeAttr != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(budgetItemTypeAttr);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("BudgetItemTypeAttr could not be created.");
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
	public boolean updateBudgetItemTypeAttr(HttpServletRequest request) throws Exception {

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

		BudgetItemTypeAttr budgetItemTypeAttrToBeUpdated = new BudgetItemTypeAttr();

		try {
			budgetItemTypeAttrToBeUpdated = BudgetItemTypeAttrMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateBudgetItemTypeAttr(budgetItemTypeAttrToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the BudgetItemTypeAttr with the specific Id
	 * 
	 * @param budgetItemTypeAttrToBeUpdated
	 *            the BudgetItemTypeAttr thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateBudgetItemTypeAttr(@RequestBody BudgetItemTypeAttr budgetItemTypeAttrToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		budgetItemTypeAttrToBeUpdated.setnull(null);

		UpdateBudgetItemTypeAttr command = new UpdateBudgetItemTypeAttr(budgetItemTypeAttrToBeUpdated);

		try {
			if(((BudgetItemTypeAttrUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{budgetItemTypeAttrId}")
	public ResponseEntity<Object> findById(@PathVariable String budgetItemTypeAttrId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("budgetItemTypeAttrId", budgetItemTypeAttrId);
		try {

			Object foundBudgetItemTypeAttr = findBudgetItemTypeAttrsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundBudgetItemTypeAttr);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{budgetItemTypeAttrId}")
	public ResponseEntity<Object> deleteBudgetItemTypeAttrByIdUpdated(@PathVariable String budgetItemTypeAttrId) throws Exception {
		DeleteBudgetItemTypeAttr command = new DeleteBudgetItemTypeAttr(budgetItemTypeAttrId);

		try {
			if (((BudgetItemTypeAttrDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("BudgetItemTypeAttr could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/budgetItemTypeAttr/\" plus one of the following: "
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
