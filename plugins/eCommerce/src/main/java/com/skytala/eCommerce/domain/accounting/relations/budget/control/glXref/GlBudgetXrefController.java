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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/budget/glBudgetXrefs")
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
	@GetMapping("/find")
	public ResponseEntity<List<GlBudgetXref>> findGlBudgetXrefsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindGlBudgetXrefsBy query = new FindGlBudgetXrefsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<GlBudgetXref> glBudgetXrefs =((GlBudgetXrefFound) Scheduler.execute(query).data()).getGlBudgetXrefs();

		return ResponseEntity.ok().body(glBudgetXrefs);

	}

	/**
	 * creates a new GlBudgetXref entry in the ofbiz database
	 * 
	 * @param glBudgetXrefToBeAdded
	 *            the GlBudgetXref thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<GlBudgetXref> createGlBudgetXref(@RequestBody GlBudgetXref glBudgetXrefToBeAdded) throws Exception {

		AddGlBudgetXref command = new AddGlBudgetXref(glBudgetXrefToBeAdded);
		GlBudgetXref glBudgetXref = ((GlBudgetXrefAdded) Scheduler.execute(command).data()).getAddedGlBudgetXref();
		
		if (glBudgetXref != null) 
			return successful(glBudgetXref);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateGlBudgetXref(@RequestBody GlBudgetXref glBudgetXrefToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		glBudgetXrefToBeUpdated.setnull(null);

		UpdateGlBudgetXref command = new UpdateGlBudgetXref(glBudgetXrefToBeUpdated);

		try {
			if(((GlBudgetXrefUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{glBudgetXrefId}")
	public ResponseEntity<GlBudgetXref> findById(@PathVariable String glBudgetXrefId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("glBudgetXrefId", glBudgetXrefId);
		try {

			List<GlBudgetXref> foundGlBudgetXref = findGlBudgetXrefsBy(requestParams).getBody();
			if(foundGlBudgetXref.size()==1){				return successful(foundGlBudgetXref.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{glBudgetXrefId}")
	public ResponseEntity<String> deleteGlBudgetXrefByIdUpdated(@PathVariable String glBudgetXrefId) throws Exception {
		DeleteGlBudgetXref command = new DeleteGlBudgetXref(glBudgetXrefId);

		try {
			if (((GlBudgetXrefDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
