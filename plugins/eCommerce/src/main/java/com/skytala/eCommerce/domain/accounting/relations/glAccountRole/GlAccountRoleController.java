package com.skytala.eCommerce.domain.accounting.relations.glAccountRole;

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
import com.skytala.eCommerce.domain.accounting.relations.glAccountRole.command.AddGlAccountRole;
import com.skytala.eCommerce.domain.accounting.relations.glAccountRole.command.DeleteGlAccountRole;
import com.skytala.eCommerce.domain.accounting.relations.glAccountRole.command.UpdateGlAccountRole;
import com.skytala.eCommerce.domain.accounting.relations.glAccountRole.event.GlAccountRoleAdded;
import com.skytala.eCommerce.domain.accounting.relations.glAccountRole.event.GlAccountRoleDeleted;
import com.skytala.eCommerce.domain.accounting.relations.glAccountRole.event.GlAccountRoleFound;
import com.skytala.eCommerce.domain.accounting.relations.glAccountRole.event.GlAccountRoleUpdated;
import com.skytala.eCommerce.domain.accounting.relations.glAccountRole.mapper.GlAccountRoleMapper;
import com.skytala.eCommerce.domain.accounting.relations.glAccountRole.model.GlAccountRole;
import com.skytala.eCommerce.domain.accounting.relations.glAccountRole.query.FindGlAccountRolesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/glAccountRoles")
public class GlAccountRoleController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public GlAccountRoleController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a GlAccountRole
	 * @return a List with the GlAccountRoles
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findGlAccountRolesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindGlAccountRolesBy query = new FindGlAccountRolesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<GlAccountRole> glAccountRoles =((GlAccountRoleFound) Scheduler.execute(query).data()).getGlAccountRoles();

		if (glAccountRoles.size() == 1) {
			return ResponseEntity.ok().body(glAccountRoles.get(0));
		}

		return ResponseEntity.ok().body(glAccountRoles);

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
	public ResponseEntity<Object> createGlAccountRole(HttpServletRequest request) throws Exception {

		GlAccountRole glAccountRoleToBeAdded = new GlAccountRole();
		try {
			glAccountRoleToBeAdded = GlAccountRoleMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createGlAccountRole(glAccountRoleToBeAdded);

	}

	/**
	 * creates a new GlAccountRole entry in the ofbiz database
	 * 
	 * @param glAccountRoleToBeAdded
	 *            the GlAccountRole thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createGlAccountRole(@RequestBody GlAccountRole glAccountRoleToBeAdded) throws Exception {

		AddGlAccountRole command = new AddGlAccountRole(glAccountRoleToBeAdded);
		GlAccountRole glAccountRole = ((GlAccountRoleAdded) Scheduler.execute(command).data()).getAddedGlAccountRole();
		
		if (glAccountRole != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(glAccountRole);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("GlAccountRole could not be created.");
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
	public boolean updateGlAccountRole(HttpServletRequest request) throws Exception {

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

		GlAccountRole glAccountRoleToBeUpdated = new GlAccountRole();

		try {
			glAccountRoleToBeUpdated = GlAccountRoleMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateGlAccountRole(glAccountRoleToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the GlAccountRole with the specific Id
	 * 
	 * @param glAccountRoleToBeUpdated
	 *            the GlAccountRole thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateGlAccountRole(@RequestBody GlAccountRole glAccountRoleToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		glAccountRoleToBeUpdated.setnull(null);

		UpdateGlAccountRole command = new UpdateGlAccountRole(glAccountRoleToBeUpdated);

		try {
			if(((GlAccountRoleUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{glAccountRoleId}")
	public ResponseEntity<Object> findById(@PathVariable String glAccountRoleId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("glAccountRoleId", glAccountRoleId);
		try {

			Object foundGlAccountRole = findGlAccountRolesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundGlAccountRole);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{glAccountRoleId}")
	public ResponseEntity<Object> deleteGlAccountRoleByIdUpdated(@PathVariable String glAccountRoleId) throws Exception {
		DeleteGlAccountRole command = new DeleteGlAccountRole(glAccountRoleId);

		try {
			if (((GlAccountRoleDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("GlAccountRole could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/glAccountRole/\" plus one of the following: "
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
