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
	public ResponseEntity<Object> findPicklistStatusHistorysBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPicklistStatusHistorysBy query = new FindPicklistStatusHistorysBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PicklistStatusHistory> picklistStatusHistorys =((PicklistStatusHistoryFound) Scheduler.execute(query).data()).getPicklistStatusHistorys();

		if (picklistStatusHistorys.size() == 1) {
			return ResponseEntity.ok().body(picklistStatusHistorys.get(0));
		}

		return ResponseEntity.ok().body(picklistStatusHistorys);

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
	public ResponseEntity<Object> createPicklistStatusHistory(HttpServletRequest request) throws Exception {

		PicklistStatusHistory picklistStatusHistoryToBeAdded = new PicklistStatusHistory();
		try {
			picklistStatusHistoryToBeAdded = PicklistStatusHistoryMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createPicklistStatusHistory(picklistStatusHistoryToBeAdded);

	}

	/**
	 * creates a new PicklistStatusHistory entry in the ofbiz database
	 * 
	 * @param picklistStatusHistoryToBeAdded
	 *            the PicklistStatusHistory thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createPicklistStatusHistory(@RequestBody PicklistStatusHistory picklistStatusHistoryToBeAdded) throws Exception {

		AddPicklistStatusHistory command = new AddPicklistStatusHistory(picklistStatusHistoryToBeAdded);
		PicklistStatusHistory picklistStatusHistory = ((PicklistStatusHistoryAdded) Scheduler.execute(command).data()).getAddedPicklistStatusHistory();
		
		if (picklistStatusHistory != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(picklistStatusHistory);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("PicklistStatusHistory could not be created.");
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
	@PutMapping(value = "/update", consumes = "application/x-www-form-urlencoded")
	public boolean updatePicklistStatusHistory(HttpServletRequest request) throws Exception {

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

		PicklistStatusHistory picklistStatusHistoryToBeUpdated = new PicklistStatusHistory();

		try {
			picklistStatusHistoryToBeUpdated = PicklistStatusHistoryMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updatePicklistStatusHistory(picklistStatusHistoryToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updatePicklistStatusHistory(@RequestBody PicklistStatusHistory picklistStatusHistoryToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		picklistStatusHistoryToBeUpdated.setnull(null);

		UpdatePicklistStatusHistory command = new UpdatePicklistStatusHistory(picklistStatusHistoryToBeUpdated);

		try {
			if(((PicklistStatusHistoryUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{picklistStatusHistoryId}")
	public ResponseEntity<Object> findById(@PathVariable String picklistStatusHistoryId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("picklistStatusHistoryId", picklistStatusHistoryId);
		try {

			Object foundPicklistStatusHistory = findPicklistStatusHistorysBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundPicklistStatusHistory);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{picklistStatusHistoryId}")
	public ResponseEntity<Object> deletePicklistStatusHistoryByIdUpdated(@PathVariable String picklistStatusHistoryId) throws Exception {
		DeletePicklistStatusHistory command = new DeletePicklistStatusHistory(picklistStatusHistoryId);

		try {
			if (((PicklistStatusHistoryDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("PicklistStatusHistory could not be deleted");

	}

}
