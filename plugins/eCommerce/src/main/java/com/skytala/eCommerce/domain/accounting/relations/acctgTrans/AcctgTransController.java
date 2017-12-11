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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findAcctgTranssBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindAcctgTranssBy query = new FindAcctgTranssBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<AcctgTrans> acctgTranss =((AcctgTransFound) Scheduler.execute(query).data()).getAcctgTranss();

		if (acctgTranss.size() == 1) {
			return ResponseEntity.ok().body(acctgTranss.get(0));
		}

		return ResponseEntity.ok().body(acctgTranss);

	}

	/**
	 * 
	 * this method will only be called by Springs DispatcherServlet
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createAcctgTrans(HttpServletRequest request) throws Exception {

		AcctgTrans acctgTransToBeAdded = new AcctgTrans();
		try {
			acctgTransToBeAdded = AcctgTransMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createAcctgTrans(acctgTransToBeAdded);

	}

	/**
	 * creates a new AcctgTrans entry in the ofbiz database
	 * 
	 * @param acctgTransToBeAdded
	 *            the AcctgTrans thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createAcctgTrans(@RequestBody AcctgTrans acctgTransToBeAdded) throws Exception {

		AddAcctgTrans command = new AddAcctgTrans(acctgTransToBeAdded);
		AcctgTrans acctgTrans = ((AcctgTransAdded) Scheduler.execute(command).data()).getAddedAcctgTrans();
		
		if (acctgTrans != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(acctgTrans);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("AcctgTrans could not be created.");
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
	@RequestMapping(method = RequestMethod.PUT, value = "/update", consumes = "application/x-www-form-urlencoded")
	public boolean updateAcctgTrans(HttpServletRequest request) throws Exception {

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

		AcctgTrans acctgTransToBeUpdated = new AcctgTrans();

		try {
			acctgTransToBeUpdated = AcctgTransMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateAcctgTrans(acctgTransToBeUpdated, acctgTransToBeUpdated.getAcctgTransId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateAcctgTrans(@RequestBody AcctgTrans acctgTransToBeUpdated,
			@PathVariable String acctgTransId) throws Exception {

		acctgTransToBeUpdated.setAcctgTransId(acctgTransId);

		UpdateAcctgTrans command = new UpdateAcctgTrans(acctgTransToBeUpdated);

		try {
			if(((AcctgTransUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{acctgTransId}")
	public ResponseEntity<Object> findById(@PathVariable String acctgTransId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("acctgTransId", acctgTransId);
		try {

			Object foundAcctgTrans = findAcctgTranssBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundAcctgTrans);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{acctgTransId}")
	public ResponseEntity<Object> deleteAcctgTransByIdUpdated(@PathVariable String acctgTransId) throws Exception {
		DeleteAcctgTrans command = new DeleteAcctgTrans(acctgTransId);

		try {
			if (((AcctgTransDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("AcctgTrans could not be deleted");

	}

}
