package com.skytala.eCommerce.domain.party.relations.agreement.control.attribute;

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
import com.skytala.eCommerce.domain.party.relations.agreement.command.attribute.AddAgreementAttribute;
import com.skytala.eCommerce.domain.party.relations.agreement.command.attribute.DeleteAgreementAttribute;
import com.skytala.eCommerce.domain.party.relations.agreement.command.attribute.UpdateAgreementAttribute;
import com.skytala.eCommerce.domain.party.relations.agreement.event.attribute.AgreementAttributeAdded;
import com.skytala.eCommerce.domain.party.relations.agreement.event.attribute.AgreementAttributeDeleted;
import com.skytala.eCommerce.domain.party.relations.agreement.event.attribute.AgreementAttributeFound;
import com.skytala.eCommerce.domain.party.relations.agreement.event.attribute.AgreementAttributeUpdated;
import com.skytala.eCommerce.domain.party.relations.agreement.mapper.attribute.AgreementAttributeMapper;
import com.skytala.eCommerce.domain.party.relations.agreement.model.attribute.AgreementAttribute;
import com.skytala.eCommerce.domain.party.relations.agreement.query.attribute.FindAgreementAttributesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/party/agreement/agreementAttributes")
public class AgreementAttributeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public AgreementAttributeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a AgreementAttribute
	 * @return a List with the AgreementAttributes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<AgreementAttribute>> findAgreementAttributesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindAgreementAttributesBy query = new FindAgreementAttributesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<AgreementAttribute> agreementAttributes =((AgreementAttributeFound) Scheduler.execute(query).data()).getAgreementAttributes();

		return ResponseEntity.ok().body(agreementAttributes);

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
	public ResponseEntity<AgreementAttribute> createAgreementAttribute(HttpServletRequest request) throws Exception {

		AgreementAttribute agreementAttributeToBeAdded = new AgreementAttribute();
		try {
			agreementAttributeToBeAdded = AgreementAttributeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createAgreementAttribute(agreementAttributeToBeAdded);

	}

	/**
	 * creates a new AgreementAttribute entry in the ofbiz database
	 * 
	 * @param agreementAttributeToBeAdded
	 *            the AgreementAttribute thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<AgreementAttribute> createAgreementAttribute(@RequestBody AgreementAttribute agreementAttributeToBeAdded) throws Exception {

		AddAgreementAttribute command = new AddAgreementAttribute(agreementAttributeToBeAdded);
		AgreementAttribute agreementAttribute = ((AgreementAttributeAdded) Scheduler.execute(command).data()).getAddedAgreementAttribute();
		
		if (agreementAttribute != null) 
			return successful(agreementAttribute);
		else 
			return conflict(null);
	}

	/**
	 * Updates the AgreementAttribute with the specific Id
	 * 
	 * @param agreementAttributeToBeUpdated
	 *            the AgreementAttribute thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{attrName}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateAgreementAttribute(@RequestBody AgreementAttribute agreementAttributeToBeUpdated,
			@PathVariable String attrName) throws Exception {

		agreementAttributeToBeUpdated.setAttrName(attrName);

		UpdateAgreementAttribute command = new UpdateAgreementAttribute(agreementAttributeToBeUpdated);

		try {
			if(((AgreementAttributeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{agreementAttributeId}")
	public ResponseEntity<AgreementAttribute> findById(@PathVariable String agreementAttributeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("agreementAttributeId", agreementAttributeId);
		try {

			List<AgreementAttribute> foundAgreementAttribute = findAgreementAttributesBy(requestParams).getBody();
			if(foundAgreementAttribute.size()==1){				return successful(foundAgreementAttribute.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{agreementAttributeId}")
	public ResponseEntity<String> deleteAgreementAttributeByIdUpdated(@PathVariable String agreementAttributeId) throws Exception {
		DeleteAgreementAttribute command = new DeleteAgreementAttribute(agreementAttributeId);

		try {
			if (((AgreementAttributeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
