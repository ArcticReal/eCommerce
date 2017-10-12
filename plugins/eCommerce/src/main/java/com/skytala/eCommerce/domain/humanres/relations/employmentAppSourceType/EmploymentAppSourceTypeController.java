package com.skytala.eCommerce.domain.humanres.relations.employmentAppSourceType;

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
import com.skytala.eCommerce.domain.humanres.relations.employmentAppSourceType.command.AddEmploymentAppSourceType;
import com.skytala.eCommerce.domain.humanres.relations.employmentAppSourceType.command.DeleteEmploymentAppSourceType;
import com.skytala.eCommerce.domain.humanres.relations.employmentAppSourceType.command.UpdateEmploymentAppSourceType;
import com.skytala.eCommerce.domain.humanres.relations.employmentAppSourceType.event.EmploymentAppSourceTypeAdded;
import com.skytala.eCommerce.domain.humanres.relations.employmentAppSourceType.event.EmploymentAppSourceTypeDeleted;
import com.skytala.eCommerce.domain.humanres.relations.employmentAppSourceType.event.EmploymentAppSourceTypeFound;
import com.skytala.eCommerce.domain.humanres.relations.employmentAppSourceType.event.EmploymentAppSourceTypeUpdated;
import com.skytala.eCommerce.domain.humanres.relations.employmentAppSourceType.mapper.EmploymentAppSourceTypeMapper;
import com.skytala.eCommerce.domain.humanres.relations.employmentAppSourceType.model.EmploymentAppSourceType;
import com.skytala.eCommerce.domain.humanres.relations.employmentAppSourceType.query.FindEmploymentAppSourceTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/employmentAppSourceTypes")
public class EmploymentAppSourceTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public EmploymentAppSourceTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a EmploymentAppSourceType
	 * @return a List with the EmploymentAppSourceTypes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findEmploymentAppSourceTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindEmploymentAppSourceTypesBy query = new FindEmploymentAppSourceTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<EmploymentAppSourceType> employmentAppSourceTypes =((EmploymentAppSourceTypeFound) Scheduler.execute(query).data()).getEmploymentAppSourceTypes();

		if (employmentAppSourceTypes.size() == 1) {
			return ResponseEntity.ok().body(employmentAppSourceTypes.get(0));
		}

		return ResponseEntity.ok().body(employmentAppSourceTypes);

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
	public ResponseEntity<Object> createEmploymentAppSourceType(HttpServletRequest request) throws Exception {

		EmploymentAppSourceType employmentAppSourceTypeToBeAdded = new EmploymentAppSourceType();
		try {
			employmentAppSourceTypeToBeAdded = EmploymentAppSourceTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createEmploymentAppSourceType(employmentAppSourceTypeToBeAdded);

	}

	/**
	 * creates a new EmploymentAppSourceType entry in the ofbiz database
	 * 
	 * @param employmentAppSourceTypeToBeAdded
	 *            the EmploymentAppSourceType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createEmploymentAppSourceType(@RequestBody EmploymentAppSourceType employmentAppSourceTypeToBeAdded) throws Exception {

		AddEmploymentAppSourceType command = new AddEmploymentAppSourceType(employmentAppSourceTypeToBeAdded);
		EmploymentAppSourceType employmentAppSourceType = ((EmploymentAppSourceTypeAdded) Scheduler.execute(command).data()).getAddedEmploymentAppSourceType();
		
		if (employmentAppSourceType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(employmentAppSourceType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("EmploymentAppSourceType could not be created.");
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
	public boolean updateEmploymentAppSourceType(HttpServletRequest request) throws Exception {

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

		EmploymentAppSourceType employmentAppSourceTypeToBeUpdated = new EmploymentAppSourceType();

		try {
			employmentAppSourceTypeToBeUpdated = EmploymentAppSourceTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateEmploymentAppSourceType(employmentAppSourceTypeToBeUpdated, employmentAppSourceTypeToBeUpdated.getEmploymentAppSourceTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the EmploymentAppSourceType with the specific Id
	 * 
	 * @param employmentAppSourceTypeToBeUpdated
	 *            the EmploymentAppSourceType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{employmentAppSourceTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateEmploymentAppSourceType(@RequestBody EmploymentAppSourceType employmentAppSourceTypeToBeUpdated,
			@PathVariable String employmentAppSourceTypeId) throws Exception {

		employmentAppSourceTypeToBeUpdated.setEmploymentAppSourceTypeId(employmentAppSourceTypeId);

		UpdateEmploymentAppSourceType command = new UpdateEmploymentAppSourceType(employmentAppSourceTypeToBeUpdated);

		try {
			if(((EmploymentAppSourceTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{employmentAppSourceTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String employmentAppSourceTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("employmentAppSourceTypeId", employmentAppSourceTypeId);
		try {

			Object foundEmploymentAppSourceType = findEmploymentAppSourceTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundEmploymentAppSourceType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{employmentAppSourceTypeId}")
	public ResponseEntity<Object> deleteEmploymentAppSourceTypeByIdUpdated(@PathVariable String employmentAppSourceTypeId) throws Exception {
		DeleteEmploymentAppSourceType command = new DeleteEmploymentAppSourceType(employmentAppSourceTypeId);

		try {
			if (((EmploymentAppSourceTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("EmploymentAppSourceType could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/employmentAppSourceType/\" plus one of the following: "
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
