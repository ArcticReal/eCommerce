package com.skytala.eCommerce.domain.product.relations.facility.control.type;

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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Splitter;
import com.skytala.eCommerce.domain.product.relations.facility.command.type.AddFacilityType;
import com.skytala.eCommerce.domain.product.relations.facility.command.type.DeleteFacilityType;
import com.skytala.eCommerce.domain.product.relations.facility.command.type.UpdateFacilityType;
import com.skytala.eCommerce.domain.product.relations.facility.event.type.FacilityTypeAdded;
import com.skytala.eCommerce.domain.product.relations.facility.event.type.FacilityTypeDeleted;
import com.skytala.eCommerce.domain.product.relations.facility.event.type.FacilityTypeFound;
import com.skytala.eCommerce.domain.product.relations.facility.event.type.FacilityTypeUpdated;
import com.skytala.eCommerce.domain.product.relations.facility.mapper.type.FacilityTypeMapper;
import com.skytala.eCommerce.domain.product.relations.facility.model.type.FacilityType;
import com.skytala.eCommerce.domain.product.relations.facility.query.type.FindFacilityTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/product/facility/facilityTypes")
public class FacilityTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public FacilityTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a FacilityType
	 * @return a List with the FacilityTypes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findFacilityTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindFacilityTypesBy query = new FindFacilityTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<FacilityType> facilityTypes =((FacilityTypeFound) Scheduler.execute(query).data()).getFacilityTypes();

		if (facilityTypes.size() == 1) {
			return ResponseEntity.ok().body(facilityTypes.get(0));
		}

		return ResponseEntity.ok().body(facilityTypes);

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
	public ResponseEntity<Object> createFacilityType(HttpServletRequest request) throws Exception {

		FacilityType facilityTypeToBeAdded = new FacilityType();
		try {
			facilityTypeToBeAdded = FacilityTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createFacilityType(facilityTypeToBeAdded);

	}

	/**
	 * creates a new FacilityType entry in the ofbiz database
	 * 
	 * @param facilityTypeToBeAdded
	 *            the FacilityType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createFacilityType(@RequestBody FacilityType facilityTypeToBeAdded) throws Exception {

		AddFacilityType command = new AddFacilityType(facilityTypeToBeAdded);
		FacilityType facilityType = ((FacilityTypeAdded) Scheduler.execute(command).data()).getAddedFacilityType();
		
		if (facilityType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(facilityType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("FacilityType could not be created.");
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
	public boolean updateFacilityType(HttpServletRequest request) throws Exception {

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

		FacilityType facilityTypeToBeUpdated = new FacilityType();

		try {
			facilityTypeToBeUpdated = FacilityTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateFacilityType(facilityTypeToBeUpdated, facilityTypeToBeUpdated.getFacilityTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the FacilityType with the specific Id
	 * 
	 * @param facilityTypeToBeUpdated
	 *            the FacilityType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{facilityTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateFacilityType(@RequestBody FacilityType facilityTypeToBeUpdated,
			@PathVariable String facilityTypeId) throws Exception {

		facilityTypeToBeUpdated.setFacilityTypeId(facilityTypeId);

		UpdateFacilityType command = new UpdateFacilityType(facilityTypeToBeUpdated);

		try {
			if(((FacilityTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{facilityTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String facilityTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("facilityTypeId", facilityTypeId);
		try {

			Object foundFacilityType = findFacilityTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundFacilityType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{facilityTypeId}")
	public ResponseEntity<Object> deleteFacilityTypeByIdUpdated(@PathVariable String facilityTypeId) throws Exception {
		DeleteFacilityType command = new DeleteFacilityType(facilityTypeId);

		try {
			if (((FacilityTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("FacilityType could not be deleted");

	}

}
