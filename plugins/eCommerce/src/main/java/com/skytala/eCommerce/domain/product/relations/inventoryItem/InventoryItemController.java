package com.skytala.eCommerce.domain.product.relations.inventoryItem;

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
import com.skytala.eCommerce.domain.product.relations.inventoryItem.command.AddInventoryItem;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.command.DeleteInventoryItem;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.command.UpdateInventoryItem;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.event.InventoryItemAdded;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.event.InventoryItemDeleted;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.event.InventoryItemFound;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.event.InventoryItemUpdated;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.mapper.InventoryItemMapper;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.model.InventoryItem;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.query.FindInventoryItemsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/inventoryItems")
public class InventoryItemController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public InventoryItemController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a InventoryItem
	 * @return a List with the InventoryItems
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<InventoryItem>> findInventoryItemsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindInventoryItemsBy query = new FindInventoryItemsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<InventoryItem> inventoryItems =((InventoryItemFound) Scheduler.execute(query).data()).getInventoryItems();

		return ResponseEntity.ok().body(inventoryItems);

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
	public ResponseEntity<InventoryItem> createInventoryItem(HttpServletRequest request) throws Exception {

		InventoryItem inventoryItemToBeAdded = new InventoryItem();
		try {
			inventoryItemToBeAdded = InventoryItemMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createInventoryItem(inventoryItemToBeAdded);

	}

	/**
	 * creates a new InventoryItem entry in the ofbiz database
	 * 
	 * @param inventoryItemToBeAdded
	 *            the InventoryItem thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<InventoryItem> createInventoryItem(@RequestBody InventoryItem inventoryItemToBeAdded) throws Exception {

		AddInventoryItem command = new AddInventoryItem(inventoryItemToBeAdded);
		InventoryItem inventoryItem = ((InventoryItemAdded) Scheduler.execute(command).data()).getAddedInventoryItem();
		
		if (inventoryItem != null) 
			return successful(inventoryItem);
		else 
			return conflict(null);
	}

	/**
	 * Updates the InventoryItem with the specific Id
	 * 
	 * @param inventoryItemToBeUpdated
	 *            the InventoryItem thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{inventoryItemId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateInventoryItem(@RequestBody InventoryItem inventoryItemToBeUpdated,
			@PathVariable String inventoryItemId) throws Exception {

		inventoryItemToBeUpdated.setInventoryItemId(inventoryItemId);

		UpdateInventoryItem command = new UpdateInventoryItem(inventoryItemToBeUpdated);

		try {
			if(((InventoryItemUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{inventoryItemId}")
	public ResponseEntity<InventoryItem> findById(@PathVariable String inventoryItemId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("inventoryItemId", inventoryItemId);
		try {

			List<InventoryItem> foundInventoryItem = findInventoryItemsBy(requestParams).getBody();
			if(foundInventoryItem.size()==1){				return successful(foundInventoryItem.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{inventoryItemId}")
	public ResponseEntity<String> deleteInventoryItemByIdUpdated(@PathVariable String inventoryItemId) throws Exception {
		DeleteInventoryItem command = new DeleteInventoryItem(inventoryItemId);

		try {
			if (((InventoryItemDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
