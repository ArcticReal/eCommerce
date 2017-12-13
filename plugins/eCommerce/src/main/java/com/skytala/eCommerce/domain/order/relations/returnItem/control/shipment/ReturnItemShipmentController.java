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
	public ResponseEntity<Object> findReturnItemShipmentsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindReturnItemShipmentsBy query = new FindReturnItemShipmentsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ReturnItemShipment> returnItemShipments =((ReturnItemShipmentFound) Scheduler.execute(query).data()).getReturnItemShipments();

		if (returnItemShipments.size() == 1) {
			return ResponseEntity.ok().body(returnItemShipments.get(0));
		}

		return ResponseEntity.ok().body(returnItemShipments);

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
	public ResponseEntity<Object> createReturnItemShipment(HttpServletRequest request) throws Exception {

		ReturnItemShipment returnItemShipmentToBeAdded = new ReturnItemShipment();
		try {
			returnItemShipmentToBeAdded = ReturnItemShipmentMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createReturnItemShipment(returnItemShipmentToBeAdded);

	}

	/**
	 * creates a new ReturnItemShipment entry in the ofbiz database
	 * 
	 * @param returnItemShipmentToBeAdded
	 *            the ReturnItemShipment thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createReturnItemShipment(@RequestBody ReturnItemShipment returnItemShipmentToBeAdded) throws Exception {

		AddReturnItemShipment command = new AddReturnItemShipment(returnItemShipmentToBeAdded);
		ReturnItemShipment returnItemShipment = ((ReturnItemShipmentAdded) Scheduler.execute(command).data()).getAddedReturnItemShipment();
		
		if (returnItemShipment != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(returnItemShipment);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ReturnItemShipment could not be created.");
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
	public boolean updateReturnItemShipment(HttpServletRequest request) throws Exception {

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

		ReturnItemShipment returnItemShipmentToBeUpdated = new ReturnItemShipment();

		try {
			returnItemShipmentToBeUpdated = ReturnItemShipmentMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateReturnItemShipment(returnItemShipmentToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateReturnItemShipment(@RequestBody ReturnItemShipment returnItemShipmentToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		returnItemShipmentToBeUpdated.setnull(null);

		UpdateReturnItemShipment command = new UpdateReturnItemShipment(returnItemShipmentToBeUpdated);

		try {
			if(((ReturnItemShipmentUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{returnItemShipmentId}")
	public ResponseEntity<Object> findById(@PathVariable String returnItemShipmentId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("returnItemShipmentId", returnItemShipmentId);
		try {

			Object foundReturnItemShipment = findReturnItemShipmentsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundReturnItemShipment);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{returnItemShipmentId}")
	public ResponseEntity<Object> deleteReturnItemShipmentByIdUpdated(@PathVariable String returnItemShipmentId) throws Exception {
		DeleteReturnItemShipment command = new DeleteReturnItemShipment(returnItemShipmentId);

		try {
			if (((ReturnItemShipmentDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ReturnItemShipment could not be deleted");

	}

}
