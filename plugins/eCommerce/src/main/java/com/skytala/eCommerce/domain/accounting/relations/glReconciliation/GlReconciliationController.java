package com.skytala.eCommerce.domain.accounting.relations.glReconciliation;

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
import com.skytala.eCommerce.domain.accounting.relations.glReconciliation.command.AddGlReconciliation;
import com.skytala.eCommerce.domain.accounting.relations.glReconciliation.command.DeleteGlReconciliation;
import com.skytala.eCommerce.domain.accounting.relations.glReconciliation.command.UpdateGlReconciliation;
import com.skytala.eCommerce.domain.accounting.relations.glReconciliation.event.GlReconciliationAdded;
import com.skytala.eCommerce.domain.accounting.relations.glReconciliation.event.GlReconciliationDeleted;
import com.skytala.eCommerce.domain.accounting.relations.glReconciliation.event.GlReconciliationFound;
import com.skytala.eCommerce.domain.accounting.relations.glReconciliation.event.GlReconciliationUpdated;
import com.skytala.eCommerce.domain.accounting.relations.glReconciliation.mapper.GlReconciliationMapper;
import com.skytala.eCommerce.domain.accounting.relations.glReconciliation.model.GlReconciliation;
import com.skytala.eCommerce.domain.accounting.relations.glReconciliation.query.FindGlReconciliationsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/glReconciliations")
public class GlReconciliationController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public GlReconciliationController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a GlReconciliation
	 * @return a List with the GlReconciliations
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findGlReconciliationsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindGlReconciliationsBy query = new FindGlReconciliationsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<GlReconciliation> glReconciliations =((GlReconciliationFound) Scheduler.execute(query).data()).getGlReconciliations();

		if (glReconciliations.size() == 1) {
			return ResponseEntity.ok().body(glReconciliations.get(0));
		}

		return ResponseEntity.ok().body(glReconciliations);

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
	public ResponseEntity<Object> createGlReconciliation(HttpServletRequest request) throws Exception {

		GlReconciliation glReconciliationToBeAdded = new GlReconciliation();
		try {
			glReconciliationToBeAdded = GlReconciliationMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createGlReconciliation(glReconciliationToBeAdded);

	}

	/**
	 * creates a new GlReconciliation entry in the ofbiz database
	 * 
	 * @param glReconciliationToBeAdded
	 *            the GlReconciliation thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createGlReconciliation(@RequestBody GlReconciliation glReconciliationToBeAdded) throws Exception {

		AddGlReconciliation command = new AddGlReconciliation(glReconciliationToBeAdded);
		GlReconciliation glReconciliation = ((GlReconciliationAdded) Scheduler.execute(command).data()).getAddedGlReconciliation();
		
		if (glReconciliation != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(glReconciliation);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("GlReconciliation could not be created.");
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
	public boolean updateGlReconciliation(HttpServletRequest request) throws Exception {

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

		GlReconciliation glReconciliationToBeUpdated = new GlReconciliation();

		try {
			glReconciliationToBeUpdated = GlReconciliationMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateGlReconciliation(glReconciliationToBeUpdated, glReconciliationToBeUpdated.getGlReconciliationId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the GlReconciliation with the specific Id
	 * 
	 * @param glReconciliationToBeUpdated
	 *            the GlReconciliation thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{glReconciliationId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateGlReconciliation(@RequestBody GlReconciliation glReconciliationToBeUpdated,
			@PathVariable String glReconciliationId) throws Exception {

		glReconciliationToBeUpdated.setGlReconciliationId(glReconciliationId);

		UpdateGlReconciliation command = new UpdateGlReconciliation(glReconciliationToBeUpdated);

		try {
			if(((GlReconciliationUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{glReconciliationId}")
	public ResponseEntity<Object> findById(@PathVariable String glReconciliationId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("glReconciliationId", glReconciliationId);
		try {

			Object foundGlReconciliation = findGlReconciliationsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundGlReconciliation);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{glReconciliationId}")
	public ResponseEntity<Object> deleteGlReconciliationByIdUpdated(@PathVariable String glReconciliationId) throws Exception {
		DeleteGlReconciliation command = new DeleteGlReconciliation(glReconciliationId);

		try {
			if (((GlReconciliationDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("GlReconciliation could not be deleted");

	}

}
