package com.skytala.eCommerce.domain.product.relations.inventoryItem.control.detail;

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
import com.skytala.eCommerce.domain.product.relations.inventoryItem.command.detail.AddInventoryItemDetail;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.command.detail.DeleteInventoryItemDetail;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.command.detail.UpdateInventoryItemDetail;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.event.detail.InventoryItemDetailAdded;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.event.detail.InventoryItemDetailDeleted;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.event.detail.InventoryItemDetailFound;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.event.detail.InventoryItemDetailUpdated;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.mapper.detail.InventoryItemDetailMapper;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.model.detail.InventoryItemDetail;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.query.detail.FindInventoryItemDetailsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/inventoryItem/inventoryItemDetails")
public class InventoryItemDetailController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public InventoryItemDetailController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a InventoryItemDetail
	 * @return a List with the InventoryItemDetails
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<InventoryItemDetail>> findInventoryItemDetailsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindInventoryItemDetailsBy query = new FindInventoryItemDetailsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<InventoryItemDetail> inventoryItemDetails =((InventoryItemDetailFound) Scheduler.execute(query).data()).getInventoryItemDetails();

		return ResponseEntity.ok().body(inventoryItemDetails);

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
	public ResponseEntity<InventoryItemDetail> createInventoryItemDetail(HttpServletRequest request) throws Exception {

		InventoryItemDetail inventoryItemDetailToBeAdded = new InventoryItemDetail();
		try {
			inventoryItemDetailToBeAdded = InventoryItemDetailMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createInventoryItemDetail(inventoryItemDetailToBeAdded);

	}

	/**
	 * creates a new InventoryItemDetail entry in the ofbiz database
	 * 
	 * @param inventoryItemDetailToBeAdded
	 *            the InventoryItemDetail thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<InventoryItemDetail> createInventoryItemDetail(@RequestBody InventoryItemDetail inventoryItemDetailToBeAdded) throws Exception {

		AddInventoryItemDetail command = new AddInventoryItemDetail(inventoryItemDetailToBeAdded);
		InventoryItemDetail inventoryItemDetail = ((InventoryItemDetailAdded) Scheduler.execute(command).data()).getAddedInventoryItemDetail();
		
		if (inventoryItemDetail != null) 
			return successful(inventoryItemDetail);
		else 
			return conflict(null);
	}

	/**
	 * Updates the InventoryItemDetail with the specific Id
	 * 
	 * @param inventoryItemDetailToBeUpdated
	 *            the InventoryItemDetail thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{inventoryItemDetailSeqId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateInventoryItemDetail(@RequestBody InventoryItemDetail inventoryItemDetailToBeUpdated,
			@PathVariable String inventoryItemDetailSeqId) throws Exception {

		inventoryItemDetailToBeUpdated.setInventoryItemDetailSeqId(inventoryItemDetailSeqId);

		UpdateInventoryItemDetail command = new UpdateInventoryItemDetail(inventoryItemDetailToBeUpdated);

		try {
			if(((InventoryItemDetailUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{inventoryItemDetailId}")
	public ResponseEntity<InventoryItemDetail> findById(@PathVariable String inventoryItemDetailId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("inventoryItemDetailId", inventoryItemDetailId);
		try {

			List<InventoryItemDetail> foundInventoryItemDetail = findInventoryItemDetailsBy(requestParams).getBody();
			if(foundInventoryItemDetail.size()==1){				return successful(foundInventoryItemDetail.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{inventoryItemDetailId}")
	public ResponseEntity<String> deleteInventoryItemDetailByIdUpdated(@PathVariable String inventoryItemDetailId) throws Exception {
		DeleteInventoryItemDetail command = new DeleteInventoryItemDetail(inventoryItemDetailId);

		try {
			if (((InventoryItemDetailDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
