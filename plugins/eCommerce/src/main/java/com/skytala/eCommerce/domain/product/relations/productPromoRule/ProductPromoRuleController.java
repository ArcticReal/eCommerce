package com.skytala.eCommerce.domain.product.relations.productPromoRule;

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
import com.skytala.eCommerce.domain.product.relations.productPromoRule.command.AddProductPromoRule;
import com.skytala.eCommerce.domain.product.relations.productPromoRule.command.DeleteProductPromoRule;
import com.skytala.eCommerce.domain.product.relations.productPromoRule.command.UpdateProductPromoRule;
import com.skytala.eCommerce.domain.product.relations.productPromoRule.event.ProductPromoRuleAdded;
import com.skytala.eCommerce.domain.product.relations.productPromoRule.event.ProductPromoRuleDeleted;
import com.skytala.eCommerce.domain.product.relations.productPromoRule.event.ProductPromoRuleFound;
import com.skytala.eCommerce.domain.product.relations.productPromoRule.event.ProductPromoRuleUpdated;
import com.skytala.eCommerce.domain.product.relations.productPromoRule.mapper.ProductPromoRuleMapper;
import com.skytala.eCommerce.domain.product.relations.productPromoRule.model.ProductPromoRule;
import com.skytala.eCommerce.domain.product.relations.productPromoRule.query.FindProductPromoRulesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/productPromoRules")
public class ProductPromoRuleController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductPromoRuleController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductPromoRule
	 * @return a List with the ProductPromoRules
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findProductPromoRulesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductPromoRulesBy query = new FindProductPromoRulesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductPromoRule> productPromoRules =((ProductPromoRuleFound) Scheduler.execute(query).data()).getProductPromoRules();

		if (productPromoRules.size() == 1) {
			return ResponseEntity.ok().body(productPromoRules.get(0));
		}

		return ResponseEntity.ok().body(productPromoRules);

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
	public ResponseEntity<Object> createProductPromoRule(HttpServletRequest request) throws Exception {

		ProductPromoRule productPromoRuleToBeAdded = new ProductPromoRule();
		try {
			productPromoRuleToBeAdded = ProductPromoRuleMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createProductPromoRule(productPromoRuleToBeAdded);

	}

	/**
	 * creates a new ProductPromoRule entry in the ofbiz database
	 * 
	 * @param productPromoRuleToBeAdded
	 *            the ProductPromoRule thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createProductPromoRule(@RequestBody ProductPromoRule productPromoRuleToBeAdded) throws Exception {

		AddProductPromoRule command = new AddProductPromoRule(productPromoRuleToBeAdded);
		ProductPromoRule productPromoRule = ((ProductPromoRuleAdded) Scheduler.execute(command).data()).getAddedProductPromoRule();
		
		if (productPromoRule != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(productPromoRule);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProductPromoRule could not be created.");
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
	public boolean updateProductPromoRule(HttpServletRequest request) throws Exception {

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

		ProductPromoRule productPromoRuleToBeUpdated = new ProductPromoRule();

		try {
			productPromoRuleToBeUpdated = ProductPromoRuleMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProductPromoRule(productPromoRuleToBeUpdated, productPromoRuleToBeUpdated.getProductPromoRuleId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ProductPromoRule with the specific Id
	 * 
	 * @param productPromoRuleToBeUpdated
	 *            the ProductPromoRule thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{productPromoRuleId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateProductPromoRule(@RequestBody ProductPromoRule productPromoRuleToBeUpdated,
			@PathVariable String productPromoRuleId) throws Exception {

		productPromoRuleToBeUpdated.setProductPromoRuleId(productPromoRuleId);

		UpdateProductPromoRule command = new UpdateProductPromoRule(productPromoRuleToBeUpdated);

		try {
			if(((ProductPromoRuleUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{productPromoRuleId}")
	public ResponseEntity<Object> findById(@PathVariable String productPromoRuleId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productPromoRuleId", productPromoRuleId);
		try {

			Object foundProductPromoRule = findProductPromoRulesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProductPromoRule);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{productPromoRuleId}")
	public ResponseEntity<Object> deleteProductPromoRuleByIdUpdated(@PathVariable String productPromoRuleId) throws Exception {
		DeleteProductPromoRule command = new DeleteProductPromoRule(productPromoRuleId);

		try {
			if (((ProductPromoRuleDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductPromoRule could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/productPromoRule/\" plus one of the following: "
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