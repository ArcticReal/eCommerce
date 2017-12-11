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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findAgreementPromoApplsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindAgreementPromoApplsBy query = new FindAgreementPromoApplsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<AgreementPromoAppl> agreementPromoAppls =((AgreementPromoApplFound) Scheduler.execute(query).data()).getAgreementPromoAppls();

		if (agreementPromoAppls.size() == 1) {
			return ResponseEntity.ok().body(agreementPromoAppls.get(0));
		}

		return ResponseEntity.ok().body(agreementPromoAppls);

	}

	/**
	 * 
	 * this method will only be called by Springs DispatcherServlet
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createAgreementPromoAppl(HttpServletRequest request) throws Exception {

		AgreementPromoAppl agreementPromoApplToBeAdded = new AgreementPromoAppl();
		try {
			agreementPromoApplToBeAdded = AgreementPromoApplMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createAgreementPromoAppl(agreementPromoApplToBeAdded);

	}

	/**
	 * creates a new AgreementPromoAppl entry in the ofbiz database
	 * 
	 * @param agreementPromoApplToBeAdded
	 *            the AgreementPromoAppl thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createAgreementPromoAppl(@RequestBody AgreementPromoAppl agreementPromoApplToBeAdded) throws Exception {

		AddAgreementPromoAppl command = new AddAgreementPromoAppl(agreementPromoApplToBeAdded);
		AgreementPromoAppl agreementPromoAppl = ((AgreementPromoApplAdded) Scheduler.execute(command).data()).getAddedAgreementPromoAppl();
		
		if (agreementPromoAppl != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(agreementPromoAppl);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("AgreementPromoAppl could not be created.");
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
	@RequestMapping(method = RequestMethod.PUT, value = "/update", consumes = "application/x-www-form-urlencoded")
	public boolean updateAgreementPromoAppl(HttpServletRequest request) throws Exception {

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

		AgreementPromoAppl agreementPromoApplToBeUpdated = new AgreementPromoAppl();

		try {
			agreementPromoApplToBeUpdated = AgreementPromoApplMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateAgreementPromoAppl(agreementPromoApplToBeUpdated, agreementPromoApplToBeUpdated.getAgreementItemSeqId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateAgreementPromoAppl(@RequestBody AgreementPromoAppl agreementPromoApplToBeUpdated,
			@PathVariable String agreementItemSeqId) throws Exception {

		agreementPromoApplToBeUpdated.setAgreementItemSeqId(agreementItemSeqId);

		UpdateAgreementPromoAppl command = new UpdateAgreementPromoAppl(agreementPromoApplToBeUpdated);

		try {
			if(((AgreementPromoApplUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{agreementPromoApplId}")
	public ResponseEntity<Object> findById(@PathVariable String agreementPromoApplId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("agreementPromoApplId", agreementPromoApplId);
		try {

			Object foundAgreementPromoAppl = findAgreementPromoApplsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundAgreementPromoAppl);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{agreementPromoApplId}")
	public ResponseEntity<Object> deleteAgreementPromoApplByIdUpdated(@PathVariable String agreementPromoApplId) throws Exception {
		DeleteAgreementPromoAppl command = new DeleteAgreementPromoAppl(agreementPromoApplId);

		try {
			if (((AgreementPromoApplDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("AgreementPromoAppl could not be deleted");

	}

}
