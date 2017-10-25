package com.skytala.eCommerce.domain.accounting.relations.finAccount.control.status;

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
import com.skytala.eCommerce.domain.accounting.relations.finAccount.command.status.AddFinAccountStatus;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.command.status.DeleteFinAccountStatus;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.command.status.UpdateFinAccountStatus;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.event.status.FinAccountStatusAdded;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.event.status.FinAccountStatusDeleted;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.event.status.FinAccountStatusFound;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.event.status.FinAccountStatusUpdated;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.mapper.status.FinAccountStatusMapper;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.model.status.FinAccountStatus;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.query.status.FindFinAccountStatussBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/finAccountStatuss")
public class FinAccountStatusController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public FinAccountStatusController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a FinAccountStatus
	 * @return a List with the FinAccountStatuss
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findFinAccountStatussBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindFinAccountStatussBy query = new FindFinAccountStatussBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<FinAccountStatus> finAccountStatuss =((FinAccountStatusFound) Scheduler.execute(query).data()).getFinAccountStatuss();

		if (finAccountStatuss.size() == 1) {
			return ResponseEntity.ok().body(finAccountStatuss.get(0));
		}

		return ResponseEntity.ok().body(finAccountStatuss);

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
	public ResponseEntity<Object> createFinAccountStatus(HttpServletRequest request) throws Exception {

		FinAccountStatus finAccountStatusToBeAdded = new FinAccountStatus();
		try {
			finAccountStatusToBeAdded = FinAccountStatusMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createFinAccountStatus(finAccountStatusToBeAdded);

	}

	/**
	 * creates a new FinAccountStatus entry in the ofbiz database
	 * 
	 * @param finAccountStatusToBeAdded
	 *            the FinAccountStatus thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createFinAccountStatus(@RequestBody FinAccountStatus finAccountStatusToBeAdded) throws Exception {

		AddFinAccountStatus command = new AddFinAccountStatus(finAccountStatusToBeAdded);
		FinAccountStatus finAccountStatus = ((FinAccountStatusAdded) Scheduler.execute(command).data()).getAddedFinAccountStatus();
		
		if (finAccountStatus != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(finAccountStatus);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("FinAccountStatus could not be created.");
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
	public boolean updateFinAccountStatus(HttpServletRequest request) throws Exception {

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

		FinAccountStatus finAccountStatusToBeUpdated = new FinAccountStatus();

		try {
			finAccountStatusToBeUpdated = FinAccountStatusMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateFinAccountStatus(finAccountStatusToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the FinAccountStatus with the specific Id
	 * 
	 * @param finAccountStatusToBeUpdated
	 *            the FinAccountStatus thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateFinAccountStatus(@RequestBody FinAccountStatus finAccountStatusToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		finAccountStatusToBeUpdated.setnull(null);

		UpdateFinAccountStatus command = new UpdateFinAccountStatus(finAccountStatusToBeUpdated);

		try {
			if(((FinAccountStatusUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{finAccountStatusId}")
	public ResponseEntity<Object> findById(@PathVariable String finAccountStatusId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("finAccountStatusId", finAccountStatusId);
		try {

			Object foundFinAccountStatus = findFinAccountStatussBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundFinAccountStatus);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{finAccountStatusId}")
	public ResponseEntity<Object> deleteFinAccountStatusByIdUpdated(@PathVariable String finAccountStatusId) throws Exception {
		DeleteFinAccountStatus command = new DeleteFinAccountStatus(finAccountStatusId);

		try {
			if (((FinAccountStatusDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("FinAccountStatus could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/finAccountStatus/\" plus one of the following: "
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
