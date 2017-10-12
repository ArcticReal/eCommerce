package com.skytala.eCommerce.domain.marketing.relations.contactListParty;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Splitter;
import com.skytala.eCommerce.domain.marketing.relations.contactListParty.command.AddContactListParty;
import com.skytala.eCommerce.domain.marketing.relations.contactListParty.command.DeleteContactListParty;
import com.skytala.eCommerce.domain.marketing.relations.contactListParty.command.UpdateContactListParty;
import com.skytala.eCommerce.domain.marketing.relations.contactListParty.event.ContactListPartyAdded;
import com.skytala.eCommerce.domain.marketing.relations.contactListParty.event.ContactListPartyDeleted;
import com.skytala.eCommerce.domain.marketing.relations.contactListParty.event.ContactListPartyFound;
import com.skytala.eCommerce.domain.marketing.relations.contactListParty.event.ContactListPartyUpdated;
import com.skytala.eCommerce.domain.marketing.relations.contactListParty.mapper.ContactListPartyMapper;
import com.skytala.eCommerce.domain.marketing.relations.contactListParty.model.ContactListParty;
import com.skytala.eCommerce.domain.marketing.relations.contactListParty.query.FindContactListPartysBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/contactListPartys")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
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
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
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
	@RequestMapping(method = RequestMethod.PUT, value = "/update", consumes = "application/x-www-form-urlencoded")
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

	@RequestMapping(method = RequestMethod.GET, value = "/{contactListPartyId}")
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

	@RequestMapping(method = RequestMethod.DELETE, value = "/{contactListPartyId}")
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

	@RequestMapping(value = (" ** "))
	public ResponseEntity<Object> returnErrorPage(HttpServletRequest request) {

		String usedUri = request.getRequestURI();
		String[] splittedString = usedUri.split("/");

		String usedRequest = splittedString[splittedString.length - 1];

		if (validRequests.containsKey(usedRequest)) {
			String returnVal = "Error: request method " + request.getMethod() + " not allowed for \"" + usedUri
					+ "\"!\n" + "Please use " + validRequests.get(usedRequest) + "!";

			return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(returnVal);
		}

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/contactListParty/\" plus one of the following: "
				+ "";

		Set<String> keySet = validRequests.keySet();
		Iterator<String> it = keySet.iterator();

		while (it.hasNext()) {
			returnVal += "\"" + it.next() + "\"";
			if (it.hasNext())
				returnVal += ", ";
		}

		returnVal += "!";

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnVal);

	}
}
