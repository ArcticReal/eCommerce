package com.skytala.eCommerce.domain.party.relations.agreement.control.itemTypeAttr;

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
import com.skytala.eCommerce.domain.party.relations.agreement.command.itemTypeAttr.AddAgreementItemTypeAttr;
import com.skytala.eCommerce.domain.party.relations.agreement.command.itemTypeAttr.DeleteAgreementItemTypeAttr;
import com.skytala.eCommerce.domain.party.relations.agreement.command.itemTypeAttr.UpdateAgreementItemTypeAttr;
import com.skytala.eCommerce.domain.party.relations.agreement.event.itemTypeAttr.AgreementItemTypeAttrAdded;
import com.skytala.eCommerce.domain.party.relations.agreement.event.itemTypeAttr.AgreementItemTypeAttrDeleted;
import com.skytala.eCommerce.domain.party.relations.agreement.event.itemTypeAttr.AgreementItemTypeAttrFound;
import com.skytala.eCommerce.domain.party.relations.agreement.event.itemTypeAttr.AgreementItemTypeAttrUpdated;
import com.skytala.eCommerce.domain.party.relations.agreement.mapper.itemTypeAttr.AgreementItemTypeAttrMapper;
import com.skytala.eCommerce.domain.party.relations.agreement.model.itemTypeAttr.AgreementItemTypeAttr;
import com.skytala.eCommerce.domain.party.relations.agreement.query.itemTypeAttr.FindAgreementItemTypeAttrsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/party/agreement/agreementItemTypeAttrs")
public class AgreementItemTypeAttrController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public AgreementItemTypeAttrController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a AgreementItemTypeAttr
	 * @return a List with the AgreementItemTypeAttrs
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findAgreementItemTypeAttrsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindAgreementItemTypeAttrsBy query = new FindAgreementItemTypeAttrsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<AgreementItemTypeAttr> agreementItemTypeAttrs =((AgreementItemTypeAttrFound) Scheduler.execute(query).data()).getAgreementItemTypeAttrs();

		if (agreementItemTypeAttrs.size() == 1) {
			return ResponseEntity.ok().body(agreementItemTypeAttrs.get(0));
		}

		return ResponseEntity.ok().body(agreementItemTypeAttrs);

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
	public ResponseEntity<Object> createAgreementItemTypeAttr(HttpServletRequest request) throws Exception {

		AgreementItemTypeAttr agreementItemTypeAttrToBeAdded = new AgreementItemTypeAttr();
		try {
			agreementItemTypeAttrToBeAdded = AgreementItemTypeAttrMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createAgreementItemTypeAttr(agreementItemTypeAttrToBeAdded);

	}

	/**
	 * creates a new AgreementItemTypeAttr entry in the ofbiz database
	 * 
	 * @param agreementItemTypeAttrToBeAdded
	 *            the AgreementItemTypeAttr thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createAgreementItemTypeAttr(@RequestBody AgreementItemTypeAttr agreementItemTypeAttrToBeAdded) throws Exception {

		AddAgreementItemTypeAttr command = new AddAgreementItemTypeAttr(agreementItemTypeAttrToBeAdded);
		AgreementItemTypeAttr agreementItemTypeAttr = ((AgreementItemTypeAttrAdded) Scheduler.execute(command).data()).getAddedAgreementItemTypeAttr();
		
		if (agreementItemTypeAttr != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(agreementItemTypeAttr);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("AgreementItemTypeAttr could not be created.");
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
	public boolean updateAgreementItemTypeAttr(HttpServletRequest request) throws Exception {

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

		AgreementItemTypeAttr agreementItemTypeAttrToBeUpdated = new AgreementItemTypeAttr();

		try {
			agreementItemTypeAttrToBeUpdated = AgreementItemTypeAttrMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateAgreementItemTypeAttr(agreementItemTypeAttrToBeUpdated, agreementItemTypeAttrToBeUpdated.getAttrName()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the AgreementItemTypeAttr with the specific Id
	 * 
	 * @param agreementItemTypeAttrToBeUpdated
	 *            the AgreementItemTypeAttr thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{attrName}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateAgreementItemTypeAttr(@RequestBody AgreementItemTypeAttr agreementItemTypeAttrToBeUpdated,
			@PathVariable String attrName) throws Exception {

		agreementItemTypeAttrToBeUpdated.setAttrName(attrName);

		UpdateAgreementItemTypeAttr command = new UpdateAgreementItemTypeAttr(agreementItemTypeAttrToBeUpdated);

		try {
			if(((AgreementItemTypeAttrUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{agreementItemTypeAttrId}")
	public ResponseEntity<Object> findById(@PathVariable String agreementItemTypeAttrId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("agreementItemTypeAttrId", agreementItemTypeAttrId);
		try {

			Object foundAgreementItemTypeAttr = findAgreementItemTypeAttrsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundAgreementItemTypeAttr);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{agreementItemTypeAttrId}")
	public ResponseEntity<Object> deleteAgreementItemTypeAttrByIdUpdated(@PathVariable String agreementItemTypeAttrId) throws Exception {
		DeleteAgreementItemTypeAttr command = new DeleteAgreementItemTypeAttr(agreementItemTypeAttrId);

		try {
			if (((AgreementItemTypeAttrDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("AgreementItemTypeAttr could not be deleted");

	}

}
