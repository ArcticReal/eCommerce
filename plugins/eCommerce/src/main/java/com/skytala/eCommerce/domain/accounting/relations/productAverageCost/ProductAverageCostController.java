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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findProductAverageCostsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductAverageCostsBy query = new FindProductAverageCostsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductAverageCost> productAverageCosts =((ProductAverageCostFound) Scheduler.execute(query).data()).getProductAverageCosts();

		if (productAverageCosts.size() == 1) {
			return ResponseEntity.ok().body(productAverageCosts.get(0));
		}

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
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createProductAverageCost(HttpServletRequest request) throws Exception {

		ProductAverageCost productAverageCostToBeAdded = new ProductAverageCost();
		try {
			productAverageCostToBeAdded = ProductAverageCostMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
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
	public ResponseEntity<Object> createProductAverageCost(@RequestBody ProductAverageCost productAverageCostToBeAdded) throws Exception {

		AddProductAverageCost command = new AddProductAverageCost(productAverageCostToBeAdded);
		ProductAverageCost productAverageCost = ((ProductAverageCostAdded) Scheduler.execute(command).data()).getAddedProductAverageCost();
		
		if (productAverageCost != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(productAverageCost);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProductAverageCost could not be created.");
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
	public boolean updateProductAverageCost(HttpServletRequest request) throws Exception {

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

		ProductAverageCost productAverageCostToBeUpdated = new ProductAverageCost();

		try {
			productAverageCostToBeUpdated = ProductAverageCostMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProductAverageCost(productAverageCostToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateProductAverageCost(@RequestBody ProductAverageCost productAverageCostToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		productAverageCostToBeUpdated.setnull(null);

		UpdateProductAverageCost command = new UpdateProductAverageCost(productAverageCostToBeUpdated);

		try {
			if(((ProductAverageCostUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{productAverageCostId}")
	public ResponseEntity<Object> findById(@PathVariable String productAverageCostId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productAverageCostId", productAverageCostId);
		try {

			Object foundProductAverageCost = findProductAverageCostsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProductAverageCost);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{productAverageCostId}")
	public ResponseEntity<Object> deleteProductAverageCostByIdUpdated(@PathVariable String productAverageCostId) throws Exception {
		DeleteProductAverageCost command = new DeleteProductAverageCost(productAverageCostId);

		try {
			if (((ProductAverageCostDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductAverageCost could not be deleted");

	}

}
