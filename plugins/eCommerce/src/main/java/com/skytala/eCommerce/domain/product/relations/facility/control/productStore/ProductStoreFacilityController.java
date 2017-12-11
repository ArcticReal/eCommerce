package com.skytala.eCommerce.domain.product.relations.facility.control.productStore;

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
import com.skytala.eCommerce.domain.product.relations.facility.command.productStore.AddProductStoreFacility;
import com.skytala.eCommerce.domain.product.relations.facility.command.productStore.DeleteProductStoreFacility;
import com.skytala.eCommerce.domain.product.relations.facility.command.productStore.UpdateProductStoreFacility;
import com.skytala.eCommerce.domain.product.relations.facility.event.productStore.ProductStoreFacilityAdded;
import com.skytala.eCommerce.domain.product.relations.facility.event.productStore.ProductStoreFacilityDeleted;
import com.skytala.eCommerce.domain.product.relations.facility.event.productStore.ProductStoreFacilityFound;
import com.skytala.eCommerce.domain.product.relations.facility.event.productStore.ProductStoreFacilityUpdated;
import com.skytala.eCommerce.domain.product.relations.facility.mapper.productStore.ProductStoreFacilityMapper;
import com.skytala.eCommerce.domain.product.relations.facility.model.productStore.ProductStoreFacility;
import com.skytala.eCommerce.domain.product.relations.facility.query.productStore.FindProductStoreFacilitysBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/product/facility/productStoreFacilitys")
public class ProductStoreFacilityController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductStoreFacilityController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductStoreFacility
	 * @return a List with the ProductStoreFacilitys
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findProductStoreFacilitysBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductStoreFacilitysBy query = new FindProductStoreFacilitysBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductStoreFacility> productStoreFacilitys =((ProductStoreFacilityFound) Scheduler.execute(query).data()).getProductStoreFacilitys();

		if (productStoreFacilitys.size() == 1) {
			return ResponseEntity.ok().body(productStoreFacilitys.get(0));
		}

		return ResponseEntity.ok().body(productStoreFacilitys);

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
	public ResponseEntity<Object> createProductStoreFacility(HttpServletRequest request) throws Exception {

		ProductStoreFacility productStoreFacilityToBeAdded = new ProductStoreFacility();
		try {
			productStoreFacilityToBeAdded = ProductStoreFacilityMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createProductStoreFacility(productStoreFacilityToBeAdded);

	}

	/**
	 * creates a new ProductStoreFacility entry in the ofbiz database
	 * 
	 * @param productStoreFacilityToBeAdded
	 *            the ProductStoreFacility thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createProductStoreFacility(@RequestBody ProductStoreFacility productStoreFacilityToBeAdded) throws Exception {

		AddProductStoreFacility command = new AddProductStoreFacility(productStoreFacilityToBeAdded);
		ProductStoreFacility productStoreFacility = ((ProductStoreFacilityAdded) Scheduler.execute(command).data()).getAddedProductStoreFacility();
		
		if (productStoreFacility != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(productStoreFacility);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ProductStoreFacility could not be created.");
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
	public boolean updateProductStoreFacility(HttpServletRequest request) throws Exception {

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

		ProductStoreFacility productStoreFacilityToBeUpdated = new ProductStoreFacility();

		try {
			productStoreFacilityToBeUpdated = ProductStoreFacilityMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateProductStoreFacility(productStoreFacilityToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ProductStoreFacility with the specific Id
	 * 
	 * @param productStoreFacilityToBeUpdated
	 *            the ProductStoreFacility thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateProductStoreFacility(@RequestBody ProductStoreFacility productStoreFacilityToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		productStoreFacilityToBeUpdated.setnull(null);

		UpdateProductStoreFacility command = new UpdateProductStoreFacility(productStoreFacilityToBeUpdated);

		try {
			if(((ProductStoreFacilityUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{productStoreFacilityId}")
	public ResponseEntity<Object> findById(@PathVariable String productStoreFacilityId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productStoreFacilityId", productStoreFacilityId);
		try {

			Object foundProductStoreFacility = findProductStoreFacilitysBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundProductStoreFacility);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{productStoreFacilityId}")
	public ResponseEntity<Object> deleteProductStoreFacilityByIdUpdated(@PathVariable String productStoreFacilityId) throws Exception {
		DeleteProductStoreFacility command = new DeleteProductStoreFacility(productStoreFacilityId);

		try {
			if (((ProductStoreFacilityDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductStoreFacility could not be deleted");

	}

}
