package com.skytala.eCommerce.domain.marketing.relations.salesForecast;

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
import com.skytala.eCommerce.domain.marketing.relations.salesForecast.command.AddSalesForecast;
import com.skytala.eCommerce.domain.marketing.relations.salesForecast.command.DeleteSalesForecast;
import com.skytala.eCommerce.domain.marketing.relations.salesForecast.command.UpdateSalesForecast;
import com.skytala.eCommerce.domain.marketing.relations.salesForecast.event.SalesForecastAdded;
import com.skytala.eCommerce.domain.marketing.relations.salesForecast.event.SalesForecastDeleted;
import com.skytala.eCommerce.domain.marketing.relations.salesForecast.event.SalesForecastFound;
import com.skytala.eCommerce.domain.marketing.relations.salesForecast.event.SalesForecastUpdated;
import com.skytala.eCommerce.domain.marketing.relations.salesForecast.mapper.SalesForecastMapper;
import com.skytala.eCommerce.domain.marketing.relations.salesForecast.model.SalesForecast;
import com.skytala.eCommerce.domain.marketing.relations.salesForecast.query.FindSalesForecastsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/salesForecasts")
public class SalesForecastController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public SalesForecastController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a SalesForecast
	 * @return a List with the SalesForecasts
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findSalesForecastsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindSalesForecastsBy query = new FindSalesForecastsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<SalesForecast> salesForecasts =((SalesForecastFound) Scheduler.execute(query).data()).getSalesForecasts();

		if (salesForecasts.size() == 1) {
			return ResponseEntity.ok().body(salesForecasts.get(0));
		}

		return ResponseEntity.ok().body(salesForecasts);

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
	public ResponseEntity<Object> createSalesForecast(HttpServletRequest request) throws Exception {

		SalesForecast salesForecastToBeAdded = new SalesForecast();
		try {
			salesForecastToBeAdded = SalesForecastMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createSalesForecast(salesForecastToBeAdded);

	}

	/**
	 * creates a new SalesForecast entry in the ofbiz database
	 * 
	 * @param salesForecastToBeAdded
	 *            the SalesForecast thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createSalesForecast(@RequestBody SalesForecast salesForecastToBeAdded) throws Exception {

		AddSalesForecast command = new AddSalesForecast(salesForecastToBeAdded);
		SalesForecast salesForecast = ((SalesForecastAdded) Scheduler.execute(command).data()).getAddedSalesForecast();
		
		if (salesForecast != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(salesForecast);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("SalesForecast could not be created.");
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
	public boolean updateSalesForecast(HttpServletRequest request) throws Exception {

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

		SalesForecast salesForecastToBeUpdated = new SalesForecast();

		try {
			salesForecastToBeUpdated = SalesForecastMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateSalesForecast(salesForecastToBeUpdated, salesForecastToBeUpdated.getSalesForecastId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the SalesForecast with the specific Id
	 * 
	 * @param salesForecastToBeUpdated
	 *            the SalesForecast thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{salesForecastId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateSalesForecast(@RequestBody SalesForecast salesForecastToBeUpdated,
			@PathVariable String salesForecastId) throws Exception {

		salesForecastToBeUpdated.setSalesForecastId(salesForecastId);

		UpdateSalesForecast command = new UpdateSalesForecast(salesForecastToBeUpdated);

		try {
			if(((SalesForecastUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{salesForecastId}")
	public ResponseEntity<Object> findById(@PathVariable String salesForecastId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("salesForecastId", salesForecastId);
		try {

			Object foundSalesForecast = findSalesForecastsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundSalesForecast);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{salesForecastId}")
	public ResponseEntity<Object> deleteSalesForecastByIdUpdated(@PathVariable String salesForecastId) throws Exception {
		DeleteSalesForecast command = new DeleteSalesForecast(salesForecastId);

		try {
			if (((SalesForecastDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("SalesForecast could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/salesForecast/\" plus one of the following: "
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
