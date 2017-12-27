package com.skytala.eCommerce.domain.party.relations.roleType.control.attr;

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
import com.skytala.eCommerce.domain.party.relations.roleType.command.attr.AddRoleTypeAttr;
import com.skytala.eCommerce.domain.party.relations.roleType.command.attr.DeleteRoleTypeAttr;
import com.skytala.eCommerce.domain.party.relations.roleType.command.attr.UpdateRoleTypeAttr;
import com.skytala.eCommerce.domain.party.relations.roleType.event.attr.RoleTypeAttrAdded;
import com.skytala.eCommerce.domain.party.relations.roleType.event.attr.RoleTypeAttrDeleted;
import com.skytala.eCommerce.domain.party.relations.roleType.event.attr.RoleTypeAttrFound;
import com.skytala.eCommerce.domain.party.relations.roleType.event.attr.RoleTypeAttrUpdated;
import com.skytala.eCommerce.domain.party.relations.roleType.mapper.attr.RoleTypeAttrMapper;
import com.skytala.eCommerce.domain.party.relations.roleType.model.attr.RoleTypeAttr;
import com.skytala.eCommerce.domain.party.relations.roleType.query.attr.FindRoleTypeAttrsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/party/roleType/roleTypeAttrs")
public class RoleTypeAttrController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public RoleTypeAttrController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a RoleTypeAttr
	 * @return a List with the RoleTypeAttrs
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<RoleTypeAttr>> findRoleTypeAttrsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindRoleTypeAttrsBy query = new FindRoleTypeAttrsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<RoleTypeAttr> roleTypeAttrs =((RoleTypeAttrFound) Scheduler.execute(query).data()).getRoleTypeAttrs();

		return ResponseEntity.ok().body(roleTypeAttrs);

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
	public ResponseEntity<RoleTypeAttr> createRoleTypeAttr(HttpServletRequest request) throws Exception {

		RoleTypeAttr roleTypeAttrToBeAdded = new RoleTypeAttr();
		try {
			roleTypeAttrToBeAdded = RoleTypeAttrMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createRoleTypeAttr(roleTypeAttrToBeAdded);

	}

	/**
	 * creates a new RoleTypeAttr entry in the ofbiz database
	 * 
	 * @param roleTypeAttrToBeAdded
	 *            the RoleTypeAttr thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<RoleTypeAttr> createRoleTypeAttr(@RequestBody RoleTypeAttr roleTypeAttrToBeAdded) throws Exception {

		AddRoleTypeAttr command = new AddRoleTypeAttr(roleTypeAttrToBeAdded);
		RoleTypeAttr roleTypeAttr = ((RoleTypeAttrAdded) Scheduler.execute(command).data()).getAddedRoleTypeAttr();
		
		if (roleTypeAttr != null) 
			return successful(roleTypeAttr);
		else 
			return conflict(null);
	}

	/**
	 * Updates the RoleTypeAttr with the specific Id
	 * 
	 * @param roleTypeAttrToBeUpdated
	 *            the RoleTypeAttr thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{attrName}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateRoleTypeAttr(@RequestBody RoleTypeAttr roleTypeAttrToBeUpdated,
			@PathVariable String attrName) throws Exception {

		roleTypeAttrToBeUpdated.setAttrName(attrName);

		UpdateRoleTypeAttr command = new UpdateRoleTypeAttr(roleTypeAttrToBeUpdated);

		try {
			if(((RoleTypeAttrUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{roleTypeAttrId}")
	public ResponseEntity<RoleTypeAttr> findById(@PathVariable String roleTypeAttrId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("roleTypeAttrId", roleTypeAttrId);
		try {

			List<RoleTypeAttr> foundRoleTypeAttr = findRoleTypeAttrsBy(requestParams).getBody();
			if(foundRoleTypeAttr.size()==1){				return successful(foundRoleTypeAttr.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{roleTypeAttrId}")
	public ResponseEntity<String> deleteRoleTypeAttrByIdUpdated(@PathVariable String roleTypeAttrId) throws Exception {
		DeleteRoleTypeAttr command = new DeleteRoleTypeAttr(roleTypeAttrId);

		try {
			if (((RoleTypeAttrDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
