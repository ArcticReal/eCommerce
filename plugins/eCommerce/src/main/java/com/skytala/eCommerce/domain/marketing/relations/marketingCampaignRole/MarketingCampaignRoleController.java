package com.skytala.eCommerce.domain.marketing.relations.marketingCampaignRole;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Splitter;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaignRole.command.AddMarketingCampaignRole;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaignRole.command.DeleteMarketingCampaignRole;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaignRole.command.UpdateMarketingCampaignRole;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaignRole.event.MarketingCampaignRoleAdded;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaignRole.event.MarketingCampaignRoleDeleted;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaignRole.event.MarketingCampaignRoleFound;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaignRole.event.MarketingCampaignRoleUpdated;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaignRole.mapper.MarketingCampaignRoleMapper;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaignRole.model.MarketingCampaignRole;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaignRole.query.FindMarketingCampaignRolesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/marketingCampaignRoles")
public class MarketingCampaignRoleController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public MarketingCampaignRoleController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a MarketingCampaignRole
	 * @return a List with the MarketingCampaignRoles
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findMarketingCampaignRolesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindMarketingCampaignRolesBy query = new FindMarketingCampaignRolesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<MarketingCampaignRole> marketingCampaignRoles =((MarketingCampaignRoleFound) Scheduler.execute(query).data()).getMarketingCampaignRoles();

		if (marketingCampaignRoles.size() == 1) {
			return ResponseEntity.ok().body(marketingCampaignRoles.get(0));
		}

		return ResponseEntity.ok().body(marketingCampaignRoles);

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
	public ResponseEntity<Object> createMarketingCampaignRole(HttpServletRequest request) throws Exception {

		MarketingCampaignRole marketingCampaignRoleToBeAdded = new MarketingCampaignRole();
		try {
			marketingCampaignRoleToBeAdded = MarketingCampaignRoleMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createMarketingCampaignRole(marketingCampaignRoleToBeAdded);

	}

	/**
	 * creates a new MarketingCampaignRole entry in the ofbiz database
	 * 
	 * @param marketingCampaignRoleToBeAdded
	 *            the MarketingCampaignRole thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createMarketingCampaignRole(@RequestBody MarketingCampaignRole marketingCampaignRoleToBeAdded) throws Exception {

		AddMarketingCampaignRole command = new AddMarketingCampaignRole(marketingCampaignRoleToBeAdded);
		MarketingCampaignRole marketingCampaignRole = ((MarketingCampaignRoleAdded) Scheduler.execute(command).data()).getAddedMarketingCampaignRole();
		
		if (marketingCampaignRole != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(marketingCampaignRole);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("MarketingCampaignRole could not be created.");
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
	public boolean updateMarketingCampaignRole(HttpServletRequest request) throws Exception {

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

		MarketingCampaignRole marketingCampaignRoleToBeUpdated = new MarketingCampaignRole();

		try {
			marketingCampaignRoleToBeUpdated = MarketingCampaignRoleMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateMarketingCampaignRole(marketingCampaignRoleToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the MarketingCampaignRole with the specific Id
	 * 
	 * @param marketingCampaignRoleToBeUpdated
	 *            the MarketingCampaignRole thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateMarketingCampaignRole(@RequestBody MarketingCampaignRole marketingCampaignRoleToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		marketingCampaignRoleToBeUpdated.setnull(null);

		UpdateMarketingCampaignRole command = new UpdateMarketingCampaignRole(marketingCampaignRoleToBeUpdated);

		try {
			if(((MarketingCampaignRoleUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{marketingCampaignRoleId}")
	public ResponseEntity<Object> findById(@PathVariable String marketingCampaignRoleId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("marketingCampaignRoleId", marketingCampaignRoleId);
		try {

			Object foundMarketingCampaignRole = findMarketingCampaignRolesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundMarketingCampaignRole);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{marketingCampaignRoleId}")
	public ResponseEntity<Object> deleteMarketingCampaignRoleByIdUpdated(@PathVariable String marketingCampaignRoleId) throws Exception {
		DeleteMarketingCampaignRole command = new DeleteMarketingCampaignRole(marketingCampaignRoleId);

		try {
			if (((MarketingCampaignRoleDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("MarketingCampaignRole could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/marketingCampaignRole/\" plus one of the following: "
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
