package com.skytala.eCommerce.domain.humanres.relations.perfReview.control.item;

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
import com.skytala.eCommerce.domain.humanres.relations.perfReview.command.item.AddPerfReviewItem;
import com.skytala.eCommerce.domain.humanres.relations.perfReview.command.item.DeletePerfReviewItem;
import com.skytala.eCommerce.domain.humanres.relations.perfReview.command.item.UpdatePerfReviewItem;
import com.skytala.eCommerce.domain.humanres.relations.perfReview.event.item.PerfReviewItemAdded;
import com.skytala.eCommerce.domain.humanres.relations.perfReview.event.item.PerfReviewItemDeleted;
import com.skytala.eCommerce.domain.humanres.relations.perfReview.event.item.PerfReviewItemFound;
import com.skytala.eCommerce.domain.humanres.relations.perfReview.event.item.PerfReviewItemUpdated;
import com.skytala.eCommerce.domain.humanres.relations.perfReview.mapper.item.PerfReviewItemMapper;
import com.skytala.eCommerce.domain.humanres.relations.perfReview.model.item.PerfReviewItem;
import com.skytala.eCommerce.domain.humanres.relations.perfReview.query.item.FindPerfReviewItemsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/humanres/perfReview/perfReviewItems")
public class PerfReviewItemController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PerfReviewItemController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PerfReviewItem
	 * @return a List with the PerfReviewItems
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<PerfReviewItem>> findPerfReviewItemsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPerfReviewItemsBy query = new FindPerfReviewItemsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PerfReviewItem> perfReviewItems =((PerfReviewItemFound) Scheduler.execute(query).data()).getPerfReviewItems();

		return ResponseEntity.ok().body(perfReviewItems);

	}

	/**
	 * creates a new PerfReviewItem entry in the ofbiz database
	 * 
	 * @param perfReviewItemToBeAdded
	 *            the PerfReviewItem thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PerfReviewItem> createPerfReviewItem(@RequestBody PerfReviewItem perfReviewItemToBeAdded) throws Exception {

		AddPerfReviewItem command = new AddPerfReviewItem(perfReviewItemToBeAdded);
		PerfReviewItem perfReviewItem = ((PerfReviewItemAdded) Scheduler.execute(command).data()).getAddedPerfReviewItem();
		
		if (perfReviewItem != null) 
			return successful(perfReviewItem);
		else 
			return conflict(null);
	}

	/**
	 * Updates the PerfReviewItem with the specific Id
	 * 
	 * @param perfReviewItemToBeUpdated
	 *            the PerfReviewItem thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updatePerfReviewItem(@RequestBody PerfReviewItem perfReviewItemToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		perfReviewItemToBeUpdated.setnull(null);

		UpdatePerfReviewItem command = new UpdatePerfReviewItem(perfReviewItemToBeUpdated);

		try {
			if(((PerfReviewItemUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{perfReviewItemId}")
	public ResponseEntity<PerfReviewItem> findById(@PathVariable String perfReviewItemId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("perfReviewItemId", perfReviewItemId);
		try {

			List<PerfReviewItem> foundPerfReviewItem = findPerfReviewItemsBy(requestParams).getBody();
			if(foundPerfReviewItem.size()==1){				return successful(foundPerfReviewItem.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{perfReviewItemId}")
	public ResponseEntity<String> deletePerfReviewItemByIdUpdated(@PathVariable String perfReviewItemId) throws Exception {
		DeletePerfReviewItem command = new DeletePerfReviewItem(perfReviewItemId);

		try {
			if (((PerfReviewItemDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
