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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	public ResponseEntity<List<ReorderGuideline>> findReorderGuidelinesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindReorderGuidelinesBy query = new FindReorderGuidelinesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ReorderGuideline> reorderGuidelines =((ReorderGuidelineFound) Scheduler.execute(query).data()).getReorderGuidelines();

		return ResponseEntity.ok().body(reorderGuidelines);

	}

	/**
	 * creates a new ReorderGuideline entry in the ofbiz database
	 * 
	 * @param reorderGuidelineToBeAdded
	 *            the ReorderGuideline thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ReorderGuideline> createReorderGuideline(@RequestBody ReorderGuideline reorderGuidelineToBeAdded) throws Exception {

		AddReorderGuideline command = new AddReorderGuideline(reorderGuidelineToBeAdded);
		ReorderGuideline reorderGuideline = ((ReorderGuidelineAdded) Scheduler.execute(command).data()).getAddedReorderGuideline();
		
		if (reorderGuideline != null) 
			return successful(reorderGuideline);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateReorderGuideline(@RequestBody ReorderGuideline reorderGuidelineToBeUpdated,
			@PathVariable String reorderGuidelineId) throws Exception {

		reorderGuidelineToBeUpdated.setReorderGuidelineId(reorderGuidelineId);

		UpdateReorderGuideline command = new UpdateReorderGuideline(reorderGuidelineToBeUpdated);

		try {
			if(((ReorderGuidelineUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{reorderGuidelineId}")
	public ResponseEntity<ReorderGuideline> findById(@PathVariable String reorderGuidelineId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("reorderGuidelineId", reorderGuidelineId);
		try {

			List<ReorderGuideline> foundReorderGuideline = findReorderGuidelinesBy(requestParams).getBody();
			if(foundReorderGuideline.size()==1){				return successful(foundReorderGuideline.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{reorderGuidelineId}")
	public ResponseEntity<String> deleteReorderGuidelineByIdUpdated(@PathVariable String reorderGuidelineId) throws Exception {
		DeleteReorderGuideline command = new DeleteReorderGuideline(reorderGuidelineId);

		try {
			if (((ReorderGuidelineDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
