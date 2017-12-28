package com.skytala.eCommerce.domain.product.relations.product.control.priceType;

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
import com.skytala.eCommerce.domain.product.relations.product.command.priceType.AddProductPriceType;
import com.skytala.eCommerce.domain.product.relations.product.command.priceType.DeleteProductPriceType;
import com.skytala.eCommerce.domain.product.relations.product.command.priceType.UpdateProductPriceType;
import com.skytala.eCommerce.domain.product.relations.product.event.priceType.ProductPriceTypeAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.priceType.ProductPriceTypeDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.priceType.ProductPriceTypeFound;
import com.skytala.eCommerce.domain.product.relations.product.event.priceType.ProductPriceTypeUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.priceType.ProductPriceTypeMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.priceType.ProductPriceType;
import com.skytala.eCommerce.domain.product.relations.product.query.priceType.FindProductPriceTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/product/productPriceTypes")
public class ProductPriceTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductPriceTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductPriceType
	 * @return a List with the ProductPriceTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ProductPriceType>> findProductPriceTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductPriceTypesBy query = new FindProductPriceTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductPriceType> productPriceTypes =((ProductPriceTypeFound) Scheduler.execute(query).data()).getProductPriceTypes();

		return ResponseEntity.ok().body(productPriceTypes);

	}

	/**
	 * creates a new ProductPriceType entry in the ofbiz database
	 * 
	 * @param productPriceTypeToBeAdded
	 *            the ProductPriceType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductPriceType> createProductPriceType(@RequestBody ProductPriceType productPriceTypeToBeAdded) throws Exception {

		AddProductPriceType command = new AddProductPriceType(productPriceTypeToBeAdded);
		ProductPriceType productPriceType = ((ProductPriceTypeAdded) Scheduler.execute(command).data()).getAddedProductPriceType();
		
		if (productPriceType != null) 
			return successful(productPriceType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ProductPriceType with the specific Id
	 * 
	 * @param productPriceTypeToBeUpdated
	 *            the ProductPriceType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{productPriceTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateProductPriceType(@RequestBody ProductPriceType productPriceTypeToBeUpdated,
			@PathVariable String productPriceTypeId) throws Exception {

		productPriceTypeToBeUpdated.setProductPriceTypeId(productPriceTypeId);

		UpdateProductPriceType command = new UpdateProductPriceType(productPriceTypeToBeUpdated);

		try {
			if(((ProductPriceTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{productPriceTypeId}")
	public ResponseEntity<ProductPriceType> findById(@PathVariable String productPriceTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productPriceTypeId", productPriceTypeId);
		try {

			List<ProductPriceType> foundProductPriceType = findProductPriceTypesBy(requestParams).getBody();
			if(foundProductPriceType.size()==1){				return successful(foundProductPriceType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{productPriceTypeId}")
	public ResponseEntity<String> deleteProductPriceTypeByIdUpdated(@PathVariable String productPriceTypeId) throws Exception {
		DeleteProductPriceType command = new DeleteProductPriceType(productPriceTypeId);

		try {
			if (((ProductPriceTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
