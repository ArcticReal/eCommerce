package com.skytala.eCommerce.domain.accounting.relations.glAccount.control.history;

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
import com.skytala.eCommerce.domain.accounting.relations.glAccount.command.history.AddGlAccountHistory;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.command.history.DeleteGlAccountHistory;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.command.history.UpdateGlAccountHistory;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.history.GlAccountHistoryAdded;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.history.GlAccountHistoryDeleted;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.history.GlAccountHistoryFound;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.history.GlAccountHistoryUpdated;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.mapper.history.GlAccountHistoryMapper;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.history.GlAccountHistory;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.query.history.FindGlAccountHistorysBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/glAccountHistorys")
public class GlAccountHistoryController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public GlAccountHistoryController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a GlAccountHistory
	 * @return a List with the GlAccountHistorys
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findGlAccountHistorysBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindGlAccountHistorysBy query = new FindGlAccountHistorysBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<GlAccountHistory> glAccountHistorys =((GlAccountHistoryFound) Scheduler.execute(query).data()).getGlAccountHistorys();

		if (glAccountHistorys.size() == 1) {
			return ResponseEntity.ok().body(glAccountHistorys.get(0));
		}

		return ResponseEntity.ok().body(glAccountHistorys);

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
	public ResponseEntity<Object> createGlAccountHistory(HttpServletRequest request) throws Exception {

		GlAccountHistory glAccountHistoryToBeAdded = new GlAccountHistory();
		try {
			glAccountHistoryToBeAdded = GlAccountHistoryMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createGlAccountHistory(glAccountHistoryToBeAdded);

	}

	/**
	 * creates a new GlAccountHistory entry in the ofbiz database
	 * 
	 * @param glAccountHistoryToBeAdded
	 *            the GlAccountHistory thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createGlAccountHistory(@RequestBody GlAccountHistory glAccountHistoryToBeAdded) throws Exception {

		AddGlAccountHistory command = new AddGlAccountHistory(glAccountHistoryToBeAdded);
		GlAccountHistory glAccountHistory = ((GlAccountHistoryAdded) Scheduler.execute(command).data()).getAddedGlAccountHistory();
		
		if (glAccountHistory != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(glAccountHistory);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("GlAccountHistory could not be created.");
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
	public boolean updateGlAccountHistory(HttpServletRequest request) throws Exception {

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

		GlAccountHistory glAccountHistoryToBeUpdated = new GlAccountHistory();

		try {
			glAccountHistoryToBeUpdated = GlAccountHistoryMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateGlAccountHistory(glAccountHistoryToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the GlAccountHistory with the specific Id
	 * 
	 * @param glAccountHistoryToBeUpdated
	 *            the GlAccountHistory thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateGlAccountHistory(@RequestBody GlAccountHistory glAccountHistoryToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		glAccountHistoryToBeUpdated.setnull(null);

		UpdateGlAccountHistory command = new UpdateGlAccountHistory(glAccountHistoryToBeUpdated);

		try {
			if(((GlAccountHistoryUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{glAccountHistoryId}")
	public ResponseEntity<Object> findById(@PathVariable String glAccountHistoryId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("glAccountHistoryId", glAccountHistoryId);
		try {

			Object foundGlAccountHistory = findGlAccountHistorysBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundGlAccountHistory);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{glAccountHistoryId}")
	public ResponseEntity<Object> deleteGlAccountHistoryByIdUpdated(@PathVariable String glAccountHistoryId) throws Exception {
		DeleteGlAccountHistory command = new DeleteGlAccountHistory(glAccountHistoryId);

		try {
			if (((GlAccountHistoryDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("GlAccountHistory could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/glAccountHistory/\" plus one of the following: "
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
