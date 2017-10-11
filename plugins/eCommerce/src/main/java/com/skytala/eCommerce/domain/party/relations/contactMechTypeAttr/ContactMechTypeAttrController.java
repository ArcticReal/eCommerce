package com.skytala.eCommerce.domain.party.relations.contactMechTypeAttr;

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
import com.skytala.eCommerce.domain.party.relations.contactMechTypeAttr.command.AddContactMechTypeAttr;
import com.skytala.eCommerce.domain.party.relations.contactMechTypeAttr.command.DeleteContactMechTypeAttr;
import com.skytala.eCommerce.domain.party.relations.contactMechTypeAttr.command.UpdateContactMechTypeAttr;
import com.skytala.eCommerce.domain.party.relations.contactMechTypeAttr.event.ContactMechTypeAttrAdded;
import com.skytala.eCommerce.domain.party.relations.contactMechTypeAttr.event.ContactMechTypeAttrDeleted;
import com.skytala.eCommerce.domain.party.relations.contactMechTypeAttr.event.ContactMechTypeAttrFound;
import com.skytala.eCommerce.domain.party.relations.contactMechTypeAttr.event.ContactMechTypeAttrUpdated;
import com.skytala.eCommerce.domain.party.relations.contactMechTypeAttr.mapper.ContactMechTypeAttrMapper;
import com.skytala.eCommerce.domain.party.relations.contactMechTypeAttr.model.ContactMechTypeAttr;
import com.skytala.eCommerce.domain.party.relations.contactMechTypeAttr.query.FindContactMechTypeAttrsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/contactMechTypeAttrs")
public class ContactMechTypeAttrController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ContactMechTypeAttrController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ContactMechTypeAttr
	 * @return a List with the ContactMechTypeAttrs
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findContactMechTypeAttrsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindContactMechTypeAttrsBy query = new FindContactMechTypeAttrsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ContactMechTypeAttr> contactMechTypeAttrs =((ContactMechTypeAttrFound) Scheduler.execute(query).data()).getContactMechTypeAttrs();

		if (contactMechTypeAttrs.size() == 1) {
			return ResponseEntity.ok().body(contactMechTypeAttrs.get(0));
		}

		return ResponseEntity.ok().body(contactMechTypeAttrs);

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
	public ResponseEntity<Object> createContactMechTypeAttr(HttpServletRequest request) throws Exception {

		ContactMechTypeAttr contactMechTypeAttrToBeAdded = new ContactMechTypeAttr();
		try {
			contactMechTypeAttrToBeAdded = ContactMechTypeAttrMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createContactMechTypeAttr(contactMechTypeAttrToBeAdded);

	}

	/**
	 * creates a new ContactMechTypeAttr entry in the ofbiz database
	 * 
	 * @param contactMechTypeAttrToBeAdded
	 *            the ContactMechTypeAttr thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createContactMechTypeAttr(@RequestBody ContactMechTypeAttr contactMechTypeAttrToBeAdded) throws Exception {

		AddContactMechTypeAttr command = new AddContactMechTypeAttr(contactMechTypeAttrToBeAdded);
		ContactMechTypeAttr contactMechTypeAttr = ((ContactMechTypeAttrAdded) Scheduler.execute(command).data()).getAddedContactMechTypeAttr();
		
		if (contactMechTypeAttr != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(contactMechTypeAttr);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ContactMechTypeAttr could not be created.");
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
	public boolean updateContactMechTypeAttr(HttpServletRequest request) throws Exception {

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

		ContactMechTypeAttr contactMechTypeAttrToBeUpdated = new ContactMechTypeAttr();

		try {
			contactMechTypeAttrToBeUpdated = ContactMechTypeAttrMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateContactMechTypeAttr(contactMechTypeAttrToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ContactMechTypeAttr with the specific Id
	 * 
	 * @param contactMechTypeAttrToBeUpdated
	 *            the ContactMechTypeAttr thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateContactMechTypeAttr(@RequestBody ContactMechTypeAttr contactMechTypeAttrToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		contactMechTypeAttrToBeUpdated.setnull(null);

		UpdateContactMechTypeAttr command = new UpdateContactMechTypeAttr(contactMechTypeAttrToBeUpdated);

		try {
			if(((ContactMechTypeAttrUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{contactMechTypeAttrId}")
	public ResponseEntity<Object> findById(@PathVariable String contactMechTypeAttrId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("contactMechTypeAttrId", contactMechTypeAttrId);
		try {

			Object foundContactMechTypeAttr = findContactMechTypeAttrsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundContactMechTypeAttr);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{contactMechTypeAttrId}")
	public ResponseEntity<Object> deleteContactMechTypeAttrByIdUpdated(@PathVariable String contactMechTypeAttrId) throws Exception {
		DeleteContactMechTypeAttr command = new DeleteContactMechTypeAttr(contactMechTypeAttrId);

		try {
			if (((ContactMechTypeAttrDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ContactMechTypeAttr could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/contactMechTypeAttr/\" plus one of the following: "
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