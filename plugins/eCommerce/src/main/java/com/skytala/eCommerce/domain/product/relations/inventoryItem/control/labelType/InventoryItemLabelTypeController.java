package com.skytala.eCommerce.domain.product.relations.inventoryItem.control.labelType;

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
import com.skytala.eCommerce.domain.product.relations.inventoryItem.command.labelType.AddInventoryItemLabelType;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.command.labelType.DeleteInventoryItemLabelType;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.command.labelType.UpdateInventoryItemLabelType;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.event.labelType.InventoryItemLabelTypeAdded;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.event.labelType.InventoryItemLabelTypeDeleted;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.event.labelType.InventoryItemLabelTypeFound;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.event.labelType.InventoryItemLabelTypeUpdated;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.mapper.labelType.InventoryItemLabelTypeMapper;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.model.labelType.InventoryItemLabelType;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.query.labelType.FindInventoryItemLabelTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/inventoryItem/inventoryItemLabelTypes")
public class InventoryItemLabelTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public InventoryItemLabelTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a InventoryItemLabelType
	 * @return a List with the InventoryItemLabelTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<InventoryItemLabelType>> findInventoryItemLabelTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindInventoryItemLabelTypesBy query = new FindInventoryItemLabelTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<InventoryItemLabelType> inventoryItemLabelTypes =((InventoryItemLabelTypeFound) Scheduler.execute(query).data()).getInventoryItemLabelTypes();

		return ResponseEntity.ok().body(inventoryItemLabelTypes);

	}

	/**
	 * creates a new InventoryItemLabelType entry in the ofbiz database
	 * 
	 * @param inventoryItemLabelTypeToBeAdded
	 *            the InventoryItemLabelType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<InventoryItemLabelType> createInventoryItemLabelType(@RequestBody InventoryItemLabelType inventoryItemLabelTypeToBeAdded) throws Exception {

		AddInventoryItemLabelType command = new AddInventoryItemLabelType(inventoryItemLabelTypeToBeAdded);
		InventoryItemLabelType inventoryItemLabelType = ((InventoryItemLabelTypeAdded) Scheduler.execute(command).data()).getAddedInventoryItemLabelType();
		
		if (inventoryItemLabelType != null) 
			return successful(inventoryItemLabelType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the InventoryItemLabelType with the specific Id
	 * 
	 * @param inventoryItemLabelTypeToBeUpdated
	 *            the InventoryItemLabelType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{inventoryItemLabelTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateInventoryItemLabelType(@RequestBody InventoryItemLabelType inventoryItemLabelTypeToBeUpdated,
			@PathVariable String inventoryItemLabelTypeId) throws Exception {

		inventoryItemLabelTypeToBeUpdated.setInventoryItemLabelTypeId(inventoryItemLabelTypeId);

		UpdateInventoryItemLabelType command = new UpdateInventoryItemLabelType(inventoryItemLabelTypeToBeUpdated);

		try {
			if(((InventoryItemLabelTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{inventoryItemLabelTypeId}")
	public ResponseEntity<InventoryItemLabelType> findById(@PathVariable String inventoryItemLabelTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("inventoryItemLabelTypeId", inventoryItemLabelTypeId);
		try {

			List<InventoryItemLabelType> foundInventoryItemLabelType = findInventoryItemLabelTypesBy(requestParams).getBody();
			if(foundInventoryItemLabelType.size()==1){				return successful(foundInventoryItemLabelType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{inventoryItemLabelTypeId}")
	public ResponseEntity<String> deleteInventoryItemLabelTypeByIdUpdated(@PathVariable String inventoryItemLabelTypeId) throws Exception {
		DeleteInventoryItemLabelType command = new DeleteInventoryItemLabelType(inventoryItemLabelTypeId);

		try {
			if (((InventoryItemLabelTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
