package com.skytala.eCommerce.domain.product.relations.product.control.featureApplType;

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
import com.skytala.eCommerce.domain.product.relations.product.command.featureApplType.AddProductFeatureApplType;
import com.skytala.eCommerce.domain.product.relations.product.command.featureApplType.DeleteProductFeatureApplType;
import com.skytala.eCommerce.domain.product.relations.product.command.featureApplType.UpdateProductFeatureApplType;
import com.skytala.eCommerce.domain.product.relations.product.event.featureApplType.ProductFeatureApplTypeAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.featureApplType.ProductFeatureApplTypeDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.featureApplType.ProductFeatureApplTypeFound;
import com.skytala.eCommerce.domain.product.relations.product.event.featureApplType.ProductFeatureApplTypeUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.featureApplType.ProductFeatureApplTypeMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.featureApplType.ProductFeatureApplType;
import com.skytala.eCommerce.domain.product.relations.product.query.featureApplType.FindProductFeatureApplTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/product/productFeatureApplTypes")
public class ProductFeatureApplTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductFeatureApplTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductFeatureApplType
	 * @return a List with the ProductFeatureApplTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ProductFeatureApplType>> findProductFeatureApplTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductFeatureApplTypesBy query = new FindProductFeatureApplTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductFeatureApplType> productFeatureApplTypes =((ProductFeatureApplTypeFound) Scheduler.execute(query).data()).getProductFeatureApplTypes();

		return ResponseEntity.ok().body(productFeatureApplTypes);

	}

	/**
	 * creates a new ProductFeatureApplType entry in the ofbiz database
	 * 
	 * @param productFeatureApplTypeToBeAdded
	 *            the ProductFeatureApplType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductFeatureApplType> createProductFeatureApplType(@RequestBody ProductFeatureApplType productFeatureApplTypeToBeAdded) throws Exception {

		AddProductFeatureApplType command = new AddProductFeatureApplType(productFeatureApplTypeToBeAdded);
		ProductFeatureApplType productFeatureApplType = ((ProductFeatureApplTypeAdded) Scheduler.execute(command).data()).getAddedProductFeatureApplType();
		
		if (productFeatureApplType != null) 
			return successful(productFeatureApplType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ProductFeatureApplType with the specific Id
	 * 
	 * @param productFeatureApplTypeToBeUpdated
	 *            the ProductFeatureApplType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{productFeatureApplTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateProductFeatureApplType(@RequestBody ProductFeatureApplType productFeatureApplTypeToBeUpdated,
			@PathVariable String productFeatureApplTypeId) throws Exception {

		productFeatureApplTypeToBeUpdated.setProductFeatureApplTypeId(productFeatureApplTypeId);

		UpdateProductFeatureApplType command = new UpdateProductFeatureApplType(productFeatureApplTypeToBeUpdated);

		try {
			if(((ProductFeatureApplTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{productFeatureApplTypeId}")
	public ResponseEntity<ProductFeatureApplType> findById(@PathVariable String productFeatureApplTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productFeatureApplTypeId", productFeatureApplTypeId);
		try {

			List<ProductFeatureApplType> foundProductFeatureApplType = findProductFeatureApplTypesBy(requestParams).getBody();
			if(foundProductFeatureApplType.size()==1){				return successful(foundProductFeatureApplType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{productFeatureApplTypeId}")
	public ResponseEntity<String> deleteProductFeatureApplTypeByIdUpdated(@PathVariable String productFeatureApplTypeId) throws Exception {
		DeleteProductFeatureApplType command = new DeleteProductFeatureApplType(productFeatureApplTypeId);

		try {
			if (((ProductFeatureApplTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
