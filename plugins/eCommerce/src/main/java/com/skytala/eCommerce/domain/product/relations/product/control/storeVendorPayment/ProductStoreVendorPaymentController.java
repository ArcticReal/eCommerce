package com.skytala.eCommerce.domain.product.relations.product.control.storeVendorPayment;

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
import com.skytala.eCommerce.domain.product.relations.product.command.storeVendorPayment.AddProductStoreVendorPayment;
import com.skytala.eCommerce.domain.product.relations.product.command.storeVendorPayment.DeleteProductStoreVendorPayment;
import com.skytala.eCommerce.domain.product.relations.product.command.storeVendorPayment.UpdateProductStoreVendorPayment;
import com.skytala.eCommerce.domain.product.relations.product.event.storeVendorPayment.ProductStoreVendorPaymentAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.storeVendorPayment.ProductStoreVendorPaymentDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.storeVendorPayment.ProductStoreVendorPaymentFound;
import com.skytala.eCommerce.domain.product.relations.product.event.storeVendorPayment.ProductStoreVendorPaymentUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.storeVendorPayment.ProductStoreVendorPaymentMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.storeVendorPayment.ProductStoreVendorPayment;
import com.skytala.eCommerce.domain.product.relations.product.query.storeVendorPayment.FindProductStoreVendorPaymentsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/product/productStoreVendorPayments")
public class ProductStoreVendorPaymentController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductStoreVendorPaymentController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductStoreVendorPayment
	 * @return a List with the ProductStoreVendorPayments
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ProductStoreVendorPayment>> findProductStoreVendorPaymentsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductStoreVendorPaymentsBy query = new FindProductStoreVendorPaymentsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductStoreVendorPayment> productStoreVendorPayments =((ProductStoreVendorPaymentFound) Scheduler.execute(query).data()).getProductStoreVendorPayments();

		return ResponseEntity.ok().body(productStoreVendorPayments);

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
	public ResponseEntity<ProductStoreVendorPayment> createProductStoreVendorPayment(HttpServletRequest request) throws Exception {

		ProductStoreVendorPayment productStoreVendorPaymentToBeAdded = new ProductStoreVendorPayment();
		try {
			productStoreVendorPaymentToBeAdded = ProductStoreVendorPaymentMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createProductStoreVendorPayment(productStoreVendorPaymentToBeAdded);

	}

	/**
	 * creates a new ProductStoreVendorPayment entry in the ofbiz database
	 * 
	 * @param productStoreVendorPaymentToBeAdded
	 *            the ProductStoreVendorPayment thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductStoreVendorPayment> createProductStoreVendorPayment(@RequestBody ProductStoreVendorPayment productStoreVendorPaymentToBeAdded) throws Exception {

		AddProductStoreVendorPayment command = new AddProductStoreVendorPayment(productStoreVendorPaymentToBeAdded);
		ProductStoreVendorPayment productStoreVendorPayment = ((ProductStoreVendorPaymentAdded) Scheduler.execute(command).data()).getAddedProductStoreVendorPayment();
		
		if (productStoreVendorPayment != null) 
			return successful(productStoreVendorPayment);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ProductStoreVendorPayment with the specific Id
	 * 
	 * @param productStoreVendorPaymentToBeUpdated
	 *            the ProductStoreVendorPayment thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateProductStoreVendorPayment(@RequestBody ProductStoreVendorPayment productStoreVendorPaymentToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		productStoreVendorPaymentToBeUpdated.setnull(null);

		UpdateProductStoreVendorPayment command = new UpdateProductStoreVendorPayment(productStoreVendorPaymentToBeUpdated);

		try {
			if(((ProductStoreVendorPaymentUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{productStoreVendorPaymentId}")
	public ResponseEntity<ProductStoreVendorPayment> findById(@PathVariable String productStoreVendorPaymentId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productStoreVendorPaymentId", productStoreVendorPaymentId);
		try {

			List<ProductStoreVendorPayment> foundProductStoreVendorPayment = findProductStoreVendorPaymentsBy(requestParams).getBody();
			if(foundProductStoreVendorPayment.size()==1){				return successful(foundProductStoreVendorPayment.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{productStoreVendorPaymentId}")
	public ResponseEntity<String> deleteProductStoreVendorPaymentByIdUpdated(@PathVariable String productStoreVendorPaymentId) throws Exception {
		DeleteProductStoreVendorPayment command = new DeleteProductStoreVendorPayment(productStoreVendorPaymentId);

		try {
			if (((ProductStoreVendorPaymentDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
