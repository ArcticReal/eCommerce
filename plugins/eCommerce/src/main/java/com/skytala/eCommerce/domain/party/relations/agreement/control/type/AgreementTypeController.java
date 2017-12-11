package com.skytala.eCommerce.domain.party.relations.agreement.control.type;

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
import com.skytala.eCommerce.domain.party.relations.agreement.command.type.AddAgreementType;
import com.skytala.eCommerce.domain.party.relations.agreement.command.type.DeleteAgreementType;
import com.skytala.eCommerce.domain.party.relations.agreement.command.type.UpdateAgreementType;
import com.skytala.eCommerce.domain.party.relations.agreement.event.type.AgreementTypeAdded;
import com.skytala.eCommerce.domain.party.relations.agreement.event.type.AgreementTypeDeleted;
import com.skytala.eCommerce.domain.party.relations.agreement.event.type.AgreementTypeFound;
import com.skytala.eCommerce.domain.party.relations.agreement.event.type.AgreementTypeUpdated;
import com.skytala.eCommerce.domain.party.relations.agreement.mapper.type.AgreementTypeMapper;
import com.skytala.eCommerce.domain.party.relations.agreement.model.type.AgreementType;
import com.skytala.eCommerce.domain.party.relations.agreement.query.type.FindAgreementTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/party/agreement/agreementTypes")
public class AgreementTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public AgreementTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a AgreementType
	 * @return a List with the AgreementTypes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findAgreementTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindAgreementTypesBy query = new FindAgreementTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<AgreementType> agreementTypes =((AgreementTypeFound) Scheduler.execute(query).data()).getAgreementTypes();

		if (agreementTypes.size() == 1) {
			return ResponseEntity.ok().body(agreementTypes.get(0));
		}

		return ResponseEntity.ok().body(agreementTypes);

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
	public ResponseEntity<Object> createAgreementType(HttpServletRequest request) throws Exception {

		AgreementType agreementTypeToBeAdded = new AgreementType();
		try {
			agreementTypeToBeAdded = AgreementTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createAgreementType(agreementTypeToBeAdded);

	}

	/**
	 * creates a new AgreementType entry in the ofbiz database
	 * 
	 * @param agreementTypeToBeAdded
	 *            the AgreementType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createAgreementType(@RequestBody AgreementType agreementTypeToBeAdded) throws Exception {

		AddAgreementType command = new AddAgreementType(agreementTypeToBeAdded);
		AgreementType agreementType = ((AgreementTypeAdded) Scheduler.execute(command).data()).getAddedAgreementType();
		
		if (agreementType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(agreementType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("AgreementType could not be created.");
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
	public boolean updateAgreementType(HttpServletRequest request) throws Exception {

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

		AgreementType agreementTypeToBeUpdated = new AgreementType();

		try {
			agreementTypeToBeUpdated = AgreementTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateAgreementType(agreementTypeToBeUpdated, agreementTypeToBeUpdated.getAgreementTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the AgreementType with the specific Id
	 * 
	 * @param agreementTypeToBeUpdated
	 *            the AgreementType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{agreementTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateAgreementType(@RequestBody AgreementType agreementTypeToBeUpdated,
			@PathVariable String agreementTypeId) throws Exception {

		agreementTypeToBeUpdated.setAgreementTypeId(agreementTypeId);

		UpdateAgreementType command = new UpdateAgreementType(agreementTypeToBeUpdated);

		try {
			if(((AgreementTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{agreementTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String agreementTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("agreementTypeId", agreementTypeId);
		try {

			Object foundAgreementType = findAgreementTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundAgreementType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{agreementTypeId}")
	public ResponseEntity<Object> deleteAgreementTypeByIdUpdated(@PathVariable String agreementTypeId) throws Exception {
		DeleteAgreementType command = new DeleteAgreementType(agreementTypeId);

		try {
			if (((AgreementTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("AgreementType could not be deleted");

	}

}
