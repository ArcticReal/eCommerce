package com.skytala.eCommerce.domain.product.relations.product.control.priceChange;

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
import com.skytala.eCommerce.domain.product.relations.product.command.priceChange.AddProductPriceChange;
import com.skytala.eCommerce.domain.product.relations.product.command.priceChange.DeleteProductPriceChange;
import com.skytala.eCommerce.domain.product.relations.product.command.priceChange.UpdateProductPriceChange;
import com.skytala.eCommerce.domain.product.relations.product.event.priceChange.ProductPriceChangeAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.priceChange.ProductPriceChangeDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.priceChange.ProductPriceChangeFound;
import com.skytala.eCommerce.domain.product.relations.product.event.priceChange.ProductPriceChangeUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.priceChange.ProductPriceChangeMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.priceChange.ProductPriceChange;
import com.skytala.eCommerce.domain.product.relations.product.query.priceChange.FindProductPriceChangesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/product/productPriceChanges")
public class ProductPriceChangeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductPriceChangeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductPriceChange
	 * @return a List with the ProductPriceChanges
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ProductPriceChange>> findProductPriceChangesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductPriceChangesBy query = new FindProductPriceChangesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductPriceChange> productPriceChanges =((ProductPriceChangeFound) Scheduler.execute(query).data()).getProductPriceChanges();

		return ResponseEntity.ok().body(productPriceChanges);

	}

	/**
	 * creates a new ProductPriceChange entry in the ofbiz database
	 * 
	 * @param productPriceChangeToBeAdded
	 *            the ProductPriceChange thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductPriceChange> createProductPriceChange(@RequestBody ProductPriceChange productPriceChangeToBeAdded) throws Exception {

		AddProductPriceChange command = new AddProductPriceChange(productPriceChangeToBeAdded);
		ProductPriceChange productPriceChange = ((ProductPriceChangeAdded) Scheduler.execute(command).data()).getAddedProductPriceChange();
		
		if (productPriceChange != null) 
			return successful(productPriceChange);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ProductPriceChange with the specific Id
	 * 
	 * @param productPriceChangeToBeUpdated
	 *            the ProductPriceChange thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{productPriceChangeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateProductPriceChange(@RequestBody ProductPriceChange productPriceChangeToBeUpdated,
			@PathVariable String productPriceChangeId) throws Exception {

		productPriceChangeToBeUpdated.setProductPriceChangeId(productPriceChangeId);

		UpdateProductPriceChange command = new UpdateProductPriceChange(productPriceChangeToBeUpdated);

		try {
			if(((ProductPriceChangeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{productPriceChangeId}")
	public ResponseEntity<ProductPriceChange> findById(@PathVariable String productPriceChangeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productPriceChangeId", productPriceChangeId);
		try {

			List<ProductPriceChange> foundProductPriceChange = findProductPriceChangesBy(requestParams).getBody();
			if(foundProductPriceChange.size()==1){				return successful(foundProductPriceChange.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{productPriceChangeId}")
	public ResponseEntity<String> deleteProductPriceChangeByIdUpdated(@PathVariable String productPriceChangeId) throws Exception {
		DeleteProductPriceChange command = new DeleteProductPriceChange(productPriceChangeId);

		try {
			if (((ProductPriceChangeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
