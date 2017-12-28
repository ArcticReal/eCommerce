package com.skytala.eCommerce.domain.product.relations.product.control.categoryContent;

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
import com.skytala.eCommerce.domain.product.relations.product.command.categoryContent.AddProductCategoryContent;
import com.skytala.eCommerce.domain.product.relations.product.command.categoryContent.DeleteProductCategoryContent;
import com.skytala.eCommerce.domain.product.relations.product.command.categoryContent.UpdateProductCategoryContent;
import com.skytala.eCommerce.domain.product.relations.product.event.categoryContent.ProductCategoryContentAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.categoryContent.ProductCategoryContentDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.categoryContent.ProductCategoryContentFound;
import com.skytala.eCommerce.domain.product.relations.product.event.categoryContent.ProductCategoryContentUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.categoryContent.ProductCategoryContentMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.categoryContent.ProductCategoryContent;
import com.skytala.eCommerce.domain.product.relations.product.query.categoryContent.FindProductCategoryContentsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/product/productCategoryContents")
public class ProductCategoryContentController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductCategoryContentController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductCategoryContent
	 * @return a List with the ProductCategoryContents
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ProductCategoryContent>> findProductCategoryContentsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductCategoryContentsBy query = new FindProductCategoryContentsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductCategoryContent> productCategoryContents =((ProductCategoryContentFound) Scheduler.execute(query).data()).getProductCategoryContents();

		return ResponseEntity.ok().body(productCategoryContents);

	}

	/**
	 * creates a new ProductCategoryContent entry in the ofbiz database
	 * 
	 * @param productCategoryContentToBeAdded
	 *            the ProductCategoryContent thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductCategoryContent> createProductCategoryContent(@RequestBody ProductCategoryContent productCategoryContentToBeAdded) throws Exception {

		AddProductCategoryContent command = new AddProductCategoryContent(productCategoryContentToBeAdded);
		ProductCategoryContent productCategoryContent = ((ProductCategoryContentAdded) Scheduler.execute(command).data()).getAddedProductCategoryContent();
		
		if (productCategoryContent != null) 
			return successful(productCategoryContent);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ProductCategoryContent with the specific Id
	 * 
	 * @param productCategoryContentToBeUpdated
	 *            the ProductCategoryContent thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateProductCategoryContent(@RequestBody ProductCategoryContent productCategoryContentToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		productCategoryContentToBeUpdated.setnull(null);

		UpdateProductCategoryContent command = new UpdateProductCategoryContent(productCategoryContentToBeUpdated);

		try {
			if(((ProductCategoryContentUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{productCategoryContentId}")
	public ResponseEntity<ProductCategoryContent> findById(@PathVariable String productCategoryContentId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productCategoryContentId", productCategoryContentId);
		try {

			List<ProductCategoryContent> foundProductCategoryContent = findProductCategoryContentsBy(requestParams).getBody();
			if(foundProductCategoryContent.size()==1){				return successful(foundProductCategoryContent.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{productCategoryContentId}")
	public ResponseEntity<String> deleteProductCategoryContentByIdUpdated(@PathVariable String productCategoryContentId) throws Exception {
		DeleteProductCategoryContent command = new DeleteProductCategoryContent(productCategoryContentId);

		try {
			if (((ProductCategoryContentDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
