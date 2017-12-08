package com.skytala.eCommerce.domain.product.relations.product.control.type;

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
import com.skytala.eCommerce.domain.product.relations.product.command.type.AddProductType;
import com.skytala.eCommerce.domain.product.relations.product.command.type.DeleteProductType;
import com.skytala.eCommerce.domain.product.relations.product.command.type.UpdateProductType;
import com.skytala.eCommerce.domain.product.relations.product.event.type.ProductTypeAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.type.ProductTypeDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.type.ProductTypeFound;
import com.skytala.eCommerce.domain.product.relations.product.event.type.ProductTypeUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.type.ProductTypeMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.type.ProductType;
import com.skytala.eCommerce.domain.product.relations.product.query.type.FindProductTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/productTypes")
public class ProductTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductType
	 * @return a List with the ProductTypes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findProductTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductTypesBy query = new FindProductTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductType> productTypes =((ProductTypeFound) Scheduler.execute(query).data()).getProductTypes();

		if (productTypes.size() == 1) {
			return ResponseEntity.ok().body(productTypes.get(0));
		}

		return ResponseEntity.ok().body(productTypes);

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
	public ResponseEntity<Object> createProductType(HttpServletRequest request) throws Exception {

		ProductType productTypeToBeAdded = new ProductType();
		try {
			productTypeToBeAdded = ProductTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createProductType(productTypeToBeAdded);

	}

	/**
	 * creates a new ProductType entry in the ofbiz database
	 * 
	 * @param productTypeToBeAdded
	 *            the ProductType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createProductType(@RequestBody ProductType productTypeToBeAdded) throws Exception {

		AddProductType command = new AddProductType(productTypeToBeAdded);
		ProductType productType = ((ProductTypeAdded) Scheduler.execute(command).data()).getAddedProductType();
		
		if (productType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(productType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProductType could not be created.");
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
	public boolean updateProductType(HttpServletRequest request) throws Exception {

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

		ProductType productTypeToBeUpdated = new ProductType();

		try {
			productTypeToBeUpdated = ProductTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProductType(productTypeToBeUpdated, productTypeToBeUpdated.getProductTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ProductType with the specific Id
	 * 
	 * @param productTypeToBeUpdated
	 *            the ProductType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{productTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateProductType(@RequestBody ProductType productTypeToBeUpdated,
			@PathVariable String productTypeId) throws Exception {

		productTypeToBeUpdated.setProductTypeId(productTypeId);

		UpdateProductType command = new UpdateProductType(productTypeToBeUpdated);

		try {
			if(((ProductTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{productTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String productTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productTypeId", productTypeId);
		try {

			Object foundProductType = findProductTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProductType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{productTypeId}")
	public ResponseEntity<Object> deleteProductTypeByIdUpdated(@PathVariable String productTypeId) throws Exception {
		DeleteProductType command = new DeleteProductType(productTypeId);

		try {
			if (((ProductTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductType could not be deleted");

	}

}
