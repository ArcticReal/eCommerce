package com.skytala.eCommerce.domain.product.relations.product.control.promoCode;

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
import com.skytala.eCommerce.domain.product.relations.product.command.promoCode.AddProductPromoCode;
import com.skytala.eCommerce.domain.product.relations.product.command.promoCode.DeleteProductPromoCode;
import com.skytala.eCommerce.domain.product.relations.product.command.promoCode.UpdateProductPromoCode;
import com.skytala.eCommerce.domain.product.relations.product.event.promoCode.ProductPromoCodeAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.promoCode.ProductPromoCodeDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.promoCode.ProductPromoCodeFound;
import com.skytala.eCommerce.domain.product.relations.product.event.promoCode.ProductPromoCodeUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.promoCode.ProductPromoCodeMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.promoCode.ProductPromoCode;
import com.skytala.eCommerce.domain.product.relations.product.query.promoCode.FindProductPromoCodesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/productPromoCodes")
public class ProductPromoCodeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductPromoCodeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductPromoCode
	 * @return a List with the ProductPromoCodes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findProductPromoCodesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductPromoCodesBy query = new FindProductPromoCodesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductPromoCode> productPromoCodes =((ProductPromoCodeFound) Scheduler.execute(query).data()).getProductPromoCodes();

		if (productPromoCodes.size() == 1) {
			return ResponseEntity.ok().body(productPromoCodes.get(0));
		}

		return ResponseEntity.ok().body(productPromoCodes);

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
	public ResponseEntity<Object> createProductPromoCode(HttpServletRequest request) throws Exception {

		ProductPromoCode productPromoCodeToBeAdded = new ProductPromoCode();
		try {
			productPromoCodeToBeAdded = ProductPromoCodeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createProductPromoCode(productPromoCodeToBeAdded);

	}

	/**
	 * creates a new ProductPromoCode entry in the ofbiz database
	 * 
	 * @param productPromoCodeToBeAdded
	 *            the ProductPromoCode thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createProductPromoCode(@RequestBody ProductPromoCode productPromoCodeToBeAdded) throws Exception {

		AddProductPromoCode command = new AddProductPromoCode(productPromoCodeToBeAdded);
		ProductPromoCode productPromoCode = ((ProductPromoCodeAdded) Scheduler.execute(command).data()).getAddedProductPromoCode();
		
		if (productPromoCode != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(productPromoCode);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProductPromoCode could not be created.");
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
	public boolean updateProductPromoCode(HttpServletRequest request) throws Exception {

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

		ProductPromoCode productPromoCodeToBeUpdated = new ProductPromoCode();

		try {
			productPromoCodeToBeUpdated = ProductPromoCodeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProductPromoCode(productPromoCodeToBeUpdated, productPromoCodeToBeUpdated.getProductPromoCodeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ProductPromoCode with the specific Id
	 * 
	 * @param productPromoCodeToBeUpdated
	 *            the ProductPromoCode thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{productPromoCodeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateProductPromoCode(@RequestBody ProductPromoCode productPromoCodeToBeUpdated,
			@PathVariable String productPromoCodeId) throws Exception {

		productPromoCodeToBeUpdated.setProductPromoCodeId(productPromoCodeId);

		UpdateProductPromoCode command = new UpdateProductPromoCode(productPromoCodeToBeUpdated);

		try {
			if(((ProductPromoCodeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{productPromoCodeId}")
	public ResponseEntity<Object> findById(@PathVariable String productPromoCodeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productPromoCodeId", productPromoCodeId);
		try {

			Object foundProductPromoCode = findProductPromoCodesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProductPromoCode);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{productPromoCodeId}")
	public ResponseEntity<Object> deleteProductPromoCodeByIdUpdated(@PathVariable String productPromoCodeId) throws Exception {
		DeleteProductPromoCode command = new DeleteProductPromoCode(productPromoCodeId);

		try {
			if (((ProductPromoCodeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductPromoCode could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/productPromoCode/\" plus one of the following: "
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
