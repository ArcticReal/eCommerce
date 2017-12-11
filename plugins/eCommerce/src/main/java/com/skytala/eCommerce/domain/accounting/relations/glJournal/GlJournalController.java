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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<Object> findGlJournalsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindGlJournalsBy query = new FindGlJournalsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<GlJournal> glJournals =((GlJournalFound) Scheduler.execute(query).data()).getGlJournals();

		if (glJournals.size() == 1) {
			return ResponseEntity.ok().body(glJournals.get(0));
		}

		return ResponseEntity.ok().body(glJournals);

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
	public ResponseEntity<Object> createGlJournal(HttpServletRequest request) throws Exception {

		GlJournal glJournalToBeAdded = new GlJournal();
		try {
			glJournalToBeAdded = GlJournalMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createGlJournal(glJournalToBeAdded);

	}

	/**
	 * creates a new GlJournal entry in the ofbiz database
	 * 
	 * @param glJournalToBeAdded
	 *            the GlJournal thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createGlJournal(@RequestBody GlJournal glJournalToBeAdded) throws Exception {

		AddGlJournal command = new AddGlJournal(glJournalToBeAdded);
		GlJournal glJournal = ((GlJournalAdded) Scheduler.execute(command).data()).getAddedGlJournal();
		
		if (glJournal != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(glJournal);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("GlJournal could not be created.");
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
	@PutMapping(value = "/update", consumes = "application/x-www-form-urlencoded")
	public boolean updateGlJournal(HttpServletRequest request) throws Exception {

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

		GlJournal glJournalToBeUpdated = new GlJournal();

		try {
			glJournalToBeUpdated = GlJournalMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateGlJournal(glJournalToBeUpdated, glJournalToBeUpdated.getGlJournalId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateGlJournal(@RequestBody GlJournal glJournalToBeUpdated,
			@PathVariable String glJournalId) throws Exception {

		glJournalToBeUpdated.setGlJournalId(glJournalId);

		UpdateGlJournal command = new UpdateGlJournal(glJournalToBeUpdated);

		try {
			if(((GlJournalUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{glJournalId}")
	public ResponseEntity<Object> findById(@PathVariable String glJournalId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("glJournalId", glJournalId);
		try {

			Object foundGlJournal = findGlJournalsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundGlJournal);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{glJournalId}")
	public ResponseEntity<Object> deleteGlJournalByIdUpdated(@PathVariable String glJournalId) throws Exception {
		DeleteGlJournal command = new DeleteGlJournal(glJournalId);

		try {
			if (((GlJournalDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("GlJournal could not be deleted");

	}

}
