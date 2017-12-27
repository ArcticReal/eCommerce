package com.skytala.eCommerce.domain.accounting.relations.acctgTrans.control.entry;

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
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.command.entry.AddAcctgTransEntry;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.command.entry.DeleteAcctgTransEntry;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.command.entry.UpdateAcctgTransEntry;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.event.entry.AcctgTransEntryAdded;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.event.entry.AcctgTransEntryDeleted;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.event.entry.AcctgTransEntryFound;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.event.entry.AcctgTransEntryUpdated;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.mapper.entry.AcctgTransEntryMapper;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.model.entry.AcctgTransEntry;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.query.entry.FindAcctgTransEntrysBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/acctgTrans/acctgTransEntrys")
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
	@GetMapping("/find")
	public ResponseEntity<List<AcctgTransEntry>> findAcctgTransEntrysBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindAcctgTransEntrysBy query = new FindAcctgTransEntrysBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<AcctgTransEntry> acctgTransEntrys =((AcctgTransEntryFound) Scheduler.execute(query).data()).getAcctgTransEntrys();

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
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<AcctgTransEntry> createAcctgTransEntry(HttpServletRequest request) throws Exception {

		AcctgTransEntry acctgTransEntryToBeAdded = new AcctgTransEntry();
		try {
			acctgTransEntryToBeAdded = AcctgTransEntryMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
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
	public ResponseEntity<AcctgTransEntry> createAcctgTransEntry(@RequestBody AcctgTransEntry acctgTransEntryToBeAdded) throws Exception {

		AddAcctgTransEntry command = new AddAcctgTransEntry(acctgTransEntryToBeAdded);
		AcctgTransEntry acctgTransEntry = ((AcctgTransEntryAdded) Scheduler.execute(command).data()).getAddedAcctgTransEntry();
		
		if (acctgTransEntry != null) 
			return successful(acctgTransEntry);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateAcctgTransEntry(@RequestBody AcctgTransEntry acctgTransEntryToBeUpdated,
			@PathVariable String acctgTransEntrySeqId) throws Exception {

		acctgTransEntryToBeUpdated.setAcctgTransEntrySeqId(acctgTransEntrySeqId);

		UpdateAcctgTransEntry command = new UpdateAcctgTransEntry(acctgTransEntryToBeUpdated);

		try {
			if(((AcctgTransEntryUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{acctgTransEntryId}")
	public ResponseEntity<AcctgTransEntry> findById(@PathVariable String acctgTransEntryId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("acctgTransEntryId", acctgTransEntryId);
		try {

			List<AcctgTransEntry> foundAcctgTransEntry = findAcctgTransEntrysBy(requestParams).getBody();
			if(foundAcctgTransEntry.size()==1){				return successful(foundAcctgTransEntry.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{acctgTransEntryId}")
	public ResponseEntity<String> deleteAcctgTransEntryByIdUpdated(@PathVariable String acctgTransEntryId) throws Exception {
		DeleteAcctgTransEntry command = new DeleteAcctgTransEntry(acctgTransEntryId);

		try {
			if (((AcctgTransEntryDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
