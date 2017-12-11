package com.skytala.eCommerce.domain.product.relations.product.control.storeFinActSetting;

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
import com.skytala.eCommerce.domain.product.relations.product.command.storeFinActSetting.AddProductStoreFinActSetting;
import com.skytala.eCommerce.domain.product.relations.product.command.storeFinActSetting.DeleteProductStoreFinActSetting;
import com.skytala.eCommerce.domain.product.relations.product.command.storeFinActSetting.UpdateProductStoreFinActSetting;
import com.skytala.eCommerce.domain.product.relations.product.event.storeFinActSetting.ProductStoreFinActSettingAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.storeFinActSetting.ProductStoreFinActSettingDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.storeFinActSetting.ProductStoreFinActSettingFound;
import com.skytala.eCommerce.domain.product.relations.product.event.storeFinActSetting.ProductStoreFinActSettingUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.storeFinActSetting.ProductStoreFinActSettingMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.storeFinActSetting.ProductStoreFinActSetting;
import com.skytala.eCommerce.domain.product.relations.product.query.storeFinActSetting.FindProductStoreFinActSettingsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/product/product/productStoreFinActSettings")
public class ProductStoreFinActSettingController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductStoreFinActSettingController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductStoreFinActSetting
	 * @return a List with the ProductStoreFinActSettings
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findProductStoreFinActSettingsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductStoreFinActSettingsBy query = new FindProductStoreFinActSettingsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductStoreFinActSetting> productStoreFinActSettings =((ProductStoreFinActSettingFound) Scheduler.execute(query).data()).getProductStoreFinActSettings();

		if (productStoreFinActSettings.size() == 1) {
			return ResponseEntity.ok().body(productStoreFinActSettings.get(0));
		}

		return ResponseEntity.ok().body(productStoreFinActSettings);

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
	public ResponseEntity<Object> createProductStoreFinActSetting(HttpServletRequest request) throws Exception {

		ProductStoreFinActSetting productStoreFinActSettingToBeAdded = new ProductStoreFinActSetting();
		try {
			productStoreFinActSettingToBeAdded = ProductStoreFinActSettingMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createProductStoreFinActSetting(productStoreFinActSettingToBeAdded);

	}

	/**
	 * creates a new ProductStoreFinActSetting entry in the ofbiz database
	 * 
	 * @param productStoreFinActSettingToBeAdded
	 *            the ProductStoreFinActSetting thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createProductStoreFinActSetting(@RequestBody ProductStoreFinActSetting productStoreFinActSettingToBeAdded) throws Exception {

		AddProductStoreFinActSetting command = new AddProductStoreFinActSetting(productStoreFinActSettingToBeAdded);
		ProductStoreFinActSetting productStoreFinActSetting = ((ProductStoreFinActSettingAdded) Scheduler.execute(command).data()).getAddedProductStoreFinActSetting();
		
		if (productStoreFinActSetting != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(productStoreFinActSetting);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProductStoreFinActSetting could not be created.");
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
	public boolean updateProductStoreFinActSetting(HttpServletRequest request) throws Exception {

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

		ProductStoreFinActSetting productStoreFinActSettingToBeUpdated = new ProductStoreFinActSetting();

		try {
			productStoreFinActSettingToBeUpdated = ProductStoreFinActSettingMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProductStoreFinActSetting(productStoreFinActSettingToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ProductStoreFinActSetting with the specific Id
	 * 
	 * @param productStoreFinActSettingToBeUpdated
	 *            the ProductStoreFinActSetting thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateProductStoreFinActSetting(@RequestBody ProductStoreFinActSetting productStoreFinActSettingToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		productStoreFinActSettingToBeUpdated.setnull(null);

		UpdateProductStoreFinActSetting command = new UpdateProductStoreFinActSetting(productStoreFinActSettingToBeUpdated);

		try {
			if(((ProductStoreFinActSettingUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{productStoreFinActSettingId}")
	public ResponseEntity<Object> findById(@PathVariable String productStoreFinActSettingId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productStoreFinActSettingId", productStoreFinActSettingId);
		try {

			Object foundProductStoreFinActSetting = findProductStoreFinActSettingsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProductStoreFinActSetting);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{productStoreFinActSettingId}")
	public ResponseEntity<Object> deleteProductStoreFinActSettingByIdUpdated(@PathVariable String productStoreFinActSettingId) throws Exception {
		DeleteProductStoreFinActSetting command = new DeleteProductStoreFinActSetting(productStoreFinActSettingId);

		try {
			if (((ProductStoreFinActSettingDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductStoreFinActSetting could not be deleted");

	}

}
