package com.skytala.eCommerce.domain.order.relations.quoteRole;

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
import com.skytala.eCommerce.domain.order.relations.quoteRole.command.AddQuoteRole;
import com.skytala.eCommerce.domain.order.relations.quoteRole.command.DeleteQuoteRole;
import com.skytala.eCommerce.domain.order.relations.quoteRole.command.UpdateQuoteRole;
import com.skytala.eCommerce.domain.order.relations.quoteRole.event.QuoteRoleAdded;
import com.skytala.eCommerce.domain.order.relations.quoteRole.event.QuoteRoleDeleted;
import com.skytala.eCommerce.domain.order.relations.quoteRole.event.QuoteRoleFound;
import com.skytala.eCommerce.domain.order.relations.quoteRole.event.QuoteRoleUpdated;
import com.skytala.eCommerce.domain.order.relations.quoteRole.mapper.QuoteRoleMapper;
import com.skytala.eCommerce.domain.order.relations.quoteRole.model.QuoteRole;
import com.skytala.eCommerce.domain.order.relations.quoteRole.query.FindQuoteRolesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/quoteRoles")
public class QuoteRoleController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public QuoteRoleController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a QuoteRole
	 * @return a List with the QuoteRoles
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findQuoteRolesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindQuoteRolesBy query = new FindQuoteRolesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<QuoteRole> quoteRoles =((QuoteRoleFound) Scheduler.execute(query).data()).getQuoteRoles();

		if (quoteRoles.size() == 1) {
			return ResponseEntity.ok().body(quoteRoles.get(0));
		}

		return ResponseEntity.ok().body(quoteRoles);

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
	public ResponseEntity<Object> createQuoteRole(HttpServletRequest request) throws Exception {

		QuoteRole quoteRoleToBeAdded = new QuoteRole();
		try {
			quoteRoleToBeAdded = QuoteRoleMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createQuoteRole(quoteRoleToBeAdded);

	}

	/**
	 * creates a new QuoteRole entry in the ofbiz database
	 * 
	 * @param quoteRoleToBeAdded
	 *            the QuoteRole thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createQuoteRole(@RequestBody QuoteRole quoteRoleToBeAdded) throws Exception {

		AddQuoteRole command = new AddQuoteRole(quoteRoleToBeAdded);
		QuoteRole quoteRole = ((QuoteRoleAdded) Scheduler.execute(command).data()).getAddedQuoteRole();
		
		if (quoteRole != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(quoteRole);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("QuoteRole could not be created.");
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
	public boolean updateQuoteRole(HttpServletRequest request) throws Exception {

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

		QuoteRole quoteRoleToBeUpdated = new QuoteRole();

		try {
			quoteRoleToBeUpdated = QuoteRoleMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateQuoteRole(quoteRoleToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the QuoteRole with the specific Id
	 * 
	 * @param quoteRoleToBeUpdated
	 *            the QuoteRole thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateQuoteRole(@RequestBody QuoteRole quoteRoleToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		quoteRoleToBeUpdated.setnull(null);

		UpdateQuoteRole command = new UpdateQuoteRole(quoteRoleToBeUpdated);

		try {
			if(((QuoteRoleUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{quoteRoleId}")
	public ResponseEntity<Object> findById(@PathVariable String quoteRoleId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("quoteRoleId", quoteRoleId);
		try {

			Object foundQuoteRole = findQuoteRolesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundQuoteRole);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{quoteRoleId}")
	public ResponseEntity<Object> deleteQuoteRoleByIdUpdated(@PathVariable String quoteRoleId) throws Exception {
		DeleteQuoteRole command = new DeleteQuoteRole(quoteRoleId);

		try {
			if (((QuoteRoleDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("QuoteRole could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/quoteRole/\" plus one of the following: "
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
