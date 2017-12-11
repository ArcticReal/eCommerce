package com.skytala.eCommerce.domain.marketing.relations.contactList;

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
import com.skytala.eCommerce.domain.marketing.relations.contactList.command.AddContactList;
import com.skytala.eCommerce.domain.marketing.relations.contactList.command.DeleteContactList;
import com.skytala.eCommerce.domain.marketing.relations.contactList.command.UpdateContactList;
import com.skytala.eCommerce.domain.marketing.relations.contactList.event.ContactListAdded;
import com.skytala.eCommerce.domain.marketing.relations.contactList.event.ContactListDeleted;
import com.skytala.eCommerce.domain.marketing.relations.contactList.event.ContactListFound;
import com.skytala.eCommerce.domain.marketing.relations.contactList.event.ContactListUpdated;
import com.skytala.eCommerce.domain.marketing.relations.contactList.mapper.ContactListMapper;
import com.skytala.eCommerce.domain.marketing.relations.contactList.model.ContactList;
import com.skytala.eCommerce.domain.marketing.relations.contactList.query.FindContactListsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/marketing/contactLists")
public class ContactListController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ContactListController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ContactList
	 * @return a List with the ContactLists
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findContactListsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindContactListsBy query = new FindContactListsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ContactList> contactLists =((ContactListFound) Scheduler.execute(query).data()).getContactLists();

		if (contactLists.size() == 1) {
			return ResponseEntity.ok().body(contactLists.get(0));
		}

		return ResponseEntity.ok().body(contactLists);

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
	public ResponseEntity<Object> createContactList(HttpServletRequest request) throws Exception {

		ContactList contactListToBeAdded = new ContactList();
		try {
			contactListToBeAdded = ContactListMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createContactList(contactListToBeAdded);

	}

	/**
	 * creates a new ContactList entry in the ofbiz database
	 * 
	 * @param contactListToBeAdded
	 *            the ContactList thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createContactList(@RequestBody ContactList contactListToBeAdded) throws Exception {

		AddContactList command = new AddContactList(contactListToBeAdded);
		ContactList contactList = ((ContactListAdded) Scheduler.execute(command).data()).getAddedContactList();
		
		if (contactList != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(contactList);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ContactList could not be created.");
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
	public boolean updateContactList(HttpServletRequest request) throws Exception {

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

		ContactList contactListToBeUpdated = new ContactList();

		try {
			contactListToBeUpdated = ContactListMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateContactList(contactListToBeUpdated, contactListToBeUpdated.getContactListId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ContactList with the specific Id
	 * 
	 * @param contactListToBeUpdated
	 *            the ContactList thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{contactListId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateContactList(@RequestBody ContactList contactListToBeUpdated,
			@PathVariable String contactListId) throws Exception {

		contactListToBeUpdated.setContactListId(contactListId);

		UpdateContactList command = new UpdateContactList(contactListToBeUpdated);

		try {
			if(((ContactListUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{contactListId}")
	public ResponseEntity<Object> findById(@PathVariable String contactListId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("contactListId", contactListId);
		try {

			Object foundContactList = findContactListsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundContactList);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{contactListId}")
	public ResponseEntity<Object> deleteContactListByIdUpdated(@PathVariable String contactListId) throws Exception {
		DeleteContactList command = new DeleteContactList(contactListId);

		try {
			if (((ContactListDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ContactList could not be deleted");

	}

}
