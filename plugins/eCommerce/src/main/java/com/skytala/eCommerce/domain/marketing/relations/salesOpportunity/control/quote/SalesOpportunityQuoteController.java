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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findSalesOpportunityQuotesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindSalesOpportunityQuotesBy query = new FindSalesOpportunityQuotesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<SalesOpportunityQuote> salesOpportunityQuotes =((SalesOpportunityQuoteFound) Scheduler.execute(query).data()).getSalesOpportunityQuotes();

		if (salesOpportunityQuotes.size() == 1) {
			return ResponseEntity.ok().body(salesOpportunityQuotes.get(0));
		}

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
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createSalesOpportunityQuote(HttpServletRequest request) throws Exception {

		SalesOpportunityQuote salesOpportunityQuoteToBeAdded = new SalesOpportunityQuote();
		try {
			salesOpportunityQuoteToBeAdded = SalesOpportunityQuoteMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
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
	public ResponseEntity<Object> createSalesOpportunityQuote(@RequestBody SalesOpportunityQuote salesOpportunityQuoteToBeAdded) throws Exception {

		AddSalesOpportunityQuote command = new AddSalesOpportunityQuote(salesOpportunityQuoteToBeAdded);
		SalesOpportunityQuote salesOpportunityQuote = ((SalesOpportunityQuoteAdded) Scheduler.execute(command).data()).getAddedSalesOpportunityQuote();
		
		if (salesOpportunityQuote != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(salesOpportunityQuote);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("SalesOpportunityQuote could not be created.");
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
	public boolean updateSalesOpportunityQuote(HttpServletRequest request) throws Exception {

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

		SalesOpportunityQuote salesOpportunityQuoteToBeUpdated = new SalesOpportunityQuote();

		try {
			salesOpportunityQuoteToBeUpdated = SalesOpportunityQuoteMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateSalesOpportunityQuote(salesOpportunityQuoteToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateSalesOpportunityQuote(@RequestBody SalesOpportunityQuote salesOpportunityQuoteToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		salesOpportunityQuoteToBeUpdated.setnull(null);

		UpdateSalesOpportunityQuote command = new UpdateSalesOpportunityQuote(salesOpportunityQuoteToBeUpdated);

		try {
			if(((SalesOpportunityQuoteUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{salesOpportunityQuoteId}")
	public ResponseEntity<Object> findById(@PathVariable String salesOpportunityQuoteId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("salesOpportunityQuoteId", salesOpportunityQuoteId);
		try {

			Object foundSalesOpportunityQuote = findSalesOpportunityQuotesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundSalesOpportunityQuote);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{salesOpportunityQuoteId}")
	public ResponseEntity<Object> deleteSalesOpportunityQuoteByIdUpdated(@PathVariable String salesOpportunityQuoteId) throws Exception {
		DeleteSalesOpportunityQuote command = new DeleteSalesOpportunityQuote(salesOpportunityQuoteId);

		try {
			if (((SalesOpportunityQuoteDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("SalesOpportunityQuote could not be deleted");

	}

}
