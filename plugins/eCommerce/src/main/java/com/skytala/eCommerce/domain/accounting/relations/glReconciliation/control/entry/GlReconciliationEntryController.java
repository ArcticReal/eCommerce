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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@CrossOrigin
@RequestMapping("/glReconciliationEntrys")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findGlReconciliationEntrysBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindGlReconciliationEntrysBy query = new FindGlReconciliationEntrysBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<GlReconciliationEntry> glReconciliationEntrys =((GlReconciliationEntryFound) Scheduler.execute(query).data()).getGlReconciliationEntrys();

		if (glReconciliationEntrys.size() == 1) {
			return ResponseEntity.ok().body(glReconciliationEntrys.get(0));
		}

		return ResponseEntity.ok().body(glReconciliationEntrys);

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
	public ResponseEntity<Object> createGlReconciliationEntry(HttpServletRequest request) throws Exception {

		GlReconciliationEntry glReconciliationEntryToBeAdded = new GlReconciliationEntry();
		try {
			glReconciliationEntryToBeAdded = GlReconciliationEntryMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createGlReconciliationEntry(glReconciliationEntryToBeAdded);

	}

	/**
	 * creates a new GlReconciliationEntry entry in the ofbiz database
	 * 
	 * @param glReconciliationEntryToBeAdded
	 *            the GlReconciliationEntry thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createGlReconciliationEntry(@RequestBody GlReconciliationEntry glReconciliationEntryToBeAdded) throws Exception {

		AddGlReconciliationEntry command = new AddGlReconciliationEntry(glReconciliationEntryToBeAdded);
		GlReconciliationEntry glReconciliationEntry = ((GlReconciliationEntryAdded) Scheduler.execute(command).data()).getAddedGlReconciliationEntry();
		
		if (glReconciliationEntry != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(glReconciliationEntry);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("GlReconciliationEntry could not be created.");
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
	public boolean updateGlReconciliationEntry(HttpServletRequest request) throws Exception {

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

		GlReconciliationEntry glReconciliationEntryToBeUpdated = new GlReconciliationEntry();

		try {
			glReconciliationEntryToBeUpdated = GlReconciliationEntryMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateGlReconciliationEntry(glReconciliationEntryToBeUpdated, glReconciliationEntryToBeUpdated.getAcctgTransEntrySeqId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateGlReconciliationEntry(@RequestBody GlReconciliationEntry glReconciliationEntryToBeUpdated,
			@PathVariable String acctgTransEntrySeqId) throws Exception {

		glReconciliationEntryToBeUpdated.setAcctgTransEntrySeqId(acctgTransEntrySeqId);

		UpdateGlReconciliationEntry command = new UpdateGlReconciliationEntry(glReconciliationEntryToBeUpdated);

		try {
			if(((GlReconciliationEntryUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{glReconciliationEntryId}")
	public ResponseEntity<Object> findById(@PathVariable String glReconciliationEntryId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("glReconciliationEntryId", glReconciliationEntryId);
		try {

			Object foundGlReconciliationEntry = findGlReconciliationEntrysBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundGlReconciliationEntry);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{glReconciliationEntryId}")
	public ResponseEntity<Object> deleteGlReconciliationEntryByIdUpdated(@PathVariable String glReconciliationEntryId) throws Exception {
		DeleteGlReconciliationEntry command = new DeleteGlReconciliationEntry(glReconciliationEntryId);

		try {
			if (((GlReconciliationEntryDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("GlReconciliationEntry could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/glReconciliationEntry/\" plus one of the following: "
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
