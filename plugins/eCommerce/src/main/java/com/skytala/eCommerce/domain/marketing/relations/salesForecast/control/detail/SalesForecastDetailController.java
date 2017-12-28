package com.skytala.eCommerce.domain.marketing.relations.salesForecast.control.detail;

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
import com.skytala.eCommerce.domain.marketing.relations.salesForecast.command.detail.AddSalesForecastDetail;
import com.skytala.eCommerce.domain.marketing.relations.salesForecast.command.detail.DeleteSalesForecastDetail;
import com.skytala.eCommerce.domain.marketing.relations.salesForecast.command.detail.UpdateSalesForecastDetail;
import com.skytala.eCommerce.domain.marketing.relations.salesForecast.event.detail.SalesForecastDetailAdded;
import com.skytala.eCommerce.domain.marketing.relations.salesForecast.event.detail.SalesForecastDetailDeleted;
import com.skytala.eCommerce.domain.marketing.relations.salesForecast.event.detail.SalesForecastDetailFound;
import com.skytala.eCommerce.domain.marketing.relations.salesForecast.event.detail.SalesForecastDetailUpdated;
import com.skytala.eCommerce.domain.marketing.relations.salesForecast.mapper.detail.SalesForecastDetailMapper;
import com.skytala.eCommerce.domain.marketing.relations.salesForecast.model.detail.SalesForecastDetail;
import com.skytala.eCommerce.domain.marketing.relations.salesForecast.query.detail.FindSalesForecastDetailsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/marketing/salesForecast/salesForecastDetails")
public class SalesForecastDetailController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public SalesForecastDetailController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a SalesForecastDetail
	 * @return a List with the SalesForecastDetails
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<SalesForecastDetail>> findSalesForecastDetailsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindSalesForecastDetailsBy query = new FindSalesForecastDetailsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<SalesForecastDetail> salesForecastDetails =((SalesForecastDetailFound) Scheduler.execute(query).data()).getSalesForecastDetails();

		return ResponseEntity.ok().body(salesForecastDetails);

	}

	/**
	 * creates a new SalesForecastDetail entry in the ofbiz database
	 * 
	 * @param salesForecastDetailToBeAdded
	 *            the SalesForecastDetail thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<SalesForecastDetail> createSalesForecastDetail(@RequestBody SalesForecastDetail salesForecastDetailToBeAdded) throws Exception {

		AddSalesForecastDetail command = new AddSalesForecastDetail(salesForecastDetailToBeAdded);
		SalesForecastDetail salesForecastDetail = ((SalesForecastDetailAdded) Scheduler.execute(command).data()).getAddedSalesForecastDetail();
		
		if (salesForecastDetail != null) 
			return successful(salesForecastDetail);
		else 
			return conflict(null);
	}

	/**
	 * Updates the SalesForecastDetail with the specific Id
	 * 
	 * @param salesForecastDetailToBeUpdated
	 *            the SalesForecastDetail thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{salesForecastDetailId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateSalesForecastDetail(@RequestBody SalesForecastDetail salesForecastDetailToBeUpdated,
			@PathVariable String salesForecastDetailId) throws Exception {

		salesForecastDetailToBeUpdated.setSalesForecastDetailId(salesForecastDetailId);

		UpdateSalesForecastDetail command = new UpdateSalesForecastDetail(salesForecastDetailToBeUpdated);

		try {
			if(((SalesForecastDetailUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{salesForecastDetailId}")
	public ResponseEntity<SalesForecastDetail> findById(@PathVariable String salesForecastDetailId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("salesForecastDetailId", salesForecastDetailId);
		try {

			List<SalesForecastDetail> foundSalesForecastDetail = findSalesForecastDetailsBy(requestParams).getBody();
			if(foundSalesForecastDetail.size()==1){				return successful(foundSalesForecastDetail.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{salesForecastDetailId}")
	public ResponseEntity<String> deleteSalesForecastDetailByIdUpdated(@PathVariable String salesForecastDetailId) throws Exception {
		DeleteSalesForecastDetail command = new DeleteSalesForecastDetail(salesForecastDetailId);

		try {
			if (((SalesForecastDetailDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
