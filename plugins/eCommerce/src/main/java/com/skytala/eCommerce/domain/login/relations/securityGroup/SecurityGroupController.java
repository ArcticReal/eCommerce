package com.skytala.eCommerce.domain.login.relations.securityGroup;

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
import com.skytala.eCommerce.domain.login.relations.securityGroup.command.AddSecurityGroup;
import com.skytala.eCommerce.domain.login.relations.securityGroup.command.DeleteSecurityGroup;
import com.skytala.eCommerce.domain.login.relations.securityGroup.command.UpdateSecurityGroup;
import com.skytala.eCommerce.domain.login.relations.securityGroup.event.SecurityGroupAdded;
import com.skytala.eCommerce.domain.login.relations.securityGroup.event.SecurityGroupDeleted;
import com.skytala.eCommerce.domain.login.relations.securityGroup.event.SecurityGroupFound;
import com.skytala.eCommerce.domain.login.relations.securityGroup.event.SecurityGroupUpdated;
import com.skytala.eCommerce.domain.login.relations.securityGroup.mapper.SecurityGroupMapper;
import com.skytala.eCommerce.domain.login.relations.securityGroup.model.SecurityGroup;
import com.skytala.eCommerce.domain.login.relations.securityGroup.query.FindSecurityGroupsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/login/securityGroups")
public class SecurityGroupController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public SecurityGroupController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a SecurityGroup
	 * @return a List with the SecurityGroups
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<SecurityGroup>> findSecurityGroupsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindSecurityGroupsBy query = new FindSecurityGroupsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<SecurityGroup> securityGroups =((SecurityGroupFound) Scheduler.execute(query).data()).getSecurityGroups();

		return ResponseEntity.ok().body(securityGroups);

	}

	/**
	 * creates a new SecurityGroup entry in the ofbiz database
	 * 
	 * @param securityGroupToBeAdded
	 *            the SecurityGroup thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<SecurityGroup> createSecurityGroup(@RequestBody SecurityGroup securityGroupToBeAdded) throws Exception {

		AddSecurityGroup command = new AddSecurityGroup(securityGroupToBeAdded);
		SecurityGroup securityGroup = ((SecurityGroupAdded) Scheduler.execute(command).data()).getAddedSecurityGroup();
		
		if (securityGroup != null) 
			return successful(securityGroup);
		else 
			return conflict(null);
	}

	/**
	 * Updates the SecurityGroup with the specific Id
	 * 
	 * @param securityGroupToBeUpdated
	 *            the SecurityGroup thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{groupId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateSecurityGroup(@RequestBody SecurityGroup securityGroupToBeUpdated,
			@PathVariable String groupId) throws Exception {

		securityGroupToBeUpdated.setGroupId(groupId);

		UpdateSecurityGroup command = new UpdateSecurityGroup(securityGroupToBeUpdated);

		try {
			if(((SecurityGroupUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{securityGroupId}")
	public ResponseEntity<SecurityGroup> findById(@PathVariable String securityGroupId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("securityGroupId", securityGroupId);
		try {

			List<SecurityGroup> foundSecurityGroup = findSecurityGroupsBy(requestParams).getBody();
			if(foundSecurityGroup.size()==1){				return successful(foundSecurityGroup.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{securityGroupId}")
	public ResponseEntity<String> deleteSecurityGroupByIdUpdated(@PathVariable String securityGroupId) throws Exception {
		DeleteSecurityGroup command = new DeleteSecurityGroup(securityGroupId);

		try {
			if (((SecurityGroupDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
