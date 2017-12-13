package com.skytala.eCommerce.domain.product.relations.product.control.featureDataResource;

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
import com.skytala.eCommerce.domain.product.relations.product.command.featureDataResource.AddProductFeatureDataResource;
import com.skytala.eCommerce.domain.product.relations.product.command.featureDataResource.DeleteProductFeatureDataResource;
import com.skytala.eCommerce.domain.product.relations.product.command.featureDataResource.UpdateProductFeatureDataResource;
import com.skytala.eCommerce.domain.product.relations.product.event.featureDataResource.ProductFeatureDataResourceAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.featureDataResource.ProductFeatureDataResourceDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.featureDataResource.ProductFeatureDataResourceFound;
import com.skytala.eCommerce.domain.product.relations.product.event.featureDataResource.ProductFeatureDataResourceUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.featureDataResource.ProductFeatureDataResourceMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.featureDataResource.ProductFeatureDataResource;
import com.skytala.eCommerce.domain.product.relations.product.query.featureDataResource.FindProductFeatureDataResourcesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/product/product/productFeatureDataResources")
public class ProductFeatureDataResourceController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductFeatureDataResourceController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductFeatureDataResource
	 * @return a List with the ProductFeatureDataResources
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findProductFeatureDataResourcesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductFeatureDataResourcesBy query = new FindProductFeatureDataResourcesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductFeatureDataResource> productFeatureDataResources =((ProductFeatureDataResourceFound) Scheduler.execute(query).data()).getProductFeatureDataResources();

		if (productFeatureDataResources.size() == 1) {
			return ResponseEntity.ok().body(productFeatureDataResources.get(0));
		}

		return ResponseEntity.ok().body(productFeatureDataResources);

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
	public ResponseEntity<Object> createProductFeatureDataResource(HttpServletRequest request) throws Exception {

		ProductFeatureDataResource productFeatureDataResourceToBeAdded = new ProductFeatureDataResource();
		try {
			productFeatureDataResourceToBeAdded = ProductFeatureDataResourceMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createProductFeatureDataResource(productFeatureDataResourceToBeAdded);

	}

	/**
	 * creates a new ProductFeatureDataResource entry in the ofbiz database
	 * 
	 * @param productFeatureDataResourceToBeAdded
	 *            the ProductFeatureDataResource thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createProductFeatureDataResource(@RequestBody ProductFeatureDataResource productFeatureDataResourceToBeAdded) throws Exception {

		AddProductFeatureDataResource command = new AddProductFeatureDataResource(productFeatureDataResourceToBeAdded);
		ProductFeatureDataResource productFeatureDataResource = ((ProductFeatureDataResourceAdded) Scheduler.execute(command).data()).getAddedProductFeatureDataResource();
		
		if (productFeatureDataResource != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(productFeatureDataResource);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProductFeatureDataResource could not be created.");
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
	public boolean updateProductFeatureDataResource(HttpServletRequest request) throws Exception {

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

		ProductFeatureDataResource productFeatureDataResourceToBeUpdated = new ProductFeatureDataResource();

		try {
			productFeatureDataResourceToBeUpdated = ProductFeatureDataResourceMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProductFeatureDataResource(productFeatureDataResourceToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ProductFeatureDataResource with the specific Id
	 * 
	 * @param productFeatureDataResourceToBeUpdated
	 *            the ProductFeatureDataResource thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateProductFeatureDataResource(@RequestBody ProductFeatureDataResource productFeatureDataResourceToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		productFeatureDataResourceToBeUpdated.setnull(null);

		UpdateProductFeatureDataResource command = new UpdateProductFeatureDataResource(productFeatureDataResourceToBeUpdated);

		try {
			if(((ProductFeatureDataResourceUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{productFeatureDataResourceId}")
	public ResponseEntity<Object> findById(@PathVariable String productFeatureDataResourceId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productFeatureDataResourceId", productFeatureDataResourceId);
		try {

			Object foundProductFeatureDataResource = findProductFeatureDataResourcesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProductFeatureDataResource);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{productFeatureDataResourceId}")
	public ResponseEntity<Object> deleteProductFeatureDataResourceByIdUpdated(@PathVariable String productFeatureDataResourceId) throws Exception {
		DeleteProductFeatureDataResource command = new DeleteProductFeatureDataResource(productFeatureDataResourceId);

		try {
			if (((ProductFeatureDataResourceDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductFeatureDataResource could not be deleted");

	}

}
