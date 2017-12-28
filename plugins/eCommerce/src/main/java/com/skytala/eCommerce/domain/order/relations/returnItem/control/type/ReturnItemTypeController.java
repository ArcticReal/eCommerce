package com.skytala.eCommerce.domain.order.relations.returnItem.control.type;

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
import com.skytala.eCommerce.domain.order.relations.returnItem.command.type.AddReturnItemType;
import com.skytala.eCommerce.domain.order.relations.returnItem.command.type.DeleteReturnItemType;
import com.skytala.eCommerce.domain.order.relations.returnItem.command.type.UpdateReturnItemType;
import com.skytala.eCommerce.domain.order.relations.returnItem.event.type.ReturnItemTypeAdded;
import com.skytala.eCommerce.domain.order.relations.returnItem.event.type.ReturnItemTypeDeleted;
import com.skytala.eCommerce.domain.order.relations.returnItem.event.type.ReturnItemTypeFound;
import com.skytala.eCommerce.domain.order.relations.returnItem.event.type.ReturnItemTypeUpdated;
import com.skytala.eCommerce.domain.order.relations.returnItem.mapper.type.ReturnItemTypeMapper;
import com.skytala.eCommerce.domain.order.relations.returnItem.model.type.ReturnItemType;
import com.skytala.eCommerce.domain.order.relations.returnItem.query.type.FindReturnItemTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/order/returnItem/returnItemTypes")
public class ReturnItemTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ReturnItemTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ReturnItemType
	 * @return a List with the ReturnItemTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ReturnItemType>> findReturnItemTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindReturnItemTypesBy query = new FindReturnItemTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ReturnItemType> returnItemTypes =((ReturnItemTypeFound) Scheduler.execute(query).data()).getReturnItemTypes();

		return ResponseEntity.ok().body(returnItemTypes);

	}

	/**
	 * creates a new ReturnItemType entry in the ofbiz database
	 * 
	 * @param returnItemTypeToBeAdded
	 *            the ReturnItemType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ReturnItemType> createReturnItemType(@RequestBody ReturnItemType returnItemTypeToBeAdded) throws Exception {

		AddReturnItemType command = new AddReturnItemType(returnItemTypeToBeAdded);
		ReturnItemType returnItemType = ((ReturnItemTypeAdded) Scheduler.execute(command).data()).getAddedReturnItemType();
		
		if (returnItemType != null) 
			return successful(returnItemType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ReturnItemType with the specific Id
	 * 
	 * @param returnItemTypeToBeUpdated
	 *            the ReturnItemType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{returnItemTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateReturnItemType(@RequestBody ReturnItemType returnItemTypeToBeUpdated,
			@PathVariable String returnItemTypeId) throws Exception {

		returnItemTypeToBeUpdated.setReturnItemTypeId(returnItemTypeId);

		UpdateReturnItemType command = new UpdateReturnItemType(returnItemTypeToBeUpdated);

		try {
			if(((ReturnItemTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{returnItemTypeId}")
	public ResponseEntity<ReturnItemType> findById(@PathVariable String returnItemTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("returnItemTypeId", returnItemTypeId);
		try {

			List<ReturnItemType> foundReturnItemType = findReturnItemTypesBy(requestParams).getBody();
			if(foundReturnItemType.size()==1){				return successful(foundReturnItemType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{returnItemTypeId}")
	public ResponseEntity<String> deleteReturnItemTypeByIdUpdated(@PathVariable String returnItemTypeId) throws Exception {
		DeleteReturnItemType command = new DeleteReturnItemType(returnItemTypeId);

		try {
			if (((ReturnItemTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
