package com.skytala.eCommerce.domain.order.relations.quote.control.adjustment;

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
import com.skytala.eCommerce.domain.order.relations.quote.command.adjustment.AddQuoteAdjustment;
import com.skytala.eCommerce.domain.order.relations.quote.command.adjustment.DeleteQuoteAdjustment;
import com.skytala.eCommerce.domain.order.relations.quote.command.adjustment.UpdateQuoteAdjustment;
import com.skytala.eCommerce.domain.order.relations.quote.event.adjustment.QuoteAdjustmentAdded;
import com.skytala.eCommerce.domain.order.relations.quote.event.adjustment.QuoteAdjustmentDeleted;
import com.skytala.eCommerce.domain.order.relations.quote.event.adjustment.QuoteAdjustmentFound;
import com.skytala.eCommerce.domain.order.relations.quote.event.adjustment.QuoteAdjustmentUpdated;
import com.skytala.eCommerce.domain.order.relations.quote.mapper.adjustment.QuoteAdjustmentMapper;
import com.skytala.eCommerce.domain.order.relations.quote.model.adjustment.QuoteAdjustment;
import com.skytala.eCommerce.domain.order.relations.quote.query.adjustment.FindQuoteAdjustmentsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/order/quote/quoteAdjustments")
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
	@GetMapping("/find")
	public ResponseEntity<List<QuoteAdjustment>> findQuoteAdjustmentsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindQuoteAdjustmentsBy query = new FindQuoteAdjustmentsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<QuoteAdjustment> quoteAdjustments =((QuoteAdjustmentFound) Scheduler.execute(query).data()).getQuoteAdjustments();

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
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<QuoteAdjustment> createQuoteAdjustment(HttpServletRequest request) throws Exception {

		QuoteAdjustment quoteAdjustmentToBeAdded = new QuoteAdjustment();
		try {
			quoteAdjustmentToBeAdded = QuoteAdjustmentMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
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
	public ResponseEntity<QuoteAdjustment> createQuoteAdjustment(@RequestBody QuoteAdjustment quoteAdjustmentToBeAdded) throws Exception {

		AddQuoteAdjustment command = new AddQuoteAdjustment(quoteAdjustmentToBeAdded);
		QuoteAdjustment quoteAdjustment = ((QuoteAdjustmentAdded) Scheduler.execute(command).data()).getAddedQuoteAdjustment();
		
		if (quoteAdjustment != null) 
			return successful(quoteAdjustment);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateQuoteAdjustment(@RequestBody QuoteAdjustment quoteAdjustmentToBeUpdated,
			@PathVariable String quoteAdjustmentId) throws Exception {

		quoteAdjustmentToBeUpdated.setQuoteAdjustmentId(quoteAdjustmentId);

		UpdateQuoteAdjustment command = new UpdateQuoteAdjustment(quoteAdjustmentToBeUpdated);

		try {
			if(((QuoteAdjustmentUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{quoteAdjustmentId}")
	public ResponseEntity<QuoteAdjustment> findById(@PathVariable String quoteAdjustmentId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("quoteAdjustmentId", quoteAdjustmentId);
		try {

			List<QuoteAdjustment> foundQuoteAdjustment = findQuoteAdjustmentsBy(requestParams).getBody();
			if(foundQuoteAdjustment.size()==1){				return successful(foundQuoteAdjustment.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{quoteAdjustmentId}")
	public ResponseEntity<String> deleteQuoteAdjustmentByIdUpdated(@PathVariable String quoteAdjustmentId) throws Exception {
		DeleteQuoteAdjustment command = new DeleteQuoteAdjustment(quoteAdjustmentId);

		try {
			if (((QuoteAdjustmentDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
