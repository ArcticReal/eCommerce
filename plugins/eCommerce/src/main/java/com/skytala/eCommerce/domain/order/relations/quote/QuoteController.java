package com.skytala.eCommerce.domain.order.relations.quote;

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
import com.skytala.eCommerce.domain.order.relations.quote.command.AddQuote;
import com.skytala.eCommerce.domain.order.relations.quote.command.DeleteQuote;
import com.skytala.eCommerce.domain.order.relations.quote.command.UpdateQuote;
import com.skytala.eCommerce.domain.order.relations.quote.event.QuoteAdded;
import com.skytala.eCommerce.domain.order.relations.quote.event.QuoteDeleted;
import com.skytala.eCommerce.domain.order.relations.quote.event.QuoteFound;
import com.skytala.eCommerce.domain.order.relations.quote.event.QuoteUpdated;
import com.skytala.eCommerce.domain.order.relations.quote.mapper.QuoteMapper;
import com.skytala.eCommerce.domain.order.relations.quote.model.Quote;
import com.skytala.eCommerce.domain.order.relations.quote.query.FindQuotesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/quotes")
public class QuoteController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public QuoteController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a Quote
	 * @return a List with the Quotes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findQuotesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindQuotesBy query = new FindQuotesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<Quote> quotes =((QuoteFound) Scheduler.execute(query).data()).getQuotes();

		if (quotes.size() == 1) {
			return ResponseEntity.ok().body(quotes.get(0));
		}

		return ResponseEntity.ok().body(quotes);

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
	public ResponseEntity<Object> createQuote(HttpServletRequest request) throws Exception {

		Quote quoteToBeAdded = new Quote();
		try {
			quoteToBeAdded = QuoteMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createQuote(quoteToBeAdded);

	}

	/**
	 * creates a new Quote entry in the ofbiz database
	 * 
	 * @param quoteToBeAdded
	 *            the Quote thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createQuote(@RequestBody Quote quoteToBeAdded) throws Exception {

		AddQuote command = new AddQuote(quoteToBeAdded);
		Quote quote = ((QuoteAdded) Scheduler.execute(command).data()).getAddedQuote();
		
		if (quote != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(quote);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("Quote could not be created.");
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
	public boolean updateQuote(HttpServletRequest request) throws Exception {

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

		Quote quoteToBeUpdated = new Quote();

		try {
			quoteToBeUpdated = QuoteMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateQuote(quoteToBeUpdated, quoteToBeUpdated.getQuoteId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the Quote with the specific Id
	 * 
	 * @param quoteToBeUpdated
	 *            the Quote thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{quoteId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateQuote(@RequestBody Quote quoteToBeUpdated,
			@PathVariable String quoteId) throws Exception {

		quoteToBeUpdated.setQuoteId(quoteId);

		UpdateQuote command = new UpdateQuote(quoteToBeUpdated);

		try {
			if(((QuoteUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{quoteId}")
	public ResponseEntity<Object> findById(@PathVariable String quoteId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("quoteId", quoteId);
		try {

			Object foundQuote = findQuotesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundQuote);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{quoteId}")
	public ResponseEntity<Object> deleteQuoteByIdUpdated(@PathVariable String quoteId) throws Exception {
		DeleteQuote command = new DeleteQuote(quoteId);

		try {
			if (((QuoteDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("Quote could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/quote/\" plus one of the following: "
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
