package com.skytala.eCommerce.domain.order.relations.quote.control.coefficient;

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
import com.skytala.eCommerce.domain.order.relations.quote.command.coefficient.AddQuoteCoefficient;
import com.skytala.eCommerce.domain.order.relations.quote.command.coefficient.DeleteQuoteCoefficient;
import com.skytala.eCommerce.domain.order.relations.quote.command.coefficient.UpdateQuoteCoefficient;
import com.skytala.eCommerce.domain.order.relations.quote.event.coefficient.QuoteCoefficientAdded;
import com.skytala.eCommerce.domain.order.relations.quote.event.coefficient.QuoteCoefficientDeleted;
import com.skytala.eCommerce.domain.order.relations.quote.event.coefficient.QuoteCoefficientFound;
import com.skytala.eCommerce.domain.order.relations.quote.event.coefficient.QuoteCoefficientUpdated;
import com.skytala.eCommerce.domain.order.relations.quote.mapper.coefficient.QuoteCoefficientMapper;
import com.skytala.eCommerce.domain.order.relations.quote.model.coefficient.QuoteCoefficient;
import com.skytala.eCommerce.domain.order.relations.quote.query.coefficient.FindQuoteCoefficientsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/quoteCoefficients")
public class QuoteCoefficientController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public QuoteCoefficientController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a QuoteCoefficient
	 * @return a List with the QuoteCoefficients
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findQuoteCoefficientsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindQuoteCoefficientsBy query = new FindQuoteCoefficientsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<QuoteCoefficient> quoteCoefficients =((QuoteCoefficientFound) Scheduler.execute(query).data()).getQuoteCoefficients();

		if (quoteCoefficients.size() == 1) {
			return ResponseEntity.ok().body(quoteCoefficients.get(0));
		}

		return ResponseEntity.ok().body(quoteCoefficients);

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
	public ResponseEntity<Object> createQuoteCoefficient(HttpServletRequest request) throws Exception {

		QuoteCoefficient quoteCoefficientToBeAdded = new QuoteCoefficient();
		try {
			quoteCoefficientToBeAdded = QuoteCoefficientMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createQuoteCoefficient(quoteCoefficientToBeAdded);

	}

	/**
	 * creates a new QuoteCoefficient entry in the ofbiz database
	 * 
	 * @param quoteCoefficientToBeAdded
	 *            the QuoteCoefficient thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createQuoteCoefficient(@RequestBody QuoteCoefficient quoteCoefficientToBeAdded) throws Exception {

		AddQuoteCoefficient command = new AddQuoteCoefficient(quoteCoefficientToBeAdded);
		QuoteCoefficient quoteCoefficient = ((QuoteCoefficientAdded) Scheduler.execute(command).data()).getAddedQuoteCoefficient();
		
		if (quoteCoefficient != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(quoteCoefficient);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("QuoteCoefficient could not be created.");
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
	public boolean updateQuoteCoefficient(HttpServletRequest request) throws Exception {

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

		QuoteCoefficient quoteCoefficientToBeUpdated = new QuoteCoefficient();

		try {
			quoteCoefficientToBeUpdated = QuoteCoefficientMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateQuoteCoefficient(quoteCoefficientToBeUpdated, quoteCoefficientToBeUpdated.getCoeffName()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the QuoteCoefficient with the specific Id
	 * 
	 * @param quoteCoefficientToBeUpdated
	 *            the QuoteCoefficient thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{coeffName}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateQuoteCoefficient(@RequestBody QuoteCoefficient quoteCoefficientToBeUpdated,
			@PathVariable String coeffName) throws Exception {

		quoteCoefficientToBeUpdated.setCoeffName(coeffName);

		UpdateQuoteCoefficient command = new UpdateQuoteCoefficient(quoteCoefficientToBeUpdated);

		try {
			if(((QuoteCoefficientUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{quoteCoefficientId}")
	public ResponseEntity<Object> findById(@PathVariable String quoteCoefficientId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("quoteCoefficientId", quoteCoefficientId);
		try {

			Object foundQuoteCoefficient = findQuoteCoefficientsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundQuoteCoefficient);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{quoteCoefficientId}")
	public ResponseEntity<Object> deleteQuoteCoefficientByIdUpdated(@PathVariable String quoteCoefficientId) throws Exception {
		DeleteQuoteCoefficient command = new DeleteQuoteCoefficient(quoteCoefficientId);

		try {
			if (((QuoteCoefficientDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("QuoteCoefficient could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/quoteCoefficient/\" plus one of the following: "
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
