package com.skytala.eCommerce.domain.product.relations.product.control.promoContent;

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
import com.skytala.eCommerce.domain.product.relations.product.command.promoContent.AddProductPromoContent;
import com.skytala.eCommerce.domain.product.relations.product.command.promoContent.DeleteProductPromoContent;
import com.skytala.eCommerce.domain.product.relations.product.command.promoContent.UpdateProductPromoContent;
import com.skytala.eCommerce.domain.product.relations.product.event.promoContent.ProductPromoContentAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.promoContent.ProductPromoContentDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.promoContent.ProductPromoContentFound;
import com.skytala.eCommerce.domain.product.relations.product.event.promoContent.ProductPromoContentUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.promoContent.ProductPromoContentMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.promoContent.ProductPromoContent;
import com.skytala.eCommerce.domain.product.relations.product.query.promoContent.FindProductPromoContentsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/product/productPromoContents")
public class ProductPromoContentController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductPromoContentController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductPromoContent
	 * @return a List with the ProductPromoContents
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ProductPromoContent>> findProductPromoContentsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductPromoContentsBy query = new FindProductPromoContentsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductPromoContent> productPromoContents =((ProductPromoContentFound) Scheduler.execute(query).data()).getProductPromoContents();

		return ResponseEntity.ok().body(productPromoContents);

	}

	/**
	 * creates a new ProductPromoContent entry in the ofbiz database
	 * 
	 * @param productPromoContentToBeAdded
	 *            the ProductPromoContent thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductPromoContent> createProductPromoContent(@RequestBody ProductPromoContent productPromoContentToBeAdded) throws Exception {

		AddProductPromoContent command = new AddProductPromoContent(productPromoContentToBeAdded);
		ProductPromoContent productPromoContent = ((ProductPromoContentAdded) Scheduler.execute(command).data()).getAddedProductPromoContent();
		
		if (productPromoContent != null) 
			return successful(productPromoContent);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ProductPromoContent with the specific Id
	 * 
	 * @param productPromoContentToBeUpdated
	 *            the ProductPromoContent thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateProductPromoContent(@RequestBody ProductPromoContent productPromoContentToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		productPromoContentToBeUpdated.setnull(null);

		UpdateProductPromoContent command = new UpdateProductPromoContent(productPromoContentToBeUpdated);

		try {
			if(((ProductPromoContentUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{productPromoContentId}")
	public ResponseEntity<ProductPromoContent> findById(@PathVariable String productPromoContentId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productPromoContentId", productPromoContentId);
		try {

			List<ProductPromoContent> foundProductPromoContent = findProductPromoContentsBy(requestParams).getBody();
			if(foundProductPromoContent.size()==1){				return successful(foundProductPromoContent.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{productPromoContentId}")
	public ResponseEntity<String> deleteProductPromoContentByIdUpdated(@PathVariable String productPromoContentId) throws Exception {
		DeleteProductPromoContent command = new DeleteProductPromoContent(productPromoContentId);

		try {
			if (((ProductPromoContentDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
