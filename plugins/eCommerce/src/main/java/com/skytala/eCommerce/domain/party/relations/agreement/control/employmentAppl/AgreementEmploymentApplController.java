package com.skytala.eCommerce.domain.party.relations.agreement.control.employmentAppl;

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
import com.skytala.eCommerce.domain.party.relations.agreement.command.employmentAppl.AddAgreementEmploymentAppl;
import com.skytala.eCommerce.domain.party.relations.agreement.command.employmentAppl.DeleteAgreementEmploymentAppl;
import com.skytala.eCommerce.domain.party.relations.agreement.command.employmentAppl.UpdateAgreementEmploymentAppl;
import com.skytala.eCommerce.domain.party.relations.agreement.event.employmentAppl.AgreementEmploymentApplAdded;
import com.skytala.eCommerce.domain.party.relations.agreement.event.employmentAppl.AgreementEmploymentApplDeleted;
import com.skytala.eCommerce.domain.party.relations.agreement.event.employmentAppl.AgreementEmploymentApplFound;
import com.skytala.eCommerce.domain.party.relations.agreement.event.employmentAppl.AgreementEmploymentApplUpdated;
import com.skytala.eCommerce.domain.party.relations.agreement.mapper.employmentAppl.AgreementEmploymentApplMapper;
import com.skytala.eCommerce.domain.party.relations.agreement.model.employmentAppl.AgreementEmploymentAppl;
import com.skytala.eCommerce.domain.party.relations.agreement.query.employmentAppl.FindAgreementEmploymentApplsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/party/agreement/agreementEmploymentAppls")
public class AgreementEmploymentApplController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public AgreementEmploymentApplController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a AgreementEmploymentAppl
	 * @return a List with the AgreementEmploymentAppls
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<AgreementEmploymentAppl>> findAgreementEmploymentApplsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindAgreementEmploymentApplsBy query = new FindAgreementEmploymentApplsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<AgreementEmploymentAppl> agreementEmploymentAppls =((AgreementEmploymentApplFound) Scheduler.execute(query).data()).getAgreementEmploymentAppls();

		return ResponseEntity.ok().body(agreementEmploymentAppls);

	}

	/**
	 * creates a new AgreementEmploymentAppl entry in the ofbiz database
	 * 
	 * @param agreementEmploymentApplToBeAdded
	 *            the AgreementEmploymentAppl thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<AgreementEmploymentAppl> createAgreementEmploymentAppl(@RequestBody AgreementEmploymentAppl agreementEmploymentApplToBeAdded) throws Exception {

		AddAgreementEmploymentAppl command = new AddAgreementEmploymentAppl(agreementEmploymentApplToBeAdded);
		AgreementEmploymentAppl agreementEmploymentAppl = ((AgreementEmploymentApplAdded) Scheduler.execute(command).data()).getAddedAgreementEmploymentAppl();
		
		if (agreementEmploymentAppl != null) 
			return successful(agreementEmploymentAppl);
		else 
			return conflict(null);
	}

	/**
	 * Updates the AgreementEmploymentAppl with the specific Id
	 * 
	 * @param agreementEmploymentApplToBeUpdated
	 *            the AgreementEmploymentAppl thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateAgreementEmploymentAppl(@RequestBody AgreementEmploymentAppl agreementEmploymentApplToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		agreementEmploymentApplToBeUpdated.setnull(null);

		UpdateAgreementEmploymentAppl command = new UpdateAgreementEmploymentAppl(agreementEmploymentApplToBeUpdated);

		try {
			if(((AgreementEmploymentApplUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{agreementEmploymentApplId}")
	public ResponseEntity<AgreementEmploymentAppl> findById(@PathVariable String agreementEmploymentApplId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("agreementEmploymentApplId", agreementEmploymentApplId);
		try {

			List<AgreementEmploymentAppl> foundAgreementEmploymentAppl = findAgreementEmploymentApplsBy(requestParams).getBody();
			if(foundAgreementEmploymentAppl.size()==1){				return successful(foundAgreementEmploymentAppl.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{agreementEmploymentApplId}")
	public ResponseEntity<String> deleteAgreementEmploymentApplByIdUpdated(@PathVariable String agreementEmploymentApplId) throws Exception {
		DeleteAgreementEmploymentAppl command = new DeleteAgreementEmploymentAppl(agreementEmploymentApplId);

		try {
			if (((AgreementEmploymentApplDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
