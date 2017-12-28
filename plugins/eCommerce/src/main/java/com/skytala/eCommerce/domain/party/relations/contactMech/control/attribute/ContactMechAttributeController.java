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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/party/contactMech/contactMechAttributes")
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
	@GetMapping("/find")
	public ResponseEntity<List<ContactMechAttribute>> findContactMechAttributesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindContactMechAttributesBy query = new FindContactMechAttributesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ContactMechAttribute> contactMechAttributes =((ContactMechAttributeFound) Scheduler.execute(query).data()).getContactMechAttributes();

		return ResponseEntity.ok().body(contactMechAttributes);

	}

	/**
	 * creates a new ContactMechAttribute entry in the ofbiz database
	 * 
	 * @param contactMechAttributeToBeAdded
	 *            the ContactMechAttribute thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ContactMechAttribute> createContactMechAttribute(@RequestBody ContactMechAttribute contactMechAttributeToBeAdded) throws Exception {

		AddContactMechAttribute command = new AddContactMechAttribute(contactMechAttributeToBeAdded);
		ContactMechAttribute contactMechAttribute = ((ContactMechAttributeAdded) Scheduler.execute(command).data()).getAddedContactMechAttribute();
		
		if (contactMechAttribute != null) 
			return successful(contactMechAttribute);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ContactMechAttribute with the specific Id
	 * 
	 * @param contactMechAttributeToBeUpdated
	 *            the ContactMechAttribute thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{attrName}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateContactMechAttribute(@RequestBody ContactMechAttribute contactMechAttributeToBeUpdated,
			@PathVariable String attrName) throws Exception {

		contactMechAttributeToBeUpdated.setAttrName(attrName);

		UpdateContactMechAttribute command = new UpdateContactMechAttribute(contactMechAttributeToBeUpdated);

		try {
			if(((ContactMechAttributeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{contactMechAttributeId}")
	public ResponseEntity<ContactMechAttribute> findById(@PathVariable String contactMechAttributeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("contactMechAttributeId", contactMechAttributeId);
		try {

			List<ContactMechAttribute> foundContactMechAttribute = findContactMechAttributesBy(requestParams).getBody();
			if(foundContactMechAttribute.size()==1){				return successful(foundContactMechAttribute.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{contactMechAttributeId}")
	public ResponseEntity<String> deleteContactMechAttributeByIdUpdated(@PathVariable String contactMechAttributeId) throws Exception {
		DeleteContactMechAttribute command = new DeleteContactMechAttribute(contactMechAttributeId);

		try {
			if (((ContactMechAttributeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
