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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findInvoiceNotesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindInvoiceNotesBy query = new FindInvoiceNotesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<InvoiceNote> invoiceNotes =((InvoiceNoteFound) Scheduler.execute(query).data()).getInvoiceNotes();

		if (invoiceNotes.size() == 1) {
			return ResponseEntity.ok().body(invoiceNotes.get(0));
		}

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
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createInvoiceNote(HttpServletRequest request) throws Exception {

		InvoiceNote invoiceNoteToBeAdded = new InvoiceNote();
		try {
			invoiceNoteToBeAdded = InvoiceNoteMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
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
	public ResponseEntity<Object> createInvoiceNote(@RequestBody InvoiceNote invoiceNoteToBeAdded) throws Exception {

		AddInvoiceNote command = new AddInvoiceNote(invoiceNoteToBeAdded);
		InvoiceNote invoiceNote = ((InvoiceNoteAdded) Scheduler.execute(command).data()).getAddedInvoiceNote();
		
		if (invoiceNote != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(invoiceNote);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("InvoiceNote could not be created.");
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
	public boolean updateInvoiceNote(HttpServletRequest request) throws Exception {

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

		InvoiceNote invoiceNoteToBeUpdated = new InvoiceNote();

		try {
			invoiceNoteToBeUpdated = InvoiceNoteMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateInvoiceNote(invoiceNoteToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateInvoiceNote(@RequestBody InvoiceNote invoiceNoteToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		invoiceNoteToBeUpdated.setnull(null);

		UpdateInvoiceNote command = new UpdateInvoiceNote(invoiceNoteToBeUpdated);

		try {
			if(((InvoiceNoteUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{invoiceNoteId}")
	public ResponseEntity<Object> findById(@PathVariable String invoiceNoteId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("invoiceNoteId", invoiceNoteId);
		try {

			Object foundInvoiceNote = findInvoiceNotesBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundInvoiceNote);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{invoiceNoteId}")
	public ResponseEntity<Object> deleteInvoiceNoteByIdUpdated(@PathVariable String invoiceNoteId) throws Exception {
		DeleteInvoiceNote command = new DeleteInvoiceNote(invoiceNoteId);

		try {
			if (((InvoiceNoteDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("InvoiceNote could not be deleted");

	}

}
