package com.skytala.eCommerce.domain.humanres.relations.terminationType;

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
import com.skytala.eCommerce.domain.humanres.relations.terminationType.command.AddTerminationType;
import com.skytala.eCommerce.domain.humanres.relations.terminationType.command.DeleteTerminationType;
import com.skytala.eCommerce.domain.humanres.relations.terminationType.command.UpdateTerminationType;
import com.skytala.eCommerce.domain.humanres.relations.terminationType.event.TerminationTypeAdded;
import com.skytala.eCommerce.domain.humanres.relations.terminationType.event.TerminationTypeDeleted;
import com.skytala.eCommerce.domain.humanres.relations.terminationType.event.TerminationTypeFound;
import com.skytala.eCommerce.domain.humanres.relations.terminationType.event.TerminationTypeUpdated;
import com.skytala.eCommerce.domain.humanres.relations.terminationType.mapper.TerminationTypeMapper;
import com.skytala.eCommerce.domain.humanres.relations.terminationType.model.TerminationType;
import com.skytala.eCommerce.domain.humanres.relations.terminationType.query.FindTerminationTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/humanres/terminationTypes")
public class TerminationTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public TerminationTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a TerminationType
	 * @return a List with the TerminationTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findTerminationTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindTerminationTypesBy query = new FindTerminationTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<TerminationType> terminationTypes =((TerminationTypeFound) Scheduler.execute(query).data()).getTerminationTypes();

		if (terminationTypes.size() == 1) {
			return ResponseEntity.ok().body(terminationTypes.get(0));
		}

		return ResponseEntity.ok().body(terminationTypes);

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
	public ResponseEntity<Object> createTerminationType(HttpServletRequest request) throws Exception {

		TerminationType terminationTypeToBeAdded = new TerminationType();
		try {
			terminationTypeToBeAdded = TerminationTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createTerminationType(terminationTypeToBeAdded);

	}

	/**
	 * creates a new TerminationType entry in the ofbiz database
	 * 
	 * @param terminationTypeToBeAdded
	 *            the TerminationType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createTerminationType(@RequestBody TerminationType terminationTypeToBeAdded) throws Exception {

		AddTerminationType command = new AddTerminationType(terminationTypeToBeAdded);
		TerminationType terminationType = ((TerminationTypeAdded) Scheduler.execute(command).data()).getAddedTerminationType();
		
		if (terminationType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(terminationType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("TerminationType could not be created.");
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
	@PutMapping(value = "/update", consumes = "application/x-www-form-urlencoded")
	public boolean updateTerminationType(HttpServletRequest request) throws Exception {

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

		TerminationType terminationTypeToBeUpdated = new TerminationType();

		try {
			terminationTypeToBeUpdated = TerminationTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateTerminationType(terminationTypeToBeUpdated, terminationTypeToBeUpdated.getTerminationTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the TerminationType with the specific Id
	 * 
	 * @param terminationTypeToBeUpdated
	 *            the TerminationType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{terminationTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateTerminationType(@RequestBody TerminationType terminationTypeToBeUpdated,
			@PathVariable String terminationTypeId) throws Exception {

		terminationTypeToBeUpdated.setTerminationTypeId(terminationTypeId);

		UpdateTerminationType command = new UpdateTerminationType(terminationTypeToBeUpdated);

		try {
			if(((TerminationTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{terminationTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String terminationTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("terminationTypeId", terminationTypeId);
		try {

			Object foundTerminationType = findTerminationTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundTerminationType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{terminationTypeId}")
	public ResponseEntity<Object> deleteTerminationTypeByIdUpdated(@PathVariable String terminationTypeId) throws Exception {
		DeleteTerminationType command = new DeleteTerminationType(terminationTypeId);

		try {
			if (((TerminationTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("TerminationType could not be deleted");

	}

}
