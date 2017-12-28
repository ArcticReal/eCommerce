package com.skytala.eCommerce.domain.product.relations.product.control.supplierFeature;

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
import com.skytala.eCommerce.domain.product.relations.product.command.supplierFeature.AddSupplierProductFeature;
import com.skytala.eCommerce.domain.product.relations.product.command.supplierFeature.DeleteSupplierProductFeature;
import com.skytala.eCommerce.domain.product.relations.product.command.supplierFeature.UpdateSupplierProductFeature;
import com.skytala.eCommerce.domain.product.relations.product.event.supplierFeature.SupplierProductFeatureAdded;
import com.skytala.eCommerce.domain.product.relations.product.event.supplierFeature.SupplierProductFeatureDeleted;
import com.skytala.eCommerce.domain.product.relations.product.event.supplierFeature.SupplierProductFeatureFound;
import com.skytala.eCommerce.domain.product.relations.product.event.supplierFeature.SupplierProductFeatureUpdated;
import com.skytala.eCommerce.domain.product.relations.product.mapper.supplierFeature.SupplierProductFeatureMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.supplierFeature.SupplierProductFeature;
import com.skytala.eCommerce.domain.product.relations.product.query.supplierFeature.FindSupplierProductFeaturesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/product/supplierProductFeatures")
public class SupplierProductFeatureController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public SupplierProductFeatureController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a SupplierProductFeature
	 * @return a List with the SupplierProductFeatures
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<SupplierProductFeature>> findSupplierProductFeaturesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindSupplierProductFeaturesBy query = new FindSupplierProductFeaturesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<SupplierProductFeature> supplierProductFeatures =((SupplierProductFeatureFound) Scheduler.execute(query).data()).getSupplierProductFeatures();

		return ResponseEntity.ok().body(supplierProductFeatures);

	}

	/**
	 * creates a new SupplierProductFeature entry in the ofbiz database
	 * 
	 * @param supplierProductFeatureToBeAdded
	 *            the SupplierProductFeature thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<SupplierProductFeature> createSupplierProductFeature(@RequestBody SupplierProductFeature supplierProductFeatureToBeAdded) throws Exception {

		AddSupplierProductFeature command = new AddSupplierProductFeature(supplierProductFeatureToBeAdded);
		SupplierProductFeature supplierProductFeature = ((SupplierProductFeatureAdded) Scheduler.execute(command).data()).getAddedSupplierProductFeature();
		
		if (supplierProductFeature != null) 
			return successful(supplierProductFeature);
		else 
			return conflict(null);
	}

	/**
	 * Updates the SupplierProductFeature with the specific Id
	 * 
	 * @param supplierProductFeatureToBeUpdated
	 *            the SupplierProductFeature thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateSupplierProductFeature(@RequestBody SupplierProductFeature supplierProductFeatureToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		supplierProductFeatureToBeUpdated.setnull(null);

		UpdateSupplierProductFeature command = new UpdateSupplierProductFeature(supplierProductFeatureToBeUpdated);

		try {
			if(((SupplierProductFeatureUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{supplierProductFeatureId}")
	public ResponseEntity<SupplierProductFeature> findById(@PathVariable String supplierProductFeatureId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("supplierProductFeatureId", supplierProductFeatureId);
		try {

			List<SupplierProductFeature> foundSupplierProductFeature = findSupplierProductFeaturesBy(requestParams).getBody();
			if(foundSupplierProductFeature.size()==1){				return successful(foundSupplierProductFeature.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{supplierProductFeatureId}")
	public ResponseEntity<String> deleteSupplierProductFeatureByIdUpdated(@PathVariable String supplierProductFeatureId) throws Exception {
		DeleteSupplierProductFeature command = new DeleteSupplierProductFeature(supplierProductFeatureId);

		try {
			if (((SupplierProductFeatureDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
