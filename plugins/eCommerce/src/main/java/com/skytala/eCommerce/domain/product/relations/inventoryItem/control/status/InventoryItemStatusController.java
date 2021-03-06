package com.skytala.eCommerce.domain.product.relations.inventoryItem.control.status;

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
import com.skytala.eCommerce.domain.product.relations.inventoryItem.command.status.AddInventoryItemStatus;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.command.status.DeleteInventoryItemStatus;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.command.status.UpdateInventoryItemStatus;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.event.status.InventoryItemStatusAdded;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.event.status.InventoryItemStatusDeleted;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.event.status.InventoryItemStatusFound;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.event.status.InventoryItemStatusUpdated;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.mapper.status.InventoryItemStatusMapper;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.model.status.InventoryItemStatus;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.query.status.FindInventoryItemStatussBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/inventoryItem/inventoryItemStatuss")
public class InventoryItemStatusController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public InventoryItemStatusController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a InventoryItemStatus
	 * @return a List with the InventoryItemStatuss
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<InventoryItemStatus>> findInventoryItemStatussBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindInventoryItemStatussBy query = new FindInventoryItemStatussBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<InventoryItemStatus> inventoryItemStatuss =((InventoryItemStatusFound) Scheduler.execute(query).data()).getInventoryItemStatuss();

		return ResponseEntity.ok().body(inventoryItemStatuss);

	}

	/**
	 * creates a new InventoryItemStatus entry in the ofbiz database
	 * 
	 * @param inventoryItemStatusToBeAdded
	 *            the InventoryItemStatus thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<InventoryItemStatus> createInventoryItemStatus(@RequestBody InventoryItemStatus inventoryItemStatusToBeAdded) throws Exception {

		AddInventoryItemStatus command = new AddInventoryItemStatus(inventoryItemStatusToBeAdded);
		InventoryItemStatus inventoryItemStatus = ((InventoryItemStatusAdded) Scheduler.execute(command).data()).getAddedInventoryItemStatus();
		
		if (inventoryItemStatus != null) 
			return successful(inventoryItemStatus);
		else 
			return conflict(null);
	}

	/**
	 * Updates the InventoryItemStatus with the specific Id
	 * 
	 * @param inventoryItemStatusToBeUpdated
	 *            the InventoryItemStatus thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateInventoryItemStatus(@RequestBody InventoryItemStatus inventoryItemStatusToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		inventoryItemStatusToBeUpdated.setnull(null);

		UpdateInventoryItemStatus command = new UpdateInventoryItemStatus(inventoryItemStatusToBeUpdated);

		try {
			if(((InventoryItemStatusUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{inventoryItemStatusId}")
	public ResponseEntity<InventoryItemStatus> findById(@PathVariable String inventoryItemStatusId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("inventoryItemStatusId", inventoryItemStatusId);
		try {

			List<InventoryItemStatus> foundInventoryItemStatus = findInventoryItemStatussBy(requestParams).getBody();
			if(foundInventoryItemStatus.size()==1){				return successful(foundInventoryItemStatus.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{inventoryItemStatusId}")
	public ResponseEntity<String> deleteInventoryItemStatusByIdUpdated(@PathVariable String inventoryItemStatusId) throws Exception {
		DeleteInventoryItemStatus command = new DeleteInventoryItemStatus(inventoryItemStatusId);

		try {
			if (((InventoryItemStatusDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
