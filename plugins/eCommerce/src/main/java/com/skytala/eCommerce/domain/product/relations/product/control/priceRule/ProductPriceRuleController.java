package com.skytala.eCommerce.domain.product.relations.product.control.priceRule;

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
import com.skytala.eCommerce.domain.product.relations.product.command.priceRule.AddProductPriceRule;
import com.skytala.eCommerce.domain.product.relations.product.command.priceRule.DeleteProductPriceRule;
import com.skytala.eCommerce.domain.product.relations.product.command.priceRule.UpdateProductPriceRule;
import com.skytala.eCommerce.domain.product.relations.product.event.priceRule.ProductPriceRuleAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.priceRule.ProductPriceRuleDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.priceRule.ProductPriceRuleFound;
import com.skytala.eCommerce.domain.product.relations.product.event.priceRule.ProductPriceRuleUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.priceRule.ProductPriceRuleMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.priceRule.ProductPriceRule;
import com.skytala.eCommerce.domain.product.relations.product.query.priceRule.FindProductPriceRulesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/product/product/productPriceRules")
public class ProductPriceRuleController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductPriceRuleController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductPriceRule
	 * @return a List with the ProductPriceRules
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findProductPriceRulesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductPriceRulesBy query = new FindProductPriceRulesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductPriceRule> productPriceRules =((ProductPriceRuleFound) Scheduler.execute(query).data()).getProductPriceRules();

		if (productPriceRules.size() == 1) {
			return ResponseEntity.ok().body(productPriceRules.get(0));
		}

		return ResponseEntity.ok().body(productPriceRules);

	}

	/**
	 * 
	 * this method will only be called by Springs DispatcherServlet
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return true on success; false on fail
	 */
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createProductPriceRule(HttpServletRequest request) throws Exception {

		ProductPriceRule productPriceRuleToBeAdded = new ProductPriceRule();
		try {
			productPriceRuleToBeAdded = ProductPriceRuleMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createProductPriceRule(productPriceRuleToBeAdded);

	}

	/**
	 * creates a new ProductPriceRule entry in the ofbiz database
	 * 
	 * @param productPriceRuleToBeAdded
	 *            the ProductPriceRule thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createProductPriceRule(@RequestBody ProductPriceRule productPriceRuleToBeAdded) throws Exception {

		AddProductPriceRule command = new AddProductPriceRule(productPriceRuleToBeAdded);
		ProductPriceRule productPriceRule = ((ProductPriceRuleAdded) Scheduler.execute(command).data()).getAddedProductPriceRule();
		
		if (productPriceRule != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(productPriceRule);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProductPriceRule could not be created.");
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
	@PutMapping(value = "/update", consumes = "application/x-www-form-urlencoded")
	public boolean updateProductPriceRule(HttpServletRequest request) throws Exception {

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

		ProductPriceRule productPriceRuleToBeUpdated = new ProductPriceRule();

		try {
			productPriceRuleToBeUpdated = ProductPriceRuleMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProductPriceRule(productPriceRuleToBeUpdated, productPriceRuleToBeUpdated.getProductPriceRuleId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ProductPriceRule with the specific Id
	 * 
	 * @param productPriceRuleToBeUpdated
	 *            the ProductPriceRule thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{productPriceRuleId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateProductPriceRule(@RequestBody ProductPriceRule productPriceRuleToBeUpdated,
			@PathVariable String productPriceRuleId) throws Exception {

		productPriceRuleToBeUpdated.setProductPriceRuleId(productPriceRuleId);

		UpdateProductPriceRule command = new UpdateProductPriceRule(productPriceRuleToBeUpdated);

		try {
			if(((ProductPriceRuleUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{productPriceRuleId}")
	public ResponseEntity<Object> findById(@PathVariable String productPriceRuleId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productPriceRuleId", productPriceRuleId);
		try {

			Object foundProductPriceRule = findProductPriceRulesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProductPriceRule);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{productPriceRuleId}")
	public ResponseEntity<Object> deleteProductPriceRuleByIdUpdated(@PathVariable String productPriceRuleId) throws Exception {
		DeleteProductPriceRule command = new DeleteProductPriceRule(productPriceRuleId);

		try {
			if (((ProductPriceRuleDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductPriceRule could not be deleted");

	}

}
