package com.skytala.eCommerce.domain.marketing.relations.segmentGroup.control.role;

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
import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.command.role.AddSegmentGroupRole;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.command.role.DeleteSegmentGroupRole;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.command.role.UpdateSegmentGroupRole;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.event.role.SegmentGroupRoleAdded;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.event.role.SegmentGroupRoleDeleted;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.event.role.SegmentGroupRoleFound;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.event.role.SegmentGroupRoleUpdated;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.mapper.role.SegmentGroupRoleMapper;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.model.role.SegmentGroupRole;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.query.role.FindSegmentGroupRolesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/marketing/segmentGroup/segmentGroupRoles")
public class SegmentGroupRoleController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public SegmentGroupRoleController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a SegmentGroupRole
	 * @return a List with the SegmentGroupRoles
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findSegmentGroupRolesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindSegmentGroupRolesBy query = new FindSegmentGroupRolesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<SegmentGroupRole> segmentGroupRoles =((SegmentGroupRoleFound) Scheduler.execute(query).data()).getSegmentGroupRoles();

		if (segmentGroupRoles.size() == 1) {
			return ResponseEntity.ok().body(segmentGroupRoles.get(0));
		}

		return ResponseEntity.ok().body(segmentGroupRoles);

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
	public ResponseEntity<Object> createSegmentGroupRole(HttpServletRequest request) throws Exception {

		SegmentGroupRole segmentGroupRoleToBeAdded = new SegmentGroupRole();
		try {
			segmentGroupRoleToBeAdded = SegmentGroupRoleMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createSegmentGroupRole(segmentGroupRoleToBeAdded);

	}

	/**
	 * creates a new SegmentGroupRole entry in the ofbiz database
	 * 
	 * @param segmentGroupRoleToBeAdded
	 *            the SegmentGroupRole thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createSegmentGroupRole(@RequestBody SegmentGroupRole segmentGroupRoleToBeAdded) throws Exception {

		AddSegmentGroupRole command = new AddSegmentGroupRole(segmentGroupRoleToBeAdded);
		SegmentGroupRole segmentGroupRole = ((SegmentGroupRoleAdded) Scheduler.execute(command).data()).getAddedSegmentGroupRole();
		
		if (segmentGroupRole != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(segmentGroupRole);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("SegmentGroupRole could not be created.");
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
	public boolean updateSegmentGroupRole(HttpServletRequest request) throws Exception {

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

		SegmentGroupRole segmentGroupRoleToBeUpdated = new SegmentGroupRole();

		try {
			segmentGroupRoleToBeUpdated = SegmentGroupRoleMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateSegmentGroupRole(segmentGroupRoleToBeUpdated, segmentGroupRoleToBeUpdated.getRoleTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the SegmentGroupRole with the specific Id
	 * 
	 * @param segmentGroupRoleToBeUpdated
	 *            the SegmentGroupRole thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{roleTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateSegmentGroupRole(@RequestBody SegmentGroupRole segmentGroupRoleToBeUpdated,
			@PathVariable String roleTypeId) throws Exception {

		segmentGroupRoleToBeUpdated.setRoleTypeId(roleTypeId);

		UpdateSegmentGroupRole command = new UpdateSegmentGroupRole(segmentGroupRoleToBeUpdated);

		try {
			if(((SegmentGroupRoleUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{segmentGroupRoleId}")
	public ResponseEntity<Object> findById(@PathVariable String segmentGroupRoleId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("segmentGroupRoleId", segmentGroupRoleId);
		try {

			Object foundSegmentGroupRole = findSegmentGroupRolesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundSegmentGroupRole);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{segmentGroupRoleId}")
	public ResponseEntity<Object> deleteSegmentGroupRoleByIdUpdated(@PathVariable String segmentGroupRoleId) throws Exception {
		DeleteSegmentGroupRole command = new DeleteSegmentGroupRole(segmentGroupRoleId);

		try {
			if (((SegmentGroupRoleDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("SegmentGroupRole could not be deleted");

	}

}
