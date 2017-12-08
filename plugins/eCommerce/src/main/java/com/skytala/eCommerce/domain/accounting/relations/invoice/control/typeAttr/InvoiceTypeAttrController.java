package com.skytala.eCommerce.domain.accounting.relations.invoice.control.typeAttr;

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
import com.skytala.eCommerce.domain.accounting.relations.invoice.command.typeAttr.AddInvoiceTypeAttr;
import com.skytala.eCommerce.domain.accounting.relations.invoice.command.typeAttr.DeleteInvoiceTypeAttr;
import com.skytala.eCommerce.domain.accounting.relations.invoice.command.typeAttr.UpdateInvoiceTypeAttr;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.typeAttr.InvoiceTypeAttrAdded;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.typeAttr.InvoiceTypeAttrDeleted;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.typeAttr.InvoiceTypeAttrFound;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.typeAttr.InvoiceTypeAttrUpdated;
import com.skytala.eCommerce.domain.accounting.relations.invoice.mapper.typeAttr.InvoiceTypeAttrMapper;
import com.skytala.eCommerce.domain.accounting.relations.invoice.model.typeAttr.InvoiceTypeAttr;
import com.skytala.eCommerce.domain.accounting.relations.invoice.query.typeAttr.FindInvoiceTypeAttrsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/invoiceTypeAttrs")
public class InvoiceTypeAttrController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public InvoiceTypeAttrController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a InvoiceTypeAttr
	 * @return a List with the InvoiceTypeAttrs
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findInvoiceTypeAttrsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindInvoiceTypeAttrsBy query = new FindInvoiceTypeAttrsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<InvoiceTypeAttr> invoiceTypeAttrs =((InvoiceTypeAttrFound) Scheduler.execute(query).data()).getInvoiceTypeAttrs();

		if (invoiceTypeAttrs.size() == 1) {
			return ResponseEntity.ok().body(invoiceTypeAttrs.get(0));
		}

		return ResponseEntity.ok().body(invoiceTypeAttrs);

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
	public ResponseEntity<Object> createInvoiceTypeAttr(HttpServletRequest request) throws Exception {

		InvoiceTypeAttr invoiceTypeAttrToBeAdded = new InvoiceTypeAttr();
		try {
			invoiceTypeAttrToBeAdded = InvoiceTypeAttrMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createInvoiceTypeAttr(invoiceTypeAttrToBeAdded);

	}

	/**
	 * creates a new InvoiceTypeAttr entry in the ofbiz database
	 * 
	 * @param invoiceTypeAttrToBeAdded
	 *            the InvoiceTypeAttr thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createInvoiceTypeAttr(@RequestBody InvoiceTypeAttr invoiceTypeAttrToBeAdded) throws Exception {

		AddInvoiceTypeAttr command = new AddInvoiceTypeAttr(invoiceTypeAttrToBeAdded);
		InvoiceTypeAttr invoiceTypeAttr = ((InvoiceTypeAttrAdded) Scheduler.execute(command).data()).getAddedInvoiceTypeAttr();
		
		if (invoiceTypeAttr != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(invoiceTypeAttr);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("InvoiceTypeAttr could not be created.");
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
	public boolean updateInvoiceTypeAttr(HttpServletRequest request) throws Exception {

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

		InvoiceTypeAttr invoiceTypeAttrToBeUpdated = new InvoiceTypeAttr();

		try {
			invoiceTypeAttrToBeUpdated = InvoiceTypeAttrMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateInvoiceTypeAttr(invoiceTypeAttrToBeUpdated, invoiceTypeAttrToBeUpdated.getAttrName()).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the InvoiceTypeAttr with the specific Id
	 * 
	 * @param invoiceTypeAttrToBeUpdated
	 *            the InvoiceTypeAttr thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{attrName}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateInvoiceTypeAttr(@RequestBody InvoiceTypeAttr invoiceTypeAttrToBeUpdated,
			@PathVariable String attrName) throws Exception {

		invoiceTypeAttrToBeUpdated.setAttrName(attrName);

		UpdateInvoiceTypeAttr command = new UpdateInvoiceTypeAttr(invoiceTypeAttrToBeUpdated);

		try {
			if(((InvoiceTypeAttrUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{invoiceTypeAttrId}")
	public ResponseEntity<Object> findById(@PathVariable String invoiceTypeAttrId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("invoiceTypeAttrId", invoiceTypeAttrId);
		try {

			Object foundInvoiceTypeAttr = findInvoiceTypeAttrsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundInvoiceTypeAttr);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{invoiceTypeAttrId}")
	public ResponseEntity<Object> deleteInvoiceTypeAttrByIdUpdated(@PathVariable String invoiceTypeAttrId) throws Exception {
		DeleteInvoiceTypeAttr command = new DeleteInvoiceTypeAttr(invoiceTypeAttrId);

		try {
			if (((InvoiceTypeAttrDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("InvoiceTypeAttr could not be deleted");

	}

}
