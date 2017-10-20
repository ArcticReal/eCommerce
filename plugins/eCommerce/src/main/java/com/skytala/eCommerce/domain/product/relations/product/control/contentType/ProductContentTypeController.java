package com.skytala.eCommerce.domain.product.relations.product.control.contentType;

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
import com.skytala.eCommerce.domain.product.relations.product.command.contentType.AddProductContentType;
import com.skytala.eCommerce.domain.product.relations.product.command.contentType.DeleteProductContentType;
import com.skytala.eCommerce.domain.product.relations.product.command.contentType.UpdateProductContentType;
import com.skytala.eCommerce.domain.product.relations.product.event.contentType.ProductContentTypeAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.contentType.ProductContentTypeDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.contentType.ProductContentTypeFound;
import com.skytala.eCommerce.domain.product.relations.product.event.contentType.ProductContentTypeUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.contentType.ProductContentTypeMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.contentType.ProductContentType;
import com.skytala.eCommerce.domain.product.relations.product.query.contentType.FindProductContentTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@CrossOrigin
@RequestMapping("/productContentTypes")
public class ProductContentTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductContentTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductContentType
	 * @return a List with the ProductContentTypes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findProductContentTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductContentTypesBy query = new FindProductContentTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductContentType> productContentTypes =((ProductContentTypeFound) Scheduler.execute(query).data()).getProductContentTypes();

		if (productContentTypes.size() == 1) {
			return ResponseEntity.ok().body(productContentTypes.get(0));
		}

		return ResponseEntity.ok().body(productContentTypes);

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
	public ResponseEntity<Object> createProductContentType(HttpServletRequest request) throws Exception {

		ProductContentType productContentTypeToBeAdded = new ProductContentType();
		try {
			productContentTypeToBeAdded = ProductContentTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createProductContentType(productContentTypeToBeAdded);

	}

	/**
	 * creates a new ProductContentType entry in the ofbiz database
	 * 
	 * @param productContentTypeToBeAdded
	 *            the ProductContentType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createProductContentType(@RequestBody ProductContentType productContentTypeToBeAdded) throws Exception {

		AddProductContentType command = new AddProductContentType(productContentTypeToBeAdded);
		ProductContentType productContentType = ((ProductContentTypeAdded) Scheduler.execute(command).data()).getAddedProductContentType();
		
		if (productContentType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(productContentType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProductContentType could not be created.");
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
	public boolean updateProductContentType(HttpServletRequest request) throws Exception {

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

		ProductContentType productContentTypeToBeUpdated = new ProductContentType();

		try {
			productContentTypeToBeUpdated = ProductContentTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProductContentType(productContentTypeToBeUpdated, productContentTypeToBeUpdated.getProductContentTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ProductContentType with the specific Id
	 * 
	 * @param productContentTypeToBeUpdated
	 *            the ProductContentType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{productContentTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateProductContentType(@RequestBody ProductContentType productContentTypeToBeUpdated,
			@PathVariable String productContentTypeId) throws Exception {

		productContentTypeToBeUpdated.setProductContentTypeId(productContentTypeId);

		UpdateProductContentType command = new UpdateProductContentType(productContentTypeToBeUpdated);

		try {
			if(((ProductContentTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{productContentTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String productContentTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productContentTypeId", productContentTypeId);
		try {

			Object foundProductContentType = findProductContentTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProductContentType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{productContentTypeId}")
	public ResponseEntity<Object> deleteProductContentTypeByIdUpdated(@PathVariable String productContentTypeId) throws Exception {
		DeleteProductContentType command = new DeleteProductContentType(productContentTypeId);

		try {
			if (((ProductContentTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductContentType could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/productContentType/\" plus one of the following: "
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
