package com.skytala.eCommerce.domain.product.relations.productGlAccount;

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
import com.skytala.eCommerce.domain.product.relations.productGlAccount.command.AddProductGlAccount;
import com.skytala.eCommerce.domain.product.relations.productGlAccount.command.DeleteProductGlAccount;
import com.skytala.eCommerce.domain.product.relations.productGlAccount.command.UpdateProductGlAccount;
import com.skytala.eCommerce.domain.product.relations.productGlAccount.event.ProductGlAccountAdded;
import com.skytala.eCommerce.domain.product.relations.productGlAccount.event.ProductGlAccountDeleted;
import com.skytala.eCommerce.domain.product.relations.productGlAccount.event.ProductGlAccountFound;
import com.skytala.eCommerce.domain.product.relations.productGlAccount.event.ProductGlAccountUpdated;
import com.skytala.eCommerce.domain.product.relations.productGlAccount.mapper.ProductGlAccountMapper;
import com.skytala.eCommerce.domain.product.relations.productGlAccount.model.ProductGlAccount;
import com.skytala.eCommerce.domain.product.relations.productGlAccount.query.FindProductGlAccountsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/productGlAccounts")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findProductGlAccountsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductGlAccountsBy query = new FindProductGlAccountsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductGlAccount> productGlAccounts =((ProductGlAccountFound) Scheduler.execute(query).data()).getProductGlAccounts();

		if (productGlAccounts.size() == 1) {
			return ResponseEntity.ok().body(productGlAccounts.get(0));
		}

		return ResponseEntity.ok().body(productGlAccounts);

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
	public ResponseEntity<Object> createProductGlAccount(HttpServletRequest request) throws Exception {

		ProductGlAccount productGlAccountToBeAdded = new ProductGlAccount();
		try {
			productGlAccountToBeAdded = ProductGlAccountMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createProductGlAccount(productGlAccountToBeAdded);

	}

	/**
	 * creates a new ProductGlAccount entry in the ofbiz database
	 * 
	 * @param productGlAccountToBeAdded
	 *            the ProductGlAccount thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createProductGlAccount(@RequestBody ProductGlAccount productGlAccountToBeAdded) throws Exception {

		AddProductGlAccount command = new AddProductGlAccount(productGlAccountToBeAdded);
		ProductGlAccount productGlAccount = ((ProductGlAccountAdded) Scheduler.execute(command).data()).getAddedProductGlAccount();
		
		if (productGlAccount != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(productGlAccount);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProductGlAccount could not be created.");
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
	public boolean updateProductGlAccount(HttpServletRequest request) throws Exception {

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

		ProductGlAccount productGlAccountToBeUpdated = new ProductGlAccount();

		try {
			productGlAccountToBeUpdated = ProductGlAccountMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProductGlAccount(productGlAccountToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateProductGlAccount(@RequestBody ProductGlAccount productGlAccountToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		productGlAccountToBeUpdated.setnull(null);

		UpdateProductGlAccount command = new UpdateProductGlAccount(productGlAccountToBeUpdated);

		try {
			if(((ProductGlAccountUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{productGlAccountId}")
	public ResponseEntity<Object> findById(@PathVariable String productGlAccountId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productGlAccountId", productGlAccountId);
		try {

			Object foundProductGlAccount = findProductGlAccountsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProductGlAccount);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{productGlAccountId}")
	public ResponseEntity<Object> deleteProductGlAccountByIdUpdated(@PathVariable String productGlAccountId) throws Exception {
		DeleteProductGlAccount command = new DeleteProductGlAccount(productGlAccountId);

		try {
			if (((ProductGlAccountDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductGlAccount could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/productGlAccount/\" plus one of the following: "
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
