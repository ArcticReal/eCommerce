package com.skytala.eCommerce.domain.order.relations.custRequest.control.note;

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
import com.skytala.eCommerce.domain.order.relations.custRequest.command.note.AddCustRequestNote;
import com.skytala.eCommerce.domain.order.relations.custRequest.command.note.DeleteCustRequestNote;
import com.skytala.eCommerce.domain.order.relations.custRequest.command.note.UpdateCustRequestNote;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.note.CustRequestNoteAdded;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.note.CustRequestNoteDeleted;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.note.CustRequestNoteFound;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.note.CustRequestNoteUpdated;
import com.skytala.eCommerce.domain.order.relations.custRequest.mapper.note.CustRequestNoteMapper;
import com.skytala.eCommerce.domain.order.relations.custRequest.model.note.CustRequestNote;
import com.skytala.eCommerce.domain.order.relations.custRequest.query.note.FindCustRequestNotesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/custRequestNotes")
public class CustRequestNoteController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public CustRequestNoteController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a CustRequestNote
	 * @return a List with the CustRequestNotes
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findCustRequestNotesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindCustRequestNotesBy query = new FindCustRequestNotesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<CustRequestNote> custRequestNotes =((CustRequestNoteFound) Scheduler.execute(query).data()).getCustRequestNotes();

		if (custRequestNotes.size() == 1) {
			return ResponseEntity.ok().body(custRequestNotes.get(0));
		}

		return ResponseEntity.ok().body(custRequestNotes);

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
	public ResponseEntity<Object> createCustRequestNote(HttpServletRequest request) throws Exception {

		CustRequestNote custRequestNoteToBeAdded = new CustRequestNote();
		try {
			custRequestNoteToBeAdded = CustRequestNoteMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createCustRequestNote(custRequestNoteToBeAdded);

	}

	/**
	 * creates a new CustRequestNote entry in the ofbiz database
	 * 
	 * @param custRequestNoteToBeAdded
	 *            the CustRequestNote thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createCustRequestNote(@RequestBody CustRequestNote custRequestNoteToBeAdded) throws Exception {

		AddCustRequestNote command = new AddCustRequestNote(custRequestNoteToBeAdded);
		CustRequestNote custRequestNote = ((CustRequestNoteAdded) Scheduler.execute(command).data()).getAddedCustRequestNote();
		
		if (custRequestNote != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(custRequestNote);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("CustRequestNote could not be created.");
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
	public boolean updateCustRequestNote(HttpServletRequest request) throws Exception {

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

		CustRequestNote custRequestNoteToBeUpdated = new CustRequestNote();

		try {
			custRequestNoteToBeUpdated = CustRequestNoteMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateCustRequestNote(custRequestNoteToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the CustRequestNote with the specific Id
	 * 
	 * @param custRequestNoteToBeUpdated
	 *            the CustRequestNote thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateCustRequestNote(@RequestBody CustRequestNote custRequestNoteToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		custRequestNoteToBeUpdated.setnull(null);

		UpdateCustRequestNote command = new UpdateCustRequestNote(custRequestNoteToBeUpdated);

		try {
			if(((CustRequestNoteUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{custRequestNoteId}")
	public ResponseEntity<Object> findById(@PathVariable String custRequestNoteId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("custRequestNoteId", custRequestNoteId);
		try {

			Object foundCustRequestNote = findCustRequestNotesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundCustRequestNote);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{custRequestNoteId}")
	public ResponseEntity<Object> deleteCustRequestNoteByIdUpdated(@PathVariable String custRequestNoteId) throws Exception {
		DeleteCustRequestNote command = new DeleteCustRequestNote(custRequestNoteId);

		try {
			if (((CustRequestNoteDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("CustRequestNote could not be deleted");

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

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/custRequestNote/\" plus one of the following: "
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
