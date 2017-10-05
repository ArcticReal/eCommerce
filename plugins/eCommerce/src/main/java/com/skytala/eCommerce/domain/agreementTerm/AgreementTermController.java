package com.skytala.eCommerce.domain.agreementTerm;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Splitter;
import com.skytala.eCommerce.domain.agreementTerm.command.AddAgreementTerm;
import com.skytala.eCommerce.domain.agreementTerm.command.DeleteAgreementTerm;
import com.skytala.eCommerce.domain.agreementTerm.command.UpdateAgreementTerm;
import com.skytala.eCommerce.domain.agreementTerm.event.AgreementTermAdded;
import com.skytala.eCommerce.domain.agreementTerm.event.AgreementTermDeleted;
import com.skytala.eCommerce.domain.agreementTerm.event.AgreementTermFound;
import com.skytala.eCommerce.domain.agreementTerm.event.AgreementTermUpdated;
import com.skytala.eCommerce.domain.agreementTerm.mapper.AgreementTermMapper;
import com.skytala.eCommerce.domain.agreementTerm.model.AgreementTerm;
import com.skytala.eCommerce.domain.agreementTerm.query.FindAgreementTermsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/agreementTerms")
public class AgreementTermController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public AgreementTermController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a AgreementTerm
	 * @return a List with the AgreementTerms
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findAgreementTermsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindAgreementTermsBy query = new FindAgreementTermsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<AgreementTerm> agreementTerms =((AgreementTermFound) Scheduler.execute(query).data()).getAgreementTerms();

		if (agreementTerms.size() == 1) {
			return ResponseEntity.ok().body(agreementTerms.get(0));
		}

		return ResponseEntity.ok().body(agreementTerms);

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
	public ResponseEntity<Object> createAgreementTerm(HttpServletRequest request) throws Exception {

		AgreementTerm agreementTermToBeAdded = new AgreementTerm();
		try {
			agreementTermToBeAdded = AgreementTermMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createAgreementTerm(agreementTermToBeAdded);

	}

	/**
	 * creates a new AgreementTerm entry in the ofbiz database
	 * 
	 * @param agreementTermToBeAdded
	 *            the AgreementTerm thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createAgreementTerm(@RequestBody AgreementTerm agreementTermToBeAdded) throws Exception {

		AddAgreementTerm command = new AddAgreementTerm(agreementTermToBeAdded);
		AgreementTerm agreementTerm = ((AgreementTermAdded) Scheduler.execute(command).data()).getAddedAgreementTerm();
		
		if (agreementTerm != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(agreementTerm);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("AgreementTerm could not be created.");
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
	public boolean updateAgreementTerm(HttpServletRequest request) throws Exception {

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

		AgreementTerm agreementTermToBeUpdated = new AgreementTerm();

		try {
			agreementTermToBeUpdated = AgreementTermMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateAgreementTerm(agreementTermToBeUpdated, agreementTermToBeUpdated.getAgreementTermId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the AgreementTerm with the specific Id
	 * 
	 * @param agreementTermToBeUpdated
	 *            the AgreementTerm thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{agreementTermId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateAgreementTerm(@RequestBody AgreementTerm agreementTermToBeUpdated,
			@PathVariable String agreementTermId) throws Exception {

		agreementTermToBeUpdated.setAgreementTermId(agreementTermId);

		UpdateAgreementTerm command = new UpdateAgreementTerm(agreementTermToBeUpdated);

		try {
			if(((AgreementTermUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{agreementTermId}")
	public ResponseEntity<Object> findById(@PathVariable String agreementTermId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("agreementTermId", agreementTermId);
		try {

			Object foundAgreementTerm = findAgreementTermsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundAgreementTerm);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{agreementTermId}")
	public ResponseEntity<Object> deleteAgreementTermByIdUpdated(@PathVariable String agreementTermId) throws Exception {
		DeleteAgreementTerm command = new DeleteAgreementTerm(agreementTermId);

		try {
			if (((AgreementTermDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("AgreementTerm could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/agreementTerm/\" plus one of the following: "
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
