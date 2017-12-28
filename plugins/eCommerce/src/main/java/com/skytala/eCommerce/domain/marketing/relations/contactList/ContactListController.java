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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	@GetMapping("/find")
	public ResponseEntity<List<ContactList>> findContactListsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindContactListsBy query = new FindContactListsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ContactList> contactLists =((ContactListFound) Scheduler.execute(query).data()).getContactLists();

		return ResponseEntity.ok().body(contactLists);

	}

	/**
	 * creates a new ContactList entry in the ofbiz database
	 * 
	 * @param contactListToBeAdded
	 *            the ContactList thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ContactList> createContactList(@RequestBody ContactList contactListToBeAdded) throws Exception {

		AddContactList command = new AddContactList(contactListToBeAdded);
		ContactList contactList = ((ContactListAdded) Scheduler.execute(command).data()).getAddedContactList();
		
		if (contactList != null) 
			return successful(contactList);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateContactList(@RequestBody ContactList contactListToBeUpdated,
			@PathVariable String contactListId) throws Exception {

		contactListToBeUpdated.setContactListId(contactListId);

		UpdateContactList command = new UpdateContactList(contactListToBeUpdated);

		try {
			if(((ContactListUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{contactListId}")
	public ResponseEntity<ContactList> findById(@PathVariable String contactListId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("contactListId", contactListId);
		try {

			List<ContactList> foundContactList = findContactListsBy(requestParams).getBody();
			if(foundContactList.size()==1){				return successful(foundContactList.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{contactListId}")
	public ResponseEntity<String> deleteContactListByIdUpdated(@PathVariable String contactListId) throws Exception {
		DeleteContactList command = new DeleteContactList(contactListId);

		try {
			if (((ContactListDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
