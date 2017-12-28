package com.skytala.eCommerce.domain.product.relations.physicalInventory;

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
import com.skytala.eCommerce.domain.product.relations.physicalInventory.command.AddPhysicalInventory;
import com.skytala.eCommerce.domain.product.relations.physicalInventory.command.DeletePhysicalInventory;
import com.skytala.eCommerce.domain.product.relations.physicalInventory.command.UpdatePhysicalInventory;
import com.skytala.eCommerce.domain.product.relations.physicalInventory.event.PhysicalInventoryAdded;
import com.skytala.eCommerce.domain.product.relations.physicalInventory.event.PhysicalInventoryDeleted;
import com.skytala.eCommerce.domain.product.relations.physicalInventory.event.PhysicalInventoryFound;
import com.skytala.eCommerce.domain.product.relations.physicalInventory.event.PhysicalInventoryUpdated;
import com.skytala.eCommerce.domain.product.relations.physicalInventory.mapper.PhysicalInventoryMapper;
import com.skytala.eCommerce.domain.product.relations.physicalInventory.model.PhysicalInventory;
import com.skytala.eCommerce.domain.product.relations.physicalInventory.query.FindPhysicalInventorysBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/physicalInventorys")
public class PhysicalInventoryController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PhysicalInventoryController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PhysicalInventory
	 * @return a List with the PhysicalInventorys
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<PhysicalInventory>> findPhysicalInventorysBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPhysicalInventorysBy query = new FindPhysicalInventorysBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PhysicalInventory> physicalInventorys =((PhysicalInventoryFound) Scheduler.execute(query).data()).getPhysicalInventorys();

		return ResponseEntity.ok().body(physicalInventorys);

	}

	/**
	 * creates a new PhysicalInventory entry in the ofbiz database
	 * 
	 * @param physicalInventoryToBeAdded
	 *            the PhysicalInventory thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PhysicalInventory> createPhysicalInventory(@RequestBody PhysicalInventory physicalInventoryToBeAdded) throws Exception {

		AddPhysicalInventory command = new AddPhysicalInventory(physicalInventoryToBeAdded);
		PhysicalInventory physicalInventory = ((PhysicalInventoryAdded) Scheduler.execute(command).data()).getAddedPhysicalInventory();
		
		if (physicalInventory != null) 
			return successful(physicalInventory);
		else 
			return conflict(null);
	}

	/**
	 * Updates the PhysicalInventory with the specific Id
	 * 
	 * @param physicalInventoryToBeUpdated
	 *            the PhysicalInventory thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{physicalInventoryId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updatePhysicalInventory(@RequestBody PhysicalInventory physicalInventoryToBeUpdated,
			@PathVariable String physicalInventoryId) throws Exception {

		physicalInventoryToBeUpdated.setPhysicalInventoryId(physicalInventoryId);

		UpdatePhysicalInventory command = new UpdatePhysicalInventory(physicalInventoryToBeUpdated);

		try {
			if(((PhysicalInventoryUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{physicalInventoryId}")
	public ResponseEntity<PhysicalInventory> findById(@PathVariable String physicalInventoryId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("physicalInventoryId", physicalInventoryId);
		try {

			List<PhysicalInventory> foundPhysicalInventory = findPhysicalInventorysBy(requestParams).getBody();
			if(foundPhysicalInventory.size()==1){				return successful(foundPhysicalInventory.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{physicalInventoryId}")
	public ResponseEntity<String> deletePhysicalInventoryByIdUpdated(@PathVariable String physicalInventoryId) throws Exception {
		DeletePhysicalInventory command = new DeletePhysicalInventory(physicalInventoryId);

		try {
			if (((PhysicalInventoryDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
