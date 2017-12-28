package com.skytala.eCommerce.domain.product.relations.product.control.storeGroupRollup;

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
import com.skytala.eCommerce.domain.product.relations.product.command.storeGroupRollup.AddProductStoreGroupRollup;
import com.skytala.eCommerce.domain.product.relations.product.command.storeGroupRollup.DeleteProductStoreGroupRollup;
import com.skytala.eCommerce.domain.product.relations.product.command.storeGroupRollup.UpdateProductStoreGroupRollup;
import com.skytala.eCommerce.domain.product.relations.product.event.storeGroupRollup.ProductStoreGroupRollupAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.storeGroupRollup.ProductStoreGroupRollupDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.storeGroupRollup.ProductStoreGroupRollupFound;
import com.skytala.eCommerce.domain.product.relations.product.event.storeGroupRollup.ProductStoreGroupRollupUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.storeGroupRollup.ProductStoreGroupRollupMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.storeGroupRollup.ProductStoreGroupRollup;
import com.skytala.eCommerce.domain.product.relations.product.query.storeGroupRollup.FindProductStoreGroupRollupsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/product/productStoreGroupRollups")
public class ProductStoreGroupRollupController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductStoreGroupRollupController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductStoreGroupRollup
	 * @return a List with the ProductStoreGroupRollups
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ProductStoreGroupRollup>> findProductStoreGroupRollupsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductStoreGroupRollupsBy query = new FindProductStoreGroupRollupsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductStoreGroupRollup> productStoreGroupRollups =((ProductStoreGroupRollupFound) Scheduler.execute(query).data()).getProductStoreGroupRollups();

		return ResponseEntity.ok().body(productStoreGroupRollups);

	}

	/**
	 * creates a new ProductStoreGroupRollup entry in the ofbiz database
	 * 
	 * @param productStoreGroupRollupToBeAdded
	 *            the ProductStoreGroupRollup thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductStoreGroupRollup> createProductStoreGroupRollup(@RequestBody ProductStoreGroupRollup productStoreGroupRollupToBeAdded) throws Exception {

		AddProductStoreGroupRollup command = new AddProductStoreGroupRollup(productStoreGroupRollupToBeAdded);
		ProductStoreGroupRollup productStoreGroupRollup = ((ProductStoreGroupRollupAdded) Scheduler.execute(command).data()).getAddedProductStoreGroupRollup();
		
		if (productStoreGroupRollup != null) 
			return successful(productStoreGroupRollup);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ProductStoreGroupRollup with the specific Id
	 * 
	 * @param productStoreGroupRollupToBeUpdated
	 *            the ProductStoreGroupRollup thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateProductStoreGroupRollup(@RequestBody ProductStoreGroupRollup productStoreGroupRollupToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		productStoreGroupRollupToBeUpdated.setnull(null);

		UpdateProductStoreGroupRollup command = new UpdateProductStoreGroupRollup(productStoreGroupRollupToBeUpdated);

		try {
			if(((ProductStoreGroupRollupUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{productStoreGroupRollupId}")
	public ResponseEntity<ProductStoreGroupRollup> findById(@PathVariable String productStoreGroupRollupId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productStoreGroupRollupId", productStoreGroupRollupId);
		try {

			List<ProductStoreGroupRollup> foundProductStoreGroupRollup = findProductStoreGroupRollupsBy(requestParams).getBody();
			if(foundProductStoreGroupRollup.size()==1){				return successful(foundProductStoreGroupRollup.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{productStoreGroupRollupId}")
	public ResponseEntity<String> deleteProductStoreGroupRollupByIdUpdated(@PathVariable String productStoreGroupRollupId) throws Exception {
		DeleteProductStoreGroupRollup command = new DeleteProductStoreGroupRollup(productStoreGroupRollupId);

		try {
			if (((ProductStoreGroupRollupDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
