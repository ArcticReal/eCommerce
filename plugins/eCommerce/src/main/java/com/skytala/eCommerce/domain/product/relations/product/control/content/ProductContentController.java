package com.skytala.eCommerce.domain.product.relations.product.control.content;

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
import com.skytala.eCommerce.domain.product.relations.product.command.content.AddProductContent;
import com.skytala.eCommerce.domain.product.relations.product.command.content.DeleteProductContent;
import com.skytala.eCommerce.domain.product.relations.product.command.content.UpdateProductContent;
import com.skytala.eCommerce.domain.product.relations.product.event.content.ProductContentAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.content.ProductContentDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.content.ProductContentFound;
import com.skytala.eCommerce.domain.product.relations.product.event.content.ProductContentUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.content.ProductContentMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.content.ProductContent;
import com.skytala.eCommerce.domain.product.relations.product.query.content.FindProductContentsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/product/product/productContents")
public class ProductContentController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductContentController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductContent
	 * @return a List with the ProductContents
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findProductContentsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductContentsBy query = new FindProductContentsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductContent> productContents =((ProductContentFound) Scheduler.execute(query).data()).getProductContents();

		if (productContents.size() == 1) {
			return ResponseEntity.ok().body(productContents.get(0));
		}

		return ResponseEntity.ok().body(productContents);

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
	public ResponseEntity<Object> createProductContent(HttpServletRequest request) throws Exception {

		ProductContent productContentToBeAdded = new ProductContent();
		try {
			productContentToBeAdded = ProductContentMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createProductContent(productContentToBeAdded);

	}

	/**
	 * creates a new ProductContent entry in the ofbiz database
	 * 
	 * @param productContentToBeAdded
	 *            the ProductContent thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createProductContent(@RequestBody ProductContent productContentToBeAdded) throws Exception {

		AddProductContent command = new AddProductContent(productContentToBeAdded);
		ProductContent productContent = ((ProductContentAdded) Scheduler.execute(command).data()).getAddedProductContent();
		
		if (productContent != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(productContent);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProductContent could not be created.");
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
	public boolean updateProductContent(HttpServletRequest request) throws Exception {

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

		ProductContent productContentToBeUpdated = new ProductContent();

		try {
			productContentToBeUpdated = ProductContentMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProductContent(productContentToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ProductContent with the specific Id
	 * 
	 * @param productContentToBeUpdated
	 *            the ProductContent thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateProductContent(@RequestBody ProductContent productContentToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		productContentToBeUpdated.setnull(null);

		UpdateProductContent command = new UpdateProductContent(productContentToBeUpdated);

		try {
			if(((ProductContentUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{productContentId}")
	public ResponseEntity<Object> findById(@PathVariable String productContentId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productContentId", productContentId);
		try {

			Object foundProductContent = findProductContentsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProductContent);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{productContentId}")
	public ResponseEntity<Object> deleteProductContentByIdUpdated(@PathVariable String productContentId) throws Exception {
		DeleteProductContent command = new DeleteProductContent(productContentId);

		try {
			if (((ProductContentDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductContent could not be deleted");

	}

}
