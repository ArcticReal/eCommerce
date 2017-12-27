package com.skytala.eCommerce.domain.order.relations.returnType;

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
import com.skytala.eCommerce.domain.order.relations.returnType.command.AddReturnType;
import com.skytala.eCommerce.domain.order.relations.returnType.command.DeleteReturnType;
import com.skytala.eCommerce.domain.order.relations.returnType.command.UpdateReturnType;
import com.skytala.eCommerce.domain.order.relations.returnType.event.ReturnTypeAdded;
import com.skytala.eCommerce.domain.order.relations.returnType.event.ReturnTypeDeleted;
import com.skytala.eCommerce.domain.order.relations.returnType.event.ReturnTypeFound;
import com.skytala.eCommerce.domain.order.relations.returnType.event.ReturnTypeUpdated;
import com.skytala.eCommerce.domain.order.relations.returnType.mapper.ReturnTypeMapper;
import com.skytala.eCommerce.domain.order.relations.returnType.model.ReturnType;
import com.skytala.eCommerce.domain.order.relations.returnType.query.FindReturnTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/order/returnTypes")
public class ReturnTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ReturnTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ReturnType
	 * @return a List with the ReturnTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ReturnType>> findReturnTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindReturnTypesBy query = new FindReturnTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ReturnType> returnTypes =((ReturnTypeFound) Scheduler.execute(query).data()).getReturnTypes();

		return ResponseEntity.ok().body(returnTypes);

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
	public ResponseEntity<ReturnType> createReturnType(HttpServletRequest request) throws Exception {

		ReturnType returnTypeToBeAdded = new ReturnType();
		try {
			returnTypeToBeAdded = ReturnTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createReturnType(returnTypeToBeAdded);

	}

	/**
	 * creates a new ReturnType entry in the ofbiz database
	 * 
	 * @param returnTypeToBeAdded
	 *            the ReturnType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ReturnType> createReturnType(@RequestBody ReturnType returnTypeToBeAdded) throws Exception {

		AddReturnType command = new AddReturnType(returnTypeToBeAdded);
		ReturnType returnType = ((ReturnTypeAdded) Scheduler.execute(command).data()).getAddedReturnType();
		
		if (returnType != null) 
			return successful(returnType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ReturnType with the specific Id
	 * 
	 * @param returnTypeToBeUpdated
	 *            the ReturnType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{returnTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateReturnType(@RequestBody ReturnType returnTypeToBeUpdated,
			@PathVariable String returnTypeId) throws Exception {

		returnTypeToBeUpdated.setReturnTypeId(returnTypeId);

		UpdateReturnType command = new UpdateReturnType(returnTypeToBeUpdated);

		try {
			if(((ReturnTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{returnTypeId}")
	public ResponseEntity<ReturnType> findById(@PathVariable String returnTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("returnTypeId", returnTypeId);
		try {

			List<ReturnType> foundReturnType = findReturnTypesBy(requestParams).getBody();
			if(foundReturnType.size()==1){				return successful(foundReturnType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{returnTypeId}")
	public ResponseEntity<String> deleteReturnTypeByIdUpdated(@PathVariable String returnTypeId) throws Exception {
		DeleteReturnType command = new DeleteReturnType(returnTypeId);

		try {
			if (((ReturnTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
