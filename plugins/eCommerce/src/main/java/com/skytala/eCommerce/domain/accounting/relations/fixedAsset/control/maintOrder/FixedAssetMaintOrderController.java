package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.control.maintOrder;

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
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.command.maintOrder.AddFixedAssetMaintOrder;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.command.maintOrder.DeleteFixedAssetMaintOrder;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.command.maintOrder.UpdateFixedAssetMaintOrder;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.maintOrder.FixedAssetMaintOrderAdded;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.maintOrder.FixedAssetMaintOrderDeleted;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.maintOrder.FixedAssetMaintOrderFound;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.maintOrder.FixedAssetMaintOrderUpdated;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.mapper.maintOrder.FixedAssetMaintOrderMapper;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.maintOrder.FixedAssetMaintOrder;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.query.maintOrder.FindFixedAssetMaintOrdersBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/fixedAsset/fixedAssetMaintOrders")
public class FixedAssetMaintOrderController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public FixedAssetMaintOrderController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a FixedAssetMaintOrder
	 * @return a List with the FixedAssetMaintOrders
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<FixedAssetMaintOrder>> findFixedAssetMaintOrdersBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindFixedAssetMaintOrdersBy query = new FindFixedAssetMaintOrdersBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<FixedAssetMaintOrder> fixedAssetMaintOrders =((FixedAssetMaintOrderFound) Scheduler.execute(query).data()).getFixedAssetMaintOrders();

		return ResponseEntity.ok().body(fixedAssetMaintOrders);

	}

	/**
	 * creates a new FixedAssetMaintOrder entry in the ofbiz database
	 * 
	 * @param fixedAssetMaintOrderToBeAdded
	 *            the FixedAssetMaintOrder thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<FixedAssetMaintOrder> createFixedAssetMaintOrder(@RequestBody FixedAssetMaintOrder fixedAssetMaintOrderToBeAdded) throws Exception {

		AddFixedAssetMaintOrder command = new AddFixedAssetMaintOrder(fixedAssetMaintOrderToBeAdded);
		FixedAssetMaintOrder fixedAssetMaintOrder = ((FixedAssetMaintOrderAdded) Scheduler.execute(command).data()).getAddedFixedAssetMaintOrder();
		
		if (fixedAssetMaintOrder != null) 
			return successful(fixedAssetMaintOrder);
		else 
			return conflict(null);
	}

	/**
	 * Updates the FixedAssetMaintOrder with the specific Id
	 * 
	 * @param fixedAssetMaintOrderToBeUpdated
	 *            the FixedAssetMaintOrder thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateFixedAssetMaintOrder(@RequestBody FixedAssetMaintOrder fixedAssetMaintOrderToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		fixedAssetMaintOrderToBeUpdated.setnull(null);

		UpdateFixedAssetMaintOrder command = new UpdateFixedAssetMaintOrder(fixedAssetMaintOrderToBeUpdated);

		try {
			if(((FixedAssetMaintOrderUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{fixedAssetMaintOrderId}")
	public ResponseEntity<FixedAssetMaintOrder> findById(@PathVariable String fixedAssetMaintOrderId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("fixedAssetMaintOrderId", fixedAssetMaintOrderId);
		try {

			List<FixedAssetMaintOrder> foundFixedAssetMaintOrder = findFixedAssetMaintOrdersBy(requestParams).getBody();
			if(foundFixedAssetMaintOrder.size()==1){				return successful(foundFixedAssetMaintOrder.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{fixedAssetMaintOrderId}")
	public ResponseEntity<String> deleteFixedAssetMaintOrderByIdUpdated(@PathVariable String fixedAssetMaintOrderId) throws Exception {
		DeleteFixedAssetMaintOrder command = new DeleteFixedAssetMaintOrder(fixedAssetMaintOrderId);

		try {
			if (((FixedAssetMaintOrderDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
