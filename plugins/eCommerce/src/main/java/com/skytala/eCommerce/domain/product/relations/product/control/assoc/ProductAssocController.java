package com.skytala.eCommerce.domain.product.relations.product.control.assoc;

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
import com.skytala.eCommerce.domain.product.relations.product.command.assoc.AddProductAssoc;
import com.skytala.eCommerce.domain.product.relations.product.command.assoc.DeleteProductAssoc;
import com.skytala.eCommerce.domain.product.relations.product.command.assoc.UpdateProductAssoc;
import com.skytala.eCommerce.domain.product.relations.product.event.assoc.ProductAssocAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.assoc.ProductAssocDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.assoc.ProductAssocFound;
import com.skytala.eCommerce.domain.product.relations.product.event.assoc.ProductAssocUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.assoc.ProductAssocMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.assoc.ProductAssoc;
import com.skytala.eCommerce.domain.product.relations.product.query.assoc.FindProductAssocsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/productAssocs")
public class ProductAssocController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductAssocController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductAssoc
	 * @return a List with the ProductAssocs
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findProductAssocsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductAssocsBy query = new FindProductAssocsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductAssoc> productAssocs =((ProductAssocFound) Scheduler.execute(query).data()).getProductAssocs();

		if (productAssocs.size() == 1) {
			return ResponseEntity.ok().body(productAssocs.get(0));
		}

		return ResponseEntity.ok().body(productAssocs);

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
	public ResponseEntity<Object> createProductAssoc(HttpServletRequest request) throws Exception {

		ProductAssoc productAssocToBeAdded = new ProductAssoc();
		try {
			productAssocToBeAdded = ProductAssocMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createProductAssoc(productAssocToBeAdded);

	}

	/**
	 * creates a new ProductAssoc entry in the ofbiz database
	 * 
	 * @param productAssocToBeAdded
	 *            the ProductAssoc thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createProductAssoc(@RequestBody ProductAssoc productAssocToBeAdded) throws Exception {

		AddProductAssoc command = new AddProductAssoc(productAssocToBeAdded);
		ProductAssoc productAssoc = ((ProductAssocAdded) Scheduler.execute(command).data()).getAddedProductAssoc();
		
		if (productAssoc != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(productAssoc);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProductAssoc could not be created.");
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
	public boolean updateProductAssoc(HttpServletRequest request) throws Exception {

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

		ProductAssoc productAssocToBeUpdated = new ProductAssoc();

		try {
			productAssocToBeUpdated = ProductAssocMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProductAssoc(productAssocToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ProductAssoc with the specific Id
	 * 
	 * @param productAssocToBeUpdated
	 *            the ProductAssoc thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateProductAssoc(@RequestBody ProductAssoc productAssocToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		productAssocToBeUpdated.setnull(null);

		UpdateProductAssoc command = new UpdateProductAssoc(productAssocToBeUpdated);

		try {
			if(((ProductAssocUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{productAssocId}")
	public ResponseEntity<Object> findById(@PathVariable String productAssocId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productAssocId", productAssocId);
		try {

			Object foundProductAssoc = findProductAssocsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProductAssoc);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{productAssocId}")
	public ResponseEntity<Object> deleteProductAssocByIdUpdated(@PathVariable String productAssocId) throws Exception {
		DeleteProductAssoc command = new DeleteProductAssoc(productAssocId);

		try {
			if (((ProductAssocDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductAssoc could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/productAssoc/\" plus one of the following: "
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
