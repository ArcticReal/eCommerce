package com.skytala.eCommerce.domain.product.relations.product.control.storePromoAppl;

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
import com.skytala.eCommerce.domain.product.relations.product.command.storePromoAppl.AddProductStorePromoAppl;
import com.skytala.eCommerce.domain.product.relations.product.command.storePromoAppl.DeleteProductStorePromoAppl;
import com.skytala.eCommerce.domain.product.relations.product.command.storePromoAppl.UpdateProductStorePromoAppl;
import com.skytala.eCommerce.domain.product.relations.product.event.storePromoAppl.ProductStorePromoApplAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.storePromoAppl.ProductStorePromoApplDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.storePromoAppl.ProductStorePromoApplFound;
import com.skytala.eCommerce.domain.product.relations.product.event.storePromoAppl.ProductStorePromoApplUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.storePromoAppl.ProductStorePromoApplMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.storePromoAppl.ProductStorePromoAppl;
import com.skytala.eCommerce.domain.product.relations.product.query.storePromoAppl.FindProductStorePromoApplsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/productStorePromoAppls")
public class ProductStorePromoApplController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductStorePromoApplController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductStorePromoAppl
	 * @return a List with the ProductStorePromoAppls
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findProductStorePromoApplsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductStorePromoApplsBy query = new FindProductStorePromoApplsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductStorePromoAppl> productStorePromoAppls =((ProductStorePromoApplFound) Scheduler.execute(query).data()).getProductStorePromoAppls();

		if (productStorePromoAppls.size() == 1) {
			return ResponseEntity.ok().body(productStorePromoAppls.get(0));
		}

		return ResponseEntity.ok().body(productStorePromoAppls);

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
	public ResponseEntity<Object> createProductStorePromoAppl(HttpServletRequest request) throws Exception {

		ProductStorePromoAppl productStorePromoApplToBeAdded = new ProductStorePromoAppl();
		try {
			productStorePromoApplToBeAdded = ProductStorePromoApplMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createProductStorePromoAppl(productStorePromoApplToBeAdded);

	}

	/**
	 * creates a new ProductStorePromoAppl entry in the ofbiz database
	 * 
	 * @param productStorePromoApplToBeAdded
	 *            the ProductStorePromoAppl thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createProductStorePromoAppl(@RequestBody ProductStorePromoAppl productStorePromoApplToBeAdded) throws Exception {

		AddProductStorePromoAppl command = new AddProductStorePromoAppl(productStorePromoApplToBeAdded);
		ProductStorePromoAppl productStorePromoAppl = ((ProductStorePromoApplAdded) Scheduler.execute(command).data()).getAddedProductStorePromoAppl();
		
		if (productStorePromoAppl != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(productStorePromoAppl);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProductStorePromoAppl could not be created.");
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
	public boolean updateProductStorePromoAppl(HttpServletRequest request) throws Exception {

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

		ProductStorePromoAppl productStorePromoApplToBeUpdated = new ProductStorePromoAppl();

		try {
			productStorePromoApplToBeUpdated = ProductStorePromoApplMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProductStorePromoAppl(productStorePromoApplToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ProductStorePromoAppl with the specific Id
	 * 
	 * @param productStorePromoApplToBeUpdated
	 *            the ProductStorePromoAppl thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateProductStorePromoAppl(@RequestBody ProductStorePromoAppl productStorePromoApplToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		productStorePromoApplToBeUpdated.setnull(null);

		UpdateProductStorePromoAppl command = new UpdateProductStorePromoAppl(productStorePromoApplToBeUpdated);

		try {
			if(((ProductStorePromoApplUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{productStorePromoApplId}")
	public ResponseEntity<Object> findById(@PathVariable String productStorePromoApplId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productStorePromoApplId", productStorePromoApplId);
		try {

			Object foundProductStorePromoAppl = findProductStorePromoApplsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProductStorePromoAppl);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{productStorePromoApplId}")
	public ResponseEntity<Object> deleteProductStorePromoApplByIdUpdated(@PathVariable String productStorePromoApplId) throws Exception {
		DeleteProductStorePromoAppl command = new DeleteProductStorePromoAppl(productStorePromoApplId);

		try {
			if (((ProductStorePromoApplDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductStorePromoAppl could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/productStorePromoAppl/\" plus one of the following: "
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
