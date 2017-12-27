package com.skytala.eCommerce.domain.party.relations.agreement.control.itemAttribute;

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
import com.skytala.eCommerce.domain.party.relations.agreement.command.itemAttribute.AddAgreementItemAttribute;
import com.skytala.eCommerce.domain.party.relations.agreement.command.itemAttribute.DeleteAgreementItemAttribute;
import com.skytala.eCommerce.domain.party.relations.agreement.command.itemAttribute.UpdateAgreementItemAttribute;
import com.skytala.eCommerce.domain.party.relations.agreement.event.itemAttribute.AgreementItemAttributeAdded;
import com.skytala.eCommerce.domain.party.relations.agreement.event.itemAttribute.AgreementItemAttributeDeleted;
import com.skytala.eCommerce.domain.party.relations.agreement.event.itemAttribute.AgreementItemAttributeFound;
import com.skytala.eCommerce.domain.party.relations.agreement.event.itemAttribute.AgreementItemAttributeUpdated;
import com.skytala.eCommerce.domain.party.relations.agreement.mapper.itemAttribute.AgreementItemAttributeMapper;
import com.skytala.eCommerce.domain.party.relations.agreement.model.itemAttribute.AgreementItemAttribute;
import com.skytala.eCommerce.domain.party.relations.agreement.query.itemAttribute.FindAgreementItemAttributesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/party/agreement/agreementItemAttributes")
public class AgreementItemAttributeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public AgreementItemAttributeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a AgreementItemAttribute
	 * @return a List with the AgreementItemAttributes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<AgreementItemAttribute>> findAgreementItemAttributesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindAgreementItemAttributesBy query = new FindAgreementItemAttributesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<AgreementItemAttribute> agreementItemAttributes =((AgreementItemAttributeFound) Scheduler.execute(query).data()).getAgreementItemAttributes();

		return ResponseEntity.ok().body(agreementItemAttributes);

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
	public ResponseEntity<AgreementItemAttribute> createAgreementItemAttribute(HttpServletRequest request) throws Exception {

		AgreementItemAttribute agreementItemAttributeToBeAdded = new AgreementItemAttribute();
		try {
			agreementItemAttributeToBeAdded = AgreementItemAttributeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createAgreementItemAttribute(agreementItemAttributeToBeAdded);

	}

	/**
	 * creates a new AgreementItemAttribute entry in the ofbiz database
	 * 
	 * @param agreementItemAttributeToBeAdded
	 *            the AgreementItemAttribute thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<AgreementItemAttribute> createAgreementItemAttribute(@RequestBody AgreementItemAttribute agreementItemAttributeToBeAdded) throws Exception {

		AddAgreementItemAttribute command = new AddAgreementItemAttribute(agreementItemAttributeToBeAdded);
		AgreementItemAttribute agreementItemAttribute = ((AgreementItemAttributeAdded) Scheduler.execute(command).data()).getAddedAgreementItemAttribute();
		
		if (agreementItemAttribute != null) 
			return successful(agreementItemAttribute);
		else 
			return conflict(null);
	}

	/**
	 * Updates the AgreementItemAttribute with the specific Id
	 * 
	 * @param agreementItemAttributeToBeUpdated
	 *            the AgreementItemAttribute thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateAgreementItemAttribute(@RequestBody AgreementItemAttribute agreementItemAttributeToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		agreementItemAttributeToBeUpdated.setnull(null);

		UpdateAgreementItemAttribute command = new UpdateAgreementItemAttribute(agreementItemAttributeToBeUpdated);

		try {
			if(((AgreementItemAttributeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{agreementItemAttributeId}")
	public ResponseEntity<AgreementItemAttribute> findById(@PathVariable String agreementItemAttributeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("agreementItemAttributeId", agreementItemAttributeId);
		try {

			List<AgreementItemAttribute> foundAgreementItemAttribute = findAgreementItemAttributesBy(requestParams).getBody();
			if(foundAgreementItemAttribute.size()==1){				return successful(foundAgreementItemAttribute.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{agreementItemAttributeId}")
	public ResponseEntity<String> deleteAgreementItemAttributeByIdUpdated(@PathVariable String agreementItemAttributeId) throws Exception {
		DeleteAgreementItemAttribute command = new DeleteAgreementItemAttribute(agreementItemAttributeId);

		try {
			if (((AgreementItemAttributeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
