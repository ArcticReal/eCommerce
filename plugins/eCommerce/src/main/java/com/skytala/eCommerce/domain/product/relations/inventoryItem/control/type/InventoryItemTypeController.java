package com.skytala.eCommerce.domain.product.relations.inventoryItem.control.type;

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
import com.skytala.eCommerce.domain.product.relations.inventoryItem.command.type.AddInventoryItemType;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.command.type.DeleteInventoryItemType;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.command.type.UpdateInventoryItemType;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.event.type.InventoryItemTypeAdded;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.event.type.InventoryItemTypeDeleted;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.event.type.InventoryItemTypeFound;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.event.type.InventoryItemTypeUpdated;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.mapper.type.InventoryItemTypeMapper;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.model.type.InventoryItemType;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.query.type.FindInventoryItemTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/inventoryItem/inventoryItemTypes")
public class InventoryItemTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public InventoryItemTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a InventoryItemType
	 * @return a List with the InventoryItemTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<InventoryItemType>> findInventoryItemTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindInventoryItemTypesBy query = new FindInventoryItemTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<InventoryItemType> inventoryItemTypes =((InventoryItemTypeFound) Scheduler.execute(query).data()).getInventoryItemTypes();

		return ResponseEntity.ok().body(inventoryItemTypes);

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
	public ResponseEntity<InventoryItemType> createInventoryItemType(HttpServletRequest request) throws Exception {

		InventoryItemType inventoryItemTypeToBeAdded = new InventoryItemType();
		try {
			inventoryItemTypeToBeAdded = InventoryItemTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createInventoryItemType(inventoryItemTypeToBeAdded);

	}

	/**
	 * creates a new InventoryItemType entry in the ofbiz database
	 * 
	 * @param inventoryItemTypeToBeAdded
	 *            the InventoryItemType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<InventoryItemType> createInventoryItemType(@RequestBody InventoryItemType inventoryItemTypeToBeAdded) throws Exception {

		AddInventoryItemType command = new AddInventoryItemType(inventoryItemTypeToBeAdded);
		InventoryItemType inventoryItemType = ((InventoryItemTypeAdded) Scheduler.execute(command).data()).getAddedInventoryItemType();
		
		if (inventoryItemType != null) 
			return successful(inventoryItemType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the InventoryItemType with the specific Id
	 * 
	 * @param inventoryItemTypeToBeUpdated
	 *            the InventoryItemType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{inventoryItemTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateInventoryItemType(@RequestBody InventoryItemType inventoryItemTypeToBeUpdated,
			@PathVariable String inventoryItemTypeId) throws Exception {

		inventoryItemTypeToBeUpdated.setInventoryItemTypeId(inventoryItemTypeId);

		UpdateInventoryItemType command = new UpdateInventoryItemType(inventoryItemTypeToBeUpdated);

		try {
			if(((InventoryItemTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{inventoryItemTypeId}")
	public ResponseEntity<InventoryItemType> findById(@PathVariable String inventoryItemTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("inventoryItemTypeId", inventoryItemTypeId);
		try {

			List<InventoryItemType> foundInventoryItemType = findInventoryItemTypesBy(requestParams).getBody();
			if(foundInventoryItemType.size()==1){				return successful(foundInventoryItemType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{inventoryItemTypeId}")
	public ResponseEntity<String> deleteInventoryItemTypeByIdUpdated(@PathVariable String inventoryItemTypeId) throws Exception {
		DeleteInventoryItemType command = new DeleteInventoryItemType(inventoryItemTypeId);

		try {
			if (((InventoryItemTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
