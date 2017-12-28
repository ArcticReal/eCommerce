package com.skytala.eCommerce.domain.accounting.relations.finAccount.control.status;

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
import com.skytala.eCommerce.domain.accounting.relations.finAccount.command.status.AddFinAccountStatus;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.command.status.DeleteFinAccountStatus;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.command.status.UpdateFinAccountStatus;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.event.status.FinAccountStatusAdded;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.event.status.FinAccountStatusDeleted;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.event.status.FinAccountStatusFound;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.event.status.FinAccountStatusUpdated;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.mapper.status.FinAccountStatusMapper;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.model.status.FinAccountStatus;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.query.status.FindFinAccountStatussBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/finAccount/finAccountStatuss")
public class FinAccountStatusController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public FinAccountStatusController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a FinAccountStatus
	 * @return a List with the FinAccountStatuss
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<FinAccountStatus>> findFinAccountStatussBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindFinAccountStatussBy query = new FindFinAccountStatussBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<FinAccountStatus> finAccountStatuss =((FinAccountStatusFound) Scheduler.execute(query).data()).getFinAccountStatuss();

		return ResponseEntity.ok().body(finAccountStatuss);

	}

	/**
	 * creates a new FinAccountStatus entry in the ofbiz database
	 * 
	 * @param finAccountStatusToBeAdded
	 *            the FinAccountStatus thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<FinAccountStatus> createFinAccountStatus(@RequestBody FinAccountStatus finAccountStatusToBeAdded) throws Exception {

		AddFinAccountStatus command = new AddFinAccountStatus(finAccountStatusToBeAdded);
		FinAccountStatus finAccountStatus = ((FinAccountStatusAdded) Scheduler.execute(command).data()).getAddedFinAccountStatus();
		
		if (finAccountStatus != null) 
			return successful(finAccountStatus);
		else 
			return conflict(null);
	}

	/**
	 * Updates the FinAccountStatus with the specific Id
	 * 
	 * @param finAccountStatusToBeUpdated
	 *            the FinAccountStatus thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateFinAccountStatus(@RequestBody FinAccountStatus finAccountStatusToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		finAccountStatusToBeUpdated.setnull(null);

		UpdateFinAccountStatus command = new UpdateFinAccountStatus(finAccountStatusToBeUpdated);

		try {
			if(((FinAccountStatusUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{finAccountStatusId}")
	public ResponseEntity<FinAccountStatus> findById(@PathVariable String finAccountStatusId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("finAccountStatusId", finAccountStatusId);
		try {

			List<FinAccountStatus> foundFinAccountStatus = findFinAccountStatussBy(requestParams).getBody();
			if(foundFinAccountStatus.size()==1){				return successful(foundFinAccountStatus.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{finAccountStatusId}")
	public ResponseEntity<String> deleteFinAccountStatusByIdUpdated(@PathVariable String finAccountStatusId) throws Exception {
		DeleteFinAccountStatus command = new DeleteFinAccountStatus(finAccountStatusId);

		try {
			if (((FinAccountStatusDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
