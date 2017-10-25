package com.skytala.eCommerce.domain.product.relations.product.control.geo;

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
import com.skytala.eCommerce.domain.product.relations.product.command.geo.AddProductGeo;
import com.skytala.eCommerce.domain.product.relations.product.command.geo.DeleteProductGeo;
import com.skytala.eCommerce.domain.product.relations.product.command.geo.UpdateProductGeo;
import com.skytala.eCommerce.domain.product.relations.product.event.geo.ProductGeoAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.geo.ProductGeoDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.geo.ProductGeoFound;
import com.skytala.eCommerce.domain.product.relations.product.event.geo.ProductGeoUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.geo.ProductGeoMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.geo.ProductGeo;
import com.skytala.eCommerce.domain.product.relations.product.query.geo.FindProductGeosBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/productGeos")
public class ProductGeoController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductGeoController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductGeo
	 * @return a List with the ProductGeos
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findProductGeosBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductGeosBy query = new FindProductGeosBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductGeo> productGeos =((ProductGeoFound) Scheduler.execute(query).data()).getProductGeos();

		if (productGeos.size() == 1) {
			return ResponseEntity.ok().body(productGeos.get(0));
		}

		return ResponseEntity.ok().body(productGeos);

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
	public ResponseEntity<Object> createProductGeo(HttpServletRequest request) throws Exception {

		ProductGeo productGeoToBeAdded = new ProductGeo();
		try {
			productGeoToBeAdded = ProductGeoMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createProductGeo(productGeoToBeAdded);

	}

	/**
	 * creates a new ProductGeo entry in the ofbiz database
	 * 
	 * @param productGeoToBeAdded
	 *            the ProductGeo thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createProductGeo(@RequestBody ProductGeo productGeoToBeAdded) throws Exception {

		AddProductGeo command = new AddProductGeo(productGeoToBeAdded);
		ProductGeo productGeo = ((ProductGeoAdded) Scheduler.execute(command).data()).getAddedProductGeo();
		
		if (productGeo != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(productGeo);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProductGeo could not be created.");
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
	public boolean updateProductGeo(HttpServletRequest request) throws Exception {

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

		ProductGeo productGeoToBeUpdated = new ProductGeo();

		try {
			productGeoToBeUpdated = ProductGeoMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProductGeo(productGeoToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ProductGeo with the specific Id
	 * 
	 * @param productGeoToBeUpdated
	 *            the ProductGeo thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateProductGeo(@RequestBody ProductGeo productGeoToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		productGeoToBeUpdated.setnull(null);

		UpdateProductGeo command = new UpdateProductGeo(productGeoToBeUpdated);

		try {
			if(((ProductGeoUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{productGeoId}")
	public ResponseEntity<Object> findById(@PathVariable String productGeoId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productGeoId", productGeoId);
		try {

			Object foundProductGeo = findProductGeosBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProductGeo);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{productGeoId}")
	public ResponseEntity<Object> deleteProductGeoByIdUpdated(@PathVariable String productGeoId) throws Exception {
		DeleteProductGeo command = new DeleteProductGeo(productGeoId);

		try {
			if (((ProductGeoDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductGeo could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/productGeo/\" plus one of the following: "
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
