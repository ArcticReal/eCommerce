package com.skytala.eCommerce.domain.shipment.relations.shipment.control.receipt;

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
import com.skytala.eCommerce.domain.shipment.relations.shipment.command.receipt.AddShipmentReceipt;
import com.skytala.eCommerce.domain.shipment.relations.shipment.command.receipt.DeleteShipmentReceipt;
import com.skytala.eCommerce.domain.shipment.relations.shipment.command.receipt.UpdateShipmentReceipt;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.receipt.ShipmentReceiptAdded;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.receipt.ShipmentReceiptDeleted;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.receipt.ShipmentReceiptFound;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.receipt.ShipmentReceiptUpdated;
import com.skytala.eCommerce.domain.shipment.relations.shipment.mapper.receipt.ShipmentReceiptMapper;
import com.skytala.eCommerce.domain.shipment.relations.shipment.model.receipt.ShipmentReceipt;
import com.skytala.eCommerce.domain.shipment.relations.shipment.query.receipt.FindShipmentReceiptsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/shipment/shipment/shipmentReceipts")
public class ShipmentReceiptController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ShipmentReceiptController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ShipmentReceipt
	 * @return a List with the ShipmentReceipts
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ShipmentReceipt>> findShipmentReceiptsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindShipmentReceiptsBy query = new FindShipmentReceiptsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ShipmentReceipt> shipmentReceipts =((ShipmentReceiptFound) Scheduler.execute(query).data()).getShipmentReceipts();

		return ResponseEntity.ok().body(shipmentReceipts);

	}

	/**
	 * creates a new ShipmentReceipt entry in the ofbiz database
	 * 
	 * @param shipmentReceiptToBeAdded
	 *            the ShipmentReceipt thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ShipmentReceipt> createShipmentReceipt(@RequestBody ShipmentReceipt shipmentReceiptToBeAdded) throws Exception {

		AddShipmentReceipt command = new AddShipmentReceipt(shipmentReceiptToBeAdded);
		ShipmentReceipt shipmentReceipt = ((ShipmentReceiptAdded) Scheduler.execute(command).data()).getAddedShipmentReceipt();
		
		if (shipmentReceipt != null) 
			return successful(shipmentReceipt);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ShipmentReceipt with the specific Id
	 * 
	 * @param shipmentReceiptToBeUpdated
	 *            the ShipmentReceipt thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{receiptId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateShipmentReceipt(@RequestBody ShipmentReceipt shipmentReceiptToBeUpdated,
			@PathVariable String receiptId) throws Exception {

		shipmentReceiptToBeUpdated.setReceiptId(receiptId);

		UpdateShipmentReceipt command = new UpdateShipmentReceipt(shipmentReceiptToBeUpdated);

		try {
			if(((ShipmentReceiptUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{shipmentReceiptId}")
	public ResponseEntity<ShipmentReceipt> findById(@PathVariable String shipmentReceiptId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("shipmentReceiptId", shipmentReceiptId);
		try {

			List<ShipmentReceipt> foundShipmentReceipt = findShipmentReceiptsBy(requestParams).getBody();
			if(foundShipmentReceipt.size()==1){				return successful(foundShipmentReceipt.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{shipmentReceiptId}")
	public ResponseEntity<String> deleteShipmentReceiptByIdUpdated(@PathVariable String shipmentReceiptId) throws Exception {
		DeleteShipmentReceipt command = new DeleteShipmentReceipt(shipmentReceiptId);

		try {
			if (((ShipmentReceiptDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
