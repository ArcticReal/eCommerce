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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	public ResponseEntity<List<ProductStoreVendorShipment>> findProductStoreVendorShipmentsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductStoreVendorShipmentsBy query = new FindProductStoreVendorShipmentsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductStoreVendorShipment> productStoreVendorShipments =((ProductStoreVendorShipmentFound) Scheduler.execute(query).data()).getProductStoreVendorShipments();

		return ResponseEntity.ok().body(productStoreVendorShipments);

	}

	/**
	 * creates a new ProductStoreVendorShipment entry in the ofbiz database
	 * 
	 * @param productStoreVendorShipmentToBeAdded
	 *            the ProductStoreVendorShipment thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductStoreVendorShipment> createProductStoreVendorShipment(@RequestBody ProductStoreVendorShipment productStoreVendorShipmentToBeAdded) throws Exception {

		AddProductStoreVendorShipment command = new AddProductStoreVendorShipment(productStoreVendorShipmentToBeAdded);
		ProductStoreVendorShipment productStoreVendorShipment = ((ProductStoreVendorShipmentAdded) Scheduler.execute(command).data()).getAddedProductStoreVendorShipment();
		
		if (productStoreVendorShipment != null) 
			return successful(productStoreVendorShipment);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateProductStoreVendorShipment(@RequestBody ProductStoreVendorShipment productStoreVendorShipmentToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		productStoreVendorShipmentToBeUpdated.setnull(null);

		UpdateProductStoreVendorShipment command = new UpdateProductStoreVendorShipment(productStoreVendorShipmentToBeUpdated);

		try {
			if(((ProductStoreVendorShipmentUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{productStoreVendorShipmentId}")
	public ResponseEntity<ProductStoreVendorShipment> findById(@PathVariable String productStoreVendorShipmentId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productStoreVendorShipmentId", productStoreVendorShipmentId);
		try {

			List<ProductStoreVendorShipment> foundProductStoreVendorShipment = findProductStoreVendorShipmentsBy(requestParams).getBody();
			if(foundProductStoreVendorShipment.size()==1){				return successful(foundProductStoreVendorShipment.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{productStoreVendorShipmentId}")
	public ResponseEntity<String> deleteProductStoreVendorShipmentByIdUpdated(@PathVariable String productStoreVendorShipmentId) throws Exception {
		DeleteProductStoreVendorShipment command = new DeleteProductStoreVendorShipment(productStoreVendorShipmentId);

		try {
			if (((ProductStoreVendorShipmentDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
