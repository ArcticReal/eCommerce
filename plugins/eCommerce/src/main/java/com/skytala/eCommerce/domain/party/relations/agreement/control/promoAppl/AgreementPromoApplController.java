package com.skytala.eCommerce.domain.party.relations.agreement.control.promoAppl;

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
import com.skytala.eCommerce.domain.party.relations.agreement.command.promoAppl.AddAgreementPromoAppl;
import com.skytala.eCommerce.domain.party.relations.agreement.command.promoAppl.DeleteAgreementPromoAppl;
import com.skytala.eCommerce.domain.party.relations.agreement.command.promoAppl.UpdateAgreementPromoAppl;
import com.skytala.eCommerce.domain.party.relations.agreement.event.promoAppl.AgreementPromoApplAdded;
import com.skytala.eCommerce.domain.party.relations.agreement.event.promoAppl.AgreementPromoApplDeleted;
import com.skytala.eCommerce.domain.party.relations.agreement.event.promoAppl.AgreementPromoApplFound;
import com.skytala.eCommerce.domain.party.relations.agreement.event.promoAppl.AgreementPromoApplUpdated;
import com.skytala.eCommerce.domain.party.relations.agreement.mapper.promoAppl.AgreementPromoApplMapper;
import com.skytala.eCommerce.domain.party.relations.agreement.model.promoAppl.AgreementPromoAppl;
import com.skytala.eCommerce.domain.party.relations.agreement.query.promoAppl.FindAgreementPromoApplsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/party/agreement/agreementPromoAppls")
public class AgreementPromoApplController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public AgreementPromoApplController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a AgreementPromoAppl
	 * @return a List with the AgreementPromoAppls
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<AgreementPromoAppl>> findAgreementPromoApplsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindAgreementPromoApplsBy query = new FindAgreementPromoApplsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<AgreementPromoAppl> agreementPromoAppls =((AgreementPromoApplFound) Scheduler.execute(query).data()).getAgreementPromoAppls();

		return ResponseEntity.ok().body(agreementPromoAppls);

	}

	/**
	 * creates a new AgreementPromoAppl entry in the ofbiz database
	 * 
	 * @param agreementPromoApplToBeAdded
	 *            the AgreementPromoAppl thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<AgreementPromoAppl> createAgreementPromoAppl(@RequestBody AgreementPromoAppl agreementPromoApplToBeAdded) throws Exception {

		AddAgreementPromoAppl command = new AddAgreementPromoAppl(agreementPromoApplToBeAdded);
		AgreementPromoAppl agreementPromoAppl = ((AgreementPromoApplAdded) Scheduler.execute(command).data()).getAddedAgreementPromoAppl();
		
		if (agreementPromoAppl != null) 
			return successful(agreementPromoAppl);
		else 
			return conflict(null);
	}

	/**
	 * Updates the AgreementPromoAppl with the specific Id
	 * 
	 * @param agreementPromoApplToBeUpdated
	 *            the AgreementPromoAppl thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{agreementItemSeqId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateAgreementPromoAppl(@RequestBody AgreementPromoAppl agreementPromoApplToBeUpdated,
			@PathVariable String agreementItemSeqId) throws Exception {

		agreementPromoApplToBeUpdated.setAgreementItemSeqId(agreementItemSeqId);

		UpdateAgreementPromoAppl command = new UpdateAgreementPromoAppl(agreementPromoApplToBeUpdated);

		try {
			if(((AgreementPromoApplUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{agreementPromoApplId}")
	public ResponseEntity<AgreementPromoAppl> findById(@PathVariable String agreementPromoApplId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("agreementPromoApplId", agreementPromoApplId);
		try {

			List<AgreementPromoAppl> foundAgreementPromoAppl = findAgreementPromoApplsBy(requestParams).getBody();
			if(foundAgreementPromoAppl.size()==1){				return successful(foundAgreementPromoAppl.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{agreementPromoApplId}")
	public ResponseEntity<String> deleteAgreementPromoApplByIdUpdated(@PathVariable String agreementPromoApplId) throws Exception {
		DeleteAgreementPromoAppl command = new DeleteAgreementPromoAppl(agreementPromoApplId);

		try {
			if (((AgreementPromoApplDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
