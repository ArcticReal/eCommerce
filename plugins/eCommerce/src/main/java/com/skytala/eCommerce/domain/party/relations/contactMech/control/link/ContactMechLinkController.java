package com.skytala.eCommerce.domain.party.relations.contactMech.control.link;

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
import com.skytala.eCommerce.domain.party.relations.contactMech.command.link.AddContactMechLink;
import com.skytala.eCommerce.domain.party.relations.contactMech.command.link.DeleteContactMechLink;
import com.skytala.eCommerce.domain.party.relations.contactMech.command.link.UpdateContactMechLink;
import com.skytala.eCommerce.domain.party.relations.contactMech.event.link.ContactMechLinkAdded;
import com.skytala.eCommerce.domain.party.relations.contactMech.event.link.ContactMechLinkDeleted;
import com.skytala.eCommerce.domain.party.relations.contactMech.event.link.ContactMechLinkFound;
import com.skytala.eCommerce.domain.party.relations.contactMech.event.link.ContactMechLinkUpdated;
import com.skytala.eCommerce.domain.party.relations.contactMech.mapper.link.ContactMechLinkMapper;
import com.skytala.eCommerce.domain.party.relations.contactMech.model.link.ContactMechLink;
import com.skytala.eCommerce.domain.party.relations.contactMech.query.link.FindContactMechLinksBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/party/contactMech/contactMechLinks")
public class ContactMechLinkController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ContactMechLinkController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ContactMechLink
	 * @return a List with the ContactMechLinks
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ContactMechLink>> findContactMechLinksBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindContactMechLinksBy query = new FindContactMechLinksBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ContactMechLink> contactMechLinks =((ContactMechLinkFound) Scheduler.execute(query).data()).getContactMechLinks();

		return ResponseEntity.ok().body(contactMechLinks);

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
	public ResponseEntity<ContactMechLink> createContactMechLink(HttpServletRequest request) throws Exception {

		ContactMechLink contactMechLinkToBeAdded = new ContactMechLink();
		try {
			contactMechLinkToBeAdded = ContactMechLinkMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createContactMechLink(contactMechLinkToBeAdded);

	}

	/**
	 * creates a new ContactMechLink entry in the ofbiz database
	 * 
	 * @param contactMechLinkToBeAdded
	 *            the ContactMechLink thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ContactMechLink> createContactMechLink(@RequestBody ContactMechLink contactMechLinkToBeAdded) throws Exception {

		AddContactMechLink command = new AddContactMechLink(contactMechLinkToBeAdded);
		ContactMechLink contactMechLink = ((ContactMechLinkAdded) Scheduler.execute(command).data()).getAddedContactMechLink();
		
		if (contactMechLink != null) 
			return successful(contactMechLink);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ContactMechLink with the specific Id
	 * 
	 * @param contactMechLinkToBeUpdated
	 *            the ContactMechLink thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateContactMechLink(@RequestBody ContactMechLink contactMechLinkToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		contactMechLinkToBeUpdated.setnull(null);

		UpdateContactMechLink command = new UpdateContactMechLink(contactMechLinkToBeUpdated);

		try {
			if(((ContactMechLinkUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{contactMechLinkId}")
	public ResponseEntity<ContactMechLink> findById(@PathVariable String contactMechLinkId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("contactMechLinkId", contactMechLinkId);
		try {

			List<ContactMechLink> foundContactMechLink = findContactMechLinksBy(requestParams).getBody();
			if(foundContactMechLink.size()==1){				return successful(foundContactMechLink.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{contactMechLinkId}")
	public ResponseEntity<String> deleteContactMechLinkByIdUpdated(@PathVariable String contactMechLinkId) throws Exception {
		DeleteContactMechLink command = new DeleteContactMechLink(contactMechLinkId);

		try {
			if (((ContactMechLinkDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
