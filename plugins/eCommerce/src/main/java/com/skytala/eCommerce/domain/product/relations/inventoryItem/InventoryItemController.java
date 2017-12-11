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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findInventoryItemsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindInventoryItemsBy query = new FindInventoryItemsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<InventoryItem> inventoryItems =((InventoryItemFound) Scheduler.execute(query).data()).getInventoryItems();

		if (inventoryItems.size() == 1) {
			return ResponseEntity.ok().body(inventoryItems.get(0));
		}

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
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createInventoryItem(HttpServletRequest request) throws Exception {

		InventoryItem inventoryItemToBeAdded = new InventoryItem();
		try {
			inventoryItemToBeAdded = InventoryItemMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
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
	public ResponseEntity<Object> createInventoryItem(@RequestBody InventoryItem inventoryItemToBeAdded) throws Exception {

		AddInventoryItem command = new AddInventoryItem(inventoryItemToBeAdded);
		InventoryItem inventoryItem = ((InventoryItemAdded) Scheduler.execute(command).data()).getAddedInventoryItem();
		
		if (inventoryItem != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(inventoryItem);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("InventoryItem could not be created.");
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
	public boolean updateInventoryItem(HttpServletRequest request) throws Exception {

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

		InventoryItem inventoryItemToBeUpdated = new InventoryItem();

		try {
			inventoryItemToBeUpdated = InventoryItemMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateInventoryItem(inventoryItemToBeUpdated, inventoryItemToBeUpdated.getInventoryItemId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateInventoryItem(@RequestBody InventoryItem inventoryItemToBeUpdated,
			@PathVariable String inventoryItemId) throws Exception {

		inventoryItemToBeUpdated.setInventoryItemId(inventoryItemId);

		UpdateInventoryItem command = new UpdateInventoryItem(inventoryItemToBeUpdated);

		try {
			if(((InventoryItemUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{inventoryItemId}")
	public ResponseEntity<Object> findById(@PathVariable String inventoryItemId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("inventoryItemId", inventoryItemId);
		try {

			Object foundInventoryItem = findInventoryItemsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundInventoryItem);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{inventoryItemId}")
	public ResponseEntity<Object> deleteInventoryItemByIdUpdated(@PathVariable String inventoryItemId) throws Exception {
		DeleteInventoryItem command = new DeleteInventoryItem(inventoryItemId);

		try {
			if (((InventoryItemDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("InventoryItem could not be deleted");

	}

}
