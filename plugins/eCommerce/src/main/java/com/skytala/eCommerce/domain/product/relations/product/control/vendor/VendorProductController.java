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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@RequestMapping("/vendorProducts")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findVendorProductsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindVendorProductsBy query = new FindVendorProductsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<VendorProduct> vendorProducts =((VendorProductFound) Scheduler.execute(query).data()).getVendorProducts();

		if (vendorProducts.size() == 1) {
			return ResponseEntity.ok().body(vendorProducts.get(0));
		}

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
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createVendorProduct(HttpServletRequest request) throws Exception {

		VendorProduct vendorProductToBeAdded = new VendorProduct();
		try {
			vendorProductToBeAdded = VendorProductMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
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
	public ResponseEntity<Object> createVendorProduct(@RequestBody VendorProduct vendorProductToBeAdded) throws Exception {

		AddVendorProduct command = new AddVendorProduct(vendorProductToBeAdded);
		VendorProduct vendorProduct = ((VendorProductAdded) Scheduler.execute(command).data()).getAddedVendorProduct();
		
		if (vendorProduct != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(vendorProduct);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("VendorProduct could not be created.");
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
	@RequestMapping(method = RequestMethod.PUT, value = "/update", consumes = "application/x-www-form-urlencoded")
	public boolean updateVendorProduct(HttpServletRequest request) throws Exception {

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

		VendorProduct vendorProductToBeUpdated = new VendorProduct();

		try {
			vendorProductToBeUpdated = VendorProductMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateVendorProduct(vendorProductToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateVendorProduct(@RequestBody VendorProduct vendorProductToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		vendorProductToBeUpdated.setnull(null);

		UpdateVendorProduct command = new UpdateVendorProduct(vendorProductToBeUpdated);

		try {
			if(((VendorProductUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{vendorProductId}")
	public ResponseEntity<Object> findById(@PathVariable String vendorProductId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("vendorProductId", vendorProductId);
		try {

			Object foundVendorProduct = findVendorProductsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundVendorProduct);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{vendorProductId}")
	public ResponseEntity<Object> deleteVendorProductByIdUpdated(@PathVariable String vendorProductId) throws Exception {
		DeleteVendorProduct command = new DeleteVendorProduct(vendorProductId);

		try {
			if (((VendorProductDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("VendorProduct could not be deleted");

	}

}
