package com.skytala.eCommerce.domain.product.relations.inventoryItem.control.variance;

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
import com.skytala.eCommerce.domain.product.relations.inventoryItem.command.variance.AddInventoryItemVariance;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.command.variance.DeleteInventoryItemVariance;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.command.variance.UpdateInventoryItemVariance;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.event.variance.InventoryItemVarianceAdded;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.event.variance.InventoryItemVarianceDeleted;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.event.variance.InventoryItemVarianceFound;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.event.variance.InventoryItemVarianceUpdated;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.mapper.variance.InventoryItemVarianceMapper;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.model.variance.InventoryItemVariance;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.query.variance.FindInventoryItemVariancesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/inventoryItem/inventoryItemVariances")
public class InventoryItemVarianceController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public InventoryItemVarianceController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a InventoryItemVariance
	 * @return a List with the InventoryItemVariances
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<InventoryItemVariance>> findInventoryItemVariancesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindInventoryItemVariancesBy query = new FindInventoryItemVariancesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<InventoryItemVariance> inventoryItemVariances =((InventoryItemVarianceFound) Scheduler.execute(query).data()).getInventoryItemVariances();

		return ResponseEntity.ok().body(inventoryItemVariances);

	}

	/**
	 * creates a new InventoryItemVariance entry in the ofbiz database
	 * 
	 * @param inventoryItemVarianceToBeAdded
	 *            the InventoryItemVariance thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<InventoryItemVariance> createInventoryItemVariance(@RequestBody InventoryItemVariance inventoryItemVarianceToBeAdded) throws Exception {

		AddInventoryItemVariance command = new AddInventoryItemVariance(inventoryItemVarianceToBeAdded);
		InventoryItemVariance inventoryItemVariance = ((InventoryItemVarianceAdded) Scheduler.execute(command).data()).getAddedInventoryItemVariance();
		
		if (inventoryItemVariance != null) 
			return successful(inventoryItemVariance);
		else 
			return conflict(null);
	}

	/**
	 * Updates the InventoryItemVariance with the specific Id
	 * 
	 * @param inventoryItemVarianceToBeUpdated
	 *            the InventoryItemVariance thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateInventoryItemVariance(@RequestBody InventoryItemVariance inventoryItemVarianceToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		inventoryItemVarianceToBeUpdated.setnull(null);

		UpdateInventoryItemVariance command = new UpdateInventoryItemVariance(inventoryItemVarianceToBeUpdated);

		try {
			if(((InventoryItemVarianceUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{inventoryItemVarianceId}")
	public ResponseEntity<InventoryItemVariance> findById(@PathVariable String inventoryItemVarianceId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("inventoryItemVarianceId", inventoryItemVarianceId);
		try {

			List<InventoryItemVariance> foundInventoryItemVariance = findInventoryItemVariancesBy(requestParams).getBody();
			if(foundInventoryItemVariance.size()==1){				return successful(foundInventoryItemVariance.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{inventoryItemVarianceId}")
	public ResponseEntity<String> deleteInventoryItemVarianceByIdUpdated(@PathVariable String inventoryItemVarianceId) throws Exception {
		DeleteInventoryItemVariance command = new DeleteInventoryItemVariance(inventoryItemVarianceId);

		try {
			if (((InventoryItemVarianceDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
