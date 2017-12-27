package com.skytala.eCommerce.domain.order.relations.quote.control.typeAttr;

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
import com.skytala.eCommerce.domain.order.relations.quote.command.typeAttr.AddQuoteTypeAttr;
import com.skytala.eCommerce.domain.order.relations.quote.command.typeAttr.DeleteQuoteTypeAttr;
import com.skytala.eCommerce.domain.order.relations.quote.command.typeAttr.UpdateQuoteTypeAttr;
import com.skytala.eCommerce.domain.order.relations.quote.event.typeAttr.QuoteTypeAttrAdded;
import com.skytala.eCommerce.domain.order.relations.quote.event.typeAttr.QuoteTypeAttrDeleted;
import com.skytala.eCommerce.domain.order.relations.quote.event.typeAttr.QuoteTypeAttrFound;
import com.skytala.eCommerce.domain.order.relations.quote.event.typeAttr.QuoteTypeAttrUpdated;
import com.skytala.eCommerce.domain.order.relations.quote.mapper.typeAttr.QuoteTypeAttrMapper;
import com.skytala.eCommerce.domain.order.relations.quote.model.typeAttr.QuoteTypeAttr;
import com.skytala.eCommerce.domain.order.relations.quote.query.typeAttr.FindQuoteTypeAttrsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/order/quote/quoteTypeAttrs")
public class QuoteTypeAttrController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public QuoteTypeAttrController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a QuoteTypeAttr
	 * @return a List with the QuoteTypeAttrs
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<QuoteTypeAttr>> findQuoteTypeAttrsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindQuoteTypeAttrsBy query = new FindQuoteTypeAttrsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<QuoteTypeAttr> quoteTypeAttrs =((QuoteTypeAttrFound) Scheduler.execute(query).data()).getQuoteTypeAttrs();

		return ResponseEntity.ok().body(quoteTypeAttrs);

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
	public ResponseEntity<QuoteTypeAttr> createQuoteTypeAttr(HttpServletRequest request) throws Exception {

		QuoteTypeAttr quoteTypeAttrToBeAdded = new QuoteTypeAttr();
		try {
			quoteTypeAttrToBeAdded = QuoteTypeAttrMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createQuoteTypeAttr(quoteTypeAttrToBeAdded);

	}

	/**
	 * creates a new QuoteTypeAttr entry in the ofbiz database
	 * 
	 * @param quoteTypeAttrToBeAdded
	 *            the QuoteTypeAttr thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<QuoteTypeAttr> createQuoteTypeAttr(@RequestBody QuoteTypeAttr quoteTypeAttrToBeAdded) throws Exception {

		AddQuoteTypeAttr command = new AddQuoteTypeAttr(quoteTypeAttrToBeAdded);
		QuoteTypeAttr quoteTypeAttr = ((QuoteTypeAttrAdded) Scheduler.execute(command).data()).getAddedQuoteTypeAttr();
		
		if (quoteTypeAttr != null) 
			return successful(quoteTypeAttr);
		else 
			return conflict(null);
	}

	/**
	 * Updates the QuoteTypeAttr with the specific Id
	 * 
	 * @param quoteTypeAttrToBeUpdated
	 *            the QuoteTypeAttr thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{attrName}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateQuoteTypeAttr(@RequestBody QuoteTypeAttr quoteTypeAttrToBeUpdated,
			@PathVariable String attrName) throws Exception {

		quoteTypeAttrToBeUpdated.setAttrName(attrName);

		UpdateQuoteTypeAttr command = new UpdateQuoteTypeAttr(quoteTypeAttrToBeUpdated);

		try {
			if(((QuoteTypeAttrUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{quoteTypeAttrId}")
	public ResponseEntity<QuoteTypeAttr> findById(@PathVariable String quoteTypeAttrId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("quoteTypeAttrId", quoteTypeAttrId);
		try {

			List<QuoteTypeAttr> foundQuoteTypeAttr = findQuoteTypeAttrsBy(requestParams).getBody();
			if(foundQuoteTypeAttr.size()==1){				return successful(foundQuoteTypeAttr.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{quoteTypeAttrId}")
	public ResponseEntity<String> deleteQuoteTypeAttrByIdUpdated(@PathVariable String quoteTypeAttrId) throws Exception {
		DeleteQuoteTypeAttr command = new DeleteQuoteTypeAttr(quoteTypeAttrId);

		try {
			if (((QuoteTypeAttrDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
