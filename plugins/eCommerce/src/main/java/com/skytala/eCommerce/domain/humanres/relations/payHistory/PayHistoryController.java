package com.skytala.eCommerce.domain.humanres.relations.payHistory;

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
import com.skytala.eCommerce.domain.humanres.relations.payHistory.command.AddPayHistory;
import com.skytala.eCommerce.domain.humanres.relations.payHistory.command.DeletePayHistory;
import com.skytala.eCommerce.domain.humanres.relations.payHistory.command.UpdatePayHistory;
import com.skytala.eCommerce.domain.humanres.relations.payHistory.event.PayHistoryAdded;
import com.skytala.eCommerce.domain.humanres.relations.payHistory.event.PayHistoryDeleted;
import com.skytala.eCommerce.domain.humanres.relations.payHistory.event.PayHistoryFound;
import com.skytala.eCommerce.domain.humanres.relations.payHistory.event.PayHistoryUpdated;
import com.skytala.eCommerce.domain.humanres.relations.payHistory.mapper.PayHistoryMapper;
import com.skytala.eCommerce.domain.humanres.relations.payHistory.model.PayHistory;
import com.skytala.eCommerce.domain.humanres.relations.payHistory.query.FindPayHistorysBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/humanres/payHistorys")
public class PayHistoryController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public PayHistoryController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a PayHistory
	 * @return a List with the PayHistorys
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<PayHistory>> findPayHistorysBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindPayHistorysBy query = new FindPayHistorysBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<PayHistory> payHistorys =((PayHistoryFound) Scheduler.execute(query).data()).getPayHistorys();

		return ResponseEntity.ok().body(payHistorys);

	}

	/**
	 * creates a new PayHistory entry in the ofbiz database
	 * 
	 * @param payHistoryToBeAdded
	 *            the PayHistory thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PayHistory> createPayHistory(@RequestBody PayHistory payHistoryToBeAdded) throws Exception {

		AddPayHistory command = new AddPayHistory(payHistoryToBeAdded);
		PayHistory payHistory = ((PayHistoryAdded) Scheduler.execute(command).data()).getAddedPayHistory();
		
		if (payHistory != null) 
			return successful(payHistory);
		else 
			return conflict(null);
	}

	/**
	 * Updates the PayHistory with the specific Id
	 * 
	 * @param payHistoryToBeUpdated
	 *            the PayHistory thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updatePayHistory(@RequestBody PayHistory payHistoryToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		payHistoryToBeUpdated.setnull(null);

		UpdatePayHistory command = new UpdatePayHistory(payHistoryToBeUpdated);

		try {
			if(((PayHistoryUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{payHistoryId}")
	public ResponseEntity<PayHistory> findById(@PathVariable String payHistoryId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("payHistoryId", payHistoryId);
		try {

			List<PayHistory> foundPayHistory = findPayHistorysBy(requestParams).getBody();
			if(foundPayHistory.size()==1){				return successful(foundPayHistory.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{payHistoryId}")
	public ResponseEntity<String> deletePayHistoryByIdUpdated(@PathVariable String payHistoryId) throws Exception {
		DeletePayHistory command = new DeletePayHistory(payHistoryId);

		try {
			if (((PayHistoryDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
