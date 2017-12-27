package com.skytala.eCommerce.domain.accounting.relations.invoice.control.attribute;

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
import com.skytala.eCommerce.domain.accounting.relations.invoice.command.attribute.AddInvoiceAttribute;
import com.skytala.eCommerce.domain.accounting.relations.invoice.command.attribute.DeleteInvoiceAttribute;
import com.skytala.eCommerce.domain.accounting.relations.invoice.command.attribute.UpdateInvoiceAttribute;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.attribute.InvoiceAttributeAdded;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.attribute.InvoiceAttributeDeleted;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.attribute.InvoiceAttributeFound;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.attribute.InvoiceAttributeUpdated;
import com.skytala.eCommerce.domain.accounting.relations.invoice.mapper.attribute.InvoiceAttributeMapper;
import com.skytala.eCommerce.domain.accounting.relations.invoice.model.attribute.InvoiceAttribute;
import com.skytala.eCommerce.domain.accounting.relations.invoice.query.attribute.FindInvoiceAttributesBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/invoice/invoiceAttributes")
public class InvoiceAttributeController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public InvoiceAttributeController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a InvoiceAttribute
	 * @return a List with the InvoiceAttributes
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<InvoiceAttribute>> findInvoiceAttributesBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindInvoiceAttributesBy query = new FindInvoiceAttributesBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<InvoiceAttribute> invoiceAttributes =((InvoiceAttributeFound) Scheduler.execute(query).data()).getInvoiceAttributes();

		return ResponseEntity.ok().body(invoiceAttributes);

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
	public ResponseEntity<InvoiceAttribute> createInvoiceAttribute(HttpServletRequest request) throws Exception {

		InvoiceAttribute invoiceAttributeToBeAdded = new InvoiceAttribute();
		try {
			invoiceAttributeToBeAdded = InvoiceAttributeMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createInvoiceAttribute(invoiceAttributeToBeAdded);

	}

	/**
	 * creates a new InvoiceAttribute entry in the ofbiz database
	 * 
	 * @param invoiceAttributeToBeAdded
	 *            the InvoiceAttribute thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<InvoiceAttribute> createInvoiceAttribute(@RequestBody InvoiceAttribute invoiceAttributeToBeAdded) throws Exception {

		AddInvoiceAttribute command = new AddInvoiceAttribute(invoiceAttributeToBeAdded);
		InvoiceAttribute invoiceAttribute = ((InvoiceAttributeAdded) Scheduler.execute(command).data()).getAddedInvoiceAttribute();
		
		if (invoiceAttribute != null) 
			return successful(invoiceAttribute);
		else 
			return conflict(null);
	}

	/**
	 * Updates the InvoiceAttribute with the specific Id
	 * 
	 * @param invoiceAttributeToBeUpdated
	 *            the InvoiceAttribute thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{attrName}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateInvoiceAttribute(@RequestBody InvoiceAttribute invoiceAttributeToBeUpdated,
			@PathVariable String attrName) throws Exception {

		invoiceAttributeToBeUpdated.setAttrName(attrName);

		UpdateInvoiceAttribute command = new UpdateInvoiceAttribute(invoiceAttributeToBeUpdated);

		try {
			if(((InvoiceAttributeUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{invoiceAttributeId}")
	public ResponseEntity<InvoiceAttribute> findById(@PathVariable String invoiceAttributeId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("invoiceAttributeId", invoiceAttributeId);
		try {

			List<InvoiceAttribute> foundInvoiceAttribute = findInvoiceAttributesBy(requestParams).getBody();
			if(foundInvoiceAttribute.size()==1){				return successful(foundInvoiceAttribute.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{invoiceAttributeId}")
	public ResponseEntity<String> deleteInvoiceAttributeByIdUpdated(@PathVariable String invoiceAttributeId) throws Exception {
		DeleteInvoiceAttribute command = new DeleteInvoiceAttribute(invoiceAttributeId);

		try {
			if (((InvoiceAttributeDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
