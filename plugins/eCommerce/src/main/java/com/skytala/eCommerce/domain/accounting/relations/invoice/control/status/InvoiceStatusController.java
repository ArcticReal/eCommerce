package com.skytala.eCommerce.domain.accounting.relations.invoice.control.status;

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
import com.skytala.eCommerce.domain.accounting.relations.invoice.command.status.AddInvoiceStatus;
import com.skytala.eCommerce.domain.accounting.relations.invoice.command.status.DeleteInvoiceStatus;
import com.skytala.eCommerce.domain.accounting.relations.invoice.command.status.UpdateInvoiceStatus;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.status.InvoiceStatusAdded;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.status.InvoiceStatusDeleted;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.status.InvoiceStatusFound;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.status.InvoiceStatusUpdated;
import com.skytala.eCommerce.domain.accounting.relations.invoice.mapper.status.InvoiceStatusMapper;
import com.skytala.eCommerce.domain.accounting.relations.invoice.model.status.InvoiceStatus;
import com.skytala.eCommerce.domain.accounting.relations.invoice.query.status.FindInvoiceStatussBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/invoice/invoiceStatuss")
public class InvoiceStatusController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public InvoiceStatusController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a InvoiceStatus
	 * @return a List with the InvoiceStatuss
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<InvoiceStatus>> findInvoiceStatussBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindInvoiceStatussBy query = new FindInvoiceStatussBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<InvoiceStatus> invoiceStatuss =((InvoiceStatusFound) Scheduler.execute(query).data()).getInvoiceStatuss();

		return ResponseEntity.ok().body(invoiceStatuss);

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
	public ResponseEntity<InvoiceStatus> createInvoiceStatus(HttpServletRequest request) throws Exception {

		InvoiceStatus invoiceStatusToBeAdded = new InvoiceStatus();
		try {
			invoiceStatusToBeAdded = InvoiceStatusMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createInvoiceStatus(invoiceStatusToBeAdded);

	}

	/**
	 * creates a new InvoiceStatus entry in the ofbiz database
	 * 
	 * @param invoiceStatusToBeAdded
	 *            the InvoiceStatus thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<InvoiceStatus> createInvoiceStatus(@RequestBody InvoiceStatus invoiceStatusToBeAdded) throws Exception {

		AddInvoiceStatus command = new AddInvoiceStatus(invoiceStatusToBeAdded);
		InvoiceStatus invoiceStatus = ((InvoiceStatusAdded) Scheduler.execute(command).data()).getAddedInvoiceStatus();
		
		if (invoiceStatus != null) 
			return successful(invoiceStatus);
		else 
			return conflict(null);
	}

	/**
	 * Updates the InvoiceStatus with the specific Id
	 * 
	 * @param invoiceStatusToBeUpdated
	 *            the InvoiceStatus thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateInvoiceStatus(@RequestBody InvoiceStatus invoiceStatusToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		invoiceStatusToBeUpdated.setnull(null);

		UpdateInvoiceStatus command = new UpdateInvoiceStatus(invoiceStatusToBeUpdated);

		try {
			if(((InvoiceStatusUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{invoiceStatusId}")
	public ResponseEntity<InvoiceStatus> findById(@PathVariable String invoiceStatusId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("invoiceStatusId", invoiceStatusId);
		try {

			List<InvoiceStatus> foundInvoiceStatus = findInvoiceStatussBy(requestParams).getBody();
			if(foundInvoiceStatus.size()==1){				return successful(foundInvoiceStatus.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{invoiceStatusId}")
	public ResponseEntity<String> deleteInvoiceStatusByIdUpdated(@PathVariable String invoiceStatusId) throws Exception {
		DeleteInvoiceStatus command = new DeleteInvoiceStatus(invoiceStatusId);

		try {
			if (((InvoiceStatusDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
