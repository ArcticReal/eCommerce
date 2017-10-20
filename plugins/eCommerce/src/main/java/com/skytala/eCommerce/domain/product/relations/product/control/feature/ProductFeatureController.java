package com.skytala.eCommerce.domain.product.relations.product.control.feature;

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
import com.skytala.eCommerce.domain.product.relations.product.command.feature.AddProductFeature;
import com.skytala.eCommerce.domain.product.relations.product.command.feature.DeleteProductFeature;
import com.skytala.eCommerce.domain.product.relations.product.command.feature.UpdateProductFeature;
import com.skytala.eCommerce.domain.product.relations.product.event.feature.ProductFeatureAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.feature.ProductFeatureDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.feature.ProductFeatureFound;
import com.skytala.eCommerce.domain.product.relations.product.event.feature.ProductFeatureUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.feature.ProductFeatureMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.feature.ProductFeature;
import com.skytala.eCommerce.domain.product.relations.product.query.feature.FindProductFeaturesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@CrossOrigin
@RequestMapping("/productFeatures")
public class ProductFeatureController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductFeatureController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductFeature
	 * @return a List with the ProductFeatures
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findProductFeaturesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductFeaturesBy query = new FindProductFeaturesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductFeature> productFeatures =((ProductFeatureFound) Scheduler.execute(query).data()).getProductFeatures();

		if (productFeatures.size() == 1) {
			return ResponseEntity.ok().body(productFeatures.get(0));
		}

		return ResponseEntity.ok().body(productFeatures);

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
	public ResponseEntity<Object> createProductFeature(HttpServletRequest request) throws Exception {

		ProductFeature productFeatureToBeAdded = new ProductFeature();
		try {
			productFeatureToBeAdded = ProductFeatureMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createProductFeature(productFeatureToBeAdded);

	}

	/**
	 * creates a new ProductFeature entry in the ofbiz database
	 * 
	 * @param productFeatureToBeAdded
	 *            the ProductFeature thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createProductFeature(@RequestBody ProductFeature productFeatureToBeAdded) throws Exception {

		AddProductFeature command = new AddProductFeature(productFeatureToBeAdded);
		ProductFeature productFeature = ((ProductFeatureAdded) Scheduler.execute(command).data()).getAddedProductFeature();
		
		if (productFeature != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(productFeature);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProductFeature could not be created.");
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
	public boolean updateProductFeature(HttpServletRequest request) throws Exception {

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

		ProductFeature productFeatureToBeUpdated = new ProductFeature();

		try {
			productFeatureToBeUpdated = ProductFeatureMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProductFeature(productFeatureToBeUpdated, productFeatureToBeUpdated.getProductFeatureId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ProductFeature with the specific Id
	 * 
	 * @param productFeatureToBeUpdated
	 *            the ProductFeature thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{productFeatureId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateProductFeature(@RequestBody ProductFeature productFeatureToBeUpdated,
			@PathVariable String productFeatureId) throws Exception {

		productFeatureToBeUpdated.setProductFeatureId(productFeatureId);

		UpdateProductFeature command = new UpdateProductFeature(productFeatureToBeUpdated);

		try {
			if(((ProductFeatureUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{productFeatureId}")
	public ResponseEntity<Object> findById(@PathVariable String productFeatureId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productFeatureId", productFeatureId);
		try {

			Object foundProductFeature = findProductFeaturesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProductFeature);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{productFeatureId}")
	public ResponseEntity<Object> deleteProductFeatureByIdUpdated(@PathVariable String productFeatureId) throws Exception {
		DeleteProductFeature command = new DeleteProductFeature(productFeatureId);

		try {
			if (((ProductFeatureDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductFeature could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/productFeature/\" plus one of the following: "
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
