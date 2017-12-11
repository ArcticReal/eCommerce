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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<Object> findProductPricePurposesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductPricePurposesBy query = new FindProductPricePurposesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductPricePurpose> productPricePurposes =((ProductPricePurposeFound) Scheduler.execute(query).data()).getProductPricePurposes();

		if (productPricePurposes.size() == 1) {
			return ResponseEntity.ok().body(productPricePurposes.get(0));
		}

		return ResponseEntity.ok().body(productPricePurposes);

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
	public ResponseEntity<Object> createProductPricePurpose(HttpServletRequest request) throws Exception {

		ProductPricePurpose productPricePurposeToBeAdded = new ProductPricePurpose();
		try {
			productPricePurposeToBeAdded = ProductPricePurposeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createProductPricePurpose(productPricePurposeToBeAdded);

	}

	/**
	 * creates a new ProductPricePurpose entry in the ofbiz database
	 * 
	 * @param productPricePurposeToBeAdded
	 *            the ProductPricePurpose thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createProductPricePurpose(@RequestBody ProductPricePurpose productPricePurposeToBeAdded) throws Exception {

		AddProductPricePurpose command = new AddProductPricePurpose(productPricePurposeToBeAdded);
		ProductPricePurpose productPricePurpose = ((ProductPricePurposeAdded) Scheduler.execute(command).data()).getAddedProductPricePurpose();
		
		if (productPricePurpose != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(productPricePurpose);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProductPricePurpose could not be created.");
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
	public boolean updateProductPricePurpose(HttpServletRequest request) throws Exception {

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

		ProductPricePurpose productPricePurposeToBeUpdated = new ProductPricePurpose();

		try {
			productPricePurposeToBeUpdated = ProductPricePurposeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProductPricePurpose(productPricePurposeToBeUpdated, productPricePurposeToBeUpdated.getProductPricePurposeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateProductPricePurpose(@RequestBody ProductPricePurpose productPricePurposeToBeUpdated,
			@PathVariable String productPricePurposeId) throws Exception {

		productPricePurposeToBeUpdated.setProductPricePurposeId(productPricePurposeId);

		UpdateProductPricePurpose command = new UpdateProductPricePurpose(productPricePurposeToBeUpdated);

		try {
			if(((ProductPricePurposeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{productPricePurposeId}")
	public ResponseEntity<Object> findById(@PathVariable String productPricePurposeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productPricePurposeId", productPricePurposeId);
		try {

			Object foundProductPricePurpose = findProductPricePurposesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProductPricePurpose);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{productPricePurposeId}")
	public ResponseEntity<Object> deleteProductPricePurposeByIdUpdated(@PathVariable String productPricePurposeId) throws Exception {
		DeleteProductPricePurpose command = new DeleteProductPricePurpose(productPricePurposeId);

		try {
			if (((ProductPricePurposeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductPricePurpose could not be deleted");

	}

}
