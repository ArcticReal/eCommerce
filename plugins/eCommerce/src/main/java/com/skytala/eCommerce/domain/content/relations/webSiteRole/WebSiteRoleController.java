package com.skytala.eCommerce.domain.content.relations.webSiteRole;

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
import com.skytala.eCommerce.domain.content.relations.webSiteRole.command.AddWebSiteRole;
import com.skytala.eCommerce.domain.content.relations.webSiteRole.command.DeleteWebSiteRole;
import com.skytala.eCommerce.domain.content.relations.webSiteRole.command.UpdateWebSiteRole;
import com.skytala.eCommerce.domain.content.relations.webSiteRole.event.WebSiteRoleAdded;
import com.skytala.eCommerce.domain.content.relations.webSiteRole.event.WebSiteRoleDeleted;
import com.skytala.eCommerce.domain.content.relations.webSiteRole.event.WebSiteRoleFound;
import com.skytala.eCommerce.domain.content.relations.webSiteRole.event.WebSiteRoleUpdated;
import com.skytala.eCommerce.domain.content.relations.webSiteRole.mapper.WebSiteRoleMapper;
import com.skytala.eCommerce.domain.content.relations.webSiteRole.model.WebSiteRole;
import com.skytala.eCommerce.domain.content.relations.webSiteRole.query.FindWebSiteRolesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/webSiteRoles")
public class WebSiteRoleController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public WebSiteRoleController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a WebSiteRole
	 * @return a List with the WebSiteRoles
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findWebSiteRolesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindWebSiteRolesBy query = new FindWebSiteRolesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<WebSiteRole> webSiteRoles =((WebSiteRoleFound) Scheduler.execute(query).data()).getWebSiteRoles();

		if (webSiteRoles.size() == 1) {
			return ResponseEntity.ok().body(webSiteRoles.get(0));
		}

		return ResponseEntity.ok().body(webSiteRoles);

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
	public ResponseEntity<Object> createWebSiteRole(HttpServletRequest request) throws Exception {

		WebSiteRole webSiteRoleToBeAdded = new WebSiteRole();
		try {
			webSiteRoleToBeAdded = WebSiteRoleMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createWebSiteRole(webSiteRoleToBeAdded);

	}

	/**
	 * creates a new WebSiteRole entry in the ofbiz database
	 * 
	 * @param webSiteRoleToBeAdded
	 *            the WebSiteRole thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createWebSiteRole(@RequestBody WebSiteRole webSiteRoleToBeAdded) throws Exception {

		AddWebSiteRole command = new AddWebSiteRole(webSiteRoleToBeAdded);
		WebSiteRole webSiteRole = ((WebSiteRoleAdded) Scheduler.execute(command).data()).getAddedWebSiteRole();
		
		if (webSiteRole != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(webSiteRole);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("WebSiteRole could not be created.");
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
	public boolean updateWebSiteRole(HttpServletRequest request) throws Exception {

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

		WebSiteRole webSiteRoleToBeUpdated = new WebSiteRole();

		try {
			webSiteRoleToBeUpdated = WebSiteRoleMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateWebSiteRole(webSiteRoleToBeUpdated, webSiteRoleToBeUpdated.getRoleTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the WebSiteRole with the specific Id
	 * 
	 * @param webSiteRoleToBeUpdated
	 *            the WebSiteRole thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{roleTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateWebSiteRole(@RequestBody WebSiteRole webSiteRoleToBeUpdated,
			@PathVariable String roleTypeId) throws Exception {

		webSiteRoleToBeUpdated.setRoleTypeId(roleTypeId);

		UpdateWebSiteRole command = new UpdateWebSiteRole(webSiteRoleToBeUpdated);

		try {
			if(((WebSiteRoleUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{webSiteRoleId}")
	public ResponseEntity<Object> findById(@PathVariable String webSiteRoleId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("webSiteRoleId", webSiteRoleId);
		try {

			Object foundWebSiteRole = findWebSiteRolesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundWebSiteRole);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{webSiteRoleId}")
	public ResponseEntity<Object> deleteWebSiteRoleByIdUpdated(@PathVariable String webSiteRoleId) throws Exception {
		DeleteWebSiteRole command = new DeleteWebSiteRole(webSiteRoleId);

		try {
			if (((WebSiteRoleDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("WebSiteRole could not be deleted");

	}

}
