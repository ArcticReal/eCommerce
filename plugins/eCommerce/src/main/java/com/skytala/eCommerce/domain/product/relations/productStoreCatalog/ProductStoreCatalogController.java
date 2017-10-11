package com.skytala.eCommerce.domain.product.relations.productStoreCatalog;

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
import com.skytala.eCommerce.domain.product.relations.productStoreCatalog.command.AddProductStoreCatalog;
import com.skytala.eCommerce.domain.product.relations.productStoreCatalog.command.DeleteProductStoreCatalog;
import com.skytala.eCommerce.domain.product.relations.productStoreCatalog.command.UpdateProductStoreCatalog;
import com.skytala.eCommerce.domain.product.relations.productStoreCatalog.event.ProductStoreCatalogAdded;
import com.skytala.eCommerce.domain.product.relations.productStoreCatalog.event.ProductStoreCatalogDeleted;
import com.skytala.eCommerce.domain.product.relations.productStoreCatalog.event.ProductStoreCatalogFound;
import com.skytala.eCommerce.domain.product.relations.productStoreCatalog.event.ProductStoreCatalogUpdated;
import com.skytala.eCommerce.domain.product.relations.productStoreCatalog.mapper.ProductStoreCatalogMapper;
import com.skytala.eCommerce.domain.product.relations.productStoreCatalog.model.ProductStoreCatalog;
import com.skytala.eCommerce.domain.product.relations.productStoreCatalog.query.FindProductStoreCatalogsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/productStoreCatalogs")
public class ProductStoreCatalogController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductStoreCatalogController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductStoreCatalog
	 * @return a List with the ProductStoreCatalogs
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findProductStoreCatalogsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductStoreCatalogsBy query = new FindProductStoreCatalogsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductStoreCatalog> productStoreCatalogs =((ProductStoreCatalogFound) Scheduler.execute(query).data()).getProductStoreCatalogs();

		if (productStoreCatalogs.size() == 1) {
			return ResponseEntity.ok().body(productStoreCatalogs.get(0));
		}

		return ResponseEntity.ok().body(productStoreCatalogs);

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
	public ResponseEntity<Object> createProductStoreCatalog(HttpServletRequest request) throws Exception {

		ProductStoreCatalog productStoreCatalogToBeAdded = new ProductStoreCatalog();
		try {
			productStoreCatalogToBeAdded = ProductStoreCatalogMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createProductStoreCatalog(productStoreCatalogToBeAdded);

	}

	/**
	 * creates a new ProductStoreCatalog entry in the ofbiz database
	 * 
	 * @param productStoreCatalogToBeAdded
	 *            the ProductStoreCatalog thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createProductStoreCatalog(@RequestBody ProductStoreCatalog productStoreCatalogToBeAdded) throws Exception {

		AddProductStoreCatalog command = new AddProductStoreCatalog(productStoreCatalogToBeAdded);
		ProductStoreCatalog productStoreCatalog = ((ProductStoreCatalogAdded) Scheduler.execute(command).data()).getAddedProductStoreCatalog();
		
		if (productStoreCatalog != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(productStoreCatalog);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProductStoreCatalog could not be created.");
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
	public boolean updateProductStoreCatalog(HttpServletRequest request) throws Exception {

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

		ProductStoreCatalog productStoreCatalogToBeUpdated = new ProductStoreCatalog();

		try {
			productStoreCatalogToBeUpdated = ProductStoreCatalogMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProductStoreCatalog(productStoreCatalogToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ProductStoreCatalog with the specific Id
	 * 
	 * @param productStoreCatalogToBeUpdated
	 *            the ProductStoreCatalog thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateProductStoreCatalog(@RequestBody ProductStoreCatalog productStoreCatalogToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		productStoreCatalogToBeUpdated.setnull(null);

		UpdateProductStoreCatalog command = new UpdateProductStoreCatalog(productStoreCatalogToBeUpdated);

		try {
			if(((ProductStoreCatalogUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{productStoreCatalogId}")
	public ResponseEntity<Object> findById(@PathVariable String productStoreCatalogId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productStoreCatalogId", productStoreCatalogId);
		try {

			Object foundProductStoreCatalog = findProductStoreCatalogsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProductStoreCatalog);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{productStoreCatalogId}")
	public ResponseEntity<Object> deleteProductStoreCatalogByIdUpdated(@PathVariable String productStoreCatalogId) throws Exception {
		DeleteProductStoreCatalog command = new DeleteProductStoreCatalog(productStoreCatalogId);

		try {
			if (((ProductStoreCatalogDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductStoreCatalog could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/productStoreCatalog/\" plus one of the following: "
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
