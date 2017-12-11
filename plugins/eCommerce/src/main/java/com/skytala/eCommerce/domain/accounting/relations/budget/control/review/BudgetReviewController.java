package com.skytala.eCommerce.domain.accounting.relations.budget.control.review;

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
import com.skytala.eCommerce.domain.accounting.relations.budget.command.review.AddBudgetReview;
import com.skytala.eCommerce.domain.accounting.relations.budget.command.review.DeleteBudgetReview;
import com.skytala.eCommerce.domain.accounting.relations.budget.command.review.UpdateBudgetReview;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.review.BudgetReviewAdded;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.review.BudgetReviewDeleted;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.review.BudgetReviewFound;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.review.BudgetReviewUpdated;
import com.skytala.eCommerce.domain.accounting.relations.budget.mapper.review.BudgetReviewMapper;
import com.skytala.eCommerce.domain.accounting.relations.budget.model.review.BudgetReview;
import com.skytala.eCommerce.domain.accounting.relations.budget.query.review.FindBudgetReviewsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/accounting/budget/budgetReviews")
public class BudgetReviewController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public BudgetReviewController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a BudgetReview
	 * @return a List with the BudgetReviews
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findBudgetReviewsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindBudgetReviewsBy query = new FindBudgetReviewsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<BudgetReview> budgetReviews =((BudgetReviewFound) Scheduler.execute(query).data()).getBudgetReviews();

		if (budgetReviews.size() == 1) {
			return ResponseEntity.ok().body(budgetReviews.get(0));
		}

		return ResponseEntity.ok().body(budgetReviews);

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
	public ResponseEntity<Object> createBudgetReview(HttpServletRequest request) throws Exception {

		BudgetReview budgetReviewToBeAdded = new BudgetReview();
		try {
			budgetReviewToBeAdded = BudgetReviewMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createBudgetReview(budgetReviewToBeAdded);

	}

	/**
	 * creates a new BudgetReview entry in the ofbiz database
	 * 
	 * @param budgetReviewToBeAdded
	 *            the BudgetReview thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createBudgetReview(@RequestBody BudgetReview budgetReviewToBeAdded) throws Exception {

		AddBudgetReview command = new AddBudgetReview(budgetReviewToBeAdded);
		BudgetReview budgetReview = ((BudgetReviewAdded) Scheduler.execute(command).data()).getAddedBudgetReview();
		
		if (budgetReview != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(budgetReview);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("BudgetReview could not be created.");
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
	public boolean updateBudgetReview(HttpServletRequest request) throws Exception {

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

		BudgetReview budgetReviewToBeUpdated = new BudgetReview();

		try {
			budgetReviewToBeUpdated = BudgetReviewMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateBudgetReview(budgetReviewToBeUpdated, budgetReviewToBeUpdated.getBudgetReviewId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the BudgetReview with the specific Id
	 * 
	 * @param budgetReviewToBeUpdated
	 *            the BudgetReview thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{budgetReviewId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateBudgetReview(@RequestBody BudgetReview budgetReviewToBeUpdated,
			@PathVariable String budgetReviewId) throws Exception {

		budgetReviewToBeUpdated.setBudgetReviewId(budgetReviewId);

		UpdateBudgetReview command = new UpdateBudgetReview(budgetReviewToBeUpdated);

		try {
			if(((BudgetReviewUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{budgetReviewId}")
	public ResponseEntity<Object> findById(@PathVariable String budgetReviewId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("budgetReviewId", budgetReviewId);
		try {

			Object foundBudgetReview = findBudgetReviewsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundBudgetReview);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{budgetReviewId}")
	public ResponseEntity<Object> deleteBudgetReviewByIdUpdated(@PathVariable String budgetReviewId) throws Exception {
		DeleteBudgetReview command = new DeleteBudgetReview(budgetReviewId);

		try {
			if (((BudgetReviewDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("BudgetReview could not be deleted");

	}

}
