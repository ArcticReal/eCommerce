package com.skytala.eCommerce.domain.shipment.relations.shippingDocument;

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
import com.skytala.eCommerce.domain.shipment.relations.shippingDocument.command.AddShippingDocument;
import com.skytala.eCommerce.domain.shipment.relations.shippingDocument.command.DeleteShippingDocument;
import com.skytala.eCommerce.domain.shipment.relations.shippingDocument.command.UpdateShippingDocument;
import com.skytala.eCommerce.domain.shipment.relations.shippingDocument.event.ShippingDocumentAdded;
import com.skytala.eCommerce.domain.shipment.relations.shippingDocument.event.ShippingDocumentDeleted;
import com.skytala.eCommerce.domain.shipment.relations.shippingDocument.event.ShippingDocumentFound;
import com.skytala.eCommerce.domain.shipment.relations.shippingDocument.event.ShippingDocumentUpdated;
import com.skytala.eCommerce.domain.shipment.relations.shippingDocument.mapper.ShippingDocumentMapper;
import com.skytala.eCommerce.domain.shipment.relations.shippingDocument.model.ShippingDocument;
import com.skytala.eCommerce.domain.shipment.relations.shippingDocument.query.FindShippingDocumentsBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/shipment/shippingDocuments")
public class ShippingDocumentController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public ShippingDocumentController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a ShippingDocument
	 * @return a List with the ShippingDocuments
	 * @throws Exception 
	 */
	@GetMapping("/find")
	public ResponseEntity<List<ShippingDocument>> findShippingDocumentsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindShippingDocumentsBy query = new FindShippingDocumentsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ShippingDocument> shippingDocuments =((ShippingDocumentFound) Scheduler.execute(query).data()).getShippingDocuments();

		return ResponseEntity.ok().body(shippingDocuments);

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
	public ResponseEntity<ShippingDocument> createShippingDocument(HttpServletRequest request) throws Exception {

		ShippingDocument shippingDocumentToBeAdded = new ShippingDocument();
		try {
			shippingDocumentToBeAdded = ShippingDocumentMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalArgumentException();
		}

		return this.createShippingDocument(shippingDocumentToBeAdded);

	}

	/**
	 * creates a new ShippingDocument entry in the ofbiz database
	 * 
	 * @param shippingDocumentToBeAdded
	 *            the ShippingDocument thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ShippingDocument> createShippingDocument(@RequestBody ShippingDocument shippingDocumentToBeAdded) throws Exception {

		AddShippingDocument command = new AddShippingDocument(shippingDocumentToBeAdded);
		ShippingDocument shippingDocument = ((ShippingDocumentAdded) Scheduler.execute(command).data()).getAddedShippingDocument();
		
		if (shippingDocument != null) 
			return successful(shippingDocument);
		else 
			return conflict(null);
	}

	/**
	 * Updates the ShippingDocument with the specific Id
	 * 
	 * @param shippingDocumentToBeUpdated
	 *            the ShippingDocument thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateShippingDocument(@RequestBody ShippingDocument shippingDocumentToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		shippingDocumentToBeUpdated.setnull(null);

		UpdateShippingDocument command = new UpdateShippingDocument(shippingDocumentToBeUpdated);

		try {
			if(((ShippingDocumentUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return noContent();	
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();
	}

	@GetMapping("/{shippingDocumentId}")
	public ResponseEntity<ShippingDocument> findById(@PathVariable String shippingDocumentId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("shippingDocumentId", shippingDocumentId);
		try {

			List<ShippingDocument> foundShippingDocument = findShippingDocumentsBy(requestParams).getBody();
			if(foundShippingDocument.size()==1){				return successful(foundShippingDocument.get(0));
			}else{
				return notFound();
			}
		} catch (RecordNotFoundException e) {

			return notFound();
		}

	}

	@DeleteMapping("/{shippingDocumentId}")
	public ResponseEntity<String> deleteShippingDocumentByIdUpdated(@PathVariable String shippingDocumentId) throws Exception {
		DeleteShippingDocument command = new DeleteShippingDocument(shippingDocumentId);

		try {
			if (((ShippingDocumentDeleted) Scheduler.execute(command).data()).isSuccess())
				return noContent();
		} catch (RecordNotFoundException e) {
			return notFound();
		}

		return conflict();

	}

}
