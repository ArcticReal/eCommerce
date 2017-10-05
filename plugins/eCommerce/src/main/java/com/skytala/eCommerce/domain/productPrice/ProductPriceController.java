package com.skytala.eCommerce.domain.productPrice;

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
import com.skytala.eCommerce.domain.productPrice.command.AddProductPrice;
import com.skytala.eCommerce.domain.productPrice.command.DeleteProductPrice;
import com.skytala.eCommerce.domain.productPrice.command.UpdateProductPrice;
import com.skytala.eCommerce.domain.productPrice.event.ProductPriceAdded;
import com.skytala.eCommerce.domain.productPrice.event.ProductPriceDeleted;
import com.skytala.eCommerce.domain.productPrice.event.ProductPriceFound;
import com.skytala.eCommerce.domain.productPrice.event.ProductPriceUpdated;
import com.skytala.eCommerce.domain.productPrice.mapper.ProductPriceMapper;
import com.skytala.eCommerce.domain.productPrice.model.ProductPrice;
import com.skytala.eCommerce.domain.productPrice.query.FindProductPricesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/productPrices")
public class ProductPriceController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductPriceController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductPrice
	 * @return a List with the ProductPrices
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findProductPricesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductPricesBy query = new FindProductPricesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductPrice> productPrices =((ProductPriceFound) Scheduler.execute(query).data()).getProductPrices();

		if (productPrices.size() == 1) {
			return ResponseEntity.ok().body(productPrices.get(0));
		}

		return ResponseEntity.ok().body(productPrices);

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
	public ResponseEntity<Object> createProductPrice(HttpServletRequest request) throws Exception {

		ProductPrice productPriceToBeAdded = new ProductPrice();
		try {
			productPriceToBeAdded = ProductPriceMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createProductPrice(productPriceToBeAdded);

	}

	/**
	 * creates a new ProductPrice entry in the ofbiz database
	 * 
	 * @param productPriceToBeAdded
	 *            the ProductPrice thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createProductPrice(@RequestBody ProductPrice productPriceToBeAdded) throws Exception {

		AddProductPrice command = new AddProductPrice(productPriceToBeAdded);
		ProductPrice productPrice = ((ProductPriceAdded) Scheduler.execute(command).data()).getAddedProductPrice();
		
		if (productPrice != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(productPrice);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProductPrice could not be created.");
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
	public boolean updateProductPrice(HttpServletRequest request) throws Exception {

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

		ProductPrice productPriceToBeUpdated = new ProductPrice();

		try {
			productPriceToBeUpdated = ProductPriceMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProductPrice(productPriceToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ProductPrice with the specific Id
	 * 
	 * @param productPriceToBeUpdated
	 *            the ProductPrice thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateProductPrice(@RequestBody ProductPrice productPriceToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		productPriceToBeUpdated.setnull(null);

		UpdateProductPrice command = new UpdateProductPrice(productPriceToBeUpdated);

		try {
			if(((ProductPriceUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{productPriceId}")
	public ResponseEntity<Object> findById(@PathVariable String productPriceId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productPriceId", productPriceId);
		try {

			Object foundProductPrice = findProductPricesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProductPrice);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{productPriceId}")
	public ResponseEntity<Object> deleteProductPriceByIdUpdated(@PathVariable String productPriceId) throws Exception {
		DeleteProductPrice command = new DeleteProductPrice(productPriceId);

		try {
			if (((ProductPriceDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductPrice could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/productPrice/\" plus one of the following: "
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
