package com.skytala.eCommerce.domain.accounting.relations.budget.control.glXref;

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
import com.skytala.eCommerce.domain.accounting.relations.budget.command.glXref.AddGlBudgetXref;
import com.skytala.eCommerce.domain.accounting.relations.budget.command.glXref.DeleteGlBudgetXref;
import com.skytala.eCommerce.domain.accounting.relations.budget.command.glXref.UpdateGlBudgetXref;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.glXref.GlBudgetXrefAdded;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.glXref.GlBudgetXrefDeleted;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.glXref.GlBudgetXrefFound;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.glXref.GlBudgetXrefUpdated;
import com.skytala.eCommerce.domain.accounting.relations.budget.mapper.glXref.GlBudgetXrefMapper;
import com.skytala.eCommerce.domain.accounting.relations.budget.model.glXref.GlBudgetXref;
import com.skytala.eCommerce.domain.accounting.relations.budget.query.glXref.FindGlBudgetXrefsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/glBudgetXrefs")
public class GlBudgetXrefController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public GlBudgetXrefController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a GlBudgetXref
	 * @return a List with the GlBudgetXrefs
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findGlBudgetXrefsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindGlBudgetXrefsBy query = new FindGlBudgetXrefsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<GlBudgetXref> glBudgetXrefs =((GlBudgetXrefFound) Scheduler.execute(query).data()).getGlBudgetXrefs();

		if (glBudgetXrefs.size() == 1) {
			return ResponseEntity.ok().body(glBudgetXrefs.get(0));
		}

		return ResponseEntity.ok().body(glBudgetXrefs);

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
	public ResponseEntity<Object> createGlBudgetXref(HttpServletRequest request) throws Exception {

		GlBudgetXref glBudgetXrefToBeAdded = new GlBudgetXref();
		try {
			glBudgetXrefToBeAdded = GlBudgetXrefMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createGlBudgetXref(glBudgetXrefToBeAdded);

	}

	/**
	 * creates a new GlBudgetXref entry in the ofbiz database
	 * 
	 * @param glBudgetXrefToBeAdded
	 *            the GlBudgetXref thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createGlBudgetXref(@RequestBody GlBudgetXref glBudgetXrefToBeAdded) throws Exception {

		AddGlBudgetXref command = new AddGlBudgetXref(glBudgetXrefToBeAdded);
		GlBudgetXref glBudgetXref = ((GlBudgetXrefAdded) Scheduler.execute(command).data()).getAddedGlBudgetXref();
		
		if (glBudgetXref != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(glBudgetXref);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("GlBudgetXref could not be created.");
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
	public boolean updateGlBudgetXref(HttpServletRequest request) throws Exception {

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

		GlBudgetXref glBudgetXrefToBeUpdated = new GlBudgetXref();

		try {
			glBudgetXrefToBeUpdated = GlBudgetXrefMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateGlBudgetXref(glBudgetXrefToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the GlBudgetXref with the specific Id
	 * 
	 * @param glBudgetXrefToBeUpdated
	 *            the GlBudgetXref thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateGlBudgetXref(@RequestBody GlBudgetXref glBudgetXrefToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		glBudgetXrefToBeUpdated.setnull(null);

		UpdateGlBudgetXref command = new UpdateGlBudgetXref(glBudgetXrefToBeUpdated);

		try {
			if(((GlBudgetXrefUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{glBudgetXrefId}")
	public ResponseEntity<Object> findById(@PathVariable String glBudgetXrefId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("glBudgetXrefId", glBudgetXrefId);
		try {

			Object foundGlBudgetXref = findGlBudgetXrefsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundGlBudgetXref);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{glBudgetXrefId}")
	public ResponseEntity<Object> deleteGlBudgetXrefByIdUpdated(@PathVariable String glBudgetXrefId) throws Exception {
		DeleteGlBudgetXref command = new DeleteGlBudgetXref(glBudgetXrefId);

		try {
			if (((GlBudgetXrefDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("GlBudgetXref could not be deleted");

	}

}
