package com.skytala.eCommerce.domain.product.relations.inventoryItem.control.label;

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
import com.skytala.eCommerce.domain.product.relations.inventoryItem.command.label.AddInventoryItemLabel;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.command.label.DeleteInventoryItemLabel;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.command.label.UpdateInventoryItemLabel;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.event.label.InventoryItemLabelAdded;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.event.label.InventoryItemLabelDeleted;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.event.label.InventoryItemLabelFound;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.event.label.InventoryItemLabelUpdated;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.mapper.label.InventoryItemLabelMapper;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.model.label.InventoryItemLabel;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.query.label.FindInventoryItemLabelsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/inventoryItem/inventoryItemLabels")
public class InventoryItemLabelController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public InventoryItemLabelController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a InventoryItemLabel
	 * @return a List with the InventoryItemLabels
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<InventoryItemLabel>> findInventoryItemLabelsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindInventoryItemLabelsBy query = new FindInventoryItemLabelsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<InventoryItemLabel> inventoryItemLabels =((InventoryItemLabelFound) Scheduler.execute(query).data()).getInventoryItemLabels();

		return ResponseEntity.ok().body(inventoryItemLabels);

	}

	/**
	 * creates a new InventoryItemLabel entry in the ofbiz database
	 * 
	 * @param inventoryItemLabelToBeAdded
	 *            the InventoryItemLabel thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<InventoryItemLabel> createInventoryItemLabel(@RequestBody InventoryItemLabel inventoryItemLabelToBeAdded) throws Exception {

		AddInventoryItemLabel command = new AddInventoryItemLabel(inventoryItemLabelToBeAdded);
		InventoryItemLabel inventoryItemLabel = ((InventoryItemLabelAdded) Scheduler.execute(command).data()).getAddedInventoryItemLabel();
		
		if (inventoryItemLabel != null) 
			return successful(inventoryItemLabel);
		else 
			return conflict(null);
	}

	/**
	 * Updates the InventoryItemLabel with the specific Id
	 * 
	 * @param inventoryItemLabelToBeUpdated
	 *            the InventoryItemLabel thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{inventoryItemLabelId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateInventoryItemLabel(@RequestBody InventoryItemLabel inventoryItemLabelToBeUpdated,
			@PathVariable String inventoryItemLabelId) throws Exception {

		inventoryItemLabelToBeUpdated.setInventoryItemLabelId(inventoryItemLabelId);

		UpdateInventoryItemLabel command = new UpdateInventoryItemLabel(inventoryItemLabelToBeUpdated);

		try {
			if(((InventoryItemLabelUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{inventoryItemLabelId}")
	public ResponseEntity<InventoryItemLabel> findById(@PathVariable String inventoryItemLabelId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("inventoryItemLabelId", inventoryItemLabelId);
		try {

			List<InventoryItemLabel> foundInventoryItemLabel = findInventoryItemLabelsBy(requestParams).getBody();
			if(foundInventoryItemLabel.size()==1){				return successful(foundInventoryItemLabel.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{inventoryItemLabelId}")
	public ResponseEntity<String> deleteInventoryItemLabelByIdUpdated(@PathVariable String inventoryItemLabelId) throws Exception {
		DeleteInventoryItemLabel command = new DeleteInventoryItemLabel(inventoryItemLabelId);

		try {
			if (((InventoryItemLabelDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
