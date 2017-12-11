package com.skytala.eCommerce.domain.order.relations.returnHeader;

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
import com.skytala.eCommerce.domain.order.relations.returnHeader.command.AddReturnHeader;
import com.skytala.eCommerce.domain.order.relations.returnHeader.command.DeleteReturnHeader;
import com.skytala.eCommerce.domain.order.relations.returnHeader.command.UpdateReturnHeader;
import com.skytala.eCommerce.domain.order.relations.returnHeader.event.ReturnHeaderAdded;
import com.skytala.eCommerce.domain.order.relations.returnHeader.event.ReturnHeaderDeleted;
import com.skytala.eCommerce.domain.order.relations.returnHeader.event.ReturnHeaderFound;
import com.skytala.eCommerce.domain.order.relations.returnHeader.event.ReturnHeaderUpdated;
import com.skytala.eCommerce.domain.order.relations.returnHeader.mapper.ReturnHeaderMapper;
import com.skytala.eCommerce.domain.order.relations.returnHeader.model.ReturnHeader;
import com.skytala.eCommerce.domain.order.relations.returnHeader.query.FindReturnHeadersBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/order/returnHeaders")
public class ReturnHeaderController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ReturnHeaderController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ReturnHeader
	 * @return a List with the ReturnHeaders
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findReturnHeadersBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindReturnHeadersBy query = new FindReturnHeadersBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ReturnHeader> returnHeaders =((ReturnHeaderFound) Scheduler.execute(query).data()).getReturnHeaders();

		if (returnHeaders.size() == 1) {
			return ResponseEntity.ok().body(returnHeaders.get(0));
		}

		return ResponseEntity.ok().body(returnHeaders);

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
	public ResponseEntity<Object> createReturnHeader(HttpServletRequest request) throws Exception {

		ReturnHeader returnHeaderToBeAdded = new ReturnHeader();
		try {
			returnHeaderToBeAdded = ReturnHeaderMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createReturnHeader(returnHeaderToBeAdded);

	}

	/**
	 * creates a new ReturnHeader entry in the ofbiz database
	 * 
	 * @param returnHeaderToBeAdded
	 *            the ReturnHeader thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createReturnHeader(@RequestBody ReturnHeader returnHeaderToBeAdded) throws Exception {

		AddReturnHeader command = new AddReturnHeader(returnHeaderToBeAdded);
		ReturnHeader returnHeader = ((ReturnHeaderAdded) Scheduler.execute(command).data()).getAddedReturnHeader();
		
		if (returnHeader != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(returnHeader);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ReturnHeader could not be created.");
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
	public boolean updateReturnHeader(HttpServletRequest request) throws Exception {

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

		ReturnHeader returnHeaderToBeUpdated = new ReturnHeader();

		try {
			returnHeaderToBeUpdated = ReturnHeaderMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateReturnHeader(returnHeaderToBeUpdated, returnHeaderToBeUpdated.getReturnId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the ReturnHeader with the specific Id
	 * 
	 * @param returnHeaderToBeUpdated
	 *            the ReturnHeader thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{returnId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateReturnHeader(@RequestBody ReturnHeader returnHeaderToBeUpdated,
			@PathVariable String returnId) throws Exception {

		returnHeaderToBeUpdated.setReturnId(returnId);

		UpdateReturnHeader command = new UpdateReturnHeader(returnHeaderToBeUpdated);

		try {
			if(((ReturnHeaderUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{returnHeaderId}")
	public ResponseEntity<Object> findById(@PathVariable String returnHeaderId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("returnHeaderId", returnHeaderId);
		try {

			Object foundReturnHeader = findReturnHeadersBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundReturnHeader);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{returnHeaderId}")
	public ResponseEntity<Object> deleteReturnHeaderByIdUpdated(@PathVariable String returnHeaderId) throws Exception {
		DeleteReturnHeader command = new DeleteReturnHeader(returnHeaderId);

		try {
			if (((ReturnHeaderDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ReturnHeader could not be deleted");

	}

}
