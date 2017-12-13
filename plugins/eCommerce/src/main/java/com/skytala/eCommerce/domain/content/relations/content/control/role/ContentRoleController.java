package com.skytala.eCommerce.domain.content.relations.content.control.role;

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
import com.skytala.eCommerce.domain.content.relations.content.command.role.AddContentRole;
import com.skytala.eCommerce.domain.content.relations.content.command.role.DeleteContentRole;
import com.skytala.eCommerce.domain.content.relations.content.command.role.UpdateContentRole;
import com.skytala.eCommerce.domain.content.relations.content.event.role.ContentRoleAdded;
import com.skytala.eCommerce.domain.content.relations.content.event.role.ContentRoleDeleted;
import com.skytala.eCommerce.domain.content.relations.content.event.role.ContentRoleFound;
import com.skytala.eCommerce.domain.content.relations.content.event.role.ContentRoleUpdated;
import com.skytala.eCommerce.domain.content.relations.content.mapper.role.ContentRoleMapper;
import com.skytala.eCommerce.domain.content.relations.content.model.role.ContentRole;
import com.skytala.eCommerce.domain.content.relations.content.query.role.FindContentRolesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/content/content/contentRoles")
public class ContentRoleController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ContentRoleController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ContentRole
	 * @return a List with the ContentRoles
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findContentRolesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindContentRolesBy query = new FindContentRolesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ContentRole> contentRoles =((ContentRoleFound) Scheduler.execute(query).data()).getContentRoles();

		if (contentRoles.size() == 1) {
			return ResponseEntity.ok().body(contentRoles.get(0));
		}

		return ResponseEntity.ok().body(contentRoles);

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
	public ResponseEntity<Object> createContentRole(HttpServletRequest request) throws Exception {

		ContentRole contentRoleToBeAdded = new ContentRole();
		try {
			contentRoleToBeAdded = ContentRoleMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createContentRole(contentRoleToBeAdded);

	}

	/**
	 * creates a new ContentRole entry in the ofbiz database
	 * 
	 * @param contentRoleToBeAdded
	 *            the ContentRole thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createContentRole(@RequestBody ContentRole contentRoleToBeAdded) throws Exception {

		AddContentRole command = new AddContentRole(contentRoleToBeAdded);
		ContentRole contentRole = ((ContentRoleAdded) Scheduler.execute(command).data()).getAddedContentRole();
		
		if (contentRole != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(contentRole);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ContentRole could not be created.");
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
	public boolean updateContentRole(HttpServletRequest request) throws Exception {

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

		ContentRole contentRoleToBeUpdated = new ContentRole();

		try {
			contentRoleToBeUpdated = ContentRoleMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateContentRole(contentRoleToBeUpdated, contentRoleToBeUpdated.getRoleTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ContentRole with the specific Id
	 * 
	 * @param contentRoleToBeUpdated
	 *            the ContentRole thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{roleTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateContentRole(@RequestBody ContentRole contentRoleToBeUpdated,
			@PathVariable String roleTypeId) throws Exception {

		contentRoleToBeUpdated.setRoleTypeId(roleTypeId);

		UpdateContentRole command = new UpdateContentRole(contentRoleToBeUpdated);

		try {
			if(((ContentRoleUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{contentRoleId}")
	public ResponseEntity<Object> findById(@PathVariable String contentRoleId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("contentRoleId", contentRoleId);
		try {

			Object foundContentRole = findContentRolesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundContentRole);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{contentRoleId}")
	public ResponseEntity<Object> deleteContentRoleByIdUpdated(@PathVariable String contentRoleId) throws Exception {
		DeleteContentRole command = new DeleteContentRole(contentRoleId);

		try {
			if (((ContentRoleDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ContentRole could not be deleted");

	}

}
