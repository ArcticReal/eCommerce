package com.skytala.eCommerce.domain.party.relations.agreement.control.typeAttr;

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
import com.skytala.eCommerce.domain.party.relations.agreement.command.typeAttr.AddAgreementTypeAttr;
import com.skytala.eCommerce.domain.party.relations.agreement.command.typeAttr.DeleteAgreementTypeAttr;
import com.skytala.eCommerce.domain.party.relations.agreement.command.typeAttr.UpdateAgreementTypeAttr;
import com.skytala.eCommerce.domain.party.relations.agreement.event.typeAttr.AgreementTypeAttrAdded;
import com.skytala.eCommerce.domain.party.relations.agreement.event.typeAttr.AgreementTypeAttrDeleted;
import com.skytala.eCommerce.domain.party.relations.agreement.event.typeAttr.AgreementTypeAttrFound;
import com.skytala.eCommerce.domain.party.relations.agreement.event.typeAttr.AgreementTypeAttrUpdated;
import com.skytala.eCommerce.domain.party.relations.agreement.mapper.typeAttr.AgreementTypeAttrMapper;
import com.skytala.eCommerce.domain.party.relations.agreement.model.typeAttr.AgreementTypeAttr;
import com.skytala.eCommerce.domain.party.relations.agreement.query.typeAttr.FindAgreementTypeAttrsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/party/agreement/agreementTypeAttrs")
public class AgreementTypeAttrController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public AgreementTypeAttrController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a AgreementTypeAttr
	 * @return a List with the AgreementTypeAttrs
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findAgreementTypeAttrsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindAgreementTypeAttrsBy query = new FindAgreementTypeAttrsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<AgreementTypeAttr> agreementTypeAttrs =((AgreementTypeAttrFound) Scheduler.execute(query).data()).getAgreementTypeAttrs();

		if (agreementTypeAttrs.size() == 1) {
			return ResponseEntity.ok().body(agreementTypeAttrs.get(0));
		}

		return ResponseEntity.ok().body(agreementTypeAttrs);

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
	public ResponseEntity<Object> createAgreementTypeAttr(HttpServletRequest request) throws Exception {

		AgreementTypeAttr agreementTypeAttrToBeAdded = new AgreementTypeAttr();
		try {
			agreementTypeAttrToBeAdded = AgreementTypeAttrMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createAgreementTypeAttr(agreementTypeAttrToBeAdded);

	}

	/**
	 * creates a new AgreementTypeAttr entry in the ofbiz database
	 * 
	 * @param agreementTypeAttrToBeAdded
	 *            the AgreementTypeAttr thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createAgreementTypeAttr(@RequestBody AgreementTypeAttr agreementTypeAttrToBeAdded) throws Exception {

		AddAgreementTypeAttr command = new AddAgreementTypeAttr(agreementTypeAttrToBeAdded);
		AgreementTypeAttr agreementTypeAttr = ((AgreementTypeAttrAdded) Scheduler.execute(command).data()).getAddedAgreementTypeAttr();
		
		if (agreementTypeAttr != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(agreementTypeAttr);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("AgreementTypeAttr could not be created.");
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
	public boolean updateAgreementTypeAttr(HttpServletRequest request) throws Exception {

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

		AgreementTypeAttr agreementTypeAttrToBeUpdated = new AgreementTypeAttr();

		try {
			agreementTypeAttrToBeUpdated = AgreementTypeAttrMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateAgreementTypeAttr(agreementTypeAttrToBeUpdated, agreementTypeAttrToBeUpdated.getAttrName()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the AgreementTypeAttr with the specific Id
	 * 
	 * @param agreementTypeAttrToBeUpdated
	 *            the AgreementTypeAttr thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{attrName}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateAgreementTypeAttr(@RequestBody AgreementTypeAttr agreementTypeAttrToBeUpdated,
			@PathVariable String attrName) throws Exception {

		agreementTypeAttrToBeUpdated.setAttrName(attrName);

		UpdateAgreementTypeAttr command = new UpdateAgreementTypeAttr(agreementTypeAttrToBeUpdated);

		try {
			if(((AgreementTypeAttrUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{agreementTypeAttrId}")
	public ResponseEntity<Object> findById(@PathVariable String agreementTypeAttrId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("agreementTypeAttrId", agreementTypeAttrId);
		try {

			Object foundAgreementTypeAttr = findAgreementTypeAttrsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundAgreementTypeAttr);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{agreementTypeAttrId}")
	public ResponseEntity<Object> deleteAgreementTypeAttrByIdUpdated(@PathVariable String agreementTypeAttrId) throws Exception {
		DeleteAgreementTypeAttr command = new DeleteAgreementTypeAttr(agreementTypeAttrId);

		try {
			if (((AgreementTypeAttrDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("AgreementTypeAttr could not be deleted");

	}

}
