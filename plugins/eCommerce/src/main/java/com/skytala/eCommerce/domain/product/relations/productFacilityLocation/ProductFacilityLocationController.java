package com.skytala.eCommerce.domain.product.relations.productFacilityLocation;

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
import com.skytala.eCommerce.domain.product.relations.productFacilityLocation.command.AddProductFacilityLocation;
import com.skytala.eCommerce.domain.product.relations.productFacilityLocation.command.DeleteProductFacilityLocation;
import com.skytala.eCommerce.domain.product.relations.productFacilityLocation.command.UpdateProductFacilityLocation;
import com.skytala.eCommerce.domain.product.relations.productFacilityLocation.event.ProductFacilityLocationAdded;
import com.skytala.eCommerce.domain.product.relations.productFacilityLocation.event.ProductFacilityLocationDeleted;
import com.skytala.eCommerce.domain.product.relations.productFacilityLocation.event.ProductFacilityLocationFound;
import com.skytala.eCommerce.domain.product.relations.productFacilityLocation.event.ProductFacilityLocationUpdated;
import com.skytala.eCommerce.domain.product.relations.productFacilityLocation.mapper.ProductFacilityLocationMapper;
import com.skytala.eCommerce.domain.product.relations.productFacilityLocation.model.ProductFacilityLocation;
import com.skytala.eCommerce.domain.product.relations.productFacilityLocation.query.FindProductFacilityLocationsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/productFacilityLocations")
public class ProductFacilityLocationController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductFacilityLocationController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductFacilityLocation
	 * @return a List with the ProductFacilityLocations
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findProductFacilityLocationsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductFacilityLocationsBy query = new FindProductFacilityLocationsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductFacilityLocation> productFacilityLocations =((ProductFacilityLocationFound) Scheduler.execute(query).data()).getProductFacilityLocations();

		if (productFacilityLocations.size() == 1) {
			return ResponseEntity.ok().body(productFacilityLocations.get(0));
		}

		return ResponseEntity.ok().body(productFacilityLocations);

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
	public ResponseEntity<Object> createProductFacilityLocation(HttpServletRequest request) throws Exception {

		ProductFacilityLocation productFacilityLocationToBeAdded = new ProductFacilityLocation();
		try {
			productFacilityLocationToBeAdded = ProductFacilityLocationMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createProductFacilityLocation(productFacilityLocationToBeAdded);

	}

	/**
	 * creates a new ProductFacilityLocation entry in the ofbiz database
	 * 
	 * @param productFacilityLocationToBeAdded
	 *            the ProductFacilityLocation thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createProductFacilityLocation(@RequestBody ProductFacilityLocation productFacilityLocationToBeAdded) throws Exception {

		AddProductFacilityLocation command = new AddProductFacilityLocation(productFacilityLocationToBeAdded);
		ProductFacilityLocation productFacilityLocation = ((ProductFacilityLocationAdded) Scheduler.execute(command).data()).getAddedProductFacilityLocation();
		
		if (productFacilityLocation != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(productFacilityLocation);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProductFacilityLocation could not be created.");
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
	public boolean updateProductFacilityLocation(HttpServletRequest request) throws Exception {

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

		ProductFacilityLocation productFacilityLocationToBeUpdated = new ProductFacilityLocation();

		try {
			productFacilityLocationToBeUpdated = ProductFacilityLocationMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProductFacilityLocation(productFacilityLocationToBeUpdated, productFacilityLocationToBeUpdated.getLocationSeqId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ProductFacilityLocation with the specific Id
	 * 
	 * @param productFacilityLocationToBeUpdated
	 *            the ProductFacilityLocation thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{locationSeqId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateProductFacilityLocation(@RequestBody ProductFacilityLocation productFacilityLocationToBeUpdated,
			@PathVariable String locationSeqId) throws Exception {

		productFacilityLocationToBeUpdated.setLocationSeqId(locationSeqId);

		UpdateProductFacilityLocation command = new UpdateProductFacilityLocation(productFacilityLocationToBeUpdated);

		try {
			if(((ProductFacilityLocationUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{productFacilityLocationId}")
	public ResponseEntity<Object> findById(@PathVariable String productFacilityLocationId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productFacilityLocationId", productFacilityLocationId);
		try {

			Object foundProductFacilityLocation = findProductFacilityLocationsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProductFacilityLocation);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{productFacilityLocationId}")
	public ResponseEntity<Object> deleteProductFacilityLocationByIdUpdated(@PathVariable String productFacilityLocationId) throws Exception {
		DeleteProductFacilityLocation command = new DeleteProductFacilityLocation(productFacilityLocationId);

		try {
			if (((ProductFacilityLocationDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductFacilityLocation could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/productFacilityLocation/\" plus one of the following: "
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
