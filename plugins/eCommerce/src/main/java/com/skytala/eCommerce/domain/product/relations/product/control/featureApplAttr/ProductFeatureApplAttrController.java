package com.skytala.eCommerce.domain.product.relations.product.control.featureApplAttr;

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
import com.skytala.eCommerce.domain.product.relations.product.command.featureApplAttr.AddProductFeatureApplAttr;
import com.skytala.eCommerce.domain.product.relations.product.command.featureApplAttr.DeleteProductFeatureApplAttr;
import com.skytala.eCommerce.domain.product.relations.product.command.featureApplAttr.UpdateProductFeatureApplAttr;
import com.skytala.eCommerce.domain.product.relations.product.event.featureApplAttr.ProductFeatureApplAttrAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.featureApplAttr.ProductFeatureApplAttrDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.featureApplAttr.ProductFeatureApplAttrFound;
import com.skytala.eCommerce.domain.product.relations.product.event.featureApplAttr.ProductFeatureApplAttrUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.featureApplAttr.ProductFeatureApplAttrMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.featureApplAttr.ProductFeatureApplAttr;
import com.skytala.eCommerce.domain.product.relations.product.query.featureApplAttr.FindProductFeatureApplAttrsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/product/productFeatureApplAttrs")
public class ProductFeatureApplAttrController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductFeatureApplAttrController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductFeatureApplAttr
	 * @return a List with the ProductFeatureApplAttrs
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ProductFeatureApplAttr>> findProductFeatureApplAttrsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductFeatureApplAttrsBy query = new FindProductFeatureApplAttrsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductFeatureApplAttr> productFeatureApplAttrs =((ProductFeatureApplAttrFound) Scheduler.execute(query).data()).getProductFeatureApplAttrs();

		return ResponseEntity.ok().body(productFeatureApplAttrs);

	}

	/**
	 * creates a new ProductFeatureApplAttr entry in the ofbiz database
	 * 
	 * @param productFeatureApplAttrToBeAdded
	 *            the ProductFeatureApplAttr thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductFeatureApplAttr> createProductFeatureApplAttr(@RequestBody ProductFeatureApplAttr productFeatureApplAttrToBeAdded) throws Exception {

		AddProductFeatureApplAttr command = new AddProductFeatureApplAttr(productFeatureApplAttrToBeAdded);
		ProductFeatureApplAttr productFeatureApplAttr = ((ProductFeatureApplAttrAdded) Scheduler.execute(command).data()).getAddedProductFeatureApplAttr();
		
		if (productFeatureApplAttr != null) 
			return successful(productFeatureApplAttr);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ProductFeatureApplAttr with the specific Id
	 * 
	 * @param productFeatureApplAttrToBeUpdated
	 *            the ProductFeatureApplAttr thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{attrName}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateProductFeatureApplAttr(@RequestBody ProductFeatureApplAttr productFeatureApplAttrToBeUpdated,
			@PathVariable String attrName) throws Exception {

		productFeatureApplAttrToBeUpdated.setAttrName(attrName);

		UpdateProductFeatureApplAttr command = new UpdateProductFeatureApplAttr(productFeatureApplAttrToBeUpdated);

		try {
			if(((ProductFeatureApplAttrUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{productFeatureApplAttrId}")
	public ResponseEntity<ProductFeatureApplAttr> findById(@PathVariable String productFeatureApplAttrId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productFeatureApplAttrId", productFeatureApplAttrId);
		try {

			List<ProductFeatureApplAttr> foundProductFeatureApplAttr = findProductFeatureApplAttrsBy(requestParams).getBody();
			if(foundProductFeatureApplAttr.size()==1){				return successful(foundProductFeatureApplAttr.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{productFeatureApplAttrId}")
	public ResponseEntity<String> deleteProductFeatureApplAttrByIdUpdated(@PathVariable String productFeatureApplAttrId) throws Exception {
		DeleteProductFeatureApplAttr command = new DeleteProductFeatureApplAttr(productFeatureApplAttrId);

		try {
			if (((ProductFeatureApplAttrDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
