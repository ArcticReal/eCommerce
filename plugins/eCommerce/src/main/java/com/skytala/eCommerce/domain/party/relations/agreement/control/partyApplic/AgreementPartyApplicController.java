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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	@GetMapping("/find")
	public ResponseEntity<List<AgreementPartyApplic>> findAgreementPartyApplicsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindAgreementPartyApplicsBy query = new FindAgreementPartyApplicsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<AgreementPartyApplic> agreementPartyApplics =((AgreementPartyApplicFound) Scheduler.execute(query).data()).getAgreementPartyApplics();

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
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<AgreementPartyApplic> createAgreementPartyApplic(HttpServletRequest request) throws Exception {

		AgreementPartyApplic agreementPartyApplicToBeAdded = new AgreementPartyApplic();
		try {
			agreementPartyApplicToBeAdded = AgreementPartyApplicMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
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
	public ResponseEntity<AgreementPartyApplic> createAgreementPartyApplic(@RequestBody AgreementPartyApplic agreementPartyApplicToBeAdded) throws Exception {

		AddAgreementPartyApplic command = new AddAgreementPartyApplic(agreementPartyApplicToBeAdded);
		AgreementPartyApplic agreementPartyApplic = ((AgreementPartyApplicAdded) Scheduler.execute(command).data()).getAddedAgreementPartyApplic();
		
		if (agreementPartyApplic != null) 
			return successful(agreementPartyApplic);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateAgreementPartyApplic(@RequestBody AgreementPartyApplic agreementPartyApplicToBeUpdated,
			@PathVariable String agreementItemSeqId) throws Exception {

		agreementPartyApplicToBeUpdated.setAgreementItemSeqId(agreementItemSeqId);

		UpdateAgreementPartyApplic command = new UpdateAgreementPartyApplic(agreementPartyApplicToBeUpdated);

		try {
			if(((AgreementPartyApplicUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{agreementPartyApplicId}")
	public ResponseEntity<AgreementPartyApplic> findById(@PathVariable String agreementPartyApplicId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("agreementPartyApplicId", agreementPartyApplicId);
		try {

			List<AgreementPartyApplic> foundAgreementPartyApplic = findAgreementPartyApplicsBy(requestParams).getBody();
			if(foundAgreementPartyApplic.size()==1){				return successful(foundAgreementPartyApplic.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{agreementPartyApplicId}")
	public ResponseEntity<String> deleteAgreementPartyApplicByIdUpdated(@PathVariable String agreementPartyApplicId) throws Exception {
		DeleteAgreementPartyApplic command = new DeleteAgreementPartyApplic(agreementPartyApplicId);

		try {
			if (((AgreementPartyApplicDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
