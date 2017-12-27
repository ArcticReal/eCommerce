package com.skytala.eCommerce.domain.product.relations.product.control.promoRule;

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
import com.skytala.eCommerce.domain.product.relations.product.command.promoRule.AddProductPromoRule;
import com.skytala.eCommerce.domain.product.relations.product.command.promoRule.DeleteProductPromoRule;
import com.skytala.eCommerce.domain.product.relations.product.command.promoRule.UpdateProductPromoRule;
import com.skytala.eCommerce.domain.product.relations.product.event.promoRule.ProductPromoRuleAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.promoRule.ProductPromoRuleDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.promoRule.ProductPromoRuleFound;
import com.skytala.eCommerce.domain.product.relations.product.event.promoRule.ProductPromoRuleUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.promoRule.ProductPromoRuleMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.promoRule.ProductPromoRule;
import com.skytala.eCommerce.domain.product.relations.product.query.promoRule.FindProductPromoRulesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/product/productPromoRules")
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
	@GetMapping("/find")
	public ResponseEntity<List<ProductPromoRule>> findProductPromoRulesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductPromoRulesBy query = new FindProductPromoRulesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductPromoRule> productPromoRules =((ProductPromoRuleFound) Scheduler.execute(query).data()).getProductPromoRules();

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
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<ProductPromoRule> createProductPromoRule(HttpServletRequest request) throws Exception {

		ProductPromoRule productPromoRuleToBeAdded = new ProductPromoRule();
		try {
			productPromoRuleToBeAdded = ProductPromoRuleMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
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
	public ResponseEntity<ProductPromoRule> createProductPromoRule(@RequestBody ProductPromoRule productPromoRuleToBeAdded) throws Exception {

		AddProductPromoRule command = new AddProductPromoRule(productPromoRuleToBeAdded);
		ProductPromoRule productPromoRule = ((ProductPromoRuleAdded) Scheduler.execute(command).data()).getAddedProductPromoRule();
		
		if (productPromoRule != null) 
			return successful(productPromoRule);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateProductPromoRule(@RequestBody ProductPromoRule productPromoRuleToBeUpdated,
			@PathVariable String productPromoRuleId) throws Exception {

		productPromoRuleToBeUpdated.setProductPromoRuleId(productPromoRuleId);

		UpdateProductPromoRule command = new UpdateProductPromoRule(productPromoRuleToBeUpdated);

		try {
			if(((ProductPromoRuleUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{productPromoRuleId}")
	public ResponseEntity<ProductPromoRule> findById(@PathVariable String productPromoRuleId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productPromoRuleId", productPromoRuleId);
		try {

			List<ProductPromoRule> foundProductPromoRule = findProductPromoRulesBy(requestParams).getBody();
			if(foundProductPromoRule.size()==1){				return successful(foundProductPromoRule.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{productPromoRuleId}")
	public ResponseEntity<String> deleteProductPromoRuleByIdUpdated(@PathVariable String productPromoRuleId) throws Exception {
		DeleteProductPromoRule command = new DeleteProductPromoRule(productPromoRuleId);

		try {
			if (((ProductPromoRuleDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
