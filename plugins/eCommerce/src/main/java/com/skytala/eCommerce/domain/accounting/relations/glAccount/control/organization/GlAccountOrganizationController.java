package com.skytala.eCommerce.domain.accounting.relations.glAccount.control.organization;

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
import com.skytala.eCommerce.domain.accounting.relations.glAccount.command.organization.AddGlAccountOrganization;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.command.organization.DeleteGlAccountOrganization;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.command.organization.UpdateGlAccountOrganization;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.organization.GlAccountOrganizationAdded;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.organization.GlAccountOrganizationDeleted;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.organization.GlAccountOrganizationFound;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.organization.GlAccountOrganizationUpdated;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.mapper.organization.GlAccountOrganizationMapper;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.organization.GlAccountOrganization;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.query.organization.FindGlAccountOrganizationsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/glAccount/glAccountOrganizations")
public class GlAccountOrganizationController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public GlAccountOrganizationController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a GlAccountOrganization
	 * @return a List with the GlAccountOrganizations
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<GlAccountOrganization>> findGlAccountOrganizationsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindGlAccountOrganizationsBy query = new FindGlAccountOrganizationsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<GlAccountOrganization> glAccountOrganizations =((GlAccountOrganizationFound) Scheduler.execute(query).data()).getGlAccountOrganizations();

		return ResponseEntity.ok().body(glAccountOrganizations);

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
	public ResponseEntity<GlAccountOrganization> createGlAccountOrganization(HttpServletRequest request) throws Exception {

		GlAccountOrganization glAccountOrganizationToBeAdded = new GlAccountOrganization();
		try {
			glAccountOrganizationToBeAdded = GlAccountOrganizationMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createGlAccountOrganization(glAccountOrganizationToBeAdded);

	}

	/**
	 * creates a new GlAccountOrganization entry in the ofbiz database
	 * 
	 * @param glAccountOrganizationToBeAdded
	 *            the GlAccountOrganization thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<GlAccountOrganization> createGlAccountOrganization(@RequestBody GlAccountOrganization glAccountOrganizationToBeAdded) throws Exception {

		AddGlAccountOrganization command = new AddGlAccountOrganization(glAccountOrganizationToBeAdded);
		GlAccountOrganization glAccountOrganization = ((GlAccountOrganizationAdded) Scheduler.execute(command).data()).getAddedGlAccountOrganization();
		
		if (glAccountOrganization != null) 
			return successful(glAccountOrganization);
		else 
			return conflict(null);
	}

	/**
	 * Updates the GlAccountOrganization with the specific Id
	 * 
	 * @param glAccountOrganizationToBeUpdated
	 *            the GlAccountOrganization thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateGlAccountOrganization(@RequestBody GlAccountOrganization glAccountOrganizationToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		glAccountOrganizationToBeUpdated.setnull(null);

		UpdateGlAccountOrganization command = new UpdateGlAccountOrganization(glAccountOrganizationToBeUpdated);

		try {
			if(((GlAccountOrganizationUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{glAccountOrganizationId}")
	public ResponseEntity<GlAccountOrganization> findById(@PathVariable String glAccountOrganizationId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("glAccountOrganizationId", glAccountOrganizationId);
		try {

			List<GlAccountOrganization> foundGlAccountOrganization = findGlAccountOrganizationsBy(requestParams).getBody();
			if(foundGlAccountOrganization.size()==1){				return successful(foundGlAccountOrganization.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{glAccountOrganizationId}")
	public ResponseEntity<String> deleteGlAccountOrganizationByIdUpdated(@PathVariable String glAccountOrganizationId) throws Exception {
		DeleteGlAccountOrganization command = new DeleteGlAccountOrganization(glAccountOrganizationId);

		try {
			if (((GlAccountOrganizationDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
