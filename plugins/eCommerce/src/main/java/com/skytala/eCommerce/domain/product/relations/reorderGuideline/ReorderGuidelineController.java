package com.skytala.eCommerce.domain.product.relations.reorderGuideline;

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
import com.skytala.eCommerce.domain.product.relations.reorderGuideline.command.AddReorderGuideline;
import com.skytala.eCommerce.domain.product.relations.reorderGuideline.command.DeleteReorderGuideline;
import com.skytala.eCommerce.domain.product.relations.reorderGuideline.command.UpdateReorderGuideline;
import com.skytala.eCommerce.domain.product.relations.reorderGuideline.event.ReorderGuidelineAdded;
import com.skytala.eCommerce.domain.product.relations.reorderGuideline.event.ReorderGuidelineDeleted;
import com.skytala.eCommerce.domain.product.relations.reorderGuideline.event.ReorderGuidelineFound;
import com.skytala.eCommerce.domain.product.relations.reorderGuideline.event.ReorderGuidelineUpdated;
import com.skytala.eCommerce.domain.product.relations.reorderGuideline.mapper.ReorderGuidelineMapper;
import com.skytala.eCommerce.domain.product.relations.reorderGuideline.model.ReorderGuideline;
import com.skytala.eCommerce.domain.product.relations.reorderGuideline.query.FindReorderGuidelinesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/product/reorderGuidelines")
public class ReorderGuidelineController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ReorderGuidelineController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ReorderGuideline
	 * @return a List with the ReorderGuidelines
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findReorderGuidelinesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindReorderGuidelinesBy query = new FindReorderGuidelinesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ReorderGuideline> reorderGuidelines =((ReorderGuidelineFound) Scheduler.execute(query).data()).getReorderGuidelines();

		if (reorderGuidelines.size() == 1) {
			return ResponseEntity.ok().body(reorderGuidelines.get(0));
		}

		return ResponseEntity.ok().body(reorderGuidelines);

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
	public ResponseEntity<Object> createReorderGuideline(HttpServletRequest request) throws Exception {

		ReorderGuideline reorderGuidelineToBeAdded = new ReorderGuideline();
		try {
			reorderGuidelineToBeAdded = ReorderGuidelineMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createReorderGuideline(reorderGuidelineToBeAdded);

	}

	/**
	 * creates a new ReorderGuideline entry in the ofbiz database
	 * 
	 * @param reorderGuidelineToBeAdded
	 *            the ReorderGuideline thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createReorderGuideline(@RequestBody ReorderGuideline reorderGuidelineToBeAdded) throws Exception {

		AddReorderGuideline command = new AddReorderGuideline(reorderGuidelineToBeAdded);
		ReorderGuideline reorderGuideline = ((ReorderGuidelineAdded) Scheduler.execute(command).data()).getAddedReorderGuideline();
		
		if (reorderGuideline != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(reorderGuideline);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ReorderGuideline could not be created.");
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
	public boolean updateReorderGuideline(HttpServletRequest request) throws Exception {

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

		ReorderGuideline reorderGuidelineToBeUpdated = new ReorderGuideline();

		try {
			reorderGuidelineToBeUpdated = ReorderGuidelineMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateReorderGuideline(reorderGuidelineToBeUpdated, reorderGuidelineToBeUpdated.getReorderGuidelineId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ReorderGuideline with the specific Id
	 * 
	 * @param reorderGuidelineToBeUpdated
	 *            the ReorderGuideline thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{reorderGuidelineId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateReorderGuideline(@RequestBody ReorderGuideline reorderGuidelineToBeUpdated,
			@PathVariable String reorderGuidelineId) throws Exception {

		reorderGuidelineToBeUpdated.setReorderGuidelineId(reorderGuidelineId);

		UpdateReorderGuideline command = new UpdateReorderGuideline(reorderGuidelineToBeUpdated);

		try {
			if(((ReorderGuidelineUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{reorderGuidelineId}")
	public ResponseEntity<Object> findById(@PathVariable String reorderGuidelineId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("reorderGuidelineId", reorderGuidelineId);
		try {

			Object foundReorderGuideline = findReorderGuidelinesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundReorderGuideline);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{reorderGuidelineId}")
	public ResponseEntity<Object> deleteReorderGuidelineByIdUpdated(@PathVariable String reorderGuidelineId) throws Exception {
		DeleteReorderGuideline command = new DeleteReorderGuideline(reorderGuidelineId);

		try {
			if (((ReorderGuidelineDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ReorderGuideline could not be deleted");

	}

}
