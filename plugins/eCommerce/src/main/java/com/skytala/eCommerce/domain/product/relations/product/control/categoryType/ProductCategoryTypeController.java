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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	public ResponseEntity<List<ProductCategoryType>> findProductCategoryTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductCategoryTypesBy query = new FindProductCategoryTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductCategoryType> productCategoryTypes =((ProductCategoryTypeFound) Scheduler.execute(query).data()).getProductCategoryTypes();

		return ResponseEntity.ok().body(productCategoryTypes);

	}

	/**
	 * creates a new ProductCategoryType entry in the ofbiz database
	 * 
	 * @param productCategoryTypeToBeAdded
	 *            the ProductCategoryType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductCategoryType> createProductCategoryType(@RequestBody ProductCategoryType productCategoryTypeToBeAdded) throws Exception {

		AddProductCategoryType command = new AddProductCategoryType(productCategoryTypeToBeAdded);
		ProductCategoryType productCategoryType = ((ProductCategoryTypeAdded) Scheduler.execute(command).data()).getAddedProductCategoryType();
		
		if (productCategoryType != null) 
			return successful(productCategoryType);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateProductCategoryType(@RequestBody ProductCategoryType productCategoryTypeToBeUpdated,
			@PathVariable String productCategoryTypeId) throws Exception {

		productCategoryTypeToBeUpdated.setProductCategoryTypeId(productCategoryTypeId);

		UpdateProductCategoryType command = new UpdateProductCategoryType(productCategoryTypeToBeUpdated);

		try {
			if(((ProductCategoryTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{productCategoryTypeId}")
	public ResponseEntity<ProductCategoryType> findById(@PathVariable String productCategoryTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productCategoryTypeId", productCategoryTypeId);
		try {

			List<ProductCategoryType> foundProductCategoryType = findProductCategoryTypesBy(requestParams).getBody();
			if(foundProductCategoryType.size()==1){				return successful(foundProductCategoryType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{productCategoryTypeId}")
	public ResponseEntity<String> deleteProductCategoryTypeByIdUpdated(@PathVariable String productCategoryTypeId) throws Exception {
		DeleteProductCategoryType command = new DeleteProductCategoryType(productCategoryTypeId);

		try {
			if (((ProductCategoryTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
