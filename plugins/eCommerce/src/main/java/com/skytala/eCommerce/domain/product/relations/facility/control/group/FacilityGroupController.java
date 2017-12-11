package com.skytala.eCommerce.domain.product.relations.facility.control.group;

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
import com.skytala.eCommerce.domain.product.relations.facility.command.group.AddFacilityGroup;
import com.skytala.eCommerce.domain.product.relations.facility.command.group.DeleteFacilityGroup;
import com.skytala.eCommerce.domain.product.relations.facility.command.group.UpdateFacilityGroup;
import com.skytala.eCommerce.domain.product.relations.facility.event.group.FacilityGroupAdded;
import com.skytala.eCommerce.domain.product.relations.facility.event.group.FacilityGroupDeleted;
import com.skytala.eCommerce.domain.product.relations.facility.event.group.FacilityGroupFound;
import com.skytala.eCommerce.domain.product.relations.facility.event.group.FacilityGroupUpdated;
import com.skytala.eCommerce.domain.product.relations.facility.mapper.group.FacilityGroupMapper;
import com.skytala.eCommerce.domain.product.relations.facility.model.group.FacilityGroup;
import com.skytala.eCommerce.domain.product.relations.facility.query.group.FindFacilityGroupsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/product/facility/facilityGroups")
public class FacilityGroupController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public FacilityGroupController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a FacilityGroup
	 * @return a List with the FacilityGroups
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findFacilityGroupsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindFacilityGroupsBy query = new FindFacilityGroupsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<FacilityGroup> facilityGroups =((FacilityGroupFound) Scheduler.execute(query).data()).getFacilityGroups();

		if (facilityGroups.size() == 1) {
			return ResponseEntity.ok().body(facilityGroups.get(0));
		}

		return ResponseEntity.ok().body(facilityGroups);

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
	public ResponseEntity<Object> createFacilityGroup(HttpServletRequest request) throws Exception {

		FacilityGroup facilityGroupToBeAdded = new FacilityGroup();
		try {
			facilityGroupToBeAdded = FacilityGroupMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createFacilityGroup(facilityGroupToBeAdded);

	}

	/**
	 * creates a new FacilityGroup entry in the ofbiz database
	 * 
	 * @param facilityGroupToBeAdded
	 *            the FacilityGroup thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createFacilityGroup(@RequestBody FacilityGroup facilityGroupToBeAdded) throws Exception {

		AddFacilityGroup command = new AddFacilityGroup(facilityGroupToBeAdded);
		FacilityGroup facilityGroup = ((FacilityGroupAdded) Scheduler.execute(command).data()).getAddedFacilityGroup();
		
		if (facilityGroup != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(facilityGroup);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("FacilityGroup could not be created.");
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
	@PutMapping(value = "/update", consumes = "application/x-www-form-urlencoded")
	public boolean updateFacilityGroup(HttpServletRequest request) throws Exception {

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

		FacilityGroup facilityGroupToBeUpdated = new FacilityGroup();

		try {
			facilityGroupToBeUpdated = FacilityGroupMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateFacilityGroup(facilityGroupToBeUpdated, facilityGroupToBeUpdated.getFacilityGroupId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the FacilityGroup with the specific Id
	 * 
	 * @param facilityGroupToBeUpdated
	 *            the FacilityGroup thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{facilityGroupId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateFacilityGroup(@RequestBody FacilityGroup facilityGroupToBeUpdated,
			@PathVariable String facilityGroupId) throws Exception {

		facilityGroupToBeUpdated.setFacilityGroupId(facilityGroupId);

		UpdateFacilityGroup command = new UpdateFacilityGroup(facilityGroupToBeUpdated);

		try {
			if(((FacilityGroupUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{facilityGroupId}")
	public ResponseEntity<Object> findById(@PathVariable String facilityGroupId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("facilityGroupId", facilityGroupId);
		try {

			Object foundFacilityGroup = findFacilityGroupsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundFacilityGroup);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{facilityGroupId}")
	public ResponseEntity<Object> deleteFacilityGroupByIdUpdated(@PathVariable String facilityGroupId) throws Exception {
		DeleteFacilityGroup command = new DeleteFacilityGroup(facilityGroupId);

		try {
			if (((FacilityGroupDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("FacilityGroup could not be deleted");

	}

}
