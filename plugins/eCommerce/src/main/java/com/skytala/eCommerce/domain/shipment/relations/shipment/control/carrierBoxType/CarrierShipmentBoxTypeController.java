package com.skytala.eCommerce.domain.shipment.relations.shipment.control.carrierBoxType;

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
import com.skytala.eCommerce.domain.shipment.relations.shipment.command.carrierBoxType.AddCarrierShipmentBoxType;
import com.skytala.eCommerce.domain.shipment.relations.shipment.command.carrierBoxType.DeleteCarrierShipmentBoxType;
import com.skytala.eCommerce.domain.shipment.relations.shipment.command.carrierBoxType.UpdateCarrierShipmentBoxType;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.carrierBoxType.CarrierShipmentBoxTypeAdded;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.carrierBoxType.CarrierShipmentBoxTypeDeleted;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.carrierBoxType.CarrierShipmentBoxTypeFound;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.carrierBoxType.CarrierShipmentBoxTypeUpdated;
import com.skytala.eCommerce.domain.shipment.relations.shipment.mapper.carrierBoxType.CarrierShipmentBoxTypeMapper;
import com.skytala.eCommerce.domain.shipment.relations.shipment.model.carrierBoxType.CarrierShipmentBoxType;
import com.skytala.eCommerce.domain.shipment.relations.shipment.query.carrierBoxType.FindCarrierShipmentBoxTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/shipment/shipment/carrierShipmentBoxTypes")
public class CarrierShipmentBoxTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public CarrierShipmentBoxTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a CarrierShipmentBoxType
	 * @return a List with the CarrierShipmentBoxTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<CarrierShipmentBoxType>> findCarrierShipmentBoxTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindCarrierShipmentBoxTypesBy query = new FindCarrierShipmentBoxTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<CarrierShipmentBoxType> carrierShipmentBoxTypes =((CarrierShipmentBoxTypeFound) Scheduler.execute(query).data()).getCarrierShipmentBoxTypes();

		return ResponseEntity.ok().body(carrierShipmentBoxTypes);

	}

	/**
	 * creates a new CarrierShipmentBoxType entry in the ofbiz database
	 * 
	 * @param carrierShipmentBoxTypeToBeAdded
	 *            the CarrierShipmentBoxType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<CarrierShipmentBoxType> createCarrierShipmentBoxType(@RequestBody CarrierShipmentBoxType carrierShipmentBoxTypeToBeAdded) throws Exception {

		AddCarrierShipmentBoxType command = new AddCarrierShipmentBoxType(carrierShipmentBoxTypeToBeAdded);
		CarrierShipmentBoxType carrierShipmentBoxType = ((CarrierShipmentBoxTypeAdded) Scheduler.execute(command).data()).getAddedCarrierShipmentBoxType();
		
		if (carrierShipmentBoxType != null) 
			return successful(carrierShipmentBoxType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the CarrierShipmentBoxType with the specific Id
	 * 
	 * @param carrierShipmentBoxTypeToBeUpdated
	 *            the CarrierShipmentBoxType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateCarrierShipmentBoxType(@RequestBody CarrierShipmentBoxType carrierShipmentBoxTypeToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		carrierShipmentBoxTypeToBeUpdated.setnull(null);

		UpdateCarrierShipmentBoxType command = new UpdateCarrierShipmentBoxType(carrierShipmentBoxTypeToBeUpdated);

		try {
			if(((CarrierShipmentBoxTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{carrierShipmentBoxTypeId}")
	public ResponseEntity<CarrierShipmentBoxType> findById(@PathVariable String carrierShipmentBoxTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("carrierShipmentBoxTypeId", carrierShipmentBoxTypeId);
		try {

			List<CarrierShipmentBoxType> foundCarrierShipmentBoxType = findCarrierShipmentBoxTypesBy(requestParams).getBody();
			if(foundCarrierShipmentBoxType.size()==1){				return successful(foundCarrierShipmentBoxType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{carrierShipmentBoxTypeId}")
	public ResponseEntity<String> deleteCarrierShipmentBoxTypeByIdUpdated(@PathVariable String carrierShipmentBoxTypeId) throws Exception {
		DeleteCarrierShipmentBoxType command = new DeleteCarrierShipmentBoxType(carrierShipmentBoxTypeId);

		try {
			if (((CarrierShipmentBoxTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
