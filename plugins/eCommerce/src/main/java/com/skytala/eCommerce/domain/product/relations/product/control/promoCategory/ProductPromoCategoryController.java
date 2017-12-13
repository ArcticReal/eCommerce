package com.skytala.eCommerce.domain.product.relations.product.control.promoCategory;

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
import com.skytala.eCommerce.domain.product.relations.product.command.promoCategory.AddProductPromoCategory;
import com.skytala.eCommerce.domain.product.relations.product.command.promoCategory.DeleteProductPromoCategory;
import com.skytala.eCommerce.domain.product.relations.product.command.promoCategory.UpdateProductPromoCategory;
import com.skytala.eCommerce.domain.product.relations.product.event.promoCategory.ProductPromoCategoryAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.promoCategory.ProductPromoCategoryDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.promoCategory.ProductPromoCategoryFound;
import com.skytala.eCommerce.domain.product.relations.product.event.promoCategory.ProductPromoCategoryUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.promoCategory.ProductPromoCategoryMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.promoCategory.ProductPromoCategory;
import com.skytala.eCommerce.domain.product.relations.product.query.promoCategory.FindProductPromoCategorysBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/product/product/productPromoCategorys")
public class ProductPromoCategoryController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductPromoCategoryController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductPromoCategory
	 * @return a List with the ProductPromoCategorys
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findProductPromoCategorysBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductPromoCategorysBy query = new FindProductPromoCategorysBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductPromoCategory> productPromoCategorys =((ProductPromoCategoryFound) Scheduler.execute(query).data()).getProductPromoCategorys();

		if (productPromoCategorys.size() == 1) {
			return ResponseEntity.ok().body(productPromoCategorys.get(0));
		}

		return ResponseEntity.ok().body(productPromoCategorys);

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
	public ResponseEntity<Object> createProductPromoCategory(HttpServletRequest request) throws Exception {

		ProductPromoCategory productPromoCategoryToBeAdded = new ProductPromoCategory();
		try {
			productPromoCategoryToBeAdded = ProductPromoCategoryMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createProductPromoCategory(productPromoCategoryToBeAdded);

	}

	/**
	 * creates a new ProductPromoCategory entry in the ofbiz database
	 * 
	 * @param productPromoCategoryToBeAdded
	 *            the ProductPromoCategory thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createProductPromoCategory(@RequestBody ProductPromoCategory productPromoCategoryToBeAdded) throws Exception {

		AddProductPromoCategory command = new AddProductPromoCategory(productPromoCategoryToBeAdded);
		ProductPromoCategory productPromoCategory = ((ProductPromoCategoryAdded) Scheduler.execute(command).data()).getAddedProductPromoCategory();
		
		if (productPromoCategory != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(productPromoCategory);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProductPromoCategory could not be created.");
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
	public boolean updateProductPromoCategory(HttpServletRequest request) throws Exception {

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

		ProductPromoCategory productPromoCategoryToBeUpdated = new ProductPromoCategory();

		try {
			productPromoCategoryToBeUpdated = ProductPromoCategoryMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProductPromoCategory(productPromoCategoryToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ProductPromoCategory with the specific Id
	 * 
	 * @param productPromoCategoryToBeUpdated
	 *            the ProductPromoCategory thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateProductPromoCategory(@RequestBody ProductPromoCategory productPromoCategoryToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		productPromoCategoryToBeUpdated.setnull(null);

		UpdateProductPromoCategory command = new UpdateProductPromoCategory(productPromoCategoryToBeUpdated);

		try {
			if(((ProductPromoCategoryUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{productPromoCategoryId}")
	public ResponseEntity<Object> findById(@PathVariable String productPromoCategoryId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productPromoCategoryId", productPromoCategoryId);
		try {

			Object foundProductPromoCategory = findProductPromoCategorysBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProductPromoCategory);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{productPromoCategoryId}")
	public ResponseEntity<Object> deleteProductPromoCategoryByIdUpdated(@PathVariable String productPromoCategoryId) throws Exception {
		DeleteProductPromoCategory command = new DeleteProductPromoCategory(productPromoCategoryId);

		try {
			if (((ProductPromoCategoryDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductPromoCategory could not be deleted");

	}

}
