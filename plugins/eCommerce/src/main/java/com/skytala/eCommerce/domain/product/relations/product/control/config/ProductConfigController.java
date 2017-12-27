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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	@GetMapping("/find")
	public ResponseEntity<List<ProductConfig>> findProductConfigsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductConfigsBy query = new FindProductConfigsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductConfig> productConfigs =((ProductConfigFound) Scheduler.execute(query).data()).getProductConfigs();

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
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<ProductConfig> createProductConfig(HttpServletRequest request) throws Exception {

		ProductConfig productConfigToBeAdded = new ProductConfig();
		try {
			productConfigToBeAdded = ProductConfigMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
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
	public ResponseEntity<ProductConfig> createProductConfig(@RequestBody ProductConfig productConfigToBeAdded) throws Exception {

		AddProductConfig command = new AddProductConfig(productConfigToBeAdded);
		ProductConfig productConfig = ((ProductConfigAdded) Scheduler.execute(command).data()).getAddedProductConfig();
		
		if (productConfig != null) 
			return successful(productConfig);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateProductConfig(@RequestBody ProductConfig productConfigToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		productConfigToBeUpdated.setnull(null);

		UpdateProductConfig command = new UpdateProductConfig(productConfigToBeUpdated);

		try {
			if(((ProductConfigUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{productConfigId}")
	public ResponseEntity<ProductConfig> findById(@PathVariable String productConfigId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productConfigId", productConfigId);
		try {

			List<ProductConfig> foundProductConfig = findProductConfigsBy(requestParams).getBody();
			if(foundProductConfig.size()==1){				return successful(foundProductConfig.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{productConfigId}")
	public ResponseEntity<String> deleteProductConfigByIdUpdated(@PathVariable String productConfigId) throws Exception {
		DeleteProductConfig command = new DeleteProductConfig(productConfigId);

		try {
			if (((ProductConfigDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
