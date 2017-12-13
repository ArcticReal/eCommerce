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
	public ResponseEntity<Object> findFinAccountTransTypeAttrsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindFinAccountTransTypeAttrsBy query = new FindFinAccountTransTypeAttrsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<FinAccountTransTypeAttr> finAccountTransTypeAttrs =((FinAccountTransTypeAttrFound) Scheduler.execute(query).data()).getFinAccountTransTypeAttrs();

		if (finAccountTransTypeAttrs.size() == 1) {
			return ResponseEntity.ok().body(finAccountTransTypeAttrs.get(0));
		}

		return ResponseEntity.ok().body(finAccountTransTypeAttrs);

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
	public ResponseEntity<Object> createFinAccountTransTypeAttr(HttpServletRequest request) throws Exception {

		FinAccountTransTypeAttr finAccountTransTypeAttrToBeAdded = new FinAccountTransTypeAttr();
		try {
			finAccountTransTypeAttrToBeAdded = FinAccountTransTypeAttrMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createFinAccountTransTypeAttr(finAccountTransTypeAttrToBeAdded);

	}

	/**
	 * creates a new FinAccountTransTypeAttr entry in the ofbiz database
	 * 
	 * @param finAccountTransTypeAttrToBeAdded
	 *            the FinAccountTransTypeAttr thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createFinAccountTransTypeAttr(@RequestBody FinAccountTransTypeAttr finAccountTransTypeAttrToBeAdded) throws Exception {

		AddFinAccountTransTypeAttr command = new AddFinAccountTransTypeAttr(finAccountTransTypeAttrToBeAdded);
		FinAccountTransTypeAttr finAccountTransTypeAttr = ((FinAccountTransTypeAttrAdded) Scheduler.execute(command).data()).getAddedFinAccountTransTypeAttr();
		
		if (finAccountTransTypeAttr != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(finAccountTransTypeAttr);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("FinAccountTransTypeAttr could not be created.");
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
	public boolean updateFinAccountTransTypeAttr(HttpServletRequest request) throws Exception {

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

		FinAccountTransTypeAttr finAccountTransTypeAttrToBeUpdated = new FinAccountTransTypeAttr();

		try {
			finAccountTransTypeAttrToBeUpdated = FinAccountTransTypeAttrMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateFinAccountTransTypeAttr(finAccountTransTypeAttrToBeUpdated, finAccountTransTypeAttrToBeUpdated.getAttrName()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateFinAccountTransTypeAttr(@RequestBody FinAccountTransTypeAttr finAccountTransTypeAttrToBeUpdated,
			@PathVariable String attrName) throws Exception {

		finAccountTransTypeAttrToBeUpdated.setAttrName(attrName);

		UpdateFinAccountTransTypeAttr command = new UpdateFinAccountTransTypeAttr(finAccountTransTypeAttrToBeUpdated);

		try {
			if(((FinAccountTransTypeAttrUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{finAccountTransTypeAttrId}")
	public ResponseEntity<Object> findById(@PathVariable String finAccountTransTypeAttrId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("finAccountTransTypeAttrId", finAccountTransTypeAttrId);
		try {

			Object foundFinAccountTransTypeAttr = findFinAccountTransTypeAttrsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundFinAccountTransTypeAttr);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{finAccountTransTypeAttrId}")
	public ResponseEntity<Object> deleteFinAccountTransTypeAttrByIdUpdated(@PathVariable String finAccountTransTypeAttrId) throws Exception {
		DeleteFinAccountTransTypeAttr command = new DeleteFinAccountTransTypeAttr(finAccountTransTypeAttrId);

		try {
			if (((FinAccountTransTypeAttrDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("FinAccountTransTypeAttr could not be deleted");

	}

}
