package com.skytala.eCommerce.domain.product.relations.product.control.featureAppl;

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
import com.skytala.eCommerce.domain.product.relations.product.command.featureAppl.AddProductFeatureAppl;
import com.skytala.eCommerce.domain.product.relations.product.command.featureAppl.DeleteProductFeatureAppl;
import com.skytala.eCommerce.domain.product.relations.product.command.featureAppl.UpdateProductFeatureAppl;
import com.skytala.eCommerce.domain.product.relations.product.event.featureAppl.ProductFeatureApplAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.featureAppl.ProductFeatureApplDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.featureAppl.ProductFeatureApplFound;
import com.skytala.eCommerce.domain.product.relations.product.event.featureAppl.ProductFeatureApplUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.featureAppl.ProductFeatureApplMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.featureAppl.ProductFeatureAppl;
import com.skytala.eCommerce.domain.product.relations.product.query.featureAppl.FindProductFeatureApplsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/product/productFeatureAppls")
public class ProductFeatureApplController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductFeatureApplController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductFeatureAppl
	 * @return a List with the ProductFeatureAppls
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ProductFeatureAppl>> findProductFeatureApplsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductFeatureApplsBy query = new FindProductFeatureApplsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductFeatureAppl> productFeatureAppls =((ProductFeatureApplFound) Scheduler.execute(query).data()).getProductFeatureAppls();

		return ResponseEntity.ok().body(productFeatureAppls);

	}

	/**
	 * creates a new ProductFeatureAppl entry in the ofbiz database
	 * 
	 * @param productFeatureApplToBeAdded
	 *            the ProductFeatureAppl thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductFeatureAppl> createProductFeatureAppl(@RequestBody ProductFeatureAppl productFeatureApplToBeAdded) throws Exception {

		AddProductFeatureAppl command = new AddProductFeatureAppl(productFeatureApplToBeAdded);
		ProductFeatureAppl productFeatureAppl = ((ProductFeatureApplAdded) Scheduler.execute(command).data()).getAddedProductFeatureAppl();
		
		if (productFeatureAppl != null) 
			return successful(productFeatureAppl);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ProductFeatureAppl with the specific Id
	 * 
	 * @param productFeatureApplToBeUpdated
	 *            the ProductFeatureAppl thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateProductFeatureAppl(@RequestBody ProductFeatureAppl productFeatureApplToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		productFeatureApplToBeUpdated.setnull(null);

		UpdateProductFeatureAppl command = new UpdateProductFeatureAppl(productFeatureApplToBeUpdated);

		try {
			if(((ProductFeatureApplUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{productFeatureApplId}")
	public ResponseEntity<ProductFeatureAppl> findById(@PathVariable String productFeatureApplId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productFeatureApplId", productFeatureApplId);
		try {

			List<ProductFeatureAppl> foundProductFeatureAppl = findProductFeatureApplsBy(requestParams).getBody();
			if(foundProductFeatureAppl.size()==1){				return successful(foundProductFeatureAppl.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{productFeatureApplId}")
	public ResponseEntity<String> deleteProductFeatureApplByIdUpdated(@PathVariable String productFeatureApplId) throws Exception {
		DeleteProductFeatureAppl command = new DeleteProductFeatureAppl(productFeatureApplId);

		try {
			if (((ProductFeatureApplDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
