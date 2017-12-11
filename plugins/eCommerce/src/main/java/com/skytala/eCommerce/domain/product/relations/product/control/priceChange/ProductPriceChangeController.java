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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<Object> findProductPriceChangesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductPriceChangesBy query = new FindProductPriceChangesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductPriceChange> productPriceChanges =((ProductPriceChangeFound) Scheduler.execute(query).data()).getProductPriceChanges();

		if (productPriceChanges.size() == 1) {
			return ResponseEntity.ok().body(productPriceChanges.get(0));
		}

		return ResponseEntity.ok().body(productPriceChanges);

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
	public ResponseEntity<Object> createProductPriceChange(HttpServletRequest request) throws Exception {

		ProductPriceChange productPriceChangeToBeAdded = new ProductPriceChange();
		try {
			productPriceChangeToBeAdded = ProductPriceChangeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createProductPriceChange(productPriceChangeToBeAdded);

	}

	/**
	 * creates a new ProductPriceChange entry in the ofbiz database
	 * 
	 * @param productPriceChangeToBeAdded
	 *            the ProductPriceChange thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createProductPriceChange(@RequestBody ProductPriceChange productPriceChangeToBeAdded) throws Exception {

		AddProductPriceChange command = new AddProductPriceChange(productPriceChangeToBeAdded);
		ProductPriceChange productPriceChange = ((ProductPriceChangeAdded) Scheduler.execute(command).data()).getAddedProductPriceChange();
		
		if (productPriceChange != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(productPriceChange);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProductPriceChange could not be created.");
	}

	/**
	 * this method will only be called by Springs DispatcherServlet
	 * 
	 * @deprecated
	 * @param request
	 *            HttpServletRequest object
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@PutMapping(value = "/update", consumes = "application/x-www-form-urlencoded")
	public boolean updateProductPriceChange(HttpServletRequest request) throws Exception {

		BufferedReader br;
		String data = null;
		Map<String, String> dataMap = null;

		try {
			br = new BufferedReader(new InputStreamReader(request.getInputStream()));
			if (br != null) {
				data = java.net.URLDecoder.decode(br.readLine(), "UTF-8");
			}
		} catch (IOException e1) {
			e1.printStackTrace();
			return false;
		}

		dataMap = Splitter.on('&').trimResults().withKeyValueSeparator(Splitter.on('=').limit(2).trimResults())
				.split(data);

		ProductPriceChange productPriceChangeToBeUpdated = new ProductPriceChange();

		try {
			productPriceChangeToBeUpdated = ProductPriceChangeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProductPriceChange(productPriceChangeToBeUpdated, productPriceChangeToBeUpdated.getProductPriceChangeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateProductPriceChange(@RequestBody ProductPriceChange productPriceChangeToBeUpdated,
			@PathVariable String productPriceChangeId) throws Exception {

		productPriceChangeToBeUpdated.setProductPriceChangeId(productPriceChangeId);

		UpdateProductPriceChange command = new UpdateProductPriceChange(productPriceChangeToBeUpdated);

		try {
			if(((ProductPriceChangeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{productPriceChangeId}")
	public ResponseEntity<Object> findById(@PathVariable String productPriceChangeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productPriceChangeId", productPriceChangeId);
		try {

			Object foundProductPriceChange = findProductPriceChangesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProductPriceChange);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{productPriceChangeId}")
	public ResponseEntity<Object> deleteProductPriceChangeByIdUpdated(@PathVariable String productPriceChangeId) throws Exception {
		DeleteProductPriceChange command = new DeleteProductPriceChange(productPriceChangeId);

		try {
			if (((ProductPriceChangeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductPriceChange could not be deleted");

	}

}
