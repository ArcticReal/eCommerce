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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	public ResponseEntity<List<ProductPromoCategory>> findProductPromoCategorysBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductPromoCategorysBy query = new FindProductPromoCategorysBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductPromoCategory> productPromoCategorys =((ProductPromoCategoryFound) Scheduler.execute(query).data()).getProductPromoCategorys();

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
	public ResponseEntity<ProductPromoCategory> createProductPromoCategory(HttpServletRequest request) throws Exception {

		ProductPromoCategory productPromoCategoryToBeAdded = new ProductPromoCategory();
		try {
			productPromoCategoryToBeAdded = ProductPromoCategoryMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
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
	public ResponseEntity<ProductPromoCategory> createProductPromoCategory(@RequestBody ProductPromoCategory productPromoCategoryToBeAdded) throws Exception {

		AddProductPromoCategory command = new AddProductPromoCategory(productPromoCategoryToBeAdded);
		ProductPromoCategory productPromoCategory = ((ProductPromoCategoryAdded) Scheduler.execute(command).data()).getAddedProductPromoCategory();
		
		if (productPromoCategory != null) 
			return successful(productPromoCategory);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateProductPromoCategory(@RequestBody ProductPromoCategory productPromoCategoryToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		productPromoCategoryToBeUpdated.setnull(null);

		UpdateProductPromoCategory command = new UpdateProductPromoCategory(productPromoCategoryToBeUpdated);

		try {
			if(((ProductPromoCategoryUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{productPromoCategoryId}")
	public ResponseEntity<ProductPromoCategory> findById(@PathVariable String productPromoCategoryId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productPromoCategoryId", productPromoCategoryId);
		try {

			List<ProductPromoCategory> foundProductPromoCategory = findProductPromoCategorysBy(requestParams).getBody();
			if(foundProductPromoCategory.size()==1){				return successful(foundProductPromoCategory.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{productPromoCategoryId}")
	public ResponseEntity<String> deleteProductPromoCategoryByIdUpdated(@PathVariable String productPromoCategoryId) throws Exception {
		DeleteProductPromoCategory command = new DeleteProductPromoCategory(productPromoCategoryId);

		try {
			if (((ProductPromoCategoryDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
