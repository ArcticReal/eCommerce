package com.skytala.eCommerce.domain.party.relations.contactMech.control.typeAttr;

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
import org.springframework.web.bind.annotation.*;

import com.google.common.base.Splitter;
import com.skytala.eCommerce.domain.party.relations.contactMech.command.typeAttr.AddContactMechTypeAttr;
import com.skytala.eCommerce.domain.party.relations.contactMech.command.typeAttr.DeleteContactMechTypeAttr;
import com.skytala.eCommerce.domain.party.relations.contactMech.command.typeAttr.UpdateContactMechTypeAttr;
import com.skytala.eCommerce.domain.party.relations.contactMech.event.typeAttr.ContactMechTypeAttrAdded;
import com.skytala.eCommerce.domain.party.relations.contactMech.event.typeAttr.ContactMechTypeAttrDeleted;
import com.skytala.eCommerce.domain.party.relations.contactMech.event.typeAttr.ContactMechTypeAttrFound;
import com.skytala.eCommerce.domain.party.relations.contactMech.event.typeAttr.ContactMechTypeAttrUpdated;
import com.skytala.eCommerce.domain.party.relations.contactMech.mapper.typeAttr.ContactMechTypeAttrMapper;
import com.skytala.eCommerce.domain.party.relations.contactMech.model.typeAttr.ContactMechTypeAttr;
import com.skytala.eCommerce.domain.party.relations.contactMech.query.typeAttr.FindContactMechTypeAttrsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/party/contactMech/contactMechTypeAttrs")
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
	@GetMapping("/find")
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
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
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
	@PutMapping(value = "/update", consumes = "application/x-www-form-urlencoded")
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

		if (updateContactMechTypeAttr(contactMechTypeAttrToBeUpdated, contactMechTypeAttrToBeUpdated.getAttrName()).getStatusCode()
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
	@RequestMapping(method = RequestMethod.PUT, value = "/{attrName}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateContactMechTypeAttr(@RequestBody ContactMechTypeAttr contactMechTypeAttrToBeUpdated,
			@PathVariable String attrName) throws Exception {

		contactMechTypeAttrToBeUpdated.setAttrName(attrName);

		UpdateContactMechTypeAttr command = new UpdateContactMechTypeAttr(contactMechTypeAttrToBeUpdated);

		try {
			if(((ContactMechTypeAttrUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{contactMechTypeAttrId}")
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

	@DeleteMapping("/{contactMechTypeAttrId}")
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

}
