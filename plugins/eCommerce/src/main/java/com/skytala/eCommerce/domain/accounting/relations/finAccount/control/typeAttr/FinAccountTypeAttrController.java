package com.skytala.eCommerce.domain.accounting.relations.finAccount.control.typeAttr;

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
import com.skytala.eCommerce.domain.accounting.relations.finAccount.command.typeAttr.AddFinAccountTypeAttr;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.command.typeAttr.DeleteFinAccountTypeAttr;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.command.typeAttr.UpdateFinAccountTypeAttr;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.event.typeAttr.FinAccountTypeAttrAdded;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.event.typeAttr.FinAccountTypeAttrDeleted;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.event.typeAttr.FinAccountTypeAttrFound;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.event.typeAttr.FinAccountTypeAttrUpdated;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.mapper.typeAttr.FinAccountTypeAttrMapper;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.model.typeAttr.FinAccountTypeAttr;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.query.typeAttr.FindFinAccountTypeAttrsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/finAccount/finAccountTypeAttrs")
public class FinAccountTypeAttrController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public FinAccountTypeAttrController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a FinAccountTypeAttr
	 * @return a List with the FinAccountTypeAttrs
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<FinAccountTypeAttr>> findFinAccountTypeAttrsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindFinAccountTypeAttrsBy query = new FindFinAccountTypeAttrsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<FinAccountTypeAttr> finAccountTypeAttrs =((FinAccountTypeAttrFound) Scheduler.execute(query).data()).getFinAccountTypeAttrs();

		return ResponseEntity.ok().body(finAccountTypeAttrs);

	}

	/**
	 * creates a new FinAccountTypeAttr entry in the ofbiz database
	 * 
	 * @param finAccountTypeAttrToBeAdded
	 *            the FinAccountTypeAttr thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<FinAccountTypeAttr> createFinAccountTypeAttr(@RequestBody FinAccountTypeAttr finAccountTypeAttrToBeAdded) throws Exception {

		AddFinAccountTypeAttr command = new AddFinAccountTypeAttr(finAccountTypeAttrToBeAdded);
		FinAccountTypeAttr finAccountTypeAttr = ((FinAccountTypeAttrAdded) Scheduler.execute(command).data()).getAddedFinAccountTypeAttr();
		
		if (finAccountTypeAttr != null) 
			return successful(finAccountTypeAttr);
		else 
			return conflict(null);
	}

	/**
	 * Updates the FinAccountTypeAttr with the specific Id
	 * 
	 * @param finAccountTypeAttrToBeUpdated
	 *            the FinAccountTypeAttr thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{attrName}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateFinAccountTypeAttr(@RequestBody FinAccountTypeAttr finAccountTypeAttrToBeUpdated,
			@PathVariable String attrName) throws Exception {

		finAccountTypeAttrToBeUpdated.setAttrName(attrName);

		UpdateFinAccountTypeAttr command = new UpdateFinAccountTypeAttr(finAccountTypeAttrToBeUpdated);

		try {
			if(((FinAccountTypeAttrUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{finAccountTypeAttrId}")
	public ResponseEntity<FinAccountTypeAttr> findById(@PathVariable String finAccountTypeAttrId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("finAccountTypeAttrId", finAccountTypeAttrId);
		try {

			List<FinAccountTypeAttr> foundFinAccountTypeAttr = findFinAccountTypeAttrsBy(requestParams).getBody();
			if(foundFinAccountTypeAttr.size()==1){				return successful(foundFinAccountTypeAttr.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{finAccountTypeAttrId}")
	public ResponseEntity<String> deleteFinAccountTypeAttrByIdUpdated(@PathVariable String finAccountTypeAttrId) throws Exception {
		DeleteFinAccountTypeAttr command = new DeleteFinAccountTypeAttr(finAccountTypeAttrId);

		try {
			if (((FinAccountTypeAttrDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
