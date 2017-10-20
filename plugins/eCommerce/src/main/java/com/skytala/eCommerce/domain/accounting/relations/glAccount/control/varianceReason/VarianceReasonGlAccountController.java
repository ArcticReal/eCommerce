package com.skytala.eCommerce.domain.accounting.relations.glAccount.control.varianceReason;

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
import com.skytala.eCommerce.domain.accounting.relations.glAccount.command.varianceReason.AddVarianceReasonGlAccount;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.command.varianceReason.DeleteVarianceReasonGlAccount;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.command.varianceReason.UpdateVarianceReasonGlAccount;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.varianceReason.VarianceReasonGlAccountAdded;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.varianceReason.VarianceReasonGlAccountDeleted;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.varianceReason.VarianceReasonGlAccountFound;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.varianceReason.VarianceReasonGlAccountUpdated;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.mapper.varianceReason.VarianceReasonGlAccountMapper;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.varianceReason.VarianceReasonGlAccount;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.query.varianceReason.FindVarianceReasonGlAccountsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@CrossOrigin
@RequestMapping("/varianceReasonGlAccounts")
public class VarianceReasonGlAccountController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public VarianceReasonGlAccountController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a VarianceReasonGlAccount
	 * @return a List with the VarianceReasonGlAccounts
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findVarianceReasonGlAccountsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindVarianceReasonGlAccountsBy query = new FindVarianceReasonGlAccountsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<VarianceReasonGlAccount> varianceReasonGlAccounts =((VarianceReasonGlAccountFound) Scheduler.execute(query).data()).getVarianceReasonGlAccounts();

		if (varianceReasonGlAccounts.size() == 1) {
			return ResponseEntity.ok().body(varianceReasonGlAccounts.get(0));
		}

		return ResponseEntity.ok().body(varianceReasonGlAccounts);

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
	public ResponseEntity<Object> createVarianceReasonGlAccount(HttpServletRequest request) throws Exception {

		VarianceReasonGlAccount varianceReasonGlAccountToBeAdded = new VarianceReasonGlAccount();
		try {
			varianceReasonGlAccountToBeAdded = VarianceReasonGlAccountMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createVarianceReasonGlAccount(varianceReasonGlAccountToBeAdded);

	}

	/**
	 * creates a new VarianceReasonGlAccount entry in the ofbiz database
	 * 
	 * @param varianceReasonGlAccountToBeAdded
	 *            the VarianceReasonGlAccount thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createVarianceReasonGlAccount(@RequestBody VarianceReasonGlAccount varianceReasonGlAccountToBeAdded) throws Exception {

		AddVarianceReasonGlAccount command = new AddVarianceReasonGlAccount(varianceReasonGlAccountToBeAdded);
		VarianceReasonGlAccount varianceReasonGlAccount = ((VarianceReasonGlAccountAdded) Scheduler.execute(command).data()).getAddedVarianceReasonGlAccount();
		
		if (varianceReasonGlAccount != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(varianceReasonGlAccount);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("VarianceReasonGlAccount could not be created.");
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
	public boolean updateVarianceReasonGlAccount(HttpServletRequest request) throws Exception {

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

		VarianceReasonGlAccount varianceReasonGlAccountToBeUpdated = new VarianceReasonGlAccount();

		try {
			varianceReasonGlAccountToBeUpdated = VarianceReasonGlAccountMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateVarianceReasonGlAccount(varianceReasonGlAccountToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the VarianceReasonGlAccount with the specific Id
	 * 
	 * @param varianceReasonGlAccountToBeUpdated
	 *            the VarianceReasonGlAccount thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateVarianceReasonGlAccount(@RequestBody VarianceReasonGlAccount varianceReasonGlAccountToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		varianceReasonGlAccountToBeUpdated.setnull(null);

		UpdateVarianceReasonGlAccount command = new UpdateVarianceReasonGlAccount(varianceReasonGlAccountToBeUpdated);

		try {
			if(((VarianceReasonGlAccountUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{varianceReasonGlAccountId}")
	public ResponseEntity<Object> findById(@PathVariable String varianceReasonGlAccountId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("varianceReasonGlAccountId", varianceReasonGlAccountId);
		try {

			Object foundVarianceReasonGlAccount = findVarianceReasonGlAccountsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundVarianceReasonGlAccount);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{varianceReasonGlAccountId}")
	public ResponseEntity<Object> deleteVarianceReasonGlAccountByIdUpdated(@PathVariable String varianceReasonGlAccountId) throws Exception {
		DeleteVarianceReasonGlAccount command = new DeleteVarianceReasonGlAccount(varianceReasonGlAccountId);

		try {
			if (((VarianceReasonGlAccountDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("VarianceReasonGlAccount could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/varianceReasonGlAccount/\" plus one of the following: "
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
