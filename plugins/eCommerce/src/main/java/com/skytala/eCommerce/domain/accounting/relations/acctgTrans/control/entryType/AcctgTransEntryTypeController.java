package com.skytala.eCommerce.domain.accounting.relations.acctgTrans.control.entryType;

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
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.command.entryType.AddAcctgTransEntryType;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.command.entryType.DeleteAcctgTransEntryType;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.command.entryType.UpdateAcctgTransEntryType;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.event.entryType.AcctgTransEntryTypeAdded;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.event.entryType.AcctgTransEntryTypeDeleted;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.event.entryType.AcctgTransEntryTypeFound;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.event.entryType.AcctgTransEntryTypeUpdated;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.mapper.entryType.AcctgTransEntryTypeMapper;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.model.entryType.AcctgTransEntryType;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.query.entryType.FindAcctgTransEntryTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/accounting/acctgTrans/acctgTransEntryTypes")
public class AcctgTransEntryTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public AcctgTransEntryTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a AcctgTransEntryType
	 * @return a List with the AcctgTransEntryTypes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findAcctgTransEntryTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindAcctgTransEntryTypesBy query = new FindAcctgTransEntryTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<AcctgTransEntryType> acctgTransEntryTypes =((AcctgTransEntryTypeFound) Scheduler.execute(query).data()).getAcctgTransEntryTypes();

		if (acctgTransEntryTypes.size() == 1) {
			return ResponseEntity.ok().body(acctgTransEntryTypes.get(0));
		}

		return ResponseEntity.ok().body(acctgTransEntryTypes);

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
	public ResponseEntity<Object> createAcctgTransEntryType(HttpServletRequest request) throws Exception {

		AcctgTransEntryType acctgTransEntryTypeToBeAdded = new AcctgTransEntryType();
		try {
			acctgTransEntryTypeToBeAdded = AcctgTransEntryTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createAcctgTransEntryType(acctgTransEntryTypeToBeAdded);

	}

	/**
	 * creates a new AcctgTransEntryType entry in the ofbiz database
	 * 
	 * @param acctgTransEntryTypeToBeAdded
	 *            the AcctgTransEntryType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createAcctgTransEntryType(@RequestBody AcctgTransEntryType acctgTransEntryTypeToBeAdded) throws Exception {

		AddAcctgTransEntryType command = new AddAcctgTransEntryType(acctgTransEntryTypeToBeAdded);
		AcctgTransEntryType acctgTransEntryType = ((AcctgTransEntryTypeAdded) Scheduler.execute(command).data()).getAddedAcctgTransEntryType();
		
		if (acctgTransEntryType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(acctgTransEntryType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("AcctgTransEntryType could not be created.");
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
	public boolean updateAcctgTransEntryType(HttpServletRequest request) throws Exception {

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

		AcctgTransEntryType acctgTransEntryTypeToBeUpdated = new AcctgTransEntryType();

		try {
			acctgTransEntryTypeToBeUpdated = AcctgTransEntryTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateAcctgTransEntryType(acctgTransEntryTypeToBeUpdated, acctgTransEntryTypeToBeUpdated.getAcctgTransEntryTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the AcctgTransEntryType with the specific Id
	 * 
	 * @param acctgTransEntryTypeToBeUpdated
	 *            the AcctgTransEntryType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{acctgTransEntryTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateAcctgTransEntryType(@RequestBody AcctgTransEntryType acctgTransEntryTypeToBeUpdated,
			@PathVariable String acctgTransEntryTypeId) throws Exception {

		acctgTransEntryTypeToBeUpdated.setAcctgTransEntryTypeId(acctgTransEntryTypeId);

		UpdateAcctgTransEntryType command = new UpdateAcctgTransEntryType(acctgTransEntryTypeToBeUpdated);

		try {
			if(((AcctgTransEntryTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{acctgTransEntryTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String acctgTransEntryTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("acctgTransEntryTypeId", acctgTransEntryTypeId);
		try {

			Object foundAcctgTransEntryType = findAcctgTransEntryTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundAcctgTransEntryType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{acctgTransEntryTypeId}")
	public ResponseEntity<Object> deleteAcctgTransEntryTypeByIdUpdated(@PathVariable String acctgTransEntryTypeId) throws Exception {
		DeleteAcctgTransEntryType command = new DeleteAcctgTransEntryType(acctgTransEntryTypeId);

		try {
			if (((AcctgTransEntryTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("AcctgTransEntryType could not be deleted");

	}

}
