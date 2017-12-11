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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<Object> findProductStoreVendorPaymentsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductStoreVendorPaymentsBy query = new FindProductStoreVendorPaymentsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductStoreVendorPayment> productStoreVendorPayments =((ProductStoreVendorPaymentFound) Scheduler.execute(query).data()).getProductStoreVendorPayments();

		if (productStoreVendorPayments.size() == 1) {
			return ResponseEntity.ok().body(productStoreVendorPayments.get(0));
		}

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
	public ResponseEntity<Object> createProductStoreVendorPayment(HttpServletRequest request) throws Exception {

		ProductStoreVendorPayment productStoreVendorPaymentToBeAdded = new ProductStoreVendorPayment();
		try {
			productStoreVendorPaymentToBeAdded = ProductStoreVendorPaymentMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
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
	public ResponseEntity<Object> createProductStoreVendorPayment(@RequestBody ProductStoreVendorPayment productStoreVendorPaymentToBeAdded) throws Exception {

		AddProductStoreVendorPayment command = new AddProductStoreVendorPayment(productStoreVendorPaymentToBeAdded);
		ProductStoreVendorPayment productStoreVendorPayment = ((ProductStoreVendorPaymentAdded) Scheduler.execute(command).data()).getAddedProductStoreVendorPayment();
		
		if (productStoreVendorPayment != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(productStoreVendorPayment);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProductStoreVendorPayment could not be created.");
	}

	/**
	 * this method will only be called by Springs DispatcherServlet
	 * 
	 * @deprecated
	 * @param request
	 *            HttpServletRequest object
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@PutMapping(value = "/update", consumes = "application/x-www-form-urlencoded")
	public boolean updateProductStoreVendorPayment(HttpServletRequest request) throws Exception {

		BufferedReader br;
		String data = null;
		Map<String, String> dataMap = null;

		try {
			br = new BufferedReader(new InputStreamReader(request.getInputStream()));
			if (br != null) {
				data = java.net.URLDecoder.decode(br.readLine(), "UTF-8");
			}
		} catch (IOException e1) {
			e1.printStackTrace();
			return false;
		}

		dataMap = Splitter.on('&').trimResults().withKeyValueSeparator(Splitter.on('=').limit(2).trimResults())
				.split(data);

		ProductStoreVendorPayment productStoreVendorPaymentToBeUpdated = new ProductStoreVendorPayment();

		try {
			productStoreVendorPaymentToBeUpdated = ProductStoreVendorPaymentMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProductStoreVendorPayment(productStoreVendorPaymentToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateProductStoreVendorPayment(@RequestBody ProductStoreVendorPayment productStoreVendorPaymentToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		productStoreVendorPaymentToBeUpdated.setnull(null);

		UpdateProductStoreVendorPayment command = new UpdateProductStoreVendorPayment(productStoreVendorPaymentToBeUpdated);

		try {
			if(((ProductStoreVendorPaymentUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{productStoreVendorPaymentId}")
	public ResponseEntity<Object> findById(@PathVariable String productStoreVendorPaymentId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productStoreVendorPaymentId", productStoreVendorPaymentId);
		try {

			Object foundProductStoreVendorPayment = findProductStoreVendorPaymentsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProductStoreVendorPayment);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{productStoreVendorPaymentId}")
	public ResponseEntity<Object> deleteProductStoreVendorPaymentByIdUpdated(@PathVariable String productStoreVendorPaymentId) throws Exception {
		DeleteProductStoreVendorPayment command = new DeleteProductStoreVendorPayment(productStoreVendorPaymentId);

		try {
			if (((ProductStoreVendorPaymentDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductStoreVendorPayment could not be deleted");

	}

}
