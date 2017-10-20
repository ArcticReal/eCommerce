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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@CrossOrigin
@RequestMapping("/contactListCommStatuss")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findContactListCommStatussBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindContactListCommStatussBy query = new FindContactListCommStatussBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ContactListCommStatus> contactListCommStatuss =((ContactListCommStatusFound) Scheduler.execute(query).data()).getContactListCommStatuss();

		if (contactListCommStatuss.size() == 1) {
			return ResponseEntity.ok().body(contactListCommStatuss.get(0));
		}

		return ResponseEntity.ok().body(contactListCommStatuss);

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
	public ResponseEntity<Object> createContactListCommStatus(HttpServletRequest request) throws Exception {

		ContactListCommStatus contactListCommStatusToBeAdded = new ContactListCommStatus();
		try {
			contactListCommStatusToBeAdded = ContactListCommStatusMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createContactListCommStatus(contactListCommStatusToBeAdded);

	}

	/**
	 * creates a new ContactListCommStatus entry in the ofbiz database
	 * 
	 * @param contactListCommStatusToBeAdded
	 *            the ContactListCommStatus thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createContactListCommStatus(@RequestBody ContactListCommStatus contactListCommStatusToBeAdded) throws Exception {

		AddContactListCommStatus command = new AddContactListCommStatus(contactListCommStatusToBeAdded);
		ContactListCommStatus contactListCommStatus = ((ContactListCommStatusAdded) Scheduler.execute(command).data()).getAddedContactListCommStatus();
		
		if (contactListCommStatus != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(contactListCommStatus);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ContactListCommStatus could not be created.");
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
	public boolean updateContactListCommStatus(HttpServletRequest request) throws Exception {

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

		ContactListCommStatus contactListCommStatusToBeUpdated = new ContactListCommStatus();

		try {
			contactListCommStatusToBeUpdated = ContactListCommStatusMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateContactListCommStatus(contactListCommStatusToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateContactListCommStatus(@RequestBody ContactListCommStatus contactListCommStatusToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		contactListCommStatusToBeUpdated.setnull(null);

		UpdateContactListCommStatus command = new UpdateContactListCommStatus(contactListCommStatusToBeUpdated);

		try {
			if(((ContactListCommStatusUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{contactListCommStatusId}")
	public ResponseEntity<Object> findById(@PathVariable String contactListCommStatusId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("contactListCommStatusId", contactListCommStatusId);
		try {

			Object foundContactListCommStatus = findContactListCommStatussBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundContactListCommStatus);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{contactListCommStatusId}")
	public ResponseEntity<Object> deleteContactListCommStatusByIdUpdated(@PathVariable String contactListCommStatusId) throws Exception {
		DeleteContactListCommStatus command = new DeleteContactListCommStatus(contactListCommStatusId);

		try {
			if (((ContactListCommStatusDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ContactListCommStatus could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/contactListCommStatus/\" plus one of the following: "
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
