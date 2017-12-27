package com.skytala.eCommerce.domain.marketing.relations.marketingCampaign;

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
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.command.AddMarketingCampaign;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.command.DeleteMarketingCampaign;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.command.UpdateMarketingCampaign;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.event.MarketingCampaignAdded;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.event.MarketingCampaignDeleted;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.event.MarketingCampaignFound;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.event.MarketingCampaignUpdated;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.mapper.MarketingCampaignMapper;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.model.MarketingCampaign;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.query.FindMarketingCampaignsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/marketing/marketingCampaigns")
public class MarketingCampaignController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public MarketingCampaignController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a MarketingCampaign
	 * @return a List with the MarketingCampaigns
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<MarketingCampaign>> findMarketingCampaignsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindMarketingCampaignsBy query = new FindMarketingCampaignsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<MarketingCampaign> marketingCampaigns =((MarketingCampaignFound) Scheduler.execute(query).data()).getMarketingCampaigns();

		return ResponseEntity.ok().body(marketingCampaigns);

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
	public ResponseEntity<MarketingCampaign> createMarketingCampaign(HttpServletRequest request) throws Exception {

		MarketingCampaign marketingCampaignToBeAdded = new MarketingCampaign();
		try {
			marketingCampaignToBeAdded = MarketingCampaignMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createMarketingCampaign(marketingCampaignToBeAdded);

	}

	/**
	 * creates a new MarketingCampaign entry in the ofbiz database
	 * 
	 * @param marketingCampaignToBeAdded
	 *            the MarketingCampaign thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<MarketingCampaign> createMarketingCampaign(@RequestBody MarketingCampaign marketingCampaignToBeAdded) throws Exception {

		AddMarketingCampaign command = new AddMarketingCampaign(marketingCampaignToBeAdded);
		MarketingCampaign marketingCampaign = ((MarketingCampaignAdded) Scheduler.execute(command).data()).getAddedMarketingCampaign();
		
		if (marketingCampaign != null) 
			return successful(marketingCampaign);
		else 
			return conflict(null);
	}

	/**
	 * Updates the MarketingCampaign with the specific Id
	 * 
	 * @param marketingCampaignToBeUpdated
	 *            the MarketingCampaign thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{marketingCampaignId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateMarketingCampaign(@RequestBody MarketingCampaign marketingCampaignToBeUpdated,
			@PathVariable String marketingCampaignId) throws Exception {

		marketingCampaignToBeUpdated.setMarketingCampaignId(marketingCampaignId);

		UpdateMarketingCampaign command = new UpdateMarketingCampaign(marketingCampaignToBeUpdated);

		try {
			if(((MarketingCampaignUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{marketingCampaignId}")
	public ResponseEntity<MarketingCampaign> findById(@PathVariable String marketingCampaignId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("marketingCampaignId", marketingCampaignId);
		try {

			List<MarketingCampaign> foundMarketingCampaign = findMarketingCampaignsBy(requestParams).getBody();
			if(foundMarketingCampaign.size()==1){				return successful(foundMarketingCampaign.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{marketingCampaignId}")
	public ResponseEntity<String> deleteMarketingCampaignByIdUpdated(@PathVariable String marketingCampaignId) throws Exception {
		DeleteMarketingCampaign command = new DeleteMarketingCampaign(marketingCampaignId);

		try {
			if (((MarketingCampaignDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
