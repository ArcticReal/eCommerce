package com.skytala.eCommerce.domain.returnStatus;

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
import com.skytala.eCommerce.domain.returnStatus.command.AddReturnStatus;
import com.skytala.eCommerce.domain.returnStatus.command.DeleteReturnStatus;
import com.skytala.eCommerce.domain.returnStatus.command.UpdateReturnStatus;
import com.skytala.eCommerce.domain.returnStatus.event.ReturnStatusAdded;
import com.skytala.eCommerce.domain.returnStatus.event.ReturnStatusDeleted;
import com.skytala.eCommerce.domain.returnStatus.event.ReturnStatusFound;
import com.skytala.eCommerce.domain.returnStatus.event.ReturnStatusUpdated;
import com.skytala.eCommerce.domain.returnStatus.mapper.ReturnStatusMapper;
import com.skytala.eCommerce.domain.returnStatus.model.ReturnStatus;
import com.skytala.eCommerce.domain.returnStatus.query.FindReturnStatussBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/returnStatuss")
public class ReturnStatusController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ReturnStatusController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ReturnStatus
	 * @return a List with the ReturnStatuss
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findReturnStatussBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindReturnStatussBy query = new FindReturnStatussBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ReturnStatus> returnStatuss =((ReturnStatusFound) Scheduler.execute(query).data()).getReturnStatuss();

		if (returnStatuss.size() == 1) {
			return ResponseEntity.ok().body(returnStatuss.get(0));
		}

		return ResponseEntity.ok().body(returnStatuss);

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
	public ResponseEntity<Object> createReturnStatus(HttpServletRequest request) throws Exception {

		ReturnStatus returnStatusToBeAdded = new ReturnStatus();
		try {
			returnStatusToBeAdded = ReturnStatusMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createReturnStatus(returnStatusToBeAdded);

	}

	/**
	 * creates a new ReturnStatus entry in the ofbiz database
	 * 
	 * @param returnStatusToBeAdded
	 *            the ReturnStatus thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createReturnStatus(@RequestBody ReturnStatus returnStatusToBeAdded) throws Exception {

		AddReturnStatus command = new AddReturnStatus(returnStatusToBeAdded);
		ReturnStatus returnStatus = ((ReturnStatusAdded) Scheduler.execute(command).data()).getAddedReturnStatus();
		
		if (returnStatus != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(returnStatus);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ReturnStatus could not be created.");
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
	public boolean updateReturnStatus(HttpServletRequest request) throws Exception {

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

		ReturnStatus returnStatusToBeUpdated = new ReturnStatus();

		try {
			returnStatusToBeUpdated = ReturnStatusMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateReturnStatus(returnStatusToBeUpdated, returnStatusToBeUpdated.getReturnStatusId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ReturnStatus with the specific Id
	 * 
	 * @param returnStatusToBeUpdated
	 *            the ReturnStatus thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{returnStatusId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateReturnStatus(@RequestBody ReturnStatus returnStatusToBeUpdated,
			@PathVariable String returnStatusId) throws Exception {

		returnStatusToBeUpdated.setReturnStatusId(returnStatusId);

		UpdateReturnStatus command = new UpdateReturnStatus(returnStatusToBeUpdated);

		try {
			if(((ReturnStatusUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{returnStatusId}")
	public ResponseEntity<Object> findById(@PathVariable String returnStatusId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("returnStatusId", returnStatusId);
		try {

			Object foundReturnStatus = findReturnStatussBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundReturnStatus);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{returnStatusId}")
	public ResponseEntity<Object> deleteReturnStatusByIdUpdated(@PathVariable String returnStatusId) throws Exception {
		DeleteReturnStatus command = new DeleteReturnStatus(returnStatusId);

		try {
			if (((ReturnStatusDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ReturnStatus could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/returnStatus/\" plus one of the following: "
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