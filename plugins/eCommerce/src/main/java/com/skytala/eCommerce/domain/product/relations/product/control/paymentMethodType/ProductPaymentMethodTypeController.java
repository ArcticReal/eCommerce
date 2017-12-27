package com.skytala.eCommerce.domain.product.relations.product.control.paymentMethodType;

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
import com.skytala.eCommerce.domain.product.relations.product.command.paymentMethodType.AddProductPaymentMethodType;
import com.skytala.eCommerce.domain.product.relations.product.command.paymentMethodType.DeleteProductPaymentMethodType;
import com.skytala.eCommerce.domain.product.relations.product.command.paymentMethodType.UpdateProductPaymentMethodType;
import com.skytala.eCommerce.domain.product.relations.product.event.paymentMethodType.ProductPaymentMethodTypeAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.paymentMethodType.ProductPaymentMethodTypeDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.paymentMethodType.ProductPaymentMethodTypeFound;
import com.skytala.eCommerce.domain.product.relations.product.event.paymentMethodType.ProductPaymentMethodTypeUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.paymentMethodType.ProductPaymentMethodTypeMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.paymentMethodType.ProductPaymentMethodType;
import com.skytala.eCommerce.domain.product.relations.product.query.paymentMethodType.FindProductPaymentMethodTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/product/productPaymentMethodTypes")
public class ProductPaymentMethodTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductPaymentMethodTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductPaymentMethodType
	 * @return a List with the ProductPaymentMethodTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ProductPaymentMethodType>> findProductPaymentMethodTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductPaymentMethodTypesBy query = new FindProductPaymentMethodTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductPaymentMethodType> productPaymentMethodTypes =((ProductPaymentMethodTypeFound) Scheduler.execute(query).data()).getProductPaymentMethodTypes();

		return ResponseEntity.ok().body(productPaymentMethodTypes);

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
	public ResponseEntity<ProductPaymentMethodType> createProductPaymentMethodType(HttpServletRequest request) throws Exception {

		ProductPaymentMethodType productPaymentMethodTypeToBeAdded = new ProductPaymentMethodType();
		try {
			productPaymentMethodTypeToBeAdded = ProductPaymentMethodTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createProductPaymentMethodType(productPaymentMethodTypeToBeAdded);

	}

	/**
	 * creates a new ProductPaymentMethodType entry in the ofbiz database
	 * 
	 * @param productPaymentMethodTypeToBeAdded
	 *            the ProductPaymentMethodType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductPaymentMethodType> createProductPaymentMethodType(@RequestBody ProductPaymentMethodType productPaymentMethodTypeToBeAdded) throws Exception {

		AddProductPaymentMethodType command = new AddProductPaymentMethodType(productPaymentMethodTypeToBeAdded);
		ProductPaymentMethodType productPaymentMethodType = ((ProductPaymentMethodTypeAdded) Scheduler.execute(command).data()).getAddedProductPaymentMethodType();
		
		if (productPaymentMethodType != null) 
			return successful(productPaymentMethodType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ProductPaymentMethodType with the specific Id
	 * 
	 * @param productPaymentMethodTypeToBeUpdated
	 *            the ProductPaymentMethodType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateProductPaymentMethodType(@RequestBody ProductPaymentMethodType productPaymentMethodTypeToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		productPaymentMethodTypeToBeUpdated.setnull(null);

		UpdateProductPaymentMethodType command = new UpdateProductPaymentMethodType(productPaymentMethodTypeToBeUpdated);

		try {
			if(((ProductPaymentMethodTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{productPaymentMethodTypeId}")
	public ResponseEntity<ProductPaymentMethodType> findById(@PathVariable String productPaymentMethodTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productPaymentMethodTypeId", productPaymentMethodTypeId);
		try {

			List<ProductPaymentMethodType> foundProductPaymentMethodType = findProductPaymentMethodTypesBy(requestParams).getBody();
			if(foundProductPaymentMethodType.size()==1){				return successful(foundProductPaymentMethodType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{productPaymentMethodTypeId}")
	public ResponseEntity<String> deleteProductPaymentMethodTypeByIdUpdated(@PathVariable String productPaymentMethodTypeId) throws Exception {
		DeleteProductPaymentMethodType command = new DeleteProductPaymentMethodType(productPaymentMethodTypeId);

		try {
			if (((ProductPaymentMethodTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
