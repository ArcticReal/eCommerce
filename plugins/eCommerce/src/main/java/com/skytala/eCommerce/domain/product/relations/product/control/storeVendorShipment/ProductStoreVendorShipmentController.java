package com.skytala.eCommerce.domain.product.relations.product.control.storeVendorShipment;

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
import com.skytala.eCommerce.domain.product.relations.product.command.storeVendorShipment.AddProductStoreVendorShipment;
import com.skytala.eCommerce.domain.product.relations.product.command.storeVendorShipment.DeleteProductStoreVendorShipment;
import com.skytala.eCommerce.domain.product.relations.product.command.storeVendorShipment.UpdateProductStoreVendorShipment;
import com.skytala.eCommerce.domain.product.relations.product.event.storeVendorShipment.ProductStoreVendorShipmentAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.storeVendorShipment.ProductStoreVendorShipmentDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.storeVendorShipment.ProductStoreVendorShipmentFound;
import com.skytala.eCommerce.domain.product.relations.product.event.storeVendorShipment.ProductStoreVendorShipmentUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.storeVendorShipment.ProductStoreVendorShipmentMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.storeVendorShipment.ProductStoreVendorShipment;
import com.skytala.eCommerce.domain.product.relations.product.query.storeVendorShipment.FindProductStoreVendorShipmentsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/product/product/productStoreVendorShipments")
public class ProductStoreVendorShipmentController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductStoreVendorShipmentController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductStoreVendorShipment
	 * @return a List with the ProductStoreVendorShipments
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findProductStoreVendorShipmentsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductStoreVendorShipmentsBy query = new FindProductStoreVendorShipmentsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductStoreVendorShipment> productStoreVendorShipments =((ProductStoreVendorShipmentFound) Scheduler.execute(query).data()).getProductStoreVendorShipments();

		if (productStoreVendorShipments.size() == 1) {
			return ResponseEntity.ok().body(productStoreVendorShipments.get(0));
		}

		return ResponseEntity.ok().body(productStoreVendorShipments);

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
	public ResponseEntity<Object> createProductStoreVendorShipment(HttpServletRequest request) throws Exception {

		ProductStoreVendorShipment productStoreVendorShipmentToBeAdded = new ProductStoreVendorShipment();
		try {
			productStoreVendorShipmentToBeAdded = ProductStoreVendorShipmentMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createProductStoreVendorShipment(productStoreVendorShipmentToBeAdded);

	}

	/**
	 * creates a new ProductStoreVendorShipment entry in the ofbiz database
	 * 
	 * @param productStoreVendorShipmentToBeAdded
	 *            the ProductStoreVendorShipment thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createProductStoreVendorShipment(@RequestBody ProductStoreVendorShipment productStoreVendorShipmentToBeAdded) throws Exception {

		AddProductStoreVendorShipment command = new AddProductStoreVendorShipment(productStoreVendorShipmentToBeAdded);
		ProductStoreVendorShipment productStoreVendorShipment = ((ProductStoreVendorShipmentAdded) Scheduler.execute(command).data()).getAddedProductStoreVendorShipment();
		
		if (productStoreVendorShipment != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(productStoreVendorShipment);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProductStoreVendorShipment could not be created.");
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
	public boolean updateProductStoreVendorShipment(HttpServletRequest request) throws Exception {

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

		ProductStoreVendorShipment productStoreVendorShipmentToBeUpdated = new ProductStoreVendorShipment();

		try {
			productStoreVendorShipmentToBeUpdated = ProductStoreVendorShipmentMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProductStoreVendorShipment(productStoreVendorShipmentToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ProductStoreVendorShipment with the specific Id
	 * 
	 * @param productStoreVendorShipmentToBeUpdated
	 *            the ProductStoreVendorShipment thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateProductStoreVendorShipment(@RequestBody ProductStoreVendorShipment productStoreVendorShipmentToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		productStoreVendorShipmentToBeUpdated.setnull(null);

		UpdateProductStoreVendorShipment command = new UpdateProductStoreVendorShipment(productStoreVendorShipmentToBeUpdated);

		try {
			if(((ProductStoreVendorShipmentUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{productStoreVendorShipmentId}")
	public ResponseEntity<Object> findById(@PathVariable String productStoreVendorShipmentId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productStoreVendorShipmentId", productStoreVendorShipmentId);
		try {

			Object foundProductStoreVendorShipment = findProductStoreVendorShipmentsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProductStoreVendorShipment);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{productStoreVendorShipmentId}")
	public ResponseEntity<Object> deleteProductStoreVendorShipmentByIdUpdated(@PathVariable String productStoreVendorShipmentId) throws Exception {
		DeleteProductStoreVendorShipment command = new DeleteProductStoreVendorShipment(productStoreVendorShipmentId);

		try {
			if (((ProductStoreVendorShipmentDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductStoreVendorShipment could not be deleted");

	}

}
