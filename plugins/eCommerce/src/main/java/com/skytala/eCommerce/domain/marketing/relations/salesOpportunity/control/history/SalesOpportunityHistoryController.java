package com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.control.history;

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
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.command.history.AddSalesOpportunityHistory;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.command.history.DeleteSalesOpportunityHistory;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.command.history.UpdateSalesOpportunityHistory;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.event.history.SalesOpportunityHistoryAdded;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.event.history.SalesOpportunityHistoryDeleted;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.event.history.SalesOpportunityHistoryFound;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.event.history.SalesOpportunityHistoryUpdated;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.mapper.history.SalesOpportunityHistoryMapper;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.model.history.SalesOpportunityHistory;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.query.history.FindSalesOpportunityHistorysBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/marketing/salesOpportunity/salesOpportunityHistorys")
public class SalesOpportunityHistoryController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public SalesOpportunityHistoryController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a SalesOpportunityHistory
	 * @return a List with the SalesOpportunityHistorys
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<SalesOpportunityHistory>> findSalesOpportunityHistorysBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindSalesOpportunityHistorysBy query = new FindSalesOpportunityHistorysBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<SalesOpportunityHistory> salesOpportunityHistorys =((SalesOpportunityHistoryFound) Scheduler.execute(query).data()).getSalesOpportunityHistorys();

		return ResponseEntity.ok().body(salesOpportunityHistorys);

	}

	/**
	 * creates a new SalesOpportunityHistory entry in the ofbiz database
	 * 
	 * @param salesOpportunityHistoryToBeAdded
	 *            the SalesOpportunityHistory thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<SalesOpportunityHistory> createSalesOpportunityHistory(@RequestBody SalesOpportunityHistory salesOpportunityHistoryToBeAdded) throws Exception {

		AddSalesOpportunityHistory command = new AddSalesOpportunityHistory(salesOpportunityHistoryToBeAdded);
		SalesOpportunityHistory salesOpportunityHistory = ((SalesOpportunityHistoryAdded) Scheduler.execute(command).data()).getAddedSalesOpportunityHistory();
		
		if (salesOpportunityHistory != null) 
			return successful(salesOpportunityHistory);
		else 
			return conflict(null);
	}

	/**
	 * Updates the SalesOpportunityHistory with the specific Id
	 * 
	 * @param salesOpportunityHistoryToBeUpdated
	 *            the SalesOpportunityHistory thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{salesOpportunityHistoryId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateSalesOpportunityHistory(@RequestBody SalesOpportunityHistory salesOpportunityHistoryToBeUpdated,
			@PathVariable String salesOpportunityHistoryId) throws Exception {

		salesOpportunityHistoryToBeUpdated.setSalesOpportunityHistoryId(salesOpportunityHistoryId);

		UpdateSalesOpportunityHistory command = new UpdateSalesOpportunityHistory(salesOpportunityHistoryToBeUpdated);

		try {
			if(((SalesOpportunityHistoryUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{salesOpportunityHistoryId}")
	public ResponseEntity<SalesOpportunityHistory> findById(@PathVariable String salesOpportunityHistoryId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("salesOpportunityHistoryId", salesOpportunityHistoryId);
		try {

			List<SalesOpportunityHistory> foundSalesOpportunityHistory = findSalesOpportunityHistorysBy(requestParams).getBody();
			if(foundSalesOpportunityHistory.size()==1){				return successful(foundSalesOpportunityHistory.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{salesOpportunityHistoryId}")
	public ResponseEntity<String> deleteSalesOpportunityHistoryByIdUpdated(@PathVariable String salesOpportunityHistoryId) throws Exception {
		DeleteSalesOpportunityHistory command = new DeleteSalesOpportunityHistory(salesOpportunityHistoryId);

		try {
			if (((SalesOpportunityHistoryDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
