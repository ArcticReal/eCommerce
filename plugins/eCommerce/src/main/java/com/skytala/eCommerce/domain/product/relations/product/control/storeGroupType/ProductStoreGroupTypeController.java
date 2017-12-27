package com.skytala.eCommerce.domain.product.relations.product.control.storeGroupType;

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
import com.skytala.eCommerce.domain.product.relations.product.command.storeGroupType.AddProductStoreGroupType;
import com.skytala.eCommerce.domain.product.relations.product.command.storeGroupType.DeleteProductStoreGroupType;
import com.skytala.eCommerce.domain.product.relations.product.command.storeGroupType.UpdateProductStoreGroupType;
import com.skytala.eCommerce.domain.product.relations.product.event.storeGroupType.ProductStoreGroupTypeAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.storeGroupType.ProductStoreGroupTypeDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.storeGroupType.ProductStoreGroupTypeFound;
import com.skytala.eCommerce.domain.product.relations.product.event.storeGroupType.ProductStoreGroupTypeUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.storeGroupType.ProductStoreGroupTypeMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.storeGroupType.ProductStoreGroupType;
import com.skytala.eCommerce.domain.product.relations.product.query.storeGroupType.FindProductStoreGroupTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/product/productStoreGroupTypes")
public class ProductStoreGroupTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductStoreGroupTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductStoreGroupType
	 * @return a List with the ProductStoreGroupTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ProductStoreGroupType>> findProductStoreGroupTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductStoreGroupTypesBy query = new FindProductStoreGroupTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductStoreGroupType> productStoreGroupTypes =((ProductStoreGroupTypeFound) Scheduler.execute(query).data()).getProductStoreGroupTypes();

		return ResponseEntity.ok().body(productStoreGroupTypes);

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
	public ResponseEntity<ProductStoreGroupType> createProductStoreGroupType(HttpServletRequest request) throws Exception {

		ProductStoreGroupType productStoreGroupTypeToBeAdded = new ProductStoreGroupType();
		try {
			productStoreGroupTypeToBeAdded = ProductStoreGroupTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createProductStoreGroupType(productStoreGroupTypeToBeAdded);

	}

	/**
	 * creates a new ProductStoreGroupType entry in the ofbiz database
	 * 
	 * @param productStoreGroupTypeToBeAdded
	 *            the ProductStoreGroupType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductStoreGroupType> createProductStoreGroupType(@RequestBody ProductStoreGroupType productStoreGroupTypeToBeAdded) throws Exception {

		AddProductStoreGroupType command = new AddProductStoreGroupType(productStoreGroupTypeToBeAdded);
		ProductStoreGroupType productStoreGroupType = ((ProductStoreGroupTypeAdded) Scheduler.execute(command).data()).getAddedProductStoreGroupType();
		
		if (productStoreGroupType != null) 
			return successful(productStoreGroupType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ProductStoreGroupType with the specific Id
	 * 
	 * @param productStoreGroupTypeToBeUpdated
	 *            the ProductStoreGroupType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{productStoreGroupTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateProductStoreGroupType(@RequestBody ProductStoreGroupType productStoreGroupTypeToBeUpdated,
			@PathVariable String productStoreGroupTypeId) throws Exception {

		productStoreGroupTypeToBeUpdated.setProductStoreGroupTypeId(productStoreGroupTypeId);

		UpdateProductStoreGroupType command = new UpdateProductStoreGroupType(productStoreGroupTypeToBeUpdated);

		try {
			if(((ProductStoreGroupTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{productStoreGroupTypeId}")
	public ResponseEntity<ProductStoreGroupType> findById(@PathVariable String productStoreGroupTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productStoreGroupTypeId", productStoreGroupTypeId);
		try {

			List<ProductStoreGroupType> foundProductStoreGroupType = findProductStoreGroupTypesBy(requestParams).getBody();
			if(foundProductStoreGroupType.size()==1){				return successful(foundProductStoreGroupType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{productStoreGroupTypeId}")
	public ResponseEntity<String> deleteProductStoreGroupTypeByIdUpdated(@PathVariable String productStoreGroupTypeId) throws Exception {
		DeleteProductStoreGroupType command = new DeleteProductStoreGroupType(productStoreGroupTypeId);

		try {
			if (((ProductStoreGroupTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
