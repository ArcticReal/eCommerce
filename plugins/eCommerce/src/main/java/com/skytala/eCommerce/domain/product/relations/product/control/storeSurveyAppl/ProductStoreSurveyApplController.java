package com.skytala.eCommerce.domain.product.relations.product.control.storeSurveyAppl;

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
import com.skytala.eCommerce.domain.product.relations.product.command.storeSurveyAppl.AddProductStoreSurveyAppl;
import com.skytala.eCommerce.domain.product.relations.product.command.storeSurveyAppl.DeleteProductStoreSurveyAppl;
import com.skytala.eCommerce.domain.product.relations.product.command.storeSurveyAppl.UpdateProductStoreSurveyAppl;
import com.skytala.eCommerce.domain.product.relations.product.event.storeSurveyAppl.ProductStoreSurveyApplAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.storeSurveyAppl.ProductStoreSurveyApplDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.storeSurveyAppl.ProductStoreSurveyApplFound;
import com.skytala.eCommerce.domain.product.relations.product.event.storeSurveyAppl.ProductStoreSurveyApplUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.storeSurveyAppl.ProductStoreSurveyApplMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.storeSurveyAppl.ProductStoreSurveyAppl;
import com.skytala.eCommerce.domain.product.relations.product.query.storeSurveyAppl.FindProductStoreSurveyApplsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/product/productStoreSurveyAppls")
public class ProductStoreSurveyApplController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductStoreSurveyApplController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductStoreSurveyAppl
	 * @return a List with the ProductStoreSurveyAppls
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ProductStoreSurveyAppl>> findProductStoreSurveyApplsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductStoreSurveyApplsBy query = new FindProductStoreSurveyApplsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductStoreSurveyAppl> productStoreSurveyAppls =((ProductStoreSurveyApplFound) Scheduler.execute(query).data()).getProductStoreSurveyAppls();

		return ResponseEntity.ok().body(productStoreSurveyAppls);

	}

	/**
	 * creates a new ProductStoreSurveyAppl entry in the ofbiz database
	 * 
	 * @param productStoreSurveyApplToBeAdded
	 *            the ProductStoreSurveyAppl thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductStoreSurveyAppl> createProductStoreSurveyAppl(@RequestBody ProductStoreSurveyAppl productStoreSurveyApplToBeAdded) throws Exception {

		AddProductStoreSurveyAppl command = new AddProductStoreSurveyAppl(productStoreSurveyApplToBeAdded);
		ProductStoreSurveyAppl productStoreSurveyAppl = ((ProductStoreSurveyApplAdded) Scheduler.execute(command).data()).getAddedProductStoreSurveyAppl();
		
		if (productStoreSurveyAppl != null) 
			return successful(productStoreSurveyAppl);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ProductStoreSurveyAppl with the specific Id
	 * 
	 * @param productStoreSurveyApplToBeUpdated
	 *            the ProductStoreSurveyAppl thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{productStoreSurveyId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateProductStoreSurveyAppl(@RequestBody ProductStoreSurveyAppl productStoreSurveyApplToBeUpdated,
			@PathVariable String productStoreSurveyId) throws Exception {

		productStoreSurveyApplToBeUpdated.setProductStoreSurveyId(productStoreSurveyId);

		UpdateProductStoreSurveyAppl command = new UpdateProductStoreSurveyAppl(productStoreSurveyApplToBeUpdated);

		try {
			if(((ProductStoreSurveyApplUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{productStoreSurveyApplId}")
	public ResponseEntity<ProductStoreSurveyAppl> findById(@PathVariable String productStoreSurveyApplId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productStoreSurveyApplId", productStoreSurveyApplId);
		try {

			List<ProductStoreSurveyAppl> foundProductStoreSurveyAppl = findProductStoreSurveyApplsBy(requestParams).getBody();
			if(foundProductStoreSurveyAppl.size()==1){				return successful(foundProductStoreSurveyAppl.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{productStoreSurveyApplId}")
	public ResponseEntity<String> deleteProductStoreSurveyApplByIdUpdated(@PathVariable String productStoreSurveyApplId) throws Exception {
		DeleteProductStoreSurveyAppl command = new DeleteProductStoreSurveyAppl(productStoreSurveyApplId);

		try {
			if (((ProductStoreSurveyApplDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
