package com.skytala.eCommerce.domain.product.relations.productFeatureCatGrpAppl;

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
import com.skytala.eCommerce.domain.product.relations.productFeatureCatGrpAppl.command.AddProductFeatureCatGrpAppl;
import com.skytala.eCommerce.domain.product.relations.productFeatureCatGrpAppl.command.DeleteProductFeatureCatGrpAppl;
import com.skytala.eCommerce.domain.product.relations.productFeatureCatGrpAppl.command.UpdateProductFeatureCatGrpAppl;
import com.skytala.eCommerce.domain.product.relations.productFeatureCatGrpAppl.event.ProductFeatureCatGrpApplAdded;
import com.skytala.eCommerce.domain.product.relations.productFeatureCatGrpAppl.event.ProductFeatureCatGrpApplDeleted;
import com.skytala.eCommerce.domain.product.relations.productFeatureCatGrpAppl.event.ProductFeatureCatGrpApplFound;
import com.skytala.eCommerce.domain.product.relations.productFeatureCatGrpAppl.event.ProductFeatureCatGrpApplUpdated;
import com.skytala.eCommerce.domain.product.relations.productFeatureCatGrpAppl.mapper.ProductFeatureCatGrpApplMapper;
import com.skytala.eCommerce.domain.product.relations.productFeatureCatGrpAppl.model.ProductFeatureCatGrpAppl;
import com.skytala.eCommerce.domain.product.relations.productFeatureCatGrpAppl.query.FindProductFeatureCatGrpApplsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/productFeatureCatGrpAppls")
public class ProductFeatureCatGrpApplController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductFeatureCatGrpApplController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductFeatureCatGrpAppl
	 * @return a List with the ProductFeatureCatGrpAppls
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findProductFeatureCatGrpApplsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductFeatureCatGrpApplsBy query = new FindProductFeatureCatGrpApplsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductFeatureCatGrpAppl> productFeatureCatGrpAppls =((ProductFeatureCatGrpApplFound) Scheduler.execute(query).data()).getProductFeatureCatGrpAppls();

		if (productFeatureCatGrpAppls.size() == 1) {
			return ResponseEntity.ok().body(productFeatureCatGrpAppls.get(0));
		}

		return ResponseEntity.ok().body(productFeatureCatGrpAppls);

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
	public ResponseEntity<Object> createProductFeatureCatGrpAppl(HttpServletRequest request) throws Exception {

		ProductFeatureCatGrpAppl productFeatureCatGrpApplToBeAdded = new ProductFeatureCatGrpAppl();
		try {
			productFeatureCatGrpApplToBeAdded = ProductFeatureCatGrpApplMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createProductFeatureCatGrpAppl(productFeatureCatGrpApplToBeAdded);

	}

	/**
	 * creates a new ProductFeatureCatGrpAppl entry in the ofbiz database
	 * 
	 * @param productFeatureCatGrpApplToBeAdded
	 *            the ProductFeatureCatGrpAppl thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createProductFeatureCatGrpAppl(@RequestBody ProductFeatureCatGrpAppl productFeatureCatGrpApplToBeAdded) throws Exception {

		AddProductFeatureCatGrpAppl command = new AddProductFeatureCatGrpAppl(productFeatureCatGrpApplToBeAdded);
		ProductFeatureCatGrpAppl productFeatureCatGrpAppl = ((ProductFeatureCatGrpApplAdded) Scheduler.execute(command).data()).getAddedProductFeatureCatGrpAppl();
		
		if (productFeatureCatGrpAppl != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(productFeatureCatGrpAppl);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProductFeatureCatGrpAppl could not be created.");
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
	public boolean updateProductFeatureCatGrpAppl(HttpServletRequest request) throws Exception {

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

		ProductFeatureCatGrpAppl productFeatureCatGrpApplToBeUpdated = new ProductFeatureCatGrpAppl();

		try {
			productFeatureCatGrpApplToBeUpdated = ProductFeatureCatGrpApplMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProductFeatureCatGrpAppl(productFeatureCatGrpApplToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ProductFeatureCatGrpAppl with the specific Id
	 * 
	 * @param productFeatureCatGrpApplToBeUpdated
	 *            the ProductFeatureCatGrpAppl thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateProductFeatureCatGrpAppl(@RequestBody ProductFeatureCatGrpAppl productFeatureCatGrpApplToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		productFeatureCatGrpApplToBeUpdated.setnull(null);

		UpdateProductFeatureCatGrpAppl command = new UpdateProductFeatureCatGrpAppl(productFeatureCatGrpApplToBeUpdated);

		try {
			if(((ProductFeatureCatGrpApplUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{productFeatureCatGrpApplId}")
	public ResponseEntity<Object> findById(@PathVariable String productFeatureCatGrpApplId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productFeatureCatGrpApplId", productFeatureCatGrpApplId);
		try {

			Object foundProductFeatureCatGrpAppl = findProductFeatureCatGrpApplsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProductFeatureCatGrpAppl);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{productFeatureCatGrpApplId}")
	public ResponseEntity<Object> deleteProductFeatureCatGrpApplByIdUpdated(@PathVariable String productFeatureCatGrpApplId) throws Exception {
		DeleteProductFeatureCatGrpAppl command = new DeleteProductFeatureCatGrpAppl(productFeatureCatGrpApplId);

		try {
			if (((ProductFeatureCatGrpApplDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductFeatureCatGrpAppl could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/productFeatureCatGrpAppl/\" plus one of the following: "
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
