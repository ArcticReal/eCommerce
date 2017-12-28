package com.skytala.eCommerce.domain.party.relations.contactMech.control.purposeType;

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
import com.skytala.eCommerce.domain.party.relations.contactMech.command.purposeType.AddContactMechPurposeType;
import com.skytala.eCommerce.domain.party.relations.contactMech.command.purposeType.DeleteContactMechPurposeType;
import com.skytala.eCommerce.domain.party.relations.contactMech.command.purposeType.UpdateContactMechPurposeType;
import com.skytala.eCommerce.domain.party.relations.contactMech.event.purposeType.ContactMechPurposeTypeAdded;
import com.skytala.eCommerce.domain.party.relations.contactMech.event.purposeType.ContactMechPurposeTypeDeleted;
import com.skytala.eCommerce.domain.party.relations.contactMech.event.purposeType.ContactMechPurposeTypeFound;
import com.skytala.eCommerce.domain.party.relations.contactMech.event.purposeType.ContactMechPurposeTypeUpdated;
import com.skytala.eCommerce.domain.party.relations.contactMech.mapper.purposeType.ContactMechPurposeTypeMapper;
import com.skytala.eCommerce.domain.party.relations.contactMech.model.purposeType.ContactMechPurposeType;
import com.skytala.eCommerce.domain.party.relations.contactMech.query.purposeType.FindContactMechPurposeTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/party/contactMech/contactMechPurposeTypes")
public class ContactMechPurposeTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ContactMechPurposeTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ContactMechPurposeType
	 * @return a List with the ContactMechPurposeTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ContactMechPurposeType>> findContactMechPurposeTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindContactMechPurposeTypesBy query = new FindContactMechPurposeTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ContactMechPurposeType> contactMechPurposeTypes =((ContactMechPurposeTypeFound) Scheduler.execute(query).data()).getContactMechPurposeTypes();

		return ResponseEntity.ok().body(contactMechPurposeTypes);

	}

	/**
	 * creates a new ContactMechPurposeType entry in the ofbiz database
	 * 
	 * @param contactMechPurposeTypeToBeAdded
	 *            the ContactMechPurposeType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ContactMechPurposeType> createContactMechPurposeType(@RequestBody ContactMechPurposeType contactMechPurposeTypeToBeAdded) throws Exception {

		AddContactMechPurposeType command = new AddContactMechPurposeType(contactMechPurposeTypeToBeAdded);
		ContactMechPurposeType contactMechPurposeType = ((ContactMechPurposeTypeAdded) Scheduler.execute(command).data()).getAddedContactMechPurposeType();
		
		if (contactMechPurposeType != null) 
			return successful(contactMechPurposeType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ContactMechPurposeType with the specific Id
	 * 
	 * @param contactMechPurposeTypeToBeUpdated
	 *            the ContactMechPurposeType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{contactMechPurposeTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateContactMechPurposeType(@RequestBody ContactMechPurposeType contactMechPurposeTypeToBeUpdated,
			@PathVariable String contactMechPurposeTypeId) throws Exception {

		contactMechPurposeTypeToBeUpdated.setContactMechPurposeTypeId(contactMechPurposeTypeId);

		UpdateContactMechPurposeType command = new UpdateContactMechPurposeType(contactMechPurposeTypeToBeUpdated);

		try {
			if(((ContactMechPurposeTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{contactMechPurposeTypeId}")
	public ResponseEntity<ContactMechPurposeType> findById(@PathVariable String contactMechPurposeTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("contactMechPurposeTypeId", contactMechPurposeTypeId);
		try {

			List<ContactMechPurposeType> foundContactMechPurposeType = findContactMechPurposeTypesBy(requestParams).getBody();
			if(foundContactMechPurposeType.size()==1){				return successful(foundContactMechPurposeType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{contactMechPurposeTypeId}")
	public ResponseEntity<String> deleteContactMechPurposeTypeByIdUpdated(@PathVariable String contactMechPurposeTypeId) throws Exception {
		DeleteContactMechPurposeType command = new DeleteContactMechPurposeType(contactMechPurposeTypeId);

		try {
			if (((ContactMechPurposeTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
