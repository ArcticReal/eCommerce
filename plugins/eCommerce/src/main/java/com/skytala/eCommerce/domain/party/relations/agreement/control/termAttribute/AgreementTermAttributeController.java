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
	public ResponseEntity<Object> findAgreementTermAttributesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindAgreementTermAttributesBy query = new FindAgreementTermAttributesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<AgreementTermAttribute> agreementTermAttributes =((AgreementTermAttributeFound) Scheduler.execute(query).data()).getAgreementTermAttributes();

		if (agreementTermAttributes.size() == 1) {
			return ResponseEntity.ok().body(agreementTermAttributes.get(0));
		}

		return ResponseEntity.ok().body(agreementTermAttributes);

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
	public ResponseEntity<Object> createAgreementTermAttribute(HttpServletRequest request) throws Exception {

		AgreementTermAttribute agreementTermAttributeToBeAdded = new AgreementTermAttribute();
		try {
			agreementTermAttributeToBeAdded = AgreementTermAttributeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createAgreementTermAttribute(agreementTermAttributeToBeAdded);

	}

	/**
	 * creates a new AgreementTermAttribute entry in the ofbiz database
	 * 
	 * @param agreementTermAttributeToBeAdded
	 *            the AgreementTermAttribute thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createAgreementTermAttribute(@RequestBody AgreementTermAttribute agreementTermAttributeToBeAdded) throws Exception {

		AddAgreementTermAttribute command = new AddAgreementTermAttribute(agreementTermAttributeToBeAdded);
		AgreementTermAttribute agreementTermAttribute = ((AgreementTermAttributeAdded) Scheduler.execute(command).data()).getAddedAgreementTermAttribute();
		
		if (agreementTermAttribute != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(agreementTermAttribute);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("AgreementTermAttribute could not be created.");
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
	public boolean updateAgreementTermAttribute(HttpServletRequest request) throws Exception {

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

		AgreementTermAttribute agreementTermAttributeToBeUpdated = new AgreementTermAttribute();

		try {
			agreementTermAttributeToBeUpdated = AgreementTermAttributeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateAgreementTermAttribute(agreementTermAttributeToBeUpdated, agreementTermAttributeToBeUpdated.getAttrName()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateAgreementTermAttribute(@RequestBody AgreementTermAttribute agreementTermAttributeToBeUpdated,
			@PathVariable String attrName) throws Exception {

		agreementTermAttributeToBeUpdated.setAttrName(attrName);

		UpdateAgreementTermAttribute command = new UpdateAgreementTermAttribute(agreementTermAttributeToBeUpdated);

		try {
			if(((AgreementTermAttributeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{agreementTermAttributeId}")
	public ResponseEntity<Object> findById(@PathVariable String agreementTermAttributeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("agreementTermAttributeId", agreementTermAttributeId);
		try {

			Object foundAgreementTermAttribute = findAgreementTermAttributesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundAgreementTermAttribute);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{agreementTermAttributeId}")
	public ResponseEntity<Object> deleteAgreementTermAttributeByIdUpdated(@PathVariable String agreementTermAttributeId) throws Exception {
		DeleteAgreementTermAttribute command = new DeleteAgreementTermAttribute(agreementTermAttributeId);

		try {
			if (((AgreementTermAttributeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("AgreementTermAttribute could not be deleted");

	}

}
