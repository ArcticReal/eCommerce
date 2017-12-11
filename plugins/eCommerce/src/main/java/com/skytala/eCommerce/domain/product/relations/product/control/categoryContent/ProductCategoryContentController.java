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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findProductCategoryContentsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductCategoryContentsBy query = new FindProductCategoryContentsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductCategoryContent> productCategoryContents =((ProductCategoryContentFound) Scheduler.execute(query).data()).getProductCategoryContents();

		if (productCategoryContents.size() == 1) {
			return ResponseEntity.ok().body(productCategoryContents.get(0));
		}

		return ResponseEntity.ok().body(productCategoryContents);

	}

	/**
	 * 
	 * this method will only be called by Springs DispatcherServlet
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createProductCategoryContent(HttpServletRequest request) throws Exception {

		ProductCategoryContent productCategoryContentToBeAdded = new ProductCategoryContent();
		try {
			productCategoryContentToBeAdded = ProductCategoryContentMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createProductCategoryContent(productCategoryContentToBeAdded);

	}

	/**
	 * creates a new ProductCategoryContent entry in the ofbiz database
	 * 
	 * @param productCategoryContentToBeAdded
	 *            the ProductCategoryContent thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createProductCategoryContent(@RequestBody ProductCategoryContent productCategoryContentToBeAdded) throws Exception {

		AddProductCategoryContent command = new AddProductCategoryContent(productCategoryContentToBeAdded);
		ProductCategoryContent productCategoryContent = ((ProductCategoryContentAdded) Scheduler.execute(command).data()).getAddedProductCategoryContent();
		
		if (productCategoryContent != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(productCategoryContent);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProductCategoryContent could not be created.");
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
	@RequestMapping(method = RequestMethod.PUT, value = "/update", consumes = "application/x-www-form-urlencoded")
	public boolean updateProductCategoryContent(HttpServletRequest request) throws Exception {

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

		ProductCategoryContent productCategoryContentToBeUpdated = new ProductCategoryContent();

		try {
			productCategoryContentToBeUpdated = ProductCategoryContentMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProductCategoryContent(productCategoryContentToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateProductCategoryContent(@RequestBody ProductCategoryContent productCategoryContentToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		productCategoryContentToBeUpdated.setnull(null);

		UpdateProductCategoryContent command = new UpdateProductCategoryContent(productCategoryContentToBeUpdated);

		try {
			if(((ProductCategoryContentUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{productCategoryContentId}")
	public ResponseEntity<Object> findById(@PathVariable String productCategoryContentId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productCategoryContentId", productCategoryContentId);
		try {

			Object foundProductCategoryContent = findProductCategoryContentsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProductCategoryContent);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{productCategoryContentId}")
	public ResponseEntity<Object> deleteProductCategoryContentByIdUpdated(@PathVariable String productCategoryContentId) throws Exception {
		DeleteProductCategoryContent command = new DeleteProductCategoryContent(productCategoryContentId);

		try {
			if (((ProductCategoryContentDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductCategoryContent could not be deleted");

	}

}
