package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.control.productType;

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
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.command.productType.AddFixedAssetProductType;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.command.productType.DeleteFixedAssetProductType;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.command.productType.UpdateFixedAssetProductType;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.productType.FixedAssetProductTypeAdded;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.productType.FixedAssetProductTypeDeleted;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.productType.FixedAssetProductTypeFound;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.productType.FixedAssetProductTypeUpdated;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.mapper.productType.FixedAssetProductTypeMapper;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.productType.FixedAssetProductType;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.query.productType.FindFixedAssetProductTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/fixedAsset/fixedAssetProductTypes")
public class FixedAssetProductTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public FixedAssetProductTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a FixedAssetProductType
	 * @return a List with the FixedAssetProductTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<FixedAssetProductType>> findFixedAssetProductTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindFixedAssetProductTypesBy query = new FindFixedAssetProductTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<FixedAssetProductType> fixedAssetProductTypes =((FixedAssetProductTypeFound) Scheduler.execute(query).data()).getFixedAssetProductTypes();

		return ResponseEntity.ok().body(fixedAssetProductTypes);

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
	public ResponseEntity<FixedAssetProductType> createFixedAssetProductType(HttpServletRequest request) throws Exception {

		FixedAssetProductType fixedAssetProductTypeToBeAdded = new FixedAssetProductType();
		try {
			fixedAssetProductTypeToBeAdded = FixedAssetProductTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createFixedAssetProductType(fixedAssetProductTypeToBeAdded);

	}

	/**
	 * creates a new FixedAssetProductType entry in the ofbiz database
	 * 
	 * @param fixedAssetProductTypeToBeAdded
	 *            the FixedAssetProductType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<FixedAssetProductType> createFixedAssetProductType(@RequestBody FixedAssetProductType fixedAssetProductTypeToBeAdded) throws Exception {

		AddFixedAssetProductType command = new AddFixedAssetProductType(fixedAssetProductTypeToBeAdded);
		FixedAssetProductType fixedAssetProductType = ((FixedAssetProductTypeAdded) Scheduler.execute(command).data()).getAddedFixedAssetProductType();
		
		if (fixedAssetProductType != null) 
			return successful(fixedAssetProductType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the FixedAssetProductType with the specific Id
	 * 
	 * @param fixedAssetProductTypeToBeUpdated
	 *            the FixedAssetProductType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{fixedAssetProductTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateFixedAssetProductType(@RequestBody FixedAssetProductType fixedAssetProductTypeToBeUpdated,
			@PathVariable String fixedAssetProductTypeId) throws Exception {

		fixedAssetProductTypeToBeUpdated.setFixedAssetProductTypeId(fixedAssetProductTypeId);

		UpdateFixedAssetProductType command = new UpdateFixedAssetProductType(fixedAssetProductTypeToBeUpdated);

		try {
			if(((FixedAssetProductTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{fixedAssetProductTypeId}")
	public ResponseEntity<FixedAssetProductType> findById(@PathVariable String fixedAssetProductTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("fixedAssetProductTypeId", fixedAssetProductTypeId);
		try {

			List<FixedAssetProductType> foundFixedAssetProductType = findFixedAssetProductTypesBy(requestParams).getBody();
			if(foundFixedAssetProductType.size()==1){				return successful(foundFixedAssetProductType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{fixedAssetProductTypeId}")
	public ResponseEntity<String> deleteFixedAssetProductTypeByIdUpdated(@PathVariable String fixedAssetProductTypeId) throws Exception {
		DeleteFixedAssetProductType command = new DeleteFixedAssetProductType(fixedAssetProductTypeId);

		try {
			if (((FixedAssetProductTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
