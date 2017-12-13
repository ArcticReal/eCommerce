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
	public ResponseEntity<Object> findContactListPartysBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindContactListPartysBy query = new FindContactListPartysBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ContactListParty> contactListPartys =((ContactListPartyFound) Scheduler.execute(query).data()).getContactListPartys();

		if (contactListPartys.size() == 1) {
			return ResponseEntity.ok().body(contactListPartys.get(0));
		}

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
	public ResponseEntity<Object> createContactListParty(HttpServletRequest request) throws Exception {

		ContactListParty contactListPartyToBeAdded = new ContactListParty();
		try {
			contactListPartyToBeAdded = ContactListPartyMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
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
	public ResponseEntity<Object> createContactListParty(@RequestBody ContactListParty contactListPartyToBeAdded) throws Exception {

		AddContactListParty command = new AddContactListParty(contactListPartyToBeAdded);
		ContactListParty contactListParty = ((ContactListPartyAdded) Scheduler.execute(command).data()).getAddedContactListParty();
		
		if (contactListParty != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(contactListParty);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ContactListParty could not be created.");
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
	public boolean updateContactListParty(HttpServletRequest request) throws Exception {

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

		ContactListParty contactListPartyToBeUpdated = new ContactListParty();

		try {
			contactListPartyToBeUpdated = ContactListPartyMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateContactListParty(contactListPartyToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateContactListParty(@RequestBody ContactListParty contactListPartyToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		contactListPartyToBeUpdated.setnull(null);

		UpdateContactListParty command = new UpdateContactListParty(contactListPartyToBeUpdated);

		try {
			if(((ContactListPartyUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{contactListPartyId}")
	public ResponseEntity<Object> findById(@PathVariable String contactListPartyId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("contactListPartyId", contactListPartyId);
		try {

			Object foundContactListParty = findContactListPartysBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundContactListParty);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{contactListPartyId}")
	public ResponseEntity<Object> deleteContactListPartyByIdUpdated(@PathVariable String contactListPartyId) throws Exception {
		DeleteContactListParty command = new DeleteContactListParty(contactListPartyId);

		try {
			if (((ContactListPartyDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ContactListParty could not be deleted");

	}

}
