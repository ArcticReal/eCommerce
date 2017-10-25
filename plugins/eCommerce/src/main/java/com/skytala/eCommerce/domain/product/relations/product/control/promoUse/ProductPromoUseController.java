package com.skytala.eCommerce.domain.product.relations.product.control.promoUse;

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
import com.skytala.eCommerce.domain.product.relations.product.command.promoUse.AddProductPromoUse;
import com.skytala.eCommerce.domain.product.relations.product.command.promoUse.DeleteProductPromoUse;
import com.skytala.eCommerce.domain.product.relations.product.command.promoUse.UpdateProductPromoUse;
import com.skytala.eCommerce.domain.product.relations.product.event.promoUse.ProductPromoUseAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.promoUse.ProductPromoUseDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.promoUse.ProductPromoUseFound;
import com.skytala.eCommerce.domain.product.relations.product.event.promoUse.ProductPromoUseUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.promoUse.ProductPromoUseMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.promoUse.ProductPromoUse;
import com.skytala.eCommerce.domain.product.relations.product.query.promoUse.FindProductPromoUsesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/productPromoUses")
public class ProductPromoUseController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductPromoUseController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductPromoUse
	 * @return a List with the ProductPromoUses
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findProductPromoUsesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductPromoUsesBy query = new FindProductPromoUsesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductPromoUse> productPromoUses =((ProductPromoUseFound) Scheduler.execute(query).data()).getProductPromoUses();

		if (productPromoUses.size() == 1) {
			return ResponseEntity.ok().body(productPromoUses.get(0));
		}

		return ResponseEntity.ok().body(productPromoUses);

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
	public ResponseEntity<Object> createProductPromoUse(HttpServletRequest request) throws Exception {

		ProductPromoUse productPromoUseToBeAdded = new ProductPromoUse();
		try {
			productPromoUseToBeAdded = ProductPromoUseMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createProductPromoUse(productPromoUseToBeAdded);

	}

	/**
	 * creates a new ProductPromoUse entry in the ofbiz database
	 * 
	 * @param productPromoUseToBeAdded
	 *            the ProductPromoUse thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createProductPromoUse(@RequestBody ProductPromoUse productPromoUseToBeAdded) throws Exception {

		AddProductPromoUse command = new AddProductPromoUse(productPromoUseToBeAdded);
		ProductPromoUse productPromoUse = ((ProductPromoUseAdded) Scheduler.execute(command).data()).getAddedProductPromoUse();
		
		if (productPromoUse != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(productPromoUse);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProductPromoUse could not be created.");
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
	public boolean updateProductPromoUse(HttpServletRequest request) throws Exception {

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

		ProductPromoUse productPromoUseToBeUpdated = new ProductPromoUse();

		try {
			productPromoUseToBeUpdated = ProductPromoUseMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProductPromoUse(productPromoUseToBeUpdated, productPromoUseToBeUpdated.getPromoSequenceId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ProductPromoUse with the specific Id
	 * 
	 * @param productPromoUseToBeUpdated
	 *            the ProductPromoUse thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{promoSequenceId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateProductPromoUse(@RequestBody ProductPromoUse productPromoUseToBeUpdated,
			@PathVariable String promoSequenceId) throws Exception {

		productPromoUseToBeUpdated.setPromoSequenceId(promoSequenceId);

		UpdateProductPromoUse command = new UpdateProductPromoUse(productPromoUseToBeUpdated);

		try {
			if(((ProductPromoUseUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{productPromoUseId}")
	public ResponseEntity<Object> findById(@PathVariable String productPromoUseId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productPromoUseId", productPromoUseId);
		try {

			Object foundProductPromoUse = findProductPromoUsesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProductPromoUse);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{productPromoUseId}")
	public ResponseEntity<Object> deleteProductPromoUseByIdUpdated(@PathVariable String productPromoUseId) throws Exception {
		DeleteProductPromoUse command = new DeleteProductPromoUse(productPromoUseId);

		try {
			if (((ProductPromoUseDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductPromoUse could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/productPromoUse/\" plus one of the following: "
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
