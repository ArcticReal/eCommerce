package com.skytala.eCommerce.domain.order.relations.returnItem.control.typeMap;

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
import com.skytala.eCommerce.domain.order.relations.returnItem.command.typeMap.AddReturnItemTypeMap;
import com.skytala.eCommerce.domain.order.relations.returnItem.command.typeMap.DeleteReturnItemTypeMap;
import com.skytala.eCommerce.domain.order.relations.returnItem.command.typeMap.UpdateReturnItemTypeMap;
import com.skytala.eCommerce.domain.order.relations.returnItem.event.typeMap.ReturnItemTypeMapAdded;
import com.skytala.eCommerce.domain.order.relations.returnItem.event.typeMap.ReturnItemTypeMapDeleted;
import com.skytala.eCommerce.domain.order.relations.returnItem.event.typeMap.ReturnItemTypeMapFound;
import com.skytala.eCommerce.domain.order.relations.returnItem.event.typeMap.ReturnItemTypeMapUpdated;
import com.skytala.eCommerce.domain.order.relations.returnItem.mapper.typeMap.ReturnItemTypeMapMapper;
import com.skytala.eCommerce.domain.order.relations.returnItem.model.typeMap.ReturnItemTypeMap;
import com.skytala.eCommerce.domain.order.relations.returnItem.query.typeMap.FindReturnItemTypeMapsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/order/returnItem/returnItemTypeMaps")
public class ReturnItemTypeMapController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ReturnItemTypeMapController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ReturnItemTypeMap
	 * @return a List with the ReturnItemTypeMaps
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ReturnItemTypeMap>> findReturnItemTypeMapsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindReturnItemTypeMapsBy query = new FindReturnItemTypeMapsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ReturnItemTypeMap> returnItemTypeMaps =((ReturnItemTypeMapFound) Scheduler.execute(query).data()).getReturnItemTypeMaps();

		return ResponseEntity.ok().body(returnItemTypeMaps);

	}

	/**
	 * creates a new ReturnItemTypeMap entry in the ofbiz database
	 * 
	 * @param returnItemTypeMapToBeAdded
	 *            the ReturnItemTypeMap thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ReturnItemTypeMap> createReturnItemTypeMap(@RequestBody ReturnItemTypeMap returnItemTypeMapToBeAdded) throws Exception {

		AddReturnItemTypeMap command = new AddReturnItemTypeMap(returnItemTypeMapToBeAdded);
		ReturnItemTypeMap returnItemTypeMap = ((ReturnItemTypeMapAdded) Scheduler.execute(command).data()).getAddedReturnItemTypeMap();
		
		if (returnItemTypeMap != null) 
			return successful(returnItemTypeMap);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ReturnItemTypeMap with the specific Id
	 * 
	 * @param returnItemTypeMapToBeUpdated
	 *            the ReturnItemTypeMap thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{returnItemMapKey}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateReturnItemTypeMap(@RequestBody ReturnItemTypeMap returnItemTypeMapToBeUpdated,
			@PathVariable String returnItemMapKey) throws Exception {

		returnItemTypeMapToBeUpdated.setReturnItemMapKey(returnItemMapKey);

		UpdateReturnItemTypeMap command = new UpdateReturnItemTypeMap(returnItemTypeMapToBeUpdated);

		try {
			if(((ReturnItemTypeMapUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{returnItemTypeMapId}")
	public ResponseEntity<ReturnItemTypeMap> findById(@PathVariable String returnItemTypeMapId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("returnItemTypeMapId", returnItemTypeMapId);
		try {

			List<ReturnItemTypeMap> foundReturnItemTypeMap = findReturnItemTypeMapsBy(requestParams).getBody();
			if(foundReturnItemTypeMap.size()==1){				return successful(foundReturnItemTypeMap.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{returnItemTypeMapId}")
	public ResponseEntity<String> deleteReturnItemTypeMapByIdUpdated(@PathVariable String returnItemTypeMapId) throws Exception {
		DeleteReturnItemTypeMap command = new DeleteReturnItemTypeMap(returnItemTypeMapId);

		try {
			if (((ReturnItemTypeMapDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
