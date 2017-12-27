package com.skytala.eCommerce.domain.product.relations.product.control.review;

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
import com.skytala.eCommerce.domain.product.relations.product.command.review.AddProductReview;
import com.skytala.eCommerce.domain.product.relations.product.command.review.DeleteProductReview;
import com.skytala.eCommerce.domain.product.relations.product.command.review.UpdateProductReview;
import com.skytala.eCommerce.domain.product.relations.product.event.review.ProductReviewAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.review.ProductReviewDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.review.ProductReviewFound;
import com.skytala.eCommerce.domain.product.relations.product.event.review.ProductReviewUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.review.ProductReviewMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.review.ProductReview;
import com.skytala.eCommerce.domain.product.relations.product.query.review.FindProductReviewsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/product/productReviews")
public class ProductReviewController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductReviewController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductReview
	 * @return a List with the ProductReviews
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ProductReview>> findProductReviewsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductReviewsBy query = new FindProductReviewsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductReview> productReviews =((ProductReviewFound) Scheduler.execute(query).data()).getProductReviews();

		return ResponseEntity.ok().body(productReviews);

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
	public ResponseEntity<ProductReview> createProductReview(HttpServletRequest request) throws Exception {

		ProductReview productReviewToBeAdded = new ProductReview();
		try {
			productReviewToBeAdded = ProductReviewMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createProductReview(productReviewToBeAdded);

	}

	/**
	 * creates a new ProductReview entry in the ofbiz database
	 * 
	 * @param productReviewToBeAdded
	 *            the ProductReview thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductReview> createProductReview(@RequestBody ProductReview productReviewToBeAdded) throws Exception {

		AddProductReview command = new AddProductReview(productReviewToBeAdded);
		ProductReview productReview = ((ProductReviewAdded) Scheduler.execute(command).data()).getAddedProductReview();
		
		if (productReview != null) 
			return successful(productReview);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ProductReview with the specific Id
	 * 
	 * @param productReviewToBeUpdated
	 *            the ProductReview thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{productReviewId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateProductReview(@RequestBody ProductReview productReviewToBeUpdated,
			@PathVariable String productReviewId) throws Exception {

		productReviewToBeUpdated.setProductReviewId(productReviewId);

		UpdateProductReview command = new UpdateProductReview(productReviewToBeUpdated);

		try {
			if(((ProductReviewUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{productReviewId}")
	public ResponseEntity<ProductReview> findById(@PathVariable String productReviewId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productReviewId", productReviewId);
		try {

			List<ProductReview> foundProductReview = findProductReviewsBy(requestParams).getBody();
			if(foundProductReview.size()==1){				return successful(foundProductReview.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{productReviewId}")
	public ResponseEntity<String> deleteProductReviewByIdUpdated(@PathVariable String productReviewId) throws Exception {
		DeleteProductReview command = new DeleteProductReview(productReviewId);

		try {
			if (((ProductReviewDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
