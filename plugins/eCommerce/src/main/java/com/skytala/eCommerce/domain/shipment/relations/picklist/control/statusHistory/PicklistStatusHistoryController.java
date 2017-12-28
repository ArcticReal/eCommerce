package com.skytala.eCommerce.domain.shipment.relations.picklist.control.statusHistory;

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
import com.skytala.eCommerce.domain.shipment.relations.picklist.command.statusHistory.AddPicklistStatusHistory;
import com.skytala.eCommerce.domain.shipment.relations.picklist.command.statusHistory.DeletePicklistStatusHistory;
import com.skytala.eCommerce.domain.shipment.relations.picklist.command.statusHistory.UpdatePicklistStatusHistory;
import com.skytala.eCommerce.domain.shipment.relations.picklist.event.statusHistory.PicklistStatusHistoryAdded;
import com.skytala.eCommerce.domain.shipment.relations.picklist.event.statusHistory.PicklistStatusHistoryDeleted;
import com.skytala.eCommerce.domain.shipment.relations.picklist.event.statusHistory.PicklistStatusHistoryFound;
import com.skytala.eCommerce.domain.shipment.relations.picklist.event.statusHistory.PicklistStatusHistoryUpdated;
import com.skytala.eCommerce.domain.shipment.relations.picklist.mapper.statusHistory.PicklistStatusHistoryMapper;
import com.skytala.eCommerce.domain.shipment.relations.picklist.model.statusHistory.PicklistStatusHistory;
import com.skytala.eCommerce.domain.shipment.relations.picklist.query.statusHistory.FindPicklistStatusHistorysBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/shipment/picklist/picklistStatusHistorys")
public class PicklistStatusHistoryController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PicklistStatusHistoryController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PicklistStatusHistory
	 * @return a List with the PicklistStatusHistorys
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<PicklistStatusHistory>> findPicklistStatusHistorysBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPicklistStatusHistorysBy query = new FindPicklistStatusHistorysBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PicklistStatusHistory> picklistStatusHistorys =((PicklistStatusHistoryFound) Scheduler.execute(query).data()).getPicklistStatusHistorys();

		return ResponseEntity.ok().body(picklistStatusHistorys);

	}

	/**
	 * creates a new PicklistStatusHistory entry in the ofbiz database
	 * 
	 * @param picklistStatusHistoryToBeAdded
	 *            the PicklistStatusHistory thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PicklistStatusHistory> createPicklistStatusHistory(@RequestBody PicklistStatusHistory picklistStatusHistoryToBeAdded) throws Exception {

		AddPicklistStatusHistory command = new AddPicklistStatusHistory(picklistStatusHistoryToBeAdded);
		PicklistStatusHistory picklistStatusHistory = ((PicklistStatusHistoryAdded) Scheduler.execute(command).data()).getAddedPicklistStatusHistory();
		
		if (picklistStatusHistory != null) 
			return successful(picklistStatusHistory);
		else 
			return conflict(null);
	}

	/**
	 * Updates the PicklistStatusHistory with the specific Id
	 * 
	 * @param picklistStatusHistoryToBeUpdated
	 *            the PicklistStatusHistory thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updatePicklistStatusHistory(@RequestBody PicklistStatusHistory picklistStatusHistoryToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		picklistStatusHistoryToBeUpdated.setnull(null);

		UpdatePicklistStatusHistory command = new UpdatePicklistStatusHistory(picklistStatusHistoryToBeUpdated);

		try {
			if(((PicklistStatusHistoryUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{picklistStatusHistoryId}")
	public ResponseEntity<PicklistStatusHistory> findById(@PathVariable String picklistStatusHistoryId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("picklistStatusHistoryId", picklistStatusHistoryId);
		try {

			List<PicklistStatusHistory> foundPicklistStatusHistory = findPicklistStatusHistorysBy(requestParams).getBody();
			if(foundPicklistStatusHistory.size()==1){				return successful(foundPicklistStatusHistory.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{picklistStatusHistoryId}")
	public ResponseEntity<String> deletePicklistStatusHistoryByIdUpdated(@PathVariable String picklistStatusHistoryId) throws Exception {
		DeletePicklistStatusHistory command = new DeletePicklistStatusHistory(picklistStatusHistoryId);

		try {
			if (((PicklistStatusHistoryDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
