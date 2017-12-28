package com.skytala.eCommerce.domain.product.relations.product.control.contentType;

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
import com.skytala.eCommerce.domain.product.relations.product.command.contentType.AddProductContentType;
import com.skytala.eCommerce.domain.product.relations.product.command.contentType.DeleteProductContentType;
import com.skytala.eCommerce.domain.product.relations.product.command.contentType.UpdateProductContentType;
import com.skytala.eCommerce.domain.product.relations.product.event.contentType.ProductContentTypeAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.contentType.ProductContentTypeDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.contentType.ProductContentTypeFound;
import com.skytala.eCommerce.domain.product.relations.product.event.contentType.ProductContentTypeUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.contentType.ProductContentTypeMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.contentType.ProductContentType;
import com.skytala.eCommerce.domain.product.relations.product.query.contentType.FindProductContentTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/product/productContentTypes")
public class ProductContentTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductContentTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductContentType
	 * @return a List with the ProductContentTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ProductContentType>> findProductContentTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductContentTypesBy query = new FindProductContentTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductContentType> productContentTypes =((ProductContentTypeFound) Scheduler.execute(query).data()).getProductContentTypes();

		return ResponseEntity.ok().body(productContentTypes);

	}

	/**
	 * creates a new ProductContentType entry in the ofbiz database
	 * 
	 * @param productContentTypeToBeAdded
	 *            the ProductContentType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductContentType> createProductContentType(@RequestBody ProductContentType productContentTypeToBeAdded) throws Exception {

		AddProductContentType command = new AddProductContentType(productContentTypeToBeAdded);
		ProductContentType productContentType = ((ProductContentTypeAdded) Scheduler.execute(command).data()).getAddedProductContentType();
		
		if (productContentType != null) 
			return successful(productContentType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ProductContentType with the specific Id
	 * 
	 * @param productContentTypeToBeUpdated
	 *            the ProductContentType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{productContentTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateProductContentType(@RequestBody ProductContentType productContentTypeToBeUpdated,
			@PathVariable String productContentTypeId) throws Exception {

		productContentTypeToBeUpdated.setProductContentTypeId(productContentTypeId);

		UpdateProductContentType command = new UpdateProductContentType(productContentTypeToBeUpdated);

		try {
			if(((ProductContentTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{productContentTypeId}")
	public ResponseEntity<ProductContentType> findById(@PathVariable String productContentTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productContentTypeId", productContentTypeId);
		try {

			List<ProductContentType> foundProductContentType = findProductContentTypesBy(requestParams).getBody();
			if(foundProductContentType.size()==1){				return successful(foundProductContentType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{productContentTypeId}")
	public ResponseEntity<String> deleteProductContentTypeByIdUpdated(@PathVariable String productContentTypeId) throws Exception {
		DeleteProductContentType command = new DeleteProductContentType(productContentTypeId);

		try {
			if (((ProductContentTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
