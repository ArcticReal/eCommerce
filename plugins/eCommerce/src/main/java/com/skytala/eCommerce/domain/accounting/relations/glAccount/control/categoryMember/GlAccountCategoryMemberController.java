package com.skytala.eCommerce.domain.accounting.relations.glAccount.control.categoryMember;

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
import com.skytala.eCommerce.domain.accounting.relations.glAccount.command.categoryMember.AddGlAccountCategoryMember;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.command.categoryMember.DeleteGlAccountCategoryMember;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.command.categoryMember.UpdateGlAccountCategoryMember;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.categoryMember.GlAccountCategoryMemberAdded;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.categoryMember.GlAccountCategoryMemberDeleted;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.categoryMember.GlAccountCategoryMemberFound;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.categoryMember.GlAccountCategoryMemberUpdated;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.mapper.categoryMember.GlAccountCategoryMemberMapper;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.categoryMember.GlAccountCategoryMember;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.query.categoryMember.FindGlAccountCategoryMembersBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/glAccount/glAccountCategoryMembers")
public class GlAccountCategoryMemberController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public GlAccountCategoryMemberController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a GlAccountCategoryMember
	 * @return a List with the GlAccountCategoryMembers
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<GlAccountCategoryMember>> findGlAccountCategoryMembersBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindGlAccountCategoryMembersBy query = new FindGlAccountCategoryMembersBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<GlAccountCategoryMember> glAccountCategoryMembers =((GlAccountCategoryMemberFound) Scheduler.execute(query).data()).getGlAccountCategoryMembers();

		return ResponseEntity.ok().body(glAccountCategoryMembers);

	}

	/**
	 * creates a new GlAccountCategoryMember entry in the ofbiz database
	 * 
	 * @param glAccountCategoryMemberToBeAdded
	 *            the GlAccountCategoryMember thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<GlAccountCategoryMember> createGlAccountCategoryMember(@RequestBody GlAccountCategoryMember glAccountCategoryMemberToBeAdded) throws Exception {

		AddGlAccountCategoryMember command = new AddGlAccountCategoryMember(glAccountCategoryMemberToBeAdded);
		GlAccountCategoryMember glAccountCategoryMember = ((GlAccountCategoryMemberAdded) Scheduler.execute(command).data()).getAddedGlAccountCategoryMember();
		
		if (glAccountCategoryMember != null) 
			return successful(glAccountCategoryMember);
		else 
			return conflict(null);
	}

	/**
	 * Updates the GlAccountCategoryMember with the specific Id
	 * 
	 * @param glAccountCategoryMemberToBeUpdated
	 *            the GlAccountCategoryMember thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateGlAccountCategoryMember(@RequestBody GlAccountCategoryMember glAccountCategoryMemberToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		glAccountCategoryMemberToBeUpdated.setnull(null);

		UpdateGlAccountCategoryMember command = new UpdateGlAccountCategoryMember(glAccountCategoryMemberToBeUpdated);

		try {
			if(((GlAccountCategoryMemberUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{glAccountCategoryMemberId}")
	public ResponseEntity<GlAccountCategoryMember> findById(@PathVariable String glAccountCategoryMemberId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("glAccountCategoryMemberId", glAccountCategoryMemberId);
		try {

			List<GlAccountCategoryMember> foundGlAccountCategoryMember = findGlAccountCategoryMembersBy(requestParams).getBody();
			if(foundGlAccountCategoryMember.size()==1){				return successful(foundGlAccountCategoryMember.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{glAccountCategoryMemberId}")
	public ResponseEntity<String> deleteGlAccountCategoryMemberByIdUpdated(@PathVariable String glAccountCategoryMemberId) throws Exception {
		DeleteGlAccountCategoryMember command = new DeleteGlAccountCategoryMember(glAccountCategoryMemberId);

		try {
			if (((GlAccountCategoryMemberDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
