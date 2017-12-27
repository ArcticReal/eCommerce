package com.skytala.eCommerce.domain.marketing.relations.contactList.control.type;

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
import com.skytala.eCommerce.domain.marketing.relations.contactList.command.type.AddContactListType;
import com.skytala.eCommerce.domain.marketing.relations.contactList.command.type.DeleteContactListType;
import com.skytala.eCommerce.domain.marketing.relations.contactList.command.type.UpdateContactListType;
import com.skytala.eCommerce.domain.marketing.relations.contactList.event.type.ContactListTypeAdded;
import com.skytala.eCommerce.domain.marketing.relations.contactList.event.type.ContactListTypeDeleted;
import com.skytala.eCommerce.domain.marketing.relations.contactList.event.type.ContactListTypeFound;
import com.skytala.eCommerce.domain.marketing.relations.contactList.event.type.ContactListTypeUpdated;
import com.skytala.eCommerce.domain.marketing.relations.contactList.mapper.type.ContactListTypeMapper;
import com.skytala.eCommerce.domain.marketing.relations.contactList.model.type.ContactListType;
import com.skytala.eCommerce.domain.marketing.relations.contactList.query.type.FindContactListTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/marketing/contactList/contactListTypes")
public class ContactListTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ContactListTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ContactListType
	 * @return a List with the ContactListTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ContactListType>> findContactListTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindContactListTypesBy query = new FindContactListTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ContactListType> contactListTypes =((ContactListTypeFound) Scheduler.execute(query).data()).getContactListTypes();

		return ResponseEntity.ok().body(contactListTypes);

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
	public ResponseEntity<ContactListType> createContactListType(HttpServletRequest request) throws Exception {

		ContactListType contactListTypeToBeAdded = new ContactListType();
		try {
			contactListTypeToBeAdded = ContactListTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createContactListType(contactListTypeToBeAdded);

	}

	/**
	 * creates a new ContactListType entry in the ofbiz database
	 * 
	 * @param contactListTypeToBeAdded
	 *            the ContactListType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ContactListType> createContactListType(@RequestBody ContactListType contactListTypeToBeAdded) throws Exception {

		AddContactListType command = new AddContactListType(contactListTypeToBeAdded);
		ContactListType contactListType = ((ContactListTypeAdded) Scheduler.execute(command).data()).getAddedContactListType();
		
		if (contactListType != null) 
			return successful(contactListType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ContactListType with the specific Id
	 * 
	 * @param contactListTypeToBeUpdated
	 *            the ContactListType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{contactListTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateContactListType(@RequestBody ContactListType contactListTypeToBeUpdated,
			@PathVariable String contactListTypeId) throws Exception {

		contactListTypeToBeUpdated.setContactListTypeId(contactListTypeId);

		UpdateContactListType command = new UpdateContactListType(contactListTypeToBeUpdated);

		try {
			if(((ContactListTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{contactListTypeId}")
	public ResponseEntity<ContactListType> findById(@PathVariable String contactListTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("contactListTypeId", contactListTypeId);
		try {

			List<ContactListType> foundContactListType = findContactListTypesBy(requestParams).getBody();
			if(foundContactListType.size()==1){				return successful(foundContactListType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{contactListTypeId}")
	public ResponseEntity<String> deleteContactListTypeByIdUpdated(@PathVariable String contactListTypeId) throws Exception {
		DeleteContactListType command = new DeleteContactListType(contactListTypeId);

		try {
			if (((ContactListTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
