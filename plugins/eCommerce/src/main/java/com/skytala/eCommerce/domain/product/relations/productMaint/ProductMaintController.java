package com.skytala.eCommerce.domain.product.relations.productMaint;

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
import com.skytala.eCommerce.domain.product.relations.productMaint.command.AddProductMaint;
import com.skytala.eCommerce.domain.product.relations.productMaint.command.DeleteProductMaint;
import com.skytala.eCommerce.domain.product.relations.productMaint.command.UpdateProductMaint;
import com.skytala.eCommerce.domain.product.relations.productMaint.event.ProductMaintAdded;
import com.skytala.eCommerce.domain.product.relations.productMaint.event.ProductMaintDeleted;
import com.skytala.eCommerce.domain.product.relations.productMaint.event.ProductMaintFound;
import com.skytala.eCommerce.domain.product.relations.productMaint.event.ProductMaintUpdated;
import com.skytala.eCommerce.domain.product.relations.productMaint.mapper.ProductMaintMapper;
import com.skytala.eCommerce.domain.product.relations.productMaint.model.ProductMaint;
import com.skytala.eCommerce.domain.product.relations.productMaint.query.FindProductMaintsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/productMaints")
public class ProductMaintController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductMaintController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductMaint
	 * @return a List with the ProductMaints
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findProductMaintsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductMaintsBy query = new FindProductMaintsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductMaint> productMaints =((ProductMaintFound) Scheduler.execute(query).data()).getProductMaints();

		if (productMaints.size() == 1) {
			return ResponseEntity.ok().body(productMaints.get(0));
		}

		return ResponseEntity.ok().body(productMaints);

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
	public ResponseEntity<Object> createProductMaint(HttpServletRequest request) throws Exception {

		ProductMaint productMaintToBeAdded = new ProductMaint();
		try {
			productMaintToBeAdded = ProductMaintMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createProductMaint(productMaintToBeAdded);

	}

	/**
	 * creates a new ProductMaint entry in the ofbiz database
	 * 
	 * @param productMaintToBeAdded
	 *            the ProductMaint thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createProductMaint(@RequestBody ProductMaint productMaintToBeAdded) throws Exception {

		AddProductMaint command = new AddProductMaint(productMaintToBeAdded);
		ProductMaint productMaint = ((ProductMaintAdded) Scheduler.execute(command).data()).getAddedProductMaint();
		
		if (productMaint != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(productMaint);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProductMaint could not be created.");
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
	public boolean updateProductMaint(HttpServletRequest request) throws Exception {

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

		ProductMaint productMaintToBeUpdated = new ProductMaint();

		try {
			productMaintToBeUpdated = ProductMaintMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProductMaint(productMaintToBeUpdated, productMaintToBeUpdated.getProductMaintSeqId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ProductMaint with the specific Id
	 * 
	 * @param productMaintToBeUpdated
	 *            the ProductMaint thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{productMaintSeqId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateProductMaint(@RequestBody ProductMaint productMaintToBeUpdated,
			@PathVariable String productMaintSeqId) throws Exception {

		productMaintToBeUpdated.setProductMaintSeqId(productMaintSeqId);

		UpdateProductMaint command = new UpdateProductMaint(productMaintToBeUpdated);

		try {
			if(((ProductMaintUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{productMaintId}")
	public ResponseEntity<Object> findById(@PathVariable String productMaintId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productMaintId", productMaintId);
		try {

			Object foundProductMaint = findProductMaintsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProductMaint);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{productMaintId}")
	public ResponseEntity<Object> deleteProductMaintByIdUpdated(@PathVariable String productMaintId) throws Exception {
		DeleteProductMaint command = new DeleteProductMaint(productMaintId);

		try {
			if (((ProductMaintDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductMaint could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/productMaint/\" plus one of the following: "
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