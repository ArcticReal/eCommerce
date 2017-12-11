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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findSalesOpportunityStagesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindSalesOpportunityStagesBy query = new FindSalesOpportunityStagesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<SalesOpportunityStage> salesOpportunityStages =((SalesOpportunityStageFound) Scheduler.execute(query).data()).getSalesOpportunityStages();

		if (salesOpportunityStages.size() == 1) {
			return ResponseEntity.ok().body(salesOpportunityStages.get(0));
		}

		return ResponseEntity.ok().body(salesOpportunityStages);

	}

	/**
	 * 
	 * this method will only be called by Springs DispatcherServlet
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createSalesOpportunityStage(HttpServletRequest request) throws Exception {

		SalesOpportunityStage salesOpportunityStageToBeAdded = new SalesOpportunityStage();
		try {
			salesOpportunityStageToBeAdded = SalesOpportunityStageMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createSalesOpportunityStage(salesOpportunityStageToBeAdded);

	}

	/**
	 * creates a new SalesOpportunityStage entry in the ofbiz database
	 * 
	 * @param salesOpportunityStageToBeAdded
	 *            the SalesOpportunityStage thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createSalesOpportunityStage(@RequestBody SalesOpportunityStage salesOpportunityStageToBeAdded) throws Exception {

		AddSalesOpportunityStage command = new AddSalesOpportunityStage(salesOpportunityStageToBeAdded);
		SalesOpportunityStage salesOpportunityStage = ((SalesOpportunityStageAdded) Scheduler.execute(command).data()).getAddedSalesOpportunityStage();
		
		if (salesOpportunityStage != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(salesOpportunityStage);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("SalesOpportunityStage could not be created.");
	}

	/**
	 * this method will only be called by Springs DispatcherServlet
	 * 
	 * @deprecated
	 * @param request
	 *            HttpServletRequest object
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/update", consumes = "application/x-www-form-urlencoded")
	public boolean updateSalesOpportunityStage(HttpServletRequest request) throws Exception {

		BufferedReader br;
		String data = null;
		Map<String, String> dataMap = null;

		try {
			br = new BufferedReader(new InputStreamReader(request.getInputStream()));
			if (br != null) {
				data = java.net.URLDecoder.decode(br.readLine(), "UTF-8");
			}
		} catch (IOException e1) {
			e1.printStackTrace();
			return false;
		}

		dataMap = Splitter.on('&').trimResults().withKeyValueSeparator(Splitter.on('=').limit(2).trimResults())
				.split(data);

		SalesOpportunityStage salesOpportunityStageToBeUpdated = new SalesOpportunityStage();

		try {
			salesOpportunityStageToBeUpdated = SalesOpportunityStageMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateSalesOpportunityStage(salesOpportunityStageToBeUpdated, salesOpportunityStageToBeUpdated.getOpportunityStageId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateSalesOpportunityStage(@RequestBody SalesOpportunityStage salesOpportunityStageToBeUpdated,
			@PathVariable String opportunityStageId) throws Exception {

		salesOpportunityStageToBeUpdated.setOpportunityStageId(opportunityStageId);

		UpdateSalesOpportunityStage command = new UpdateSalesOpportunityStage(salesOpportunityStageToBeUpdated);

		try {
			if(((SalesOpportunityStageUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{salesOpportunityStageId}")
	public ResponseEntity<Object> findById(@PathVariable String salesOpportunityStageId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("salesOpportunityStageId", salesOpportunityStageId);
		try {

			Object foundSalesOpportunityStage = findSalesOpportunityStagesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundSalesOpportunityStage);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{salesOpportunityStageId}")
	public ResponseEntity<Object> deleteSalesOpportunityStageByIdUpdated(@PathVariable String salesOpportunityStageId) throws Exception {
		DeleteSalesOpportunityStage command = new DeleteSalesOpportunityStage(salesOpportunityStageId);

		try {
			if (((SalesOpportunityStageDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("SalesOpportunityStage could not be deleted");

	}

}
