package com.skytala.eCommerce.domain.product.relations.facility.control.product;

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
import com.skytala.eCommerce.domain.product.relations.facility.command.product.AddProductFacility;
import com.skytala.eCommerce.domain.product.relations.facility.command.product.DeleteProductFacility;
import com.skytala.eCommerce.domain.product.relations.facility.command.product.UpdateProductFacility;
import com.skytala.eCommerce.domain.product.relations.facility.event.product.ProductFacilityAdded;
import com.skytala.eCommerce.domain.product.relations.facility.event.product.ProductFacilityDeleted;
import com.skytala.eCommerce.domain.product.relations.facility.event.product.ProductFacilityFound;
import com.skytala.eCommerce.domain.product.relations.facility.event.product.ProductFacilityUpdated;
import com.skytala.eCommerce.domain.product.relations.facility.mapper.product.ProductFacilityMapper;
import com.skytala.eCommerce.domain.product.relations.facility.model.product.ProductFacility;
import com.skytala.eCommerce.domain.product.relations.facility.query.product.FindProductFacilitysBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@CrossOrigin
@RequestMapping("/productFacilitys")
public class ProductFacilityController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductFacilityController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductFacility
	 * @return a List with the ProductFacilitys
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findProductFacilitysBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductFacilitysBy query = new FindProductFacilitysBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductFacility> productFacilitys =((ProductFacilityFound) Scheduler.execute(query).data()).getProductFacilitys();

		if (productFacilitys.size() == 1) {
			return ResponseEntity.ok().body(productFacilitys.get(0));
		}

		return ResponseEntity.ok().body(productFacilitys);

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
	public ResponseEntity<Object> createProductFacility(HttpServletRequest request) throws Exception {

		ProductFacility productFacilityToBeAdded = new ProductFacility();
		try {
			productFacilityToBeAdded = ProductFacilityMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createProductFacility(productFacilityToBeAdded);

	}

	/**
	 * creates a new ProductFacility entry in the ofbiz database
	 * 
	 * @param productFacilityToBeAdded
	 *            the ProductFacility thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createProductFacility(@RequestBody ProductFacility productFacilityToBeAdded) throws Exception {

		AddProductFacility command = new AddProductFacility(productFacilityToBeAdded);
		ProductFacility productFacility = ((ProductFacilityAdded) Scheduler.execute(command).data()).getAddedProductFacility();
		
		if (productFacility != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(productFacility);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProductFacility could not be created.");
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
	public boolean updateProductFacility(HttpServletRequest request) throws Exception {

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

		ProductFacility productFacilityToBeUpdated = new ProductFacility();

		try {
			productFacilityToBeUpdated = ProductFacilityMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProductFacility(productFacilityToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ProductFacility with the specific Id
	 * 
	 * @param productFacilityToBeUpdated
	 *            the ProductFacility thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateProductFacility(@RequestBody ProductFacility productFacilityToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		productFacilityToBeUpdated.setnull(null);

		UpdateProductFacility command = new UpdateProductFacility(productFacilityToBeUpdated);

		try {
			if(((ProductFacilityUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{productFacilityId}")
	public ResponseEntity<Object> findById(@PathVariable String productFacilityId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productFacilityId", productFacilityId);
		try {

			Object foundProductFacility = findProductFacilitysBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProductFacility);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{productFacilityId}")
	public ResponseEntity<Object> deleteProductFacilityByIdUpdated(@PathVariable String productFacilityId) throws Exception {
		DeleteProductFacility command = new DeleteProductFacility(productFacilityId);

		try {
			if (((ProductFacilityDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductFacility could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/productFacility/\" plus one of the following: "
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
