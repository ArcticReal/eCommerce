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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<Object> findInventoryItemTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindInventoryItemTypesBy query = new FindInventoryItemTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<InventoryItemType> inventoryItemTypes =((InventoryItemTypeFound) Scheduler.execute(query).data()).getInventoryItemTypes();

		if (inventoryItemTypes.size() == 1) {
			return ResponseEntity.ok().body(inventoryItemTypes.get(0));
		}

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
	public ResponseEntity<Object> createInventoryItemType(HttpServletRequest request) throws Exception {

		InventoryItemType inventoryItemTypeToBeAdded = new InventoryItemType();
		try {
			inventoryItemTypeToBeAdded = InventoryItemTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
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
	public ResponseEntity<Object> createInventoryItemType(@RequestBody InventoryItemType inventoryItemTypeToBeAdded) throws Exception {

		AddInventoryItemType command = new AddInventoryItemType(inventoryItemTypeToBeAdded);
		InventoryItemType inventoryItemType = ((InventoryItemTypeAdded) Scheduler.execute(command).data()).getAddedInventoryItemType();
		
		if (inventoryItemType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(inventoryItemType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("InventoryItemType could not be created.");
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
	@PutMapping(value = "/update", consumes = "application/x-www-form-urlencoded")
	public boolean updateInventoryItemType(HttpServletRequest request) throws Exception {

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

		InventoryItemType inventoryItemTypeToBeUpdated = new InventoryItemType();

		try {
			inventoryItemTypeToBeUpdated = InventoryItemTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateInventoryItemType(inventoryItemTypeToBeUpdated, inventoryItemTypeToBeUpdated.getInventoryItemTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateInventoryItemType(@RequestBody InventoryItemType inventoryItemTypeToBeUpdated,
			@PathVariable String inventoryItemTypeId) throws Exception {

		inventoryItemTypeToBeUpdated.setInventoryItemTypeId(inventoryItemTypeId);

		UpdateInventoryItemType command = new UpdateInventoryItemType(inventoryItemTypeToBeUpdated);

		try {
			if(((InventoryItemTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{inventoryItemTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String inventoryItemTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("inventoryItemTypeId", inventoryItemTypeId);
		try {

			Object foundInventoryItemType = findInventoryItemTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundInventoryItemType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{inventoryItemTypeId}")
	public ResponseEntity<Object> deleteInventoryItemTypeByIdUpdated(@PathVariable String inventoryItemTypeId) throws Exception {
		DeleteInventoryItemType command = new DeleteInventoryItemType(inventoryItemTypeId);

		try {
			if (((InventoryItemTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("InventoryItemType could not be deleted");

	}

}
