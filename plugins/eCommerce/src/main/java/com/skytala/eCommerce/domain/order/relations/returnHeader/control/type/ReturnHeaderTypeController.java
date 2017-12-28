package com.skytala.eCommerce.domain.order.relations.returnHeader.control.type;

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
import com.skytala.eCommerce.domain.order.relations.returnHeader.command.type.AddReturnHeaderType;
import com.skytala.eCommerce.domain.order.relations.returnHeader.command.type.DeleteReturnHeaderType;
import com.skytala.eCommerce.domain.order.relations.returnHeader.command.type.UpdateReturnHeaderType;
import com.skytala.eCommerce.domain.order.relations.returnHeader.event.type.ReturnHeaderTypeAdded;
import com.skytala.eCommerce.domain.order.relations.returnHeader.event.type.ReturnHeaderTypeDeleted;
import com.skytala.eCommerce.domain.order.relations.returnHeader.event.type.ReturnHeaderTypeFound;
import com.skytala.eCommerce.domain.order.relations.returnHeader.event.type.ReturnHeaderTypeUpdated;
import com.skytala.eCommerce.domain.order.relations.returnHeader.mapper.type.ReturnHeaderTypeMapper;
import com.skytala.eCommerce.domain.order.relations.returnHeader.model.type.ReturnHeaderType;
import com.skytala.eCommerce.domain.order.relations.returnHeader.query.type.FindReturnHeaderTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/order/returnHeader/returnHeaderTypes")
public class ReturnHeaderTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ReturnHeaderTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ReturnHeaderType
	 * @return a List with the ReturnHeaderTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ReturnHeaderType>> findReturnHeaderTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindReturnHeaderTypesBy query = new FindReturnHeaderTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ReturnHeaderType> returnHeaderTypes =((ReturnHeaderTypeFound) Scheduler.execute(query).data()).getReturnHeaderTypes();

		return ResponseEntity.ok().body(returnHeaderTypes);

	}

	/**
	 * creates a new ReturnHeaderType entry in the ofbiz database
	 * 
	 * @param returnHeaderTypeToBeAdded
	 *            the ReturnHeaderType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ReturnHeaderType> createReturnHeaderType(@RequestBody ReturnHeaderType returnHeaderTypeToBeAdded) throws Exception {

		AddReturnHeaderType command = new AddReturnHeaderType(returnHeaderTypeToBeAdded);
		ReturnHeaderType returnHeaderType = ((ReturnHeaderTypeAdded) Scheduler.execute(command).data()).getAddedReturnHeaderType();
		
		if (returnHeaderType != null) 
			return successful(returnHeaderType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ReturnHeaderType with the specific Id
	 * 
	 * @param returnHeaderTypeToBeUpdated
	 *            the ReturnHeaderType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{returnHeaderTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateReturnHeaderType(@RequestBody ReturnHeaderType returnHeaderTypeToBeUpdated,
			@PathVariable String returnHeaderTypeId) throws Exception {

		returnHeaderTypeToBeUpdated.setReturnHeaderTypeId(returnHeaderTypeId);

		UpdateReturnHeaderType command = new UpdateReturnHeaderType(returnHeaderTypeToBeUpdated);

		try {
			if(((ReturnHeaderTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{returnHeaderTypeId}")
	public ResponseEntity<ReturnHeaderType> findById(@PathVariable String returnHeaderTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("returnHeaderTypeId", returnHeaderTypeId);
		try {

			List<ReturnHeaderType> foundReturnHeaderType = findReturnHeaderTypesBy(requestParams).getBody();
			if(foundReturnHeaderType.size()==1){				return successful(foundReturnHeaderType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{returnHeaderTypeId}")
	public ResponseEntity<String> deleteReturnHeaderTypeByIdUpdated(@PathVariable String returnHeaderTypeId) throws Exception {
		DeleteReturnHeaderType command = new DeleteReturnHeaderType(returnHeaderTypeId);

		try {
			if (((ReturnHeaderTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
