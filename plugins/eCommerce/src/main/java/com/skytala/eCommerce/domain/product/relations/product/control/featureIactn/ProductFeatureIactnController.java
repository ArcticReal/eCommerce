package com.skytala.eCommerce.domain.product.relations.product.control.featureIactn;

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
import com.skytala.eCommerce.domain.product.relations.product.command.featureIactn.AddProductFeatureIactn;
import com.skytala.eCommerce.domain.product.relations.product.command.featureIactn.DeleteProductFeatureIactn;
import com.skytala.eCommerce.domain.product.relations.product.command.featureIactn.UpdateProductFeatureIactn;
import com.skytala.eCommerce.domain.product.relations.product.event.featureIactn.ProductFeatureIactnAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.featureIactn.ProductFeatureIactnDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.featureIactn.ProductFeatureIactnFound;
import com.skytala.eCommerce.domain.product.relations.product.event.featureIactn.ProductFeatureIactnUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.featureIactn.ProductFeatureIactnMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.featureIactn.ProductFeatureIactn;
import com.skytala.eCommerce.domain.product.relations.product.query.featureIactn.FindProductFeatureIactnsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/product/productFeatureIactns")
public class ProductFeatureIactnController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductFeatureIactnController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductFeatureIactn
	 * @return a List with the ProductFeatureIactns
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ProductFeatureIactn>> findProductFeatureIactnsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductFeatureIactnsBy query = new FindProductFeatureIactnsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductFeatureIactn> productFeatureIactns =((ProductFeatureIactnFound) Scheduler.execute(query).data()).getProductFeatureIactns();

		return ResponseEntity.ok().body(productFeatureIactns);

	}

	/**
	 * creates a new ProductFeatureIactn entry in the ofbiz database
	 * 
	 * @param productFeatureIactnToBeAdded
	 *            the ProductFeatureIactn thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductFeatureIactn> createProductFeatureIactn(@RequestBody ProductFeatureIactn productFeatureIactnToBeAdded) throws Exception {

		AddProductFeatureIactn command = new AddProductFeatureIactn(productFeatureIactnToBeAdded);
		ProductFeatureIactn productFeatureIactn = ((ProductFeatureIactnAdded) Scheduler.execute(command).data()).getAddedProductFeatureIactn();
		
		if (productFeatureIactn != null) 
			return successful(productFeatureIactn);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ProductFeatureIactn with the specific Id
	 * 
	 * @param productFeatureIactnToBeUpdated
	 *            the ProductFeatureIactn thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateProductFeatureIactn(@RequestBody ProductFeatureIactn productFeatureIactnToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		productFeatureIactnToBeUpdated.setnull(null);

		UpdateProductFeatureIactn command = new UpdateProductFeatureIactn(productFeatureIactnToBeUpdated);

		try {
			if(((ProductFeatureIactnUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{productFeatureIactnId}")
	public ResponseEntity<ProductFeatureIactn> findById(@PathVariable String productFeatureIactnId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productFeatureIactnId", productFeatureIactnId);
		try {

			List<ProductFeatureIactn> foundProductFeatureIactn = findProductFeatureIactnsBy(requestParams).getBody();
			if(foundProductFeatureIactn.size()==1){				return successful(foundProductFeatureIactn.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{productFeatureIactnId}")
	public ResponseEntity<String> deleteProductFeatureIactnByIdUpdated(@PathVariable String productFeatureIactnId) throws Exception {
		DeleteProductFeatureIactn command = new DeleteProductFeatureIactn(productFeatureIactnId);

		try {
			if (((ProductFeatureIactnDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
