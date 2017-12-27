package com.skytala.eCommerce.domain.order.relations.quote.control.attribute;

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
import com.skytala.eCommerce.domain.order.relations.quote.command.attribute.AddQuoteAttribute;
import com.skytala.eCommerce.domain.order.relations.quote.command.attribute.DeleteQuoteAttribute;
import com.skytala.eCommerce.domain.order.relations.quote.command.attribute.UpdateQuoteAttribute;
import com.skytala.eCommerce.domain.order.relations.quote.event.attribute.QuoteAttributeAdded;
import com.skytala.eCommerce.domain.order.relations.quote.event.attribute.QuoteAttributeDeleted;
import com.skytala.eCommerce.domain.order.relations.quote.event.attribute.QuoteAttributeFound;
import com.skytala.eCommerce.domain.order.relations.quote.event.attribute.QuoteAttributeUpdated;
import com.skytala.eCommerce.domain.order.relations.quote.mapper.attribute.QuoteAttributeMapper;
import com.skytala.eCommerce.domain.order.relations.quote.model.attribute.QuoteAttribute;
import com.skytala.eCommerce.domain.order.relations.quote.query.attribute.FindQuoteAttributesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/order/quote/quoteAttributes")
public class QuoteAttributeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public QuoteAttributeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a QuoteAttribute
	 * @return a List with the QuoteAttributes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<QuoteAttribute>> findQuoteAttributesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindQuoteAttributesBy query = new FindQuoteAttributesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<QuoteAttribute> quoteAttributes =((QuoteAttributeFound) Scheduler.execute(query).data()).getQuoteAttributes();

		return ResponseEntity.ok().body(quoteAttributes);

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
	public ResponseEntity<QuoteAttribute> createQuoteAttribute(HttpServletRequest request) throws Exception {

		QuoteAttribute quoteAttributeToBeAdded = new QuoteAttribute();
		try {
			quoteAttributeToBeAdded = QuoteAttributeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createQuoteAttribute(quoteAttributeToBeAdded);

	}

	/**
	 * creates a new QuoteAttribute entry in the ofbiz database
	 * 
	 * @param quoteAttributeToBeAdded
	 *            the QuoteAttribute thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<QuoteAttribute> createQuoteAttribute(@RequestBody QuoteAttribute quoteAttributeToBeAdded) throws Exception {

		AddQuoteAttribute command = new AddQuoteAttribute(quoteAttributeToBeAdded);
		QuoteAttribute quoteAttribute = ((QuoteAttributeAdded) Scheduler.execute(command).data()).getAddedQuoteAttribute();
		
		if (quoteAttribute != null) 
			return successful(quoteAttribute);
		else 
			return conflict(null);
	}

	/**
	 * Updates the QuoteAttribute with the specific Id
	 * 
	 * @param quoteAttributeToBeUpdated
	 *            the QuoteAttribute thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{attrName}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateQuoteAttribute(@RequestBody QuoteAttribute quoteAttributeToBeUpdated,
			@PathVariable String attrName) throws Exception {

		quoteAttributeToBeUpdated.setAttrName(attrName);

		UpdateQuoteAttribute command = new UpdateQuoteAttribute(quoteAttributeToBeUpdated);

		try {
			if(((QuoteAttributeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{quoteAttributeId}")
	public ResponseEntity<QuoteAttribute> findById(@PathVariable String quoteAttributeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("quoteAttributeId", quoteAttributeId);
		try {

			List<QuoteAttribute> foundQuoteAttribute = findQuoteAttributesBy(requestParams).getBody();
			if(foundQuoteAttribute.size()==1){				return successful(foundQuoteAttribute.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{quoteAttributeId}")
	public ResponseEntity<String> deleteQuoteAttributeByIdUpdated(@PathVariable String quoteAttributeId) throws Exception {
		DeleteQuoteAttribute command = new DeleteQuoteAttribute(quoteAttributeId);

		try {
			if (((QuoteAttributeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
