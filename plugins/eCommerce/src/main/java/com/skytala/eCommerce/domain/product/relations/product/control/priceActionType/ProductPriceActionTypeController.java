package com.skytala.eCommerce.domain.product.relations.product.control.priceActionType;

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
import com.skytala.eCommerce.domain.product.relations.product.command.priceActionType.AddProductPriceActionType;
import com.skytala.eCommerce.domain.product.relations.product.command.priceActionType.DeleteProductPriceActionType;
import com.skytala.eCommerce.domain.product.relations.product.command.priceActionType.UpdateProductPriceActionType;
import com.skytala.eCommerce.domain.product.relations.product.event.priceActionType.ProductPriceActionTypeAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.priceActionType.ProductPriceActionTypeDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.priceActionType.ProductPriceActionTypeFound;
import com.skytala.eCommerce.domain.product.relations.product.event.priceActionType.ProductPriceActionTypeUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.priceActionType.ProductPriceActionTypeMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.priceActionType.ProductPriceActionType;
import com.skytala.eCommerce.domain.product.relations.product.query.priceActionType.FindProductPriceActionTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/product/productPriceActionTypes")
public class ProductPriceActionTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductPriceActionTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductPriceActionType
	 * @return a List with the ProductPriceActionTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ProductPriceActionType>> findProductPriceActionTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductPriceActionTypesBy query = new FindProductPriceActionTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductPriceActionType> productPriceActionTypes =((ProductPriceActionTypeFound) Scheduler.execute(query).data()).getProductPriceActionTypes();

		return ResponseEntity.ok().body(productPriceActionTypes);

	}

	/**
	 * creates a new ProductPriceActionType entry in the ofbiz database
	 * 
	 * @param productPriceActionTypeToBeAdded
	 *            the ProductPriceActionType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductPriceActionType> createProductPriceActionType(@RequestBody ProductPriceActionType productPriceActionTypeToBeAdded) throws Exception {

		AddProductPriceActionType command = new AddProductPriceActionType(productPriceActionTypeToBeAdded);
		ProductPriceActionType productPriceActionType = ((ProductPriceActionTypeAdded) Scheduler.execute(command).data()).getAddedProductPriceActionType();
		
		if (productPriceActionType != null) 
			return successful(productPriceActionType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ProductPriceActionType with the specific Id
	 * 
	 * @param productPriceActionTypeToBeUpdated
	 *            the ProductPriceActionType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{productPriceActionTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateProductPriceActionType(@RequestBody ProductPriceActionType productPriceActionTypeToBeUpdated,
			@PathVariable String productPriceActionTypeId) throws Exception {

		productPriceActionTypeToBeUpdated.setProductPriceActionTypeId(productPriceActionTypeId);

		UpdateProductPriceActionType command = new UpdateProductPriceActionType(productPriceActionTypeToBeUpdated);

		try {
			if(((ProductPriceActionTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{productPriceActionTypeId}")
	public ResponseEntity<ProductPriceActionType> findById(@PathVariable String productPriceActionTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productPriceActionTypeId", productPriceActionTypeId);
		try {

			List<ProductPriceActionType> foundProductPriceActionType = findProductPriceActionTypesBy(requestParams).getBody();
			if(foundProductPriceActionType.size()==1){				return successful(foundProductPriceActionType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{productPriceActionTypeId}")
	public ResponseEntity<String> deleteProductPriceActionTypeByIdUpdated(@PathVariable String productPriceActionTypeId) throws Exception {
		DeleteProductPriceActionType command = new DeleteProductPriceActionType(productPriceActionTypeId);

		try {
			if (((ProductPriceActionTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
