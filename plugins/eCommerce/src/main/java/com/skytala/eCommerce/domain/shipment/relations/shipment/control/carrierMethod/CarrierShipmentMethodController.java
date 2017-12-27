package com.skytala.eCommerce.domain.shipment.relations.shipment.control.carrierMethod;

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
import com.skytala.eCommerce.domain.shipment.relations.shipment.command.carrierMethod.AddCarrierShipmentMethod;
import com.skytala.eCommerce.domain.shipment.relations.shipment.command.carrierMethod.DeleteCarrierShipmentMethod;
import com.skytala.eCommerce.domain.shipment.relations.shipment.command.carrierMethod.UpdateCarrierShipmentMethod;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.carrierMethod.CarrierShipmentMethodAdded;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.carrierMethod.CarrierShipmentMethodDeleted;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.carrierMethod.CarrierShipmentMethodFound;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.carrierMethod.CarrierShipmentMethodUpdated;
import com.skytala.eCommerce.domain.shipment.relations.shipment.mapper.carrierMethod.CarrierShipmentMethodMapper;
import com.skytala.eCommerce.domain.shipment.relations.shipment.model.carrierMethod.CarrierShipmentMethod;
import com.skytala.eCommerce.domain.shipment.relations.shipment.query.carrierMethod.FindCarrierShipmentMethodsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/shipment/shipment/carrierShipmentMethods")
public class CarrierShipmentMethodController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public CarrierShipmentMethodController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a CarrierShipmentMethod
	 * @return a List with the CarrierShipmentMethods
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<CarrierShipmentMethod>> findCarrierShipmentMethodsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindCarrierShipmentMethodsBy query = new FindCarrierShipmentMethodsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<CarrierShipmentMethod> carrierShipmentMethods =((CarrierShipmentMethodFound) Scheduler.execute(query).data()).getCarrierShipmentMethods();

		return ResponseEntity.ok().body(carrierShipmentMethods);

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
	public ResponseEntity<CarrierShipmentMethod> createCarrierShipmentMethod(HttpServletRequest request) throws Exception {

		CarrierShipmentMethod carrierShipmentMethodToBeAdded = new CarrierShipmentMethod();
		try {
			carrierShipmentMethodToBeAdded = CarrierShipmentMethodMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createCarrierShipmentMethod(carrierShipmentMethodToBeAdded);

	}

	/**
	 * creates a new CarrierShipmentMethod entry in the ofbiz database
	 * 
	 * @param carrierShipmentMethodToBeAdded
	 *            the CarrierShipmentMethod thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<CarrierShipmentMethod> createCarrierShipmentMethod(@RequestBody CarrierShipmentMethod carrierShipmentMethodToBeAdded) throws Exception {

		AddCarrierShipmentMethod command = new AddCarrierShipmentMethod(carrierShipmentMethodToBeAdded);
		CarrierShipmentMethod carrierShipmentMethod = ((CarrierShipmentMethodAdded) Scheduler.execute(command).data()).getAddedCarrierShipmentMethod();
		
		if (carrierShipmentMethod != null) 
			return successful(carrierShipmentMethod);
		else 
			return conflict(null);
	}

	/**
	 * Updates the CarrierShipmentMethod with the specific Id
	 * 
	 * @param carrierShipmentMethodToBeUpdated
	 *            the CarrierShipmentMethod thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{roleTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateCarrierShipmentMethod(@RequestBody CarrierShipmentMethod carrierShipmentMethodToBeUpdated,
			@PathVariable String roleTypeId) throws Exception {

		carrierShipmentMethodToBeUpdated.setRoleTypeId(roleTypeId);

		UpdateCarrierShipmentMethod command = new UpdateCarrierShipmentMethod(carrierShipmentMethodToBeUpdated);

		try {
			if(((CarrierShipmentMethodUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{carrierShipmentMethodId}")
	public ResponseEntity<CarrierShipmentMethod> findById(@PathVariable String carrierShipmentMethodId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("carrierShipmentMethodId", carrierShipmentMethodId);
		try {

			List<CarrierShipmentMethod> foundCarrierShipmentMethod = findCarrierShipmentMethodsBy(requestParams).getBody();
			if(foundCarrierShipmentMethod.size()==1){				return successful(foundCarrierShipmentMethod.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{carrierShipmentMethodId}")
	public ResponseEntity<String> deleteCarrierShipmentMethodByIdUpdated(@PathVariable String carrierShipmentMethodId) throws Exception {
		DeleteCarrierShipmentMethod command = new DeleteCarrierShipmentMethod(carrierShipmentMethodId);

		try {
			if (((CarrierShipmentMethodDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
