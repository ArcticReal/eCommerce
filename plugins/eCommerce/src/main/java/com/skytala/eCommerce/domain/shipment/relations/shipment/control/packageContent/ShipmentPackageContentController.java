package com.skytala.eCommerce.domain.shipment.relations.shipment.control.packageContent;

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
import com.skytala.eCommerce.domain.shipment.relations.shipment.command.packageContent.AddShipmentPackageContent;
import com.skytala.eCommerce.domain.shipment.relations.shipment.command.packageContent.DeleteShipmentPackageContent;
import com.skytala.eCommerce.domain.shipment.relations.shipment.command.packageContent.UpdateShipmentPackageContent;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.packageContent.ShipmentPackageContentAdded;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.packageContent.ShipmentPackageContentDeleted;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.packageContent.ShipmentPackageContentFound;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.packageContent.ShipmentPackageContentUpdated;
import com.skytala.eCommerce.domain.shipment.relations.shipment.mapper.packageContent.ShipmentPackageContentMapper;
import com.skytala.eCommerce.domain.shipment.relations.shipment.model.packageContent.ShipmentPackageContent;
import com.skytala.eCommerce.domain.shipment.relations.shipment.query.packageContent.FindShipmentPackageContentsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/shipment/shipment/shipmentPackageContents")
public class ShipmentPackageContentController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ShipmentPackageContentController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ShipmentPackageContent
	 * @return a List with the ShipmentPackageContents
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ShipmentPackageContent>> findShipmentPackageContentsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindShipmentPackageContentsBy query = new FindShipmentPackageContentsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ShipmentPackageContent> shipmentPackageContents =((ShipmentPackageContentFound) Scheduler.execute(query).data()).getShipmentPackageContents();

		return ResponseEntity.ok().body(shipmentPackageContents);

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
	public ResponseEntity<ShipmentPackageContent> createShipmentPackageContent(HttpServletRequest request) throws Exception {

		ShipmentPackageContent shipmentPackageContentToBeAdded = new ShipmentPackageContent();
		try {
			shipmentPackageContentToBeAdded = ShipmentPackageContentMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createShipmentPackageContent(shipmentPackageContentToBeAdded);

	}

	/**
	 * creates a new ShipmentPackageContent entry in the ofbiz database
	 * 
	 * @param shipmentPackageContentToBeAdded
	 *            the ShipmentPackageContent thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ShipmentPackageContent> createShipmentPackageContent(@RequestBody ShipmentPackageContent shipmentPackageContentToBeAdded) throws Exception {

		AddShipmentPackageContent command = new AddShipmentPackageContent(shipmentPackageContentToBeAdded);
		ShipmentPackageContent shipmentPackageContent = ((ShipmentPackageContentAdded) Scheduler.execute(command).data()).getAddedShipmentPackageContent();
		
		if (shipmentPackageContent != null) 
			return successful(shipmentPackageContent);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ShipmentPackageContent with the specific Id
	 * 
	 * @param shipmentPackageContentToBeUpdated
	 *            the ShipmentPackageContent thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateShipmentPackageContent(@RequestBody ShipmentPackageContent shipmentPackageContentToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		shipmentPackageContentToBeUpdated.setnull(null);

		UpdateShipmentPackageContent command = new UpdateShipmentPackageContent(shipmentPackageContentToBeUpdated);

		try {
			if(((ShipmentPackageContentUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{shipmentPackageContentId}")
	public ResponseEntity<ShipmentPackageContent> findById(@PathVariable String shipmentPackageContentId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("shipmentPackageContentId", shipmentPackageContentId);
		try {

			List<ShipmentPackageContent> foundShipmentPackageContent = findShipmentPackageContentsBy(requestParams).getBody();
			if(foundShipmentPackageContent.size()==1){				return successful(foundShipmentPackageContent.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{shipmentPackageContentId}")
	public ResponseEntity<String> deleteShipmentPackageContentByIdUpdated(@PathVariable String shipmentPackageContentId) throws Exception {
		DeleteShipmentPackageContent command = new DeleteShipmentPackageContent(shipmentPackageContentId);

		try {
			if (((ShipmentPackageContentDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
