package com.skytala.eCommerce.domain.productFeatureType;

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
import com.skytala.eCommerce.domain.productFeatureType.command.AddProductFeatureType;
import com.skytala.eCommerce.domain.productFeatureType.command.DeleteProductFeatureType;
import com.skytala.eCommerce.domain.productFeatureType.command.UpdateProductFeatureType;
import com.skytala.eCommerce.domain.productFeatureType.event.ProductFeatureTypeAdded;
import com.skytala.eCommerce.domain.productFeatureType.event.ProductFeatureTypeDeleted;
import com.skytala.eCommerce.domain.productFeatureType.event.ProductFeatureTypeFound;
import com.skytala.eCommerce.domain.productFeatureType.event.ProductFeatureTypeUpdated;
import com.skytala.eCommerce.domain.productFeatureType.mapper.ProductFeatureTypeMapper;
import com.skytala.eCommerce.domain.productFeatureType.model.ProductFeatureType;
import com.skytala.eCommerce.domain.productFeatureType.query.FindProductFeatureTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/productFeatureTypes")
public class ProductFeatureTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductFeatureTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductFeatureType
	 * @return a List with the ProductFeatureTypes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findProductFeatureTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductFeatureTypesBy query = new FindProductFeatureTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductFeatureType> productFeatureTypes =((ProductFeatureTypeFound) Scheduler.execute(query).data()).getProductFeatureTypes();

		if (productFeatureTypes.size() == 1) {
			return ResponseEntity.ok().body(productFeatureTypes.get(0));
		}

		return ResponseEntity.ok().body(productFeatureTypes);

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
	public ResponseEntity<Object> createProductFeatureType(HttpServletRequest request) throws Exception {

		ProductFeatureType productFeatureTypeToBeAdded = new ProductFeatureType();
		try {
			productFeatureTypeToBeAdded = ProductFeatureTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createProductFeatureType(productFeatureTypeToBeAdded);

	}

	/**
	 * creates a new ProductFeatureType entry in the ofbiz database
	 * 
	 * @param productFeatureTypeToBeAdded
	 *            the ProductFeatureType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createProductFeatureType(@RequestBody ProductFeatureType productFeatureTypeToBeAdded) throws Exception {

		AddProductFeatureType command = new AddProductFeatureType(productFeatureTypeToBeAdded);
		ProductFeatureType productFeatureType = ((ProductFeatureTypeAdded) Scheduler.execute(command).data()).getAddedProductFeatureType();
		
		if (productFeatureType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(productFeatureType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProductFeatureType could not be created.");
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
	public boolean updateProductFeatureType(HttpServletRequest request) throws Exception {

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

		ProductFeatureType productFeatureTypeToBeUpdated = new ProductFeatureType();

		try {
			productFeatureTypeToBeUpdated = ProductFeatureTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProductFeatureType(productFeatureTypeToBeUpdated, productFeatureTypeToBeUpdated.getProductFeatureTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ProductFeatureType with the specific Id
	 * 
	 * @param productFeatureTypeToBeUpdated
	 *            the ProductFeatureType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{productFeatureTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateProductFeatureType(@RequestBody ProductFeatureType productFeatureTypeToBeUpdated,
			@PathVariable String productFeatureTypeId) throws Exception {

		productFeatureTypeToBeUpdated.setProductFeatureTypeId(productFeatureTypeId);

		UpdateProductFeatureType command = new UpdateProductFeatureType(productFeatureTypeToBeUpdated);

		try {
			if(((ProductFeatureTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{productFeatureTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String productFeatureTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productFeatureTypeId", productFeatureTypeId);
		try {

			Object foundProductFeatureType = findProductFeatureTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProductFeatureType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{productFeatureTypeId}")
	public ResponseEntity<Object> deleteProductFeatureTypeByIdUpdated(@PathVariable String productFeatureTypeId) throws Exception {
		DeleteProductFeatureType command = new DeleteProductFeatureType(productFeatureTypeId);

		try {
			if (((ProductFeatureTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductFeatureType could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/productFeatureType/\" plus one of the following: "
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