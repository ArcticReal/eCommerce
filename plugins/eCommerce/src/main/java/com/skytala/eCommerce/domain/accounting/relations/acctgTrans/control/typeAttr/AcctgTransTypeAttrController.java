package com.skytala.eCommerce.domain.accounting.relations.acctgTrans.control.typeAttr;

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
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.command.typeAttr.AddAcctgTransTypeAttr;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.command.typeAttr.DeleteAcctgTransTypeAttr;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.command.typeAttr.UpdateAcctgTransTypeAttr;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.event.typeAttr.AcctgTransTypeAttrAdded;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.event.typeAttr.AcctgTransTypeAttrDeleted;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.event.typeAttr.AcctgTransTypeAttrFound;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.event.typeAttr.AcctgTransTypeAttrUpdated;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.mapper.typeAttr.AcctgTransTypeAttrMapper;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.model.typeAttr.AcctgTransTypeAttr;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.query.typeAttr.FindAcctgTransTypeAttrsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/acctgTrans/acctgTransTypeAttrs")
public class AcctgTransTypeAttrController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public AcctgTransTypeAttrController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a AcctgTransTypeAttr
	 * @return a List with the AcctgTransTypeAttrs
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<AcctgTransTypeAttr>> findAcctgTransTypeAttrsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindAcctgTransTypeAttrsBy query = new FindAcctgTransTypeAttrsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<AcctgTransTypeAttr> acctgTransTypeAttrs =((AcctgTransTypeAttrFound) Scheduler.execute(query).data()).getAcctgTransTypeAttrs();

		return ResponseEntity.ok().body(acctgTransTypeAttrs);

	}

	/**
	 * creates a new AcctgTransTypeAttr entry in the ofbiz database
	 * 
	 * @param acctgTransTypeAttrToBeAdded
	 *            the AcctgTransTypeAttr thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<AcctgTransTypeAttr> createAcctgTransTypeAttr(@RequestBody AcctgTransTypeAttr acctgTransTypeAttrToBeAdded) throws Exception {

		AddAcctgTransTypeAttr command = new AddAcctgTransTypeAttr(acctgTransTypeAttrToBeAdded);
		AcctgTransTypeAttr acctgTransTypeAttr = ((AcctgTransTypeAttrAdded) Scheduler.execute(command).data()).getAddedAcctgTransTypeAttr();
		
		if (acctgTransTypeAttr != null) 
			return successful(acctgTransTypeAttr);
		else 
			return conflict(null);
	}

	/**
	 * Updates the AcctgTransTypeAttr with the specific Id
	 * 
	 * @param acctgTransTypeAttrToBeUpdated
	 *            the AcctgTransTypeAttr thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{attrName}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateAcctgTransTypeAttr(@RequestBody AcctgTransTypeAttr acctgTransTypeAttrToBeUpdated,
			@PathVariable String attrName) throws Exception {

		acctgTransTypeAttrToBeUpdated.setAttrName(attrName);

		UpdateAcctgTransTypeAttr command = new UpdateAcctgTransTypeAttr(acctgTransTypeAttrToBeUpdated);

		try {
			if(((AcctgTransTypeAttrUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{acctgTransTypeAttrId}")
	public ResponseEntity<AcctgTransTypeAttr> findById(@PathVariable String acctgTransTypeAttrId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("acctgTransTypeAttrId", acctgTransTypeAttrId);
		try {

			List<AcctgTransTypeAttr> foundAcctgTransTypeAttr = findAcctgTransTypeAttrsBy(requestParams).getBody();
			if(foundAcctgTransTypeAttr.size()==1){				return successful(foundAcctgTransTypeAttr.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{acctgTransTypeAttrId}")
	public ResponseEntity<String> deleteAcctgTransTypeAttrByIdUpdated(@PathVariable String acctgTransTypeAttrId) throws Exception {
		DeleteAcctgTransTypeAttr command = new DeleteAcctgTransTypeAttr(acctgTransTypeAttrId);

		try {
			if (((AcctgTransTypeAttrDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
