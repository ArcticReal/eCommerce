package com.skytala.eCommerce.domain.order.relations.quote.control.workEffort;

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
import com.skytala.eCommerce.domain.order.relations.quote.command.workEffort.AddQuoteWorkEffort;
import com.skytala.eCommerce.domain.order.relations.quote.command.workEffort.DeleteQuoteWorkEffort;
import com.skytala.eCommerce.domain.order.relations.quote.command.workEffort.UpdateQuoteWorkEffort;
import com.skytala.eCommerce.domain.order.relations.quote.event.workEffort.QuoteWorkEffortAdded;
import com.skytala.eCommerce.domain.order.relations.quote.event.workEffort.QuoteWorkEffortDeleted;
import com.skytala.eCommerce.domain.order.relations.quote.event.workEffort.QuoteWorkEffortFound;
import com.skytala.eCommerce.domain.order.relations.quote.event.workEffort.QuoteWorkEffortUpdated;
import com.skytala.eCommerce.domain.order.relations.quote.mapper.workEffort.QuoteWorkEffortMapper;
import com.skytala.eCommerce.domain.order.relations.quote.model.workEffort.QuoteWorkEffort;
import com.skytala.eCommerce.domain.order.relations.quote.query.workEffort.FindQuoteWorkEffortsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/quoteWorkEfforts")
public class QuoteWorkEffortController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public QuoteWorkEffortController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a QuoteWorkEffort
	 * @return a List with the QuoteWorkEfforts
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findQuoteWorkEffortsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindQuoteWorkEffortsBy query = new FindQuoteWorkEffortsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<QuoteWorkEffort> quoteWorkEfforts =((QuoteWorkEffortFound) Scheduler.execute(query).data()).getQuoteWorkEfforts();

		if (quoteWorkEfforts.size() == 1) {
			return ResponseEntity.ok().body(quoteWorkEfforts.get(0));
		}

		return ResponseEntity.ok().body(quoteWorkEfforts);

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
	public ResponseEntity<Object> createQuoteWorkEffort(HttpServletRequest request) throws Exception {

		QuoteWorkEffort quoteWorkEffortToBeAdded = new QuoteWorkEffort();
		try {
			quoteWorkEffortToBeAdded = QuoteWorkEffortMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createQuoteWorkEffort(quoteWorkEffortToBeAdded);

	}

	/**
	 * creates a new QuoteWorkEffort entry in the ofbiz database
	 * 
	 * @param quoteWorkEffortToBeAdded
	 *            the QuoteWorkEffort thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createQuoteWorkEffort(@RequestBody QuoteWorkEffort quoteWorkEffortToBeAdded) throws Exception {

		AddQuoteWorkEffort command = new AddQuoteWorkEffort(quoteWorkEffortToBeAdded);
		QuoteWorkEffort quoteWorkEffort = ((QuoteWorkEffortAdded) Scheduler.execute(command).data()).getAddedQuoteWorkEffort();
		
		if (quoteWorkEffort != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(quoteWorkEffort);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("QuoteWorkEffort could not be created.");
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
	public boolean updateQuoteWorkEffort(HttpServletRequest request) throws Exception {

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

		QuoteWorkEffort quoteWorkEffortToBeUpdated = new QuoteWorkEffort();

		try {
			quoteWorkEffortToBeUpdated = QuoteWorkEffortMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateQuoteWorkEffort(quoteWorkEffortToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the QuoteWorkEffort with the specific Id
	 * 
	 * @param quoteWorkEffortToBeUpdated
	 *            the QuoteWorkEffort thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateQuoteWorkEffort(@RequestBody QuoteWorkEffort quoteWorkEffortToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		quoteWorkEffortToBeUpdated.setnull(null);

		UpdateQuoteWorkEffort command = new UpdateQuoteWorkEffort(quoteWorkEffortToBeUpdated);

		try {
			if(((QuoteWorkEffortUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{quoteWorkEffortId}")
	public ResponseEntity<Object> findById(@PathVariable String quoteWorkEffortId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("quoteWorkEffortId", quoteWorkEffortId);
		try {

			Object foundQuoteWorkEffort = findQuoteWorkEffortsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundQuoteWorkEffort);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{quoteWorkEffortId}")
	public ResponseEntity<Object> deleteQuoteWorkEffortByIdUpdated(@PathVariable String quoteWorkEffortId) throws Exception {
		DeleteQuoteWorkEffort command = new DeleteQuoteWorkEffort(quoteWorkEffortId);

		try {
			if (((QuoteWorkEffortDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("QuoteWorkEffort could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/quoteWorkEffort/\" plus one of the following: "
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
