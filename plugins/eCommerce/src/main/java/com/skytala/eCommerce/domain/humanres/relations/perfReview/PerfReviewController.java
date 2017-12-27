package com.skytala.eCommerce.domain.humanres.relations.perfReview;

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
import com.skytala.eCommerce.domain.humanres.relations.perfReview.command.AddPerfReview;
import com.skytala.eCommerce.domain.humanres.relations.perfReview.command.DeletePerfReview;
import com.skytala.eCommerce.domain.humanres.relations.perfReview.command.UpdatePerfReview;
import com.skytala.eCommerce.domain.humanres.relations.perfReview.event.PerfReviewAdded;
import com.skytala.eCommerce.domain.humanres.relations.perfReview.event.PerfReviewDeleted;
import com.skytala.eCommerce.domain.humanres.relations.perfReview.event.PerfReviewFound;
import com.skytala.eCommerce.domain.humanres.relations.perfReview.event.PerfReviewUpdated;
import com.skytala.eCommerce.domain.humanres.relations.perfReview.mapper.PerfReviewMapper;
import com.skytala.eCommerce.domain.humanres.relations.perfReview.model.PerfReview;
import com.skytala.eCommerce.domain.humanres.relations.perfReview.query.FindPerfReviewsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/humanres/perfReviews")
public class PerfReviewController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PerfReviewController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PerfReview
	 * @return a List with the PerfReviews
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<PerfReview>> findPerfReviewsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPerfReviewsBy query = new FindPerfReviewsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PerfReview> perfReviews =((PerfReviewFound) Scheduler.execute(query).data()).getPerfReviews();

		return ResponseEntity.ok().body(perfReviews);

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
	public ResponseEntity<PerfReview> createPerfReview(HttpServletRequest request) throws Exception {

		PerfReview perfReviewToBeAdded = new PerfReview();
		try {
			perfReviewToBeAdded = PerfReviewMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createPerfReview(perfReviewToBeAdded);

	}

	/**
	 * creates a new PerfReview entry in the ofbiz database
	 * 
	 * @param perfReviewToBeAdded
	 *            the PerfReview thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PerfReview> createPerfReview(@RequestBody PerfReview perfReviewToBeAdded) throws Exception {

		AddPerfReview command = new AddPerfReview(perfReviewToBeAdded);
		PerfReview perfReview = ((PerfReviewAdded) Scheduler.execute(command).data()).getAddedPerfReview();
		
		if (perfReview != null) 
			return successful(perfReview);
		else 
			return conflict(null);
	}

	/**
	 * Updates the PerfReview with the specific Id
	 * 
	 * @param perfReviewToBeUpdated
	 *            the PerfReview thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{perfReviewId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updatePerfReview(@RequestBody PerfReview perfReviewToBeUpdated,
			@PathVariable String perfReviewId) throws Exception {

		perfReviewToBeUpdated.setPerfReviewId(perfReviewId);

		UpdatePerfReview command = new UpdatePerfReview(perfReviewToBeUpdated);

		try {
			if(((PerfReviewUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{perfReviewId}")
	public ResponseEntity<PerfReview> findById(@PathVariable String perfReviewId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("perfReviewId", perfReviewId);
		try {

			List<PerfReview> foundPerfReview = findPerfReviewsBy(requestParams).getBody();
			if(foundPerfReview.size()==1){				return successful(foundPerfReview.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{perfReviewId}")
	public ResponseEntity<String> deletePerfReviewByIdUpdated(@PathVariable String perfReviewId) throws Exception {
		DeletePerfReview command = new DeletePerfReview(perfReviewId);

		try {
			if (((PerfReviewDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
