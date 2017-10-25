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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@RequestMapping("/glAccountOrganizations")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findGlAccountOrganizationsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindGlAccountOrganizationsBy query = new FindGlAccountOrganizationsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<GlAccountOrganization> glAccountOrganizations =((GlAccountOrganizationFound) Scheduler.execute(query).data()).getGlAccountOrganizations();

		if (glAccountOrganizations.size() == 1) {
			return ResponseEntity.ok().body(glAccountOrganizations.get(0));
		}

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
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createGlAccountOrganization(HttpServletRequest request) throws Exception {

		GlAccountOrganization glAccountOrganizationToBeAdded = new GlAccountOrganization();
		try {
			glAccountOrganizationToBeAdded = GlAccountOrganizationMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
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
	public ResponseEntity<Object> createGlAccountOrganization(@RequestBody GlAccountOrganization glAccountOrganizationToBeAdded) throws Exception {

		AddGlAccountOrganization command = new AddGlAccountOrganization(glAccountOrganizationToBeAdded);
		GlAccountOrganization glAccountOrganization = ((GlAccountOrganizationAdded) Scheduler.execute(command).data()).getAddedGlAccountOrganization();
		
		if (glAccountOrganization != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(glAccountOrganization);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("GlAccountOrganization could not be created.");
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
	public boolean updateGlAccountOrganization(HttpServletRequest request) throws Exception {

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

		GlAccountOrganization glAccountOrganizationToBeUpdated = new GlAccountOrganization();

		try {
			glAccountOrganizationToBeUpdated = GlAccountOrganizationMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateGlAccountOrganization(glAccountOrganizationToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateGlAccountOrganization(@RequestBody GlAccountOrganization glAccountOrganizationToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		glAccountOrganizationToBeUpdated.setnull(null);

		UpdateGlAccountOrganization command = new UpdateGlAccountOrganization(glAccountOrganizationToBeUpdated);

		try {
			if(((GlAccountOrganizationUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{glAccountOrganizationId}")
	public ResponseEntity<Object> findById(@PathVariable String glAccountOrganizationId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("glAccountOrganizationId", glAccountOrganizationId);
		try {

			Object foundGlAccountOrganization = findGlAccountOrganizationsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundGlAccountOrganization);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{glAccountOrganizationId}")
	public ResponseEntity<Object> deleteGlAccountOrganizationByIdUpdated(@PathVariable String glAccountOrganizationId) throws Exception {
		DeleteGlAccountOrganization command = new DeleteGlAccountOrganization(glAccountOrganizationId);

		try {
			if (((GlAccountOrganizationDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("GlAccountOrganization could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/glAccountOrganization/\" plus one of the following: "
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
