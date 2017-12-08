package com.skytala.eCommerce.domain.product.relations.product.control.maintType;

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
import com.skytala.eCommerce.domain.product.relations.product.command.maintType.AddProductMaintType;
import com.skytala.eCommerce.domain.product.relations.product.command.maintType.DeleteProductMaintType;
import com.skytala.eCommerce.domain.product.relations.product.command.maintType.UpdateProductMaintType;
import com.skytala.eCommerce.domain.product.relations.product.event.maintType.ProductMaintTypeAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.maintType.ProductMaintTypeDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.maintType.ProductMaintTypeFound;
import com.skytala.eCommerce.domain.product.relations.product.event.maintType.ProductMaintTypeUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.maintType.ProductMaintTypeMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.maintType.ProductMaintType;
import com.skytala.eCommerce.domain.product.relations.product.query.maintType.FindProductMaintTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/productMaintTypes")
public class ProductMaintTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductMaintTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductMaintType
	 * @return a List with the ProductMaintTypes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findProductMaintTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductMaintTypesBy query = new FindProductMaintTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductMaintType> productMaintTypes =((ProductMaintTypeFound) Scheduler.execute(query).data()).getProductMaintTypes();

		if (productMaintTypes.size() == 1) {
			return ResponseEntity.ok().body(productMaintTypes.get(0));
		}

		return ResponseEntity.ok().body(productMaintTypes);

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
	public ResponseEntity<Object> createProductMaintType(HttpServletRequest request) throws Exception {

		ProductMaintType productMaintTypeToBeAdded = new ProductMaintType();
		try {
			productMaintTypeToBeAdded = ProductMaintTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createProductMaintType(productMaintTypeToBeAdded);

	}

	/**
	 * creates a new ProductMaintType entry in the ofbiz database
	 * 
	 * @param productMaintTypeToBeAdded
	 *            the ProductMaintType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createProductMaintType(@RequestBody ProductMaintType productMaintTypeToBeAdded) throws Exception {

		AddProductMaintType command = new AddProductMaintType(productMaintTypeToBeAdded);
		ProductMaintType productMaintType = ((ProductMaintTypeAdded) Scheduler.execute(command).data()).getAddedProductMaintType();
		
		if (productMaintType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(productMaintType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProductMaintType could not be created.");
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
	public boolean updateProductMaintType(HttpServletRequest request) throws Exception {

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

		ProductMaintType productMaintTypeToBeUpdated = new ProductMaintType();

		try {
			productMaintTypeToBeUpdated = ProductMaintTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProductMaintType(productMaintTypeToBeUpdated, productMaintTypeToBeUpdated.getProductMaintTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ProductMaintType with the specific Id
	 * 
	 * @param productMaintTypeToBeUpdated
	 *            the ProductMaintType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{productMaintTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateProductMaintType(@RequestBody ProductMaintType productMaintTypeToBeUpdated,
			@PathVariable String productMaintTypeId) throws Exception {

		productMaintTypeToBeUpdated.setProductMaintTypeId(productMaintTypeId);

		UpdateProductMaintType command = new UpdateProductMaintType(productMaintTypeToBeUpdated);

		try {
			if(((ProductMaintTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{productMaintTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String productMaintTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productMaintTypeId", productMaintTypeId);
		try {

			Object foundProductMaintType = findProductMaintTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProductMaintType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{productMaintTypeId}")
	public ResponseEntity<Object> deleteProductMaintTypeByIdUpdated(@PathVariable String productMaintTypeId) throws Exception {
		DeleteProductMaintType command = new DeleteProductMaintType(productMaintTypeId);

		try {
			if (((ProductMaintTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductMaintType could not be deleted");

	}

}
