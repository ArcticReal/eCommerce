package com.skytala.eCommerce.domain.acctgTransType;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Splitter;
import com.skytala.eCommerce.domain.acctgTransType.command.AddAcctgTransType;
import com.skytala.eCommerce.domain.acctgTransType.command.DeleteAcctgTransType;
import com.skytala.eCommerce.domain.acctgTransType.command.UpdateAcctgTransType;
import com.skytala.eCommerce.domain.acctgTransType.event.AcctgTransTypeAdded;
import com.skytala.eCommerce.domain.acctgTransType.event.AcctgTransTypeDeleted;
import com.skytala.eCommerce.domain.acctgTransType.event.AcctgTransTypeFound;
import com.skytala.eCommerce.domain.acctgTransType.event.AcctgTransTypeUpdated;
import com.skytala.eCommerce.domain.acctgTransType.mapper.AcctgTransTypeMapper;
import com.skytala.eCommerce.domain.acctgTransType.model.AcctgTransType;
import com.skytala.eCommerce.domain.acctgTransType.query.FindAcctgTransTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/acctgTransTypes")
public class AcctgTransTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public AcctgTransTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a AcctgTransType
	 * @return a List with the AcctgTransTypes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findAcctgTransTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindAcctgTransTypesBy query = new FindAcctgTransTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<AcctgTransType> acctgTransTypes =((AcctgTransTypeFound) Scheduler.execute(query).data()).getAcctgTransTypes();

		if (acctgTransTypes.size() == 1) {
			return ResponseEntity.ok().body(acctgTransTypes.get(0));
		}

		return ResponseEntity.ok().body(acctgTransTypes);

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
	public ResponseEntity<Object> createAcctgTransType(HttpServletRequest request) throws Exception {

		AcctgTransType acctgTransTypeToBeAdded = new AcctgTransType();
		try {
			acctgTransTypeToBeAdded = AcctgTransTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createAcctgTransType(acctgTransTypeToBeAdded);

	}

	/**
	 * creates a new AcctgTransType entry in the ofbiz database
	 * 
	 * @param acctgTransTypeToBeAdded
	 *            the AcctgTransType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createAcctgTransType(@RequestBody AcctgTransType acctgTransTypeToBeAdded) throws Exception {

		AddAcctgTransType command = new AddAcctgTransType(acctgTransTypeToBeAdded);
		AcctgTransType acctgTransType = ((AcctgTransTypeAdded) Scheduler.execute(command).data()).getAddedAcctgTransType();
		
		if (acctgTransType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(acctgTransType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("AcctgTransType could not be created.");
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
	public boolean updateAcctgTransType(HttpServletRequest request) throws Exception {

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

		AcctgTransType acctgTransTypeToBeUpdated = new AcctgTransType();

		try {
			acctgTransTypeToBeUpdated = AcctgTransTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateAcctgTransType(acctgTransTypeToBeUpdated, acctgTransTypeToBeUpdated.getAcctgTransTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the AcctgTransType with the specific Id
	 * 
	 * @param acctgTransTypeToBeUpdated
	 *            the AcctgTransType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{acctgTransTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateAcctgTransType(@RequestBody AcctgTransType acctgTransTypeToBeUpdated,
			@PathVariable String acctgTransTypeId) throws Exception {

		acctgTransTypeToBeUpdated.setAcctgTransTypeId(acctgTransTypeId);

		UpdateAcctgTransType command = new UpdateAcctgTransType(acctgTransTypeToBeUpdated);

		try {
			if(((AcctgTransTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{acctgTransTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String acctgTransTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("acctgTransTypeId", acctgTransTypeId);
		try {

			Object foundAcctgTransType = findAcctgTransTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundAcctgTransType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{acctgTransTypeId}")
	public ResponseEntity<Object> deleteAcctgTransTypeByIdUpdated(@PathVariable String acctgTransTypeId) throws Exception {
		DeleteAcctgTransType command = new DeleteAcctgTransType(acctgTransTypeId);

		try {
			if (((AcctgTransTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("AcctgTransType could not be deleted");

	}

	@RequestMapping(value = (" ** "))
	public ResponseEntity<Object> returnErrorPage(HttpServletRequest request) {

		String usedUri = request.getRequestURI();
		String[] splittedString = usedUri.split("/");

		String usedRequest = splittedString[splittedString.length - 1];

		if (validRequests.containsKey(usedRequest)) {
			String returnVal = "Error: request method " + request.getMethod() + " not allowed for \"" + usedUri
					+ "\"!\n" + "Please use " + validRequests.get(usedRequest) + "!";

			return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(returnVal);
		}

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/acctgTransType/\" plus one of the following: "
				+ "";

		Set<String> keySet = validRequests.keySet();
		Iterator<String> it = keySet.iterator();

		while (it.hasNext()) {
			returnVal += "\"" + it.next() + "\"";
			if (it.hasNext())
				returnVal += ", ";
		}

		returnVal += "!";

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnVal);

	}
}
