package com.skytala.eCommerce.domain.product.relations.inventoryItem.control.tempRes;

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
import com.skytala.eCommerce.domain.product.relations.inventoryItem.command.tempRes.AddInventoryItemTempRes;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.command.tempRes.DeleteInventoryItemTempRes;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.command.tempRes.UpdateInventoryItemTempRes;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.event.tempRes.InventoryItemTempResAdded;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.event.tempRes.InventoryItemTempResDeleted;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.event.tempRes.InventoryItemTempResFound;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.event.tempRes.InventoryItemTempResUpdated;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.mapper.tempRes.InventoryItemTempResMapper;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.model.tempRes.InventoryItemTempRes;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.query.tempRes.FindInventoryItemTempRessBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/inventoryItem/inventoryItemTempRess")
public class InventoryItemTempResController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public InventoryItemTempResController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a InventoryItemTempRes
	 * @return a List with the InventoryItemTempRess
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<InventoryItemTempRes>> findInventoryItemTempRessBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindInventoryItemTempRessBy query = new FindInventoryItemTempRessBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<InventoryItemTempRes> inventoryItemTempRess =((InventoryItemTempResFound) Scheduler.execute(query).data()).getInventoryItemTempRess();

		return ResponseEntity.ok().body(inventoryItemTempRess);

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
	public ResponseEntity<InventoryItemTempRes> createInventoryItemTempRes(HttpServletRequest request) throws Exception {

		InventoryItemTempRes inventoryItemTempResToBeAdded = new InventoryItemTempRes();
		try {
			inventoryItemTempResToBeAdded = InventoryItemTempResMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createInventoryItemTempRes(inventoryItemTempResToBeAdded);

	}

	/**
	 * creates a new InventoryItemTempRes entry in the ofbiz database
	 * 
	 * @param inventoryItemTempResToBeAdded
	 *            the InventoryItemTempRes thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<InventoryItemTempRes> createInventoryItemTempRes(@RequestBody InventoryItemTempRes inventoryItemTempResToBeAdded) throws Exception {

		AddInventoryItemTempRes command = new AddInventoryItemTempRes(inventoryItemTempResToBeAdded);
		InventoryItemTempRes inventoryItemTempRes = ((InventoryItemTempResAdded) Scheduler.execute(command).data()).getAddedInventoryItemTempRes();
		
		if (inventoryItemTempRes != null) 
			return successful(inventoryItemTempRes);
		else 
			return conflict(null);
	}

	/**
	 * Updates the InventoryItemTempRes with the specific Id
	 * 
	 * @param inventoryItemTempResToBeUpdated
	 *            the InventoryItemTempRes thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{visitId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateInventoryItemTempRes(@RequestBody InventoryItemTempRes inventoryItemTempResToBeUpdated,
			@PathVariable String visitId) throws Exception {

		inventoryItemTempResToBeUpdated.setVisitId(visitId);

		UpdateInventoryItemTempRes command = new UpdateInventoryItemTempRes(inventoryItemTempResToBeUpdated);

		try {
			if(((InventoryItemTempResUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{inventoryItemTempResId}")
	public ResponseEntity<InventoryItemTempRes> findById(@PathVariable String inventoryItemTempResId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("inventoryItemTempResId", inventoryItemTempResId);
		try {

			List<InventoryItemTempRes> foundInventoryItemTempRes = findInventoryItemTempRessBy(requestParams).getBody();
			if(foundInventoryItemTempRes.size()==1){				return successful(foundInventoryItemTempRes.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{inventoryItemTempResId}")
	public ResponseEntity<String> deleteInventoryItemTempResByIdUpdated(@PathVariable String inventoryItemTempResId) throws Exception {
		DeleteInventoryItemTempRes command = new DeleteInventoryItemTempRes(inventoryItemTempResId);

		try {
			if (((InventoryItemTempResDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
