package com.skytala.eCommerce.domain.product.relations.product.control.paymentMethodType;

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
import com.skytala.eCommerce.domain.product.relations.product.command.paymentMethodType.AddProductPaymentMethodType;
import com.skytala.eCommerce.domain.product.relations.product.command.paymentMethodType.DeleteProductPaymentMethodType;
import com.skytala.eCommerce.domain.product.relations.product.command.paymentMethodType.UpdateProductPaymentMethodType;
import com.skytala.eCommerce.domain.product.relations.product.event.paymentMethodType.ProductPaymentMethodTypeAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.paymentMethodType.ProductPaymentMethodTypeDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.paymentMethodType.ProductPaymentMethodTypeFound;
import com.skytala.eCommerce.domain.product.relations.product.event.paymentMethodType.ProductPaymentMethodTypeUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.paymentMethodType.ProductPaymentMethodTypeMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.paymentMethodType.ProductPaymentMethodType;
import com.skytala.eCommerce.domain.product.relations.product.query.paymentMethodType.FindProductPaymentMethodTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/product/product/productPaymentMethodTypes")
public class ProductPaymentMethodTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductPaymentMethodTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductPaymentMethodType
	 * @return a List with the ProductPaymentMethodTypes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findProductPaymentMethodTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductPaymentMethodTypesBy query = new FindProductPaymentMethodTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductPaymentMethodType> productPaymentMethodTypes =((ProductPaymentMethodTypeFound) Scheduler.execute(query).data()).getProductPaymentMethodTypes();

		if (productPaymentMethodTypes.size() == 1) {
			return ResponseEntity.ok().body(productPaymentMethodTypes.get(0));
		}

		return ResponseEntity.ok().body(productPaymentMethodTypes);

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
	public ResponseEntity<Object> createProductPaymentMethodType(HttpServletRequest request) throws Exception {

		ProductPaymentMethodType productPaymentMethodTypeToBeAdded = new ProductPaymentMethodType();
		try {
			productPaymentMethodTypeToBeAdded = ProductPaymentMethodTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createProductPaymentMethodType(productPaymentMethodTypeToBeAdded);

	}

	/**
	 * creates a new ProductPaymentMethodType entry in the ofbiz database
	 * 
	 * @param productPaymentMethodTypeToBeAdded
	 *            the ProductPaymentMethodType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createProductPaymentMethodType(@RequestBody ProductPaymentMethodType productPaymentMethodTypeToBeAdded) throws Exception {

		AddProductPaymentMethodType command = new AddProductPaymentMethodType(productPaymentMethodTypeToBeAdded);
		ProductPaymentMethodType productPaymentMethodType = ((ProductPaymentMethodTypeAdded) Scheduler.execute(command).data()).getAddedProductPaymentMethodType();
		
		if (productPaymentMethodType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(productPaymentMethodType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProductPaymentMethodType could not be created.");
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
	public boolean updateProductPaymentMethodType(HttpServletRequest request) throws Exception {

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

		ProductPaymentMethodType productPaymentMethodTypeToBeUpdated = new ProductPaymentMethodType();

		try {
			productPaymentMethodTypeToBeUpdated = ProductPaymentMethodTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProductPaymentMethodType(productPaymentMethodTypeToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ProductPaymentMethodType with the specific Id
	 * 
	 * @param productPaymentMethodTypeToBeUpdated
	 *            the ProductPaymentMethodType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateProductPaymentMethodType(@RequestBody ProductPaymentMethodType productPaymentMethodTypeToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		productPaymentMethodTypeToBeUpdated.setnull(null);

		UpdateProductPaymentMethodType command = new UpdateProductPaymentMethodType(productPaymentMethodTypeToBeUpdated);

		try {
			if(((ProductPaymentMethodTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{productPaymentMethodTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String productPaymentMethodTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productPaymentMethodTypeId", productPaymentMethodTypeId);
		try {

			Object foundProductPaymentMethodType = findProductPaymentMethodTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProductPaymentMethodType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{productPaymentMethodTypeId}")
	public ResponseEntity<Object> deleteProductPaymentMethodTypeByIdUpdated(@PathVariable String productPaymentMethodTypeId) throws Exception {
		DeleteProductPaymentMethodType command = new DeleteProductPaymentMethodType(productPaymentMethodTypeId);

		try {
			if (((ProductPaymentMethodTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductPaymentMethodType could not be deleted");

	}

}
