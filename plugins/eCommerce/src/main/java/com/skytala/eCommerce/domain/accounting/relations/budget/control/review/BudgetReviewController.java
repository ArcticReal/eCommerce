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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	public ResponseEntity<List<BudgetReview>> findBudgetReviewsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindBudgetReviewsBy query = new FindBudgetReviewsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<BudgetReview> budgetReviews =((BudgetReviewFound) Scheduler.execute(query).data()).getBudgetReviews();

		return ResponseEntity.ok().body(budgetReviews);

	}

	/**
	 * creates a new BudgetReview entry in the ofbiz database
	 * 
	 * @param budgetReviewToBeAdded
	 *            the BudgetReview thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<BudgetReview> createBudgetReview(@RequestBody BudgetReview budgetReviewToBeAdded) throws Exception {

		AddBudgetReview command = new AddBudgetReview(budgetReviewToBeAdded);
		BudgetReview budgetReview = ((BudgetReviewAdded) Scheduler.execute(command).data()).getAddedBudgetReview();
		
		if (budgetReview != null) 
			return successful(budgetReview);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateBudgetReview(@RequestBody BudgetReview budgetReviewToBeUpdated,
			@PathVariable String budgetReviewId) throws Exception {

		budgetReviewToBeUpdated.setBudgetReviewId(budgetReviewId);

		UpdateBudgetReview command = new UpdateBudgetReview(budgetReviewToBeUpdated);

		try {
			if(((BudgetReviewUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{budgetReviewId}")
	public ResponseEntity<BudgetReview> findById(@PathVariable String budgetReviewId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("budgetReviewId", budgetReviewId);
		try {

			List<BudgetReview> foundBudgetReview = findBudgetReviewsBy(requestParams).getBody();
			if(foundBudgetReview.size()==1){				return successful(foundBudgetReview.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{budgetReviewId}")
	public ResponseEntity<String> deleteBudgetReviewByIdUpdated(@PathVariable String budgetReviewId) throws Exception {
		DeleteBudgetReview command = new DeleteBudgetReview(budgetReviewId);

		try {
			if (((BudgetReviewDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
