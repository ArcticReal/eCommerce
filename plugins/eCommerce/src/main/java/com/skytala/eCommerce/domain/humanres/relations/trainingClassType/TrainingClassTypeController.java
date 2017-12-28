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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/humanres/trainingClassTypes")
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
	@GetMapping("/find")
	public ResponseEntity<List<TrainingClassType>> findTrainingClassTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindTrainingClassTypesBy query = new FindTrainingClassTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<TrainingClassType> trainingClassTypes =((TrainingClassTypeFound) Scheduler.execute(query).data()).getTrainingClassTypes();

		return ResponseEntity.ok().body(trainingClassTypes);

	}

	/**
	 * creates a new TrainingClassType entry in the ofbiz database
	 * 
	 * @param trainingClassTypeToBeAdded
	 *            the TrainingClassType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<TrainingClassType> createTrainingClassType(@RequestBody TrainingClassType trainingClassTypeToBeAdded) throws Exception {

		AddTrainingClassType command = new AddTrainingClassType(trainingClassTypeToBeAdded);
		TrainingClassType trainingClassType = ((TrainingClassTypeAdded) Scheduler.execute(command).data()).getAddedTrainingClassType();
		
		if (trainingClassType != null) 
			return successful(trainingClassType);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateTrainingClassType(@RequestBody TrainingClassType trainingClassTypeToBeUpdated,
			@PathVariable String trainingClassTypeId) throws Exception {

		trainingClassTypeToBeUpdated.setTrainingClassTypeId(trainingClassTypeId);

		UpdateTrainingClassType command = new UpdateTrainingClassType(trainingClassTypeToBeUpdated);

		try {
			if(((TrainingClassTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{trainingClassTypeId}")
	public ResponseEntity<TrainingClassType> findById(@PathVariable String trainingClassTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("trainingClassTypeId", trainingClassTypeId);
		try {

			List<TrainingClassType> foundTrainingClassType = findTrainingClassTypesBy(requestParams).getBody();
			if(foundTrainingClassType.size()==1){				return successful(foundTrainingClassType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{trainingClassTypeId}")
	public ResponseEntity<String> deleteTrainingClassTypeByIdUpdated(@PathVariable String trainingClassTypeId) throws Exception {
		DeleteTrainingClassType command = new DeleteTrainingClassType(trainingClassTypeId);

		try {
			if (((TrainingClassTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
