package com.skytala.eCommerce.domain.accounting.relations.glAccount.control.groupMember;

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
import com.skytala.eCommerce.domain.accounting.relations.glAccount.command.groupMember.AddGlAccountGroupMember;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.command.groupMember.DeleteGlAccountGroupMember;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.command.groupMember.UpdateGlAccountGroupMember;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.groupMember.GlAccountGroupMemberAdded;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.groupMember.GlAccountGroupMemberDeleted;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.groupMember.GlAccountGroupMemberFound;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.groupMember.GlAccountGroupMemberUpdated;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.mapper.groupMember.GlAccountGroupMemberMapper;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.groupMember.GlAccountGroupMember;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.query.groupMember.FindGlAccountGroupMembersBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/glAccount/glAccountGroupMembers")
public class GlAccountGroupMemberController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public GlAccountGroupMemberController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a GlAccountGroupMember
	 * @return a List with the GlAccountGroupMembers
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<GlAccountGroupMember>> findGlAccountGroupMembersBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindGlAccountGroupMembersBy query = new FindGlAccountGroupMembersBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<GlAccountGroupMember> glAccountGroupMembers =((GlAccountGroupMemberFound) Scheduler.execute(query).data()).getGlAccountGroupMembers();

		return ResponseEntity.ok().body(glAccountGroupMembers);

	}

	/**
	 * creates a new GlAccountGroupMember entry in the ofbiz database
	 * 
	 * @param glAccountGroupMemberToBeAdded
	 *            the GlAccountGroupMember thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<GlAccountGroupMember> createGlAccountGroupMember(@RequestBody GlAccountGroupMember glAccountGroupMemberToBeAdded) throws Exception {

		AddGlAccountGroupMember command = new AddGlAccountGroupMember(glAccountGroupMemberToBeAdded);
		GlAccountGroupMember glAccountGroupMember = ((GlAccountGroupMemberAdded) Scheduler.execute(command).data()).getAddedGlAccountGroupMember();
		
		if (glAccountGroupMember != null) 
			return successful(glAccountGroupMember);
		else 
			return conflict(null);
	}

	/**
	 * Updates the GlAccountGroupMember with the specific Id
	 * 
	 * @param glAccountGroupMemberToBeUpdated
	 *            the GlAccountGroupMember thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateGlAccountGroupMember(@RequestBody GlAccountGroupMember glAccountGroupMemberToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		glAccountGroupMemberToBeUpdated.setnull(null);

		UpdateGlAccountGroupMember command = new UpdateGlAccountGroupMember(glAccountGroupMemberToBeUpdated);

		try {
			if(((GlAccountGroupMemberUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{glAccountGroupMemberId}")
	public ResponseEntity<GlAccountGroupMember> findById(@PathVariable String glAccountGroupMemberId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("glAccountGroupMemberId", glAccountGroupMemberId);
		try {

			List<GlAccountGroupMember> foundGlAccountGroupMember = findGlAccountGroupMembersBy(requestParams).getBody();
			if(foundGlAccountGroupMember.size()==1){				return successful(foundGlAccountGroupMember.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{glAccountGroupMemberId}")
	public ResponseEntity<String> deleteGlAccountGroupMemberByIdUpdated(@PathVariable String glAccountGroupMemberId) throws Exception {
		DeleteGlAccountGroupMember command = new DeleteGlAccountGroupMember(glAccountGroupMemberId);

		try {
			if (((GlAccountGroupMemberDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
