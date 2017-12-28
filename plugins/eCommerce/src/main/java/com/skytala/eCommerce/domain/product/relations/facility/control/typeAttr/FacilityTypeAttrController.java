package com.skytala.eCommerce.domain.product.relations.facility.control.typeAttr;

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
import com.skytala.eCommerce.domain.product.relations.facility.command.typeAttr.AddFacilityTypeAttr;
import com.skytala.eCommerce.domain.product.relations.facility.command.typeAttr.DeleteFacilityTypeAttr;
import com.skytala.eCommerce.domain.product.relations.facility.command.typeAttr.UpdateFacilityTypeAttr;
import com.skytala.eCommerce.domain.product.relations.facility.event.typeAttr.FacilityTypeAttrAdded;
import com.skytala.eCommerce.domain.product.relations.facility.event.typeAttr.FacilityTypeAttrDeleted;
import com.skytala.eCommerce.domain.product.relations.facility.event.typeAttr.FacilityTypeAttrFound;
import com.skytala.eCommerce.domain.product.relations.facility.event.typeAttr.FacilityTypeAttrUpdated;
import com.skytala.eCommerce.domain.product.relations.facility.mapper.typeAttr.FacilityTypeAttrMapper;
import com.skytala.eCommerce.domain.product.relations.facility.model.typeAttr.FacilityTypeAttr;
import com.skytala.eCommerce.domain.product.relations.facility.query.typeAttr.FindFacilityTypeAttrsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/facility/facilityTypeAttrs")
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
	@GetMapping("/find")
	public ResponseEntity<List<FacilityTypeAttr>> findFacilityTypeAttrsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindFacilityTypeAttrsBy query = new FindFacilityTypeAttrsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<FacilityTypeAttr> facilityTypeAttrs =((FacilityTypeAttrFound) Scheduler.execute(query).data()).getFacilityTypeAttrs();

		return ResponseEntity.ok().body(facilityTypeAttrs);

	}

	/**
	 * creates a new FacilityTypeAttr entry in the ofbiz database
	 * 
	 * @param facilityTypeAttrToBeAdded
	 *            the FacilityTypeAttr thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<FacilityTypeAttr> createFacilityTypeAttr(@RequestBody FacilityTypeAttr facilityTypeAttrToBeAdded) throws Exception {

		AddFacilityTypeAttr command = new AddFacilityTypeAttr(facilityTypeAttrToBeAdded);
		FacilityTypeAttr facilityTypeAttr = ((FacilityTypeAttrAdded) Scheduler.execute(command).data()).getAddedFacilityTypeAttr();
		
		if (facilityTypeAttr != null) 
			return successful(facilityTypeAttr);
		else 
			return conflict(null);
	}

	/**
	 * Updates the FacilityTypeAttr with the specific Id
	 * 
	 * @param facilityTypeAttrToBeUpdated
	 *            the FacilityTypeAttr thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{attrName}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateFacilityTypeAttr(@RequestBody FacilityTypeAttr facilityTypeAttrToBeUpdated,
			@PathVariable String attrName) throws Exception {

		facilityTypeAttrToBeUpdated.setAttrName(attrName);

		UpdateFacilityTypeAttr command = new UpdateFacilityTypeAttr(facilityTypeAttrToBeUpdated);

		try {
			if(((FacilityTypeAttrUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{facilityTypeAttrId}")
	public ResponseEntity<FacilityTypeAttr> findById(@PathVariable String facilityTypeAttrId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("facilityTypeAttrId", facilityTypeAttrId);
		try {

			List<FacilityTypeAttr> foundFacilityTypeAttr = findFacilityTypeAttrsBy(requestParams).getBody();
			if(foundFacilityTypeAttr.size()==1){				return successful(foundFacilityTypeAttr.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{facilityTypeAttrId}")
	public ResponseEntity<String> deleteFacilityTypeAttrByIdUpdated(@PathVariable String facilityTypeAttrId) throws Exception {
		DeleteFacilityTypeAttr command = new DeleteFacilityTypeAttr(facilityTypeAttrId);

		try {
			if (((FacilityTypeAttrDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
