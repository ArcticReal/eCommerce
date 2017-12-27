package com.skytala.eCommerce.domain.product.relations.product.control.categoryLink;

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
import com.skytala.eCommerce.domain.product.relations.product.command.categoryLink.AddProductCategoryLink;
import com.skytala.eCommerce.domain.product.relations.product.command.categoryLink.DeleteProductCategoryLink;
import com.skytala.eCommerce.domain.product.relations.product.command.categoryLink.UpdateProductCategoryLink;
import com.skytala.eCommerce.domain.product.relations.product.event.categoryLink.ProductCategoryLinkAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.categoryLink.ProductCategoryLinkDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.categoryLink.ProductCategoryLinkFound;
import com.skytala.eCommerce.domain.product.relations.product.event.categoryLink.ProductCategoryLinkUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.categoryLink.ProductCategoryLinkMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.categoryLink.ProductCategoryLink;
import com.skytala.eCommerce.domain.product.relations.product.query.categoryLink.FindProductCategoryLinksBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/product/productCategoryLinks")
public class ProductCategoryLinkController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductCategoryLinkController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductCategoryLink
	 * @return a List with the ProductCategoryLinks
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ProductCategoryLink>> findProductCategoryLinksBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductCategoryLinksBy query = new FindProductCategoryLinksBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductCategoryLink> productCategoryLinks =((ProductCategoryLinkFound) Scheduler.execute(query).data()).getProductCategoryLinks();

		return ResponseEntity.ok().body(productCategoryLinks);

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
	public ResponseEntity<ProductCategoryLink> createProductCategoryLink(HttpServletRequest request) throws Exception {

		ProductCategoryLink productCategoryLinkToBeAdded = new ProductCategoryLink();
		try {
			productCategoryLinkToBeAdded = ProductCategoryLinkMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createProductCategoryLink(productCategoryLinkToBeAdded);

	}

	/**
	 * creates a new ProductCategoryLink entry in the ofbiz database
	 * 
	 * @param productCategoryLinkToBeAdded
	 *            the ProductCategoryLink thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductCategoryLink> createProductCategoryLink(@RequestBody ProductCategoryLink productCategoryLinkToBeAdded) throws Exception {

		AddProductCategoryLink command = new AddProductCategoryLink(productCategoryLinkToBeAdded);
		ProductCategoryLink productCategoryLink = ((ProductCategoryLinkAdded) Scheduler.execute(command).data()).getAddedProductCategoryLink();
		
		if (productCategoryLink != null) 
			return successful(productCategoryLink);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ProductCategoryLink with the specific Id
	 * 
	 * @param productCategoryLinkToBeUpdated
	 *            the ProductCategoryLink thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{linkSeqId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateProductCategoryLink(@RequestBody ProductCategoryLink productCategoryLinkToBeUpdated,
			@PathVariable String linkSeqId) throws Exception {

		productCategoryLinkToBeUpdated.setLinkSeqId(linkSeqId);

		UpdateProductCategoryLink command = new UpdateProductCategoryLink(productCategoryLinkToBeUpdated);

		try {
			if(((ProductCategoryLinkUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{productCategoryLinkId}")
	public ResponseEntity<ProductCategoryLink> findById(@PathVariable String productCategoryLinkId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productCategoryLinkId", productCategoryLinkId);
		try {

			List<ProductCategoryLink> foundProductCategoryLink = findProductCategoryLinksBy(requestParams).getBody();
			if(foundProductCategoryLink.size()==1){				return successful(foundProductCategoryLink.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{productCategoryLinkId}")
	public ResponseEntity<String> deleteProductCategoryLinkByIdUpdated(@PathVariable String productCategoryLinkId) throws Exception {
		DeleteProductCategoryLink command = new DeleteProductCategoryLink(productCategoryLinkId);

		try {
			if (((ProductCategoryLinkDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
