package com.skytala.eCommerce.domain.accounting.relations.acctgTrans.control.type;

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
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.command.type.AddAcctgTransType;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.command.type.DeleteAcctgTransType;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.command.type.UpdateAcctgTransType;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.event.type.AcctgTransTypeAdded;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.event.type.AcctgTransTypeDeleted;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.event.type.AcctgTransTypeFound;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.event.type.AcctgTransTypeUpdated;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.mapper.type.AcctgTransTypeMapper;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.model.type.AcctgTransType;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.query.type.FindAcctgTransTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/acctgTrans/acctgTransTypes")
public class AcctgTransTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public AcctgTransTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a AcctgTransType
	 * @return a List with the AcctgTransTypes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<AcctgTransType>> findAcctgTransTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindAcctgTransTypesBy query = new FindAcctgTransTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<AcctgTransType> acctgTransTypes =((AcctgTransTypeFound) Scheduler.execute(query).data()).getAcctgTransTypes();

		return ResponseEntity.ok().body(acctgTransTypes);

	}

	/**
	 * creates a new AcctgTransType entry in the ofbiz database
	 * 
	 * @param acctgTransTypeToBeAdded
	 *            the AcctgTransType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<AcctgTransType> createAcctgTransType(@RequestBody AcctgTransType acctgTransTypeToBeAdded) throws Exception {

		AddAcctgTransType command = new AddAcctgTransType(acctgTransTypeToBeAdded);
		AcctgTransType acctgTransType = ((AcctgTransTypeAdded) Scheduler.execute(command).data()).getAddedAcctgTransType();
		
		if (acctgTransType != null) 
			return successful(acctgTransType);
		else 
			return conflict(null);
	}

	/**
	 * Updates the AcctgTransType with the specific Id
	 * 
	 * @param acctgTransTypeToBeUpdated
	 *            the AcctgTransType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{acctgTransTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateAcctgTransType(@RequestBody AcctgTransType acctgTransTypeToBeUpdated,
			@PathVariable String acctgTransTypeId) throws Exception {

		acctgTransTypeToBeUpdated.setAcctgTransTypeId(acctgTransTypeId);

		UpdateAcctgTransType command = new UpdateAcctgTransType(acctgTransTypeToBeUpdated);

		try {
			if(((AcctgTransTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{acctgTransTypeId}")
	public ResponseEntity<AcctgTransType> findById(@PathVariable String acctgTransTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("acctgTransTypeId", acctgTransTypeId);
		try {

			List<AcctgTransType> foundAcctgTransType = findAcctgTransTypesBy(requestParams).getBody();
			if(foundAcctgTransType.size()==1){				return successful(foundAcctgTransType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{acctgTransTypeId}")
	public ResponseEntity<String> deleteAcctgTransTypeByIdUpdated(@PathVariable String acctgTransTypeId) throws Exception {
		DeleteAcctgTransType command = new DeleteAcctgTransType(acctgTransTypeId);

		try {
			if (((AcctgTransTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
