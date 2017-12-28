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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/glReconciliations")
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
	@GetMapping("/find")
	public ResponseEntity<List<GlReconciliation>> findGlReconciliationsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindGlReconciliationsBy query = new FindGlReconciliationsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<GlReconciliation> glReconciliations =((GlReconciliationFound) Scheduler.execute(query).data()).getGlReconciliations();

		return ResponseEntity.ok().body(glReconciliations);

	}

	/**
	 * creates a new GlReconciliation entry in the ofbiz database
	 * 
	 * @param glReconciliationToBeAdded
	 *            the GlReconciliation thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<GlReconciliation> createGlReconciliation(@RequestBody GlReconciliation glReconciliationToBeAdded) throws Exception {

		AddGlReconciliation command = new AddGlReconciliation(glReconciliationToBeAdded);
		GlReconciliation glReconciliation = ((GlReconciliationAdded) Scheduler.execute(command).data()).getAddedGlReconciliation();
		
		if (glReconciliation != null) 
			return successful(glReconciliation);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateGlReconciliation(@RequestBody GlReconciliation glReconciliationToBeUpdated,
			@PathVariable String glReconciliationId) throws Exception {

		glReconciliationToBeUpdated.setGlReconciliationId(glReconciliationId);

		UpdateGlReconciliation command = new UpdateGlReconciliation(glReconciliationToBeUpdated);

		try {
			if(((GlReconciliationUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{glReconciliationId}")
	public ResponseEntity<GlReconciliation> findById(@PathVariable String glReconciliationId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("glReconciliationId", glReconciliationId);
		try {

			List<GlReconciliation> foundGlReconciliation = findGlReconciliationsBy(requestParams).getBody();
			if(foundGlReconciliation.size()==1){				return successful(foundGlReconciliation.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{glReconciliationId}")
	public ResponseEntity<String> deleteGlReconciliationByIdUpdated(@PathVariable String glReconciliationId) throws Exception {
		DeleteGlReconciliation command = new DeleteGlReconciliation(glReconciliationId);

		try {
			if (((GlReconciliationDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
