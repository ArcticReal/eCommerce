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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	public ResponseEntity<List<ProductCategoryRollup>> findProductCategoryRollupsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductCategoryRollupsBy query = new FindProductCategoryRollupsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductCategoryRollup> productCategoryRollups =((ProductCategoryRollupFound) Scheduler.execute(query).data()).getProductCategoryRollups();

		return ResponseEntity.ok().body(productCategoryRollups);

	}

	/**
	 * creates a new ProductCategoryRollup entry in the ofbiz database
	 * 
	 * @param productCategoryRollupToBeAdded
	 *            the ProductCategoryRollup thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductCategoryRollup> createProductCategoryRollup(@RequestBody ProductCategoryRollup productCategoryRollupToBeAdded) throws Exception {

		AddProductCategoryRollup command = new AddProductCategoryRollup(productCategoryRollupToBeAdded);
		ProductCategoryRollup productCategoryRollup = ((ProductCategoryRollupAdded) Scheduler.execute(command).data()).getAddedProductCategoryRollup();
		
		if (productCategoryRollup != null) 
			return successful(productCategoryRollup);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateProductCategoryRollup(@RequestBody ProductCategoryRollup productCategoryRollupToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		productCategoryRollupToBeUpdated.setnull(null);

		UpdateProductCategoryRollup command = new UpdateProductCategoryRollup(productCategoryRollupToBeUpdated);

		try {
			if(((ProductCategoryRollupUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{productCategoryRollupId}")
	public ResponseEntity<ProductCategoryRollup> findById(@PathVariable String productCategoryRollupId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productCategoryRollupId", productCategoryRollupId);
		try {

			List<ProductCategoryRollup> foundProductCategoryRollup = findProductCategoryRollupsBy(requestParams).getBody();
			if(foundProductCategoryRollup.size()==1){				return successful(foundProductCategoryRollup.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{productCategoryRollupId}")
	public ResponseEntity<String> deleteProductCategoryRollupByIdUpdated(@PathVariable String productCategoryRollupId) throws Exception {
		DeleteProductCategoryRollup command = new DeleteProductCategoryRollup(productCategoryRollupId);

		try {
			if (((ProductCategoryRollupDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
