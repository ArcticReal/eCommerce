package com.skytala.eCommerce.domain.product.relations.product.control.categoryGlAccount;

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
import com.skytala.eCommerce.domain.product.relations.product.command.categoryGlAccount.AddProductCategoryGlAccount;
import com.skytala.eCommerce.domain.product.relations.product.command.categoryGlAccount.DeleteProductCategoryGlAccount;
import com.skytala.eCommerce.domain.product.relations.product.command.categoryGlAccount.UpdateProductCategoryGlAccount;
import com.skytala.eCommerce.domain.product.relations.product.event.categoryGlAccount.ProductCategoryGlAccountAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.categoryGlAccount.ProductCategoryGlAccountDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.categoryGlAccount.ProductCategoryGlAccountFound;
import com.skytala.eCommerce.domain.product.relations.product.event.categoryGlAccount.ProductCategoryGlAccountUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.categoryGlAccount.ProductCategoryGlAccountMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.categoryGlAccount.ProductCategoryGlAccount;
import com.skytala.eCommerce.domain.product.relations.product.query.categoryGlAccount.FindProductCategoryGlAccountsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/product/productCategoryGlAccounts")
public class ProductCategoryGlAccountController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductCategoryGlAccountController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductCategoryGlAccount
	 * @return a List with the ProductCategoryGlAccounts
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ProductCategoryGlAccount>> findProductCategoryGlAccountsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductCategoryGlAccountsBy query = new FindProductCategoryGlAccountsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductCategoryGlAccount> productCategoryGlAccounts =((ProductCategoryGlAccountFound) Scheduler.execute(query).data()).getProductCategoryGlAccounts();

		return ResponseEntity.ok().body(productCategoryGlAccounts);

	}

	/**
	 * creates a new ProductCategoryGlAccount entry in the ofbiz database
	 * 
	 * @param productCategoryGlAccountToBeAdded
	 *            the ProductCategoryGlAccount thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductCategoryGlAccount> createProductCategoryGlAccount(@RequestBody ProductCategoryGlAccount productCategoryGlAccountToBeAdded) throws Exception {

		AddProductCategoryGlAccount command = new AddProductCategoryGlAccount(productCategoryGlAccountToBeAdded);
		ProductCategoryGlAccount productCategoryGlAccount = ((ProductCategoryGlAccountAdded) Scheduler.execute(command).data()).getAddedProductCategoryGlAccount();
		
		if (productCategoryGlAccount != null) 
			return successful(productCategoryGlAccount);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ProductCategoryGlAccount with the specific Id
	 * 
	 * @param productCategoryGlAccountToBeUpdated
	 *            the ProductCategoryGlAccount thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateProductCategoryGlAccount(@RequestBody ProductCategoryGlAccount productCategoryGlAccountToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		productCategoryGlAccountToBeUpdated.setnull(null);

		UpdateProductCategoryGlAccount command = new UpdateProductCategoryGlAccount(productCategoryGlAccountToBeUpdated);

		try {
			if(((ProductCategoryGlAccountUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{productCategoryGlAccountId}")
	public ResponseEntity<ProductCategoryGlAccount> findById(@PathVariable String productCategoryGlAccountId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productCategoryGlAccountId", productCategoryGlAccountId);
		try {

			List<ProductCategoryGlAccount> foundProductCategoryGlAccount = findProductCategoryGlAccountsBy(requestParams).getBody();
			if(foundProductCategoryGlAccount.size()==1){				return successful(foundProductCategoryGlAccount.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{productCategoryGlAccountId}")
	public ResponseEntity<String> deleteProductCategoryGlAccountByIdUpdated(@PathVariable String productCategoryGlAccountId) throws Exception {
		DeleteProductCategoryGlAccount command = new DeleteProductCategoryGlAccount(productCategoryGlAccountId);

		try {
			if (((ProductCategoryGlAccountDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
