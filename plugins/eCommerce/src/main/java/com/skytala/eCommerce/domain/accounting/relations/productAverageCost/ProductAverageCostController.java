package com.skytala.eCommerce.domain.accounting.relations.productAverageCost;

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
import com.skytala.eCommerce.domain.accounting.relations.productAverageCost.command.AddProductAverageCost;
import com.skytala.eCommerce.domain.accounting.relations.productAverageCost.command.DeleteProductAverageCost;
import com.skytala.eCommerce.domain.accounting.relations.productAverageCost.command.UpdateProductAverageCost;
import com.skytala.eCommerce.domain.accounting.relations.productAverageCost.event.ProductAverageCostAdded;
import com.skytala.eCommerce.domain.accounting.relations.productAverageCost.event.ProductAverageCostDeleted;
import com.skytala.eCommerce.domain.accounting.relations.productAverageCost.event.ProductAverageCostFound;
import com.skytala.eCommerce.domain.accounting.relations.productAverageCost.event.ProductAverageCostUpdated;
import com.skytala.eCommerce.domain.accounting.relations.productAverageCost.mapper.ProductAverageCostMapper;
import com.skytala.eCommerce.domain.accounting.relations.productAverageCost.model.ProductAverageCost;
import com.skytala.eCommerce.domain.accounting.relations.productAverageCost.query.FindProductAverageCostsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/productAverageCosts")
public class ProductAverageCostController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductAverageCostController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductAverageCost
	 * @return a List with the ProductAverageCosts
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ProductAverageCost>> findProductAverageCostsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductAverageCostsBy query = new FindProductAverageCostsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductAverageCost> productAverageCosts =((ProductAverageCostFound) Scheduler.execute(query).data()).getProductAverageCosts();

		return ResponseEntity.ok().body(productAverageCosts);

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
	public ResponseEntity<ProductAverageCost> createProductAverageCost(HttpServletRequest request) throws Exception {

		ProductAverageCost productAverageCostToBeAdded = new ProductAverageCost();
		try {
			productAverageCostToBeAdded = ProductAverageCostMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createProductAverageCost(productAverageCostToBeAdded);

	}

	/**
	 * creates a new ProductAverageCost entry in the ofbiz database
	 * 
	 * @param productAverageCostToBeAdded
	 *            the ProductAverageCost thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductAverageCost> createProductAverageCost(@RequestBody ProductAverageCost productAverageCostToBeAdded) throws Exception {

		AddProductAverageCost command = new AddProductAverageCost(productAverageCostToBeAdded);
		ProductAverageCost productAverageCost = ((ProductAverageCostAdded) Scheduler.execute(command).data()).getAddedProductAverageCost();
		
		if (productAverageCost != null) 
			return successful(productAverageCost);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ProductAverageCost with the specific Id
	 * 
	 * @param productAverageCostToBeUpdated
	 *            the ProductAverageCost thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateProductAverageCost(@RequestBody ProductAverageCost productAverageCostToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		productAverageCostToBeUpdated.setnull(null);

		UpdateProductAverageCost command = new UpdateProductAverageCost(productAverageCostToBeUpdated);

		try {
			if(((ProductAverageCostUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{productAverageCostId}")
	public ResponseEntity<ProductAverageCost> findById(@PathVariable String productAverageCostId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productAverageCostId", productAverageCostId);
		try {

			List<ProductAverageCost> foundProductAverageCost = findProductAverageCostsBy(requestParams).getBody();
			if(foundProductAverageCost.size()==1){				return successful(foundProductAverageCost.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{productAverageCostId}")
	public ResponseEntity<String> deleteProductAverageCostByIdUpdated(@PathVariable String productAverageCostId) throws Exception {
		DeleteProductAverageCost command = new DeleteProductAverageCost(productAverageCostId);

		try {
			if (((ProductAverageCostDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
