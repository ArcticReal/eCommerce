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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@RequestMapping("/agreementItemAttributes")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findAgreementItemAttributesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindAgreementItemAttributesBy query = new FindAgreementItemAttributesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<AgreementItemAttribute> agreementItemAttributes =((AgreementItemAttributeFound) Scheduler.execute(query).data()).getAgreementItemAttributes();

		if (agreementItemAttributes.size() == 1) {
			return ResponseEntity.ok().body(agreementItemAttributes.get(0));
		}

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
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createAgreementItemAttribute(HttpServletRequest request) throws Exception {

		AgreementItemAttribute agreementItemAttributeToBeAdded = new AgreementItemAttribute();
		try {
			agreementItemAttributeToBeAdded = AgreementItemAttributeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
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
	public ResponseEntity<Object> createAgreementItemAttribute(@RequestBody AgreementItemAttribute agreementItemAttributeToBeAdded) throws Exception {

		AddAgreementItemAttribute command = new AddAgreementItemAttribute(agreementItemAttributeToBeAdded);
		AgreementItemAttribute agreementItemAttribute = ((AgreementItemAttributeAdded) Scheduler.execute(command).data()).getAddedAgreementItemAttribute();
		
		if (agreementItemAttribute != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(agreementItemAttribute);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("AgreementItemAttribute could not be created.");
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
	public boolean updateAgreementItemAttribute(HttpServletRequest request) throws Exception {

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

		AgreementItemAttribute agreementItemAttributeToBeUpdated = new AgreementItemAttribute();

		try {
			agreementItemAttributeToBeUpdated = AgreementItemAttributeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateAgreementItemAttribute(agreementItemAttributeToBeUpdated, agreementItemAttributeToBeUpdated.getAgreementItemSeqId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the AgreementItemAttribute with the specific Id
	 * 
	 * @param agreementItemAttributeToBeUpdated
	 *            the AgreementItemAttribute thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{agreementItemSeqId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateAgreementItemAttribute(@RequestBody AgreementItemAttribute agreementItemAttributeToBeUpdated,
			@PathVariable String agreementItemSeqId) throws Exception {

		agreementItemAttributeToBeUpdated.setAgreementItemSeqId(agreementItemSeqId);

		UpdateAgreementItemAttribute command = new UpdateAgreementItemAttribute(agreementItemAttributeToBeUpdated);

		try {
			if(((AgreementItemAttributeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{agreementItemAttributeId}")
	public ResponseEntity<Object> findById(@PathVariable String agreementItemAttributeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("agreementItemAttributeId", agreementItemAttributeId);
		try {

			Object foundAgreementItemAttribute = findAgreementItemAttributesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundAgreementItemAttribute);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{agreementItemAttributeId}")
	public ResponseEntity<Object> deleteAgreementItemAttributeByIdUpdated(@PathVariable String agreementItemAttributeId) throws Exception {
		DeleteAgreementItemAttribute command = new DeleteAgreementItemAttribute(agreementItemAttributeId);

		try {
			if (((AgreementItemAttributeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("AgreementItemAttribute could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/agreementItemAttribute/\" plus one of the following: "
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
