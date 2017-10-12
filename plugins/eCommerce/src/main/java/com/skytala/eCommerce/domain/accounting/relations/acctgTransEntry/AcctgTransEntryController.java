package com.skytala.eCommerce.domain.accounting.relations.acctgTransEntry;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Splitter;
import com.skytala.eCommerce.domain.accounting.relations.acctgTransEntry.command.AddAcctgTransEntry;
import com.skytala.eCommerce.domain.accounting.relations.acctgTransEntry.command.DeleteAcctgTransEntry;
import com.skytala.eCommerce.domain.accounting.relations.acctgTransEntry.command.UpdateAcctgTransEntry;
import com.skytala.eCommerce.domain.accounting.relations.acctgTransEntry.event.AcctgTransEntryAdded;
import com.skytala.eCommerce.domain.accounting.relations.acctgTransEntry.event.AcctgTransEntryDeleted;
import com.skytala.eCommerce.domain.accounting.relations.acctgTransEntry.event.AcctgTransEntryFound;
import com.skytala.eCommerce.domain.accounting.relations.acctgTransEntry.event.AcctgTransEntryUpdated;
import com.skytala.eCommerce.domain.accounting.relations.acctgTransEntry.mapper.AcctgTransEntryMapper;
import com.skytala.eCommerce.domain.accounting.relations.acctgTransEntry.model.AcctgTransEntry;
import com.skytala.eCommerce.domain.accounting.relations.acctgTransEntry.query.FindAcctgTransEntrysBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/acctgTransEntrys")
public class AcctgTransEntryController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public AcctgTransEntryController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a AcctgTransEntry
	 * @return a List with the AcctgTransEntrys
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findAcctgTransEntrysBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindAcctgTransEntrysBy query = new FindAcctgTransEntrysBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<AcctgTransEntry> acctgTransEntrys =((AcctgTransEntryFound) Scheduler.execute(query).data()).getAcctgTransEntrys();

		if (acctgTransEntrys.size() == 1) {
			return ResponseEntity.ok().body(acctgTransEntrys.get(0));
		}

		return ResponseEntity.ok().body(acctgTransEntrys);

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
	public ResponseEntity<Object> createAcctgTransEntry(HttpServletRequest request) throws Exception {

		AcctgTransEntry acctgTransEntryToBeAdded = new AcctgTransEntry();
		try {
			acctgTransEntryToBeAdded = AcctgTransEntryMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createAcctgTransEntry(acctgTransEntryToBeAdded);

	}

	/**
	 * creates a new AcctgTransEntry entry in the ofbiz database
	 * 
	 * @param acctgTransEntryToBeAdded
	 *            the AcctgTransEntry thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createAcctgTransEntry(@RequestBody AcctgTransEntry acctgTransEntryToBeAdded) throws Exception {

		AddAcctgTransEntry command = new AddAcctgTransEntry(acctgTransEntryToBeAdded);
		AcctgTransEntry acctgTransEntry = ((AcctgTransEntryAdded) Scheduler.execute(command).data()).getAddedAcctgTransEntry();
		
		if (acctgTransEntry != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(acctgTransEntry);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("AcctgTransEntry could not be created.");
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
	public boolean updateAcctgTransEntry(HttpServletRequest request) throws Exception {

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

		AcctgTransEntry acctgTransEntryToBeUpdated = new AcctgTransEntry();

		try {
			acctgTransEntryToBeUpdated = AcctgTransEntryMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateAcctgTransEntry(acctgTransEntryToBeUpdated, acctgTransEntryToBeUpdated.getAcctgTransEntrySeqId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the AcctgTransEntry with the specific Id
	 * 
	 * @param acctgTransEntryToBeUpdated
	 *            the AcctgTransEntry thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{acctgTransEntrySeqId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateAcctgTransEntry(@RequestBody AcctgTransEntry acctgTransEntryToBeUpdated,
			@PathVariable String acctgTransEntrySeqId) throws Exception {

		acctgTransEntryToBeUpdated.setAcctgTransEntrySeqId(acctgTransEntrySeqId);

		UpdateAcctgTransEntry command = new UpdateAcctgTransEntry(acctgTransEntryToBeUpdated);

		try {
			if(((AcctgTransEntryUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{acctgTransEntryId}")
	public ResponseEntity<Object> findById(@PathVariable String acctgTransEntryId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("acctgTransEntryId", acctgTransEntryId);
		try {

			Object foundAcctgTransEntry = findAcctgTransEntrysBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundAcctgTransEntry);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{acctgTransEntryId}")
	public ResponseEntity<Object> deleteAcctgTransEntryByIdUpdated(@PathVariable String acctgTransEntryId) throws Exception {
		DeleteAcctgTransEntry command = new DeleteAcctgTransEntry(acctgTransEntryId);

		try {
			if (((AcctgTransEntryDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("AcctgTransEntry could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/acctgTransEntry/\" plus one of the following: "
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
