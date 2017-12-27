package com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.control.quote;

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
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.command.quote.AddSalesOpportunityQuote;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.command.quote.DeleteSalesOpportunityQuote;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.command.quote.UpdateSalesOpportunityQuote;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.event.quote.SalesOpportunityQuoteAdded;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.event.quote.SalesOpportunityQuoteDeleted;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.event.quote.SalesOpportunityQuoteFound;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.event.quote.SalesOpportunityQuoteUpdated;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.mapper.quote.SalesOpportunityQuoteMapper;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.model.quote.SalesOpportunityQuote;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.query.quote.FindSalesOpportunityQuotesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/marketing/salesOpportunity/salesOpportunityQuotes")
public class SalesOpportunityQuoteController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public SalesOpportunityQuoteController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a SalesOpportunityQuote
	 * @return a List with the SalesOpportunityQuotes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<SalesOpportunityQuote>> findSalesOpportunityQuotesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindSalesOpportunityQuotesBy query = new FindSalesOpportunityQuotesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<SalesOpportunityQuote> salesOpportunityQuotes =((SalesOpportunityQuoteFound) Scheduler.execute(query).data()).getSalesOpportunityQuotes();

		return ResponseEntity.ok().body(salesOpportunityQuotes);

	}

	/**
	 * 
	 * this method will only be called by Springs DispatcherServlet
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return true on success; false on fail
	 */
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<SalesOpportunityQuote> createSalesOpportunityQuote(HttpServletRequest request) throws Exception {

		SalesOpportunityQuote salesOpportunityQuoteToBeAdded = new SalesOpportunityQuote();
		try {
			salesOpportunityQuoteToBeAdded = SalesOpportunityQuoteMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createSalesOpportunityQuote(salesOpportunityQuoteToBeAdded);

	}

	/**
	 * creates a new SalesOpportunityQuote entry in the ofbiz database
	 * 
	 * @param salesOpportunityQuoteToBeAdded
	 *            the SalesOpportunityQuote thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<SalesOpportunityQuote> createSalesOpportunityQuote(@RequestBody SalesOpportunityQuote salesOpportunityQuoteToBeAdded) throws Exception {

		AddSalesOpportunityQuote command = new AddSalesOpportunityQuote(salesOpportunityQuoteToBeAdded);
		SalesOpportunityQuote salesOpportunityQuote = ((SalesOpportunityQuoteAdded) Scheduler.execute(command).data()).getAddedSalesOpportunityQuote();
		
		if (salesOpportunityQuote != null) 
			return successful(salesOpportunityQuote);
		else 
			return conflict(null);
	}

	/**
	 * Updates the SalesOpportunityQuote with the specific Id
	 * 
	 * @param salesOpportunityQuoteToBeUpdated
	 *            the SalesOpportunityQuote thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateSalesOpportunityQuote(@RequestBody SalesOpportunityQuote salesOpportunityQuoteToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		salesOpportunityQuoteToBeUpdated.setnull(null);

		UpdateSalesOpportunityQuote command = new UpdateSalesOpportunityQuote(salesOpportunityQuoteToBeUpdated);

		try {
			if(((SalesOpportunityQuoteUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{salesOpportunityQuoteId}")
	public ResponseEntity<SalesOpportunityQuote> findById(@PathVariable String salesOpportunityQuoteId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("salesOpportunityQuoteId", salesOpportunityQuoteId);
		try {

			List<SalesOpportunityQuote> foundSalesOpportunityQuote = findSalesOpportunityQuotesBy(requestParams).getBody();
			if(foundSalesOpportunityQuote.size()==1){				return successful(foundSalesOpportunityQuote.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{salesOpportunityQuoteId}")
	public ResponseEntity<String> deleteSalesOpportunityQuoteByIdUpdated(@PathVariable String salesOpportunityQuoteId) throws Exception {
		DeleteSalesOpportunityQuote command = new DeleteSalesOpportunityQuote(salesOpportunityQuoteId);

		try {
			if (((SalesOpportunityQuoteDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
