package com.skytala.eCommerce.domain.party.relations.contactMech.control.typePurpose;

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
import com.skytala.eCommerce.domain.party.relations.contactMech.command.typePurpose.AddContactMechTypePurpose;
import com.skytala.eCommerce.domain.party.relations.contactMech.command.typePurpose.DeleteContactMechTypePurpose;
import com.skytala.eCommerce.domain.party.relations.contactMech.command.typePurpose.UpdateContactMechTypePurpose;
import com.skytala.eCommerce.domain.party.relations.contactMech.event.typePurpose.ContactMechTypePurposeAdded;
import com.skytala.eCommerce.domain.party.relations.contactMech.event.typePurpose.ContactMechTypePurposeDeleted;
import com.skytala.eCommerce.domain.party.relations.contactMech.event.typePurpose.ContactMechTypePurposeFound;
import com.skytala.eCommerce.domain.party.relations.contactMech.event.typePurpose.ContactMechTypePurposeUpdated;
import com.skytala.eCommerce.domain.party.relations.contactMech.mapper.typePurpose.ContactMechTypePurposeMapper;
import com.skytala.eCommerce.domain.party.relations.contactMech.model.typePurpose.ContactMechTypePurpose;
import com.skytala.eCommerce.domain.party.relations.contactMech.query.typePurpose.FindContactMechTypePurposesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/party/contactMech/contactMechTypePurposes")
public class ContactMechTypePurposeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ContactMechTypePurposeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ContactMechTypePurpose
	 * @return a List with the ContactMechTypePurposes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ContactMechTypePurpose>> findContactMechTypePurposesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindContactMechTypePurposesBy query = new FindContactMechTypePurposesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ContactMechTypePurpose> contactMechTypePurposes =((ContactMechTypePurposeFound) Scheduler.execute(query).data()).getContactMechTypePurposes();

		return ResponseEntity.ok().body(contactMechTypePurposes);

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
	public ResponseEntity<ContactMechTypePurpose> createContactMechTypePurpose(HttpServletRequest request) throws Exception {

		ContactMechTypePurpose contactMechTypePurposeToBeAdded = new ContactMechTypePurpose();
		try {
			contactMechTypePurposeToBeAdded = ContactMechTypePurposeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createContactMechTypePurpose(contactMechTypePurposeToBeAdded);

	}

	/**
	 * creates a new ContactMechTypePurpose entry in the ofbiz database
	 * 
	 * @param contactMechTypePurposeToBeAdded
	 *            the ContactMechTypePurpose thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ContactMechTypePurpose> createContactMechTypePurpose(@RequestBody ContactMechTypePurpose contactMechTypePurposeToBeAdded) throws Exception {

		AddContactMechTypePurpose command = new AddContactMechTypePurpose(contactMechTypePurposeToBeAdded);
		ContactMechTypePurpose contactMechTypePurpose = ((ContactMechTypePurposeAdded) Scheduler.execute(command).data()).getAddedContactMechTypePurpose();
		
		if (contactMechTypePurpose != null) 
			return successful(contactMechTypePurpose);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ContactMechTypePurpose with the specific Id
	 * 
	 * @param contactMechTypePurposeToBeUpdated
	 *            the ContactMechTypePurpose thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateContactMechTypePurpose(@RequestBody ContactMechTypePurpose contactMechTypePurposeToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		contactMechTypePurposeToBeUpdated.setnull(null);

		UpdateContactMechTypePurpose command = new UpdateContactMechTypePurpose(contactMechTypePurposeToBeUpdated);

		try {
			if(((ContactMechTypePurposeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{contactMechTypePurposeId}")
	public ResponseEntity<ContactMechTypePurpose> findById(@PathVariable String contactMechTypePurposeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("contactMechTypePurposeId", contactMechTypePurposeId);
		try {

			List<ContactMechTypePurpose> foundContactMechTypePurpose = findContactMechTypePurposesBy(requestParams).getBody();
			if(foundContactMechTypePurpose.size()==1){				return successful(foundContactMechTypePurpose.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{contactMechTypePurposeId}")
	public ResponseEntity<String> deleteContactMechTypePurposeByIdUpdated(@PathVariable String contactMechTypePurposeId) throws Exception {
		DeleteContactMechTypePurpose command = new DeleteContactMechTypePurpose(contactMechTypePurposeId);

		try {
			if (((ContactMechTypePurposeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
