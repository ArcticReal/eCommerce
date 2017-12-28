package com.skytala.eCommerce.domain.product.relations.inventoryItem.control.typeAttr;

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
import com.skytala.eCommerce.domain.product.relations.inventoryItem.command.typeAttr.AddInventoryItemTypeAttr;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.command.typeAttr.DeleteInventoryItemTypeAttr;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.command.typeAttr.UpdateInventoryItemTypeAttr;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.event.typeAttr.InventoryItemTypeAttrAdded;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.event.typeAttr.InventoryItemTypeAttrDeleted;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.event.typeAttr.InventoryItemTypeAttrFound;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.event.typeAttr.InventoryItemTypeAttrUpdated;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.mapper.typeAttr.InventoryItemTypeAttrMapper;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.model.typeAttr.InventoryItemTypeAttr;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.query.typeAttr.FindInventoryItemTypeAttrsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/inventoryItem/inventoryItemTypeAttrs")
public class InventoryItemTypeAttrController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public InventoryItemTypeAttrController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a InventoryItemTypeAttr
	 * @return a List with the InventoryItemTypeAttrs
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<InventoryItemTypeAttr>> findInventoryItemTypeAttrsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindInventoryItemTypeAttrsBy query = new FindInventoryItemTypeAttrsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<InventoryItemTypeAttr> inventoryItemTypeAttrs =((InventoryItemTypeAttrFound) Scheduler.execute(query).data()).getInventoryItemTypeAttrs();

		return ResponseEntity.ok().body(inventoryItemTypeAttrs);

	}

	/**
	 * creates a new InventoryItemTypeAttr entry in the ofbiz database
	 * 
	 * @param inventoryItemTypeAttrToBeAdded
	 *            the InventoryItemTypeAttr thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<InventoryItemTypeAttr> createInventoryItemTypeAttr(@RequestBody InventoryItemTypeAttr inventoryItemTypeAttrToBeAdded) throws Exception {

		AddInventoryItemTypeAttr command = new AddInventoryItemTypeAttr(inventoryItemTypeAttrToBeAdded);
		InventoryItemTypeAttr inventoryItemTypeAttr = ((InventoryItemTypeAttrAdded) Scheduler.execute(command).data()).getAddedInventoryItemTypeAttr();
		
		if (inventoryItemTypeAttr != null) 
			return successful(inventoryItemTypeAttr);
		else 
			return conflict(null);
	}

	/**
	 * Updates the InventoryItemTypeAttr with the specific Id
	 * 
	 * @param inventoryItemTypeAttrToBeUpdated
	 *            the InventoryItemTypeAttr thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{attrName}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateInventoryItemTypeAttr(@RequestBody InventoryItemTypeAttr inventoryItemTypeAttrToBeUpdated,
			@PathVariable String attrName) throws Exception {

		inventoryItemTypeAttrToBeUpdated.setAttrName(attrName);

		UpdateInventoryItemTypeAttr command = new UpdateInventoryItemTypeAttr(inventoryItemTypeAttrToBeUpdated);

		try {
			if(((InventoryItemTypeAttrUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{inventoryItemTypeAttrId}")
	public ResponseEntity<InventoryItemTypeAttr> findById(@PathVariable String inventoryItemTypeAttrId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("inventoryItemTypeAttrId", inventoryItemTypeAttrId);
		try {

			List<InventoryItemTypeAttr> foundInventoryItemTypeAttr = findInventoryItemTypeAttrsBy(requestParams).getBody();
			if(foundInventoryItemTypeAttr.size()==1){				return successful(foundInventoryItemTypeAttr.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{inventoryItemTypeAttrId}")
	public ResponseEntity<String> deleteInventoryItemTypeAttrByIdUpdated(@PathVariable String inventoryItemTypeAttrId) throws Exception {
		DeleteInventoryItemTypeAttr command = new DeleteInventoryItemTypeAttr(inventoryItemTypeAttrId);

		try {
			if (((InventoryItemTypeAttrDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
