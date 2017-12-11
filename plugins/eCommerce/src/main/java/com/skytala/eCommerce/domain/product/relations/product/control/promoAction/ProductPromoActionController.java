package com.skytala.eCommerce.domain.product.relations.product.control.promoAction;

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
import com.skytala.eCommerce.domain.product.relations.product.command.promoAction.AddProductPromoAction;
import com.skytala.eCommerce.domain.product.relations.product.command.promoAction.DeleteProductPromoAction;
import com.skytala.eCommerce.domain.product.relations.product.command.promoAction.UpdateProductPromoAction;
import com.skytala.eCommerce.domain.product.relations.product.event.promoAction.ProductPromoActionAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.promoAction.ProductPromoActionDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.promoAction.ProductPromoActionFound;
import com.skytala.eCommerce.domain.product.relations.product.event.promoAction.ProductPromoActionUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.promoAction.ProductPromoActionMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.promoAction.ProductPromoAction;
import com.skytala.eCommerce.domain.product.relations.product.query.promoAction.FindProductPromoActionsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/product/product/productPromoActions")
public class ProductPromoActionController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductPromoActionController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductPromoAction
	 * @return a List with the ProductPromoActions
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findProductPromoActionsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductPromoActionsBy query = new FindProductPromoActionsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductPromoAction> productPromoActions =((ProductPromoActionFound) Scheduler.execute(query).data()).getProductPromoActions();

		if (productPromoActions.size() == 1) {
			return ResponseEntity.ok().body(productPromoActions.get(0));
		}

		return ResponseEntity.ok().body(productPromoActions);

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
	public ResponseEntity<Object> createProductPromoAction(HttpServletRequest request) throws Exception {

		ProductPromoAction productPromoActionToBeAdded = new ProductPromoAction();
		try {
			productPromoActionToBeAdded = ProductPromoActionMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createProductPromoAction(productPromoActionToBeAdded);

	}

	/**
	 * creates a new ProductPromoAction entry in the ofbiz database
	 * 
	 * @param productPromoActionToBeAdded
	 *            the ProductPromoAction thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createProductPromoAction(@RequestBody ProductPromoAction productPromoActionToBeAdded) throws Exception {

		AddProductPromoAction command = new AddProductPromoAction(productPromoActionToBeAdded);
		ProductPromoAction productPromoAction = ((ProductPromoActionAdded) Scheduler.execute(command).data()).getAddedProductPromoAction();
		
		if (productPromoAction != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(productPromoAction);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProductPromoAction could not be created.");
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
	public boolean updateProductPromoAction(HttpServletRequest request) throws Exception {

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

		ProductPromoAction productPromoActionToBeUpdated = new ProductPromoAction();

		try {
			productPromoActionToBeUpdated = ProductPromoActionMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProductPromoAction(productPromoActionToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ProductPromoAction with the specific Id
	 * 
	 * @param productPromoActionToBeUpdated
	 *            the ProductPromoAction thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateProductPromoAction(@RequestBody ProductPromoAction productPromoActionToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		productPromoActionToBeUpdated.setnull(null);

		UpdateProductPromoAction command = new UpdateProductPromoAction(productPromoActionToBeUpdated);

		try {
			if(((ProductPromoActionUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{productPromoActionId}")
	public ResponseEntity<Object> findById(@PathVariable String productPromoActionId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productPromoActionId", productPromoActionId);
		try {

			Object foundProductPromoAction = findProductPromoActionsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProductPromoAction);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{productPromoActionId}")
	public ResponseEntity<Object> deleteProductPromoActionByIdUpdated(@PathVariable String productPromoActionId) throws Exception {
		DeleteProductPromoAction command = new DeleteProductPromoAction(productPromoActionId);

		try {
			if (((ProductPromoActionDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductPromoAction could not be deleted");

	}

}
