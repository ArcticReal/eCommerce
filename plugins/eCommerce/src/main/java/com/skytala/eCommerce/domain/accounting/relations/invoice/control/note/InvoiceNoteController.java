package com.skytala.eCommerce.domain.accounting.relations.invoice.control.note;

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
import com.skytala.eCommerce.domain.accounting.relations.invoice.command.note.AddInvoiceNote;
import com.skytala.eCommerce.domain.accounting.relations.invoice.command.note.DeleteInvoiceNote;
import com.skytala.eCommerce.domain.accounting.relations.invoice.command.note.UpdateInvoiceNote;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.note.InvoiceNoteAdded;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.note.InvoiceNoteDeleted;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.note.InvoiceNoteFound;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.note.InvoiceNoteUpdated;
import com.skytala.eCommerce.domain.accounting.relations.invoice.mapper.note.InvoiceNoteMapper;
import com.skytala.eCommerce.domain.accounting.relations.invoice.model.note.InvoiceNote;
import com.skytala.eCommerce.domain.accounting.relations.invoice.query.note.FindInvoiceNotesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/invoice/invoiceNotes")
public class InvoiceNoteController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public InvoiceNoteController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a InvoiceNote
	 * @return a List with the InvoiceNotes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<InvoiceNote>> findInvoiceNotesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindInvoiceNotesBy query = new FindInvoiceNotesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<InvoiceNote> invoiceNotes =((InvoiceNoteFound) Scheduler.execute(query).data()).getInvoiceNotes();

		return ResponseEntity.ok().body(invoiceNotes);

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
	public ResponseEntity<InvoiceNote> createInvoiceNote(HttpServletRequest request) throws Exception {

		InvoiceNote invoiceNoteToBeAdded = new InvoiceNote();
		try {
			invoiceNoteToBeAdded = InvoiceNoteMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createInvoiceNote(invoiceNoteToBeAdded);

	}

	/**
	 * creates a new InvoiceNote entry in the ofbiz database
	 * 
	 * @param invoiceNoteToBeAdded
	 *            the InvoiceNote thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<InvoiceNote> createInvoiceNote(@RequestBody InvoiceNote invoiceNoteToBeAdded) throws Exception {

		AddInvoiceNote command = new AddInvoiceNote(invoiceNoteToBeAdded);
		InvoiceNote invoiceNote = ((InvoiceNoteAdded) Scheduler.execute(command).data()).getAddedInvoiceNote();
		
		if (invoiceNote != null) 
			return successful(invoiceNote);
		else 
			return conflict(null);
	}

	/**
	 * Updates the InvoiceNote with the specific Id
	 * 
	 * @param invoiceNoteToBeUpdated
	 *            the InvoiceNote thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateInvoiceNote(@RequestBody InvoiceNote invoiceNoteToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		invoiceNoteToBeUpdated.setnull(null);

		UpdateInvoiceNote command = new UpdateInvoiceNote(invoiceNoteToBeUpdated);

		try {
			if(((InvoiceNoteUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{invoiceNoteId}")
	public ResponseEntity<InvoiceNote> findById(@PathVariable String invoiceNoteId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("invoiceNoteId", invoiceNoteId);
		try {

			List<InvoiceNote> foundInvoiceNote = findInvoiceNotesBy(requestParams).getBody();
			if(foundInvoiceNote.size()==1){				return successful(foundInvoiceNote.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{invoiceNoteId}")
	public ResponseEntity<String> deleteInvoiceNoteByIdUpdated(@PathVariable String invoiceNoteId) throws Exception {
		DeleteInvoiceNote command = new DeleteInvoiceNote(invoiceNoteId);

		try {
			if (((InvoiceNoteDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
