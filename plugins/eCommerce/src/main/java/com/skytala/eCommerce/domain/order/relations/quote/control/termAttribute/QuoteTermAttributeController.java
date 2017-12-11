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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findQuoteTermAttributesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindQuoteTermAttributesBy query = new FindQuoteTermAttributesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<QuoteTermAttribute> quoteTermAttributes =((QuoteTermAttributeFound) Scheduler.execute(query).data()).getQuoteTermAttributes();

		if (quoteTermAttributes.size() == 1) {
			return ResponseEntity.ok().body(quoteTermAttributes.get(0));
		}

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
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createQuoteTermAttribute(HttpServletRequest request) throws Exception {

		QuoteTermAttribute quoteTermAttributeToBeAdded = new QuoteTermAttribute();
		try {
			quoteTermAttributeToBeAdded = QuoteTermAttributeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
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
	public ResponseEntity<Object> createQuoteTermAttribute(@RequestBody QuoteTermAttribute quoteTermAttributeToBeAdded) throws Exception {

		AddQuoteTermAttribute command = new AddQuoteTermAttribute(quoteTermAttributeToBeAdded);
		QuoteTermAttribute quoteTermAttribute = ((QuoteTermAttributeAdded) Scheduler.execute(command).data()).getAddedQuoteTermAttribute();
		
		if (quoteTermAttribute != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(quoteTermAttribute);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("QuoteTermAttribute could not be created.");
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
	public boolean updateQuoteTermAttribute(HttpServletRequest request) throws Exception {

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

		QuoteTermAttribute quoteTermAttributeToBeUpdated = new QuoteTermAttribute();

		try {
			quoteTermAttributeToBeUpdated = QuoteTermAttributeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateQuoteTermAttribute(quoteTermAttributeToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateQuoteTermAttribute(@RequestBody QuoteTermAttribute quoteTermAttributeToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		quoteTermAttributeToBeUpdated.setnull(null);

		UpdateQuoteTermAttribute command = new UpdateQuoteTermAttribute(quoteTermAttributeToBeUpdated);

		try {
			if(((QuoteTermAttributeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{quoteTermAttributeId}")
	public ResponseEntity<Object> findById(@PathVariable String quoteTermAttributeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("quoteTermAttributeId", quoteTermAttributeId);
		try {

			Object foundQuoteTermAttribute = findQuoteTermAttributesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundQuoteTermAttribute);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{quoteTermAttributeId}")
	public ResponseEntity<Object> deleteQuoteTermAttributeByIdUpdated(@PathVariable String quoteTermAttributeId) throws Exception {
		DeleteQuoteTermAttribute command = new DeleteQuoteTermAttribute(quoteTermAttributeId);

		try {
			if (((QuoteTermAttributeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("QuoteTermAttribute could not be deleted");

	}

}
