package com.skytala.eCommerce.domain.product.relations.product.control.priceAction;

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
import com.skytala.eCommerce.domain.product.relations.product.command.priceAction.AddProductPriceAction;
import com.skytala.eCommerce.domain.product.relations.product.command.priceAction.DeleteProductPriceAction;
import com.skytala.eCommerce.domain.product.relations.product.command.priceAction.UpdateProductPriceAction;
import com.skytala.eCommerce.domain.product.relations.product.event.priceAction.ProductPriceActionAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.priceAction.ProductPriceActionDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.priceAction.ProductPriceActionFound;
import com.skytala.eCommerce.domain.product.relations.product.event.priceAction.ProductPriceActionUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.priceAction.ProductPriceActionMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.priceAction.ProductPriceAction;
import com.skytala.eCommerce.domain.product.relations.product.query.priceAction.FindProductPriceActionsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/product/productPriceActions")
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
	@GetMapping("/find")
	public ResponseEntity<List<ProductPriceAction>> findProductPriceActionsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductPriceActionsBy query = new FindProductPriceActionsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductPriceAction> productPriceActions =((ProductPriceActionFound) Scheduler.execute(query).data()).getProductPriceActions();

		return ResponseEntity.ok().body(productPriceActions);

	}

	/**
	 * creates a new ProductPriceAction entry in the ofbiz database
	 * 
	 * @param productPriceActionToBeAdded
	 *            the ProductPriceAction thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductPriceAction> createProductPriceAction(@RequestBody ProductPriceAction productPriceActionToBeAdded) throws Exception {

		AddProductPriceAction command = new AddProductPriceAction(productPriceActionToBeAdded);
		ProductPriceAction productPriceAction = ((ProductPriceActionAdded) Scheduler.execute(command).data()).getAddedProductPriceAction();
		
		if (productPriceAction != null) 
			return successful(productPriceAction);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateProductPriceAction(@RequestBody ProductPriceAction productPriceActionToBeUpdated,
			@PathVariable String productPriceActionSeqId) throws Exception {

		productPriceActionToBeUpdated.setProductPriceActionSeqId(productPriceActionSeqId);

		UpdateProductPriceAction command = new UpdateProductPriceAction(productPriceActionToBeUpdated);

		try {
			if(((ProductPriceActionUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{productPriceActionId}")
	public ResponseEntity<ProductPriceAction> findById(@PathVariable String productPriceActionId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productPriceActionId", productPriceActionId);
		try {

			List<ProductPriceAction> foundProductPriceAction = findProductPriceActionsBy(requestParams).getBody();
			if(foundProductPriceAction.size()==1){				return successful(foundProductPriceAction.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{productPriceActionId}")
	public ResponseEntity<String> deleteProductPriceActionByIdUpdated(@PathVariable String productPriceActionId) throws Exception {
		DeleteProductPriceAction command = new DeleteProductPriceAction(productPriceActionId);

		try {
			if (((ProductPriceActionDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
