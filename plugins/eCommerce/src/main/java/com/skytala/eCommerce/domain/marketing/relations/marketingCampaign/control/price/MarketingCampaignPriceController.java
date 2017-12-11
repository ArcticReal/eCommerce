package com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.control.price;

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
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.command.price.AddMarketingCampaignPrice;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.command.price.DeleteMarketingCampaignPrice;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.command.price.UpdateMarketingCampaignPrice;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.event.price.MarketingCampaignPriceAdded;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.event.price.MarketingCampaignPriceDeleted;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.event.price.MarketingCampaignPriceFound;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.event.price.MarketingCampaignPriceUpdated;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.mapper.price.MarketingCampaignPriceMapper;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.model.price.MarketingCampaignPrice;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.query.price.FindMarketingCampaignPricesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/marketing/marketingCampaign/marketingCampaignPrices")
public class MarketingCampaignPriceController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public MarketingCampaignPriceController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a MarketingCampaignPrice
	 * @return a List with the MarketingCampaignPrices
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findMarketingCampaignPricesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindMarketingCampaignPricesBy query = new FindMarketingCampaignPricesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<MarketingCampaignPrice> marketingCampaignPrices =((MarketingCampaignPriceFound) Scheduler.execute(query).data()).getMarketingCampaignPrices();

		if (marketingCampaignPrices.size() == 1) {
			return ResponseEntity.ok().body(marketingCampaignPrices.get(0));
		}

		return ResponseEntity.ok().body(marketingCampaignPrices);

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
	public ResponseEntity<Object> createMarketingCampaignPrice(HttpServletRequest request) throws Exception {

		MarketingCampaignPrice marketingCampaignPriceToBeAdded = new MarketingCampaignPrice();
		try {
			marketingCampaignPriceToBeAdded = MarketingCampaignPriceMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createMarketingCampaignPrice(marketingCampaignPriceToBeAdded);

	}

	/**
	 * creates a new MarketingCampaignPrice entry in the ofbiz database
	 * 
	 * @param marketingCampaignPriceToBeAdded
	 *            the MarketingCampaignPrice thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createMarketingCampaignPrice(@RequestBody MarketingCampaignPrice marketingCampaignPriceToBeAdded) throws Exception {

		AddMarketingCampaignPrice command = new AddMarketingCampaignPrice(marketingCampaignPriceToBeAdded);
		MarketingCampaignPrice marketingCampaignPrice = ((MarketingCampaignPriceAdded) Scheduler.execute(command).data()).getAddedMarketingCampaignPrice();
		
		if (marketingCampaignPrice != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(marketingCampaignPrice);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("MarketingCampaignPrice could not be created.");
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
	public boolean updateMarketingCampaignPrice(HttpServletRequest request) throws Exception {

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

		MarketingCampaignPrice marketingCampaignPriceToBeUpdated = new MarketingCampaignPrice();

		try {
			marketingCampaignPriceToBeUpdated = MarketingCampaignPriceMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateMarketingCampaignPrice(marketingCampaignPriceToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the MarketingCampaignPrice with the specific Id
	 * 
	 * @param marketingCampaignPriceToBeUpdated
	 *            the MarketingCampaignPrice thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateMarketingCampaignPrice(@RequestBody MarketingCampaignPrice marketingCampaignPriceToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		marketingCampaignPriceToBeUpdated.setnull(null);

		UpdateMarketingCampaignPrice command = new UpdateMarketingCampaignPrice(marketingCampaignPriceToBeUpdated);

		try {
			if(((MarketingCampaignPriceUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{marketingCampaignPriceId}")
	public ResponseEntity<Object> findById(@PathVariable String marketingCampaignPriceId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("marketingCampaignPriceId", marketingCampaignPriceId);
		try {

			Object foundMarketingCampaignPrice = findMarketingCampaignPricesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundMarketingCampaignPrice);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{marketingCampaignPriceId}")
	public ResponseEntity<Object> deleteMarketingCampaignPriceByIdUpdated(@PathVariable String marketingCampaignPriceId) throws Exception {
		DeleteMarketingCampaignPrice command = new DeleteMarketingCampaignPrice(marketingCampaignPriceId);

		try {
			if (((MarketingCampaignPriceDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("MarketingCampaignPrice could not be deleted");

	}

}
