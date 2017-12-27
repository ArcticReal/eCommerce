package com.skytala.eCommerce.domain.order.relations.returnAdjustment.control.type;

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
import com.skytala.eCommerce.domain.order.relations.returnAdjustment.command.type.AddReturnAdjustmentType;
import com.skytala.eCommerce.domain.order.relations.returnAdjustment.command.type.DeleteReturnAdjustmentType;
import com.skytala.eCommerce.domain.order.relations.returnAdjustment.command.type.UpdateReturnAdjustmentType;
import com.skytala.eCommerce.domain.order.relations.returnAdjustment.event.type.ReturnAdjustmentTypeAdded;
import com.skytala.eCommerce.domain.order.relations.returnAdjustment.event.type.ReturnAdjustmentTypeDeleted;
import com.skytala.eCommerce.domain.order.relations.returnAdjustment.event.type.ReturnAdjustmentTypeFound;
import com.skytala.eCommerce.domain.order.relations.returnAdjustment.event.type.ReturnAdjustmentTypeUpdated;
import com.skytala.eCommerce.domain.order.relations.returnAdjustment.mapper.type.ReturnAdjustmentTypeMapper;
import com.skytala.eCommerce.domain.order.relations.returnAdjustment.model.type.ReturnAdjustmentType;
import com.skytala.eCommerce.domain.order.relations.returnAdjustment.query.type.FindReturnAdjustmentTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/order/returnAdjustment/returnAdjustmentTypes")
public class ReturnAdjustmentTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ReturnAdjustmentTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ReturnAdjustmentType
	 * @return a List with the ReturnAdjustmentTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ReturnAdjustmentType>> findReturnAdjustmentTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindReturnAdjustmentTypesBy query = new FindReturnAdjustmentTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ReturnAdjustmentType> returnAdjustmentTypes =((ReturnAdjustmentTypeFound) Scheduler.execute(query).data()).getReturnAdjustmentTypes();

		return ResponseEntity.ok().body(returnAdjustmentTypes);

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
	public ResponseEntity<ReturnAdjustmentType> createReturnAdjustmentType(HttpServletRequest request) throws Exception {

		ReturnAdjustmentType returnAdjustmentTypeToBeAdded = new ReturnAdjustmentType();
		try {
			returnAdjustmentTypeToBeAdded = ReturnAdjustmentTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createReturnAdjustmentType(returnAdjustmentTypeToBeAdded);

	}

	/**
	 * creates a new ReturnAdjustmentType entry in the ofbiz database
	 * 
	 * @param returnAdjustmentTypeToBeAdded
	 *            the ReturnAdjustmentType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ReturnAdjustmentType> createReturnAdjustmentType(@RequestBody ReturnAdjustmentType returnAdjustmentTypeToBeAdded) throws Exception {

		AddReturnAdjustmentType command = new AddReturnAdjustmentType(returnAdjustmentTypeToBeAdded);
		ReturnAdjustmentType returnAdjustmentType = ((ReturnAdjustmentTypeAdded) Scheduler.execute(command).data()).getAddedReturnAdjustmentType();
		
		if (returnAdjustmentType != null) 
			return successful(returnAdjustmentType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ReturnAdjustmentType with the specific Id
	 * 
	 * @param returnAdjustmentTypeToBeUpdated
	 *            the ReturnAdjustmentType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{returnAdjustmentTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateReturnAdjustmentType(@RequestBody ReturnAdjustmentType returnAdjustmentTypeToBeUpdated,
			@PathVariable String returnAdjustmentTypeId) throws Exception {

		returnAdjustmentTypeToBeUpdated.setReturnAdjustmentTypeId(returnAdjustmentTypeId);

		UpdateReturnAdjustmentType command = new UpdateReturnAdjustmentType(returnAdjustmentTypeToBeUpdated);

		try {
			if(((ReturnAdjustmentTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{returnAdjustmentTypeId}")
	public ResponseEntity<ReturnAdjustmentType> findById(@PathVariable String returnAdjustmentTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("returnAdjustmentTypeId", returnAdjustmentTypeId);
		try {

			List<ReturnAdjustmentType> foundReturnAdjustmentType = findReturnAdjustmentTypesBy(requestParams).getBody();
			if(foundReturnAdjustmentType.size()==1){				return successful(foundReturnAdjustmentType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{returnAdjustmentTypeId}")
	public ResponseEntity<String> deleteReturnAdjustmentTypeByIdUpdated(@PathVariable String returnAdjustmentTypeId) throws Exception {
		DeleteReturnAdjustmentType command = new DeleteReturnAdjustmentType(returnAdjustmentTypeId);

		try {
			if (((ReturnAdjustmentTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
