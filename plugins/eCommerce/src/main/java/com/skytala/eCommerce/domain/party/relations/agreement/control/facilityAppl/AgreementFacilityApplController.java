package com.skytala.eCommerce.domain.party.relations.agreement.control.facilityAppl;

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
import com.skytala.eCommerce.domain.party.relations.agreement.command.facilityAppl.AddAgreementFacilityAppl;
import com.skytala.eCommerce.domain.party.relations.agreement.command.facilityAppl.DeleteAgreementFacilityAppl;
import com.skytala.eCommerce.domain.party.relations.agreement.command.facilityAppl.UpdateAgreementFacilityAppl;
import com.skytala.eCommerce.domain.party.relations.agreement.event.facilityAppl.AgreementFacilityApplAdded;
import com.skytala.eCommerce.domain.party.relations.agreement.event.facilityAppl.AgreementFacilityApplDeleted;
import com.skytala.eCommerce.domain.party.relations.agreement.event.facilityAppl.AgreementFacilityApplFound;
import com.skytala.eCommerce.domain.party.relations.agreement.event.facilityAppl.AgreementFacilityApplUpdated;
import com.skytala.eCommerce.domain.party.relations.agreement.mapper.facilityAppl.AgreementFacilityApplMapper;
import com.skytala.eCommerce.domain.party.relations.agreement.model.facilityAppl.AgreementFacilityAppl;
import com.skytala.eCommerce.domain.party.relations.agreement.query.facilityAppl.FindAgreementFacilityApplsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/party/agreement/agreementFacilityAppls")
public class AgreementFacilityApplController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public AgreementFacilityApplController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a AgreementFacilityAppl
	 * @return a List with the AgreementFacilityAppls
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<AgreementFacilityAppl>> findAgreementFacilityApplsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindAgreementFacilityApplsBy query = new FindAgreementFacilityApplsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<AgreementFacilityAppl> agreementFacilityAppls =((AgreementFacilityApplFound) Scheduler.execute(query).data()).getAgreementFacilityAppls();

		return ResponseEntity.ok().body(agreementFacilityAppls);

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
	public ResponseEntity<AgreementFacilityAppl> createAgreementFacilityAppl(HttpServletRequest request) throws Exception {

		AgreementFacilityAppl agreementFacilityApplToBeAdded = new AgreementFacilityAppl();
		try {
			agreementFacilityApplToBeAdded = AgreementFacilityApplMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createAgreementFacilityAppl(agreementFacilityApplToBeAdded);

	}

	/**
	 * creates a new AgreementFacilityAppl entry in the ofbiz database
	 * 
	 * @param agreementFacilityApplToBeAdded
	 *            the AgreementFacilityAppl thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<AgreementFacilityAppl> createAgreementFacilityAppl(@RequestBody AgreementFacilityAppl agreementFacilityApplToBeAdded) throws Exception {

		AddAgreementFacilityAppl command = new AddAgreementFacilityAppl(agreementFacilityApplToBeAdded);
		AgreementFacilityAppl agreementFacilityAppl = ((AgreementFacilityApplAdded) Scheduler.execute(command).data()).getAddedAgreementFacilityAppl();
		
		if (agreementFacilityAppl != null) 
			return successful(agreementFacilityAppl);
		else 
			return conflict(null);
	}

	/**
	 * Updates the AgreementFacilityAppl with the specific Id
	 * 
	 * @param agreementFacilityApplToBeUpdated
	 *            the AgreementFacilityAppl thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{agreementItemSeqId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateAgreementFacilityAppl(@RequestBody AgreementFacilityAppl agreementFacilityApplToBeUpdated,
			@PathVariable String agreementItemSeqId) throws Exception {

		agreementFacilityApplToBeUpdated.setAgreementItemSeqId(agreementItemSeqId);

		UpdateAgreementFacilityAppl command = new UpdateAgreementFacilityAppl(agreementFacilityApplToBeUpdated);

		try {
			if(((AgreementFacilityApplUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{agreementFacilityApplId}")
	public ResponseEntity<AgreementFacilityAppl> findById(@PathVariable String agreementFacilityApplId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("agreementFacilityApplId", agreementFacilityApplId);
		try {

			List<AgreementFacilityAppl> foundAgreementFacilityAppl = findAgreementFacilityApplsBy(requestParams).getBody();
			if(foundAgreementFacilityAppl.size()==1){				return successful(foundAgreementFacilityAppl.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{agreementFacilityApplId}")
	public ResponseEntity<String> deleteAgreementFacilityApplByIdUpdated(@PathVariable String agreementFacilityApplId) throws Exception {
		DeleteAgreementFacilityAppl command = new DeleteAgreementFacilityAppl(agreementFacilityApplId);

		try {
			if (((AgreementFacilityApplDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
