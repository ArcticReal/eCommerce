package com.skytala.eCommerce.domain.marketing.relations.contactListPartyStatus;

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
import com.skytala.eCommerce.domain.marketing.relations.contactListPartyStatus.command.AddContactListPartyStatus;
import com.skytala.eCommerce.domain.marketing.relations.contactListPartyStatus.command.DeleteContactListPartyStatus;
import com.skytala.eCommerce.domain.marketing.relations.contactListPartyStatus.command.UpdateContactListPartyStatus;
import com.skytala.eCommerce.domain.marketing.relations.contactListPartyStatus.event.ContactListPartyStatusAdded;
import com.skytala.eCommerce.domain.marketing.relations.contactListPartyStatus.event.ContactListPartyStatusDeleted;
import com.skytala.eCommerce.domain.marketing.relations.contactListPartyStatus.event.ContactListPartyStatusFound;
import com.skytala.eCommerce.domain.marketing.relations.contactListPartyStatus.event.ContactListPartyStatusUpdated;
import com.skytala.eCommerce.domain.marketing.relations.contactListPartyStatus.mapper.ContactListPartyStatusMapper;
import com.skytala.eCommerce.domain.marketing.relations.contactListPartyStatus.model.ContactListPartyStatus;
import com.skytala.eCommerce.domain.marketing.relations.contactListPartyStatus.query.FindContactListPartyStatussBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/contactListPartyStatuss")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findContactListPartyStatussBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindContactListPartyStatussBy query = new FindContactListPartyStatussBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ContactListPartyStatus> contactListPartyStatuss =((ContactListPartyStatusFound) Scheduler.execute(query).data()).getContactListPartyStatuss();

		if (contactListPartyStatuss.size() == 1) {
			return ResponseEntity.ok().body(contactListPartyStatuss.get(0));
		}

		return ResponseEntity.ok().body(contactListPartyStatuss);

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
	public ResponseEntity<Object> createContactListPartyStatus(HttpServletRequest request) throws Exception {

		ContactListPartyStatus contactListPartyStatusToBeAdded = new ContactListPartyStatus();
		try {
			contactListPartyStatusToBeAdded = ContactListPartyStatusMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createContactListPartyStatus(contactListPartyStatusToBeAdded);

	}

	/**
	 * creates a new ContactListPartyStatus entry in the ofbiz database
	 * 
	 * @param contactListPartyStatusToBeAdded
	 *            the ContactListPartyStatus thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createContactListPartyStatus(@RequestBody ContactListPartyStatus contactListPartyStatusToBeAdded) throws Exception {

		AddContactListPartyStatus command = new AddContactListPartyStatus(contactListPartyStatusToBeAdded);
		ContactListPartyStatus contactListPartyStatus = ((ContactListPartyStatusAdded) Scheduler.execute(command).data()).getAddedContactListPartyStatus();
		
		if (contactListPartyStatus != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(contactListPartyStatus);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ContactListPartyStatus could not be created.");
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
	public boolean updateContactListPartyStatus(HttpServletRequest request) throws Exception {

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

		ContactListPartyStatus contactListPartyStatusToBeUpdated = new ContactListPartyStatus();

		try {
			contactListPartyStatusToBeUpdated = ContactListPartyStatusMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateContactListPartyStatus(contactListPartyStatusToBeUpdated, contactListPartyStatusToBeUpdated.getPartyId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateContactListPartyStatus(@RequestBody ContactListPartyStatus contactListPartyStatusToBeUpdated,
			@PathVariable String partyId) throws Exception {

		contactListPartyStatusToBeUpdated.setPartyId(partyId);

		UpdateContactListPartyStatus command = new UpdateContactListPartyStatus(contactListPartyStatusToBeUpdated);

		try {
			if(((ContactListPartyStatusUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{contactListPartyStatusId}")
	public ResponseEntity<Object> findById(@PathVariable String contactListPartyStatusId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("contactListPartyStatusId", contactListPartyStatusId);
		try {

			Object foundContactListPartyStatus = findContactListPartyStatussBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundContactListPartyStatus);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{contactListPartyStatusId}")
	public ResponseEntity<Object> deleteContactListPartyStatusByIdUpdated(@PathVariable String contactListPartyStatusId) throws Exception {
		DeleteContactListPartyStatus command = new DeleteContactListPartyStatus(contactListPartyStatusId);

		try {
			if (((ContactListPartyStatusDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ContactListPartyStatus could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/contactListPartyStatus/\" plus one of the following: "
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
