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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findQuoteAttributesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindQuoteAttributesBy query = new FindQuoteAttributesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<QuoteAttribute> quoteAttributes =((QuoteAttributeFound) Scheduler.execute(query).data()).getQuoteAttributes();

		if (quoteAttributes.size() == 1) {
			return ResponseEntity.ok().body(quoteAttributes.get(0));
		}

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
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createQuoteAttribute(HttpServletRequest request) throws Exception {

		QuoteAttribute quoteAttributeToBeAdded = new QuoteAttribute();
		try {
			quoteAttributeToBeAdded = QuoteAttributeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
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
	public ResponseEntity<Object> createQuoteAttribute(@RequestBody QuoteAttribute quoteAttributeToBeAdded) throws Exception {

		AddQuoteAttribute command = new AddQuoteAttribute(quoteAttributeToBeAdded);
		QuoteAttribute quoteAttribute = ((QuoteAttributeAdded) Scheduler.execute(command).data()).getAddedQuoteAttribute();
		
		if (quoteAttribute != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(quoteAttribute);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("QuoteAttribute could not be created.");
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
	public boolean updateQuoteAttribute(HttpServletRequest request) throws Exception {

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

		QuoteAttribute quoteAttributeToBeUpdated = new QuoteAttribute();

		try {
			quoteAttributeToBeUpdated = QuoteAttributeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateQuoteAttribute(quoteAttributeToBeUpdated, quoteAttributeToBeUpdated.getAttrName()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateQuoteAttribute(@RequestBody QuoteAttribute quoteAttributeToBeUpdated,
			@PathVariable String attrName) throws Exception {

		quoteAttributeToBeUpdated.setAttrName(attrName);

		UpdateQuoteAttribute command = new UpdateQuoteAttribute(quoteAttributeToBeUpdated);

		try {
			if(((QuoteAttributeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{quoteAttributeId}")
	public ResponseEntity<Object> findById(@PathVariable String quoteAttributeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("quoteAttributeId", quoteAttributeId);
		try {

			Object foundQuoteAttribute = findQuoteAttributesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundQuoteAttribute);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{quoteAttributeId}")
	public ResponseEntity<Object> deleteQuoteAttributeByIdUpdated(@PathVariable String quoteAttributeId) throws Exception {
		DeleteQuoteAttribute command = new DeleteQuoteAttribute(quoteAttributeId);

		try {
			if (((QuoteAttributeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("QuoteAttribute could not be deleted");

	}

}
