package com.skytala.eCommerce.domain.order.relations.returnItem.control.shipment;

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
import com.skytala.eCommerce.domain.order.relations.returnItem.command.shipment.AddReturnItemShipment;
import com.skytala.eCommerce.domain.order.relations.returnItem.command.shipment.DeleteReturnItemShipment;
import com.skytala.eCommerce.domain.order.relations.returnItem.command.shipment.UpdateReturnItemShipment;
import com.skytala.eCommerce.domain.order.relations.returnItem.event.shipment.ReturnItemShipmentAdded;
import com.skytala.eCommerce.domain.order.relations.returnItem.event.shipment.ReturnItemShipmentDeleted;
import com.skytala.eCommerce.domain.order.relations.returnItem.event.shipment.ReturnItemShipmentFound;
import com.skytala.eCommerce.domain.order.relations.returnItem.event.shipment.ReturnItemShipmentUpdated;
import com.skytala.eCommerce.domain.order.relations.returnItem.mapper.shipment.ReturnItemShipmentMapper;
import com.skytala.eCommerce.domain.order.relations.returnItem.model.shipment.ReturnItemShipment;
import com.skytala.eCommerce.domain.order.relations.returnItem.query.shipment.FindReturnItemShipmentsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/order/returnItem/returnItemShipments")
public class ReturnItemShipmentController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ReturnItemShipmentController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ReturnItemShipment
	 * @return a List with the ReturnItemShipments
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ReturnItemShipment>> findReturnItemShipmentsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindReturnItemShipmentsBy query = new FindReturnItemShipmentsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ReturnItemShipment> returnItemShipments =((ReturnItemShipmentFound) Scheduler.execute(query).data()).getReturnItemShipments();

		return ResponseEntity.ok().body(returnItemShipments);

	}

	/**
	 * creates a new ReturnItemShipment entry in the ofbiz database
	 * 
	 * @param returnItemShipmentToBeAdded
	 *            the ReturnItemShipment thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ReturnItemShipment> createReturnItemShipment(@RequestBody ReturnItemShipment returnItemShipmentToBeAdded) throws Exception {

		AddReturnItemShipment command = new AddReturnItemShipment(returnItemShipmentToBeAdded);
		ReturnItemShipment returnItemShipment = ((ReturnItemShipmentAdded) Scheduler.execute(command).data()).getAddedReturnItemShipment();
		
		if (returnItemShipment != null) 
			return successful(returnItemShipment);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ReturnItemShipment with the specific Id
	 * 
	 * @param returnItemShipmentToBeUpdated
	 *            the ReturnItemShipment thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateReturnItemShipment(@RequestBody ReturnItemShipment returnItemShipmentToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		returnItemShipmentToBeUpdated.setnull(null);

		UpdateReturnItemShipment command = new UpdateReturnItemShipment(returnItemShipmentToBeUpdated);

		try {
			if(((ReturnItemShipmentUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{returnItemShipmentId}")
	public ResponseEntity<ReturnItemShipment> findById(@PathVariable String returnItemShipmentId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("returnItemShipmentId", returnItemShipmentId);
		try {

			List<ReturnItemShipment> foundReturnItemShipment = findReturnItemShipmentsBy(requestParams).getBody();
			if(foundReturnItemShipment.size()==1){				return successful(foundReturnItemShipment.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{returnItemShipmentId}")
	public ResponseEntity<String> deleteReturnItemShipmentByIdUpdated(@PathVariable String returnItemShipmentId) throws Exception {
		DeleteReturnItemShipment command = new DeleteReturnItemShipment(returnItemShipmentId);

		try {
			if (((ReturnItemShipmentDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
