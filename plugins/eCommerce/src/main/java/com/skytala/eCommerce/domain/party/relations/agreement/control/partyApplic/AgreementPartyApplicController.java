package com.skytala.eCommerce.domain.party.relations.agreement.control.partyApplic;

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
import com.skytala.eCommerce.domain.party.relations.agreement.command.partyApplic.AddAgreementPartyApplic;
import com.skytala.eCommerce.domain.party.relations.agreement.command.partyApplic.DeleteAgreementPartyApplic;
import com.skytala.eCommerce.domain.party.relations.agreement.command.partyApplic.UpdateAgreementPartyApplic;
import com.skytala.eCommerce.domain.party.relations.agreement.event.partyApplic.AgreementPartyApplicAdded;
import com.skytala.eCommerce.domain.party.relations.agreement.event.partyApplic.AgreementPartyApplicDeleted;
import com.skytala.eCommerce.domain.party.relations.agreement.event.partyApplic.AgreementPartyApplicFound;
import com.skytala.eCommerce.domain.party.relations.agreement.event.partyApplic.AgreementPartyApplicUpdated;
import com.skytala.eCommerce.domain.party.relations.agreement.mapper.partyApplic.AgreementPartyApplicMapper;
import com.skytala.eCommerce.domain.party.relations.agreement.model.partyApplic.AgreementPartyApplic;
import com.skytala.eCommerce.domain.party.relations.agreement.query.partyApplic.FindAgreementPartyApplicsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/party/agreement/agreementPartyApplics")
public class AgreementPartyApplicController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public AgreementPartyApplicController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a AgreementPartyApplic
	 * @return a List with the AgreementPartyApplics
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findAgreementPartyApplicsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindAgreementPartyApplicsBy query = new FindAgreementPartyApplicsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<AgreementPartyApplic> agreementPartyApplics =((AgreementPartyApplicFound) Scheduler.execute(query).data()).getAgreementPartyApplics();

		if (agreementPartyApplics.size() == 1) {
			return ResponseEntity.ok().body(agreementPartyApplics.get(0));
		}

		return ResponseEntity.ok().body(agreementPartyApplics);

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
	public ResponseEntity<Object> createAgreementPartyApplic(HttpServletRequest request) throws Exception {

		AgreementPartyApplic agreementPartyApplicToBeAdded = new AgreementPartyApplic();
		try {
			agreementPartyApplicToBeAdded = AgreementPartyApplicMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createAgreementPartyApplic(agreementPartyApplicToBeAdded);

	}

	/**
	 * creates a new AgreementPartyApplic entry in the ofbiz database
	 * 
	 * @param agreementPartyApplicToBeAdded
	 *            the AgreementPartyApplic thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createAgreementPartyApplic(@RequestBody AgreementPartyApplic agreementPartyApplicToBeAdded) throws Exception {

		AddAgreementPartyApplic command = new AddAgreementPartyApplic(agreementPartyApplicToBeAdded);
		AgreementPartyApplic agreementPartyApplic = ((AgreementPartyApplicAdded) Scheduler.execute(command).data()).getAddedAgreementPartyApplic();
		
		if (agreementPartyApplic != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(agreementPartyApplic);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("AgreementPartyApplic could not be created.");
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
	public boolean updateAgreementPartyApplic(HttpServletRequest request) throws Exception {

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

		AgreementPartyApplic agreementPartyApplicToBeUpdated = new AgreementPartyApplic();

		try {
			agreementPartyApplicToBeUpdated = AgreementPartyApplicMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateAgreementPartyApplic(agreementPartyApplicToBeUpdated, agreementPartyApplicToBeUpdated.getAgreementItemSeqId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the AgreementPartyApplic with the specific Id
	 * 
	 * @param agreementPartyApplicToBeUpdated
	 *            the AgreementPartyApplic thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{agreementItemSeqId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateAgreementPartyApplic(@RequestBody AgreementPartyApplic agreementPartyApplicToBeUpdated,
			@PathVariable String agreementItemSeqId) throws Exception {

		agreementPartyApplicToBeUpdated.setAgreementItemSeqId(agreementItemSeqId);

		UpdateAgreementPartyApplic command = new UpdateAgreementPartyApplic(agreementPartyApplicToBeUpdated);

		try {
			if(((AgreementPartyApplicUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{agreementPartyApplicId}")
	public ResponseEntity<Object> findById(@PathVariable String agreementPartyApplicId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("agreementPartyApplicId", agreementPartyApplicId);
		try {

			Object foundAgreementPartyApplic = findAgreementPartyApplicsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundAgreementPartyApplic);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{agreementPartyApplicId}")
	public ResponseEntity<Object> deleteAgreementPartyApplicByIdUpdated(@PathVariable String agreementPartyApplicId) throws Exception {
		DeleteAgreementPartyApplic command = new DeleteAgreementPartyApplic(agreementPartyApplicId);

		try {
			if (((AgreementPartyApplicDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("AgreementPartyApplic could not be deleted");

	}

}
