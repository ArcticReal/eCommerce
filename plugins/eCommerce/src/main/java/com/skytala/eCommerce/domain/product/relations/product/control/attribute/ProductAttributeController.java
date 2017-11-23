package com.skytala.eCommerce.domain.product.relations.product.control.attribute;

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
import com.skytala.eCommerce.domain.product.relations.product.command.attribute.AddProductAttribute;
import com.skytala.eCommerce.domain.product.relations.product.command.attribute.DeleteProductAttribute;
import com.skytala.eCommerce.domain.product.relations.product.command.attribute.UpdateProductAttribute;
import com.skytala.eCommerce.domain.product.relations.product.event.attribute.ProductAttributeAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.attribute.ProductAttributeDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.attribute.ProductAttributeFound;
import com.skytala.eCommerce.domain.product.relations.product.event.attribute.ProductAttributeUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.attribute.ProductAttributeMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.attribute.ProductAttribute;
import com.skytala.eCommerce.domain.product.relations.product.query.attribute.FindProductAttributesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/productAttributes")
public class ProductAttributeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductAttributeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductAttribute
	 * @return a List with the ProductAttributes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<List<ProductAttribute>> findProductAttributesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductAttributesBy query = new FindProductAttributesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductAttribute> productAttributes =((ProductAttributeFound) Scheduler.execute(query).data()).getProductAttributes();

		return ResponseEntity.ok().body(productAttributes);

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
	public ResponseEntity<ProductAttribute> createProductAttribute(HttpServletRequest request) throws Exception {

		ProductAttribute productAttributeToBeAdded = new ProductAttribute();
		try {
			productAttributeToBeAdded = ProductAttributeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}

		return this.createProductAttribute(productAttributeToBeAdded);

	}

	/**
	 * creates a new ProductAttribute entry in the ofbiz database
	 * 
	 * @param productAttributeToBeAdded
	 *            the ProductAttribute thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductAttribute> createProductAttribute(@RequestBody ProductAttribute productAttributeToBeAdded) throws Exception {

		AddProductAttribute command = new AddProductAttribute(productAttributeToBeAdded);
		ProductAttribute productAttribute = ((ProductAttributeAdded) Scheduler.execute(command).data()).getAddedProductAttribute();
		
		if (productAttribute != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(productAttribute);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body(null);
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
	public boolean updateProductAttribute(HttpServletRequest request) throws Exception {

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

		ProductAttribute productAttributeToBeUpdated = new ProductAttribute();

		try {
			productAttributeToBeUpdated = ProductAttributeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProductAttribute(productAttributeToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ProductAttribute with the specific Id
	 * 
	 * @param productAttributeToBeUpdated
	 *            the ProductAttribute thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{productId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductAttribute> updateProductAttribute(@RequestBody ProductAttribute productAttributeToBeUpdated,
			@PathVariable String productId) throws Exception {



		UpdateProductAttribute command = new UpdateProductAttribute(productAttributeToBeUpdated);

		try {
			if(((ProductAttributeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return createProductAttribute(productAttributeToBeUpdated);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{productAttributeId}")
	public ResponseEntity<List<ProductAttribute>> findById(@PathVariable String productAttributeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productAttributeId", productAttributeId);
		try {

			List<ProductAttribute> foundProductAttribute = findProductAttributesBy(requestParams).getBody();

			return ResponseEntity.status(HttpStatus.OK).body(foundProductAttribute);

		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{productAttributeId}")
	public ResponseEntity<Object> deleteProductAttributeByIdUpdated(@PathVariable String productAttributeId) throws Exception {
		DeleteProductAttribute command = new DeleteProductAttribute(productAttributeId);

		try {
			if (((ProductAttributeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductAttribute could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/productAttribute/\" plus one of the following: "
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
