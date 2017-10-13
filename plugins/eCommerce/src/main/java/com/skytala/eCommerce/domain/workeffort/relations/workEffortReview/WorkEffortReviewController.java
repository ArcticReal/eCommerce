package com.skytala.eCommerce.domain.workeffort.relations.workEffortReview;

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
import com.skytala.eCommerce.domain.workeffort.relations.workEffortReview.command.AddWorkEffortReview;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortReview.command.DeleteWorkEffortReview;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortReview.command.UpdateWorkEffortReview;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortReview.event.WorkEffortReviewAdded;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortReview.event.WorkEffortReviewDeleted;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortReview.event.WorkEffortReviewFound;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortReview.event.WorkEffortReviewUpdated;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortReview.mapper.WorkEffortReviewMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortReview.model.WorkEffortReview;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortReview.query.FindWorkEffortReviewsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/workEffortReviews")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findWorkEffortReviewsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindWorkEffortReviewsBy query = new FindWorkEffortReviewsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<WorkEffortReview> workEffortReviews =((WorkEffortReviewFound) Scheduler.execute(query).data()).getWorkEffortReviews();

		if (workEffortReviews.size() == 1) {
			return ResponseEntity.ok().body(workEffortReviews.get(0));
		}

		return ResponseEntity.ok().body(workEffortReviews);

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
	public ResponseEntity<Object> createWorkEffortReview(HttpServletRequest request) throws Exception {

		WorkEffortReview workEffortReviewToBeAdded = new WorkEffortReview();
		try {
			workEffortReviewToBeAdded = WorkEffortReviewMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createWorkEffortReview(workEffortReviewToBeAdded);

	}

	/**
	 * creates a new WorkEffortReview entry in the ofbiz database
	 * 
	 * @param workEffortReviewToBeAdded
	 *            the WorkEffortReview thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createWorkEffortReview(@RequestBody WorkEffortReview workEffortReviewToBeAdded) throws Exception {

		AddWorkEffortReview command = new AddWorkEffortReview(workEffortReviewToBeAdded);
		WorkEffortReview workEffortReview = ((WorkEffortReviewAdded) Scheduler.execute(command).data()).getAddedWorkEffortReview();
		
		if (workEffortReview != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(workEffortReview);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("WorkEffortReview could not be created.");
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
	public boolean updateWorkEffortReview(HttpServletRequest request) throws Exception {

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

		WorkEffortReview workEffortReviewToBeUpdated = new WorkEffortReview();

		try {
			workEffortReviewToBeUpdated = WorkEffortReviewMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateWorkEffortReview(workEffortReviewToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateWorkEffortReview(@RequestBody WorkEffortReview workEffortReviewToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		workEffortReviewToBeUpdated.setnull(null);

		UpdateWorkEffortReview command = new UpdateWorkEffortReview(workEffortReviewToBeUpdated);

		try {
			if(((WorkEffortReviewUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{workEffortReviewId}")
	public ResponseEntity<Object> findById(@PathVariable String workEffortReviewId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("workEffortReviewId", workEffortReviewId);
		try {

			Object foundWorkEffortReview = findWorkEffortReviewsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundWorkEffortReview);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{workEffortReviewId}")
	public ResponseEntity<Object> deleteWorkEffortReviewByIdUpdated(@PathVariable String workEffortReviewId) throws Exception {
		DeleteWorkEffortReview command = new DeleteWorkEffortReview(workEffortReviewId);

		try {
			if (((WorkEffortReviewDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("WorkEffortReview could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/workEffortReview/\" plus one of the following: "
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