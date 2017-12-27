package com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.control.role;

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
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.command.role.AddSalesOpportunityRole;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.command.role.DeleteSalesOpportunityRole;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.command.role.UpdateSalesOpportunityRole;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.event.role.SalesOpportunityRoleAdded;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.event.role.SalesOpportunityRoleDeleted;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.event.role.SalesOpportunityRoleFound;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.event.role.SalesOpportunityRoleUpdated;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.mapper.role.SalesOpportunityRoleMapper;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.model.role.SalesOpportunityRole;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.query.role.FindSalesOpportunityRolesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/marketing/salesOpportunity/salesOpportunityRoles")
public class SalesOpportunityRoleController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public SalesOpportunityRoleController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a SalesOpportunityRole
	 * @return a List with the SalesOpportunityRoles
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<SalesOpportunityRole>> findSalesOpportunityRolesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindSalesOpportunityRolesBy query = new FindSalesOpportunityRolesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<SalesOpportunityRole> salesOpportunityRoles =((SalesOpportunityRoleFound) Scheduler.execute(query).data()).getSalesOpportunityRoles();

		return ResponseEntity.ok().body(salesOpportunityRoles);

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
	public ResponseEntity<SalesOpportunityRole> createSalesOpportunityRole(HttpServletRequest request) throws Exception {

		SalesOpportunityRole salesOpportunityRoleToBeAdded = new SalesOpportunityRole();
		try {
			salesOpportunityRoleToBeAdded = SalesOpportunityRoleMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createSalesOpportunityRole(salesOpportunityRoleToBeAdded);

	}

	/**
	 * creates a new SalesOpportunityRole entry in the ofbiz database
	 * 
	 * @param salesOpportunityRoleToBeAdded
	 *            the SalesOpportunityRole thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<SalesOpportunityRole> createSalesOpportunityRole(@RequestBody SalesOpportunityRole salesOpportunityRoleToBeAdded) throws Exception {

		AddSalesOpportunityRole command = new AddSalesOpportunityRole(salesOpportunityRoleToBeAdded);
		SalesOpportunityRole salesOpportunityRole = ((SalesOpportunityRoleAdded) Scheduler.execute(command).data()).getAddedSalesOpportunityRole();
		
		if (salesOpportunityRole != null) 
			return successful(salesOpportunityRole);
		else 
			return conflict(null);
	}

	/**
	 * Updates the SalesOpportunityRole with the specific Id
	 * 
	 * @param salesOpportunityRoleToBeUpdated
	 *            the SalesOpportunityRole thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateSalesOpportunityRole(@RequestBody SalesOpportunityRole salesOpportunityRoleToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		salesOpportunityRoleToBeUpdated.setnull(null);

		UpdateSalesOpportunityRole command = new UpdateSalesOpportunityRole(salesOpportunityRoleToBeUpdated);

		try {
			if(((SalesOpportunityRoleUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{salesOpportunityRoleId}")
	public ResponseEntity<SalesOpportunityRole> findById(@PathVariable String salesOpportunityRoleId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("salesOpportunityRoleId", salesOpportunityRoleId);
		try {

			List<SalesOpportunityRole> foundSalesOpportunityRole = findSalesOpportunityRolesBy(requestParams).getBody();
			if(foundSalesOpportunityRole.size()==1){				return successful(foundSalesOpportunityRole.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{salesOpportunityRoleId}")
	public ResponseEntity<String> deleteSalesOpportunityRoleByIdUpdated(@PathVariable String salesOpportunityRoleId) throws Exception {
		DeleteSalesOpportunityRole command = new DeleteSalesOpportunityRole(salesOpportunityRoleId);

		try {
			if (((SalesOpportunityRoleDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
