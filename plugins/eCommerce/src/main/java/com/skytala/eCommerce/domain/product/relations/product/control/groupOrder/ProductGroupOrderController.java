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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	public ResponseEntity<List<ProductGroupOrder>> findProductGroupOrdersBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductGroupOrdersBy query = new FindProductGroupOrdersBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductGroupOrder> productGroupOrders =((ProductGroupOrderFound) Scheduler.execute(query).data()).getProductGroupOrders();

		return ResponseEntity.ok().body(productGroupOrders);

	}

	/**
	 * creates a new ProductGroupOrder entry in the ofbiz database
	 * 
	 * @param productGroupOrderToBeAdded
	 *            the ProductGroupOrder thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductGroupOrder> createProductGroupOrder(@RequestBody ProductGroupOrder productGroupOrderToBeAdded) throws Exception {

		AddProductGroupOrder command = new AddProductGroupOrder(productGroupOrderToBeAdded);
		ProductGroupOrder productGroupOrder = ((ProductGroupOrderAdded) Scheduler.execute(command).data()).getAddedProductGroupOrder();
		
		if (productGroupOrder != null) 
			return successful(productGroupOrder);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateProductGroupOrder(@RequestBody ProductGroupOrder productGroupOrderToBeUpdated,
			@PathVariable String groupOrderId) throws Exception {

		productGroupOrderToBeUpdated.setGroupOrderId(groupOrderId);

		UpdateProductGroupOrder command = new UpdateProductGroupOrder(productGroupOrderToBeUpdated);

		try {
			if(((ProductGroupOrderUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{productGroupOrderId}")
	public ResponseEntity<ProductGroupOrder> findById(@PathVariable String productGroupOrderId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productGroupOrderId", productGroupOrderId);
		try {

			List<ProductGroupOrder> foundProductGroupOrder = findProductGroupOrdersBy(requestParams).getBody();
			if(foundProductGroupOrder.size()==1){				return successful(foundProductGroupOrder.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{productGroupOrderId}")
	public ResponseEntity<String> deleteProductGroupOrderByIdUpdated(@PathVariable String productGroupOrderId) throws Exception {
		DeleteProductGroupOrder command = new DeleteProductGroupOrder(productGroupOrderId);

		try {
			if (((ProductGroupOrderDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
