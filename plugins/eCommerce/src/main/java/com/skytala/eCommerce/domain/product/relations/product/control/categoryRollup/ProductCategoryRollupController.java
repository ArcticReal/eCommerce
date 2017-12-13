package com.skytala.eCommerce.domain.product.relations.product.control.categoryRollup;

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
import com.skytala.eCommerce.domain.product.relations.product.command.categoryRollup.AddProductCategoryRollup;
import com.skytala.eCommerce.domain.product.relations.product.command.categoryRollup.DeleteProductCategoryRollup;
import com.skytala.eCommerce.domain.product.relations.product.command.categoryRollup.UpdateProductCategoryRollup;
import com.skytala.eCommerce.domain.product.relations.product.event.categoryRollup.ProductCategoryRollupAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.categoryRollup.ProductCategoryRollupDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.categoryRollup.ProductCategoryRollupFound;
import com.skytala.eCommerce.domain.product.relations.product.event.categoryRollup.ProductCategoryRollupUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.categoryRollup.ProductCategoryRollupMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.categoryRollup.ProductCategoryRollup;
import com.skytala.eCommerce.domain.product.relations.product.query.categoryRollup.FindProductCategoryRollupsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/product/product/productCategoryRollups")
public class ProductCategoryRollupController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductCategoryRollupController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductCategoryRollup
	 * @return a List with the ProductCategoryRollups
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findProductCategoryRollupsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductCategoryRollupsBy query = new FindProductCategoryRollupsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductCategoryRollup> productCategoryRollups =((ProductCategoryRollupFound) Scheduler.execute(query).data()).getProductCategoryRollups();

		if (productCategoryRollups.size() == 1) {
			return ResponseEntity.ok().body(productCategoryRollups.get(0));
		}

		return ResponseEntity.ok().body(productCategoryRollups);

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
	public ResponseEntity<Object> createProductCategoryRollup(HttpServletRequest request) throws Exception {

		ProductCategoryRollup productCategoryRollupToBeAdded = new ProductCategoryRollup();
		try {
			productCategoryRollupToBeAdded = ProductCategoryRollupMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createProductCategoryRollup(productCategoryRollupToBeAdded);

	}

	/**
	 * creates a new ProductCategoryRollup entry in the ofbiz database
	 * 
	 * @param productCategoryRollupToBeAdded
	 *            the ProductCategoryRollup thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createProductCategoryRollup(@RequestBody ProductCategoryRollup productCategoryRollupToBeAdded) throws Exception {

		AddProductCategoryRollup command = new AddProductCategoryRollup(productCategoryRollupToBeAdded);
		ProductCategoryRollup productCategoryRollup = ((ProductCategoryRollupAdded) Scheduler.execute(command).data()).getAddedProductCategoryRollup();
		
		if (productCategoryRollup != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(productCategoryRollup);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProductCategoryRollup could not be created.");
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
	public boolean updateProductCategoryRollup(HttpServletRequest request) throws Exception {

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

		ProductCategoryRollup productCategoryRollupToBeUpdated = new ProductCategoryRollup();

		try {
			productCategoryRollupToBeUpdated = ProductCategoryRollupMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProductCategoryRollup(productCategoryRollupToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ProductCategoryRollup with the specific Id
	 * 
	 * @param productCategoryRollupToBeUpdated
	 *            the ProductCategoryRollup thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateProductCategoryRollup(@RequestBody ProductCategoryRollup productCategoryRollupToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		productCategoryRollupToBeUpdated.setnull(null);

		UpdateProductCategoryRollup command = new UpdateProductCategoryRollup(productCategoryRollupToBeUpdated);

		try {
			if(((ProductCategoryRollupUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{productCategoryRollupId}")
	public ResponseEntity<Object> findById(@PathVariable String productCategoryRollupId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productCategoryRollupId", productCategoryRollupId);
		try {

			Object foundProductCategoryRollup = findProductCategoryRollupsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProductCategoryRollup);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{productCategoryRollupId}")
	public ResponseEntity<Object> deleteProductCategoryRollupByIdUpdated(@PathVariable String productCategoryRollupId) throws Exception {
		DeleteProductCategoryRollup command = new DeleteProductCategoryRollup(productCategoryRollupId);

		try {
			if (((ProductCategoryRollupDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductCategoryRollup could not be deleted");

	}

}
