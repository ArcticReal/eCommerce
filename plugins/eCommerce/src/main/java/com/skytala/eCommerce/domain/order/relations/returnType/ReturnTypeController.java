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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@RequestMapping("/returnTypes")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findReturnTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindReturnTypesBy query = new FindReturnTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ReturnType> returnTypes =((ReturnTypeFound) Scheduler.execute(query).data()).getReturnTypes();

		if (returnTypes.size() == 1) {
			return ResponseEntity.ok().body(returnTypes.get(0));
		}

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
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createReturnType(HttpServletRequest request) throws Exception {

		ReturnType returnTypeToBeAdded = new ReturnType();
		try {
			returnTypeToBeAdded = ReturnTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
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
	public ResponseEntity<Object> createReturnType(@RequestBody ReturnType returnTypeToBeAdded) throws Exception {

		AddReturnType command = new AddReturnType(returnTypeToBeAdded);
		ReturnType returnType = ((ReturnTypeAdded) Scheduler.execute(command).data()).getAddedReturnType();
		
		if (returnType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(returnType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ReturnType could not be created.");
	}

	/**
	 * this method will only be called by Springs DispatcherServlet
	 * 
	 * @deprecated
	 * @param request
	 *            HttpServletRequest object
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/update", consumes = "application/x-www-form-urlencoded")
	public boolean updateReturnType(HttpServletRequest request) throws Exception {

		BufferedReader br;
		String data = null;
		Map<String, String> dataMap = null;

		try {
			br = new BufferedReader(new InputStreamReader(request.getInputStream()));
			if (br != null) {
				data = java.net.URLDecoder.decode(br.readLine(), "UTF-8");
			}
		} catch (IOException e1) {
			e1.printStackTrace();
			return false;
		}

		dataMap = Splitter.on('&').trimResults().withKeyValueSeparator(Splitter.on('=').limit(2).trimResults())
				.split(data);

		ReturnType returnTypeToBeUpdated = new ReturnType();

		try {
			returnTypeToBeUpdated = ReturnTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateReturnType(returnTypeToBeUpdated, returnTypeToBeUpdated.getReturnTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateReturnType(@RequestBody ReturnType returnTypeToBeUpdated,
			@PathVariable String returnTypeId) throws Exception {

		returnTypeToBeUpdated.setReturnTypeId(returnTypeId);

		UpdateReturnType command = new UpdateReturnType(returnTypeToBeUpdated);

		try {
			if(((ReturnTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{returnTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String returnTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("returnTypeId", returnTypeId);
		try {

			Object foundReturnType = findReturnTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundReturnType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{returnTypeId}")
	public ResponseEntity<Object> deleteReturnTypeByIdUpdated(@PathVariable String returnTypeId) throws Exception {
		DeleteReturnType command = new DeleteReturnType(returnTypeId);

		try {
			if (((ReturnTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ReturnType could not be deleted");

	}

}
