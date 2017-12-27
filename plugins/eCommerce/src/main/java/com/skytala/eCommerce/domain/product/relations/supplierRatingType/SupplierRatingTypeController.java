package com.skytala.eCommerce.domain.product.relations.supplierRatingType;

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
import com.skytala.eCommerce.domain.product.relations.supplierRatingType.command.AddSupplierRatingType;
import com.skytala.eCommerce.domain.product.relations.supplierRatingType.command.DeleteSupplierRatingType;
import com.skytala.eCommerce.domain.product.relations.supplierRatingType.command.UpdateSupplierRatingType;
import com.skytala.eCommerce.domain.product.relations.supplierRatingType.event.SupplierRatingTypeAdded;
import com.skytala.eCommerce.domain.product.relations.supplierRatingType.event.SupplierRatingTypeDeleted;
import com.skytala.eCommerce.domain.product.relations.supplierRatingType.event.SupplierRatingTypeFound;
import com.skytala.eCommerce.domain.product.relations.supplierRatingType.event.SupplierRatingTypeUpdated;
import com.skytala.eCommerce.domain.product.relations.supplierRatingType.mapper.SupplierRatingTypeMapper;
import com.skytala.eCommerce.domain.product.relations.supplierRatingType.model.SupplierRatingType;
import com.skytala.eCommerce.domain.product.relations.supplierRatingType.query.FindSupplierRatingTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/product/supplierRatingTypes")
public class SupplierRatingTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public SupplierRatingTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a SupplierRatingType
	 * @return a List with the SupplierRatingTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<SupplierRatingType>> findSupplierRatingTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindSupplierRatingTypesBy query = new FindSupplierRatingTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<SupplierRatingType> supplierRatingTypes =((SupplierRatingTypeFound) Scheduler.execute(query).data()).getSupplierRatingTypes();

		return ResponseEntity.ok().body(supplierRatingTypes);

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
	public ResponseEntity<SupplierRatingType> createSupplierRatingType(HttpServletRequest request) throws Exception {

		SupplierRatingType supplierRatingTypeToBeAdded = new SupplierRatingType();
		try {
			supplierRatingTypeToBeAdded = SupplierRatingTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createSupplierRatingType(supplierRatingTypeToBeAdded);

	}

	/**
	 * creates a new SupplierRatingType entry in the ofbiz database
	 * 
	 * @param supplierRatingTypeToBeAdded
	 *            the SupplierRatingType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<SupplierRatingType> createSupplierRatingType(@RequestBody SupplierRatingType supplierRatingTypeToBeAdded) throws Exception {

		AddSupplierRatingType command = new AddSupplierRatingType(supplierRatingTypeToBeAdded);
		SupplierRatingType supplierRatingType = ((SupplierRatingTypeAdded) Scheduler.execute(command).data()).getAddedSupplierRatingType();
		
		if (supplierRatingType != null) 
			return successful(supplierRatingType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the SupplierRatingType with the specific Id
	 * 
	 * @param supplierRatingTypeToBeUpdated
	 *            the SupplierRatingType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{supplierRatingTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateSupplierRatingType(@RequestBody SupplierRatingType supplierRatingTypeToBeUpdated,
			@PathVariable String supplierRatingTypeId) throws Exception {

		supplierRatingTypeToBeUpdated.setSupplierRatingTypeId(supplierRatingTypeId);

		UpdateSupplierRatingType command = new UpdateSupplierRatingType(supplierRatingTypeToBeUpdated);

		try {
			if(((SupplierRatingTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{supplierRatingTypeId}")
	public ResponseEntity<SupplierRatingType> findById(@PathVariable String supplierRatingTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("supplierRatingTypeId", supplierRatingTypeId);
		try {

			List<SupplierRatingType> foundSupplierRatingType = findSupplierRatingTypesBy(requestParams).getBody();
			if(foundSupplierRatingType.size()==1){				return successful(foundSupplierRatingType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{supplierRatingTypeId}")
	public ResponseEntity<String> deleteSupplierRatingTypeByIdUpdated(@PathVariable String supplierRatingTypeId) throws Exception {
		DeleteSupplierRatingType command = new DeleteSupplierRatingType(supplierRatingTypeId);

		try {
			if (((SupplierRatingTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
