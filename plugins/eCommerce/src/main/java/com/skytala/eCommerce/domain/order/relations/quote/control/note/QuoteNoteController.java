package com.skytala.eCommerce.domain.order.relations.quote.control.note;

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
import com.skytala.eCommerce.domain.order.relations.quote.command.note.AddQuoteNote;
import com.skytala.eCommerce.domain.order.relations.quote.command.note.DeleteQuoteNote;
import com.skytala.eCommerce.domain.order.relations.quote.command.note.UpdateQuoteNote;
import com.skytala.eCommerce.domain.order.relations.quote.event.note.QuoteNoteAdded;
import com.skytala.eCommerce.domain.order.relations.quote.event.note.QuoteNoteDeleted;
import com.skytala.eCommerce.domain.order.relations.quote.event.note.QuoteNoteFound;
import com.skytala.eCommerce.domain.order.relations.quote.event.note.QuoteNoteUpdated;
import com.skytala.eCommerce.domain.order.relations.quote.mapper.note.QuoteNoteMapper;
import com.skytala.eCommerce.domain.order.relations.quote.model.note.QuoteNote;
import com.skytala.eCommerce.domain.order.relations.quote.query.note.FindQuoteNotesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/order/quote/quoteNotes")
public class QuoteNoteController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public QuoteNoteController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a QuoteNote
	 * @return a List with the QuoteNotes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<QuoteNote>> findQuoteNotesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindQuoteNotesBy query = new FindQuoteNotesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<QuoteNote> quoteNotes =((QuoteNoteFound) Scheduler.execute(query).data()).getQuoteNotes();

		return ResponseEntity.ok().body(quoteNotes);

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
	public ResponseEntity<QuoteNote> createQuoteNote(HttpServletRequest request) throws Exception {

		QuoteNote quoteNoteToBeAdded = new QuoteNote();
		try {
			quoteNoteToBeAdded = QuoteNoteMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createQuoteNote(quoteNoteToBeAdded);

	}

	/**
	 * creates a new QuoteNote entry in the ofbiz database
	 * 
	 * @param quoteNoteToBeAdded
	 *            the QuoteNote thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<QuoteNote> createQuoteNote(@RequestBody QuoteNote quoteNoteToBeAdded) throws Exception {

		AddQuoteNote command = new AddQuoteNote(quoteNoteToBeAdded);
		QuoteNote quoteNote = ((QuoteNoteAdded) Scheduler.execute(command).data()).getAddedQuoteNote();
		
		if (quoteNote != null) 
			return successful(quoteNote);
		else 
			return conflict(null);
	}

	/**
	 * Updates the QuoteNote with the specific Id
	 * 
	 * @param quoteNoteToBeUpdated
	 *            the QuoteNote thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateQuoteNote(@RequestBody QuoteNote quoteNoteToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		quoteNoteToBeUpdated.setnull(null);

		UpdateQuoteNote command = new UpdateQuoteNote(quoteNoteToBeUpdated);

		try {
			if(((QuoteNoteUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{quoteNoteId}")
	public ResponseEntity<QuoteNote> findById(@PathVariable String quoteNoteId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("quoteNoteId", quoteNoteId);
		try {

			List<QuoteNote> foundQuoteNote = findQuoteNotesBy(requestParams).getBody();
			if(foundQuoteNote.size()==1){				return successful(foundQuoteNote.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{quoteNoteId}")
	public ResponseEntity<String> deleteQuoteNoteByIdUpdated(@PathVariable String quoteNoteId) throws Exception {
		DeleteQuoteNote command = new DeleteQuoteNote(quoteNoteId);

		try {
			if (((QuoteNoteDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
