package com.skytala.eCommerce.domain.product.relations.supplierPrefOrder;

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
import com.skytala.eCommerce.domain.product.relations.supplierPrefOrder.command.AddSupplierPrefOrder;
import com.skytala.eCommerce.domain.product.relations.supplierPrefOrder.command.DeleteSupplierPrefOrder;
import com.skytala.eCommerce.domain.product.relations.supplierPrefOrder.command.UpdateSupplierPrefOrder;
import com.skytala.eCommerce.domain.product.relations.supplierPrefOrder.event.SupplierPrefOrderAdded;
import com.skytala.eCommerce.domain.product.relations.supplierPrefOrder.event.SupplierPrefOrderDeleted;
import com.skytala.eCommerce.domain.product.relations.supplierPrefOrder.event.SupplierPrefOrderFound;
import com.skytala.eCommerce.domain.product.relations.supplierPrefOrder.event.SupplierPrefOrderUpdated;
import com.skytala.eCommerce.domain.product.relations.supplierPrefOrder.mapper.SupplierPrefOrderMapper;
import com.skytala.eCommerce.domain.product.relations.supplierPrefOrder.model.SupplierPrefOrder;
import com.skytala.eCommerce.domain.product.relations.supplierPrefOrder.query.FindSupplierPrefOrdersBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/supplierPrefOrders")
public class SupplierPrefOrderController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public SupplierPrefOrderController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a SupplierPrefOrder
	 * @return a List with the SupplierPrefOrders
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<SupplierPrefOrder>> findSupplierPrefOrdersBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindSupplierPrefOrdersBy query = new FindSupplierPrefOrdersBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<SupplierPrefOrder> supplierPrefOrders =((SupplierPrefOrderFound) Scheduler.execute(query).data()).getSupplierPrefOrders();

		return ResponseEntity.ok().body(supplierPrefOrders);

	}

	/**
	 * creates a new SupplierPrefOrder entry in the ofbiz database
	 * 
	 * @param supplierPrefOrderToBeAdded
	 *            the SupplierPrefOrder thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<SupplierPrefOrder> createSupplierPrefOrder(@RequestBody SupplierPrefOrder supplierPrefOrderToBeAdded) throws Exception {

		AddSupplierPrefOrder command = new AddSupplierPrefOrder(supplierPrefOrderToBeAdded);
		SupplierPrefOrder supplierPrefOrder = ((SupplierPrefOrderAdded) Scheduler.execute(command).data()).getAddedSupplierPrefOrder();
		
		if (supplierPrefOrder != null) 
			return successful(supplierPrefOrder);
		else 
			return conflict(null);
	}

	/**
	 * Updates the SupplierPrefOrder with the specific Id
	 * 
	 * @param supplierPrefOrderToBeUpdated
	 *            the SupplierPrefOrder thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{supplierPrefOrderId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateSupplierPrefOrder(@RequestBody SupplierPrefOrder supplierPrefOrderToBeUpdated,
			@PathVariable String supplierPrefOrderId) throws Exception {

		supplierPrefOrderToBeUpdated.setSupplierPrefOrderId(supplierPrefOrderId);

		UpdateSupplierPrefOrder command = new UpdateSupplierPrefOrder(supplierPrefOrderToBeUpdated);

		try {
			if(((SupplierPrefOrderUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{supplierPrefOrderId}")
	public ResponseEntity<SupplierPrefOrder> findById(@PathVariable String supplierPrefOrderId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("supplierPrefOrderId", supplierPrefOrderId);
		try {

			List<SupplierPrefOrder> foundSupplierPrefOrder = findSupplierPrefOrdersBy(requestParams).getBody();
			if(foundSupplierPrefOrder.size()==1){				return successful(foundSupplierPrefOrder.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{supplierPrefOrderId}")
	public ResponseEntity<String> deleteSupplierPrefOrderByIdUpdated(@PathVariable String supplierPrefOrderId) throws Exception {
		DeleteSupplierPrefOrder command = new DeleteSupplierPrefOrder(supplierPrefOrderId);

		try {
			if (((SupplierPrefOrderDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
