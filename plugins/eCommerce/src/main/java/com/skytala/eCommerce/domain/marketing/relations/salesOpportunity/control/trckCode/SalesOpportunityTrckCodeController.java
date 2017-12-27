package com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.control.trckCode;

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
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.command.trckCode.AddSalesOpportunityTrckCode;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.command.trckCode.DeleteSalesOpportunityTrckCode;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.command.trckCode.UpdateSalesOpportunityTrckCode;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.event.trckCode.SalesOpportunityTrckCodeAdded;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.event.trckCode.SalesOpportunityTrckCodeDeleted;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.event.trckCode.SalesOpportunityTrckCodeFound;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.event.trckCode.SalesOpportunityTrckCodeUpdated;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.mapper.trckCode.SalesOpportunityTrckCodeMapper;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.model.trckCode.SalesOpportunityTrckCode;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.query.trckCode.FindSalesOpportunityTrckCodesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/marketing/salesOpportunity/salesOpportunityTrckCodes")
public class SalesOpportunityTrckCodeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public SalesOpportunityTrckCodeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a SalesOpportunityTrckCode
	 * @return a List with the SalesOpportunityTrckCodes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<SalesOpportunityTrckCode>> findSalesOpportunityTrckCodesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindSalesOpportunityTrckCodesBy query = new FindSalesOpportunityTrckCodesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<SalesOpportunityTrckCode> salesOpportunityTrckCodes =((SalesOpportunityTrckCodeFound) Scheduler.execute(query).data()).getSalesOpportunityTrckCodes();

		return ResponseEntity.ok().body(salesOpportunityTrckCodes);

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
	public ResponseEntity<SalesOpportunityTrckCode> createSalesOpportunityTrckCode(HttpServletRequest request) throws Exception {

		SalesOpportunityTrckCode salesOpportunityTrckCodeToBeAdded = new SalesOpportunityTrckCode();
		try {
			salesOpportunityTrckCodeToBeAdded = SalesOpportunityTrckCodeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createSalesOpportunityTrckCode(salesOpportunityTrckCodeToBeAdded);

	}

	/**
	 * creates a new SalesOpportunityTrckCode entry in the ofbiz database
	 * 
	 * @param salesOpportunityTrckCodeToBeAdded
	 *            the SalesOpportunityTrckCode thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<SalesOpportunityTrckCode> createSalesOpportunityTrckCode(@RequestBody SalesOpportunityTrckCode salesOpportunityTrckCodeToBeAdded) throws Exception {

		AddSalesOpportunityTrckCode command = new AddSalesOpportunityTrckCode(salesOpportunityTrckCodeToBeAdded);
		SalesOpportunityTrckCode salesOpportunityTrckCode = ((SalesOpportunityTrckCodeAdded) Scheduler.execute(command).data()).getAddedSalesOpportunityTrckCode();
		
		if (salesOpportunityTrckCode != null) 
			return successful(salesOpportunityTrckCode);
		else 
			return conflict(null);
	}

	/**
	 * Updates the SalesOpportunityTrckCode with the specific Id
	 * 
	 * @param salesOpportunityTrckCodeToBeUpdated
	 *            the SalesOpportunityTrckCode thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{trackingCodeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateSalesOpportunityTrckCode(@RequestBody SalesOpportunityTrckCode salesOpportunityTrckCodeToBeUpdated,
			@PathVariable String trackingCodeId) throws Exception {

		salesOpportunityTrckCodeToBeUpdated.setTrackingCodeId(trackingCodeId);

		UpdateSalesOpportunityTrckCode command = new UpdateSalesOpportunityTrckCode(salesOpportunityTrckCodeToBeUpdated);

		try {
			if(((SalesOpportunityTrckCodeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{salesOpportunityTrckCodeId}")
	public ResponseEntity<SalesOpportunityTrckCode> findById(@PathVariable String salesOpportunityTrckCodeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("salesOpportunityTrckCodeId", salesOpportunityTrckCodeId);
		try {

			List<SalesOpportunityTrckCode> foundSalesOpportunityTrckCode = findSalesOpportunityTrckCodesBy(requestParams).getBody();
			if(foundSalesOpportunityTrckCode.size()==1){				return successful(foundSalesOpportunityTrckCode.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{salesOpportunityTrckCodeId}")
	public ResponseEntity<String> deleteSalesOpportunityTrckCodeByIdUpdated(@PathVariable String salesOpportunityTrckCodeId) throws Exception {
		DeleteSalesOpportunityTrckCode command = new DeleteSalesOpportunityTrckCode(salesOpportunityTrckCodeId);

		try {
			if (((SalesOpportunityTrckCodeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
