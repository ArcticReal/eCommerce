package com.skytala.eCommerce.domain.order.relations.returnItem.control.response;

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
import com.skytala.eCommerce.domain.order.relations.returnItem.command.response.AddReturnItemResponse;
import com.skytala.eCommerce.domain.order.relations.returnItem.command.response.DeleteReturnItemResponse;
import com.skytala.eCommerce.domain.order.relations.returnItem.command.response.UpdateReturnItemResponse;
import com.skytala.eCommerce.domain.order.relations.returnItem.event.response.ReturnItemResponseAdded;
import com.skytala.eCommerce.domain.order.relations.returnItem.event.response.ReturnItemResponseDeleted;
import com.skytala.eCommerce.domain.order.relations.returnItem.event.response.ReturnItemResponseFound;
import com.skytala.eCommerce.domain.order.relations.returnItem.event.response.ReturnItemResponseUpdated;
import com.skytala.eCommerce.domain.order.relations.returnItem.mapper.response.ReturnItemResponseMapper;
import com.skytala.eCommerce.domain.order.relations.returnItem.model.response.ReturnItemResponse;
import com.skytala.eCommerce.domain.order.relations.returnItem.query.response.FindReturnItemResponsesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/order/returnItem/returnItemResponses")
public class ReturnItemResponseController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ReturnItemResponseController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ReturnItemResponse
	 * @return a List with the ReturnItemResponses
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ReturnItemResponse>> findReturnItemResponsesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindReturnItemResponsesBy query = new FindReturnItemResponsesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ReturnItemResponse> returnItemResponses =((ReturnItemResponseFound) Scheduler.execute(query).data()).getReturnItemResponses();

		return ResponseEntity.ok().body(returnItemResponses);

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
	public ResponseEntity<ReturnItemResponse> createReturnItemResponse(HttpServletRequest request) throws Exception {

		ReturnItemResponse returnItemResponseToBeAdded = new ReturnItemResponse();
		try {
			returnItemResponseToBeAdded = ReturnItemResponseMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createReturnItemResponse(returnItemResponseToBeAdded);

	}

	/**
	 * creates a new ReturnItemResponse entry in the ofbiz database
	 * 
	 * @param returnItemResponseToBeAdded
	 *            the ReturnItemResponse thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ReturnItemResponse> createReturnItemResponse(@RequestBody ReturnItemResponse returnItemResponseToBeAdded) throws Exception {

		AddReturnItemResponse command = new AddReturnItemResponse(returnItemResponseToBeAdded);
		ReturnItemResponse returnItemResponse = ((ReturnItemResponseAdded) Scheduler.execute(command).data()).getAddedReturnItemResponse();
		
		if (returnItemResponse != null) 
			return successful(returnItemResponse);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ReturnItemResponse with the specific Id
	 * 
	 * @param returnItemResponseToBeUpdated
	 *            the ReturnItemResponse thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{returnItemResponseId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateReturnItemResponse(@RequestBody ReturnItemResponse returnItemResponseToBeUpdated,
			@PathVariable String returnItemResponseId) throws Exception {

		returnItemResponseToBeUpdated.setReturnItemResponseId(returnItemResponseId);

		UpdateReturnItemResponse command = new UpdateReturnItemResponse(returnItemResponseToBeUpdated);

		try {
			if(((ReturnItemResponseUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{returnItemResponseId}")
	public ResponseEntity<ReturnItemResponse> findById(@PathVariable String returnItemResponseId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("returnItemResponseId", returnItemResponseId);
		try {

			List<ReturnItemResponse> foundReturnItemResponse = findReturnItemResponsesBy(requestParams).getBody();
			if(foundReturnItemResponse.size()==1){				return successful(foundReturnItemResponse.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{returnItemResponseId}")
	public ResponseEntity<String> deleteReturnItemResponseByIdUpdated(@PathVariable String returnItemResponseId) throws Exception {
		DeleteReturnItemResponse command = new DeleteReturnItemResponse(returnItemResponseId);

		try {
			if (((ReturnItemResponseDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
