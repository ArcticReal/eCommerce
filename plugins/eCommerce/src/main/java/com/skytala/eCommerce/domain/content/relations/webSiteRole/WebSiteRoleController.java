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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/content/webSiteRoles")
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
	@GetMapping("/find")
	public ResponseEntity<List<WebSiteRole>> findWebSiteRolesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindWebSiteRolesBy query = new FindWebSiteRolesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<WebSiteRole> webSiteRoles =((WebSiteRoleFound) Scheduler.execute(query).data()).getWebSiteRoles();

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
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<WebSiteRole> createWebSiteRole(HttpServletRequest request) throws Exception {

		WebSiteRole webSiteRoleToBeAdded = new WebSiteRole();
		try {
			webSiteRoleToBeAdded = WebSiteRoleMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
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
	public ResponseEntity<WebSiteRole> createWebSiteRole(@RequestBody WebSiteRole webSiteRoleToBeAdded) throws Exception {

		AddWebSiteRole command = new AddWebSiteRole(webSiteRoleToBeAdded);
		WebSiteRole webSiteRole = ((WebSiteRoleAdded) Scheduler.execute(command).data()).getAddedWebSiteRole();
		
		if (webSiteRole != null) 
			return successful(webSiteRole);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateWebSiteRole(@RequestBody WebSiteRole webSiteRoleToBeUpdated,
			@PathVariable String roleTypeId) throws Exception {

		webSiteRoleToBeUpdated.setRoleTypeId(roleTypeId);

		UpdateWebSiteRole command = new UpdateWebSiteRole(webSiteRoleToBeUpdated);

		try {
			if(((WebSiteRoleUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{webSiteRoleId}")
	public ResponseEntity<WebSiteRole> findById(@PathVariable String webSiteRoleId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("webSiteRoleId", webSiteRoleId);
		try {

			List<WebSiteRole> foundWebSiteRole = findWebSiteRolesBy(requestParams).getBody();
			if(foundWebSiteRole.size()==1){				return successful(foundWebSiteRole.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{webSiteRoleId}")
	public ResponseEntity<String> deleteWebSiteRoleByIdUpdated(@PathVariable String webSiteRoleId) throws Exception {
		DeleteWebSiteRole command = new DeleteWebSiteRole(webSiteRoleId);

		try {
			if (((WebSiteRoleDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
