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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	public ResponseEntity<List<SegmentGroupRole>> findSegmentGroupRolesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindSegmentGroupRolesBy query = new FindSegmentGroupRolesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<SegmentGroupRole> segmentGroupRoles =((SegmentGroupRoleFound) Scheduler.execute(query).data()).getSegmentGroupRoles();

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
	public ResponseEntity<SegmentGroupRole> createSegmentGroupRole(HttpServletRequest request) throws Exception {

		SegmentGroupRole segmentGroupRoleToBeAdded = new SegmentGroupRole();
		try {
			segmentGroupRoleToBeAdded = SegmentGroupRoleMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
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
	public ResponseEntity<SegmentGroupRole> createSegmentGroupRole(@RequestBody SegmentGroupRole segmentGroupRoleToBeAdded) throws Exception {

		AddSegmentGroupRole command = new AddSegmentGroupRole(segmentGroupRoleToBeAdded);
		SegmentGroupRole segmentGroupRole = ((SegmentGroupRoleAdded) Scheduler.execute(command).data()).getAddedSegmentGroupRole();
		
		if (segmentGroupRole != null) 
			return successful(segmentGroupRole);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateSegmentGroupRole(@RequestBody SegmentGroupRole segmentGroupRoleToBeUpdated,
			@PathVariable String roleTypeId) throws Exception {

		segmentGroupRoleToBeUpdated.setRoleTypeId(roleTypeId);

		UpdateSegmentGroupRole command = new UpdateSegmentGroupRole(segmentGroupRoleToBeUpdated);

		try {
			if(((SegmentGroupRoleUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{segmentGroupRoleId}")
	public ResponseEntity<SegmentGroupRole> findById(@PathVariable String segmentGroupRoleId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("segmentGroupRoleId", segmentGroupRoleId);
		try {

			List<SegmentGroupRole> foundSegmentGroupRole = findSegmentGroupRolesBy(requestParams).getBody();
			if(foundSegmentGroupRole.size()==1){				return successful(foundSegmentGroupRole.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{segmentGroupRoleId}")
	public ResponseEntity<String> deleteSegmentGroupRoleByIdUpdated(@PathVariable String segmentGroupRoleId) throws Exception {
		DeleteSegmentGroupRole command = new DeleteSegmentGroupRole(segmentGroupRoleId);

		try {
			if (((SegmentGroupRoleDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
