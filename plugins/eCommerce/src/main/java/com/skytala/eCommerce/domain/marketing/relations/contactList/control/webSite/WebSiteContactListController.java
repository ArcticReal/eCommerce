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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	public ResponseEntity<List<WebSiteContactList>> findWebSiteContactListsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindWebSiteContactListsBy query = new FindWebSiteContactListsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<WebSiteContactList> webSiteContactLists =((WebSiteContactListFound) Scheduler.execute(query).data()).getWebSiteContactLists();

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
	public ResponseEntity<WebSiteContactList> createWebSiteContactList(HttpServletRequest request) throws Exception {

		WebSiteContactList webSiteContactListToBeAdded = new WebSiteContactList();
		try {
			webSiteContactListToBeAdded = WebSiteContactListMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
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
	public ResponseEntity<WebSiteContactList> createWebSiteContactList(@RequestBody WebSiteContactList webSiteContactListToBeAdded) throws Exception {

		AddWebSiteContactList command = new AddWebSiteContactList(webSiteContactListToBeAdded);
		WebSiteContactList webSiteContactList = ((WebSiteContactListAdded) Scheduler.execute(command).data()).getAddedWebSiteContactList();
		
		if (webSiteContactList != null) 
			return successful(webSiteContactList);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateWebSiteContactList(@RequestBody WebSiteContactList webSiteContactListToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		webSiteContactListToBeUpdated.setnull(null);

		UpdateWebSiteContactList command = new UpdateWebSiteContactList(webSiteContactListToBeUpdated);

		try {
			if(((WebSiteContactListUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{webSiteContactListId}")
	public ResponseEntity<WebSiteContactList> findById(@PathVariable String webSiteContactListId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("webSiteContactListId", webSiteContactListId);
		try {

			List<WebSiteContactList> foundWebSiteContactList = findWebSiteContactListsBy(requestParams).getBody();
			if(foundWebSiteContactList.size()==1){				return successful(foundWebSiteContactList.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{webSiteContactListId}")
	public ResponseEntity<String> deleteWebSiteContactListByIdUpdated(@PathVariable String webSiteContactListId) throws Exception {
		DeleteWebSiteContactList command = new DeleteWebSiteContactList(webSiteContactListId);

		try {
			if (((WebSiteContactListDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
