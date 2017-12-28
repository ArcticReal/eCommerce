package com.skytala.eCommerce.domain.marketing.relations.salesOpportunity;

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
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.command.AddSalesOpportunity;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.command.DeleteSalesOpportunity;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.command.UpdateSalesOpportunity;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.event.SalesOpportunityAdded;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.event.SalesOpportunityDeleted;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.event.SalesOpportunityFound;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.event.SalesOpportunityUpdated;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.mapper.SalesOpportunityMapper;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.model.SalesOpportunity;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.query.FindSalesOpportunitysBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/marketing/salesOpportunitys")
public class SalesOpportunityController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public SalesOpportunityController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a SalesOpportunity
	 * @return a List with the SalesOpportunitys
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<SalesOpportunity>> findSalesOpportunitysBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindSalesOpportunitysBy query = new FindSalesOpportunitysBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<SalesOpportunity> salesOpportunitys =((SalesOpportunityFound) Scheduler.execute(query).data()).getSalesOpportunitys();

		return ResponseEntity.ok().body(salesOpportunitys);

	}

	/**
	 * creates a new SalesOpportunity entry in the ofbiz database
	 * 
	 * @param salesOpportunityToBeAdded
	 *            the SalesOpportunity thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<SalesOpportunity> createSalesOpportunity(@RequestBody SalesOpportunity salesOpportunityToBeAdded) throws Exception {

		AddSalesOpportunity command = new AddSalesOpportunity(salesOpportunityToBeAdded);
		SalesOpportunity salesOpportunity = ((SalesOpportunityAdded) Scheduler.execute(command).data()).getAddedSalesOpportunity();
		
		if (salesOpportunity != null) 
			return successful(salesOpportunity);
		else 
			return conflict(null);
	}

	/**
	 * Updates the SalesOpportunity with the specific Id
	 * 
	 * @param salesOpportunityToBeUpdated
	 *            the SalesOpportunity thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{salesOpportunityId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateSalesOpportunity(@RequestBody SalesOpportunity salesOpportunityToBeUpdated,
			@PathVariable String salesOpportunityId) throws Exception {

		salesOpportunityToBeUpdated.setSalesOpportunityId(salesOpportunityId);

		UpdateSalesOpportunity command = new UpdateSalesOpportunity(salesOpportunityToBeUpdated);

		try {
			if(((SalesOpportunityUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{salesOpportunityId}")
	public ResponseEntity<SalesOpportunity> findById(@PathVariable String salesOpportunityId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("salesOpportunityId", salesOpportunityId);
		try {

			List<SalesOpportunity> foundSalesOpportunity = findSalesOpportunitysBy(requestParams).getBody();
			if(foundSalesOpportunity.size()==1){				return successful(foundSalesOpportunity.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{salesOpportunityId}")
	public ResponseEntity<String> deleteSalesOpportunityByIdUpdated(@PathVariable String salesOpportunityId) throws Exception {
		DeleteSalesOpportunity command = new DeleteSalesOpportunity(salesOpportunityId);

		try {
			if (((SalesOpportunityDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
