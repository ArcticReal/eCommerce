package com.skytala.eCommerce.domain.marketing.relations.salesForecast.control.history;

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
import com.skytala.eCommerce.domain.marketing.relations.salesForecast.command.history.AddSalesForecastHistory;
import com.skytala.eCommerce.domain.marketing.relations.salesForecast.command.history.DeleteSalesForecastHistory;
import com.skytala.eCommerce.domain.marketing.relations.salesForecast.command.history.UpdateSalesForecastHistory;
import com.skytala.eCommerce.domain.marketing.relations.salesForecast.event.history.SalesForecastHistoryAdded;
import com.skytala.eCommerce.domain.marketing.relations.salesForecast.event.history.SalesForecastHistoryDeleted;
import com.skytala.eCommerce.domain.marketing.relations.salesForecast.event.history.SalesForecastHistoryFound;
import com.skytala.eCommerce.domain.marketing.relations.salesForecast.event.history.SalesForecastHistoryUpdated;
import com.skytala.eCommerce.domain.marketing.relations.salesForecast.mapper.history.SalesForecastHistoryMapper;
import com.skytala.eCommerce.domain.marketing.relations.salesForecast.model.history.SalesForecastHistory;
import com.skytala.eCommerce.domain.marketing.relations.salesForecast.query.history.FindSalesForecastHistorysBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/marketing/salesForecast/salesForecastHistorys")
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
	@GetMapping("/find")
	public ResponseEntity<List<SalesForecastHistory>> findSalesForecastHistorysBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindSalesForecastHistorysBy query = new FindSalesForecastHistorysBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<SalesForecastHistory> salesForecastHistorys =((SalesForecastHistoryFound) Scheduler.execute(query).data()).getSalesForecastHistorys();

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
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<SalesForecastHistory> createSalesForecastHistory(HttpServletRequest request) throws Exception {

		SalesForecastHistory salesForecastHistoryToBeAdded = new SalesForecastHistory();
		try {
			salesForecastHistoryToBeAdded = SalesForecastHistoryMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
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
	public ResponseEntity<SalesForecastHistory> createSalesForecastHistory(@RequestBody SalesForecastHistory salesForecastHistoryToBeAdded) throws Exception {

		AddSalesForecastHistory command = new AddSalesForecastHistory(salesForecastHistoryToBeAdded);
		SalesForecastHistory salesForecastHistory = ((SalesForecastHistoryAdded) Scheduler.execute(command).data()).getAddedSalesForecastHistory();
		
		if (salesForecastHistory != null) 
			return successful(salesForecastHistory);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateSalesForecastHistory(@RequestBody SalesForecastHistory salesForecastHistoryToBeUpdated,
			@PathVariable String salesForecastHistoryId) throws Exception {

		salesForecastHistoryToBeUpdated.setSalesForecastHistoryId(salesForecastHistoryId);

		UpdateSalesForecastHistory command = new UpdateSalesForecastHistory(salesForecastHistoryToBeUpdated);

		try {
			if(((SalesForecastHistoryUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{salesForecastHistoryId}")
	public ResponseEntity<SalesForecastHistory> findById(@PathVariable String salesForecastHistoryId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("salesForecastHistoryId", salesForecastHistoryId);
		try {

			List<SalesForecastHistory> foundSalesForecastHistory = findSalesForecastHistorysBy(requestParams).getBody();
			if(foundSalesForecastHistory.size()==1){				return successful(foundSalesForecastHistory.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{salesForecastHistoryId}")
	public ResponseEntity<String> deleteSalesForecastHistoryByIdUpdated(@PathVariable String salesForecastHistoryId) throws Exception {
		DeleteSalesForecastHistory command = new DeleteSalesForecastHistory(salesForecastHistoryId);

		try {
			if (((SalesForecastHistoryDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
