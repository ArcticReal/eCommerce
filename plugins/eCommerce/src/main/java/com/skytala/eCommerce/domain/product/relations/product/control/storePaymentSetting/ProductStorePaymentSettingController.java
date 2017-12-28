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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	public ResponseEntity<List<ProductStorePaymentSetting>> findProductStorePaymentSettingsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductStorePaymentSettingsBy query = new FindProductStorePaymentSettingsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductStorePaymentSetting> productStorePaymentSettings =((ProductStorePaymentSettingFound) Scheduler.execute(query).data()).getProductStorePaymentSettings();

		return ResponseEntity.ok().body(productStorePaymentSettings);

	}

	/**
	 * creates a new ProductStorePaymentSetting entry in the ofbiz database
	 * 
	 * @param productStorePaymentSettingToBeAdded
	 *            the ProductStorePaymentSetting thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductStorePaymentSetting> createProductStorePaymentSetting(@RequestBody ProductStorePaymentSetting productStorePaymentSettingToBeAdded) throws Exception {

		AddProductStorePaymentSetting command = new AddProductStorePaymentSetting(productStorePaymentSettingToBeAdded);
		ProductStorePaymentSetting productStorePaymentSetting = ((ProductStorePaymentSettingAdded) Scheduler.execute(command).data()).getAddedProductStorePaymentSetting();
		
		if (productStorePaymentSetting != null) 
			return successful(productStorePaymentSetting);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateProductStorePaymentSetting(@RequestBody ProductStorePaymentSetting productStorePaymentSettingToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		productStorePaymentSettingToBeUpdated.setnull(null);

		UpdateProductStorePaymentSetting command = new UpdateProductStorePaymentSetting(productStorePaymentSettingToBeUpdated);

		try {
			if(((ProductStorePaymentSettingUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{productStorePaymentSettingId}")
	public ResponseEntity<ProductStorePaymentSetting> findById(@PathVariable String productStorePaymentSettingId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productStorePaymentSettingId", productStorePaymentSettingId);
		try {

			List<ProductStorePaymentSetting> foundProductStorePaymentSetting = findProductStorePaymentSettingsBy(requestParams).getBody();
			if(foundProductStorePaymentSetting.size()==1){				return successful(foundProductStorePaymentSetting.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{productStorePaymentSettingId}")
	public ResponseEntity<String> deleteProductStorePaymentSettingByIdUpdated(@PathVariable String productStorePaymentSettingId) throws Exception {
		DeleteProductStorePaymentSetting command = new DeleteProductStorePaymentSetting(productStorePaymentSettingId);

		try {
			if (((ProductStorePaymentSettingDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
