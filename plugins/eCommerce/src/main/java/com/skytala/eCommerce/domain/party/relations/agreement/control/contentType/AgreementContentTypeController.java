package com.skytala.eCommerce.domain.party.relations.agreement.control.contentType;

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
import com.skytala.eCommerce.domain.party.relations.agreement.command.contentType.AddAgreementContentType;
import com.skytala.eCommerce.domain.party.relations.agreement.command.contentType.DeleteAgreementContentType;
import com.skytala.eCommerce.domain.party.relations.agreement.command.contentType.UpdateAgreementContentType;
import com.skytala.eCommerce.domain.party.relations.agreement.event.contentType.AgreementContentTypeAdded;
import com.skytala.eCommerce.domain.party.relations.agreement.event.contentType.AgreementContentTypeDeleted;
import com.skytala.eCommerce.domain.party.relations.agreement.event.contentType.AgreementContentTypeFound;
import com.skytala.eCommerce.domain.party.relations.agreement.event.contentType.AgreementContentTypeUpdated;
import com.skytala.eCommerce.domain.party.relations.agreement.mapper.contentType.AgreementContentTypeMapper;
import com.skytala.eCommerce.domain.party.relations.agreement.model.contentType.AgreementContentType;
import com.skytala.eCommerce.domain.party.relations.agreement.query.contentType.FindAgreementContentTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/party/agreement/agreementContentTypes")
public class AgreementContentTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public AgreementContentTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a AgreementContentType
	 * @return a List with the AgreementContentTypes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findAgreementContentTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindAgreementContentTypesBy query = new FindAgreementContentTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<AgreementContentType> agreementContentTypes =((AgreementContentTypeFound) Scheduler.execute(query).data()).getAgreementContentTypes();

		if (agreementContentTypes.size() == 1) {
			return ResponseEntity.ok().body(agreementContentTypes.get(0));
		}

		return ResponseEntity.ok().body(agreementContentTypes);

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
	public ResponseEntity<Object> createAgreementContentType(HttpServletRequest request) throws Exception {

		AgreementContentType agreementContentTypeToBeAdded = new AgreementContentType();
		try {
			agreementContentTypeToBeAdded = AgreementContentTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createAgreementContentType(agreementContentTypeToBeAdded);

	}

	/**
	 * creates a new AgreementContentType entry in the ofbiz database
	 * 
	 * @param agreementContentTypeToBeAdded
	 *            the AgreementContentType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createAgreementContentType(@RequestBody AgreementContentType agreementContentTypeToBeAdded) throws Exception {

		AddAgreementContentType command = new AddAgreementContentType(agreementContentTypeToBeAdded);
		AgreementContentType agreementContentType = ((AgreementContentTypeAdded) Scheduler.execute(command).data()).getAddedAgreementContentType();
		
		if (agreementContentType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(agreementContentType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("AgreementContentType could not be created.");
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
	public boolean updateAgreementContentType(HttpServletRequest request) throws Exception {

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

		AgreementContentType agreementContentTypeToBeUpdated = new AgreementContentType();

		try {
			agreementContentTypeToBeUpdated = AgreementContentTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateAgreementContentType(agreementContentTypeToBeUpdated, agreementContentTypeToBeUpdated.getAgreementContentTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the AgreementContentType with the specific Id
	 * 
	 * @param agreementContentTypeToBeUpdated
	 *            the AgreementContentType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{agreementContentTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateAgreementContentType(@RequestBody AgreementContentType agreementContentTypeToBeUpdated,
			@PathVariable String agreementContentTypeId) throws Exception {

		agreementContentTypeToBeUpdated.setAgreementContentTypeId(agreementContentTypeId);

		UpdateAgreementContentType command = new UpdateAgreementContentType(agreementContentTypeToBeUpdated);

		try {
			if(((AgreementContentTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{agreementContentTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String agreementContentTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("agreementContentTypeId", agreementContentTypeId);
		try {

			Object foundAgreementContentType = findAgreementContentTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundAgreementContentType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{agreementContentTypeId}")
	public ResponseEntity<Object> deleteAgreementContentTypeByIdUpdated(@PathVariable String agreementContentTypeId) throws Exception {
		DeleteAgreementContentType command = new DeleteAgreementContentType(agreementContentTypeId);

		try {
			if (((AgreementContentTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("AgreementContentType could not be deleted");

	}

}
