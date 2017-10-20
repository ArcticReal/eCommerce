package com.skytala.eCommerce.domain.product.relations.product.control.subscriptionResource;

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
import com.skytala.eCommerce.domain.product.relations.product.command.subscriptionResource.AddProductSubscriptionResource;
import com.skytala.eCommerce.domain.product.relations.product.command.subscriptionResource.DeleteProductSubscriptionResource;
import com.skytala.eCommerce.domain.product.relations.product.command.subscriptionResource.UpdateProductSubscriptionResource;
import com.skytala.eCommerce.domain.product.relations.product.event.subscriptionResource.ProductSubscriptionResourceAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.subscriptionResource.ProductSubscriptionResourceDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.subscriptionResource.ProductSubscriptionResourceFound;
import com.skytala.eCommerce.domain.product.relations.product.event.subscriptionResource.ProductSubscriptionResourceUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.subscriptionResource.ProductSubscriptionResourceMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.subscriptionResource.ProductSubscriptionResource;
import com.skytala.eCommerce.domain.product.relations.product.query.subscriptionResource.FindProductSubscriptionResourcesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@CrossOrigin
@RequestMapping("/productSubscriptionResources")
public class ProductSubscriptionResourceController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductSubscriptionResourceController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductSubscriptionResource
	 * @return a List with the ProductSubscriptionResources
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findProductSubscriptionResourcesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductSubscriptionResourcesBy query = new FindProductSubscriptionResourcesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductSubscriptionResource> productSubscriptionResources =((ProductSubscriptionResourceFound) Scheduler.execute(query).data()).getProductSubscriptionResources();

		if (productSubscriptionResources.size() == 1) {
			return ResponseEntity.ok().body(productSubscriptionResources.get(0));
		}

		return ResponseEntity.ok().body(productSubscriptionResources);

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
	public ResponseEntity<Object> createProductSubscriptionResource(HttpServletRequest request) throws Exception {

		ProductSubscriptionResource productSubscriptionResourceToBeAdded = new ProductSubscriptionResource();
		try {
			productSubscriptionResourceToBeAdded = ProductSubscriptionResourceMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createProductSubscriptionResource(productSubscriptionResourceToBeAdded);

	}

	/**
	 * creates a new ProductSubscriptionResource entry in the ofbiz database
	 * 
	 * @param productSubscriptionResourceToBeAdded
	 *            the ProductSubscriptionResource thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createProductSubscriptionResource(@RequestBody ProductSubscriptionResource productSubscriptionResourceToBeAdded) throws Exception {

		AddProductSubscriptionResource command = new AddProductSubscriptionResource(productSubscriptionResourceToBeAdded);
		ProductSubscriptionResource productSubscriptionResource = ((ProductSubscriptionResourceAdded) Scheduler.execute(command).data()).getAddedProductSubscriptionResource();
		
		if (productSubscriptionResource != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(productSubscriptionResource);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProductSubscriptionResource could not be created.");
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
	public boolean updateProductSubscriptionResource(HttpServletRequest request) throws Exception {

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

		ProductSubscriptionResource productSubscriptionResourceToBeUpdated = new ProductSubscriptionResource();

		try {
			productSubscriptionResourceToBeUpdated = ProductSubscriptionResourceMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProductSubscriptionResource(productSubscriptionResourceToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ProductSubscriptionResource with the specific Id
	 * 
	 * @param productSubscriptionResourceToBeUpdated
	 *            the ProductSubscriptionResource thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateProductSubscriptionResource(@RequestBody ProductSubscriptionResource productSubscriptionResourceToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		productSubscriptionResourceToBeUpdated.setnull(null);

		UpdateProductSubscriptionResource command = new UpdateProductSubscriptionResource(productSubscriptionResourceToBeUpdated);

		try {
			if(((ProductSubscriptionResourceUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{productSubscriptionResourceId}")
	public ResponseEntity<Object> findById(@PathVariable String productSubscriptionResourceId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productSubscriptionResourceId", productSubscriptionResourceId);
		try {

			Object foundProductSubscriptionResource = findProductSubscriptionResourcesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProductSubscriptionResource);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{productSubscriptionResourceId}")
	public ResponseEntity<Object> deleteProductSubscriptionResourceByIdUpdated(@PathVariable String productSubscriptionResourceId) throws Exception {
		DeleteProductSubscriptionResource command = new DeleteProductSubscriptionResource(productSubscriptionResourceId);

		try {
			if (((ProductSubscriptionResourceDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductSubscriptionResource could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/productSubscriptionResource/\" plus one of the following: "
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
