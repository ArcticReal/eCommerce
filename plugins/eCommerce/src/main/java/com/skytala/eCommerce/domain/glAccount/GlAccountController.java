package com.skytala.eCommerce.domain.glAccount;

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
import com.skytala.eCommerce.domain.glAccount.command.AddGlAccount;
import com.skytala.eCommerce.domain.glAccount.command.DeleteGlAccount;
import com.skytala.eCommerce.domain.glAccount.command.UpdateGlAccount;
import com.skytala.eCommerce.domain.glAccount.event.GlAccountAdded;
import com.skytala.eCommerce.domain.glAccount.event.GlAccountDeleted;
import com.skytala.eCommerce.domain.glAccount.event.GlAccountFound;
import com.skytala.eCommerce.domain.glAccount.event.GlAccountUpdated;
import com.skytala.eCommerce.domain.glAccount.mapper.GlAccountMapper;
import com.skytala.eCommerce.domain.glAccount.model.GlAccount;
import com.skytala.eCommerce.domain.glAccount.query.FindGlAccountsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/glAccounts")
public class GlAccountController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public GlAccountController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a GlAccount
	 * @return a List with the GlAccounts
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findGlAccountsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindGlAccountsBy query = new FindGlAccountsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<GlAccount> glAccounts =((GlAccountFound) Scheduler.execute(query).data()).getGlAccounts();

		if (glAccounts.size() == 1) {
			return ResponseEntity.ok().body(glAccounts.get(0));
		}

		return ResponseEntity.ok().body(glAccounts);

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
	public ResponseEntity<Object> createGlAccount(HttpServletRequest request) throws Exception {

		GlAccount glAccountToBeAdded = new GlAccount();
		try {
			glAccountToBeAdded = GlAccountMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createGlAccount(glAccountToBeAdded);

	}

	/**
	 * creates a new GlAccount entry in the ofbiz database
	 * 
	 * @param glAccountToBeAdded
	 *            the GlAccount thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createGlAccount(@RequestBody GlAccount glAccountToBeAdded) throws Exception {

		AddGlAccount command = new AddGlAccount(glAccountToBeAdded);
		GlAccount glAccount = ((GlAccountAdded) Scheduler.execute(command).data()).getAddedGlAccount();
		
		if (glAccount != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(glAccount);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("GlAccount could not be created.");
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
	public boolean updateGlAccount(HttpServletRequest request) throws Exception {

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

		GlAccount glAccountToBeUpdated = new GlAccount();

		try {
			glAccountToBeUpdated = GlAccountMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateGlAccount(glAccountToBeUpdated, glAccountToBeUpdated.getGlAccountId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the GlAccount with the specific Id
	 * 
	 * @param glAccountToBeUpdated
	 *            the GlAccount thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{glAccountId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateGlAccount(@RequestBody GlAccount glAccountToBeUpdated,
			@PathVariable String glAccountId) throws Exception {

		glAccountToBeUpdated.setGlAccountId(glAccountId);

		UpdateGlAccount command = new UpdateGlAccount(glAccountToBeUpdated);

		try {
			if(((GlAccountUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{glAccountId}")
	public ResponseEntity<Object> findById(@PathVariable String glAccountId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("glAccountId", glAccountId);
		try {

			Object foundGlAccount = findGlAccountsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundGlAccount);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{glAccountId}")
	public ResponseEntity<Object> deleteGlAccountByIdUpdated(@PathVariable String glAccountId) throws Exception {
		DeleteGlAccount command = new DeleteGlAccount(glAccountId);

		try {
			if (((GlAccountDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("GlAccount could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/glAccount/\" plus one of the following: "
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
