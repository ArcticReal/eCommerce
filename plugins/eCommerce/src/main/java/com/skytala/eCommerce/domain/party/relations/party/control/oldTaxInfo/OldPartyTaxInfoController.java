package com.skytala.eCommerce.domain.party.relations.party.control.oldTaxInfo;

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
import com.skytala.eCommerce.domain.party.relations.party.command.oldTaxInfo.AddOldPartyTaxInfo;
import com.skytala.eCommerce.domain.party.relations.party.command.oldTaxInfo.DeleteOldPartyTaxInfo;
import com.skytala.eCommerce.domain.party.relations.party.command.oldTaxInfo.UpdateOldPartyTaxInfo;
import com.skytala.eCommerce.domain.party.relations.party.event.oldTaxInfo.OldPartyTaxInfoAdded;
import com.skytala.eCommerce.domain.party.relations.party.event.oldTaxInfo.OldPartyTaxInfoDeleted;
import com.skytala.eCommerce.domain.party.relations.party.event.oldTaxInfo.OldPartyTaxInfoFound;
import com.skytala.eCommerce.domain.party.relations.party.event.oldTaxInfo.OldPartyTaxInfoUpdated;
import com.skytala.eCommerce.domain.party.relations.party.mapper.oldTaxInfo.OldPartyTaxInfoMapper;
import com.skytala.eCommerce.domain.party.relations.party.model.oldTaxInfo.OldPartyTaxInfo;
import com.skytala.eCommerce.domain.party.relations.party.query.oldTaxInfo.FindOldPartyTaxInfosBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/party/party/oldPartyTaxInfos")
public class OldPartyTaxInfoController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public OldPartyTaxInfoController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a OldPartyTaxInfo
	 * @return a List with the OldPartyTaxInfos
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<OldPartyTaxInfo>> findOldPartyTaxInfosBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindOldPartyTaxInfosBy query = new FindOldPartyTaxInfosBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<OldPartyTaxInfo> oldPartyTaxInfos =((OldPartyTaxInfoFound) Scheduler.execute(query).data()).getOldPartyTaxInfos();

		return ResponseEntity.ok().body(oldPartyTaxInfos);

	}

	/**
	 * creates a new OldPartyTaxInfo entry in the ofbiz database
	 * 
	 * @param oldPartyTaxInfoToBeAdded
	 *            the OldPartyTaxInfo thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<OldPartyTaxInfo> createOldPartyTaxInfo(@RequestBody OldPartyTaxInfo oldPartyTaxInfoToBeAdded) throws Exception {

		AddOldPartyTaxInfo command = new AddOldPartyTaxInfo(oldPartyTaxInfoToBeAdded);
		OldPartyTaxInfo oldPartyTaxInfo = ((OldPartyTaxInfoAdded) Scheduler.execute(command).data()).getAddedOldPartyTaxInfo();
		
		if (oldPartyTaxInfo != null) 
			return successful(oldPartyTaxInfo);
		else 
			return conflict(null);
	}

	/**
	 * Updates the OldPartyTaxInfo with the specific Id
	 * 
	 * @param oldPartyTaxInfoToBeUpdated
	 *            the OldPartyTaxInfo thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateOldPartyTaxInfo(@RequestBody OldPartyTaxInfo oldPartyTaxInfoToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		oldPartyTaxInfoToBeUpdated.setnull(null);

		UpdateOldPartyTaxInfo command = new UpdateOldPartyTaxInfo(oldPartyTaxInfoToBeUpdated);

		try {
			if(((OldPartyTaxInfoUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{oldPartyTaxInfoId}")
	public ResponseEntity<OldPartyTaxInfo> findById(@PathVariable String oldPartyTaxInfoId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("oldPartyTaxInfoId", oldPartyTaxInfoId);
		try {

			List<OldPartyTaxInfo> foundOldPartyTaxInfo = findOldPartyTaxInfosBy(requestParams).getBody();
			if(foundOldPartyTaxInfo.size()==1){				return successful(foundOldPartyTaxInfo.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{oldPartyTaxInfoId}")
	public ResponseEntity<String> deleteOldPartyTaxInfoByIdUpdated(@PathVariable String oldPartyTaxInfoId) throws Exception {
		DeleteOldPartyTaxInfo command = new DeleteOldPartyTaxInfo(oldPartyTaxInfoId);

		try {
			if (((OldPartyTaxInfoDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
