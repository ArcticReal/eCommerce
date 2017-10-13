package com.skytala.eCommerce.domain.humanres.relations.emplPositionType;

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
import com.skytala.eCommerce.domain.humanres.relations.emplPositionType.command.AddEmplPositionType;
import com.skytala.eCommerce.domain.humanres.relations.emplPositionType.command.DeleteEmplPositionType;
import com.skytala.eCommerce.domain.humanres.relations.emplPositionType.command.UpdateEmplPositionType;
import com.skytala.eCommerce.domain.humanres.relations.emplPositionType.event.EmplPositionTypeAdded;
import com.skytala.eCommerce.domain.humanres.relations.emplPositionType.event.EmplPositionTypeDeleted;
import com.skytala.eCommerce.domain.humanres.relations.emplPositionType.event.EmplPositionTypeFound;
import com.skytala.eCommerce.domain.humanres.relations.emplPositionType.event.EmplPositionTypeUpdated;
import com.skytala.eCommerce.domain.humanres.relations.emplPositionType.mapper.EmplPositionTypeMapper;
import com.skytala.eCommerce.domain.humanres.relations.emplPositionType.model.EmplPositionType;
import com.skytala.eCommerce.domain.humanres.relations.emplPositionType.query.FindEmplPositionTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/emplPositionTypes")
public class EmplPositionTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public EmplPositionTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a EmplPositionType
	 * @return a List with the EmplPositionTypes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findEmplPositionTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindEmplPositionTypesBy query = new FindEmplPositionTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<EmplPositionType> emplPositionTypes =((EmplPositionTypeFound) Scheduler.execute(query).data()).getEmplPositionTypes();

		if (emplPositionTypes.size() == 1) {
			return ResponseEntity.ok().body(emplPositionTypes.get(0));
		}

		return ResponseEntity.ok().body(emplPositionTypes);

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
	public ResponseEntity<Object> createEmplPositionType(HttpServletRequest request) throws Exception {

		EmplPositionType emplPositionTypeToBeAdded = new EmplPositionType();
		try {
			emplPositionTypeToBeAdded = EmplPositionTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createEmplPositionType(emplPositionTypeToBeAdded);

	}

	/**
	 * creates a new EmplPositionType entry in the ofbiz database
	 * 
	 * @param emplPositionTypeToBeAdded
	 *            the EmplPositionType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createEmplPositionType(@RequestBody EmplPositionType emplPositionTypeToBeAdded) throws Exception {

		AddEmplPositionType command = new AddEmplPositionType(emplPositionTypeToBeAdded);
		EmplPositionType emplPositionType = ((EmplPositionTypeAdded) Scheduler.execute(command).data()).getAddedEmplPositionType();
		
		if (emplPositionType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(emplPositionType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("EmplPositionType could not be created.");
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
	public boolean updateEmplPositionType(HttpServletRequest request) throws Exception {

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

		EmplPositionType emplPositionTypeToBeUpdated = new EmplPositionType();

		try {
			emplPositionTypeToBeUpdated = EmplPositionTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateEmplPositionType(emplPositionTypeToBeUpdated, emplPositionTypeToBeUpdated.getEmplPositionTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the EmplPositionType with the specific Id
	 * 
	 * @param emplPositionTypeToBeUpdated
	 *            the EmplPositionType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{emplPositionTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateEmplPositionType(@RequestBody EmplPositionType emplPositionTypeToBeUpdated,
			@PathVariable String emplPositionTypeId) throws Exception {

		emplPositionTypeToBeUpdated.setEmplPositionTypeId(emplPositionTypeId);

		UpdateEmplPositionType command = new UpdateEmplPositionType(emplPositionTypeToBeUpdated);

		try {
			if(((EmplPositionTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{emplPositionTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String emplPositionTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("emplPositionTypeId", emplPositionTypeId);
		try {

			Object foundEmplPositionType = findEmplPositionTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundEmplPositionType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{emplPositionTypeId}")
	public ResponseEntity<Object> deleteEmplPositionTypeByIdUpdated(@PathVariable String emplPositionTypeId) throws Exception {
		DeleteEmplPositionType command = new DeleteEmplPositionType(emplPositionTypeId);

		try {
			if (((EmplPositionTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("EmplPositionType could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/emplPositionType/\" plus one of the following: "
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