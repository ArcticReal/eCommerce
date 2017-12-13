package com.skytala.eCommerce.domain.order.relations.quote.control.item;

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
import com.skytala.eCommerce.domain.order.relations.quote.command.item.AddQuoteItem;
import com.skytala.eCommerce.domain.order.relations.quote.command.item.DeleteQuoteItem;
import com.skytala.eCommerce.domain.order.relations.quote.command.item.UpdateQuoteItem;
import com.skytala.eCommerce.domain.order.relations.quote.event.item.QuoteItemAdded;
import com.skytala.eCommerce.domain.order.relations.quote.event.item.QuoteItemDeleted;
import com.skytala.eCommerce.domain.order.relations.quote.event.item.QuoteItemFound;
import com.skytala.eCommerce.domain.order.relations.quote.event.item.QuoteItemUpdated;
import com.skytala.eCommerce.domain.order.relations.quote.mapper.item.QuoteItemMapper;
import com.skytala.eCommerce.domain.order.relations.quote.model.item.QuoteItem;
import com.skytala.eCommerce.domain.order.relations.quote.query.item.FindQuoteItemsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/order/quote/quoteItems")
public class QuoteItemController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public QuoteItemController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a QuoteItem
	 * @return a List with the QuoteItems
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<Object> findQuoteItemsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindQuoteItemsBy query = new FindQuoteItemsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<QuoteItem> quoteItems =((QuoteItemFound) Scheduler.execute(query).data()).getQuoteItems();

		if (quoteItems.size() == 1) {
			return ResponseEntity.ok().body(quoteItems.get(0));
		}

		return ResponseEntity.ok().body(quoteItems);

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
	public ResponseEntity<Object> createQuoteItem(HttpServletRequest request) throws Exception {

		QuoteItem quoteItemToBeAdded = new QuoteItem();
		try {
			quoteItemToBeAdded = QuoteItemMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createQuoteItem(quoteItemToBeAdded);

	}

	/**
	 * creates a new QuoteItem entry in the ofbiz database
	 * 
	 * @param quoteItemToBeAdded
	 *            the QuoteItem thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createQuoteItem(@RequestBody QuoteItem quoteItemToBeAdded) throws Exception {

		AddQuoteItem command = new AddQuoteItem(quoteItemToBeAdded);
		QuoteItem quoteItem = ((QuoteItemAdded) Scheduler.execute(command).data()).getAddedQuoteItem();
		
		if (quoteItem != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(quoteItem);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("QuoteItem could not be created.");
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
	@PutMapping(value = "/update", consumes = "application/x-www-form-urlencoded")
	public boolean updateQuoteItem(HttpServletRequest request) throws Exception {

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

		QuoteItem quoteItemToBeUpdated = new QuoteItem();

		try {
			quoteItemToBeUpdated = QuoteItemMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateQuoteItem(quoteItemToBeUpdated, quoteItemToBeUpdated.getQuoteItemSeqId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the QuoteItem with the specific Id
	 * 
	 * @param quoteItemToBeUpdated
	 *            the QuoteItem thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{quoteItemSeqId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateQuoteItem(@RequestBody QuoteItem quoteItemToBeUpdated,
			@PathVariable String quoteItemSeqId) throws Exception {

		quoteItemToBeUpdated.setQuoteItemSeqId(quoteItemSeqId);

		UpdateQuoteItem command = new UpdateQuoteItem(quoteItemToBeUpdated);

		try {
			if(((QuoteItemUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@GetMapping("/{quoteItemId}")
	public ResponseEntity<Object> findById(@PathVariable String quoteItemId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("quoteItemId", quoteItemId);
		try {

			Object foundQuoteItem = findQuoteItemsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundQuoteItem);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@DeleteMapping("/{quoteItemId}")
	public ResponseEntity<Object> deleteQuoteItemByIdUpdated(@PathVariable String quoteItemId) throws Exception {
		DeleteQuoteItem command = new DeleteQuoteItem(quoteItemId);

		try {
			if (((QuoteItemDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("QuoteItem could not be deleted");

	}

}
