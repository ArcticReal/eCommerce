package com.skytala.eCommerce.domain.contactMechType;

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
import com.skytala.eCommerce.domain.contactMechType.command.AddContactMechType;
import com.skytala.eCommerce.domain.contactMechType.command.DeleteContactMechType;
import com.skytala.eCommerce.domain.contactMechType.command.UpdateContactMechType;
import com.skytala.eCommerce.domain.contactMechType.event.ContactMechTypeAdded;
import com.skytala.eCommerce.domain.contactMechType.event.ContactMechTypeDeleted;
import com.skytala.eCommerce.domain.contactMechType.event.ContactMechTypeFound;
import com.skytala.eCommerce.domain.contactMechType.event.ContactMechTypeUpdated;
import com.skytala.eCommerce.domain.contactMechType.mapper.ContactMechTypeMapper;
import com.skytala.eCommerce.domain.contactMechType.model.ContactMechType;
import com.skytala.eCommerce.domain.contactMechType.query.FindContactMechTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/contactMechTypes")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findContactMechTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindContactMechTypesBy query = new FindContactMechTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ContactMechType> contactMechTypes =((ContactMechTypeFound) Scheduler.execute(query).data()).getContactMechTypes();

		if (contactMechTypes.size() == 1) {
			return ResponseEntity.ok().body(contactMechTypes.get(0));
		}

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
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createContactMechType(HttpServletRequest request) throws Exception {

		ContactMechType contactMechTypeToBeAdded = new ContactMechType();
		try {
			contactMechTypeToBeAdded = ContactMechTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
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
	public ResponseEntity<Object> createContactMechType(@RequestBody ContactMechType contactMechTypeToBeAdded) throws Exception {

		AddContactMechType command = new AddContactMechType(contactMechTypeToBeAdded);
		ContactMechType contactMechType = ((ContactMechTypeAdded) Scheduler.execute(command).data()).getAddedContactMechType();
		
		if (contactMechType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(contactMechType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ContactMechType could not be created.");
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
	public boolean updateContactMechType(HttpServletRequest request) throws Exception {

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

		ContactMechType contactMechTypeToBeUpdated = new ContactMechType();

		try {
			contactMechTypeToBeUpdated = ContactMechTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateContactMechType(contactMechTypeToBeUpdated, contactMechTypeToBeUpdated.getContactMechTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateContactMechType(@RequestBody ContactMechType contactMechTypeToBeUpdated,
			@PathVariable String contactMechTypeId) throws Exception {

		contactMechTypeToBeUpdated.setContactMechTypeId(contactMechTypeId);

		UpdateContactMechType command = new UpdateContactMechType(contactMechTypeToBeUpdated);

		try {
			if(((ContactMechTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{contactMechTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String contactMechTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("contactMechTypeId", contactMechTypeId);
		try {

			Object foundContactMechType = findContactMechTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundContactMechType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{contactMechTypeId}")
	public ResponseEntity<Object> deleteContactMechTypeByIdUpdated(@PathVariable String contactMechTypeId) throws Exception {
		DeleteContactMechType command = new DeleteContactMechType(contactMechTypeId);

		try {
			if (((ContactMechTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ContactMechType could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/contactMechType/\" plus one of the following: "
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
