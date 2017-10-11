package com.skytala.eCommerce.domain.product.relations.productFeatureIactnType;

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
import com.skytala.eCommerce.domain.product.relations.productFeatureIactnType.command.AddProductFeatureIactnType;
import com.skytala.eCommerce.domain.product.relations.productFeatureIactnType.command.DeleteProductFeatureIactnType;
import com.skytala.eCommerce.domain.product.relations.productFeatureIactnType.command.UpdateProductFeatureIactnType;
import com.skytala.eCommerce.domain.product.relations.productFeatureIactnType.event.ProductFeatureIactnTypeAdded;
import com.skytala.eCommerce.domain.product.relations.productFeatureIactnType.event.ProductFeatureIactnTypeDeleted;
import com.skytala.eCommerce.domain.product.relations.productFeatureIactnType.event.ProductFeatureIactnTypeFound;
import com.skytala.eCommerce.domain.product.relations.productFeatureIactnType.event.ProductFeatureIactnTypeUpdated;
import com.skytala.eCommerce.domain.product.relations.productFeatureIactnType.mapper.ProductFeatureIactnTypeMapper;
import com.skytala.eCommerce.domain.product.relations.productFeatureIactnType.model.ProductFeatureIactnType;
import com.skytala.eCommerce.domain.product.relations.productFeatureIactnType.query.FindProductFeatureIactnTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/productFeatureIactnTypes")
public class ProductFeatureIactnTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductFeatureIactnTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductFeatureIactnType
	 * @return a List with the ProductFeatureIactnTypes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findProductFeatureIactnTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductFeatureIactnTypesBy query = new FindProductFeatureIactnTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductFeatureIactnType> productFeatureIactnTypes =((ProductFeatureIactnTypeFound) Scheduler.execute(query).data()).getProductFeatureIactnTypes();

		if (productFeatureIactnTypes.size() == 1) {
			return ResponseEntity.ok().body(productFeatureIactnTypes.get(0));
		}

		return ResponseEntity.ok().body(productFeatureIactnTypes);

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
	public ResponseEntity<Object> createProductFeatureIactnType(HttpServletRequest request) throws Exception {

		ProductFeatureIactnType productFeatureIactnTypeToBeAdded = new ProductFeatureIactnType();
		try {
			productFeatureIactnTypeToBeAdded = ProductFeatureIactnTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createProductFeatureIactnType(productFeatureIactnTypeToBeAdded);

	}

	/**
	 * creates a new ProductFeatureIactnType entry in the ofbiz database
	 * 
	 * @param productFeatureIactnTypeToBeAdded
	 *            the ProductFeatureIactnType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createProductFeatureIactnType(@RequestBody ProductFeatureIactnType productFeatureIactnTypeToBeAdded) throws Exception {

		AddProductFeatureIactnType command = new AddProductFeatureIactnType(productFeatureIactnTypeToBeAdded);
		ProductFeatureIactnType productFeatureIactnType = ((ProductFeatureIactnTypeAdded) Scheduler.execute(command).data()).getAddedProductFeatureIactnType();
		
		if (productFeatureIactnType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(productFeatureIactnType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProductFeatureIactnType could not be created.");
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
	public boolean updateProductFeatureIactnType(HttpServletRequest request) throws Exception {

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

		ProductFeatureIactnType productFeatureIactnTypeToBeUpdated = new ProductFeatureIactnType();

		try {
			productFeatureIactnTypeToBeUpdated = ProductFeatureIactnTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProductFeatureIactnType(productFeatureIactnTypeToBeUpdated, productFeatureIactnTypeToBeUpdated.getProductFeatureIactnTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ProductFeatureIactnType with the specific Id
	 * 
	 * @param productFeatureIactnTypeToBeUpdated
	 *            the ProductFeatureIactnType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{productFeatureIactnTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateProductFeatureIactnType(@RequestBody ProductFeatureIactnType productFeatureIactnTypeToBeUpdated,
			@PathVariable String productFeatureIactnTypeId) throws Exception {

		productFeatureIactnTypeToBeUpdated.setProductFeatureIactnTypeId(productFeatureIactnTypeId);

		UpdateProductFeatureIactnType command = new UpdateProductFeatureIactnType(productFeatureIactnTypeToBeUpdated);

		try {
			if(((ProductFeatureIactnTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{productFeatureIactnTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String productFeatureIactnTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productFeatureIactnTypeId", productFeatureIactnTypeId);
		try {

			Object foundProductFeatureIactnType = findProductFeatureIactnTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProductFeatureIactnType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{productFeatureIactnTypeId}")
	public ResponseEntity<Object> deleteProductFeatureIactnTypeByIdUpdated(@PathVariable String productFeatureIactnTypeId) throws Exception {
		DeleteProductFeatureIactnType command = new DeleteProductFeatureIactnType(productFeatureIactnTypeId);

		try {
			if (((ProductFeatureIactnTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductFeatureIactnType could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/productFeatureIactnType/\" plus one of the following: "
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
