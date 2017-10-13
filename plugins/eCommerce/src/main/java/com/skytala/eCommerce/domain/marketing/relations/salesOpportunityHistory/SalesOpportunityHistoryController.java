package com.skytala.eCommerce.domain.marketing.relations.salesOpportunityHistory;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Splitter;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunityHistory.command.AddSalesOpportunityHistory;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunityHistory.command.DeleteSalesOpportunityHistory;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunityHistory.command.UpdateSalesOpportunityHistory;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunityHistory.event.SalesOpportunityHistoryAdded;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunityHistory.event.SalesOpportunityHistoryDeleted;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunityHistory.event.SalesOpportunityHistoryFound;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunityHistory.event.SalesOpportunityHistoryUpdated;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunityHistory.mapper.SalesOpportunityHistoryMapper;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunityHistory.model.SalesOpportunityHistory;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunityHistory.query.FindSalesOpportunityHistorysBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/salesOpportunityHistorys")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findSalesOpportunityHistorysBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindSalesOpportunityHistorysBy query = new FindSalesOpportunityHistorysBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<SalesOpportunityHistory> salesOpportunityHistorys =((SalesOpportunityHistoryFound) Scheduler.execute(query).data()).getSalesOpportunityHistorys();

		if (salesOpportunityHistorys.size() == 1) {
			return ResponseEntity.ok().body(salesOpportunityHistorys.get(0));
		}

		return ResponseEntity.ok().body(salesOpportunityHistorys);

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
	public ResponseEntity<Object> createSalesOpportunityHistory(HttpServletRequest request) throws Exception {

		SalesOpportunityHistory salesOpportunityHistoryToBeAdded = new SalesOpportunityHistory();
		try {
			salesOpportunityHistoryToBeAdded = SalesOpportunityHistoryMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createSalesOpportunityHistory(salesOpportunityHistoryToBeAdded);

	}

	/**
	 * creates a new SalesOpportunityHistory entry in the ofbiz database
	 * 
	 * @param salesOpportunityHistoryToBeAdded
	 *            the SalesOpportunityHistory thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createSalesOpportunityHistory(@RequestBody SalesOpportunityHistory salesOpportunityHistoryToBeAdded) throws Exception {

		AddSalesOpportunityHistory command = new AddSalesOpportunityHistory(salesOpportunityHistoryToBeAdded);
		SalesOpportunityHistory salesOpportunityHistory = ((SalesOpportunityHistoryAdded) Scheduler.execute(command).data()).getAddedSalesOpportunityHistory();
		
		if (salesOpportunityHistory != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(salesOpportunityHistory);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("SalesOpportunityHistory could not be created.");
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
	public boolean updateSalesOpportunityHistory(HttpServletRequest request) throws Exception {

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

		SalesOpportunityHistory salesOpportunityHistoryToBeUpdated = new SalesOpportunityHistory();

		try {
			salesOpportunityHistoryToBeUpdated = SalesOpportunityHistoryMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateSalesOpportunityHistory(salesOpportunityHistoryToBeUpdated, salesOpportunityHistoryToBeUpdated.getSalesOpportunityHistoryId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateSalesOpportunityHistory(@RequestBody SalesOpportunityHistory salesOpportunityHistoryToBeUpdated,
			@PathVariable String salesOpportunityHistoryId) throws Exception {

		salesOpportunityHistoryToBeUpdated.setSalesOpportunityHistoryId(salesOpportunityHistoryId);

		UpdateSalesOpportunityHistory command = new UpdateSalesOpportunityHistory(salesOpportunityHistoryToBeUpdated);

		try {
			if(((SalesOpportunityHistoryUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{salesOpportunityHistoryId}")
	public ResponseEntity<Object> findById(@PathVariable String salesOpportunityHistoryId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("salesOpportunityHistoryId", salesOpportunityHistoryId);
		try {

			Object foundSalesOpportunityHistory = findSalesOpportunityHistorysBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundSalesOpportunityHistory);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{salesOpportunityHistoryId}")
	public ResponseEntity<Object> deleteSalesOpportunityHistoryByIdUpdated(@PathVariable String salesOpportunityHistoryId) throws Exception {
		DeleteSalesOpportunityHistory command = new DeleteSalesOpportunityHistory(salesOpportunityHistoryId);

		try {
			if (((SalesOpportunityHistoryDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("SalesOpportunityHistory could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/salesOpportunityHistory/\" plus one of the following: "
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