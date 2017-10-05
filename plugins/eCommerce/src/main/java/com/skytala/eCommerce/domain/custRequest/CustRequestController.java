package com.skytala.eCommerce.domain.custRequest;

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
import com.skytala.eCommerce.domain.custRequest.command.AddCustRequest;
import com.skytala.eCommerce.domain.custRequest.command.DeleteCustRequest;
import com.skytala.eCommerce.domain.custRequest.command.UpdateCustRequest;
import com.skytala.eCommerce.domain.custRequest.event.CustRequestAdded;
import com.skytala.eCommerce.domain.custRequest.event.CustRequestDeleted;
import com.skytala.eCommerce.domain.custRequest.event.CustRequestFound;
import com.skytala.eCommerce.domain.custRequest.event.CustRequestUpdated;
import com.skytala.eCommerce.domain.custRequest.mapper.CustRequestMapper;
import com.skytala.eCommerce.domain.custRequest.model.CustRequest;
import com.skytala.eCommerce.domain.custRequest.query.FindCustRequestsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/custRequests")
public class CustRequestController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public CustRequestController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a CustRequest
	 * @return a List with the CustRequests
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findCustRequestsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindCustRequestsBy query = new FindCustRequestsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<CustRequest> custRequests =((CustRequestFound) Scheduler.execute(query).data()).getCustRequests();

		if (custRequests.size() == 1) {
			return ResponseEntity.ok().body(custRequests.get(0));
		}

		return ResponseEntity.ok().body(custRequests);

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
	public ResponseEntity<Object> createCustRequest(HttpServletRequest request) throws Exception {

		CustRequest custRequestToBeAdded = new CustRequest();
		try {
			custRequestToBeAdded = CustRequestMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createCustRequest(custRequestToBeAdded);

	}

	/**
	 * creates a new CustRequest entry in the ofbiz database
	 * 
	 * @param custRequestToBeAdded
	 *            the CustRequest thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createCustRequest(@RequestBody CustRequest custRequestToBeAdded) throws Exception {

		AddCustRequest command = new AddCustRequest(custRequestToBeAdded);
		CustRequest custRequest = ((CustRequestAdded) Scheduler.execute(command).data()).getAddedCustRequest();
		
		if (custRequest != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(custRequest);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("CustRequest could not be created.");
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
	public boolean updateCustRequest(HttpServletRequest request) throws Exception {

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

		CustRequest custRequestToBeUpdated = new CustRequest();

		try {
			custRequestToBeUpdated = CustRequestMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateCustRequest(custRequestToBeUpdated, custRequestToBeUpdated.getCustRequestId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the CustRequest with the specific Id
	 * 
	 * @param custRequestToBeUpdated
	 *            the CustRequest thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{custRequestId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateCustRequest(@RequestBody CustRequest custRequestToBeUpdated,
			@PathVariable String custRequestId) throws Exception {

		custRequestToBeUpdated.setCustRequestId(custRequestId);

		UpdateCustRequest command = new UpdateCustRequest(custRequestToBeUpdated);

		try {
			if(((CustRequestUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{custRequestId}")
	public ResponseEntity<Object> findById(@PathVariable String custRequestId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("custRequestId", custRequestId);
		try {

			Object foundCustRequest = findCustRequestsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundCustRequest);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{custRequestId}")
	public ResponseEntity<Object> deleteCustRequestByIdUpdated(@PathVariable String custRequestId) throws Exception {
		DeleteCustRequest command = new DeleteCustRequest(custRequestId);

		try {
			if (((CustRequestDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("CustRequest could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/custRequest/\" plus one of the following: "
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
