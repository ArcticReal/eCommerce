package com.skytala.eCommerce.domain.product.relations.facilityAttribute;

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
import com.skytala.eCommerce.domain.product.relations.facilityAttribute.command.AddFacilityAttribute;
import com.skytala.eCommerce.domain.product.relations.facilityAttribute.command.DeleteFacilityAttribute;
import com.skytala.eCommerce.domain.product.relations.facilityAttribute.command.UpdateFacilityAttribute;
import com.skytala.eCommerce.domain.product.relations.facilityAttribute.event.FacilityAttributeAdded;
import com.skytala.eCommerce.domain.product.relations.facilityAttribute.event.FacilityAttributeDeleted;
import com.skytala.eCommerce.domain.product.relations.facilityAttribute.event.FacilityAttributeFound;
import com.skytala.eCommerce.domain.product.relations.facilityAttribute.event.FacilityAttributeUpdated;
import com.skytala.eCommerce.domain.product.relations.facilityAttribute.mapper.FacilityAttributeMapper;
import com.skytala.eCommerce.domain.product.relations.facilityAttribute.model.FacilityAttribute;
import com.skytala.eCommerce.domain.product.relations.facilityAttribute.query.FindFacilityAttributesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/facilityAttributes")
public class FacilityAttributeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public FacilityAttributeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a FacilityAttribute
	 * @return a List with the FacilityAttributes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findFacilityAttributesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindFacilityAttributesBy query = new FindFacilityAttributesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<FacilityAttribute> facilityAttributes =((FacilityAttributeFound) Scheduler.execute(query).data()).getFacilityAttributes();

		if (facilityAttributes.size() == 1) {
			return ResponseEntity.ok().body(facilityAttributes.get(0));
		}

		return ResponseEntity.ok().body(facilityAttributes);

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
	public ResponseEntity<Object> createFacilityAttribute(HttpServletRequest request) throws Exception {

		FacilityAttribute facilityAttributeToBeAdded = new FacilityAttribute();
		try {
			facilityAttributeToBeAdded = FacilityAttributeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createFacilityAttribute(facilityAttributeToBeAdded);

	}

	/**
	 * creates a new FacilityAttribute entry in the ofbiz database
	 * 
	 * @param facilityAttributeToBeAdded
	 *            the FacilityAttribute thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createFacilityAttribute(@RequestBody FacilityAttribute facilityAttributeToBeAdded) throws Exception {

		AddFacilityAttribute command = new AddFacilityAttribute(facilityAttributeToBeAdded);
		FacilityAttribute facilityAttribute = ((FacilityAttributeAdded) Scheduler.execute(command).data()).getAddedFacilityAttribute();
		
		if (facilityAttribute != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(facilityAttribute);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("FacilityAttribute could not be created.");
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
	public boolean updateFacilityAttribute(HttpServletRequest request) throws Exception {

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

		FacilityAttribute facilityAttributeToBeUpdated = new FacilityAttribute();

		try {
			facilityAttributeToBeUpdated = FacilityAttributeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateFacilityAttribute(facilityAttributeToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the FacilityAttribute with the specific Id
	 * 
	 * @param facilityAttributeToBeUpdated
	 *            the FacilityAttribute thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateFacilityAttribute(@RequestBody FacilityAttribute facilityAttributeToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		facilityAttributeToBeUpdated.setnull(null);

		UpdateFacilityAttribute command = new UpdateFacilityAttribute(facilityAttributeToBeUpdated);

		try {
			if(((FacilityAttributeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{facilityAttributeId}")
	public ResponseEntity<Object> findById(@PathVariable String facilityAttributeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("facilityAttributeId", facilityAttributeId);
		try {

			Object foundFacilityAttribute = findFacilityAttributesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundFacilityAttribute);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{facilityAttributeId}")
	public ResponseEntity<Object> deleteFacilityAttributeByIdUpdated(@PathVariable String facilityAttributeId) throws Exception {
		DeleteFacilityAttribute command = new DeleteFacilityAttribute(facilityAttributeId);

		try {
			if (((FacilityAttributeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("FacilityAttribute could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/facilityAttribute/\" plus one of the following: "
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