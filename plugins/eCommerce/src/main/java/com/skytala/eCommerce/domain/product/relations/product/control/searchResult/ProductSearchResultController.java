package com.skytala.eCommerce.domain.product.relations.product.control.searchResult;

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
import com.skytala.eCommerce.domain.product.relations.product.command.searchResult.AddProductSearchResult;
import com.skytala.eCommerce.domain.product.relations.product.command.searchResult.DeleteProductSearchResult;
import com.skytala.eCommerce.domain.product.relations.product.command.searchResult.UpdateProductSearchResult;
import com.skytala.eCommerce.domain.product.relations.product.event.searchResult.ProductSearchResultAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.searchResult.ProductSearchResultDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.searchResult.ProductSearchResultFound;
import com.skytala.eCommerce.domain.product.relations.product.event.searchResult.ProductSearchResultUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.searchResult.ProductSearchResultMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.searchResult.ProductSearchResult;
import com.skytala.eCommerce.domain.product.relations.product.query.searchResult.FindProductSearchResultsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/product/productSearchResults")
public class ProductSearchResultController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductSearchResultController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductSearchResult
	 * @return a List with the ProductSearchResults
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ProductSearchResult>> findProductSearchResultsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductSearchResultsBy query = new FindProductSearchResultsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductSearchResult> productSearchResults =((ProductSearchResultFound) Scheduler.execute(query).data()).getProductSearchResults();

		return ResponseEntity.ok().body(productSearchResults);

	}

	/**
	 * creates a new ProductSearchResult entry in the ofbiz database
	 * 
	 * @param productSearchResultToBeAdded
	 *            the ProductSearchResult thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductSearchResult> createProductSearchResult(@RequestBody ProductSearchResult productSearchResultToBeAdded) throws Exception {

		AddProductSearchResult command = new AddProductSearchResult(productSearchResultToBeAdded);
		ProductSearchResult productSearchResult = ((ProductSearchResultAdded) Scheduler.execute(command).data()).getAddedProductSearchResult();
		
		if (productSearchResult != null) 
			return successful(productSearchResult);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ProductSearchResult with the specific Id
	 * 
	 * @param productSearchResultToBeUpdated
	 *            the ProductSearchResult thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{productSearchResultId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateProductSearchResult(@RequestBody ProductSearchResult productSearchResultToBeUpdated,
			@PathVariable String productSearchResultId) throws Exception {

		productSearchResultToBeUpdated.setProductSearchResultId(productSearchResultId);

		UpdateProductSearchResult command = new UpdateProductSearchResult(productSearchResultToBeUpdated);

		try {
			if(((ProductSearchResultUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{productSearchResultId}")
	public ResponseEntity<ProductSearchResult> findById(@PathVariable String productSearchResultId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productSearchResultId", productSearchResultId);
		try {

			List<ProductSearchResult> foundProductSearchResult = findProductSearchResultsBy(requestParams).getBody();
			if(foundProductSearchResult.size()==1){				return successful(foundProductSearchResult.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{productSearchResultId}")
	public ResponseEntity<String> deleteProductSearchResultByIdUpdated(@PathVariable String productSearchResultId) throws Exception {
		DeleteProductSearchResult command = new DeleteProductSearchResult(productSearchResultId);

		try {
			if (((ProductSearchResultDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
