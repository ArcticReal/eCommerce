package com.skytala.eCommerce.domain.product.relations.product.control.glAccount;

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
import com.skytala.eCommerce.domain.product.relations.product.command.glAccount.AddProductGlAccount;
import com.skytala.eCommerce.domain.product.relations.product.command.glAccount.DeleteProductGlAccount;
import com.skytala.eCommerce.domain.product.relations.product.command.glAccount.UpdateProductGlAccount;
import com.skytala.eCommerce.domain.product.relations.product.event.glAccount.ProductGlAccountAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.glAccount.ProductGlAccountDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.glAccount.ProductGlAccountFound;
import com.skytala.eCommerce.domain.product.relations.product.event.glAccount.ProductGlAccountUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.glAccount.ProductGlAccountMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.glAccount.ProductGlAccount;
import com.skytala.eCommerce.domain.product.relations.product.query.glAccount.FindProductGlAccountsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/product/productGlAccounts")
public class ProductGlAccountController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductGlAccountController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductGlAccount
	 * @return a List with the ProductGlAccounts
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ProductGlAccount>> findProductGlAccountsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductGlAccountsBy query = new FindProductGlAccountsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductGlAccount> productGlAccounts =((ProductGlAccountFound) Scheduler.execute(query).data()).getProductGlAccounts();

		return ResponseEntity.ok().body(productGlAccounts);

	}

	/**
	 * creates a new ProductGlAccount entry in the ofbiz database
	 * 
	 * @param productGlAccountToBeAdded
	 *            the ProductGlAccount thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductGlAccount> createProductGlAccount(@RequestBody ProductGlAccount productGlAccountToBeAdded) throws Exception {

		AddProductGlAccount command = new AddProductGlAccount(productGlAccountToBeAdded);
		ProductGlAccount productGlAccount = ((ProductGlAccountAdded) Scheduler.execute(command).data()).getAddedProductGlAccount();
		
		if (productGlAccount != null) 
			return successful(productGlAccount);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ProductGlAccount with the specific Id
	 * 
	 * @param productGlAccountToBeUpdated
	 *            the ProductGlAccount thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateProductGlAccount(@RequestBody ProductGlAccount productGlAccountToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		productGlAccountToBeUpdated.setnull(null);

		UpdateProductGlAccount command = new UpdateProductGlAccount(productGlAccountToBeUpdated);

		try {
			if(((ProductGlAccountUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{productGlAccountId}")
	public ResponseEntity<ProductGlAccount> findById(@PathVariable String productGlAccountId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productGlAccountId", productGlAccountId);
		try {

			List<ProductGlAccount> foundProductGlAccount = findProductGlAccountsBy(requestParams).getBody();
			if(foundProductGlAccount.size()==1){				return successful(foundProductGlAccount.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{productGlAccountId}")
	public ResponseEntity<String> deleteProductGlAccountByIdUpdated(@PathVariable String productGlAccountId) throws Exception {
		DeleteProductGlAccount command = new DeleteProductGlAccount(productGlAccountId);

		try {
			if (((ProductGlAccountDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
