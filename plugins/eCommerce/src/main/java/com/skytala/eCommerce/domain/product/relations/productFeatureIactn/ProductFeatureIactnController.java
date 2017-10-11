package com.skytala.eCommerce.domain.product.relations.productFeatureIactn;

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
import com.skytala.eCommerce.domain.product.relations.productFeatureIactn.command.AddProductFeatureIactn;
import com.skytala.eCommerce.domain.product.relations.productFeatureIactn.command.DeleteProductFeatureIactn;
import com.skytala.eCommerce.domain.product.relations.productFeatureIactn.command.UpdateProductFeatureIactn;
import com.skytala.eCommerce.domain.product.relations.productFeatureIactn.event.ProductFeatureIactnAdded;
import com.skytala.eCommerce.domain.product.relations.productFeatureIactn.event.ProductFeatureIactnDeleted;
import com.skytala.eCommerce.domain.product.relations.productFeatureIactn.event.ProductFeatureIactnFound;
import com.skytala.eCommerce.domain.product.relations.productFeatureIactn.event.ProductFeatureIactnUpdated;
import com.skytala.eCommerce.domain.product.relations.productFeatureIactn.mapper.ProductFeatureIactnMapper;
import com.skytala.eCommerce.domain.product.relations.productFeatureIactn.model.ProductFeatureIactn;
import com.skytala.eCommerce.domain.product.relations.productFeatureIactn.query.FindProductFeatureIactnsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/productFeatureIactns")
public class ProductFeatureIactnController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductFeatureIactnController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductFeatureIactn
	 * @return a List with the ProductFeatureIactns
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findProductFeatureIactnsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductFeatureIactnsBy query = new FindProductFeatureIactnsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductFeatureIactn> productFeatureIactns =((ProductFeatureIactnFound) Scheduler.execute(query).data()).getProductFeatureIactns();

		if (productFeatureIactns.size() == 1) {
			return ResponseEntity.ok().body(productFeatureIactns.get(0));
		}

		return ResponseEntity.ok().body(productFeatureIactns);

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
	public ResponseEntity<Object> createProductFeatureIactn(HttpServletRequest request) throws Exception {

		ProductFeatureIactn productFeatureIactnToBeAdded = new ProductFeatureIactn();
		try {
			productFeatureIactnToBeAdded = ProductFeatureIactnMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createProductFeatureIactn(productFeatureIactnToBeAdded);

	}

	/**
	 * creates a new ProductFeatureIactn entry in the ofbiz database
	 * 
	 * @param productFeatureIactnToBeAdded
	 *            the ProductFeatureIactn thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createProductFeatureIactn(@RequestBody ProductFeatureIactn productFeatureIactnToBeAdded) throws Exception {

		AddProductFeatureIactn command = new AddProductFeatureIactn(productFeatureIactnToBeAdded);
		ProductFeatureIactn productFeatureIactn = ((ProductFeatureIactnAdded) Scheduler.execute(command).data()).getAddedProductFeatureIactn();
		
		if (productFeatureIactn != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(productFeatureIactn);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProductFeatureIactn could not be created.");
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
	public boolean updateProductFeatureIactn(HttpServletRequest request) throws Exception {

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

		ProductFeatureIactn productFeatureIactnToBeUpdated = new ProductFeatureIactn();

		try {
			productFeatureIactnToBeUpdated = ProductFeatureIactnMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProductFeatureIactn(productFeatureIactnToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ProductFeatureIactn with the specific Id
	 * 
	 * @param productFeatureIactnToBeUpdated
	 *            the ProductFeatureIactn thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateProductFeatureIactn(@RequestBody ProductFeatureIactn productFeatureIactnToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		productFeatureIactnToBeUpdated.setnull(null);

		UpdateProductFeatureIactn command = new UpdateProductFeatureIactn(productFeatureIactnToBeUpdated);

		try {
			if(((ProductFeatureIactnUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{productFeatureIactnId}")
	public ResponseEntity<Object> findById(@PathVariable String productFeatureIactnId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productFeatureIactnId", productFeatureIactnId);
		try {

			Object foundProductFeatureIactn = findProductFeatureIactnsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProductFeatureIactn);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{productFeatureIactnId}")
	public ResponseEntity<Object> deleteProductFeatureIactnByIdUpdated(@PathVariable String productFeatureIactnId) throws Exception {
		DeleteProductFeatureIactn command = new DeleteProductFeatureIactn(productFeatureIactnId);

		try {
			if (((ProductFeatureIactnDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductFeatureIactn could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/productFeatureIactn/\" plus one of the following: "
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
