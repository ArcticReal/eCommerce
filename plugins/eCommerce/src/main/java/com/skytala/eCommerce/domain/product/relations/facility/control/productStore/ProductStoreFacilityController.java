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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	public ResponseEntity<List<ProductStoreFacility>> findProductStoreFacilitysBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductStoreFacilitysBy query = new FindProductStoreFacilitysBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductStoreFacility> productStoreFacilitys =((ProductStoreFacilityFound) Scheduler.execute(query).data()).getProductStoreFacilitys();

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
	public ResponseEntity<ProductStoreFacility> createProductStoreFacility(HttpServletRequest request) throws Exception {

		ProductStoreFacility productStoreFacilityToBeAdded = new ProductStoreFacility();
		try {
			productStoreFacilityToBeAdded = ProductStoreFacilityMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
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
	public ResponseEntity<ProductStoreFacility> createProductStoreFacility(@RequestBody ProductStoreFacility productStoreFacilityToBeAdded) throws Exception {

		AddProductStoreFacility command = new AddProductStoreFacility(productStoreFacilityToBeAdded);
		ProductStoreFacility productStoreFacility = ((ProductStoreFacilityAdded) Scheduler.execute(command).data()).getAddedProductStoreFacility();
		
		if (productStoreFacility != null) 
			return successful(productStoreFacility);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateProductStoreFacility(@RequestBody ProductStoreFacility productStoreFacilityToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		productStoreFacilityToBeUpdated.setnull(null);

		UpdateProductStoreFacility command = new UpdateProductStoreFacility(productStoreFacilityToBeUpdated);

		try {
			if(((ProductStoreFacilityUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{productStoreFacilityId}")
	public ResponseEntity<ProductStoreFacility> findById(@PathVariable String productStoreFacilityId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productStoreFacilityId", productStoreFacilityId);
		try {

			List<ProductStoreFacility> foundProductStoreFacility = findProductStoreFacilitysBy(requestParams).getBody();
			if(foundProductStoreFacility.size()==1){				return successful(foundProductStoreFacility.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{productStoreFacilityId}")
	public ResponseEntity<String> deleteProductStoreFacilityByIdUpdated(@PathVariable String productStoreFacilityId) throws Exception {
		DeleteProductStoreFacility command = new DeleteProductStoreFacility(productStoreFacilityId);

		try {
			if (((ProductStoreFacilityDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
