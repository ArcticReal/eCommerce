package com.skytala.eCommerce.domain.product.relations.productPriceCond;

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
import com.skytala.eCommerce.domain.product.relations.productPriceCond.command.AddProductPriceCond;
import com.skytala.eCommerce.domain.product.relations.productPriceCond.command.DeleteProductPriceCond;
import com.skytala.eCommerce.domain.product.relations.productPriceCond.command.UpdateProductPriceCond;
import com.skytala.eCommerce.domain.product.relations.productPriceCond.event.ProductPriceCondAdded;
import com.skytala.eCommerce.domain.product.relations.productPriceCond.event.ProductPriceCondDeleted;
import com.skytala.eCommerce.domain.product.relations.productPriceCond.event.ProductPriceCondFound;
import com.skytala.eCommerce.domain.product.relations.productPriceCond.event.ProductPriceCondUpdated;
import com.skytala.eCommerce.domain.product.relations.productPriceCond.mapper.ProductPriceCondMapper;
import com.skytala.eCommerce.domain.product.relations.productPriceCond.model.ProductPriceCond;
import com.skytala.eCommerce.domain.product.relations.productPriceCond.query.FindProductPriceCondsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/productPriceConds")
public class ProductPriceCondController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductPriceCondController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductPriceCond
	 * @return a List with the ProductPriceConds
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findProductPriceCondsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductPriceCondsBy query = new FindProductPriceCondsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductPriceCond> productPriceConds =((ProductPriceCondFound) Scheduler.execute(query).data()).getProductPriceConds();

		if (productPriceConds.size() == 1) {
			return ResponseEntity.ok().body(productPriceConds.get(0));
		}

		return ResponseEntity.ok().body(productPriceConds);

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
	public ResponseEntity<Object> createProductPriceCond(HttpServletRequest request) throws Exception {

		ProductPriceCond productPriceCondToBeAdded = new ProductPriceCond();
		try {
			productPriceCondToBeAdded = ProductPriceCondMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createProductPriceCond(productPriceCondToBeAdded);

	}

	/**
	 * creates a new ProductPriceCond entry in the ofbiz database
	 * 
	 * @param productPriceCondToBeAdded
	 *            the ProductPriceCond thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createProductPriceCond(@RequestBody ProductPriceCond productPriceCondToBeAdded) throws Exception {

		AddProductPriceCond command = new AddProductPriceCond(productPriceCondToBeAdded);
		ProductPriceCond productPriceCond = ((ProductPriceCondAdded) Scheduler.execute(command).data()).getAddedProductPriceCond();
		
		if (productPriceCond != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(productPriceCond);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProductPriceCond could not be created.");
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
	public boolean updateProductPriceCond(HttpServletRequest request) throws Exception {

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

		ProductPriceCond productPriceCondToBeUpdated = new ProductPriceCond();

		try {
			productPriceCondToBeUpdated = ProductPriceCondMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProductPriceCond(productPriceCondToBeUpdated, productPriceCondToBeUpdated.getProductPriceCondSeqId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ProductPriceCond with the specific Id
	 * 
	 * @param productPriceCondToBeUpdated
	 *            the ProductPriceCond thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{productPriceCondSeqId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateProductPriceCond(@RequestBody ProductPriceCond productPriceCondToBeUpdated,
			@PathVariable String productPriceCondSeqId) throws Exception {

		productPriceCondToBeUpdated.setProductPriceCondSeqId(productPriceCondSeqId);

		UpdateProductPriceCond command = new UpdateProductPriceCond(productPriceCondToBeUpdated);

		try {
			if(((ProductPriceCondUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{productPriceCondId}")
	public ResponseEntity<Object> findById(@PathVariable String productPriceCondId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productPriceCondId", productPriceCondId);
		try {

			Object foundProductPriceCond = findProductPriceCondsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProductPriceCond);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{productPriceCondId}")
	public ResponseEntity<Object> deleteProductPriceCondByIdUpdated(@PathVariable String productPriceCondId) throws Exception {
		DeleteProductPriceCond command = new DeleteProductPriceCond(productPriceCondId);

		try {
			if (((ProductPriceCondDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductPriceCond could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/productPriceCond/\" plus one of the following: "
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
