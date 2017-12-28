package com.skytala.eCommerce.domain.party.relations.agreement.control.termAttribute;

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
import com.skytala.eCommerce.domain.party.relations.agreement.command.termAttribute.AddAgreementTermAttribute;
import com.skytala.eCommerce.domain.party.relations.agreement.command.termAttribute.DeleteAgreementTermAttribute;
import com.skytala.eCommerce.domain.party.relations.agreement.command.termAttribute.UpdateAgreementTermAttribute;
import com.skytala.eCommerce.domain.party.relations.agreement.event.termAttribute.AgreementTermAttributeAdded;
import com.skytala.eCommerce.domain.party.relations.agreement.event.termAttribute.AgreementTermAttributeDeleted;
import com.skytala.eCommerce.domain.party.relations.agreement.event.termAttribute.AgreementTermAttributeFound;
import com.skytala.eCommerce.domain.party.relations.agreement.event.termAttribute.AgreementTermAttributeUpdated;
import com.skytala.eCommerce.domain.party.relations.agreement.mapper.termAttribute.AgreementTermAttributeMapper;
import com.skytala.eCommerce.domain.party.relations.agreement.model.termAttribute.AgreementTermAttribute;
import com.skytala.eCommerce.domain.party.relations.agreement.query.termAttribute.FindAgreementTermAttributesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/party/agreement/agreementTermAttributes")
public class AgreementTermAttributeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public AgreementTermAttributeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a AgreementTermAttribute
	 * @return a List with the AgreementTermAttributes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<AgreementTermAttribute>> findAgreementTermAttributesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindAgreementTermAttributesBy query = new FindAgreementTermAttributesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<AgreementTermAttribute> agreementTermAttributes =((AgreementTermAttributeFound) Scheduler.execute(query).data()).getAgreementTermAttributes();

		return ResponseEntity.ok().body(agreementTermAttributes);

	}

	/**
	 * creates a new AgreementTermAttribute entry in the ofbiz database
	 * 
	 * @param agreementTermAttributeToBeAdded
	 *            the AgreementTermAttribute thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<AgreementTermAttribute> createAgreementTermAttribute(@RequestBody AgreementTermAttribute agreementTermAttributeToBeAdded) throws Exception {

		AddAgreementTermAttribute command = new AddAgreementTermAttribute(agreementTermAttributeToBeAdded);
		AgreementTermAttribute agreementTermAttribute = ((AgreementTermAttributeAdded) Scheduler.execute(command).data()).getAddedAgreementTermAttribute();
		
		if (agreementTermAttribute != null) 
			return successful(agreementTermAttribute);
		else 
			return conflict(null);
	}

	/**
	 * Updates the AgreementTermAttribute with the specific Id
	 * 
	 * @param agreementTermAttributeToBeUpdated
	 *            the AgreementTermAttribute thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{attrName}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateAgreementTermAttribute(@RequestBody AgreementTermAttribute agreementTermAttributeToBeUpdated,
			@PathVariable String attrName) throws Exception {

		agreementTermAttributeToBeUpdated.setAttrName(attrName);

		UpdateAgreementTermAttribute command = new UpdateAgreementTermAttribute(agreementTermAttributeToBeUpdated);

		try {
			if(((AgreementTermAttributeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{agreementTermAttributeId}")
	public ResponseEntity<AgreementTermAttribute> findById(@PathVariable String agreementTermAttributeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("agreementTermAttributeId", agreementTermAttributeId);
		try {

			List<AgreementTermAttribute> foundAgreementTermAttribute = findAgreementTermAttributesBy(requestParams).getBody();
			if(foundAgreementTermAttribute.size()==1){				return successful(foundAgreementTermAttribute.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{agreementTermAttributeId}")
	public ResponseEntity<String> deleteAgreementTermAttributeByIdUpdated(@PathVariable String agreementTermAttributeId) throws Exception {
		DeleteAgreementTermAttribute command = new DeleteAgreementTermAttribute(agreementTermAttributeId);

		try {
			if (((AgreementTermAttributeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
