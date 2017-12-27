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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	public ResponseEntity<List<GlAccountGroup>> findGlAccountGroupsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindGlAccountGroupsBy query = new FindGlAccountGroupsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<GlAccountGroup> glAccountGroups =((GlAccountGroupFound) Scheduler.execute(query).data()).getGlAccountGroups();

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
	public ResponseEntity<GlAccountGroup> createGlAccountGroup(HttpServletRequest request) throws Exception {

		GlAccountGroup glAccountGroupToBeAdded = new GlAccountGroup();
		try {
			glAccountGroupToBeAdded = GlAccountGroupMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
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
	public ResponseEntity<GlAccountGroup> createGlAccountGroup(@RequestBody GlAccountGroup glAccountGroupToBeAdded) throws Exception {

		AddGlAccountGroup command = new AddGlAccountGroup(glAccountGroupToBeAdded);
		GlAccountGroup glAccountGroup = ((GlAccountGroupAdded) Scheduler.execute(command).data()).getAddedGlAccountGroup();
		
		if (glAccountGroup != null) 
			return successful(glAccountGroup);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateGlAccountGroup(@RequestBody GlAccountGroup glAccountGroupToBeUpdated,
			@PathVariable String glAccountGroupId) throws Exception {

		glAccountGroupToBeUpdated.setGlAccountGroupId(glAccountGroupId);

		UpdateGlAccountGroup command = new UpdateGlAccountGroup(glAccountGroupToBeUpdated);

		try {
			if(((GlAccountGroupUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{glAccountGroupId}")
	public ResponseEntity<GlAccountGroup> findById(@PathVariable String glAccountGroupId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("glAccountGroupId", glAccountGroupId);
		try {

			List<GlAccountGroup> foundGlAccountGroup = findGlAccountGroupsBy(requestParams).getBody();
			if(foundGlAccountGroup.size()==1){				return successful(foundGlAccountGroup.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{glAccountGroupId}")
	public ResponseEntity<String> deleteGlAccountGroupByIdUpdated(@PathVariable String glAccountGroupId) throws Exception {
		DeleteGlAccountGroup command = new DeleteGlAccountGroup(glAccountGroupId);

		try {
			if (((GlAccountGroupDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
