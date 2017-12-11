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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findAgreementEmploymentApplsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindAgreementEmploymentApplsBy query = new FindAgreementEmploymentApplsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<AgreementEmploymentAppl> agreementEmploymentAppls =((AgreementEmploymentApplFound) Scheduler.execute(query).data()).getAgreementEmploymentAppls();

		if (agreementEmploymentAppls.size() == 1) {
			return ResponseEntity.ok().body(agreementEmploymentAppls.get(0));
		}

		return ResponseEntity.ok().body(agreementEmploymentAppls);

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
	public ResponseEntity<Object> createAgreementEmploymentAppl(HttpServletRequest request) throws Exception {

		AgreementEmploymentAppl agreementEmploymentApplToBeAdded = new AgreementEmploymentAppl();
		try {
			agreementEmploymentApplToBeAdded = AgreementEmploymentApplMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createAgreementEmploymentAppl(agreementEmploymentApplToBeAdded);

	}

	/**
	 * creates a new AgreementEmploymentAppl entry in the ofbiz database
	 * 
	 * @param agreementEmploymentApplToBeAdded
	 *            the AgreementEmploymentAppl thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createAgreementEmploymentAppl(@RequestBody AgreementEmploymentAppl agreementEmploymentApplToBeAdded) throws Exception {

		AddAgreementEmploymentAppl command = new AddAgreementEmploymentAppl(agreementEmploymentApplToBeAdded);
		AgreementEmploymentAppl agreementEmploymentAppl = ((AgreementEmploymentApplAdded) Scheduler.execute(command).data()).getAddedAgreementEmploymentAppl();
		
		if (agreementEmploymentAppl != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(agreementEmploymentAppl);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("AgreementEmploymentAppl could not be created.");
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
	public boolean updateAgreementEmploymentAppl(HttpServletRequest request) throws Exception {

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

		AgreementEmploymentAppl agreementEmploymentApplToBeUpdated = new AgreementEmploymentAppl();

		try {
			agreementEmploymentApplToBeUpdated = AgreementEmploymentApplMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateAgreementEmploymentAppl(agreementEmploymentApplToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateAgreementEmploymentAppl(@RequestBody AgreementEmploymentAppl agreementEmploymentApplToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		agreementEmploymentApplToBeUpdated.setnull(null);

		UpdateAgreementEmploymentAppl command = new UpdateAgreementEmploymentAppl(agreementEmploymentApplToBeUpdated);

		try {
			if(((AgreementEmploymentApplUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{agreementEmploymentApplId}")
	public ResponseEntity<Object> findById(@PathVariable String agreementEmploymentApplId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("agreementEmploymentApplId", agreementEmploymentApplId);
		try {

			Object foundAgreementEmploymentAppl = findAgreementEmploymentApplsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundAgreementEmploymentAppl);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{agreementEmploymentApplId}")
	public ResponseEntity<Object> deleteAgreementEmploymentApplByIdUpdated(@PathVariable String agreementEmploymentApplId) throws Exception {
		DeleteAgreementEmploymentAppl command = new DeleteAgreementEmploymentAppl(agreementEmploymentApplId);

		try {
			if (((AgreementEmploymentApplDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("AgreementEmploymentAppl could not be deleted");

	}

}
