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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	@GetMapping("/find")
	public ResponseEntity<List<MarketingCampaignPrice>> findMarketingCampaignPricesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindMarketingCampaignPricesBy query = new FindMarketingCampaignPricesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<MarketingCampaignPrice> marketingCampaignPrices =((MarketingCampaignPriceFound) Scheduler.execute(query).data()).getMarketingCampaignPrices();

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
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<MarketingCampaignPrice> createMarketingCampaignPrice(HttpServletRequest request) throws Exception {

		MarketingCampaignPrice marketingCampaignPriceToBeAdded = new MarketingCampaignPrice();
		try {
			marketingCampaignPriceToBeAdded = MarketingCampaignPriceMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
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
	public ResponseEntity<MarketingCampaignPrice> createMarketingCampaignPrice(@RequestBody MarketingCampaignPrice marketingCampaignPriceToBeAdded) throws Exception {

		AddMarketingCampaignPrice command = new AddMarketingCampaignPrice(marketingCampaignPriceToBeAdded);
		MarketingCampaignPrice marketingCampaignPrice = ((MarketingCampaignPriceAdded) Scheduler.execute(command).data()).getAddedMarketingCampaignPrice();
		
		if (marketingCampaignPrice != null) 
			return successful(marketingCampaignPrice);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateMarketingCampaignPrice(@RequestBody MarketingCampaignPrice marketingCampaignPriceToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		marketingCampaignPriceToBeUpdated.setnull(null);

		UpdateMarketingCampaignPrice command = new UpdateMarketingCampaignPrice(marketingCampaignPriceToBeUpdated);

		try {
			if(((MarketingCampaignPriceUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{marketingCampaignPriceId}")
	public ResponseEntity<MarketingCampaignPrice> findById(@PathVariable String marketingCampaignPriceId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("marketingCampaignPriceId", marketingCampaignPriceId);
		try {

			List<MarketingCampaignPrice> foundMarketingCampaignPrice = findMarketingCampaignPricesBy(requestParams).getBody();
			if(foundMarketingCampaignPrice.size()==1){				return successful(foundMarketingCampaignPrice.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{marketingCampaignPriceId}")
	public ResponseEntity<String> deleteMarketingCampaignPriceByIdUpdated(@PathVariable String marketingCampaignPriceId) throws Exception {
		DeleteMarketingCampaignPrice command = new DeleteMarketingCampaignPrice(marketingCampaignPriceId);

		try {
			if (((MarketingCampaignPriceDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
