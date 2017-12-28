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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/order/quote/quoteWorkEfforts")
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
	@GetMapping("/find")
	public ResponseEntity<List<QuoteWorkEffort>> findQuoteWorkEffortsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindQuoteWorkEffortsBy query = new FindQuoteWorkEffortsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<QuoteWorkEffort> quoteWorkEfforts =((QuoteWorkEffortFound) Scheduler.execute(query).data()).getQuoteWorkEfforts();

		return ResponseEntity.ok().body(quoteWorkEfforts);

	}

	/**
	 * creates a new QuoteWorkEffort entry in the ofbiz database
	 * 
	 * @param quoteWorkEffortToBeAdded
	 *            the QuoteWorkEffort thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<QuoteWorkEffort> createQuoteWorkEffort(@RequestBody QuoteWorkEffort quoteWorkEffortToBeAdded) throws Exception {

		AddQuoteWorkEffort command = new AddQuoteWorkEffort(quoteWorkEffortToBeAdded);
		QuoteWorkEffort quoteWorkEffort = ((QuoteWorkEffortAdded) Scheduler.execute(command).data()).getAddedQuoteWorkEffort();
		
		if (quoteWorkEffort != null) 
			return successful(quoteWorkEffort);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateQuoteWorkEffort(@RequestBody QuoteWorkEffort quoteWorkEffortToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		quoteWorkEffortToBeUpdated.setnull(null);

		UpdateQuoteWorkEffort command = new UpdateQuoteWorkEffort(quoteWorkEffortToBeUpdated);

		try {
			if(((QuoteWorkEffortUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{quoteWorkEffortId}")
	public ResponseEntity<QuoteWorkEffort> findById(@PathVariable String quoteWorkEffortId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("quoteWorkEffortId", quoteWorkEffortId);
		try {

			List<QuoteWorkEffort> foundQuoteWorkEffort = findQuoteWorkEffortsBy(requestParams).getBody();
			if(foundQuoteWorkEffort.size()==1){				return successful(foundQuoteWorkEffort.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{quoteWorkEffortId}")
	public ResponseEntity<String> deleteQuoteWorkEffortByIdUpdated(@PathVariable String quoteWorkEffortId) throws Exception {
		DeleteQuoteWorkEffort command = new DeleteQuoteWorkEffort(quoteWorkEffortId);

		try {
			if (((QuoteWorkEffortDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
