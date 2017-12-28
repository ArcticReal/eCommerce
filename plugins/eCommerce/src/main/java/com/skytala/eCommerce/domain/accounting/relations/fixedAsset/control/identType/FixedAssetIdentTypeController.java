package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.control.identType;

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
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.command.identType.AddFixedAssetIdentType;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.command.identType.DeleteFixedAssetIdentType;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.command.identType.UpdateFixedAssetIdentType;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.identType.FixedAssetIdentTypeAdded;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.identType.FixedAssetIdentTypeDeleted;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.identType.FixedAssetIdentTypeFound;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.identType.FixedAssetIdentTypeUpdated;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.mapper.identType.FixedAssetIdentTypeMapper;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.identType.FixedAssetIdentType;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.query.identType.FindFixedAssetIdentTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/fixedAsset/fixedAssetIdentTypes")
public class FixedAssetIdentTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public FixedAssetIdentTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a FixedAssetIdentType
	 * @return a List with the FixedAssetIdentTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<FixedAssetIdentType>> findFixedAssetIdentTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindFixedAssetIdentTypesBy query = new FindFixedAssetIdentTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<FixedAssetIdentType> fixedAssetIdentTypes =((FixedAssetIdentTypeFound) Scheduler.execute(query).data()).getFixedAssetIdentTypes();

		return ResponseEntity.ok().body(fixedAssetIdentTypes);

	}

	/**
	 * creates a new FixedAssetIdentType entry in the ofbiz database
	 * 
	 * @param fixedAssetIdentTypeToBeAdded
	 *            the FixedAssetIdentType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<FixedAssetIdentType> createFixedAssetIdentType(@RequestBody FixedAssetIdentType fixedAssetIdentTypeToBeAdded) throws Exception {

		AddFixedAssetIdentType command = new AddFixedAssetIdentType(fixedAssetIdentTypeToBeAdded);
		FixedAssetIdentType fixedAssetIdentType = ((FixedAssetIdentTypeAdded) Scheduler.execute(command).data()).getAddedFixedAssetIdentType();
		
		if (fixedAssetIdentType != null) 
			return successful(fixedAssetIdentType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the FixedAssetIdentType with the specific Id
	 * 
	 * @param fixedAssetIdentTypeToBeUpdated
	 *            the FixedAssetIdentType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{fixedAssetIdentTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateFixedAssetIdentType(@RequestBody FixedAssetIdentType fixedAssetIdentTypeToBeUpdated,
			@PathVariable String fixedAssetIdentTypeId) throws Exception {

		fixedAssetIdentTypeToBeUpdated.setFixedAssetIdentTypeId(fixedAssetIdentTypeId);

		UpdateFixedAssetIdentType command = new UpdateFixedAssetIdentType(fixedAssetIdentTypeToBeUpdated);

		try {
			if(((FixedAssetIdentTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{fixedAssetIdentTypeId}")
	public ResponseEntity<FixedAssetIdentType> findById(@PathVariable String fixedAssetIdentTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("fixedAssetIdentTypeId", fixedAssetIdentTypeId);
		try {

			List<FixedAssetIdentType> foundFixedAssetIdentType = findFixedAssetIdentTypesBy(requestParams).getBody();
			if(foundFixedAssetIdentType.size()==1){				return successful(foundFixedAssetIdentType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{fixedAssetIdentTypeId}")
	public ResponseEntity<String> deleteFixedAssetIdentTypeByIdUpdated(@PathVariable String fixedAssetIdentTypeId) throws Exception {
		DeleteFixedAssetIdentType command = new DeleteFixedAssetIdentType(fixedAssetIdentTypeId);

		try {
			if (((FixedAssetIdentTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
