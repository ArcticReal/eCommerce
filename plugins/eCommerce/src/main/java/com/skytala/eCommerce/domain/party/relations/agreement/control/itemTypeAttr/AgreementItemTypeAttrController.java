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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	public ResponseEntity<List<AgreementItemTypeAttr>> findAgreementItemTypeAttrsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindAgreementItemTypeAttrsBy query = new FindAgreementItemTypeAttrsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<AgreementItemTypeAttr> agreementItemTypeAttrs =((AgreementItemTypeAttrFound) Scheduler.execute(query).data()).getAgreementItemTypeAttrs();

		return ResponseEntity.ok().body(agreementItemTypeAttrs);

	}

	/**
	 * creates a new AgreementItemTypeAttr entry in the ofbiz database
	 * 
	 * @param agreementItemTypeAttrToBeAdded
	 *            the AgreementItemTypeAttr thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<AgreementItemTypeAttr> createAgreementItemTypeAttr(@RequestBody AgreementItemTypeAttr agreementItemTypeAttrToBeAdded) throws Exception {

		AddAgreementItemTypeAttr command = new AddAgreementItemTypeAttr(agreementItemTypeAttrToBeAdded);
		AgreementItemTypeAttr agreementItemTypeAttr = ((AgreementItemTypeAttrAdded) Scheduler.execute(command).data()).getAddedAgreementItemTypeAttr();
		
		if (agreementItemTypeAttr != null) 
			return successful(agreementItemTypeAttr);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateAgreementItemTypeAttr(@RequestBody AgreementItemTypeAttr agreementItemTypeAttrToBeUpdated,
			@PathVariable String attrName) throws Exception {

		agreementItemTypeAttrToBeUpdated.setAttrName(attrName);

		UpdateAgreementItemTypeAttr command = new UpdateAgreementItemTypeAttr(agreementItemTypeAttrToBeUpdated);

		try {
			if(((AgreementItemTypeAttrUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{agreementItemTypeAttrId}")
	public ResponseEntity<AgreementItemTypeAttr> findById(@PathVariable String agreementItemTypeAttrId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("agreementItemTypeAttrId", agreementItemTypeAttrId);
		try {

			List<AgreementItemTypeAttr> foundAgreementItemTypeAttr = findAgreementItemTypeAttrsBy(requestParams).getBody();
			if(foundAgreementItemTypeAttr.size()==1){				return successful(foundAgreementItemTypeAttr.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{agreementItemTypeAttrId}")
	public ResponseEntity<String> deleteAgreementItemTypeAttrByIdUpdated(@PathVariable String agreementItemTypeAttrId) throws Exception {
		DeleteAgreementItemTypeAttr command = new DeleteAgreementItemTypeAttr(agreementItemTypeAttrId);

		try {
			if (((AgreementItemTypeAttrDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
