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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@RequestMapping("/marketingCampaigns")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findMarketingCampaignsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindMarketingCampaignsBy query = new FindMarketingCampaignsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<MarketingCampaign> marketingCampaigns =((MarketingCampaignFound) Scheduler.execute(query).data()).getMarketingCampaigns();

		if (marketingCampaigns.size() == 1) {
			return ResponseEntity.ok().body(marketingCampaigns.get(0));
		}

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
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createMarketingCampaign(HttpServletRequest request) throws Exception {

		MarketingCampaign marketingCampaignToBeAdded = new MarketingCampaign();
		try {
			marketingCampaignToBeAdded = MarketingCampaignMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
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
	public ResponseEntity<Object> createMarketingCampaign(@RequestBody MarketingCampaign marketingCampaignToBeAdded) throws Exception {

		AddMarketingCampaign command = new AddMarketingCampaign(marketingCampaignToBeAdded);
		MarketingCampaign marketingCampaign = ((MarketingCampaignAdded) Scheduler.execute(command).data()).getAddedMarketingCampaign();
		
		if (marketingCampaign != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(marketingCampaign);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("MarketingCampaign could not be created.");
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
	public boolean updateMarketingCampaign(HttpServletRequest request) throws Exception {

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

		MarketingCampaign marketingCampaignToBeUpdated = new MarketingCampaign();

		try {
			marketingCampaignToBeUpdated = MarketingCampaignMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateMarketingCampaign(marketingCampaignToBeUpdated, marketingCampaignToBeUpdated.getMarketingCampaignId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateMarketingCampaign(@RequestBody MarketingCampaign marketingCampaignToBeUpdated,
			@PathVariable String marketingCampaignId) throws Exception {

		marketingCampaignToBeUpdated.setMarketingCampaignId(marketingCampaignId);

		UpdateMarketingCampaign command = new UpdateMarketingCampaign(marketingCampaignToBeUpdated);

		try {
			if(((MarketingCampaignUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{marketingCampaignId}")
	public ResponseEntity<Object> findById(@PathVariable String marketingCampaignId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("marketingCampaignId", marketingCampaignId);
		try {

			Object foundMarketingCampaign = findMarketingCampaignsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundMarketingCampaign);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{marketingCampaignId}")
	public ResponseEntity<Object> deleteMarketingCampaignByIdUpdated(@PathVariable String marketingCampaignId) throws Exception {
		DeleteMarketingCampaign command = new DeleteMarketingCampaign(marketingCampaignId);

		try {
			if (((MarketingCampaignDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("MarketingCampaign could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/marketingCampaign/\" plus one of the following: "
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
