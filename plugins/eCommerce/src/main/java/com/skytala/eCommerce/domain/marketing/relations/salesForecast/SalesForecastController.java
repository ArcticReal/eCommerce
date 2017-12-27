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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/marketing/salesForecasts")
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
	@GetMapping("/find")
	public ResponseEntity<List<SalesForecast>> findSalesForecastsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindSalesForecastsBy query = new FindSalesForecastsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<SalesForecast> salesForecasts =((SalesForecastFound) Scheduler.execute(query).data()).getSalesForecasts();

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
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<SalesForecast> createSalesForecast(HttpServletRequest request) throws Exception {

		SalesForecast salesForecastToBeAdded = new SalesForecast();
		try {
			salesForecastToBeAdded = SalesForecastMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
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
	public ResponseEntity<SalesForecast> createSalesForecast(@RequestBody SalesForecast salesForecastToBeAdded) throws Exception {

		AddSalesForecast command = new AddSalesForecast(salesForecastToBeAdded);
		SalesForecast salesForecast = ((SalesForecastAdded) Scheduler.execute(command).data()).getAddedSalesForecast();
		
		if (salesForecast != null) 
			return successful(salesForecast);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateSalesForecast(@RequestBody SalesForecast salesForecastToBeUpdated,
			@PathVariable String salesForecastId) throws Exception {

		salesForecastToBeUpdated.setSalesForecastId(salesForecastId);

		UpdateSalesForecast command = new UpdateSalesForecast(salesForecastToBeUpdated);

		try {
			if(((SalesForecastUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{salesForecastId}")
	public ResponseEntity<SalesForecast> findById(@PathVariable String salesForecastId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("salesForecastId", salesForecastId);
		try {

			List<SalesForecast> foundSalesForecast = findSalesForecastsBy(requestParams).getBody();
			if(foundSalesForecast.size()==1){				return successful(foundSalesForecast.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{salesForecastId}")
	public ResponseEntity<String> deleteSalesForecastByIdUpdated(@PathVariable String salesForecastId) throws Exception {
		DeleteSalesForecast command = new DeleteSalesForecast(salesForecastId);

		try {
			if (((SalesForecastDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
