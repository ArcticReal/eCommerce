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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	public ResponseEntity<List<ProductStoreFinActSetting>> findProductStoreFinActSettingsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductStoreFinActSettingsBy query = new FindProductStoreFinActSettingsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductStoreFinActSetting> productStoreFinActSettings =((ProductStoreFinActSettingFound) Scheduler.execute(query).data()).getProductStoreFinActSettings();

		return ResponseEntity.ok().body(productStoreFinActSettings);

	}

	/**
	 * creates a new ProductStoreFinActSetting entry in the ofbiz database
	 * 
	 * @param productStoreFinActSettingToBeAdded
	 *            the ProductStoreFinActSetting thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductStoreFinActSetting> createProductStoreFinActSetting(@RequestBody ProductStoreFinActSetting productStoreFinActSettingToBeAdded) throws Exception {

		AddProductStoreFinActSetting command = new AddProductStoreFinActSetting(productStoreFinActSettingToBeAdded);
		ProductStoreFinActSetting productStoreFinActSetting = ((ProductStoreFinActSettingAdded) Scheduler.execute(command).data()).getAddedProductStoreFinActSetting();
		
		if (productStoreFinActSetting != null) 
			return successful(productStoreFinActSetting);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateProductStoreFinActSetting(@RequestBody ProductStoreFinActSetting productStoreFinActSettingToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		productStoreFinActSettingToBeUpdated.setnull(null);

		UpdateProductStoreFinActSetting command = new UpdateProductStoreFinActSetting(productStoreFinActSettingToBeUpdated);

		try {
			if(((ProductStoreFinActSettingUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{productStoreFinActSettingId}")
	public ResponseEntity<ProductStoreFinActSetting> findById(@PathVariable String productStoreFinActSettingId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productStoreFinActSettingId", productStoreFinActSettingId);
		try {

			List<ProductStoreFinActSetting> foundProductStoreFinActSetting = findProductStoreFinActSettingsBy(requestParams).getBody();
			if(foundProductStoreFinActSetting.size()==1){				return successful(foundProductStoreFinActSetting.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{productStoreFinActSettingId}")
	public ResponseEntity<String> deleteProductStoreFinActSettingByIdUpdated(@PathVariable String productStoreFinActSettingId) throws Exception {
		DeleteProductStoreFinActSetting command = new DeleteProductStoreFinActSetting(productStoreFinActSettingId);

		try {
			if (((ProductStoreFinActSettingDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
