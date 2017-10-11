package com.skytala.eCommerce.domain.order.relations.requirementStatus;

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
import com.skytala.eCommerce.domain.order.relations.requirementStatus.command.AddRequirementStatus;
import com.skytala.eCommerce.domain.order.relations.requirementStatus.command.DeleteRequirementStatus;
import com.skytala.eCommerce.domain.order.relations.requirementStatus.command.UpdateRequirementStatus;
import com.skytala.eCommerce.domain.order.relations.requirementStatus.event.RequirementStatusAdded;
import com.skytala.eCommerce.domain.order.relations.requirementStatus.event.RequirementStatusDeleted;
import com.skytala.eCommerce.domain.order.relations.requirementStatus.event.RequirementStatusFound;
import com.skytala.eCommerce.domain.order.relations.requirementStatus.event.RequirementStatusUpdated;
import com.skytala.eCommerce.domain.order.relations.requirementStatus.mapper.RequirementStatusMapper;
import com.skytala.eCommerce.domain.order.relations.requirementStatus.model.RequirementStatus;
import com.skytala.eCommerce.domain.order.relations.requirementStatus.query.FindRequirementStatussBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/requirementStatuss")
public class RequirementStatusController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public RequirementStatusController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a RequirementStatus
	 * @return a List with the RequirementStatuss
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findRequirementStatussBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindRequirementStatussBy query = new FindRequirementStatussBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<RequirementStatus> requirementStatuss =((RequirementStatusFound) Scheduler.execute(query).data()).getRequirementStatuss();

		if (requirementStatuss.size() == 1) {
			return ResponseEntity.ok().body(requirementStatuss.get(0));
		}

		return ResponseEntity.ok().body(requirementStatuss);

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
	public ResponseEntity<Object> createRequirementStatus(HttpServletRequest request) throws Exception {

		RequirementStatus requirementStatusToBeAdded = new RequirementStatus();
		try {
			requirementStatusToBeAdded = RequirementStatusMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createRequirementStatus(requirementStatusToBeAdded);

	}

	/**
	 * creates a new RequirementStatus entry in the ofbiz database
	 * 
	 * @param requirementStatusToBeAdded
	 *            the RequirementStatus thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createRequirementStatus(@RequestBody RequirementStatus requirementStatusToBeAdded) throws Exception {

		AddRequirementStatus command = new AddRequirementStatus(requirementStatusToBeAdded);
		RequirementStatus requirementStatus = ((RequirementStatusAdded) Scheduler.execute(command).data()).getAddedRequirementStatus();
		
		if (requirementStatus != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(requirementStatus);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("RequirementStatus could not be created.");
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
	public boolean updateRequirementStatus(HttpServletRequest request) throws Exception {

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

		RequirementStatus requirementStatusToBeUpdated = new RequirementStatus();

		try {
			requirementStatusToBeUpdated = RequirementStatusMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateRequirementStatus(requirementStatusToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the RequirementStatus with the specific Id
	 * 
	 * @param requirementStatusToBeUpdated
	 *            the RequirementStatus thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateRequirementStatus(@RequestBody RequirementStatus requirementStatusToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		requirementStatusToBeUpdated.setnull(null);

		UpdateRequirementStatus command = new UpdateRequirementStatus(requirementStatusToBeUpdated);

		try {
			if(((RequirementStatusUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{requirementStatusId}")
	public ResponseEntity<Object> findById(@PathVariable String requirementStatusId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("requirementStatusId", requirementStatusId);
		try {

			Object foundRequirementStatus = findRequirementStatussBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundRequirementStatus);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{requirementStatusId}")
	public ResponseEntity<Object> deleteRequirementStatusByIdUpdated(@PathVariable String requirementStatusId) throws Exception {
		DeleteRequirementStatus command = new DeleteRequirementStatus(requirementStatusId);

		try {
			if (((RequirementStatusDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("RequirementStatus could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/requirementStatus/\" plus one of the following: "
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
