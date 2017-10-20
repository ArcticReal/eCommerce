package com.skytala.eCommerce.domain.product.relations.product.control.typeAttr;

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
import com.skytala.eCommerce.domain.product.relations.product.command.typeAttr.AddProductTypeAttr;
import com.skytala.eCommerce.domain.product.relations.product.command.typeAttr.DeleteProductTypeAttr;
import com.skytala.eCommerce.domain.product.relations.product.command.typeAttr.UpdateProductTypeAttr;
import com.skytala.eCommerce.domain.product.relations.product.event.typeAttr.ProductTypeAttrAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.typeAttr.ProductTypeAttrDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.typeAttr.ProductTypeAttrFound;
import com.skytala.eCommerce.domain.product.relations.product.event.typeAttr.ProductTypeAttrUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.typeAttr.ProductTypeAttrMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.typeAttr.ProductTypeAttr;
import com.skytala.eCommerce.domain.product.relations.product.query.typeAttr.FindProductTypeAttrsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@CrossOrigin
@RequestMapping("/productTypeAttrs")
public class ProductTypeAttrController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductTypeAttrController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductTypeAttr
	 * @return a List with the ProductTypeAttrs
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findProductTypeAttrsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductTypeAttrsBy query = new FindProductTypeAttrsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductTypeAttr> productTypeAttrs =((ProductTypeAttrFound) Scheduler.execute(query).data()).getProductTypeAttrs();

		if (productTypeAttrs.size() == 1) {
			return ResponseEntity.ok().body(productTypeAttrs.get(0));
		}

		return ResponseEntity.ok().body(productTypeAttrs);

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
	public ResponseEntity<Object> createProductTypeAttr(HttpServletRequest request) throws Exception {

		ProductTypeAttr productTypeAttrToBeAdded = new ProductTypeAttr();
		try {
			productTypeAttrToBeAdded = ProductTypeAttrMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createProductTypeAttr(productTypeAttrToBeAdded);

	}

	/**
	 * creates a new ProductTypeAttr entry in the ofbiz database
	 * 
	 * @param productTypeAttrToBeAdded
	 *            the ProductTypeAttr thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createProductTypeAttr(@RequestBody ProductTypeAttr productTypeAttrToBeAdded) throws Exception {

		AddProductTypeAttr command = new AddProductTypeAttr(productTypeAttrToBeAdded);
		ProductTypeAttr productTypeAttr = ((ProductTypeAttrAdded) Scheduler.execute(command).data()).getAddedProductTypeAttr();
		
		if (productTypeAttr != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(productTypeAttr);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProductTypeAttr could not be created.");
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
	public boolean updateProductTypeAttr(HttpServletRequest request) throws Exception {

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

		ProductTypeAttr productTypeAttrToBeUpdated = new ProductTypeAttr();

		try {
			productTypeAttrToBeUpdated = ProductTypeAttrMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProductTypeAttr(productTypeAttrToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ProductTypeAttr with the specific Id
	 * 
	 * @param productTypeAttrToBeUpdated
	 *            the ProductTypeAttr thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateProductTypeAttr(@RequestBody ProductTypeAttr productTypeAttrToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		productTypeAttrToBeUpdated.setnull(null);

		UpdateProductTypeAttr command = new UpdateProductTypeAttr(productTypeAttrToBeUpdated);

		try {
			if(((ProductTypeAttrUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{productTypeAttrId}")
	public ResponseEntity<Object> findById(@PathVariable String productTypeAttrId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productTypeAttrId", productTypeAttrId);
		try {

			Object foundProductTypeAttr = findProductTypeAttrsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProductTypeAttr);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{productTypeAttrId}")
	public ResponseEntity<Object> deleteProductTypeAttrByIdUpdated(@PathVariable String productTypeAttrId) throws Exception {
		DeleteProductTypeAttr command = new DeleteProductTypeAttr(productTypeAttrId);

		try {
			if (((ProductTypeAttrDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductTypeAttr could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/productTypeAttr/\" plus one of the following: "
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
