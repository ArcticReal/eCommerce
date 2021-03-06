package com.skytala.eCommerce.domain.product.relations.product.control.storePromoAppl;

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
import com.skytala.eCommerce.domain.product.relations.product.command.storePromoAppl.AddProductStorePromoAppl;
import com.skytala.eCommerce.domain.product.relations.product.command.storePromoAppl.DeleteProductStorePromoAppl;
import com.skytala.eCommerce.domain.product.relations.product.command.storePromoAppl.UpdateProductStorePromoAppl;
import com.skytala.eCommerce.domain.product.relations.product.event.storePromoAppl.ProductStorePromoApplAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.storePromoAppl.ProductStorePromoApplDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.storePromoAppl.ProductStorePromoApplFound;
import com.skytala.eCommerce.domain.product.relations.product.event.storePromoAppl.ProductStorePromoApplUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.storePromoAppl.ProductStorePromoApplMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.storePromoAppl.ProductStorePromoAppl;
import com.skytala.eCommerce.domain.product.relations.product.query.storePromoAppl.FindProductStorePromoApplsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/product/productStorePromoAppls")
public class ProductStorePromoApplController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductStorePromoApplController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductStorePromoAppl
	 * @return a List with the ProductStorePromoAppls
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ProductStorePromoAppl>> findProductStorePromoApplsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductStorePromoApplsBy query = new FindProductStorePromoApplsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductStorePromoAppl> productStorePromoAppls =((ProductStorePromoApplFound) Scheduler.execute(query).data()).getProductStorePromoAppls();

		return ResponseEntity.ok().body(productStorePromoAppls);

	}

	/**
	 * creates a new ProductStorePromoAppl entry in the ofbiz database
	 * 
	 * @param productStorePromoApplToBeAdded
	 *            the ProductStorePromoAppl thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductStorePromoAppl> createProductStorePromoAppl(@RequestBody ProductStorePromoAppl productStorePromoApplToBeAdded) throws Exception {

		AddProductStorePromoAppl command = new AddProductStorePromoAppl(productStorePromoApplToBeAdded);
		ProductStorePromoAppl productStorePromoAppl = ((ProductStorePromoApplAdded) Scheduler.execute(command).data()).getAddedProductStorePromoAppl();
		
		if (productStorePromoAppl != null) 
			return successful(productStorePromoAppl);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ProductStorePromoAppl with the specific Id
	 * 
	 * @param productStorePromoApplToBeUpdated
	 *            the ProductStorePromoAppl thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateProductStorePromoAppl(@RequestBody ProductStorePromoAppl productStorePromoApplToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		productStorePromoApplToBeUpdated.setnull(null);

		UpdateProductStorePromoAppl command = new UpdateProductStorePromoAppl(productStorePromoApplToBeUpdated);

		try {
			if(((ProductStorePromoApplUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{productStorePromoApplId}")
	public ResponseEntity<ProductStorePromoAppl> findById(@PathVariable String productStorePromoApplId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productStorePromoApplId", productStorePromoApplId);
		try {

			List<ProductStorePromoAppl> foundProductStorePromoAppl = findProductStorePromoApplsBy(requestParams).getBody();
			if(foundProductStorePromoAppl.size()==1){				return successful(foundProductStorePromoAppl.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{productStorePromoApplId}")
	public ResponseEntity<String> deleteProductStorePromoApplByIdUpdated(@PathVariable String productStorePromoApplId) throws Exception {
		DeleteProductStorePromoAppl command = new DeleteProductStorePromoAppl(productStorePromoApplId);

		try {
			if (((ProductStorePromoApplDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
