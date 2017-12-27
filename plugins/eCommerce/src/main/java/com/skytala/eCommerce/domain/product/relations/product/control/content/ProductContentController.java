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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	@GetMapping("/find")
	public ResponseEntity<List<ProductContent>> findProductContentsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductContentsBy query = new FindProductContentsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductContent> productContents =((ProductContentFound) Scheduler.execute(query).data()).getProductContents();

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
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<ProductContent> createProductContent(HttpServletRequest request) throws Exception {

		ProductContent productContentToBeAdded = new ProductContent();
		try {
			productContentToBeAdded = ProductContentMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
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
	public ResponseEntity<ProductContent> createProductContent(@RequestBody ProductContent productContentToBeAdded) throws Exception {

		AddProductContent command = new AddProductContent(productContentToBeAdded);
		ProductContent productContent = ((ProductContentAdded) Scheduler.execute(command).data()).getAddedProductContent();
		
		if (productContent != null) 
			return successful(productContent);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateProductContent(@RequestBody ProductContent productContentToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		productContentToBeUpdated.setnull(null);

		UpdateProductContent command = new UpdateProductContent(productContentToBeUpdated);

		try {
			if(((ProductContentUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{productContentId}")
	public ResponseEntity<ProductContent> findById(@PathVariable String productContentId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productContentId", productContentId);
		try {

			List<ProductContent> foundProductContent = findProductContentsBy(requestParams).getBody();
			if(foundProductContent.size()==1){				return successful(foundProductContent.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{productContentId}")
	public ResponseEntity<String> deleteProductContentByIdUpdated(@PathVariable String productContentId) throws Exception {
		DeleteProductContent command = new DeleteProductContent(productContentId);

		try {
			if (((ProductContentDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
