package com.skytala.eCommerce.domain.responsibilityType;

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
import com.skytala.eCommerce.domain.responsibilityType.command.AddResponsibilityType;
import com.skytala.eCommerce.domain.responsibilityType.command.DeleteResponsibilityType;
import com.skytala.eCommerce.domain.responsibilityType.command.UpdateResponsibilityType;
import com.skytala.eCommerce.domain.responsibilityType.event.ResponsibilityTypeAdded;
import com.skytala.eCommerce.domain.responsibilityType.event.ResponsibilityTypeDeleted;
import com.skytala.eCommerce.domain.responsibilityType.event.ResponsibilityTypeFound;
import com.skytala.eCommerce.domain.responsibilityType.event.ResponsibilityTypeUpdated;
import com.skytala.eCommerce.domain.responsibilityType.mapper.ResponsibilityTypeMapper;
import com.skytala.eCommerce.domain.responsibilityType.model.ResponsibilityType;
import com.skytala.eCommerce.domain.responsibilityType.query.FindResponsibilityTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/responsibilityTypes")
public class ResponsibilityTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ResponsibilityTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ResponsibilityType
	 * @return a List with the ResponsibilityTypes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findResponsibilityTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindResponsibilityTypesBy query = new FindResponsibilityTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ResponsibilityType> responsibilityTypes =((ResponsibilityTypeFound) Scheduler.execute(query).data()).getResponsibilityTypes();

		if (responsibilityTypes.size() == 1) {
			return ResponseEntity.ok().body(responsibilityTypes.get(0));
		}

		return ResponseEntity.ok().body(responsibilityTypes);

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
	public ResponseEntity<Object> createResponsibilityType(HttpServletRequest request) throws Exception {

		ResponsibilityType responsibilityTypeToBeAdded = new ResponsibilityType();
		try {
			responsibilityTypeToBeAdded = ResponsibilityTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createResponsibilityType(responsibilityTypeToBeAdded);

	}

	/**
	 * creates a new ResponsibilityType entry in the ofbiz database
	 * 
	 * @param responsibilityTypeToBeAdded
	 *            the ResponsibilityType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createResponsibilityType(@RequestBody ResponsibilityType responsibilityTypeToBeAdded) throws Exception {

		AddResponsibilityType command = new AddResponsibilityType(responsibilityTypeToBeAdded);
		ResponsibilityType responsibilityType = ((ResponsibilityTypeAdded) Scheduler.execute(command).data()).getAddedResponsibilityType();
		
		if (responsibilityType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(responsibilityType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ResponsibilityType could not be created.");
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
	public boolean updateResponsibilityType(HttpServletRequest request) throws Exception {

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

		ResponsibilityType responsibilityTypeToBeUpdated = new ResponsibilityType();

		try {
			responsibilityTypeToBeUpdated = ResponsibilityTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateResponsibilityType(responsibilityTypeToBeUpdated, responsibilityTypeToBeUpdated.getResponsibilityTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ResponsibilityType with the specific Id
	 * 
	 * @param responsibilityTypeToBeUpdated
	 *            the ResponsibilityType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{responsibilityTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateResponsibilityType(@RequestBody ResponsibilityType responsibilityTypeToBeUpdated,
			@PathVariable String responsibilityTypeId) throws Exception {

		responsibilityTypeToBeUpdated.setResponsibilityTypeId(responsibilityTypeId);

		UpdateResponsibilityType command = new UpdateResponsibilityType(responsibilityTypeToBeUpdated);

		try {
			if(((ResponsibilityTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{responsibilityTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String responsibilityTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("responsibilityTypeId", responsibilityTypeId);
		try {

			Object foundResponsibilityType = findResponsibilityTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundResponsibilityType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{responsibilityTypeId}")
	public ResponseEntity<Object> deleteResponsibilityTypeByIdUpdated(@PathVariable String responsibilityTypeId) throws Exception {
		DeleteResponsibilityType command = new DeleteResponsibilityType(responsibilityTypeId);

		try {
			if (((ResponsibilityTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ResponsibilityType could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/responsibilityType/\" plus one of the following: "
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