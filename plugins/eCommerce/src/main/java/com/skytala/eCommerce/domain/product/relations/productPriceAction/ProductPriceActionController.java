package com.skytala.eCommerce.domain.product.relations.productPriceAction;

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
import com.skytala.eCommerce.domain.product.relations.productPriceAction.command.AddProductPriceAction;
import com.skytala.eCommerce.domain.product.relations.productPriceAction.command.DeleteProductPriceAction;
import com.skytala.eCommerce.domain.product.relations.productPriceAction.command.UpdateProductPriceAction;
import com.skytala.eCommerce.domain.product.relations.productPriceAction.event.ProductPriceActionAdded;
import com.skytala.eCommerce.domain.product.relations.productPriceAction.event.ProductPriceActionDeleted;
import com.skytala.eCommerce.domain.product.relations.productPriceAction.event.ProductPriceActionFound;
import com.skytala.eCommerce.domain.product.relations.productPriceAction.event.ProductPriceActionUpdated;
import com.skytala.eCommerce.domain.product.relations.productPriceAction.mapper.ProductPriceActionMapper;
import com.skytala.eCommerce.domain.product.relations.productPriceAction.model.ProductPriceAction;
import com.skytala.eCommerce.domain.product.relations.productPriceAction.query.FindProductPriceActionsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/productPriceActions")
public class ProductPriceActionController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductPriceActionController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductPriceAction
	 * @return a List with the ProductPriceActions
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findProductPriceActionsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductPriceActionsBy query = new FindProductPriceActionsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductPriceAction> productPriceActions =((ProductPriceActionFound) Scheduler.execute(query).data()).getProductPriceActions();

		if (productPriceActions.size() == 1) {
			return ResponseEntity.ok().body(productPriceActions.get(0));
		}

		return ResponseEntity.ok().body(productPriceActions);

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
	public ResponseEntity<Object> createProductPriceAction(HttpServletRequest request) throws Exception {

		ProductPriceAction productPriceActionToBeAdded = new ProductPriceAction();
		try {
			productPriceActionToBeAdded = ProductPriceActionMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createProductPriceAction(productPriceActionToBeAdded);

	}

	/**
	 * creates a new ProductPriceAction entry in the ofbiz database
	 * 
	 * @param productPriceActionToBeAdded
	 *            the ProductPriceAction thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createProductPriceAction(@RequestBody ProductPriceAction productPriceActionToBeAdded) throws Exception {

		AddProductPriceAction command = new AddProductPriceAction(productPriceActionToBeAdded);
		ProductPriceAction productPriceAction = ((ProductPriceActionAdded) Scheduler.execute(command).data()).getAddedProductPriceAction();
		
		if (productPriceAction != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(productPriceAction);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProductPriceAction could not be created.");
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
	public boolean updateProductPriceAction(HttpServletRequest request) throws Exception {

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

		ProductPriceAction productPriceActionToBeUpdated = new ProductPriceAction();

		try {
			productPriceActionToBeUpdated = ProductPriceActionMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProductPriceAction(productPriceActionToBeUpdated, productPriceActionToBeUpdated.getProductPriceActionSeqId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ProductPriceAction with the specific Id
	 * 
	 * @param productPriceActionToBeUpdated
	 *            the ProductPriceAction thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{productPriceActionSeqId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateProductPriceAction(@RequestBody ProductPriceAction productPriceActionToBeUpdated,
			@PathVariable String productPriceActionSeqId) throws Exception {

		productPriceActionToBeUpdated.setProductPriceActionSeqId(productPriceActionSeqId);

		UpdateProductPriceAction command = new UpdateProductPriceAction(productPriceActionToBeUpdated);

		try {
			if(((ProductPriceActionUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{productPriceActionId}")
	public ResponseEntity<Object> findById(@PathVariable String productPriceActionId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productPriceActionId", productPriceActionId);
		try {

			Object foundProductPriceAction = findProductPriceActionsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProductPriceAction);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{productPriceActionId}")
	public ResponseEntity<Object> deleteProductPriceActionByIdUpdated(@PathVariable String productPriceActionId) throws Exception {
		DeleteProductPriceAction command = new DeleteProductPriceAction(productPriceActionId);

		try {
			if (((ProductPriceActionDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductPriceAction could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/productPriceAction/\" plus one of the following: "
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
