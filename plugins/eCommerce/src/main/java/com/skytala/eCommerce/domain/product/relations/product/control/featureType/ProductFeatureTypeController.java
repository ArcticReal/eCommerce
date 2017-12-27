package com.skytala.eCommerce.domain.product.relations.product.control.featureType;

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
import com.skytala.eCommerce.domain.product.relations.product.command.featureType.AddProductFeatureType;
import com.skytala.eCommerce.domain.product.relations.product.command.featureType.DeleteProductFeatureType;
import com.skytala.eCommerce.domain.product.relations.product.command.featureType.UpdateProductFeatureType;
import com.skytala.eCommerce.domain.product.relations.product.event.featureType.ProductFeatureTypeAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.featureType.ProductFeatureTypeDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.featureType.ProductFeatureTypeFound;
import com.skytala.eCommerce.domain.product.relations.product.event.featureType.ProductFeatureTypeUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.featureType.ProductFeatureTypeMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.featureType.ProductFeatureType;
import com.skytala.eCommerce.domain.product.relations.product.query.featureType.FindProductFeatureTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/product/productFeatureTypes")
public class ProductFeatureTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductFeatureTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductFeatureType
	 * @return a List with the ProductFeatureTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ProductFeatureType>> findProductFeatureTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductFeatureTypesBy query = new FindProductFeatureTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductFeatureType> productFeatureTypes =((ProductFeatureTypeFound) Scheduler.execute(query).data()).getProductFeatureTypes();

		return ResponseEntity.ok().body(productFeatureTypes);

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
	public ResponseEntity<ProductFeatureType> createProductFeatureType(HttpServletRequest request) throws Exception {

		ProductFeatureType productFeatureTypeToBeAdded = new ProductFeatureType();
		try {
			productFeatureTypeToBeAdded = ProductFeatureTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createProductFeatureType(productFeatureTypeToBeAdded);

	}

	/**
	 * creates a new ProductFeatureType entry in the ofbiz database
	 * 
	 * @param productFeatureTypeToBeAdded
	 *            the ProductFeatureType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductFeatureType> createProductFeatureType(@RequestBody ProductFeatureType productFeatureTypeToBeAdded) throws Exception {

		AddProductFeatureType command = new AddProductFeatureType(productFeatureTypeToBeAdded);
		ProductFeatureType productFeatureType = ((ProductFeatureTypeAdded) Scheduler.execute(command).data()).getAddedProductFeatureType();
		
		if (productFeatureType != null) 
			return successful(productFeatureType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ProductFeatureType with the specific Id
	 * 
	 * @param productFeatureTypeToBeUpdated
	 *            the ProductFeatureType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{productFeatureTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateProductFeatureType(@RequestBody ProductFeatureType productFeatureTypeToBeUpdated,
			@PathVariable String productFeatureTypeId) throws Exception {

		productFeatureTypeToBeUpdated.setProductFeatureTypeId(productFeatureTypeId);

		UpdateProductFeatureType command = new UpdateProductFeatureType(productFeatureTypeToBeUpdated);

		try {
			if(((ProductFeatureTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{productFeatureTypeId}")
	public ResponseEntity<ProductFeatureType> findById(@PathVariable String productFeatureTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productFeatureTypeId", productFeatureTypeId);
		try {

			List<ProductFeatureType> foundProductFeatureType = findProductFeatureTypesBy(requestParams).getBody();
			if(foundProductFeatureType.size()==1){				return successful(foundProductFeatureType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{productFeatureTypeId}")
	public ResponseEntity<String> deleteProductFeatureTypeByIdUpdated(@PathVariable String productFeatureTypeId) throws Exception {
		DeleteProductFeatureType command = new DeleteProductFeatureType(productFeatureTypeId);

		try {
			if (((ProductFeatureTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
