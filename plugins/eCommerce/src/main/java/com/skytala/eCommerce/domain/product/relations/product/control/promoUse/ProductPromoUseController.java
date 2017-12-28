package com.skytala.eCommerce.domain.product.relations.product.control.promoUse;

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
import com.skytala.eCommerce.domain.product.relations.product.command.promoUse.AddProductPromoUse;
import com.skytala.eCommerce.domain.product.relations.product.command.promoUse.DeleteProductPromoUse;
import com.skytala.eCommerce.domain.product.relations.product.command.promoUse.UpdateProductPromoUse;
import com.skytala.eCommerce.domain.product.relations.product.event.promoUse.ProductPromoUseAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.promoUse.ProductPromoUseDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.promoUse.ProductPromoUseFound;
import com.skytala.eCommerce.domain.product.relations.product.event.promoUse.ProductPromoUseUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.promoUse.ProductPromoUseMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.promoUse.ProductPromoUse;
import com.skytala.eCommerce.domain.product.relations.product.query.promoUse.FindProductPromoUsesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/product/productPromoUses")
public class ProductPromoUseController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductPromoUseController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductPromoUse
	 * @return a List with the ProductPromoUses
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ProductPromoUse>> findProductPromoUsesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductPromoUsesBy query = new FindProductPromoUsesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductPromoUse> productPromoUses =((ProductPromoUseFound) Scheduler.execute(query).data()).getProductPromoUses();

		return ResponseEntity.ok().body(productPromoUses);

	}

	/**
	 * creates a new ProductPromoUse entry in the ofbiz database
	 * 
	 * @param productPromoUseToBeAdded
	 *            the ProductPromoUse thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductPromoUse> createProductPromoUse(@RequestBody ProductPromoUse productPromoUseToBeAdded) throws Exception {

		AddProductPromoUse command = new AddProductPromoUse(productPromoUseToBeAdded);
		ProductPromoUse productPromoUse = ((ProductPromoUseAdded) Scheduler.execute(command).data()).getAddedProductPromoUse();
		
		if (productPromoUse != null) 
			return successful(productPromoUse);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ProductPromoUse with the specific Id
	 * 
	 * @param productPromoUseToBeUpdated
	 *            the ProductPromoUse thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{promoSequenceId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateProductPromoUse(@RequestBody ProductPromoUse productPromoUseToBeUpdated,
			@PathVariable String promoSequenceId) throws Exception {

		productPromoUseToBeUpdated.setPromoSequenceId(promoSequenceId);

		UpdateProductPromoUse command = new UpdateProductPromoUse(productPromoUseToBeUpdated);

		try {
			if(((ProductPromoUseUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{productPromoUseId}")
	public ResponseEntity<ProductPromoUse> findById(@PathVariable String productPromoUseId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productPromoUseId", productPromoUseId);
		try {

			List<ProductPromoUse> foundProductPromoUse = findProductPromoUsesBy(requestParams).getBody();
			if(foundProductPromoUse.size()==1){				return successful(foundProductPromoUse.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{productPromoUseId}")
	public ResponseEntity<String> deleteProductPromoUseByIdUpdated(@PathVariable String productPromoUseId) throws Exception {
		DeleteProductPromoUse command = new DeleteProductPromoUse(productPromoUseId);

		try {
			if (((ProductPromoUseDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
