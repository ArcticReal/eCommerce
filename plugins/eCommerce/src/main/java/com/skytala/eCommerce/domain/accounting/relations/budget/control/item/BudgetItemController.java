package com.skytala.eCommerce.domain.accounting.relations.budget.control.item;

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
import com.skytala.eCommerce.domain.accounting.relations.budget.command.item.AddBudgetItem;
import com.skytala.eCommerce.domain.accounting.relations.budget.command.item.DeleteBudgetItem;
import com.skytala.eCommerce.domain.accounting.relations.budget.command.item.UpdateBudgetItem;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.item.BudgetItemAdded;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.item.BudgetItemDeleted;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.item.BudgetItemFound;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.item.BudgetItemUpdated;
import com.skytala.eCommerce.domain.accounting.relations.budget.mapper.item.BudgetItemMapper;
import com.skytala.eCommerce.domain.accounting.relations.budget.model.item.BudgetItem;
import com.skytala.eCommerce.domain.accounting.relations.budget.query.item.FindBudgetItemsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/accounting/budget/budgetItems")
public class BudgetItemController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public BudgetItemController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a BudgetItem
	 * @return a List with the BudgetItems
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findBudgetItemsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindBudgetItemsBy query = new FindBudgetItemsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<BudgetItem> budgetItems =((BudgetItemFound) Scheduler.execute(query).data()).getBudgetItems();

		if (budgetItems.size() == 1) {
			return ResponseEntity.ok().body(budgetItems.get(0));
		}

		return ResponseEntity.ok().body(budgetItems);

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
	public ResponseEntity<Object> createBudgetItem(HttpServletRequest request) throws Exception {

		BudgetItem budgetItemToBeAdded = new BudgetItem();
		try {
			budgetItemToBeAdded = BudgetItemMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createBudgetItem(budgetItemToBeAdded);

	}

	/**
	 * creates a new BudgetItem entry in the ofbiz database
	 * 
	 * @param budgetItemToBeAdded
	 *            the BudgetItem thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createBudgetItem(@RequestBody BudgetItem budgetItemToBeAdded) throws Exception {

		AddBudgetItem command = new AddBudgetItem(budgetItemToBeAdded);
		BudgetItem budgetItem = ((BudgetItemAdded) Scheduler.execute(command).data()).getAddedBudgetItem();
		
		if (budgetItem != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(budgetItem);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("BudgetItem could not be created.");
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
	public boolean updateBudgetItem(HttpServletRequest request) throws Exception {

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

		BudgetItem budgetItemToBeUpdated = new BudgetItem();

		try {
			budgetItemToBeUpdated = BudgetItemMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateBudgetItem(budgetItemToBeUpdated, budgetItemToBeUpdated.getBudgetItemSeqId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the BudgetItem with the specific Id
	 * 
	 * @param budgetItemToBeUpdated
	 *            the BudgetItem thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{budgetItemSeqId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateBudgetItem(@RequestBody BudgetItem budgetItemToBeUpdated,
			@PathVariable String budgetItemSeqId) throws Exception {

		budgetItemToBeUpdated.setBudgetItemSeqId(budgetItemSeqId);

		UpdateBudgetItem command = new UpdateBudgetItem(budgetItemToBeUpdated);

		try {
			if(((BudgetItemUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{budgetItemId}")
	public ResponseEntity<Object> findById(@PathVariable String budgetItemId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("budgetItemId", budgetItemId);
		try {

			Object foundBudgetItem = findBudgetItemsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundBudgetItem);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{budgetItemId}")
	public ResponseEntity<Object> deleteBudgetItemByIdUpdated(@PathVariable String budgetItemId) throws Exception {
		DeleteBudgetItem command = new DeleteBudgetItem(budgetItemId);

		try {
			if (((BudgetItemDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("BudgetItem could not be deleted");

	}

}
