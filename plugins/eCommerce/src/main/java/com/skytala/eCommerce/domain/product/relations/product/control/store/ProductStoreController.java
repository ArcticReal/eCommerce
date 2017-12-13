package com.skytala.eCommerce.domain.product.relations.product.control.store;

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
import com.skytala.eCommerce.domain.product.relations.product.command.store.AddProductStore;
import com.skytala.eCommerce.domain.product.relations.product.command.store.DeleteProductStore;
import com.skytala.eCommerce.domain.product.relations.product.command.store.UpdateProductStore;
import com.skytala.eCommerce.domain.product.relations.product.event.store.ProductStoreAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.store.ProductStoreDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.store.ProductStoreFound;
import com.skytala.eCommerce.domain.product.relations.product.event.store.ProductStoreUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.store.ProductStoreMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.store.ProductStore;
import com.skytala.eCommerce.domain.product.relations.product.query.store.FindProductStoresBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/product/product/productStores")
public class ProductStoreController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductStoreController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductStore
	 * @return a List with the ProductStores
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findProductStoresBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductStoresBy query = new FindProductStoresBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductStore> productStores =((ProductStoreFound) Scheduler.execute(query).data()).getProductStores();

		if (productStores.size() == 1) {
			return ResponseEntity.ok().body(productStores.get(0));
		}

		return ResponseEntity.ok().body(productStores);

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
	public ResponseEntity<Object> createProductStore(HttpServletRequest request) throws Exception {

		ProductStore productStoreToBeAdded = new ProductStore();
		try {
			productStoreToBeAdded = ProductStoreMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createProductStore(productStoreToBeAdded);

	}

	/**
	 * creates a new ProductStore entry in the ofbiz database
	 * 
	 * @param productStoreToBeAdded
	 *            the ProductStore thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createProductStore(@RequestBody ProductStore productStoreToBeAdded) throws Exception {

		AddProductStore command = new AddProductStore(productStoreToBeAdded);
		ProductStore productStore = ((ProductStoreAdded) Scheduler.execute(command).data()).getAddedProductStore();
		
		if (productStore != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(productStore);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProductStore could not be created.");
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
	public boolean updateProductStore(HttpServletRequest request) throws Exception {

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

		ProductStore productStoreToBeUpdated = new ProductStore();

		try {
			productStoreToBeUpdated = ProductStoreMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProductStore(productStoreToBeUpdated, productStoreToBeUpdated.getProductStoreId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ProductStore with the specific Id
	 * 
	 * @param productStoreToBeUpdated
	 *            the ProductStore thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{productStoreId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateProductStore(@RequestBody ProductStore productStoreToBeUpdated,
			@PathVariable String productStoreId) throws Exception {

		productStoreToBeUpdated.setProductStoreId(productStoreId);

		UpdateProductStore command = new UpdateProductStore(productStoreToBeUpdated);

		try {
			if(((ProductStoreUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{productStoreId}")
	public ResponseEntity<Object> findById(@PathVariable String productStoreId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productStoreId", productStoreId);
		try {

			Object foundProductStore = findProductStoresBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProductStore);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{productStoreId}")
	public ResponseEntity<Object> deleteProductStoreByIdUpdated(@PathVariable String productStoreId) throws Exception {
		DeleteProductStore command = new DeleteProductStore(productStoreId);

		try {
			if (((ProductStoreDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductStore could not be deleted");

	}

}
