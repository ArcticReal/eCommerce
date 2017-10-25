package com.skytala.eCommerce.domain.humanres.relations.emplPosition.control.typeClass;

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
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.command.typeClass.AddEmplPositionTypeClass;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.command.typeClass.DeleteEmplPositionTypeClass;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.command.typeClass.UpdateEmplPositionTypeClass;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.event.typeClass.EmplPositionTypeClassAdded;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.event.typeClass.EmplPositionTypeClassDeleted;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.event.typeClass.EmplPositionTypeClassFound;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.event.typeClass.EmplPositionTypeClassUpdated;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.mapper.typeClass.EmplPositionTypeClassMapper;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.model.typeClass.EmplPositionTypeClass;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.query.typeClass.FindEmplPositionTypeClasssBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/emplPositionTypeClasss")
public class EmplPositionTypeClassController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public EmplPositionTypeClassController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a EmplPositionTypeClass
	 * @return a List with the EmplPositionTypeClasss
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findEmplPositionTypeClasssBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindEmplPositionTypeClasssBy query = new FindEmplPositionTypeClasssBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<EmplPositionTypeClass> emplPositionTypeClasss =((EmplPositionTypeClassFound) Scheduler.execute(query).data()).getEmplPositionTypeClasss();

		if (emplPositionTypeClasss.size() == 1) {
			return ResponseEntity.ok().body(emplPositionTypeClasss.get(0));
		}

		return ResponseEntity.ok().body(emplPositionTypeClasss);

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
	public ResponseEntity<Object> createEmplPositionTypeClass(HttpServletRequest request) throws Exception {

		EmplPositionTypeClass emplPositionTypeClassToBeAdded = new EmplPositionTypeClass();
		try {
			emplPositionTypeClassToBeAdded = EmplPositionTypeClassMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createEmplPositionTypeClass(emplPositionTypeClassToBeAdded);

	}

	/**
	 * creates a new EmplPositionTypeClass entry in the ofbiz database
	 * 
	 * @param emplPositionTypeClassToBeAdded
	 *            the EmplPositionTypeClass thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createEmplPositionTypeClass(@RequestBody EmplPositionTypeClass emplPositionTypeClassToBeAdded) throws Exception {

		AddEmplPositionTypeClass command = new AddEmplPositionTypeClass(emplPositionTypeClassToBeAdded);
		EmplPositionTypeClass emplPositionTypeClass = ((EmplPositionTypeClassAdded) Scheduler.execute(command).data()).getAddedEmplPositionTypeClass();
		
		if (emplPositionTypeClass != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(emplPositionTypeClass);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("EmplPositionTypeClass could not be created.");
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
	public boolean updateEmplPositionTypeClass(HttpServletRequest request) throws Exception {

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

		EmplPositionTypeClass emplPositionTypeClassToBeUpdated = new EmplPositionTypeClass();

		try {
			emplPositionTypeClassToBeUpdated = EmplPositionTypeClassMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateEmplPositionTypeClass(emplPositionTypeClassToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the EmplPositionTypeClass with the specific Id
	 * 
	 * @param emplPositionTypeClassToBeUpdated
	 *            the EmplPositionTypeClass thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateEmplPositionTypeClass(@RequestBody EmplPositionTypeClass emplPositionTypeClassToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		emplPositionTypeClassToBeUpdated.setnull(null);

		UpdateEmplPositionTypeClass command = new UpdateEmplPositionTypeClass(emplPositionTypeClassToBeUpdated);

		try {
			if(((EmplPositionTypeClassUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{emplPositionTypeClassId}")
	public ResponseEntity<Object> findById(@PathVariable String emplPositionTypeClassId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("emplPositionTypeClassId", emplPositionTypeClassId);
		try {

			Object foundEmplPositionTypeClass = findEmplPositionTypeClasssBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundEmplPositionTypeClass);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{emplPositionTypeClassId}")
	public ResponseEntity<Object> deleteEmplPositionTypeClassByIdUpdated(@PathVariable String emplPositionTypeClassId) throws Exception {
		DeleteEmplPositionTypeClass command = new DeleteEmplPositionTypeClass(emplPositionTypeClassId);

		try {
			if (((EmplPositionTypeClassDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("EmplPositionTypeClass could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/emplPositionTypeClass/\" plus one of the following: "
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
