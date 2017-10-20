package com.skytala.eCommerce.domain.party.relations.agreement;

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
import com.skytala.eCommerce.domain.party.relations.agreement.command.AddAgreement;
import com.skytala.eCommerce.domain.party.relations.agreement.command.DeleteAgreement;
import com.skytala.eCommerce.domain.party.relations.agreement.command.UpdateAgreement;
import com.skytala.eCommerce.domain.party.relations.agreement.event.AgreementAdded;
import com.skytala.eCommerce.domain.party.relations.agreement.event.AgreementDeleted;
import com.skytala.eCommerce.domain.party.relations.agreement.event.AgreementFound;
import com.skytala.eCommerce.domain.party.relations.agreement.event.AgreementUpdated;
import com.skytala.eCommerce.domain.party.relations.agreement.mapper.AgreementMapper;
import com.skytala.eCommerce.domain.party.relations.agreement.model.Agreement;
import com.skytala.eCommerce.domain.party.relations.agreement.query.FindAgreementsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@CrossOrigin
@RequestMapping("/agreements")
public class AgreementController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public AgreementController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a Agreement
	 * @return a List with the Agreements
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findAgreementsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindAgreementsBy query = new FindAgreementsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<Agreement> agreements =((AgreementFound) Scheduler.execute(query).data()).getAgreements();

		if (agreements.size() == 1) {
			return ResponseEntity.ok().body(agreements.get(0));
		}

		return ResponseEntity.ok().body(agreements);

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
	public ResponseEntity<Object> createAgreement(HttpServletRequest request) throws Exception {

		Agreement agreementToBeAdded = new Agreement();
		try {
			agreementToBeAdded = AgreementMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createAgreement(agreementToBeAdded);

	}

	/**
	 * creates a new Agreement entry in the ofbiz database
	 * 
	 * @param agreementToBeAdded
	 *            the Agreement thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createAgreement(@RequestBody Agreement agreementToBeAdded) throws Exception {

		AddAgreement command = new AddAgreement(agreementToBeAdded);
		Agreement agreement = ((AgreementAdded) Scheduler.execute(command).data()).getAddedAgreement();
		
		if (agreement != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(agreement);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("Agreement could not be created.");
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
	public boolean updateAgreement(HttpServletRequest request) throws Exception {

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

		Agreement agreementToBeUpdated = new Agreement();

		try {
			agreementToBeUpdated = AgreementMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateAgreement(agreementToBeUpdated, agreementToBeUpdated.getAgreementId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the Agreement with the specific Id
	 * 
	 * @param agreementToBeUpdated
	 *            the Agreement thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{agreementId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateAgreement(@RequestBody Agreement agreementToBeUpdated,
			@PathVariable String agreementId) throws Exception {

		agreementToBeUpdated.setAgreementId(agreementId);

		UpdateAgreement command = new UpdateAgreement(agreementToBeUpdated);

		try {
			if(((AgreementUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{agreementId}")
	public ResponseEntity<Object> findById(@PathVariable String agreementId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("agreementId", agreementId);
		try {

			Object foundAgreement = findAgreementsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundAgreement);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{agreementId}")
	public ResponseEntity<Object> deleteAgreementByIdUpdated(@PathVariable String agreementId) throws Exception {
		DeleteAgreement command = new DeleteAgreement(agreementId);

		try {
			if (((AgreementDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("Agreement could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/agreement/\" plus one of the following: "
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
