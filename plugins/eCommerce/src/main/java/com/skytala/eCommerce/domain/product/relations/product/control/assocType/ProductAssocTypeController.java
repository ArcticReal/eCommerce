package com.skytala.eCommerce.domain.product.relations.product.control.assocType;

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
import com.skytala.eCommerce.domain.product.relations.product.command.assocType.AddProductAssocType;
import com.skytala.eCommerce.domain.product.relations.product.command.assocType.DeleteProductAssocType;
import com.skytala.eCommerce.domain.product.relations.product.command.assocType.UpdateProductAssocType;
import com.skytala.eCommerce.domain.product.relations.product.event.assocType.ProductAssocTypeAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.assocType.ProductAssocTypeDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.assocType.ProductAssocTypeFound;
import com.skytala.eCommerce.domain.product.relations.product.event.assocType.ProductAssocTypeUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.assocType.ProductAssocTypeMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.assocType.ProductAssocType;
import com.skytala.eCommerce.domain.product.relations.product.query.assocType.FindProductAssocTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/productAssocTypes")
public class ProductAssocTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductAssocTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductAssocType
	 * @return a List with the ProductAssocTypes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findProductAssocTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductAssocTypesBy query = new FindProductAssocTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductAssocType> productAssocTypes =((ProductAssocTypeFound) Scheduler.execute(query).data()).getProductAssocTypes();

		if (productAssocTypes.size() == 1) {
			return ResponseEntity.ok().body(productAssocTypes.get(0));
		}

		return ResponseEntity.ok().body(productAssocTypes);

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
	public ResponseEntity<Object> createProductAssocType(HttpServletRequest request) throws Exception {

		ProductAssocType productAssocTypeToBeAdded = new ProductAssocType();
		try {
			productAssocTypeToBeAdded = ProductAssocTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createProductAssocType(productAssocTypeToBeAdded);

	}

	/**
	 * creates a new ProductAssocType entry in the ofbiz database
	 * 
	 * @param productAssocTypeToBeAdded
	 *            the ProductAssocType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createProductAssocType(@RequestBody ProductAssocType productAssocTypeToBeAdded) throws Exception {

		AddProductAssocType command = new AddProductAssocType(productAssocTypeToBeAdded);
		ProductAssocType productAssocType = ((ProductAssocTypeAdded) Scheduler.execute(command).data()).getAddedProductAssocType();
		
		if (productAssocType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(productAssocType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProductAssocType could not be created.");
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
	public boolean updateProductAssocType(HttpServletRequest request) throws Exception {

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

		ProductAssocType productAssocTypeToBeUpdated = new ProductAssocType();

		try {
			productAssocTypeToBeUpdated = ProductAssocTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProductAssocType(productAssocTypeToBeUpdated, productAssocTypeToBeUpdated.getProductAssocTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ProductAssocType with the specific Id
	 * 
	 * @param productAssocTypeToBeUpdated
	 *            the ProductAssocType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{productAssocTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateProductAssocType(@RequestBody ProductAssocType productAssocTypeToBeUpdated,
			@PathVariable String productAssocTypeId) throws Exception {

		productAssocTypeToBeUpdated.setProductAssocTypeId(productAssocTypeId);

		UpdateProductAssocType command = new UpdateProductAssocType(productAssocTypeToBeUpdated);

		try {
			if(((ProductAssocTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{productAssocTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String productAssocTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productAssocTypeId", productAssocTypeId);
		try {

			Object foundProductAssocType = findProductAssocTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProductAssocType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{productAssocTypeId}")
	public ResponseEntity<Object> deleteProductAssocTypeByIdUpdated(@PathVariable String productAssocTypeId) throws Exception {
		DeleteProductAssocType command = new DeleteProductAssocType(productAssocTypeId);

		try {
			if (((ProductAssocTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductAssocType could not be deleted");

	}

}
