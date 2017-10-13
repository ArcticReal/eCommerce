package com.skytala.eCommerce.domain.content.relations.contentRole;

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
import com.skytala.eCommerce.domain.content.relations.contentRole.command.AddContentRole;
import com.skytala.eCommerce.domain.content.relations.contentRole.command.DeleteContentRole;
import com.skytala.eCommerce.domain.content.relations.contentRole.command.UpdateContentRole;
import com.skytala.eCommerce.domain.content.relations.contentRole.event.ContentRoleAdded;
import com.skytala.eCommerce.domain.content.relations.contentRole.event.ContentRoleDeleted;
import com.skytala.eCommerce.domain.content.relations.contentRole.event.ContentRoleFound;
import com.skytala.eCommerce.domain.content.relations.contentRole.event.ContentRoleUpdated;
import com.skytala.eCommerce.domain.content.relations.contentRole.mapper.ContentRoleMapper;
import com.skytala.eCommerce.domain.content.relations.contentRole.model.ContentRole;
import com.skytala.eCommerce.domain.content.relations.contentRole.query.FindContentRolesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/contentRoles")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
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
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
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
	@RequestMapping(method = RequestMethod.PUT, value = "/update", consumes = "application/x-www-form-urlencoded")
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

	@RequestMapping(method = RequestMethod.GET, value = "/{contentRoleId}")
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

	@RequestMapping(method = RequestMethod.DELETE, value = "/{contentRoleId}")
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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/contentRole/\" plus one of the following: "
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