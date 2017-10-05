package com.skytala.eCommerce.domain.trainingRequest;

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
import com.skytala.eCommerce.domain.trainingRequest.command.AddTrainingRequest;
import com.skytala.eCommerce.domain.trainingRequest.command.DeleteTrainingRequest;
import com.skytala.eCommerce.domain.trainingRequest.command.UpdateTrainingRequest;
import com.skytala.eCommerce.domain.trainingRequest.event.TrainingRequestAdded;
import com.skytala.eCommerce.domain.trainingRequest.event.TrainingRequestDeleted;
import com.skytala.eCommerce.domain.trainingRequest.event.TrainingRequestFound;
import com.skytala.eCommerce.domain.trainingRequest.event.TrainingRequestUpdated;
import com.skytala.eCommerce.domain.trainingRequest.mapper.TrainingRequestMapper;
import com.skytala.eCommerce.domain.trainingRequest.model.TrainingRequest;
import com.skytala.eCommerce.domain.trainingRequest.query.FindTrainingRequestsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/trainingRequests")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findTrainingRequestsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindTrainingRequestsBy query = new FindTrainingRequestsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<TrainingRequest> trainingRequests =((TrainingRequestFound) Scheduler.execute(query).data()).getTrainingRequests();

		if (trainingRequests.size() == 1) {
			return ResponseEntity.ok().body(trainingRequests.get(0));
		}

		return ResponseEntity.ok().body(trainingRequests);

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
	public ResponseEntity<Object> createTrainingRequest(HttpServletRequest request) throws Exception {

		TrainingRequest trainingRequestToBeAdded = new TrainingRequest();
		try {
			trainingRequestToBeAdded = TrainingRequestMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createTrainingRequest(trainingRequestToBeAdded);

	}

	/**
	 * creates a new TrainingRequest entry in the ofbiz database
	 * 
	 * @param trainingRequestToBeAdded
	 *            the TrainingRequest thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createTrainingRequest(@RequestBody TrainingRequest trainingRequestToBeAdded) throws Exception {

		AddTrainingRequest command = new AddTrainingRequest(trainingRequestToBeAdded);
		TrainingRequest trainingRequest = ((TrainingRequestAdded) Scheduler.execute(command).data()).getAddedTrainingRequest();
		
		if (trainingRequest != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(trainingRequest);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("TrainingRequest could not be created.");
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
	public boolean updateTrainingRequest(HttpServletRequest request) throws Exception {

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

		TrainingRequest trainingRequestToBeUpdated = new TrainingRequest();

		try {
			trainingRequestToBeUpdated = TrainingRequestMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateTrainingRequest(trainingRequestToBeUpdated, trainingRequestToBeUpdated.getTrainingRequestId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateTrainingRequest(@RequestBody TrainingRequest trainingRequestToBeUpdated,
			@PathVariable String trainingRequestId) throws Exception {

		trainingRequestToBeUpdated.setTrainingRequestId(trainingRequestId);

		UpdateTrainingRequest command = new UpdateTrainingRequest(trainingRequestToBeUpdated);

		try {
			if(((TrainingRequestUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{trainingRequestId}")
	public ResponseEntity<Object> findById(@PathVariable String trainingRequestId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("trainingRequestId", trainingRequestId);
		try {

			Object foundTrainingRequest = findTrainingRequestsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundTrainingRequest);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{trainingRequestId}")
	public ResponseEntity<Object> deleteTrainingRequestByIdUpdated(@PathVariable String trainingRequestId) throws Exception {
		DeleteTrainingRequest command = new DeleteTrainingRequest(trainingRequestId);

		try {
			if (((TrainingRequestDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("TrainingRequest could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/trainingRequest/\" plus one of the following: "
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
