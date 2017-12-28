package com.skytala.eCommerce.domain.accounting.relations.acctgTrans;

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
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.command.AddAcctgTrans;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.command.DeleteAcctgTrans;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.command.UpdateAcctgTrans;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.event.AcctgTransAdded;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.event.AcctgTransDeleted;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.event.AcctgTransFound;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.event.AcctgTransUpdated;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.mapper.AcctgTransMapper;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.model.AcctgTrans;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.query.FindAcctgTranssBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/acctgTranss")
public class AcctgTransController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public AcctgTransController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a AcctgTrans
	 * @return a List with the AcctgTranss
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<AcctgTrans>> findAcctgTranssBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindAcctgTranssBy query = new FindAcctgTranssBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<AcctgTrans> acctgTranss =((AcctgTransFound) Scheduler.execute(query).data()).getAcctgTranss();

		return ResponseEntity.ok().body(acctgTranss);

	}

	/**
	 * creates a new AcctgTrans entry in the ofbiz database
	 * 
	 * @param acctgTransToBeAdded
	 *            the AcctgTrans thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<AcctgTrans> createAcctgTrans(@RequestBody AcctgTrans acctgTransToBeAdded) throws Exception {

		AddAcctgTrans command = new AddAcctgTrans(acctgTransToBeAdded);
		AcctgTrans acctgTrans = ((AcctgTransAdded) Scheduler.execute(command).data()).getAddedAcctgTrans();
		
		if (acctgTrans != null) 
			return successful(acctgTrans);
		else 
			return conflict(null);
	}

	/**
	 * Updates the AcctgTrans with the specific Id
	 * 
	 * @param acctgTransToBeUpdated
	 *            the AcctgTrans thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{acctgTransId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateAcctgTrans(@RequestBody AcctgTrans acctgTransToBeUpdated,
			@PathVariable String acctgTransId) throws Exception {

		acctgTransToBeUpdated.setAcctgTransId(acctgTransId);

		UpdateAcctgTrans command = new UpdateAcctgTrans(acctgTransToBeUpdated);

		try {
			if(((AcctgTransUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{acctgTransId}")
	public ResponseEntity<AcctgTrans> findById(@PathVariable String acctgTransId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("acctgTransId", acctgTransId);
		try {

			List<AcctgTrans> foundAcctgTrans = findAcctgTranssBy(requestParams).getBody();
			if(foundAcctgTrans.size()==1){				return successful(foundAcctgTrans.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{acctgTransId}")
	public ResponseEntity<String> deleteAcctgTransByIdUpdated(@PathVariable String acctgTransId) throws Exception {
		DeleteAcctgTrans command = new DeleteAcctgTrans(acctgTransId);

		try {
			if (((AcctgTransDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
