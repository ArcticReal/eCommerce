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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<Object> findProductKeywordsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductKeywordsBy query = new FindProductKeywordsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductKeyword> productKeywords =((ProductKeywordFound) Scheduler.execute(query).data()).getProductKeywords();

		if (productKeywords.size() == 1) {
			return ResponseEntity.ok().body(productKeywords.get(0));
		}

		return ResponseEntity.ok().body(productKeywords);

	}

	/**
	 * 
	 * this method will only be called by Springs DispatcherServlet
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return true on success; false on fail
	 */
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createProductKeyword(HttpServletRequest request) throws Exception {

		ProductKeyword productKeywordToBeAdded = new ProductKeyword();
		try {
			productKeywordToBeAdded = ProductKeywordMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createProductKeyword(productKeywordToBeAdded);

	}

	/**
	 * creates a new ProductKeyword entry in the ofbiz database
	 * 
	 * @param productKeywordToBeAdded
	 *            the ProductKeyword thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createProductKeyword(@RequestBody ProductKeyword productKeywordToBeAdded) throws Exception {

		AddProductKeyword command = new AddProductKeyword(productKeywordToBeAdded);
		ProductKeyword productKeyword = ((ProductKeywordAdded) Scheduler.execute(command).data()).getAddedProductKeyword();
		
		if (productKeyword != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(productKeyword);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProductKeyword could not be created.");
	}

	/**
	 * this method will only be called by Springs DispatcherServlet
	 * 
	 * @deprecated
	 * @param request
	 *            HttpServletRequest object
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@PutMapping(value = "/update", consumes = "application/x-www-form-urlencoded")
	public boolean updateProductKeyword(HttpServletRequest request) throws Exception {

		BufferedReader br;
		String data = null;
		Map<String, String> dataMap = null;

		try {
			br = new BufferedReader(new InputStreamReader(request.getInputStream()));
			if (br != null) {
				data = java.net.URLDecoder.decode(br.readLine(), "UTF-8");
			}
		} catch (IOException e1) {
			e1.printStackTrace();
			return false;
		}

		dataMap = Splitter.on('&').trimResults().withKeyValueSeparator(Splitter.on('=').limit(2).trimResults())
				.split(data);

		ProductKeyword productKeywordToBeUpdated = new ProductKeyword();

		try {
			productKeywordToBeUpdated = ProductKeywordMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProductKeyword(productKeywordToBeUpdated, productKeywordToBeUpdated.getKeyword()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateProductKeyword(@RequestBody ProductKeyword productKeywordToBeUpdated,
			@PathVariable String keyword) throws Exception {

		productKeywordToBeUpdated.setKeyword(keyword);

		UpdateProductKeyword command = new UpdateProductKeyword(productKeywordToBeUpdated);

		try {
			if(((ProductKeywordUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{productKeywordId}")
	public ResponseEntity<Object> findById(@PathVariable String productKeywordId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productKeywordId", productKeywordId);
		try {

			Object foundProductKeyword = findProductKeywordsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProductKeyword);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{productKeywordId}")
	public ResponseEntity<Object> deleteProductKeywordByIdUpdated(@PathVariable String productKeywordId) throws Exception {
		DeleteProductKeyword command = new DeleteProductKeyword(productKeywordId);

		try {
			if (((ProductKeywordDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductKeyword could not be deleted");

	}

}
