package com.skytala.eCommerce.domain.product.relations.product.control.featureCategoryAppl;

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
import com.skytala.eCommerce.domain.product.relations.product.command.featureCategoryAppl.AddProductFeatureCategoryAppl;
import com.skytala.eCommerce.domain.product.relations.product.command.featureCategoryAppl.DeleteProductFeatureCategoryAppl;
import com.skytala.eCommerce.domain.product.relations.product.command.featureCategoryAppl.UpdateProductFeatureCategoryAppl;
import com.skytala.eCommerce.domain.product.relations.product.event.featureCategoryAppl.ProductFeatureCategoryApplAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.featureCategoryAppl.ProductFeatureCategoryApplDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.featureCategoryAppl.ProductFeatureCategoryApplFound;
import com.skytala.eCommerce.domain.product.relations.product.event.featureCategoryAppl.ProductFeatureCategoryApplUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.featureCategoryAppl.ProductFeatureCategoryApplMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.featureCategoryAppl.ProductFeatureCategoryAppl;
import com.skytala.eCommerce.domain.product.relations.product.query.featureCategoryAppl.FindProductFeatureCategoryApplsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/product/productFeatureCategoryAppls")
public class ProductFeatureCategoryApplController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductFeatureCategoryApplController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductFeatureCategoryAppl
	 * @return a List with the ProductFeatureCategoryAppls
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ProductFeatureCategoryAppl>> findProductFeatureCategoryApplsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductFeatureCategoryApplsBy query = new FindProductFeatureCategoryApplsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductFeatureCategoryAppl> productFeatureCategoryAppls =((ProductFeatureCategoryApplFound) Scheduler.execute(query).data()).getProductFeatureCategoryAppls();

		return ResponseEntity.ok().body(productFeatureCategoryAppls);

	}

	/**
	 * creates a new ProductFeatureCategoryAppl entry in the ofbiz database
	 * 
	 * @param productFeatureCategoryApplToBeAdded
	 *            the ProductFeatureCategoryAppl thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductFeatureCategoryAppl> createProductFeatureCategoryAppl(@RequestBody ProductFeatureCategoryAppl productFeatureCategoryApplToBeAdded) throws Exception {

		AddProductFeatureCategoryAppl command = new AddProductFeatureCategoryAppl(productFeatureCategoryApplToBeAdded);
		ProductFeatureCategoryAppl productFeatureCategoryAppl = ((ProductFeatureCategoryApplAdded) Scheduler.execute(command).data()).getAddedProductFeatureCategoryAppl();
		
		if (productFeatureCategoryAppl != null) 
			return successful(productFeatureCategoryAppl);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ProductFeatureCategoryAppl with the specific Id
	 * 
	 * @param productFeatureCategoryApplToBeUpdated
	 *            the ProductFeatureCategoryAppl thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateProductFeatureCategoryAppl(@RequestBody ProductFeatureCategoryAppl productFeatureCategoryApplToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		productFeatureCategoryApplToBeUpdated.setnull(null);

		UpdateProductFeatureCategoryAppl command = new UpdateProductFeatureCategoryAppl(productFeatureCategoryApplToBeUpdated);

		try {
			if(((ProductFeatureCategoryApplUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{productFeatureCategoryApplId}")
	public ResponseEntity<ProductFeatureCategoryAppl> findById(@PathVariable String productFeatureCategoryApplId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productFeatureCategoryApplId", productFeatureCategoryApplId);
		try {

			List<ProductFeatureCategoryAppl> foundProductFeatureCategoryAppl = findProductFeatureCategoryApplsBy(requestParams).getBody();
			if(foundProductFeatureCategoryAppl.size()==1){				return successful(foundProductFeatureCategoryAppl.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{productFeatureCategoryApplId}")
	public ResponseEntity<String> deleteProductFeatureCategoryApplByIdUpdated(@PathVariable String productFeatureCategoryApplId) throws Exception {
		DeleteProductFeatureCategoryAppl command = new DeleteProductFeatureCategoryAppl(productFeatureCategoryApplId);

		try {
			if (((ProductFeatureCategoryApplDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
