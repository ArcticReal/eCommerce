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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@RequestMapping("/emplPositions")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findEmplPositionsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindEmplPositionsBy query = new FindEmplPositionsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<EmplPosition> emplPositions =((EmplPositionFound) Scheduler.execute(query).data()).getEmplPositions();

		if (emplPositions.size() == 1) {
			return ResponseEntity.ok().body(emplPositions.get(0));
		}

		return ResponseEntity.ok().body(emplPositions);

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
	public ResponseEntity<Object> createEmplPosition(HttpServletRequest request) throws Exception {

		EmplPosition emplPositionToBeAdded = new EmplPosition();
		try {
			emplPositionToBeAdded = EmplPositionMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createEmplPosition(emplPositionToBeAdded);

	}

	/**
	 * creates a new EmplPosition entry in the ofbiz database
	 * 
	 * @param emplPositionToBeAdded
	 *            the EmplPosition thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createEmplPosition(@RequestBody EmplPosition emplPositionToBeAdded) throws Exception {

		AddEmplPosition command = new AddEmplPosition(emplPositionToBeAdded);
		EmplPosition emplPosition = ((EmplPositionAdded) Scheduler.execute(command).data()).getAddedEmplPosition();
		
		if (emplPosition != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(emplPosition);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("EmplPosition could not be created.");
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
	public boolean updateEmplPosition(HttpServletRequest request) throws Exception {

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

		EmplPosition emplPositionToBeUpdated = new EmplPosition();

		try {
			emplPositionToBeUpdated = EmplPositionMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateEmplPosition(emplPositionToBeUpdated, emplPositionToBeUpdated.getEmplPositionId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateEmplPosition(@RequestBody EmplPosition emplPositionToBeUpdated,
			@PathVariable String emplPositionId) throws Exception {

		emplPositionToBeUpdated.setEmplPositionId(emplPositionId);

		UpdateEmplPosition command = new UpdateEmplPosition(emplPositionToBeUpdated);

		try {
			if(((EmplPositionUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{emplPositionId}")
	public ResponseEntity<Object> findById(@PathVariable String emplPositionId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("emplPositionId", emplPositionId);
		try {

			Object foundEmplPosition = findEmplPositionsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundEmplPosition);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{emplPositionId}")
	public ResponseEntity<Object> deleteEmplPositionByIdUpdated(@PathVariable String emplPositionId) throws Exception {
		DeleteEmplPosition command = new DeleteEmplPosition(emplPositionId);

		try {
			if (((EmplPositionDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("EmplPosition could not be deleted");

	}

}
