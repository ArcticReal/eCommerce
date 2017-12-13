package com.skytala.eCommerce.domain.product.relations.product.control.featureIactnType;

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
import com.skytala.eCommerce.domain.product.relations.product.command.featureIactnType.AddProductFeatureIactnType;
import com.skytala.eCommerce.domain.product.relations.product.command.featureIactnType.DeleteProductFeatureIactnType;
import com.skytala.eCommerce.domain.product.relations.product.command.featureIactnType.UpdateProductFeatureIactnType;
import com.skytala.eCommerce.domain.product.relations.product.event.featureIactnType.ProductFeatureIactnTypeAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.featureIactnType.ProductFeatureIactnTypeDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.featureIactnType.ProductFeatureIactnTypeFound;
import com.skytala.eCommerce.domain.product.relations.product.event.featureIactnType.ProductFeatureIactnTypeUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.featureIactnType.ProductFeatureIactnTypeMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.featureIactnType.ProductFeatureIactnType;
import com.skytala.eCommerce.domain.product.relations.product.query.featureIactnType.FindProductFeatureIactnTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/product/product/productFeatureIactnTypes")
public class ProductFeatureIactnTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductFeatureIactnTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductFeatureIactnType
	 * @return a List with the ProductFeatureIactnTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findProductFeatureIactnTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductFeatureIactnTypesBy query = new FindProductFeatureIactnTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductFeatureIactnType> productFeatureIactnTypes =((ProductFeatureIactnTypeFound) Scheduler.execute(query).data()).getProductFeatureIactnTypes();

		if (productFeatureIactnTypes.size() == 1) {
			return ResponseEntity.ok().body(productFeatureIactnTypes.get(0));
		}

		return ResponseEntity.ok().body(productFeatureIactnTypes);

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
	public ResponseEntity<Object> createProductFeatureIactnType(HttpServletRequest request) throws Exception {

		ProductFeatureIactnType productFeatureIactnTypeToBeAdded = new ProductFeatureIactnType();
		try {
			productFeatureIactnTypeToBeAdded = ProductFeatureIactnTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createProductFeatureIactnType(productFeatureIactnTypeToBeAdded);

	}

	/**
	 * creates a new ProductFeatureIactnType entry in the ofbiz database
	 * 
	 * @param productFeatureIactnTypeToBeAdded
	 *            the ProductFeatureIactnType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createProductFeatureIactnType(@RequestBody ProductFeatureIactnType productFeatureIactnTypeToBeAdded) throws Exception {

		AddProductFeatureIactnType command = new AddProductFeatureIactnType(productFeatureIactnTypeToBeAdded);
		ProductFeatureIactnType productFeatureIactnType = ((ProductFeatureIactnTypeAdded) Scheduler.execute(command).data()).getAddedProductFeatureIactnType();
		
		if (productFeatureIactnType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(productFeatureIactnType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProductFeatureIactnType could not be created.");
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
	public boolean updateProductFeatureIactnType(HttpServletRequest request) throws Exception {

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

		ProductFeatureIactnType productFeatureIactnTypeToBeUpdated = new ProductFeatureIactnType();

		try {
			productFeatureIactnTypeToBeUpdated = ProductFeatureIactnTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProductFeatureIactnType(productFeatureIactnTypeToBeUpdated, productFeatureIactnTypeToBeUpdated.getProductFeatureIactnTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ProductFeatureIactnType with the specific Id
	 * 
	 * @param productFeatureIactnTypeToBeUpdated
	 *            the ProductFeatureIactnType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{productFeatureIactnTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateProductFeatureIactnType(@RequestBody ProductFeatureIactnType productFeatureIactnTypeToBeUpdated,
			@PathVariable String productFeatureIactnTypeId) throws Exception {

		productFeatureIactnTypeToBeUpdated.setProductFeatureIactnTypeId(productFeatureIactnTypeId);

		UpdateProductFeatureIactnType command = new UpdateProductFeatureIactnType(productFeatureIactnTypeToBeUpdated);

		try {
			if(((ProductFeatureIactnTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{productFeatureIactnTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String productFeatureIactnTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productFeatureIactnTypeId", productFeatureIactnTypeId);
		try {

			Object foundProductFeatureIactnType = findProductFeatureIactnTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProductFeatureIactnType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{productFeatureIactnTypeId}")
	public ResponseEntity<Object> deleteProductFeatureIactnTypeByIdUpdated(@PathVariable String productFeatureIactnTypeId) throws Exception {
		DeleteProductFeatureIactnType command = new DeleteProductFeatureIactnType(productFeatureIactnTypeId);

		try {
			if (((ProductFeatureIactnTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductFeatureIactnType could not be deleted");

	}

}
