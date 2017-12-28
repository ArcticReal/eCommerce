package com.skytala.eCommerce.domain.product.relations.product.control.subscriptionResource;

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
import com.skytala.eCommerce.domain.product.relations.product.command.subscriptionResource.AddProductSubscriptionResource;
import com.skytala.eCommerce.domain.product.relations.product.command.subscriptionResource.DeleteProductSubscriptionResource;
import com.skytala.eCommerce.domain.product.relations.product.command.subscriptionResource.UpdateProductSubscriptionResource;
import com.skytala.eCommerce.domain.product.relations.product.event.subscriptionResource.ProductSubscriptionResourceAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.subscriptionResource.ProductSubscriptionResourceDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.subscriptionResource.ProductSubscriptionResourceFound;
import com.skytala.eCommerce.domain.product.relations.product.event.subscriptionResource.ProductSubscriptionResourceUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.subscriptionResource.ProductSubscriptionResourceMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.subscriptionResource.ProductSubscriptionResource;
import com.skytala.eCommerce.domain.product.relations.product.query.subscriptionResource.FindProductSubscriptionResourcesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/product/productSubscriptionResources")
public class ProductSubscriptionResourceController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductSubscriptionResourceController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductSubscriptionResource
	 * @return a List with the ProductSubscriptionResources
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ProductSubscriptionResource>> findProductSubscriptionResourcesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductSubscriptionResourcesBy query = new FindProductSubscriptionResourcesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductSubscriptionResource> productSubscriptionResources =((ProductSubscriptionResourceFound) Scheduler.execute(query).data()).getProductSubscriptionResources();

		return ResponseEntity.ok().body(productSubscriptionResources);

	}

	/**
	 * creates a new ProductSubscriptionResource entry in the ofbiz database
	 * 
	 * @param productSubscriptionResourceToBeAdded
	 *            the ProductSubscriptionResource thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductSubscriptionResource> createProductSubscriptionResource(@RequestBody ProductSubscriptionResource productSubscriptionResourceToBeAdded) throws Exception {

		AddProductSubscriptionResource command = new AddProductSubscriptionResource(productSubscriptionResourceToBeAdded);
		ProductSubscriptionResource productSubscriptionResource = ((ProductSubscriptionResourceAdded) Scheduler.execute(command).data()).getAddedProductSubscriptionResource();
		
		if (productSubscriptionResource != null) 
			return successful(productSubscriptionResource);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ProductSubscriptionResource with the specific Id
	 * 
	 * @param productSubscriptionResourceToBeUpdated
	 *            the ProductSubscriptionResource thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateProductSubscriptionResource(@RequestBody ProductSubscriptionResource productSubscriptionResourceToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		productSubscriptionResourceToBeUpdated.setnull(null);

		UpdateProductSubscriptionResource command = new UpdateProductSubscriptionResource(productSubscriptionResourceToBeUpdated);

		try {
			if(((ProductSubscriptionResourceUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{productSubscriptionResourceId}")
	public ResponseEntity<ProductSubscriptionResource> findById(@PathVariable String productSubscriptionResourceId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productSubscriptionResourceId", productSubscriptionResourceId);
		try {

			List<ProductSubscriptionResource> foundProductSubscriptionResource = findProductSubscriptionResourcesBy(requestParams).getBody();
			if(foundProductSubscriptionResource.size()==1){				return successful(foundProductSubscriptionResource.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{productSubscriptionResourceId}")
	public ResponseEntity<String> deleteProductSubscriptionResourceByIdUpdated(@PathVariable String productSubscriptionResourceId) throws Exception {
		DeleteProductSubscriptionResource command = new DeleteProductSubscriptionResource(productSubscriptionResourceId);

		try {
			if (((ProductSubscriptionResourceDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
