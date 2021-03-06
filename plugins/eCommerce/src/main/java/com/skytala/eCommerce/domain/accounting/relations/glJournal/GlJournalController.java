package com.skytala.eCommerce.domain.accounting.relations.glJournal;

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
import com.skytala.eCommerce.domain.accounting.relations.glJournal.command.AddGlJournal;
import com.skytala.eCommerce.domain.accounting.relations.glJournal.command.DeleteGlJournal;
import com.skytala.eCommerce.domain.accounting.relations.glJournal.command.UpdateGlJournal;
import com.skytala.eCommerce.domain.accounting.relations.glJournal.event.GlJournalAdded;
import com.skytala.eCommerce.domain.accounting.relations.glJournal.event.GlJournalDeleted;
import com.skytala.eCommerce.domain.accounting.relations.glJournal.event.GlJournalFound;
import com.skytala.eCommerce.domain.accounting.relations.glJournal.event.GlJournalUpdated;
import com.skytala.eCommerce.domain.accounting.relations.glJournal.mapper.GlJournalMapper;
import com.skytala.eCommerce.domain.accounting.relations.glJournal.model.GlJournal;
import com.skytala.eCommerce.domain.accounting.relations.glJournal.query.FindGlJournalsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/glJournals")
public class GlJournalController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public GlJournalController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a GlJournal
	 * @return a List with the GlJournals
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<GlJournal>> findGlJournalsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindGlJournalsBy query = new FindGlJournalsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<GlJournal> glJournals =((GlJournalFound) Scheduler.execute(query).data()).getGlJournals();

		return ResponseEntity.ok().body(glJournals);

	}

	/**
	 * creates a new GlJournal entry in the ofbiz database
	 * 
	 * @param glJournalToBeAdded
	 *            the GlJournal thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<GlJournal> createGlJournal(@RequestBody GlJournal glJournalToBeAdded) throws Exception {

		AddGlJournal command = new AddGlJournal(glJournalToBeAdded);
		GlJournal glJournal = ((GlJournalAdded) Scheduler.execute(command).data()).getAddedGlJournal();
		
		if (glJournal != null) 
			return successful(glJournal);
		else 
			return conflict(null);
	}

	/**
	 * Updates the GlJournal with the specific Id
	 * 
	 * @param glJournalToBeUpdated
	 *            the GlJournal thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{glJournalId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateGlJournal(@RequestBody GlJournal glJournalToBeUpdated,
			@PathVariable String glJournalId) throws Exception {

		glJournalToBeUpdated.setGlJournalId(glJournalId);

		UpdateGlJournal command = new UpdateGlJournal(glJournalToBeUpdated);

		try {
			if(((GlJournalUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{glJournalId}")
	public ResponseEntity<GlJournal> findById(@PathVariable String glJournalId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("glJournalId", glJournalId);
		try {

			List<GlJournal> foundGlJournal = findGlJournalsBy(requestParams).getBody();
			if(foundGlJournal.size()==1){				return successful(foundGlJournal.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{glJournalId}")
	public ResponseEntity<String> deleteGlJournalByIdUpdated(@PathVariable String glJournalId) throws Exception {
		DeleteGlJournal command = new DeleteGlJournal(glJournalId);

		try {
			if (((GlJournalDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
