package com.skytala.eCommerce.domain.party.relations.agreementProductAppl;

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
import com.skytala.eCommerce.domain.party.relations.agreementProductAppl.command.AddAgreementProductAppl;
import com.skytala.eCommerce.domain.party.relations.agreementProductAppl.command.DeleteAgreementProductAppl;
import com.skytala.eCommerce.domain.party.relations.agreementProductAppl.command.UpdateAgreementProductAppl;
import com.skytala.eCommerce.domain.party.relations.agreementProductAppl.event.AgreementProductApplAdded;
import com.skytala.eCommerce.domain.party.relations.agreementProductAppl.event.AgreementProductApplDeleted;
import com.skytala.eCommerce.domain.party.relations.agreementProductAppl.event.AgreementProductApplFound;
import com.skytala.eCommerce.domain.party.relations.agreementProductAppl.event.AgreementProductApplUpdated;
import com.skytala.eCommerce.domain.party.relations.agreementProductAppl.mapper.AgreementProductApplMapper;
import com.skytala.eCommerce.domain.party.relations.agreementProductAppl.model.AgreementProductAppl;
import com.skytala.eCommerce.domain.party.relations.agreementProductAppl.query.FindAgreementProductApplsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/agreementProductAppls")
public class AgreementProductApplController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public AgreementProductApplController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a AgreementProductAppl
	 * @return a List with the AgreementProductAppls
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findAgreementProductApplsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindAgreementProductApplsBy query = new FindAgreementProductApplsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<AgreementProductAppl> agreementProductAppls =((AgreementProductApplFound) Scheduler.execute(query).data()).getAgreementProductAppls();

		if (agreementProductAppls.size() == 1) {
			return ResponseEntity.ok().body(agreementProductAppls.get(0));
		}

		return ResponseEntity.ok().body(agreementProductAppls);

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
	public ResponseEntity<Object> createAgreementProductAppl(HttpServletRequest request) throws Exception {

		AgreementProductAppl agreementProductApplToBeAdded = new AgreementProductAppl();
		try {
			agreementProductApplToBeAdded = AgreementProductApplMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createAgreementProductAppl(agreementProductApplToBeAdded);

	}

	/**
	 * creates a new AgreementProductAppl entry in the ofbiz database
	 * 
	 * @param agreementProductApplToBeAdded
	 *            the AgreementProductAppl thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createAgreementProductAppl(@RequestBody AgreementProductAppl agreementProductApplToBeAdded) throws Exception {

		AddAgreementProductAppl command = new AddAgreementProductAppl(agreementProductApplToBeAdded);
		AgreementProductAppl agreementProductAppl = ((AgreementProductApplAdded) Scheduler.execute(command).data()).getAddedAgreementProductAppl();
		
		if (agreementProductAppl != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(agreementProductAppl);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("AgreementProductAppl could not be created.");
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
	public boolean updateAgreementProductAppl(HttpServletRequest request) throws Exception {

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

		AgreementProductAppl agreementProductApplToBeUpdated = new AgreementProductAppl();

		try {
			agreementProductApplToBeUpdated = AgreementProductApplMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateAgreementProductAppl(agreementProductApplToBeUpdated, agreementProductApplToBeUpdated.getAgreementItemSeqId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the AgreementProductAppl with the specific Id
	 * 
	 * @param agreementProductApplToBeUpdated
	 *            the AgreementProductAppl thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{agreementItemSeqId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateAgreementProductAppl(@RequestBody AgreementProductAppl agreementProductApplToBeUpdated,
			@PathVariable String agreementItemSeqId) throws Exception {

		agreementProductApplToBeUpdated.setAgreementItemSeqId(agreementItemSeqId);

		UpdateAgreementProductAppl command = new UpdateAgreementProductAppl(agreementProductApplToBeUpdated);

		try {
			if(((AgreementProductApplUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{agreementProductApplId}")
	public ResponseEntity<Object> findById(@PathVariable String agreementProductApplId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("agreementProductApplId", agreementProductApplId);
		try {

			Object foundAgreementProductAppl = findAgreementProductApplsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundAgreementProductAppl);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{agreementProductApplId}")
	public ResponseEntity<Object> deleteAgreementProductApplByIdUpdated(@PathVariable String agreementProductApplId) throws Exception {
		DeleteAgreementProductAppl command = new DeleteAgreementProductAppl(agreementProductApplId);

		try {
			if (((AgreementProductApplDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("AgreementProductAppl could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/agreementProductAppl/\" plus one of the following: "
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
