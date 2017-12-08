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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@RequestMapping("/productConfigStatss")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findProductConfigStatssBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductConfigStatssBy query = new FindProductConfigStatssBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductConfigStats> productConfigStatss =((ProductConfigStatsFound) Scheduler.execute(query).data()).getProductConfigStatss();

		if (productConfigStatss.size() == 1) {
			return ResponseEntity.ok().body(productConfigStatss.get(0));
		}

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
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createProductConfigStats(HttpServletRequest request) throws Exception {

		ProductConfigStats productConfigStatsToBeAdded = new ProductConfigStats();
		try {
			productConfigStatsToBeAdded = ProductConfigStatsMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
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
	public ResponseEntity<Object> createProductConfigStats(@RequestBody ProductConfigStats productConfigStatsToBeAdded) throws Exception {

		AddProductConfigStats command = new AddProductConfigStats(productConfigStatsToBeAdded);
		ProductConfigStats productConfigStats = ((ProductConfigStatsAdded) Scheduler.execute(command).data()).getAddedProductConfigStats();
		
		if (productConfigStats != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(productConfigStats);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProductConfigStats could not be created.");
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
	public boolean updateProductConfigStats(HttpServletRequest request) throws Exception {

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

		ProductConfigStats productConfigStatsToBeUpdated = new ProductConfigStats();

		try {
			productConfigStatsToBeUpdated = ProductConfigStatsMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProductConfigStats(productConfigStatsToBeUpdated, productConfigStatsToBeUpdated.getConfigId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateProductConfigStats(@RequestBody ProductConfigStats productConfigStatsToBeUpdated,
			@PathVariable String configId) throws Exception {

		productConfigStatsToBeUpdated.setConfigId(configId);

		UpdateProductConfigStats command = new UpdateProductConfigStats(productConfigStatsToBeUpdated);

		try {
			if(((ProductConfigStatsUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{productConfigStatsId}")
	public ResponseEntity<Object> findById(@PathVariable String productConfigStatsId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productConfigStatsId", productConfigStatsId);
		try {

			Object foundProductConfigStats = findProductConfigStatssBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProductConfigStats);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{productConfigStatsId}")
	public ResponseEntity<Object> deleteProductConfigStatsByIdUpdated(@PathVariable String productConfigStatsId) throws Exception {
		DeleteProductConfigStats command = new DeleteProductConfigStats(productConfigStatsId);

		try {
			if (((ProductConfigStatsDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductConfigStats could not be deleted");

	}

}
