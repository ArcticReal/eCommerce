package com.skytala.eCommerce.domain.product.relations.product.control.featurePrice;

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
import com.skytala.eCommerce.domain.product.relations.product.command.featurePrice.AddProductFeaturePrice;
import com.skytala.eCommerce.domain.product.relations.product.command.featurePrice.DeleteProductFeaturePrice;
import com.skytala.eCommerce.domain.product.relations.product.command.featurePrice.UpdateProductFeaturePrice;
import com.skytala.eCommerce.domain.product.relations.product.event.featurePrice.ProductFeaturePriceAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.featurePrice.ProductFeaturePriceDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.featurePrice.ProductFeaturePriceFound;
import com.skytala.eCommerce.domain.product.relations.product.event.featurePrice.ProductFeaturePriceUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.featurePrice.ProductFeaturePriceMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.featurePrice.ProductFeaturePrice;
import com.skytala.eCommerce.domain.product.relations.product.query.featurePrice.FindProductFeaturePricesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/product/productFeaturePrices")
public class ProductFeaturePriceController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductFeaturePriceController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductFeaturePrice
	 * @return a List with the ProductFeaturePrices
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ProductFeaturePrice>> findProductFeaturePricesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductFeaturePricesBy query = new FindProductFeaturePricesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductFeaturePrice> productFeaturePrices =((ProductFeaturePriceFound) Scheduler.execute(query).data()).getProductFeaturePrices();

		return ResponseEntity.ok().body(productFeaturePrices);

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
	public ResponseEntity<ProductFeaturePrice> createProductFeaturePrice(HttpServletRequest request) throws Exception {

		ProductFeaturePrice productFeaturePriceToBeAdded = new ProductFeaturePrice();
		try {
			productFeaturePriceToBeAdded = ProductFeaturePriceMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createProductFeaturePrice(productFeaturePriceToBeAdded);

	}

	/**
	 * creates a new ProductFeaturePrice entry in the ofbiz database
	 * 
	 * @param productFeaturePriceToBeAdded
	 *            the ProductFeaturePrice thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductFeaturePrice> createProductFeaturePrice(@RequestBody ProductFeaturePrice productFeaturePriceToBeAdded) throws Exception {

		AddProductFeaturePrice command = new AddProductFeaturePrice(productFeaturePriceToBeAdded);
		ProductFeaturePrice productFeaturePrice = ((ProductFeaturePriceAdded) Scheduler.execute(command).data()).getAddedProductFeaturePrice();
		
		if (productFeaturePrice != null) 
			return successful(productFeaturePrice);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ProductFeaturePrice with the specific Id
	 * 
	 * @param productFeaturePriceToBeUpdated
	 *            the ProductFeaturePrice thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{productFeatureId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateProductFeaturePrice(@RequestBody ProductFeaturePrice productFeaturePriceToBeUpdated,
			@PathVariable String productFeatureId) throws Exception {

		productFeaturePriceToBeUpdated.setProductFeatureId(productFeatureId);

		UpdateProductFeaturePrice command = new UpdateProductFeaturePrice(productFeaturePriceToBeUpdated);

		try {
			if(((ProductFeaturePriceUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{productFeaturePriceId}")
	public ResponseEntity<ProductFeaturePrice> findById(@PathVariable String productFeaturePriceId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productFeaturePriceId", productFeaturePriceId);
		try {

			List<ProductFeaturePrice> foundProductFeaturePrice = findProductFeaturePricesBy(requestParams).getBody();
			if(foundProductFeaturePrice.size()==1){				return successful(foundProductFeaturePrice.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{productFeaturePriceId}")
	public ResponseEntity<String> deleteProductFeaturePriceByIdUpdated(@PathVariable String productFeaturePriceId) throws Exception {
		DeleteProductFeaturePrice command = new DeleteProductFeaturePrice(productFeaturePriceId);

		try {
			if (((ProductFeaturePriceDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
