package com.skytala.eCommerce.domain.product.relations.product.control.searchConstraint;

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
import com.skytala.eCommerce.domain.product.relations.product.command.searchConstraint.AddProductSearchConstraint;
import com.skytala.eCommerce.domain.product.relations.product.command.searchConstraint.DeleteProductSearchConstraint;
import com.skytala.eCommerce.domain.product.relations.product.command.searchConstraint.UpdateProductSearchConstraint;
import com.skytala.eCommerce.domain.product.relations.product.event.searchConstraint.ProductSearchConstraintAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.searchConstraint.ProductSearchConstraintDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.searchConstraint.ProductSearchConstraintFound;
import com.skytala.eCommerce.domain.product.relations.product.event.searchConstraint.ProductSearchConstraintUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.searchConstraint.ProductSearchConstraintMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.searchConstraint.ProductSearchConstraint;
import com.skytala.eCommerce.domain.product.relations.product.query.searchConstraint.FindProductSearchConstraintsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/product/productSearchConstraints")
public class ProductSearchConstraintController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductSearchConstraintController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductSearchConstraint
	 * @return a List with the ProductSearchConstraints
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ProductSearchConstraint>> findProductSearchConstraintsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductSearchConstraintsBy query = new FindProductSearchConstraintsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductSearchConstraint> productSearchConstraints =((ProductSearchConstraintFound) Scheduler.execute(query).data()).getProductSearchConstraints();

		return ResponseEntity.ok().body(productSearchConstraints);

	}

	/**
	 * creates a new ProductSearchConstraint entry in the ofbiz database
	 * 
	 * @param productSearchConstraintToBeAdded
	 *            the ProductSearchConstraint thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductSearchConstraint> createProductSearchConstraint(@RequestBody ProductSearchConstraint productSearchConstraintToBeAdded) throws Exception {

		AddProductSearchConstraint command = new AddProductSearchConstraint(productSearchConstraintToBeAdded);
		ProductSearchConstraint productSearchConstraint = ((ProductSearchConstraintAdded) Scheduler.execute(command).data()).getAddedProductSearchConstraint();
		
		if (productSearchConstraint != null) 
			return successful(productSearchConstraint);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ProductSearchConstraint with the specific Id
	 * 
	 * @param productSearchConstraintToBeUpdated
	 *            the ProductSearchConstraint thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{constraintSeqId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateProductSearchConstraint(@RequestBody ProductSearchConstraint productSearchConstraintToBeUpdated,
			@PathVariable String constraintSeqId) throws Exception {

		productSearchConstraintToBeUpdated.setConstraintSeqId(constraintSeqId);

		UpdateProductSearchConstraint command = new UpdateProductSearchConstraint(productSearchConstraintToBeUpdated);

		try {
			if(((ProductSearchConstraintUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{productSearchConstraintId}")
	public ResponseEntity<ProductSearchConstraint> findById(@PathVariable String productSearchConstraintId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productSearchConstraintId", productSearchConstraintId);
		try {

			List<ProductSearchConstraint> foundProductSearchConstraint = findProductSearchConstraintsBy(requestParams).getBody();
			if(foundProductSearchConstraint.size()==1){				return successful(foundProductSearchConstraint.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{productSearchConstraintId}")
	public ResponseEntity<String> deleteProductSearchConstraintByIdUpdated(@PathVariable String productSearchConstraintId) throws Exception {
		DeleteProductSearchConstraint command = new DeleteProductSearchConstraint(productSearchConstraintId);

		try {
			if (((ProductSearchConstraintDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
