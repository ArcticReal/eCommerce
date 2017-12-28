package com.skytala.eCommerce.domain.accounting.relations.partyTaxAuthInfo;

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
import com.skytala.eCommerce.domain.accounting.relations.partyTaxAuthInfo.command.AddPartyTaxAuthInfo;
import com.skytala.eCommerce.domain.accounting.relations.partyTaxAuthInfo.command.DeletePartyTaxAuthInfo;
import com.skytala.eCommerce.domain.accounting.relations.partyTaxAuthInfo.command.UpdatePartyTaxAuthInfo;
import com.skytala.eCommerce.domain.accounting.relations.partyTaxAuthInfo.event.PartyTaxAuthInfoAdded;
import com.skytala.eCommerce.domain.accounting.relations.partyTaxAuthInfo.event.PartyTaxAuthInfoDeleted;
import com.skytala.eCommerce.domain.accounting.relations.partyTaxAuthInfo.event.PartyTaxAuthInfoFound;
import com.skytala.eCommerce.domain.accounting.relations.partyTaxAuthInfo.event.PartyTaxAuthInfoUpdated;
import com.skytala.eCommerce.domain.accounting.relations.partyTaxAuthInfo.mapper.PartyTaxAuthInfoMapper;
import com.skytala.eCommerce.domain.accounting.relations.partyTaxAuthInfo.model.PartyTaxAuthInfo;
import com.skytala.eCommerce.domain.accounting.relations.partyTaxAuthInfo.query.FindPartyTaxAuthInfosBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/partyTaxAuthInfos")
public class PartyTaxAuthInfoController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PartyTaxAuthInfoController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PartyTaxAuthInfo
	 * @return a List with the PartyTaxAuthInfos
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<PartyTaxAuthInfo>> findPartyTaxAuthInfosBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPartyTaxAuthInfosBy query = new FindPartyTaxAuthInfosBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PartyTaxAuthInfo> partyTaxAuthInfos =((PartyTaxAuthInfoFound) Scheduler.execute(query).data()).getPartyTaxAuthInfos();

		return ResponseEntity.ok().body(partyTaxAuthInfos);

	}

	/**
	 * creates a new PartyTaxAuthInfo entry in the ofbiz database
	 * 
	 * @param partyTaxAuthInfoToBeAdded
	 *            the PartyTaxAuthInfo thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PartyTaxAuthInfo> createPartyTaxAuthInfo(@RequestBody PartyTaxAuthInfo partyTaxAuthInfoToBeAdded) throws Exception {

		AddPartyTaxAuthInfo command = new AddPartyTaxAuthInfo(partyTaxAuthInfoToBeAdded);
		PartyTaxAuthInfo partyTaxAuthInfo = ((PartyTaxAuthInfoAdded) Scheduler.execute(command).data()).getAddedPartyTaxAuthInfo();
		
		if (partyTaxAuthInfo != null) 
			return successful(partyTaxAuthInfo);
		else 
			return conflict(null);
	}

	/**
	 * Updates the PartyTaxAuthInfo with the specific Id
	 * 
	 * @param partyTaxAuthInfoToBeUpdated
	 *            the PartyTaxAuthInfo thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{taxAuthPartyId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updatePartyTaxAuthInfo(@RequestBody PartyTaxAuthInfo partyTaxAuthInfoToBeUpdated,
			@PathVariable String taxAuthPartyId) throws Exception {

		partyTaxAuthInfoToBeUpdated.setTaxAuthPartyId(taxAuthPartyId);

		UpdatePartyTaxAuthInfo command = new UpdatePartyTaxAuthInfo(partyTaxAuthInfoToBeUpdated);

		try {
			if(((PartyTaxAuthInfoUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{partyTaxAuthInfoId}")
	public ResponseEntity<PartyTaxAuthInfo> findById(@PathVariable String partyTaxAuthInfoId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("partyTaxAuthInfoId", partyTaxAuthInfoId);
		try {

			List<PartyTaxAuthInfo> foundPartyTaxAuthInfo = findPartyTaxAuthInfosBy(requestParams).getBody();
			if(foundPartyTaxAuthInfo.size()==1){				return successful(foundPartyTaxAuthInfo.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{partyTaxAuthInfoId}")
	public ResponseEntity<String> deletePartyTaxAuthInfoByIdUpdated(@PathVariable String partyTaxAuthInfoId) throws Exception {
		DeletePartyTaxAuthInfo command = new DeletePartyTaxAuthInfo(partyTaxAuthInfoId);

		try {
			if (((PartyTaxAuthInfoDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
