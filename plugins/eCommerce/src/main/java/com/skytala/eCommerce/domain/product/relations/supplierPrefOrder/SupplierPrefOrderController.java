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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findSupplierPrefOrdersBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindSupplierPrefOrdersBy query = new FindSupplierPrefOrdersBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<SupplierPrefOrder> supplierPrefOrders =((SupplierPrefOrderFound) Scheduler.execute(query).data()).getSupplierPrefOrders();

		if (supplierPrefOrders.size() == 1) {
			return ResponseEntity.ok().body(supplierPrefOrders.get(0));
		}

		return ResponseEntity.ok().body(supplierPrefOrders);

	}

	/**
	 * 
	 * this method will only be called by Springs DispatcherServlet
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createSupplierPrefOrder(HttpServletRequest request) throws Exception {

		SupplierPrefOrder supplierPrefOrderToBeAdded = new SupplierPrefOrder();
		try {
			supplierPrefOrderToBeAdded = SupplierPrefOrderMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createSupplierPrefOrder(supplierPrefOrderToBeAdded);

	}

	/**
	 * creates a new SupplierPrefOrder entry in the ofbiz database
	 * 
	 * @param supplierPrefOrderToBeAdded
	 *            the SupplierPrefOrder thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createSupplierPrefOrder(@RequestBody SupplierPrefOrder supplierPrefOrderToBeAdded) throws Exception {

		AddSupplierPrefOrder command = new AddSupplierPrefOrder(supplierPrefOrderToBeAdded);
		SupplierPrefOrder supplierPrefOrder = ((SupplierPrefOrderAdded) Scheduler.execute(command).data()).getAddedSupplierPrefOrder();
		
		if (supplierPrefOrder != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(supplierPrefOrder);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("SupplierPrefOrder could not be created.");
	}

	/**
	 * this method will only be called by Springs DispatcherServlet
	 * 
	 * @deprecated
	 * @param request
	 *            HttpServletRequest object
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/update", consumes = "application/x-www-form-urlencoded")
	public boolean updateSupplierPrefOrder(HttpServletRequest request) throws Exception {

		BufferedReader br;
		String data = null;
		Map<String, String> dataMap = null;

		try {
			br = new BufferedReader(new InputStreamReader(request.getInputStream()));
			if (br != null) {
				data = java.net.URLDecoder.decode(br.readLine(), "UTF-8");
			}
		} catch (IOException e1) {
			e1.printStackTrace();
			return false;
		}

		dataMap = Splitter.on('&').trimResults().withKeyValueSeparator(Splitter.on('=').limit(2).trimResults())
				.split(data);

		SupplierPrefOrder supplierPrefOrderToBeUpdated = new SupplierPrefOrder();

		try {
			supplierPrefOrderToBeUpdated = SupplierPrefOrderMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateSupplierPrefOrder(supplierPrefOrderToBeUpdated, supplierPrefOrderToBeUpdated.getSupplierPrefOrderId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateSupplierPrefOrder(@RequestBody SupplierPrefOrder supplierPrefOrderToBeUpdated,
			@PathVariable String supplierPrefOrderId) throws Exception {

		supplierPrefOrderToBeUpdated.setSupplierPrefOrderId(supplierPrefOrderId);

		UpdateSupplierPrefOrder command = new UpdateSupplierPrefOrder(supplierPrefOrderToBeUpdated);

		try {
			if(((SupplierPrefOrderUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{supplierPrefOrderId}")
	public ResponseEntity<Object> findById(@PathVariable String supplierPrefOrderId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("supplierPrefOrderId", supplierPrefOrderId);
		try {

			Object foundSupplierPrefOrder = findSupplierPrefOrdersBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundSupplierPrefOrder);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{supplierPrefOrderId}")
	public ResponseEntity<Object> deleteSupplierPrefOrderByIdUpdated(@PathVariable String supplierPrefOrderId) throws Exception {
		DeleteSupplierPrefOrder command = new DeleteSupplierPrefOrder(supplierPrefOrderId);

		try {
			if (((SupplierPrefOrderDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("SupplierPrefOrder could not be deleted");

	}

}
