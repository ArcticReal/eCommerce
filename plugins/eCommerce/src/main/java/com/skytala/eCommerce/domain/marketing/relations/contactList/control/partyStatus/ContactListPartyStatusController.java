package com.skytala.eCommerce.domain.marketing.relations.contactList.control.partyStatus;

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
import com.skytala.eCommerce.domain.marketing.relations.contactList.command.partyStatus.AddContactListPartyStatus;
import com.skytala.eCommerce.domain.marketing.relations.contactList.command.partyStatus.DeleteContactListPartyStatus;
import com.skytala.eCommerce.domain.marketing.relations.contactList.command.partyStatus.UpdateContactListPartyStatus;
import com.skytala.eCommerce.domain.marketing.relations.contactList.event.partyStatus.ContactListPartyStatusAdded;
import com.skytala.eCommerce.domain.marketing.relations.contactList.event.partyStatus.ContactListPartyStatusDeleted;
import com.skytala.eCommerce.domain.marketing.relations.contactList.event.partyStatus.ContactListPartyStatusFound;
import com.skytala.eCommerce.domain.marketing.relations.contactList.event.partyStatus.ContactListPartyStatusUpdated;
import com.skytala.eCommerce.domain.marketing.relations.contactList.mapper.partyStatus.ContactListPartyStatusMapper;
import com.skytala.eCommerce.domain.marketing.relations.contactList.model.partyStatus.ContactListPartyStatus;
import com.skytala.eCommerce.domain.marketing.relations.contactList.query.partyStatus.FindContactListPartyStatussBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/marketing/contactList/contactListPartyStatuss")
public class ContactListPartyStatusController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ContactListPartyStatusController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ContactListPartyStatus
	 * @return a List with the ContactListPartyStatuss
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ContactListPartyStatus>> findContactListPartyStatussBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindContactListPartyStatussBy query = new FindContactListPartyStatussBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ContactListPartyStatus> contactListPartyStatuss =((ContactListPartyStatusFound) Scheduler.execute(query).data()).getContactListPartyStatuss();

		return ResponseEntity.ok().body(contactListPartyStatuss);

	}

	/**
	 * creates a new ContactListPartyStatus entry in the ofbiz database
	 * 
	 * @param contactListPartyStatusToBeAdded
	 *            the ContactListPartyStatus thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ContactListPartyStatus> createContactListPartyStatus(@RequestBody ContactListPartyStatus contactListPartyStatusToBeAdded) throws Exception {

		AddContactListPartyStatus command = new AddContactListPartyStatus(contactListPartyStatusToBeAdded);
		ContactListPartyStatus contactListPartyStatus = ((ContactListPartyStatusAdded) Scheduler.execute(command).data()).getAddedContactListPartyStatus();
		
		if (contactListPartyStatus != null) 
			return successful(contactListPartyStatus);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ContactListPartyStatus with the specific Id
	 * 
	 * @param contactListPartyStatusToBeUpdated
	 *            the ContactListPartyStatus thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{partyId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateContactListPartyStatus(@RequestBody ContactListPartyStatus contactListPartyStatusToBeUpdated,
			@PathVariable String partyId) throws Exception {

		contactListPartyStatusToBeUpdated.setPartyId(partyId);

		UpdateContactListPartyStatus command = new UpdateContactListPartyStatus(contactListPartyStatusToBeUpdated);

		try {
			if(((ContactListPartyStatusUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{contactListPartyStatusId}")
	public ResponseEntity<ContactListPartyStatus> findById(@PathVariable String contactListPartyStatusId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("contactListPartyStatusId", contactListPartyStatusId);
		try {

			List<ContactListPartyStatus> foundContactListPartyStatus = findContactListPartyStatussBy(requestParams).getBody();
			if(foundContactListPartyStatus.size()==1){				return successful(foundContactListPartyStatus.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{contactListPartyStatusId}")
	public ResponseEntity<String> deleteContactListPartyStatusByIdUpdated(@PathVariable String contactListPartyStatusId) throws Exception {
		DeleteContactListPartyStatus command = new DeleteContactListPartyStatus(contactListPartyStatusId);

		try {
			if (((ContactListPartyStatusDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
