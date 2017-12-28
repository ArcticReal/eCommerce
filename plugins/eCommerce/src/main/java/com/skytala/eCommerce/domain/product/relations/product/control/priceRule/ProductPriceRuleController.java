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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	public ResponseEntity<List<ProductPriceRule>> findProductPriceRulesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductPriceRulesBy query = new FindProductPriceRulesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductPriceRule> productPriceRules =((ProductPriceRuleFound) Scheduler.execute(query).data()).getProductPriceRules();

		return ResponseEntity.ok().body(productPriceRules);

	}

	/**
	 * creates a new ProductPriceRule entry in the ofbiz database
	 * 
	 * @param productPriceRuleToBeAdded
	 *            the ProductPriceRule thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductPriceRule> createProductPriceRule(@RequestBody ProductPriceRule productPriceRuleToBeAdded) throws Exception {

		AddProductPriceRule command = new AddProductPriceRule(productPriceRuleToBeAdded);
		ProductPriceRule productPriceRule = ((ProductPriceRuleAdded) Scheduler.execute(command).data()).getAddedProductPriceRule();
		
		if (productPriceRule != null) 
			return successful(productPriceRule);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateProductPriceRule(@RequestBody ProductPriceRule productPriceRuleToBeUpdated,
			@PathVariable String productPriceRuleId) throws Exception {

		productPriceRuleToBeUpdated.setProductPriceRuleId(productPriceRuleId);

		UpdateProductPriceRule command = new UpdateProductPriceRule(productPriceRuleToBeUpdated);

		try {
			if(((ProductPriceRuleUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{productPriceRuleId}")
	public ResponseEntity<ProductPriceRule> findById(@PathVariable String productPriceRuleId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productPriceRuleId", productPriceRuleId);
		try {

			List<ProductPriceRule> foundProductPriceRule = findProductPriceRulesBy(requestParams).getBody();
			if(foundProductPriceRule.size()==1){				return successful(foundProductPriceRule.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{productPriceRuleId}")
	public ResponseEntity<String> deleteProductPriceRuleByIdUpdated(@PathVariable String productPriceRuleId) throws Exception {
		DeleteProductPriceRule command = new DeleteProductPriceRule(productPriceRuleId);

		try {
			if (((ProductPriceRuleDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
