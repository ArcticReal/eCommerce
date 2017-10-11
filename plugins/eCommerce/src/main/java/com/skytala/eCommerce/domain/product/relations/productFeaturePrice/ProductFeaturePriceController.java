package com.skytala.eCommerce.domain.product.relations.productFeaturePrice;

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
import com.skytala.eCommerce.domain.product.relations.productFeaturePrice.command.AddProductFeaturePrice;
import com.skytala.eCommerce.domain.product.relations.productFeaturePrice.command.DeleteProductFeaturePrice;
import com.skytala.eCommerce.domain.product.relations.productFeaturePrice.command.UpdateProductFeaturePrice;
import com.skytala.eCommerce.domain.product.relations.productFeaturePrice.event.ProductFeaturePriceAdded;
import com.skytala.eCommerce.domain.product.relations.productFeaturePrice.event.ProductFeaturePriceDeleted;
import com.skytala.eCommerce.domain.product.relations.productFeaturePrice.event.ProductFeaturePriceFound;
import com.skytala.eCommerce.domain.product.relations.productFeaturePrice.event.ProductFeaturePriceUpdated;
import com.skytala.eCommerce.domain.product.relations.productFeaturePrice.mapper.ProductFeaturePriceMapper;
import com.skytala.eCommerce.domain.product.relations.productFeaturePrice.model.ProductFeaturePrice;
import com.skytala.eCommerce.domain.product.relations.productFeaturePrice.query.FindProductFeaturePricesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/productFeaturePrices")
public class ProductFeaturePriceController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductFeaturePriceController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductFeaturePrice
	 * @return a List with the ProductFeaturePrices
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findProductFeaturePricesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductFeaturePricesBy query = new FindProductFeaturePricesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductFeaturePrice> productFeaturePrices =((ProductFeaturePriceFound) Scheduler.execute(query).data()).getProductFeaturePrices();

		if (productFeaturePrices.size() == 1) {
			return ResponseEntity.ok().body(productFeaturePrices.get(0));
		}

		return ResponseEntity.ok().body(productFeaturePrices);

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
	public ResponseEntity<Object> createProductFeaturePrice(HttpServletRequest request) throws Exception {

		ProductFeaturePrice productFeaturePriceToBeAdded = new ProductFeaturePrice();
		try {
			productFeaturePriceToBeAdded = ProductFeaturePriceMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createProductFeaturePrice(productFeaturePriceToBeAdded);

	}

	/**
	 * creates a new ProductFeaturePrice entry in the ofbiz database
	 * 
	 * @param productFeaturePriceToBeAdded
	 *            the ProductFeaturePrice thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createProductFeaturePrice(@RequestBody ProductFeaturePrice productFeaturePriceToBeAdded) throws Exception {

		AddProductFeaturePrice command = new AddProductFeaturePrice(productFeaturePriceToBeAdded);
		ProductFeaturePrice productFeaturePrice = ((ProductFeaturePriceAdded) Scheduler.execute(command).data()).getAddedProductFeaturePrice();
		
		if (productFeaturePrice != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(productFeaturePrice);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProductFeaturePrice could not be created.");
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
	public boolean updateProductFeaturePrice(HttpServletRequest request) throws Exception {

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

		ProductFeaturePrice productFeaturePriceToBeUpdated = new ProductFeaturePrice();

		try {
			productFeaturePriceToBeUpdated = ProductFeaturePriceMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProductFeaturePrice(productFeaturePriceToBeUpdated, productFeaturePriceToBeUpdated.getProductFeatureId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ProductFeaturePrice with the specific Id
	 * 
	 * @param productFeaturePriceToBeUpdated
	 *            the ProductFeaturePrice thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{productFeatureId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateProductFeaturePrice(@RequestBody ProductFeaturePrice productFeaturePriceToBeUpdated,
			@PathVariable String productFeatureId) throws Exception {

		productFeaturePriceToBeUpdated.setProductFeatureId(productFeatureId);

		UpdateProductFeaturePrice command = new UpdateProductFeaturePrice(productFeaturePriceToBeUpdated);

		try {
			if(((ProductFeaturePriceUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{productFeaturePriceId}")
	public ResponseEntity<Object> findById(@PathVariable String productFeaturePriceId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productFeaturePriceId", productFeaturePriceId);
		try {

			Object foundProductFeaturePrice = findProductFeaturePricesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProductFeaturePrice);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{productFeaturePriceId}")
	public ResponseEntity<Object> deleteProductFeaturePriceByIdUpdated(@PathVariable String productFeaturePriceId) throws Exception {
		DeleteProductFeaturePrice command = new DeleteProductFeaturePrice(productFeaturePriceId);

		try {
			if (((ProductFeaturePriceDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductFeaturePrice could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/productFeaturePrice/\" plus one of the following: "
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
