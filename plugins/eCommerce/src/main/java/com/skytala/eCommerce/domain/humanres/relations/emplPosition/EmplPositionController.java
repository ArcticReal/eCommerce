package com.skytala.eCommerce.domain.humanres.relations.emplPosition;

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
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.command.AddEmplPosition;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.command.DeleteEmplPosition;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.command.UpdateEmplPosition;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.event.EmplPositionAdded;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.event.EmplPositionDeleted;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.event.EmplPositionFound;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.event.EmplPositionUpdated;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.mapper.EmplPositionMapper;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.model.EmplPosition;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.query.FindEmplPositionsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/humanres/emplPositions")
public class EmplPositionController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public EmplPositionController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a EmplPosition
	 * @return a List with the EmplPositions
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<EmplPosition>> findEmplPositionsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindEmplPositionsBy query = new FindEmplPositionsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<EmplPosition> emplPositions =((EmplPositionFound) Scheduler.execute(query).data()).getEmplPositions();

		return ResponseEntity.ok().body(emplPositions);

	}

	/**
	 * creates a new EmplPosition entry in the ofbiz database
	 * 
	 * @param emplPositionToBeAdded
	 *            the EmplPosition thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<EmplPosition> createEmplPosition(@RequestBody EmplPosition emplPositionToBeAdded) throws Exception {

		AddEmplPosition command = new AddEmplPosition(emplPositionToBeAdded);
		EmplPosition emplPosition = ((EmplPositionAdded) Scheduler.execute(command).data()).getAddedEmplPosition();
		
		if (emplPosition != null) 
			return successful(emplPosition);
		else 
			return conflict(null);
	}

	/**
	 * Updates the EmplPosition with the specific Id
	 * 
	 * @param emplPositionToBeUpdated
	 *            the EmplPosition thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{emplPositionId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateEmplPosition(@RequestBody EmplPosition emplPositionToBeUpdated,
			@PathVariable String emplPositionId) throws Exception {

		emplPositionToBeUpdated.setEmplPositionId(emplPositionId);

		UpdateEmplPosition command = new UpdateEmplPosition(emplPositionToBeUpdated);

		try {
			if(((EmplPositionUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{emplPositionId}")
	public ResponseEntity<EmplPosition> findById(@PathVariable String emplPositionId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("emplPositionId", emplPositionId);
		try {

			List<EmplPosition> foundEmplPosition = findEmplPositionsBy(requestParams).getBody();
			if(foundEmplPosition.size()==1){				return successful(foundEmplPosition.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{emplPositionId}")
	public ResponseEntity<String> deleteEmplPositionByIdUpdated(@PathVariable String emplPositionId) throws Exception {
		DeleteEmplPosition command = new DeleteEmplPosition(emplPositionId);

		try {
			if (((EmplPositionDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
