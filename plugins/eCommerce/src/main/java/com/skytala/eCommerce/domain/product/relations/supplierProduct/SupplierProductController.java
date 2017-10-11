package com.skytala.eCommerce.domain.product.relations.supplierProduct;

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
import com.skytala.eCommerce.domain.product.relations.supplierProduct.command.AddSupplierProduct;
import com.skytala.eCommerce.domain.product.relations.supplierProduct.command.DeleteSupplierProduct;
import com.skytala.eCommerce.domain.product.relations.supplierProduct.command.UpdateSupplierProduct;
import com.skytala.eCommerce.domain.product.relations.supplierProduct.event.SupplierProductAdded;
import com.skytala.eCommerce.domain.product.relations.supplierProduct.event.SupplierProductDeleted;
import com.skytala.eCommerce.domain.product.relations.supplierProduct.event.SupplierProductFound;
import com.skytala.eCommerce.domain.product.relations.supplierProduct.event.SupplierProductUpdated;
import com.skytala.eCommerce.domain.product.relations.supplierProduct.mapper.SupplierProductMapper;
import com.skytala.eCommerce.domain.product.relations.supplierProduct.model.SupplierProduct;
import com.skytala.eCommerce.domain.product.relations.supplierProduct.query.FindSupplierProductsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/supplierProducts")
public class SupplierProductController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public SupplierProductController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a SupplierProduct
	 * @return a List with the SupplierProducts
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findSupplierProductsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindSupplierProductsBy query = new FindSupplierProductsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<SupplierProduct> supplierProducts =((SupplierProductFound) Scheduler.execute(query).data()).getSupplierProducts();

		if (supplierProducts.size() == 1) {
			return ResponseEntity.ok().body(supplierProducts.get(0));
		}

		return ResponseEntity.ok().body(supplierProducts);

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
	public ResponseEntity<Object> createSupplierProduct(HttpServletRequest request) throws Exception {

		SupplierProduct supplierProductToBeAdded = new SupplierProduct();
		try {
			supplierProductToBeAdded = SupplierProductMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createSupplierProduct(supplierProductToBeAdded);

	}

	/**
	 * creates a new SupplierProduct entry in the ofbiz database
	 * 
	 * @param supplierProductToBeAdded
	 *            the SupplierProduct thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createSupplierProduct(@RequestBody SupplierProduct supplierProductToBeAdded) throws Exception {

		AddSupplierProduct command = new AddSupplierProduct(supplierProductToBeAdded);
		SupplierProduct supplierProduct = ((SupplierProductAdded) Scheduler.execute(command).data()).getAddedSupplierProduct();
		
		if (supplierProduct != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(supplierProduct);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("SupplierProduct could not be created.");
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
	public boolean updateSupplierProduct(HttpServletRequest request) throws Exception {

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

		SupplierProduct supplierProductToBeUpdated = new SupplierProduct();

		try {
			supplierProductToBeUpdated = SupplierProductMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateSupplierProduct(supplierProductToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the SupplierProduct with the specific Id
	 * 
	 * @param supplierProductToBeUpdated
	 *            the SupplierProduct thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateSupplierProduct(@RequestBody SupplierProduct supplierProductToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		supplierProductToBeUpdated.setnull(null);

		UpdateSupplierProduct command = new UpdateSupplierProduct(supplierProductToBeUpdated);

		try {
			if(((SupplierProductUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{supplierProductId}")
	public ResponseEntity<Object> findById(@PathVariable String supplierProductId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("supplierProductId", supplierProductId);
		try {

			Object foundSupplierProduct = findSupplierProductsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundSupplierProduct);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{supplierProductId}")
	public ResponseEntity<Object> deleteSupplierProductByIdUpdated(@PathVariable String supplierProductId) throws Exception {
		DeleteSupplierProduct command = new DeleteSupplierProduct(supplierProductId);

		try {
			if (((SupplierProductDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("SupplierProduct could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/supplierProduct/\" plus one of the following: "
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
