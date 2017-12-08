package com.skytala.eCommerce.domain.order.relations.quote.control.type;

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
import com.skytala.eCommerce.domain.order.relations.quote.command.type.AddQuoteType;
import com.skytala.eCommerce.domain.order.relations.quote.command.type.DeleteQuoteType;
import com.skytala.eCommerce.domain.order.relations.quote.command.type.UpdateQuoteType;
import com.skytala.eCommerce.domain.order.relations.quote.event.type.QuoteTypeAdded;
import com.skytala.eCommerce.domain.order.relations.quote.event.type.QuoteTypeDeleted;
import com.skytala.eCommerce.domain.order.relations.quote.event.type.QuoteTypeFound;
import com.skytala.eCommerce.domain.order.relations.quote.event.type.QuoteTypeUpdated;
import com.skytala.eCommerce.domain.order.relations.quote.mapper.type.QuoteTypeMapper;
import com.skytala.eCommerce.domain.order.relations.quote.model.type.QuoteType;
import com.skytala.eCommerce.domain.order.relations.quote.query.type.FindQuoteTypesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/quoteTypes")
public class QuoteTypeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public QuoteTypeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a QuoteType
	 * @return a List with the QuoteTypes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findQuoteTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindQuoteTypesBy query = new FindQuoteTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<QuoteType> quoteTypes =((QuoteTypeFound) Scheduler.execute(query).data()).getQuoteTypes();

		if (quoteTypes.size() == 1) {
			return ResponseEntity.ok().body(quoteTypes.get(0));
		}

		return ResponseEntity.ok().body(quoteTypes);

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
	public ResponseEntity<Object> createQuoteType(HttpServletRequest request) throws Exception {

		QuoteType quoteTypeToBeAdded = new QuoteType();
		try {
			quoteTypeToBeAdded = QuoteTypeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createQuoteType(quoteTypeToBeAdded);

	}

	/**
	 * creates a new QuoteType entry in the ofbiz database
	 * 
	 * @param quoteTypeToBeAdded
	 *            the QuoteType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createQuoteType(@RequestBody QuoteType quoteTypeToBeAdded) throws Exception {

		AddQuoteType command = new AddQuoteType(quoteTypeToBeAdded);
		QuoteType quoteType = ((QuoteTypeAdded) Scheduler.execute(command).data()).getAddedQuoteType();
		
		if (quoteType != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(quoteType);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("QuoteType could not be created.");
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
	public boolean updateQuoteType(HttpServletRequest request) throws Exception {

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

		QuoteType quoteTypeToBeUpdated = new QuoteType();

		try {
			quoteTypeToBeUpdated = QuoteTypeMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateQuoteType(quoteTypeToBeUpdated, quoteTypeToBeUpdated.getQuoteTypeId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the QuoteType with the specific Id
	 * 
	 * @param quoteTypeToBeUpdated
	 *            the QuoteType thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{quoteTypeId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateQuoteType(@RequestBody QuoteType quoteTypeToBeUpdated,
			@PathVariable String quoteTypeId) throws Exception {

		quoteTypeToBeUpdated.setQuoteTypeId(quoteTypeId);

		UpdateQuoteType command = new UpdateQuoteType(quoteTypeToBeUpdated);

		try {
			if(((QuoteTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{quoteTypeId}")
	public ResponseEntity<Object> findById(@PathVariable String quoteTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("quoteTypeId", quoteTypeId);
		try {

			Object foundQuoteType = findQuoteTypesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundQuoteType);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{quoteTypeId}")
	public ResponseEntity<Object> deleteQuoteTypeByIdUpdated(@PathVariable String quoteTypeId) throws Exception {
		DeleteQuoteType command = new DeleteQuoteType(quoteTypeId);

		try {
			if (((QuoteTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("QuoteType could not be deleted");

	}

}
