package com.skytala.eCommerce.domain.product.relations.productCategoryTypeAttr;

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
import com.skytala.eCommerce.domain.product.relations.productCategoryTypeAttr.command.AddProductCategoryTypeAttr;
import com.skytala.eCommerce.domain.product.relations.productCategoryTypeAttr.command.DeleteProductCategoryTypeAttr;
import com.skytala.eCommerce.domain.product.relations.productCategoryTypeAttr.command.UpdateProductCategoryTypeAttr;
import com.skytala.eCommerce.domain.product.relations.productCategoryTypeAttr.event.ProductCategoryTypeAttrAdded;
import com.skytala.eCommerce.domain.product.relations.productCategoryTypeAttr.event.ProductCategoryTypeAttrDeleted;
import com.skytala.eCommerce.domain.product.relations.productCategoryTypeAttr.event.ProductCategoryTypeAttrFound;
import com.skytala.eCommerce.domain.product.relations.productCategoryTypeAttr.event.ProductCategoryTypeAttrUpdated;
import com.skytala.eCommerce.domain.product.relations.productCategoryTypeAttr.mapper.ProductCategoryTypeAttrMapper;
import com.skytala.eCommerce.domain.product.relations.productCategoryTypeAttr.model.ProductCategoryTypeAttr;
import com.skytala.eCommerce.domain.product.relations.productCategoryTypeAttr.query.FindProductCategoryTypeAttrsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/productCategoryTypeAttrs")
public class ProductCategoryTypeAttrController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductCategoryTypeAttrController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductCategoryTypeAttr
	 * @return a List with the ProductCategoryTypeAttrs
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findProductCategoryTypeAttrsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductCategoryTypeAttrsBy query = new FindProductCategoryTypeAttrsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductCategoryTypeAttr> productCategoryTypeAttrs =((ProductCategoryTypeAttrFound) Scheduler.execute(query).data()).getProductCategoryTypeAttrs();

		if (productCategoryTypeAttrs.size() == 1) {
			return ResponseEntity.ok().body(productCategoryTypeAttrs.get(0));
		}

		return ResponseEntity.ok().body(productCategoryTypeAttrs);

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
	public ResponseEntity<Object> createProductCategoryTypeAttr(HttpServletRequest request) throws Exception {

		ProductCategoryTypeAttr productCategoryTypeAttrToBeAdded = new ProductCategoryTypeAttr();
		try {
			productCategoryTypeAttrToBeAdded = ProductCategoryTypeAttrMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createProductCategoryTypeAttr(productCategoryTypeAttrToBeAdded);

	}

	/**
	 * creates a new ProductCategoryTypeAttr entry in the ofbiz database
	 * 
	 * @param productCategoryTypeAttrToBeAdded
	 *            the ProductCategoryTypeAttr thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createProductCategoryTypeAttr(@RequestBody ProductCategoryTypeAttr productCategoryTypeAttrToBeAdded) throws Exception {

		AddProductCategoryTypeAttr command = new AddProductCategoryTypeAttr(productCategoryTypeAttrToBeAdded);
		ProductCategoryTypeAttr productCategoryTypeAttr = ((ProductCategoryTypeAttrAdded) Scheduler.execute(command).data()).getAddedProductCategoryTypeAttr();
		
		if (productCategoryTypeAttr != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(productCategoryTypeAttr);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProductCategoryTypeAttr could not be created.");
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
	public boolean updateProductCategoryTypeAttr(HttpServletRequest request) throws Exception {

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

		ProductCategoryTypeAttr productCategoryTypeAttrToBeUpdated = new ProductCategoryTypeAttr();

		try {
			productCategoryTypeAttrToBeUpdated = ProductCategoryTypeAttrMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProductCategoryTypeAttr(productCategoryTypeAttrToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ProductCategoryTypeAttr with the specific Id
	 * 
	 * @param productCategoryTypeAttrToBeUpdated
	 *            the ProductCategoryTypeAttr thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateProductCategoryTypeAttr(@RequestBody ProductCategoryTypeAttr productCategoryTypeAttrToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		productCategoryTypeAttrToBeUpdated.setnull(null);

		UpdateProductCategoryTypeAttr command = new UpdateProductCategoryTypeAttr(productCategoryTypeAttrToBeUpdated);

		try {
			if(((ProductCategoryTypeAttrUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{productCategoryTypeAttrId}")
	public ResponseEntity<Object> findById(@PathVariable String productCategoryTypeAttrId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productCategoryTypeAttrId", productCategoryTypeAttrId);
		try {

			Object foundProductCategoryTypeAttr = findProductCategoryTypeAttrsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProductCategoryTypeAttr);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{productCategoryTypeAttrId}")
	public ResponseEntity<Object> deleteProductCategoryTypeAttrByIdUpdated(@PathVariable String productCategoryTypeAttrId) throws Exception {
		DeleteProductCategoryTypeAttr command = new DeleteProductCategoryTypeAttr(productCategoryTypeAttrId);

		try {
			if (((ProductCategoryTypeAttrDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductCategoryTypeAttr could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/productCategoryTypeAttr/\" plus one of the following: "
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