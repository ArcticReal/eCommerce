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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@RequestMapping("/contactMechLinks")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findContactMechLinksBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindContactMechLinksBy query = new FindContactMechLinksBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ContactMechLink> contactMechLinks =((ContactMechLinkFound) Scheduler.execute(query).data()).getContactMechLinks();

		if (contactMechLinks.size() == 1) {
			return ResponseEntity.ok().body(contactMechLinks.get(0));
		}

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
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createContactMechLink(HttpServletRequest request) throws Exception {

		ContactMechLink contactMechLinkToBeAdded = new ContactMechLink();
		try {
			contactMechLinkToBeAdded = ContactMechLinkMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
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
	public ResponseEntity<Object> createContactMechLink(@RequestBody ContactMechLink contactMechLinkToBeAdded) throws Exception {

		AddContactMechLink command = new AddContactMechLink(contactMechLinkToBeAdded);
		ContactMechLink contactMechLink = ((ContactMechLinkAdded) Scheduler.execute(command).data()).getAddedContactMechLink();
		
		if (contactMechLink != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(contactMechLink);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ContactMechLink could not be created.");
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
	@RequestMapping(method = RequestMethod.PUT, value = "/update", consumes = "application/x-www-form-urlencoded")
	public boolean updateContactMechLink(HttpServletRequest request) throws Exception {

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

		ContactMechLink contactMechLinkToBeUpdated = new ContactMechLink();

		try {
			contactMechLinkToBeUpdated = ContactMechLinkMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateContactMechLink(contactMechLinkToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateContactMechLink(@RequestBody ContactMechLink contactMechLinkToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		contactMechLinkToBeUpdated.setnull(null);

		UpdateContactMechLink command = new UpdateContactMechLink(contactMechLinkToBeUpdated);

		try {
			if(((ContactMechLinkUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{contactMechLinkId}")
	public ResponseEntity<Object> findById(@PathVariable String contactMechLinkId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("contactMechLinkId", contactMechLinkId);
		try {

			Object foundContactMechLink = findContactMechLinksBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundContactMechLink);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{contactMechLinkId}")
	public ResponseEntity<Object> deleteContactMechLinkByIdUpdated(@PathVariable String contactMechLinkId) throws Exception {
		DeleteContactMechLink command = new DeleteContactMechLink(contactMechLinkId);

		try {
			if (((ContactMechLinkDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ContactMechLink could not be deleted");

	}

}
