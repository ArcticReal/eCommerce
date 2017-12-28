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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/party/agreements")
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
	@GetMapping("/find")
	public ResponseEntity<List<Agreement>> findAgreementsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindAgreementsBy query = new FindAgreementsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<Agreement> agreements =((AgreementFound) Scheduler.execute(query).data()).getAgreements();

		return ResponseEntity.ok().body(agreements);

	}

	/**
	 * creates a new Agreement entry in the ofbiz database
	 * 
	 * @param agreementToBeAdded
	 *            the Agreement thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Agreement> createAgreement(@RequestBody Agreement agreementToBeAdded) throws Exception {

		AddAgreement command = new AddAgreement(agreementToBeAdded);
		Agreement agreement = ((AgreementAdded) Scheduler.execute(command).data()).getAddedAgreement();
		
		if (agreement != null) 
			return successful(agreement);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateAgreement(@RequestBody Agreement agreementToBeUpdated,
			@PathVariable String agreementId) throws Exception {

		agreementToBeUpdated.setAgreementId(agreementId);

		UpdateAgreement command = new UpdateAgreement(agreementToBeUpdated);

		try {
			if(((AgreementUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{agreementId}")
	public ResponseEntity<Agreement> findById(@PathVariable String agreementId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("agreementId", agreementId);
		try {

			List<Agreement> foundAgreement = findAgreementsBy(requestParams).getBody();
			if(foundAgreement.size()==1){				return successful(foundAgreement.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{agreementId}")
	public ResponseEntity<String> deleteAgreementByIdUpdated(@PathVariable String agreementId) throws Exception {
		DeleteAgreement command = new DeleteAgreement(agreementId);

		try {
			if (((AgreementDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
