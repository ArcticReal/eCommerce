package com.skytala.eCommerce.domain.product.relations.product.control.featureCategory;

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
import com.skytala.eCommerce.domain.product.relations.product.command.featureCategory.AddProductFeatureCategory;
import com.skytala.eCommerce.domain.product.relations.product.command.featureCategory.DeleteProductFeatureCategory;
import com.skytala.eCommerce.domain.product.relations.product.command.featureCategory.UpdateProductFeatureCategory;
import com.skytala.eCommerce.domain.product.relations.product.event.featureCategory.ProductFeatureCategoryAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.featureCategory.ProductFeatureCategoryDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.featureCategory.ProductFeatureCategoryFound;
import com.skytala.eCommerce.domain.product.relations.product.event.featureCategory.ProductFeatureCategoryUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.featureCategory.ProductFeatureCategoryMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.featureCategory.ProductFeatureCategory;
import com.skytala.eCommerce.domain.product.relations.product.query.featureCategory.FindProductFeatureCategorysBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/product/productFeatureCategorys")
public class ProductFeatureCategoryController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductFeatureCategoryController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductFeatureCategory
	 * @return a List with the ProductFeatureCategorys
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ProductFeatureCategory>> findProductFeatureCategorysBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductFeatureCategorysBy query = new FindProductFeatureCategorysBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductFeatureCategory> productFeatureCategorys =((ProductFeatureCategoryFound) Scheduler.execute(query).data()).getProductFeatureCategorys();

		return ResponseEntity.ok().body(productFeatureCategorys);

	}

	/**
	 * creates a new ProductFeatureCategory entry in the ofbiz database
	 * 
	 * @param productFeatureCategoryToBeAdded
	 *            the ProductFeatureCategory thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductFeatureCategory> createProductFeatureCategory(@RequestBody ProductFeatureCategory productFeatureCategoryToBeAdded) throws Exception {

		AddProductFeatureCategory command = new AddProductFeatureCategory(productFeatureCategoryToBeAdded);
		ProductFeatureCategory productFeatureCategory = ((ProductFeatureCategoryAdded) Scheduler.execute(command).data()).getAddedProductFeatureCategory();
		
		if (productFeatureCategory != null) 
			return successful(productFeatureCategory);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ProductFeatureCategory with the specific Id
	 * 
	 * @param productFeatureCategoryToBeUpdated
	 *            the ProductFeatureCategory thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{productFeatureCategoryId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateProductFeatureCategory(@RequestBody ProductFeatureCategory productFeatureCategoryToBeUpdated,
			@PathVariable String productFeatureCategoryId) throws Exception {

		productFeatureCategoryToBeUpdated.setProductFeatureCategoryId(productFeatureCategoryId);

		UpdateProductFeatureCategory command = new UpdateProductFeatureCategory(productFeatureCategoryToBeUpdated);

		try {
			if(((ProductFeatureCategoryUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{productFeatureCategoryId}")
	public ResponseEntity<ProductFeatureCategory> findById(@PathVariable String productFeatureCategoryId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productFeatureCategoryId", productFeatureCategoryId);
		try {

			List<ProductFeatureCategory> foundProductFeatureCategory = findProductFeatureCategorysBy(requestParams).getBody();
			if(foundProductFeatureCategory.size()==1){				return successful(foundProductFeatureCategory.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{productFeatureCategoryId}")
	public ResponseEntity<String> deleteProductFeatureCategoryByIdUpdated(@PathVariable String productFeatureCategoryId) throws Exception {
		DeleteProductFeatureCategory command = new DeleteProductFeatureCategory(productFeatureCategoryId);

		try {
			if (((ProductFeatureCategoryDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
