package com.skytala.eCommerce.domain.humanres.relations.trainingRequest;

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
import com.skytala.eCommerce.domain.humanres.relations.trainingRequest.command.AddTrainingRequest;
import com.skytala.eCommerce.domain.humanres.relations.trainingRequest.command.DeleteTrainingRequest;
import com.skytala.eCommerce.domain.humanres.relations.trainingRequest.command.UpdateTrainingRequest;
import com.skytala.eCommerce.domain.humanres.relations.trainingRequest.event.TrainingRequestAdded;
import com.skytala.eCommerce.domain.humanres.relations.trainingRequest.event.TrainingRequestDeleted;
import com.skytala.eCommerce.domain.humanres.relations.trainingRequest.event.TrainingRequestFound;
import com.skytala.eCommerce.domain.humanres.relations.trainingRequest.event.TrainingRequestUpdated;
import com.skytala.eCommerce.domain.humanres.relations.trainingRequest.mapper.TrainingRequestMapper;
import com.skytala.eCommerce.domain.humanres.relations.trainingRequest.model.TrainingRequest;
import com.skytala.eCommerce.domain.humanres.relations.trainingRequest.query.FindTrainingRequestsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/humanres/trainingRequests")
public class TrainingRequestController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public TrainingRequestController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a TrainingRequest
	 * @return a List with the TrainingRequests
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<TrainingRequest>> findTrainingRequestsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindTrainingRequestsBy query = new FindTrainingRequestsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<TrainingRequest> trainingRequests =((TrainingRequestFound) Scheduler.execute(query).data()).getTrainingRequests();

		return ResponseEntity.ok().body(trainingRequests);

	}

	/**
	 * creates a new TrainingRequest entry in the ofbiz database
	 * 
	 * @param trainingRequestToBeAdded
	 *            the TrainingRequest thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<TrainingRequest> createTrainingRequest(@RequestBody TrainingRequest trainingRequestToBeAdded) throws Exception {

		AddTrainingRequest command = new AddTrainingRequest(trainingRequestToBeAdded);
		TrainingRequest trainingRequest = ((TrainingRequestAdded) Scheduler.execute(command).data()).getAddedTrainingRequest();
		
		if (trainingRequest != null) 
			return successful(trainingRequest);
		else 
			return conflict(null);
	}

	/**
	 * Updates the TrainingRequest with the specific Id
	 * 
	 * @param trainingRequestToBeUpdated
	 *            the TrainingRequest thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{trainingRequestId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateTrainingRequest(@RequestBody TrainingRequest trainingRequestToBeUpdated,
			@PathVariable String trainingRequestId) throws Exception {

		trainingRequestToBeUpdated.setTrainingRequestId(trainingRequestId);

		UpdateTrainingRequest command = new UpdateTrainingRequest(trainingRequestToBeUpdated);

		try {
			if(((TrainingRequestUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{trainingRequestId}")
	public ResponseEntity<TrainingRequest> findById(@PathVariable String trainingRequestId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("trainingRequestId", trainingRequestId);
		try {

			List<TrainingRequest> foundTrainingRequest = findTrainingRequestsBy(requestParams).getBody();
			if(foundTrainingRequest.size()==1){				return successful(foundTrainingRequest.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{trainingRequestId}")
	public ResponseEntity<String> deleteTrainingRequestByIdUpdated(@PathVariable String trainingRequestId) throws Exception {
		DeleteTrainingRequest command = new DeleteTrainingRequest(trainingRequestId);

		try {
			if (((TrainingRequestDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
