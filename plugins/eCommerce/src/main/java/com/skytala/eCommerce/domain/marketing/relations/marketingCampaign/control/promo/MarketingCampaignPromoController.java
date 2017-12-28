package com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.control.promo;

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
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.command.promo.AddMarketingCampaignPromo;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.command.promo.DeleteMarketingCampaignPromo;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.command.promo.UpdateMarketingCampaignPromo;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.event.promo.MarketingCampaignPromoAdded;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.event.promo.MarketingCampaignPromoDeleted;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.event.promo.MarketingCampaignPromoFound;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.event.promo.MarketingCampaignPromoUpdated;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.mapper.promo.MarketingCampaignPromoMapper;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.model.promo.MarketingCampaignPromo;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.query.promo.FindMarketingCampaignPromosBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/marketing/marketingCampaign/marketingCampaignPromos")
public class MarketingCampaignPromoController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public MarketingCampaignPromoController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a MarketingCampaignPromo
	 * @return a List with the MarketingCampaignPromos
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<MarketingCampaignPromo>> findMarketingCampaignPromosBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindMarketingCampaignPromosBy query = new FindMarketingCampaignPromosBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<MarketingCampaignPromo> marketingCampaignPromos =((MarketingCampaignPromoFound) Scheduler.execute(query).data()).getMarketingCampaignPromos();

		return ResponseEntity.ok().body(marketingCampaignPromos);

	}

	/**
	 * creates a new MarketingCampaignPromo entry in the ofbiz database
	 * 
	 * @param marketingCampaignPromoToBeAdded
	 *            the MarketingCampaignPromo thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<MarketingCampaignPromo> createMarketingCampaignPromo(@RequestBody MarketingCampaignPromo marketingCampaignPromoToBeAdded) throws Exception {

		AddMarketingCampaignPromo command = new AddMarketingCampaignPromo(marketingCampaignPromoToBeAdded);
		MarketingCampaignPromo marketingCampaignPromo = ((MarketingCampaignPromoAdded) Scheduler.execute(command).data()).getAddedMarketingCampaignPromo();
		
		if (marketingCampaignPromo != null) 
			return successful(marketingCampaignPromo);
		else 
			return conflict(null);
	}

	/**
	 * Updates the MarketingCampaignPromo with the specific Id
	 * 
	 * @param marketingCampaignPromoToBeUpdated
	 *            the MarketingCampaignPromo thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateMarketingCampaignPromo(@RequestBody MarketingCampaignPromo marketingCampaignPromoToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		marketingCampaignPromoToBeUpdated.setnull(null);

		UpdateMarketingCampaignPromo command = new UpdateMarketingCampaignPromo(marketingCampaignPromoToBeUpdated);

		try {
			if(((MarketingCampaignPromoUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{marketingCampaignPromoId}")
	public ResponseEntity<MarketingCampaignPromo> findById(@PathVariable String marketingCampaignPromoId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("marketingCampaignPromoId", marketingCampaignPromoId);
		try {

			List<MarketingCampaignPromo> foundMarketingCampaignPromo = findMarketingCampaignPromosBy(requestParams).getBody();
			if(foundMarketingCampaignPromo.size()==1){				return successful(foundMarketingCampaignPromo.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{marketingCampaignPromoId}")
	public ResponseEntity<String> deleteMarketingCampaignPromoByIdUpdated(@PathVariable String marketingCampaignPromoId) throws Exception {
		DeleteMarketingCampaignPromo command = new DeleteMarketingCampaignPromo(marketingCampaignPromoId);

		try {
			if (((MarketingCampaignPromoDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
