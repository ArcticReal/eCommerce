package com.skytala.eCommerce.domain.product.relations.product.control.meterType;

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
import com.skytala.eCommerce.domain.product.relations.product.command.meterType.AddProductMeterType;
import com.skytala.eCommerce.domain.product.relations.product.command.meterType.DeleteProductMeterType;
import com.skytala.eCommerce.domain.product.relations.product.command.meterType.UpdateProductMeterType;
import com.skytala.eCommerce.domain.product.relations.product.event.meterType.ProductMeterTypeAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.meterType.ProductMeterTypeDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.meterType.ProductMeterTypeFound;
import com.skytala.eCommerce.domain.product.relations.product.event.meterType.ProductMeterTypeUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.meterType.ProductMeterTypeMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.meterType.ProductMeterType;
import com.skytala.eCommerce.domain.product.relations.product.query.meterType.FindProductMeterTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/product/productMeterTypes")
public class ProductMeterTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductMeterTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductMeterType
	 * @return a List with the ProductMeterTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ProductMeterType>> findProductMeterTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductMeterTypesBy query = new FindProductMeterTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductMeterType> productMeterTypes =((ProductMeterTypeFound) Scheduler.execute(query).data()).getProductMeterTypes();

		return ResponseEntity.ok().body(productMeterTypes);

	}

	/**
	 * creates a new ProductMeterType entry in the ofbiz database
	 * 
	 * @param productMeterTypeToBeAdded
	 *            the ProductMeterType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductMeterType> createProductMeterType(@RequestBody ProductMeterType productMeterTypeToBeAdded) throws Exception {

		AddProductMeterType command = new AddProductMeterType(productMeterTypeToBeAdded);
		ProductMeterType productMeterType = ((ProductMeterTypeAdded) Scheduler.execute(command).data()).getAddedProductMeterType();
		
		if (productMeterType != null) 
			return successful(productMeterType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ProductMeterType with the specific Id
	 * 
	 * @param productMeterTypeToBeUpdated
	 *            the ProductMeterType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{productMeterTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateProductMeterType(@RequestBody ProductMeterType productMeterTypeToBeUpdated,
			@PathVariable String productMeterTypeId) throws Exception {

		productMeterTypeToBeUpdated.setProductMeterTypeId(productMeterTypeId);

		UpdateProductMeterType command = new UpdateProductMeterType(productMeterTypeToBeUpdated);

		try {
			if(((ProductMeterTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{productMeterTypeId}")
	public ResponseEntity<ProductMeterType> findById(@PathVariable String productMeterTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productMeterTypeId", productMeterTypeId);
		try {

			List<ProductMeterType> foundProductMeterType = findProductMeterTypesBy(requestParams).getBody();
			if(foundProductMeterType.size()==1){				return successful(foundProductMeterType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{productMeterTypeId}")
	public ResponseEntity<String> deleteProductMeterTypeByIdUpdated(@PathVariable String productMeterTypeId) throws Exception {
		DeleteProductMeterType command = new DeleteProductMeterType(productMeterTypeId);

		try {
			if (((ProductMeterTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
