package com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.control.role;

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
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.command.role.AddMarketingCampaignRole;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.command.role.DeleteMarketingCampaignRole;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.command.role.UpdateMarketingCampaignRole;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.event.role.MarketingCampaignRoleAdded;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.event.role.MarketingCampaignRoleDeleted;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.event.role.MarketingCampaignRoleFound;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.event.role.MarketingCampaignRoleUpdated;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.mapper.role.MarketingCampaignRoleMapper;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.model.role.MarketingCampaignRole;
import com.skytala.eCommerce.domain.marketing.relations.marketingCampaign.query.role.FindMarketingCampaignRolesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/marketing/marketingCampaign/marketingCampaignRoles")
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
	@GetMapping("/find")
	public ResponseEntity<List<MarketingCampaignRole>> findMarketingCampaignRolesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindMarketingCampaignRolesBy query = new FindMarketingCampaignRolesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<MarketingCampaignRole> marketingCampaignRoles =((MarketingCampaignRoleFound) Scheduler.execute(query).data()).getMarketingCampaignRoles();

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
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<MarketingCampaignRole> createMarketingCampaignRole(HttpServletRequest request) throws Exception {

		MarketingCampaignRole marketingCampaignRoleToBeAdded = new MarketingCampaignRole();
		try {
			marketingCampaignRoleToBeAdded = MarketingCampaignRoleMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
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
	public ResponseEntity<MarketingCampaignRole> createMarketingCampaignRole(@RequestBody MarketingCampaignRole marketingCampaignRoleToBeAdded) throws Exception {

		AddMarketingCampaignRole command = new AddMarketingCampaignRole(marketingCampaignRoleToBeAdded);
		MarketingCampaignRole marketingCampaignRole = ((MarketingCampaignRoleAdded) Scheduler.execute(command).data()).getAddedMarketingCampaignRole();
		
		if (marketingCampaignRole != null) 
			return successful(marketingCampaignRole);
		else 
			return conflict(null);
	}

	/**
	 * Updates the MarketingCampaignRole with the specific Id
	 * 
	 * @param marketingCampaignRoleToBeUpdated
	 *            the MarketingCampaignRole thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{roleTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateMarketingCampaignRole(@RequestBody MarketingCampaignRole marketingCampaignRoleToBeUpdated,
			@PathVariable String roleTypeId) throws Exception {

		marketingCampaignRoleToBeUpdated.setRoleTypeId(roleTypeId);

		UpdateMarketingCampaignRole command = new UpdateMarketingCampaignRole(marketingCampaignRoleToBeUpdated);

		try {
			if(((MarketingCampaignRoleUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{marketingCampaignRoleId}")
	public ResponseEntity<MarketingCampaignRole> findById(@PathVariable String marketingCampaignRoleId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("marketingCampaignRoleId", marketingCampaignRoleId);
		try {

			List<MarketingCampaignRole> foundMarketingCampaignRole = findMarketingCampaignRolesBy(requestParams).getBody();
			if(foundMarketingCampaignRole.size()==1){				return successful(foundMarketingCampaignRole.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{marketingCampaignRoleId}")
	public ResponseEntity<String> deleteMarketingCampaignRoleByIdUpdated(@PathVariable String marketingCampaignRoleId) throws Exception {
		DeleteMarketingCampaignRole command = new DeleteMarketingCampaignRole(marketingCampaignRoleId);

		try {
			if (((MarketingCampaignRoleDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
