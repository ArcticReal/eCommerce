package com.skytala.eCommerce.domain.humanres.relations.emplPosition.control.classType;

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
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.command.classType.AddEmplPositionClassType;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.command.classType.DeleteEmplPositionClassType;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.command.classType.UpdateEmplPositionClassType;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.event.classType.EmplPositionClassTypeAdded;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.event.classType.EmplPositionClassTypeDeleted;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.event.classType.EmplPositionClassTypeFound;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.event.classType.EmplPositionClassTypeUpdated;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.mapper.classType.EmplPositionClassTypeMapper;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.model.classType.EmplPositionClassType;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.query.classType.FindEmplPositionClassTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/emplPositionClassTypes")
public class EmplPositionClassTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public EmplPositionClassTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a EmplPositionClassType
	 * @return a List with the EmplPositionClassTypes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findEmplPositionClassTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindEmplPositionClassTypesBy query = new FindEmplPositionClassTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<EmplPositionClassType> emplPositionClassTypes =((EmplPositionClassTypeFound) Scheduler.execute(query).data()).getEmplPositionClassTypes();

		if (emplPositionClassTypes.size() == 1) {
			return ResponseEntity.ok().body(emplPositionClassTypes.get(0));
		}

		return ResponseEntity.ok().body(emplPositionClassTypes);

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
	public ResponseEntity<Object> createEmplPositionClassType(HttpServletRequest request) throws Exception {

		EmplPositionClassType emplPositionClassTypeToBeAdded = new EmplPositionClassType();
		try {
			emplPositionClassTypeToBeAdded = EmplPositionClassTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createEmplPositionClassType(emplPositionClassTypeToBeAdded);

	}

	/**
	 * creates a new EmplPositionClassType entry in the ofbiz database
	 * 
	 * @param emplPositionClassTypeToBeAdded
	 *            the EmplPositionClassType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createEmplPositionClassType(@RequestBody EmplPositionClassType emplPositionClassTypeToBeAdded) throws Exception {

		AddEmplPositionClassType command = new AddEmplPositionClassType(emplPositionClassTypeToBeAdded);
		EmplPositionClassType emplPositionClassType = ((EmplPositionClassTypeAdded) Scheduler.execute(command).data()).getAddedEmplPositionClassType();
		
		if (emplPositionClassType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(emplPositionClassType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("EmplPositionClassType could not be created.");
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
	public boolean updateEmplPositionClassType(HttpServletRequest request) throws Exception {

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

		EmplPositionClassType emplPositionClassTypeToBeUpdated = new EmplPositionClassType();

		try {
			emplPositionClassTypeToBeUpdated = EmplPositionClassTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateEmplPositionClassType(emplPositionClassTypeToBeUpdated, emplPositionClassTypeToBeUpdated.getEmplPositionClassTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the EmplPositionClassType with the specific Id
	 * 
	 * @param emplPositionClassTypeToBeUpdated
	 *            the EmplPositionClassType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{emplPositionClassTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateEmplPositionClassType(@RequestBody EmplPositionClassType emplPositionClassTypeToBeUpdated,
			@PathVariable String emplPositionClassTypeId) throws Exception {

		emplPositionClassTypeToBeUpdated.setEmplPositionClassTypeId(emplPositionClassTypeId);

		UpdateEmplPositionClassType command = new UpdateEmplPositionClassType(emplPositionClassTypeToBeUpdated);

		try {
			if(((EmplPositionClassTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{emplPositionClassTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String emplPositionClassTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("emplPositionClassTypeId", emplPositionClassTypeId);
		try {

			Object foundEmplPositionClassType = findEmplPositionClassTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundEmplPositionClassType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{emplPositionClassTypeId}")
	public ResponseEntity<Object> deleteEmplPositionClassTypeByIdUpdated(@PathVariable String emplPositionClassTypeId) throws Exception {
		DeleteEmplPositionClassType command = new DeleteEmplPositionClassType(emplPositionClassTypeId);

		try {
			if (((EmplPositionClassTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("EmplPositionClassType could not be deleted");

	}

}
