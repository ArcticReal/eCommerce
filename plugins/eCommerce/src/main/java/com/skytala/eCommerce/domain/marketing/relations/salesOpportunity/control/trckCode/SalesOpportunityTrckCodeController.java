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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@RequestMapping("/salesOpportunityTrckCodes")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findSalesOpportunityTrckCodesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindSalesOpportunityTrckCodesBy query = new FindSalesOpportunityTrckCodesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<SalesOpportunityTrckCode> salesOpportunityTrckCodes =((SalesOpportunityTrckCodeFound) Scheduler.execute(query).data()).getSalesOpportunityTrckCodes();

		if (salesOpportunityTrckCodes.size() == 1) {
			return ResponseEntity.ok().body(salesOpportunityTrckCodes.get(0));
		}

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
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createSalesOpportunityTrckCode(HttpServletRequest request) throws Exception {

		SalesOpportunityTrckCode salesOpportunityTrckCodeToBeAdded = new SalesOpportunityTrckCode();
		try {
			salesOpportunityTrckCodeToBeAdded = SalesOpportunityTrckCodeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
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
	public ResponseEntity<Object> createSalesOpportunityTrckCode(@RequestBody SalesOpportunityTrckCode salesOpportunityTrckCodeToBeAdded) throws Exception {

		AddSalesOpportunityTrckCode command = new AddSalesOpportunityTrckCode(salesOpportunityTrckCodeToBeAdded);
		SalesOpportunityTrckCode salesOpportunityTrckCode = ((SalesOpportunityTrckCodeAdded) Scheduler.execute(command).data()).getAddedSalesOpportunityTrckCode();
		
		if (salesOpportunityTrckCode != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(salesOpportunityTrckCode);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("SalesOpportunityTrckCode could not be created.");
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
	public boolean updateSalesOpportunityTrckCode(HttpServletRequest request) throws Exception {

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

		SalesOpportunityTrckCode salesOpportunityTrckCodeToBeUpdated = new SalesOpportunityTrckCode();

		try {
			salesOpportunityTrckCodeToBeUpdated = SalesOpportunityTrckCodeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateSalesOpportunityTrckCode(salesOpportunityTrckCodeToBeUpdated, salesOpportunityTrckCodeToBeUpdated.getTrackingCodeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateSalesOpportunityTrckCode(@RequestBody SalesOpportunityTrckCode salesOpportunityTrckCodeToBeUpdated,
			@PathVariable String trackingCodeId) throws Exception {

		salesOpportunityTrckCodeToBeUpdated.setTrackingCodeId(trackingCodeId);

		UpdateSalesOpportunityTrckCode command = new UpdateSalesOpportunityTrckCode(salesOpportunityTrckCodeToBeUpdated);

		try {
			if(((SalesOpportunityTrckCodeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{salesOpportunityTrckCodeId}")
	public ResponseEntity<Object> findById(@PathVariable String salesOpportunityTrckCodeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("salesOpportunityTrckCodeId", salesOpportunityTrckCodeId);
		try {

			Object foundSalesOpportunityTrckCode = findSalesOpportunityTrckCodesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundSalesOpportunityTrckCode);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{salesOpportunityTrckCodeId}")
	public ResponseEntity<Object> deleteSalesOpportunityTrckCodeByIdUpdated(@PathVariable String salesOpportunityTrckCodeId) throws Exception {
		DeleteSalesOpportunityTrckCode command = new DeleteSalesOpportunityTrckCode(salesOpportunityTrckCodeId);

		try {
			if (((SalesOpportunityTrckCodeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("SalesOpportunityTrckCode could not be deleted");

	}

	@RequestMapping(value = (" ** "))
	public ResponseEntity<Object> returnErrorPage(HttpServletRequest request) {

		String usedUri = request.getRequestURI();
		String[] splittedString = usedUri.split("/");

		String usedRequest = splittedString[splittedString.length - 1];

		if (validRequests.containsKey(usedRequest)) {
			String returnVal = "Error: request method " + request.getMethod() + " not allowed for \"" + usedUri
					+ "\"!\n" + "Please use " + validRequests.get(usedRequest) + "!";

			return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(returnVal);
		}

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/salesOpportunityTrckCode/\" plus one of the following: "
				+ "";

		Set<String> keySet = validRequests.keySet();
		Iterator<String> it = keySet.iterator();

		while (it.hasNext()) {
			returnVal += "\"" + it.next() + "\"";
			if (it.hasNext())
				returnVal += ", ";
		}

		returnVal += "!";

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnVal);

	}
}
