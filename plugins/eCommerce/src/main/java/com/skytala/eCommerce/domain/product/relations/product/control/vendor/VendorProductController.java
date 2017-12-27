package com.skytala.eCommerce.domain.product.relations.product.control.vendor;

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
import com.skytala.eCommerce.domain.product.relations.product.command.vendor.AddVendorProduct;
import com.skytala.eCommerce.domain.product.relations.product.command.vendor.DeleteVendorProduct;
import com.skytala.eCommerce.domain.product.relations.product.command.vendor.UpdateVendorProduct;
import com.skytala.eCommerce.domain.product.relations.product.event.vendor.VendorProductAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.vendor.VendorProductDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.vendor.VendorProductFound;
import com.skytala.eCommerce.domain.product.relations.product.event.vendor.VendorProductUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.vendor.VendorProductMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.vendor.VendorProduct;
import com.skytala.eCommerce.domain.product.relations.product.query.vendor.FindVendorProductsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/product/vendorProducts")
public class VendorProductController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public VendorProductController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a VendorProduct
	 * @return a List with the VendorProducts
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<VendorProduct>> findVendorProductsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindVendorProductsBy query = new FindVendorProductsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<VendorProduct> vendorProducts =((VendorProductFound) Scheduler.execute(query).data()).getVendorProducts();

		return ResponseEntity.ok().body(vendorProducts);

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
	public ResponseEntity<VendorProduct> createVendorProduct(HttpServletRequest request) throws Exception {

		VendorProduct vendorProductToBeAdded = new VendorProduct();
		try {
			vendorProductToBeAdded = VendorProductMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createVendorProduct(vendorProductToBeAdded);

	}

	/**
	 * creates a new VendorProduct entry in the ofbiz database
	 * 
	 * @param vendorProductToBeAdded
	 *            the VendorProduct thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<VendorProduct> createVendorProduct(@RequestBody VendorProduct vendorProductToBeAdded) throws Exception {

		AddVendorProduct command = new AddVendorProduct(vendorProductToBeAdded);
		VendorProduct vendorProduct = ((VendorProductAdded) Scheduler.execute(command).data()).getAddedVendorProduct();
		
		if (vendorProduct != null) 
			return successful(vendorProduct);
		else 
			return conflict(null);
	}

	/**
	 * Updates the VendorProduct with the specific Id
	 * 
	 * @param vendorProductToBeUpdated
	 *            the VendorProduct thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateVendorProduct(@RequestBody VendorProduct vendorProductToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		vendorProductToBeUpdated.setnull(null);

		UpdateVendorProduct command = new UpdateVendorProduct(vendorProductToBeUpdated);

		try {
			if(((VendorProductUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{vendorProductId}")
	public ResponseEntity<VendorProduct> findById(@PathVariable String vendorProductId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("vendorProductId", vendorProductId);
		try {

			List<VendorProduct> foundVendorProduct = findVendorProductsBy(requestParams).getBody();
			if(foundVendorProduct.size()==1){				return successful(foundVendorProduct.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{vendorProductId}")
	public ResponseEntity<String> deleteVendorProductByIdUpdated(@PathVariable String vendorProductId) throws Exception {
		DeleteVendorProduct command = new DeleteVendorProduct(vendorProductId);

		try {
			if (((VendorProductDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
