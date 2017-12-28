package com.skytala.eCommerce.domain.product.relations.product.control.pricePurpose;

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
import com.skytala.eCommerce.domain.product.relations.product.command.pricePurpose.AddProductPricePurpose;
import com.skytala.eCommerce.domain.product.relations.product.command.pricePurpose.DeleteProductPricePurpose;
import com.skytala.eCommerce.domain.product.relations.product.command.pricePurpose.UpdateProductPricePurpose;
import com.skytala.eCommerce.domain.product.relations.product.event.pricePurpose.ProductPricePurposeAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.pricePurpose.ProductPricePurposeDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.pricePurpose.ProductPricePurposeFound;
import com.skytala.eCommerce.domain.product.relations.product.event.pricePurpose.ProductPricePurposeUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.pricePurpose.ProductPricePurposeMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.pricePurpose.ProductPricePurpose;
import com.skytala.eCommerce.domain.product.relations.product.query.pricePurpose.FindProductPricePurposesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/product/productPricePurposes")
public class ProductPricePurposeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductPricePurposeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductPricePurpose
	 * @return a List with the ProductPricePurposes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ProductPricePurpose>> findProductPricePurposesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductPricePurposesBy query = new FindProductPricePurposesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductPricePurpose> productPricePurposes =((ProductPricePurposeFound) Scheduler.execute(query).data()).getProductPricePurposes();

		return ResponseEntity.ok().body(productPricePurposes);

	}

	/**
	 * creates a new ProductPricePurpose entry in the ofbiz database
	 * 
	 * @param productPricePurposeToBeAdded
	 *            the ProductPricePurpose thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductPricePurpose> createProductPricePurpose(@RequestBody ProductPricePurpose productPricePurposeToBeAdded) throws Exception {

		AddProductPricePurpose command = new AddProductPricePurpose(productPricePurposeToBeAdded);
		ProductPricePurpose productPricePurpose = ((ProductPricePurposeAdded) Scheduler.execute(command).data()).getAddedProductPricePurpose();
		
		if (productPricePurpose != null) 
			return successful(productPricePurpose);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ProductPricePurpose with the specific Id
	 * 
	 * @param productPricePurposeToBeUpdated
	 *            the ProductPricePurpose thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{productPricePurposeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateProductPricePurpose(@RequestBody ProductPricePurpose productPricePurposeToBeUpdated,
			@PathVariable String productPricePurposeId) throws Exception {

		productPricePurposeToBeUpdated.setProductPricePurposeId(productPricePurposeId);

		UpdateProductPricePurpose command = new UpdateProductPricePurpose(productPricePurposeToBeUpdated);

		try {
			if(((ProductPricePurposeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{productPricePurposeId}")
	public ResponseEntity<ProductPricePurpose> findById(@PathVariable String productPricePurposeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productPricePurposeId", productPricePurposeId);
		try {

			List<ProductPricePurpose> foundProductPricePurpose = findProductPricePurposesBy(requestParams).getBody();
			if(foundProductPricePurpose.size()==1){				return successful(foundProductPricePurpose.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{productPricePurposeId}")
	public ResponseEntity<String> deleteProductPricePurposeByIdUpdated(@PathVariable String productPricePurposeId) throws Exception {
		DeleteProductPricePurpose command = new DeleteProductPricePurpose(productPricePurposeId);

		try {
			if (((ProductPricePurposeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
