package com.skytala.eCommerce.domain.product.relations.product.control.storeGroupRollup;

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
import com.skytala.eCommerce.domain.product.relations.product.command.storeGroupRollup.AddProductStoreGroupRollup;
import com.skytala.eCommerce.domain.product.relations.product.command.storeGroupRollup.DeleteProductStoreGroupRollup;
import com.skytala.eCommerce.domain.product.relations.product.command.storeGroupRollup.UpdateProductStoreGroupRollup;
import com.skytala.eCommerce.domain.product.relations.product.event.storeGroupRollup.ProductStoreGroupRollupAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.storeGroupRollup.ProductStoreGroupRollupDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.storeGroupRollup.ProductStoreGroupRollupFound;
import com.skytala.eCommerce.domain.product.relations.product.event.storeGroupRollup.ProductStoreGroupRollupUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.storeGroupRollup.ProductStoreGroupRollupMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.storeGroupRollup.ProductStoreGroupRollup;
import com.skytala.eCommerce.domain.product.relations.product.query.storeGroupRollup.FindProductStoreGroupRollupsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/productStoreGroupRollups")
public class ProductStoreGroupRollupController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductStoreGroupRollupController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductStoreGroupRollup
	 * @return a List with the ProductStoreGroupRollups
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findProductStoreGroupRollupsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductStoreGroupRollupsBy query = new FindProductStoreGroupRollupsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductStoreGroupRollup> productStoreGroupRollups =((ProductStoreGroupRollupFound) Scheduler.execute(query).data()).getProductStoreGroupRollups();

		if (productStoreGroupRollups.size() == 1) {
			return ResponseEntity.ok().body(productStoreGroupRollups.get(0));
		}

		return ResponseEntity.ok().body(productStoreGroupRollups);

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
	public ResponseEntity<Object> createProductStoreGroupRollup(HttpServletRequest request) throws Exception {

		ProductStoreGroupRollup productStoreGroupRollupToBeAdded = new ProductStoreGroupRollup();
		try {
			productStoreGroupRollupToBeAdded = ProductStoreGroupRollupMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createProductStoreGroupRollup(productStoreGroupRollupToBeAdded);

	}

	/**
	 * creates a new ProductStoreGroupRollup entry in the ofbiz database
	 * 
	 * @param productStoreGroupRollupToBeAdded
	 *            the ProductStoreGroupRollup thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createProductStoreGroupRollup(@RequestBody ProductStoreGroupRollup productStoreGroupRollupToBeAdded) throws Exception {

		AddProductStoreGroupRollup command = new AddProductStoreGroupRollup(productStoreGroupRollupToBeAdded);
		ProductStoreGroupRollup productStoreGroupRollup = ((ProductStoreGroupRollupAdded) Scheduler.execute(command).data()).getAddedProductStoreGroupRollup();
		
		if (productStoreGroupRollup != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(productStoreGroupRollup);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProductStoreGroupRollup could not be created.");
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
	public boolean updateProductStoreGroupRollup(HttpServletRequest request) throws Exception {

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

		ProductStoreGroupRollup productStoreGroupRollupToBeUpdated = new ProductStoreGroupRollup();

		try {
			productStoreGroupRollupToBeUpdated = ProductStoreGroupRollupMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProductStoreGroupRollup(productStoreGroupRollupToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ProductStoreGroupRollup with the specific Id
	 * 
	 * @param productStoreGroupRollupToBeUpdated
	 *            the ProductStoreGroupRollup thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateProductStoreGroupRollup(@RequestBody ProductStoreGroupRollup productStoreGroupRollupToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		productStoreGroupRollupToBeUpdated.setnull(null);

		UpdateProductStoreGroupRollup command = new UpdateProductStoreGroupRollup(productStoreGroupRollupToBeUpdated);

		try {
			if(((ProductStoreGroupRollupUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{productStoreGroupRollupId}")
	public ResponseEntity<Object> findById(@PathVariable String productStoreGroupRollupId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productStoreGroupRollupId", productStoreGroupRollupId);
		try {

			Object foundProductStoreGroupRollup = findProductStoreGroupRollupsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProductStoreGroupRollup);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{productStoreGroupRollupId}")
	public ResponseEntity<Object> deleteProductStoreGroupRollupByIdUpdated(@PathVariable String productStoreGroupRollupId) throws Exception {
		DeleteProductStoreGroupRollup command = new DeleteProductStoreGroupRollup(productStoreGroupRollupId);

		try {
			if (((ProductStoreGroupRollupDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductStoreGroupRollup could not be deleted");

	}

}
