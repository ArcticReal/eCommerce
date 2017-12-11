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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findSalesForecastDetailsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindSalesForecastDetailsBy query = new FindSalesForecastDetailsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<SalesForecastDetail> salesForecastDetails =((SalesForecastDetailFound) Scheduler.execute(query).data()).getSalesForecastDetails();

		if (salesForecastDetails.size() == 1) {
			return ResponseEntity.ok().body(salesForecastDetails.get(0));
		}

		return ResponseEntity.ok().body(salesForecastDetails);

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
	public ResponseEntity<Object> createSalesForecastDetail(HttpServletRequest request) throws Exception {

		SalesForecastDetail salesForecastDetailToBeAdded = new SalesForecastDetail();
		try {
			salesForecastDetailToBeAdded = SalesForecastDetailMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createSalesForecastDetail(salesForecastDetailToBeAdded);

	}

	/**
	 * creates a new SalesForecastDetail entry in the ofbiz database
	 * 
	 * @param salesForecastDetailToBeAdded
	 *            the SalesForecastDetail thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createSalesForecastDetail(@RequestBody SalesForecastDetail salesForecastDetailToBeAdded) throws Exception {

		AddSalesForecastDetail command = new AddSalesForecastDetail(salesForecastDetailToBeAdded);
		SalesForecastDetail salesForecastDetail = ((SalesForecastDetailAdded) Scheduler.execute(command).data()).getAddedSalesForecastDetail();
		
		if (salesForecastDetail != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(salesForecastDetail);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("SalesForecastDetail could not be created.");
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
	public boolean updateSalesForecastDetail(HttpServletRequest request) throws Exception {

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

		SalesForecastDetail salesForecastDetailToBeUpdated = new SalesForecastDetail();

		try {
			salesForecastDetailToBeUpdated = SalesForecastDetailMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateSalesForecastDetail(salesForecastDetailToBeUpdated, salesForecastDetailToBeUpdated.getSalesForecastDetailId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateSalesForecastDetail(@RequestBody SalesForecastDetail salesForecastDetailToBeUpdated,
			@PathVariable String salesForecastDetailId) throws Exception {

		salesForecastDetailToBeUpdated.setSalesForecastDetailId(salesForecastDetailId);

		UpdateSalesForecastDetail command = new UpdateSalesForecastDetail(salesForecastDetailToBeUpdated);

		try {
			if(((SalesForecastDetailUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{salesForecastDetailId}")
	public ResponseEntity<Object> findById(@PathVariable String salesForecastDetailId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("salesForecastDetailId", salesForecastDetailId);
		try {

			Object foundSalesForecastDetail = findSalesForecastDetailsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundSalesForecastDetail);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{salesForecastDetailId}")
	public ResponseEntity<Object> deleteSalesForecastDetailByIdUpdated(@PathVariable String salesForecastDetailId) throws Exception {
		DeleteSalesForecastDetail command = new DeleteSalesForecastDetail(salesForecastDetailId);

		try {
			if (((SalesForecastDetailDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("SalesForecastDetail could not be deleted");

	}

}
