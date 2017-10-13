package com.skytala.eCommerce.domain.accounting.relations.finAccountRole;

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
import com.skytala.eCommerce.domain.accounting.relations.finAccountRole.command.AddFinAccountRole;
import com.skytala.eCommerce.domain.accounting.relations.finAccountRole.command.DeleteFinAccountRole;
import com.skytala.eCommerce.domain.accounting.relations.finAccountRole.command.UpdateFinAccountRole;
import com.skytala.eCommerce.domain.accounting.relations.finAccountRole.event.FinAccountRoleAdded;
import com.skytala.eCommerce.domain.accounting.relations.finAccountRole.event.FinAccountRoleDeleted;
import com.skytala.eCommerce.domain.accounting.relations.finAccountRole.event.FinAccountRoleFound;
import com.skytala.eCommerce.domain.accounting.relations.finAccountRole.event.FinAccountRoleUpdated;
import com.skytala.eCommerce.domain.accounting.relations.finAccountRole.mapper.FinAccountRoleMapper;
import com.skytala.eCommerce.domain.accounting.relations.finAccountRole.model.FinAccountRole;
import com.skytala.eCommerce.domain.accounting.relations.finAccountRole.query.FindFinAccountRolesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/finAccountRoles")
public class FinAccountRoleController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public FinAccountRoleController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a FinAccountRole
	 * @return a List with the FinAccountRoles
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findFinAccountRolesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindFinAccountRolesBy query = new FindFinAccountRolesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<FinAccountRole> finAccountRoles =((FinAccountRoleFound) Scheduler.execute(query).data()).getFinAccountRoles();

		if (finAccountRoles.size() == 1) {
			return ResponseEntity.ok().body(finAccountRoles.get(0));
		}

		return ResponseEntity.ok().body(finAccountRoles);

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
	public ResponseEntity<Object> createFinAccountRole(HttpServletRequest request) throws Exception {

		FinAccountRole finAccountRoleToBeAdded = new FinAccountRole();
		try {
			finAccountRoleToBeAdded = FinAccountRoleMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createFinAccountRole(finAccountRoleToBeAdded);

	}

	/**
	 * creates a new FinAccountRole entry in the ofbiz database
	 * 
	 * @param finAccountRoleToBeAdded
	 *            the FinAccountRole thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createFinAccountRole(@RequestBody FinAccountRole finAccountRoleToBeAdded) throws Exception {

		AddFinAccountRole command = new AddFinAccountRole(finAccountRoleToBeAdded);
		FinAccountRole finAccountRole = ((FinAccountRoleAdded) Scheduler.execute(command).data()).getAddedFinAccountRole();
		
		if (finAccountRole != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(finAccountRole);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("FinAccountRole could not be created.");
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
	public boolean updateFinAccountRole(HttpServletRequest request) throws Exception {

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

		FinAccountRole finAccountRoleToBeUpdated = new FinAccountRole();

		try {
			finAccountRoleToBeUpdated = FinAccountRoleMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateFinAccountRole(finAccountRoleToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the FinAccountRole with the specific Id
	 * 
	 * @param finAccountRoleToBeUpdated
	 *            the FinAccountRole thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateFinAccountRole(@RequestBody FinAccountRole finAccountRoleToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		finAccountRoleToBeUpdated.setnull(null);

		UpdateFinAccountRole command = new UpdateFinAccountRole(finAccountRoleToBeUpdated);

		try {
			if(((FinAccountRoleUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{finAccountRoleId}")
	public ResponseEntity<Object> findById(@PathVariable String finAccountRoleId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("finAccountRoleId", finAccountRoleId);
		try {

			Object foundFinAccountRole = findFinAccountRolesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundFinAccountRole);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{finAccountRoleId}")
	public ResponseEntity<Object> deleteFinAccountRoleByIdUpdated(@PathVariable String finAccountRoleId) throws Exception {
		DeleteFinAccountRole command = new DeleteFinAccountRole(finAccountRoleId);

		try {
			if (((FinAccountRoleDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("FinAccountRole could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/finAccountRole/\" plus one of the following: "
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