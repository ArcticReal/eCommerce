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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/party/agreement/agreementWorkEffortApplics")
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
	@GetMapping("/find")
	public ResponseEntity<List<AgreementWorkEffortApplic>> findAgreementWorkEffortApplicsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindAgreementWorkEffortApplicsBy query = new FindAgreementWorkEffortApplicsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<AgreementWorkEffortApplic> agreementWorkEffortApplics =((AgreementWorkEffortApplicFound) Scheduler.execute(query).data()).getAgreementWorkEffortApplics();

		return ResponseEntity.ok().body(agreementWorkEffortApplics);

	}

	/**
	 * creates a new AgreementWorkEffortApplic entry in the ofbiz database
	 * 
	 * @param agreementWorkEffortApplicToBeAdded
	 *            the AgreementWorkEffortApplic thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<AgreementWorkEffortApplic> createAgreementWorkEffortApplic(@RequestBody AgreementWorkEffortApplic agreementWorkEffortApplicToBeAdded) throws Exception {

		AddAgreementWorkEffortApplic command = new AddAgreementWorkEffortApplic(agreementWorkEffortApplicToBeAdded);
		AgreementWorkEffortApplic agreementWorkEffortApplic = ((AgreementWorkEffortApplicAdded) Scheduler.execute(command).data()).getAddedAgreementWorkEffortApplic();
		
		if (agreementWorkEffortApplic != null) 
			return successful(agreementWorkEffortApplic);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateAgreementWorkEffortApplic(@RequestBody AgreementWorkEffortApplic agreementWorkEffortApplicToBeUpdated,
			@PathVariable String agreementItemSeqId) throws Exception {

		agreementWorkEffortApplicToBeUpdated.setAgreementItemSeqId(agreementItemSeqId);

		UpdateAgreementWorkEffortApplic command = new UpdateAgreementWorkEffortApplic(agreementWorkEffortApplicToBeUpdated);

		try {
			if(((AgreementWorkEffortApplicUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{agreementWorkEffortApplicId}")
	public ResponseEntity<AgreementWorkEffortApplic> findById(@PathVariable String agreementWorkEffortApplicId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("agreementWorkEffortApplicId", agreementWorkEffortApplicId);
		try {

			List<AgreementWorkEffortApplic> foundAgreementWorkEffortApplic = findAgreementWorkEffortApplicsBy(requestParams).getBody();
			if(foundAgreementWorkEffortApplic.size()==1){				return successful(foundAgreementWorkEffortApplic.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{agreementWorkEffortApplicId}")
	public ResponseEntity<String> deleteAgreementWorkEffortApplicByIdUpdated(@PathVariable String agreementWorkEffortApplicId) throws Exception {
		DeleteAgreementWorkEffortApplic command = new DeleteAgreementWorkEffortApplic(agreementWorkEffortApplicId);

		try {
			if (((AgreementWorkEffortApplicDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
