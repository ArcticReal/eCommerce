package com.skytala.eCommerce.domain.accounting.relations.finAccount.control.transTypeAttr;

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
import com.skytala.eCommerce.domain.accounting.relations.finAccount.command.transTypeAttr.AddFinAccountTransTypeAttr;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.command.transTypeAttr.DeleteFinAccountTransTypeAttr;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.command.transTypeAttr.UpdateFinAccountTransTypeAttr;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.event.transTypeAttr.FinAccountTransTypeAttrAdded;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.event.transTypeAttr.FinAccountTransTypeAttrDeleted;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.event.transTypeAttr.FinAccountTransTypeAttrFound;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.event.transTypeAttr.FinAccountTransTypeAttrUpdated;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.mapper.transTypeAttr.FinAccountTransTypeAttrMapper;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.model.transTypeAttr.FinAccountTransTypeAttr;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.query.transTypeAttr.FindFinAccountTransTypeAttrsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/finAccount/finAccountTransTypeAttrs")
public class FinAccountTransTypeAttrController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public FinAccountTransTypeAttrController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a FinAccountTransTypeAttr
	 * @return a List with the FinAccountTransTypeAttrs
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<FinAccountTransTypeAttr>> findFinAccountTransTypeAttrsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindFinAccountTransTypeAttrsBy query = new FindFinAccountTransTypeAttrsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<FinAccountTransTypeAttr> finAccountTransTypeAttrs =((FinAccountTransTypeAttrFound) Scheduler.execute(query).data()).getFinAccountTransTypeAttrs();

		return ResponseEntity.ok().body(finAccountTransTypeAttrs);

	}

	/**
	 * creates a new FinAccountTransTypeAttr entry in the ofbiz database
	 * 
	 * @param finAccountTransTypeAttrToBeAdded
	 *            the FinAccountTransTypeAttr thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<FinAccountTransTypeAttr> createFinAccountTransTypeAttr(@RequestBody FinAccountTransTypeAttr finAccountTransTypeAttrToBeAdded) throws Exception {

		AddFinAccountTransTypeAttr command = new AddFinAccountTransTypeAttr(finAccountTransTypeAttrToBeAdded);
		FinAccountTransTypeAttr finAccountTransTypeAttr = ((FinAccountTransTypeAttrAdded) Scheduler.execute(command).data()).getAddedFinAccountTransTypeAttr();
		
		if (finAccountTransTypeAttr != null) 
			return successful(finAccountTransTypeAttr);
		else 
			return conflict(null);
	}

	/**
	 * Updates the FinAccountTransTypeAttr with the specific Id
	 * 
	 * @param finAccountTransTypeAttrToBeUpdated
	 *            the FinAccountTransTypeAttr thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{attrName}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateFinAccountTransTypeAttr(@RequestBody FinAccountTransTypeAttr finAccountTransTypeAttrToBeUpdated,
			@PathVariable String attrName) throws Exception {

		finAccountTransTypeAttrToBeUpdated.setAttrName(attrName);

		UpdateFinAccountTransTypeAttr command = new UpdateFinAccountTransTypeAttr(finAccountTransTypeAttrToBeUpdated);

		try {
			if(((FinAccountTransTypeAttrUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{finAccountTransTypeAttrId}")
	public ResponseEntity<FinAccountTransTypeAttr> findById(@PathVariable String finAccountTransTypeAttrId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("finAccountTransTypeAttrId", finAccountTransTypeAttrId);
		try {

			List<FinAccountTransTypeAttr> foundFinAccountTransTypeAttr = findFinAccountTransTypeAttrsBy(requestParams).getBody();
			if(foundFinAccountTransTypeAttr.size()==1){				return successful(foundFinAccountTransTypeAttr.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{finAccountTransTypeAttrId}")
	public ResponseEntity<String> deleteFinAccountTransTypeAttrByIdUpdated(@PathVariable String finAccountTransTypeAttrId) throws Exception {
		DeleteFinAccountTransTypeAttr command = new DeleteFinAccountTransTypeAttr(finAccountTransTypeAttrId);

		try {
			if (((FinAccountTransTypeAttrDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
