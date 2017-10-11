package com.skytala.eCommerce.domain.product.relations.productStoreEmailSetting;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Splitter;
import com.skytala.eCommerce.domain.product.relations.productStoreEmailSetting.command.AddProductStoreEmailSetting;
import com.skytala.eCommerce.domain.product.relations.productStoreEmailSetting.command.DeleteProductStoreEmailSetting;
import com.skytala.eCommerce.domain.product.relations.productStoreEmailSetting.command.UpdateProductStoreEmailSetting;
import com.skytala.eCommerce.domain.product.relations.productStoreEmailSetting.event.ProductStoreEmailSettingAdded;
import com.skytala.eCommerce.domain.product.relations.productStoreEmailSetting.event.ProductStoreEmailSettingDeleted;
import com.skytala.eCommerce.domain.product.relations.productStoreEmailSetting.event.ProductStoreEmailSettingFound;
import com.skytala.eCommerce.domain.product.relations.productStoreEmailSetting.event.ProductStoreEmailSettingUpdated;
import com.skytala.eCommerce.domain.product.relations.productStoreEmailSetting.mapper.ProductStoreEmailSettingMapper;
import com.skytala.eCommerce.domain.product.relations.productStoreEmailSetting.model.ProductStoreEmailSetting;
import com.skytala.eCommerce.domain.product.relations.productStoreEmailSetting.query.FindProductStoreEmailSettingsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/productStoreEmailSettings")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findProductStoreEmailSettingsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductStoreEmailSettingsBy query = new FindProductStoreEmailSettingsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductStoreEmailSetting> productStoreEmailSettings =((ProductStoreEmailSettingFound) Scheduler.execute(query).data()).getProductStoreEmailSettings();

		if (productStoreEmailSettings.size() == 1) {
			return ResponseEntity.ok().body(productStoreEmailSettings.get(0));
		}

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
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createProductStoreEmailSetting(HttpServletRequest request) throws Exception {

		ProductStoreEmailSetting productStoreEmailSettingToBeAdded = new ProductStoreEmailSetting();
		try {
			productStoreEmailSettingToBeAdded = ProductStoreEmailSettingMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
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
	public ResponseEntity<Object> createProductStoreEmailSetting(@RequestBody ProductStoreEmailSetting productStoreEmailSettingToBeAdded) throws Exception {

		AddProductStoreEmailSetting command = new AddProductStoreEmailSetting(productStoreEmailSettingToBeAdded);
		ProductStoreEmailSetting productStoreEmailSetting = ((ProductStoreEmailSettingAdded) Scheduler.execute(command).data()).getAddedProductStoreEmailSetting();
		
		if (productStoreEmailSetting != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(productStoreEmailSetting);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProductStoreEmailSetting could not be created.");
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
	public boolean updateProductStoreEmailSetting(HttpServletRequest request) throws Exception {

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

		ProductStoreEmailSetting productStoreEmailSettingToBeUpdated = new ProductStoreEmailSetting();

		try {
			productStoreEmailSettingToBeUpdated = ProductStoreEmailSettingMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProductStoreEmailSetting(productStoreEmailSettingToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateProductStoreEmailSetting(@RequestBody ProductStoreEmailSetting productStoreEmailSettingToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		productStoreEmailSettingToBeUpdated.setnull(null);

		UpdateProductStoreEmailSetting command = new UpdateProductStoreEmailSetting(productStoreEmailSettingToBeUpdated);

		try {
			if(((ProductStoreEmailSettingUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{productStoreEmailSettingId}")
	public ResponseEntity<Object> findById(@PathVariable String productStoreEmailSettingId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productStoreEmailSettingId", productStoreEmailSettingId);
		try {

			Object foundProductStoreEmailSetting = findProductStoreEmailSettingsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProductStoreEmailSetting);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{productStoreEmailSettingId}")
	public ResponseEntity<Object> deleteProductStoreEmailSettingByIdUpdated(@PathVariable String productStoreEmailSettingId) throws Exception {
		DeleteProductStoreEmailSetting command = new DeleteProductStoreEmailSetting(productStoreEmailSettingId);

		try {
			if (((ProductStoreEmailSettingDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductStoreEmailSetting could not be deleted");

	}

	@RequestMapping(value = (" ** "))
	public ResponseEntity<Object> returnErrorPage(HttpServletRequest request) {

		String usedUri = request.getRequestURI();
		String[] splittedString = usedUri.split("/");

		String usedRequest = splittedString[splittedString.length - 1];

		if (validRequests.containsKey(usedRequest)) {
			String returnVal = "Error: request method " + request.getMethod() + " not allowed for \"" + usedUri
					+ "\"!\n" + "Please use " + validRequests.get(usedRequest) + "!";

			return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(returnVal);
		}

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/productStoreEmailSetting/\" plus one of the following: "
				+ "";

		Set<String> keySet = validRequests.keySet();
		Iterator<String> it = keySet.iterator();

		while (it.hasNext()) {
			returnVal += "\"" + it.next() + "\"";
			if (it.hasNext())
				returnVal += ", ";
		}

		returnVal += "!";

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnVal);

	}
}
