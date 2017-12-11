package com.skytala.eCommerce.domain.order.relations.returnContactMech;

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
import com.skytala.eCommerce.domain.order.relations.returnContactMech.command.AddReturnContactMech;
import com.skytala.eCommerce.domain.order.relations.returnContactMech.command.DeleteReturnContactMech;
import com.skytala.eCommerce.domain.order.relations.returnContactMech.command.UpdateReturnContactMech;
import com.skytala.eCommerce.domain.order.relations.returnContactMech.event.ReturnContactMechAdded;
import com.skytala.eCommerce.domain.order.relations.returnContactMech.event.ReturnContactMechDeleted;
import com.skytala.eCommerce.domain.order.relations.returnContactMech.event.ReturnContactMechFound;
import com.skytala.eCommerce.domain.order.relations.returnContactMech.event.ReturnContactMechUpdated;
import com.skytala.eCommerce.domain.order.relations.returnContactMech.mapper.ReturnContactMechMapper;
import com.skytala.eCommerce.domain.order.relations.returnContactMech.model.ReturnContactMech;
import com.skytala.eCommerce.domain.order.relations.returnContactMech.query.FindReturnContactMechsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/order/returnContactMechs")
public class ReturnContactMechController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ReturnContactMechController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ReturnContactMech
	 * @return a List with the ReturnContactMechs
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findReturnContactMechsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindReturnContactMechsBy query = new FindReturnContactMechsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ReturnContactMech> returnContactMechs =((ReturnContactMechFound) Scheduler.execute(query).data()).getReturnContactMechs();

		if (returnContactMechs.size() == 1) {
			return ResponseEntity.ok().body(returnContactMechs.get(0));
		}

		return ResponseEntity.ok().body(returnContactMechs);

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
	public ResponseEntity<Object> createReturnContactMech(HttpServletRequest request) throws Exception {

		ReturnContactMech returnContactMechToBeAdded = new ReturnContactMech();
		try {
			returnContactMechToBeAdded = ReturnContactMechMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createReturnContactMech(returnContactMechToBeAdded);

	}

	/**
	 * creates a new ReturnContactMech entry in the ofbiz database
	 * 
	 * @param returnContactMechToBeAdded
	 *            the ReturnContactMech thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createReturnContactMech(@RequestBody ReturnContactMech returnContactMechToBeAdded) throws Exception {

		AddReturnContactMech command = new AddReturnContactMech(returnContactMechToBeAdded);
		ReturnContactMech returnContactMech = ((ReturnContactMechAdded) Scheduler.execute(command).data()).getAddedReturnContactMech();
		
		if (returnContactMech != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(returnContactMech);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ReturnContactMech could not be created.");
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
	@PutMapping(value = "/update", consumes = "application/x-www-form-urlencoded")
	public boolean updateReturnContactMech(HttpServletRequest request) throws Exception {

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

		ReturnContactMech returnContactMechToBeUpdated = new ReturnContactMech();

		try {
			returnContactMechToBeUpdated = ReturnContactMechMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateReturnContactMech(returnContactMechToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ReturnContactMech with the specific Id
	 * 
	 * @param returnContactMechToBeUpdated
	 *            the ReturnContactMech thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateReturnContactMech(@RequestBody ReturnContactMech returnContactMechToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		returnContactMechToBeUpdated.setnull(null);

		UpdateReturnContactMech command = new UpdateReturnContactMech(returnContactMechToBeUpdated);

		try {
			if(((ReturnContactMechUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{returnContactMechId}")
	public ResponseEntity<Object> findById(@PathVariable String returnContactMechId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("returnContactMechId", returnContactMechId);
		try {

			Object foundReturnContactMech = findReturnContactMechsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundReturnContactMech);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{returnContactMechId}")
	public ResponseEntity<Object> deleteReturnContactMechByIdUpdated(@PathVariable String returnContactMechId) throws Exception {
		DeleteReturnContactMech command = new DeleteReturnContactMech(returnContactMechId);

		try {
			if (((ReturnContactMechDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ReturnContactMech could not be deleted");

	}

}
