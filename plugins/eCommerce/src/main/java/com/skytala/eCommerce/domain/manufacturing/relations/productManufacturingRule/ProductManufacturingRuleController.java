package com.skytala.eCommerce.domain.manufacturing.relations.productManufacturingRule;

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
import com.skytala.eCommerce.domain.manufacturing.relations.productManufacturingRule.command.AddProductManufacturingRule;
import com.skytala.eCommerce.domain.manufacturing.relations.productManufacturingRule.command.DeleteProductManufacturingRule;
import com.skytala.eCommerce.domain.manufacturing.relations.productManufacturingRule.command.UpdateProductManufacturingRule;
import com.skytala.eCommerce.domain.manufacturing.relations.productManufacturingRule.event.ProductManufacturingRuleAdded;
import com.skytala.eCommerce.domain.manufacturing.relations.productManufacturingRule.event.ProductManufacturingRuleDeleted;
import com.skytala.eCommerce.domain.manufacturing.relations.productManufacturingRule.event.ProductManufacturingRuleFound;
import com.skytala.eCommerce.domain.manufacturing.relations.productManufacturingRule.event.ProductManufacturingRuleUpdated;
import com.skytala.eCommerce.domain.manufacturing.relations.productManufacturingRule.mapper.ProductManufacturingRuleMapper;
import com.skytala.eCommerce.domain.manufacturing.relations.productManufacturingRule.model.ProductManufacturingRule;
import com.skytala.eCommerce.domain.manufacturing.relations.productManufacturingRule.query.FindProductManufacturingRulesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/productManufacturingRules")
public class ProductManufacturingRuleController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductManufacturingRuleController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductManufacturingRule
	 * @return a List with the ProductManufacturingRules
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findProductManufacturingRulesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductManufacturingRulesBy query = new FindProductManufacturingRulesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductManufacturingRule> productManufacturingRules =((ProductManufacturingRuleFound) Scheduler.execute(query).data()).getProductManufacturingRules();

		if (productManufacturingRules.size() == 1) {
			return ResponseEntity.ok().body(productManufacturingRules.get(0));
		}

		return ResponseEntity.ok().body(productManufacturingRules);

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
	public ResponseEntity<Object> createProductManufacturingRule(HttpServletRequest request) throws Exception {

		ProductManufacturingRule productManufacturingRuleToBeAdded = new ProductManufacturingRule();
		try {
			productManufacturingRuleToBeAdded = ProductManufacturingRuleMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createProductManufacturingRule(productManufacturingRuleToBeAdded);

	}

	/**
	 * creates a new ProductManufacturingRule entry in the ofbiz database
	 * 
	 * @param productManufacturingRuleToBeAdded
	 *            the ProductManufacturingRule thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createProductManufacturingRule(@RequestBody ProductManufacturingRule productManufacturingRuleToBeAdded) throws Exception {

		AddProductManufacturingRule command = new AddProductManufacturingRule(productManufacturingRuleToBeAdded);
		ProductManufacturingRule productManufacturingRule = ((ProductManufacturingRuleAdded) Scheduler.execute(command).data()).getAddedProductManufacturingRule();
		
		if (productManufacturingRule != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(productManufacturingRule);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProductManufacturingRule could not be created.");
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
	public boolean updateProductManufacturingRule(HttpServletRequest request) throws Exception {

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

		ProductManufacturingRule productManufacturingRuleToBeUpdated = new ProductManufacturingRule();

		try {
			productManufacturingRuleToBeUpdated = ProductManufacturingRuleMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProductManufacturingRule(productManufacturingRuleToBeUpdated, productManufacturingRuleToBeUpdated.getRuleId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ProductManufacturingRule with the specific Id
	 * 
	 * @param productManufacturingRuleToBeUpdated
	 *            the ProductManufacturingRule thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{ruleId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateProductManufacturingRule(@RequestBody ProductManufacturingRule productManufacturingRuleToBeUpdated,
			@PathVariable String ruleId) throws Exception {

		productManufacturingRuleToBeUpdated.setRuleId(ruleId);

		UpdateProductManufacturingRule command = new UpdateProductManufacturingRule(productManufacturingRuleToBeUpdated);

		try {
			if(((ProductManufacturingRuleUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{productManufacturingRuleId}")
	public ResponseEntity<Object> findById(@PathVariable String productManufacturingRuleId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productManufacturingRuleId", productManufacturingRuleId);
		try {

			Object foundProductManufacturingRule = findProductManufacturingRulesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProductManufacturingRule);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{productManufacturingRuleId}")
	public ResponseEntity<Object> deleteProductManufacturingRuleByIdUpdated(@PathVariable String productManufacturingRuleId) throws Exception {
		DeleteProductManufacturingRule command = new DeleteProductManufacturingRule(productManufacturingRuleId);

		try {
			if (((ProductManufacturingRuleDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductManufacturingRule could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/productManufacturingRule/\" plus one of the following: "
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
