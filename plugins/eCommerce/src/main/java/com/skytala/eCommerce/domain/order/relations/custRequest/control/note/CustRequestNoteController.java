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
import org.springframework.web.bind.annotation.*;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/order/custRequest/custRequestNotes")
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
	@GetMapping("/find")
	public ResponseEntity<List<CustRequestNote>> findCustRequestNotesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindCustRequestNotesBy query = new FindCustRequestNotesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<CustRequestNote> custRequestNotes =((CustRequestNoteFound) Scheduler.execute(query).data()).getCustRequestNotes();

		return ResponseEntity.ok().body(custRequestNotes);

	}

	/**
	 * creates a new CustRequestNote entry in the ofbiz database
	 * 
	 * @param custRequestNoteToBeAdded
	 *            the CustRequestNote thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<CustRequestNote> createCustRequestNote(@RequestBody CustRequestNote custRequestNoteToBeAdded) throws Exception {

		AddCustRequestNote command = new AddCustRequestNote(custRequestNoteToBeAdded);
		CustRequestNote custRequestNote = ((CustRequestNoteAdded) Scheduler.execute(command).data()).getAddedCustRequestNote();
		
		if (custRequestNote != null) 
			return successful(custRequestNote);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateCustRequestNote(@RequestBody CustRequestNote custRequestNoteToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		custRequestNoteToBeUpdated.setnull(null);

		UpdateCustRequestNote command = new UpdateCustRequestNote(custRequestNoteToBeUpdated);

		try {
			if(((CustRequestNoteUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{custRequestNoteId}")
	public ResponseEntity<CustRequestNote> findById(@PathVariable String custRequestNoteId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("custRequestNoteId", custRequestNoteId);
		try {

			List<CustRequestNote> foundCustRequestNote = findCustRequestNotesBy(requestParams).getBody();
			if(foundCustRequestNote.size()==1){				return successful(foundCustRequestNote.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{custRequestNoteId}")
	public ResponseEntity<String> deleteCustRequestNoteByIdUpdated(@PathVariable String custRequestNoteId) throws Exception {
		DeleteCustRequestNote command = new DeleteCustRequestNote(custRequestNoteId);

		try {
			if (((CustRequestNoteDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
