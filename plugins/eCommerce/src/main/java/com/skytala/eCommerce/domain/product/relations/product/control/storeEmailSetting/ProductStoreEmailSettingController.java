package com.skytala.eCommerce.domain.product.relations.product.control.storeEmailSetting;

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
import com.skytala.eCommerce.domain.product.relations.product.command.storeEmailSetting.AddProductStoreEmailSetting;
import com.skytala.eCommerce.domain.product.relations.product.command.storeEmailSetting.DeleteProductStoreEmailSetting;
import com.skytala.eCommerce.domain.product.relations.product.command.storeEmailSetting.UpdateProductStoreEmailSetting;
import com.skytala.eCommerce.domain.product.relations.product.event.storeEmailSetting.ProductStoreEmailSettingAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.storeEmailSetting.ProductStoreEmailSettingDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.storeEmailSetting.ProductStoreEmailSettingFound;
import com.skytala.eCommerce.domain.product.relations.product.event.storeEmailSetting.ProductStoreEmailSettingUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.storeEmailSetting.ProductStoreEmailSettingMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.storeEmailSetting.ProductStoreEmailSetting;
import com.skytala.eCommerce.domain.product.relations.product.query.storeEmailSetting.FindProductStoreEmailSettingsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/product/productStoreEmailSettings")
public class ProductStoreEmailSettingController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductStoreEmailSettingController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductStoreEmailSetting
	 * @return a List with the ProductStoreEmailSettings
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ProductStoreEmailSetting>> findProductStoreEmailSettingsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductStoreEmailSettingsBy query = new FindProductStoreEmailSettingsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductStoreEmailSetting> productStoreEmailSettings =((ProductStoreEmailSettingFound) Scheduler.execute(query).data()).getProductStoreEmailSettings();

		return ResponseEntity.ok().body(productStoreEmailSettings);

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
	public ResponseEntity<ProductStoreEmailSetting> createProductStoreEmailSetting(HttpServletRequest request) throws Exception {

		ProductStoreEmailSetting productStoreEmailSettingToBeAdded = new ProductStoreEmailSetting();
		try {
			productStoreEmailSettingToBeAdded = ProductStoreEmailSettingMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createProductStoreEmailSetting(productStoreEmailSettingToBeAdded);

	}

	/**
	 * creates a new ProductStoreEmailSetting entry in the ofbiz database
	 * 
	 * @param productStoreEmailSettingToBeAdded
	 *            the ProductStoreEmailSetting thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductStoreEmailSetting> createProductStoreEmailSetting(@RequestBody ProductStoreEmailSetting productStoreEmailSettingToBeAdded) throws Exception {

		AddProductStoreEmailSetting command = new AddProductStoreEmailSetting(productStoreEmailSettingToBeAdded);
		ProductStoreEmailSetting productStoreEmailSetting = ((ProductStoreEmailSettingAdded) Scheduler.execute(command).data()).getAddedProductStoreEmailSetting();
		
		if (productStoreEmailSetting != null) 
			return successful(productStoreEmailSetting);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ProductStoreEmailSetting with the specific Id
	 * 
	 * @param productStoreEmailSettingToBeUpdated
	 *            the ProductStoreEmailSetting thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateProductStoreEmailSetting(@RequestBody ProductStoreEmailSetting productStoreEmailSettingToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		productStoreEmailSettingToBeUpdated.setnull(null);

		UpdateProductStoreEmailSetting command = new UpdateProductStoreEmailSetting(productStoreEmailSettingToBeUpdated);

		try {
			if(((ProductStoreEmailSettingUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{productStoreEmailSettingId}")
	public ResponseEntity<ProductStoreEmailSetting> findById(@PathVariable String productStoreEmailSettingId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productStoreEmailSettingId", productStoreEmailSettingId);
		try {

			List<ProductStoreEmailSetting> foundProductStoreEmailSetting = findProductStoreEmailSettingsBy(requestParams).getBody();
			if(foundProductStoreEmailSetting.size()==1){				return successful(foundProductStoreEmailSetting.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{productStoreEmailSettingId}")
	public ResponseEntity<String> deleteProductStoreEmailSettingByIdUpdated(@PathVariable String productStoreEmailSettingId) throws Exception {
		DeleteProductStoreEmailSetting command = new DeleteProductStoreEmailSetting(productStoreEmailSettingId);

		try {
			if (((ProductStoreEmailSettingDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
