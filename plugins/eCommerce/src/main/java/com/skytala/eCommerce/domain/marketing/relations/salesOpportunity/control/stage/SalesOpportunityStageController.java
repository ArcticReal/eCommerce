package com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.control.stage;

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
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.command.stage.AddSalesOpportunityStage;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.command.stage.DeleteSalesOpportunityStage;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.command.stage.UpdateSalesOpportunityStage;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.event.stage.SalesOpportunityStageAdded;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.event.stage.SalesOpportunityStageDeleted;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.event.stage.SalesOpportunityStageFound;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.event.stage.SalesOpportunityStageUpdated;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.mapper.stage.SalesOpportunityStageMapper;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.model.stage.SalesOpportunityStage;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.query.stage.FindSalesOpportunityStagesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/marketing/salesOpportunity/salesOpportunityStages")
public class SalesOpportunityStageController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public SalesOpportunityStageController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a SalesOpportunityStage
	 * @return a List with the SalesOpportunityStages
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<SalesOpportunityStage>> findSalesOpportunityStagesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindSalesOpportunityStagesBy query = new FindSalesOpportunityStagesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<SalesOpportunityStage> salesOpportunityStages =((SalesOpportunityStageFound) Scheduler.execute(query).data()).getSalesOpportunityStages();

		return ResponseEntity.ok().body(salesOpportunityStages);

	}

	/**
	 * creates a new SalesOpportunityStage entry in the ofbiz database
	 * 
	 * @param salesOpportunityStageToBeAdded
	 *            the SalesOpportunityStage thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<SalesOpportunityStage> createSalesOpportunityStage(@RequestBody SalesOpportunityStage salesOpportunityStageToBeAdded) throws Exception {

		AddSalesOpportunityStage command = new AddSalesOpportunityStage(salesOpportunityStageToBeAdded);
		SalesOpportunityStage salesOpportunityStage = ((SalesOpportunityStageAdded) Scheduler.execute(command).data()).getAddedSalesOpportunityStage();
		
		if (salesOpportunityStage != null) 
			return successful(salesOpportunityStage);
		else 
			return conflict(null);
	}

	/**
	 * Updates the SalesOpportunityStage with the specific Id
	 * 
	 * @param salesOpportunityStageToBeUpdated
	 *            the SalesOpportunityStage thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{opportunityStageId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateSalesOpportunityStage(@RequestBody SalesOpportunityStage salesOpportunityStageToBeUpdated,
			@PathVariable String opportunityStageId) throws Exception {

		salesOpportunityStageToBeUpdated.setOpportunityStageId(opportunityStageId);

		UpdateSalesOpportunityStage command = new UpdateSalesOpportunityStage(salesOpportunityStageToBeUpdated);

		try {
			if(((SalesOpportunityStageUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{salesOpportunityStageId}")
	public ResponseEntity<SalesOpportunityStage> findById(@PathVariable String salesOpportunityStageId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("salesOpportunityStageId", salesOpportunityStageId);
		try {

			List<SalesOpportunityStage> foundSalesOpportunityStage = findSalesOpportunityStagesBy(requestParams).getBody();
			if(foundSalesOpportunityStage.size()==1){				return successful(foundSalesOpportunityStage.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{salesOpportunityStageId}")
	public ResponseEntity<String> deleteSalesOpportunityStageByIdUpdated(@PathVariable String salesOpportunityStageId) throws Exception {
		DeleteSalesOpportunityStage command = new DeleteSalesOpportunityStage(salesOpportunityStageId);

		try {
			if (((SalesOpportunityStageDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
