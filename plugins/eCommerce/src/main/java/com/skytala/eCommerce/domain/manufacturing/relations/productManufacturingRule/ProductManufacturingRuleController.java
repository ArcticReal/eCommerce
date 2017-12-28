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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/manufacturing/productManufacturingRules")
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
	@GetMapping("/find")
	public ResponseEntity<List<ProductManufacturingRule>> findProductManufacturingRulesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductManufacturingRulesBy query = new FindProductManufacturingRulesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductManufacturingRule> productManufacturingRules =((ProductManufacturingRuleFound) Scheduler.execute(query).data()).getProductManufacturingRules();

		return ResponseEntity.ok().body(productManufacturingRules);

	}

	/**
	 * creates a new ProductManufacturingRule entry in the ofbiz database
	 * 
	 * @param productManufacturingRuleToBeAdded
	 *            the ProductManufacturingRule thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductManufacturingRule> createProductManufacturingRule(@RequestBody ProductManufacturingRule productManufacturingRuleToBeAdded) throws Exception {

		AddProductManufacturingRule command = new AddProductManufacturingRule(productManufacturingRuleToBeAdded);
		ProductManufacturingRule productManufacturingRule = ((ProductManufacturingRuleAdded) Scheduler.execute(command).data()).getAddedProductManufacturingRule();
		
		if (productManufacturingRule != null) 
			return successful(productManufacturingRule);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateProductManufacturingRule(@RequestBody ProductManufacturingRule productManufacturingRuleToBeUpdated,
			@PathVariable String ruleId) throws Exception {

		productManufacturingRuleToBeUpdated.setRuleId(ruleId);

		UpdateProductManufacturingRule command = new UpdateProductManufacturingRule(productManufacturingRuleToBeUpdated);

		try {
			if(((ProductManufacturingRuleUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{productManufacturingRuleId}")
	public ResponseEntity<ProductManufacturingRule> findById(@PathVariable String productManufacturingRuleId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productManufacturingRuleId", productManufacturingRuleId);
		try {

			List<ProductManufacturingRule> foundProductManufacturingRule = findProductManufacturingRulesBy(requestParams).getBody();
			if(foundProductManufacturingRule.size()==1){				return successful(foundProductManufacturingRule.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{productManufacturingRuleId}")
	public ResponseEntity<String> deleteProductManufacturingRuleByIdUpdated(@PathVariable String productManufacturingRuleId) throws Exception {
		DeleteProductManufacturingRule command = new DeleteProductManufacturingRule(productManufacturingRuleId);

		try {
			if (((ProductManufacturingRuleDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
