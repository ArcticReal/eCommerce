package com.skytala.eCommerce.domain.product.relations.product.control.categoryAttribute;

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
import com.skytala.eCommerce.domain.product.relations.product.command.categoryAttribute.AddProductCategoryAttribute;
import com.skytala.eCommerce.domain.product.relations.product.command.categoryAttribute.DeleteProductCategoryAttribute;
import com.skytala.eCommerce.domain.product.relations.product.command.categoryAttribute.UpdateProductCategoryAttribute;
import com.skytala.eCommerce.domain.product.relations.product.event.categoryAttribute.ProductCategoryAttributeAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.categoryAttribute.ProductCategoryAttributeDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.categoryAttribute.ProductCategoryAttributeFound;
import com.skytala.eCommerce.domain.product.relations.product.event.categoryAttribute.ProductCategoryAttributeUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.categoryAttribute.ProductCategoryAttributeMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.categoryAttribute.ProductCategoryAttribute;
import com.skytala.eCommerce.domain.product.relations.product.query.categoryAttribute.FindProductCategoryAttributesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/product/productCategoryAttributes")
public class ProductCategoryAttributeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductCategoryAttributeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductCategoryAttribute
	 * @return a List with the ProductCategoryAttributes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ProductCategoryAttribute>> findProductCategoryAttributesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductCategoryAttributesBy query = new FindProductCategoryAttributesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductCategoryAttribute> productCategoryAttributes =((ProductCategoryAttributeFound) Scheduler.execute(query).data()).getProductCategoryAttributes();

		return ResponseEntity.ok().body(productCategoryAttributes);

	}

	/**
	 * creates a new ProductCategoryAttribute entry in the ofbiz database
	 * 
	 * @param productCategoryAttributeToBeAdded
	 *            the ProductCategoryAttribute thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductCategoryAttribute> createProductCategoryAttribute(@RequestBody ProductCategoryAttribute productCategoryAttributeToBeAdded) throws Exception {

		AddProductCategoryAttribute command = new AddProductCategoryAttribute(productCategoryAttributeToBeAdded);
		ProductCategoryAttribute productCategoryAttribute = ((ProductCategoryAttributeAdded) Scheduler.execute(command).data()).getAddedProductCategoryAttribute();
		
		if (productCategoryAttribute != null) 
			return successful(productCategoryAttribute);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ProductCategoryAttribute with the specific Id
	 * 
	 * @param productCategoryAttributeToBeUpdated
	 *            the ProductCategoryAttribute thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{attrName}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateProductCategoryAttribute(@RequestBody ProductCategoryAttribute productCategoryAttributeToBeUpdated,
			@PathVariable String attrName) throws Exception {

		productCategoryAttributeToBeUpdated.setAttrName(attrName);

		UpdateProductCategoryAttribute command = new UpdateProductCategoryAttribute(productCategoryAttributeToBeUpdated);

		try {
			if(((ProductCategoryAttributeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{productCategoryAttributeId}")
	public ResponseEntity<ProductCategoryAttribute> findById(@PathVariable String productCategoryAttributeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productCategoryAttributeId", productCategoryAttributeId);
		try {

			List<ProductCategoryAttribute> foundProductCategoryAttribute = findProductCategoryAttributesBy(requestParams).getBody();
			if(foundProductCategoryAttribute.size()==1){				return successful(foundProductCategoryAttribute.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{productCategoryAttributeId}")
	public ResponseEntity<String> deleteProductCategoryAttributeByIdUpdated(@PathVariable String productCategoryAttributeId) throws Exception {
		DeleteProductCategoryAttribute command = new DeleteProductCategoryAttribute(productCategoryAttributeId);

		try {
			if (((ProductCategoryAttributeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
