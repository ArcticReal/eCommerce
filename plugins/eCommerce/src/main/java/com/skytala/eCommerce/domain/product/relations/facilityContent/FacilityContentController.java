package com.skytala.eCommerce.domain.product.relations.facilityContent;

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
import com.skytala.eCommerce.domain.product.relations.facilityContent.command.AddFacilityContent;
import com.skytala.eCommerce.domain.product.relations.facilityContent.command.DeleteFacilityContent;
import com.skytala.eCommerce.domain.product.relations.facilityContent.command.UpdateFacilityContent;
import com.skytala.eCommerce.domain.product.relations.facilityContent.event.FacilityContentAdded;
import com.skytala.eCommerce.domain.product.relations.facilityContent.event.FacilityContentDeleted;
import com.skytala.eCommerce.domain.product.relations.facilityContent.event.FacilityContentFound;
import com.skytala.eCommerce.domain.product.relations.facilityContent.event.FacilityContentUpdated;
import com.skytala.eCommerce.domain.product.relations.facilityContent.mapper.FacilityContentMapper;
import com.skytala.eCommerce.domain.product.relations.facilityContent.model.FacilityContent;
import com.skytala.eCommerce.domain.product.relations.facilityContent.query.FindFacilityContentsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/facilityContents")
public class FacilityContentController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public FacilityContentController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a FacilityContent
	 * @return a List with the FacilityContents
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findFacilityContentsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindFacilityContentsBy query = new FindFacilityContentsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<FacilityContent> facilityContents =((FacilityContentFound) Scheduler.execute(query).data()).getFacilityContents();

		if (facilityContents.size() == 1) {
			return ResponseEntity.ok().body(facilityContents.get(0));
		}

		return ResponseEntity.ok().body(facilityContents);

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
	public ResponseEntity<Object> createFacilityContent(HttpServletRequest request) throws Exception {

		FacilityContent facilityContentToBeAdded = new FacilityContent();
		try {
			facilityContentToBeAdded = FacilityContentMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createFacilityContent(facilityContentToBeAdded);

	}

	/**
	 * creates a new FacilityContent entry in the ofbiz database
	 * 
	 * @param facilityContentToBeAdded
	 *            the FacilityContent thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createFacilityContent(@RequestBody FacilityContent facilityContentToBeAdded) throws Exception {

		AddFacilityContent command = new AddFacilityContent(facilityContentToBeAdded);
		FacilityContent facilityContent = ((FacilityContentAdded) Scheduler.execute(command).data()).getAddedFacilityContent();
		
		if (facilityContent != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(facilityContent);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("FacilityContent could not be created.");
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
	public boolean updateFacilityContent(HttpServletRequest request) throws Exception {

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

		FacilityContent facilityContentToBeUpdated = new FacilityContent();

		try {
			facilityContentToBeUpdated = FacilityContentMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateFacilityContent(facilityContentToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the FacilityContent with the specific Id
	 * 
	 * @param facilityContentToBeUpdated
	 *            the FacilityContent thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateFacilityContent(@RequestBody FacilityContent facilityContentToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		facilityContentToBeUpdated.setnull(null);

		UpdateFacilityContent command = new UpdateFacilityContent(facilityContentToBeUpdated);

		try {
			if(((FacilityContentUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{facilityContentId}")
	public ResponseEntity<Object> findById(@PathVariable String facilityContentId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("facilityContentId", facilityContentId);
		try {

			Object foundFacilityContent = findFacilityContentsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundFacilityContent);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{facilityContentId}")
	public ResponseEntity<Object> deleteFacilityContentByIdUpdated(@PathVariable String facilityContentId) throws Exception {
		DeleteFacilityContent command = new DeleteFacilityContent(facilityContentId);

		try {
			if (((FacilityContentDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("FacilityContent could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/facilityContent/\" plus one of the following: "
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
