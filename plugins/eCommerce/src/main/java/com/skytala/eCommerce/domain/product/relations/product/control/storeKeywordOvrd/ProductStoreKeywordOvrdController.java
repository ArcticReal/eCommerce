package com.skytala.eCommerce.domain.product.relations.product.control.storeKeywordOvrd;

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
import com.skytala.eCommerce.domain.product.relations.product.command.storeKeywordOvrd.AddProductStoreKeywordOvrd;
import com.skytala.eCommerce.domain.product.relations.product.command.storeKeywordOvrd.DeleteProductStoreKeywordOvrd;
import com.skytala.eCommerce.domain.product.relations.product.command.storeKeywordOvrd.UpdateProductStoreKeywordOvrd;
import com.skytala.eCommerce.domain.product.relations.product.event.storeKeywordOvrd.ProductStoreKeywordOvrdAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.storeKeywordOvrd.ProductStoreKeywordOvrdDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.storeKeywordOvrd.ProductStoreKeywordOvrdFound;
import com.skytala.eCommerce.domain.product.relations.product.event.storeKeywordOvrd.ProductStoreKeywordOvrdUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.storeKeywordOvrd.ProductStoreKeywordOvrdMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.storeKeywordOvrd.ProductStoreKeywordOvrd;
import com.skytala.eCommerce.domain.product.relations.product.query.storeKeywordOvrd.FindProductStoreKeywordOvrdsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/product/productStoreKeywordOvrds")
public class ProductStoreKeywordOvrdController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductStoreKeywordOvrdController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductStoreKeywordOvrd
	 * @return a List with the ProductStoreKeywordOvrds
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ProductStoreKeywordOvrd>> findProductStoreKeywordOvrdsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductStoreKeywordOvrdsBy query = new FindProductStoreKeywordOvrdsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductStoreKeywordOvrd> productStoreKeywordOvrds =((ProductStoreKeywordOvrdFound) Scheduler.execute(query).data()).getProductStoreKeywordOvrds();

		return ResponseEntity.ok().body(productStoreKeywordOvrds);

	}

	/**
	 * creates a new ProductStoreKeywordOvrd entry in the ofbiz database
	 * 
	 * @param productStoreKeywordOvrdToBeAdded
	 *            the ProductStoreKeywordOvrd thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductStoreKeywordOvrd> createProductStoreKeywordOvrd(@RequestBody ProductStoreKeywordOvrd productStoreKeywordOvrdToBeAdded) throws Exception {

		AddProductStoreKeywordOvrd command = new AddProductStoreKeywordOvrd(productStoreKeywordOvrdToBeAdded);
		ProductStoreKeywordOvrd productStoreKeywordOvrd = ((ProductStoreKeywordOvrdAdded) Scheduler.execute(command).data()).getAddedProductStoreKeywordOvrd();
		
		if (productStoreKeywordOvrd != null) 
			return successful(productStoreKeywordOvrd);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ProductStoreKeywordOvrd with the specific Id
	 * 
	 * @param productStoreKeywordOvrdToBeUpdated
	 *            the ProductStoreKeywordOvrd thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{keyword}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateProductStoreKeywordOvrd(@RequestBody ProductStoreKeywordOvrd productStoreKeywordOvrdToBeUpdated,
			@PathVariable String keyword) throws Exception {

		productStoreKeywordOvrdToBeUpdated.setKeyword(keyword);

		UpdateProductStoreKeywordOvrd command = new UpdateProductStoreKeywordOvrd(productStoreKeywordOvrdToBeUpdated);

		try {
			if(((ProductStoreKeywordOvrdUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{productStoreKeywordOvrdId}")
	public ResponseEntity<ProductStoreKeywordOvrd> findById(@PathVariable String productStoreKeywordOvrdId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productStoreKeywordOvrdId", productStoreKeywordOvrdId);
		try {

			List<ProductStoreKeywordOvrd> foundProductStoreKeywordOvrd = findProductStoreKeywordOvrdsBy(requestParams).getBody();
			if(foundProductStoreKeywordOvrd.size()==1){				return successful(foundProductStoreKeywordOvrd.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{productStoreKeywordOvrdId}")
	public ResponseEntity<String> deleteProductStoreKeywordOvrdByIdUpdated(@PathVariable String productStoreKeywordOvrdId) throws Exception {
		DeleteProductStoreKeywordOvrd command = new DeleteProductStoreKeywordOvrd(productStoreKeywordOvrdId);

		try {
			if (((ProductStoreKeywordOvrdDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
