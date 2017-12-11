package com.skytala.eCommerce.domain.order.relations.orderItem.control.product;

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
import com.skytala.eCommerce.domain.order.relations.orderItem.command.product.AddProductOrderItem;
import com.skytala.eCommerce.domain.order.relations.orderItem.command.product.DeleteProductOrderItem;
import com.skytala.eCommerce.domain.order.relations.orderItem.command.product.UpdateProductOrderItem;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.product.ProductOrderItemAdded;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.product.ProductOrderItemDeleted;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.product.ProductOrderItemFound;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.product.ProductOrderItemUpdated;
import com.skytala.eCommerce.domain.order.relations.orderItem.mapper.product.ProductOrderItemMapper;
import com.skytala.eCommerce.domain.order.relations.orderItem.model.product.ProductOrderItem;
import com.skytala.eCommerce.domain.order.relations.orderItem.query.product.FindProductOrderItemsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/order/orderItem/productOrderItems")
public class ProductOrderItemController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductOrderItemController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductOrderItem
	 * @return a List with the ProductOrderItems
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findProductOrderItemsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductOrderItemsBy query = new FindProductOrderItemsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductOrderItem> productOrderItems =((ProductOrderItemFound) Scheduler.execute(query).data()).getProductOrderItems();

		if (productOrderItems.size() == 1) {
			return ResponseEntity.ok().body(productOrderItems.get(0));
		}

		return ResponseEntity.ok().body(productOrderItems);

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
	public ResponseEntity<Object> createProductOrderItem(HttpServletRequest request) throws Exception {

		ProductOrderItem productOrderItemToBeAdded = new ProductOrderItem();
		try {
			productOrderItemToBeAdded = ProductOrderItemMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createProductOrderItem(productOrderItemToBeAdded);

	}

	/**
	 * creates a new ProductOrderItem entry in the ofbiz database
	 * 
	 * @param productOrderItemToBeAdded
	 *            the ProductOrderItem thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createProductOrderItem(@RequestBody ProductOrderItem productOrderItemToBeAdded) throws Exception {

		AddProductOrderItem command = new AddProductOrderItem(productOrderItemToBeAdded);
		ProductOrderItem productOrderItem = ((ProductOrderItemAdded) Scheduler.execute(command).data()).getAddedProductOrderItem();
		
		if (productOrderItem != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(productOrderItem);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProductOrderItem could not be created.");
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
	public boolean updateProductOrderItem(HttpServletRequest request) throws Exception {

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

		ProductOrderItem productOrderItemToBeUpdated = new ProductOrderItem();

		try {
			productOrderItemToBeUpdated = ProductOrderItemMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProductOrderItem(productOrderItemToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ProductOrderItem with the specific Id
	 * 
	 * @param productOrderItemToBeUpdated
	 *            the ProductOrderItem thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateProductOrderItem(@RequestBody ProductOrderItem productOrderItemToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		productOrderItemToBeUpdated.setnull(null);

		UpdateProductOrderItem command = new UpdateProductOrderItem(productOrderItemToBeUpdated);

		try {
			if(((ProductOrderItemUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{productOrderItemId}")
	public ResponseEntity<Object> findById(@PathVariable String productOrderItemId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productOrderItemId", productOrderItemId);
		try {

			Object foundProductOrderItem = findProductOrderItemsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProductOrderItem);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{productOrderItemId}")
	public ResponseEntity<Object> deleteProductOrderItemByIdUpdated(@PathVariable String productOrderItemId) throws Exception {
		DeleteProductOrderItem command = new DeleteProductOrderItem(productOrderItemId);

		try {
			if (((ProductOrderItemDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductOrderItem could not be deleted");

	}

}
