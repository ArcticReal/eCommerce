package com.skytala.eCommerce.domain.product.relations.inventoryItem.control.labelAppl;

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
import com.skytala.eCommerce.domain.product.relations.inventoryItem.command.labelAppl.AddInventoryItemLabelAppl;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.command.labelAppl.DeleteInventoryItemLabelAppl;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.command.labelAppl.UpdateInventoryItemLabelAppl;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.event.labelAppl.InventoryItemLabelApplAdded;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.event.labelAppl.InventoryItemLabelApplDeleted;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.event.labelAppl.InventoryItemLabelApplFound;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.event.labelAppl.InventoryItemLabelApplUpdated;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.mapper.labelAppl.InventoryItemLabelApplMapper;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.model.labelAppl.InventoryItemLabelAppl;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.query.labelAppl.FindInventoryItemLabelApplsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/inventoryItem/inventoryItemLabelAppls")
public class InventoryItemLabelApplController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public InventoryItemLabelApplController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a InventoryItemLabelAppl
	 * @return a List with the InventoryItemLabelAppls
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<InventoryItemLabelAppl>> findInventoryItemLabelApplsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindInventoryItemLabelApplsBy query = new FindInventoryItemLabelApplsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<InventoryItemLabelAppl> inventoryItemLabelAppls =((InventoryItemLabelApplFound) Scheduler.execute(query).data()).getInventoryItemLabelAppls();

		return ResponseEntity.ok().body(inventoryItemLabelAppls);

	}

	/**
	 * creates a new InventoryItemLabelAppl entry in the ofbiz database
	 * 
	 * @param inventoryItemLabelApplToBeAdded
	 *            the InventoryItemLabelAppl thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<InventoryItemLabelAppl> createInventoryItemLabelAppl(@RequestBody InventoryItemLabelAppl inventoryItemLabelApplToBeAdded) throws Exception {

		AddInventoryItemLabelAppl command = new AddInventoryItemLabelAppl(inventoryItemLabelApplToBeAdded);
		InventoryItemLabelAppl inventoryItemLabelAppl = ((InventoryItemLabelApplAdded) Scheduler.execute(command).data()).getAddedInventoryItemLabelAppl();
		
		if (inventoryItemLabelAppl != null) 
			return successful(inventoryItemLabelAppl);
		else 
			return conflict(null);
	}

	/**
	 * Updates the InventoryItemLabelAppl with the specific Id
	 * 
	 * @param inventoryItemLabelApplToBeUpdated
	 *            the InventoryItemLabelAppl thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateInventoryItemLabelAppl(@RequestBody InventoryItemLabelAppl inventoryItemLabelApplToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		inventoryItemLabelApplToBeUpdated.setnull(null);

		UpdateInventoryItemLabelAppl command = new UpdateInventoryItemLabelAppl(inventoryItemLabelApplToBeUpdated);

		try {
			if(((InventoryItemLabelApplUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{inventoryItemLabelApplId}")
	public ResponseEntity<InventoryItemLabelAppl> findById(@PathVariable String inventoryItemLabelApplId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("inventoryItemLabelApplId", inventoryItemLabelApplId);
		try {

			List<InventoryItemLabelAppl> foundInventoryItemLabelAppl = findInventoryItemLabelApplsBy(requestParams).getBody();
			if(foundInventoryItemLabelAppl.size()==1){				return successful(foundInventoryItemLabelAppl.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{inventoryItemLabelApplId}")
	public ResponseEntity<String> deleteInventoryItemLabelApplByIdUpdated(@PathVariable String inventoryItemLabelApplId) throws Exception {
		DeleteInventoryItemLabelAppl command = new DeleteInventoryItemLabelAppl(inventoryItemLabelApplId);

		try {
			if (((InventoryItemLabelApplDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
