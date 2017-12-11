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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findProductSearchConstraintsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductSearchConstraintsBy query = new FindProductSearchConstraintsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductSearchConstraint> productSearchConstraints =((ProductSearchConstraintFound) Scheduler.execute(query).data()).getProductSearchConstraints();

		if (productSearchConstraints.size() == 1) {
			return ResponseEntity.ok().body(productSearchConstraints.get(0));
		}

		return ResponseEntity.ok().body(productSearchConstraints);

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
	public ResponseEntity<Object> createProductSearchConstraint(HttpServletRequest request) throws Exception {

		ProductSearchConstraint productSearchConstraintToBeAdded = new ProductSearchConstraint();
		try {
			productSearchConstraintToBeAdded = ProductSearchConstraintMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createProductSearchConstraint(productSearchConstraintToBeAdded);

	}

	/**
	 * creates a new ProductSearchConstraint entry in the ofbiz database
	 * 
	 * @param productSearchConstraintToBeAdded
	 *            the ProductSearchConstraint thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createProductSearchConstraint(@RequestBody ProductSearchConstraint productSearchConstraintToBeAdded) throws Exception {

		AddProductSearchConstraint command = new AddProductSearchConstraint(productSearchConstraintToBeAdded);
		ProductSearchConstraint productSearchConstraint = ((ProductSearchConstraintAdded) Scheduler.execute(command).data()).getAddedProductSearchConstraint();
		
		if (productSearchConstraint != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(productSearchConstraint);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProductSearchConstraint could not be created.");
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
	public boolean updateProductSearchConstraint(HttpServletRequest request) throws Exception {

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

		ProductSearchConstraint productSearchConstraintToBeUpdated = new ProductSearchConstraint();

		try {
			productSearchConstraintToBeUpdated = ProductSearchConstraintMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProductSearchConstraint(productSearchConstraintToBeUpdated, productSearchConstraintToBeUpdated.getConstraintSeqId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateProductSearchConstraint(@RequestBody ProductSearchConstraint productSearchConstraintToBeUpdated,
			@PathVariable String constraintSeqId) throws Exception {

		productSearchConstraintToBeUpdated.setConstraintSeqId(constraintSeqId);

		UpdateProductSearchConstraint command = new UpdateProductSearchConstraint(productSearchConstraintToBeUpdated);

		try {
			if(((ProductSearchConstraintUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{productSearchConstraintId}")
	public ResponseEntity<Object> findById(@PathVariable String productSearchConstraintId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productSearchConstraintId", productSearchConstraintId);
		try {

			Object foundProductSearchConstraint = findProductSearchConstraintsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProductSearchConstraint);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{productSearchConstraintId}")
	public ResponseEntity<Object> deleteProductSearchConstraintByIdUpdated(@PathVariable String productSearchConstraintId) throws Exception {
		DeleteProductSearchConstraint command = new DeleteProductSearchConstraint(productSearchConstraintId);

		try {
			if (((ProductSearchConstraintDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductSearchConstraint could not be deleted");

	}

}
