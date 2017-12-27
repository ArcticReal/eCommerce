package com.skytala.eCommerce.domain.party.relations.contactMech.control.type;

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
import com.skytala.eCommerce.domain.party.relations.contactMech.command.type.AddContactMechType;
import com.skytala.eCommerce.domain.party.relations.contactMech.command.type.DeleteContactMechType;
import com.skytala.eCommerce.domain.party.relations.contactMech.command.type.UpdateContactMechType;
import com.skytala.eCommerce.domain.party.relations.contactMech.event.type.ContactMechTypeAdded;
import com.skytala.eCommerce.domain.party.relations.contactMech.event.type.ContactMechTypeDeleted;
import com.skytala.eCommerce.domain.party.relations.contactMech.event.type.ContactMechTypeFound;
import com.skytala.eCommerce.domain.party.relations.contactMech.event.type.ContactMechTypeUpdated;
import com.skytala.eCommerce.domain.party.relations.contactMech.mapper.type.ContactMechTypeMapper;
import com.skytala.eCommerce.domain.party.relations.contactMech.model.type.ContactMechType;
import com.skytala.eCommerce.domain.party.relations.contactMech.query.type.FindContactMechTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/party/contactMech/contactMechTypes")
public class ContactMechTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ContactMechTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ContactMechType
	 * @return a List with the ContactMechTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ContactMechType>> findContactMechTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindContactMechTypesBy query = new FindContactMechTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ContactMechType> contactMechTypes =((ContactMechTypeFound) Scheduler.execute(query).data()).getContactMechTypes();

		return ResponseEntity.ok().body(contactMechTypes);

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
	public ResponseEntity<ContactMechType> createContactMechType(HttpServletRequest request) throws Exception {

		ContactMechType contactMechTypeToBeAdded = new ContactMechType();
		try {
			contactMechTypeToBeAdded = ContactMechTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createContactMechType(contactMechTypeToBeAdded);

	}

	/**
	 * creates a new ContactMechType entry in the ofbiz database
	 * 
	 * @param contactMechTypeToBeAdded
	 *            the ContactMechType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ContactMechType> createContactMechType(@RequestBody ContactMechType contactMechTypeToBeAdded) throws Exception {

		AddContactMechType command = new AddContactMechType(contactMechTypeToBeAdded);
		ContactMechType contactMechType = ((ContactMechTypeAdded) Scheduler.execute(command).data()).getAddedContactMechType();
		
		if (contactMechType != null) 
			return successful(contactMechType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ContactMechType with the specific Id
	 * 
	 * @param contactMechTypeToBeUpdated
	 *            the ContactMechType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{contactMechTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateContactMechType(@RequestBody ContactMechType contactMechTypeToBeUpdated,
			@PathVariable String contactMechTypeId) throws Exception {

		contactMechTypeToBeUpdated.setContactMechTypeId(contactMechTypeId);

		UpdateContactMechType command = new UpdateContactMechType(contactMechTypeToBeUpdated);

		try {
			if(((ContactMechTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{contactMechTypeId}")
	public ResponseEntity<ContactMechType> findById(@PathVariable String contactMechTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("contactMechTypeId", contactMechTypeId);
		try {

			List<ContactMechType> foundContactMechType = findContactMechTypesBy(requestParams).getBody();
			if(foundContactMechType.size()==1){				return successful(foundContactMechType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{contactMechTypeId}")
	public ResponseEntity<String> deleteContactMechTypeByIdUpdated(@PathVariable String contactMechTypeId) throws Exception {
		DeleteContactMechType command = new DeleteContactMechType(contactMechTypeId);

		try {
			if (((ContactMechTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
