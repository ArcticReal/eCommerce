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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@CrossOrigin
@RequestMapping("/marketingCampaignPromos")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findMarketingCampaignPromosBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindMarketingCampaignPromosBy query = new FindMarketingCampaignPromosBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<MarketingCampaignPromo> marketingCampaignPromos =((MarketingCampaignPromoFound) Scheduler.execute(query).data()).getMarketingCampaignPromos();

		if (marketingCampaignPromos.size() == 1) {
			return ResponseEntity.ok().body(marketingCampaignPromos.get(0));
		}

		return ResponseEntity.ok().body(marketingCampaignPromos);

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
	public ResponseEntity<Object> createMarketingCampaignPromo(HttpServletRequest request) throws Exception {

		MarketingCampaignPromo marketingCampaignPromoToBeAdded = new MarketingCampaignPromo();
		try {
			marketingCampaignPromoToBeAdded = MarketingCampaignPromoMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createMarketingCampaignPromo(marketingCampaignPromoToBeAdded);

	}

	/**
	 * creates a new MarketingCampaignPromo entry in the ofbiz database
	 * 
	 * @param marketingCampaignPromoToBeAdded
	 *            the MarketingCampaignPromo thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createMarketingCampaignPromo(@RequestBody MarketingCampaignPromo marketingCampaignPromoToBeAdded) throws Exception {

		AddMarketingCampaignPromo command = new AddMarketingCampaignPromo(marketingCampaignPromoToBeAdded);
		MarketingCampaignPromo marketingCampaignPromo = ((MarketingCampaignPromoAdded) Scheduler.execute(command).data()).getAddedMarketingCampaignPromo();
		
		if (marketingCampaignPromo != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(marketingCampaignPromo);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("MarketingCampaignPromo could not be created.");
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
	public boolean updateMarketingCampaignPromo(HttpServletRequest request) throws Exception {

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

		MarketingCampaignPromo marketingCampaignPromoToBeUpdated = new MarketingCampaignPromo();

		try {
			marketingCampaignPromoToBeUpdated = MarketingCampaignPromoMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateMarketingCampaignPromo(marketingCampaignPromoToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateMarketingCampaignPromo(@RequestBody MarketingCampaignPromo marketingCampaignPromoToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		marketingCampaignPromoToBeUpdated.setnull(null);

		UpdateMarketingCampaignPromo command = new UpdateMarketingCampaignPromo(marketingCampaignPromoToBeUpdated);

		try {
			if(((MarketingCampaignPromoUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{marketingCampaignPromoId}")
	public ResponseEntity<Object> findById(@PathVariable String marketingCampaignPromoId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("marketingCampaignPromoId", marketingCampaignPromoId);
		try {

			Object foundMarketingCampaignPromo = findMarketingCampaignPromosBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundMarketingCampaignPromo);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{marketingCampaignPromoId}")
	public ResponseEntity<Object> deleteMarketingCampaignPromoByIdUpdated(@PathVariable String marketingCampaignPromoId) throws Exception {
		DeleteMarketingCampaignPromo command = new DeleteMarketingCampaignPromo(marketingCampaignPromoId);

		try {
			if (((MarketingCampaignPromoDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("MarketingCampaignPromo could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/marketingCampaignPromo/\" plus one of the following: "
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