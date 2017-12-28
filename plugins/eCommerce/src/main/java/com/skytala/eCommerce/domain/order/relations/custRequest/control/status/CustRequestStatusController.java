package com.skytala.eCommerce.domain.order.relations.custRequest.control.status;

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
import com.skytala.eCommerce.domain.order.relations.custRequest.command.status.AddCustRequestStatus;
import com.skytala.eCommerce.domain.order.relations.custRequest.command.status.DeleteCustRequestStatus;
import com.skytala.eCommerce.domain.order.relations.custRequest.command.status.UpdateCustRequestStatus;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.status.CustRequestStatusAdded;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.status.CustRequestStatusDeleted;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.status.CustRequestStatusFound;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.status.CustRequestStatusUpdated;
import com.skytala.eCommerce.domain.order.relations.custRequest.mapper.status.CustRequestStatusMapper;
import com.skytala.eCommerce.domain.order.relations.custRequest.model.status.CustRequestStatus;
import com.skytala.eCommerce.domain.order.relations.custRequest.query.status.FindCustRequestStatussBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/order/custRequest/custRequestStatuss")
public class CustRequestStatusController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public CustRequestStatusController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a CustRequestStatus
	 * @return a List with the CustRequestStatuss
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<CustRequestStatus>> findCustRequestStatussBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindCustRequestStatussBy query = new FindCustRequestStatussBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<CustRequestStatus> custRequestStatuss =((CustRequestStatusFound) Scheduler.execute(query).data()).getCustRequestStatuss();

		return ResponseEntity.ok().body(custRequestStatuss);

	}

	/**
	 * creates a new CustRequestStatus entry in the ofbiz database
	 * 
	 * @param custRequestStatusToBeAdded
	 *            the CustRequestStatus thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<CustRequestStatus> createCustRequestStatus(@RequestBody CustRequestStatus custRequestStatusToBeAdded) throws Exception {

		AddCustRequestStatus command = new AddCustRequestStatus(custRequestStatusToBeAdded);
		CustRequestStatus custRequestStatus = ((CustRequestStatusAdded) Scheduler.execute(command).data()).getAddedCustRequestStatus();
		
		if (custRequestStatus != null) 
			return successful(custRequestStatus);
		else 
			return conflict(null);
	}

	/**
	 * Updates the CustRequestStatus with the specific Id
	 * 
	 * @param custRequestStatusToBeUpdated
	 *            the CustRequestStatus thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{custRequestStatusId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateCustRequestStatus(@RequestBody CustRequestStatus custRequestStatusToBeUpdated,
			@PathVariable String custRequestStatusId) throws Exception {

		custRequestStatusToBeUpdated.setCustRequestStatusId(custRequestStatusId);

		UpdateCustRequestStatus command = new UpdateCustRequestStatus(custRequestStatusToBeUpdated);

		try {
			if(((CustRequestStatusUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{custRequestStatusId}")
	public ResponseEntity<CustRequestStatus> findById(@PathVariable String custRequestStatusId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("custRequestStatusId", custRequestStatusId);
		try {

			List<CustRequestStatus> foundCustRequestStatus = findCustRequestStatussBy(requestParams).getBody();
			if(foundCustRequestStatus.size()==1){				return successful(foundCustRequestStatus.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{custRequestStatusId}")
	public ResponseEntity<String> deleteCustRequestStatusByIdUpdated(@PathVariable String custRequestStatusId) throws Exception {
		DeleteCustRequestStatus command = new DeleteCustRequestStatus(custRequestStatusId);

		try {
			if (((CustRequestStatusDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
