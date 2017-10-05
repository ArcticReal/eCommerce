package com.skytala.eCommerce.domain.productSearchResult;

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
import com.skytala.eCommerce.domain.productSearchResult.command.AddProductSearchResult;
import com.skytala.eCommerce.domain.productSearchResult.command.DeleteProductSearchResult;
import com.skytala.eCommerce.domain.productSearchResult.command.UpdateProductSearchResult;
import com.skytala.eCommerce.domain.productSearchResult.event.ProductSearchResultAdded;
import com.skytala.eCommerce.domain.productSearchResult.event.ProductSearchResultDeleted;
import com.skytala.eCommerce.domain.productSearchResult.event.ProductSearchResultFound;
import com.skytala.eCommerce.domain.productSearchResult.event.ProductSearchResultUpdated;
import com.skytala.eCommerce.domain.productSearchResult.mapper.ProductSearchResultMapper;
import com.skytala.eCommerce.domain.productSearchResult.model.ProductSearchResult;
import com.skytala.eCommerce.domain.productSearchResult.query.FindProductSearchResultsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/productSearchResults")
public class ProductSearchResultController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductSearchResultController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductSearchResult
	 * @return a List with the ProductSearchResults
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findProductSearchResultsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductSearchResultsBy query = new FindProductSearchResultsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductSearchResult> productSearchResults =((ProductSearchResultFound) Scheduler.execute(query).data()).getProductSearchResults();

		if (productSearchResults.size() == 1) {
			return ResponseEntity.ok().body(productSearchResults.get(0));
		}

		return ResponseEntity.ok().body(productSearchResults);

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
	public ResponseEntity<Object> createProductSearchResult(HttpServletRequest request) throws Exception {

		ProductSearchResult productSearchResultToBeAdded = new ProductSearchResult();
		try {
			productSearchResultToBeAdded = ProductSearchResultMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createProductSearchResult(productSearchResultToBeAdded);

	}

	/**
	 * creates a new ProductSearchResult entry in the ofbiz database
	 * 
	 * @param productSearchResultToBeAdded
	 *            the ProductSearchResult thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createProductSearchResult(@RequestBody ProductSearchResult productSearchResultToBeAdded) throws Exception {

		AddProductSearchResult command = new AddProductSearchResult(productSearchResultToBeAdded);
		ProductSearchResult productSearchResult = ((ProductSearchResultAdded) Scheduler.execute(command).data()).getAddedProductSearchResult();
		
		if (productSearchResult != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(productSearchResult);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProductSearchResult could not be created.");
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
	public boolean updateProductSearchResult(HttpServletRequest request) throws Exception {

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

		ProductSearchResult productSearchResultToBeUpdated = new ProductSearchResult();

		try {
			productSearchResultToBeUpdated = ProductSearchResultMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProductSearchResult(productSearchResultToBeUpdated, productSearchResultToBeUpdated.getProductSearchResultId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ProductSearchResult with the specific Id
	 * 
	 * @param productSearchResultToBeUpdated
	 *            the ProductSearchResult thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{productSearchResultId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateProductSearchResult(@RequestBody ProductSearchResult productSearchResultToBeUpdated,
			@PathVariable String productSearchResultId) throws Exception {

		productSearchResultToBeUpdated.setProductSearchResultId(productSearchResultId);

		UpdateProductSearchResult command = new UpdateProductSearchResult(productSearchResultToBeUpdated);

		try {
			if(((ProductSearchResultUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{productSearchResultId}")
	public ResponseEntity<Object> findById(@PathVariable String productSearchResultId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productSearchResultId", productSearchResultId);
		try {

			Object foundProductSearchResult = findProductSearchResultsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProductSearchResult);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{productSearchResultId}")
	public ResponseEntity<Object> deleteProductSearchResultByIdUpdated(@PathVariable String productSearchResultId) throws Exception {
		DeleteProductSearchResult command = new DeleteProductSearchResult(productSearchResultId);

		try {
			if (((ProductSearchResultDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductSearchResult could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/productSearchResult/\" plus one of the following: "
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
