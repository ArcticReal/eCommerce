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
	public ResponseEntity<Object> findFinAccountTranssBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindFinAccountTranssBy query = new FindFinAccountTranssBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<FinAccountTrans> finAccountTranss =((FinAccountTransFound) Scheduler.execute(query).data()).getFinAccountTranss();

		if (finAccountTranss.size() == 1) {
			return ResponseEntity.ok().body(finAccountTranss.get(0));
		}

		return ResponseEntity.ok().body(finAccountTranss);

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
	public ResponseEntity<Object> createFinAccountTrans(HttpServletRequest request) throws Exception {

		FinAccountTrans finAccountTransToBeAdded = new FinAccountTrans();
		try {
			finAccountTransToBeAdded = FinAccountTransMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createFinAccountTrans(finAccountTransToBeAdded);

	}

	/**
	 * creates a new FinAccountTrans entry in the ofbiz database
	 * 
	 * @param finAccountTransToBeAdded
	 *            the FinAccountTrans thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createFinAccountTrans(@RequestBody FinAccountTrans finAccountTransToBeAdded) throws Exception {

		AddFinAccountTrans command = new AddFinAccountTrans(finAccountTransToBeAdded);
		FinAccountTrans finAccountTrans = ((FinAccountTransAdded) Scheduler.execute(command).data()).getAddedFinAccountTrans();
		
		if (finAccountTrans != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(finAccountTrans);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("FinAccountTrans could not be created.");
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
	public boolean updateFinAccountTrans(HttpServletRequest request) throws Exception {

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

		FinAccountTrans finAccountTransToBeUpdated = new FinAccountTrans();

		try {
			finAccountTransToBeUpdated = FinAccountTransMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateFinAccountTrans(finAccountTransToBeUpdated, finAccountTransToBeUpdated.getFinAccountTransId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateFinAccountTrans(@RequestBody FinAccountTrans finAccountTransToBeUpdated,
			@PathVariable String finAccountTransId) throws Exception {

		finAccountTransToBeUpdated.setFinAccountTransId(finAccountTransId);

		UpdateFinAccountTrans command = new UpdateFinAccountTrans(finAccountTransToBeUpdated);

		try {
			if(((FinAccountTransUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{finAccountTransId}")
	public ResponseEntity<Object> findById(@PathVariable String finAccountTransId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("finAccountTransId", finAccountTransId);
		try {

			Object foundFinAccountTrans = findFinAccountTranssBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundFinAccountTrans);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{finAccountTransId}")
	public ResponseEntity<Object> deleteFinAccountTransByIdUpdated(@PathVariable String finAccountTransId) throws Exception {
		DeleteFinAccountTrans command = new DeleteFinAccountTrans(finAccountTransId);

		try {
			if (((FinAccountTransDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("FinAccountTrans could not be deleted");

	}

}
