package com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.control.competitor;

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
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.command.competitor.AddSalesOpportunityCompetitor;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.command.competitor.DeleteSalesOpportunityCompetitor;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.command.competitor.UpdateSalesOpportunityCompetitor;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.event.competitor.SalesOpportunityCompetitorAdded;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.event.competitor.SalesOpportunityCompetitorDeleted;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.event.competitor.SalesOpportunityCompetitorFound;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.event.competitor.SalesOpportunityCompetitorUpdated;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.mapper.competitor.SalesOpportunityCompetitorMapper;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.model.competitor.SalesOpportunityCompetitor;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.query.competitor.FindSalesOpportunityCompetitorsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/marketing/salesOpportunity/salesOpportunityCompetitors")
public class SalesOpportunityCompetitorController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public SalesOpportunityCompetitorController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a SalesOpportunityCompetitor
	 * @return a List with the SalesOpportunityCompetitors
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<SalesOpportunityCompetitor>> findSalesOpportunityCompetitorsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindSalesOpportunityCompetitorsBy query = new FindSalesOpportunityCompetitorsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<SalesOpportunityCompetitor> salesOpportunityCompetitors =((SalesOpportunityCompetitorFound) Scheduler.execute(query).data()).getSalesOpportunityCompetitors();

		return ResponseEntity.ok().body(salesOpportunityCompetitors);

	}

	/**
	 * creates a new SalesOpportunityCompetitor entry in the ofbiz database
	 * 
	 * @param salesOpportunityCompetitorToBeAdded
	 *            the SalesOpportunityCompetitor thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<SalesOpportunityCompetitor> createSalesOpportunityCompetitor(@RequestBody SalesOpportunityCompetitor salesOpportunityCompetitorToBeAdded) throws Exception {

		AddSalesOpportunityCompetitor command = new AddSalesOpportunityCompetitor(salesOpportunityCompetitorToBeAdded);
		SalesOpportunityCompetitor salesOpportunityCompetitor = ((SalesOpportunityCompetitorAdded) Scheduler.execute(command).data()).getAddedSalesOpportunityCompetitor();
		
		if (salesOpportunityCompetitor != null) 
			return successful(salesOpportunityCompetitor);
		else 
			return conflict(null);
	}

	/**
	 * Updates the SalesOpportunityCompetitor with the specific Id
	 * 
	 * @param salesOpportunityCompetitorToBeUpdated
	 *            the SalesOpportunityCompetitor thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{competitorPartyId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateSalesOpportunityCompetitor(@RequestBody SalesOpportunityCompetitor salesOpportunityCompetitorToBeUpdated,
			@PathVariable String competitorPartyId) throws Exception {

		salesOpportunityCompetitorToBeUpdated.setCompetitorPartyId(competitorPartyId);

		UpdateSalesOpportunityCompetitor command = new UpdateSalesOpportunityCompetitor(salesOpportunityCompetitorToBeUpdated);

		try {
			if(((SalesOpportunityCompetitorUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{salesOpportunityCompetitorId}")
	public ResponseEntity<SalesOpportunityCompetitor> findById(@PathVariable String salesOpportunityCompetitorId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("salesOpportunityCompetitorId", salesOpportunityCompetitorId);
		try {

			List<SalesOpportunityCompetitor> foundSalesOpportunityCompetitor = findSalesOpportunityCompetitorsBy(requestParams).getBody();
			if(foundSalesOpportunityCompetitor.size()==1){				return successful(foundSalesOpportunityCompetitor.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{salesOpportunityCompetitorId}")
	public ResponseEntity<String> deleteSalesOpportunityCompetitorByIdUpdated(@PathVariable String salesOpportunityCompetitorId) throws Exception {
		DeleteSalesOpportunityCompetitor command = new DeleteSalesOpportunityCompetitor(salesOpportunityCompetitorId);

		try {
			if (((SalesOpportunityCompetitorDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
