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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	public ResponseEntity<List<ProductCategoryContentType>> findProductCategoryContentTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductCategoryContentTypesBy query = new FindProductCategoryContentTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductCategoryContentType> productCategoryContentTypes =((ProductCategoryContentTypeFound) Scheduler.execute(query).data()).getProductCategoryContentTypes();

		return ResponseEntity.ok().body(productCategoryContentTypes);

	}

	/**
	 * creates a new ProductCategoryContentType entry in the ofbiz database
	 * 
	 * @param productCategoryContentTypeToBeAdded
	 *            the ProductCategoryContentType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductCategoryContentType> createProductCategoryContentType(@RequestBody ProductCategoryContentType productCategoryContentTypeToBeAdded) throws Exception {

		AddProductCategoryContentType command = new AddProductCategoryContentType(productCategoryContentTypeToBeAdded);
		ProductCategoryContentType productCategoryContentType = ((ProductCategoryContentTypeAdded) Scheduler.execute(command).data()).getAddedProductCategoryContentType();
		
		if (productCategoryContentType != null) 
			return successful(productCategoryContentType);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateProductCategoryContentType(@RequestBody ProductCategoryContentType productCategoryContentTypeToBeUpdated,
			@PathVariable String prodCatContentTypeId) throws Exception {

		productCategoryContentTypeToBeUpdated.setProdCatContentTypeId(prodCatContentTypeId);

		UpdateProductCategoryContentType command = new UpdateProductCategoryContentType(productCategoryContentTypeToBeUpdated);

		try {
			if(((ProductCategoryContentTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{productCategoryContentTypeId}")
	public ResponseEntity<ProductCategoryContentType> findById(@PathVariable String productCategoryContentTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productCategoryContentTypeId", productCategoryContentTypeId);
		try {

			List<ProductCategoryContentType> foundProductCategoryContentType = findProductCategoryContentTypesBy(requestParams).getBody();
			if(foundProductCategoryContentType.size()==1){				return successful(foundProductCategoryContentType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{productCategoryContentTypeId}")
	public ResponseEntity<String> deleteProductCategoryContentTypeByIdUpdated(@PathVariable String productCategoryContentTypeId) throws Exception {
		DeleteProductCategoryContentType command = new DeleteProductCategoryContentType(productCategoryContentTypeId);

		try {
			if (((ProductCategoryContentTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
