package com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.control.workEffort;

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
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.command.workEffort.AddSalesOpportunityWorkEffort;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.command.workEffort.DeleteSalesOpportunityWorkEffort;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.command.workEffort.UpdateSalesOpportunityWorkEffort;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.event.workEffort.SalesOpportunityWorkEffortAdded;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.event.workEffort.SalesOpportunityWorkEffortDeleted;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.event.workEffort.SalesOpportunityWorkEffortFound;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.event.workEffort.SalesOpportunityWorkEffortUpdated;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.mapper.workEffort.SalesOpportunityWorkEffortMapper;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.model.workEffort.SalesOpportunityWorkEffort;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.query.workEffort.FindSalesOpportunityWorkEffortsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/marketing/salesOpportunity/salesOpportunityWorkEfforts")
public class SalesOpportunityWorkEffortController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public SalesOpportunityWorkEffortController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a SalesOpportunityWorkEffort
	 * @return a List with the SalesOpportunityWorkEfforts
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<SalesOpportunityWorkEffort>> findSalesOpportunityWorkEffortsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindSalesOpportunityWorkEffortsBy query = new FindSalesOpportunityWorkEffortsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<SalesOpportunityWorkEffort> salesOpportunityWorkEfforts =((SalesOpportunityWorkEffortFound) Scheduler.execute(query).data()).getSalesOpportunityWorkEfforts();

		return ResponseEntity.ok().body(salesOpportunityWorkEfforts);

	}

	/**
	 * creates a new SalesOpportunityWorkEffort entry in the ofbiz database
	 * 
	 * @param salesOpportunityWorkEffortToBeAdded
	 *            the SalesOpportunityWorkEffort thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<SalesOpportunityWorkEffort> createSalesOpportunityWorkEffort(@RequestBody SalesOpportunityWorkEffort salesOpportunityWorkEffortToBeAdded) throws Exception {

		AddSalesOpportunityWorkEffort command = new AddSalesOpportunityWorkEffort(salesOpportunityWorkEffortToBeAdded);
		SalesOpportunityWorkEffort salesOpportunityWorkEffort = ((SalesOpportunityWorkEffortAdded) Scheduler.execute(command).data()).getAddedSalesOpportunityWorkEffort();
		
		if (salesOpportunityWorkEffort != null) 
			return successful(salesOpportunityWorkEffort);
		else 
			return conflict(null);
	}

	/**
	 * Updates the SalesOpportunityWorkEffort with the specific Id
	 * 
	 * @param salesOpportunityWorkEffortToBeUpdated
	 *            the SalesOpportunityWorkEffort thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateSalesOpportunityWorkEffort(@RequestBody SalesOpportunityWorkEffort salesOpportunityWorkEffortToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		salesOpportunityWorkEffortToBeUpdated.setnull(null);

		UpdateSalesOpportunityWorkEffort command = new UpdateSalesOpportunityWorkEffort(salesOpportunityWorkEffortToBeUpdated);

		try {
			if(((SalesOpportunityWorkEffortUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{salesOpportunityWorkEffortId}")
	public ResponseEntity<SalesOpportunityWorkEffort> findById(@PathVariable String salesOpportunityWorkEffortId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("salesOpportunityWorkEffortId", salesOpportunityWorkEffortId);
		try {

			List<SalesOpportunityWorkEffort> foundSalesOpportunityWorkEffort = findSalesOpportunityWorkEffortsBy(requestParams).getBody();
			if(foundSalesOpportunityWorkEffort.size()==1){				return successful(foundSalesOpportunityWorkEffort.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{salesOpportunityWorkEffortId}")
	public ResponseEntity<String> deleteSalesOpportunityWorkEffortByIdUpdated(@PathVariable String salesOpportunityWorkEffortId) throws Exception {
		DeleteSalesOpportunityWorkEffort command = new DeleteSalesOpportunityWorkEffort(salesOpportunityWorkEffortId);

		try {
			if (((SalesOpportunityWorkEffortDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
