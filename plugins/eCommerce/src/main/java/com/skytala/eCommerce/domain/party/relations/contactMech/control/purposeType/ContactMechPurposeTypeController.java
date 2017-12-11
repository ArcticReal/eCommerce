package com.skytala.eCommerce.domain.party.relations.contactMech.control.purposeType;

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
import com.skytala.eCommerce.domain.party.relations.contactMech.command.purposeType.AddContactMechPurposeType;
import com.skytala.eCommerce.domain.party.relations.contactMech.command.purposeType.DeleteContactMechPurposeType;
import com.skytala.eCommerce.domain.party.relations.contactMech.command.purposeType.UpdateContactMechPurposeType;
import com.skytala.eCommerce.domain.party.relations.contactMech.event.purposeType.ContactMechPurposeTypeAdded;
import com.skytala.eCommerce.domain.party.relations.contactMech.event.purposeType.ContactMechPurposeTypeDeleted;
import com.skytala.eCommerce.domain.party.relations.contactMech.event.purposeType.ContactMechPurposeTypeFound;
import com.skytala.eCommerce.domain.party.relations.contactMech.event.purposeType.ContactMechPurposeTypeUpdated;
import com.skytala.eCommerce.domain.party.relations.contactMech.mapper.purposeType.ContactMechPurposeTypeMapper;
import com.skytala.eCommerce.domain.party.relations.contactMech.model.purposeType.ContactMechPurposeType;
import com.skytala.eCommerce.domain.party.relations.contactMech.query.purposeType.FindContactMechPurposeTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/party/contactMech/contactMechPurposeTypes")
public class ContactMechPurposeTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ContactMechPurposeTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ContactMechPurposeType
	 * @return a List with the ContactMechPurposeTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findContactMechPurposeTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindContactMechPurposeTypesBy query = new FindContactMechPurposeTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ContactMechPurposeType> contactMechPurposeTypes =((ContactMechPurposeTypeFound) Scheduler.execute(query).data()).getContactMechPurposeTypes();

		if (contactMechPurposeTypes.size() == 1) {
			return ResponseEntity.ok().body(contactMechPurposeTypes.get(0));
		}

		return ResponseEntity.ok().body(contactMechPurposeTypes);

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
	public ResponseEntity<Object> createContactMechPurposeType(HttpServletRequest request) throws Exception {

		ContactMechPurposeType contactMechPurposeTypeToBeAdded = new ContactMechPurposeType();
		try {
			contactMechPurposeTypeToBeAdded = ContactMechPurposeTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createContactMechPurposeType(contactMechPurposeTypeToBeAdded);

	}

	/**
	 * creates a new ContactMechPurposeType entry in the ofbiz database
	 * 
	 * @param contactMechPurposeTypeToBeAdded
	 *            the ContactMechPurposeType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createContactMechPurposeType(@RequestBody ContactMechPurposeType contactMechPurposeTypeToBeAdded) throws Exception {

		AddContactMechPurposeType command = new AddContactMechPurposeType(contactMechPurposeTypeToBeAdded);
		ContactMechPurposeType contactMechPurposeType = ((ContactMechPurposeTypeAdded) Scheduler.execute(command).data()).getAddedContactMechPurposeType();
		
		if (contactMechPurposeType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(contactMechPurposeType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ContactMechPurposeType could not be created.");
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
	public boolean updateContactMechPurposeType(HttpServletRequest request) throws Exception {

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

		ContactMechPurposeType contactMechPurposeTypeToBeUpdated = new ContactMechPurposeType();

		try {
			contactMechPurposeTypeToBeUpdated = ContactMechPurposeTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateContactMechPurposeType(contactMechPurposeTypeToBeUpdated, contactMechPurposeTypeToBeUpdated.getContactMechPurposeTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ContactMechPurposeType with the specific Id
	 * 
	 * @param contactMechPurposeTypeToBeUpdated
	 *            the ContactMechPurposeType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{contactMechPurposeTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateContactMechPurposeType(@RequestBody ContactMechPurposeType contactMechPurposeTypeToBeUpdated,
			@PathVariable String contactMechPurposeTypeId) throws Exception {

		contactMechPurposeTypeToBeUpdated.setContactMechPurposeTypeId(contactMechPurposeTypeId);

		UpdateContactMechPurposeType command = new UpdateContactMechPurposeType(contactMechPurposeTypeToBeUpdated);

		try {
			if(((ContactMechPurposeTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{contactMechPurposeTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String contactMechPurposeTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("contactMechPurposeTypeId", contactMechPurposeTypeId);
		try {

			Object foundContactMechPurposeType = findContactMechPurposeTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundContactMechPurposeType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{contactMechPurposeTypeId}")
	public ResponseEntity<Object> deleteContactMechPurposeTypeByIdUpdated(@PathVariable String contactMechPurposeTypeId) throws Exception {
		DeleteContactMechPurposeType command = new DeleteContactMechPurposeType(contactMechPurposeTypeId);

		try {
			if (((ContactMechPurposeTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ContactMechPurposeType could not be deleted");

	}

}
