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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@CrossOrigin
@RequestMapping("/contactMechTypePurposes")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findContactMechTypePurposesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindContactMechTypePurposesBy query = new FindContactMechTypePurposesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ContactMechTypePurpose> contactMechTypePurposes =((ContactMechTypePurposeFound) Scheduler.execute(query).data()).getContactMechTypePurposes();

		if (contactMechTypePurposes.size() == 1) {
			return ResponseEntity.ok().body(contactMechTypePurposes.get(0));
		}

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
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createContactMechTypePurpose(HttpServletRequest request) throws Exception {

		ContactMechTypePurpose contactMechTypePurposeToBeAdded = new ContactMechTypePurpose();
		try {
			contactMechTypePurposeToBeAdded = ContactMechTypePurposeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
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
	public ResponseEntity<Object> createContactMechTypePurpose(@RequestBody ContactMechTypePurpose contactMechTypePurposeToBeAdded) throws Exception {

		AddContactMechTypePurpose command = new AddContactMechTypePurpose(contactMechTypePurposeToBeAdded);
		ContactMechTypePurpose contactMechTypePurpose = ((ContactMechTypePurposeAdded) Scheduler.execute(command).data()).getAddedContactMechTypePurpose();
		
		if (contactMechTypePurpose != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(contactMechTypePurpose);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ContactMechTypePurpose could not be created.");
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
	public boolean updateContactMechTypePurpose(HttpServletRequest request) throws Exception {

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

		ContactMechTypePurpose contactMechTypePurposeToBeUpdated = new ContactMechTypePurpose();

		try {
			contactMechTypePurposeToBeUpdated = ContactMechTypePurposeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateContactMechTypePurpose(contactMechTypePurposeToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateContactMechTypePurpose(@RequestBody ContactMechTypePurpose contactMechTypePurposeToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		contactMechTypePurposeToBeUpdated.setnull(null);

		UpdateContactMechTypePurpose command = new UpdateContactMechTypePurpose(contactMechTypePurposeToBeUpdated);

		try {
			if(((ContactMechTypePurposeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{contactMechTypePurposeId}")
	public ResponseEntity<Object> findById(@PathVariable String contactMechTypePurposeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("contactMechTypePurposeId", contactMechTypePurposeId);
		try {

			Object foundContactMechTypePurpose = findContactMechTypePurposesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundContactMechTypePurpose);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{contactMechTypePurposeId}")
	public ResponseEntity<Object> deleteContactMechTypePurposeByIdUpdated(@PathVariable String contactMechTypePurposeId) throws Exception {
		DeleteContactMechTypePurpose command = new DeleteContactMechTypePurpose(contactMechTypePurposeId);

		try {
			if (((ContactMechTypePurposeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ContactMechTypePurpose could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/contactMechTypePurpose/\" plus one of the following: "
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
