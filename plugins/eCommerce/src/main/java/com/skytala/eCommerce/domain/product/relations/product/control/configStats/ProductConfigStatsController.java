package com.skytala.eCommerce.domain.product.relations.product.control.configStats;

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
import com.skytala.eCommerce.domain.product.relations.product.command.configStats.AddProductConfigStats;
import com.skytala.eCommerce.domain.product.relations.product.command.configStats.DeleteProductConfigStats;
import com.skytala.eCommerce.domain.product.relations.product.command.configStats.UpdateProductConfigStats;
import com.skytala.eCommerce.domain.product.relations.product.event.configStats.ProductConfigStatsAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.configStats.ProductConfigStatsDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.configStats.ProductConfigStatsFound;
import com.skytala.eCommerce.domain.product.relations.product.event.configStats.ProductConfigStatsUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.configStats.ProductConfigStatsMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.configStats.ProductConfigStats;
import com.skytala.eCommerce.domain.product.relations.product.query.configStats.FindProductConfigStatssBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/product/productConfigStatss")
public class ProductConfigStatsController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductConfigStatsController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductConfigStats
	 * @return a List with the ProductConfigStatss
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ProductConfigStats>> findProductConfigStatssBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductConfigStatssBy query = new FindProductConfigStatssBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductConfigStats> productConfigStatss =((ProductConfigStatsFound) Scheduler.execute(query).data()).getProductConfigStatss();

		return ResponseEntity.ok().body(productConfigStatss);

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
	public ResponseEntity<ProductConfigStats> createProductConfigStats(HttpServletRequest request) throws Exception {

		ProductConfigStats productConfigStatsToBeAdded = new ProductConfigStats();
		try {
			productConfigStatsToBeAdded = ProductConfigStatsMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createProductConfigStats(productConfigStatsToBeAdded);

	}

	/**
	 * creates a new ProductConfigStats entry in the ofbiz database
	 * 
	 * @param productConfigStatsToBeAdded
	 *            the ProductConfigStats thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductConfigStats> createProductConfigStats(@RequestBody ProductConfigStats productConfigStatsToBeAdded) throws Exception {

		AddProductConfigStats command = new AddProductConfigStats(productConfigStatsToBeAdded);
		ProductConfigStats productConfigStats = ((ProductConfigStatsAdded) Scheduler.execute(command).data()).getAddedProductConfigStats();
		
		if (productConfigStats != null) 
			return successful(productConfigStats);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ProductConfigStats with the specific Id
	 * 
	 * @param productConfigStatsToBeUpdated
	 *            the ProductConfigStats thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{configId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateProductConfigStats(@RequestBody ProductConfigStats productConfigStatsToBeUpdated,
			@PathVariable String configId) throws Exception {

		productConfigStatsToBeUpdated.setConfigId(configId);

		UpdateProductConfigStats command = new UpdateProductConfigStats(productConfigStatsToBeUpdated);

		try {
			if(((ProductConfigStatsUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{productConfigStatsId}")
	public ResponseEntity<ProductConfigStats> findById(@PathVariable String productConfigStatsId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productConfigStatsId", productConfigStatsId);
		try {

			List<ProductConfigStats> foundProductConfigStats = findProductConfigStatssBy(requestParams).getBody();
			if(foundProductConfigStats.size()==1){				return successful(foundProductConfigStats.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{productConfigStatsId}")
	public ResponseEntity<String> deleteProductConfigStatsByIdUpdated(@PathVariable String productConfigStatsId) throws Exception {
		DeleteProductConfigStats command = new DeleteProductConfigStats(productConfigStatsId);

		try {
			if (((ProductConfigStatsDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
