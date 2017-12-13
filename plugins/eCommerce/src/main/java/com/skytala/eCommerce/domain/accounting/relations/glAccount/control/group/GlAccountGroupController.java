package com.skytala.eCommerce.domain.accounting.relations.glAccount.control.group;

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
import com.skytala.eCommerce.domain.accounting.relations.glAccount.command.group.AddGlAccountGroup;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.command.group.DeleteGlAccountGroup;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.command.group.UpdateGlAccountGroup;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.group.GlAccountGroupAdded;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.group.GlAccountGroupDeleted;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.group.GlAccountGroupFound;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.group.GlAccountGroupUpdated;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.mapper.group.GlAccountGroupMapper;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.group.GlAccountGroup;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.query.group.FindGlAccountGroupsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/accounting/glAccount/glAccountGroups")
public class GlAccountGroupController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public GlAccountGroupController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a GlAccountGroup
	 * @return a List with the GlAccountGroups
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findGlAccountGroupsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindGlAccountGroupsBy query = new FindGlAccountGroupsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<GlAccountGroup> glAccountGroups =((GlAccountGroupFound) Scheduler.execute(query).data()).getGlAccountGroups();

		if (glAccountGroups.size() == 1) {
			return ResponseEntity.ok().body(glAccountGroups.get(0));
		}

		return ResponseEntity.ok().body(glAccountGroups);

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
	public ResponseEntity<Object> createGlAccountGroup(HttpServletRequest request) throws Exception {

		GlAccountGroup glAccountGroupToBeAdded = new GlAccountGroup();
		try {
			glAccountGroupToBeAdded = GlAccountGroupMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createGlAccountGroup(glAccountGroupToBeAdded);

	}

	/**
	 * creates a new GlAccountGroup entry in the ofbiz database
	 * 
	 * @param glAccountGroupToBeAdded
	 *            the GlAccountGroup thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createGlAccountGroup(@RequestBody GlAccountGroup glAccountGroupToBeAdded) throws Exception {

		AddGlAccountGroup command = new AddGlAccountGroup(glAccountGroupToBeAdded);
		GlAccountGroup glAccountGroup = ((GlAccountGroupAdded) Scheduler.execute(command).data()).getAddedGlAccountGroup();
		
		if (glAccountGroup != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(glAccountGroup);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("GlAccountGroup could not be created.");
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
	public boolean updateGlAccountGroup(HttpServletRequest request) throws Exception {

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

		GlAccountGroup glAccountGroupToBeUpdated = new GlAccountGroup();

		try {
			glAccountGroupToBeUpdated = GlAccountGroupMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateGlAccountGroup(glAccountGroupToBeUpdated, glAccountGroupToBeUpdated.getGlAccountGroupId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the GlAccountGroup with the specific Id
	 * 
	 * @param glAccountGroupToBeUpdated
	 *            the GlAccountGroup thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{glAccountGroupId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateGlAccountGroup(@RequestBody GlAccountGroup glAccountGroupToBeUpdated,
			@PathVariable String glAccountGroupId) throws Exception {

		glAccountGroupToBeUpdated.setGlAccountGroupId(glAccountGroupId);

		UpdateGlAccountGroup command = new UpdateGlAccountGroup(glAccountGroupToBeUpdated);

		try {
			if(((GlAccountGroupUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{glAccountGroupId}")
	public ResponseEntity<Object> findById(@PathVariable String glAccountGroupId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("glAccountGroupId", glAccountGroupId);
		try {

			Object foundGlAccountGroup = findGlAccountGroupsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundGlAccountGroup);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{glAccountGroupId}")
	public ResponseEntity<Object> deleteGlAccountGroupByIdUpdated(@PathVariable String glAccountGroupId) throws Exception {
		DeleteGlAccountGroup command = new DeleteGlAccountGroup(glAccountGroupId);

		try {
			if (((GlAccountGroupDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("GlAccountGroup could not be deleted");

	}

}
