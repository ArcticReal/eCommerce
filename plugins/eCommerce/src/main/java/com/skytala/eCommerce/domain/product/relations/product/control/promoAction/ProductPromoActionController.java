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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	public ResponseEntity<List<ProductPromoAction>> findProductPromoActionsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductPromoActionsBy query = new FindProductPromoActionsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductPromoAction> productPromoActions =((ProductPromoActionFound) Scheduler.execute(query).data()).getProductPromoActions();

		return ResponseEntity.ok().body(productPromoActions);

	}

	/**
	 * creates a new ProductPromoAction entry in the ofbiz database
	 * 
	 * @param productPromoActionToBeAdded
	 *            the ProductPromoAction thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductPromoAction> createProductPromoAction(@RequestBody ProductPromoAction productPromoActionToBeAdded) throws Exception {

		AddProductPromoAction command = new AddProductPromoAction(productPromoActionToBeAdded);
		ProductPromoAction productPromoAction = ((ProductPromoActionAdded) Scheduler.execute(command).data()).getAddedProductPromoAction();
		
		if (productPromoAction != null) 
			return successful(productPromoAction);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateProductPromoAction(@RequestBody ProductPromoAction productPromoActionToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		productPromoActionToBeUpdated.setnull(null);

		UpdateProductPromoAction command = new UpdateProductPromoAction(productPromoActionToBeUpdated);

		try {
			if(((ProductPromoActionUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{productPromoActionId}")
	public ResponseEntity<ProductPromoAction> findById(@PathVariable String productPromoActionId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productPromoActionId", productPromoActionId);
		try {

			List<ProductPromoAction> foundProductPromoAction = findProductPromoActionsBy(requestParams).getBody();
			if(foundProductPromoAction.size()==1){				return successful(foundProductPromoAction.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{productPromoActionId}")
	public ResponseEntity<String> deleteProductPromoActionByIdUpdated(@PathVariable String productPromoActionId) throws Exception {
		DeleteProductPromoAction command = new DeleteProductPromoAction(productPromoActionId);

		try {
			if (((ProductPromoActionDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
