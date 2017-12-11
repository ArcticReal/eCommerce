package com.skytala.eCommerce.domain.product.relations.product.control.config;

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
import com.skytala.eCommerce.domain.product.relations.product.command.config.AddProductConfig;
import com.skytala.eCommerce.domain.product.relations.product.command.config.DeleteProductConfig;
import com.skytala.eCommerce.domain.product.relations.product.command.config.UpdateProductConfig;
import com.skytala.eCommerce.domain.product.relations.product.event.config.ProductConfigAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.config.ProductConfigDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.config.ProductConfigFound;
import com.skytala.eCommerce.domain.product.relations.product.event.config.ProductConfigUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.config.ProductConfigMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.config.ProductConfig;
import com.skytala.eCommerce.domain.product.relations.product.query.config.FindProductConfigsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/product/product/productConfigs")
public class ProductConfigController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductConfigController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductConfig
	 * @return a List with the ProductConfigs
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findProductConfigsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductConfigsBy query = new FindProductConfigsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductConfig> productConfigs =((ProductConfigFound) Scheduler.execute(query).data()).getProductConfigs();

		if (productConfigs.size() == 1) {
			return ResponseEntity.ok().body(productConfigs.get(0));
		}

		return ResponseEntity.ok().body(productConfigs);

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
	public ResponseEntity<Object> createProductConfig(HttpServletRequest request) throws Exception {

		ProductConfig productConfigToBeAdded = new ProductConfig();
		try {
			productConfigToBeAdded = ProductConfigMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createProductConfig(productConfigToBeAdded);

	}

	/**
	 * creates a new ProductConfig entry in the ofbiz database
	 * 
	 * @param productConfigToBeAdded
	 *            the ProductConfig thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createProductConfig(@RequestBody ProductConfig productConfigToBeAdded) throws Exception {

		AddProductConfig command = new AddProductConfig(productConfigToBeAdded);
		ProductConfig productConfig = ((ProductConfigAdded) Scheduler.execute(command).data()).getAddedProductConfig();
		
		if (productConfig != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(productConfig);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProductConfig could not be created.");
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
	public boolean updateProductConfig(HttpServletRequest request) throws Exception {

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

		ProductConfig productConfigToBeUpdated = new ProductConfig();

		try {
			productConfigToBeUpdated = ProductConfigMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProductConfig(productConfigToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ProductConfig with the specific Id
	 * 
	 * @param productConfigToBeUpdated
	 *            the ProductConfig thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateProductConfig(@RequestBody ProductConfig productConfigToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		productConfigToBeUpdated.setnull(null);

		UpdateProductConfig command = new UpdateProductConfig(productConfigToBeUpdated);

		try {
			if(((ProductConfigUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{productConfigId}")
	public ResponseEntity<Object> findById(@PathVariable String productConfigId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productConfigId", productConfigId);
		try {

			Object foundProductConfig = findProductConfigsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProductConfig);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{productConfigId}")
	public ResponseEntity<Object> deleteProductConfigByIdUpdated(@PathVariable String productConfigId) throws Exception {
		DeleteProductConfig command = new DeleteProductConfig(productConfigId);

		try {
			if (((ProductConfigDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductConfig could not be deleted");

	}

}
