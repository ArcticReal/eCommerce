package com.skytala.eCommerce.domain.product.relations.facility.control.product;

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
import com.skytala.eCommerce.domain.product.relations.facility.command.product.AddProductFacility;
import com.skytala.eCommerce.domain.product.relations.facility.command.product.DeleteProductFacility;
import com.skytala.eCommerce.domain.product.relations.facility.command.product.UpdateProductFacility;
import com.skytala.eCommerce.domain.product.relations.facility.event.product.ProductFacilityAdded;
import com.skytala.eCommerce.domain.product.relations.facility.event.product.ProductFacilityDeleted;
import com.skytala.eCommerce.domain.product.relations.facility.event.product.ProductFacilityFound;
import com.skytala.eCommerce.domain.product.relations.facility.event.product.ProductFacilityUpdated;
import com.skytala.eCommerce.domain.product.relations.facility.mapper.product.ProductFacilityMapper;
import com.skytala.eCommerce.domain.product.relations.facility.model.product.ProductFacility;
import com.skytala.eCommerce.domain.product.relations.facility.query.product.FindProductFacilitysBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/facility/productFacilitys")
public class ProductFacilityController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ProductFacilityController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ProductFacility
	 * @return a List with the ProductFacilitys
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ProductFacility>> findProductFacilitysBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindProductFacilitysBy query = new FindProductFacilitysBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ProductFacility> productFacilitys =((ProductFacilityFound) Scheduler.execute(query).data()).getProductFacilitys();

		return ResponseEntity.ok().body(productFacilitys);

	}

	/**
	 * creates a new ProductFacility entry in the ofbiz database
	 * 
	 * @param productFacilityToBeAdded
	 *            the ProductFacility thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductFacility> createProductFacility(@RequestBody ProductFacility productFacilityToBeAdded) throws Exception {

		AddProductFacility command = new AddProductFacility(productFacilityToBeAdded);
		ProductFacility productFacility = ((ProductFacilityAdded) Scheduler.execute(command).data()).getAddedProductFacility();
		
		if (productFacility != null) 
			return successful(productFacility);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ProductFacility with the specific Id
	 * 
	 * @param productFacilityToBeUpdated
	 *            the ProductFacility thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateProductFacility(@RequestBody ProductFacility productFacilityToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		productFacilityToBeUpdated.setnull(null);

		UpdateProductFacility command = new UpdateProductFacility(productFacilityToBeUpdated);

		try {
			if(((ProductFacilityUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{productFacilityId}")
	public ResponseEntity<ProductFacility> findById(@PathVariable String productFacilityId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("productFacilityId", productFacilityId);
		try {

			List<ProductFacility> foundProductFacility = findProductFacilitysBy(requestParams).getBody();
			if(foundProductFacility.size()==1){				return successful(foundProductFacility.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{productFacilityId}")
	public ResponseEntity<String> deleteProductFacilityByIdUpdated(@PathVariable String productFacilityId) throws Exception {
		DeleteProductFacility command = new DeleteProductFacility(productFacilityId);

		try {
			if (((ProductFacilityDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
