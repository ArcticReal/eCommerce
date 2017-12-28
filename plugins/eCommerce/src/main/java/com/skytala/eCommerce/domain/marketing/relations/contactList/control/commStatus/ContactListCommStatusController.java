package com.skytala.eCommerce.domain.marketing.relations.contactList.control.commStatus;

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
import com.skytala.eCommerce.domain.marketing.relations.contactList.command.commStatus.AddContactListCommStatus;
import com.skytala.eCommerce.domain.marketing.relations.contactList.command.commStatus.DeleteContactListCommStatus;
import com.skytala.eCommerce.domain.marketing.relations.contactList.command.commStatus.UpdateContactListCommStatus;
import com.skytala.eCommerce.domain.marketing.relations.contactList.event.commStatus.ContactListCommStatusAdded;
import com.skytala.eCommerce.domain.marketing.relations.contactList.event.commStatus.ContactListCommStatusDeleted;
import com.skytala.eCommerce.domain.marketing.relations.contactList.event.commStatus.ContactListCommStatusFound;
import com.skytala.eCommerce.domain.marketing.relations.contactList.event.commStatus.ContactListCommStatusUpdated;
import com.skytala.eCommerce.domain.marketing.relations.contactList.mapper.commStatus.ContactListCommStatusMapper;
import com.skytala.eCommerce.domain.marketing.relations.contactList.model.commStatus.ContactListCommStatus;
import com.skytala.eCommerce.domain.marketing.relations.contactList.query.commStatus.FindContactListCommStatussBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/marketing/contactList/contactListCommStatuss")
public class ContactListCommStatusController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ContactListCommStatusController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ContactListCommStatus
	 * @return a List with the ContactListCommStatuss
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ContactListCommStatus>> findContactListCommStatussBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindContactListCommStatussBy query = new FindContactListCommStatussBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ContactListCommStatus> contactListCommStatuss =((ContactListCommStatusFound) Scheduler.execute(query).data()).getContactListCommStatuss();

		return ResponseEntity.ok().body(contactListCommStatuss);

	}

	/**
	 * creates a new ContactListCommStatus entry in the ofbiz database
	 * 
	 * @param contactListCommStatusToBeAdded
	 *            the ContactListCommStatus thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ContactListCommStatus> createContactListCommStatus(@RequestBody ContactListCommStatus contactListCommStatusToBeAdded) throws Exception {

		AddContactListCommStatus command = new AddContactListCommStatus(contactListCommStatusToBeAdded);
		ContactListCommStatus contactListCommStatus = ((ContactListCommStatusAdded) Scheduler.execute(command).data()).getAddedContactListCommStatus();
		
		if (contactListCommStatus != null) 
			return successful(contactListCommStatus);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ContactListCommStatus with the specific Id
	 * 
	 * @param contactListCommStatusToBeUpdated
	 *            the ContactListCommStatus thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateContactListCommStatus(@RequestBody ContactListCommStatus contactListCommStatusToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		contactListCommStatusToBeUpdated.setnull(null);

		UpdateContactListCommStatus command = new UpdateContactListCommStatus(contactListCommStatusToBeUpdated);

		try {
			if(((ContactListCommStatusUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{contactListCommStatusId}")
	public ResponseEntity<ContactListCommStatus> findById(@PathVariable String contactListCommStatusId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("contactListCommStatusId", contactListCommStatusId);
		try {

			List<ContactListCommStatus> foundContactListCommStatus = findContactListCommStatussBy(requestParams).getBody();
			if(foundContactListCommStatus.size()==1){				return successful(foundContactListCommStatus.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{contactListCommStatusId}")
	public ResponseEntity<String> deleteContactListCommStatusByIdUpdated(@PathVariable String contactListCommStatusId) throws Exception {
		DeleteContactListCommStatus command = new DeleteContactListCommStatus(contactListCommStatusId);

		try {
			if (((ContactListCommStatusDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
