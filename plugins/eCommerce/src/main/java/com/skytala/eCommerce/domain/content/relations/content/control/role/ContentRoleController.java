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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	public ResponseEntity<List<ContentRole>> findContentRolesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindContentRolesBy query = new FindContentRolesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ContentRole> contentRoles =((ContentRoleFound) Scheduler.execute(query).data()).getContentRoles();

		return ResponseEntity.ok().body(contentRoles);

	}

	/**
	 * creates a new ContentRole entry in the ofbiz database
	 * 
	 * @param contentRoleToBeAdded
	 *            the ContentRole thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ContentRole> createContentRole(@RequestBody ContentRole contentRoleToBeAdded) throws Exception {

		AddContentRole command = new AddContentRole(contentRoleToBeAdded);
		ContentRole contentRole = ((ContentRoleAdded) Scheduler.execute(command).data()).getAddedContentRole();
		
		if (contentRole != null) 
			return successful(contentRole);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateContentRole(@RequestBody ContentRole contentRoleToBeUpdated,
			@PathVariable String roleTypeId) throws Exception {

		contentRoleToBeUpdated.setRoleTypeId(roleTypeId);

		UpdateContentRole command = new UpdateContentRole(contentRoleToBeUpdated);

		try {
			if(((ContentRoleUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{contentRoleId}")
	public ResponseEntity<ContentRole> findById(@PathVariable String contentRoleId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("contentRoleId", contentRoleId);
		try {

			List<ContentRole> foundContentRole = findContentRolesBy(requestParams).getBody();
			if(foundContentRole.size()==1){				return successful(foundContentRole.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{contentRoleId}")
	public ResponseEntity<String> deleteContentRoleByIdUpdated(@PathVariable String contentRoleId) throws Exception {
		DeleteContentRole command = new DeleteContentRole(contentRoleId);

		try {
			if (((ContentRoleDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
