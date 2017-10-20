package com.skytala.eCommerce.domain.product.relations.product.control.storeSurveyAppl;

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
import com.skytala.eCommerce.domain.product.relations.product.command.storeSurveyAppl.AddProductStoreSurveyAppl;
import com.skytala.eCommerce.domain.product.relations.product.command.storeSurveyAppl.DeleteProductStoreSurveyAppl;
import com.skytala.eCommerce.domain.product.relations.product.command.storeSurveyAppl.UpdateProductStoreSurveyAppl;
import com.skytala.eCommerce.domain.product.relations.product.event.storeSurveyAppl.ProductStoreSurveyApplAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.storeSurveyAppl.ProductStoreSurveyApplDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.storeSurveyAppl.ProductStoreSurveyApplFound;
import com.skytala.eCommerce.domain.product.relations.product.event.storeSurveyAppl.ProductStoreSurveyApplUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.storeSurveyAppl.ProductStoreSurveyApplMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.storeSurveyAppl.ProductStoreSurveyAppl;
import com.skytala.eCommerce.domain.product.relations.product.query.storeSurveyAppl.FindProductStoreSurveyApplsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@CrossOrigin
@RequestMapping("/productStoreSurveyAppls")
public class ProductStoreSurveyApplController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductStoreSurveyApplController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductStoreSurveyAppl
	 * @return a List with the ProductStoreSurveyAppls
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findProductStoreSurveyApplsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductStoreSurveyApplsBy query = new FindProductStoreSurveyApplsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductStoreSurveyAppl> productStoreSurveyAppls =((ProductStoreSurveyApplFound) Scheduler.execute(query).data()).getProductStoreSurveyAppls();

		if (productStoreSurveyAppls.size() == 1) {
			return ResponseEntity.ok().body(productStoreSurveyAppls.get(0));
		}

		return ResponseEntity.ok().body(productStoreSurveyAppls);

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
	public ResponseEntity<Object> createProductStoreSurveyAppl(HttpServletRequest request) throws Exception {

		ProductStoreSurveyAppl productStoreSurveyApplToBeAdded = new ProductStoreSurveyAppl();
		try {
			productStoreSurveyApplToBeAdded = ProductStoreSurveyApplMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createProductStoreSurveyAppl(productStoreSurveyApplToBeAdded);

	}

	/**
	 * creates a new ProductStoreSurveyAppl entry in the ofbiz database
	 * 
	 * @param productStoreSurveyApplToBeAdded
	 *            the ProductStoreSurveyAppl thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createProductStoreSurveyAppl(@RequestBody ProductStoreSurveyAppl productStoreSurveyApplToBeAdded) throws Exception {

		AddProductStoreSurveyAppl command = new AddProductStoreSurveyAppl(productStoreSurveyApplToBeAdded);
		ProductStoreSurveyAppl productStoreSurveyAppl = ((ProductStoreSurveyApplAdded) Scheduler.execute(command).data()).getAddedProductStoreSurveyAppl();
		
		if (productStoreSurveyAppl != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(productStoreSurveyAppl);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProductStoreSurveyAppl could not be created.");
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
	public boolean updateProductStoreSurveyAppl(HttpServletRequest request) throws Exception {

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

		ProductStoreSurveyAppl productStoreSurveyApplToBeUpdated = new ProductStoreSurveyAppl();

		try {
			productStoreSurveyApplToBeUpdated = ProductStoreSurveyApplMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProductStoreSurveyAppl(productStoreSurveyApplToBeUpdated, productStoreSurveyApplToBeUpdated.getProductStoreSurveyId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ProductStoreSurveyAppl with the specific Id
	 * 
	 * @param productStoreSurveyApplToBeUpdated
	 *            the ProductStoreSurveyAppl thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{productStoreSurveyId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateProductStoreSurveyAppl(@RequestBody ProductStoreSurveyAppl productStoreSurveyApplToBeUpdated,
			@PathVariable String productStoreSurveyId) throws Exception {

		productStoreSurveyApplToBeUpdated.setProductStoreSurveyId(productStoreSurveyId);

		UpdateProductStoreSurveyAppl command = new UpdateProductStoreSurveyAppl(productStoreSurveyApplToBeUpdated);

		try {
			if(((ProductStoreSurveyApplUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{productStoreSurveyApplId}")
	public ResponseEntity<Object> findById(@PathVariable String productStoreSurveyApplId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productStoreSurveyApplId", productStoreSurveyApplId);
		try {

			Object foundProductStoreSurveyAppl = findProductStoreSurveyApplsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProductStoreSurveyAppl);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{productStoreSurveyApplId}")
	public ResponseEntity<Object> deleteProductStoreSurveyApplByIdUpdated(@PathVariable String productStoreSurveyApplId) throws Exception {
		DeleteProductStoreSurveyAppl command = new DeleteProductStoreSurveyAppl(productStoreSurveyApplId);

		try {
			if (((ProductStoreSurveyApplDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductStoreSurveyAppl could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/productStoreSurveyAppl/\" plus one of the following: "
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
