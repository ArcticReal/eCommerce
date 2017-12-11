package com.skytala.eCommerce.domain.marketing.relations.contactList.control.webSite;

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
import com.skytala.eCommerce.domain.marketing.relations.contactList.command.webSite.AddWebSiteContactList;
import com.skytala.eCommerce.domain.marketing.relations.contactList.command.webSite.DeleteWebSiteContactList;
import com.skytala.eCommerce.domain.marketing.relations.contactList.command.webSite.UpdateWebSiteContactList;
import com.skytala.eCommerce.domain.marketing.relations.contactList.event.webSite.WebSiteContactListAdded;
import com.skytala.eCommerce.domain.marketing.relations.contactList.event.webSite.WebSiteContactListDeleted;
import com.skytala.eCommerce.domain.marketing.relations.contactList.event.webSite.WebSiteContactListFound;
import com.skytala.eCommerce.domain.marketing.relations.contactList.event.webSite.WebSiteContactListUpdated;
import com.skytala.eCommerce.domain.marketing.relations.contactList.mapper.webSite.WebSiteContactListMapper;
import com.skytala.eCommerce.domain.marketing.relations.contactList.model.webSite.WebSiteContactList;
import com.skytala.eCommerce.domain.marketing.relations.contactList.query.webSite.FindWebSiteContactListsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/marketing/contactList/webSiteContactLists")
public class WebSiteContactListController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public WebSiteContactListController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a WebSiteContactList
	 * @return a List with the WebSiteContactLists
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findWebSiteContactListsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindWebSiteContactListsBy query = new FindWebSiteContactListsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<WebSiteContactList> webSiteContactLists =((WebSiteContactListFound) Scheduler.execute(query).data()).getWebSiteContactLists();

		if (webSiteContactLists.size() == 1) {
			return ResponseEntity.ok().body(webSiteContactLists.get(0));
		}

		return ResponseEntity.ok().body(webSiteContactLists);

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
	public ResponseEntity<Object> createWebSiteContactList(HttpServletRequest request) throws Exception {

		WebSiteContactList webSiteContactListToBeAdded = new WebSiteContactList();
		try {
			webSiteContactListToBeAdded = WebSiteContactListMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createWebSiteContactList(webSiteContactListToBeAdded);

	}

	/**
	 * creates a new WebSiteContactList entry in the ofbiz database
	 * 
	 * @param webSiteContactListToBeAdded
	 *            the WebSiteContactList thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createWebSiteContactList(@RequestBody WebSiteContactList webSiteContactListToBeAdded) throws Exception {

		AddWebSiteContactList command = new AddWebSiteContactList(webSiteContactListToBeAdded);
		WebSiteContactList webSiteContactList = ((WebSiteContactListAdded) Scheduler.execute(command).data()).getAddedWebSiteContactList();
		
		if (webSiteContactList != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(webSiteContactList);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("WebSiteContactList could not be created.");
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
	public boolean updateWebSiteContactList(HttpServletRequest request) throws Exception {

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

		WebSiteContactList webSiteContactListToBeUpdated = new WebSiteContactList();

		try {
			webSiteContactListToBeUpdated = WebSiteContactListMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateWebSiteContactList(webSiteContactListToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the WebSiteContactList with the specific Id
	 * 
	 * @param webSiteContactListToBeUpdated
	 *            the WebSiteContactList thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateWebSiteContactList(@RequestBody WebSiteContactList webSiteContactListToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		webSiteContactListToBeUpdated.setnull(null);

		UpdateWebSiteContactList command = new UpdateWebSiteContactList(webSiteContactListToBeUpdated);

		try {
			if(((WebSiteContactListUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{webSiteContactListId}")
	public ResponseEntity<Object> findById(@PathVariable String webSiteContactListId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("webSiteContactListId", webSiteContactListId);
		try {

			Object foundWebSiteContactList = findWebSiteContactListsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundWebSiteContactList);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{webSiteContactListId}")
	public ResponseEntity<Object> deleteWebSiteContactListByIdUpdated(@PathVariable String webSiteContactListId) throws Exception {
		DeleteWebSiteContactList command = new DeleteWebSiteContactList(webSiteContactListId);

		try {
			if (((WebSiteContactListDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("WebSiteContactList could not be deleted");

	}

}
