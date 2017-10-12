package com.skytala.eCommerce.domain.marketing.relations.salesForecastHistory;

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
import com.skytala.eCommerce.domain.marketing.relations.salesForecastHistory.command.AddSalesForecastHistory;
import com.skytala.eCommerce.domain.marketing.relations.salesForecastHistory.command.DeleteSalesForecastHistory;
import com.skytala.eCommerce.domain.marketing.relations.salesForecastHistory.command.UpdateSalesForecastHistory;
import com.skytala.eCommerce.domain.marketing.relations.salesForecastHistory.event.SalesForecastHistoryAdded;
import com.skytala.eCommerce.domain.marketing.relations.salesForecastHistory.event.SalesForecastHistoryDeleted;
import com.skytala.eCommerce.domain.marketing.relations.salesForecastHistory.event.SalesForecastHistoryFound;
import com.skytala.eCommerce.domain.marketing.relations.salesForecastHistory.event.SalesForecastHistoryUpdated;
import com.skytala.eCommerce.domain.marketing.relations.salesForecastHistory.mapper.SalesForecastHistoryMapper;
import com.skytala.eCommerce.domain.marketing.relations.salesForecastHistory.model.SalesForecastHistory;
import com.skytala.eCommerce.domain.marketing.relations.salesForecastHistory.query.FindSalesForecastHistorysBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/salesForecastHistorys")
public class SalesForecastHistoryController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public SalesForecastHistoryController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a SalesForecastHistory
	 * @return a List with the SalesForecastHistorys
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findSalesForecastHistorysBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindSalesForecastHistorysBy query = new FindSalesForecastHistorysBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<SalesForecastHistory> salesForecastHistorys =((SalesForecastHistoryFound) Scheduler.execute(query).data()).getSalesForecastHistorys();

		if (salesForecastHistorys.size() == 1) {
			return ResponseEntity.ok().body(salesForecastHistorys.get(0));
		}

		return ResponseEntity.ok().body(salesForecastHistorys);

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
	public ResponseEntity<Object> createSalesForecastHistory(HttpServletRequest request) throws Exception {

		SalesForecastHistory salesForecastHistoryToBeAdded = new SalesForecastHistory();
		try {
			salesForecastHistoryToBeAdded = SalesForecastHistoryMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createSalesForecastHistory(salesForecastHistoryToBeAdded);

	}

	/**
	 * creates a new SalesForecastHistory entry in the ofbiz database
	 * 
	 * @param salesForecastHistoryToBeAdded
	 *            the SalesForecastHistory thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createSalesForecastHistory(@RequestBody SalesForecastHistory salesForecastHistoryToBeAdded) throws Exception {

		AddSalesForecastHistory command = new AddSalesForecastHistory(salesForecastHistoryToBeAdded);
		SalesForecastHistory salesForecastHistory = ((SalesForecastHistoryAdded) Scheduler.execute(command).data()).getAddedSalesForecastHistory();
		
		if (salesForecastHistory != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(salesForecastHistory);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("SalesForecastHistory could not be created.");
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
	public boolean updateSalesForecastHistory(HttpServletRequest request) throws Exception {

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

		SalesForecastHistory salesForecastHistoryToBeUpdated = new SalesForecastHistory();

		try {
			salesForecastHistoryToBeUpdated = SalesForecastHistoryMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateSalesForecastHistory(salesForecastHistoryToBeUpdated, salesForecastHistoryToBeUpdated.getSalesForecastHistoryId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the SalesForecastHistory with the specific Id
	 * 
	 * @param salesForecastHistoryToBeUpdated
	 *            the SalesForecastHistory thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{salesForecastHistoryId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateSalesForecastHistory(@RequestBody SalesForecastHistory salesForecastHistoryToBeUpdated,
			@PathVariable String salesForecastHistoryId) throws Exception {

		salesForecastHistoryToBeUpdated.setSalesForecastHistoryId(salesForecastHistoryId);

		UpdateSalesForecastHistory command = new UpdateSalesForecastHistory(salesForecastHistoryToBeUpdated);

		try {
			if(((SalesForecastHistoryUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{salesForecastHistoryId}")
	public ResponseEntity<Object> findById(@PathVariable String salesForecastHistoryId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("salesForecastHistoryId", salesForecastHistoryId);
		try {

			Object foundSalesForecastHistory = findSalesForecastHistorysBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundSalesForecastHistory);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{salesForecastHistoryId}")
	public ResponseEntity<Object> deleteSalesForecastHistoryByIdUpdated(@PathVariable String salesForecastHistoryId) throws Exception {
		DeleteSalesForecastHistory command = new DeleteSalesForecastHistory(salesForecastHistoryId);

		try {
			if (((SalesForecastHistoryDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("SalesForecastHistory could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/salesForecastHistory/\" plus one of the following: "
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
