package com.skytala.eCommerce.domain.accounting.relations.invoice.control.itemTypeMap;

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
import com.skytala.eCommerce.domain.accounting.relations.invoice.command.itemTypeMap.AddInvoiceItemTypeMap;
import com.skytala.eCommerce.domain.accounting.relations.invoice.command.itemTypeMap.DeleteInvoiceItemTypeMap;
import com.skytala.eCommerce.domain.accounting.relations.invoice.command.itemTypeMap.UpdateInvoiceItemTypeMap;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.itemTypeMap.InvoiceItemTypeMapAdded;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.itemTypeMap.InvoiceItemTypeMapDeleted;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.itemTypeMap.InvoiceItemTypeMapFound;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.itemTypeMap.InvoiceItemTypeMapUpdated;
import com.skytala.eCommerce.domain.accounting.relations.invoice.mapper.itemTypeMap.InvoiceItemTypeMapMapper;
import com.skytala.eCommerce.domain.accounting.relations.invoice.model.itemTypeMap.InvoiceItemTypeMap;
import com.skytala.eCommerce.domain.accounting.relations.invoice.query.itemTypeMap.FindInvoiceItemTypeMapsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/accounting/invoice/invoiceItemTypeMaps")
public class InvoiceItemTypeMapController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public InvoiceItemTypeMapController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a InvoiceItemTypeMap
	 * @return a List with the InvoiceItemTypeMaps
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<InvoiceItemTypeMap>> findInvoiceItemTypeMapsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindInvoiceItemTypeMapsBy query = new FindInvoiceItemTypeMapsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<InvoiceItemTypeMap> invoiceItemTypeMaps =((InvoiceItemTypeMapFound) Scheduler.execute(query).data()).getInvoiceItemTypeMaps();

		return ResponseEntity.ok().body(invoiceItemTypeMaps);

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
	public ResponseEntity<InvoiceItemTypeMap> createInvoiceItemTypeMap(HttpServletRequest request) throws Exception {

		InvoiceItemTypeMap invoiceItemTypeMapToBeAdded = new InvoiceItemTypeMap();
		try {
			invoiceItemTypeMapToBeAdded = InvoiceItemTypeMapMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createInvoiceItemTypeMap(invoiceItemTypeMapToBeAdded);

	}

	/**
	 * creates a new InvoiceItemTypeMap entry in the ofbiz database
	 * 
	 * @param invoiceItemTypeMapToBeAdded
	 *            the InvoiceItemTypeMap thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<InvoiceItemTypeMap> createInvoiceItemTypeMap(@RequestBody InvoiceItemTypeMap invoiceItemTypeMapToBeAdded) throws Exception {

		AddInvoiceItemTypeMap command = new AddInvoiceItemTypeMap(invoiceItemTypeMapToBeAdded);
		InvoiceItemTypeMap invoiceItemTypeMap = ((InvoiceItemTypeMapAdded) Scheduler.execute(command).data()).getAddedInvoiceItemTypeMap();
		
		if (invoiceItemTypeMap != null) 
			return successful(invoiceItemTypeMap);
		else 
			return conflict(null);
	}

	/**
	 * Updates the InvoiceItemTypeMap with the specific Id
	 * 
	 * @param invoiceItemTypeMapToBeUpdated
	 *            the InvoiceItemTypeMap thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{invoiceItemMapKey}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateInvoiceItemTypeMap(@RequestBody InvoiceItemTypeMap invoiceItemTypeMapToBeUpdated,
			@PathVariable String invoiceItemMapKey) throws Exception {

		invoiceItemTypeMapToBeUpdated.setInvoiceItemMapKey(invoiceItemMapKey);

		UpdateInvoiceItemTypeMap command = new UpdateInvoiceItemTypeMap(invoiceItemTypeMapToBeUpdated);

		try {
			if(((InvoiceItemTypeMapUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{invoiceItemTypeMapId}")
	public ResponseEntity<InvoiceItemTypeMap> findById(@PathVariable String invoiceItemTypeMapId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("invoiceItemTypeMapId", invoiceItemTypeMapId);
		try {

			List<InvoiceItemTypeMap> foundInvoiceItemTypeMap = findInvoiceItemTypeMapsBy(requestParams).getBody();
			if(foundInvoiceItemTypeMap.size()==1){				return successful(foundInvoiceItemTypeMap.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{invoiceItemTypeMapId}")
	public ResponseEntity<String> deleteInvoiceItemTypeMapByIdUpdated(@PathVariable String invoiceItemTypeMapId) throws Exception {
		DeleteInvoiceItemTypeMap command = new DeleteInvoiceItemTypeMap(invoiceItemTypeMapId);

		try {
			if (((InvoiceItemTypeMapDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
