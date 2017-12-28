package com.skytala.eCommerce.domain.party.relations.agreement.control.term;

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
import com.skytala.eCommerce.domain.party.relations.agreement.command.term.AddAgreementTerm;
import com.skytala.eCommerce.domain.party.relations.agreement.command.term.DeleteAgreementTerm;
import com.skytala.eCommerce.domain.party.relations.agreement.command.term.UpdateAgreementTerm;
import com.skytala.eCommerce.domain.party.relations.agreement.event.term.AgreementTermAdded;
import com.skytala.eCommerce.domain.party.relations.agreement.event.term.AgreementTermDeleted;
import com.skytala.eCommerce.domain.party.relations.agreement.event.term.AgreementTermFound;
import com.skytala.eCommerce.domain.party.relations.agreement.event.term.AgreementTermUpdated;
import com.skytala.eCommerce.domain.party.relations.agreement.mapper.term.AgreementTermMapper;
import com.skytala.eCommerce.domain.party.relations.agreement.model.term.AgreementTerm;
import com.skytala.eCommerce.domain.party.relations.agreement.query.term.FindAgreementTermsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/party/agreement/agreementTerms")
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
	@GetMapping("/find")
	public ResponseEntity<List<AgreementTerm>> findAgreementTermsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindAgreementTermsBy query = new FindAgreementTermsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<AgreementTerm> agreementTerms =((AgreementTermFound) Scheduler.execute(query).data()).getAgreementTerms();

		return ResponseEntity.ok().body(agreementTerms);

	}

	/**
	 * creates a new AgreementTerm entry in the ofbiz database
	 * 
	 * @param agreementTermToBeAdded
	 *            the AgreementTerm thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<AgreementTerm> createAgreementTerm(@RequestBody AgreementTerm agreementTermToBeAdded) throws Exception {

		AddAgreementTerm command = new AddAgreementTerm(agreementTermToBeAdded);
		AgreementTerm agreementTerm = ((AgreementTermAdded) Scheduler.execute(command).data()).getAddedAgreementTerm();
		
		if (agreementTerm != null) 
			return successful(agreementTerm);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateAgreementTerm(@RequestBody AgreementTerm agreementTermToBeUpdated,
			@PathVariable String agreementTermId) throws Exception {

		agreementTermToBeUpdated.setAgreementTermId(agreementTermId);

		UpdateAgreementTerm command = new UpdateAgreementTerm(agreementTermToBeUpdated);

		try {
			if(((AgreementTermUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{agreementTermId}")
	public ResponseEntity<AgreementTerm> findById(@PathVariable String agreementTermId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("agreementTermId", agreementTermId);
		try {

			List<AgreementTerm> foundAgreementTerm = findAgreementTermsBy(requestParams).getBody();
			if(foundAgreementTerm.size()==1){				return successful(foundAgreementTerm.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{agreementTermId}")
	public ResponseEntity<String> deleteAgreementTermByIdUpdated(@PathVariable String agreementTermId) throws Exception {
		DeleteAgreementTerm command = new DeleteAgreementTerm(agreementTermId);

		try {
			if (((AgreementTermDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
