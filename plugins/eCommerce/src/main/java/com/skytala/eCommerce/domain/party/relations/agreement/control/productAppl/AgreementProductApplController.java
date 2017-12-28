package com.skytala.eCommerce.domain.party.relations.agreement.control.productAppl;

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
import com.skytala.eCommerce.domain.party.relations.agreement.command.productAppl.AddAgreementProductAppl;
import com.skytala.eCommerce.domain.party.relations.agreement.command.productAppl.DeleteAgreementProductAppl;
import com.skytala.eCommerce.domain.party.relations.agreement.command.productAppl.UpdateAgreementProductAppl;
import com.skytala.eCommerce.domain.party.relations.agreement.event.productAppl.AgreementProductApplAdded;
import com.skytala.eCommerce.domain.party.relations.agreement.event.productAppl.AgreementProductApplDeleted;
import com.skytala.eCommerce.domain.party.relations.agreement.event.productAppl.AgreementProductApplFound;
import com.skytala.eCommerce.domain.party.relations.agreement.event.productAppl.AgreementProductApplUpdated;
import com.skytala.eCommerce.domain.party.relations.agreement.mapper.productAppl.AgreementProductApplMapper;
import com.skytala.eCommerce.domain.party.relations.agreement.model.productAppl.AgreementProductAppl;
import com.skytala.eCommerce.domain.party.relations.agreement.query.productAppl.FindAgreementProductApplsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/party/agreement/agreementProductAppls")
public class AgreementProductApplController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public AgreementProductApplController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a AgreementProductAppl
	 * @return a List with the AgreementProductAppls
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<AgreementProductAppl>> findAgreementProductApplsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindAgreementProductApplsBy query = new FindAgreementProductApplsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<AgreementProductAppl> agreementProductAppls =((AgreementProductApplFound) Scheduler.execute(query).data()).getAgreementProductAppls();

		return ResponseEntity.ok().body(agreementProductAppls);

	}

	/**
	 * creates a new AgreementProductAppl entry in the ofbiz database
	 * 
	 * @param agreementProductApplToBeAdded
	 *            the AgreementProductAppl thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<AgreementProductAppl> createAgreementProductAppl(@RequestBody AgreementProductAppl agreementProductApplToBeAdded) throws Exception {

		AddAgreementProductAppl command = new AddAgreementProductAppl(agreementProductApplToBeAdded);
		AgreementProductAppl agreementProductAppl = ((AgreementProductApplAdded) Scheduler.execute(command).data()).getAddedAgreementProductAppl();
		
		if (agreementProductAppl != null) 
			return successful(agreementProductAppl);
		else 
			return conflict(null);
	}

	/**
	 * Updates the AgreementProductAppl with the specific Id
	 * 
	 * @param agreementProductApplToBeUpdated
	 *            the AgreementProductAppl thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{agreementItemSeqId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateAgreementProductAppl(@RequestBody AgreementProductAppl agreementProductApplToBeUpdated,
			@PathVariable String agreementItemSeqId) throws Exception {

		agreementProductApplToBeUpdated.setAgreementItemSeqId(agreementItemSeqId);

		UpdateAgreementProductAppl command = new UpdateAgreementProductAppl(agreementProductApplToBeUpdated);

		try {
			if(((AgreementProductApplUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{agreementProductApplId}")
	public ResponseEntity<AgreementProductAppl> findById(@PathVariable String agreementProductApplId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("agreementProductApplId", agreementProductApplId);
		try {

			List<AgreementProductAppl> foundAgreementProductAppl = findAgreementProductApplsBy(requestParams).getBody();
			if(foundAgreementProductAppl.size()==1){				return successful(foundAgreementProductAppl.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{agreementProductApplId}")
	public ResponseEntity<String> deleteAgreementProductApplByIdUpdated(@PathVariable String agreementProductApplId) throws Exception {
		DeleteAgreementProductAppl command = new DeleteAgreementProductAppl(agreementProductApplId);

		try {
			if (((AgreementProductApplDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
