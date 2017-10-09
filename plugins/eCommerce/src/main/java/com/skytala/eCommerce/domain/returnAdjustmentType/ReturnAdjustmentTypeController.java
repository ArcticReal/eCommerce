package com.skytala.eCommerce.domain.returnAdjustmentType;

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
import com.skytala.eCommerce.domain.returnAdjustmentType.command.AddReturnAdjustmentType;
import com.skytala.eCommerce.domain.returnAdjustmentType.command.DeleteReturnAdjustmentType;
import com.skytala.eCommerce.domain.returnAdjustmentType.command.UpdateReturnAdjustmentType;
import com.skytala.eCommerce.domain.returnAdjustmentType.event.ReturnAdjustmentTypeAdded;
import com.skytala.eCommerce.domain.returnAdjustmentType.event.ReturnAdjustmentTypeDeleted;
import com.skytala.eCommerce.domain.returnAdjustmentType.event.ReturnAdjustmentTypeFound;
import com.skytala.eCommerce.domain.returnAdjustmentType.event.ReturnAdjustmentTypeUpdated;
import com.skytala.eCommerce.domain.returnAdjustmentType.mapper.ReturnAdjustmentTypeMapper;
import com.skytala.eCommerce.domain.returnAdjustmentType.model.ReturnAdjustmentType;
import com.skytala.eCommerce.domain.returnAdjustmentType.query.FindReturnAdjustmentTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/returnAdjustmentTypes")
public class ReturnAdjustmentTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ReturnAdjustmentTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ReturnAdjustmentType
	 * @return a List with the ReturnAdjustmentTypes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findReturnAdjustmentTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindReturnAdjustmentTypesBy query = new FindReturnAdjustmentTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ReturnAdjustmentType> returnAdjustmentTypes =((ReturnAdjustmentTypeFound) Scheduler.execute(query).data()).getReturnAdjustmentTypes();

		if (returnAdjustmentTypes.size() == 1) {
			return ResponseEntity.ok().body(returnAdjustmentTypes.get(0));
		}

		return ResponseEntity.ok().body(returnAdjustmentTypes);

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
	public ResponseEntity<Object> createReturnAdjustmentType(HttpServletRequest request) throws Exception {

		ReturnAdjustmentType returnAdjustmentTypeToBeAdded = new ReturnAdjustmentType();
		try {
			returnAdjustmentTypeToBeAdded = ReturnAdjustmentTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createReturnAdjustmentType(returnAdjustmentTypeToBeAdded);

	}

	/**
	 * creates a new ReturnAdjustmentType entry in the ofbiz database
	 * 
	 * @param returnAdjustmentTypeToBeAdded
	 *            the ReturnAdjustmentType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createReturnAdjustmentType(@RequestBody ReturnAdjustmentType returnAdjustmentTypeToBeAdded) throws Exception {

		AddReturnAdjustmentType command = new AddReturnAdjustmentType(returnAdjustmentTypeToBeAdded);
		ReturnAdjustmentType returnAdjustmentType = ((ReturnAdjustmentTypeAdded) Scheduler.execute(command).data()).getAddedReturnAdjustmentType();
		
		if (returnAdjustmentType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(returnAdjustmentType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ReturnAdjustmentType could not be created.");
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
	public boolean updateReturnAdjustmentType(HttpServletRequest request) throws Exception {

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

		ReturnAdjustmentType returnAdjustmentTypeToBeUpdated = new ReturnAdjustmentType();

		try {
			returnAdjustmentTypeToBeUpdated = ReturnAdjustmentTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateReturnAdjustmentType(returnAdjustmentTypeToBeUpdated, returnAdjustmentTypeToBeUpdated.getReturnAdjustmentTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ReturnAdjustmentType with the specific Id
	 * 
	 * @param returnAdjustmentTypeToBeUpdated
	 *            the ReturnAdjustmentType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{returnAdjustmentTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateReturnAdjustmentType(@RequestBody ReturnAdjustmentType returnAdjustmentTypeToBeUpdated,
			@PathVariable String returnAdjustmentTypeId) throws Exception {

		returnAdjustmentTypeToBeUpdated.setReturnAdjustmentTypeId(returnAdjustmentTypeId);

		UpdateReturnAdjustmentType command = new UpdateReturnAdjustmentType(returnAdjustmentTypeToBeUpdated);

		try {
			if(((ReturnAdjustmentTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{returnAdjustmentTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String returnAdjustmentTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("returnAdjustmentTypeId", returnAdjustmentTypeId);
		try {

			Object foundReturnAdjustmentType = findReturnAdjustmentTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundReturnAdjustmentType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{returnAdjustmentTypeId}")
	public ResponseEntity<Object> deleteReturnAdjustmentTypeByIdUpdated(@PathVariable String returnAdjustmentTypeId) throws Exception {
		DeleteReturnAdjustmentType command = new DeleteReturnAdjustmentType(returnAdjustmentTypeId);

		try {
			if (((ReturnAdjustmentTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ReturnAdjustmentType could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/returnAdjustmentType/\" plus one of the following: "
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