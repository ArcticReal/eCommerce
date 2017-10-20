package com.skytala.eCommerce.domain.product.relations.product.control.categoryAttribute;

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
import com.skytala.eCommerce.domain.product.relations.product.command.categoryAttribute.AddProductCategoryAttribute;
import com.skytala.eCommerce.domain.product.relations.product.command.categoryAttribute.DeleteProductCategoryAttribute;
import com.skytala.eCommerce.domain.product.relations.product.command.categoryAttribute.UpdateProductCategoryAttribute;
import com.skytala.eCommerce.domain.product.relations.product.event.categoryAttribute.ProductCategoryAttributeAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.categoryAttribute.ProductCategoryAttributeDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.categoryAttribute.ProductCategoryAttributeFound;
import com.skytala.eCommerce.domain.product.relations.product.event.categoryAttribute.ProductCategoryAttributeUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.categoryAttribute.ProductCategoryAttributeMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.categoryAttribute.ProductCategoryAttribute;
import com.skytala.eCommerce.domain.product.relations.product.query.categoryAttribute.FindProductCategoryAttributesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@CrossOrigin
@RequestMapping("/productCategoryAttributes")
public class ProductCategoryAttributeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductCategoryAttributeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductCategoryAttribute
	 * @return a List with the ProductCategoryAttributes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findProductCategoryAttributesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductCategoryAttributesBy query = new FindProductCategoryAttributesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductCategoryAttribute> productCategoryAttributes =((ProductCategoryAttributeFound) Scheduler.execute(query).data()).getProductCategoryAttributes();

		if (productCategoryAttributes.size() == 1) {
			return ResponseEntity.ok().body(productCategoryAttributes.get(0));
		}

		return ResponseEntity.ok().body(productCategoryAttributes);

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
	public ResponseEntity<Object> createProductCategoryAttribute(HttpServletRequest request) throws Exception {

		ProductCategoryAttribute productCategoryAttributeToBeAdded = new ProductCategoryAttribute();
		try {
			productCategoryAttributeToBeAdded = ProductCategoryAttributeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createProductCategoryAttribute(productCategoryAttributeToBeAdded);

	}

	/**
	 * creates a new ProductCategoryAttribute entry in the ofbiz database
	 * 
	 * @param productCategoryAttributeToBeAdded
	 *            the ProductCategoryAttribute thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createProductCategoryAttribute(@RequestBody ProductCategoryAttribute productCategoryAttributeToBeAdded) throws Exception {

		AddProductCategoryAttribute command = new AddProductCategoryAttribute(productCategoryAttributeToBeAdded);
		ProductCategoryAttribute productCategoryAttribute = ((ProductCategoryAttributeAdded) Scheduler.execute(command).data()).getAddedProductCategoryAttribute();
		
		if (productCategoryAttribute != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(productCategoryAttribute);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProductCategoryAttribute could not be created.");
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
	public boolean updateProductCategoryAttribute(HttpServletRequest request) throws Exception {

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

		ProductCategoryAttribute productCategoryAttributeToBeUpdated = new ProductCategoryAttribute();

		try {
			productCategoryAttributeToBeUpdated = ProductCategoryAttributeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProductCategoryAttribute(productCategoryAttributeToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ProductCategoryAttribute with the specific Id
	 * 
	 * @param productCategoryAttributeToBeUpdated
	 *            the ProductCategoryAttribute thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateProductCategoryAttribute(@RequestBody ProductCategoryAttribute productCategoryAttributeToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		productCategoryAttributeToBeUpdated.setnull(null);

		UpdateProductCategoryAttribute command = new UpdateProductCategoryAttribute(productCategoryAttributeToBeUpdated);

		try {
			if(((ProductCategoryAttributeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{productCategoryAttributeId}")
	public ResponseEntity<Object> findById(@PathVariable String productCategoryAttributeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productCategoryAttributeId", productCategoryAttributeId);
		try {

			Object foundProductCategoryAttribute = findProductCategoryAttributesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProductCategoryAttribute);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{productCategoryAttributeId}")
	public ResponseEntity<Object> deleteProductCategoryAttributeByIdUpdated(@PathVariable String productCategoryAttributeId) throws Exception {
		DeleteProductCategoryAttribute command = new DeleteProductCategoryAttribute(productCategoryAttributeId);

		try {
			if (((ProductCategoryAttributeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductCategoryAttribute could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/productCategoryAttribute/\" plus one of the following: "
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
