package com.skytala.eCommerce.domain.party.relations.agreement.control.geographicalApplic;

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
import com.skytala.eCommerce.domain.party.relations.agreement.command.geographicalApplic.AddAgreementGeographicalApplic;
import com.skytala.eCommerce.domain.party.relations.agreement.command.geographicalApplic.DeleteAgreementGeographicalApplic;
import com.skytala.eCommerce.domain.party.relations.agreement.command.geographicalApplic.UpdateAgreementGeographicalApplic;
import com.skytala.eCommerce.domain.party.relations.agreement.event.geographicalApplic.AgreementGeographicalApplicAdded;
import com.skytala.eCommerce.domain.party.relations.agreement.event.geographicalApplic.AgreementGeographicalApplicDeleted;
import com.skytala.eCommerce.domain.party.relations.agreement.event.geographicalApplic.AgreementGeographicalApplicFound;
import com.skytala.eCommerce.domain.party.relations.agreement.event.geographicalApplic.AgreementGeographicalApplicUpdated;
import com.skytala.eCommerce.domain.party.relations.agreement.mapper.geographicalApplic.AgreementGeographicalApplicMapper;
import com.skytala.eCommerce.domain.party.relations.agreement.model.geographicalApplic.AgreementGeographicalApplic;
import com.skytala.eCommerce.domain.party.relations.agreement.query.geographicalApplic.FindAgreementGeographicalApplicsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/agreementGeographicalApplics")
public class AgreementGeographicalApplicController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public AgreementGeographicalApplicController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a AgreementGeographicalApplic
	 * @return a List with the AgreementGeographicalApplics
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findAgreementGeographicalApplicsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindAgreementGeographicalApplicsBy query = new FindAgreementGeographicalApplicsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<AgreementGeographicalApplic> agreementGeographicalApplics =((AgreementGeographicalApplicFound) Scheduler.execute(query).data()).getAgreementGeographicalApplics();

		if (agreementGeographicalApplics.size() == 1) {
			return ResponseEntity.ok().body(agreementGeographicalApplics.get(0));
		}

		return ResponseEntity.ok().body(agreementGeographicalApplics);

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
	public ResponseEntity<Object> createAgreementGeographicalApplic(HttpServletRequest request) throws Exception {

		AgreementGeographicalApplic agreementGeographicalApplicToBeAdded = new AgreementGeographicalApplic();
		try {
			agreementGeographicalApplicToBeAdded = AgreementGeographicalApplicMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createAgreementGeographicalApplic(agreementGeographicalApplicToBeAdded);

	}

	/**
	 * creates a new AgreementGeographicalApplic entry in the ofbiz database
	 * 
	 * @param agreementGeographicalApplicToBeAdded
	 *            the AgreementGeographicalApplic thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createAgreementGeographicalApplic(@RequestBody AgreementGeographicalApplic agreementGeographicalApplicToBeAdded) throws Exception {

		AddAgreementGeographicalApplic command = new AddAgreementGeographicalApplic(agreementGeographicalApplicToBeAdded);
		AgreementGeographicalApplic agreementGeographicalApplic = ((AgreementGeographicalApplicAdded) Scheduler.execute(command).data()).getAddedAgreementGeographicalApplic();
		
		if (agreementGeographicalApplic != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(agreementGeographicalApplic);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("AgreementGeographicalApplic could not be created.");
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
	public boolean updateAgreementGeographicalApplic(HttpServletRequest request) throws Exception {

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

		AgreementGeographicalApplic agreementGeographicalApplicToBeUpdated = new AgreementGeographicalApplic();

		try {
			agreementGeographicalApplicToBeUpdated = AgreementGeographicalApplicMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateAgreementGeographicalApplic(agreementGeographicalApplicToBeUpdated, agreementGeographicalApplicToBeUpdated.getAgreementItemSeqId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the AgreementGeographicalApplic with the specific Id
	 * 
	 * @param agreementGeographicalApplicToBeUpdated
	 *            the AgreementGeographicalApplic thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{agreementItemSeqId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateAgreementGeographicalApplic(@RequestBody AgreementGeographicalApplic agreementGeographicalApplicToBeUpdated,
			@PathVariable String agreementItemSeqId) throws Exception {

		agreementGeographicalApplicToBeUpdated.setAgreementItemSeqId(agreementItemSeqId);

		UpdateAgreementGeographicalApplic command = new UpdateAgreementGeographicalApplic(agreementGeographicalApplicToBeUpdated);

		try {
			if(((AgreementGeographicalApplicUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{agreementGeographicalApplicId}")
	public ResponseEntity<Object> findById(@PathVariable String agreementGeographicalApplicId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("agreementGeographicalApplicId", agreementGeographicalApplicId);
		try {

			Object foundAgreementGeographicalApplic = findAgreementGeographicalApplicsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundAgreementGeographicalApplic);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{agreementGeographicalApplicId}")
	public ResponseEntity<Object> deleteAgreementGeographicalApplicByIdUpdated(@PathVariable String agreementGeographicalApplicId) throws Exception {
		DeleteAgreementGeographicalApplic command = new DeleteAgreementGeographicalApplic(agreementGeographicalApplicId);

		try {
			if (((AgreementGeographicalApplicDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("AgreementGeographicalApplic could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/agreementGeographicalApplic/\" plus one of the following: "
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
