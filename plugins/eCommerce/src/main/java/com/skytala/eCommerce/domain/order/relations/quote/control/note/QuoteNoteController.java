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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@CrossOrigin
@RequestMapping("/quoteNotes")
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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findQuoteNotesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindQuoteNotesBy query = new FindQuoteNotesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<QuoteNote> quoteNotes =((QuoteNoteFound) Scheduler.execute(query).data()).getQuoteNotes();

		if (quoteNotes.size() == 1) {
			return ResponseEntity.ok().body(quoteNotes.get(0));
		}

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
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createQuoteNote(HttpServletRequest request) throws Exception {

		QuoteNote quoteNoteToBeAdded = new QuoteNote();
		try {
			quoteNoteToBeAdded = QuoteNoteMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
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
	public ResponseEntity<Object> createQuoteNote(@RequestBody QuoteNote quoteNoteToBeAdded) throws Exception {

		AddQuoteNote command = new AddQuoteNote(quoteNoteToBeAdded);
		QuoteNote quoteNote = ((QuoteNoteAdded) Scheduler.execute(command).data()).getAddedQuoteNote();
		
		if (quoteNote != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(quoteNote);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("QuoteNote could not be created.");
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
	public boolean updateQuoteNote(HttpServletRequest request) throws Exception {

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

		QuoteNote quoteNoteToBeUpdated = new QuoteNote();

		try {
			quoteNoteToBeUpdated = QuoteNoteMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateQuoteNote(quoteNoteToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateQuoteNote(@RequestBody QuoteNote quoteNoteToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		quoteNoteToBeUpdated.setnull(null);

		UpdateQuoteNote command = new UpdateQuoteNote(quoteNoteToBeUpdated);

		try {
			if(((QuoteNoteUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{quoteNoteId}")
	public ResponseEntity<Object> findById(@PathVariable String quoteNoteId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("quoteNoteId", quoteNoteId);
		try {

			Object foundQuoteNote = findQuoteNotesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundQuoteNote);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{quoteNoteId}")
	public ResponseEntity<Object> deleteQuoteNoteByIdUpdated(@PathVariable String quoteNoteId) throws Exception {
		DeleteQuoteNote command = new DeleteQuoteNote(quoteNoteId);

		try {
			if (((QuoteNoteDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("QuoteNote could not be deleted");

	}

	@RequestMapping(value = (" ** "))
	public ResponseEntity<Object> returnErrorPage(HttpServletRequest request) {

		String usedUri = request.getRequestURI();
		String[] splittedString = usedUri.split("/");

		String usedRequest = splittedString[splittedString.length - 1];

		if (validRequests.containsKey(usedRequest)) {
			String returnVal = "Error: request method " + request.getMethod() + " not allowed for \"" + usedUri
					+ "\"!\n" + "Please use " + validRequests.get(usedRequest) + "!";

			return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(returnVal);
		}

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/quoteNote/\" plus one of the following: "
				+ "";

		Set<String> keySet = validRequests.keySet();
		Iterator<String> it = keySet.iterator();

		while (it.hasNext()) {
			returnVal += "\"" + it.next() + "\"";
			if (it.hasNext())
				returnVal += ", ";
		}

		returnVal += "!";

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnVal);

	}
}
