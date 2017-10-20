package com.skytala.eCommerce.domain.party.relations.contactMech.control.attribute;

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
import com.skytala.eCommerce.domain.party.relations.contactMech.command.attribute.AddContactMechAttribute;
import com.skytala.eCommerce.domain.party.relations.contactMech.command.attribute.DeleteContactMechAttribute;
import com.skytala.eCommerce.domain.party.relations.contactMech.command.attribute.UpdateContactMechAttribute;
import com.skytala.eCommerce.domain.party.relations.contactMech.event.attribute.ContactMechAttributeAdded;
import com.skytala.eCommerce.domain.party.relations.contactMech.event.attribute.ContactMechAttributeDeleted;
import com.skytala.eCommerce.domain.party.relations.contactMech.event.attribute.ContactMechAttributeFound;
import com.skytala.eCommerce.domain.party.relations.contactMech.event.attribute.ContactMechAttributeUpdated;
import com.skytala.eCommerce.domain.party.relations.contactMech.mapper.attribute.ContactMechAttributeMapper;
import com.skytala.eCommerce.domain.party.relations.contactMech.model.attribute.ContactMechAttribute;
import com.skytala.eCommerce.domain.party.relations.contactMech.query.attribute.FindContactMechAttributesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@CrossOrigin
@RequestMapping("/contactMechAttributes")
public class ContactMechAttributeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ContactMechAttributeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ContactMechAttribute
	 * @return a List with the ContactMechAttributes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findContactMechAttributesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindContactMechAttributesBy query = new FindContactMechAttributesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ContactMechAttribute> contactMechAttributes =((ContactMechAttributeFound) Scheduler.execute(query).data()).getContactMechAttributes();

		if (contactMechAttributes.size() == 1) {
			return ResponseEntity.ok().body(contactMechAttributes.get(0));
		}

		return ResponseEntity.ok().body(contactMechAttributes);

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
	public ResponseEntity<Object> createContactMechAttribute(HttpServletRequest request) throws Exception {

		ContactMechAttribute contactMechAttributeToBeAdded = new ContactMechAttribute();
		try {
			contactMechAttributeToBeAdded = ContactMechAttributeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createContactMechAttribute(contactMechAttributeToBeAdded);

	}

	/**
	 * creates a new ContactMechAttribute entry in the ofbiz database
	 * 
	 * @param contactMechAttributeToBeAdded
	 *            the ContactMechAttribute thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createContactMechAttribute(@RequestBody ContactMechAttribute contactMechAttributeToBeAdded) throws Exception {

		AddContactMechAttribute command = new AddContactMechAttribute(contactMechAttributeToBeAdded);
		ContactMechAttribute contactMechAttribute = ((ContactMechAttributeAdded) Scheduler.execute(command).data()).getAddedContactMechAttribute();
		
		if (contactMechAttribute != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(contactMechAttribute);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ContactMechAttribute could not be created.");
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
	public boolean updateContactMechAttribute(HttpServletRequest request) throws Exception {

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

		ContactMechAttribute contactMechAttributeToBeUpdated = new ContactMechAttribute();

		try {
			contactMechAttributeToBeUpdated = ContactMechAttributeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateContactMechAttribute(contactMechAttributeToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ContactMechAttribute with the specific Id
	 * 
	 * @param contactMechAttributeToBeUpdated
	 *            the ContactMechAttribute thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateContactMechAttribute(@RequestBody ContactMechAttribute contactMechAttributeToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		contactMechAttributeToBeUpdated.setnull(null);

		UpdateContactMechAttribute command = new UpdateContactMechAttribute(contactMechAttributeToBeUpdated);

		try {
			if(((ContactMechAttributeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{contactMechAttributeId}")
	public ResponseEntity<Object> findById(@PathVariable String contactMechAttributeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("contactMechAttributeId", contactMechAttributeId);
		try {

			Object foundContactMechAttribute = findContactMechAttributesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundContactMechAttribute);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{contactMechAttributeId}")
	public ResponseEntity<Object> deleteContactMechAttributeByIdUpdated(@PathVariable String contactMechAttributeId) throws Exception {
		DeleteContactMechAttribute command = new DeleteContactMechAttribute(contactMechAttributeId);

		try {
			if (((ContactMechAttributeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ContactMechAttribute could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/contactMechAttribute/\" plus one of the following: "
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
