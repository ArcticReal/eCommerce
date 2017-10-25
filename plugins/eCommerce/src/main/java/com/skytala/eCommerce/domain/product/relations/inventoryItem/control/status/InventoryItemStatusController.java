package com.skytala.eCommerce.domain.product.relations.inventoryItem.control.status;

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
import com.skytala.eCommerce.domain.product.relations.inventoryItem.command.status.AddInventoryItemStatus;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.command.status.DeleteInventoryItemStatus;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.command.status.UpdateInventoryItemStatus;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.event.status.InventoryItemStatusAdded;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.event.status.InventoryItemStatusDeleted;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.event.status.InventoryItemStatusFound;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.event.status.InventoryItemStatusUpdated;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.mapper.status.InventoryItemStatusMapper;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.model.status.InventoryItemStatus;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.query.status.FindInventoryItemStatussBy;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Scheduler;

@RestController
@RequestMapping("/inventoryItemStatuss")
public class InventoryItemStatusController {

	private static Map<String, RequestMethod> validRequests = new HashMap<>();

	public InventoryItemStatusController() {

		validRequests.put("find", RequestMethod.GET);
		validRequests.put("add", RequestMethod.POST);
		validRequests.put("update", RequestMethod.PUT);
		validRequests.put("removeById", RequestMethod.DELETE);
	}

	/**
	 * 
	 * @param allRequestParams
	 *            all params by which you want to find a InventoryItemStatus
	 * @return a List with the InventoryItemStatuss
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<Object> findInventoryItemStatussBy(@RequestParam(required = false) Map<String, String> allRequestParams) throws Exception {

		FindInventoryItemStatussBy query = new FindInventoryItemStatussBy(allRequestParams);
		if (allRequestParams == null) {
			query.setFilter(new HashMap<>());
		}

		List<InventoryItemStatus> inventoryItemStatuss =((InventoryItemStatusFound) Scheduler.execute(query).data()).getInventoryItemStatuss();

		if (inventoryItemStatuss.size() == 1) {
			return ResponseEntity.ok().body(inventoryItemStatuss.get(0));
		}

		return ResponseEntity.ok().body(inventoryItemStatuss);

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
	public ResponseEntity<Object> createInventoryItemStatus(HttpServletRequest request) throws Exception {

		InventoryItemStatus inventoryItemStatusToBeAdded = new InventoryItemStatus();
		try {
			inventoryItemStatusToBeAdded = InventoryItemStatusMapper.map(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arguments could not be resolved.");
		}

		return this.createInventoryItemStatus(inventoryItemStatusToBeAdded);

	}

	/**
	 * creates a new InventoryItemStatus entry in the ofbiz database
	 * 
	 * @param inventoryItemStatusToBeAdded
	 *            the InventoryItemStatus thats to be added
	 * @return true on success; false on fail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createInventoryItemStatus(@RequestBody InventoryItemStatus inventoryItemStatusToBeAdded) throws Exception {

		AddInventoryItemStatus command = new AddInventoryItemStatus(inventoryItemStatusToBeAdded);
		InventoryItemStatus inventoryItemStatus = ((InventoryItemStatusAdded) Scheduler.execute(command).data()).getAddedInventoryItemStatus();
		
		if (inventoryItemStatus != null) 
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(inventoryItemStatus);
		else 
			return ResponseEntity.status(HttpStatus.CONFLICT)
					             .body("InventoryItemStatus could not be created.");
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
	public boolean updateInventoryItemStatus(HttpServletRequest request) throws Exception {

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

		InventoryItemStatus inventoryItemStatusToBeUpdated = new InventoryItemStatus();

		try {
			inventoryItemStatusToBeUpdated = InventoryItemStatusMapper.mapstrstr(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (updateInventoryItemStatus(inventoryItemStatusToBeUpdated, null).getStatusCode()
				.equals(HttpStatus.NO_CONTENT)) {
			return true;
		}
		return false;

	}

	/**
	 * Updates the InventoryItemStatus with the specific Id
	 * 
	 * @param inventoryItemStatusToBeUpdated
	 *            the InventoryItemStatus thats to be updated
	 * @return true on success, false on fail
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{nullVal}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> updateInventoryItemStatus(@RequestBody InventoryItemStatus inventoryItemStatusToBeUpdated,
			@PathVariable String nullVal) throws Exception {

//		inventoryItemStatusToBeUpdated.setnull(null);

		UpdateInventoryItemStatus command = new UpdateInventoryItemStatus(inventoryItemStatusToBeUpdated);

		try {
			if(((InventoryItemStatusUpdated) Scheduler.execute(command).data()).isSuccess()) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{inventoryItemStatusId}")
	public ResponseEntity<Object> findById(@PathVariable String inventoryItemStatusId) throws Exception {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("inventoryItemStatusId", inventoryItemStatusId);
		try {

			Object foundInventoryItemStatus = findInventoryItemStatussBy(requestParams).getBody();
			return ResponseEntity.status(HttpStatus.OK).body(foundInventoryItemStatus);
		} catch (RecordNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{inventoryItemStatusId}")
	public ResponseEntity<Object> deleteInventoryItemStatusByIdUpdated(@PathVariable String inventoryItemStatusId) throws Exception {
		DeleteInventoryItemStatus command = new DeleteInventoryItemStatus(inventoryItemStatusId);

		try {
			if (((InventoryItemStatusDeleted) Scheduler.execute(command).data()).isSuccess())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (RecordNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body("InventoryItemStatus could not be deleted");

	}

	@RequestMapping(value = (" ** "))
	public ResponseEntity<Object> returnErrorPage(HttpServletRequest request) {

		String usedUri = request.getRequestURI();
		String[] splittedString = usedUri.split("/");

		String usedRequest = splittedString[splittedString.length - 1];

		if (validRequests.containsKey(usedRequest)) {
			String returnVal = "Error: request method " + request.getMethod() + " not allowed for \"" + usedUri
					+ "\"!\n" + "Please use " + validRequests.get(usedRequest) + "!";

			return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(returnVal);
		}

		String returnVal = "Error 404: Page not found! Valid pages are: \"eCommerce/api/inventoryItemStatus/\" plus one of the following: "
				+ "";

		Set<String> keySet = validRequests.keySet();
		Iterator<String> it = keySet.iterator();

		while (it.hasNext()) {
			returnVal += "\"" + it.next() + "\"";
			if (it.hasNext())
				returnVal += ", ";
		}

		returnVal += "!";

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnVal);

	}
}
