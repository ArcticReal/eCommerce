package com.skytala.eCommerce.domain.product.relations.product.control.storeGroupType;

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
import com.skytala.eCommerce.domain.product.relations.product.command.storeGroupType.AddProductStoreGroupType;
import com.skytala.eCommerce.domain.product.relations.product.command.storeGroupType.DeleteProductStoreGroupType;
import com.skytala.eCommerce.domain.product.relations.product.command.storeGroupType.UpdateProductStoreGroupType;
import com.skytala.eCommerce.domain.product.relations.product.event.storeGroupType.ProductStoreGroupTypeAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.storeGroupType.ProductStoreGroupTypeDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.storeGroupType.ProductStoreGroupTypeFound;
import com.skytala.eCommerce.domain.product.relations.product.event.storeGroupType.ProductStoreGroupTypeUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.storeGroupType.ProductStoreGroupTypeMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.storeGroupType.ProductStoreGroupType;
import com.skytala.eCommerce.domain.product.relations.product.query.storeGroupType.FindProductStoreGroupTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/productStoreGroupTypes")
public class ProductStoreGroupTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductStoreGroupTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductStoreGroupType
	 * @return a List with the ProductStoreGroupTypes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findProductStoreGroupTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductStoreGroupTypesBy query = new FindProductStoreGroupTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductStoreGroupType> productStoreGroupTypes =((ProductStoreGroupTypeFound) Scheduler.execute(query).data()).getProductStoreGroupTypes();

		if (productStoreGroupTypes.size() == 1) {
			return ResponseEntity.ok().body(productStoreGroupTypes.get(0));
		}

		return ResponseEntity.ok().body(productStoreGroupTypes);

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
	public ResponseEntity<Object> createProductStoreGroupType(HttpServletRequest request) throws Exception {

		ProductStoreGroupType productStoreGroupTypeToBeAdded = new ProductStoreGroupType();
		try {
			productStoreGroupTypeToBeAdded = ProductStoreGroupTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createProductStoreGroupType(productStoreGroupTypeToBeAdded);

	}

	/**
	 * creates a new ProductStoreGroupType entry in the ofbiz database
	 * 
	 * @param productStoreGroupTypeToBeAdded
	 *            the ProductStoreGroupType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createProductStoreGroupType(@RequestBody ProductStoreGroupType productStoreGroupTypeToBeAdded) throws Exception {

		AddProductStoreGroupType command = new AddProductStoreGroupType(productStoreGroupTypeToBeAdded);
		ProductStoreGroupType productStoreGroupType = ((ProductStoreGroupTypeAdded) Scheduler.execute(command).data()).getAddedProductStoreGroupType();
		
		if (productStoreGroupType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(productStoreGroupType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProductStoreGroupType could not be created.");
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
	public boolean updateProductStoreGroupType(HttpServletRequest request) throws Exception {

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

		ProductStoreGroupType productStoreGroupTypeToBeUpdated = new ProductStoreGroupType();

		try {
			productStoreGroupTypeToBeUpdated = ProductStoreGroupTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProductStoreGroupType(productStoreGroupTypeToBeUpdated, productStoreGroupTypeToBeUpdated.getProductStoreGroupTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ProductStoreGroupType with the specific Id
	 * 
	 * @param productStoreGroupTypeToBeUpdated
	 *            the ProductStoreGroupType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{productStoreGroupTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateProductStoreGroupType(@RequestBody ProductStoreGroupType productStoreGroupTypeToBeUpdated,
			@PathVariable String productStoreGroupTypeId) throws Exception {

		productStoreGroupTypeToBeUpdated.setProductStoreGroupTypeId(productStoreGroupTypeId);

		UpdateProductStoreGroupType command = new UpdateProductStoreGroupType(productStoreGroupTypeToBeUpdated);

		try {
			if(((ProductStoreGroupTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{productStoreGroupTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String productStoreGroupTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productStoreGroupTypeId", productStoreGroupTypeId);
		try {

			Object foundProductStoreGroupType = findProductStoreGroupTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProductStoreGroupType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{productStoreGroupTypeId}")
	public ResponseEntity<Object> deleteProductStoreGroupTypeByIdUpdated(@PathVariable String productStoreGroupTypeId) throws Exception {
		DeleteProductStoreGroupType command = new DeleteProductStoreGroupType(productStoreGroupTypeId);

		try {
			if (((ProductStoreGroupTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductStoreGroupType could not be deleted");

	}

}
