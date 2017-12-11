package com.skytala.eCommerce.domain.accounting.relations.finAccount.control.type;

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
import com.skytala.eCommerce.domain.accounting.relations.finAccount.command.type.AddFinAccountType;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.command.type.DeleteFinAccountType;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.command.type.UpdateFinAccountType;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.event.type.FinAccountTypeAdded;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.event.type.FinAccountTypeDeleted;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.event.type.FinAccountTypeFound;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.event.type.FinAccountTypeUpdated;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.mapper.type.FinAccountTypeMapper;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.model.type.FinAccountType;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.query.type.FindFinAccountTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/accounting/finAccount/finAccountTypes")
public class FinAccountTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public FinAccountTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a FinAccountType
	 * @return a List with the FinAccountTypes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findFinAccountTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindFinAccountTypesBy query = new FindFinAccountTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<FinAccountType> finAccountTypes =((FinAccountTypeFound) Scheduler.execute(query).data()).getFinAccountTypes();

		if (finAccountTypes.size() == 1) {
			return ResponseEntity.ok().body(finAccountTypes.get(0));
		}

		return ResponseEntity.ok().body(finAccountTypes);

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
	public ResponseEntity<Object> createFinAccountType(HttpServletRequest request) throws Exception {

		FinAccountType finAccountTypeToBeAdded = new FinAccountType();
		try {
			finAccountTypeToBeAdded = FinAccountTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createFinAccountType(finAccountTypeToBeAdded);

	}

	/**
	 * creates a new FinAccountType entry in the ofbiz database
	 * 
	 * @param finAccountTypeToBeAdded
	 *            the FinAccountType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createFinAccountType(@RequestBody FinAccountType finAccountTypeToBeAdded) throws Exception {

		AddFinAccountType command = new AddFinAccountType(finAccountTypeToBeAdded);
		FinAccountType finAccountType = ((FinAccountTypeAdded) Scheduler.execute(command).data()).getAddedFinAccountType();
		
		if (finAccountType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(finAccountType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("FinAccountType could not be created.");
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
	public boolean updateFinAccountType(HttpServletRequest request) throws Exception {

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

		FinAccountType finAccountTypeToBeUpdated = new FinAccountType();

		try {
			finAccountTypeToBeUpdated = FinAccountTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateFinAccountType(finAccountTypeToBeUpdated, finAccountTypeToBeUpdated.getFinAccountTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the FinAccountType with the specific Id
	 * 
	 * @param finAccountTypeToBeUpdated
	 *            the FinAccountType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{finAccountTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateFinAccountType(@RequestBody FinAccountType finAccountTypeToBeUpdated,
			@PathVariable String finAccountTypeId) throws Exception {

		finAccountTypeToBeUpdated.setFinAccountTypeId(finAccountTypeId);

		UpdateFinAccountType command = new UpdateFinAccountType(finAccountTypeToBeUpdated);

		try {
			if(((FinAccountTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{finAccountTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String finAccountTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("finAccountTypeId", finAccountTypeId);
		try {

			Object foundFinAccountType = findFinAccountTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundFinAccountType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{finAccountTypeId}")
	public ResponseEntity<Object> deleteFinAccountTypeByIdUpdated(@PathVariable String finAccountTypeId) throws Exception {
		DeleteFinAccountType command = new DeleteFinAccountType(finAccountTypeId);

		try {
			if (((FinAccountTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("FinAccountType could not be deleted");

	}

}
