package com.skytala.eCommerce.domain.shipment.relations.itemIssuance;

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
import com.skytala.eCommerce.domain.shipment.relations.itemIssuance.command.AddItemIssuance;
import com.skytala.eCommerce.domain.shipment.relations.itemIssuance.command.DeleteItemIssuance;
import com.skytala.eCommerce.domain.shipment.relations.itemIssuance.command.UpdateItemIssuance;
import com.skytala.eCommerce.domain.shipment.relations.itemIssuance.event.ItemIssuanceAdded;
import com.skytala.eCommerce.domain.shipment.relations.itemIssuance.event.ItemIssuanceDeleted;
import com.skytala.eCommerce.domain.shipment.relations.itemIssuance.event.ItemIssuanceFound;
import com.skytala.eCommerce.domain.shipment.relations.itemIssuance.event.ItemIssuanceUpdated;
import com.skytala.eCommerce.domain.shipment.relations.itemIssuance.mapper.ItemIssuanceMapper;
import com.skytala.eCommerce.domain.shipment.relations.itemIssuance.model.ItemIssuance;
import com.skytala.eCommerce.domain.shipment.relations.itemIssuance.query.FindItemIssuancesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/shipment/itemIssuances")
public class ItemIssuanceController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ItemIssuanceController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ItemIssuance
	 * @return a List with the ItemIssuances
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ItemIssuance>> findItemIssuancesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindItemIssuancesBy query = new FindItemIssuancesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ItemIssuance> itemIssuances =((ItemIssuanceFound) Scheduler.execute(query).data()).getItemIssuances();

		return ResponseEntity.ok().body(itemIssuances);

	}

	/**
	 * creates a new ItemIssuance entry in the ofbiz database
	 * 
	 * @param itemIssuanceToBeAdded
	 *            the ItemIssuance thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ItemIssuance> createItemIssuance(@RequestBody ItemIssuance itemIssuanceToBeAdded) throws Exception {

		AddItemIssuance command = new AddItemIssuance(itemIssuanceToBeAdded);
		ItemIssuance itemIssuance = ((ItemIssuanceAdded) Scheduler.execute(command).data()).getAddedItemIssuance();
		
		if (itemIssuance != null) 
			return successful(itemIssuance);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ItemIssuance with the specific Id
	 * 
	 * @param itemIssuanceToBeUpdated
	 *            the ItemIssuance thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{itemIssuanceId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateItemIssuance(@RequestBody ItemIssuance itemIssuanceToBeUpdated,
			@PathVariable String itemIssuanceId) throws Exception {

		itemIssuanceToBeUpdated.setItemIssuanceId(itemIssuanceId);

		UpdateItemIssuance command = new UpdateItemIssuance(itemIssuanceToBeUpdated);

		try {
			if(((ItemIssuanceUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{itemIssuanceId}")
	public ResponseEntity<ItemIssuance> findById(@PathVariable String itemIssuanceId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("itemIssuanceId", itemIssuanceId);
		try {

			List<ItemIssuance> foundItemIssuance = findItemIssuancesBy(requestParams).getBody();
			if(foundItemIssuance.size()==1){				return successful(foundItemIssuance.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{itemIssuanceId}")
	public ResponseEntity<String> deleteItemIssuanceByIdUpdated(@PathVariable String itemIssuanceId) throws Exception {
		DeleteItemIssuance command = new DeleteItemIssuance(itemIssuanceId);

		try {
			if (((ItemIssuanceDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
