package com.skytala.eCommerce.domain.humanres.relations.emplPosition.control.responsibility;

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
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.command.responsibility.AddEmplPositionResponsibility;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.command.responsibility.DeleteEmplPositionResponsibility;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.command.responsibility.UpdateEmplPositionResponsibility;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.event.responsibility.EmplPositionResponsibilityAdded;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.event.responsibility.EmplPositionResponsibilityDeleted;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.event.responsibility.EmplPositionResponsibilityFound;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.event.responsibility.EmplPositionResponsibilityUpdated;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.mapper.responsibility.EmplPositionResponsibilityMapper;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.model.responsibility.EmplPositionResponsibility;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.query.responsibility.FindEmplPositionResponsibilitysBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/humanres/emplPosition/emplPositionResponsibilitys")
public class EmplPositionResponsibilityController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public EmplPositionResponsibilityController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a EmplPositionResponsibility
	 * @return a List with the EmplPositionResponsibilitys
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<EmplPositionResponsibility>> findEmplPositionResponsibilitysBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindEmplPositionResponsibilitysBy query = new FindEmplPositionResponsibilitysBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<EmplPositionResponsibility> emplPositionResponsibilitys =((EmplPositionResponsibilityFound) Scheduler.execute(query).data()).getEmplPositionResponsibilitys();

		return ResponseEntity.ok().body(emplPositionResponsibilitys);

	}

	/**
	 * creates a new EmplPositionResponsibility entry in the ofbiz database
	 * 
	 * @param emplPositionResponsibilityToBeAdded
	 *            the EmplPositionResponsibility thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<EmplPositionResponsibility> createEmplPositionResponsibility(@RequestBody EmplPositionResponsibility emplPositionResponsibilityToBeAdded) throws Exception {

		AddEmplPositionResponsibility command = new AddEmplPositionResponsibility(emplPositionResponsibilityToBeAdded);
		EmplPositionResponsibility emplPositionResponsibility = ((EmplPositionResponsibilityAdded) Scheduler.execute(command).data()).getAddedEmplPositionResponsibility();
		
		if (emplPositionResponsibility != null) 
			return successful(emplPositionResponsibility);
		else 
			return conflict(null);
	}

	/**
	 * Updates the EmplPositionResponsibility with the specific Id
	 * 
	 * @param emplPositionResponsibilityToBeUpdated
	 *            the EmplPositionResponsibility thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateEmplPositionResponsibility(@RequestBody EmplPositionResponsibility emplPositionResponsibilityToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		emplPositionResponsibilityToBeUpdated.setnull(null);

		UpdateEmplPositionResponsibility command = new UpdateEmplPositionResponsibility(emplPositionResponsibilityToBeUpdated);

		try {
			if(((EmplPositionResponsibilityUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{emplPositionResponsibilityId}")
	public ResponseEntity<EmplPositionResponsibility> findById(@PathVariable String emplPositionResponsibilityId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("emplPositionResponsibilityId", emplPositionResponsibilityId);
		try {

			List<EmplPositionResponsibility> foundEmplPositionResponsibility = findEmplPositionResponsibilitysBy(requestParams).getBody();
			if(foundEmplPositionResponsibility.size()==1){				return successful(foundEmplPositionResponsibility.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{emplPositionResponsibilityId}")
	public ResponseEntity<String> deleteEmplPositionResponsibilityByIdUpdated(@PathVariable String emplPositionResponsibilityId) throws Exception {
		DeleteEmplPositionResponsibility command = new DeleteEmplPositionResponsibility(emplPositionResponsibilityId);

		try {
			if (((EmplPositionResponsibilityDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
