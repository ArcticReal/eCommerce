package com.skytala.eCommerce.domain.product.relations.inventoryTransfer;

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
import com.skytala.eCommerce.domain.product.relations.inventoryTransfer.command.AddInventoryTransfer;
import com.skytala.eCommerce.domain.product.relations.inventoryTransfer.command.DeleteInventoryTransfer;
import com.skytala.eCommerce.domain.product.relations.inventoryTransfer.command.UpdateInventoryTransfer;
import com.skytala.eCommerce.domain.product.relations.inventoryTransfer.event.InventoryTransferAdded;
import com.skytala.eCommerce.domain.product.relations.inventoryTransfer.event.InventoryTransferDeleted;
import com.skytala.eCommerce.domain.product.relations.inventoryTransfer.event.InventoryTransferFound;
import com.skytala.eCommerce.domain.product.relations.inventoryTransfer.event.InventoryTransferUpdated;
import com.skytala.eCommerce.domain.product.relations.inventoryTransfer.mapper.InventoryTransferMapper;
import com.skytala.eCommerce.domain.product.relations.inventoryTransfer.model.InventoryTransfer;
import com.skytala.eCommerce.domain.product.relations.inventoryTransfer.query.FindInventoryTransfersBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/inventoryTransfers")
public class InventoryTransferController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public InventoryTransferController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a InventoryTransfer
	 * @return a List with the InventoryTransfers
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<InventoryTransfer>> findInventoryTransfersBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindInventoryTransfersBy query = new FindInventoryTransfersBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<InventoryTransfer> inventoryTransfers =((InventoryTransferFound) Scheduler.execute(query).data()).getInventoryTransfers();

		return ResponseEntity.ok().body(inventoryTransfers);

	}

	/**
	 * creates a new InventoryTransfer entry in the ofbiz database
	 * 
	 * @param inventoryTransferToBeAdded
	 *            the InventoryTransfer thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<InventoryTransfer> createInventoryTransfer(@RequestBody InventoryTransfer inventoryTransferToBeAdded) throws Exception {

		AddInventoryTransfer command = new AddInventoryTransfer(inventoryTransferToBeAdded);
		InventoryTransfer inventoryTransfer = ((InventoryTransferAdded) Scheduler.execute(command).data()).getAddedInventoryTransfer();
		
		if (inventoryTransfer != null) 
			return successful(inventoryTransfer);
		else 
			return conflict(null);
	}

	/**
	 * Updates the InventoryTransfer with the specific Id
	 * 
	 * @param inventoryTransferToBeUpdated
	 *            the InventoryTransfer thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{inventoryTransferId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateInventoryTransfer(@RequestBody InventoryTransfer inventoryTransferToBeUpdated,
			@PathVariable String inventoryTransferId) throws Exception {

		inventoryTransferToBeUpdated.setInventoryTransferId(inventoryTransferId);

		UpdateInventoryTransfer command = new UpdateInventoryTransfer(inventoryTransferToBeUpdated);

		try {
			if(((InventoryTransferUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{inventoryTransferId}")
	public ResponseEntity<InventoryTransfer> findById(@PathVariable String inventoryTransferId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("inventoryTransferId", inventoryTransferId);
		try {

			List<InventoryTransfer> foundInventoryTransfer = findInventoryTransfersBy(requestParams).getBody();
			if(foundInventoryTransfer.size()==1){				return successful(foundInventoryTransfer.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{inventoryTransferId}")
	public ResponseEntity<String> deleteInventoryTransferByIdUpdated(@PathVariable String inventoryTransferId) throws Exception {
		DeleteInventoryTransfer command = new DeleteInventoryTransfer(inventoryTransferId);

		try {
			if (((InventoryTransferDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
