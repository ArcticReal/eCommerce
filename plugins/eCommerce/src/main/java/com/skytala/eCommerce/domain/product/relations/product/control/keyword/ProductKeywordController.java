package com.skytala.eCommerce.domain.product.relations.product.control.keyword;

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
import com.skytala.eCommerce.domain.product.relations.product.command.keyword.AddProductKeyword;
import com.skytala.eCommerce.domain.product.relations.product.command.keyword.DeleteProductKeyword;
import com.skytala.eCommerce.domain.product.relations.product.command.keyword.UpdateProductKeyword;
import com.skytala.eCommerce.domain.product.relations.product.event.keyword.ProductKeywordAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.keyword.ProductKeywordDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.keyword.ProductKeywordFound;
import com.skytala.eCommerce.domain.product.relations.product.event.keyword.ProductKeywordUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.keyword.ProductKeywordMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.keyword.ProductKeyword;
import com.skytala.eCommerce.domain.product.relations.product.query.keyword.FindProductKeywordsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/product/productKeywords")
public class ProductKeywordController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductKeywordController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductKeyword
	 * @return a List with the ProductKeywords
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ProductKeyword>> findProductKeywordsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductKeywordsBy query = new FindProductKeywordsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductKeyword> productKeywords =((ProductKeywordFound) Scheduler.execute(query).data()).getProductKeywords();

		return ResponseEntity.ok().body(productKeywords);

	}

	/**
	 * creates a new ProductKeyword entry in the ofbiz database
	 * 
	 * @param productKeywordToBeAdded
	 *            the ProductKeyword thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductKeyword> createProductKeyword(@RequestBody ProductKeyword productKeywordToBeAdded) throws Exception {

		AddProductKeyword command = new AddProductKeyword(productKeywordToBeAdded);
		ProductKeyword productKeyword = ((ProductKeywordAdded) Scheduler.execute(command).data()).getAddedProductKeyword();
		
		if (productKeyword != null) 
			return successful(productKeyword);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ProductKeyword with the specific Id
	 * 
	 * @param productKeywordToBeUpdated
	 *            the ProductKeyword thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{keyword}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateProductKeyword(@RequestBody ProductKeyword productKeywordToBeUpdated,
			@PathVariable String keyword) throws Exception {

		productKeywordToBeUpdated.setKeyword(keyword);

		UpdateProductKeyword command = new UpdateProductKeyword(productKeywordToBeUpdated);

		try {
			if(((ProductKeywordUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{productKeywordId}")
	public ResponseEntity<ProductKeyword> findById(@PathVariable String productKeywordId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productKeywordId", productKeywordId);
		try {

			List<ProductKeyword> foundProductKeyword = findProductKeywordsBy(requestParams).getBody();
			if(foundProductKeyword.size()==1){				return successful(foundProductKeyword.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{productKeywordId}")
	public ResponseEntity<String> deleteProductKeywordByIdUpdated(@PathVariable String productKeywordId) throws Exception {
		DeleteProductKeyword command = new DeleteProductKeyword(productKeywordId);

		try {
			if (((ProductKeywordDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
