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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<Object> findGlAccountCategoryMembersBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindGlAccountCategoryMembersBy query = new FindGlAccountCategoryMembersBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<GlAccountCategoryMember> glAccountCategoryMembers =((GlAccountCategoryMemberFound) Scheduler.execute(query).data()).getGlAccountCategoryMembers();

		if (glAccountCategoryMembers.size() == 1) {
			return ResponseEntity.ok().body(glAccountCategoryMembers.get(0));
		}

		return ResponseEntity.ok().body(glAccountCategoryMembers);

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
	public ResponseEntity<Object> createGlAccountCategoryMember(HttpServletRequest request) throws Exception {

		GlAccountCategoryMember glAccountCategoryMemberToBeAdded = new GlAccountCategoryMember();
		try {
			glAccountCategoryMemberToBeAdded = GlAccountCategoryMemberMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createGlAccountCategoryMember(glAccountCategoryMemberToBeAdded);

	}

	/**
	 * creates a new GlAccountCategoryMember entry in the ofbiz database
	 * 
	 * @param glAccountCategoryMemberToBeAdded
	 *            the GlAccountCategoryMember thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createGlAccountCategoryMember(@RequestBody GlAccountCategoryMember glAccountCategoryMemberToBeAdded) throws Exception {

		AddGlAccountCategoryMember command = new AddGlAccountCategoryMember(glAccountCategoryMemberToBeAdded);
		GlAccountCategoryMember glAccountCategoryMember = ((GlAccountCategoryMemberAdded) Scheduler.execute(command).data()).getAddedGlAccountCategoryMember();
		
		if (glAccountCategoryMember != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(glAccountCategoryMember);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("GlAccountCategoryMember could not be created.");
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
	public boolean updateGlAccountCategoryMember(HttpServletRequest request) throws Exception {

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

		GlAccountCategoryMember glAccountCategoryMemberToBeUpdated = new GlAccountCategoryMember();

		try {
			glAccountCategoryMemberToBeUpdated = GlAccountCategoryMemberMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateGlAccountCategoryMember(glAccountCategoryMemberToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateGlAccountCategoryMember(@RequestBody GlAccountCategoryMember glAccountCategoryMemberToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		glAccountCategoryMemberToBeUpdated.setnull(null);

		UpdateGlAccountCategoryMember command = new UpdateGlAccountCategoryMember(glAccountCategoryMemberToBeUpdated);

		try {
			if(((GlAccountCategoryMemberUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{glAccountCategoryMemberId}")
	public ResponseEntity<Object> findById(@PathVariable String glAccountCategoryMemberId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("glAccountCategoryMemberId", glAccountCategoryMemberId);
		try {

			Object foundGlAccountCategoryMember = findGlAccountCategoryMembersBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundGlAccountCategoryMember);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{glAccountCategoryMemberId}")
	public ResponseEntity<Object> deleteGlAccountCategoryMemberByIdUpdated(@PathVariable String glAccountCategoryMemberId) throws Exception {
		DeleteGlAccountCategoryMember command = new DeleteGlAccountCategoryMember(glAccountCategoryMemberId);

		try {
			if (((GlAccountCategoryMemberDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("GlAccountCategoryMember could not be deleted");

	}

}
