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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

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
	public ResponseEntity<List<QuoteItem>> findQuoteItemsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindQuoteItemsBy query = new FindQuoteItemsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<QuoteItem> quoteItems =((QuoteItemFound) Scheduler.execute(query).data()).getQuoteItems();

		return ResponseEntity.ok().body(quoteItems);

	}

	/**
	 * creates a new QuoteItem entry in the ofbiz database
	 * 
	 * @param quoteItemToBeAdded
	 *            the QuoteItem thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<QuoteItem> createQuoteItem(@RequestBody QuoteItem quoteItemToBeAdded) throws Exception {

		AddQuoteItem command = new AddQuoteItem(quoteItemToBeAdded);
		QuoteItem quoteItem = ((QuoteItemAdded) Scheduler.execute(command).data()).getAddedQuoteItem();
		
		if (quoteItem != null) 
			return successful(quoteItem);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateQuoteItem(@RequestBody QuoteItem quoteItemToBeUpdated,
			@PathVariable String quoteItemSeqId) throws Exception {

		quoteItemToBeUpdated.setQuoteItemSeqId(quoteItemSeqId);

		UpdateQuoteItem command = new UpdateQuoteItem(quoteItemToBeUpdated);

		try {
			if(((QuoteItemUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{quoteItemId}")
	public ResponseEntity<QuoteItem> findById(@PathVariable String quoteItemId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("quoteItemId", quoteItemId);
		try {

			List<QuoteItem> foundQuoteItem = findQuoteItemsBy(requestParams).getBody();
			if(foundQuoteItem.size()==1){				return successful(foundQuoteItem.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{quoteItemId}")
	public ResponseEntity<String> deleteQuoteItemByIdUpdated(@PathVariable String quoteItemId) throws Exception {
		DeleteQuoteItem command = new DeleteQuoteItem(quoteItemId);

		try {
			if (((QuoteItemDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
