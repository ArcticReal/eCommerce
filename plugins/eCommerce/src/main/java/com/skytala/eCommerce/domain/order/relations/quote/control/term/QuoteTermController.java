package com.skytala.eCommerce.domain.order.relations.quote.control.term;

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
import com.skytala.eCommerce.domain.order.relations.quote.command.term.AddQuoteTerm;
import com.skytala.eCommerce.domain.order.relations.quote.command.term.DeleteQuoteTerm;
import com.skytala.eCommerce.domain.order.relations.quote.command.term.UpdateQuoteTerm;
import com.skytala.eCommerce.domain.order.relations.quote.event.term.QuoteTermAdded;
import com.skytala.eCommerce.domain.order.relations.quote.event.term.QuoteTermDeleted;
import com.skytala.eCommerce.domain.order.relations.quote.event.term.QuoteTermFound;
import com.skytala.eCommerce.domain.order.relations.quote.event.term.QuoteTermUpdated;
import com.skytala.eCommerce.domain.order.relations.quote.mapper.term.QuoteTermMapper;
import com.skytala.eCommerce.domain.order.relations.quote.model.term.QuoteTerm;
import com.skytala.eCommerce.domain.order.relations.quote.query.term.FindQuoteTermsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/order/quote/quoteTerms")
public class QuoteTermController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public QuoteTermController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a QuoteTerm
	 * @return a List with the QuoteTerms
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<QuoteTerm>> findQuoteTermsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindQuoteTermsBy query = new FindQuoteTermsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<QuoteTerm> quoteTerms =((QuoteTermFound) Scheduler.execute(query).data()).getQuoteTerms();

		return ResponseEntity.ok().body(quoteTerms);

	}

	/**
	 * creates a new QuoteTerm entry in the ofbiz database
	 * 
	 * @param quoteTermToBeAdded
	 *            the QuoteTerm thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<QuoteTerm> createQuoteTerm(@RequestBody QuoteTerm quoteTermToBeAdded) throws Exception {

		AddQuoteTerm command = new AddQuoteTerm(quoteTermToBeAdded);
		QuoteTerm quoteTerm = ((QuoteTermAdded) Scheduler.execute(command).data()).getAddedQuoteTerm();
		
		if (quoteTerm != null) 
			return successful(quoteTerm);
		else 
			return conflict(null);
	}

	/**
	 * Updates the QuoteTerm with the specific Id
	 * 
	 * @param quoteTermToBeUpdated
	 *            the QuoteTerm thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{quoteItemSeqId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateQuoteTerm(@RequestBody QuoteTerm quoteTermToBeUpdated,
			@PathVariable String quoteItemSeqId) throws Exception {

		quoteTermToBeUpdated.setQuoteItemSeqId(quoteItemSeqId);

		UpdateQuoteTerm command = new UpdateQuoteTerm(quoteTermToBeUpdated);

		try {
			if(((QuoteTermUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{quoteTermId}")
	public ResponseEntity<QuoteTerm> findById(@PathVariable String quoteTermId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("quoteTermId", quoteTermId);
		try {

			List<QuoteTerm> foundQuoteTerm = findQuoteTermsBy(requestParams).getBody();
			if(foundQuoteTerm.size()==1){				return successful(foundQuoteTerm.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{quoteTermId}")
	public ResponseEntity<String> deleteQuoteTermByIdUpdated(@PathVariable String quoteTermId) throws Exception {
		DeleteQuoteTerm command = new DeleteQuoteTerm(quoteTermId);

		try {
			if (((QuoteTermDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
