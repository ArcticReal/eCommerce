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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	@GetMapping("/find")
	public ResponseEntity<List<FacilityType>> findFacilityTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindFacilityTypesBy query = new FindFacilityTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<FacilityType> facilityTypes =((FacilityTypeFound) Scheduler.execute(query).data()).getFacilityTypes();

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
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<FacilityType> createFacilityType(HttpServletRequest request) throws Exception {

		FacilityType facilityTypeToBeAdded = new FacilityType();
		try {
			facilityTypeToBeAdded = FacilityTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
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
	public ResponseEntity<FacilityType> createFacilityType(@RequestBody FacilityType facilityTypeToBeAdded) throws Exception {

		AddFacilityType command = new AddFacilityType(facilityTypeToBeAdded);
		FacilityType facilityType = ((FacilityTypeAdded) Scheduler.execute(command).data()).getAddedFacilityType();
		
		if (facilityType != null) 
			return successful(facilityType);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateFacilityType(@RequestBody FacilityType facilityTypeToBeUpdated,
			@PathVariable String facilityTypeId) throws Exception {

		facilityTypeToBeUpdated.setFacilityTypeId(facilityTypeId);

		UpdateFacilityType command = new UpdateFacilityType(facilityTypeToBeUpdated);

		try {
			if(((FacilityTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{facilityTypeId}")
	public ResponseEntity<FacilityType> findById(@PathVariable String facilityTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("facilityTypeId", facilityTypeId);
		try {

			List<FacilityType> foundFacilityType = findFacilityTypesBy(requestParams).getBody();
			if(foundFacilityType.size()==1){				return successful(foundFacilityType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{facilityTypeId}")
	public ResponseEntity<String> deleteFacilityTypeByIdUpdated(@PathVariable String facilityTypeId) throws Exception {
		DeleteFacilityType command = new DeleteFacilityType(facilityTypeId);

		try {
			if (((FacilityTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
