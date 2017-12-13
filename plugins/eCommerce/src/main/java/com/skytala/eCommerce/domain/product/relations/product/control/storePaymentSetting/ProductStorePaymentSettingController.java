package com.skytala.eCommerce.domain.product.relations.product.control.storePaymentSetting;

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
import com.skytala.eCommerce.domain.product.relations.product.command.storePaymentSetting.AddProductStorePaymentSetting;
import com.skytala.eCommerce.domain.product.relations.product.command.storePaymentSetting.DeleteProductStorePaymentSetting;
import com.skytala.eCommerce.domain.product.relations.product.command.storePaymentSetting.UpdateProductStorePaymentSetting;
import com.skytala.eCommerce.domain.product.relations.product.event.storePaymentSetting.ProductStorePaymentSettingAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.storePaymentSetting.ProductStorePaymentSettingDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.storePaymentSetting.ProductStorePaymentSettingFound;
import com.skytala.eCommerce.domain.product.relations.product.event.storePaymentSetting.ProductStorePaymentSettingUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.storePaymentSetting.ProductStorePaymentSettingMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.storePaymentSetting.ProductStorePaymentSetting;
import com.skytala.eCommerce.domain.product.relations.product.query.storePaymentSetting.FindProductStorePaymentSettingsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/product/product/productStorePaymentSettings")
public class ProductStorePaymentSettingController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductStorePaymentSettingController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductStorePaymentSetting
	 * @return a List with the ProductStorePaymentSettings
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findProductStorePaymentSettingsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductStorePaymentSettingsBy query = new FindProductStorePaymentSettingsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductStorePaymentSetting> productStorePaymentSettings =((ProductStorePaymentSettingFound) Scheduler.execute(query).data()).getProductStorePaymentSettings();

		if (productStorePaymentSettings.size() == 1) {
			return ResponseEntity.ok().body(productStorePaymentSettings.get(0));
		}

		return ResponseEntity.ok().body(productStorePaymentSettings);

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
	public ResponseEntity<Object> createProductStorePaymentSetting(HttpServletRequest request) throws Exception {

		ProductStorePaymentSetting productStorePaymentSettingToBeAdded = new ProductStorePaymentSetting();
		try {
			productStorePaymentSettingToBeAdded = ProductStorePaymentSettingMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createProductStorePaymentSetting(productStorePaymentSettingToBeAdded);

	}

	/**
	 * creates a new ProductStorePaymentSetting entry in the ofbiz database
	 * 
	 * @param productStorePaymentSettingToBeAdded
	 *            the ProductStorePaymentSetting thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createProductStorePaymentSetting(@RequestBody ProductStorePaymentSetting productStorePaymentSettingToBeAdded) throws Exception {

		AddProductStorePaymentSetting command = new AddProductStorePaymentSetting(productStorePaymentSettingToBeAdded);
		ProductStorePaymentSetting productStorePaymentSetting = ((ProductStorePaymentSettingAdded) Scheduler.execute(command).data()).getAddedProductStorePaymentSetting();
		
		if (productStorePaymentSetting != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(productStorePaymentSetting);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProductStorePaymentSetting could not be created.");
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
	public boolean updateProductStorePaymentSetting(HttpServletRequest request) throws Exception {

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

		ProductStorePaymentSetting productStorePaymentSettingToBeUpdated = new ProductStorePaymentSetting();

		try {
			productStorePaymentSettingToBeUpdated = ProductStorePaymentSettingMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProductStorePaymentSetting(productStorePaymentSettingToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ProductStorePaymentSetting with the specific Id
	 * 
	 * @param productStorePaymentSettingToBeUpdated
	 *            the ProductStorePaymentSetting thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateProductStorePaymentSetting(@RequestBody ProductStorePaymentSetting productStorePaymentSettingToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		productStorePaymentSettingToBeUpdated.setnull(null);

		UpdateProductStorePaymentSetting command = new UpdateProductStorePaymentSetting(productStorePaymentSettingToBeUpdated);

		try {
			if(((ProductStorePaymentSettingUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{productStorePaymentSettingId}")
	public ResponseEntity<Object> findById(@PathVariable String productStorePaymentSettingId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productStorePaymentSettingId", productStorePaymentSettingId);
		try {

			Object foundProductStorePaymentSetting = findProductStorePaymentSettingsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProductStorePaymentSetting);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{productStorePaymentSettingId}")
	public ResponseEntity<Object> deleteProductStorePaymentSettingByIdUpdated(@PathVariable String productStorePaymentSettingId) throws Exception {
		DeleteProductStorePaymentSetting command = new DeleteProductStorePaymentSetting(productStorePaymentSettingId);

		try {
			if (((ProductStorePaymentSettingDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductStorePaymentSetting could not be deleted");

	}

}
