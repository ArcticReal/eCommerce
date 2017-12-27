package com.skytala.eCommerce.domain.product.relations.facility.control.attribute;

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
import com.skytala.eCommerce.domain.product.relations.facility.command.attribute.AddFacilityAttribute;
import com.skytala.eCommerce.domain.product.relations.facility.command.attribute.DeleteFacilityAttribute;
import com.skytala.eCommerce.domain.product.relations.facility.command.attribute.UpdateFacilityAttribute;
import com.skytala.eCommerce.domain.product.relations.facility.event.attribute.FacilityAttributeAdded;
import com.skytala.eCommerce.domain.product.relations.facility.event.attribute.FacilityAttributeDeleted;
import com.skytala.eCommerce.domain.product.relations.facility.event.attribute.FacilityAttributeFound;
import com.skytala.eCommerce.domain.product.relations.facility.event.attribute.FacilityAttributeUpdated;
import com.skytala.eCommerce.domain.product.relations.facility.mapper.attribute.FacilityAttributeMapper;
import com.skytala.eCommerce.domain.product.relations.facility.model.attribute.FacilityAttribute;
import com.skytala.eCommerce.domain.product.relations.facility.query.attribute.FindFacilityAttributesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/facility/facilityAttributes")
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
	@GetMapping("/find")
	public ResponseEntity<List<FacilityAttribute>> findFacilityAttributesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindFacilityAttributesBy query = new FindFacilityAttributesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<FacilityAttribute> facilityAttributes =((FacilityAttributeFound) Scheduler.execute(query).data()).getFacilityAttributes();

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
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<FacilityAttribute> createFacilityAttribute(HttpServletRequest request) throws Exception {

		FacilityAttribute facilityAttributeToBeAdded = new FacilityAttribute();
		try {
			facilityAttributeToBeAdded = FacilityAttributeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
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
	public ResponseEntity<FacilityAttribute> createFacilityAttribute(@RequestBody FacilityAttribute facilityAttributeToBeAdded) throws Exception {

		AddFacilityAttribute command = new AddFacilityAttribute(facilityAttributeToBeAdded);
		FacilityAttribute facilityAttribute = ((FacilityAttributeAdded) Scheduler.execute(command).data()).getAddedFacilityAttribute();
		
		if (facilityAttribute != null) 
			return successful(facilityAttribute);
		else 
			return conflict(null);
	}

	/**
	 * Updates the FacilityAttribute with the specific Id
	 * 
	 * @param facilityAttributeToBeUpdated
	 *            the FacilityAttribute thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{attrName}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateFacilityAttribute(@RequestBody FacilityAttribute facilityAttributeToBeUpdated,
			@PathVariable String attrName) throws Exception {

		facilityAttributeToBeUpdated.setAttrName(attrName);

		UpdateFacilityAttribute command = new UpdateFacilityAttribute(facilityAttributeToBeUpdated);

		try {
			if(((FacilityAttributeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{facilityAttributeId}")
	public ResponseEntity<FacilityAttribute> findById(@PathVariable String facilityAttributeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("facilityAttributeId", facilityAttributeId);
		try {

			List<FacilityAttribute> foundFacilityAttribute = findFacilityAttributesBy(requestParams).getBody();
			if(foundFacilityAttribute.size()==1){				return successful(foundFacilityAttribute.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{facilityAttributeId}")
	public ResponseEntity<String> deleteFacilityAttributeByIdUpdated(@PathVariable String facilityAttributeId) throws Exception {
		DeleteFacilityAttribute command = new DeleteFacilityAttribute(facilityAttributeId);

		try {
			if (((FacilityAttributeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
