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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findReturnItemTypeMapsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindReturnItemTypeMapsBy query = new FindReturnItemTypeMapsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ReturnItemTypeMap> returnItemTypeMaps =((ReturnItemTypeMapFound) Scheduler.execute(query).data()).getReturnItemTypeMaps();

		if (returnItemTypeMaps.size() == 1) {
			return ResponseEntity.ok().body(returnItemTypeMaps.get(0));
		}

		return ResponseEntity.ok().body(returnItemTypeMaps);

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
	public ResponseEntity<Object> createReturnItemTypeMap(HttpServletRequest request) throws Exception {

		ReturnItemTypeMap returnItemTypeMapToBeAdded = new ReturnItemTypeMap();
		try {
			returnItemTypeMapToBeAdded = ReturnItemTypeMapMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createReturnItemTypeMap(returnItemTypeMapToBeAdded);

	}

	/**
	 * creates a new ReturnItemTypeMap entry in the ofbiz database
	 * 
	 * @param returnItemTypeMapToBeAdded
	 *            the ReturnItemTypeMap thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createReturnItemTypeMap(@RequestBody ReturnItemTypeMap returnItemTypeMapToBeAdded) throws Exception {

		AddReturnItemTypeMap command = new AddReturnItemTypeMap(returnItemTypeMapToBeAdded);
		ReturnItemTypeMap returnItemTypeMap = ((ReturnItemTypeMapAdded) Scheduler.execute(command).data()).getAddedReturnItemTypeMap();
		
		if (returnItemTypeMap != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(returnItemTypeMap);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ReturnItemTypeMap could not be created.");
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
	public boolean updateReturnItemTypeMap(HttpServletRequest request) throws Exception {

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

		ReturnItemTypeMap returnItemTypeMapToBeUpdated = new ReturnItemTypeMap();

		try {
			returnItemTypeMapToBeUpdated = ReturnItemTypeMapMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateReturnItemTypeMap(returnItemTypeMapToBeUpdated, returnItemTypeMapToBeUpdated.getReturnItemMapKey()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateReturnItemTypeMap(@RequestBody ReturnItemTypeMap returnItemTypeMapToBeUpdated,
			@PathVariable String returnItemMapKey) throws Exception {

		returnItemTypeMapToBeUpdated.setReturnItemMapKey(returnItemMapKey);

		UpdateReturnItemTypeMap command = new UpdateReturnItemTypeMap(returnItemTypeMapToBeUpdated);

		try {
			if(((ReturnItemTypeMapUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{returnItemTypeMapId}")
	public ResponseEntity<Object> findById(@PathVariable String returnItemTypeMapId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("returnItemTypeMapId", returnItemTypeMapId);
		try {

			Object foundReturnItemTypeMap = findReturnItemTypeMapsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundReturnItemTypeMap);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{returnItemTypeMapId}")
	public ResponseEntity<Object> deleteReturnItemTypeMapByIdUpdated(@PathVariable String returnItemTypeMapId) throws Exception {
		DeleteReturnItemTypeMap command = new DeleteReturnItemTypeMap(returnItemTypeMapId);

		try {
			if (((ReturnItemTypeMapDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ReturnItemTypeMap could not be deleted");

	}

}
