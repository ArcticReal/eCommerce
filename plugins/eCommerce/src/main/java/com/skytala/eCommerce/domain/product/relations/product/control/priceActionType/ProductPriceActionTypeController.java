package com.skytala.eCommerce.domain.product.relations.product.control.priceActionType;

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
import com.skytala.eCommerce.domain.product.relations.product.command.priceActionType.AddProductPriceActionType;
import com.skytala.eCommerce.domain.product.relations.product.command.priceActionType.DeleteProductPriceActionType;
import com.skytala.eCommerce.domain.product.relations.product.command.priceActionType.UpdateProductPriceActionType;
import com.skytala.eCommerce.domain.product.relations.product.event.priceActionType.ProductPriceActionTypeAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.priceActionType.ProductPriceActionTypeDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.priceActionType.ProductPriceActionTypeFound;
import com.skytala.eCommerce.domain.product.relations.product.event.priceActionType.ProductPriceActionTypeUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.priceActionType.ProductPriceActionTypeMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.priceActionType.ProductPriceActionType;
import com.skytala.eCommerce.domain.product.relations.product.query.priceActionType.FindProductPriceActionTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/productPriceActionTypes")
public class ProductPriceActionTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductPriceActionTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductPriceActionType
	 * @return a List with the ProductPriceActionTypes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findProductPriceActionTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductPriceActionTypesBy query = new FindProductPriceActionTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductPriceActionType> productPriceActionTypes =((ProductPriceActionTypeFound) Scheduler.execute(query).data()).getProductPriceActionTypes();

		if (productPriceActionTypes.size() == 1) {
			return ResponseEntity.ok().body(productPriceActionTypes.get(0));
		}

		return ResponseEntity.ok().body(productPriceActionTypes);

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
	public ResponseEntity<Object> createProductPriceActionType(HttpServletRequest request) throws Exception {

		ProductPriceActionType productPriceActionTypeToBeAdded = new ProductPriceActionType();
		try {
			productPriceActionTypeToBeAdded = ProductPriceActionTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createProductPriceActionType(productPriceActionTypeToBeAdded);

	}

	/**
	 * creates a new ProductPriceActionType entry in the ofbiz database
	 * 
	 * @param productPriceActionTypeToBeAdded
	 *            the ProductPriceActionType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createProductPriceActionType(@RequestBody ProductPriceActionType productPriceActionTypeToBeAdded) throws Exception {

		AddProductPriceActionType command = new AddProductPriceActionType(productPriceActionTypeToBeAdded);
		ProductPriceActionType productPriceActionType = ((ProductPriceActionTypeAdded) Scheduler.execute(command).data()).getAddedProductPriceActionType();
		
		if (productPriceActionType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(productPriceActionType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProductPriceActionType could not be created.");
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
	public boolean updateProductPriceActionType(HttpServletRequest request) throws Exception {

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

		ProductPriceActionType productPriceActionTypeToBeUpdated = new ProductPriceActionType();

		try {
			productPriceActionTypeToBeUpdated = ProductPriceActionTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProductPriceActionType(productPriceActionTypeToBeUpdated, productPriceActionTypeToBeUpdated.getProductPriceActionTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ProductPriceActionType with the specific Id
	 * 
	 * @param productPriceActionTypeToBeUpdated
	 *            the ProductPriceActionType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{productPriceActionTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateProductPriceActionType(@RequestBody ProductPriceActionType productPriceActionTypeToBeUpdated,
			@PathVariable String productPriceActionTypeId) throws Exception {

		productPriceActionTypeToBeUpdated.setProductPriceActionTypeId(productPriceActionTypeId);

		UpdateProductPriceActionType command = new UpdateProductPriceActionType(productPriceActionTypeToBeUpdated);

		try {
			if(((ProductPriceActionTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{productPriceActionTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String productPriceActionTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productPriceActionTypeId", productPriceActionTypeId);
		try {

			Object foundProductPriceActionType = findProductPriceActionTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProductPriceActionType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{productPriceActionTypeId}")
	public ResponseEntity<Object> deleteProductPriceActionTypeByIdUpdated(@PathVariable String productPriceActionTypeId) throws Exception {
		DeleteProductPriceActionType command = new DeleteProductPriceActionType(productPriceActionTypeId);

		try {
			if (((ProductPriceActionTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductPriceActionType could not be deleted");

	}

}
