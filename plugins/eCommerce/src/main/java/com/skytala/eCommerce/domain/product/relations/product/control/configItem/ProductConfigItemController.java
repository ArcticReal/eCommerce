package com.skytala.eCommerce.domain.product.relations.product.control.configItem;

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
import com.skytala.eCommerce.domain.product.relations.product.command.configItem.AddProductConfigItem;
import com.skytala.eCommerce.domain.product.relations.product.command.configItem.DeleteProductConfigItem;
import com.skytala.eCommerce.domain.product.relations.product.command.configItem.UpdateProductConfigItem;
import com.skytala.eCommerce.domain.product.relations.product.event.configItem.ProductConfigItemAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.configItem.ProductConfigItemDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.configItem.ProductConfigItemFound;
import com.skytala.eCommerce.domain.product.relations.product.event.configItem.ProductConfigItemUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.configItem.ProductConfigItemMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.configItem.ProductConfigItem;
import com.skytala.eCommerce.domain.product.relations.product.query.configItem.FindProductConfigItemsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/product/product/productConfigItems")
public class ProductConfigItemController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductConfigItemController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductConfigItem
	 * @return a List with the ProductConfigItems
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findProductConfigItemsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductConfigItemsBy query = new FindProductConfigItemsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductConfigItem> productConfigItems =((ProductConfigItemFound) Scheduler.execute(query).data()).getProductConfigItems();

		if (productConfigItems.size() == 1) {
			return ResponseEntity.ok().body(productConfigItems.get(0));
		}

		return ResponseEntity.ok().body(productConfigItems);

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
	public ResponseEntity<Object> createProductConfigItem(HttpServletRequest request) throws Exception {

		ProductConfigItem productConfigItemToBeAdded = new ProductConfigItem();
		try {
			productConfigItemToBeAdded = ProductConfigItemMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createProductConfigItem(productConfigItemToBeAdded);

	}

	/**
	 * creates a new ProductConfigItem entry in the ofbiz database
	 * 
	 * @param productConfigItemToBeAdded
	 *            the ProductConfigItem thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createProductConfigItem(@RequestBody ProductConfigItem productConfigItemToBeAdded) throws Exception {

		AddProductConfigItem command = new AddProductConfigItem(productConfigItemToBeAdded);
		ProductConfigItem productConfigItem = ((ProductConfigItemAdded) Scheduler.execute(command).data()).getAddedProductConfigItem();
		
		if (productConfigItem != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(productConfigItem);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProductConfigItem could not be created.");
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
	public boolean updateProductConfigItem(HttpServletRequest request) throws Exception {

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

		ProductConfigItem productConfigItemToBeUpdated = new ProductConfigItem();

		try {
			productConfigItemToBeUpdated = ProductConfigItemMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProductConfigItem(productConfigItemToBeUpdated, productConfigItemToBeUpdated.getConfigItemId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ProductConfigItem with the specific Id
	 * 
	 * @param productConfigItemToBeUpdated
	 *            the ProductConfigItem thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{configItemId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateProductConfigItem(@RequestBody ProductConfigItem productConfigItemToBeUpdated,
			@PathVariable String configItemId) throws Exception {

		productConfigItemToBeUpdated.setConfigItemId(configItemId);

		UpdateProductConfigItem command = new UpdateProductConfigItem(productConfigItemToBeUpdated);

		try {
			if(((ProductConfigItemUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{productConfigItemId}")
	public ResponseEntity<Object> findById(@PathVariable String productConfigItemId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productConfigItemId", productConfigItemId);
		try {

			Object foundProductConfigItem = findProductConfigItemsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProductConfigItem);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{productConfigItemId}")
	public ResponseEntity<Object> deleteProductConfigItemByIdUpdated(@PathVariable String productConfigItemId) throws Exception {
		DeleteProductConfigItem command = new DeleteProductConfigItem(productConfigItemId);

		try {
			if (((ProductConfigItemDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductConfigItem could not be deleted");

	}

}
