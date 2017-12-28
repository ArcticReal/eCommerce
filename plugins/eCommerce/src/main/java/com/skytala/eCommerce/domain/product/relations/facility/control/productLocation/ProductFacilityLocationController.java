package com.skytala.eCommerce.domain.product.relations.facility.control.productLocation;

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
import com.skytala.eCommerce.domain.product.relations.facility.command.productLocation.AddProductFacilityLocation;
import com.skytala.eCommerce.domain.product.relations.facility.command.productLocation.DeleteProductFacilityLocation;
import com.skytala.eCommerce.domain.product.relations.facility.command.productLocation.UpdateProductFacilityLocation;
import com.skytala.eCommerce.domain.product.relations.facility.event.productLocation.ProductFacilityLocationAdded;
import com.skytala.eCommerce.domain.product.relations.facility.event.productLocation.ProductFacilityLocationDeleted;
import com.skytala.eCommerce.domain.product.relations.facility.event.productLocation.ProductFacilityLocationFound;
import com.skytala.eCommerce.domain.product.relations.facility.event.productLocation.ProductFacilityLocationUpdated;
import com.skytala.eCommerce.domain.product.relations.facility.mapper.productLocation.ProductFacilityLocationMapper;
import com.skytala.eCommerce.domain.product.relations.facility.model.productLocation.ProductFacilityLocation;
import com.skytala.eCommerce.domain.product.relations.facility.query.productLocation.FindProductFacilityLocationsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/facility/productFacilityLocations")
public class ProductFacilityLocationController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductFacilityLocationController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductFacilityLocation
	 * @return a List with the ProductFacilityLocations
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ProductFacilityLocation>> findProductFacilityLocationsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductFacilityLocationsBy query = new FindProductFacilityLocationsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductFacilityLocation> productFacilityLocations =((ProductFacilityLocationFound) Scheduler.execute(query).data()).getProductFacilityLocations();

		return ResponseEntity.ok().body(productFacilityLocations);

	}

	/**
	 * creates a new ProductFacilityLocation entry in the ofbiz database
	 * 
	 * @param productFacilityLocationToBeAdded
	 *            the ProductFacilityLocation thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductFacilityLocation> createProductFacilityLocation(@RequestBody ProductFacilityLocation productFacilityLocationToBeAdded) throws Exception {

		AddProductFacilityLocation command = new AddProductFacilityLocation(productFacilityLocationToBeAdded);
		ProductFacilityLocation productFacilityLocation = ((ProductFacilityLocationAdded) Scheduler.execute(command).data()).getAddedProductFacilityLocation();
		
		if (productFacilityLocation != null) 
			return successful(productFacilityLocation);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ProductFacilityLocation with the specific Id
	 * 
	 * @param productFacilityLocationToBeUpdated
	 *            the ProductFacilityLocation thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{locationSeqId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateProductFacilityLocation(@RequestBody ProductFacilityLocation productFacilityLocationToBeUpdated,
			@PathVariable String locationSeqId) throws Exception {

		productFacilityLocationToBeUpdated.setLocationSeqId(locationSeqId);

		UpdateProductFacilityLocation command = new UpdateProductFacilityLocation(productFacilityLocationToBeUpdated);

		try {
			if(((ProductFacilityLocationUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{productFacilityLocationId}")
	public ResponseEntity<ProductFacilityLocation> findById(@PathVariable String productFacilityLocationId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productFacilityLocationId", productFacilityLocationId);
		try {

			List<ProductFacilityLocation> foundProductFacilityLocation = findProductFacilityLocationsBy(requestParams).getBody();
			if(foundProductFacilityLocation.size()==1){				return successful(foundProductFacilityLocation.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{productFacilityLocationId}")
	public ResponseEntity<String> deleteProductFacilityLocationByIdUpdated(@PathVariable String productFacilityLocationId) throws Exception {
		DeleteProductFacilityLocation command = new DeleteProductFacilityLocation(productFacilityLocationId);

		try {
			if (((ProductFacilityLocationDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
