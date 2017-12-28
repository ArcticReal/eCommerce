package com.skytala.eCommerce.domain.accounting.relations.finAccount.control.trans;

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
import com.skytala.eCommerce.domain.accounting.relations.finAccount.command.trans.AddFinAccountTrans;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.command.trans.DeleteFinAccountTrans;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.command.trans.UpdateFinAccountTrans;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.event.trans.FinAccountTransAdded;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.event.trans.FinAccountTransDeleted;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.event.trans.FinAccountTransFound;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.event.trans.FinAccountTransUpdated;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.mapper.trans.FinAccountTransMapper;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.model.trans.FinAccountTrans;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.query.trans.FindFinAccountTranssBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/finAccount/finAccountTranss")
public class FinAccountTransController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public FinAccountTransController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a FinAccountTrans
	 * @return a List with the FinAccountTranss
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<FinAccountTrans>> findFinAccountTranssBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindFinAccountTranssBy query = new FindFinAccountTranssBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<FinAccountTrans> finAccountTranss =((FinAccountTransFound) Scheduler.execute(query).data()).getFinAccountTranss();

		return ResponseEntity.ok().body(finAccountTranss);

	}

	/**
	 * creates a new FinAccountTrans entry in the ofbiz database
	 * 
	 * @param finAccountTransToBeAdded
	 *            the FinAccountTrans thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<FinAccountTrans> createFinAccountTrans(@RequestBody FinAccountTrans finAccountTransToBeAdded) throws Exception {

		AddFinAccountTrans command = new AddFinAccountTrans(finAccountTransToBeAdded);
		FinAccountTrans finAccountTrans = ((FinAccountTransAdded) Scheduler.execute(command).data()).getAddedFinAccountTrans();
		
		if (finAccountTrans != null) 
			return successful(finAccountTrans);
		else 
			return conflict(null);
	}

	/**
	 * Updates the FinAccountTrans with the specific Id
	 * 
	 * @param finAccountTransToBeUpdated
	 *            the FinAccountTrans thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{finAccountTransId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateFinAccountTrans(@RequestBody FinAccountTrans finAccountTransToBeUpdated,
			@PathVariable String finAccountTransId) throws Exception {

		finAccountTransToBeUpdated.setFinAccountTransId(finAccountTransId);

		UpdateFinAccountTrans command = new UpdateFinAccountTrans(finAccountTransToBeUpdated);

		try {
			if(((FinAccountTransUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{finAccountTransId}")
	public ResponseEntity<FinAccountTrans> findById(@PathVariable String finAccountTransId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("finAccountTransId", finAccountTransId);
		try {

			List<FinAccountTrans> foundFinAccountTrans = findFinAccountTranssBy(requestParams).getBody();
			if(foundFinAccountTrans.size()==1){				return successful(foundFinAccountTrans.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{finAccountTransId}")
	public ResponseEntity<String> deleteFinAccountTransByIdUpdated(@PathVariable String finAccountTransId) throws Exception {
		DeleteFinAccountTrans command = new DeleteFinAccountTrans(finAccountTransId);

		try {
			if (((FinAccountTransDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
