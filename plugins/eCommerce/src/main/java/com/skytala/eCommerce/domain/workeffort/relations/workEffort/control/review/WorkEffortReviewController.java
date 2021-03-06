package com.skytala.eCommerce.domain.workeffort.relations.workEffort.control.review;

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
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.review.AddWorkEffortReview;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.review.DeleteWorkEffortReview;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.review.UpdateWorkEffortReview;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.review.WorkEffortReviewAdded;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.review.WorkEffortReviewDeleted;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.review.WorkEffortReviewFound;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.review.WorkEffortReviewUpdated;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.review.WorkEffortReviewMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.review.WorkEffortReview;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.query.review.FindWorkEffortReviewsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/workeffort/workEffort/workEffortReviews")
public class WorkEffortReviewController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public WorkEffortReviewController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a WorkEffortReview
	 * @return a List with the WorkEffortReviews
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<WorkEffortReview>> findWorkEffortReviewsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindWorkEffortReviewsBy query = new FindWorkEffortReviewsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<WorkEffortReview> workEffortReviews =((WorkEffortReviewFound) Scheduler.execute(query).data()).getWorkEffortReviews();

		return ResponseEntity.ok().body(workEffortReviews);

	}

	/**
	 * creates a new WorkEffortReview entry in the ofbiz database
	 * 
	 * @param workEffortReviewToBeAdded
	 *            the WorkEffortReview thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<WorkEffortReview> createWorkEffortReview(@RequestBody WorkEffortReview workEffortReviewToBeAdded) throws Exception {

		AddWorkEffortReview command = new AddWorkEffortReview(workEffortReviewToBeAdded);
		WorkEffortReview workEffortReview = ((WorkEffortReviewAdded) Scheduler.execute(command).data()).getAddedWorkEffortReview();
		
		if (workEffortReview != null) 
			return successful(workEffortReview);
		else 
			return conflict(null);
	}

	/**
	 * Updates the WorkEffortReview with the specific Id
	 * 
	 * @param workEffortReviewToBeUpdated
	 *            the WorkEffortReview thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateWorkEffortReview(@RequestBody WorkEffortReview workEffortReviewToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		workEffortReviewToBeUpdated.setnull(null);

		UpdateWorkEffortReview command = new UpdateWorkEffortReview(workEffortReviewToBeUpdated);

		try {
			if(((WorkEffortReviewUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{workEffortReviewId}")
	public ResponseEntity<WorkEffortReview> findById(@PathVariable String workEffortReviewId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("workEffortReviewId", workEffortReviewId);
		try {

			List<WorkEffortReview> foundWorkEffortReview = findWorkEffortReviewsBy(requestParams).getBody();
			if(foundWorkEffortReview.size()==1){				return successful(foundWorkEffortReview.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{workEffortReviewId}")
	public ResponseEntity<String> deleteWorkEffortReviewByIdUpdated(@PathVariable String workEffortReviewId) throws Exception {
		DeleteWorkEffortReview command = new DeleteWorkEffortReview(workEffortReviewId);

		try {
			if (((WorkEffortReviewDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
