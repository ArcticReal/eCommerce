package com.skytala.eCommerce.domain.accounting.relations.budget.control.typeAttr;

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
import com.skytala.eCommerce.domain.accounting.relations.budget.command.typeAttr.AddBudgetTypeAttr;
import com.skytala.eCommerce.domain.accounting.relations.budget.command.typeAttr.DeleteBudgetTypeAttr;
import com.skytala.eCommerce.domain.accounting.relations.budget.command.typeAttr.UpdateBudgetTypeAttr;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.typeAttr.BudgetTypeAttrAdded;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.typeAttr.BudgetTypeAttrDeleted;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.typeAttr.BudgetTypeAttrFound;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.typeAttr.BudgetTypeAttrUpdated;
import com.skytala.eCommerce.domain.accounting.relations.budget.mapper.typeAttr.BudgetTypeAttrMapper;
import com.skytala.eCommerce.domain.accounting.relations.budget.model.typeAttr.BudgetTypeAttr;
import com.skytala.eCommerce.domain.accounting.relations.budget.query.typeAttr.FindBudgetTypeAttrsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/accounting/budget/budgetTypeAttrs")
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
	@GetMapping("/find")
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
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
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
	@PutMapping(value = "/update", consumes = "application/x-www-form-urlencoded")
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

		if (updateBudgetTypeAttr(budgetTypeAttrToBeUpdated, budgetTypeAttrToBeUpdated.getAttrName()).getStatusCode()
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
	@RequestMapping(method = RequestMethod.PUT, value = "/{attrName}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateBudgetTypeAttr(@RequestBody BudgetTypeAttr budgetTypeAttrToBeUpdated,
			@PathVariable String attrName) throws Exception {

		budgetTypeAttrToBeUpdated.setAttrName(attrName);

		UpdateBudgetTypeAttr command = new UpdateBudgetTypeAttr(budgetTypeAttrToBeUpdated);

		try {
			if(((BudgetTypeAttrUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{budgetTypeAttrId}")
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

	@DeleteMapping("/{budgetTypeAttrId}")
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

}
