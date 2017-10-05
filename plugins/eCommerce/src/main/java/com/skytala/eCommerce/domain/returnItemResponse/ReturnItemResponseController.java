package com.skytala.eCommerce.domain.returnItemResponse;

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
import com.skytala.eCommerce.domain.returnItemResponse.command.AddReturnItemResponse;
import com.skytala.eCommerce.domain.returnItemResponse.command.DeleteReturnItemResponse;
import com.skytala.eCommerce.domain.returnItemResponse.command.UpdateReturnItemResponse;
import com.skytala.eCommerce.domain.returnItemResponse.event.ReturnItemResponseAdded;
import com.skytala.eCommerce.domain.returnItemResponse.event.ReturnItemResponseDeleted;
import com.skytala.eCommerce.domain.returnItemResponse.event.ReturnItemResponseFound;
import com.skytala.eCommerce.domain.returnItemResponse.event.ReturnItemResponseUpdated;
import com.skytala.eCommerce.domain.returnItemResponse.mapper.ReturnItemResponseMapper;
import com.skytala.eCommerce.domain.returnItemResponse.model.ReturnItemResponse;
import com.skytala.eCommerce.domain.returnItemResponse.query.FindReturnItemResponsesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/returnItemResponses")
public class ReturnItemResponseController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ReturnItemResponseController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ReturnItemResponse
	 * @return a List with the ReturnItemResponses
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findReturnItemResponsesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindReturnItemResponsesBy query = new FindReturnItemResponsesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ReturnItemResponse> returnItemResponses =((ReturnItemResponseFound) Scheduler.execute(query).data()).getReturnItemResponses();

		if (returnItemResponses.size() == 1) {
			return ResponseEntity.ok().body(returnItemResponses.get(0));
		}

		return ResponseEntity.ok().body(returnItemResponses);

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
	public ResponseEntity<Object> createReturnItemResponse(HttpServletRequest request) throws Exception {

		ReturnItemResponse returnItemResponseToBeAdded = new ReturnItemResponse();
		try {
			returnItemResponseToBeAdded = ReturnItemResponseMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createReturnItemResponse(returnItemResponseToBeAdded);

	}

	/**
	 * creates a new ReturnItemResponse entry in the ofbiz database
	 * 
	 * @param returnItemResponseToBeAdded
	 *            the ReturnItemResponse thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createReturnItemResponse(@RequestBody ReturnItemResponse returnItemResponseToBeAdded) throws Exception {

		AddReturnItemResponse command = new AddReturnItemResponse(returnItemResponseToBeAdded);
		ReturnItemResponse returnItemResponse = ((ReturnItemResponseAdded) Scheduler.execute(command).data()).getAddedReturnItemResponse();
		
		if (returnItemResponse != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(returnItemResponse);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ReturnItemResponse could not be created.");
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
	public boolean updateReturnItemResponse(HttpServletRequest request) throws Exception {

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

		ReturnItemResponse returnItemResponseToBeUpdated = new ReturnItemResponse();

		try {
			returnItemResponseToBeUpdated = ReturnItemResponseMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateReturnItemResponse(returnItemResponseToBeUpdated, returnItemResponseToBeUpdated.getReturnItemResponseId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ReturnItemResponse with the specific Id
	 * 
	 * @param returnItemResponseToBeUpdated
	 *            the ReturnItemResponse thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{returnItemResponseId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateReturnItemResponse(@RequestBody ReturnItemResponse returnItemResponseToBeUpdated,
			@PathVariable String returnItemResponseId) throws Exception {

		returnItemResponseToBeUpdated.setReturnItemResponseId(returnItemResponseId);

		UpdateReturnItemResponse command = new UpdateReturnItemResponse(returnItemResponseToBeUpdated);

		try {
			if(((ReturnItemResponseUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{returnItemResponseId}")
	public ResponseEntity<Object> findById(@PathVariable String returnItemResponseId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("returnItemResponseId", returnItemResponseId);
		try {

			Object foundReturnItemResponse = findReturnItemResponsesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundReturnItemResponse);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{returnItemResponseId}")
	public ResponseEntity<Object> deleteReturnItemResponseByIdUpdated(@PathVariable String returnItemResponseId) throws Exception {
		DeleteReturnItemResponse command = new DeleteReturnItemResponse(returnItemResponseId);

		try {
			if (((ReturnItemResponseDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ReturnItemResponse could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/returnItemResponse/\" plus one of the following: "
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
