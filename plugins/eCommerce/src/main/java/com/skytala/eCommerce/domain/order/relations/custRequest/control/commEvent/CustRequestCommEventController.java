package com.skytala.eCommerce.domain.order.relations.custRequest.control.commEvent;

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
import com.skytala.eCommerce.domain.order.relations.custRequest.command.commEvent.AddCustRequestCommEvent;
import com.skytala.eCommerce.domain.order.relations.custRequest.command.commEvent.DeleteCustRequestCommEvent;
import com.skytala.eCommerce.domain.order.relations.custRequest.command.commEvent.UpdateCustRequestCommEvent;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.commEvent.CustRequestCommEventAdded;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.commEvent.CustRequestCommEventDeleted;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.commEvent.CustRequestCommEventFound;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.commEvent.CustRequestCommEventUpdated;
import com.skytala.eCommerce.domain.order.relations.custRequest.mapper.commEvent.CustRequestCommEventMapper;
import com.skytala.eCommerce.domain.order.relations.custRequest.model.commEvent.CustRequestCommEvent;
import com.skytala.eCommerce.domain.order.relations.custRequest.query.commEvent.FindCustRequestCommEventsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/order/custRequest/custRequestCommEvents")
public class CustRequestCommEventController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public CustRequestCommEventController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a CustRequestCommEvent
	 * @return a List with the CustRequestCommEvents
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findCustRequestCommEventsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindCustRequestCommEventsBy query = new FindCustRequestCommEventsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<CustRequestCommEvent> custRequestCommEvents =((CustRequestCommEventFound) Scheduler.execute(query).data()).getCustRequestCommEvents();

		if (custRequestCommEvents.size() == 1) {
			return ResponseEntity.ok().body(custRequestCommEvents.get(0));
		}

		return ResponseEntity.ok().body(custRequestCommEvents);

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
	public ResponseEntity<Object> createCustRequestCommEvent(HttpServletRequest request) throws Exception {

		CustRequestCommEvent custRequestCommEventToBeAdded = new CustRequestCommEvent();
		try {
			custRequestCommEventToBeAdded = CustRequestCommEventMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createCustRequestCommEvent(custRequestCommEventToBeAdded);

	}

	/**
	 * creates a new CustRequestCommEvent entry in the ofbiz database
	 * 
	 * @param custRequestCommEventToBeAdded
	 *            the CustRequestCommEvent thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createCustRequestCommEvent(@RequestBody CustRequestCommEvent custRequestCommEventToBeAdded) throws Exception {

		AddCustRequestCommEvent command = new AddCustRequestCommEvent(custRequestCommEventToBeAdded);
		CustRequestCommEvent custRequestCommEvent = ((CustRequestCommEventAdded) Scheduler.execute(command).data()).getAddedCustRequestCommEvent();
		
		if (custRequestCommEvent != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(custRequestCommEvent);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("CustRequestCommEvent could not be created.");
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
	public boolean updateCustRequestCommEvent(HttpServletRequest request) throws Exception {

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

		CustRequestCommEvent custRequestCommEventToBeUpdated = new CustRequestCommEvent();

		try {
			custRequestCommEventToBeUpdated = CustRequestCommEventMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateCustRequestCommEvent(custRequestCommEventToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the CustRequestCommEvent with the specific Id
	 * 
	 * @param custRequestCommEventToBeUpdated
	 *            the CustRequestCommEvent thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateCustRequestCommEvent(@RequestBody CustRequestCommEvent custRequestCommEventToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		custRequestCommEventToBeUpdated.setnull(null);

		UpdateCustRequestCommEvent command = new UpdateCustRequestCommEvent(custRequestCommEventToBeUpdated);

		try {
			if(((CustRequestCommEventUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{custRequestCommEventId}")
	public ResponseEntity<Object> findById(@PathVariable String custRequestCommEventId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("custRequestCommEventId", custRequestCommEventId);
		try {

			Object foundCustRequestCommEvent = findCustRequestCommEventsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundCustRequestCommEvent);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{custRequestCommEventId}")
	public ResponseEntity<Object> deleteCustRequestCommEventByIdUpdated(@PathVariable String custRequestCommEventId) throws Exception {
		DeleteCustRequestCommEvent command = new DeleteCustRequestCommEvent(custRequestCommEventId);

		try {
			if (((CustRequestCommEventDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("CustRequestCommEvent could not be deleted");

	}

}
