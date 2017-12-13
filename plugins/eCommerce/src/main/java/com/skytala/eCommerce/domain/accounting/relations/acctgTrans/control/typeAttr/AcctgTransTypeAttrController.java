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
	public ResponseEntity<Object> findAcctgTransTypeAttrsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindAcctgTransTypeAttrsBy query = new FindAcctgTransTypeAttrsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<AcctgTransTypeAttr> acctgTransTypeAttrs =((AcctgTransTypeAttrFound) Scheduler.execute(query).data()).getAcctgTransTypeAttrs();

		if (acctgTransTypeAttrs.size() == 1) {
			return ResponseEntity.ok().body(acctgTransTypeAttrs.get(0));
		}

		return ResponseEntity.ok().body(acctgTransTypeAttrs);

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
	public ResponseEntity<Object> createAcctgTransTypeAttr(HttpServletRequest request) throws Exception {

		AcctgTransTypeAttr acctgTransTypeAttrToBeAdded = new AcctgTransTypeAttr();
		try {
			acctgTransTypeAttrToBeAdded = AcctgTransTypeAttrMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createAcctgTransTypeAttr(acctgTransTypeAttrToBeAdded);

	}

	/**
	 * creates a new AcctgTransTypeAttr entry in the ofbiz database
	 * 
	 * @param acctgTransTypeAttrToBeAdded
	 *            the AcctgTransTypeAttr thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createAcctgTransTypeAttr(@RequestBody AcctgTransTypeAttr acctgTransTypeAttrToBeAdded) throws Exception {

		AddAcctgTransTypeAttr command = new AddAcctgTransTypeAttr(acctgTransTypeAttrToBeAdded);
		AcctgTransTypeAttr acctgTransTypeAttr = ((AcctgTransTypeAttrAdded) Scheduler.execute(command).data()).getAddedAcctgTransTypeAttr();
		
		if (acctgTransTypeAttr != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(acctgTransTypeAttr);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("AcctgTransTypeAttr could not be created.");
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
	@PutMapping(value = "/update", consumes = "application/x-www-form-urlencoded")
	public boolean updateAcctgTransTypeAttr(HttpServletRequest request) throws Exception {

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

		AcctgTransTypeAttr acctgTransTypeAttrToBeUpdated = new AcctgTransTypeAttr();

		try {
			acctgTransTypeAttrToBeUpdated = AcctgTransTypeAttrMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateAcctgTransTypeAttr(acctgTransTypeAttrToBeUpdated, acctgTransTypeAttrToBeUpdated.getAttrName()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateAcctgTransTypeAttr(@RequestBody AcctgTransTypeAttr acctgTransTypeAttrToBeUpdated,
			@PathVariable String attrName) throws Exception {

		acctgTransTypeAttrToBeUpdated.setAttrName(attrName);

		UpdateAcctgTransTypeAttr command = new UpdateAcctgTransTypeAttr(acctgTransTypeAttrToBeUpdated);

		try {
			if(((AcctgTransTypeAttrUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{acctgTransTypeAttrId}")
	public ResponseEntity<Object> findById(@PathVariable String acctgTransTypeAttrId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("acctgTransTypeAttrId", acctgTransTypeAttrId);
		try {

			Object foundAcctgTransTypeAttr = findAcctgTransTypeAttrsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundAcctgTransTypeAttr);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{acctgTransTypeAttrId}")
	public ResponseEntity<Object> deleteAcctgTransTypeAttrByIdUpdated(@PathVariable String acctgTransTypeAttrId) throws Exception {
		DeleteAcctgTransTypeAttr command = new DeleteAcctgTransTypeAttr(acctgTransTypeAttrId);

		try {
			if (((AcctgTransTypeAttrDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("AcctgTransTypeAttr could not be deleted");

	}

}
