package com.skytala.eCommerce.domain.accounting.relations.acctgTrans.control.entryType;

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
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.command.entryType.AddAcctgTransEntryType;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.command.entryType.DeleteAcctgTransEntryType;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.command.entryType.UpdateAcctgTransEntryType;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.event.entryType.AcctgTransEntryTypeAdded;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.event.entryType.AcctgTransEntryTypeDeleted;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.event.entryType.AcctgTransEntryTypeFound;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.event.entryType.AcctgTransEntryTypeUpdated;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.mapper.entryType.AcctgTransEntryTypeMapper;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.model.entryType.AcctgTransEntryType;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.query.entryType.FindAcctgTransEntryTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/acctgTrans/acctgTransEntryTypes")
public class AcctgTransEntryTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public AcctgTransEntryTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a AcctgTransEntryType
	 * @return a List with the AcctgTransEntryTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<AcctgTransEntryType>> findAcctgTransEntryTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindAcctgTransEntryTypesBy query = new FindAcctgTransEntryTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<AcctgTransEntryType> acctgTransEntryTypes =((AcctgTransEntryTypeFound) Scheduler.execute(query).data()).getAcctgTransEntryTypes();

		return ResponseEntity.ok().body(acctgTransEntryTypes);

	}

	/**
	 * creates a new AcctgTransEntryType entry in the ofbiz database
	 * 
	 * @param acctgTransEntryTypeToBeAdded
	 *            the AcctgTransEntryType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<AcctgTransEntryType> createAcctgTransEntryType(@RequestBody AcctgTransEntryType acctgTransEntryTypeToBeAdded) throws Exception {

		AddAcctgTransEntryType command = new AddAcctgTransEntryType(acctgTransEntryTypeToBeAdded);
		AcctgTransEntryType acctgTransEntryType = ((AcctgTransEntryTypeAdded) Scheduler.execute(command).data()).getAddedAcctgTransEntryType();
		
		if (acctgTransEntryType != null) 
			return successful(acctgTransEntryType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the AcctgTransEntryType with the specific Id
	 * 
	 * @param acctgTransEntryTypeToBeUpdated
	 *            the AcctgTransEntryType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{acctgTransEntryTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateAcctgTransEntryType(@RequestBody AcctgTransEntryType acctgTransEntryTypeToBeUpdated,
			@PathVariable String acctgTransEntryTypeId) throws Exception {

		acctgTransEntryTypeToBeUpdated.setAcctgTransEntryTypeId(acctgTransEntryTypeId);

		UpdateAcctgTransEntryType command = new UpdateAcctgTransEntryType(acctgTransEntryTypeToBeUpdated);

		try {
			if(((AcctgTransEntryTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{acctgTransEntryTypeId}")
	public ResponseEntity<AcctgTransEntryType> findById(@PathVariable String acctgTransEntryTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("acctgTransEntryTypeId", acctgTransEntryTypeId);
		try {

			List<AcctgTransEntryType> foundAcctgTransEntryType = findAcctgTransEntryTypesBy(requestParams).getBody();
			if(foundAcctgTransEntryType.size()==1){				return successful(foundAcctgTransEntryType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{acctgTransEntryTypeId}")
	public ResponseEntity<String> deleteAcctgTransEntryTypeByIdUpdated(@PathVariable String acctgTransEntryTypeId) throws Exception {
		DeleteAcctgTransEntryType command = new DeleteAcctgTransEntryType(acctgTransEntryTypeId);

		try {
			if (((AcctgTransEntryTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
