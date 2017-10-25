package com.skytala.eCommerce.domain.product.relations.product.control.category;

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
import com.skytala.eCommerce.domain.product.relations.product.command.category.AddProductCategory;
import com.skytala.eCommerce.domain.product.relations.product.command.category.DeleteProductCategory;
import com.skytala.eCommerce.domain.product.relations.product.command.category.UpdateProductCategory;
import com.skytala.eCommerce.domain.product.relations.product.event.category.ProductCategoryAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.category.ProductCategoryDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.category.ProductCategoryFound;
import com.skytala.eCommerce.domain.product.relations.product.event.category.ProductCategoryUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.category.ProductCategoryMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.category.ProductCategory;
import com.skytala.eCommerce.domain.product.relations.product.query.category.FindProductCategorysBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/productCategorys")
public class ProductCategoryController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductCategoryController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductCategory
	 * @return a List with the ProductCategorys
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findProductCategorysBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductCategorysBy query = new FindProductCategorysBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductCategory> productCategorys =((ProductCategoryFound) Scheduler.execute(query).data()).getProductCategorys();

		if (productCategorys.size() == 1) {
			return ResponseEntity.ok().body(productCategorys.get(0));
		}

		return ResponseEntity.ok().body(productCategorys);

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
	public ResponseEntity<Object> createProductCategory(HttpServletRequest request) throws Exception {

		ProductCategory productCategoryToBeAdded = new ProductCategory();
		try {
			productCategoryToBeAdded = ProductCategoryMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createProductCategory(productCategoryToBeAdded);

	}

	/**
	 * creates a new ProductCategory entry in the ofbiz database
	 * 
	 * @param productCategoryToBeAdded
	 *            the ProductCategory thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createProductCategory(@RequestBody ProductCategory productCategoryToBeAdded) throws Exception {

		AddProductCategory command = new AddProductCategory(productCategoryToBeAdded);
		ProductCategory productCategory = ((ProductCategoryAdded) Scheduler.execute(command).data()).getAddedProductCategory();
		
		if (productCategory != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(productCategory);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProductCategory could not be created.");
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
	public boolean updateProductCategory(HttpServletRequest request) throws Exception {

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

		ProductCategory productCategoryToBeUpdated = new ProductCategory();

		try {
			productCategoryToBeUpdated = ProductCategoryMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProductCategory(productCategoryToBeUpdated, productCategoryToBeUpdated.getProductCategoryId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ProductCategory with the specific Id
	 * 
	 * @param productCategoryToBeUpdated
	 *            the ProductCategory thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{productCategoryId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateProductCategory(@RequestBody ProductCategory productCategoryToBeUpdated,
			@PathVariable String productCategoryId) throws Exception {

		productCategoryToBeUpdated.setProductCategoryId(productCategoryId);

		UpdateProductCategory command = new UpdateProductCategory(productCategoryToBeUpdated);

		try {
			if(((ProductCategoryUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{productCategoryId}")
	public ResponseEntity<Object> findById(@PathVariable String productCategoryId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productCategoryId", productCategoryId);
		try {

			Object foundProductCategory = findProductCategorysBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProductCategory);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{productCategoryId}")
	public ResponseEntity<Object> deleteProductCategoryByIdUpdated(@PathVariable String productCategoryId) throws Exception {
		DeleteProductCategory command = new DeleteProductCategory(productCategoryId);

		try {
			if (((ProductCategoryDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductCategory could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/productCategory/\" plus one of the following: "
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
