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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	public ResponseEntity<List<CustRequestCommEvent>> findCustRequestCommEventsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindCustRequestCommEventsBy query = new FindCustRequestCommEventsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<CustRequestCommEvent> custRequestCommEvents =((CustRequestCommEventFound) Scheduler.execute(query).data()).getCustRequestCommEvents();

		return ResponseEntity.ok().body(custRequestCommEvents);

	}

	/**
	 * creates a new CustRequestCommEvent entry in the ofbiz database
	 * 
	 * @param custRequestCommEventToBeAdded
	 *            the CustRequestCommEvent thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<CustRequestCommEvent> createCustRequestCommEvent(@RequestBody CustRequestCommEvent custRequestCommEventToBeAdded) throws Exception {

		AddCustRequestCommEvent command = new AddCustRequestCommEvent(custRequestCommEventToBeAdded);
		CustRequestCommEvent custRequestCommEvent = ((CustRequestCommEventAdded) Scheduler.execute(command).data()).getAddedCustRequestCommEvent();
		
		if (custRequestCommEvent != null) 
			return successful(custRequestCommEvent);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateCustRequestCommEvent(@RequestBody CustRequestCommEvent custRequestCommEventToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		custRequestCommEventToBeUpdated.setnull(null);

		UpdateCustRequestCommEvent command = new UpdateCustRequestCommEvent(custRequestCommEventToBeUpdated);

		try {
			if(((CustRequestCommEventUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{custRequestCommEventId}")
	public ResponseEntity<CustRequestCommEvent> findById(@PathVariable String custRequestCommEventId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("custRequestCommEventId", custRequestCommEventId);
		try {

			List<CustRequestCommEvent> foundCustRequestCommEvent = findCustRequestCommEventsBy(requestParams).getBody();
			if(foundCustRequestCommEvent.size()==1){				return successful(foundCustRequestCommEvent.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{custRequestCommEventId}")
	public ResponseEntity<String> deleteCustRequestCommEventByIdUpdated(@PathVariable String custRequestCommEventId) throws Exception {
		DeleteCustRequestCommEvent command = new DeleteCustRequestCommEvent(custRequestCommEventId);

		try {
			if (((CustRequestCommEventDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
