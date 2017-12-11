package com.skytala.eCommerce.domain.order.relations.returnReason;

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
import com.skytala.eCommerce.domain.order.relations.returnReason.command.AddReturnReason;
import com.skytala.eCommerce.domain.order.relations.returnReason.command.DeleteReturnReason;
import com.skytala.eCommerce.domain.order.relations.returnReason.command.UpdateReturnReason;
import com.skytala.eCommerce.domain.order.relations.returnReason.event.ReturnReasonAdded;
import com.skytala.eCommerce.domain.order.relations.returnReason.event.ReturnReasonDeleted;
import com.skytala.eCommerce.domain.order.relations.returnReason.event.ReturnReasonFound;
import com.skytala.eCommerce.domain.order.relations.returnReason.event.ReturnReasonUpdated;
import com.skytala.eCommerce.domain.order.relations.returnReason.mapper.ReturnReasonMapper;
import com.skytala.eCommerce.domain.order.relations.returnReason.model.ReturnReason;
import com.skytala.eCommerce.domain.order.relations.returnReason.query.FindReturnReasonsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/order/returnReasons")
public class ReturnReasonController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ReturnReasonController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ReturnReason
	 * @return a List with the ReturnReasons
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findReturnReasonsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindReturnReasonsBy query = new FindReturnReasonsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ReturnReason> returnReasons =((ReturnReasonFound) Scheduler.execute(query).data()).getReturnReasons();

		if (returnReasons.size() == 1) {
			return ResponseEntity.ok().body(returnReasons.get(0));
		}

		return ResponseEntity.ok().body(returnReasons);

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
	public ResponseEntity<Object> createReturnReason(HttpServletRequest request) throws Exception {

		ReturnReason returnReasonToBeAdded = new ReturnReason();
		try {
			returnReasonToBeAdded = ReturnReasonMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createReturnReason(returnReasonToBeAdded);

	}

	/**
	 * creates a new ReturnReason entry in the ofbiz database
	 * 
	 * @param returnReasonToBeAdded
	 *            the ReturnReason thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createReturnReason(@RequestBody ReturnReason returnReasonToBeAdded) throws Exception {

		AddReturnReason command = new AddReturnReason(returnReasonToBeAdded);
		ReturnReason returnReason = ((ReturnReasonAdded) Scheduler.execute(command).data()).getAddedReturnReason();
		
		if (returnReason != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(returnReason);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ReturnReason could not be created.");
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
	public boolean updateReturnReason(HttpServletRequest request) throws Exception {

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

		ReturnReason returnReasonToBeUpdated = new ReturnReason();

		try {
			returnReasonToBeUpdated = ReturnReasonMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateReturnReason(returnReasonToBeUpdated, returnReasonToBeUpdated.getReturnReasonId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ReturnReason with the specific Id
	 * 
	 * @param returnReasonToBeUpdated
	 *            the ReturnReason thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{returnReasonId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateReturnReason(@RequestBody ReturnReason returnReasonToBeUpdated,
			@PathVariable String returnReasonId) throws Exception {

		returnReasonToBeUpdated.setReturnReasonId(returnReasonId);

		UpdateReturnReason command = new UpdateReturnReason(returnReasonToBeUpdated);

		try {
			if(((ReturnReasonUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{returnReasonId}")
	public ResponseEntity<Object> findById(@PathVariable String returnReasonId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("returnReasonId", returnReasonId);
		try {

			Object foundReturnReason = findReturnReasonsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundReturnReason);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{returnReasonId}")
	public ResponseEntity<Object> deleteReturnReasonByIdUpdated(@PathVariable String returnReasonId) throws Exception {
		DeleteReturnReason command = new DeleteReturnReason(returnReasonId);

		try {
			if (((ReturnReasonDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ReturnReason could not be deleted");

	}

}
