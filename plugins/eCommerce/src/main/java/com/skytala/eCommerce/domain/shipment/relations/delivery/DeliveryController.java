package com.skytala.eCommerce.domain.shipment.relations.delivery;

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
import com.skytala.eCommerce.domain.shipment.relations.delivery.command.AddDelivery;
import com.skytala.eCommerce.domain.shipment.relations.delivery.command.DeleteDelivery;
import com.skytala.eCommerce.domain.shipment.relations.delivery.command.UpdateDelivery;
import com.skytala.eCommerce.domain.shipment.relations.delivery.event.DeliveryAdded;
import com.skytala.eCommerce.domain.shipment.relations.delivery.event.DeliveryDeleted;
import com.skytala.eCommerce.domain.shipment.relations.delivery.event.DeliveryFound;
import com.skytala.eCommerce.domain.shipment.relations.delivery.event.DeliveryUpdated;
import com.skytala.eCommerce.domain.shipment.relations.delivery.mapper.DeliveryMapper;
import com.skytala.eCommerce.domain.shipment.relations.delivery.model.Delivery;
import com.skytala.eCommerce.domain.shipment.relations.delivery.query.FindDeliverysBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/shipment/deliverys")
public class DeliveryController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public DeliveryController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a Delivery
	 * @return a List with the Deliverys
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<Delivery>> findDeliverysBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindDeliverysBy query = new FindDeliverysBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<Delivery> deliverys =((DeliveryFound) Scheduler.execute(query).data()).getDeliverys();

		return ResponseEntity.ok().body(deliverys);

	}

	/**
	 * creates a new Delivery entry in the ofbiz database
	 * 
	 * @param deliveryToBeAdded
	 *            the Delivery thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Delivery> createDelivery(@RequestBody Delivery deliveryToBeAdded) throws Exception {

		AddDelivery command = new AddDelivery(deliveryToBeAdded);
		Delivery delivery = ((DeliveryAdded) Scheduler.execute(command).data()).getAddedDelivery();
		
		if (delivery != null) 
			return successful(delivery);
		else 
			return conflict(null);
	}

	/**
	 * Updates the Delivery with the specific Id
	 * 
	 * @param deliveryToBeUpdated
	 *            the Delivery thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{deliveryId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateDelivery(@RequestBody Delivery deliveryToBeUpdated,
			@PathVariable String deliveryId) throws Exception {

		deliveryToBeUpdated.setDeliveryId(deliveryId);

		UpdateDelivery command = new UpdateDelivery(deliveryToBeUpdated);

		try {
			if(((DeliveryUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{deliveryId}")
	public ResponseEntity<Delivery> findById(@PathVariable String deliveryId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("deliveryId", deliveryId);
		try {

			List<Delivery> foundDelivery = findDeliverysBy(requestParams).getBody();
			if(foundDelivery.size()==1){				return successful(foundDelivery.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{deliveryId}")
	public ResponseEntity<String> deleteDeliveryByIdUpdated(@PathVariable String deliveryId) throws Exception {
		DeleteDelivery command = new DeleteDelivery(deliveryId);

		try {
			if (((DeliveryDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
