package com.skytala.eCommerce.domain.accounting.relations.glReconciliation.control.entry;

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
import com.skytala.eCommerce.domain.accounting.relations.glReconciliation.command.entry.AddGlReconciliationEntry;
import com.skytala.eCommerce.domain.accounting.relations.glReconciliation.command.entry.DeleteGlReconciliationEntry;
import com.skytala.eCommerce.domain.accounting.relations.glReconciliation.command.entry.UpdateGlReconciliationEntry;
import com.skytala.eCommerce.domain.accounting.relations.glReconciliation.event.entry.GlReconciliationEntryAdded;
import com.skytala.eCommerce.domain.accounting.relations.glReconciliation.event.entry.GlReconciliationEntryDeleted;
import com.skytala.eCommerce.domain.accounting.relations.glReconciliation.event.entry.GlReconciliationEntryFound;
import com.skytala.eCommerce.domain.accounting.relations.glReconciliation.event.entry.GlReconciliationEntryUpdated;
import com.skytala.eCommerce.domain.accounting.relations.glReconciliation.mapper.entry.GlReconciliationEntryMapper;
import com.skytala.eCommerce.domain.accounting.relations.glReconciliation.model.entry.GlReconciliationEntry;
import com.skytala.eCommerce.domain.accounting.relations.glReconciliation.query.entry.FindGlReconciliationEntrysBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/glReconciliation/glReconciliationEntrys")
public class GlReconciliationEntryController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public GlReconciliationEntryController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a GlReconciliationEntry
	 * @return a List with the GlReconciliationEntrys
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<GlReconciliationEntry>> findGlReconciliationEntrysBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindGlReconciliationEntrysBy query = new FindGlReconciliationEntrysBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<GlReconciliationEntry> glReconciliationEntrys =((GlReconciliationEntryFound) Scheduler.execute(query).data()).getGlReconciliationEntrys();

		return ResponseEntity.ok().body(glReconciliationEntrys);

	}

	/**
	 * creates a new GlReconciliationEntry entry in the ofbiz database
	 * 
	 * @param glReconciliationEntryToBeAdded
	 *            the GlReconciliationEntry thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<GlReconciliationEntry> createGlReconciliationEntry(@RequestBody GlReconciliationEntry glReconciliationEntryToBeAdded) throws Exception {

		AddGlReconciliationEntry command = new AddGlReconciliationEntry(glReconciliationEntryToBeAdded);
		GlReconciliationEntry glReconciliationEntry = ((GlReconciliationEntryAdded) Scheduler.execute(command).data()).getAddedGlReconciliationEntry();
		
		if (glReconciliationEntry != null) 
			return successful(glReconciliationEntry);
		else 
			return conflict(null);
	}

	/**
	 * Updates the GlReconciliationEntry with the specific Id
	 * 
	 * @param glReconciliationEntryToBeUpdated
	 *            the GlReconciliationEntry thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{acctgTransEntrySeqId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateGlReconciliationEntry(@RequestBody GlReconciliationEntry glReconciliationEntryToBeUpdated,
			@PathVariable String acctgTransEntrySeqId) throws Exception {

		glReconciliationEntryToBeUpdated.setAcctgTransEntrySeqId(acctgTransEntrySeqId);

		UpdateGlReconciliationEntry command = new UpdateGlReconciliationEntry(glReconciliationEntryToBeUpdated);

		try {
			if(((GlReconciliationEntryUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{glReconciliationEntryId}")
	public ResponseEntity<GlReconciliationEntry> findById(@PathVariable String glReconciliationEntryId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("glReconciliationEntryId", glReconciliationEntryId);
		try {

			List<GlReconciliationEntry> foundGlReconciliationEntry = findGlReconciliationEntrysBy(requestParams).getBody();
			if(foundGlReconciliationEntry.size()==1){				return successful(foundGlReconciliationEntry.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{glReconciliationEntryId}")
	public ResponseEntity<String> deleteGlReconciliationEntryByIdUpdated(@PathVariable String glReconciliationEntryId) throws Exception {
		DeleteGlReconciliationEntry command = new DeleteGlReconciliationEntry(glReconciliationEntryId);

		try {
			if (((GlReconciliationEntryDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
