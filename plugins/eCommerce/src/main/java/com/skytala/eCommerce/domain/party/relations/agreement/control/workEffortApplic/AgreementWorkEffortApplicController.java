package com.skytala.eCommerce.domain.party.relations.agreement.control.workEffortApplic;

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
import com.skytala.eCommerce.domain.party.relations.agreement.command.workEffortApplic.AddAgreementWorkEffortApplic;
import com.skytala.eCommerce.domain.party.relations.agreement.command.workEffortApplic.DeleteAgreementWorkEffortApplic;
import com.skytala.eCommerce.domain.party.relations.agreement.command.workEffortApplic.UpdateAgreementWorkEffortApplic;
import com.skytala.eCommerce.domain.party.relations.agreement.event.workEffortApplic.AgreementWorkEffortApplicAdded;
import com.skytala.eCommerce.domain.party.relations.agreement.event.workEffortApplic.AgreementWorkEffortApplicDeleted;
import com.skytala.eCommerce.domain.party.relations.agreement.event.workEffortApplic.AgreementWorkEffortApplicFound;
import com.skytala.eCommerce.domain.party.relations.agreement.event.workEffortApplic.AgreementWorkEffortApplicUpdated;
import com.skytala.eCommerce.domain.party.relations.agreement.mapper.workEffortApplic.AgreementWorkEffortApplicMapper;
import com.skytala.eCommerce.domain.party.relations.agreement.model.workEffortApplic.AgreementWorkEffortApplic;
import com.skytala.eCommerce.domain.party.relations.agreement.query.workEffortApplic.FindAgreementWorkEffortApplicsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/agreementWorkEffortApplics")
public class AgreementWorkEffortApplicController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public AgreementWorkEffortApplicController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a AgreementWorkEffortApplic
	 * @return a List with the AgreementWorkEffortApplics
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findAgreementWorkEffortApplicsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindAgreementWorkEffortApplicsBy query = new FindAgreementWorkEffortApplicsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<AgreementWorkEffortApplic> agreementWorkEffortApplics =((AgreementWorkEffortApplicFound) Scheduler.execute(query).data()).getAgreementWorkEffortApplics();

		if (agreementWorkEffortApplics.size() == 1) {
			return ResponseEntity.ok().body(agreementWorkEffortApplics.get(0));
		}

		return ResponseEntity.ok().body(agreementWorkEffortApplics);

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
	public ResponseEntity<Object> createAgreementWorkEffortApplic(HttpServletRequest request) throws Exception {

		AgreementWorkEffortApplic agreementWorkEffortApplicToBeAdded = new AgreementWorkEffortApplic();
		try {
			agreementWorkEffortApplicToBeAdded = AgreementWorkEffortApplicMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createAgreementWorkEffortApplic(agreementWorkEffortApplicToBeAdded);

	}

	/**
	 * creates a new AgreementWorkEffortApplic entry in the ofbiz database
	 * 
	 * @param agreementWorkEffortApplicToBeAdded
	 *            the AgreementWorkEffortApplic thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createAgreementWorkEffortApplic(@RequestBody AgreementWorkEffortApplic agreementWorkEffortApplicToBeAdded) throws Exception {

		AddAgreementWorkEffortApplic command = new AddAgreementWorkEffortApplic(agreementWorkEffortApplicToBeAdded);
		AgreementWorkEffortApplic agreementWorkEffortApplic = ((AgreementWorkEffortApplicAdded) Scheduler.execute(command).data()).getAddedAgreementWorkEffortApplic();
		
		if (agreementWorkEffortApplic != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(agreementWorkEffortApplic);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("AgreementWorkEffortApplic could not be created.");
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
	public boolean updateAgreementWorkEffortApplic(HttpServletRequest request) throws Exception {

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

		AgreementWorkEffortApplic agreementWorkEffortApplicToBeUpdated = new AgreementWorkEffortApplic();

		try {
			agreementWorkEffortApplicToBeUpdated = AgreementWorkEffortApplicMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateAgreementWorkEffortApplic(agreementWorkEffortApplicToBeUpdated, agreementWorkEffortApplicToBeUpdated.getAgreementItemSeqId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the AgreementWorkEffortApplic with the specific Id
	 * 
	 * @param agreementWorkEffortApplicToBeUpdated
	 *            the AgreementWorkEffortApplic thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{agreementItemSeqId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateAgreementWorkEffortApplic(@RequestBody AgreementWorkEffortApplic agreementWorkEffortApplicToBeUpdated,
			@PathVariable String agreementItemSeqId) throws Exception {

		agreementWorkEffortApplicToBeUpdated.setAgreementItemSeqId(agreementItemSeqId);

		UpdateAgreementWorkEffortApplic command = new UpdateAgreementWorkEffortApplic(agreementWorkEffortApplicToBeUpdated);

		try {
			if(((AgreementWorkEffortApplicUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{agreementWorkEffortApplicId}")
	public ResponseEntity<Object> findById(@PathVariable String agreementWorkEffortApplicId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("agreementWorkEffortApplicId", agreementWorkEffortApplicId);
		try {

			Object foundAgreementWorkEffortApplic = findAgreementWorkEffortApplicsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundAgreementWorkEffortApplic);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{agreementWorkEffortApplicId}")
	public ResponseEntity<Object> deleteAgreementWorkEffortApplicByIdUpdated(@PathVariable String agreementWorkEffortApplicId) throws Exception {
		DeleteAgreementWorkEffortApplic command = new DeleteAgreementWorkEffortApplic(agreementWorkEffortApplicId);

		try {
			if (((AgreementWorkEffortApplicDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("AgreementWorkEffortApplic could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/agreementWorkEffortApplic/\" plus one of the following: "
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
