package com.skytala.eCommerce.domain.product.relations.product.control.featureGroupAppl;

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
import com.skytala.eCommerce.domain.product.relations.product.command.featureGroupAppl.AddProductFeatureGroupAppl;
import com.skytala.eCommerce.domain.product.relations.product.command.featureGroupAppl.DeleteProductFeatureGroupAppl;
import com.skytala.eCommerce.domain.product.relations.product.command.featureGroupAppl.UpdateProductFeatureGroupAppl;
import com.skytala.eCommerce.domain.product.relations.product.event.featureGroupAppl.ProductFeatureGroupApplAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.featureGroupAppl.ProductFeatureGroupApplDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.featureGroupAppl.ProductFeatureGroupApplFound;
import com.skytala.eCommerce.domain.product.relations.product.event.featureGroupAppl.ProductFeatureGroupApplUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.featureGroupAppl.ProductFeatureGroupApplMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.featureGroupAppl.ProductFeatureGroupAppl;
import com.skytala.eCommerce.domain.product.relations.product.query.featureGroupAppl.FindProductFeatureGroupApplsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/product/product/productFeatureGroupAppls")
public class ProductFeatureGroupApplController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductFeatureGroupApplController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductFeatureGroupAppl
	 * @return a List with the ProductFeatureGroupAppls
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findProductFeatureGroupApplsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductFeatureGroupApplsBy query = new FindProductFeatureGroupApplsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductFeatureGroupAppl> productFeatureGroupAppls =((ProductFeatureGroupApplFound) Scheduler.execute(query).data()).getProductFeatureGroupAppls();

		if (productFeatureGroupAppls.size() == 1) {
			return ResponseEntity.ok().body(productFeatureGroupAppls.get(0));
		}

		return ResponseEntity.ok().body(productFeatureGroupAppls);

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
	public ResponseEntity<Object> createProductFeatureGroupAppl(HttpServletRequest request) throws Exception {

		ProductFeatureGroupAppl productFeatureGroupApplToBeAdded = new ProductFeatureGroupAppl();
		try {
			productFeatureGroupApplToBeAdded = ProductFeatureGroupApplMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createProductFeatureGroupAppl(productFeatureGroupApplToBeAdded);

	}

	/**
	 * creates a new ProductFeatureGroupAppl entry in the ofbiz database
	 * 
	 * @param productFeatureGroupApplToBeAdded
	 *            the ProductFeatureGroupAppl thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createProductFeatureGroupAppl(@RequestBody ProductFeatureGroupAppl productFeatureGroupApplToBeAdded) throws Exception {

		AddProductFeatureGroupAppl command = new AddProductFeatureGroupAppl(productFeatureGroupApplToBeAdded);
		ProductFeatureGroupAppl productFeatureGroupAppl = ((ProductFeatureGroupApplAdded) Scheduler.execute(command).data()).getAddedProductFeatureGroupAppl();
		
		if (productFeatureGroupAppl != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(productFeatureGroupAppl);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProductFeatureGroupAppl could not be created.");
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
	public boolean updateProductFeatureGroupAppl(HttpServletRequest request) throws Exception {

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

		ProductFeatureGroupAppl productFeatureGroupApplToBeUpdated = new ProductFeatureGroupAppl();

		try {
			productFeatureGroupApplToBeUpdated = ProductFeatureGroupApplMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProductFeatureGroupAppl(productFeatureGroupApplToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ProductFeatureGroupAppl with the specific Id
	 * 
	 * @param productFeatureGroupApplToBeUpdated
	 *            the ProductFeatureGroupAppl thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateProductFeatureGroupAppl(@RequestBody ProductFeatureGroupAppl productFeatureGroupApplToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		productFeatureGroupApplToBeUpdated.setnull(null);

		UpdateProductFeatureGroupAppl command = new UpdateProductFeatureGroupAppl(productFeatureGroupApplToBeUpdated);

		try {
			if(((ProductFeatureGroupApplUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{productFeatureGroupApplId}")
	public ResponseEntity<Object> findById(@PathVariable String productFeatureGroupApplId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productFeatureGroupApplId", productFeatureGroupApplId);
		try {

			Object foundProductFeatureGroupAppl = findProductFeatureGroupApplsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProductFeatureGroupAppl);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{productFeatureGroupApplId}")
	public ResponseEntity<Object> deleteProductFeatureGroupApplByIdUpdated(@PathVariable String productFeatureGroupApplId) throws Exception {
		DeleteProductFeatureGroupAppl command = new DeleteProductFeatureGroupAppl(productFeatureGroupApplId);

		try {
			if (((ProductFeatureGroupApplDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductFeatureGroupAppl could not be deleted");

	}

}
