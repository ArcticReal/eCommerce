package com.skytala.eCommerce.domain.product.relations.facilityTypeAttr;

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
import com.skytala.eCommerce.domain.product.relations.facilityTypeAttr.command.AddFacilityTypeAttr;
import com.skytala.eCommerce.domain.product.relations.facilityTypeAttr.command.DeleteFacilityTypeAttr;
import com.skytala.eCommerce.domain.product.relations.facilityTypeAttr.command.UpdateFacilityTypeAttr;
import com.skytala.eCommerce.domain.product.relations.facilityTypeAttr.event.FacilityTypeAttrAdded;
import com.skytala.eCommerce.domain.product.relations.facilityTypeAttr.event.FacilityTypeAttrDeleted;
import com.skytala.eCommerce.domain.product.relations.facilityTypeAttr.event.FacilityTypeAttrFound;
import com.skytala.eCommerce.domain.product.relations.facilityTypeAttr.event.FacilityTypeAttrUpdated;
import com.skytala.eCommerce.domain.product.relations.facilityTypeAttr.mapper.FacilityTypeAttrMapper;
import com.skytala.eCommerce.domain.product.relations.facilityTypeAttr.model.FacilityTypeAttr;
import com.skytala.eCommerce.domain.product.relations.facilityTypeAttr.query.FindFacilityTypeAttrsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/facilityTypeAttrs")
public class FacilityTypeAttrController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public FacilityTypeAttrController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a FacilityTypeAttr
	 * @return a List with the FacilityTypeAttrs
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findFacilityTypeAttrsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindFacilityTypeAttrsBy query = new FindFacilityTypeAttrsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<FacilityTypeAttr> facilityTypeAttrs =((FacilityTypeAttrFound) Scheduler.execute(query).data()).getFacilityTypeAttrs();

		if (facilityTypeAttrs.size() == 1) {
			return ResponseEntity.ok().body(facilityTypeAttrs.get(0));
		}

		return ResponseEntity.ok().body(facilityTypeAttrs);

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
	public ResponseEntity<Object> createFacilityTypeAttr(HttpServletRequest request) throws Exception {

		FacilityTypeAttr facilityTypeAttrToBeAdded = new FacilityTypeAttr();
		try {
			facilityTypeAttrToBeAdded = FacilityTypeAttrMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createFacilityTypeAttr(facilityTypeAttrToBeAdded);

	}

	/**
	 * creates a new FacilityTypeAttr entry in the ofbiz database
	 * 
	 * @param facilityTypeAttrToBeAdded
	 *            the FacilityTypeAttr thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createFacilityTypeAttr(@RequestBody FacilityTypeAttr facilityTypeAttrToBeAdded) throws Exception {

		AddFacilityTypeAttr command = new AddFacilityTypeAttr(facilityTypeAttrToBeAdded);
		FacilityTypeAttr facilityTypeAttr = ((FacilityTypeAttrAdded) Scheduler.execute(command).data()).getAddedFacilityTypeAttr();
		
		if (facilityTypeAttr != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(facilityTypeAttr);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("FacilityTypeAttr could not be created.");
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
	public boolean updateFacilityTypeAttr(HttpServletRequest request) throws Exception {

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

		FacilityTypeAttr facilityTypeAttrToBeUpdated = new FacilityTypeAttr();

		try {
			facilityTypeAttrToBeUpdated = FacilityTypeAttrMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateFacilityTypeAttr(facilityTypeAttrToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the FacilityTypeAttr with the specific Id
	 * 
	 * @param facilityTypeAttrToBeUpdated
	 *            the FacilityTypeAttr thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateFacilityTypeAttr(@RequestBody FacilityTypeAttr facilityTypeAttrToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		facilityTypeAttrToBeUpdated.setnull(null);

		UpdateFacilityTypeAttr command = new UpdateFacilityTypeAttr(facilityTypeAttrToBeUpdated);

		try {
			if(((FacilityTypeAttrUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{facilityTypeAttrId}")
	public ResponseEntity<Object> findById(@PathVariable String facilityTypeAttrId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("facilityTypeAttrId", facilityTypeAttrId);
		try {

			Object foundFacilityTypeAttr = findFacilityTypeAttrsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundFacilityTypeAttr);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{facilityTypeAttrId}")
	public ResponseEntity<Object> deleteFacilityTypeAttrByIdUpdated(@PathVariable String facilityTypeAttrId) throws Exception {
		DeleteFacilityTypeAttr command = new DeleteFacilityTypeAttr(facilityTypeAttrId);

		try {
			if (((FacilityTypeAttrDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("FacilityTypeAttr could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/facilityTypeAttr/\" plus one of the following: "
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
