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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	public ResponseEntity<List<ProductFeatureGroupAppl>> findProductFeatureGroupApplsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductFeatureGroupApplsBy query = new FindProductFeatureGroupApplsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductFeatureGroupAppl> productFeatureGroupAppls =((ProductFeatureGroupApplFound) Scheduler.execute(query).data()).getProductFeatureGroupAppls();

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
	public ResponseEntity<ProductFeatureGroupAppl> createProductFeatureGroupAppl(HttpServletRequest request) throws Exception {

		ProductFeatureGroupAppl productFeatureGroupApplToBeAdded = new ProductFeatureGroupAppl();
		try {
			productFeatureGroupApplToBeAdded = ProductFeatureGroupApplMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
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
	public ResponseEntity<ProductFeatureGroupAppl> createProductFeatureGroupAppl(@RequestBody ProductFeatureGroupAppl productFeatureGroupApplToBeAdded) throws Exception {

		AddProductFeatureGroupAppl command = new AddProductFeatureGroupAppl(productFeatureGroupApplToBeAdded);
		ProductFeatureGroupAppl productFeatureGroupAppl = ((ProductFeatureGroupApplAdded) Scheduler.execute(command).data()).getAddedProductFeatureGroupAppl();
		
		if (productFeatureGroupAppl != null) 
			return successful(productFeatureGroupAppl);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateProductFeatureGroupAppl(@RequestBody ProductFeatureGroupAppl productFeatureGroupApplToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		productFeatureGroupApplToBeUpdated.setnull(null);

		UpdateProductFeatureGroupAppl command = new UpdateProductFeatureGroupAppl(productFeatureGroupApplToBeUpdated);

		try {
			if(((ProductFeatureGroupApplUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{productFeatureGroupApplId}")
	public ResponseEntity<ProductFeatureGroupAppl> findById(@PathVariable String productFeatureGroupApplId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productFeatureGroupApplId", productFeatureGroupApplId);
		try {

			List<ProductFeatureGroupAppl> foundProductFeatureGroupAppl = findProductFeatureGroupApplsBy(requestParams).getBody();
			if(foundProductFeatureGroupAppl.size()==1){				return successful(foundProductFeatureGroupAppl.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{productFeatureGroupApplId}")
	public ResponseEntity<String> deleteProductFeatureGroupApplByIdUpdated(@PathVariable String productFeatureGroupApplId) throws Exception {
		DeleteProductFeatureGroupAppl command = new DeleteProductFeatureGroupAppl(productFeatureGroupApplId);

		try {
			if (((ProductFeatureGroupApplDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
