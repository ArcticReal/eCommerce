package com.skytala.eCommerce.domain.humanres.relations.personTraining;

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
import com.skytala.eCommerce.domain.humanres.relations.personTraining.command.AddPersonTraining;
import com.skytala.eCommerce.domain.humanres.relations.personTraining.command.DeletePersonTraining;
import com.skytala.eCommerce.domain.humanres.relations.personTraining.command.UpdatePersonTraining;
import com.skytala.eCommerce.domain.humanres.relations.personTraining.event.PersonTrainingAdded;
import com.skytala.eCommerce.domain.humanres.relations.personTraining.event.PersonTrainingDeleted;
import com.skytala.eCommerce.domain.humanres.relations.personTraining.event.PersonTrainingFound;
import com.skytala.eCommerce.domain.humanres.relations.personTraining.event.PersonTrainingUpdated;
import com.skytala.eCommerce.domain.humanres.relations.personTraining.mapper.PersonTrainingMapper;
import com.skytala.eCommerce.domain.humanres.relations.personTraining.model.PersonTraining;
import com.skytala.eCommerce.domain.humanres.relations.personTraining.query.FindPersonTrainingsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/humanres/personTrainings")
public class PersonTrainingController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PersonTrainingController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PersonTraining
	 * @return a List with the PersonTrainings
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<PersonTraining>> findPersonTrainingsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPersonTrainingsBy query = new FindPersonTrainingsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PersonTraining> personTrainings =((PersonTrainingFound) Scheduler.execute(query).data()).getPersonTrainings();

		return ResponseEntity.ok().body(personTrainings);

	}

	/**
	 * creates a new PersonTraining entry in the ofbiz database
	 * 
	 * @param personTrainingToBeAdded
	 *            the PersonTraining thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PersonTraining> createPersonTraining(@RequestBody PersonTraining personTrainingToBeAdded) throws Exception {

		AddPersonTraining command = new AddPersonTraining(personTrainingToBeAdded);
		PersonTraining personTraining = ((PersonTrainingAdded) Scheduler.execute(command).data()).getAddedPersonTraining();
		
		if (personTraining != null) 
			return successful(personTraining);
		else 
			return conflict(null);
	}

	/**
	 * Updates the PersonTraining with the specific Id
	 * 
	 * @param personTrainingToBeUpdated
	 *            the PersonTraining thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updatePersonTraining(@RequestBody PersonTraining personTrainingToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		personTrainingToBeUpdated.setnull(null);

		UpdatePersonTraining command = new UpdatePersonTraining(personTrainingToBeUpdated);

		try {
			if(((PersonTrainingUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{personTrainingId}")
	public ResponseEntity<PersonTraining> findById(@PathVariable String personTrainingId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("personTrainingId", personTrainingId);
		try {

			List<PersonTraining> foundPersonTraining = findPersonTrainingsBy(requestParams).getBody();
			if(foundPersonTraining.size()==1){				return successful(foundPersonTraining.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{personTrainingId}")
	public ResponseEntity<String> deletePersonTrainingByIdUpdated(@PathVariable String personTrainingId) throws Exception {
		DeletePersonTraining command = new DeletePersonTraining(personTrainingId);

		try {
			if (((PersonTrainingDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
