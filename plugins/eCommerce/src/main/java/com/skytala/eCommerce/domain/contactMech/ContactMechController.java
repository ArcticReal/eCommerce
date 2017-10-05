package com.skytala.eCommerce.domain.contactMech;

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
import com.skytala.eCommerce.domain.contactMech.command.AddContactMech;
import com.skytala.eCommerce.domain.contactMech.command.DeleteContactMech;
import com.skytala.eCommerce.domain.contactMech.command.UpdateContactMech;
import com.skytala.eCommerce.domain.contactMech.event.ContactMechAdded;
import com.skytala.eCommerce.domain.contactMech.event.ContactMechDeleted;
import com.skytala.eCommerce.domain.contactMech.event.ContactMechFound;
import com.skytala.eCommerce.domain.contactMech.event.ContactMechUpdated;
import com.skytala.eCommerce.domain.contactMech.mapper.ContactMechMapper;
import com.skytala.eCommerce.domain.contactMech.model.ContactMech;
import com.skytala.eCommerce.domain.contactMech.query.FindContactMechsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/contactMechs")
public class ContactMechController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ContactMechController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ContactMech
	 * @return a List with the ContactMechs
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findContactMechsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindContactMechsBy query = new FindContactMechsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ContactMech> contactMechs =((ContactMechFound) Scheduler.execute(query).data()).getContactMechs();

		if (contactMechs.size() == 1) {
			return ResponseEntity.ok().body(contactMechs.get(0));
		}

		return ResponseEntity.ok().body(contactMechs);

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
	public ResponseEntity<Object> createContactMech(HttpServletRequest request) throws Exception {

		ContactMech contactMechToBeAdded = new ContactMech();
		try {
			contactMechToBeAdded = ContactMechMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createContactMech(contactMechToBeAdded);

	}

	/**
	 * creates a new ContactMech entry in the ofbiz database
	 * 
	 * @param contactMechToBeAdded
	 *            the ContactMech thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createContactMech(@RequestBody ContactMech contactMechToBeAdded) throws Exception {

		AddContactMech command = new AddContactMech(contactMechToBeAdded);
		ContactMech contactMech = ((ContactMechAdded) Scheduler.execute(command).data()).getAddedContactMech();
		
		if (contactMech != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(contactMech);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ContactMech could not be created.");
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
	public boolean updateContactMech(HttpServletRequest request) throws Exception {

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

		ContactMech contactMechToBeUpdated = new ContactMech();

		try {
			contactMechToBeUpdated = ContactMechMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateContactMech(contactMechToBeUpdated, contactMechToBeUpdated.getContactMechId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ContactMech with the specific Id
	 * 
	 * @param contactMechToBeUpdated
	 *            the ContactMech thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{contactMechId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateContactMech(@RequestBody ContactMech contactMechToBeUpdated,
			@PathVariable String contactMechId) throws Exception {

		contactMechToBeUpdated.setContactMechId(contactMechId);

		UpdateContactMech command = new UpdateContactMech(contactMechToBeUpdated);

		try {
			if(((ContactMechUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{contactMechId}")
	public ResponseEntity<Object> findById(@PathVariable String contactMechId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("contactMechId", contactMechId);
		try {

			Object foundContactMech = findContactMechsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundContactMech);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{contactMechId}")
	public ResponseEntity<Object> deleteContactMechByIdUpdated(@PathVariable String contactMechId) throws Exception {
		DeleteContactMech command = new DeleteContactMech(contactMechId);

		try {
			if (((ContactMechDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ContactMech could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/contactMech/\" plus one of the following: "
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
