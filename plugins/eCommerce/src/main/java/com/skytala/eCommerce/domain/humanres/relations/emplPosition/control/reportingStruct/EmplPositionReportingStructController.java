package com.skytala.eCommerce.domain.humanres.relations.emplPosition.control.reportingStruct;

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
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.command.reportingStruct.AddEmplPositionReportingStruct;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.command.reportingStruct.DeleteEmplPositionReportingStruct;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.command.reportingStruct.UpdateEmplPositionReportingStruct;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.event.reportingStruct.EmplPositionReportingStructAdded;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.event.reportingStruct.EmplPositionReportingStructDeleted;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.event.reportingStruct.EmplPositionReportingStructFound;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.event.reportingStruct.EmplPositionReportingStructUpdated;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.mapper.reportingStruct.EmplPositionReportingStructMapper;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.model.reportingStruct.EmplPositionReportingStruct;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.query.reportingStruct.FindEmplPositionReportingStructsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/humanres/emplPosition/emplPositionReportingStructs")
public class EmplPositionReportingStructController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public EmplPositionReportingStructController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a EmplPositionReportingStruct
	 * @return a List with the EmplPositionReportingStructs
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<EmplPositionReportingStruct>> findEmplPositionReportingStructsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindEmplPositionReportingStructsBy query = new FindEmplPositionReportingStructsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<EmplPositionReportingStruct> emplPositionReportingStructs =((EmplPositionReportingStructFound) Scheduler.execute(query).data()).getEmplPositionReportingStructs();

		return ResponseEntity.ok().body(emplPositionReportingStructs);

	}

	/**
	 * creates a new EmplPositionReportingStruct entry in the ofbiz database
	 * 
	 * @param emplPositionReportingStructToBeAdded
	 *            the EmplPositionReportingStruct thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<EmplPositionReportingStruct> createEmplPositionReportingStruct(@RequestBody EmplPositionReportingStruct emplPositionReportingStructToBeAdded) throws Exception {

		AddEmplPositionReportingStruct command = new AddEmplPositionReportingStruct(emplPositionReportingStructToBeAdded);
		EmplPositionReportingStruct emplPositionReportingStruct = ((EmplPositionReportingStructAdded) Scheduler.execute(command).data()).getAddedEmplPositionReportingStruct();
		
		if (emplPositionReportingStruct != null) 
			return successful(emplPositionReportingStruct);
		else 
			return conflict(null);
	}

	/**
	 * Updates the EmplPositionReportingStruct with the specific Id
	 * 
	 * @param emplPositionReportingStructToBeUpdated
	 *            the EmplPositionReportingStruct thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateEmplPositionReportingStruct(@RequestBody EmplPositionReportingStruct emplPositionReportingStructToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		emplPositionReportingStructToBeUpdated.setnull(null);

		UpdateEmplPositionReportingStruct command = new UpdateEmplPositionReportingStruct(emplPositionReportingStructToBeUpdated);

		try {
			if(((EmplPositionReportingStructUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{emplPositionReportingStructId}")
	public ResponseEntity<EmplPositionReportingStruct> findById(@PathVariable String emplPositionReportingStructId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("emplPositionReportingStructId", emplPositionReportingStructId);
		try {

			List<EmplPositionReportingStruct> foundEmplPositionReportingStruct = findEmplPositionReportingStructsBy(requestParams).getBody();
			if(foundEmplPositionReportingStruct.size()==1){				return successful(foundEmplPositionReportingStruct.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{emplPositionReportingStructId}")
	public ResponseEntity<String> deleteEmplPositionReportingStructByIdUpdated(@PathVariable String emplPositionReportingStructId) throws Exception {
		DeleteEmplPositionReportingStruct command = new DeleteEmplPositionReportingStruct(emplPositionReportingStructId);

		try {
			if (((EmplPositionReportingStructDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
