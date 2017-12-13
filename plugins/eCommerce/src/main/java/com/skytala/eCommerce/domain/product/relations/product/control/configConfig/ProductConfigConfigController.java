package com.skytala.eCommerce.domain.product.relations.product.control.configConfig;

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
import com.skytala.eCommerce.domain.product.relations.product.command.configConfig.AddProductConfigConfig;
import com.skytala.eCommerce.domain.product.relations.product.command.configConfig.DeleteProductConfigConfig;
import com.skytala.eCommerce.domain.product.relations.product.command.configConfig.UpdateProductConfigConfig;
import com.skytala.eCommerce.domain.product.relations.product.event.configConfig.ProductConfigConfigAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.configConfig.ProductConfigConfigDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.configConfig.ProductConfigConfigFound;
import com.skytala.eCommerce.domain.product.relations.product.event.configConfig.ProductConfigConfigUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.configConfig.ProductConfigConfigMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.configConfig.ProductConfigConfig;
import com.skytala.eCommerce.domain.product.relations.product.query.configConfig.FindProductConfigConfigsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/product/product/productConfigConfigs")
public class ProductConfigConfigController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductConfigConfigController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductConfigConfig
	 * @return a List with the ProductConfigConfigs
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findProductConfigConfigsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductConfigConfigsBy query = new FindProductConfigConfigsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductConfigConfig> productConfigConfigs =((ProductConfigConfigFound) Scheduler.execute(query).data()).getProductConfigConfigs();

		if (productConfigConfigs.size() == 1) {
			return ResponseEntity.ok().body(productConfigConfigs.get(0));
		}

		return ResponseEntity.ok().body(productConfigConfigs);

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
	public ResponseEntity<Object> createProductConfigConfig(HttpServletRequest request) throws Exception {

		ProductConfigConfig productConfigConfigToBeAdded = new ProductConfigConfig();
		try {
			productConfigConfigToBeAdded = ProductConfigConfigMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createProductConfigConfig(productConfigConfigToBeAdded);

	}

	/**
	 * creates a new ProductConfigConfig entry in the ofbiz database
	 * 
	 * @param productConfigConfigToBeAdded
	 *            the ProductConfigConfig thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createProductConfigConfig(@RequestBody ProductConfigConfig productConfigConfigToBeAdded) throws Exception {

		AddProductConfigConfig command = new AddProductConfigConfig(productConfigConfigToBeAdded);
		ProductConfigConfig productConfigConfig = ((ProductConfigConfigAdded) Scheduler.execute(command).data()).getAddedProductConfigConfig();
		
		if (productConfigConfig != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(productConfigConfig);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProductConfigConfig could not be created.");
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
	public boolean updateProductConfigConfig(HttpServletRequest request) throws Exception {

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

		ProductConfigConfig productConfigConfigToBeUpdated = new ProductConfigConfig();

		try {
			productConfigConfigToBeUpdated = ProductConfigConfigMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProductConfigConfig(productConfigConfigToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ProductConfigConfig with the specific Id
	 * 
	 * @param productConfigConfigToBeUpdated
	 *            the ProductConfigConfig thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateProductConfigConfig(@RequestBody ProductConfigConfig productConfigConfigToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		productConfigConfigToBeUpdated.setnull(null);

		UpdateProductConfigConfig command = new UpdateProductConfigConfig(productConfigConfigToBeUpdated);

		try {
			if(((ProductConfigConfigUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{productConfigConfigId}")
	public ResponseEntity<Object> findById(@PathVariable String productConfigConfigId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productConfigConfigId", productConfigConfigId);
		try {

			Object foundProductConfigConfig = findProductConfigConfigsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProductConfigConfig);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{productConfigConfigId}")
	public ResponseEntity<Object> deleteProductConfigConfigByIdUpdated(@PathVariable String productConfigConfigId) throws Exception {
		DeleteProductConfigConfig command = new DeleteProductConfigConfig(productConfigConfigId);

		try {
			if (((ProductConfigConfigDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductConfigConfig could not be deleted");

	}

}
