package com.skytala.eCommerce.domain.order.relations.returnAdjustment;

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
import com.skytala.eCommerce.domain.order.relations.returnAdjustment.command.AddReturnAdjustment;
import com.skytala.eCommerce.domain.order.relations.returnAdjustment.command.DeleteReturnAdjustment;
import com.skytala.eCommerce.domain.order.relations.returnAdjustment.command.UpdateReturnAdjustment;
import com.skytala.eCommerce.domain.order.relations.returnAdjustment.event.ReturnAdjustmentAdded;
import com.skytala.eCommerce.domain.order.relations.returnAdjustment.event.ReturnAdjustmentDeleted;
import com.skytala.eCommerce.domain.order.relations.returnAdjustment.event.ReturnAdjustmentFound;
import com.skytala.eCommerce.domain.order.relations.returnAdjustment.event.ReturnAdjustmentUpdated;
import com.skytala.eCommerce.domain.order.relations.returnAdjustment.mapper.ReturnAdjustmentMapper;
import com.skytala.eCommerce.domain.order.relations.returnAdjustment.model.ReturnAdjustment;
import com.skytala.eCommerce.domain.order.relations.returnAdjustment.query.FindReturnAdjustmentsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@CrossOrigin
@RequestMapping("/returnAdjustments")
public class ReturnAdjustmentController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ReturnAdjustmentController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ReturnAdjustment
	 * @return a List with the ReturnAdjustments
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findReturnAdjustmentsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindReturnAdjustmentsBy query = new FindReturnAdjustmentsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ReturnAdjustment> returnAdjustments =((ReturnAdjustmentFound) Scheduler.execute(query).data()).getReturnAdjustments();

		if (returnAdjustments.size() == 1) {
			return ResponseEntity.ok().body(returnAdjustments.get(0));
		}

		return ResponseEntity.ok().body(returnAdjustments);

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
	public ResponseEntity<Object> createReturnAdjustment(HttpServletRequest request) throws Exception {

		ReturnAdjustment returnAdjustmentToBeAdded = new ReturnAdjustment();
		try {
			returnAdjustmentToBeAdded = ReturnAdjustmentMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createReturnAdjustment(returnAdjustmentToBeAdded);

	}

	/**
	 * creates a new ReturnAdjustment entry in the ofbiz database
	 * 
	 * @param returnAdjustmentToBeAdded
	 *            the ReturnAdjustment thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createReturnAdjustment(@RequestBody ReturnAdjustment returnAdjustmentToBeAdded) throws Exception {

		AddReturnAdjustment command = new AddReturnAdjustment(returnAdjustmentToBeAdded);
		ReturnAdjustment returnAdjustment = ((ReturnAdjustmentAdded) Scheduler.execute(command).data()).getAddedReturnAdjustment();
		
		if (returnAdjustment != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(returnAdjustment);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ReturnAdjustment could not be created.");
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
	public boolean updateReturnAdjustment(HttpServletRequest request) throws Exception {

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

		ReturnAdjustment returnAdjustmentToBeUpdated = new ReturnAdjustment();

		try {
			returnAdjustmentToBeUpdated = ReturnAdjustmentMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateReturnAdjustment(returnAdjustmentToBeUpdated, returnAdjustmentToBeUpdated.getReturnAdjustmentId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ReturnAdjustment with the specific Id
	 * 
	 * @param returnAdjustmentToBeUpdated
	 *            the ReturnAdjustment thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{returnAdjustmentId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateReturnAdjustment(@RequestBody ReturnAdjustment returnAdjustmentToBeUpdated,
			@PathVariable String returnAdjustmentId) throws Exception {

		returnAdjustmentToBeUpdated.setReturnAdjustmentId(returnAdjustmentId);

		UpdateReturnAdjustment command = new UpdateReturnAdjustment(returnAdjustmentToBeUpdated);

		try {
			if(((ReturnAdjustmentUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{returnAdjustmentId}")
	public ResponseEntity<Object> findById(@PathVariable String returnAdjustmentId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("returnAdjustmentId", returnAdjustmentId);
		try {

			Object foundReturnAdjustment = findReturnAdjustmentsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundReturnAdjustment);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{returnAdjustmentId}")
	public ResponseEntity<Object> deleteReturnAdjustmentByIdUpdated(@PathVariable String returnAdjustmentId) throws Exception {
		DeleteReturnAdjustment command = new DeleteReturnAdjustment(returnAdjustmentId);

		try {
			if (((ReturnAdjustmentDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ReturnAdjustment could not be deleted");

	}

	@RequestMapping(value = (" ** "))
	public ResponseEntity<Object> returnErrorPage(HttpServletRequest request) {

		String usedUri = request.getRequestURI();
		String[] splittedString = usedUri.split("/");

		String usedRequest = splittedString[splittedString.length - 1];

		if (validRequests.containsKey(usedRequest)) {
			String returnVal = "Error: request method " + request.getMethod() + " not allowed for \"" + usedUri
					+ "\"!\n" + "Please use " + validRequests.get(usedRequest) + "!";

			return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(returnVal);
		}

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/returnAdjustment/\" plus one of the following: "
				+ "";

		Set<String> keySet = validRequests.keySet();
		Iterator<String> it = keySet.iterator();

		while (it.hasNext()) {
			returnVal += "\"" + it.next() + "\"";
			if (it.hasNext())
				returnVal += ", ";
		}

		returnVal += "!";

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnVal);

	}
}
