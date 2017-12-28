package com.skytala.eCommerce.domain.product.relations.facility.control.content;

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
import com.skytala.eCommerce.domain.product.relations.facility.command.content.AddFacilityContent;
import com.skytala.eCommerce.domain.product.relations.facility.command.content.DeleteFacilityContent;
import com.skytala.eCommerce.domain.product.relations.facility.command.content.UpdateFacilityContent;
import com.skytala.eCommerce.domain.product.relations.facility.event.content.FacilityContentAdded;
import com.skytala.eCommerce.domain.product.relations.facility.event.content.FacilityContentDeleted;
import com.skytala.eCommerce.domain.product.relations.facility.event.content.FacilityContentFound;
import com.skytala.eCommerce.domain.product.relations.facility.event.content.FacilityContentUpdated;
import com.skytala.eCommerce.domain.product.relations.facility.mapper.content.FacilityContentMapper;
import com.skytala.eCommerce.domain.product.relations.facility.model.content.FacilityContent;
import com.skytala.eCommerce.domain.product.relations.facility.query.content.FindFacilityContentsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/facility/facilityContents")
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
	@GetMapping("/find")
	public ResponseEntity<List<FacilityContent>> findFacilityContentsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindFacilityContentsBy query = new FindFacilityContentsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<FacilityContent> facilityContents =((FacilityContentFound) Scheduler.execute(query).data()).getFacilityContents();

		return ResponseEntity.ok().body(facilityContents);

	}

	/**
	 * creates a new FacilityContent entry in the ofbiz database
	 * 
	 * @param facilityContentToBeAdded
	 *            the FacilityContent thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<FacilityContent> createFacilityContent(@RequestBody FacilityContent facilityContentToBeAdded) throws Exception {

		AddFacilityContent command = new AddFacilityContent(facilityContentToBeAdded);
		FacilityContent facilityContent = ((FacilityContentAdded) Scheduler.execute(command).data()).getAddedFacilityContent();
		
		if (facilityContent != null) 
			return successful(facilityContent);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateFacilityContent(@RequestBody FacilityContent facilityContentToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		facilityContentToBeUpdated.setnull(null);

		UpdateFacilityContent command = new UpdateFacilityContent(facilityContentToBeUpdated);

		try {
			if(((FacilityContentUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{facilityContentId}")
	public ResponseEntity<FacilityContent> findById(@PathVariable String facilityContentId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("facilityContentId", facilityContentId);
		try {

			List<FacilityContent> foundFacilityContent = findFacilityContentsBy(requestParams).getBody();
			if(foundFacilityContent.size()==1){				return successful(foundFacilityContent.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{facilityContentId}")
	public ResponseEntity<String> deleteFacilityContentByIdUpdated(@PathVariable String facilityContentId) throws Exception {
		DeleteFacilityContent command = new DeleteFacilityContent(facilityContentId);

		try {
			if (((FacilityContentDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
