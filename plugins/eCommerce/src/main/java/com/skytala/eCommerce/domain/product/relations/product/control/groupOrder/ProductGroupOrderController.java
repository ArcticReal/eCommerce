package com.skytala.eCommerce.domain.product.relations.product.control.groupOrder;

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
import com.skytala.eCommerce.domain.product.relations.product.command.groupOrder.AddProductGroupOrder;
import com.skytala.eCommerce.domain.product.relations.product.command.groupOrder.DeleteProductGroupOrder;
import com.skytala.eCommerce.domain.product.relations.product.command.groupOrder.UpdateProductGroupOrder;
import com.skytala.eCommerce.domain.product.relations.product.event.groupOrder.ProductGroupOrderAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.groupOrder.ProductGroupOrderDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.groupOrder.ProductGroupOrderFound;
import com.skytala.eCommerce.domain.product.relations.product.event.groupOrder.ProductGroupOrderUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.groupOrder.ProductGroupOrderMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.groupOrder.ProductGroupOrder;
import com.skytala.eCommerce.domain.product.relations.product.query.groupOrder.FindProductGroupOrdersBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/product/product/productGroupOrders")
public class ProductGroupOrderController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductGroupOrderController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductGroupOrder
	 * @return a List with the ProductGroupOrders
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findProductGroupOrdersBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductGroupOrdersBy query = new FindProductGroupOrdersBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductGroupOrder> productGroupOrders =((ProductGroupOrderFound) Scheduler.execute(query).data()).getProductGroupOrders();

		if (productGroupOrders.size() == 1) {
			return ResponseEntity.ok().body(productGroupOrders.get(0));
		}

		return ResponseEntity.ok().body(productGroupOrders);

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
	public ResponseEntity<Object> createProductGroupOrder(HttpServletRequest request) throws Exception {

		ProductGroupOrder productGroupOrderToBeAdded = new ProductGroupOrder();
		try {
			productGroupOrderToBeAdded = ProductGroupOrderMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createProductGroupOrder(productGroupOrderToBeAdded);

	}

	/**
	 * creates a new ProductGroupOrder entry in the ofbiz database
	 * 
	 * @param productGroupOrderToBeAdded
	 *            the ProductGroupOrder thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createProductGroupOrder(@RequestBody ProductGroupOrder productGroupOrderToBeAdded) throws Exception {

		AddProductGroupOrder command = new AddProductGroupOrder(productGroupOrderToBeAdded);
		ProductGroupOrder productGroupOrder = ((ProductGroupOrderAdded) Scheduler.execute(command).data()).getAddedProductGroupOrder();
		
		if (productGroupOrder != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(productGroupOrder);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProductGroupOrder could not be created.");
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
	@PutMapping(value = "/update", consumes = "application/x-www-form-urlencoded")
	public boolean updateProductGroupOrder(HttpServletRequest request) throws Exception {

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

		ProductGroupOrder productGroupOrderToBeUpdated = new ProductGroupOrder();

		try {
			productGroupOrderToBeUpdated = ProductGroupOrderMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProductGroupOrder(productGroupOrderToBeUpdated, productGroupOrderToBeUpdated.getGroupOrderId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ProductGroupOrder with the specific Id
	 * 
	 * @param productGroupOrderToBeUpdated
	 *            the ProductGroupOrder thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{groupOrderId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateProductGroupOrder(@RequestBody ProductGroupOrder productGroupOrderToBeUpdated,
			@PathVariable String groupOrderId) throws Exception {

		productGroupOrderToBeUpdated.setGroupOrderId(groupOrderId);

		UpdateProductGroupOrder command = new UpdateProductGroupOrder(productGroupOrderToBeUpdated);

		try {
			if(((ProductGroupOrderUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{productGroupOrderId}")
	public ResponseEntity<Object> findById(@PathVariable String productGroupOrderId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productGroupOrderId", productGroupOrderId);
		try {

			Object foundProductGroupOrder = findProductGroupOrdersBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProductGroupOrder);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{productGroupOrderId}")
	public ResponseEntity<Object> deleteProductGroupOrderByIdUpdated(@PathVariable String productGroupOrderId) throws Exception {
		DeleteProductGroupOrder command = new DeleteProductGroupOrder(productGroupOrderId);

		try {
			if (((ProductGroupOrderDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductGroupOrder could not be deleted");

	}

}
