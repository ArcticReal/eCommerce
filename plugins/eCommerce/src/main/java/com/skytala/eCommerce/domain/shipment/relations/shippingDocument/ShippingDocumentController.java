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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findShippingDocumentsBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindShippingDocumentsBy query = new FindShippingDocumentsBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<ShippingDocument> shippingDocuments =((ShippingDocumentFound) Scheduler.execute(query).data()).getShippingDocuments();

		if (shippingDocuments.size() == 1) {
			return ResponseEntity.ok().body(shippingDocuments.get(0));
		}

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
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createShippingDocument(HttpServletRequest request) throws Exception {

		ShippingDocument shippingDocumentToBeAdded = new ShippingDocument();
		try {
			shippingDocumentToBeAdded = ShippingDocumentMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
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
	public ResponseEntity<Object> createShippingDocument(@RequestBody ShippingDocument shippingDocumentToBeAdded) throws Exception {

		AddShippingDocument command = new AddShippingDocument(shippingDocumentToBeAdded);
		ShippingDocument shippingDocument = ((ShippingDocumentAdded) Scheduler.execute(command).data()).getAddedShippingDocument();
		
		if (shippingDocument != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(shippingDocument);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("ShippingDocument could not be created.");
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
	public boolean updateShippingDocument(HttpServletRequest request) throws Exception {

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

		ShippingDocument shippingDocumentToBeUpdated = new ShippingDocument();

		try {
			shippingDocumentToBeUpdated = ShippingDocumentMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateShippingDocument(shippingDocumentToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

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
	public ResponseEntity<Object> updateShippingDocument(@RequestBody ShippingDocument shippingDocumentToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		shippingDocumentToBeUpdated.setnull(null);

		UpdateShippingDocument command = new UpdateShippingDocument(shippingDocumentToBeUpdated);

		try {
			if(((ShippingDocumentUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{shippingDocumentId}")
	public ResponseEntity<Object> findById(@PathVariable String shippingDocumentId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("shippingDocumentId", shippingDocumentId);
		try {

			Object foundShippingDocument = findShippingDocumentsBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundShippingDocument);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{shippingDocumentId}")
	public ResponseEntity<Object> deleteShippingDocumentByIdUpdated(@PathVariable String shippingDocumentId) throws Exception {
		DeleteShippingDocument command = new DeleteShippingDocument(shippingDocumentId);

		try {
			if (((ShippingDocumentDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("ShippingDocument could not be deleted");

	}

}
