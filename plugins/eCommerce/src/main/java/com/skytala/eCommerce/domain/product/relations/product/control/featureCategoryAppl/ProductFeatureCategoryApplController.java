package com.skytala.eCommerce.domain.product.relations.product.control.featureCategoryAppl;

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
import com.skytala.eCommerce.domain.product.relations.product.command.featureCategoryAppl.AddProductFeatureCategoryAppl;
import com.skytala.eCommerce.domain.product.relations.product.command.featureCategoryAppl.DeleteProductFeatureCategoryAppl;
import com.skytala.eCommerce.domain.product.relations.product.command.featureCategoryAppl.UpdateProductFeatureCategoryAppl;
import com.skytala.eCommerce.domain.product.relations.product.event.featureCategoryAppl.ProductFeatureCategoryApplAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.featureCategoryAppl.ProductFeatureCategoryApplDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.featureCategoryAppl.ProductFeatureCategoryApplFound;
import com.skytala.eCommerce.domain.product.relations.product.event.featureCategoryAppl.ProductFeatureCategoryApplUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.featureCategoryAppl.ProductFeatureCategoryApplMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.featureCategoryAppl.ProductFeatureCategoryAppl;
import com.skytala.eCommerce.domain.product.relations.product.query.featureCategoryAppl.FindProductFeatureCategoryApplsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/product/product/productFeatureCategoryAppls")
public class ProductFeatureCategoryApplController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductFeatureCategoryApplController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductFeatureCategoryAppl
	 * @return a List with the ProductFeatureCategoryAppls
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findProductFeatureCategoryApplsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductFeatureCategoryApplsBy query = new FindProductFeatureCategoryApplsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductFeatureCategoryAppl> productFeatureCategoryAppls =((ProductFeatureCategoryApplFound) Scheduler.execute(query).data()).getProductFeatureCategoryAppls();

		if (productFeatureCategoryAppls.size() == 1) {
			return ResponseEntity.ok().body(productFeatureCategoryAppls.get(0));
		}

		return ResponseEntity.ok().body(productFeatureCategoryAppls);

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
	public ResponseEntity<Object> createProductFeatureCategoryAppl(HttpServletRequest request) throws Exception {

		ProductFeatureCategoryAppl productFeatureCategoryApplToBeAdded = new ProductFeatureCategoryAppl();
		try {
			productFeatureCategoryApplToBeAdded = ProductFeatureCategoryApplMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createProductFeatureCategoryAppl(productFeatureCategoryApplToBeAdded);

	}

	/**
	 * creates a new ProductFeatureCategoryAppl entry in the ofbiz database
	 * 
	 * @param productFeatureCategoryApplToBeAdded
	 *            the ProductFeatureCategoryAppl thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createProductFeatureCategoryAppl(@RequestBody ProductFeatureCategoryAppl productFeatureCategoryApplToBeAdded) throws Exception {

		AddProductFeatureCategoryAppl command = new AddProductFeatureCategoryAppl(productFeatureCategoryApplToBeAdded);
		ProductFeatureCategoryAppl productFeatureCategoryAppl = ((ProductFeatureCategoryApplAdded) Scheduler.execute(command).data()).getAddedProductFeatureCategoryAppl();
		
		if (productFeatureCategoryAppl != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(productFeatureCategoryAppl);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProductFeatureCategoryAppl could not be created.");
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
	public boolean updateProductFeatureCategoryAppl(HttpServletRequest request) throws Exception {

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

		ProductFeatureCategoryAppl productFeatureCategoryApplToBeUpdated = new ProductFeatureCategoryAppl();

		try {
			productFeatureCategoryApplToBeUpdated = ProductFeatureCategoryApplMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProductFeatureCategoryAppl(productFeatureCategoryApplToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ProductFeatureCategoryAppl with the specific Id
	 * 
	 * @param productFeatureCategoryApplToBeUpdated
	 *            the ProductFeatureCategoryAppl thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateProductFeatureCategoryAppl(@RequestBody ProductFeatureCategoryAppl productFeatureCategoryApplToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		productFeatureCategoryApplToBeUpdated.setnull(null);

		UpdateProductFeatureCategoryAppl command = new UpdateProductFeatureCategoryAppl(productFeatureCategoryApplToBeUpdated);

		try {
			if(((ProductFeatureCategoryApplUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{productFeatureCategoryApplId}")
	public ResponseEntity<Object> findById(@PathVariable String productFeatureCategoryApplId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productFeatureCategoryApplId", productFeatureCategoryApplId);
		try {

			Object foundProductFeatureCategoryAppl = findProductFeatureCategoryApplsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProductFeatureCategoryAppl);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{productFeatureCategoryApplId}")
	public ResponseEntity<Object> deleteProductFeatureCategoryApplByIdUpdated(@PathVariable String productFeatureCategoryApplId) throws Exception {
		DeleteProductFeatureCategoryAppl command = new DeleteProductFeatureCategoryAppl(productFeatureCategoryApplId);

		try {
			if (((ProductFeatureCategoryApplDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductFeatureCategoryAppl could not be deleted");

	}

}
