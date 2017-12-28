package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.control.type;

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
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.command.type.AddFixedAssetType;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.command.type.DeleteFixedAssetType;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.command.type.UpdateFixedAssetType;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.type.FixedAssetTypeAdded;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.type.FixedAssetTypeDeleted;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.type.FixedAssetTypeFound;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.type.FixedAssetTypeUpdated;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.mapper.type.FixedAssetTypeMapper;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.type.FixedAssetType;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.query.type.FindFixedAssetTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/fixedAsset/fixedAssetTypes")
public class FixedAssetTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public FixedAssetTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a FixedAssetType
	 * @return a List with the FixedAssetTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<FixedAssetType>> findFixedAssetTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindFixedAssetTypesBy query = new FindFixedAssetTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<FixedAssetType> fixedAssetTypes =((FixedAssetTypeFound) Scheduler.execute(query).data()).getFixedAssetTypes();

		return ResponseEntity.ok().body(fixedAssetTypes);

	}

	/**
	 * creates a new FixedAssetType entry in the ofbiz database
	 * 
	 * @param fixedAssetTypeToBeAdded
	 *            the FixedAssetType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<FixedAssetType> createFixedAssetType(@RequestBody FixedAssetType fixedAssetTypeToBeAdded) throws Exception {

		AddFixedAssetType command = new AddFixedAssetType(fixedAssetTypeToBeAdded);
		FixedAssetType fixedAssetType = ((FixedAssetTypeAdded) Scheduler.execute(command).data()).getAddedFixedAssetType();
		
		if (fixedAssetType != null) 
			return successful(fixedAssetType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the FixedAssetType with the specific Id
	 * 
	 * @param fixedAssetTypeToBeUpdated
	 *            the FixedAssetType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{fixedAssetTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateFixedAssetType(@RequestBody FixedAssetType fixedAssetTypeToBeUpdated,
			@PathVariable String fixedAssetTypeId) throws Exception {

		fixedAssetTypeToBeUpdated.setFixedAssetTypeId(fixedAssetTypeId);

		UpdateFixedAssetType command = new UpdateFixedAssetType(fixedAssetTypeToBeUpdated);

		try {
			if(((FixedAssetTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{fixedAssetTypeId}")
	public ResponseEntity<FixedAssetType> findById(@PathVariable String fixedAssetTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("fixedAssetTypeId", fixedAssetTypeId);
		try {

			List<FixedAssetType> foundFixedAssetType = findFixedAssetTypesBy(requestParams).getBody();
			if(foundFixedAssetType.size()==1){				return successful(foundFixedAssetType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{fixedAssetTypeId}")
	public ResponseEntity<String> deleteFixedAssetTypeByIdUpdated(@PathVariable String fixedAssetTypeId) throws Exception {
		DeleteFixedAssetType command = new DeleteFixedAssetType(fixedAssetTypeId);

		try {
			if (((FixedAssetTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
