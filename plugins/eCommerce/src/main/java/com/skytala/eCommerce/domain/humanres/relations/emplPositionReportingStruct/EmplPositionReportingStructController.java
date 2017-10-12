package com.skytala.eCommerce.domain.humanres.relations.emplPositionReportingStruct;

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
import com.skytala.eCommerce.domain.humanres.relations.emplPositionReportingStruct.command.AddEmplPositionReportingStruct;
import com.skytala.eCommerce.domain.humanres.relations.emplPositionReportingStruct.command.DeleteEmplPositionReportingStruct;
import com.skytala.eCommerce.domain.humanres.relations.emplPositionReportingStruct.command.UpdateEmplPositionReportingStruct;
import com.skytala.eCommerce.domain.humanres.relations.emplPositionReportingStruct.event.EmplPositionReportingStructAdded;
import com.skytala.eCommerce.domain.humanres.relations.emplPositionReportingStruct.event.EmplPositionReportingStructDeleted;
import com.skytala.eCommerce.domain.humanres.relations.emplPositionReportingStruct.event.EmplPositionReportingStructFound;
import com.skytala.eCommerce.domain.humanres.relations.emplPositionReportingStruct.event.EmplPositionReportingStructUpdated;
import com.skytala.eCommerce.domain.humanres.relations.emplPositionReportingStruct.mapper.EmplPositionReportingStructMapper;
import com.skytala.eCommerce.domain.humanres.relations.emplPositionReportingStruct.model.EmplPositionReportingStruct;
import com.skytala.eCommerce.domain.humanres.relations.emplPositionReportingStruct.query.FindEmplPositionReportingStructsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/emplPositionReportingStructs")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findEmplPositionReportingStructsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindEmplPositionReportingStructsBy query = new FindEmplPositionReportingStructsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<EmplPositionReportingStruct> emplPositionReportingStructs =((EmplPositionReportingStructFound) Scheduler.execute(query).data()).getEmplPositionReportingStructs();

		if (emplPositionReportingStructs.size() == 1) {
			return ResponseEntity.ok().body(emplPositionReportingStructs.get(0));
		}

		return ResponseEntity.ok().body(emplPositionReportingStructs);

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
	public ResponseEntity<Object> createEmplPositionReportingStruct(HttpServletRequest request) throws Exception {

		EmplPositionReportingStruct emplPositionReportingStructToBeAdded = new EmplPositionReportingStruct();
		try {
			emplPositionReportingStructToBeAdded = EmplPositionReportingStructMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createEmplPositionReportingStruct(emplPositionReportingStructToBeAdded);

	}

	/**
	 * creates a new EmplPositionReportingStruct entry in the ofbiz database
	 * 
	 * @param emplPositionReportingStructToBeAdded
	 *            the EmplPositionReportingStruct thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createEmplPositionReportingStruct(@RequestBody EmplPositionReportingStruct emplPositionReportingStructToBeAdded) throws Exception {

		AddEmplPositionReportingStruct command = new AddEmplPositionReportingStruct(emplPositionReportingStructToBeAdded);
		EmplPositionReportingStruct emplPositionReportingStruct = ((EmplPositionReportingStructAdded) Scheduler.execute(command).data()).getAddedEmplPositionReportingStruct();
		
		if (emplPositionReportingStruct != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(emplPositionReportingStruct);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("EmplPositionReportingStruct could not be created.");
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
	public boolean updateEmplPositionReportingStruct(HttpServletRequest request) throws Exception {

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

		EmplPositionReportingStruct emplPositionReportingStructToBeUpdated = new EmplPositionReportingStruct();

		try {
			emplPositionReportingStructToBeUpdated = EmplPositionReportingStructMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateEmplPositionReportingStruct(emplPositionReportingStructToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateEmplPositionReportingStruct(@RequestBody EmplPositionReportingStruct emplPositionReportingStructToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		emplPositionReportingStructToBeUpdated.setnull(null);

		UpdateEmplPositionReportingStruct command = new UpdateEmplPositionReportingStruct(emplPositionReportingStructToBeUpdated);

		try {
			if(((EmplPositionReportingStructUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{emplPositionReportingStructId}")
	public ResponseEntity<Object> findById(@PathVariable String emplPositionReportingStructId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("emplPositionReportingStructId", emplPositionReportingStructId);
		try {

			Object foundEmplPositionReportingStruct = findEmplPositionReportingStructsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundEmplPositionReportingStruct);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{emplPositionReportingStructId}")
	public ResponseEntity<Object> deleteEmplPositionReportingStructByIdUpdated(@PathVariable String emplPositionReportingStructId) throws Exception {
		DeleteEmplPositionReportingStruct command = new DeleteEmplPositionReportingStruct(emplPositionReportingStructId);

		try {
			if (((EmplPositionReportingStructDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("EmplPositionReportingStruct could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/emplPositionReportingStruct/\" plus one of the following: "
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
