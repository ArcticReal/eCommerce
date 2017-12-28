package com.skytala.eCommerce.domain.product.relations.product.control.configOptionIactn;

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
import com.skytala.eCommerce.domain.product.relations.product.command.configOptionIactn.AddProductConfigOptionIactn;
import com.skytala.eCommerce.domain.product.relations.product.command.configOptionIactn.DeleteProductConfigOptionIactn;
import com.skytala.eCommerce.domain.product.relations.product.command.configOptionIactn.UpdateProductConfigOptionIactn;
import com.skytala.eCommerce.domain.product.relations.product.event.configOptionIactn.ProductConfigOptionIactnAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.configOptionIactn.ProductConfigOptionIactnDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.configOptionIactn.ProductConfigOptionIactnFound;
import com.skytala.eCommerce.domain.product.relations.product.event.configOptionIactn.ProductConfigOptionIactnUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.configOptionIactn.ProductConfigOptionIactnMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.configOptionIactn.ProductConfigOptionIactn;
import com.skytala.eCommerce.domain.product.relations.product.query.configOptionIactn.FindProductConfigOptionIactnsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/product/productConfigOptionIactns")
public class ProductConfigOptionIactnController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductConfigOptionIactnController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductConfigOptionIactn
	 * @return a List with the ProductConfigOptionIactns
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ProductConfigOptionIactn>> findProductConfigOptionIactnsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductConfigOptionIactnsBy query = new FindProductConfigOptionIactnsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductConfigOptionIactn> productConfigOptionIactns =((ProductConfigOptionIactnFound) Scheduler.execute(query).data()).getProductConfigOptionIactns();

		return ResponseEntity.ok().body(productConfigOptionIactns);

	}

	/**
	 * creates a new ProductConfigOptionIactn entry in the ofbiz database
	 * 
	 * @param productConfigOptionIactnToBeAdded
	 *            the ProductConfigOptionIactn thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductConfigOptionIactn> createProductConfigOptionIactn(@RequestBody ProductConfigOptionIactn productConfigOptionIactnToBeAdded) throws Exception {

		AddProductConfigOptionIactn command = new AddProductConfigOptionIactn(productConfigOptionIactnToBeAdded);
		ProductConfigOptionIactn productConfigOptionIactn = ((ProductConfigOptionIactnAdded) Scheduler.execute(command).data()).getAddedProductConfigOptionIactn();
		
		if (productConfigOptionIactn != null) 
			return successful(productConfigOptionIactn);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ProductConfigOptionIactn with the specific Id
	 * 
	 * @param productConfigOptionIactnToBeUpdated
	 *            the ProductConfigOptionIactn thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateProductConfigOptionIactn(@RequestBody ProductConfigOptionIactn productConfigOptionIactnToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		productConfigOptionIactnToBeUpdated.setnull(null);

		UpdateProductConfigOptionIactn command = new UpdateProductConfigOptionIactn(productConfigOptionIactnToBeUpdated);

		try {
			if(((ProductConfigOptionIactnUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{productConfigOptionIactnId}")
	public ResponseEntity<ProductConfigOptionIactn> findById(@PathVariable String productConfigOptionIactnId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productConfigOptionIactnId", productConfigOptionIactnId);
		try {

			List<ProductConfigOptionIactn> foundProductConfigOptionIactn = findProductConfigOptionIactnsBy(requestParams).getBody();
			if(foundProductConfigOptionIactn.size()==1){				return successful(foundProductConfigOptionIactn.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{productConfigOptionIactnId}")
	public ResponseEntity<String> deleteProductConfigOptionIactnByIdUpdated(@PathVariable String productConfigOptionIactnId) throws Exception {
		DeleteProductConfigOptionIactn command = new DeleteProductConfigOptionIactn(productConfigOptionIactnId);

		try {
			if (((ProductConfigOptionIactnDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
