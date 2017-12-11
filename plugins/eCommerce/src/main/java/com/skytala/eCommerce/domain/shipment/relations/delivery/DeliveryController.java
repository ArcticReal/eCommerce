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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findDeliverysBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindDeliverysBy query = new FindDeliverysBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<Delivery> deliverys =((DeliveryFound) Scheduler.execute(query).data()).getDeliverys();

		if (deliverys.size() == 1) {
			return ResponseEntity.ok().body(deliverys.get(0));
		}

		return ResponseEntity.ok().body(deliverys);

	}

	/**
	 * 
	 * this method will only be called by Springs DispatcherServlet
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createDelivery(HttpServletRequest request) throws Exception {

		Delivery deliveryToBeAdded = new Delivery();
		try {
			deliveryToBeAdded = DeliveryMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createDelivery(deliveryToBeAdded);

	}

	/**
	 * creates a new Delivery entry in the ofbiz database
	 * 
	 * @param deliveryToBeAdded
	 *            the Delivery thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createDelivery(@RequestBody Delivery deliveryToBeAdded) throws Exception {

		AddDelivery command = new AddDelivery(deliveryToBeAdded);
		Delivery delivery = ((DeliveryAdded) Scheduler.execute(command).data()).getAddedDelivery();
		
		if (delivery != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(delivery);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("Delivery could not be created.");
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
	@RequestMapping(method = RequestMethod.PUT, value = "/update", consumes = "application/x-www-form-urlencoded")
	public boolean updateDelivery(HttpServletRequest request) throws Exception {

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

		Delivery deliveryToBeUpdated = new Delivery();

		try {
			deliveryToBeUpdated = DeliveryMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateDelivery(deliveryToBeUpdated, deliveryToBeUpdated.getDeliveryId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateDelivery(@RequestBody Delivery deliveryToBeUpdated,
			@PathVariable String deliveryId) throws Exception {

		deliveryToBeUpdated.setDeliveryId(deliveryId);

		UpdateDelivery command = new UpdateDelivery(deliveryToBeUpdated);

		try {
			if(((DeliveryUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{deliveryId}")
	public ResponseEntity<Object> findById(@PathVariable String deliveryId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("deliveryId", deliveryId);
		try {

			Object foundDelivery = findDeliverysBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundDelivery);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{deliveryId}")
	public ResponseEntity<Object> deleteDeliveryByIdUpdated(@PathVariable String deliveryId) throws Exception {
		DeleteDelivery command = new DeleteDelivery(deliveryId);

		try {
			if (((DeliveryDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("Delivery could not be deleted");

	}

}
