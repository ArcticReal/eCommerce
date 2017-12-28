package com.skytala.eCommerce.domain.product.relations.product.control.categoryTypeAttr;

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
import com.skytala.eCommerce.domain.product.relations.product.command.categoryTypeAttr.AddProductCategoryTypeAttr;
import com.skytala.eCommerce.domain.product.relations.product.command.categoryTypeAttr.DeleteProductCategoryTypeAttr;
import com.skytala.eCommerce.domain.product.relations.product.command.categoryTypeAttr.UpdateProductCategoryTypeAttr;
import com.skytala.eCommerce.domain.product.relations.product.event.categoryTypeAttr.ProductCategoryTypeAttrAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.categoryTypeAttr.ProductCategoryTypeAttrDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.categoryTypeAttr.ProductCategoryTypeAttrFound;
import com.skytala.eCommerce.domain.product.relations.product.event.categoryTypeAttr.ProductCategoryTypeAttrUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.categoryTypeAttr.ProductCategoryTypeAttrMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.categoryTypeAttr.ProductCategoryTypeAttr;
import com.skytala.eCommerce.domain.product.relations.product.query.categoryTypeAttr.FindProductCategoryTypeAttrsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/product/productCategoryTypeAttrs")
public class ProductCategoryTypeAttrController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductCategoryTypeAttrController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductCategoryTypeAttr
	 * @return a List with the ProductCategoryTypeAttrs
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ProductCategoryTypeAttr>> findProductCategoryTypeAttrsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductCategoryTypeAttrsBy query = new FindProductCategoryTypeAttrsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductCategoryTypeAttr> productCategoryTypeAttrs =((ProductCategoryTypeAttrFound) Scheduler.execute(query).data()).getProductCategoryTypeAttrs();

		return ResponseEntity.ok().body(productCategoryTypeAttrs);

	}

	/**
	 * creates a new ProductCategoryTypeAttr entry in the ofbiz database
	 * 
	 * @param productCategoryTypeAttrToBeAdded
	 *            the ProductCategoryTypeAttr thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductCategoryTypeAttr> createProductCategoryTypeAttr(@RequestBody ProductCategoryTypeAttr productCategoryTypeAttrToBeAdded) throws Exception {

		AddProductCategoryTypeAttr command = new AddProductCategoryTypeAttr(productCategoryTypeAttrToBeAdded);
		ProductCategoryTypeAttr productCategoryTypeAttr = ((ProductCategoryTypeAttrAdded) Scheduler.execute(command).data()).getAddedProductCategoryTypeAttr();
		
		if (productCategoryTypeAttr != null) 
			return successful(productCategoryTypeAttr);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ProductCategoryTypeAttr with the specific Id
	 * 
	 * @param productCategoryTypeAttrToBeUpdated
	 *            the ProductCategoryTypeAttr thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{attrName}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateProductCategoryTypeAttr(@RequestBody ProductCategoryTypeAttr productCategoryTypeAttrToBeUpdated,
			@PathVariable String attrName) throws Exception {

		productCategoryTypeAttrToBeUpdated.setAttrName(attrName);

		UpdateProductCategoryTypeAttr command = new UpdateProductCategoryTypeAttr(productCategoryTypeAttrToBeUpdated);

		try {
			if(((ProductCategoryTypeAttrUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{productCategoryTypeAttrId}")
	public ResponseEntity<ProductCategoryTypeAttr> findById(@PathVariable String productCategoryTypeAttrId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productCategoryTypeAttrId", productCategoryTypeAttrId);
		try {

			List<ProductCategoryTypeAttr> foundProductCategoryTypeAttr = findProductCategoryTypeAttrsBy(requestParams).getBody();
			if(foundProductCategoryTypeAttr.size()==1){				return successful(foundProductCategoryTypeAttr.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{productCategoryTypeAttrId}")
	public ResponseEntity<String> deleteProductCategoryTypeAttrByIdUpdated(@PathVariable String productCategoryTypeAttrId) throws Exception {
		DeleteProductCategoryTypeAttr command = new DeleteProductCategoryTypeAttr(productCategoryTypeAttrId);

		try {
			if (((ProductCategoryTypeAttrDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
