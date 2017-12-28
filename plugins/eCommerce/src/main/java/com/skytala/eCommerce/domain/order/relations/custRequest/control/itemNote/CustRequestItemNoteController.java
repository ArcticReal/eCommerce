package com.skytala.eCommerce.domain.order.relations.custRequest.control.itemNote;

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
import com.skytala.eCommerce.domain.order.relations.custRequest.command.itemNote.AddCustRequestItemNote;
import com.skytala.eCommerce.domain.order.relations.custRequest.command.itemNote.DeleteCustRequestItemNote;
import com.skytala.eCommerce.domain.order.relations.custRequest.command.itemNote.UpdateCustRequestItemNote;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.itemNote.CustRequestItemNoteAdded;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.itemNote.CustRequestItemNoteDeleted;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.itemNote.CustRequestItemNoteFound;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.itemNote.CustRequestItemNoteUpdated;
import com.skytala.eCommerce.domain.order.relations.custRequest.mapper.itemNote.CustRequestItemNoteMapper;
import com.skytala.eCommerce.domain.order.relations.custRequest.model.itemNote.CustRequestItemNote;
import com.skytala.eCommerce.domain.order.relations.custRequest.query.itemNote.FindCustRequestItemNotesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/order/custRequest/custRequestItemNotes")
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
	@GetMapping("/find")
	public ResponseEntity<List<CustRequestItemNote>> findCustRequestItemNotesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindCustRequestItemNotesBy query = new FindCustRequestItemNotesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<CustRequestItemNote> custRequestItemNotes =((CustRequestItemNoteFound) Scheduler.execute(query).data()).getCustRequestItemNotes();

		return ResponseEntity.ok().body(custRequestItemNotes);

	}

	/**
	 * creates a new CustRequestItemNote entry in the ofbiz database
	 * 
	 * @param custRequestItemNoteToBeAdded
	 *            the CustRequestItemNote thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<CustRequestItemNote> createCustRequestItemNote(@RequestBody CustRequestItemNote custRequestItemNoteToBeAdded) throws Exception {

		AddCustRequestItemNote command = new AddCustRequestItemNote(custRequestItemNoteToBeAdded);
		CustRequestItemNote custRequestItemNote = ((CustRequestItemNoteAdded) Scheduler.execute(command).data()).getAddedCustRequestItemNote();
		
		if (custRequestItemNote != null) 
			return successful(custRequestItemNote);
		else 
			return conflict(null);
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
	public ResponseEntity<String> updateCustRequestItemNote(@RequestBody CustRequestItemNote custRequestItemNoteToBeUpdated,
			@PathVariable String custRequestItemSeqId) throws Exception {

		custRequestItemNoteToBeUpdated.setCustRequestItemSeqId(custRequestItemSeqId);

		UpdateCustRequestItemNote command = new UpdateCustRequestItemNote(custRequestItemNoteToBeUpdated);

		try {
			if(((CustRequestItemNoteUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{custRequestItemNoteId}")
	public ResponseEntity<CustRequestItemNote> findById(@PathVariable String custRequestItemNoteId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("custRequestItemNoteId", custRequestItemNoteId);
		try {

			List<CustRequestItemNote> foundCustRequestItemNote = findCustRequestItemNotesBy(requestParams).getBody();
			if(foundCustRequestItemNote.size()==1){				return successful(foundCustRequestItemNote.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{custRequestItemNoteId}")
	public ResponseEntity<String> deleteCustRequestItemNoteByIdUpdated(@PathVariable String custRequestItemNoteId) throws Exception {
		DeleteCustRequestItemNote command = new DeleteCustRequestItemNote(custRequestItemNoteId);

		try {
			if (((CustRequestItemNoteDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
