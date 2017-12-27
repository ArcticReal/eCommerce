package com.skytala.eCommerce.domain.order.relations.returnStatus;

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
import com.skytala.eCommerce.domain.order.relations.returnStatus.command.AddReturnStatus;
import com.skytala.eCommerce.domain.order.relations.returnStatus.command.DeleteReturnStatus;
import com.skytala.eCommerce.domain.order.relations.returnStatus.command.UpdateReturnStatus;
import com.skytala.eCommerce.domain.order.relations.returnStatus.event.ReturnStatusAdded;
import com.skytala.eCommerce.domain.order.relations.returnStatus.event.ReturnStatusDeleted;
import com.skytala.eCommerce.domain.order.relations.returnStatus.event.ReturnStatusFound;
import com.skytala.eCommerce.domain.order.relations.returnStatus.event.ReturnStatusUpdated;
import com.skytala.eCommerce.domain.order.relations.returnStatus.mapper.ReturnStatusMapper;
import com.skytala.eCommerce.domain.order.relations.returnStatus.model.ReturnStatus;
import com.skytala.eCommerce.domain.order.relations.returnStatus.query.FindReturnStatussBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/order/returnStatuss")
public class ReturnStatusController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ReturnStatusController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ReturnStatus
	 * @return a List with the ReturnStatuss
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ReturnStatus>> findReturnStatussBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindReturnStatussBy query = new FindReturnStatussBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ReturnStatus> returnStatuss =((ReturnStatusFound) Scheduler.execute(query).data()).getReturnStatuss();

		return ResponseEntity.ok().body(returnStatuss);

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
	public ResponseEntity<ReturnStatus> createReturnStatus(HttpServletRequest request) throws Exception {

		ReturnStatus returnStatusToBeAdded = new ReturnStatus();
		try {
			returnStatusToBeAdded = ReturnStatusMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createReturnStatus(returnStatusToBeAdded);

	}

	/**
	 * creates a new ReturnStatus entry in the ofbiz database
	 * 
	 * @param returnStatusToBeAdded
	 *            the ReturnStatus thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ReturnStatus> createReturnStatus(@RequestBody ReturnStatus returnStatusToBeAdded) throws Exception {

		AddReturnStatus command = new AddReturnStatus(returnStatusToBeAdded);
		ReturnStatus returnStatus = ((ReturnStatusAdded) Scheduler.execute(command).data()).getAddedReturnStatus();
		
		if (returnStatus != null) 
			return successful(returnStatus);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ReturnStatus with the specific Id
	 * 
	 * @param returnStatusToBeUpdated
	 *            the ReturnStatus thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{returnStatusId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateReturnStatus(@RequestBody ReturnStatus returnStatusToBeUpdated,
			@PathVariable String returnStatusId) throws Exception {

		returnStatusToBeUpdated.setReturnStatusId(returnStatusId);

		UpdateReturnStatus command = new UpdateReturnStatus(returnStatusToBeUpdated);

		try {
			if(((ReturnStatusUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{returnStatusId}")
	public ResponseEntity<ReturnStatus> findById(@PathVariable String returnStatusId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("returnStatusId", returnStatusId);
		try {

			List<ReturnStatus> foundReturnStatus = findReturnStatussBy(requestParams).getBody();
			if(foundReturnStatus.size()==1){				return successful(foundReturnStatus.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{returnStatusId}")
	public ResponseEntity<String> deleteReturnStatusByIdUpdated(@PathVariable String returnStatusId) throws Exception {
		DeleteReturnStatus command = new DeleteReturnStatus(returnStatusId);

		try {
			if (((ReturnStatusDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
