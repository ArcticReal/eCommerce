package com.skytala.eCommerce.domain.product.relations.product.control.categoryContentType;

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
import com.skytala.eCommerce.domain.product.relations.product.command.categoryContentType.AddProductCategoryContentType;
import com.skytala.eCommerce.domain.product.relations.product.command.categoryContentType.DeleteProductCategoryContentType;
import com.skytala.eCommerce.domain.product.relations.product.command.categoryContentType.UpdateProductCategoryContentType;
import com.skytala.eCommerce.domain.product.relations.product.event.categoryContentType.ProductCategoryContentTypeAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.categoryContentType.ProductCategoryContentTypeDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.categoryContentType.ProductCategoryContentTypeFound;
import com.skytala.eCommerce.domain.product.relations.product.event.categoryContentType.ProductCategoryContentTypeUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.categoryContentType.ProductCategoryContentTypeMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.categoryContentType.ProductCategoryContentType;
import com.skytala.eCommerce.domain.product.relations.product.query.categoryContentType.FindProductCategoryContentTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/product/product/productCategoryContentTypes")
public class ProductCategoryContentTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductCategoryContentTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductCategoryContentType
	 * @return a List with the ProductCategoryContentTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findProductCategoryContentTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductCategoryContentTypesBy query = new FindProductCategoryContentTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductCategoryContentType> productCategoryContentTypes =((ProductCategoryContentTypeFound) Scheduler.execute(query).data()).getProductCategoryContentTypes();

		if (productCategoryContentTypes.size() == 1) {
			return ResponseEntity.ok().body(productCategoryContentTypes.get(0));
		}

		return ResponseEntity.ok().body(productCategoryContentTypes);

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
	public ResponseEntity<Object> createProductCategoryContentType(HttpServletRequest request) throws Exception {

		ProductCategoryContentType productCategoryContentTypeToBeAdded = new ProductCategoryContentType();
		try {
			productCategoryContentTypeToBeAdded = ProductCategoryContentTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createProductCategoryContentType(productCategoryContentTypeToBeAdded);

	}

	/**
	 * creates a new ProductCategoryContentType entry in the ofbiz database
	 * 
	 * @param productCategoryContentTypeToBeAdded
	 *            the ProductCategoryContentType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createProductCategoryContentType(@RequestBody ProductCategoryContentType productCategoryContentTypeToBeAdded) throws Exception {

		AddProductCategoryContentType command = new AddProductCategoryContentType(productCategoryContentTypeToBeAdded);
		ProductCategoryContentType productCategoryContentType = ((ProductCategoryContentTypeAdded) Scheduler.execute(command).data()).getAddedProductCategoryContentType();
		
		if (productCategoryContentType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(productCategoryContentType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProductCategoryContentType could not be created.");
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
	public boolean updateProductCategoryContentType(HttpServletRequest request) throws Exception {

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

		ProductCategoryContentType productCategoryContentTypeToBeUpdated = new ProductCategoryContentType();

		try {
			productCategoryContentTypeToBeUpdated = ProductCategoryContentTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProductCategoryContentType(productCategoryContentTypeToBeUpdated, productCategoryContentTypeToBeUpdated.getProdCatContentTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ProductCategoryContentType with the specific Id
	 * 
	 * @param productCategoryContentTypeToBeUpdated
	 *            the ProductCategoryContentType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{prodCatContentTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateProductCategoryContentType(@RequestBody ProductCategoryContentType productCategoryContentTypeToBeUpdated,
			@PathVariable String prodCatContentTypeId) throws Exception {

		productCategoryContentTypeToBeUpdated.setProdCatContentTypeId(prodCatContentTypeId);

		UpdateProductCategoryContentType command = new UpdateProductCategoryContentType(productCategoryContentTypeToBeUpdated);

		try {
			if(((ProductCategoryContentTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{productCategoryContentTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String productCategoryContentTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productCategoryContentTypeId", productCategoryContentTypeId);
		try {

			Object foundProductCategoryContentType = findProductCategoryContentTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProductCategoryContentType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{productCategoryContentTypeId}")
	public ResponseEntity<Object> deleteProductCategoryContentTypeByIdUpdated(@PathVariable String productCategoryContentTypeId) throws Exception {
		DeleteProductCategoryContentType command = new DeleteProductCategoryContentType(productCategoryContentTypeId);

		try {
			if (((ProductCategoryContentTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductCategoryContentType could not be deleted");

	}

}
