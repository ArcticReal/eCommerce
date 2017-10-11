package com.skytala.eCommerce.domain.party.relations.roleTypeAttr;

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
import com.skytala.eCommerce.domain.party.relations.roleTypeAttr.command.AddRoleTypeAttr;
import com.skytala.eCommerce.domain.party.relations.roleTypeAttr.command.DeleteRoleTypeAttr;
import com.skytala.eCommerce.domain.party.relations.roleTypeAttr.command.UpdateRoleTypeAttr;
import com.skytala.eCommerce.domain.party.relations.roleTypeAttr.event.RoleTypeAttrAdded;
import com.skytala.eCommerce.domain.party.relations.roleTypeAttr.event.RoleTypeAttrDeleted;
import com.skytala.eCommerce.domain.party.relations.roleTypeAttr.event.RoleTypeAttrFound;
import com.skytala.eCommerce.domain.party.relations.roleTypeAttr.event.RoleTypeAttrUpdated;
import com.skytala.eCommerce.domain.party.relations.roleTypeAttr.mapper.RoleTypeAttrMapper;
import com.skytala.eCommerce.domain.party.relations.roleTypeAttr.model.RoleTypeAttr;
import com.skytala.eCommerce.domain.party.relations.roleTypeAttr.query.FindRoleTypeAttrsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/roleTypeAttrs")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findRoleTypeAttrsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindRoleTypeAttrsBy query = new FindRoleTypeAttrsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<RoleTypeAttr> roleTypeAttrs =((RoleTypeAttrFound) Scheduler.execute(query).data()).getRoleTypeAttrs();

		if (roleTypeAttrs.size() == 1) {
			return ResponseEntity.ok().body(roleTypeAttrs.get(0));
		}

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
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createRoleTypeAttr(HttpServletRequest request) throws Exception {

		RoleTypeAttr roleTypeAttrToBeAdded = new RoleTypeAttr();
		try {
			roleTypeAttrToBeAdded = RoleTypeAttrMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
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
	public ResponseEntity<Object> createRoleTypeAttr(@RequestBody RoleTypeAttr roleTypeAttrToBeAdded) throws Exception {

		AddRoleTypeAttr command = new AddRoleTypeAttr(roleTypeAttrToBeAdded);
		RoleTypeAttr roleTypeAttr = ((RoleTypeAttrAdded) Scheduler.execute(command).data()).getAddedRoleTypeAttr();
		
		if (roleTypeAttr != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(roleTypeAttr);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("RoleTypeAttr could not be created.");
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
	public boolean updateRoleTypeAttr(HttpServletRequest request) throws Exception {

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

		RoleTypeAttr roleTypeAttrToBeUpdated = new RoleTypeAttr();

		try {
			roleTypeAttrToBeUpdated = RoleTypeAttrMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateRoleTypeAttr(roleTypeAttrToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the RoleTypeAttr with the specific Id
	 * 
	 * @param roleTypeAttrToBeUpdated
	 *            the RoleTypeAttr thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateRoleTypeAttr(@RequestBody RoleTypeAttr roleTypeAttrToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		roleTypeAttrToBeUpdated.setnull(null);

		UpdateRoleTypeAttr command = new UpdateRoleTypeAttr(roleTypeAttrToBeUpdated);

		try {
			if(((RoleTypeAttrUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{roleTypeAttrId}")
	public ResponseEntity<Object> findById(@PathVariable String roleTypeAttrId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("roleTypeAttrId", roleTypeAttrId);
		try {

			Object foundRoleTypeAttr = findRoleTypeAttrsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundRoleTypeAttr);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{roleTypeAttrId}")
	public ResponseEntity<Object> deleteRoleTypeAttrByIdUpdated(@PathVariable String roleTypeAttrId) throws Exception {
		DeleteRoleTypeAttr command = new DeleteRoleTypeAttr(roleTypeAttrId);

		try {
			if (((RoleTypeAttrDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("RoleTypeAttr could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/roleTypeAttr/\" plus one of the following: "
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
