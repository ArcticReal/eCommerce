package com.skytala.eCommerce.domain.humanres.relations.perfReview.control.itemType;

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
import com.skytala.eCommerce.domain.humanres.relations.perfReview.command.itemType.AddPerfReviewItemType;
import com.skytala.eCommerce.domain.humanres.relations.perfReview.command.itemType.DeletePerfReviewItemType;
import com.skytala.eCommerce.domain.humanres.relations.perfReview.command.itemType.UpdatePerfReviewItemType;
import com.skytala.eCommerce.domain.humanres.relations.perfReview.event.itemType.PerfReviewItemTypeAdded;
import com.skytala.eCommerce.domain.humanres.relations.perfReview.event.itemType.PerfReviewItemTypeDeleted;
import com.skytala.eCommerce.domain.humanres.relations.perfReview.event.itemType.PerfReviewItemTypeFound;
import com.skytala.eCommerce.domain.humanres.relations.perfReview.event.itemType.PerfReviewItemTypeUpdated;
import com.skytala.eCommerce.domain.humanres.relations.perfReview.mapper.itemType.PerfReviewItemTypeMapper;
import com.skytala.eCommerce.domain.humanres.relations.perfReview.model.itemType.PerfReviewItemType;
import com.skytala.eCommerce.domain.humanres.relations.perfReview.query.itemType.FindPerfReviewItemTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/humanres/perfReview/perfReviewItemTypes")
public class PerfReviewItemTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PerfReviewItemTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PerfReviewItemType
	 * @return a List with the PerfReviewItemTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<PerfReviewItemType>> findPerfReviewItemTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPerfReviewItemTypesBy query = new FindPerfReviewItemTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PerfReviewItemType> perfReviewItemTypes =((PerfReviewItemTypeFound) Scheduler.execute(query).data()).getPerfReviewItemTypes();

		return ResponseEntity.ok().body(perfReviewItemTypes);

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
	public ResponseEntity<PerfReviewItemType> createPerfReviewItemType(HttpServletRequest request) throws Exception {

		PerfReviewItemType perfReviewItemTypeToBeAdded = new PerfReviewItemType();
		try {
			perfReviewItemTypeToBeAdded = PerfReviewItemTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createPerfReviewItemType(perfReviewItemTypeToBeAdded);

	}

	/**
	 * creates a new PerfReviewItemType entry in the ofbiz database
	 * 
	 * @param perfReviewItemTypeToBeAdded
	 *            the PerfReviewItemType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PerfReviewItemType> createPerfReviewItemType(@RequestBody PerfReviewItemType perfReviewItemTypeToBeAdded) throws Exception {

		AddPerfReviewItemType command = new AddPerfReviewItemType(perfReviewItemTypeToBeAdded);
		PerfReviewItemType perfReviewItemType = ((PerfReviewItemTypeAdded) Scheduler.execute(command).data()).getAddedPerfReviewItemType();
		
		if (perfReviewItemType != null) 
			return successful(perfReviewItemType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the PerfReviewItemType with the specific Id
	 * 
	 * @param perfReviewItemTypeToBeUpdated
	 *            the PerfReviewItemType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{perfReviewItemTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updatePerfReviewItemType(@RequestBody PerfReviewItemType perfReviewItemTypeToBeUpdated,
			@PathVariable String perfReviewItemTypeId) throws Exception {

		perfReviewItemTypeToBeUpdated.setPerfReviewItemTypeId(perfReviewItemTypeId);

		UpdatePerfReviewItemType command = new UpdatePerfReviewItemType(perfReviewItemTypeToBeUpdated);

		try {
			if(((PerfReviewItemTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{perfReviewItemTypeId}")
	public ResponseEntity<PerfReviewItemType> findById(@PathVariable String perfReviewItemTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("perfReviewItemTypeId", perfReviewItemTypeId);
		try {

			List<PerfReviewItemType> foundPerfReviewItemType = findPerfReviewItemTypesBy(requestParams).getBody();
			if(foundPerfReviewItemType.size()==1){				return successful(foundPerfReviewItemType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{perfReviewItemTypeId}")
	public ResponseEntity<String> deletePerfReviewItemTypeByIdUpdated(@PathVariable String perfReviewItemTypeId) throws Exception {
		DeletePerfReviewItemType command = new DeletePerfReviewItemType(perfReviewItemTypeId);

		try {
			if (((PerfReviewItemTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
