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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/party/agreement/agreementContents")
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
	@GetMapping("/find")
	public ResponseEntity<List<AgreementContent>> findAgreementContentsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindAgreementContentsBy query = new FindAgreementContentsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<AgreementContent> agreementContents =((AgreementContentFound) Scheduler.execute(query).data()).getAgreementContents();

		return ResponseEntity.ok().body(agreementContents);

	}

	/**
	 * creates a new AgreementContent entry in the ofbiz database
	 * 
	 * @param agreementContentToBeAdded
	 *            the AgreementContent thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<AgreementContent> createAgreementContent(@RequestBody AgreementContent agreementContentToBeAdded) throws Exception {

		AddAgreementContent command = new AddAgreementContent(agreementContentToBeAdded);
		AgreementContent agreementContent = ((AgreementContentAdded) Scheduler.execute(command).data()).getAddedAgreementContent();
		
		if (agreementContent != null) 
			return successful(agreementContent);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateAgreementContent(@RequestBody AgreementContent agreementContentToBeUpdated,
			@PathVariable String agreementItemSeqId) throws Exception {

		agreementContentToBeUpdated.setAgreementItemSeqId(agreementItemSeqId);

		UpdateAgreementContent command = new UpdateAgreementContent(agreementContentToBeUpdated);

		try {
			if(((AgreementContentUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{agreementContentId}")
	public ResponseEntity<AgreementContent> findById(@PathVariable String agreementContentId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("agreementContentId", agreementContentId);
		try {

			List<AgreementContent> foundAgreementContent = findAgreementContentsBy(requestParams).getBody();
			if(foundAgreementContent.size()==1){				return successful(foundAgreementContent.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{agreementContentId}")
	public ResponseEntity<String> deleteAgreementContentByIdUpdated(@PathVariable String agreementContentId) throws Exception {
		DeleteAgreementContent command = new DeleteAgreementContent(agreementContentId);

		try {
			if (((AgreementContentDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
