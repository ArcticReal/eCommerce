package com.skytala.eCommerce.domain.order.relations.custRequest;

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
import com.skytala.eCommerce.domain.order.relations.custRequest.command.AddCustRequest;
import com.skytala.eCommerce.domain.order.relations.custRequest.command.DeleteCustRequest;
import com.skytala.eCommerce.domain.order.relations.custRequest.command.UpdateCustRequest;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.CustRequestAdded;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.CustRequestDeleted;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.CustRequestFound;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.CustRequestUpdated;
import com.skytala.eCommerce.domain.order.relations.custRequest.mapper.CustRequestMapper;
import com.skytala.eCommerce.domain.order.relations.custRequest.model.CustRequest;
import com.skytala.eCommerce.domain.order.relations.custRequest.query.FindCustRequestsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/order/custRequests")
public class CustRequestController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public CustRequestController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a CustRequest
	 * @return a List with the CustRequests
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<CustRequest>> findCustRequestsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindCustRequestsBy query = new FindCustRequestsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<CustRequest> custRequests =((CustRequestFound) Scheduler.execute(query).data()).getCustRequests();

		return ResponseEntity.ok().body(custRequests);

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
	public ResponseEntity<CustRequest> createCustRequest(HttpServletRequest request) throws Exception {

		CustRequest custRequestToBeAdded = new CustRequest();
		try {
			custRequestToBeAdded = CustRequestMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createCustRequest(custRequestToBeAdded);

	}

	/**
	 * creates a new CustRequest entry in the ofbiz database
	 * 
	 * @param custRequestToBeAdded
	 *            the CustRequest thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<CustRequest> createCustRequest(@RequestBody CustRequest custRequestToBeAdded) throws Exception {

		AddCustRequest command = new AddCustRequest(custRequestToBeAdded);
		CustRequest custRequest = ((CustRequestAdded) Scheduler.execute(command).data()).getAddedCustRequest();
		
		if (custRequest != null) 
			return successful(custRequest);
		else 
			return conflict(null);
	}

	/**
	 * Updates the CustRequest with the specific Id
	 * 
	 * @param custRequestToBeUpdated
	 *            the CustRequest thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{custRequestId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateCustRequest(@RequestBody CustRequest custRequestToBeUpdated,
			@PathVariable String custRequestId) throws Exception {

		custRequestToBeUpdated.setCustRequestId(custRequestId);

		UpdateCustRequest command = new UpdateCustRequest(custRequestToBeUpdated);

		try {
			if(((CustRequestUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{custRequestId}")
	public ResponseEntity<CustRequest> findById(@PathVariable String custRequestId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("custRequestId", custRequestId);
		try {

			List<CustRequest> foundCustRequest = findCustRequestsBy(requestParams).getBody();
			if(foundCustRequest.size()==1){				return successful(foundCustRequest.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{custRequestId}")
	public ResponseEntity<String> deleteCustRequestByIdUpdated(@PathVariable String custRequestId) throws Exception {
		DeleteCustRequest command = new DeleteCustRequest(custRequestId);

		try {
			if (((CustRequestDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
