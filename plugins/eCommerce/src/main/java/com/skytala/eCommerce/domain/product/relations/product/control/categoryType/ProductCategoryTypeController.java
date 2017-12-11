package com.skytala.eCommerce.domain.product.relations.product.control.categoryType;

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
import com.skytala.eCommerce.domain.product.relations.product.command.categoryType.AddProductCategoryType;
import com.skytala.eCommerce.domain.product.relations.product.command.categoryType.DeleteProductCategoryType;
import com.skytala.eCommerce.domain.product.relations.product.command.categoryType.UpdateProductCategoryType;
import com.skytala.eCommerce.domain.product.relations.product.event.categoryType.ProductCategoryTypeAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.categoryType.ProductCategoryTypeDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.categoryType.ProductCategoryTypeFound;
import com.skytala.eCommerce.domain.product.relations.product.event.categoryType.ProductCategoryTypeUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.categoryType.ProductCategoryTypeMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.categoryType.ProductCategoryType;
import com.skytala.eCommerce.domain.product.relations.product.query.categoryType.FindProductCategoryTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/product/product/productCategoryTypes")
public class ProductCategoryTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductCategoryTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductCategoryType
	 * @return a List with the ProductCategoryTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findProductCategoryTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductCategoryTypesBy query = new FindProductCategoryTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductCategoryType> productCategoryTypes =((ProductCategoryTypeFound) Scheduler.execute(query).data()).getProductCategoryTypes();

		if (productCategoryTypes.size() == 1) {
			return ResponseEntity.ok().body(productCategoryTypes.get(0));
		}

		return ResponseEntity.ok().body(productCategoryTypes);

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
	public ResponseEntity<Object> createProductCategoryType(HttpServletRequest request) throws Exception {

		ProductCategoryType productCategoryTypeToBeAdded = new ProductCategoryType();
		try {
			productCategoryTypeToBeAdded = ProductCategoryTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createProductCategoryType(productCategoryTypeToBeAdded);

	}

	/**
	 * creates a new ProductCategoryType entry in the ofbiz database
	 * 
	 * @param productCategoryTypeToBeAdded
	 *            the ProductCategoryType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createProductCategoryType(@RequestBody ProductCategoryType productCategoryTypeToBeAdded) throws Exception {

		AddProductCategoryType command = new AddProductCategoryType(productCategoryTypeToBeAdded);
		ProductCategoryType productCategoryType = ((ProductCategoryTypeAdded) Scheduler.execute(command).data()).getAddedProductCategoryType();
		
		if (productCategoryType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(productCategoryType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProductCategoryType could not be created.");
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
	public boolean updateProductCategoryType(HttpServletRequest request) throws Exception {

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

		ProductCategoryType productCategoryTypeToBeUpdated = new ProductCategoryType();

		try {
			productCategoryTypeToBeUpdated = ProductCategoryTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProductCategoryType(productCategoryTypeToBeUpdated, productCategoryTypeToBeUpdated.getProductCategoryTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ProductCategoryType with the specific Id
	 * 
	 * @param productCategoryTypeToBeUpdated
	 *            the ProductCategoryType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{productCategoryTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateProductCategoryType(@RequestBody ProductCategoryType productCategoryTypeToBeUpdated,
			@PathVariable String productCategoryTypeId) throws Exception {

		productCategoryTypeToBeUpdated.setProductCategoryTypeId(productCategoryTypeId);

		UpdateProductCategoryType command = new UpdateProductCategoryType(productCategoryTypeToBeUpdated);

		try {
			if(((ProductCategoryTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{productCategoryTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String productCategoryTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productCategoryTypeId", productCategoryTypeId);
		try {

			Object foundProductCategoryType = findProductCategoryTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProductCategoryType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{productCategoryTypeId}")
	public ResponseEntity<Object> deleteProductCategoryTypeByIdUpdated(@PathVariable String productCategoryTypeId) throws Exception {
		DeleteProductCategoryType command = new DeleteProductCategoryType(productCategoryTypeId);

		try {
			if (((ProductCategoryTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductCategoryType could not be deleted");

	}

}
