package com.skytala.eCommerce.domain.humanres.relations.trainingClassType;

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
import com.skytala.eCommerce.domain.humanres.relations.trainingClassType.command.AddTrainingClassType;
import com.skytala.eCommerce.domain.humanres.relations.trainingClassType.command.DeleteTrainingClassType;
import com.skytala.eCommerce.domain.humanres.relations.trainingClassType.command.UpdateTrainingClassType;
import com.skytala.eCommerce.domain.humanres.relations.trainingClassType.event.TrainingClassTypeAdded;
import com.skytala.eCommerce.domain.humanres.relations.trainingClassType.event.TrainingClassTypeDeleted;
import com.skytala.eCommerce.domain.humanres.relations.trainingClassType.event.TrainingClassTypeFound;
import com.skytala.eCommerce.domain.humanres.relations.trainingClassType.event.TrainingClassTypeUpdated;
import com.skytala.eCommerce.domain.humanres.relations.trainingClassType.mapper.TrainingClassTypeMapper;
import com.skytala.eCommerce.domain.humanres.relations.trainingClassType.model.TrainingClassType;
import com.skytala.eCommerce.domain.humanres.relations.trainingClassType.query.FindTrainingClassTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@CrossOrigin
@RequestMapping("/trainingClassTypes")
public class TrainingClassTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public TrainingClassTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a TrainingClassType
	 * @return a List with the TrainingClassTypes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findTrainingClassTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindTrainingClassTypesBy query = new FindTrainingClassTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<TrainingClassType> trainingClassTypes =((TrainingClassTypeFound) Scheduler.execute(query).data()).getTrainingClassTypes();

		if (trainingClassTypes.size() == 1) {
			return ResponseEntity.ok().body(trainingClassTypes.get(0));
		}

		return ResponseEntity.ok().body(trainingClassTypes);

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
	public ResponseEntity<Object> createTrainingClassType(HttpServletRequest request) throws Exception {

		TrainingClassType trainingClassTypeToBeAdded = new TrainingClassType();
		try {
			trainingClassTypeToBeAdded = TrainingClassTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createTrainingClassType(trainingClassTypeToBeAdded);

	}

	/**
	 * creates a new TrainingClassType entry in the ofbiz database
	 * 
	 * @param trainingClassTypeToBeAdded
	 *            the TrainingClassType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createTrainingClassType(@RequestBody TrainingClassType trainingClassTypeToBeAdded) throws Exception {

		AddTrainingClassType command = new AddTrainingClassType(trainingClassTypeToBeAdded);
		TrainingClassType trainingClassType = ((TrainingClassTypeAdded) Scheduler.execute(command).data()).getAddedTrainingClassType();
		
		if (trainingClassType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(trainingClassType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("TrainingClassType could not be created.");
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
	public boolean updateTrainingClassType(HttpServletRequest request) throws Exception {

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

		TrainingClassType trainingClassTypeToBeUpdated = new TrainingClassType();

		try {
			trainingClassTypeToBeUpdated = TrainingClassTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateTrainingClassType(trainingClassTypeToBeUpdated, trainingClassTypeToBeUpdated.getTrainingClassTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the TrainingClassType with the specific Id
	 * 
	 * @param trainingClassTypeToBeUpdated
	 *            the TrainingClassType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{trainingClassTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateTrainingClassType(@RequestBody TrainingClassType trainingClassTypeToBeUpdated,
			@PathVariable String trainingClassTypeId) throws Exception {

		trainingClassTypeToBeUpdated.setTrainingClassTypeId(trainingClassTypeId);

		UpdateTrainingClassType command = new UpdateTrainingClassType(trainingClassTypeToBeUpdated);

		try {
			if(((TrainingClassTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{trainingClassTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String trainingClassTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("trainingClassTypeId", trainingClassTypeId);
		try {

			Object foundTrainingClassType = findTrainingClassTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundTrainingClassType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{trainingClassTypeId}")
	public ResponseEntity<Object> deleteTrainingClassTypeByIdUpdated(@PathVariable String trainingClassTypeId) throws Exception {
		DeleteTrainingClassType command = new DeleteTrainingClassType(trainingClassTypeId);

		try {
			if (((TrainingClassTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("TrainingClassType could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/trainingClassType/\" plus one of the following: "
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
