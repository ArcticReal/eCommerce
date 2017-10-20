package com.skytala.eCommerce.domain.product.relations.product.control.storeShipmentMeth;

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
import com.skytala.eCommerce.domain.product.relations.product.command.storeShipmentMeth.AddProductStoreShipmentMeth;
import com.skytala.eCommerce.domain.product.relations.product.command.storeShipmentMeth.DeleteProductStoreShipmentMeth;
import com.skytala.eCommerce.domain.product.relations.product.command.storeShipmentMeth.UpdateProductStoreShipmentMeth;
import com.skytala.eCommerce.domain.product.relations.product.event.storeShipmentMeth.ProductStoreShipmentMethAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.storeShipmentMeth.ProductStoreShipmentMethDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.storeShipmentMeth.ProductStoreShipmentMethFound;
import com.skytala.eCommerce.domain.product.relations.product.event.storeShipmentMeth.ProductStoreShipmentMethUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.storeShipmentMeth.ProductStoreShipmentMethMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.storeShipmentMeth.ProductStoreShipmentMeth;
import com.skytala.eCommerce.domain.product.relations.product.query.storeShipmentMeth.FindProductStoreShipmentMethsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@CrossOrigin
@RequestMapping("/productStoreShipmentMeths")
public class ProductStoreShipmentMethController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductStoreShipmentMethController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductStoreShipmentMeth
	 * @return a List with the ProductStoreShipmentMeths
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findProductStoreShipmentMethsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductStoreShipmentMethsBy query = new FindProductStoreShipmentMethsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductStoreShipmentMeth> productStoreShipmentMeths =((ProductStoreShipmentMethFound) Scheduler.execute(query).data()).getProductStoreShipmentMeths();

		if (productStoreShipmentMeths.size() == 1) {
			return ResponseEntity.ok().body(productStoreShipmentMeths.get(0));
		}

		return ResponseEntity.ok().body(productStoreShipmentMeths);

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
	public ResponseEntity<Object> createProductStoreShipmentMeth(HttpServletRequest request) throws Exception {

		ProductStoreShipmentMeth productStoreShipmentMethToBeAdded = new ProductStoreShipmentMeth();
		try {
			productStoreShipmentMethToBeAdded = ProductStoreShipmentMethMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createProductStoreShipmentMeth(productStoreShipmentMethToBeAdded);

	}

	/**
	 * creates a new ProductStoreShipmentMeth entry in the ofbiz database
	 * 
	 * @param productStoreShipmentMethToBeAdded
	 *            the ProductStoreShipmentMeth thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createProductStoreShipmentMeth(@RequestBody ProductStoreShipmentMeth productStoreShipmentMethToBeAdded) throws Exception {

		AddProductStoreShipmentMeth command = new AddProductStoreShipmentMeth(productStoreShipmentMethToBeAdded);
		ProductStoreShipmentMeth productStoreShipmentMeth = ((ProductStoreShipmentMethAdded) Scheduler.execute(command).data()).getAddedProductStoreShipmentMeth();
		
		if (productStoreShipmentMeth != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(productStoreShipmentMeth);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProductStoreShipmentMeth could not be created.");
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
	public boolean updateProductStoreShipmentMeth(HttpServletRequest request) throws Exception {

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

		ProductStoreShipmentMeth productStoreShipmentMethToBeUpdated = new ProductStoreShipmentMeth();

		try {
			productStoreShipmentMethToBeUpdated = ProductStoreShipmentMethMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProductStoreShipmentMeth(productStoreShipmentMethToBeUpdated, productStoreShipmentMethToBeUpdated.getProductStoreShipMethId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ProductStoreShipmentMeth with the specific Id
	 * 
	 * @param productStoreShipmentMethToBeUpdated
	 *            the ProductStoreShipmentMeth thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{productStoreShipMethId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateProductStoreShipmentMeth(@RequestBody ProductStoreShipmentMeth productStoreShipmentMethToBeUpdated,
			@PathVariable String productStoreShipMethId) throws Exception {

		productStoreShipmentMethToBeUpdated.setProductStoreShipMethId(productStoreShipMethId);

		UpdateProductStoreShipmentMeth command = new UpdateProductStoreShipmentMeth(productStoreShipmentMethToBeUpdated);

		try {
			if(((ProductStoreShipmentMethUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{productStoreShipmentMethId}")
	public ResponseEntity<Object> findById(@PathVariable String productStoreShipmentMethId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productStoreShipmentMethId", productStoreShipmentMethId);
		try {

			Object foundProductStoreShipmentMeth = findProductStoreShipmentMethsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProductStoreShipmentMeth);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{productStoreShipmentMethId}")
	public ResponseEntity<Object> deleteProductStoreShipmentMethByIdUpdated(@PathVariable String productStoreShipmentMethId) throws Exception {
		DeleteProductStoreShipmentMeth command = new DeleteProductStoreShipmentMeth(productStoreShipmentMethId);

		try {
			if (((ProductStoreShipmentMethDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductStoreShipmentMeth could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/productStoreShipmentMeth/\" plus one of the following: "
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
