package com.skytala.eCommerce.domain.party.relations.agreement.control.content;

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
import com.skytala.eCommerce.domain.party.relations.agreement.command.content.AddAgreementContent;
import com.skytala.eCommerce.domain.party.relations.agreement.command.content.DeleteAgreementContent;
import com.skytala.eCommerce.domain.party.relations.agreement.command.content.UpdateAgreementContent;
import com.skytala.eCommerce.domain.party.relations.agreement.event.content.AgreementContentAdded;
import com.skytala.eCommerce.domain.party.relations.agreement.event.content.AgreementContentDeleted;
import com.skytala.eCommerce.domain.party.relations.agreement.event.content.AgreementContentFound;
import com.skytala.eCommerce.domain.party.relations.agreement.event.content.AgreementContentUpdated;
import com.skytala.eCommerce.domain.party.relations.agreement.mapper.content.AgreementContentMapper;
import com.skytala.eCommerce.domain.party.relations.agreement.model.content.AgreementContent;
import com.skytala.eCommerce.domain.party.relations.agreement.query.content.FindAgreementContentsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/agreementContents")
public class AgreementContentController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public AgreementContentController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a AgreementContent
	 * @return a List with the AgreementContents
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findAgreementContentsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindAgreementContentsBy query = new FindAgreementContentsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<AgreementContent> agreementContents =((AgreementContentFound) Scheduler.execute(query).data()).getAgreementContents();

		if (agreementContents.size() == 1) {
			return ResponseEntity.ok().body(agreementContents.get(0));
		}

		return ResponseEntity.ok().body(agreementContents);

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
	public ResponseEntity<Object> createAgreementContent(HttpServletRequest request) throws Exception {

		AgreementContent agreementContentToBeAdded = new AgreementContent();
		try {
			agreementContentToBeAdded = AgreementContentMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createAgreementContent(agreementContentToBeAdded);

	}

	/**
	 * creates a new AgreementContent entry in the ofbiz database
	 * 
	 * @param agreementContentToBeAdded
	 *            the AgreementContent thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createAgreementContent(@RequestBody AgreementContent agreementContentToBeAdded) throws Exception {

		AddAgreementContent command = new AddAgreementContent(agreementContentToBeAdded);
		AgreementContent agreementContent = ((AgreementContentAdded) Scheduler.execute(command).data()).getAddedAgreementContent();
		
		if (agreementContent != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(agreementContent);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("AgreementContent could not be created.");
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
	public boolean updateAgreementContent(HttpServletRequest request) throws Exception {

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

		AgreementContent agreementContentToBeUpdated = new AgreementContent();

		try {
			agreementContentToBeUpdated = AgreementContentMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateAgreementContent(agreementContentToBeUpdated, agreementContentToBeUpdated.getAgreementItemSeqId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the AgreementContent with the specific Id
	 * 
	 * @param agreementContentToBeUpdated
	 *            the AgreementContent thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{agreementItemSeqId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateAgreementContent(@RequestBody AgreementContent agreementContentToBeUpdated,
			@PathVariable String agreementItemSeqId) throws Exception {

		agreementContentToBeUpdated.setAgreementItemSeqId(agreementItemSeqId);

		UpdateAgreementContent command = new UpdateAgreementContent(agreementContentToBeUpdated);

		try {
			if(((AgreementContentUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{agreementContentId}")
	public ResponseEntity<Object> findById(@PathVariable String agreementContentId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("agreementContentId", agreementContentId);
		try {

			Object foundAgreementContent = findAgreementContentsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundAgreementContent);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{agreementContentId}")
	public ResponseEntity<Object> deleteAgreementContentByIdUpdated(@PathVariable String agreementContentId) throws Exception {
		DeleteAgreementContent command = new DeleteAgreementContent(agreementContentId);

		try {
			if (((AgreementContentDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("AgreementContent could not be deleted");

	}

}
