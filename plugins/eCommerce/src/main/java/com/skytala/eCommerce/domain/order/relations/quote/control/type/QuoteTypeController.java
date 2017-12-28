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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/order/quote/quoteTypes")
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
	@GetMapping("/find")
	public ResponseEntity<List<QuoteType>> findQuoteTypesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindQuoteTypesBy query = new FindQuoteTypesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<QuoteType> quoteTypes =((QuoteTypeFound) Scheduler.execute(query).data()).getQuoteTypes();

		return ResponseEntity.ok().body(quoteTypes);

	}

	/**
	 * creates a new QuoteType entry in the ofbiz database
	 * 
	 * @param quoteTypeToBeAdded
	 *            the QuoteType thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<QuoteType> createQuoteType(@RequestBody QuoteType quoteTypeToBeAdded) throws Exception {

		AddQuoteType command = new AddQuoteType(quoteTypeToBeAdded);
		QuoteType quoteType = ((QuoteTypeAdded) Scheduler.execute(command).data()).getAddedQuoteType();
		
		if (quoteType != null) 
			return successful(quoteType);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateQuoteType(@RequestBody QuoteType quoteTypeToBeUpdated,
			@PathVariable String quoteTypeId) throws Exception {

		quoteTypeToBeUpdated.setQuoteTypeId(quoteTypeId);

		UpdateQuoteType command = new UpdateQuoteType(quoteTypeToBeUpdated);

		try {
			if(((QuoteTypeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{quoteTypeId}")
	public ResponseEntity<QuoteType> findById(@PathVariable String quoteTypeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("quoteTypeId", quoteTypeId);
		try {

			List<QuoteType> foundQuoteType = findQuoteTypesBy(requestParams).getBody();
			if(foundQuoteType.size()==1){				return successful(foundQuoteType.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{quoteTypeId}")
	public ResponseEntity<String> deleteQuoteTypeByIdUpdated(@PathVariable String quoteTypeId) throws Exception {
		DeleteQuoteType command = new DeleteQuoteType(quoteTypeId);

		try {
			if (((QuoteTypeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
