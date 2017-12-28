package com.skytala.eCommerce.domain.product.relations.product.control.promoCodeParty;

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
import com.skytala.eCommerce.domain.product.relations.product.command.promoCodeParty.AddProductPromoCodeParty;
import com.skytala.eCommerce.domain.product.relations.product.command.promoCodeParty.DeleteProductPromoCodeParty;
import com.skytala.eCommerce.domain.product.relations.product.command.promoCodeParty.UpdateProductPromoCodeParty;
import com.skytala.eCommerce.domain.product.relations.product.event.promoCodeParty.ProductPromoCodePartyAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.promoCodeParty.ProductPromoCodePartyDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.promoCodeParty.ProductPromoCodePartyFound;
import com.skytala.eCommerce.domain.product.relations.product.event.promoCodeParty.ProductPromoCodePartyUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.promoCodeParty.ProductPromoCodePartyMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.promoCodeParty.ProductPromoCodeParty;
import com.skytala.eCommerce.domain.product.relations.product.query.promoCodeParty.FindProductPromoCodePartysBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/product/productPromoCodePartys")
public class ProductPromoCodePartyController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductPromoCodePartyController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductPromoCodeParty
	 * @return a List with the ProductPromoCodePartys
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ProductPromoCodeParty>> findProductPromoCodePartysBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductPromoCodePartysBy query = new FindProductPromoCodePartysBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductPromoCodeParty> productPromoCodePartys =((ProductPromoCodePartyFound) Scheduler.execute(query).data()).getProductPromoCodePartys();

		return ResponseEntity.ok().body(productPromoCodePartys);

	}

	/**
	 * creates a new ProductPromoCodeParty entry in the ofbiz database
	 * 
	 * @param productPromoCodePartyToBeAdded
	 *            the ProductPromoCodeParty thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductPromoCodeParty> createProductPromoCodeParty(@RequestBody ProductPromoCodeParty productPromoCodePartyToBeAdded) throws Exception {

		AddProductPromoCodeParty command = new AddProductPromoCodeParty(productPromoCodePartyToBeAdded);
		ProductPromoCodeParty productPromoCodeParty = ((ProductPromoCodePartyAdded) Scheduler.execute(command).data()).getAddedProductPromoCodeParty();
		
		if (productPromoCodeParty != null) 
			return successful(productPromoCodeParty);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ProductPromoCodeParty with the specific Id
	 * 
	 * @param productPromoCodePartyToBeUpdated
	 *            the ProductPromoCodeParty thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateProductPromoCodeParty(@RequestBody ProductPromoCodeParty productPromoCodePartyToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		productPromoCodePartyToBeUpdated.setnull(null);

		UpdateProductPromoCodeParty command = new UpdateProductPromoCodeParty(productPromoCodePartyToBeUpdated);

		try {
			if(((ProductPromoCodePartyUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{productPromoCodePartyId}")
	public ResponseEntity<ProductPromoCodeParty> findById(@PathVariable String productPromoCodePartyId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productPromoCodePartyId", productPromoCodePartyId);
		try {

			List<ProductPromoCodeParty> foundProductPromoCodeParty = findProductPromoCodePartysBy(requestParams).getBody();
			if(foundProductPromoCodeParty.size()==1){				return successful(foundProductPromoCodeParty.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{productPromoCodePartyId}")
	public ResponseEntity<String> deleteProductPromoCodePartyByIdUpdated(@PathVariable String productPromoCodePartyId) throws Exception {
		DeleteProductPromoCodeParty command = new DeleteProductPromoCodeParty(productPromoCodePartyId);

		try {
			if (((ProductPromoCodePartyDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
