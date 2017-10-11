package com.skytala.eCommerce.domain.order.relations.requirementCustRequest;

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
import com.skytala.eCommerce.domain.order.relations.requirementCustRequest.command.AddRequirementCustRequest;
import com.skytala.eCommerce.domain.order.relations.requirementCustRequest.command.DeleteRequirementCustRequest;
import com.skytala.eCommerce.domain.order.relations.requirementCustRequest.command.UpdateRequirementCustRequest;
import com.skytala.eCommerce.domain.order.relations.requirementCustRequest.event.RequirementCustRequestAdded;
import com.skytala.eCommerce.domain.order.relations.requirementCustRequest.event.RequirementCustRequestDeleted;
import com.skytala.eCommerce.domain.order.relations.requirementCustRequest.event.RequirementCustRequestFound;
import com.skytala.eCommerce.domain.order.relations.requirementCustRequest.event.RequirementCustRequestUpdated;
import com.skytala.eCommerce.domain.order.relations.requirementCustRequest.mapper.RequirementCustRequestMapper;
import com.skytala.eCommerce.domain.order.relations.requirementCustRequest.model.RequirementCustRequest;
import com.skytala.eCommerce.domain.order.relations.requirementCustRequest.query.FindRequirementCustRequestsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/requirementCustRequests")
public class RequirementCustRequestController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public RequirementCustRequestController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a RequirementCustRequest
	 * @return a List with the RequirementCustRequests
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findRequirementCustRequestsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindRequirementCustRequestsBy query = new FindRequirementCustRequestsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<RequirementCustRequest> requirementCustRequests =((RequirementCustRequestFound) Scheduler.execute(query).data()).getRequirementCustRequests();

		if (requirementCustRequests.size() == 1) {
			return ResponseEntity.ok().body(requirementCustRequests.get(0));
		}

		return ResponseEntity.ok().body(requirementCustRequests);

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
	public ResponseEntity<Object> createRequirementCustRequest(HttpServletRequest request) throws Exception {

		RequirementCustRequest requirementCustRequestToBeAdded = new RequirementCustRequest();
		try {
			requirementCustRequestToBeAdded = RequirementCustRequestMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createRequirementCustRequest(requirementCustRequestToBeAdded);

	}

	/**
	 * creates a new RequirementCustRequest entry in the ofbiz database
	 * 
	 * @param requirementCustRequestToBeAdded
	 *            the RequirementCustRequest thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createRequirementCustRequest(@RequestBody RequirementCustRequest requirementCustRequestToBeAdded) throws Exception {

		AddRequirementCustRequest command = new AddRequirementCustRequest(requirementCustRequestToBeAdded);
		RequirementCustRequest requirementCustRequest = ((RequirementCustRequestAdded) Scheduler.execute(command).data()).getAddedRequirementCustRequest();
		
		if (requirementCustRequest != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(requirementCustRequest);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("RequirementCustRequest could not be created.");
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
	public boolean updateRequirementCustRequest(HttpServletRequest request) throws Exception {

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

		RequirementCustRequest requirementCustRequestToBeUpdated = new RequirementCustRequest();

		try {
			requirementCustRequestToBeUpdated = RequirementCustRequestMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateRequirementCustRequest(requirementCustRequestToBeUpdated, requirementCustRequestToBeUpdated.getCustRequestItemSeqId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the RequirementCustRequest with the specific Id
	 * 
	 * @param requirementCustRequestToBeUpdated
	 *            the RequirementCustRequest thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{custRequestItemSeqId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateRequirementCustRequest(@RequestBody RequirementCustRequest requirementCustRequestToBeUpdated,
			@PathVariable String custRequestItemSeqId) throws Exception {

		requirementCustRequestToBeUpdated.setCustRequestItemSeqId(custRequestItemSeqId);

		UpdateRequirementCustRequest command = new UpdateRequirementCustRequest(requirementCustRequestToBeUpdated);

		try {
			if(((RequirementCustRequestUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{requirementCustRequestId}")
	public ResponseEntity<Object> findById(@PathVariable String requirementCustRequestId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("requirementCustRequestId", requirementCustRequestId);
		try {

			Object foundRequirementCustRequest = findRequirementCustRequestsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundRequirementCustRequest);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{requirementCustRequestId}")
	public ResponseEntity<Object> deleteRequirementCustRequestByIdUpdated(@PathVariable String requirementCustRequestId) throws Exception {
		DeleteRequirementCustRequest command = new DeleteRequirementCustRequest(requirementCustRequestId);

		try {
			if (((RequirementCustRequestDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("RequirementCustRequest could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/requirementCustRequest/\" plus one of the following: "
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
