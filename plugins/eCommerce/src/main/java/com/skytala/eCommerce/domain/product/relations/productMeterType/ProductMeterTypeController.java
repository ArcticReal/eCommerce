package com.skytala.eCommerce.domain.product.relations.productMeterType;

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
import com.skytala.eCommerce.domain.product.relations.productMeterType.command.AddProductMeterType;
import com.skytala.eCommerce.domain.product.relations.productMeterType.command.DeleteProductMeterType;
import com.skytala.eCommerce.domain.product.relations.productMeterType.command.UpdateProductMeterType;
import com.skytala.eCommerce.domain.product.relations.productMeterType.event.ProductMeterTypeAdded;
import com.skytala.eCommerce.domain.product.relations.productMeterType.event.ProductMeterTypeDeleted;
import com.skytala.eCommerce.domain.product.relations.productMeterType.event.ProductMeterTypeFound;
import com.skytala.eCommerce.domain.product.relations.productMeterType.event.ProductMeterTypeUpdated;
import com.skytala.eCommerce.domain.product.relations.productMeterType.mapper.ProductMeterTypeMapper;
import com.skytala.eCommerce.domain.product.relations.productMeterType.model.ProductMeterType;
import com.skytala.eCommerce.domain.product.relations.productMeterType.query.FindProductMeterTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/productMeterTypes")
public class ProductMeterTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductMeterTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductMeterType
	 * @return a List with the ProductMeterTypes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findProductMeterTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductMeterTypesBy query = new FindProductMeterTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductMeterType> productMeterTypes =((ProductMeterTypeFound) Scheduler.execute(query).data()).getProductMeterTypes();

		if (productMeterTypes.size() == 1) {
			return ResponseEntity.ok().body(productMeterTypes.get(0));
		}

		return ResponseEntity.ok().body(productMeterTypes);

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
	public ResponseEntity<Object> createProductMeterType(HttpServletRequest request) throws Exception {

		ProductMeterType productMeterTypeToBeAdded = new ProductMeterType();
		try {
			productMeterTypeToBeAdded = ProductMeterTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createProductMeterType(productMeterTypeToBeAdded);

	}

	/**
	 * creates a new ProductMeterType entry in the ofbiz database
	 * 
	 * @param productMeterTypeToBeAdded
	 *            the ProductMeterType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createProductMeterType(@RequestBody ProductMeterType productMeterTypeToBeAdded) throws Exception {

		AddProductMeterType command = new AddProductMeterType(productMeterTypeToBeAdded);
		ProductMeterType productMeterType = ((ProductMeterTypeAdded) Scheduler.execute(command).data()).getAddedProductMeterType();
		
		if (productMeterType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(productMeterType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProductMeterType could not be created.");
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
	public boolean updateProductMeterType(HttpServletRequest request) throws Exception {

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

		ProductMeterType productMeterTypeToBeUpdated = new ProductMeterType();

		try {
			productMeterTypeToBeUpdated = ProductMeterTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProductMeterType(productMeterTypeToBeUpdated, productMeterTypeToBeUpdated.getProductMeterTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ProductMeterType with the specific Id
	 * 
	 * @param productMeterTypeToBeUpdated
	 *            the ProductMeterType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{productMeterTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateProductMeterType(@RequestBody ProductMeterType productMeterTypeToBeUpdated,
			@PathVariable String productMeterTypeId) throws Exception {

		productMeterTypeToBeUpdated.setProductMeterTypeId(productMeterTypeId);

		UpdateProductMeterType command = new UpdateProductMeterType(productMeterTypeToBeUpdated);

		try {
			if(((ProductMeterTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{productMeterTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String productMeterTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productMeterTypeId", productMeterTypeId);
		try {

			Object foundProductMeterType = findProductMeterTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProductMeterType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{productMeterTypeId}")
	public ResponseEntity<Object> deleteProductMeterTypeByIdUpdated(@PathVariable String productMeterTypeId) throws Exception {
		DeleteProductMeterType command = new DeleteProductMeterType(productMeterTypeId);

		try {
			if (((ProductMeterTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductMeterType could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/productMeterType/\" plus one of the following: "
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
