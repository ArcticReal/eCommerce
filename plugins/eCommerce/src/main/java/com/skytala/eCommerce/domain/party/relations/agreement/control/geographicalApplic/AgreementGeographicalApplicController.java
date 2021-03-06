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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/party/agreement/agreementGeographicalApplics")
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
	@GetMapping("/find")
	public ResponseEntity<List<AgreementGeographicalApplic>> findAgreementGeographicalApplicsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindAgreementGeographicalApplicsBy query = new FindAgreementGeographicalApplicsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<AgreementGeographicalApplic> agreementGeographicalApplics =((AgreementGeographicalApplicFound) Scheduler.execute(query).data()).getAgreementGeographicalApplics();

		return ResponseEntity.ok().body(agreementGeographicalApplics);

	}

	/**
	 * creates a new AgreementGeographicalApplic entry in the ofbiz database
	 * 
	 * @param agreementGeographicalApplicToBeAdded
	 *            the AgreementGeographicalApplic thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<AgreementGeographicalApplic> createAgreementGeographicalApplic(@RequestBody AgreementGeographicalApplic agreementGeographicalApplicToBeAdded) throws Exception {

		AddAgreementGeographicalApplic command = new AddAgreementGeographicalApplic(agreementGeographicalApplicToBeAdded);
		AgreementGeographicalApplic agreementGeographicalApplic = ((AgreementGeographicalApplicAdded) Scheduler.execute(command).data()).getAddedAgreementGeographicalApplic();
		
		if (agreementGeographicalApplic != null) 
			return successful(agreementGeographicalApplic);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateAgreementGeographicalApplic(@RequestBody AgreementGeographicalApplic agreementGeographicalApplicToBeUpdated,
			@PathVariable String agreementItemSeqId) throws Exception {

		agreementGeographicalApplicToBeUpdated.setAgreementItemSeqId(agreementItemSeqId);

		UpdateAgreementGeographicalApplic command = new UpdateAgreementGeographicalApplic(agreementGeographicalApplicToBeUpdated);

		try {
			if(((AgreementGeographicalApplicUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{agreementGeographicalApplicId}")
	public ResponseEntity<AgreementGeographicalApplic> findById(@PathVariable String agreementGeographicalApplicId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("agreementGeographicalApplicId", agreementGeographicalApplicId);
		try {

			List<AgreementGeographicalApplic> foundAgreementGeographicalApplic = findAgreementGeographicalApplicsBy(requestParams).getBody();
			if(foundAgreementGeographicalApplic.size()==1){				return successful(foundAgreementGeographicalApplic.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{agreementGeographicalApplicId}")
	public ResponseEntity<String> deleteAgreementGeographicalApplicByIdUpdated(@PathVariable String agreementGeographicalApplicId) throws Exception {
		DeleteAgreementGeographicalApplic command = new DeleteAgreementGeographicalApplic(agreementGeographicalApplicId);

		try {
			if (((AgreementGeographicalApplicDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
