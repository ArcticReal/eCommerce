package com.skytala.eCommerce.domain.productFeatureApplType;

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
import com.skytala.eCommerce.domain.productFeatureApplType.command.AddProductFeatureApplType;
import com.skytala.eCommerce.domain.productFeatureApplType.command.DeleteProductFeatureApplType;
import com.skytala.eCommerce.domain.productFeatureApplType.command.UpdateProductFeatureApplType;
import com.skytala.eCommerce.domain.productFeatureApplType.event.ProductFeatureApplTypeAdded;
import com.skytala.eCommerce.domain.productFeatureApplType.event.ProductFeatureApplTypeDeleted;
import com.skytala.eCommerce.domain.productFeatureApplType.event.ProductFeatureApplTypeFound;
import com.skytala.eCommerce.domain.productFeatureApplType.event.ProductFeatureApplTypeUpdated;
import com.skytala.eCommerce.domain.productFeatureApplType.mapper.ProductFeatureApplTypeMapper;
import com.skytala.eCommerce.domain.productFeatureApplType.model.ProductFeatureApplType;
import com.skytala.eCommerce.domain.productFeatureApplType.query.FindProductFeatureApplTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/productFeatureApplTypes")
public class ProductFeatureApplTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductFeatureApplTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductFeatureApplType
	 * @return a List with the ProductFeatureApplTypes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findProductFeatureApplTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductFeatureApplTypesBy query = new FindProductFeatureApplTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductFeatureApplType> productFeatureApplTypes =((ProductFeatureApplTypeFound) Scheduler.execute(query).data()).getProductFeatureApplTypes();

		if (productFeatureApplTypes.size() == 1) {
			return ResponseEntity.ok().body(productFeatureApplTypes.get(0));
		}

		return ResponseEntity.ok().body(productFeatureApplTypes);

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
	public ResponseEntity<Object> createProductFeatureApplType(HttpServletRequest request) throws Exception {

		ProductFeatureApplType productFeatureApplTypeToBeAdded = new ProductFeatureApplType();
		try {
			productFeatureApplTypeToBeAdded = ProductFeatureApplTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createProductFeatureApplType(productFeatureApplTypeToBeAdded);

	}

	/**
	 * creates a new ProductFeatureApplType entry in the ofbiz database
	 * 
	 * @param productFeatureApplTypeToBeAdded
	 *            the ProductFeatureApplType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createProductFeatureApplType(@RequestBody ProductFeatureApplType productFeatureApplTypeToBeAdded) throws Exception {

		AddProductFeatureApplType command = new AddProductFeatureApplType(productFeatureApplTypeToBeAdded);
		ProductFeatureApplType productFeatureApplType = ((ProductFeatureApplTypeAdded) Scheduler.execute(command).data()).getAddedProductFeatureApplType();
		
		if (productFeatureApplType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(productFeatureApplType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProductFeatureApplType could not be created.");
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
	public boolean updateProductFeatureApplType(HttpServletRequest request) throws Exception {

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

		ProductFeatureApplType productFeatureApplTypeToBeUpdated = new ProductFeatureApplType();

		try {
			productFeatureApplTypeToBeUpdated = ProductFeatureApplTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProductFeatureApplType(productFeatureApplTypeToBeUpdated, productFeatureApplTypeToBeUpdated.getProductFeatureApplTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ProductFeatureApplType with the specific Id
	 * 
	 * @param productFeatureApplTypeToBeUpdated
	 *            the ProductFeatureApplType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{productFeatureApplTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateProductFeatureApplType(@RequestBody ProductFeatureApplType productFeatureApplTypeToBeUpdated,
			@PathVariable String productFeatureApplTypeId) throws Exception {

		productFeatureApplTypeToBeUpdated.setProductFeatureApplTypeId(productFeatureApplTypeId);

		UpdateProductFeatureApplType command = new UpdateProductFeatureApplType(productFeatureApplTypeToBeUpdated);

		try {
			if(((ProductFeatureApplTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{productFeatureApplTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String productFeatureApplTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productFeatureApplTypeId", productFeatureApplTypeId);
		try {

			Object foundProductFeatureApplType = findProductFeatureApplTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProductFeatureApplType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{productFeatureApplTypeId}")
	public ResponseEntity<Object> deleteProductFeatureApplTypeByIdUpdated(@PathVariable String productFeatureApplTypeId) throws Exception {
		DeleteProductFeatureApplType command = new DeleteProductFeatureApplType(productFeatureApplTypeId);

		try {
			if (((ProductFeatureApplTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductFeatureApplType could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/productFeatureApplType/\" plus one of the following: "
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
