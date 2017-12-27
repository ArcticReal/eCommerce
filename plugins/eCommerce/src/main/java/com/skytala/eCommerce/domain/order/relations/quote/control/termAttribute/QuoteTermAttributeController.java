package com.skytala.eCommerce.domain.order.relations.quote.control.termAttribute;

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
import com.skytala.eCommerce.domain.order.relations.quote.command.termAttribute.AddQuoteTermAttribute;
import com.skytala.eCommerce.domain.order.relations.quote.command.termAttribute.DeleteQuoteTermAttribute;
import com.skytala.eCommerce.domain.order.relations.quote.command.termAttribute.UpdateQuoteTermAttribute;
import com.skytala.eCommerce.domain.order.relations.quote.event.termAttribute.QuoteTermAttributeAdded;
import com.skytala.eCommerce.domain.order.relations.quote.event.termAttribute.QuoteTermAttributeDeleted;
import com.skytala.eCommerce.domain.order.relations.quote.event.termAttribute.QuoteTermAttributeFound;
import com.skytala.eCommerce.domain.order.relations.quote.event.termAttribute.QuoteTermAttributeUpdated;
import com.skytala.eCommerce.domain.order.relations.quote.mapper.termAttribute.QuoteTermAttributeMapper;
import com.skytala.eCommerce.domain.order.relations.quote.model.termAttribute.QuoteTermAttribute;
import com.skytala.eCommerce.domain.order.relations.quote.query.termAttribute.FindQuoteTermAttributesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/order/quote/quoteTermAttributes")
public class QuoteTermAttributeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public QuoteTermAttributeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a QuoteTermAttribute
	 * @return a List with the QuoteTermAttributes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<QuoteTermAttribute>> findQuoteTermAttributesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindQuoteTermAttributesBy query = new FindQuoteTermAttributesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<QuoteTermAttribute> quoteTermAttributes =((QuoteTermAttributeFound) Scheduler.execute(query).data()).getQuoteTermAttributes();

		return ResponseEntity.ok().body(quoteTermAttributes);

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
	public ResponseEntity<QuoteTermAttribute> createQuoteTermAttribute(HttpServletRequest request) throws Exception {

		QuoteTermAttribute quoteTermAttributeToBeAdded = new QuoteTermAttribute();
		try {
			quoteTermAttributeToBeAdded = QuoteTermAttributeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createQuoteTermAttribute(quoteTermAttributeToBeAdded);

	}

	/**
	 * creates a new QuoteTermAttribute entry in the ofbiz database
	 * 
	 * @param quoteTermAttributeToBeAdded
	 *            the QuoteTermAttribute thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<QuoteTermAttribute> createQuoteTermAttribute(@RequestBody QuoteTermAttribute quoteTermAttributeToBeAdded) throws Exception {

		AddQuoteTermAttribute command = new AddQuoteTermAttribute(quoteTermAttributeToBeAdded);
		QuoteTermAttribute quoteTermAttribute = ((QuoteTermAttributeAdded) Scheduler.execute(command).data()).getAddedQuoteTermAttribute();
		
		if (quoteTermAttribute != null) 
			return successful(quoteTermAttribute);
		else 
			return conflict(null);
	}

	/**
	 * Updates the QuoteTermAttribute with the specific Id
	 * 
	 * @param quoteTermAttributeToBeUpdated
	 *            the QuoteTermAttribute thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateQuoteTermAttribute(@RequestBody QuoteTermAttribute quoteTermAttributeToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		quoteTermAttributeToBeUpdated.setnull(null);

		UpdateQuoteTermAttribute command = new UpdateQuoteTermAttribute(quoteTermAttributeToBeUpdated);

		try {
			if(((QuoteTermAttributeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{quoteTermAttributeId}")
	public ResponseEntity<QuoteTermAttribute> findById(@PathVariable String quoteTermAttributeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("quoteTermAttributeId", quoteTermAttributeId);
		try {

			List<QuoteTermAttribute> foundQuoteTermAttribute = findQuoteTermAttributesBy(requestParams).getBody();
			if(foundQuoteTermAttribute.size()==1){				return successful(foundQuoteTermAttribute.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{quoteTermAttributeId}")
	public ResponseEntity<String> deleteQuoteTermAttributeByIdUpdated(@PathVariable String quoteTermAttributeId) throws Exception {
		DeleteQuoteTermAttribute command = new DeleteQuoteTermAttribute(quoteTermAttributeId);

		try {
			if (((QuoteTermAttributeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
