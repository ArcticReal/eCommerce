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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	public ResponseEntity<List<ProductConfigConfig>> findProductConfigConfigsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductConfigConfigsBy query = new FindProductConfigConfigsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductConfigConfig> productConfigConfigs =((ProductConfigConfigFound) Scheduler.execute(query).data()).getProductConfigConfigs();

		return ResponseEntity.ok().body(productConfigConfigs);

	}

	/**
	 * creates a new ProductConfigConfig entry in the ofbiz database
	 * 
	 * @param productConfigConfigToBeAdded
	 *            the ProductConfigConfig thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductConfigConfig> createProductConfigConfig(@RequestBody ProductConfigConfig productConfigConfigToBeAdded) throws Exception {

		AddProductConfigConfig command = new AddProductConfigConfig(productConfigConfigToBeAdded);
		ProductConfigConfig productConfigConfig = ((ProductConfigConfigAdded) Scheduler.execute(command).data()).getAddedProductConfigConfig();
		
		if (productConfigConfig != null) 
			return successful(productConfigConfig);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateProductConfigConfig(@RequestBody ProductConfigConfig productConfigConfigToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		productConfigConfigToBeUpdated.setnull(null);

		UpdateProductConfigConfig command = new UpdateProductConfigConfig(productConfigConfigToBeUpdated);

		try {
			if(((ProductConfigConfigUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{productConfigConfigId}")
	public ResponseEntity<ProductConfigConfig> findById(@PathVariable String productConfigConfigId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productConfigConfigId", productConfigConfigId);
		try {

			List<ProductConfigConfig> foundProductConfigConfig = findProductConfigConfigsBy(requestParams).getBody();
			if(foundProductConfigConfig.size()==1){				return successful(foundProductConfigConfig.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{productConfigConfigId}")
	public ResponseEntity<String> deleteProductConfigConfigByIdUpdated(@PathVariable String productConfigConfigId) throws Exception {
		DeleteProductConfigConfig command = new DeleteProductConfigConfig(productConfigConfigId);

		try {
			if (((ProductConfigConfigDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
