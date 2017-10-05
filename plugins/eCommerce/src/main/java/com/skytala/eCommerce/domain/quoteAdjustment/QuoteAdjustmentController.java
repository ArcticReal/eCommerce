package com.skytala.eCommerce.domain.quoteAdjustment;

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
import com.skytala.eCommerce.domain.quoteAdjustment.command.AddQuoteAdjustment;
import com.skytala.eCommerce.domain.quoteAdjustment.command.DeleteQuoteAdjustment;
import com.skytala.eCommerce.domain.quoteAdjustment.command.UpdateQuoteAdjustment;
import com.skytala.eCommerce.domain.quoteAdjustment.event.QuoteAdjustmentAdded;
import com.skytala.eCommerce.domain.quoteAdjustment.event.QuoteAdjustmentDeleted;
import com.skytala.eCommerce.domain.quoteAdjustment.event.QuoteAdjustmentFound;
import com.skytala.eCommerce.domain.quoteAdjustment.event.QuoteAdjustmentUpdated;
import com.skytala.eCommerce.domain.quoteAdjustment.mapper.QuoteAdjustmentMapper;
import com.skytala.eCommerce.domain.quoteAdjustment.model.QuoteAdjustment;
import com.skytala.eCommerce.domain.quoteAdjustment.query.FindQuoteAdjustmentsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/quoteAdjustments")
public class QuoteAdjustmentController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public QuoteAdjustmentController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a QuoteAdjustment
	 * @return a List with the QuoteAdjustments
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findQuoteAdjustmentsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindQuoteAdjustmentsBy query = new FindQuoteAdjustmentsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<QuoteAdjustment> quoteAdjustments =((QuoteAdjustmentFound) Scheduler.execute(query).data()).getQuoteAdjustments();

		if (quoteAdjustments.size() == 1) {
			return ResponseEntity.ok().body(quoteAdjustments.get(0));
		}

		return ResponseEntity.ok().body(quoteAdjustments);

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
	public ResponseEntity<Object> createQuoteAdjustment(HttpServletRequest request) throws Exception {

		QuoteAdjustment quoteAdjustmentToBeAdded = new QuoteAdjustment();
		try {
			quoteAdjustmentToBeAdded = QuoteAdjustmentMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createQuoteAdjustment(quoteAdjustmentToBeAdded);

	}

	/**
	 * creates a new QuoteAdjustment entry in the ofbiz database
	 * 
	 * @param quoteAdjustmentToBeAdded
	 *            the QuoteAdjustment thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createQuoteAdjustment(@RequestBody QuoteAdjustment quoteAdjustmentToBeAdded) throws Exception {

		AddQuoteAdjustment command = new AddQuoteAdjustment(quoteAdjustmentToBeAdded);
		QuoteAdjustment quoteAdjustment = ((QuoteAdjustmentAdded) Scheduler.execute(command).data()).getAddedQuoteAdjustment();
		
		if (quoteAdjustment != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(quoteAdjustment);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("QuoteAdjustment could not be created.");
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
	public boolean updateQuoteAdjustment(HttpServletRequest request) throws Exception {

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

		QuoteAdjustment quoteAdjustmentToBeUpdated = new QuoteAdjustment();

		try {
			quoteAdjustmentToBeUpdated = QuoteAdjustmentMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateQuoteAdjustment(quoteAdjustmentToBeUpdated, quoteAdjustmentToBeUpdated.getQuoteAdjustmentId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the QuoteAdjustment with the specific Id
	 * 
	 * @param quoteAdjustmentToBeUpdated
	 *            the QuoteAdjustment thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{quoteAdjustmentId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateQuoteAdjustment(@RequestBody QuoteAdjustment quoteAdjustmentToBeUpdated,
			@PathVariable String quoteAdjustmentId) throws Exception {

		quoteAdjustmentToBeUpdated.setQuoteAdjustmentId(quoteAdjustmentId);

		UpdateQuoteAdjustment command = new UpdateQuoteAdjustment(quoteAdjustmentToBeUpdated);

		try {
			if(((QuoteAdjustmentUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{quoteAdjustmentId}")
	public ResponseEntity<Object> findById(@PathVariable String quoteAdjustmentId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("quoteAdjustmentId", quoteAdjustmentId);
		try {

			Object foundQuoteAdjustment = findQuoteAdjustmentsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundQuoteAdjustment);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{quoteAdjustmentId}")
	public ResponseEntity<Object> deleteQuoteAdjustmentByIdUpdated(@PathVariable String quoteAdjustmentId) throws Exception {
		DeleteQuoteAdjustment command = new DeleteQuoteAdjustment(quoteAdjustmentId);

		try {
			if (((QuoteAdjustmentDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("QuoteAdjustment could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/quoteAdjustment/\" plus one of the following: "
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
