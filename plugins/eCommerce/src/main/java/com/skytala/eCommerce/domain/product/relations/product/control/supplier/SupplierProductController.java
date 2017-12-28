package com.skytala.eCommerce.domain.product.relations.product.control.supplier;

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
import com.skytala.eCommerce.domain.product.relations.product.command.supplier.AddSupplierProduct;
import com.skytala.eCommerce.domain.product.relations.product.command.supplier.DeleteSupplierProduct;
import com.skytala.eCommerce.domain.product.relations.product.command.supplier.UpdateSupplierProduct;
import com.skytala.eCommerce.domain.product.relations.product.event.supplier.SupplierProductAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.supplier.SupplierProductDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.supplier.SupplierProductFound;
import com.skytala.eCommerce.domain.product.relations.product.event.supplier.SupplierProductUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.supplier.SupplierProductMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.supplier.SupplierProduct;
import com.skytala.eCommerce.domain.product.relations.product.query.supplier.FindSupplierProductsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/product/supplierProducts")
public class SupplierProductController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public SupplierProductController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a SupplierProduct
	 * @return a List with the SupplierProducts
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<SupplierProduct>> findSupplierProductsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindSupplierProductsBy query = new FindSupplierProductsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<SupplierProduct> supplierProducts =((SupplierProductFound) Scheduler.execute(query).data()).getSupplierProducts();

		return ResponseEntity.ok().body(supplierProducts);

	}

	/**
	 * creates a new SupplierProduct entry in the ofbiz database
	 * 
	 * @param supplierProductToBeAdded
	 *            the SupplierProduct thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<SupplierProduct> createSupplierProduct(@RequestBody SupplierProduct supplierProductToBeAdded) throws Exception {

		AddSupplierProduct command = new AddSupplierProduct(supplierProductToBeAdded);
		SupplierProduct supplierProduct = ((SupplierProductAdded) Scheduler.execute(command).data()).getAddedSupplierProduct();
		
		if (supplierProduct != null) 
			return successful(supplierProduct);
		else 
			return conflict(null);
	}

	/**
	 * Updates the SupplierProduct with the specific Id
	 * 
	 * @param supplierProductToBeUpdated
	 *            the SupplierProduct thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateSupplierProduct(@RequestBody SupplierProduct supplierProductToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		supplierProductToBeUpdated.setnull(null);

		UpdateSupplierProduct command = new UpdateSupplierProduct(supplierProductToBeUpdated);

		try {
			if(((SupplierProductUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{supplierProductId}")
	public ResponseEntity<SupplierProduct> findById(@PathVariable String supplierProductId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("supplierProductId", supplierProductId);
		try {

			List<SupplierProduct> foundSupplierProduct = findSupplierProductsBy(requestParams).getBody();
			if(foundSupplierProduct.size()==1){				return successful(foundSupplierProduct.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{supplierProductId}")
	public ResponseEntity<String> deleteSupplierProductByIdUpdated(@PathVariable String supplierProductId) throws Exception {
		DeleteSupplierProduct command = new DeleteSupplierProduct(supplierProductId);

		try {
			if (((SupplierProductDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
