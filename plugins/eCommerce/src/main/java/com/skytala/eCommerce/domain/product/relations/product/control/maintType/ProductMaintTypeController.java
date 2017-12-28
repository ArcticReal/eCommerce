package com.skytala.eCommerce.domain.product.relations.product.control.maintType;

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
import com.skytala.eCommerce.domain.product.relations.product.command.maintType.AddProductMaintType;
import com.skytala.eCommerce.domain.product.relations.product.command.maintType.DeleteProductMaintType;
import com.skytala.eCommerce.domain.product.relations.product.command.maintType.UpdateProductMaintType;
import com.skytala.eCommerce.domain.product.relations.product.event.maintType.ProductMaintTypeAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.maintType.ProductMaintTypeDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.maintType.ProductMaintTypeFound;
import com.skytala.eCommerce.domain.product.relations.product.event.maintType.ProductMaintTypeUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.maintType.ProductMaintTypeMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.maintType.ProductMaintType;
import com.skytala.eCommerce.domain.product.relations.product.query.maintType.FindProductMaintTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/product/productMaintTypes")
public class ProductMaintTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductMaintTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductMaintType
	 * @return a List with the ProductMaintTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ProductMaintType>> findProductMaintTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductMaintTypesBy query = new FindProductMaintTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductMaintType> productMaintTypes =((ProductMaintTypeFound) Scheduler.execute(query).data()).getProductMaintTypes();

		return ResponseEntity.ok().body(productMaintTypes);

	}

	/**
	 * creates a new ProductMaintType entry in the ofbiz database
	 * 
	 * @param productMaintTypeToBeAdded
	 *            the ProductMaintType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductMaintType> createProductMaintType(@RequestBody ProductMaintType productMaintTypeToBeAdded) throws Exception {

		AddProductMaintType command = new AddProductMaintType(productMaintTypeToBeAdded);
		ProductMaintType productMaintType = ((ProductMaintTypeAdded) Scheduler.execute(command).data()).getAddedProductMaintType();
		
		if (productMaintType != null) 
			return successful(productMaintType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ProductMaintType with the specific Id
	 * 
	 * @param productMaintTypeToBeUpdated
	 *            the ProductMaintType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{productMaintTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateProductMaintType(@RequestBody ProductMaintType productMaintTypeToBeUpdated,
			@PathVariable String productMaintTypeId) throws Exception {

		productMaintTypeToBeUpdated.setProductMaintTypeId(productMaintTypeId);

		UpdateProductMaintType command = new UpdateProductMaintType(productMaintTypeToBeUpdated);

		try {
			if(((ProductMaintTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{productMaintTypeId}")
	public ResponseEntity<ProductMaintType> findById(@PathVariable String productMaintTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productMaintTypeId", productMaintTypeId);
		try {

			List<ProductMaintType> foundProductMaintType = findProductMaintTypesBy(requestParams).getBody();
			if(foundProductMaintType.size()==1){				return successful(foundProductMaintType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{productMaintTypeId}")
	public ResponseEntity<String> deleteProductMaintTypeByIdUpdated(@PathVariable String productMaintTypeId) throws Exception {
		DeleteProductMaintType command = new DeleteProductMaintType(productMaintTypeId);

		try {
			if (((ProductMaintTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
