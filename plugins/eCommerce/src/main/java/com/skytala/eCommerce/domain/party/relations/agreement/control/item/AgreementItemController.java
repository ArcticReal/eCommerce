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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findAgreementItemsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindAgreementItemsBy query = new FindAgreementItemsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<AgreementItem> agreementItems =((AgreementItemFound) Scheduler.execute(query).data()).getAgreementItems();

		if (agreementItems.size() == 1) {
			return ResponseEntity.ok().body(agreementItems.get(0));
		}

		return ResponseEntity.ok().body(agreementItems);

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
	public ResponseEntity<Object> createAgreementItem(HttpServletRequest request) throws Exception {

		AgreementItem agreementItemToBeAdded = new AgreementItem();
		try {
			agreementItemToBeAdded = AgreementItemMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createAgreementItem(agreementItemToBeAdded);

	}

	/**
	 * creates a new AgreementItem entry in the ofbiz database
	 * 
	 * @param agreementItemToBeAdded
	 *            the AgreementItem thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createAgreementItem(@RequestBody AgreementItem agreementItemToBeAdded) throws Exception {

		AddAgreementItem command = new AddAgreementItem(agreementItemToBeAdded);
		AgreementItem agreementItem = ((AgreementItemAdded) Scheduler.execute(command).data()).getAddedAgreementItem();
		
		if (agreementItem != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(agreementItem);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("AgreementItem could not be created.");
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
	public boolean updateAgreementItem(HttpServletRequest request) throws Exception {

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

		AgreementItem agreementItemToBeUpdated = new AgreementItem();

		try {
			agreementItemToBeUpdated = AgreementItemMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateAgreementItem(agreementItemToBeUpdated, agreementItemToBeUpdated.getAgreementItemSeqId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateAgreementItem(@RequestBody AgreementItem agreementItemToBeUpdated,
			@PathVariable String agreementItemSeqId) throws Exception {

		agreementItemToBeUpdated.setAgreementItemSeqId(agreementItemSeqId);

		UpdateAgreementItem command = new UpdateAgreementItem(agreementItemToBeUpdated);

		try {
			if(((AgreementItemUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{agreementItemId}")
	public ResponseEntity<Object> findById(@PathVariable String agreementItemId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("agreementItemId", agreementItemId);
		try {

			Object foundAgreementItem = findAgreementItemsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundAgreementItem);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{agreementItemId}")
	public ResponseEntity<Object> deleteAgreementItemByIdUpdated(@PathVariable String agreementItemId) throws Exception {
		DeleteAgreementItem command = new DeleteAgreementItem(agreementItemId);

		try {
			if (((AgreementItemDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("AgreementItem could not be deleted");

	}

}
