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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	public ResponseEntity<List<ProductOrderItem>> findProductOrderItemsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductOrderItemsBy query = new FindProductOrderItemsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductOrderItem> productOrderItems =((ProductOrderItemFound) Scheduler.execute(query).data()).getProductOrderItems();

		return ResponseEntity.ok().body(productOrderItems);

	}

	/**
	 * creates a new ProductOrderItem entry in the ofbiz database
	 * 
	 * @param productOrderItemToBeAdded
	 *            the ProductOrderItem thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductOrderItem> createProductOrderItem(@RequestBody ProductOrderItem productOrderItemToBeAdded) throws Exception {

		AddProductOrderItem command = new AddProductOrderItem(productOrderItemToBeAdded);
		ProductOrderItem productOrderItem = ((ProductOrderItemAdded) Scheduler.execute(command).data()).getAddedProductOrderItem();
		
		if (productOrderItem != null) 
			return successful(productOrderItem);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateProductOrderItem(@RequestBody ProductOrderItem productOrderItemToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		productOrderItemToBeUpdated.setnull(null);

		UpdateProductOrderItem command = new UpdateProductOrderItem(productOrderItemToBeUpdated);

		try {
			if(((ProductOrderItemUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{productOrderItemId}")
	public ResponseEntity<ProductOrderItem> findById(@PathVariable String productOrderItemId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productOrderItemId", productOrderItemId);
		try {

			List<ProductOrderItem> foundProductOrderItem = findProductOrderItemsBy(requestParams).getBody();
			if(foundProductOrderItem.size()==1){				return successful(foundProductOrderItem.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{productOrderItemId}")
	public ResponseEntity<String> deleteProductOrderItemByIdUpdated(@PathVariable String productOrderItemId) throws Exception {
		DeleteProductOrderItem command = new DeleteProductOrderItem(productOrderItemId);

		try {
			if (((ProductOrderItemDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
