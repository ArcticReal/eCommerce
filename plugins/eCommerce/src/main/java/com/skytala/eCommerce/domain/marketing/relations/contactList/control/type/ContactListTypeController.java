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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@CrossOrigin
@RequestMapping("/contactListTypes")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findContactListTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindContactListTypesBy query = new FindContactListTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ContactListType> contactListTypes =((ContactListTypeFound) Scheduler.execute(query).data()).getContactListTypes();

		if (contactListTypes.size() == 1) {
			return ResponseEntity.ok().body(contactListTypes.get(0));
		}

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
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createContactListType(HttpServletRequest request) throws Exception {

		ContactListType contactListTypeToBeAdded = new ContactListType();
		try {
			contactListTypeToBeAdded = ContactListTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
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
	public ResponseEntity<Object> createContactListType(@RequestBody ContactListType contactListTypeToBeAdded) throws Exception {

		AddContactListType command = new AddContactListType(contactListTypeToBeAdded);
		ContactListType contactListType = ((ContactListTypeAdded) Scheduler.execute(command).data()).getAddedContactListType();
		
		if (contactListType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(contactListType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ContactListType could not be created.");
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
	public boolean updateContactListType(HttpServletRequest request) throws Exception {

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

		ContactListType contactListTypeToBeUpdated = new ContactListType();

		try {
			contactListTypeToBeUpdated = ContactListTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateContactListType(contactListTypeToBeUpdated, contactListTypeToBeUpdated.getContactListTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateContactListType(@RequestBody ContactListType contactListTypeToBeUpdated,
			@PathVariable String contactListTypeId) throws Exception {

		contactListTypeToBeUpdated.setContactListTypeId(contactListTypeId);

		UpdateContactListType command = new UpdateContactListType(contactListTypeToBeUpdated);

		try {
			if(((ContactListTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{contactListTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String contactListTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("contactListTypeId", contactListTypeId);
		try {

			Object foundContactListType = findContactListTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundContactListType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{contactListTypeId}")
	public ResponseEntity<Object> deleteContactListTypeByIdUpdated(@PathVariable String contactListTypeId) throws Exception {
		DeleteContactListType command = new DeleteContactListType(contactListTypeId);

		try {
			if (((ContactListTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ContactListType could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/contactListType/\" plus one of the following: "
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
