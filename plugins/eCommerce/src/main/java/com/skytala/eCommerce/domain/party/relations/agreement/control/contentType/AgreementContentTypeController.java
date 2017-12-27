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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	@GetMapping("/find")
	public ResponseEntity<List<AgreementContentType>> findAgreementContentTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindAgreementContentTypesBy query = new FindAgreementContentTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<AgreementContentType> agreementContentTypes =((AgreementContentTypeFound) Scheduler.execute(query).data()).getAgreementContentTypes();

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
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<AgreementContentType> createAgreementContentType(HttpServletRequest request) throws Exception {

		AgreementContentType agreementContentTypeToBeAdded = new AgreementContentType();
		try {
			agreementContentTypeToBeAdded = AgreementContentTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
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
	public ResponseEntity<AgreementContentType> createAgreementContentType(@RequestBody AgreementContentType agreementContentTypeToBeAdded) throws Exception {

		AddAgreementContentType command = new AddAgreementContentType(agreementContentTypeToBeAdded);
		AgreementContentType agreementContentType = ((AgreementContentTypeAdded) Scheduler.execute(command).data()).getAddedAgreementContentType();
		
		if (agreementContentType != null) 
			return successful(agreementContentType);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateAgreementContentType(@RequestBody AgreementContentType agreementContentTypeToBeUpdated,
			@PathVariable String agreementContentTypeId) throws Exception {

		agreementContentTypeToBeUpdated.setAgreementContentTypeId(agreementContentTypeId);

		UpdateAgreementContentType command = new UpdateAgreementContentType(agreementContentTypeToBeUpdated);

		try {
			if(((AgreementContentTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{agreementContentTypeId}")
	public ResponseEntity<AgreementContentType> findById(@PathVariable String agreementContentTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("agreementContentTypeId", agreementContentTypeId);
		try {

			List<AgreementContentType> foundAgreementContentType = findAgreementContentTypesBy(requestParams).getBody();
			if(foundAgreementContentType.size()==1){				return successful(foundAgreementContentType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{agreementContentTypeId}")
	public ResponseEntity<String> deleteAgreementContentTypeByIdUpdated(@PathVariable String agreementContentTypeId) throws Exception {
		DeleteAgreementContentType command = new DeleteAgreementContentType(agreementContentTypeId);

		try {
			if (((AgreementContentTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
