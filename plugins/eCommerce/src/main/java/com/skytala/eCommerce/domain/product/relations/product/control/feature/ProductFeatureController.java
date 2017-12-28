package com.skytala.eCommerce.domain.product.relations.product.control.feature;

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
import com.skytala.eCommerce.domain.product.relations.product.command.feature.AddProductFeature;
import com.skytala.eCommerce.domain.product.relations.product.command.feature.DeleteProductFeature;
import com.skytala.eCommerce.domain.product.relations.product.command.feature.UpdateProductFeature;
import com.skytala.eCommerce.domain.product.relations.product.event.feature.ProductFeatureAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.feature.ProductFeatureDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.feature.ProductFeatureFound;
import com.skytala.eCommerce.domain.product.relations.product.event.feature.ProductFeatureUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.feature.ProductFeatureMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.feature.ProductFeature;
import com.skytala.eCommerce.domain.product.relations.product.query.feature.FindProductFeaturesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/product/productFeatures")
public class ProductFeatureController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductFeatureController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductFeature
	 * @return a List with the ProductFeatures
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ProductFeature>> findProductFeaturesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductFeaturesBy query = new FindProductFeaturesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductFeature> productFeatures =((ProductFeatureFound) Scheduler.execute(query).data()).getProductFeatures();

		return ResponseEntity.ok().body(productFeatures);

	}

	/**
	 * creates a new ProductFeature entry in the ofbiz database
	 * 
	 * @param productFeatureToBeAdded
	 *            the ProductFeature thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductFeature> createProductFeature(@RequestBody ProductFeature productFeatureToBeAdded) throws Exception {

		AddProductFeature command = new AddProductFeature(productFeatureToBeAdded);
		ProductFeature productFeature = ((ProductFeatureAdded) Scheduler.execute(command).data()).getAddedProductFeature();
		
		if (productFeature != null) 
			return successful(productFeature);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ProductFeature with the specific Id
	 * 
	 * @param productFeatureToBeUpdated
	 *            the ProductFeature thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{productFeatureId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateProductFeature(@RequestBody ProductFeature productFeatureToBeUpdated,
			@PathVariable String productFeatureId) throws Exception {

		productFeatureToBeUpdated.setProductFeatureId(productFeatureId);

		UpdateProductFeature command = new UpdateProductFeature(productFeatureToBeUpdated);

		try {
			if(((ProductFeatureUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{productFeatureId}")
	public ResponseEntity<ProductFeature> findById(@PathVariable String productFeatureId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productFeatureId", productFeatureId);
		try {

			List<ProductFeature> foundProductFeature = findProductFeaturesBy(requestParams).getBody();
			if(foundProductFeature.size()==1){				return successful(foundProductFeature.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{productFeatureId}")
	public ResponseEntity<String> deleteProductFeatureByIdUpdated(@PathVariable String productFeatureId) throws Exception {
		DeleteProductFeature command = new DeleteProductFeature(productFeatureId);

		try {
			if (((ProductFeatureDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
