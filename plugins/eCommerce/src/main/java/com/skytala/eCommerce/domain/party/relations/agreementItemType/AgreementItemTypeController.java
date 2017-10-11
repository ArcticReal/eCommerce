package com.skytala.eCommerce.domain.party.relations.agreementItemType;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Splitter;
import com.skytala.eCommerce.domain.party.relations.agreementItemType.command.AddAgreementItemType;
import com.skytala.eCommerce.domain.party.relations.agreementItemType.command.DeleteAgreementItemType;
import com.skytala.eCommerce.domain.party.relations.agreementItemType.command.UpdateAgreementItemType;
import com.skytala.eCommerce.domain.party.relations.agreementItemType.event.AgreementItemTypeAdded;
import com.skytala.eCommerce.domain.party.relations.agreementItemType.event.AgreementItemTypeDeleted;
import com.skytala.eCommerce.domain.party.relations.agreementItemType.event.AgreementItemTypeFound;
import com.skytala.eCommerce.domain.party.relations.agreementItemType.event.AgreementItemTypeUpdated;
import com.skytala.eCommerce.domain.party.relations.agreementItemType.mapper.AgreementItemTypeMapper;
import com.skytala.eCommerce.domain.party.relations.agreementItemType.model.AgreementItemType;
import com.skytala.eCommerce.domain.party.relations.agreementItemType.query.FindAgreementItemTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/agreementItemTypes")
public class AgreementItemTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public AgreementItemTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a AgreementItemType
	 * @return a List with the AgreementItemTypes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findAgreementItemTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindAgreementItemTypesBy query = new FindAgreementItemTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<AgreementItemType> agreementItemTypes =((AgreementItemTypeFound) Scheduler.execute(query).data()).getAgreementItemTypes();

		if (agreementItemTypes.size() == 1) {
			return ResponseEntity.ok().body(agreementItemTypes.get(0));
		}

		return ResponseEntity.ok().body(agreementItemTypes);

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
	public ResponseEntity<Object> createAgreementItemType(HttpServletRequest request) throws Exception {

		AgreementItemType agreementItemTypeToBeAdded = new AgreementItemType();
		try {
			agreementItemTypeToBeAdded = AgreementItemTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createAgreementItemType(agreementItemTypeToBeAdded);

	}

	/**
	 * creates a new AgreementItemType entry in the ofbiz database
	 * 
	 * @param agreementItemTypeToBeAdded
	 *            the AgreementItemType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createAgreementItemType(@RequestBody AgreementItemType agreementItemTypeToBeAdded) throws Exception {

		AddAgreementItemType command = new AddAgreementItemType(agreementItemTypeToBeAdded);
		AgreementItemType agreementItemType = ((AgreementItemTypeAdded) Scheduler.execute(command).data()).getAddedAgreementItemType();
		
		if (agreementItemType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(agreementItemType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("AgreementItemType could not be created.");
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
	public boolean updateAgreementItemType(HttpServletRequest request) throws Exception {

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

		AgreementItemType agreementItemTypeToBeUpdated = new AgreementItemType();

		try {
			agreementItemTypeToBeUpdated = AgreementItemTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateAgreementItemType(agreementItemTypeToBeUpdated, agreementItemTypeToBeUpdated.getAgreementItemTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the AgreementItemType with the specific Id
	 * 
	 * @param agreementItemTypeToBeUpdated
	 *            the AgreementItemType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{agreementItemTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateAgreementItemType(@RequestBody AgreementItemType agreementItemTypeToBeUpdated,
			@PathVariable String agreementItemTypeId) throws Exception {

		agreementItemTypeToBeUpdated.setAgreementItemTypeId(agreementItemTypeId);

		UpdateAgreementItemType command = new UpdateAgreementItemType(agreementItemTypeToBeUpdated);

		try {
			if(((AgreementItemTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{agreementItemTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String agreementItemTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("agreementItemTypeId", agreementItemTypeId);
		try {

			Object foundAgreementItemType = findAgreementItemTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundAgreementItemType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{agreementItemTypeId}")
	public ResponseEntity<Object> deleteAgreementItemTypeByIdUpdated(@PathVariable String agreementItemTypeId) throws Exception {
		DeleteAgreementItemType command = new DeleteAgreementItemType(agreementItemTypeId);

		try {
			if (((AgreementItemTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("AgreementItemType could not be deleted");

	}

	@RequestMapping(value = (" ** "))
	public ResponseEntity<Object> returnErrorPage(HttpServletRequest request) {

		String usedUri = request.getRequestURI();
		String[] splittedString = usedUri.split("/");

		String usedRequest = splittedString[splittedString.length - 1];

		if (validRequests.containsKey(usedRequest)) {
			String returnVal = "Error: request method " + request.getMethod() + " not allowed for \"" + usedUri
					+ "\"!\n" + "Please use " + validRequests.get(usedRequest) + "!";

			return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(returnVal);
		}

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/agreementItemType/\" plus one of the following: "
				+ "";

		Set<String> keySet = validRequests.keySet();
		Iterator<String> it = keySet.iterator();

		while (it.hasNext()) {
			returnVal += "\"" + it.next() + "\"";
			if (it.hasNext())
				returnVal += ", ";
		}

		returnVal += "!";

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnVal);

	}
}