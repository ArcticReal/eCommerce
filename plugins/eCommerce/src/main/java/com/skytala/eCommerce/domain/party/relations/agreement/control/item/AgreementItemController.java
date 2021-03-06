package com.skytala.eCommerce.domain.party.relations.agreement.control.item;

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
import com.skytala.eCommerce.domain.party.relations.agreement.command.item.AddAgreementItem;
import com.skytala.eCommerce.domain.party.relations.agreement.command.item.DeleteAgreementItem;
import com.skytala.eCommerce.domain.party.relations.agreement.command.item.UpdateAgreementItem;
import com.skytala.eCommerce.domain.party.relations.agreement.event.item.AgreementItemAdded;
import com.skytala.eCommerce.domain.party.relations.agreement.event.item.AgreementItemDeleted;
import com.skytala.eCommerce.domain.party.relations.agreement.event.item.AgreementItemFound;
import com.skytala.eCommerce.domain.party.relations.agreement.event.item.AgreementItemUpdated;
import com.skytala.eCommerce.domain.party.relations.agreement.mapper.item.AgreementItemMapper;
import com.skytala.eCommerce.domain.party.relations.agreement.model.item.AgreementItem;
import com.skytala.eCommerce.domain.party.relations.agreement.query.item.FindAgreementItemsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/party/agreement/agreementItems")
public class AgreementItemController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public AgreementItemController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a AgreementItem
	 * @return a List with the AgreementItems
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<AgreementItem>> findAgreementItemsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindAgreementItemsBy query = new FindAgreementItemsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<AgreementItem> agreementItems =((AgreementItemFound) Scheduler.execute(query).data()).getAgreementItems();

		return ResponseEntity.ok().body(agreementItems);

	}

	/**
	 * creates a new AgreementItem entry in the ofbiz database
	 * 
	 * @param agreementItemToBeAdded
	 *            the AgreementItem thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<AgreementItem> createAgreementItem(@RequestBody AgreementItem agreementItemToBeAdded) throws Exception {

		AddAgreementItem command = new AddAgreementItem(agreementItemToBeAdded);
		AgreementItem agreementItem = ((AgreementItemAdded) Scheduler.execute(command).data()).getAddedAgreementItem();
		
		if (agreementItem != null) 
			return successful(agreementItem);
		else 
			return conflict(null);
	}

	/**
	 * Updates the AgreementItem with the specific Id
	 * 
	 * @param agreementItemToBeUpdated
	 *            the AgreementItem thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{agreementItemSeqId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateAgreementItem(@RequestBody AgreementItem agreementItemToBeUpdated,
			@PathVariable String agreementItemSeqId) throws Exception {

		agreementItemToBeUpdated.setAgreementItemSeqId(agreementItemSeqId);

		UpdateAgreementItem command = new UpdateAgreementItem(agreementItemToBeUpdated);

		try {
			if(((AgreementItemUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{agreementItemId}")
	public ResponseEntity<AgreementItem> findById(@PathVariable String agreementItemId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("agreementItemId", agreementItemId);
		try {

			List<AgreementItem> foundAgreementItem = findAgreementItemsBy(requestParams).getBody();
			if(foundAgreementItem.size()==1){				return successful(foundAgreementItem.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{agreementItemId}")
	public ResponseEntity<String> deleteAgreementItemByIdUpdated(@PathVariable String agreementItemId) throws Exception {
		DeleteAgreementItem command = new DeleteAgreementItem(agreementItemId);

		try {
			if (((AgreementItemDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
