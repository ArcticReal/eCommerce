package com.skytala.eCommerce.domain.order.relations.custRequestItemNote;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Splitter;
import com.skytala.eCommerce.domain.order.relations.custRequestItemNote.command.AddCustRequestItemNote;
import com.skytala.eCommerce.domain.order.relations.custRequestItemNote.command.DeleteCustRequestItemNote;
import com.skytala.eCommerce.domain.order.relations.custRequestItemNote.command.UpdateCustRequestItemNote;
import com.skytala.eCommerce.domain.order.relations.custRequestItemNote.event.CustRequestItemNoteAdded;
import com.skytala.eCommerce.domain.order.relations.custRequestItemNote.event.CustRequestItemNoteDeleted;
import com.skytala.eCommerce.domain.order.relations.custRequestItemNote.event.CustRequestItemNoteFound;
import com.skytala.eCommerce.domain.order.relations.custRequestItemNote.event.CustRequestItemNoteUpdated;
import com.skytala.eCommerce.domain.order.relations.custRequestItemNote.mapper.CustRequestItemNoteMapper;
import com.skytala.eCommerce.domain.order.relations.custRequestItemNote.model.CustRequestItemNote;
import com.skytala.eCommerce.domain.order.relations.custRequestItemNote.query.FindCustRequestItemNotesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/custRequestItemNotes")
public class CustRequestItemNoteController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public CustRequestItemNoteController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a CustRequestItemNote
	 * @return a List with the CustRequestItemNotes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findCustRequestItemNotesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindCustRequestItemNotesBy query = new FindCustRequestItemNotesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<CustRequestItemNote> custRequestItemNotes =((CustRequestItemNoteFound) Scheduler.execute(query).data()).getCustRequestItemNotes();

		if (custRequestItemNotes.size() == 1) {
			return ResponseEntity.ok().body(custRequestItemNotes.get(0));
		}

		return ResponseEntity.ok().body(custRequestItemNotes);

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
	public ResponseEntity<Object> createCustRequestItemNote(HttpServletRequest request) throws Exception {

		CustRequestItemNote custRequestItemNoteToBeAdded = new CustRequestItemNote();
		try {
			custRequestItemNoteToBeAdded = CustRequestItemNoteMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createCustRequestItemNote(custRequestItemNoteToBeAdded);

	}

	/**
	 * creates a new CustRequestItemNote entry in the ofbiz database
	 * 
	 * @param custRequestItemNoteToBeAdded
	 *            the CustRequestItemNote thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createCustRequestItemNote(@RequestBody CustRequestItemNote custRequestItemNoteToBeAdded) throws Exception {

		AddCustRequestItemNote command = new AddCustRequestItemNote(custRequestItemNoteToBeAdded);
		CustRequestItemNote custRequestItemNote = ((CustRequestItemNoteAdded) Scheduler.execute(command).data()).getAddedCustRequestItemNote();
		
		if (custRequestItemNote != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(custRequestItemNote);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("CustRequestItemNote could not be created.");
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
	public boolean updateCustRequestItemNote(HttpServletRequest request) throws Exception {

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

		CustRequestItemNote custRequestItemNoteToBeUpdated = new CustRequestItemNote();

		try {
			custRequestItemNoteToBeUpdated = CustRequestItemNoteMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateCustRequestItemNote(custRequestItemNoteToBeUpdated, custRequestItemNoteToBeUpdated.getCustRequestItemSeqId()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the CustRequestItemNote with the specific Id
	 * 
	 * @param custRequestItemNoteToBeUpdated
	 *            the CustRequestItemNote thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{custRequestItemSeqId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateCustRequestItemNote(@RequestBody CustRequestItemNote custRequestItemNoteToBeUpdated,
			@PathVariable String custRequestItemSeqId) throws Exception {

		custRequestItemNoteToBeUpdated.setCustRequestItemSeqId(custRequestItemSeqId);

		UpdateCustRequestItemNote command = new UpdateCustRequestItemNote(custRequestItemNoteToBeUpdated);

		try {
			if(((CustRequestItemNoteUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{custRequestItemNoteId}")
	public ResponseEntity<Object> findById(@PathVariable String custRequestItemNoteId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("custRequestItemNoteId", custRequestItemNoteId);
		try {

			Object foundCustRequestItemNote = findCustRequestItemNotesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundCustRequestItemNote);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{custRequestItemNoteId}")
	public ResponseEntity<Object> deleteCustRequestItemNoteByIdUpdated(@PathVariable String custRequestItemNoteId) throws Exception {
		DeleteCustRequestItemNote command = new DeleteCustRequestItemNote(custRequestItemNoteId);

		try {
			if (((CustRequestItemNoteDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("CustRequestItemNote could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/custRequestItemNote/\" plus one of the following: "
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
