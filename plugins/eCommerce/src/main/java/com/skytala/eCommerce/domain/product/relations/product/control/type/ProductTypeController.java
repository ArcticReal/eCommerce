package com.skytala.eCommerce.domain.product.relations.product.control.type;

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
import com.skytala.eCommerce.domain.product.relations.product.command.type.AddProductType;
import com.skytala.eCommerce.domain.product.relations.product.command.type.DeleteProductType;
import com.skytala.eCommerce.domain.product.relations.product.command.type.UpdateProductType;
import com.skytala.eCommerce.domain.product.relations.product.event.type.ProductTypeAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.type.ProductTypeDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.type.ProductTypeFound;
import com.skytala.eCommerce.domain.product.relations.product.event.type.ProductTypeUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.type.ProductTypeMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.type.ProductType;
import com.skytala.eCommerce.domain.product.relations.product.query.type.FindProductTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/product/productTypes")
public class ProductTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductType
	 * @return a List with the ProductTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ProductType>> findProductTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductTypesBy query = new FindProductTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductType> productTypes =((ProductTypeFound) Scheduler.execute(query).data()).getProductTypes();

		return ResponseEntity.ok().body(productTypes);

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
	public ResponseEntity<ProductType> createProductType(HttpServletRequest request) throws Exception {

		ProductType productTypeToBeAdded = new ProductType();
		try {
			productTypeToBeAdded = ProductTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createProductType(productTypeToBeAdded);

	}

	/**
	 * creates a new ProductType entry in the ofbiz database
	 * 
	 * @param productTypeToBeAdded
	 *            the ProductType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductType> createProductType(@RequestBody ProductType productTypeToBeAdded) throws Exception {

		AddProductType command = new AddProductType(productTypeToBeAdded);
		ProductType productType = ((ProductTypeAdded) Scheduler.execute(command).data()).getAddedProductType();
		
		if (productType != null) 
			return successful(productType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ProductType with the specific Id
	 * 
	 * @param productTypeToBeUpdated
	 *            the ProductType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{productTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateProductType(@RequestBody ProductType productTypeToBeUpdated,
			@PathVariable String productTypeId) throws Exception {

		productTypeToBeUpdated.setProductTypeId(productTypeId);

		UpdateProductType command = new UpdateProductType(productTypeToBeUpdated);

		try {
			if(((ProductTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{productTypeId}")
	public ResponseEntity<ProductType> findById(@PathVariable String productTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productTypeId", productTypeId);
		try {

			List<ProductType> foundProductType = findProductTypesBy(requestParams).getBody();
			if(foundProductType.size()==1){				return successful(foundProductType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{productTypeId}")
	public ResponseEntity<String> deleteProductTypeByIdUpdated(@PathVariable String productTypeId) throws Exception {
		DeleteProductType command = new DeleteProductType(productTypeId);

		try {
			if (((ProductTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
