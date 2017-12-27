package com.skytala.eCommerce.domain.marketing.relations.contactList.control.party;

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
import com.skytala.eCommerce.domain.marketing.relations.contactList.command.party.AddContactListParty;
import com.skytala.eCommerce.domain.marketing.relations.contactList.command.party.DeleteContactListParty;
import com.skytala.eCommerce.domain.marketing.relations.contactList.command.party.UpdateContactListParty;
import com.skytala.eCommerce.domain.marketing.relations.contactList.event.party.ContactListPartyAdded;
import com.skytala.eCommerce.domain.marketing.relations.contactList.event.party.ContactListPartyDeleted;
import com.skytala.eCommerce.domain.marketing.relations.contactList.event.party.ContactListPartyFound;
import com.skytala.eCommerce.domain.marketing.relations.contactList.event.party.ContactListPartyUpdated;
import com.skytala.eCommerce.domain.marketing.relations.contactList.mapper.party.ContactListPartyMapper;
import com.skytala.eCommerce.domain.marketing.relations.contactList.model.party.ContactListParty;
import com.skytala.eCommerce.domain.marketing.relations.contactList.query.party.FindContactListPartysBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/marketing/contactList/contactListPartys")
public class ContactListPartyController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ContactListPartyController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ContactListParty
	 * @return a List with the ContactListPartys
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ContactListParty>> findContactListPartysBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindContactListPartysBy query = new FindContactListPartysBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ContactListParty> contactListPartys =((ContactListPartyFound) Scheduler.execute(query).data()).getContactListPartys();

		return ResponseEntity.ok().body(contactListPartys);

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
	public ResponseEntity<ContactListParty> createContactListParty(HttpServletRequest request) throws Exception {

		ContactListParty contactListPartyToBeAdded = new ContactListParty();
		try {
			contactListPartyToBeAdded = ContactListPartyMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createContactListParty(contactListPartyToBeAdded);

	}

	/**
	 * creates a new ContactListParty entry in the ofbiz database
	 * 
	 * @param contactListPartyToBeAdded
	 *            the ContactListParty thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ContactListParty> createContactListParty(@RequestBody ContactListParty contactListPartyToBeAdded) throws Exception {

		AddContactListParty command = new AddContactListParty(contactListPartyToBeAdded);
		ContactListParty contactListParty = ((ContactListPartyAdded) Scheduler.execute(command).data()).getAddedContactListParty();
		
		if (contactListParty != null) 
			return successful(contactListParty);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ContactListParty with the specific Id
	 * 
	 * @param contactListPartyToBeUpdated
	 *            the ContactListParty thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateContactListParty(@RequestBody ContactListParty contactListPartyToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		contactListPartyToBeUpdated.setnull(null);

		UpdateContactListParty command = new UpdateContactListParty(contactListPartyToBeUpdated);

		try {
			if(((ContactListPartyUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{contactListPartyId}")
	public ResponseEntity<ContactListParty> findById(@PathVariable String contactListPartyId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("contactListPartyId", contactListPartyId);
		try {

			List<ContactListParty> foundContactListParty = findContactListPartysBy(requestParams).getBody();
			if(foundContactListParty.size()==1){				return successful(foundContactListParty.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{contactListPartyId}")
	public ResponseEntity<String> deleteContactListPartyByIdUpdated(@PathVariable String contactListPartyId) throws Exception {
		DeleteContactListParty command = new DeleteContactListParty(contactListPartyId);

		try {
			if (((ContactListPartyDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
