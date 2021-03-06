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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/order/quotes")
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
	@GetMapping("/find")
	public ResponseEntity<List<Quote>> findQuotesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindQuotesBy query = new FindQuotesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<Quote> quotes =((QuoteFound) Scheduler.execute(query).data()).getQuotes();

		return ResponseEntity.ok().body(quotes);

	}

	/**
	 * creates a new Quote entry in the ofbiz database
	 * 
	 * @param quoteToBeAdded
	 *            the Quote thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Quote> createQuote(@RequestBody Quote quoteToBeAdded) throws Exception {

		AddQuote command = new AddQuote(quoteToBeAdded);
		Quote quote = ((QuoteAdded) Scheduler.execute(command).data()).getAddedQuote();
		
		if (quote != null) 
			return successful(quote);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateQuote(@RequestBody Quote quoteToBeUpdated,
			@PathVariable String quoteId) throws Exception {

		quoteToBeUpdated.setQuoteId(quoteId);

		UpdateQuote command = new UpdateQuote(quoteToBeUpdated);

		try {
			if(((QuoteUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{quoteId}")
	public ResponseEntity<Quote> findById(@PathVariable String quoteId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("quoteId", quoteId);
		try {

			List<Quote> foundQuote = findQuotesBy(requestParams).getBody();
			if(foundQuote.size()==1){				return successful(foundQuote.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{quoteId}")
	public ResponseEntity<String> deleteQuoteByIdUpdated(@PathVariable String quoteId) throws Exception {
		DeleteQuote command = new DeleteQuote(quoteId);

		try {
			if (((QuoteDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
