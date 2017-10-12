package com.skytala.eCommerce.domain.accounting.relations.glAccountGroupMember;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Splitter;
import com.skytala.eCommerce.domain.accounting.relations.glAccountGroupMember.command.AddGlAccountGroupMember;
import com.skytala.eCommerce.domain.accounting.relations.glAccountGroupMember.command.DeleteGlAccountGroupMember;
import com.skytala.eCommerce.domain.accounting.relations.glAccountGroupMember.command.UpdateGlAccountGroupMember;
import com.skytala.eCommerce.domain.accounting.relations.glAccountGroupMember.event.GlAccountGroupMemberAdded;
import com.skytala.eCommerce.domain.accounting.relations.glAccountGroupMember.event.GlAccountGroupMemberDeleted;
import com.skytala.eCommerce.domain.accounting.relations.glAccountGroupMember.event.GlAccountGroupMemberFound;
import com.skytala.eCommerce.domain.accounting.relations.glAccountGroupMember.event.GlAccountGroupMemberUpdated;
import com.skytala.eCommerce.domain.accounting.relations.glAccountGroupMember.mapper.GlAccountGroupMemberMapper;
import com.skytala.eCommerce.domain.accounting.relations.glAccountGroupMember.model.GlAccountGroupMember;
import com.skytala.eCommerce.domain.accounting.relations.glAccountGroupMember.query.FindGlAccountGroupMembersBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/glAccountGroupMembers")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findGlAccountGroupMembersBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindGlAccountGroupMembersBy query = new FindGlAccountGroupMembersBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<GlAccountGroupMember> glAccountGroupMembers =((GlAccountGroupMemberFound) Scheduler.execute(query).data()).getGlAccountGroupMembers();

		if (glAccountGroupMembers.size() == 1) {
			return ResponseEntity.ok().body(glAccountGroupMembers.get(0));
		}

		return ResponseEntity.ok().body(glAccountGroupMembers);

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
	public ResponseEntity<Object> createGlAccountGroupMember(HttpServletRequest request) throws Exception {

		GlAccountGroupMember glAccountGroupMemberToBeAdded = new GlAccountGroupMember();
		try {
			glAccountGroupMemberToBeAdded = GlAccountGroupMemberMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createGlAccountGroupMember(glAccountGroupMemberToBeAdded);

	}

	/**
	 * creates a new GlAccountGroupMember entry in the ofbiz database
	 * 
	 * @param glAccountGroupMemberToBeAdded
	 *            the GlAccountGroupMember thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createGlAccountGroupMember(@RequestBody GlAccountGroupMember glAccountGroupMemberToBeAdded) throws Exception {

		AddGlAccountGroupMember command = new AddGlAccountGroupMember(glAccountGroupMemberToBeAdded);
		GlAccountGroupMember glAccountGroupMember = ((GlAccountGroupMemberAdded) Scheduler.execute(command).data()).getAddedGlAccountGroupMember();
		
		if (glAccountGroupMember != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(glAccountGroupMember);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("GlAccountGroupMember could not be created.");
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
	public boolean updateGlAccountGroupMember(HttpServletRequest request) throws Exception {

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

		GlAccountGroupMember glAccountGroupMemberToBeUpdated = new GlAccountGroupMember();

		try {
			glAccountGroupMemberToBeUpdated = GlAccountGroupMemberMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateGlAccountGroupMember(glAccountGroupMemberToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateGlAccountGroupMember(@RequestBody GlAccountGroupMember glAccountGroupMemberToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		glAccountGroupMemberToBeUpdated.setnull(null);

		UpdateGlAccountGroupMember command = new UpdateGlAccountGroupMember(glAccountGroupMemberToBeUpdated);

		try {
			if(((GlAccountGroupMemberUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{glAccountGroupMemberId}")
	public ResponseEntity<Object> findById(@PathVariable String glAccountGroupMemberId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("glAccountGroupMemberId", glAccountGroupMemberId);
		try {

			Object foundGlAccountGroupMember = findGlAccountGroupMembersBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundGlAccountGroupMember);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{glAccountGroupMemberId}")
	public ResponseEntity<Object> deleteGlAccountGroupMemberByIdUpdated(@PathVariable String glAccountGroupMemberId) throws Exception {
		DeleteGlAccountGroupMember command = new DeleteGlAccountGroupMember(glAccountGroupMemberId);

		try {
			if (((GlAccountGroupMemberDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("GlAccountGroupMember could not be deleted");

	}

	@RequestMapping(value = (" ** "))
	public ResponseEntity<Object> returnErrorPage(HttpServletRequest request) {

		String usedUri = request.getRequestURI();
		String[] splittedString = usedUri.split("/");

		String usedRequest = splittedString[splittedString.length - 1];

		if (validRequests.containsKey(usedRequest)) {
			String returnVal = "Error: request method " + request.getMethod() + " not allowed for \"" + usedUri
					+ "\"!\n" + "Please use " + validRequests.get(usedRequest) + "!";

			return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(returnVal);
		}

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/glAccountGroupMember/\" plus one of the following: "
				+ "";

		Set<String> keySet = validRequests.keySet();
		Iterator<String> it = keySet.iterator();

		while (it.hasNext()) {
			returnVal += "\"" + it.next() + "\"";
			if (it.hasNext())
				returnVal += ", ";
		}

		returnVal += "!";

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnVal);

	}
}
