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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	public ResponseEntity<List<ProductFeatureDataResource>> findProductFeatureDataResourcesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductFeatureDataResourcesBy query = new FindProductFeatureDataResourcesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductFeatureDataResource> productFeatureDataResources =((ProductFeatureDataResourceFound) Scheduler.execute(query).data()).getProductFeatureDataResources();

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
	public ResponseEntity<ProductFeatureDataResource> createProductFeatureDataResource(HttpServletRequest request) throws Exception {

		ProductFeatureDataResource productFeatureDataResourceToBeAdded = new ProductFeatureDataResource();
		try {
			productFeatureDataResourceToBeAdded = ProductFeatureDataResourceMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
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
	public ResponseEntity<ProductFeatureDataResource> createProductFeatureDataResource(@RequestBody ProductFeatureDataResource productFeatureDataResourceToBeAdded) throws Exception {

		AddProductFeatureDataResource command = new AddProductFeatureDataResource(productFeatureDataResourceToBeAdded);
		ProductFeatureDataResource productFeatureDataResource = ((ProductFeatureDataResourceAdded) Scheduler.execute(command).data()).getAddedProductFeatureDataResource();
		
		if (productFeatureDataResource != null) 
			return successful(productFeatureDataResource);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateProductFeatureDataResource(@RequestBody ProductFeatureDataResource productFeatureDataResourceToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		productFeatureDataResourceToBeUpdated.setnull(null);

		UpdateProductFeatureDataResource command = new UpdateProductFeatureDataResource(productFeatureDataResourceToBeUpdated);

		try {
			if(((ProductFeatureDataResourceUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{productFeatureDataResourceId}")
	public ResponseEntity<ProductFeatureDataResource> findById(@PathVariable String productFeatureDataResourceId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productFeatureDataResourceId", productFeatureDataResourceId);
		try {

			List<ProductFeatureDataResource> foundProductFeatureDataResource = findProductFeatureDataResourcesBy(requestParams).getBody();
			if(foundProductFeatureDataResource.size()==1){				return successful(foundProductFeatureDataResource.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{productFeatureDataResourceId}")
	public ResponseEntity<String> deleteProductFeatureDataResourceByIdUpdated(@PathVariable String productFeatureDataResourceId) throws Exception {
		DeleteProductFeatureDataResource command = new DeleteProductFeatureDataResource(productFeatureDataResourceId);

		try {
			if (((ProductFeatureDataResourceDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
