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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<Object> findAgreementFacilityApplsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindAgreementFacilityApplsBy query = new FindAgreementFacilityApplsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<AgreementFacilityAppl> agreementFacilityAppls =((AgreementFacilityApplFound) Scheduler.execute(query).data()).getAgreementFacilityAppls();

		if (agreementFacilityAppls.size() == 1) {
			return ResponseEntity.ok().body(agreementFacilityAppls.get(0));
		}

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
	public ResponseEntity<Object> createAgreementFacilityAppl(HttpServletRequest request) throws Exception {

		AgreementFacilityAppl agreementFacilityApplToBeAdded = new AgreementFacilityAppl();
		try {
			agreementFacilityApplToBeAdded = AgreementFacilityApplMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
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
	public ResponseEntity<Object> createAgreementFacilityAppl(@RequestBody AgreementFacilityAppl agreementFacilityApplToBeAdded) throws Exception {

		AddAgreementFacilityAppl command = new AddAgreementFacilityAppl(agreementFacilityApplToBeAdded);
		AgreementFacilityAppl agreementFacilityAppl = ((AgreementFacilityApplAdded) Scheduler.execute(command).data()).getAddedAgreementFacilityAppl();
		
		if (agreementFacilityAppl != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(agreementFacilityAppl);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("AgreementFacilityAppl could not be created.");
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
	@PutMapping(value = "/update", consumes = "application/x-www-form-urlencoded")
	public boolean updateAgreementFacilityAppl(HttpServletRequest request) throws Exception {

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

		AgreementFacilityAppl agreementFacilityApplToBeUpdated = new AgreementFacilityAppl();

		try {
			agreementFacilityApplToBeUpdated = AgreementFacilityApplMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateAgreementFacilityAppl(agreementFacilityApplToBeUpdated, agreementFacilityApplToBeUpdated.getAgreementItemSeqId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateAgreementFacilityAppl(@RequestBody AgreementFacilityAppl agreementFacilityApplToBeUpdated,
			@PathVariable String agreementItemSeqId) throws Exception {

		agreementFacilityApplToBeUpdated.setAgreementItemSeqId(agreementItemSeqId);

		UpdateAgreementFacilityAppl command = new UpdateAgreementFacilityAppl(agreementFacilityApplToBeUpdated);

		try {
			if(((AgreementFacilityApplUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{agreementFacilityApplId}")
	public ResponseEntity<Object> findById(@PathVariable String agreementFacilityApplId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("agreementFacilityApplId", agreementFacilityApplId);
		try {

			Object foundAgreementFacilityAppl = findAgreementFacilityApplsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundAgreementFacilityAppl);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{agreementFacilityApplId}")
	public ResponseEntity<Object> deleteAgreementFacilityApplByIdUpdated(@PathVariable String agreementFacilityApplId) throws Exception {
		DeleteAgreementFacilityAppl command = new DeleteAgreementFacilityAppl(agreementFacilityApplId);

		try {
			if (((AgreementFacilityApplDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("AgreementFacilityAppl could not be deleted");

	}

}
