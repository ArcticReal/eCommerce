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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/order/quote/quoteCoefficients")
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
	@GetMapping("/find")
	public ResponseEntity<List<QuoteCoefficient>> findQuoteCoefficientsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindQuoteCoefficientsBy query = new FindQuoteCoefficientsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<QuoteCoefficient> quoteCoefficients =((QuoteCoefficientFound) Scheduler.execute(query).data()).getQuoteCoefficients();

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
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<QuoteCoefficient> createQuoteCoefficient(HttpServletRequest request) throws Exception {

		QuoteCoefficient quoteCoefficientToBeAdded = new QuoteCoefficient();
		try {
			quoteCoefficientToBeAdded = QuoteCoefficientMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
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
	public ResponseEntity<QuoteCoefficient> createQuoteCoefficient(@RequestBody QuoteCoefficient quoteCoefficientToBeAdded) throws Exception {

		AddQuoteCoefficient command = new AddQuoteCoefficient(quoteCoefficientToBeAdded);
		QuoteCoefficient quoteCoefficient = ((QuoteCoefficientAdded) Scheduler.execute(command).data()).getAddedQuoteCoefficient();
		
		if (quoteCoefficient != null) 
			return successful(quoteCoefficient);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateQuoteCoefficient(@RequestBody QuoteCoefficient quoteCoefficientToBeUpdated,
			@PathVariable String coeffName) throws Exception {

		quoteCoefficientToBeUpdated.setCoeffName(coeffName);

		UpdateQuoteCoefficient command = new UpdateQuoteCoefficient(quoteCoefficientToBeUpdated);

		try {
			if(((QuoteCoefficientUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{quoteCoefficientId}")
	public ResponseEntity<QuoteCoefficient> findById(@PathVariable String quoteCoefficientId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("quoteCoefficientId", quoteCoefficientId);
		try {

			List<QuoteCoefficient> foundQuoteCoefficient = findQuoteCoefficientsBy(requestParams).getBody();
			if(foundQuoteCoefficient.size()==1){				return successful(foundQuoteCoefficient.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{quoteCoefficientId}")
	public ResponseEntity<String> deleteQuoteCoefficientByIdUpdated(@PathVariable String quoteCoefficientId) throws Exception {
		DeleteQuoteCoefficient command = new DeleteQuoteCoefficient(quoteCoefficientId);

		try {
			if (((QuoteCoefficientDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
