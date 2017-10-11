package com.skytala.eCommerce.domain.order.relations.requirementAttribute;

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
import com.skytala.eCommerce.domain.order.relations.requirementAttribute.command.AddRequirementAttribute;
import com.skytala.eCommerce.domain.order.relations.requirementAttribute.command.DeleteRequirementAttribute;
import com.skytala.eCommerce.domain.order.relations.requirementAttribute.command.UpdateRequirementAttribute;
import com.skytala.eCommerce.domain.order.relations.requirementAttribute.event.RequirementAttributeAdded;
import com.skytala.eCommerce.domain.order.relations.requirementAttribute.event.RequirementAttributeDeleted;
import com.skytala.eCommerce.domain.order.relations.requirementAttribute.event.RequirementAttributeFound;
import com.skytala.eCommerce.domain.order.relations.requirementAttribute.event.RequirementAttributeUpdated;
import com.skytala.eCommerce.domain.order.relations.requirementAttribute.mapper.RequirementAttributeMapper;
import com.skytala.eCommerce.domain.order.relations.requirementAttribute.model.RequirementAttribute;
import com.skytala.eCommerce.domain.order.relations.requirementAttribute.query.FindRequirementAttributesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/requirementAttributes")
public class RequirementAttributeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public RequirementAttributeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a RequirementAttribute
	 * @return a List with the RequirementAttributes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findRequirementAttributesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindRequirementAttributesBy query = new FindRequirementAttributesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<RequirementAttribute> requirementAttributes =((RequirementAttributeFound) Scheduler.execute(query).data()).getRequirementAttributes();

		if (requirementAttributes.size() == 1) {
			return ResponseEntity.ok().body(requirementAttributes.get(0));
		}

		return ResponseEntity.ok().body(requirementAttributes);

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
	public ResponseEntity<Object> createRequirementAttribute(HttpServletRequest request) throws Exception {

		RequirementAttribute requirementAttributeToBeAdded = new RequirementAttribute();
		try {
			requirementAttributeToBeAdded = RequirementAttributeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createRequirementAttribute(requirementAttributeToBeAdded);

	}

	/**
	 * creates a new RequirementAttribute entry in the ofbiz database
	 * 
	 * @param requirementAttributeToBeAdded
	 *            the RequirementAttribute thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createRequirementAttribute(@RequestBody RequirementAttribute requirementAttributeToBeAdded) throws Exception {

		AddRequirementAttribute command = new AddRequirementAttribute(requirementAttributeToBeAdded);
		RequirementAttribute requirementAttribute = ((RequirementAttributeAdded) Scheduler.execute(command).data()).getAddedRequirementAttribute();
		
		if (requirementAttribute != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(requirementAttribute);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("RequirementAttribute could not be created.");
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
	public boolean updateRequirementAttribute(HttpServletRequest request) throws Exception {

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

		RequirementAttribute requirementAttributeToBeUpdated = new RequirementAttribute();

		try {
			requirementAttributeToBeUpdated = RequirementAttributeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateRequirementAttribute(requirementAttributeToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the RequirementAttribute with the specific Id
	 * 
	 * @param requirementAttributeToBeUpdated
	 *            the RequirementAttribute thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateRequirementAttribute(@RequestBody RequirementAttribute requirementAttributeToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		requirementAttributeToBeUpdated.setnull(null);

		UpdateRequirementAttribute command = new UpdateRequirementAttribute(requirementAttributeToBeUpdated);

		try {
			if(((RequirementAttributeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{requirementAttributeId}")
	public ResponseEntity<Object> findById(@PathVariable String requirementAttributeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("requirementAttributeId", requirementAttributeId);
		try {

			Object foundRequirementAttribute = findRequirementAttributesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundRequirementAttribute);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{requirementAttributeId}")
	public ResponseEntity<Object> deleteRequirementAttributeByIdUpdated(@PathVariable String requirementAttributeId) throws Exception {
		DeleteRequirementAttribute command = new DeleteRequirementAttribute(requirementAttributeId);

		try {
			if (((RequirementAttributeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("RequirementAttribute could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/requirementAttribute/\" plus one of the following: "
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
