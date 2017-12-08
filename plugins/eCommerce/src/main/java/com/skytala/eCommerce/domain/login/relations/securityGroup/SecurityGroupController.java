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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@RequestMapping("/securityGroups")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findSecurityGroupsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindSecurityGroupsBy query = new FindSecurityGroupsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<SecurityGroup> securityGroups =((SecurityGroupFound) Scheduler.execute(query).data()).getSecurityGroups();

		if (securityGroups.size() == 1) {
			return ResponseEntity.ok().body(securityGroups.get(0));
		}

		return ResponseEntity.ok().body(securityGroups);

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
	public ResponseEntity<Object> createSecurityGroup(HttpServletRequest request) throws Exception {

		SecurityGroup securityGroupToBeAdded = new SecurityGroup();
		try {
			securityGroupToBeAdded = SecurityGroupMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createSecurityGroup(securityGroupToBeAdded);

	}

	/**
	 * creates a new SecurityGroup entry in the ofbiz database
	 * 
	 * @param securityGroupToBeAdded
	 *            the SecurityGroup thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createSecurityGroup(@RequestBody SecurityGroup securityGroupToBeAdded) throws Exception {

		AddSecurityGroup command = new AddSecurityGroup(securityGroupToBeAdded);
		SecurityGroup securityGroup = ((SecurityGroupAdded) Scheduler.execute(command).data()).getAddedSecurityGroup();
		
		if (securityGroup != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(securityGroup);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("SecurityGroup could not be created.");
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
	public boolean updateSecurityGroup(HttpServletRequest request) throws Exception {

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

		SecurityGroup securityGroupToBeUpdated = new SecurityGroup();

		try {
			securityGroupToBeUpdated = SecurityGroupMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateSecurityGroup(securityGroupToBeUpdated, securityGroupToBeUpdated.getGroupId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateSecurityGroup(@RequestBody SecurityGroup securityGroupToBeUpdated,
			@PathVariable String groupId) throws Exception {

		securityGroupToBeUpdated.setGroupId(groupId);

		UpdateSecurityGroup command = new UpdateSecurityGroup(securityGroupToBeUpdated);

		try {
			if(((SecurityGroupUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{securityGroupId}")
	public ResponseEntity<Object> findById(@PathVariable String securityGroupId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("securityGroupId", securityGroupId);
		try {

			Object foundSecurityGroup = findSecurityGroupsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundSecurityGroup);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{securityGroupId}")
	public ResponseEntity<Object> deleteSecurityGroupByIdUpdated(@PathVariable String securityGroupId) throws Exception {
		DeleteSecurityGroup command = new DeleteSecurityGroup(securityGroupId);

		try {
			if (((SecurityGroupDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("SecurityGroup could not be deleted");

	}

}
