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
	public ResponseEntity<Object> findAgreementAttributesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindAgreementAttributesBy query = new FindAgreementAttributesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<AgreementAttribute> agreementAttributes =((AgreementAttributeFound) Scheduler.execute(query).data()).getAgreementAttributes();

		if (agreementAttributes.size() == 1) {
			return ResponseEntity.ok().body(agreementAttributes.get(0));
		}

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
	public ResponseEntity<Object> createAgreementAttribute(HttpServletRequest request) throws Exception {

		AgreementAttribute agreementAttributeToBeAdded = new AgreementAttribute();
		try {
			agreementAttributeToBeAdded = AgreementAttributeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
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
	public ResponseEntity<Object> createAgreementAttribute(@RequestBody AgreementAttribute agreementAttributeToBeAdded) throws Exception {

		AddAgreementAttribute command = new AddAgreementAttribute(agreementAttributeToBeAdded);
		AgreementAttribute agreementAttribute = ((AgreementAttributeAdded) Scheduler.execute(command).data()).getAddedAgreementAttribute();
		
		if (agreementAttribute != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(agreementAttribute);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("AgreementAttribute could not be created.");
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
	public boolean updateAgreementAttribute(HttpServletRequest request) throws Exception {

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

		AgreementAttribute agreementAttributeToBeUpdated = new AgreementAttribute();

		try {
			agreementAttributeToBeUpdated = AgreementAttributeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateAgreementAttribute(agreementAttributeToBeUpdated, agreementAttributeToBeUpdated.getAttrName()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateAgreementAttribute(@RequestBody AgreementAttribute agreementAttributeToBeUpdated,
			@PathVariable String attrName) throws Exception {

		agreementAttributeToBeUpdated.setAttrName(attrName);

		UpdateAgreementAttribute command = new UpdateAgreementAttribute(agreementAttributeToBeUpdated);

		try {
			if(((AgreementAttributeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{agreementAttributeId}")
	public ResponseEntity<Object> findById(@PathVariable String agreementAttributeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("agreementAttributeId", agreementAttributeId);
		try {

			Object foundAgreementAttribute = findAgreementAttributesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundAgreementAttribute);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{agreementAttributeId}")
	public ResponseEntity<Object> deleteAgreementAttributeByIdUpdated(@PathVariable String agreementAttributeId) throws Exception {
		DeleteAgreementAttribute command = new DeleteAgreementAttribute(agreementAttributeId);

		try {
			if (((AgreementAttributeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("AgreementAttribute could not be deleted");

	}

}
